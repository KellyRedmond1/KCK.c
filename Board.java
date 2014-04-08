// Team Name: KCK.c
// Colin Burke, 12340831
// Katie Aragane, 12371116
// Kelly Redmond, 12446372

import java.util.*;


public class Board 
{
	private static final int[] RESET = {0,0,0,0,0,0,5,0,3,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,2,0};	// holds the positions of the starting checkers
	private static final int BAR = 25;	// index of the bar
	private static final int OFF = 0;	// index of the bar off
	private static final int INNER_END = 6;	// index for the end of the inner board
	private static final int NUM_PIPS = 26;	// including bar and bear off 
	private static final int NUM_PLAYERS = 2;	
	private static final int NUM_CHECKERS = 15;
	public static final int WHITE_PLAYER = 0;	// for the checkers array
	public static final int RED_PLAYER = 1;	// for the checkers array
	
	// FOR DEBUG ONLY: mock resets to test bear off:
	// Backgammon
	//private static final int[] RESET = {14,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
	// Gammon
	//private static final int[] RESET = {14,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	//Single
	//private static final int[] RESET = {14,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	
	
	// 2D array of checkers
	// 1st index: 0 = White's checkers, 1 = Red's checkers
	// 2nd index: number of checkers on each pip, 0 to 25
	// pip 0 is bear off, pip 25 is the bar, pips 1-24 make up the actual board
	private int[][] checkers = new int[2][26];
	
	
	Board()	// resets the board
	{
		for(int p = 0; p < NUM_PLAYERS; p++)
		{
			for(int i = 0; i< NUM_PIPS; i++)
			{
				checkers[p][i] = RESET[i];	// makes the boards for both players equal to the starting board
			}
		}
		
		return;
	}
	
	public int opposingPlayer(int playerID)
	{
		int oppPlayerID;
		
		if(playerID == WHITE_PLAYER)
		{
			oppPlayerID = RED_PLAYER;
		}
		
		else
		{
			oppPlayerID = WHITE_PLAYER;
		}
		
		return oppPlayerID;
	}
		
	public int opposingPip(int pip)
	{
		return BAR - pip;
	}
	
	public int[] roll(int numDice)	// roll one or two dice
	{
		int[] dice = new int[numDice];
		
		for(int i = 0; i < numDice; i++)
		{
			dice[i] = 1 + (int)(Math.random()*6);
		}
		
		return dice;
	}
	
	public boolean doPlay (int playerID, Play play) 	// move checkers
	{
		int startPip, endPip, oppLastChecker;
		int oppPlayerID, oppPip;
		Move move;
		boolean finished;
		
		oppPlayerID = opposingPlayer(playerID);
	
	    for(int i = 0; i < play.length(); i++) 
	    {
	    	move = play.getMove(i);
	    	startPip = move.getFromPip();
	    	checkers[playerID][startPip] -= 1;
    		endPip = startPip - move.getByPips();
     		
    		if (endPip < OFF)	// check for long bear offs
    		{						
	    		endPip = OFF;   
     		}
    		
	    	checkers[playerID][endPip] += 1;
	    	oppPip = opposingPip(endPip);
	    	
	    	if ((checkers[oppPlayerID][oppPip]==1) && (endPip!=BAR) && (endPip!=OFF))	// check for hit
	    	{	
	    		checkers[oppPlayerID][oppPip] -= 1;
	    		checkers[oppPlayerID][BAR] += 1;
	    	}
	    }	
	    
	    if (checkers[playerID][OFF] == NUM_CHECKERS) 
	    {
	    	displayChecker(playerID);
	    	System.out.print(" WINS ");
	    	oppLastChecker = BAR;
	    	
	    	while (checkers[oppPlayerID][oppLastChecker] == 0)
	    	{
	    		oppLastChecker--;
	    	}
	    	
	    	if (oppLastChecker >= opposingPip(INNER_END)) 
	    	{
	    		System.out.println("A BACKGAMMON!!!");
	    	}
	    	
	    	else if (oppLastChecker > INNER_END) 
	    	{
	    		System.out.println("A GAMMON!!");
	    	}
	    	
	    	else {
	    		System.out.println("A SINGLE!");
	    	}
	    	
	    	finished = true;
	    }
	    
	    else
	    {
	    	finished = false;
	    }
	    
	   return finished;
	}
	
	public static void displayChecker(int playerID)
	{
		if(playerID == WHITE_PLAYER)
		{
			System.out.print("X");
		}
		
		else
		{
			System.out.print("O");
		}
		
		return;
	}
	
	private void displayPip(int pip)	// display the number of checkers on a pip
	{
		int oppPip = opposingPip(pip);
		
		if (checkers[WHITE_PLAYER][pip] > 0) 
		{
			displayChecker(WHITE_PLAYER);
			System.out.print(checkers[RED_PLAYER][pip] + "  ");
		}
		
		else
		{	
			if(checkers[RED_PLAYER][oppPip] > 0) 
			{
				displayChecker(RED_PLAYER);
				System.out.print(checkers[RED_PLAYER][oppPip] + "  ");
			}
			
			else 
			{
				System.out.print("|   ");		
			}
			
		return;
		}
	}
	
	private void displayOffBoard (int playerID, int pip) 
	{
		// display the number of checkers on the bar or on the bear off
		if (checkers[playerID][pip] > 0) 
		{
			displayChecker(playerID);
			System.out.print(checkers[playerID][pip] + "  "); 
		}
		
		else
		{
			System.out.print("    ");
		}
		
	return;
	
	}
	
