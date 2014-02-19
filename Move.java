// Team name: KCK.c
// Colin Burke, 12340831
// Katie Aragane, 12371116
// Kelly Redmond, 12446372

public class Move 
{
	private int fromPip = 0;	// the pip you're moving from 
	private int byPips = 0;		// the number of pips you want to move
	
	
	public void setMove(int setFromPip, int setByPips)
	{
		fromPip = setFromPip;	// the pip you want to move from
		byPips = setByPips;	// the number of pips you want to move
		
		return;
	}
	
	public int getFromPip()
	{
		return fromPip;	// the pip you moved from
	}
	
	public int getByPips()
	{
		return byPips;	// the number of pips you want to move
	}
	
}
