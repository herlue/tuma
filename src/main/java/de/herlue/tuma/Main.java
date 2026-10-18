package de.herlue.tuma;

import java.util.Scanner;
import de.herlue.tuma.parse.Parser;
import de.herlue.tuma.machine.Machine;

public class Main {
  public static void main(String[] args) {
    try {
      Machine machine = Parser.parse(args[0]);
      Scanner scanner = new Scanner(System.in);
      String input;

      input = scanner.nextLine();

      System.out.println(
        machine.simulate(input) ? "SUCCESS" : "FAILURE"
      );
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
