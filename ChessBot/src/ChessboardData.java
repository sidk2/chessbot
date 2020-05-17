import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

public class ChessboardData extends ActorWorld
{
	private Piece[][] pieces = new Piece[8][8];
	public ChessboardData(BoundedGrid<Actor> b)
	{
		for(Location loc : b.getOccupiedLocations())
		{
			pieces[loc.getRow()][loc.getCol()] = (Piece)b.get(loc);
		}
	}
	
	public Piece[][] getPieces()
	{
		return pieces;
	}
	public int getValue()
	{
		int value = 0;
		for(Piece[] row : pieces)
		{
			for(Piece p : row)
			{
				value+=p.getValue();
			}
		}
		return value;
	}
}
