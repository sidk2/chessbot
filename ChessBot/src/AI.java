import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

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

    public Map<String, Entry<Board, Integer>> transpositions = new HashMap<String, Entry<Board, Integer>>();



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
    public Board minimax( Board board, int depth, boolean color, double alpha, double beta)
    {
        for (Piece p : board.getBoard())
        {
            if( p instanceof Pawn)
            {
                ((Pawn) p).setPrevBoard(board);
            }
        }
        String hash = board.hash();
        ArrayList<Board> bestSet = new ArrayList<Board>();
        int searched = 0;
        if ( depth == 1 )
        {
            return board.getBestBoard( color );
        }
        if ( color )
        {
            if(transpositions.containsKey(hash) && transpositions.get(hash).getValue() > 3)
            {
                return transpositions.get(hash).getKey();
            }
            double value = -1000000;
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
                    // bestSet.clear();;
                    value = one.getValue();
                    alpha = Math.max(alpha, value);
                    best = poss.get( i );
                    // bestSet.add(best);
                }
                // else if ( one.getValue() == value){
                //     bestSet.add(one);
                // }
                if(alpha >= beta) break;
            }
            if ( value == 100000000 && depth == this.depth )
            {
                System.out.println( "Checkmate" );
                checkmate = true;
                whiteWon = true;
            }

            transpositions.put(hash, new SimpleEntry<Board, Integer>(best, depth));
            // if(bestSet.size() > 1){
            //     Board ret = null;
            //     for(Board b : bestSet)
            //     {
            //         Board temp = minimax(b, depth/2, color, alpha, beta);
            //         if (ret == null || temp.getValue() > ret.getValue()) ret = b;
            //     }
            //     best = ret;
            // }
            return best;
        }
        else
        {
            if(transpositions.containsKey(hash) && transpositions.get(hash).getValue() > 3)
            {
                return transpositions.get(hash).getKey();
            }
            double value = 1000000.0;
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
            transpositions.put(hash, new SimpleEntry<Board, Integer>(best, depth));
            return best;
        }

    }

    public Board minimax_no_transpose( Board board, int depth, boolean color, double alpha, double beta)
    {
        int searched = 0;
        if ( depth == 1 )
        {
            return board.getBestBoard( color );
        }
        if ( color )
        {
            double value = -1000000;
            Board best = null;
            ArrayList<Board> poss = board.getPossibleMoves( color );
            searched += poss.size();
            for ( int i = 0; i < poss.size(); i++ )
            {
                Board one = minimax_no_transpose( poss.get( i ), depth - 1, !color, alpha, beta);
                if ( one == null )
                {
                    best = poss.get( i );
                    value = 100000000;
                    break;
                }
                if ( one.getValue() > value || best == null )
                {
                    value = one.getValue();
                    best = poss.get( i );
                }
                if(value >= beta) break;
                alpha = Math.max(alpha, value);

            }
            if ( value == 100000000 && depth == this.depth )
            {
                System.out.println( "Checkmate" );
                checkmate = true;
                whiteWon = true;
            }
            return best;
        }
        else
        {
            double value = 1000000.0;
            Board best = null;
            ArrayList<Board> list = board.getPossibleMoves( color );
            for ( int i = 0; i < list.size(); i++ )
            {
                Board one = minimax_no_transpose( list.get( i ), depth - 1, !color, alpha, beta);
                if ( one == null )
                {
                    best = list.get( i );
                    value = -100000000;
                    break;
                }
                if ( one.getValue() < value || best == null )
                {
                    value = one.getValue();
                    best = list.get( i );
                }
                if (value<=alpha) break;
                beta = Math.min(beta, value);

            }
            if ( value == -100000000 && depth == this.depth )
            {
                System.out.println( "Checkmate" );
                checkmate = true;
                whiteWon = false;
            }
            return best;
        }

    }
    public Board search( Board board, int depth, boolean color)
    {
                
        Board ret = null;
        long time = System.currentTimeMillis();

        ret =  minimax_no_transpose(board, depth, color, -20000, 20000);

        // double window_size = 1;
        // double alpha = -20000;
        // double beta = 20000;
        // double eval = 0;
        // for ( int i = 1; i <= depth; i++){
            
        //     ret = minimax_no_transpose(board, i, color, alpha, beta);
        //     eval = ret.getValue();
        //         if(eval <= alpha || eval >= beta){
        //             i--;
        //             alpha = -20000;
        //             beta = 20000;
        //         }
        //         else{
        //             alpha = eval + window_size;
        //             beta = eval - window_size;
        //         }
            
        // }
        long end = System.currentTimeMillis();
        System.out.println(end - time);
        return ret;
    }
    
}

