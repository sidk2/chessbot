import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
 
@SuppressWarnings("serial")
public class Tile extends JComponent {
 
    private int color;
    public char currPiece;
 
    public int currX;
    public int currY;
 
    public Tile(char c, int xPos, int yPos) {
        currPiece = c;
        currX = xPos;
        currY = yPos;
        int xMod = xPos % 2;
        int yMod = yPos % 2;
 
        this.setBounds(currX*100, currY*100, 100, 100);
 
        if ((xMod == 0 && yMod == 0) || (xMod == 1 && yMod == 1)) {
            color = 1;
        } else {
            color = 2;
        }
        this.setBorder(BorderFactory.createEmptyBorder());
    }
    
    public char getCurrPiece() {
        return currPiece;
    }
    
    public void update(char c) {
        currPiece = c;
    }
 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
 
        if (this.color == 1) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.GRAY);
        }
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
 
        if(currPiece == 'p') {
            BufferedImage pieceImg;
            try {
                pieceImg = ImageIO.read(getClass().getResource("bpawn.png"));
                 g.drawImage(pieceImg, this.getX(), this.getY(), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (currPiece == 'r') {
            BufferedImage pieceImg;
            try {
                pieceImg = ImageIO.read(getClass().getResource("brook.png"));
                 g.drawImage(pieceImg, this.getX(), this.getY(), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (currPiece == 'n') {
            BufferedImage pieceImg;
            try {
                pieceImg = ImageIO.read(getClass().getResource("bknight.png"));
                 g.drawImage(pieceImg, this.getX(), this.getY(), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (currPiece == 'b') {
            BufferedImage pieceImg;
            try {
                pieceImg = ImageIO.read(getClass().getResource("bbishop.png"));
                 g.drawImage(pieceImg, this.getX(), this.getY(), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (currPiece == 'q') {
            BufferedImage pieceImg;
            try {
                pieceImg = ImageIO.read(getClass().getResource("bqueen.png"));
                 g.drawImage(pieceImg, this.getX(), this.getY(), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (currPiece == 'k') {
            BufferedImage pieceImg;
            try {
                pieceImg = ImageIO.read(getClass().getResource("bking.png"));
                 g.drawImage(pieceImg, this.getX(), this.getY(), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(currPiece == 'P') {
            BufferedImage pieceImg;
            try {
                pieceImg = ImageIO.read(getClass().getResource("wpawn.png"));
                 g.drawImage(pieceImg, this.getX(), this.getY(), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (currPiece == 'R') {
            BufferedImage pieceImg;
            try {
                pieceImg = ImageIO.read(getClass().getResource("wrook.png"));
                 g.drawImage(pieceImg, this.getX(), this.getY(), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (currPiece == 'N') {
            BufferedImage pieceImg;
            try {
                pieceImg = ImageIO.read(getClass().getResource("wknight.png"));
                 g.drawImage(pieceImg,this.getX(), this.getY(), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (currPiece == 'B') {
            BufferedImage pieceImg;
            try {
                pieceImg = ImageIO.read(getClass().getResource("wbishop.png"));
                 g.drawImage(pieceImg, this.getX(), this.getY(), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (currPiece == 'Q') {
            BufferedImage pieceImg;
            try {
                pieceImg = ImageIO.read(getClass().getResource("wqueen.png"));
                 g.drawImage(pieceImg, this.getX(), this.getY(), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (currPiece == 'K') {
            BufferedImage pieceImg;
            try {
                pieceImg = ImageIO.read(getClass().getResource("wking.png"));
                 g.drawImage(pieceImg, this.getX(), this.getY(), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}