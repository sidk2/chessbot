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
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * A display class that is the display for the GUI, has a helper method to
 * determine if a move is valid, has methods that are called from test.java
 *
 * @author Shreyas Kaasyap
 * @version May 26, 2020
 * @author Period: 1
 * @author Assignment: ChessBot
 *
 * @author Sources: Shreyas Kaasyap, Sidharth Kannan, Leo Yang
 */
@SuppressWarnings("serial")
public class Display extends JPanel implements MouseListener
{
    /**
     * a matrix of tiles which represents the current board for display
     */
    private Tile[][] disp;

    /**
     * the current board
     */
    private Board board;

    /**
     * true if it is white's turn to move, false otherwise
     */
    private boolean toMove;

    /**
     * A instance variable used in MouseClicked, represents the first tile
     * pressed
     */
    private Tile oldTile;

    /**
     * The string that determines the input that describes the current move
     */
    private String input = null;

    /**
     * a boolean that is false while the user has an illegal move, true when its
     * a legal move
     */
    private boolean isSuccessful = false;


    /**
     * instantiates the variables, creates a new Display and sets the current
     * board
     */
    public Display()
    {
        disp = new Tile[8][8];
        board = new Board();
        for ( int x = 0; x < 8; x++ )
        {
            for ( int y = 0; y < 8; y++ )
            {
                disp[x][y] = new Tile( board.charBoard()[x][y], x, y );
            }
        }
        this.addMouseListener( this );

        toMove = false;
        oldTile = null;
    }


    /**
     * returns the current Board
     * 
     * @return Board
     */
    public Board getBoard()
    {
        return board;
    }


    /**
     * Sets the current board to a given board
     * 
     * @param b
     *            the board to update the current board to
     */
    public void setBoard( Board b )
    {
        ArrayList<Piece> pieces = new ArrayList<>();
        for ( Piece i : b.getBoard() )
        {
            int xPos = i.getLoc().getXPos();
            int yPos = i.getLoc().getYPos();
            boolean color = i.getColor();
            if ( i instanceof Pawn )
            {
                pieces.add( new Pawn( xPos, yPos, color ) );
            }
            if ( i instanceof Knight )
            {
                pieces.add( new Knight( xPos, yPos, color ) );
            }
            if ( i instanceof Bishop )
            {
                pieces.add( new Bishop( xPos, yPos, color ) );
            }
            if ( i instanceof Queen )
            {
                pieces.add( new Queen( xPos, yPos, color ) );
            }
            if ( i instanceof Rook )
            {
                pieces.add( new Rook( xPos, yPos, color ) );
            }
            if ( i instanceof King )
            {
                pieces.add( new King( xPos, yPos, color ) );
            }
        }
        board = new Board( pieces );
    }


    /**
     * returns the input string
     * 
     * @return String
     */
    public String getInput()
    {
        return input;
    }


    /**
     * sets the input string, only used to set the input string back to null
     * 
     * @param s
     *            the string to set the input string to
     */
    public void setInput( String s )
    {
        input = s;
    }


    /**
     * returns the isSuccessful variable
     * 
     * @return boolean
     */
    public boolean getUpdate()
    {
        return isSuccessful;
    }


    /**
     * sets the isSuccessful variable, only used to set it back to false after a
     * move is made
     * 
     * @param change
     *            the value to set isSuccessful to
     */
    public void setUpdate( boolean change )
    {
        isSuccessful = change;
    }


    /**
     * updates the current board to board b and prints out the current board
     * 
     * @param b
     *            the board to update to
     */
    public void update( Board b )
    {
        ArrayList<Piece> pieces = new ArrayList<>();
        for ( Piece i : b.getBoard() )
        {
            int xPos = i.getLoc().getXPos();
            int yPos = i.getLoc().getYPos();
            boolean color = i.getColor();
            if ( i instanceof Pawn )
            {
                pieces.add( new Pawn( xPos, yPos, color ) );
            }
            if ( i instanceof Knight )
            {
                pieces.add( new Knight( xPos, yPos, color ) );
            }
            if ( i instanceof Bishop )
            {
                pieces.add( new Bishop( xPos, yPos, color ) );
            }
            if ( i instanceof Queen )
            {
                pieces.add( new Queen( xPos, yPos, color ) );
            }
            if ( i instanceof Rook )
            {
                pieces.add( new Rook( xPos, yPos, color ) );
            }
            if ( i instanceof King )
            {
                pieces.add( new King( xPos, yPos, color ) );
            }
        }
        board = new Board( pieces );
        for ( int x = 0; x < 8; x++ )
        {
            for ( int y = 0; y < 8; y++ )
            {
                disp[x][y] = new Tile( b.charBoard()[x][y], x, y );
            }
        }
        repaint();
    }


