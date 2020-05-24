import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Display {
	
	private JFrame display;
	
	private Board board;
	
	
	public Display() throws IOException {
        

        display = new JFrame("Chessboard");
            Image whiteImg = ImageIO.read(getClass().getResource("wp.png"));
            display.setIconImage(whiteImg);

        display.setLocation(100, 100);
        
        
        display.setLayout(new BorderLayout(20,20));
    }
    
//    public void checkmateOccurred (int c) {
//        if (c == 0) {
//            if (timer != null) timer.stop();
//            int n = JOptionPane.showConfirmDialog(
//                    display,
//                    "White wins by checkmate! Set up a new game? \n" +
//                    "Choosing \"No\" lets you look at the final situation.",
//                    "White wins!",
//                    JOptionPane.YES_NO_OPTION);
//            
//            if (n == JOptionPane.YES_OPTION) {
//                SwingUtilities.invokeLater(new StartMenu());
//                display.dispose();
//            }
//        } else {
//            if (timer != null) timer.stop();
//            int n = JOptionPane.showConfirmDialog(
//                    display,
//                    "Black wins by checkmate! Set up a new game? \n" +
//                    "Choosing \"No\" lets you look at the final situation.",
//                    "Black wins!",
//                    JOptionPane.YES_NO_OPTION);
//            
//            if (n == JOptionPane.YES_OPTION) {
//                SwingUtilities.invokeLater(new StartMenu());
//                display.dispose();
//            }
//        }
//    }
}
