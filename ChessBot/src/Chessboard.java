import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.BoundedGrid;

/**
 * Demonstrates a complete KnightsTour of an 8x8 "chess board"
 * 
 * @author George Peck
 * @author KnightsTour.java
 * @version Nov 18, 2007
 */
public class Chessboard
{
    public static void main( String[] args )
    {
        ActorWorld chessBoard = new ChessboardData( new BoundedGrid<Actor>( 8, 8 ) );

        chessBoard.setMessage( "Chess" );
        chessBoard.add(new Location(0,1),new Knight());
        chessBoard.add(new Location(7,1), new Knight());
        chessBoard.add(new Location(7,6), new Knight());
        chessBoard.add(new Location(0,6), new Knight());
        chessBoard.show();
    }
}