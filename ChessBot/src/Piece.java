import java.util.ArrayList;

interface Piece {
    ArrayList<Board> findMoves(Board b, int index);
    boolean isInCheck(Board b, int index);
    Location getLoc();
    boolean getColor();
}

/*
public class Piece
{
    private Location loc;
    private Boolean color;
    
    public Piece(int xPos, int yPos, Boolean color) {
        loc = new Location(xPos, yPos);
        this.color = color;
    }
    


    public Location getLoc() {
        return loc;
    }
    
    public Boolean getColor() {
        return color;
    }
    
    public Board findMoves(Board board, int index) {
        ArrayList<Piece> pieces = board.getBoard();
        Piece cur = pieces.get( index );
        cur.getType().findMoves( board, index );
    }
}
*/