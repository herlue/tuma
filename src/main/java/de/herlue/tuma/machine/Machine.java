package de.herlue.tuma.machine;

import java.util.*;

public class Machine {
  private Set<String> states;
  private Set<Character> symbols;
  private Map<TransitionKey, Transition> transitions;
  private Tape tape;

  public Machine(
    Set<String> states,
    Set<Character> symbols,
    Map<TransitionKey, Transition> transitions
  ) {
    this.states = states;
    this.symbols = symbols;
    this.transitions = transitions;
    this.tape = new Tape();
  }

  // Transition getTransition(TransitionKey key) {
  //   return transitions.get(key);
  // }

  // Transition getTransition(String state, Character symbol) {
  //   return getTransition(new TransitionKey(state, symbol));
  // }

  // void addTransition(TransitionKey key, Transition transition) {
  //   if (transitions.putIfAbsent(key, transition) != null)
  //     throw new IllegalArgumentException(
  //       String.format("transition for (%s, %c) already exists\n", key.state(), key.symbol())
  //     );
  // }

  // void addTransition(String state, char symbol, Transition transition) {
  //   addTransition(new TransitionKey(state, symbol), transition);
  // }

  public StringBuilder printTape() {
    StringBuilder sb = new StringBuilder();
    for (int offset = -5; offset <= 5; offset++)
      sb.append(tape.read(offset));
    
    return sb;
  }

  public boolean simulate(String word) {
    tape.write(word);
    
    String state = "q0";
    char symbol;
    TransitionKey key;
    Transition transition;

    while (!state.equals("q+") && !state.equals("q-")) {
      symbol = tape.read();
      key = new TransitionKey(state, symbol);
      if (!transitions.containsKey(key))
        return false; // transition does not exists -> stuck!

      transition = transitions.get(key); // todo: use return value to check for existence
      state = transition.nextState();
      tape.write(transition.writeSymbol());
      tape.move(transition.movement());

      StringBuilder sb = printTape();
      sb.append(String.format("\t%s\n     ^\n", transition));
      System.out.print(sb);

      new Scanner(System.in).nextLine(); // press enter to see next transition
    }

    if (state.equals("q-"))
      return false;

    return true;
  }
}
