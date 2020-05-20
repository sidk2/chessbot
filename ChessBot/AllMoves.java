import java.util.ArrayList;

public class AllMoves
{
    ArrayList<Piece> pieces;
    
    public AllMoves(Board b) {
        pieces = b.getBoard();
    }
    
    public ArrayList<Board> findAllMoves(Board b, boolean color) {
        for(int i = 0; i < b.getBoard().size(); i++) {
            if(b.getBoard().get( i ).getColor() == color) {
                if(b.getBoard().get( i ).isInCheck( b, i )) {
                    return null;
                }
            }
        }
        
        ArrayList<Board> allNextMoves = new ArrayList<>();
        
        for(int i = 0; i < b.getBoard().size(); i++) {
            //System.out.println(b.getBoard().size());
            if(b.getBoard().get( i ).getColor() == color) {
                ArrayList<Board> temp = b.getBoard().get( i ).findMoves( b, i );
                    
                if(temp == null) {
                    continue;
                }

                for(Board board : temp) {
                    allNextMoves.add( board );
                }
                
                System.out.println(b.getBoard().size());
            }
        }
        return allNextMoves;
    }
}
