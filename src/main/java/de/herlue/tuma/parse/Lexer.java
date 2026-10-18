package de.herlue.tuma.parse;

import java.util.Arrays;

class Lexer {
  private Position position;
  private char[] line;

  Lexer() {
    position = new Position();
  }

  void initialize(char[] line) {
    this.line = line;
    position.nextLine();
  }

  boolean isAtEnd() {
    return position.getColumn() == line.length;
  }

  char getCurrentChar() {
    if (null == line || isAtEnd())
      return '\0';

    return line[position.getColumn()];
  }

  char getNextChar() {
    if (null == line || isAtEnd())
      return '\0';

    char currentChar = line[position.getColumn()];
    position.nextColumn();

    return currentChar;
  }

  Token getNextToken() {
    char currentChar;

    while (Character.isWhitespace((currentChar = getCurrentChar())))
      getNextChar();

    Position start = Position.of(position);
    
    if ('\0' == currentChar)
      return new Token(TokenType.END, makeSpan(start), null);

    getNextChar();
    TokenType type;

    if (isSymbol(currentChar)) {

      type = TokenType.SYMBOL;

      if (currentChar == 'q') {
        switch (getCurrentChar()) {
          case '0':
            type = TokenType.STATE_INITIAL;
            break;
          case '+':
            type = TokenType.STATE_ACCEPT;
            break;
          case '-':
            type = TokenType.STATE_REJECT;
            break;
          default: break;
        }

        if (type != TokenType.SYMBOL) {
          getNextChar();
          return makeToken(type, start);
        }
      }

      while (isSymbol(getCurrentChar()))
        getNextChar();

      if (position.getColumn() - start.getColumn() > 1)
        type = TokenType.STRING;

      return makeToken(type, start);
    }

    switch (currentChar) {
      case '(':
        type = TokenType.LPAREN;
        break;
      case ')':
        type = TokenType.RPAREN;
        break;
      case '<':
      case '>':
      case '^':
        type = TokenType.MOVEMENT;
        break;
      case ',':
        type = TokenType.COMMA;
        break;
      case '-':
        if (getCurrentChar() != '>') {
          type = TokenType.ERROR;
        } else {
          getNextChar();
          type = TokenType.ARROW;
        }
        break;
      default:
        type = TokenType.ERROR;
    }

    return makeToken(type, start);
  }

  Span makeSpan(Position start) {
    return new Span(start, Position.of(position));
  }

  Token makeToken(TokenType type, Position start) {
    char[] lexeme = Arrays.copyOfRange(line, start.getColumn(), position.getColumn());
    return new Token(type, makeSpan(start), String.valueOf(lexeme));
  }

  // todo: maybe move to utils
  static boolean isSymbol(char c) {
    return Character.isLetterOrDigit(c) || c == '_';
  }
}
