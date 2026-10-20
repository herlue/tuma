package de.herlue.tuma.machine;

public enum Movement {
  LEFT,
  RIGHT,
  NONE;

  @Override
  public String toString() {
    switch (this) {
      case LEFT : return "<";
      case RIGHT: return ">";
      case NONE : return "^";
    }
    return null;
  }
}
