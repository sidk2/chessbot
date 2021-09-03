import java.util.ArrayList;

/**
 * A class for the King piece, methods to find Moves, keeps the location, does
 * not include castling
 *
 * @author Shreyas Kaasyap
 * @version May 25, 2020
 * @author Period: 1
 * @author Assignment: ChessBot
 *
 * @author Sources: Shreyas Kaasyap, Sidharth Kannan, Leo Yang
 */
public class King implements Piece {
    /**
     * the location of the piece
     */
    private Location loc;

    /**
     * the color of the piece
     */
    private boolean color;

    /**
     * the material value of the piece
     */
    public final int VALUE = 900;

    public final int[][] activityTable = { { 3, 5, 5, 5, 5, 5, 5, 3 }, { 5, 8, 8, 8, 8, 8, 8, 5 },
            { 5, 8, 8, 8, 8, 8, 8, 5 }, { 5, 8, 8, 8, 8, 8, 8, 5 }, { 5, 8, 8, 8, 8, 8, 8, 5 },
            { 5, 8, 8, 8, 8, 8, 8, 5 }, { 5, 8, 8, 8, 8, 8, 8, 5 }, { 3, 5, 5, 5, 5, 5, 5, 3 } };

    public final char code = color ? 'K' : 'k';

    public boolean hasMoved = false;

    /**
     * @param xPos  the x coordinate of the location
     * @param yPos  the y coordinate of the location
     * @param color the color of the piece A constructor for a king piece,
     *              instantiates fields
     */
    public King(int xPos, int yPos, boolean color, boolean hasMoved) {
        loc = new Location(xPos, yPos);
        this.color = color;
        this.hasMoved = hasMoved;
    }

    public char getCode() {
        return code;
    }

    public boolean equals(Piece other) {
        return other.getLoc().equals(getLoc())
                && other.getClass().getCanonicalName().equals(this.getClass().getCanonicalName());
    }

    public int getValue() {
        if (color)
            return VALUE;
        else
            return -1 * VALUE;
    }

