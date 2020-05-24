import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Tile extends JComponent {

	private int color;
	private Piece currPiece;
	private boolean toDisplay;

	Board board;

	private int currX;
	private int currY;

	public Tile(Board b, int xPos, int yPos) {
		board = b;
		int xMod = xPos % 2;
		int yMod = yPos % 2;

		if ((xMod == 0 && yMod == 0) || (xMod == 1 && yMod == 1)) {
			color = 1;
		} else {
			color = 2;
		}
		currX = xPos;
		currY = yPos;
		toDisplay = true;
		this.setBorder(BorderFactory.createEmptyBorder());
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (this.color == 1) {
			g.setColor(Color.WHITE);
		} else {
			g.setColor(Color.GRAY);
		}

		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

		if (currPiece != null && toDisplay) {
			currPiece.draw(g);
		}
	}

	public boolean isOccupied() {
		return (currPiece != null);
	}

	public void setDisplay(boolean b) {
		toDisplay = b;
	}
	
	public int getXNum() {
		return currX;
	}

	public int getYNum() {
		return currY;
	}

	public int getColor() {
		return this.color;
	}

	public Piece getPiece() {
		return currPiece;
	}

	public Piece capture(Piece p) {
        Piece taken = getPiece();
        removePiece();
        currPiece = p;
        if(taken.getCol() == 0)
        {
        	board.Whites.remove(taken);
        }
        if(taken.getCol() == 1)
        {
        	board.Blacks.remove(taken);
        }
        return taken;
    }

	public void put(Piece p) {
		currPiece = p;
		p.setPosition(this);
	}

	public Piece removePiece() {
		Piece p = currPiece;
		currPiece = null;
		return p;
	}

}
