package de.herlue.tuma.machine;

public record Transition(String nextState, char writeSymbol, Movement movement) {
  @Override
  public String toString() {
    return String.format("(%s, %c, %s)", nextState, writeSymbol, movement);
  }
}