    @Override
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        for ( int x = 0; x < 8; x++ )
        {
            for ( int y = 0; y < 8; y++ )
            {
                Tile t = disp[x][y];
                t.paintComponent( g );
            }
        }

    }


    @Override
    public void mouseClicked( MouseEvent e )
    {
        Tile t = disp[e.getX() / 100][e.getY() / 100];
        if ( !toMove )
        {
            System.out
                .println( "You have Selected : " + t.currPiece + " " + t.currX + " " + t.currY );
            toMove = true;
            oldTile = t;
        }
        else
        {
            if ( toMove )
            {
                System.out.println( "move to:" + t.currX + " " + t.currY );
                System.out.println();
                toMove = false;
                Piece p = board.getPiece( new Location( oldTile.currX, oldTile.currY ) );
                if ( p instanceof Pawn )
                {
                    input = "Pawn at " + oldTile.currX + " " + oldTile.currY + " to " + t.currX
                        + " " + t.currY;
                    String ans = helper( input );
                    if ( ans.equals( "Success" ) )
                    {
                        this.update(
                            board.updateBoard( p, new Pawn( t.currX, t.currY, p.getColor() ) ) );
                        isSuccessful = true;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog( this, ans );
                    }

                }
                else if ( p instanceof Rook )
                {
                    ;
                    input = "Rook at " + oldTile.currX + " " + oldTile.currY + " to " + t.currX
                        + " " + t.currY;
                    String ans = helper( input );
                    if ( ans.equals( "Success" ) )
                    {
                        this.update(
                            board.updateBoard( p, new Rook( t.currX, t.currY, p.getColor() ) ) );
                        isSuccessful = true;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog( this, ans );
                    }

                }
                else if ( p instanceof Bishop )
                {
                    input = "Bishop at " + oldTile.currX + " " + oldTile.currY + " to " + t.currX
                        + " " + t.currY;
                    String ans = helper( input );
                    if ( ans.equals( "Success" ) )
                    {
                        this.update(
                            board.updateBoard( p, new Bishop( t.currX, t.currY, p.getColor() ) ) );
                        isSuccessful = true;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog( this, ans );
                    }

                }
                else if ( p instanceof Knight )
                {
                    input = "Knight at " + oldTile.currX + " " + oldTile.currY + " to " + t.currX
                        + " " + t.currY;
                    String ans = helper( input );
                    if ( ans.equals( "Success" ) )
                    {
                        this.update(
                            board.updateBoard( p, new Knight( t.currX, t.currY, p.getColor() ) ) );
                        isSuccessful = true;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog( this, ans );
                    }

                }
                else if ( p instanceof Queen )
                {
                    input = "Queen at " + oldTile.currX + " " + oldTile.currY + " to " + t.currX
                        + " " + t.currY;
                    String ans = helper( input );
                    if ( ans.equals( "Success" ) )
                    {
                        this.update(
                            board.updateBoard( p, new Queen( t.currX, t.currY, p.getColor() ) ) );
                        isSuccessful = true;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog( this, ans );
                    }

                }
                else if ( p instanceof King )
                {
                    input = "King at " + oldTile.currX + " " + oldTile.currY + " to " + t.currX
                        + " " + t.currY;
                    String ans = helper( input );
                    if ( ans.equals( "Success" ) )
                    {
                        this.update(
                            board.updateBoard( p, new King( t.currX, t.currY, p.getColor() ) ) );
                        isSuccessful = true;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog( this, ans );
                    }

                }
            }
        }
    }


    /**
     * A helper method to determine if a given move is valid, the given move is
     * given as a string, returns "Success" if the move is valid, or returns a
     * message explaining why the move is invalid
     * 
     * @param str
     *            the move made by the user
     * @return String
     */
    public String helper( String str )
    {
        try
        {

            ArrayList<Piece> save = new ArrayList<Piece>();

            for ( Piece piece : board.getBoard() )
            {
                if ( piece instanceof Knight )
                {
                    save.add( new Knight( piece.getLoc().getXPos(),
                        piece.getLoc().getYPos(),
                        piece.getColor() ) );

                }
                else if ( piece instanceof Pawn )
                {
                    save.add( new Pawn( piece.getLoc().getXPos(),
                        piece.getLoc().getYPos(),
                        piece.getColor() ) );
                }
                else if ( piece instanceof King )
                {
                    save.add( new King( piece.getLoc().getXPos(),
                        piece.getLoc().getYPos(),
                        piece.getColor() ) );
                }
                else if ( piece instanceof Bishop )
                {
                    save.add( new Bishop( piece.getLoc().getXPos(),
                        piece.getLoc().getYPos(),
                        piece.getColor() ) );
                }
                else if ( piece instanceof Rook )
                {
                    save.add( new Rook( piece.getLoc().getXPos(),
                        piece.getLoc().getYPos(),
                        piece.getColor() ) );
                }
                else if ( piece instanceof Queen )
                {
                    save.add( new Queen( piece.getLoc().getXPos(),
                        piece.getLoc().getYPos(),
                        piece.getColor() ) );
                }
            }

            Board saved = new Board( save );

            String[] s = str.split( "\\s+" );

            if ( s[0].contains( "Knight" ) )
            {
                if ( board.getPiece(
                    new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) ) == null )
                {
                    return "There is no Knight there! Please try again.";
                }
                else if ( !board
                    .getPiece( new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                    .getClass()
                    .getCanonicalName()
                    .contentEquals( "Knight" ) )
                {
                    return "There is no Knight there! Please try again.";

                }
                else if ( board
                    .getPiece( new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                    .getColor() )
                {
                    return "That's not your Knight! Please try again.";
                }
                else
                {
                    Knight n = (Knight)board.getPiece(
                        new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) );
                    boolean valid = false;
                    boolean inCheck = false;
                    for ( Board b1 : n.findMoves( board, board.getBoard().indexOf( n ) ) )
                    {

                        Board b2 = board;
                        b2 = b2.updateBoard( n,
                            new Knight( Integer.parseInt( s[5] ),
                                Integer.parseInt( s[6] ),
                                false ) );

                        if ( b1.equals( b2 ) )
                        {
                            valid = true;
                            for ( Piece i : b1.getBoard() )
                            {
                                if ( i.getColor() && i.isInCheck( b1, b1.getBoard().indexOf( i ) ) )
                                {
                                    valid = false;
                                    break;
                                }
                            }
                            break;

                        }

                    }
                    if ( valid )
                    {
                        return "Success";
                    }
                    else
                    {
                        board = new Board( save );
                        return "Invalid move! Please try again.";
                    }
                }
            }
            else if ( s[0].contains( "Pawn" ) )
            {
                if ( board.getPiece(
                    new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) ) == null )
                {
                    return "There is no Pawn there! Please try again.";
                }
                else if ( !board
                    .getPiece( new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                    .getClass()
                    .getCanonicalName()
                    .contentEquals( "Pawn" ) )
                {
                    return "There is no Pawn there! Please try again.";

                }
                else if ( board
                    .getPiece( new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                    .getColor() )
                {
                    return "That's not your Pawn! Please try again.";
                }
                else
                {
                    Pawn n = (Pawn)board.getPiece(
                        new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) );
                    boolean valid = false;
                    boolean inCheck = false;
                    for ( Board b1 : n.findMoves( board, board.getBoard().indexOf( n ) ) )
                    {
                        Board b2 = board;
                        b2 = b2.updateBoard( n,
                            new Pawn( Integer.parseInt( s[5] ), Integer.parseInt( s[6] ), false ) );

                        if ( b1.equals( b2 ) )
                        {
                            valid = true;
                            for ( Piece i : b1.getBoard() )
                            {
                                if ( i.getColor() && i.isInCheck( b1, b1.getBoard().indexOf( i ) ) )
                                {
                                    valid = false;
                                    break;
                                }
                            }
                            break;

                        }

                    }
                    if ( valid )
                    {
                        return "Success";
                    }
                    else
                    {
                        board = new Board( save );
                        return "Invalid move! Please try again.";
                    }
                }
            }
            else if ( s[0].contains( "Rook" ) )
            {
                if ( board.getPiece(
                    new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) ) == null )
                {
                    return "There is no Rook there! Please try again.";
                }
                else if ( !board
                    .getPiece( new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                    .getClass()
                    .getCanonicalName()
                    .contentEquals( "Rook" ) )
                {
                    return "There is no Rook there! Please try again.";

                }
                else if ( board
                    .getPiece( new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                    .getColor() )
                {
                    return "That's not your Rook! Please try again.";
                }
                else
                {
                    Rook n = (Rook)board.getPiece(
                        new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) );
                    boolean valid = false;
                    boolean inCheck = false;
                    for ( Board b1 : n.findMoves( board, board.getBoard().indexOf( n ) ) )
                    {

                        Board b2 = board;
                        b2 = b2.updateBoard( n,
                            new Rook( Integer.parseInt( s[5] ), Integer.parseInt( s[6] ), false ) );

                        if ( b1.equals( b2 ) )
                        {
                            valid = true;
                            for ( Piece i : b1.getBoard() )
                            {
                                if ( i.getColor() && i.isInCheck( b1, b1.getBoard().indexOf( i ) ) )
                                {
                                    valid = false;
                                    break;
                                }
                            }
                            break;

                        }

                    }
                    if ( valid )
                    {
                        return "Success";
                    }
                    else
                    {
                        board = new Board( save );
                        return "Invalid move! Please try again.";
                    }
                }
            }
            else if ( s[0].contains( "Queen" ) )
            {
                if ( board.getPiece(
                    new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) ) == null )
                {
                    return "There is no Queen there! Please try again.";
                }
                else if ( !board
                    .getPiece( new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                    .getClass()
                    .getCanonicalName()
                    .contentEquals( "Queen" ) )
                {
                    return "There is no Queen there! Please try again.";

                }
                else if ( board
                    .getPiece( new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                    .getColor() )
                {
                    return "That's not your Queen! Please try again.";
                }
                else
                {
                    Queen n = (Queen)board.getPiece(
                        new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) );
                    boolean valid = false;
                    boolean inCheck = false;
                    for ( Board b1 : n.findMoves( board, board.getBoard().indexOf( n ) ) )
                    {

                        Board b2 = board;
                        b2 = b2.updateBoard( n,
                            new Queen( Integer.parseInt( s[5] ),
                                Integer.parseInt( s[6] ),
                                false ) );

                        if ( b1.equals( b2 ) )
                        {
                            valid = true;
                            for ( Piece i : b1.getBoard() )
                            {
                                if ( i.getColor() && i.isInCheck( b1, b1.getBoard().indexOf( i ) ) )
                                {
                                    valid = false;
                                    break;
                                }
                            }
                            break;

                        }

                    }
                    if ( valid )
                    {
                        return "Success";
                    }
                    else
                    {
                        board = new Board( save );
                        return "Invalid move! Please try again.";
                    }
                }
            }
            else if ( s[0].contains( "Bishop" ) )
            {
                if ( board.getPiece(
                    new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) ) == null )
                {
                    return "There is no Bishop there! Please try again.";
                }
                else if ( !board
                    .getPiece( new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                    .getClass()
                    .getCanonicalName()
                    .contentEquals( "Bishop" ) )
                {
                    return "There is no Bishop there! Please try again.";

                }
                else if ( board
                    .getPiece( new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                    .getColor() )
                {
                    return "That's not your Bishop! Please try again.";
                }
                else
                {
                    Bishop n = (Bishop)board.getPiece(
                        new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) );
                    boolean valid = false;
                    boolean inCheck = false;
                    for ( Board b1 : n.findMoves( board, board.getBoard().indexOf( n ) ) )
                    {

                        Board b2 = board;
                        b2 = b2.updateBoard( n,
                            new Bishop( Integer.parseInt( s[5] ),
                                Integer.parseInt( s[6] ),
                                false ) );

                        if ( b1.equals( b2 ) )
                        {
                            valid = true;
                            for ( Piece i : b1.getBoard() )
                            {
                                if ( i.getColor() && i.isInCheck( b1, b1.getBoard().indexOf( i ) ) )
                                {
                                    valid = false;
                                    break;
                                }
                            }
                            break;

                        }

                    }
                    if ( valid )
                    {
                        return "Success";
                    }
                    else
                    {
                        board = new Board( save );
                        return "Invalid move! Please try again.";
                    }
                }
            }
            else if ( s[0].contains( "King" ) )
            {
                if ( board.getPiece(
                    new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) ) == null )
                {
                    return "There is no King there! Please try again.";
                }
                else if ( !board
                    .getPiece( new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                    .getClass()
                    .getCanonicalName()
                    .contentEquals( "King" ) )
                {
                    return "There is no King there! Please try again.";

                }
                else if ( board
                    .getPiece( new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                    .getColor() )
                {
                    return "That's not your King! Please try again.";
                }
                else
                {
                    King n = (King)board.getPiece(
                        new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) );
                    boolean valid = false;
                    boolean inCheck = false;
                    for ( Board b1 : n.findMoves( board, board.getBoard().indexOf( n ) ) )
                    {

                        Board b2 = board;
                        b2 = b2.updateBoard( n,
                            new King( Integer.parseInt( s[5] ), Integer.parseInt( s[6] ), false ) );

                        if ( b1.equals( b2 ) )
                        {
                            valid = true;
                            for ( Piece i : b1.getBoard() )
                            {
                                if ( i.getColor() && i.isInCheck( b1, b1.getBoard().indexOf( i ) ) )
                                {
                                    valid = false;
                                    break;
                                }
                            }
                            break;

                        }

                    }
                    if ( valid )
                    {
                        return "Success";
                    }
                    else
                    {
                        board = new Board( save );
                        return "Invalid move! Please try again.";
                    }
                }
            }
        }
        catch ( NumberFormatException e )
        {
            System.out.println( "Wrong format! Try Again!" );
        }
        return "Success";
    }


    @Override
    public void mouseEntered( MouseEvent arg0 )
    {

    }


    @Override
    public void mouseExited( MouseEvent arg0 )
    {

    }


    @Override
    public void mousePressed( MouseEvent arg0 )
    {

    }


    @Override
    public void mouseReleased( MouseEvent arg0 )
    {

    }
}