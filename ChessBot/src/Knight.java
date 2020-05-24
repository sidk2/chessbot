import java.io.IOException;
import java.util.ArrayList;

public class Knight extends Piece{

	public Knight(int color, Tile pos, String file_Img) throws IOException {
		super(color, pos, file_Img);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Tile> getPossibleMoves(Board b) {
		ArrayList<Tile> possibles = new ArrayList<Tile>();
		int col = getCol();
		int xPos = getPos().getXNum();
		int yPos = getPos().getYNum();
		
		Tile[][] currBoard = b.board;
		
		if(yPos+2 < 8 && xPos + 1 < 8) {
			if(!currBoard[yPos+2][xPos+1].isOccupied() || (currBoard[yPos+2][xPos+1].getColor() != col))
			{
				possibles.add(currBoard[yPos+2][xPos+1]);
			}
		}
		
		if(yPos+2 < 8 && xPos - 1 > 0) {
			if(!currBoard[yPos+2][xPos-1].isOccupied() || (currBoard[yPos+2][xPos-1].getColor() != col))
			{
				possibles.add(currBoard[yPos+2][xPos-1]);
			}
		}
		
		if(yPos-2 > 0 && xPos + 1 < 8) {
			if(!currBoard[yPos-2][xPos+1].isOccupied() || (currBoard[yPos-2][xPos+1].getColor() != col))
			{
				possibles.add(currBoard[yPos-2][xPos+1]);
			}
		}
		
		if(yPos-2 > 0 && xPos - 1 > 0) {
			if(!currBoard[yPos-2][xPos-1].isOccupied() || (currBoard[yPos-2][xPos-1].getColor() != col))
			{
				possibles.add(currBoard[yPos-2][xPos-1]);
			}
		}
		
		if(yPos+1 < 8 && xPos + 2 < 8) {
			if(!currBoard[yPos+1][xPos+2].isOccupied() || (currBoard[yPos+1][xPos+2].getColor() != col))
			{
				possibles.add(currBoard[yPos+1][xPos+2]);
			}
		}
		
		if(yPos-1 > 0 && xPos +2 < 8) {
			if(!currBoard[yPos-1][xPos+2].isOccupied() || (currBoard[yPos-1][xPos+2].getColor() != col))
			{
				possibles.add(currBoard[yPos-1][xPos+2]);
			}
		}
		
		if(yPos + 1 < 8 && xPos -2 > 0) {
			if(!currBoard[yPos+1][xPos-2].isOccupied() || (currBoard[yPos+1][xPos-2].getColor() != col))
			{
				possibles.add(currBoard[yPos+1][xPos-2]);
			}
		}
		
		if(yPos-1 > 0 && xPos -2 > 0) {
			if(!currBoard[yPos-1][xPos-2].isOccupied() || (currBoard[yPos-1][xPos-2].getColor() != col))
			{
				possibles.add(currBoard[yPos-1][xPos-2]);
			}
		}
		
		return possibles;
		
	}

}
