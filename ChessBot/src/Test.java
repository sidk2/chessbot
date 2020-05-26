import java.awt.Color;
import java.awt.Container;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;

//This is the test program
public class Test {
	static LinkedList<Board> takebacks = new LinkedList<Board>();
	
	public static void main(String[] args) {
		System.out.print("              __________   _____       _____   ____________       ___________         ___________  " + '\n'
		         + "             /          \\ |     |     |     | |            |     /            \\     /             \\" + '\n'
		         + "            |    _______/ |     |     |     | |    ________/    |    _________/    |    __________/" + '\n'
		         + "            |   |         |     |     |     | |   |              \\   \\              \\   \\         " + '\n'
		         + "            |   |         |     |_____|     | |   |_______        \\   \\              \\   \\        " + '\n'
		         + "            |   |         |                 | |           |        \\   \\              \\   \\      " + '\n'
		         + "            |   |         |      _____      | |    _______|         \\   \\              \\   \\      " + '\n'
		         + "            |   |         |     |     |     | |   |                  \\   \\              \\   \\     " + '\n'
		         + "            |   |_______  |     |     |     | |   |_______     _______\\   \\       _______\\   \\    " + '\n'
		         + "            |           \\ |     |     |     | |           \\   /            |     /            | " + '\n'
		         + "             \\__________/ |_____|     |_____| |____________|  \\____________/     \\____________/    " + '\n');
					
		ArrayList<Piece> board = new ArrayList<>();
		Pawn p = new Pawn(4, 1, true);
		Pawn p1 = new Pawn(5, 6, false);
	    board.add(new King(4, 0, true));
		board.add(p);// index is 0
		board.add(p1);
		board.add(new Rook(0, 0, true));
		board.add(new Knight(1, 0, true));
		board.add(new Bishop(2, 0, true));
		board.add(new Queen(3, 0, true));

		board.add(new Bishop(5, 0, true));
		board.add(new Knight(6, 0, true));
		board.add(new Rook(7, 0, true));

		board.add(new Rook(0, 7, false));
		Knight n1 = new Knight(1, 7, false);
		board.add(n1);
		board.add(new Bishop(2, 7, false));
		board.add(new Queen(3, 7, false));
		board.add(new King(4, 7, false));
		board.add(new Bishop(5, 7, false));
		Knight n2 = new Knight(6, 7, false);
		board.add(n2);
		board.add(new Rook(7, 7, false));

		for (int i = 0; i < 8; i++) {
			if (i == 4) {
				board.add(new Pawn(i, 6, false));
				continue;
			}
			if (i == 5) {
				board.add(new Pawn(i, 1, true));
				continue;
			}
			board.add(new Pawn(i, 1, true));
			board.add(new Pawn(i, 6, false));
		}
		Board b = new Board(board);
		
		JFrame w = new JFrame( "ChessBoard - Itr2" );
        w.setBounds( 100, 100, 816, 836 );
        w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        Display panel = new Display();
        panel.setBackground( Color.WHITE );
        Container c = w.getContentPane();
        c.add( panel );
        w.setResizable( true );
        w.setVisible( true );
		int level = 4;
		while(true) {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		    try {
		        System.out.println("Pick a level from 1 - 4");
		        str = r.readLine();
		        level = Integer.parseInt(str);
		        break;
		    } 
		    catch (IOException e) {
		        continue;
		    }
		}
		takebacks.add(b);
		AI ai = new AI(b, level);
		while (true) {
			b = ai.minimax(b, level, true);
			panel.update(b);
			panel.setReet(true);
			takebacks.add(b);
			if(ai.isCheckMate()) {
			    //b.printBoard();
			    if(ai.isWhite()) {
			        System.out.println("Checkmate, white won!");
			    }
			    return;
			}
			ArrayList<Piece> save = new ArrayList<Piece>();
			
			if(b == null) {
			    System.out.println("Checkmate, black won!");
			    return;
			}
			

			while (panel.getReet()) {
				
			}
			System.out.println("White's move!");

		}
	}
}

