import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

//This is the test program
public class Test {
	public static void main(String[] args) {
		ArrayList<Piece> board = new ArrayList<>();
		Pawn p = new Pawn(4, 1, true);
		Pawn p1 = new Pawn(5, 6, false);
		board.add(p);// index is 0
		board.add(p1);
		board.add(new Rook(0, 0, true));
		board.add(new Knight(1, 0, true));
		board.add(new Bishop(2, 0, true));
		board.add(new Queen(3, 0, true));
		board.add(new King(4, 0, true));
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
		AI ai = new AI(b);
		for (int i = 0; i < 10; i++) {

			b = ai.minimax(b, 4, true);
			b.printBoard();

			while (true) {
				try {
					System.out.print(
							"Please type in your next move in the format:'Piece' at 'current row' 'current col' to 'new row' 'new column'"
									+ "\n");
					BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					String[] s = null;
					try {
						s = reader.readLine().split("\\s+");
					} catch (IOException e) {
						continue;
					}
					if (s[0].contains("Knight")) {
						b = b.updateBoard(new Knight(Integer.parseInt(s[2]), Integer.parseInt(s[3]), false),
								new Knight(Integer.parseInt(s[5]), Integer.parseInt(s[6]), false));
						b.printBoard();
						break;
					} else if (s[0].contains("Pawn")) {
						b = b.updateBoard(new Pawn(Integer.parseInt(s[2]), Integer.parseInt(s[3]), false),
								new Pawn(Integer.parseInt(s[5]), Integer.parseInt(s[6]), false));
						b.printBoard();
						break;
					} else if (s[0].contains("Rook")) {
						b = b.updateBoard(new Rook(Integer.parseInt(s[2]), Integer.parseInt(s[3]), false),
								new Rook(Integer.parseInt(s[5]), Integer.parseInt(s[6]), false));
						b.printBoard();
						break;
					} else if (s[0].contains("Queen")) {
						b = b.updateBoard(new Queen(Integer.parseInt(s[2]), Integer.parseInt(s[3]), false),
								new Queen(Integer.parseInt(s[5]), Integer.parseInt(s[6]), false));
						b.printBoard();
						break;
					} else if (s[0].contains("Bishop")) {
						b = b.updateBoard(new Bishop(Integer.parseInt(s[2]), Integer.parseInt(s[3]), false),
								new Bishop(Integer.parseInt(s[5]), Integer.parseInt(s[6]), false));
						b.printBoard();
						break;
					} else if (s[0].contains("King")) {
						b = b.updateBoard(new King(Integer.parseInt(s[2]), Integer.parseInt(s[3]), false),
								new King(Integer.parseInt(s[5]), Integer.parseInt(s[6]), false));
						b.printBoard();
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println("Wrong format! Try Again!");
				}
			}
			System.out.println("White's move!");

		}
	}
}
