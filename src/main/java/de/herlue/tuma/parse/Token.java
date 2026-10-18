package de.herlue.tuma.parse;

enum TokenType {
  ERROR,
  END,
  LPAREN,
  RPAREN,
  SYMBOL,
  STRING,
  STATE_INITIAL,
  STATE_ACCEPT,
  STATE_REJECT,
  MOVEMENT,
  COMMA,
  ARROW
};

class Token {
  private TokenType type;
  private Span span;
  private String lexeme;

  Token(TokenType type, Span span, String lexeme) {
    this.type = type;
    this.span = span;
    this.lexeme = lexeme;
  }

  TokenType getType() {
    return type;
  }

  Span getSpan() {
    return span;
  }

  String getLexeme() {
    return lexeme;
  }
}
