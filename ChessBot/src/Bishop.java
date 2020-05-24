import java.io.IOException;
import java.util.ArrayList;

public class Bishop extends Piece {

	public Bishop(int color, Tile pos, String file_Img) throws IOException {
		super(color, pos, file_Img);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Tile> getPossibleMoves(Board b) {
		
//		ArrayList<Tile> possibles = new ArrayList<Tile>();
//		
//		int col = getCol();
//		int xPos = getPos().getXNum();
//		int yPos = getPos().getYNum();
//		
//		Tile[][] currBoard = b.board;
//		
//		//all moves up and right
//		int xTemp = xPos;
//		int yTemp = yPos;
//		int notBlockedUpRight;
//		yTemp++;
//		xTemp++;
//		if((xTemp >= 0 && xTemp < 8) && (yTemp >= 0 && yTemp < 8)) 
//		{
//			if(!currBoard[yTemp][xTemp].isOccupied()) 
//			{
//				notBlockedUpRight = 2;
//			} else if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
//				notBlockedUpRight = 1;
//			}
//			else
//			{
//				notBlockedUpRight = 0;
//			}
//		} 
//		else
//		{
//			notBlockedUpRight = 0;
//		}
//		while((yTemp < 8 && xTemp < 8) && notBlockedUpRight > 0)
//		{
//			possibles.add(currBoard[yTemp][xTemp]);
//			yTemp++;
//			xTemp++;
//			if(notBlockedUpRight == 1) 
//			{
//				notBlockedUpRight = 0;
//			}
//			else if (currBoard[yTemp][xTemp].isOccupied()) { 
//				if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
//					notBlockedUpRight = 1;
//				} else {
//					notBlockedUpRight = 0;
//				}
//			}
//		}
//		//all moves up and left
//		xTemp = xPos;
//		yTemp = yPos;
//		int notBlockedUpLeft;
//		yTemp++;
//		xTemp--;
//		if((xTemp >= 0 && xTemp < 8) && (yTemp >= 0 && yTemp < 8)) 
//		{
//			if(!currBoard[yTemp][xTemp].isOccupied()) 
//			{
//				notBlockedUpLeft = 2;
//			} else if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
//				notBlockedUpLeft = 1;
//			} else {
//				notBlockedUpLeft = 0;
//			}
//		}
//		else
//		{
//			notBlockedUpLeft = 0;
//		}
//		while((yTemp < 8 && xTemp < 8) && notBlockedUpLeft > 0)
//		{
//			possibles.add(currBoard[yTemp][xTemp]);
//			yTemp++;
//			xTemp--;
//			if(notBlockedUpLeft == 1) 
//			{
//				notBlockedUpLeft = 0;
//			}
//			else if (currBoard[yTemp][xTemp].isOccupied()) { 
//				if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
//					notBlockedUpLeft = 1;
//				} else {
//					notBlockedUpLeft = 0;
//				}
//			}
//		}
//		
//		//all moves down and right
//		xTemp = xPos;
//		yTemp = yPos;
//		int notBlockedDownRight;
//		yTemp--;
//		xTemp++;
//		if((xTemp >= 0 && xTemp < 8) && (yTemp >= 0 && yTemp < 8)) 
//		{
//			if(!currBoard[yTemp][xTemp].isOccupied()) 
//			{
//				notBlockedDownRight = 2;
//			} else if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
//				notBlockedDownRight = 1;
//			} else {
//				notBlockedDownRight = 0;
//			}
//		}
//		else
//		{
//			notBlockedDownRight = 0;
//		}
//		while((yTemp < 8 && xTemp < 8) && notBlockedDownRight > 0)
//		{
//			possibles.add(currBoard[yTemp][xTemp]);
//			yTemp--;
//			xTemp++;
//			if(notBlockedDownRight == 1) 
//			{
//				notBlockedDownRight = 0;
//			}
//			else if (currBoard[yTemp][xTemp].isOccupied()) { 
//				if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
//					notBlockedDownRight = 1;
//				} else {
//					notBlockedDownRight = 0;
//				}
//			}
//		}
//		
//		//all moves down and left
//		xTemp = xPos;
//		yTemp = yPos;
//		int notBlockedDownLeft;
//		yTemp--;
//		xTemp--;
//		if((xTemp >= 0 && xTemp < 8) && (yTemp >= 0 && yTemp < 8)) 
//		{
//			if(!currBoard[yTemp][xTemp].isOccupied()) 
//			{
//				notBlockedDownLeft = 2;
//			} else if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
//				notBlockedDownLeft = 1;
//			} else {
//				notBlockedDownLeft = 0;
//			}
//		}
//		else
//		{
//			notBlockedDownLeft = 0;
//		}
//		while((yTemp < 8 && xTemp < 8) && notBlockedDownLeft > 0)
//		{
//			possibles.add(currBoard[yTemp][xTemp]);
//			yTemp--;
//			xTemp--;
//			if(notBlockedDownLeft == 1) 
//			{
//				notBlockedDownLeft = 0;
//			}
//			else if (currBoard[yTemp][xTemp].isOccupied()) { 
//				if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
//					notBlockedDownLeft = 1;
//				} else {
//					notBlockedDownLeft = 0;
//				}
//			}
//		}
//		
//		return possibles;
//		
		ArrayList<Tile> diags = super.getDiags(b);
		return diags;
	}

}
