package de.herlue.tuma.machine;

import java.util.*;

class Tape {
  private Map<Integer, Character> cells;
  private int head;
  public static final char BLANK = '_';

  Tape() {
    cells = new HashMap<>();
    head = 0;
  }

  int getHead() {
    return head;
  }

  char read() {
    return cells.getOrDefault(head, BLANK);
  }

  char read(int offset) {
    return cells.getOrDefault(head + offset, BLANK);
  }

  void write(char symbol) {
    if (symbol == BLANK)
      cells.remove(head);
    else
      cells.put(head, symbol);
  }

  void write(String string) {
    int savedHead = head;
    for (char symbol : string.toCharArray()) {
      write(symbol);
      move(Movement.RIGHT);
    }
    head = savedHead;
  }

  void move(Movement movement) {
    switch (movement) {
      case LEFT:
        head--;
        break;
      case RIGHT:
        head++;
        break;
      case NONE:
      default:
        break;
    }
  }
}