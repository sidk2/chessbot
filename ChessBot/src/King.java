import java.util.ArrayList;

/**
 *  A class for the King piece, methods to find Moves, keeps the location, does not include castling
 *
 *  @author  Shreyas Kaasyap
 *  @version May 25, 2020
 *  @author  Period: 1
 *  @author  Assignment: ChessBot
 *
 *  @author  Sources: Shreyas Kaasyap, Sidharth Kannan, Leo Yang
 */
public class King implements Piece
{
    /**
     * the location of the piece
     */
    private Location loc;
    /**
     * the color of the piece
     */
    private boolean color;
    /**
     * the material value of the piece
     */
    public final int VALUE =900;
    /**
     * @param xPos the x coordinate of the location
     * @param yPos the y coordinate of the location
     * @param color the color of the piece
     * A constructor for a king piece, instantiates fields
     */
    public King(int xPos, int yPos, boolean color) {
        loc = new Location(xPos, yPos);
        this.color = color;
    }
    public boolean equals(Piece other)
    {
    	return other.getLoc().equals(getLoc()) && other.getClass().getCanonicalName().equals(this.getClass().getCanonicalName());
    }
    public int getValue()
    {
    	if(color)
    		return VALUE;
    	else
    		return -1*VALUE;
    }
    
    @Override
    public ArrayList<Board> findMoves( Board b, int index )
    {
        ArrayList<Piece> pieces = b.getBoard();
        ArrayList<Piece> temp = new ArrayList<>();
        for(Piece i : pieces) {
            temp.add( i );
        }
        ArrayList<Board> ans = new ArrayList<>();
        temp.remove(index);
        
        int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
        
        int curX = pieces.get( index ).getLoc().getXPos();
        int curY = pieces.get( index ).getLoc().getYPos();
        
        for(int i = 0; i < 8; i++) {
            if(curX + dx[i] >= 0 && curX + dx[i] < 8 && curY + dy[i] >= 0 && curY + dy[i] < 8 && (b.check(new Location(curX + dx[i], curY + dy[i])) == true)) {
                ArrayList<Piece> nextPos = new ArrayList<>();
                for(Piece r : temp) {
                    nextPos.add( r );
                }
                nextPos.add( new King(curX + dx[i], curY + dy[i], pieces.get( index ).getColor()) );
                ans.add( new Board(nextPos) );
            }
            if(curX + dx[i] >= 0 && curX + dx[i] < 8 && curY + dy[i] >= 0 && curY + dy[i] < 8 && (b.check(new Location(curX + dx[i], curY + dy[i])) == false)) {
                for(int r = 0; r < pieces.size(); r++) {
                    if(pieces.get( r ).getLoc().getXPos() == curX + dx[i] && pieces.get( r ).getLoc().getYPos() == curY + dy[i] && pieces.get( r ).getColor() != pieces.get( index ).getColor()) {
                        ArrayList<Piece> nextPos = new ArrayList<>();
                        for(Piece q : temp) {
                            if(q.equals( pieces.get( r ) )) {
                                continue;
                            }
                            nextPos.add( q );     
                        }
                        nextPos.add( new King(curX + dx[i], curY + dy[i], pieces.get( index ).getColor()) );
                        ans.add( new Board(nextPos) );
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
        return false;
    }


    @Override
    public boolean getColor()
    {
        return color;
    }

}