	// display the whole board
	// board is facing player 0 (White), so red home is top right and white home is bottom left
	// player 0 (White) is playing anti-clockwise, player 1 (Red) is playing clockwise
	public void displayBoard(int playerID)
	{
		// far boards (on the top)
		if(playerID == WHITE_PLAYER)
		{
			System.out.println("13--+---+---+---+---18 BAR  19--+---+---+---+---24  OFF");
		}
		
		else
		{
			System.out.println("12--+---+---+---+---07 BAR  06--+---+---+---+---01  OFF");
		}
		
		for(int i = 13; i <= 18; i++)
		{
			displayPip(i);	// player 1 outer board
		}
		
		displayOffBoard(RED_PLAYER, BAR);	// player 0 bar
		
		for(int i = 19; i <= 24; i++)
		{
			displayPip(i);	// player 1 inner board
		}
		
		displayOffBoard(RED_PLAYER, OFF);	// player 1 bear off
		
		System.out.println("");
		
		// separator
		System.out.println("");
		
		// near boards
		for(int i = 12; i >= 7; i--)
		{
			displayPip(i);	// player 0 outer board
		}
		
		displayOffBoard(RED_PLAYER, BAR);	// player 1 bar
		
		for(int i = 6; i >= 1; i--)
		{
			displayPip(i);	// player 0 inner board
		}
		
		displayOffBoard(WHITE_PLAYER, OFF);	 // player 0 bear off
		System.out.println("");
		
		if(playerID == WHITE_PLAYER)
		{
			System.out.println("12--+---+---+---+---07 BAR  06--+---+---+---+---01  OFF");
		}
		
		else
		{
			System.out.println("13--+---+---+---+---18 BAR  19--+---+---+---+---24  OFF");			
		}
		
		System.out.println("");
		
	return;
	}

	public boolean checkPlay(int playerID, Play play, Dice dice)
	{
		int startPip, byPips, endPip, oppPlayerID, oppEndPip, lastChecker;
		int[] updateCheckers = new int[NUM_PIPS];
		
		ArrayList<Integer> updateDice = new ArrayList<Integer>(Play.MAX_MOVES);
		
		Move move;
		
		boolean valid = true, longBearOff;	// unless proven otherwise
		
		for(int i = 0; i< NUM_PIPS; i++)
		{
			updateCheckers[i] = 0;
		}
		
		updateDice.add(dice.getDie(0));
		updateDice.add(dice.getDie(1));
		
		if(dice.getDie(0) == dice.getDie(1))	// double roll
		{
			updateDice.add(dice.getDie(0));
			updateDice.add(dice.getDie(1));
		}
		
		lastChecker = BAR;
		
		while(checkers[playerID][lastChecker] == 0)
		{
			lastChecker--;
		}
		
		for(int i = 0; i < play.length(); i++)
		{
			move = play.getMove(i);
			startPip = move.getFromPip();
			byPips = move.getByPips();
			endPip = startPip - byPips;
			
			if(endPip < 0)
			{
				endPip = 0;
				longBearOff = true;
			}
			
			else
			{
				longBearOff = false;
			}
			
			oppPlayerID = opposingPlayer(playerID);
			oppEndPip = opposingPip(endPip);
			
			if((startPip < OFF) || (startPip > BAR))
			{
				System.out.println("Invalid pip number: " + startPip);
				valid = false;
			}
			
			else if(checkers[playerID][startPip] + updateCheckers[startPip] < 1)
			{
				System.out.println("Not enough checkers at pip: " + startPip);
				valid = false;
			}
			
			else if (checkers[playerID][startPip] + updateCheckers[startPip] < 1)
			{
	    		System.out.println("Not enough checkers at pip: " + startPip);
	    		valid = false;
	    	}
			
	    	else if (!updateDice.contains(byPips)) 
	    	{
	    		System.out.println("Roll not available: " + byPips);
	    		valid = false;
	    	}
			
	    	else if ((endPip < BAR) && (endPip > OFF) && (checkers[oppPlayerID][oppEndPip] > 1))
	    	{
	    		System.out.println("Block at pip " + endPip);
	    		valid = false;
	    	}
			
	    	else if ((endPip == OFF) && (lastChecker > INNER_END))
	    	{
	    		System.out.println("Can't bear off yet");
	    		valid = false;
	    	}
			
	    	else if (longBearOff && (startPip < lastChecker)) 
	    	{
	    		System.out.println("Can only bear off the last man with a high roll");
	    		valid = false;
	    	}
	    	else if ((startPip != BAR) && (checkers[playerID][BAR] + updateCheckers[BAR] != 0)) 
	    	{
	    		System.out.println("Must move the checker on the bar first");
	    		valid = false;
	    	}
			
	    	else 
	    	{
	    		updateCheckers[startPip]--;
	    		updateCheckers[endPip]++;
	    		updateDice.remove(updateDice.indexOf(byPips));
	    		
	    		if (startPip == lastChecker)
	    		{
	    			while (checkers[playerID][lastChecker] + updateCheckers[lastChecker] == 0)
	    			{
	    				lastChecker--;
	    			}
	    		}

	    	}
		}
		
	return valid;
	
	}
	
	public void allPossiblePlays(int[] board, int[] dice, Move move)
	{
		int i, location;
	    int die = dice[i];
	    
	    
	    for (i =0 ; i <= 24; i++) 
	    {
	      if (board[i] > 0) 
	      {
	        location = endPip(i,die);

	        boolean valid = ( location <= OFF && valid( Board, dice[dice], i, location ) );
	        if ( valid )
	        {
	          int [] boardcopy = new int[RESET];
	          System.arraycopy( board, 0, boardcopy, 0, RESET );
	        
	          Move(board, i, to);

	          allPossiblePlays(boardcopy, dice, move);
	          attemptedMoves ++;
	        }
	      }
	    }
		
	}
	
}

