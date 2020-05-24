import java.io.IOException;
import java.util.ArrayList;

public class Rook extends Piece 
{

	public Rook(int color, Tile pos, String file_Img) throws IOException {
		super(color, pos, file_Img);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Tile> getPossibleMoves(Board b) 
	{
//		ArrayList<Tile> possibles = new ArrayList<Tile>();
//		
//		int col = getCol();
//		int xPos = getPos().getXNum();
//		int yPos = getPos().getYNum();
//		
//		Tile[][] currBoard = b.board;
//		
//		//all moves up
//		int xTemp = xPos;
//		int yTemp = yPos;
//		int notBlockedUp;
//		yTemp++;
//		if((xTemp >= 0 && xTemp < 8) && (yTemp >= 0 && yTemp < 8)) 
//		{
//			if(!currBoard[yTemp][xTemp].isOccupied()) 
//			{
//				notBlockedUp = 2;
//			} else if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
//				notBlockedUp = 1;
//			}
//			else
//			{
//				notBlockedUp = 0;
//			}
//		}
//		else
//		{
//			notBlockedUp = 0;
//		}
//		while(yTemp < 8 && notBlockedUp > 0)
//		{
//			possibles.add(currBoard[yTemp][xTemp]);
//			yTemp++;
//			if(notBlockedUp == 1) 
//			{
//				notBlockedUp = 0;
//			}
//			else if (currBoard[yTemp][xTemp].isOccupied()) { 
//				if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
//					notBlockedUp = 1;
//				} else {
//					notBlockedUp = 0;
//				}
//			}
//		}
//		
//		//moves down
//		xTemp = xPos;
//		yTemp = yPos;
//		int notBlockedDown;
//		yTemp--;
//		if((xTemp >= 0 && xTemp < 8) && (yTemp >= 0 && yTemp < 8)) 
//		{
//			if(!currBoard[yTemp][xTemp].isOccupied()) 
//			{
//				notBlockedDown = 2;
//			} else if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
//				notBlockedDown = 1;
//			}
//			else
//			{
//				notBlockedDown = 0;
//			}
//		}
//		else
//		{
//			notBlockedDown = 0;
//		}
//		while(yTemp >= 0 && notBlockedDown > 0)
//		{
//			possibles.add(currBoard[yTemp][xTemp]);
//			yTemp--;
//			if(notBlockedDown == 1) 
//			{
//				notBlockedDown = 0;
//			}
//			else if (currBoard[yTemp][xTemp].isOccupied()) { 
//				if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
//					notBlockedDown = 1;
//				} else {
//					notBlockedDown = 0;
//				}
//			}
//		}
//		//moves left
//		xTemp = xPos;
//		yTemp = yPos;
//		int notBlockedLeft;
//		xTemp--;
//		if((xTemp >= 0 && xTemp < 8) && (yTemp >= 0 && yTemp < 8)) 
//		{
//			if(!currBoard[yTemp][xTemp].isOccupied()) 
//			{
//				notBlockedLeft = 2;
//			} else if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
//				notBlockedLeft = 1;
//			}
//			else
//			{
//				notBlockedLeft = 0;
//			}
//		}
//		else
//		{
//			notBlockedLeft = 0;
//		}
//		while(xTemp >= 0 && notBlockedLeft > 0)
//		{
//			possibles.add(currBoard[yTemp][xTemp]);
//			xTemp--;
//			if(notBlockedLeft == 1) 
//			{
//				notBlockedLeft = 0;
//			}
//			else if (currBoard[yTemp][xTemp].isOccupied()) { 
//				if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
//					notBlockedLeft = 1;
//				} else {
//					notBlockedLeft = 0;
//				}
//			}
//		}
//		//moves right
//		xTemp = xPos;
//		yTemp = yPos;
//		int notBlockedRight;
//		xTemp++;
//		if((xTemp >= 0 && xTemp < 8) && (yTemp >= 0 && yTemp < 8)) 
//		{
//			if(!currBoard[yTemp][xTemp].isOccupied()) 
//			{
//				notBlockedRight = 2;
//			} else if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
//				notBlockedRight = 1;
//			}
//			else
//			{
//				notBlockedRight = 0;
//			}
//		}
//		else
//		{
//			notBlockedRight = 0;
//		}
//		while(xTemp >= 0 && notBlockedRight > 0)
//		{
//			possibles.add(currBoard[yTemp][xTemp]);
//			xTemp++;
//			if(notBlockedRight == 1) 
//			{
//				notBlockedRight = 0;
//			}
//			else if (currBoard[yTemp][xTemp].isOccupied()) { 
//				if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
//					notBlockedRight = 1;
//				} else {
//					notBlockedRight = 0;
//				}
//			}
//		}
//		
		ArrayList<Tile> cols = super.getCols(b);
		return cols;
	}
	
	
	
}
