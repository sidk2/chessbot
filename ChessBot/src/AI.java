import java.util.ArrayList;

public class AI {
	private Board board;
	private int depth;
	private boolean checkmate = false;
	private boolean whiteWon = false;
	public AI(Board b, int depth)
	{
		board = b;
		this.depth = depth;
	}
	/**
	 * 
	 * @param color which player whose turn it is
	 * @return the best possible board config after a given turn. returns null if no possible moves.
	 */

	public boolean isCheckMate() {
	    return checkmate;
	}
	
	public boolean isWhite() {
	    return whiteWon;
	}
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
			if(value == 100000000 && depth == this.depth) {
			    System.out.println("Checkmate");
			    checkmate = true;
			    whiteWon = true;
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
			if(value == -100000000 && depth == this.depth) {
                System.out.println("Checkmate");
                checkmate = true;
                whiteWon = false;
            }
			return best;
		}
 
	}

}
