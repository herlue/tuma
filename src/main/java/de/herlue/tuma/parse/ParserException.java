package de.herlue.tuma.parse;

public class ParserException extends Exception {
  ParserException(Token actual, String expected) {
    super(formatMessage(actual, expected));
  }

  private static String formatMessage(Token actual, String expected) {
    Position start = actual.getSpan().getStart();

    String found = actual.getType() == TokenType.END
      ? "end of line"
      : String.format("token '%s'", actual.getLexeme());

    return String.format(
      "unexpected %s at %d:%d - expected '%s'",
      found,
      start.getLine(),
      start.getColumn() + 1,
      expected
    );
  }
}
