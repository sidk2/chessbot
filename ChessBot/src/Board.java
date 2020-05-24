import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JPanel implements MouseListener, MouseMotionListener {

	public Tile[][] board;
	
	private boolean turn;
    private Piece currPiece;
    private int currX;
    private int currY;
    
    public ArrayList<Piece> Whites;
    public ArrayList<Piece> Blacks;
    public ArrayList<Tile> movable;
    
    private CMD cmd;
    
    public Display display;
    
    public Board() throws IOException {
    	 board = new Tile[8][8];
    	 turn = true;
         Whites = new ArrayList<Piece>();
         Blacks = new ArrayList<Piece>();
         
         setLayout(new GridLayout(8, 8, 0, 0));

         this.addMouseListener(this);
         this.addMouseMotionListener(this);
         
         for (int row = 0; row < 8; row++) {
             for (int file = 0; file < 8; file++) {
                 board[row][file] = new Tile(this, file, row);
                 this.add(board[row][file]);
             }
         }
         
         
         //initialize all pieces
         

         board[7][4].put(new King(1, board[7][4], "wking.png"));
         board[0][4].put(new King(0, board[0][4], "bking.png"));
         
         board[7][3].put(new Queen(1, board[7][3], "wqueen.png"));
         board[0][3].put(new Queen(0, board[0][3], "bqueen.png"));

         board[7][0].put(new Rook(0, board[7][0], "wrook.png"));
         board[7][7].put(new Rook(0, board[7][7], "wrook.png"));
         board[0][0].put(new Rook(1, board[0][0], "brook.png"));
         board[0][7].put(new Rook(1, board[0][7], "brook.png"));

         board[0][2].put(new Bishop(0, board[0][2], "bbishop.png"));
         board[0][5].put(new Bishop(0, board[0][5], "bbishop.png"));
         board[7][2].put(new Bishop(1, board[7][2], "wbishop.png"));
         board[7][5].put(new Bishop(1, board[7][5], "wbishop.png"));

         board[7][1].put(new Knight(0, board[7][1], "wknight.png"));
         board[7][6].put(new Knight(0, board[7][6], "wknight.png"));
         board[0][1].put(new Knight(1, board[0][1], "bknight.png"));
         board[0][6].put(new Knight(1, board[0][6], "bknight.png"));
        
         for (int x = 0; x < 8; x++) {
             board[6][x].put(new Pawn(1, board[6][x], "wpawn.png"));
             board[1][x].put(new Pawn(0, board[1][x], "bpawn.png"));
         }
         
         for(int y = 0; y < 2; y++) {
             for (int x = 0; x < 8; x++) {
                 Blacks.add(board[y][x].getPiece());
                 Whites.add(board[7-y][x].getPiece());
             }
         }
         
    }
    
    public void setCurrPiece(Piece p) {
        currPiece = p;
    }
    
    public Piece getCurrPiece() {
        return currPiece;
    }
    
    public boolean getTurn() {
        return turn;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Tile t = board[y][x];
                t.paintComponent(g);
//                setCurrPiece(t.getPiece());                
            }
        }
        
//        if (currPiece != null) {
//            if ((currPiece.getCol() == 1 && turn)
//                    || (currPiece.getCol() == 0 && !turn)) {
//                Image i = currPiece.getImg();
//                g.drawImage(i, currX, currY, null);
//            }
//        }
        
    }
    
	@Override
	public void mouseDragged(MouseEvent e) {
		
//        currX = e.getX() - 24;
//        currY = e.getY() - 24;
//
//        repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
//		Tile sq = (Tile) this.getComponentAt(new Point(e.getX(), e.getY()));
//
//        if (currPiece != null) {
//            if (currPiece.getCol() == 0 && turn)
//                return;
//            if (currPiece.getCol() == 1 && !turn)
//                return;
//
//            ArrayList<Tile> possMoves = currPiece.getPossibleMoves(this);
//
//            if (possMoves.contains(sq)) {
//                sq.setDisplay(true);
//                currPiece.move(sq);
//
////                if (cmd.blackCheckMated()) {
////                    currPiece = null;
////                    repaint();
////                    this.removeMouseListener(this);
////                    this.removeMouseMotionListener(this);
////                    g.checkmateOccurred(0);
////                } else if (cmd.whiteCheckMated()) {
////                    currPiece = null;
////                    repaint();
////                    this.removeMouseListener(this);
////                    this.removeMouseMotionListener(this);
////                    g.checkmateOccurred(1);
////                } else {
//                    currPiece = null;
//                    turn = !turn;
//                    repaint();
////                    movable = cmd.getAllowableTiles(turn);
////                }
//
//            } else {
//                currPiece.getPos().setDisplay(true);
//                currPiece = null;
//            }
//        }
//
        repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		currX = e.getX();
		currY = e.getY();

		Tile t = (Tile) this.getComponentAt(new Point(e.getX(), e.getY()));
		System.out.println(t.getXNum() + " " + t.getYNum());
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		currX = e.getX();
//        currY = e.getY();
//
//        Tile t = (Tile) this.getComponentAt(new Point(e.getX(), e.getY()));
//
//        if (t.isOccupied()) {
//            currPiece = t.getPiece();
//            if (currPiece.getCol() == 0 && turn)
//                return;
//            if (currPiece.getCol() == 1 && !turn)
//                return;
//            t.setDisplay(false);
//        }
//        repaint();
//		
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
        JFrame w = new JFrame( "ChessBoard" );
        w.setBounds( 100, 100, 900, 925 );
        w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        Board panel = new Board();
        panel.setBackground( Color.WHITE );
        Container c = w.getContentPane();
        c.add( panel );
        w.setResizable( true );
        w.setVisible( true );
	 }
	
}
