import java.util.ArrayList;

public class Test1
{
    public static void main(String[] args) {
        ArrayList<Piece> board = new ArrayList<>();
        Pawn p = new Pawn(4, 3, true);
        Pawn p1 = new Pawn(5, 4, false);
       // Pawn p2 = new Pawn(6, 4, false);
        board.add(p);// index is 0
        board.add(p1);
        //board.add( p2 );
        board.add(new Rook(0, 0, true));
        board.add(new Knight(1, 0, true));
        board.add(new Bishop(2, 0, true));
        board.add(new Queen(3, 0, true));
        board.add(new King(4, 0, true));
        board.add(new Bishop(5, 0, true));
        board.add(new Knight(6, 0, true));
        board.add(new Rook(7, 0, true));

        board.add(new Rook(0, 7, false));
        Knight n1 = new Knight(1, 7, false);
        board.add(n1);
        board.add(new Bishop(2, 7, false));
        board.add(new Queen(3, 7, false));
        board.add(new King(4, 7, false));
        board.add(new Bishop(5, 7, false));
        Knight n2 = new Knight(6, 7, false);
        board.add(n2);
        board.add(new Rook(7, 7, false));

        for (int i = 0; i < 8; i++) {
            if (i == 4) {
                board.add(new Pawn(i, 6, false));
                continue;
            }
            if (i == 5) {
                board.add(new Pawn(i, 1, true));
                continue;
            }
//            if(i == 3) {
//                continue;
//            }
//            if(i == 6) {
//                board.add( new Pawn(i, 1, true) );
//                continue;
//            }
            board.add(new Pawn(i, 1, true));
            board.add(new Pawn(i, 6, false));
        }
        Board b = new Board(board);
        b.printBoard();
        System.out.println();
        AI ai = new AI(b);
        b=ai.minimax( b, 3, true );
        b.printBoard();
    }
}
