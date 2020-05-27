
import java.util.ArrayList;


/**
 * A class for a Board, which has an ArrayList of pieces that stores all the
 * pieces currently on the board. Has methods to manipulate and return parts of
 * the board.
 *
 * @author Shreyas Kaasyap
 * @version May 26, 2020
 * @author Period: 1
 * @author Assignment: ChessBot
 *
 * @author Sources: Shreyas Kaasyap, Sidharth Kannan, Leo Yang
 */
public class Board
{
    /**
     * the set of pieces currently on the board
     */
    private ArrayList<Piece> board;

    /**
     * a boolean matrix that keeps track of which tiles have any piece on it
     * true if there is a piece at the tile, false otherwise
     */
    private Boolean[][] isOccupied;


    /**
     * Makes a board with the position set as the starting position of a chess
     * game Instantiates fields
     */
    public Board()
    {
        board = new ArrayList<>();
        isOccupied = new Boolean[8][8];
        for ( int i = 0; i < 8; i++ )
        {
            isOccupied[i][0] = true;
            isOccupied[i][1] = true;
            isOccupied[i][6] = true;
            isOccupied[i][7] = true;
            isOccupied[i][2] = false;
            isOccupied[i][3] = false;
            isOccupied[i][4] = false;
            isOccupied[i][5] = false;
        }

        for ( int i = 0; i < 8; i++ )
        {
            board.add( new Pawn( i, 1, true ) );
            board.add( new Pawn( i, 6, false ) );
        }

        board.add( new Rook( 0, 0, true ) );
        board.add( new Knight( 1, 0, true ) );
        board.add( new Bishop( 2, 0, true ) );
        board.add( new Queen( 4, 0, true ) );
        board.add( new King( 3, 0, true ) );
        board.add( new Bishop( 5, 0, true ) );
        board.add( new Knight( 6, 0, true ) );
        board.add( new Rook( 7, 0, true ) );

        board.add( new Rook( 0, 7, false ) );
        board.add( new Knight( 1, 7, false ) );
        board.add( new Bishop( 2, 7, false ) );
        board.add( new Queen( 4, 7, false ) );
        board.add( new King( 3, 7, false ) );
        board.add( new Bishop( 5, 7, false ) );
        board.add( new Knight( 6, 7, false ) );
        board.add( new Rook( 7, 7, false ) );

    }


    /**
     * @param board
     *            an arraylist of pieces that are on a board sets the board to
     *            have the pieces in board, updates fields
     */
    public Board( ArrayList<Piece> board )
    {
        this.board = board;
        isOccupied = new Boolean[8][8];
        for ( int i = 0; i < 8; i++ )
        {
            for ( int j = 0; j < 8; j++ )
            {
                isOccupied[i][j] = false;
            }
        }
        for ( int i = 0; i < board.size(); i++ )
        {
            isOccupied[board.get( i ).getLoc().getXPos()][board.get( i ).getLoc().getYPos()] = true;
        }

    }


    /**
     * Checks if two boards have the same pieces in the same spots
     * 
     * @param other
     *            the board to compare to
     * @return boolean true if the boards are equal, false otherwise
     */
    public boolean equals( Board other )
    {
        char[][] c1 = this.charBoard();
        char[][] c2 = other.charBoard();
        boolean ret = true;
        for ( int i = 0; i < 8; i++ )
        {
            for ( int j = 0; j < 8; j++ )
            {
                if ( c1[i][j] != c2[i][j] )
                {
                    ret = false;
                }
            }
        }
        return ret;
    }


    /**
     * returns the set of the pieces currently on the board
     * 
     * @return ArrayList the set of pieces
     */
    public ArrayList<Piece> getBoard()
    {
        return board;
    }


    /**
     * Returns the value of the position, adds up all the material value of
     * every piece on the baord
     * 
     * @return int
     */
    public int getValue()
    {
        int val = 0;
        ArrayList<Piece> p = getBoard();
        for ( Piece piece : p )
        {
            val += piece.getValue();
        }
        return val;
    }


    /**
     * Return the pieces at a given location, returns null if there is no piece
     * at that location
     * 
     * @param loc
     *            the location to check
     * @return Piece
     */
    public Piece getPiece( Location loc )
    {
        int x = loc.getXPos();
        int y = loc.getYPos();
        for ( Piece p : getBoard() )
        {
            if ( p.getLoc().getXPos() == x && p.getLoc().getYPos() == y )
            {
                return p;
            }
        }
        return null;
    }


    /**
     * Returns the set of pieces on a board
     * 
     * @return ArrayList the set of pieces on the board
     */
    public ArrayList<Piece> copyBoard()
    {
        ArrayList<Piece> pieces = new ArrayList<>();
        for ( Piece i : this.getBoard() )
        {
            int xPos = i.getLoc().getXPos();
            int yPos = i.getLoc().getYPos();
            boolean color = i.getColor();
            if ( i instanceof Pawn )
            {
                pieces.add( new Pawn( xPos, yPos, color ) );
            }
            if ( i instanceof Knight )
            {
                pieces.add( new Knight( xPos, yPos, color ) );
            }
            if ( i instanceof Bishop )
            {
                pieces.add( new Bishop( xPos, yPos, color ) );
            }
            if ( i instanceof Queen )
            {
                pieces.add( new Queen( xPos, yPos, color ) );
            }
            if ( i instanceof Rook )
            {
                pieces.add( new Rook( xPos, yPos, color ) );
            }
            if ( i instanceof King )
            {
                pieces.add( new King( xPos, yPos, color ) );
            }
        }
        return pieces;
    }


