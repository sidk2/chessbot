import java.util.ArrayList;

public class Test
{
    public static void main(String[] args) {
        ArrayList<Piece> board = new ArrayList<>();
        Pawn p = new Pawn(4, 1, true);
        Pawn p1 = new Pawn(3, 6, false);
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
                board.add( new Pawn(i, 6, false) );
                continue;
            }
            if(i == 3) {
                board.add( new Pawn(i, 1, true) );
                continue;
            }
            board.add( new Pawn(i, 1, true) );
            board.add( new Pawn(i, 6, false) );
        }
        
        
        
        Board b = new Board(board);
        //System.out.println(board.size() - 1);
        //System.out.println(board.get( 2 ).getLoc().getXPos() + " " + board.get( 2 ).getLoc().getYPos());
        //boolean nextMoves = board.get( 5 ).isInCheck( b, 5 );
        //ArrayList<Board> nextMoves = board.get( 5 ).findMoves( b, 5 );
//        AllMoves a = new AllMoves(b);
//        ArrayList<Board> nextMoves = a.findAllMoves( b, true );
//        ArrayList<Board> nextMoves1 = new ArrayList<>();
//        ArrayList<Board> nextMoves2 = new ArrayList<>();
////        ArrayList<Board> nextMoves3 = new ArrayList<>();
//        for(Board i : nextMoves) {
//            AllMoves q = new AllMoves(i);
//            for(Board r : q.findAllMoves( i, false )){
//                nextMoves1.add( r );
//            }
//        }
//        for(Board i : nextMoves1) {
//            AllMoves q = new AllMoves(i);
//            for(Board r : q.findAllMoves( i, true )) {
//                nextMoves2.add( r );
//            }
//        }
////        Board best = nextMoves.get( 0 );
////        int bestEval = nextMoves.get( 0 ).getEval( nextMoves.get( 0 ) );
////        for(Board i : nextMoves) {
////            i.printBoard( i );
////            System.out.println(i.getEval( i ));
////            System.out.println();
////            if(Math.abs(i.getEval( i )) > Math.abs( bestEval )) {
////                best = i;
////            }
////        }
//        Board best = null;
//        //nextMoves1.get( 0 ).printBoard( nextMoves1.get(0) );
//        //System.out.println();
//        int bestEval = 900;
//        for(Board i : nextMoves2) {
//            Board worst = null;
//            int curEval = i.getEval( i );
//            if(i.findAllMoves( i, false ) == null) {
//                curEval = 10000;
//                if(curEval > bestEval) {
//                    bestEval = curEval;
//                    best = i;
//                }
//                continue;
//            }
//            for(Board j : i.findAllMoves( i, false )) {
//                if(worst == null) {
//                    curEval = j.getEval( j );
//                    worst = j;
//                }
//                if(j.getEval( j ) < curEval) {
//                    curEval = j.getEval( j );
//                    worst = j;
//                }
//            }
//            
//            if(best == null) {
//                best = worst;
//                bestEval = curEval;
//            }
//            else {
//                if(bestEval < curEval) {
//                    best = worst;
//                    bestEval = curEval;
//                }
//            }
//            System.out.println(bestEval);
//            if(worst != null) {
//                worst.printBoard();
//                System.out.println(worst.getEval( worst ));
//            }
//            System.out.println();
//        }
//        best.printBoard() );
//        //System.out.println(nextMoves.size());
//        //System.out.println(nextMoves);
////        System.out.println(board.get( 0 ).getLoc().getXPos());
////        System.out.println(board.get( 0 ).getLoc().getYPos());
////        System.out.println(board.get( 0 ).getColor());
//        //b.printBoard( b );
        AI ai = new AI(b);
        ai.minimax(b, 2, true).printBoard();
    }
}
