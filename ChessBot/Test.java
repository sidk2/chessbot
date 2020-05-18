import java.util.ArrayList;

public class Test
{
    public static void main(String[] args) {
        ArrayList<Piece> board = new ArrayList<>();
        Pawn p = new Pawn(4, 1, true);
        board.add( new Rook(0, 0, true) );
        board.add( new Knight(1, 0, true) );
        board.add( new Bishop(2, 0, true));
        board.add( new Queen(3, 0, true) );
        board.add( new King(4, 0, true) );
        board.add( new Bishop(5, 0, true));
        board.add( new Knight(6, 0, true) );
        board.add( new Rook(7, 0, true) );
        
        board.add( new Rook(0, 7, false) );
        board.add( new Knight(1, 7, false) );
        board.add( new Bishop(2, 7, false));
        board.add( new Queen(3, 7, false) );
        board.add( new King(4, 7, false) );
        board.add( new Bishop(5, 7, false));
        board.add( new Knight(6, 7, false) );
        board.add( new Rook(7, 7, false) );
        
        for(int i = 0; i < 8; i++) {
            if(i == 4) {
                board.add( new Pawn(i, 6, false) );
                continue;
            }
            board.add( new Pawn(i, 1, true) );
            board.add( new Pawn(i, 6, false) );
        }
        
        board.add( p );
        
        Board b = new Board(board);
        //System.out.println(board.size() - 1);

        ArrayList<Board> nextMoves = p.findMoves( b, board.size()- 10 );
        System.out.println(board.get( board.size() - 11 ).getLoc().getXPos());
        System.out.println(board.get( board.size() - 11 ).getLoc().getYPos());
        System.out.println(board.get( board.size() - 11 ).getColor());
        b.printBoard( b );
    }
}
