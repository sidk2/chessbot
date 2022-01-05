import java.util.ArrayList;

/**
 * Creates a bishop piece, has methods to find the moves for the piece, keeps
 * trakc of the location of the piece
 *
 */
public class Bishop implements Piece {
    /**
     * the location of the piece
     */
    private Location loc;

    /**
     * the color of the piece, true = white, false = black
     */
    private boolean color;
    public final char code = color ? 'B' : 'b';
    /**
     * the material value of the piece
     */
    public final int VALUE = 30;

    private final int[][] activityTable = { { 7, 7, 7, 7, 7, 7, 7, 7 }, { 7, 9, 9, 9, 9, 9, 9, 7 },
            { 7, 9, 11, 11, 11, 11, 9, 7 }, { 7, 9, 13, 13, 13, 13, 9, 7 }, { 7, 9, 11, 13, 13, 11, 9, 7 },
            { 7, 9, 11, 11, 11, 11, 9, 7 }, { 7, 9, 9, 9, 9, 9, 9, 7 }, { 7, 7, 7, 7, 7, 7, 7, 7 } };

    /**
     * @param xPos  the xPosition of the piece
     * @param yPos  the yPosition of the piece
     * @param color the color of the piece A constructor for a bishop, instantiates
     *              fields
     */
    public Bishop(int xPos, int yPos, boolean color) {
        loc = new Location(xPos, yPos);
        this.color = color;
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
        temp.remove(index);
        ArrayList<Board> ans = new ArrayList<>();

        int curX = pieces.get(index).getLoc().getXPos();
        int curY = pieces.get(index).getLoc().getYPos();

        int tempX = curX;
        int tempY = curY;
        while (tempX - 1 >= 0 && tempY - 1 >= 0) {
            tempX--;
            tempY--;
            if (b.check(tempX, tempY) == true) {
                ArrayList<Piece> nextPos = new ArrayList<>();
                for (Piece i : temp) {
                    nextPos.add(i);
                }
                nextPos.add(new Bishop(tempX, tempY, pieces.get(index).getColor()));
                ans.add(new Board(nextPos));
            } else {
                    for (int i = 0; i < pieces.size(); i++) {
                        if (pieces.get(i).getLoc().getXPos() == tempX && pieces.get(i).getLoc().getYPos() == tempY
                                && pieces.get(i).getColor() != pieces.get(index).getColor()) {
                            ArrayList<Piece> nextPos = new ArrayList<>();
                            for (Piece r : temp) {
                                if (r.equals(pieces.get(i))) {
                                    continue;
                                }
                                nextPos.add(r);
                            }
                            nextPos.add(new Bishop(tempX, tempY, pieces.get(index).getColor()));
                            ans.add(new Board(nextPos));
                        }
                    
                    break;
                }

            }
        }
        tempX = curX;
        tempY = curY;

        while (tempX - 1 >= 0 && tempY + 1 < 8) {
            tempX--;
            tempY++;
            if (b.check(tempX, tempY) == true) {
                ArrayList<Piece> nextPos = new ArrayList<>();
                for (Piece i : temp) {
                    nextPos.add(i);
                }
                nextPos.add(new Bishop(tempX, tempY, pieces.get(index).getColor()));
                ans.add(new Board(nextPos));
            } else {
                if (b.check(new Location(tempX, tempY)) == false) {
                    for (int i = 0; i < pieces.size(); i++) {
                        if (pieces.get(i).getLoc().getXPos() == tempX && pieces.get(i).getLoc().getYPos() == tempY
                                && pieces.get(i).getColor() != pieces.get(index).getColor()) {
                            ArrayList<Piece> nextPos = new ArrayList<>();
                            for (Piece r : temp) {
                                if (r.equals(pieces.get(i))) {
                                    continue;
                                }
                                nextPos.add(r);
                            }
                            nextPos.add(new Bishop(tempX, tempY, pieces.get(index).getColor()));
                            ans.add(new Board(nextPos));
                            break;
                        }
                    }
                    break;
                }
            }
        }

        tempX = curX;
        tempY = curY;

        while (tempX + 1 < 8 && tempY + 1 < 8) {
            tempX++;
            tempY++;
            if (b.check(tempX, tempY) == true) {
                ArrayList<Piece> nextPos = new ArrayList<>();
                for (Piece i : temp) {
                    nextPos.add(i);
                }
                nextPos.add(new Bishop(tempX, tempY, pieces.get(index).getColor()));
                ans.add(new Board(nextPos));
            } else {
                if (b.check(tempX, tempY) == false) {
                    for (int i = 0; i < pieces.size(); i++) {
                        if (pieces.get(i).getLoc().getXPos() == tempX && pieces.get(i).getLoc().getYPos() == tempY
                                && pieces.get(i).getColor() != pieces.get(index).getColor()) {
                            ArrayList<Piece> nextPos = new ArrayList<>();
                            for (Piece r : temp) {
                                if (r.equals(pieces.get(i))) {
                                    continue;
                                }
                                nextPos.add(r);
                            }
                            nextPos.add(new Bishop(tempX, tempY, pieces.get(index).getColor()));
                            ans.add(new Board(nextPos));
                        }
                    }
                    break;
                }
            }
        }

        tempX = curX;
        tempY = curY;

        while (tempX + 1 < 8 && tempY - 1 >= 0) {
            tempX++;
            tempY--;
            if (b.check(tempX, tempY) == true) {
                ArrayList<Piece> nextPos = new ArrayList<>();
                for (Piece i : temp) {
                    nextPos.add(i);
                }
                nextPos.add(new Bishop(tempX, tempY, pieces.get(index).getColor()));
                ans.add(new Board(nextPos));
            } else {
                if (b.check(tempX, tempY) == false) {
                    for (int i = 0; i < pieces.size(); i++) {
                        if (pieces.get(i).getLoc().getXPos() == tempX && pieces.get(i).getLoc().getYPos() == tempY
                                && pieces.get(i).getColor() != pieces.get(index).getColor()) {
                            ArrayList<Piece> nextPos = new ArrayList<>();
                            for (Piece r : temp) {
                                if (r.equals(pieces.get(i))) {
                                    continue;
                                }
                                nextPos.add(r);
                            }
                            nextPos.add(new Bishop(tempX, tempY, pieces.get(index).getColor()));
                            ans.add(new Board(nextPos));
                        }
                    }
                    break;
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
        ArrayList<Piece> pieces = b.getBoard();

        int curX = pieces.get(index).getLoc().getXPos();
        int curY = pieces.get(index).getLoc().getYPos();
        int blackKX = 0, blackKY = 0, whiteKX = 0, whiteKY = 0;

        int tempX = curX;
        int tempY = curY;

        for (Piece i : pieces) {
            if (i instanceof King && i.getColor() == false) {
                blackKX = i.getLoc().getXPos();
                blackKY = i.getLoc().getYPos();
            }
            if (i instanceof King && i.getColor() == true) {
                whiteKX = i.getLoc().getXPos();
                whiteKY = i.getLoc().getYPos();
            }
        }

        while (tempX - 1 >= 0 && tempY - 1 >= 0) {
            tempX--;
            tempY--;
            if ((tempX == blackKX && tempY == blackKY && pieces.get(index).getColor() == true)
                    || (tempX == whiteKX && tempY == whiteKY && pieces.get(index).getColor() == false)) {
                return true;
            } else if (b.check(new Location(tempX, tempY)) == false) {
                break;
            }
        }

        tempX = curX;
        tempY = curY;

        while (tempX + 1 < 8 && tempY - 1 >= 0) {
            tempX++;
            tempY--;
            if ((tempX == blackKX && tempY == blackKY && pieces.get(index).getColor() == true)
                    || (tempX == whiteKX && tempY == whiteKY && pieces.get(index).getColor() == false)) {
                return true;
            } else if (b.check(new Location(tempX, tempY)) == false) {
                break;
            }
        }

        tempX = curX;
        tempY = curY;

        while (tempX + 1 < 8 && tempY + 1 < 8) {
            tempX++;
            tempY++;
            if ((tempX == blackKX && tempY == blackKY && pieces.get(index).getColor() == true)
                    || (tempX == whiteKX && tempY == whiteKY && pieces.get(index).getColor() == false)) {
                return true;
            } else if (b.check(new Location(tempX, tempY)) == false) {
                break;
            }
        }

        tempX = curX;
        tempY = curY;

        while (tempX - 1 >= 0 && tempY + 1 < 8) {
            tempX--;
            tempY++;
            if ((tempX == blackKX && tempY == blackKY && pieces.get(index).getColor() == true)
                    || (tempX == whiteKX && tempY == whiteKY && pieces.get(index).getColor() == false)) {
                return true;
            } else if (b.check(new Location(tempX, tempY)) == false) {
                break;
            }
        }
        return false;
    }

    @Override
    public boolean getColor() {
        return color;
    }

    public int getActivity(Location loc) {
        return activityTable[loc.getXPos()][loc.getYPos()];
    }

}
