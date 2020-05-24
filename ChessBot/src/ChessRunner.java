import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ChessRunner extends JPanel{

//    public void run() {
//    	final JFrame startWindow = new JFrame("Chess");
//        // Set window properties
//        startWindow.setLocation(300,100);
//        startWindow.setResizable(false);
//        startWindow.setSize(260, 240);
//        
//        Box components = Box.createVerticalBox();
//        startWindow.add(components);
//    }
    
	public void paintComponent( Graphics g)
	{
		  g.setColor( Color.WHITE );
	        super.paintComponent( g ); // Call JPanel's paintComponent method
	                                   // to paint the background
	//
//	        int width = getWidth();
//	        int height = getHeight();   
//	        BoardDraw( g, width, height);
	        
	        
	        g.setColor( Color.GRAY );
	        int sq = 100;
	        int shift = 100;
	        int h = 0;
	        for ( int row = 0; row < 8; row++ )
	        {
	            int shiftBy;
	            int xPos = 0;
	            if ( row % 2 == 0 )
	            {
	                shiftBy = 0;
	            }
	            else
	            {
	                shiftBy = shift;
	            }
	            for ( int col = 0; col < 4; col++ )
	            {

	                g.fillRect( xPos + shiftBy + 50, h + 50, sq, sq );
	                xPos += 2 * sq;
	            }

	            g.drawLine( 50 , sq * ( row + 1 ) + 50 , 850, sq * ( row + 1 ) + 50 );
	            g.drawLine( sq * ( row + 1 ) + 50, 50, sq * ( row + 1 ) + 50 , 850 );
	            h += sq;
	        }
	        g.drawLine( 50, 50, 50 , 850 );
	        g.drawLine( 50, 50, 850 , 50 );
	        
				try {
					Board board = new Board();
					board.paintAll(g);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        
	}
	
    public static void main(String[] args) throws IOException {
        JFrame w = new JFrame( "ChessBoard" );
        w.setBounds( 100, 100, 900, 925 );
        w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        ChessRunner panel = new ChessRunner();
        panel.setBackground( Color.WHITE );
        Container c = w.getContentPane();
        c.add( panel );
        w.setResizable( true );
        w.setVisible( true );
    }
}