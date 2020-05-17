import java.util.ArrayList;
import info.gridworld.grid.Location;

public interface Piece {
	public int getValue();
	public void move(Location loc);
	public ArrayList<Location> getPossibleMoves(Location loc);
}
