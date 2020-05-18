import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;


public class Chessboard extends ActorWorld{
		
	public final int BOARD_SIZE = 8;
	
	public Chessboard () {
		super(new BoundedGrid<>(8, 8));
	}

	public boolean inputMove() {
		return false;
	}
	
	
}
