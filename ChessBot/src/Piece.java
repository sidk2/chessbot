import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public abstract class Piece {

	private int color;
	private Tile position;
    
    private BufferedImage pieceImg;
    
    public Piece(int color, Tile pos, String file_Img) throws IOException {
    	this.color = color;
    	position = pos;
    	pieceImg = ImageIO.read(getClass().getResource(file_Img));
    }
	
    public void move(Tile pos) {
        
//        if(newX < 8 && newX >= 0 && newY < 8 && newY >= 0) {
//            
        position = pos;
//        	return true;
//        }
//        return false;
    }
    
    public Tile getPos() {
    	return position;
    }
    
    public int getCol() {
    	return color;
    }
    
    public Image getImg() {
    	return pieceImg;
    }
    
    public void draw(Graphics g) {
        
        g.drawImage(pieceImg, position.getX(), position.getY(), null);
    }
    
    public void setPosition(Tile newPos) {
    	position = newPos;	
    }
    
    public abstract ArrayList<Tile> getPossibleMoves(Board b);
    
    public ArrayList<Tile> getCols(Board b){
    	ArrayList<Tile> possibles = new ArrayList<Tile>();
		
		int col = getCol();
		int xPos = getPos().getXNum();
		int yPos = getPos().getYNum();
		
		Tile[][] currBoard = b.board;
		
		//all moves up
		int xTemp = xPos;
		int yTemp = yPos;
		int notBlockedUp;
		yTemp++;
		if((xTemp >= 0 && xTemp < 8) && (yTemp >= 0 && yTemp < 8)) 
		{
			if(!currBoard[yTemp][xTemp].isOccupied()) 
			{
				notBlockedUp = 2;
			} else if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
				notBlockedUp = 1;
			}
			else
			{
				notBlockedUp = 0;
			}
		}
		else
		{
			notBlockedUp = 0;
		}
		while(yTemp < 8 && notBlockedUp > 0)
		{
			possibles.add(currBoard[yTemp][xTemp]);
			yTemp++;
			if(notBlockedUp == 1) 
			{
				notBlockedUp = 0;
			}
			else if (currBoard[yTemp][xTemp].isOccupied()) { 
				if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
					notBlockedUp = 1;
				} else {
					notBlockedUp = 0;
				}
			}
		}
		
		//moves down
		xTemp = xPos;
		yTemp = yPos;
		int notBlockedDown;
		yTemp--;
		if((xTemp >= 0 && xTemp < 8) && (yTemp >= 0 && yTemp < 8)) 
		{
			if(!currBoard[yTemp][xTemp].isOccupied()) 
			{
				notBlockedDown = 2;
			} else if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
				notBlockedDown = 1;
			}
			else
			{
				notBlockedDown = 0;
			}
		}
		else
		{
			notBlockedDown = 0;
		}
		while(yTemp >= 0 && notBlockedDown > 0)
		{
			possibles.add(currBoard[yTemp][xTemp]);
			yTemp--;
			if(notBlockedDown == 1) 
			{
				notBlockedDown = 0;
			}
			else if (currBoard[yTemp][xTemp].isOccupied()) { 
				if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
					notBlockedDown = 1;
				} else {
					notBlockedDown = 0;
				}
			}
		}
		//moves left
		xTemp = xPos;
		yTemp = yPos;
		int notBlockedLeft;
		xTemp--;
		if((xTemp >= 0 && xTemp < 8) && (yTemp >= 0 && yTemp < 8)) 
		{
			if(!currBoard[yTemp][xTemp].isOccupied()) 
			{
				notBlockedLeft = 2;
			} else if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
				notBlockedLeft = 1;
			}
			else
			{
				notBlockedLeft = 0;
			}
		}
		else
		{
			notBlockedLeft = 0;
		}
		while(xTemp >= 0 && notBlockedLeft > 0)
		{
			possibles.add(currBoard[yTemp][xTemp]);
			xTemp--;
			if(notBlockedLeft == 1) 
			{
				notBlockedLeft = 0;
			}
			else if (currBoard[yTemp][xTemp].isOccupied()) { 
				if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
					notBlockedLeft = 1;
				} else {
					notBlockedLeft = 0;
				}
			}
		}
		//moves right
		xTemp = xPos;
		yTemp = yPos;
		int notBlockedRight;
		xTemp++;
		if((xTemp >= 0 && xTemp < 8) && (yTemp >= 0 && yTemp < 8)) 
		{
			if(!currBoard[yTemp][xTemp].isOccupied()) 
			{
				notBlockedRight = 2;
			} else if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
				notBlockedRight = 1;
			}
			else
			{
				notBlockedRight = 0;
			}
		}
		else
		{
			notBlockedRight = 0;
		}
		while(xTemp >= 0 && notBlockedRight > 0)
		{
			possibles.add(currBoard[yTemp][xTemp]);
			xTemp++;
			if(notBlockedRight == 1) 
			{
				notBlockedRight = 0;
			}
			else if (currBoard[yTemp][xTemp].isOccupied()) { 
				if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
					notBlockedRight = 1;
				} else {
					notBlockedRight = 0;
				}
			}
		}
		
		return possibles;
		
	}
    
    public ArrayList<Tile> getDiags(Board b)
    {
    	ArrayList<Tile> possibles = new ArrayList<Tile>();
		
		int col = getCol();
		int xPos = getPos().getXNum();
		int yPos = getPos().getYNum();
		
		Tile[][] currBoard = b.board;
		
		//all moves up and right
		int xTemp = xPos;
		int yTemp = yPos;
		int notBlockedUpRight;
		yTemp++;
		xTemp++;
		if((xTemp >= 0 && xTemp < 8) && (yTemp >= 0 && yTemp < 8)) 
		{
			if(!currBoard[yTemp][xTemp].isOccupied()) 
			{
				notBlockedUpRight = 2;
			} else if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
				notBlockedUpRight = 1;
			}
			else
			{
				notBlockedUpRight = 0;
			}
		} 
		else
		{
			notBlockedUpRight = 0;
		}
		while((yTemp < 8 && xTemp < 8) && notBlockedUpRight > 0)
		{
			possibles.add(currBoard[yTemp][xTemp]);
			yTemp++;
			xTemp++;
			if(notBlockedUpRight == 1) 
			{
				notBlockedUpRight = 0;
			}
			else if (currBoard[yTemp][xTemp].isOccupied()) { 
				if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
					notBlockedUpRight = 1;
				} else {
					notBlockedUpRight = 0;
				}
			}
		}
		//all moves up and left
		xTemp = xPos;
		yTemp = yPos;
		int notBlockedUpLeft;
		yTemp++;
		xTemp--;
		if((xTemp >= 0 && xTemp < 8) && (yTemp >= 0 && yTemp < 8)) 
		{
			if(!currBoard[yTemp][xTemp].isOccupied()) 
			{
				notBlockedUpLeft = 2;
			} else if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
				notBlockedUpLeft = 1;
			} else {
				notBlockedUpLeft = 0;
			}
		}
		else
		{
			notBlockedUpLeft = 0;
		}
		while((yTemp < 8 && xTemp < 8) && notBlockedUpLeft > 0)
		{
			possibles.add(currBoard[yTemp][xTemp]);
			yTemp++;
			xTemp--;
			if(notBlockedUpLeft == 1) 
			{
				notBlockedUpLeft = 0;
			}
			else if (currBoard[yTemp][xTemp].isOccupied()) { 
				if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
					notBlockedUpLeft = 1;
				} else {
					notBlockedUpLeft = 0;
				}
			}
		}
		
		//all moves down and right
		xTemp = xPos;
		yTemp = yPos;
		int notBlockedDownRight;
		yTemp--;
		xTemp++;
		if((xTemp >= 0 && xTemp < 8) && (yTemp >= 0 && yTemp < 8)) 
		{
			if(!currBoard[yTemp][xTemp].isOccupied()) 
			{
				notBlockedDownRight = 2;
			} else if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
				notBlockedDownRight = 1;
			} else {
				notBlockedDownRight = 0;
			}
		}
		else
		{
			notBlockedDownRight = 0;
		}
		while((yTemp < 8 && xTemp < 8) && notBlockedDownRight > 0)
		{
			possibles.add(currBoard[yTemp][xTemp]);
			yTemp--;
			xTemp++;
			if(notBlockedDownRight == 1) 
			{
				notBlockedDownRight = 0;
			}
			else if (currBoard[yTemp][xTemp].isOccupied()) { 
				if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
					notBlockedDownRight = 1;
				} else {
					notBlockedDownRight = 0;
				}
			}
		}
		
		//all moves down and left
		xTemp = xPos;
		yTemp = yPos;
		int notBlockedDownLeft;
		yTemp--;
		xTemp--;
		if((xTemp >= 0 && xTemp < 8) && (yTemp >= 0 && yTemp < 8)) 
		{
			if(!currBoard[yTemp][xTemp].isOccupied()) 
			{
				notBlockedDownLeft = 2;
			} else if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
				notBlockedDownLeft = 1;
			} else {
				notBlockedDownLeft = 0;
			}
		}
		else
		{
			notBlockedDownLeft = 0;
		}
		while((yTemp < 8 && xTemp < 8) && notBlockedDownLeft > 0)
		{
			possibles.add(currBoard[yTemp][xTemp]);
			yTemp--;
			xTemp--;
			if(notBlockedDownLeft == 1) 
			{
				notBlockedDownLeft = 0;
			}
			else if (currBoard[yTemp][xTemp].isOccupied()) { 
				if(currBoard[yTemp][xTemp].getColor() != this.getCol()) {
					notBlockedDownLeft = 1;
				} else {
					notBlockedDownLeft = 0;
				}
			}
		}
		
		return possibles;
		
    }
    
}
