import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DiceGame {

	public static void main(String[] args) {
		
		// Assign no of player and max points to N and M from command line arguments
		int N = 1;
		int M = 10;
		
		if(args.length < 2) {
			System.out.println("Please provide valid inputs for number of players and minimum points");
			return;
		}
		
		// If number of players is invalid then display error message and terminate the process
		try {
			 N = Integer.parseInt(args[0]);
			 
			 // Considering N=0 as invalid
			 if(N == 0) throw new NumberFormatException();
		} catch(NumberFormatException e) {
			System.out.println("Please enter valid number of players more than 1");
			return;
		}
		
		// If minimum points is invalid then display error message and terminate the process
		try {
			 M = Integer.parseInt(args[1]);
			 
			// Considering N=0 as invalid
			 if(M == 0) throw new NumberFormatException();
		} catch(NumberFormatException e) {
			System.out.println("Please enter valid minimum points more than 1");
			return;
		}
        
        System.out.println("Dice game initialized for "+N+" players");
        System.out.println("Achieve "+M+" points to win the game");
        
        // Initialize the scanner to scan player input
        Scanner sc= new Scanner(System.in);
        
        /**
         * Initialize the ranks array of number of players
         * Initialize array to store last turn value of each player
         * Initialize rank to assign each player when they cross max points
         */
        int ranks[] = new int[N+1];
        int lastTurn[] = new int[N+1];
        int lastRank = 0;

        // Initialize players 
        Player players[] = new Player[N+1];
        for(int i=1; i<=N; i++) {
        	players[i] = new Player("Player "+i, 0);
        }
        
        // Initialize the list with shuffled order of players
        List<Integer> playersOrder = IntStream.rangeClosed(1, N)
        	    .boxed().collect(Collectors.toList());
        Collections.shuffle(playersOrder);
        
        System.out.println("#### Order of Players ####");
        for(int i=0; i<playersOrder.size(); i++) {
        	System.out.println("    Player "+playersOrder.get(i));
        }
        
        System.out.println("All the best");
        
        Dice dice = new Dice();
        
        int currPlayer = 0;
        int pointsInCurrTurn = 0;
        int diceVal = 0;
        
        /**
         * Game Algorithm:
         * 1. Check if the current player can play in the current round - while loops refers to a single round
         * 2. Save the current round points and check for 1 and 6
         * 3. If the current dice points is 6, give player another change and update the current round points
         * 4. If the current dice points is 1, check if last round value is also 1. 
         * 5. If the above step result is true then update lastTurn value of the current player to -1 else go to step 6
         * 6. If the result of step 4 is false, update player points and check if the player achieved the points required
         * 7. If any player achieved max points in step 6. Then remove the players from further round and assign a rank to the player
         * 8. If the players in any round is 1. Then terminate the game and assign the last player last rank
         */
        while(playersOrder.size() > 0) {
        	int remPlayers = playersOrder.size();
        	
        	for(int i=0 ;i<remPlayers; i++) {
        		currPlayer = playersOrder.get(i);
        		if(lastTurn[currPlayer] != -1) {
        			
        			System.out.println("Player "+currPlayer+" it's your turn. Press 'r' to roll the dice");
        			char playerInput = sc.next().charAt(0);  
        			
        			while(playerInput != 'r') {
        				System.out.println("Player "+currPlayer+" it's your turn. Press 'r' to roll the dice");
        				playerInput = sc.next().charAt(0);
        			}
        			
        			diceVal = dice.toss();
        			System.out.println("Dice Value :: " + diceVal);
        			pointsInCurrTurn = diceVal;
        			
        			// If the current and last dice value of player is 1 skip next round for that particular player
        			if(diceVal == 1 && lastTurn[currPlayer]==1) {
        				lastTurn[currPlayer] = -1;
        				System.out.println("Player "+currPlayer+". You have lost your next chance to roll :(");
        			} else {
        				lastTurn[currPlayer] = diceVal;
        			}
        			
        			while(diceVal == 6) {
        				System.out.println("Yay!!! Player "+currPlayer+". You won an extra change!. Press 'r' to roll the dice again");
        				playerInput = sc.next().charAt(0);
        				while(playerInput != 'r') {
            				System.out.println("Player "+currPlayer+" it's your turn. Press 'r' to roll the dice");
            				playerInput = sc.next().charAt(0);
            			}
        				
        				diceVal = dice.toss();
        				lastTurn[currPlayer] = diceVal;
        				System.out.println("Dice Value :: " + diceVal);
        				pointsInCurrTurn += diceVal;
        			}
        			
        			players[currPlayer].addPoints(pointsInCurrTurn);
        			
        			if(players[currPlayer].getPoints() >= M) {
        				playersOrder.remove(i);
        				i--;
        				remPlayers--;
        				ranks[currPlayer] = ++lastRank;
        			}
        			
        			System.out.println(" *********** Score Board *********** ");
        			System.out.println(" Player Name | Points | Rank");
        			for(int j=1 ; j<ranks.length; j++) {
        				System.out.println(" Player "+j+ "       "+players[j].getPoints()+"     " +ranks[j]);
        			}
        			
        		} else {
        			// Reset the players chance to play next round
        			lastTurn[currPlayer] = 0;
        		}
        	}
        }
        
        sc.close();
        // Assign rank for last player
        ranks[playersOrder.get(0)] = ++lastRank;
        
        System.out.println(" *********** Final Score Board *********** ");
		System.out.println(" Player Name | Rank");
		for(int j=1 ; j<ranks.length; j++) {
			System.out.println(" Player "+j+ "       "+ranks[j]);
		}
	}

}
