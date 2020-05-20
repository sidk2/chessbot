import java.util.ArrayList;

public class Test
{
    public static void main(String[] args) {
        ArrayList<Piece> board = new ArrayList<>();
        Pawn p = new Pawn(4, 1, true);
        Pawn p1 = new Pawn(4, 6, false);
        board.add( p );//index is 0
        board.add( p1 );
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
                continue;
            }
            board.add( new Pawn(i, 1, true) );
            board.add( new Pawn(i, 6, false) );
        }
        
        
        
        Board b = new Board(board);
        b.printBoard(b);
        //System.out.println();
        //System.out.println(board.size() - 1);
        //System.out.println(board.get( 2 ).getLoc().getXPos() + " " + board.get( 2 ).getLoc().getYPos());
        //boolean nextMoves = board.get( 5 ).isInCheck( b, 5 );
        AllMoves a = new AllMoves(b);
        ArrayList<Board> nextMoves = a.findAllMoves( b, true );
        for(Board i : nextMoves) {
            i.printBoard( i );
            System.out.println();
        }
        //System.out.println(nextMoves.size());
        //System.out.println(nextMoves);
//        System.out.println(board.get( 0 ).getLoc().getXPos());
//        System.out.println(board.get( 0 ).getLoc().getYPos());
//        System.out.println(board.get( 0 ).getColor());
        //b.printBoard( b );
    }
}
