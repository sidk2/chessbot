import java.util.ArrayList;

public class Test1
{
    public static void main(String[] args) {
        Board b = new Board();
        b = b.updateBoard(b, new Pawn(4, 1, true), new Pawn(4, 3, true));
        b.printBoard( b );
        System.out.println();
        //ArrayList<Board> nextBoard = b.findAllMoves( b, false );
        Board next = null;
        int bestEval = -900;
        for(int i = 1; i < 5; i++) {
            for(Board j : b.findAllMoves( b, i%2 == 0 )) {
                if(j.getEval( j ) > bestEval) {
                    next = j;
                    bestEval = j.getEval( j );
                }
            }
            b = next;
            b.printBoard( b );
            System.out.println();
        }
    }
}
