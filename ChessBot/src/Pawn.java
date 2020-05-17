import java.util.ArrayList;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

public class Pawn extends Critter implements Piece{

    private final int VALUE = 10;
    private boolean white;
    private boolean firstMove = true;
    
    public Pawn(ChessboardData chessboard, boolean color)
    {
    	white = color;
    }
	@Override
	public boolean isWhite() {
		return false;
	}

	@Override
	 public int getValue()
    {
    	if(white)
    		return VALUE;
    	else
    		return VALUE*-1;
    }
	@Override
	public void move(Location loc) {
		
		
	}

	@Override
	public ArrayList<Location> getPossibleMoves(Location loc) {
		ArrayList<Location> ret = new ArrayList<Location>();
		Location forwardOne = new Location(loc.getRow()+1,loc.getCol());
		Location forwardTwo = new Location(loc.getRow()+2,loc.getCol());
		Location diagLeft = new Location(loc.getRow()+1,loc.getCol()-1);
		Location diagRight = new Location(loc.getRow()+1,loc.getCol()+1);
		if(this.getGrid().isValid(forwardOne) && this.getGrid().get(forwardOne) == null)
		{
			ret.add(forwardOne);
		}
		if(this.getGrid().isValid(forwardOne) 
				&& this.getGrid().get(forwardOne) == null
				&& this.getGrid().isValid(forwardTwo)
				&& this.getGrid().get(forwardTwo)==null
				&& firstMove)
		{
			ret.add(forwardTwo);
		}
		if(this.getGrid().isValid(diagLeft) && this.getGrid().get(diagLeft) != null)
		{
			ret.add(diagLeft);
		}
		if(this.getGrid().isValid(diagRight) && this.getGrid().get(diagRight) != null)
		{
			ret.add(diagRight);
		}
		return ret;
	}

}
