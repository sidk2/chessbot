import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComponent;


/**
 * A class for a tile, a tile has a position and can hold a piece
 *
 * @author Shreyas Kaasyap
 * @version May 26, 2020
 * @author Period: 1
 * @author Assignment: ChessBot
 *
 * @author Sources: Shreyas Kaasyap, Sidharth Kannan, Leo Yang
 */
@SuppressWarnings("serial")
public class Tile extends JComponent
{

    /**
     * the color of the tile
     */
    private int color;

    /**
     * the piece that is on that tile
     */
    public char currPiece;

    /**
     * the xCoord of the tile
     */
    public int currX;

    /**
     * the yCoord of the tile
     */
    public int currY;


    /**
     * @param c
     *            the letter of the piece, upper case for White, lower case for
     *            Black
     * @param xPos
     *            the xPosition of the tile
     * @param yPos
     *            the yPosition of the tile
     */
    public Tile( char c, int xPos, int yPos )
    {
        currPiece = c;
        currX = xPos;
        currY = yPos;
        int xMod = xPos % 2;
        int yMod = yPos % 2;

        this.setBounds( currX * 100, currY * 100, 100, 100 );

        if ( ( xMod == 0 && yMod == 0 ) || ( xMod == 1 && yMod == 1 ) )
        {
            color = 1;
        }
        else
        {
            color = 2;
        }
        this.setBorder( BorderFactory.createEmptyBorder() );
    }


    /**
     * returns the letter of the piece that is on that tile
     * 
     * @return Char
     */
    public char getCurrPiece()
    {
        return currPiece;
    }


    /**
     * Updates the current piece at this tile
     * 
     * @param c
     *            the letter of the piece to update to
     */
    public void update( char c )
    {
        currPiece = c;
    }


    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );

        if ( this.color == 1 )
        {
            g.setColor( Color.WHITE );
        }
        else
        {
            g.setColor( Color.GRAY );
        }
        g.fillRect( this.getX(), this.getY(), this.getWidth(), this.getHeight() );

        if ( currPiece == 'p' )
        {
            BufferedImage pieceImg;
            try
            {
                pieceImg = ImageIO.read( getClass().getResource( "bpawn.png" ) );
                g.drawImage( pieceImg, this.getX(), this.getY(), null );
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
        else if ( currPiece == 'r' )
        {
            BufferedImage pieceImg;
            try
            {
                pieceImg = ImageIO.read( getClass().getResource( "brook.png" ) );
                g.drawImage( pieceImg, this.getX(), this.getY(), null );
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
        else if ( currPiece == 'n' )
        {
            BufferedImage pieceImg;
            try
            {
                pieceImg = ImageIO.read( getClass().getResource( "bknight.png" ) );
                g.drawImage( pieceImg, this.getX(), this.getY(), null );
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
        else if ( currPiece == 'b' )
        {
            BufferedImage pieceImg;
            try
            {
                pieceImg = ImageIO.read( getClass().getResource( "bbishop.png" ) );
                g.drawImage( pieceImg, this.getX(), this.getY(), null );
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
        else if ( currPiece == 'q' )
        {
            BufferedImage pieceImg;
            try
            {
                pieceImg = ImageIO.read( getClass().getResource( "bqueen.png" ) );
                g.drawImage( pieceImg, this.getX(), this.getY(), null );
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
        else if ( currPiece == 'k' )
        {
            BufferedImage pieceImg;
            try
            {
                pieceImg = ImageIO.read( getClass().getResource( "bking.png" ) );
                g.drawImage( pieceImg, this.getX(), this.getY(), null );
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
        else if ( currPiece == 'P' )
        {
            BufferedImage pieceImg;
            try
            {
                pieceImg = ImageIO.read( getClass().getResource( "wpawn.png" ) );
                g.drawImage( pieceImg, this.getX(), this.getY(), null );
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
        else if ( currPiece == 'R' )
        {
            BufferedImage pieceImg;
            try
            {
                pieceImg = ImageIO.read( getClass().getResource( "wrook.png" ) );
                g.drawImage( pieceImg, this.getX(), this.getY(), null );
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
        else if ( currPiece == 'N' )
        {
            BufferedImage pieceImg;
            try
            {
                pieceImg = ImageIO.read( getClass().getResource( "wknight.png" ) );
                g.drawImage( pieceImg, this.getX(), this.getY(), null );
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
        else if ( currPiece == 'B' )
        {
            BufferedImage pieceImg;
            try
            {
                pieceImg = ImageIO.read( getClass().getResource( "wbishop.png" ) );
                g.drawImage( pieceImg, this.getX(), this.getY(), null );
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
        else if ( currPiece == 'Q' )
        {
            BufferedImage pieceImg;
            try
            {
                pieceImg = ImageIO.read( getClass().getResource( "wqueen.png" ) );
                g.drawImage( pieceImg, this.getX(), this.getY(), null );
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
        else if ( currPiece == 'K' )
        {
            BufferedImage pieceImg;
            try
            {
                pieceImg = ImageIO.read( getClass().getResource( "wking.png" ) );
                g.drawImage( pieceImg, this.getX(), this.getY(), null );
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
    }
}