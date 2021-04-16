import java.util.ArrayList;


/**
 * A class for a Pawn, includes the location of the Pawn and the color. Includes
 * methods to find the possible moves for the pawn, does not include pawn
 * promotion
 *
 * @author Shreyas Kaasyap
 * @version May 25, 2020
 * @author Period: 1
 * @author Assignment: ChessBot
 *
 * @author Sources: Shreyas Kaasyap, Sidharth Kannan, Leo Yang
 */
public class Pawn implements Piece
{
    /**
     * the location of the current piece
     */
    private Location loc;

    /**
     * the color of the current piece
     */
    private Boolean color;

    /**
     * the material value of the piece, true = white, false = black
     */
    public final int VALUE = 10;

    public char code;

    private int[][] activityTable;


    /**
     * @param xPos
     *            the xPosition of the Location
     * @param yPos
     *            the yPosition of the Location
     * @param color
     *            the color of the piece Constructor for the Pawn class,
     *            instantiates the private fields
     */
    public Pawn( int xPos, int yPos, Boolean color )
    {
        loc = new Location( xPos, yPos );
        this.color = color;
        code = color ? 'P' : 'p';
        activityTable = this.color ? new int[][]{
            {2, 2, 2, 2, 2, 2, 2, 0},
            {3, 3, 3, 3, 3, 3, 3, 0},
            {3, 3, 3, 3, 3, 3, 3, 0},
            {3, 3, 3, 9, 3, 3, 3, 0},
            {3, 3, 3, 9, 3, 3, 3, 0},
            {3, 3, 3, 3, 3, 3, 3, 0},
            {3, 3, 3, 3, 3, 3, 3, 0},
            {2, 2, 2, 2, 2, 2, 2, 0}
        } : new int[][]{
            {0, 2, 2, 2, 2, 2, 2, 2},
            {0, 3, 3, 3, 3, 3, 3, 3},
            {0, 3, 3, 3, 3, 3, 3, 3},
            {0, 3, 3, 3, 9, 3, 3, 3},
            {0, 3, 3, 3, 9, 3, 3, 3},
            {0, 3, 3, 3, 3, 3, 3, 3},
            {0, 3, 3, 3, 3, 3, 3, 3},
            {0, 2, 2, 2, 2, 2, 2, 2}
        };
    }
    public char getCode()
    {
        return code;
    }

