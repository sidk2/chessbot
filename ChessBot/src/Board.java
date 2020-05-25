import java.util.ArrayList;

public class Board
{
    private ArrayList<Piece> board;
    private Boolean[][] isOccupied;
    private int value;
    public Board(boolean full) {
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
    public Board(Board b)
    {
    	board = new ArrayList<>();
        isOccupied = new Boolean[8][8];
    	for(Piece p : b.getBoard())
    	{
    		board.add(p);
    		isOccupied[p.getLoc().getXPos()][p.getLoc().getYPos()] = true;
    	}
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
    public boolean equals(Board other)
    {
    	char[][] c1 = this.charBoard();
    	char[][] c2 = other.charBoard();
    	boolean ret = true;
    	for(int i = 0; i < 8;i++)
    	{
    		for(int j = 0; j < 8;j++)
    		{
    			if(c1[i][j] != c2[i][j])
    			{
    				ret = false;
    			}
    		}
    	}
    	return ret;
    }
    public ArrayList<Piece> getBoard(){
        return board;
    }
    public int getValue()
    {
    	int val = 0;
		ArrayList<Piece> p = getBoard();
		for(Piece piece: p)
		{
			val+=piece.getValue();
		}
		return val;
    }
    public Boolean[][] getOccupied(){
        return isOccupied;
    }
    public boolean oneContains(ArrayList<Board> b, Board e)
    {
    	for(Board board : b)
    	{
    		if(board.equals(e))
    		{
    			return true;
    		}
    	}
    	return false;
    }
    public Piece getPiece(Location loc) {
        int x = loc.getXPos();
        int y = loc.getYPos();
        for(Piece p : getBoard())
        {
        	if (p.getLoc().getXPos()==x && p.getLoc().getYPos()==y)
        	{
        		return p;
        	}
        }
		return null;
    }
    public Boolean check(Location loc) {
        int x = loc.getXPos();
        int y = loc.getYPos();
        return !isOccupied[x][y];
    }
    public ArrayList<Board> getPossibleMoves( boolean color) {
    	 
        ArrayList<Board> allNextMoves = new ArrayList<>();
 
        for(int i = 0; i < this.getBoard().size(); i++) {
            //System.out.println(b.getBoard().size());
            if(this.getBoard().get( i ).getColor() == color) {
                ArrayList<Board> temp = this.getBoard().get( i ).findMoves( this, i );
 
                if(temp == null) {
                    continue;
                }
 
                for(Board board : temp) {
                    boolean bad = false;
                    for(int j = 0; j < board.getBoard().size(); j++) {
                        if(board.getBoard().get(j).getColor() != color && board.getBoard().get( j ).isInCheck( board, j ) == true) {
                            bad = true;
                            break;
                        }
                    }
                    if(bad) {
                        continue;
                    }
                    allNextMoves.add( board );
                }
 
            }
        }
        return allNextMoves;
    }
	public Board getBestBoard(boolean color)
	{
		Board best = null;
		for (Board board:getPossibleMoves(color))
		{
			if(best == null)
			{
				best = board;
			}
			else
			{
				if (best.getValue()<board.getValue())
				{
					best = board;
				}
			}
		}
		return best;
	}
	public Board updateBoard(Piece p1, Piece p2) {
        ArrayList<Piece> pieces = this.getBoard();
        
        pieces.remove(p1);
        
        for(int i = 0; i < pieces.size(); i++) {
            if(pieces.get( i ).getLoc().getXPos() == p2.getLoc().getXPos() && pieces.get( i ).getLoc().getYPos() == p2.getLoc().getYPos()) {
                pieces.remove( i );
                break;
            }
        }
        
        pieces.add( p2 );
        return new Board(pieces);
    }
    public char[][] charBoard() {
    	char[][] board = new char[8][8];
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                board[i][j] = ' ';
            }
        }
        ArrayList<Piece> pieces = getBoard();
        //System.out.println(pieces == null);
        for(Piece i : pieces) {
            
            if(i instanceof Pawn && i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'P';
            }
            else if(i instanceof Pawn && !i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'p';
            }
            if(i instanceof Knight && i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'N';
            }
            else if(i instanceof Knight && !i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'n';
            }
            if(i instanceof Bishop && i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'B';
            }
            else if(i instanceof Bishop && !i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'b';
            }
            if(i instanceof Queen && i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'Q';
            }
            else if(i instanceof Queen && !i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'q';
            }
            if(i instanceof Rook && i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'R';
            }
            else if(i instanceof Rook && !i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'r';
            }
            if(i instanceof King && i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'K';
            }
            else if(i instanceof King && !i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'k';
            }
        }
        return board;
    }
    public void printBoard() {
        char[][] board = new char[8][8];
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                board[i][j] = ' ';
            }
        }
        ArrayList<Piece> pieces = getBoard();
        //System.out.println(pieces == null);
        for(Piece i : pieces) {
            
            if(i instanceof Pawn && i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'P';
            }
            else if(i instanceof Pawn && !i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'p';
            }
            if(i instanceof Knight && i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'N';
            }
            else if(i instanceof Knight && !i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'n';
            }
            if(i instanceof Bishop && i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'B';
            }
            else if(i instanceof Bishop && !i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'b';
            }
            if(i instanceof Queen && i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'Q';
            }
            else if(i instanceof Queen && !i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'q';
            }
            if(i instanceof Rook && i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'R';
            }
            else if(i instanceof Rook && !i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'r';
            }
            if(i instanceof King && i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'K';
            }
            else if(i instanceof King && !i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'k';
            }
        }
        System.out.println("  0 1 2 3 4 5 6 7 ");
        for(int i = 0; i < 8; i++) {
        	
            for(int j = 0; j < 8; j++) {
            	
            	if(j==0)
            	{
            		System.out.print(i + " ");
            	}

            	System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        
    }
}
