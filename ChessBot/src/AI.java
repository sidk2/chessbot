import java.util.ArrayList;

public class AI {
	private Board board;
	public AI(Board b)
	{
		board = b;
	}
	/**
	 * 
	 * @param color which player whose turn it is
	 * @return the best possible board config after a given turn. returns null if no possible moves.
	 */

	public Board minimax(Board board, int depth, boolean color) {
		if(depth == 1)
		{
			return board.getBestBoard(color);
		}
		if(color)
		{
			int value = -1000000;
			Board best = null;
			for(int i = 0; i < board.getPossibleMoves(color).size(); i++)
			{
				Board one = minimax(board.getPossibleMoves(color).get(i), depth - 1, !color);
				if(one.getValue()>value||best==null)
				{
					value = one.getValue();
					best = board.getPossibleMoves(color).get(i);
				}
			}
			return best;
		}
		else
		{
			int value = 1000000;
			Board best = null;
			for(int i = 0; i < board.getPossibleMoves(color).size(); i++)
			{
				Board one = minimax(board.getPossibleMoves(color).get(i), depth - 1, !color);
				if(one.getValue()<value||best==null)
				{
					value = one.getValue();
					best = board.getPossibleMoves(color).get(i);
				}
			}
			return best;
		}
			
	}
//
//	public Board minimax( Board board, int depth, boolean color )
//    {
//
//        if ( depth == 1)
//
//        {
//
//            return board.getBestBoard(color);
//
//        }
//        
//        Board nextBoard = null;
//        Board best = null;
//        
//        if ( color )
//
//        {
//
//            for ( int i = 0; i < board.getPossibleMoves( color ).size()-1; i++ )
//
//            {
//
//                Board one = minimax( board.getPossibleMoves( color ).get( i + 1 ),
//                    depth - 1,
//                    !color );
//
//                Board two = minimax( board.getPossibleMoves( color ).get( i ), depth - 1, !color );
//                
//                if(best == null) {
//                    best = one;
//                    nextBoard = board.getPossibleMoves( color ).get( i + 1 );
//                    if(one.getValue() < two.getValue()) {
//                        best = two;
//                        nextBoard = board.getPossibleMoves( color ).get( i );
//                    }
//                }
//                else{
//                    if(one.getValue() > two.getValue() && best.getValue() < one.getValue()) {
//                        best = one;
//                        nextBoard = board.getPossibleMoves( color ).get( i + 1 );
//                    }
//                    else if(two.getValue() > one.getValue() && best.getValue() < two.getValue()) {
//                        best = two;
//                        nextBoard = board.getPossibleMoves( color ).get( i );
//                    }
//                }
//                
//            }
//            return best;
//
//        }
//
//        else
//
//        {
//
//            for ( int i = 0; i < board.getPossibleMoves( color ).size() - 1; i++ )
//
//            {
//
//                Board one = minimax( board.getPossibleMoves( color ).get( i + 1 ),
//                    depth - 1,
//                    !color );
//
//                Board two = minimax( board.getPossibleMoves( color ).get( i ), depth - 1, !color );
//
//                if(best == null) {
//                    best = one;
//                    nextBoard = board.getPossibleMoves( color ).get( i + 1 );
//                    if(one.getValue() < two.getValue()) {
//                        best = two;
//                        nextBoard = board.getPossibleMoves( color ).get( i );
//                    }
//                }
//                else{
//                    if(one.getValue() > two.getValue() && best.getValue() < one.getValue()) {
//                        best = one;
//                        nextBoard = board.getPossibleMoves( color ).get( i + 1 );
//                    }
//                    else if(two.getValue() > one.getValue() && best.getValue() < two.getValue()) {
//                        best = two;
//                        nextBoard = board.getPossibleMoves( color ).get( i );
//                    }
//                }
//
//            }
//            return best;
//        }
//
//    }
}
