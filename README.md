# Battleship Game

Welcome to the **Battleship Game**! This is a two-player, turn-based strategy game where players try to sink each other's ships by guessing their positions on a grid. The game is built entirely in Java and allows each player to place their ships on a 5x5 board before taking turns firing shots to try to destroy the opponent's fleet.

## Game Overview

- **Board Size**: The game is played on a 5x5 grid.
- **Number of Ships**: Each player has 5 ships to place on their board.
- **Gameplay**: Players take turns guessing the coordinates of the opponent’s ships. If a player guesses correctly, it’s a hit, and if not, it’s a miss.
- **Winning Condition**: The first player to sink all of the opponent’s ships wins the game.

### Game Flow

1. **Ship Placement**: 
   - Each player enters the coordinates of their 5 ships, one by one, without the opponent seeing the placements.
   - Ships are placed by specifying the row and column (e.g., 1 3).

2. **Taking Turns**:
   - Players alternate turns entering coordinates to fire a shot at the opponent’s grid.
   - The game provides feedback on whether the shot was a hit or a miss.
   - If a shot hits a ship, it is marked with an 'X' on the board. Misses are marked with an 'O'.
   
3. **Winning the Game**:
   - The game continues until one player successfully sinks all of the opponent’s ships, at which point the winner is declared.

### Features

- **Input Validation**: The game ensures that players input valid coordinates and prevents them from choosing the same location twice.
- **Board Visibility**: After every shot, the game displays the current player's hit/miss history on the opponent's board, but never reveals the entire board of the opponent.
- **Turn-based**: Players alternate turns, with clear indicators of whose turn it is.

## How to Play

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/ayahesham1/Battleship.git
