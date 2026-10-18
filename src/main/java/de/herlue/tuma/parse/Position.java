package de.herlue.tuma.parse;

class Position {
  private int line;
  private int column;

  Position() {
    line = 0;
    column = 0;
  }

  Position(int line, int column) {
    this.line = line;
    this.column = column;
  }

  static Position of(Position position) {
    return new Position(position.getLine(), position.getColumn());
  }

  int getLine() {
    return line;
  }

  int getColumn() {
    return column;
  }

  void nextLine() {
    line++;
    column = 0;
  }

  void nextColumn() {
    column++;
  }
}
