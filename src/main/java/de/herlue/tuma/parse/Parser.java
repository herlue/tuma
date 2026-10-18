package de.herlue.tuma.parse;

import java.io.*;
import java.util.*;
import de.herlue.tuma.machine.*;

public class Parser {
  public static Machine parse(String filename) throws IOException, ParserException {
    Lexer lexer = new Lexer();

    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      Token token;

      String state, nextState;
      Character symbol, writeSymbol;
      Movement movement;

      Set<String> states = new HashSet<>();
      Set<Character> symbols = new HashSet<>();
      Map<TransitionKey, Transition> transitions = new HashMap<>();

      while ((line = br.readLine()) != null) {
        lexer.initialize(line.toCharArray());

        token = lexer.getNextToken();
        if (token.getType() != TokenType.LPAREN)
          throw new ParserException(token, "(");

        token = lexer.getNextToken();
        switch (token.getType()) {
          case SYMBOL:
          case STRING:
          case STATE_INITIAL:
            break;
          default:
            throw new ParserException(token, "<non final state>");
        }
        state = token.getLexeme();

        token = lexer.getNextToken();
        if (token.getType() != TokenType.COMMA)
          throw new ParserException(token, ",");

        token = lexer.getNextToken();
        if (token.getType() != TokenType.SYMBOL)
          throw new ParserException(token, "<symbol>");
        symbol = token.getLexeme().charAt(0);

        token = lexer.getNextToken();
        if (token.getType() != TokenType.RPAREN)
          throw new ParserException(token, ")");

        token = lexer.getNextToken();
        if (token.getType() != TokenType.ARROW)
          throw new ParserException(token, "->");
        
        token = lexer.getNextToken();
        if (token.getType() != TokenType.LPAREN)
          throw new ParserException(token, "(");

        token = lexer.getNextToken();
        switch (token.getType()) {
          case SYMBOL:
          case STRING:
          case STATE_INITIAL:
          case STATE_ACCEPT:
          case STATE_REJECT:
            break;
          default:
            throw new ParserException(token, "<state>");
        }
        nextState = token.getLexeme();

        token = lexer.getNextToken();
        if (token.getType() != TokenType.COMMA)
          throw new ParserException(token, ",");

        token = lexer.getNextToken();
        if (token.getType() != TokenType.SYMBOL)
          throw new ParserException(token, "<symbol>");
        writeSymbol = token.getLexeme().charAt(0);

        token = lexer.getNextToken();
        if (token.getType() != TokenType.COMMA)
          throw new ParserException(token, ",");
        
        token = lexer.getNextToken();
        if (token.getType() != TokenType.MOVEMENT)
          throw new ParserException(token, "< | > | ^");
        movement = switch (token.getLexeme().charAt(0)) {
          case '<' -> Movement.LEFT;
          case '>' -> Movement.RIGHT;
          case '^' -> Movement.NONE;
          default -> throw new ParserException(token, "< | > | ^");
        };

        token = lexer.getNextToken();
        if (token.getType() != TokenType.RPAREN)
          throw new ParserException(token, ")");

        token = lexer.getNextToken();
        if (token.getType() != TokenType.END)
          throw new ParserException(token, "end of line");

        states.add(state);
        states.add(nextState);
        symbols.add(symbol);
        symbols.add(writeSymbol);
        // todo: check if transition already exists before adding
        TransitionKey key = new TransitionKey(state, symbol);
        Transition transition = new Transition(nextState, writeSymbol, movement);

        transitions.put(key, transition);
      }
      
      return new Machine(states, symbols, transitions);
    }
  }
}
