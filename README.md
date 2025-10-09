# Rubik's Cube CLI (Java)

A tiny console app that simulates a 3×3 Rubik’s Cube: apply moves, randomize, simplify history, and undo back to solved.

This project was assigned for a computer science class, and we were encourged to come up with creative solutions for implementing a rubik's cube by modifying multidimensional arrays. We noticed that any move on a rubik's cube could be accomplished by rotating the cube and applying a single move (in our case, we used 'U' as our base move). While there are more concise methods to implement a Rubik's cube, this was a fun exercise in manipulating three dimensional arrays.

## Features
- Apply standard face turns: `U/D/L/R/F/B1`, primes (`'`), and doubles (`2`)
- Randomize with n random moves
- Simplify a move history with local rules (e.g., `U U → U2`, `U U' → ⌀`, `U2 U' → U`)
- Print raw tokens or a colorized block view (ANSI truecolor)
- Solve by inverting the move history

## Commands
```
Moves:     U, U', U2, D, D', D2, R, R', R2, L, L', L2, F, F', F2, B, B', B2
SOLVE      Undo all moves and return cube to solved state
RANDOMIZE  Shuffle the cube with <n> random moves
SIMPLIFY   Shorten and clean up the move history
E          Display the current cube state
M          Show the menu
Q          Quit
```

> Note: Doubles use the canonical form X2. Inputs like X2' are not required/expected.

## Requirements
- Java 11+ (uses String.repeat)
- A terminal that supports 24-bit ANSI colors (for the pretty print). Examples: Windows Terminal, iTerm2, modern VS Code terminal.


