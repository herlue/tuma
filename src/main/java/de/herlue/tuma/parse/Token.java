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

  Token(TokenType type, Span span) {
    this.type = type;
    this.span = span;
  }

  TokenType getType() {
    return type;
  }

  Span getSpan() {
    return span;
  }
}
