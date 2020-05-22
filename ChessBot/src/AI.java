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
		if(depth == 0)
		{
			return board;
		}
		if(color)
		{
			for(int i = 0; i < board.getPossibleMoves(color).size(); i++)
			{
				Board one = minimax(board.getPossibleMoves(color).get(i+1), depth - 1, !color);
				Board two = minimax(board.getPossibleMoves(color).get(i), depth - 1, !color);
				if(one.getValue()> two.getValue())
					return board.getPossibleMoves(color).get(i+1);
				else return board.getPossibleMoves(color).get(i);
			}
		}
		else
		{
			for(int i = 0; i < board.getPossibleMoves(color).size(); i++)
			{
				Board one = minimax(board.getPossibleMoves(color).get(i+1), depth - 1, !color);
				Board two = minimax(board.getPossibleMoves(color).get(i), depth - 1, !color);
				if(Math.max(one.getValue(), two.getValue()) == one.getValue())
					return board.getPossibleMoves(color).get(i+1);
				else
					return board.getPossibleMoves(color).get(i);
			}
		}
		return board;
			
	}
}
