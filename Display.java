import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Display extends JPanel implements MouseListener {
	Tile[][] disp;
	Board board;
	private boolean toMove;
	private char currPiece;
	private Tile oldTile;
	public boolean moved;
	
	public Display() {
		disp = new Tile[8][8];
		board = new Board(true);
//		this.update(board.updateBoard(board.getPiece(new Location(0, 1)), new Pawn(0, 2, true)));
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
            	disp[y][x] = new Tile(board.charBoard()[y][x], y, x);
            }
        }
        
        this.addMouseListener(this);
        
        toMove = false;
        currPiece = ' ';
        oldTile = null;
        moved = false;
	}
	
	public void update(Board b) {
		board =  b;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
            	disp[y][x] = new Tile(b.charBoard()[y][x], y, x);
            }
        }
	}
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
            	Tile t = disp[y][x];
                t.paintComponent(g);
//                    System.out.println(t.getX()+ " "  + t.getY());
            }
        }       

    }    
    
	@Override
	public void mouseClicked(MouseEvent e) {
		Tile t = disp[e.getX()/100][e.getY()/100];
		if(!toMove) {
				System.out.println("You have Selected : " + t.currPiece + " " + t.currX + " " + t.currY);
				currPiece = t.currPiece;
				toMove = true;
			oldTile = t;
		} else {
			System.out.println("move to:" + t.currX + " " + t.currY);
			toMove = false;
			Piece p = board.getPiece(new Location(oldTile.currX, oldTile.currY));
			if(p instanceof Pawn) {
				this.update(board.updateBoard(p, new Pawn(t.currX, t.currY, p.getColor())));
				moved = true;
			} else if(p instanceof Rook) {
				this.update(board.updateBoard(p, new Rook(t.currX, t.currY, p.getColor())));
				moved = true;
			} else if(p instanceof Bishop) {
				this.update(board.updateBoard(p, new Bishop(t.currX, t.currY, p.getColor())));
				moved = true;
			} else if(p instanceof Knight) {
				this.update(board.updateBoard(p, new Knight(t.currX, t.currY, p.getColor())));
				moved = true;
			} else if(p instanceof Queen) {
				this.update(board.updateBoard(p, new Queen(t.currX, t.currY, p.getColor())));
				moved = true;
			} else if(p instanceof King) {
				this.update(board.updateBoard(p, new King(t.currX, t.currY, p.getColor())));
				moved = true;
			}
			currPiece = ' ';
		}
		moved = !toMove;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		repaint();
	}

	public boolean ifMoved() {
		if(moved) {
			moved = false;
			return true;
		}
		return false;
	}
		
	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	} 
	 public static void main(String[] args) throws IOException {
	        JFrame w = new JFrame( "ChessBoard - Itr2" );
	        w.setBounds( 100, 100, 816, 836 );
	        w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	        Display panel = new Display();
	        panel.setBackground( Color.WHITE );
	        Container c = w.getContentPane();
	        c.add( panel );
	        w.setResizable( true );
	        w.setVisible( true );
	 }

//		System.out.println("Hi");
//		
//		Tile t = (Tile) this.getComponentAt(new Point(e.getX(), e.getY()));
//		System.out.println(t.getX() + " " + t.getY());
//
//		if(t.currPiece != ' ' && !toMove) {
//			System.out.println("You have Selected : " + t.currPiece);
//			currPiece = t.currPiece;
//			toMove = true;
//			oldTile = t;
//		} else {
//			oldTile.currPiece = ' ';
//			t.currPiece = currPiece;
//			toMove = false;
//		}
//		repaint();
	 
}
