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
			for(int i = 0; i < board.getPossibleMoves( color).size(); i++)
			{
				Board one = minimax(board.getPossibleMoves( color).get(i), depth - 1, !color);
				if(one == null) {
				    //System.out.println("Checkmate");
				    best = board.getPossibleMoves( color ).get( i );
				    value = 100000000;
				    break;
				}
				if(one.getValue()>value||best==null)
				{
					value = one.getValue();
					best = board.getPossibleMoves( color).get(i);
				}
			}
			if(value == 100000000 && depth == 4) {
			    System.out.println("Checkmate");
			}
			return best;
		}
		else
		{
			int value = 1000000;
			Board best = null;
			for(int i = 0; i < board.getPossibleMoves( color).size(); i++)
			{
				Board one = minimax(board.getPossibleMoves( color).get(i), depth - 1, !color);
				if(one == null) {
                    //System.out.println("Checkmate");
                    best = board.getPossibleMoves( color ).get( i );
                    value = -100000000;
                    break;
                }
				if(one.getValue()<value||best==null)
				{
					value = one.getValue();
					best = board.getPossibleMoves(color).get(i);
				}
			}
			if(value == -100000000 && depth == 4) {
                System.out.println("Checkmate");
            }
			return best;
		}
 
	}

}
