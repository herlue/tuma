# Tuma

A small Turing machine simulator written in Java. The machine reads its transitions from a `.tm` file and executes them step by step on a tape.

## Getting started

Requires Java 17 or newer.

```bash
javac -d out src/main/java/de/herlue/tuma/*.java src/main/java/de/herlue/tuma/machine/*.java src/main/java/de/herlue/tuma/parse/*.java
java -cp out de.herlue.tuma.Main main.tm
```

After starting the program, enter an input word and press Enter. Keep pressing Enter to advance the simulation. A section of the tape and the executed transition are displayed at each step. The simulation ends with `SUCCESS` or `FAILURE`.

## Transitions

Each line in the `.tm` file defines a transition:

```text
(q0, 0) -> (seek, X, >)
```

When the machine reads `0` in state `q0`, this transition writes `X`, moves the head to the right, and switches to state `seek`.

- Movements: `<` (left), `>` (right), `^` (stay)
- Blank symbol: `_`
- Initial state: `q0`
- Halting states: `q+` (accept) and `q-` (reject)

The included `main.tm` recognizes words of the form `0ⁿ1ⁿ` for `n ≥ 0`.

## Future plans

- **Multi-tape support:** Support for Turing machines with multiple tapes.
- **Improved display / UI:** A clearer view of the tape, head position, and current state, along with more convenient simulation controls.
