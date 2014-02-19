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
		
		numMoves = setPips.length/2;	// the input number of pips to move, divided by 2 
		
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
		
}