    @Override
    public ArrayList<Board> findMoves(Board b, int index) {
        ArrayList<Piece> pieces = b.getBoard();
        ArrayList<Piece> temp = new ArrayList<>();
        for (Piece i : pieces) {
            temp.add(i);
        }
        ArrayList<Board> ans = new ArrayList<>();
        temp.remove(index);

        int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 };
        int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1 };

        int curX = pieces.get(index).getLoc().getXPos();
        int curY = pieces.get(index).getLoc().getYPos();

        for (int i = 0; i < 8; i++) {
            if (curX + dx[i] >= 0 && curX + dx[i] < 8 && curY + dy[i] >= 0 && curY + dy[i] < 8
                    && (b.check(new Location(curX + dx[i], curY + dy[i])) == true)) {
                ArrayList<Piece> nextPos = new ArrayList<>();
                for (Piece r : temp) {
                    nextPos.add(r);
                }
                nextPos.add(new King(curX + dx[i], curY + dy[i], pieces.get(index).getColor(), true));
                ans.add(new Board(nextPos));
            }
            if (curX + dx[i] >= 0 && curX + dx[i] < 8 && curY + dy[i] >= 0 && curY + dy[i] < 8
                    && (b.check(new Location(curX + dx[i], curY + dy[i])) == false)) {
                for (int r = 0; r < pieces.size(); r++) {
                    if (pieces.get(r).getLoc().getXPos() == curX + dx[i]
                            && pieces.get(r).getLoc().getYPos() == curY + dy[i]
                            && pieces.get(r).getColor() != pieces.get(index).getColor()) {
                        ArrayList<Piece> nextPos = new ArrayList<>();
                        for (Piece q : temp) {
                            if (q.equals(pieces.get(r))) {
                                continue;
                            }
                            nextPos.add(q);
                        }
                        nextPos.add(new King(curX + dx[i], curY + dy[i], pieces.get(index).getColor(), true));
                        ans.add(new Board(nextPos));
                        break;
                    }
                }
            }
        }
        if (!hasMoved) {
            if (color && b.check(new Location(2, 0)) && b.check(new Location(1, 0))
                    && b.getPiece(new Location(0, 0)) instanceof Rook
                    && ((Rook) (b.getPiece(new Location(0, 0)))).canCastle()) {
                boolean passed1 = true;
                boolean passed2 = true;
                Board copy = new Board(b.copyBoard());
                copy.updateBoard(this, new King(this.getLoc().getXPos() - 1, this.getLoc().getYPos(), color, true));
                for (Piece p : copy.getBoard()) {
                    if (p.isInCheck(copy, copy.getBoard().indexOf(p))) {
                        passed1 = false;
                        break;
                    }
                }
                copy = new Board(b.copyBoard());
                copy.updateBoard(this, new King(this.getLoc().getXPos() - 2, this.getLoc().getYPos(), color, true));
                copy.updateBoard(copy.getPiece(new Location(0, 0)), new Rook(2, 0, color, false));
                copy.updateBoard(copy.getPiece(new Location(3, 0)), null);
                for (Piece p : copy.getBoard()) {
                    if (p.isInCheck(copy, copy.getBoard().indexOf(p))) {
                        passed2 = false;
                        break;
                    }
                }
                if (passed1 && passed2) {
                    copy.updateBoard( copy.getPiece(new Location(0, 0)), new Rook(2, 0, color, false));
                    ans.add(copy);
                }
            }
            if (color && b.check(new Location(4, 0)) && b.check(new Location(5, 0)) && b.check(new Location(6, 0))
                    && b.getPiece(new Location(7, 0)) instanceof Rook
                    && ((Rook) (b.getPiece(new Location(7, 0)))).canCastle()) {
                boolean passed1 = true;
                boolean passed2 = true;
                // boolean passed3 = true;
                Board copy = new Board(b.copyBoard());
                copy.updateBoard(this, new King(this.getLoc().getXPos() + 1, this.getLoc().getYPos(), color, true));
                for (Piece p : copy.getBoard()) {
                    if (p.isInCheck(copy, copy.getBoard().indexOf(p))) {
                        passed1 = false;
                        break;
                    }
                }
                copy = new Board(b.copyBoard());
                copy.updateBoard(this, new King(this.getLoc().getXPos()  + 2, this.getLoc().getYPos(), color, true));
                copy.updateBoard(copy.getPiece(new Location(7, 0)), new Rook(4, 0, color, false));
                copy.updateBoard(copy.getPiece(new Location(3, 0)), null);
                for (Piece p : copy.getBoard()) {
                    if (p.isInCheck(copy, copy.getBoard().indexOf(p))) {
                        passed2 = false;
                        break;
                    }
                }
                if (passed1 && passed2) {
                    ans.add(copy);
                }
            }

            if (!color && b.check(new Location(2, 7)) && b.check(new Location(1, 7))
                    && b.getPiece(new Location(0, 7)) instanceof Rook
                    && ((Rook) (b.getPiece(new Location(0, 7)))).canCastle()) {
                boolean passed1 = true;
                boolean passed2 = true;
                Board copy = new Board(b.copyBoard());
                copy.updateBoard(this, new King(this.getLoc().getXPos() - 1, this.getLoc().getYPos(), color, true));
                for (Piece p : copy.getBoard()) {
                    if (p.isInCheck(copy, copy.getBoard().indexOf(p))) {
                        passed1 = false;
                        break;
                    }
                }
                copy = new Board(b.copyBoard());
                copy.updateBoard(this, new King(this.getLoc().getXPos() - 2, this.getLoc().getYPos(), color, true));
                copy.updateBoard(copy.getPiece(new Location(0, 7)), new Rook(2, 7, color, false));
                copy.updateBoard(this, null);
                copy.updateBoard(copy.getPiece(new Location(3, 7)), null);
                for (Piece p : copy.getBoard()) {
                    if (p.isInCheck(copy, copy.getBoard().indexOf(p))) {
                        passed2 = false;
                        break;
                    }
                }
                if (passed1 && passed2) {
                    ans.add(copy);
                    // System.out.println("short castle");
                    // copy.printBoard();
                }
            }
            if (!color && b.check(new Location(4, 7)) && b.check(new Location(5, 7)) && b.check(new Location(6, 7))
                    && b.getPiece(new Location(7, 7)) instanceof Rook
                    && ((Rook) (b.getPiece(new Location(7, 7)))).canCastle()) {
                // System.out.println("dhfksfdls");
                boolean passed1 = true;
                boolean passed2 = true;
                // boolean passed3 = true;
                Board copy = new Board(b.copyBoard());
                copy.updateBoard(this, new King(this.getLoc().getXPos() + 1, this.getLoc().getYPos(), color, true));
                for (Piece p : copy.getBoard()) {
                    if (p.isInCheck(copy, copy.getBoard().indexOf(p))) {
                        // System.out.println("didn't pass 1");
                        passed1 = false;
                        break;
                    }
                }
                copy = new Board(b.copyBoard());
                // copy.updateBoard(copy.getPiece(new Location(this.getLoc().getXPos() + 1, this.getLoc().getYPos())), null);
                copy.updateBoard(this, new King(this.getLoc().getXPos() + 2, this.getLoc().getYPos(), color, true));
                copy.updateBoard(copy.getPiece(new Location(7, 7)), new Rook(4, 7, color, false));
                copy.updateBoard(copy.getPiece(new Location(3, 7)), null);
                for (Piece p : copy.getBoard()) {
                    if (p.isInCheck(copy, copy.getBoard().indexOf(p))) {
                        passed2 = false;
                        break;
                    }
                }
                if (passed1 && passed2) {
                    ans.add(copy);
                    // System.out.println("Castled");
                    // copy.printBoard();
                    // System.out.println('\n');
                }
            }

        }

        return ans;
    }

    @Override
    public Location getLoc() {
        return loc;
    }

    @Override
    public boolean isInCheck(Board b, int index) {
        return false;
    }

    @Override
    public boolean getColor() {
        return color;
    }

    public int getActivity(Location loc) {
        return -activityTable[loc.getXPos()][loc.getYPos()];
    }

    public void setCastled(boolean b) {
        hasMoved = b;
    }
}
