import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece 
{
	
	private boolean unmoved;
	
    public Pawn(int color, Tile t, String fileImg) throws IOException 
    {
        super(color, t, fileImg);
    }
	
    @Override
    public void move(Tile moveTo) 
    {
    	unmoved = false;
    	super.move(moveTo);
    }

	@Override
	public ArrayList<Tile> getPossibleMoves(Board b) 
	{
		
		ArrayList<Tile> possibles = new ArrayList<Tile>();
		
		int col = getCol();
		int xPos = getPos().getXNum();
		int yPos = getPos().getYNum();
		
		Tile[][] currBoard = b.board;
		
		//check white
		if(col == 0) 
		{
			//unmoved pawn can go forward two steps
			if(unmoved) 
			{
				Tile twoUp = currBoard[yPos + 2][xPos];
				//check if occupied
				if(!twoUp.isOccupied()) 
				{
					possibles.add(twoUp);
				}
			}
			//same process with only next one
			Tile oneUp = currBoard[yPos + 1][xPos];
			//check if occupied
			if(yPos+1 < 8 && !oneUp.isOccupied()) 
			{
				possibles.add(oneUp);
			}
			//check if u can take
			//check for top right diagonal
			if (yPos + 1 < 8)
			{
				if(xPos + 1 < 8 && currBoard[yPos+1][xPos+1].isOccupied() && (currBoard[yPos+1][xPos+1].getColor() == 1)) 
				{
					possibles.add(currBoard[yPos+1][xPos+1]);
				}
				if(xPos - 1 > 0 && currBoard[yPos+1][xPos-1].isOccupied() && (currBoard[yPos+1][xPos-1].getColor() == 1)) 
				{
					possibles.add(currBoard[yPos+1][xPos-1]);
				}
			}
		}
		
		//check black
		if(col == 1) 
		{
			//unmoved pawn can go forward two steps
			if(unmoved) 
			{
				Tile twoDown = currBoard[yPos - 2][xPos];
				//check if occupied
				if(!twoDown.isOccupied()) 
				{
					possibles.add(twoDown);
				}
			}
			//same process with only next one
			Tile oneDown = currBoard[yPos - 1][xPos];
			//check if occupied
			if(yPos-1 >= 0 && !oneDown.isOccupied()) 
			{
				possibles.add(oneDown);
			}
			//check if u can take
			//check for top right diagonal
			if (yPos - 1 >= 0)
			{
				if(xPos + 1 < 8 && currBoard[yPos-1][xPos+1].isOccupied()  && (currBoard[yPos-1][xPos+1].getColor() == 0)) 
				{
					possibles.add(currBoard[yPos-1][xPos+1]);
				}
				if(xPos - 1 > 0 && currBoard[yPos-1][xPos-1].isOccupied()  && (currBoard[yPos-1][xPos-1].getColor() == 0)) 
				{
					possibles.add(currBoard[yPos-1][xPos-1]);
				}
			}
		}
		
		return possibles;
	}
    
}
