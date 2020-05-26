import java.util.ArrayList;

interface Piece {
    ArrayList<Board> findMoves(Board b, int index);
    boolean isInCheck(Board b, int index);
    Location getLoc();
    boolean getColor();
	int getValue();
	boolean equals(Piece other);
}