    /**
     * Return true if there is no piece at a given location, false otherwise
     * 
     * @param loc
     *            the location to check
     * @return Boolean
     */
    public Boolean check( Location loc )
    {
        int x = loc.getXPos();
        int y = loc.getYPos();
        return !isOccupied[x][y];
    }


    /**
     * returns the set of all possible moves for a side(color is true = white,
     * false = black), removes illegal moves, returns null if the current
     * position is checkmate
     * 
     * @param color
     *            true if it is white's turn, false otherwise
     * @return ArrayList the set of all possible resulting Boards
     */
    public ArrayList<Board> getPossibleMoves( boolean color )
    {

        ArrayList<Board> allNextMoves = new ArrayList<>();

        for ( int i = 0; i < this.getBoard().size(); i++ )
        {
            if ( this.getBoard().get( i ).getColor() == color )
            {
                ArrayList<Board> temp = this.getBoard().get( i ).findMoves( this, i );

                if ( temp == null )
                {
                    continue;
                }

                for ( Board board : temp )
                {
                    boolean bad = false;
                    for ( int j = 0; j < board.getBoard().size(); j++ )
                    {
                        if ( board.getBoard().get( j ).getColor() != color
                            && board.getBoard().get( j ).isInCheck( board, j ) == true )
                        {
                            bad = true;
                            break;
                        }
                    }
                    if ( bad )
                    {
                        continue;
                    }
                    allNextMoves.add( board );
                }

            }
        }
        return allNextMoves;
    }


    /**
     * Returns the best next possible board based on the material evalution of
     * the board for a given color
     * 
     * @param color
     *            true if it white to move, false otherwise
     * @return Board
     */
    public Board getBestBoard( boolean color )
    {
        Board best = null;
        for ( Board board : getPossibleMoves( color ) )
        {
            if ( best == null )
            {
                best = board;
            }
            else
            {
                if ( best.getValue() < board.getValue() )
                {
                    best = board;
                }
            }
        }
        return best;
    }


    /**
     * updates the board, removes the piece p1, and adds the piece p2, takes
     * care of the possibility of p2 is taking another piece, p1 and p2 will be
     * the same type
     * 
     * @param p1
     *            the piece to remove
     * @param p2
     *            the piece to put on the board
     * @return Board, the updated Board
     */
    public Board updateBoard( Piece p1, Piece p2 )
    {
        ArrayList<Piece> pieces = this.getBoard();

        pieces.remove( p1 );

        for ( int i = 0; i < pieces.size(); i++ )
        {
            if ( pieces.get( i ).getLoc().getXPos() == p2.getLoc().getXPos()
                && pieces.get( i ).getLoc().getYPos() == p2.getLoc().getYPos() )
            {
                pieces.remove( i );
                break;
            }
        }

        pieces.add( p2 );
        return new Board( pieces );
    }


    /**
     * returns a matrix of the current Board, capital letter for a white piece,
     * lowercase for black
     * 
     * @return char[][]
     */
    public char[][] charBoard()
    {
        char[][] board = new char[8][8];
        for ( int i = 0; i < 8; i++ )
        {
            for ( int j = 0; j < 8; j++ )
            {
                board[i][j] = ' ';
            }
        }
        ArrayList<Piece> pieces = getBoard();
        for ( Piece i : pieces )
        {

            if ( i instanceof Pawn && i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'P';
            }
            else if ( i instanceof Pawn && !i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'p';
            }
            if ( i instanceof Knight && i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'N';
            }
            else if ( i instanceof Knight && !i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'n';
            }
            if ( i instanceof Bishop && i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'B';
            }
            else if ( i instanceof Bishop && !i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'b';
            }
            if ( i instanceof Queen && i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'Q';
            }
            else if ( i instanceof Queen && !i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'q';
            }
            if ( i instanceof Rook && i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'R';
            }
            else if ( i instanceof Rook && !i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'r';
            }
            if ( i instanceof King && i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'K';
            }
            else if ( i instanceof King && !i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'k';
            }
        }
        return board;
    }


    /**
     * prints out the currentBoard, uppercase for white, lowercase for black,
     * prints out the rows and columns as well
     */
    public void printBoard()
    {
        char[][] board = new char[8][8];
        for ( int i = 0; i < 8; i++ )
        {
            for ( int j = 0; j < 8; j++ )
            {
                board[i][j] = ' ';
            }
        }
        ArrayList<Piece> pieces = getBoard();
        for ( Piece i : pieces )
        {

            if ( i instanceof Pawn && i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'P';
            }
            else if ( i instanceof Pawn && !i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'p';
            }
            if ( i instanceof Knight && i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'N';
            }
            else if ( i instanceof Knight && !i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'n';
            }
            if ( i instanceof Bishop && i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'B';
            }
            else if ( i instanceof Bishop && !i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'b';
            }
            if ( i instanceof Queen && i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'Q';
            }
            else if ( i instanceof Queen && !i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'q';
            }
            if ( i instanceof Rook && i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'R';
            }
            else if ( i instanceof Rook && !i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'r';
            }
            if ( i instanceof King && i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'K';
            }
            else if ( i instanceof King && !i.getColor() )
            {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = 'k';
            }
        }
        System.out.println( "  0 1 2 3 4 5 6 7 " );
        for ( int i = 0; i < 8; i++ )
        {

            for ( int j = 0; j < 8; j++ )
            {

                if ( j == 0 )
                {
                    System.out.print( i + " " );
                }

                System.out.print( board[i][j] + " " );
            }
            System.out.println();
        }

    }
}
