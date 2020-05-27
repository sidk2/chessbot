import java.util.ArrayList;


/**
 * TODO Write a one-sentence summary of your class here. TODO Follow it with
 * additional details about its purpose, what abstraction it represents, and how
 * to use it.
 *
 * @author Shreyas Kaasyap
 * @version May 25, 2020
 * @author Period: 1
 * @author Assignment: ChessBot
 *
 * @author Sources: Shreyas Kaasyap, Sidharth Kannan, Leo Yang
 */
public class Knight implements Piece
{
    /**
     * returns the location of the piece
     */
    private Location loc;

    /**
     * returns the color of the piece, true = white, false = black
     */
    private boolean color;

    /**
     * the value of the piece
     */
    public final int VALUE = 30;


    /**
     * @param xPos
     *            the xPosition of the location
     * @param yPos
     *            the yPosition of the location
     * @param color
     *            the color of that piece Constructor for Knight, instantiates
     *            fields
     */
    public Knight( int xPos, int yPos, boolean color )
    {
        loc = new Location( xPos, yPos );
        this.color = color;
    }


    public boolean equals( Piece other )
    {
        return other.getLoc().equals( getLoc() )
            && other.getClass().getCanonicalName().equals( this.getClass().getCanonicalName() );
    }


    public int getValue()
    {
        if ( color )
            return VALUE;
        else
            return -1 * VALUE;
    }


    @Override
    public ArrayList<Board> findMoves( Board b, int index )
    {
        ArrayList<Piece> pieces = b.getBoard();
        ArrayList<Piece> temp = new ArrayList<>();
        for ( Piece i : pieces )
        {
            temp.add( i );
        }
        temp.remove( index );
        ArrayList<Board> ans = new ArrayList<>();

        int[] dx = { -1, 1, 2, 2, 1, -1, -2, -2 };
        int[] dy = { 2, 2, 1, -1, -2, -2, -1, 1 };

        int curX = pieces.get( index ).getLoc().getXPos();
        int curY = pieces.get( index ).getLoc().getYPos();
        for ( int i = 0; i < 8; i++ )
        {
            if ( curX + dx[i] >= 0 && curX + dx[i] < 8 && curY + dy[i] >= 0 && curY + dy[i] < 8
                && b.check( new Location( curX + dx[i], curY + dy[i] ) ) == true )
            {
                ArrayList<Piece> nextPos = new ArrayList<>();
                for ( Piece r : temp )
                {
                    nextPos.add( r );
                }
                nextPos.add(
                    new Knight( curX + dx[i], curY + dy[i], pieces.get( index ).getColor() ) );
                ans.add( new Board( nextPos ) );
            }
            if ( curX + dx[i] >= 0 && curX + dx[i] < 8 && curY + dy[i] >= 0 && curY + dy[i] < 8
                && b.check( new Location( curX + dx[i], curY + dy[i] ) ) == false )
            {
                for ( int r = 0; r < pieces.size(); r++ )
                {
                    if ( pieces.get( r ).getLoc().getXPos() == curX + dx[i]
                        && pieces.get( r ).getLoc().getYPos() == curY + dy[i]
                        && pieces.get( r ).getColor() != pieces.get( index ).getColor() )
                    {
                        ArrayList<Piece> nextPos = new ArrayList<>();
                        for ( Piece q : temp )
                        {
                            if ( q.equals( pieces.get( r ) ) )
                            {
                                continue;
                            }
                            nextPos.add( q );
                        }
                        nextPos.add( new Knight( curX + dx[i],
                            curY + dy[i],
                            pieces.get( index ).getColor() ) );
                        ans.add( new Board( nextPos ) );
                        break;
                    }
                }
            }
        }
        return ans;
    }


    @Override
    public Location getLoc()
    {
        return loc;
    }


    @Override
    public boolean isInCheck( Board b, int index )
    {
        ArrayList<Piece> pieces = b.getBoard();

        int[] dx = { -1, 1, 2, 2, 1, -1, -2, -2 };
        int[] dy = { 2, 2, 1, -1, -2, -2, -1, 1 };

        int curX = pieces.get( index ).getLoc().getXPos();
        int curY = pieces.get( index ).getLoc().getYPos();

        int blackKX = 0, blackKY = 0, whiteKX = 0, whiteKY = 0;
        for ( Piece i : pieces )
        {
            if ( i instanceof King && i.getColor() == false )
            {
                blackKX = i.getLoc().getXPos();
                blackKY = i.getLoc().getYPos();
            }
            if ( i instanceof King && i.getColor() == true )
            {
                whiteKX = i.getLoc().getXPos();
                whiteKY = i.getLoc().getYPos();
            }
        }
        for ( int i = 0; i < 8; i++ )
        {
            int nextX = curX + dx[i];
            int nextY = curY + dy[i];
            if ( ( ( nextX == blackKX ) && ( nextY == blackKY )
                && ( pieces.get( index ).getColor() == true ) )
                || ( ( nextX == whiteKX ) && ( nextY == whiteKY )
                    && pieces.get( index ).getColor() == false ) )
            {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean getColor()
    {
        return color;
    }

}
