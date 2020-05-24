import java.io.IOException;
import java.util.ArrayList;

public class Queen extends Piece {

	public Queen(int color, Tile pos, String file_Img) throws IOException {
		super(color, pos, file_Img);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Tile> getPossibleMoves(Board b) {
		ArrayList<Tile> cols = super.getCols(b);
		ArrayList<Tile> diags = super.getDiags(b);
		ArrayList<Tile> possibles = new ArrayList<Tile>();
		for(Tile t : cols) {
			possibles.add(t);
		}
		for(Tile t : diags) {
			possibles.add(t);
		}
		return possibles;
	}

}
