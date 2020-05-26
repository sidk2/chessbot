import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Display extends JPanel implements MouseListener {
	Tile[][] disp;
	Board board;
	private boolean reet;
	private boolean toMove;
	private char currPiece;
	private Tile oldTile;
	private String input = null;

	public Display() {
		disp = new Tile[8][8];
		board = new Board(true);
//      this.update(board.updateBoard(board.getPiece(new Location(0, 1)), new Pawn(0, 2, true)));
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				disp[y][x] = new Tile(board.charBoard()[y][x], y, x);
			}
		}

		this.addMouseListener(this);

		toMove = false;
		currPiece = ' ';
		oldTile = null;
	}

	public Board getBoard() {
		return board;
	}

	public boolean getReet() {
		return reet;
	}

	public void setReet(boolean b) {
		reet = b;
	}

	public String getInput() {
		return input;
	}

	public void update(Board board) {
		board = board;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				disp[y][x] = new Tile(board.charBoard()[y][x], y, x);
			}
		}
	}

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
		System.out.println(e.getClickCount());
		if (reet) {
			Tile t = disp[e.getX() / 100][e.getY() / 100];
			if (!Character.isLowerCase(currPiece)) {
				currPiece = t.currPiece;
				board.printBoard();
				ArrayList<Piece> save = new ArrayList<Piece>();
				for (Piece piece : board.getBoard()) {
					if (piece instanceof Knight) {
						save.add(new Knight(piece.getLoc().getXPos(), piece.getLoc().getYPos(), piece.getColor()));

					} else if (piece instanceof Pawn) {
						save.add(new Pawn(piece.getLoc().getXPos(), piece.getLoc().getYPos(), piece.getColor()));
					} else if (piece instanceof King) {
						save.add(new King(piece.getLoc().getXPos(), piece.getLoc().getYPos(), piece.getColor()));
					} else if (piece instanceof Bishop) {
						save.add(new Bishop(piece.getLoc().getXPos(), piece.getLoc().getYPos(), piece.getColor()));
					} else if (piece instanceof Rook) {
						save.add(new Rook(piece.getLoc().getXPos(), piece.getLoc().getYPos(), piece.getColor()));
					} else if (piece instanceof Queen) {
						save.add(new Queen(piece.getLoc().getXPos(), piece.getLoc().getYPos(), piece.getColor()));
					}
				}
				Board saved = new Board(save);

				if (oldTile.currPiece == 'p') {
					Pawn n = (Pawn) board.getPiece(new Location(oldTile.currX, oldTile.currY));
					boolean valid = false;
					for (Board b1 : n.findMoves(board, board.getBoard().indexOf(n))) {
						Board b2 = board;
						b2 = b2.updateBoard(n, new Pawn(t.currX, t.currY, false));

						if (b1.equals(b2)) {

							valid = true;
						} else {

						}

					}
					if (valid) {
						this.update(board.updateBoard(new Pawn(oldTile.currX, oldTile.currY, false),
								new Pawn(t.currX, t.currY, false)));
						board = board.updateBoard(new Pawn(oldTile.currX, oldTile.currY, false),
								new Pawn(t.currX, t.currY, false));
						 
						this.update(board);

					}
				}

				// currPiece = ' ';
			}
			else if (Character.isLowerCase(currPiece)) {
				
				
				System.out.println("You have Selected : " + t.currPiece + " " + t.currX + " " + t.currY);
				currPiece = t.currPiece;
				oldTile = t;
			

			}
			repaint();
			reet = false;
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
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
}