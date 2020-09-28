# dice-game
 
 
 A game of dice for N players and M target points
 
 
## Requirements
- Jdk 1.8 or above

## Game rules
- This is a mltiplayer dice game and player wins the game after achieving user defined target points
- The player will get extra chance to roll the dice if the players gets 6 in any turn
- The player wil be penalised with a chance if they get two consective ones


## How to run

The following commads are required to start and user the application

Go inside src folder and run 
```bash
javac DiceGame.java
```

```bash
java DiceGame.class arg1 arg2
```

- arg1 is number of players
- arg2 is points required to win the game


## Test Cases
- Invalid test cases
1. java DiceGame.class
2. java DiceGame.class 0 0
3. java DiceGame.class 2 0
4. java DiceGame.class 0 3

- Valid test cases
1. java DiceGame.class 2 3
2. java DiceGame.class 30 2000
