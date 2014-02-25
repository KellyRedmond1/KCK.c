// Team name: KCK.c
// Colin Burke, 12340831
// Katie Aragane, 12371116
// Kelly Redmond, 12446372

public class Play
{
	public static final int MAX_MOVES = 4; // a play contains 0-4 moves
	
	private int numMoves = 0;	// the number of moves is initialised to 0
	private Move[] movesArray = {new Move(), new Move(), new Move(), new Move()};	// array to hold the moves 

	
	public void setPlay(int... setPips)	// a variable number of pips can be the input
	{
		int index;
		for(int i = 0; i<4; i++)
		{
			System.out.print("The set pip is: "+ setPips[i] + "\n\n");

			
		}
		
		// WHY IS THIS DIVIDED BY TWO??
		numMoves = setPips.length/2;	// the input number of pips to move, divided by 2 
		System.out.print("NUM MOVES IS: " + numMoves);
		
		for(index = 0; index < numMoves; index++)
		{
			movesArray[index].setMove(setPips[2*index], setPips[2*index + 1]);
		}
			
		for(; index < MAX_MOVES; index++)
		{
			movesArray[index].setMove(0,0);
		}
		
		return;
	}
		
	public Move getMove(int index)
	{
		return movesArray[index];
	}
		
	public int length()
	{
		return numMoves;	
	}
	
	public boolean checkIfDoubleRoll(int dieRollsArray[])	// how many moves do we need?
	{
		boolean doubleRoll = false;
		
		if(dieRollsArray[0] == dieRollsArray[1])	// if you rolled a double
		{
			doubleRoll = true; // set to true if a double has been rolled
			System.out.println("You rolled a double!");
		}
		
		else
		{
			System.out.println("You did not roll a double!");
		}
		return doubleRoll;
	}
		
}
