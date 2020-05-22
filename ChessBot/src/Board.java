import java.util.ArrayList;

public class Board
{
    private ArrayList<Piece> board;
    private Boolean[][] isOccupied;
    
    public Board() {
        board = new ArrayList<>();
        isOccupied = new Boolean[8][8];
        for(int i = 0; i < 8; i++) {
            isOccupied[i][0] = true;
            isOccupied[i][1] = true;
            isOccupied[i][6] = true;
            isOccupied[i][7] = true;
        }
        
        for(int i = 0; i < 8; i++) {
            board.add( new Pawn(i, 1, true) );
            board.add( new Pawn(i, 6, false) );
        }
        
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
    }
    
    public Board(ArrayList<Piece> board) {
        this.board = board;
        isOccupied = new Boolean[8][8];
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                isOccupied[i][j] = false;
            }
        }
        for(int i = 0; i < board.size(); i++) {
            isOccupied[board.get( i ).getLoc().getXPos()][board.get( i ).getLoc().getYPos()] = true;
        }
    }
    
    public ArrayList<Piece> getBoard(){
        return board;
    }
    
    public Boolean[][] getOccupied(){
        return isOccupied;
    }
    
    public Boolean check(Location loc) {
        int x = loc.getXPos();
        int y = loc.getYPos();
        return !isOccupied[x][y];
    }
    
    public void printBoard(Board b) {
        Character[][] board = new Character[8][8];
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                board[i][j] = ' ';
            }
        }
        ArrayList<Piece> pieces = b.getBoard();
        //System.out.println(pieces == null);
        for(Piece i : pieces) {
            
            if(i instanceof Pawn) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'P';
            }
            if(i instanceof Knight) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'N';
            }
            if(i instanceof Bishop) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'B';
            }
            if(i instanceof Rook) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'R';
            }
            if(i instanceof Queen) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'Q';
            }
            if(i instanceof King) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'K';
            }
//            if(i.getColor() == true) {
//                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'W';
//            }
//            else {
//                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'B';
//            }
            
            //board[i.getLoc().getXPos()][i.getLoc().getYPos()] = "(" + i.getLoc().getXPos() + " , " + i.getLoc().getYPos() + ")";
        }
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    public int getEval(Board b) {
        ArrayList<Piece> pieces = b.getBoard();
        int counter = 0;
        for(Piece i : pieces) {
            if(i instanceof Pawn) {
                if(i.getColor() == true) {
                    counter += 1;
                }
                else {
                    counter -= 1;
                }
            }
            if(i instanceof Bishop) {
                if(i.getColor() == true) {
                    counter += 3;
                }
                else {
                    counter -= 3;
                }
            }
            if(i instanceof Knight) {
                if(i.getColor() == true) {
                    counter += 3;
                }
                else {
                    counter -= 3;
                }
            }
            if(i instanceof Rook) {
                if(i.getColor() == true) {
                    counter += 5;
                }
                else {
                    counter -= 5;
                }
            }
            if(i instanceof Queen) {
                if(i.getColor() == true) {
                    counter += 9;
                }
                else {
                    counter -= 9;
                }
            }
        }
        return counter;
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
        ArrayList<Board> finalSet = new ArrayList<>();
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
                
                //System.out.println(b.getBoard().size());
            }
        }
        /*
        for(Board i : allNextMoves) {
            boolean bad = false;
            for(int j = 0; j < i.getBoard().size(); j++) {
                if(i.getBoard().get(j).getColor() != color && i.getBoard().get( j ).isInCheck( i, j ) == true) {
                    bad = true;
                    return null;
                }
            }
            if(!bad) {
                finalSet.add( i );
            }
        }
        */
        return allNextMoves;
    }
    
    public Board updateBoard(Board b, Piece p1, Piece p2) {
        ArrayList<Piece> pieces = b.getBoard();
        int idx = 0;
        for(int i = 0; i < pieces.size(); i++) {
            if(pieces.get( i ).getLoc().getXPos() == p1.getLoc().getXPos() && pieces.get( i ).getLoc().getYPos() == p1.getLoc().getYPos()) {
                idx = i;
            }
        }
        pieces.remove( idx );
        pieces.add( p2 );
        return new Board(pieces);
    }
}
