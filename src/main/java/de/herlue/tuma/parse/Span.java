package de.herlue.tuma.parse;

class Span {
  private Position start;
  private Position end;

  Span(Position start, Position end) {
    this.start = start;
    this.end = end;
  }

  Position getStart() {
    return start;
  }

  Position getEnd() {
    return end;
  }
}
