import java.util.ArrayList;


/**
 * An interface for all the pieces: Pawn, Bishop, Knight, Queen, King, Rook
 *
 * @author Shreyas Kaasyap
 * @version May 25, 2020
 * @author Period: 1
 * @author Assignment: ChessBot
 *
 * @author Sources: Shreyas Kaasyap, Sidharth Kannan, Leo Yang
 */
interface Piece
{
    /**
     * Finds all possible resulting boards after moving this piece, includes
     * illegal moves
     * 
     * @param b
     *            the current chessBoard
     * @param index
     *            the index of that piece in the ArrayList of the current Board
     * @return ArrayList<Board> the ArrayList of possible Boards
     */
    ArrayList<Board> findMoves( Board b, int index );


    /**
     * This method returns true if the piece is giving check to the opposite
     * colored king
     * 
     * @param b
     *            the current board
     * @param index
     *            the index of that piece in the ArrayList of the current Board
     * @return boolean if the piece is giving check or not
     */
    boolean isInCheck( Board b, int index );


    /**
     * returns the location of the piece, 0-indexed
     * 
     * @return Location
     */
    Location getLoc();


    /**
     * returns the color of the piece, true = white, false = black
     * 
     * @return boolean
     */
    boolean getColor();


    /**
     * Returns the material value of the piece, positive for a white piece,
     * negative for a black piece
     * 
     * @return int
     */
    int getValue();


    /**
     * Compares two pieces, returns true if they are equal, false otherwise
     * 
     * @param other
     *            the piece to compare with
     * @return boolean
     */
    boolean equals( Piece other );
}
