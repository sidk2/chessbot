import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * The class that plays as the computer, finds the next best move by calculating
 * a given amount of moves deep, the computer is always whtie
 *
 * @author Shreyas Kaasyap, Sidharth Kannan
 * @version May 25, 2020
 * @author Period: 1
 * @author Assignment: ChessBot
 *
 * @author Sources: Shreyas Kaasyap, Sidharth Kannan, Leo Yang
 */
public class AI
{
    /**
     * the current Board
     */
    private Board board;

    /**
     * the depth, the amount of moves in advance being calculated
     */
    private int depth;

    /**
     * a boolean that is true if the current position is checkmate
     */
    private boolean checkmate = false;

    /**
     * a boolean that is true if the current position is checkmate and white has
     * won the game
     */
    private boolean whiteWon = false;


    /**
     * @param b
     *            the current board
     * @param depth
     *            the amount of moves to calculate A constructor for the AI
     *            class, instantiates fields
     */
    public AI( Board b, int depth )
    {
        board = b;
        this.depth = depth;
    }


    /**
     * returns the checkmate boolean, true if position is checkmate, false
     * otherwise
     * 
     * @return boolean
     */
    public boolean isCheckMate()
    {
        return checkmate;
    }


    /**
     * returns the whiteWon boolean, true if white has won the game, false
     * otherwise
     * 
     * @return boolean
     */
    public boolean isWhite()
    {
        return whiteWon;
    }


    /**
     * Generates the next best move for the computer, recursive method that
     * finds the min/max board based on the iteration, returns a Board that is
     * the best possible Board for white calculating a certain number of moves
     * in advance
     * 
     * @param board
     *            the current board
     * @param depth
     *            the amount of moves in advance left to calculate
     * @param color
     *            true if it is white's turn, false otherwise
     * @return Board
     */
    public Board minimax( Board board, int depth, boolean color, int alpha, int beta)
    {
        int searched = 0;
        Map<String, Board> transpositions = new HashMap<String, Board>();
        if ( depth == 1 )
        {
            return board.getBestBoard( color );
        }
        if ( color )
        {
            if(transpositions.containsKey(board.hash()))
            {
                return transpositions.get(board.hash());
            }
            int value = -1000000;
            Board best = null;
            ArrayList<Board> poss = board.getPossibleMoves( color );
            searched += poss.size();
            for ( int i = 0; i < poss.size(); i++ )
            {
                Board one = minimax( poss.get( i ), depth - 1, !color, alpha, beta);
                if ( one == null )
                {
                    best = poss.get( i );
                    value = 100000000;
                    break;
                }
                if ( one.getValue() > value || best == null )
                {
                    value = one.getValue();
                    alpha = Math.max(alpha, value);
                    best = poss.get( i );
                }
                if(alpha >= beta) break;
            }
            if ( value == 100000000 && depth == this.depth )
            {
                System.out.println( "Checkmate" );
                checkmate = true;
                whiteWon = true;
            }
            // System.out.println(searched);
            transpositions.put(board.hash(), best);
            return best;
        }
        else
        {
            if(transpositions.containsKey(board.hash()))
            {
                return transpositions.get(board.hash());
            }
            int value = 1000000;
            Board best = null;
            for ( int i = 0; i < board.getPossibleMoves( color ).size(); i++ )
            {
                Board one = minimax( board.getPossibleMoves( color ).get( i ), depth - 1, !color, alpha, beta);
                if ( one == null )
                {
                    best = board.getPossibleMoves( color ).get( i );
                    value = -100000000;
                    break;
                }
                if ( one.getValue() < value || best == null )
                {
                    value = one.getValue();
                    beta = Math.min(beta, value);
                    best = board.getPossibleMoves( color ).get( i );
                }
                if (beta<=alpha) break;
            }
            if ( value == -100000000 && depth == this.depth )
            {
                System.out.println( "Checkmate" );
                checkmate = true;
                whiteWon = false;
            }
            transpositions.put(board.hash(), best);
            return best;
        }

    }

}
