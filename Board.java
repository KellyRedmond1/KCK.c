// Team name: KCK.c
// Colin Burke, 12340831
// Katie Aragane, 12371116
// Kelly Redmond, 12446372

//Edited

public class Board
{
	private static final int[] RESET = {0,0,0,0,0,0,5,0,3,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,2,0};	// holds the positions of the starting checkers
	private static final int BAR = 25;	// index of the bar
	private static final int OFF = 0;	// index of the bar off
	public static final int WHITE_PLAYER = 0;	// for the checkers array
	public static final int RED_PLAYER = 1;	// for the checkers array
	
	// 2D array of checkers
	// 1st index: 0 = White's checkers, 1 = Red's checkers
	// 2nd index: number of checkers on each pip, 0 to 25
	// pip 0 is bear off, pip 25 is the bar, pips 1-24 make up the actual board
	private int[][] checkers = new int[2][26];	
	
	
	Board()	// resets the board
	{
		for(int p = 0; p < 2; p++)
		{
			for(int i = 0; i < 26; i++)
			{
				checkers[p][i] = RESET[i];	// makes the boards for both players equal to the starting board
			}
		}
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
	
	public void doPlay(int player, Play play)	// move checkers
	{
		int startPip, endPip;	// the starting pip and the ending pip
		int oppPlayer, oppPip;	// the opposite player and the pip they own 
		
		Move move;	
		
		if(player == WHITE_PLAYER)
		{
			oppPlayer = RED_PLAYER;
		}
		
		else
		{
			oppPlayer = WHITE_PLAYER;
		}
		
		for(int i = 0; i < play.length(); i++)	// play length is really just numMoves, and this loop actually moves the checkers a given number of moves (up to 4)
		{
			move = play.getMove(i);		
			startPip = move.getFromPip();	// the start pip is the pip you're moving from
			checkers[player][startPip] -= 1;	// the pip you moved from loses a checker
			endPip = startPip - move.getByPips();	// the pip you end up at is the pip you left, minus the number of pips you want to move, if you're going anticlockwise
			
			if(endPip < OFF)
			{
				endPip = OFF;	// if the move takes a checker off the board then it is set to be the bearing off point
			}
			
			checkers[player][endPip] += 1;	// the pip you move to gains a checker
			
			// the opposition's pip on the other side of the board (directly vertically opposite)
			// for example, if the end pip is 15 then the opposite pip is (25-15) = 10
			// I HAVE NO IDEA WHAT OPP PIP ACTUALLY IS FOR, AS THE BELOW IF STATEMENT SEEMS TO CONTRADICT THIS DEFINITION 
			oppPip = BAR - endPip;	
			
			// check for hit, so, if the pip you land on has one checker, AND the pip you land on isn't the bar AND the pip you land on isn't off the board
			if((checkers[oppPlayer][oppPip] == 1) && (endPip != BAR) && (endPip != OFF))	
			{
				checkers[oppPlayer][oppPip] -= 1;	// the opposition loses a pip if you get a hit
				checkers[oppPlayer][BAR] += 1;	// and the lost pip goes is added to the bar
			}
			
		}
		
	}

	private void displayPip(int pip)	// display the number of checkers on a pip
	{
		int oppPip = BAR - pip;
		
		if(checkers[WHITE_PLAYER][pip] > 0)
		{
			System.out.print("W" + checkers[WHITE_PLAYER][pip] + "  ");
		}
		
		else
		{
			if (checkers[RED_PLAYER][oppPip] > 0)
			{
				System.out.print("R" + checkers[RED_PLAYER][oppPip] + "  ");
			}
			
			else
			{
				System.out.print("|   ");
			}
			
		return;
					
		}
	}
	
	private void displayOffBoard(int player, int pip)	// display the number of checkers on the bar or on the bear off
	{
		if(checkers[player][pip] > 0)
		{
			if(player == WHITE_PLAYER)
			{
				System.out.print("W");
			}
			
			else
			{
				System.out.print("R");
			}
			
			return;
		}
	}

	// display the whole board
	// board is facing player 0 (White)
	// player 0 (White) is anti-clockwise, player 1 (Red) is clockwise
	public void displayBoard()
	{
		// far boards
		{
			System.out.println("13--+---+---+---+---18 BAR  19--+---+---+---+---24  OFF");
			for(int i = 13; i <= 18; i++)
			{
				displayPip(i);	// player 1 outer board
			}
			
			displayOffBoard(WHITE_PLAYER, BAR);	// player 0 bar
			
			for(int i = 19; i<= 24; i++)
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
			
			displayOffBoard(WHITE_PLAYER, OFF);	// player 0 bear off
			System.out.println("");
			
			System.out.println("12--+---+---+---+---07 BAR  06--+---+---+---+---01  OFF");
			System.out.println("");
		}
	}
}
