import java.util.ArrayList;

/**
 * A class for a Queen piece, has methods to find moves, includes location and
 * color
 *
 */
public class Queen implements Piece {

    /**
     * the location of the piece
     */
    private Location loc;

    /**
     * the color of the piece
     */
    private boolean color;

    /**
     * the value of the piece
     */
    public final int VALUE = 90;

    public char code;

    private final int[][] activityTable = { 
        { 21, 21, 21, 21, 21, 21, 21, 21 }, 
        { 21, 23, 23, 23, 23, 23, 23, 21 },
        { 21, 23, 25, 25, 25, 25, 23, 21 },
        { 21, 23, 25, 27, 27, 25, 23, 21 }, 
        { 21, 23, 25, 27, 27, 25, 23, 21 },
        { 21, 23, 25, 25, 25, 25, 23, 21 },
        { 21, 23, 23, 23, 23, 23, 23, 21 },
        { 21, 21, 21, 21, 21, 21, 21, 21 } };

    /**
     * @param xPos  the xPosition of the location
     * @param yPos  the yPosition of the location
     * @param color the color of the piece A constructor for a Queen piece,
     *              instantiates fields
     */
    public Queen(int xPos, int yPos, boolean color) {
        loc = new Location(xPos, yPos);
        this.color = color;
        code = color ? 'Q' : 'q';
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

        int curX = pieces.get(index).getLoc().getXPos();
        int curY = pieces.get(index).getLoc().getYPos();

        int tempX = curX;
        int tempY = curY;

        while (tempX - 1 >= 0) {
            tempX--;
            if (b.check(new Location(tempX, tempY)) == true) {
                ArrayList<Piece> nextPos = new ArrayList<>();
                for (Piece i : temp) {
                    nextPos.add(i);
                }
                nextPos.add(new Queen(tempX, tempY, pieces.get(index).getColor()));
                ans.add(new Board(nextPos));
            }
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
                        nextPos.add(new Queen(tempX, tempY, pieces.get(index).getColor()));
                        ans.add(new Board(nextPos));
                    }
                }
                break;
            }
        }

        tempX = curX;
        tempY = curY;

        while (tempX + 1 < 8) {
            tempX++;
            if (b.check(new Location(tempX, tempY)) == true) {
                ArrayList<Piece> nextPos = new ArrayList<>();
                for (Piece i : temp) {
                    nextPos.add(i);
                }
                nextPos.add(new Queen(tempX, tempY, pieces.get(index).getColor()));
                ans.add(new Board(nextPos));
            }
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
                        nextPos.add(new Queen(tempX, tempY, pieces.get(index).getColor()));
                        ans.add(new Board(nextPos));
                    }
                }
                break;
            }
        }

        tempX = curX;
        tempY = curY;

        while (tempY - 1 >= 0) {
            tempY--;
            if (b.check(new Location(tempX, tempY)) == true) {
                ArrayList<Piece> nextPos = new ArrayList<>();
                for (Piece i : temp) {
                    nextPos.add(i);
                }
                nextPos.add(new Queen(tempX, tempY, pieces.get(index).getColor()));
                ans.add(new Board(nextPos));
            }
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
                        nextPos.add(new Queen(tempX, tempY, pieces.get(index).getColor()));
                        ans.add(new Board(nextPos));
                    }
                }
                break;
            }
        }
        tempX = curX;
        tempY = curY;

        while (tempY + 1 < 8) {
            tempY++;
            if (b.check(new Location(tempX, tempY)) == true) {
                ArrayList<Piece> nextPos = new ArrayList<>();
                for (Piece i : temp) {
                    nextPos.add(i);
                }
                nextPos.add(new Queen(tempX, tempY, pieces.get(index).getColor()));
                ans.add(new Board(nextPos));
            }
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
                        nextPos.add(new Queen(tempX, tempY, pieces.get(index).getColor()));
                        ans.add(new Board(nextPos));
                    }
                }
                break;
            }
        }

        tempX = curX;
        tempY = curY;
        while (tempX - 1 >= 0 && tempY - 1 >= 0) {
            tempX--;
            tempY--;
            if (b.check(new Location(tempX, tempY)) == true) {
                ArrayList<Piece> nextPos = new ArrayList<>();
                for (Piece i : temp) {
                    nextPos.add(i);
                }
                nextPos.add(new Queen(tempX, tempY, pieces.get(index).getColor()));
                ans.add(new Board(nextPos));
            }
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
                        nextPos.add(new Queen(tempX, tempY, pieces.get(index).getColor()));
                        ans.add(new Board(nextPos));
                    }
                }
                break;
            }
        }
        tempX = curX;
        tempY = curY;

        while (tempX - 1 >= 0 && tempY + 1 < 8) {
            tempX--;
            tempY++;
            if (b.check(new Location(tempX, tempY)) == true) {
                ArrayList<Piece> nextPos = new ArrayList<>();
                for (Piece i : temp) {
                    nextPos.add(i);
                }
                nextPos.add(new Queen(tempX, tempY, pieces.get(index).getColor()));
                ans.add(new Board(nextPos));
            }
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
                        nextPos.add(new Queen(tempX, tempY, pieces.get(index).getColor()));
                        ans.add(new Board(nextPos));
                    }
                }
                break;
            }
        }

        tempX = curX;
        tempY = curY;

        while (tempX + 1 < 8 && tempY + 1 < 8) {
            tempX++;
            tempY++;
            if (b.check(new Location(tempX, tempY)) == true) {
                ArrayList<Piece> nextPos = new ArrayList<>();
                for (Piece i : temp) {
                    nextPos.add(i);
                }
                nextPos.add(new Queen(tempX, tempY, pieces.get(index).getColor()));
                ans.add(new Board(nextPos));
            }
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
                        nextPos.add(new Queen(tempX, tempY, pieces.get(index).getColor()));
                        ans.add(new Board(nextPos));
                    }
                }
                break;
            }
        }

        tempX = curX;
        tempY = curY;

        while (tempX + 1 < 8 && tempY - 1 >= 0) {
            tempX++;
            tempY--;
            if (b.check(new Location(tempX, tempY)) == true) {
                ArrayList<Piece> nextPos = new ArrayList<>();
                for (Piece i : temp) {
                    nextPos.add(i);
                }
                nextPos.add(new Queen(tempX, tempY, pieces.get(index).getColor()));
                ans.add(new Board(nextPos));
            }
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
                        nextPos.add(new Queen(tempX, tempY, pieces.get(index).getColor()));
                        ans.add(new Board(nextPos));
                    }
                }
                break;
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

        for (Piece i : pieces) {
            if (i instanceof King) {
                if (i.getColor() == true) {
                    whiteKX = i.getLoc().getXPos();
                    whiteKY = i.getLoc().getYPos();
                } else {
                    blackKX = i.getLoc().getXPos();
                    blackKY = i.getLoc().getYPos();
                }
            }
        }

        int tempX = curX;
        int tempY = curY;

        while (tempX - 1 >= 0) {
            tempX = tempX - 1;
            if ((tempX == blackKX && tempY == blackKY && pieces.get(index).getColor() == true)
                    || (tempX == whiteKX && tempY == whiteKY && pieces.get(index).getColor() == false)) {
                return true;
            } else if (b.check(new Location(tempX, tempY)) == false) {
                break;
            }
        }

        tempX = curX;
        tempY = curY;

        while (tempX + 1 < 8) {
            tempX = tempX + 1;
            if ((tempX == blackKX && tempY == blackKY && pieces.get(index).getColor() == true)
                    || (tempX == whiteKX && tempY == whiteKY && pieces.get(index).getColor() == false)) {
                return true;
            } else if (b.check(new Location(tempX, tempY)) == false) {
                break;
            }
        }

        tempX = curX;
        tempY = curY;

        while (tempY + 1 < 8) {
            tempY = tempY + 1;
            if ((tempX == blackKX && tempY == blackKY && pieces.get(index).getColor() == true)
                    || (tempX == whiteKX && tempY == whiteKY && pieces.get(index).getColor() == false)) {
                return true;
            } else if (b.check(new Location(tempX, tempY)) == false) {
                break;
            }
        }

        tempX = curX;
        tempY = curY;

        while (tempY - 1 >= 0) {
            tempY = tempY - 1;
            if ((tempX == blackKX && tempY == blackKY && pieces.get(index).getColor() == true)
                    || (tempX == whiteKX && tempY == whiteKY && pieces.get(index).getColor() == false)) {
                return true;
            } else if (b.check(new Location(tempX, tempY)) == false) {
                break;
            }
        }

        tempX = curX;
        tempY = curY;

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
        return activityTable[loc.getXPos()][loc.getYPos()] - 20;
    }
}