    public boolean equals( Piece other )
    {
        return other.getLoc().equals( getLoc() )
            && other.getClass().getCanonicalName()
            .equals( this.getClass().getCanonicalName() );
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

        ArrayList<Board> ans = new ArrayList<>();
        temp.remove( index );
        int curX = pieces.get( index ).getLoc().getXPos();
        int curY = pieces.get( index ).getLoc().getYPos();
        if ( pieces.get( index ).getColor() == true )
        {
            if ( curY == 1 )
            {
                if ( b.check( new Location( curX, curY + 2 ) ) == true
                    && b.check( new Location( curX, curY + 1 ) ) == true )
                {
                    ArrayList<Piece> nextPos = new ArrayList<>();
                    for ( Piece i : temp )
                    {
                        nextPos.add( i );
                    }
                    nextPos.add( new Pawn( curX, curY + 2, pieces
                        .get( index ).getColor() ) );
                    ans.add( new Board( nextPos ) );
                    ;
                }
            }
            if ( curY + 1 < 7 )
            {
                if ( b.check( new Location( curX, curY + 1 ) ) == true )
                {
                    ArrayList<Piece> nextPos = new ArrayList<>();
                    for ( Piece i : temp )
                    {
                        nextPos.add( i );
                    }
                    nextPos.add( new Pawn( curX, curY + 1, pieces
                        .get( index ).getColor() ) );
                    Board r = new Board( nextPos );
                    ans.add( new Board( nextPos ) );

                }
            }
            if ( curX - 1 >= 0 && curY + 1 < 7
                && b.check( new Location( curX - 1, curY + 1 ) ) == false )
            {
                for ( int i = 0; i < pieces.size(); i++ )
                {
                    if ( pieces.get( i ).getLoc().getXPos() == curX - 1
                        && pieces.get( i ).getLoc().getYPos() == curY + 1
                        && pieces.get( i ).getColor() != pieces
                        .get( index ).getColor() )
                    {
                        ArrayList<Piece> nextPos = new ArrayList<>();
                        for ( Piece r : temp )
                        {
                            if ( r.equals( pieces.get( i ) ) )
                            {
                                continue;
                            }
                            nextPos.add( r );
                        }
                        nextPos
                            .add( new Pawn( curX - 1, curY + 1, pieces
                                .get( index ).getColor() ) );
                        ans.add( new Board( nextPos ) );
                        break;
                    }
                }
            }
            if ( curX + 1 < 8 && curY + 1 < 7
                && b.check( new Location( curX + 1, curY + 1 ) ) == false )
            {
                for ( int i = 0; i < pieces.size(); i++ )
                {
                    if ( pieces.get( i ).getLoc().getXPos() == curX + 1
                        && pieces.get( i ).getLoc().getYPos() == curY + 1
                        && pieces.get( i ).getColor() != pieces
                        .get( index ).getColor() )
                    {
                        ArrayList<Piece> nextPos = new ArrayList<>();
                        for ( Piece r : temp )
                        {
                            if ( r.equals( pieces.get( i ) ) )
                            {
                                continue;
                            }
                            nextPos.add( r );
                        }
                        nextPos
                            .add( new Pawn( curX + 1, curY + 1, pieces
                                .get( index ).getColor() ) );
                        ans.add( new Board( nextPos ) );
                        break;
                    }
                }
            }
        }
        else
        {
            if ( curY == 6 )
            {
                if ( b.check( new Location( curX, curY - 2 ) ) == true
                    && b.check( new Location( curX, curY - 1 ) ) == true )
                {
                    ArrayList<Piece> nextPos = new ArrayList<>();
                    for ( Piece i : temp )
                    {
                        nextPos.add( i );
                    }
                    nextPos.add( new Pawn( curX, curY - 2, pieces
                        .get( index ).getColor() ) );
                    ans.add( new Board( nextPos ) );
                }
            }
            if ( curY - 1 > 0 )
            {
                if ( b.check( new Location( curX, curY - 1 ) ) == true )
                {
                    ArrayList<Piece> nextPos = new ArrayList<>();
                    for ( Piece i : temp )
                    {
                        nextPos.add( i );
                    }
                    nextPos.add( new Pawn( curX, curY - 1, pieces
                        .get( index ).getColor() ) );
                    Board r = new Board( nextPos );
                    ans.add( new Board( nextPos ) );
                }
            }
            if ( curX - 1 >= 0 && curY - 1 > 0
                && b.check( new Location( curX - 1, curY - 1 ) ) == false )
            {
                for ( int i = 0; i < pieces.size(); i++ )
                {
                    if ( pieces.get( i ).getLoc().getXPos() == curX - 1
                        && pieces.get( i ).getLoc().getYPos() == curY - 1
                        && pieces.get( i ).getColor() != pieces
                        .get( index ).getColor() )
                    {
                        ArrayList<Piece> nextPos = new ArrayList<>();
                        for ( Piece r : temp )
                        {
                            if ( r.equals( pieces.get( i ) ) )
                            {
                                continue;
                            }
                            nextPos.add( r );
                        }
                        nextPos
                            .add( new Pawn( curX - 1, curY - 1, pieces
                                .get( index ).getColor() ) );
                        ans.add( new Board( nextPos ) );
                        break;
                    }
                }
            }
            if ( curX + 1 < 8 && curY - 1 > 0
                && b.check( new Location( curX + 1, curY - 1 ) ) == false )
            {
                for ( int i = 0; i < pieces.size(); i++ )
                {
                    if ( pieces.get( i ).getLoc().getXPos() == curX + 1
                        && pieces.get( i ).getLoc().getYPos() == curY - 1
                        && pieces.get( i ).getColor() != pieces
                        .get( index ).getColor() )
                    {
                        ArrayList<Piece> nextPos = new ArrayList<>();
                        for ( Piece r : temp )
                        {
                            if ( r.equals( pieces.get( i ) ) )
                            {
                                continue;
                            }
                            nextPos.add( r );
                        }
                        nextPos
                            .add( new Pawn( curX + 1, curY - 1, pieces
                                .get( index ).getColor() ) );
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
        int curX = pieces.get( index ).getLoc().getXPos();
        int curY = pieces.get( index ).getLoc().getYPos();
        if ( pieces.get( index ).getColor() == true )
        {
            for ( Piece i : pieces )
            {
                if ( i instanceof King && i.getColor() == false )
                {
                    if ( ( i.getLoc().getXPos() == curX + 1 && i
                                    .getLoc().getYPos() == curY + 1 )
                        || ( i.getLoc().getXPos() == curX - 1
                            && i.getLoc().getYPos() == curY + 1 ) )
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
            }
        }
        else
        {
            for ( Piece i : pieces )
            {
                if ( i instanceof King && i.getColor() == true )
                {
                    if ( ( i.getLoc().getXPos() == curX - 1 && i
                                    .getLoc().getYPos() == curY - 1 )
                        || ( i.getLoc().getXPos() == curX + 1
                            && i.getLoc().getYPos() == curY - 1 ) )
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
            }
        }
        return false;
    }


    @Override
    public boolean getColor()
    {
        return color;
    }
    public int getActivity(Location loc) {
        return activityTable[loc.getXPos()][loc.getYPos()];
    }

}
