package de.herlue.tuma.machine;

public record Transition(String nextState, char writeSymbol, Movement movement) {}
