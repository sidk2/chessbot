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
	public Board getBestBoard(boolean color)
	{
		Board best = null;
		for (Board b:(new AllMoves(board)).findAllMoves(board, color))
		{
			if(best == null)
			{
				best = b;
			}
			else
			{
				if (best.getValue()<b.getValue())
				{
					best = b;
				}
			}
		}
		return best;
	}
}
