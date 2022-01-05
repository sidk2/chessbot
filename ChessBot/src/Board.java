
import java.util.ArrayList;

/**
 * A class for a Board, which has an ArrayList of pieces that stores all the
 * pieces currently on the board. Has methods to manipulate and return parts of
 * the board.
 *
 */
public class Board {
    /**
     * the set of pieces currently on the board
     */
    private ArrayList<Piece> board;

    /**
     * a boolean matrix that keeps track of which tiles have any piece on it true if
     * there is a piece at the tile, false otherwise
     */
    private Boolean[][] isOccupied;

    public final double activityRelativeWeight = 0.1;

    public int[][] searchOrder = { { 4, 4 }, { 4, 3 }, { 3, 3 }, { 3, 4 }, { 3, 5 }, { 4, 5 }, { 5, 5 }, { 5, 4 },
            { 5, 3 }, { 5, 2 }, { 4, 2 }, { 3, 2 }, { 2, 2 }, { 2, 3 }, { 2, 4 }, { 2, 5 }, { 2, 6 }, { 3, 6 },
            { 4, 6 }, { 5, 6 }, { 6, 6 }, { 6, 5 }, { 6, 4 }, { 6, 3 }, { 6, 2 }, { 6, 1 }, { 5, 1 }, { 4, 1 },
            { 3, 1 }, { 2, 1 }, { 1, 1 }, { 1, 2 }, { 1, 3 }, { 1, 4 }, { 1, 5 }, { 1, 6 }, { 1, 7 }, { 2, 7 },
            { 3, 7 }, { 4, 7 }, { 5, 7 }, { 6, 7 }, { 7, 7 }, { 7, 6 }, { 7, 5 }, { 7, 4 }, { 7, 3 }, { 7, 2 },
            { 7, 1 }, { 7, 0 }, { 6, 0 }, { 5, 0 }, { 4, 0 }, { 3, 0 }, { 2, 0 }, { 1, 0 }, { 0, 0 }, { 0, 1 },
            { 0, 2 }, { 0, 3 }, { 0, 4 }, { 0, 5 }, { 0, 6 }, { 0, 7 } };

    /**
     * Makes a board with the position set as the starting position of a chess game
     * Instantiates fields
     */
    public Board() {
        board = new ArrayList<>();
        isOccupied = new Boolean[8][8];
        for (int i = 0; i < 8; i++) {
            isOccupied[i][0] = true;
            isOccupied[i][1] = true;
            isOccupied[i][6] = true;
            isOccupied[i][7] = true;
            isOccupied[i][2] = false;
            isOccupied[i][3] = false;
            isOccupied[i][4] = false;
            isOccupied[i][5] = false;
        }
        board.add(new Pawn(4, 1, true));
        board.add(new Pawn(4, 6, false));
        for (int i = 0; i < 8; i++) {
            if (i == 4) {
                continue;
            }
            board.add(new Pawn(i, 1, true));
            board.add(new Pawn(i, 6, false));
        }

        board.add(new Rook(0, 0, true, true));
        board.add(new Knight(1, 0, true));
        board.add(new Bishop(2, 0, true));
        board.add(new Queen(3, 0, true));
        board.add(new King(4, 0, true, false));
        board.add(new Bishop(5, 0, true));
        board.add(new Knight(6, 0, true));
        board.add(new Rook(7, 0, true, true));

        board.add(new Rook(0, 7, false, true));
        board.add(new Knight(1, 7, false));
        board.add(new Bishop(2, 7, false));
        board.add(new Queen(3, 7, false));
        board.add(new King(4, 7, false, false));
        board.add(new Bishop(5, 7, false));
        board.add(new Knight(6, 7, false));
        board.add(new Rook(7, 7, false, true));

    }

    /**
     * @param board an arraylist of pieces that are on a board sets the board to
     *              have the pieces in board, updates fields
     */
    public Board(ArrayList<Piece> board) {
        this.board = board;
        isOccupied = new Boolean[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                isOccupied[i][j] = false;
            }
        }
        for (int i = 0; i < board.size(); i++) {
            isOccupied[board.get(i).getLoc().getXPos()][board.get(i).getLoc().getYPos()] = true;
        }

    }

    /**
     * Checks if two boards have the same pieces in the same spots
     * 
     * @param other the board to compare to
     * @return boolean true if the boards are equal, false otherwise
     */
    public boolean equals(Board other) {
        char[][] c1 = this.charBoard();
        char[][] c2 = other.charBoard();
        boolean ret = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (c1[i][j] != c2[i][j]) {
                    ret = false;
                }
            }
        }
        return ret;
    }

    /**
     * returns the set of the pieces currently on the board
     * 
     * @return ArrayList the set of pieces
     */
    public ArrayList<Piece> getBoard() {
        return board;
    }

    /**
     * Returns the value of the position, adds up all the material value of every
     * piece on the baord
     * 
     * @return int
     */
    public double getValue() {
        double val = 0.0;
        ArrayList<Piece> p = getBoard();
        for (Piece piece : p) {
            val += piece.getValue();
            val = (piece.getColor()) ? (val + (activityRelativeWeight * piece.getActivity(piece.getLoc())))
                    : val - (activityRelativeWeight * piece.getActivity(piece.getLoc()));
        }

        // System.out.println("Poss Moves: " + getPossibleMoves(true).size() + '\n' +
        // "Board Value: " + val );
        return val;
    }

    /**
     * Return the pieces at a given location, returns null if there is no piece at
     * that location
     * 
     * @param loc the location to check
     * @return Piece
     */
    public Piece getPiece(Location loc) {
        int x = loc.getXPos();
        int y = loc.getYPos();
        for (Piece p : getBoard()) {
            if (p.getLoc().getXPos() == x && p.getLoc().getYPos() == y) {
                return p;
            }
        }
        return null;
    }

    /**
     * Returns the set of pieces on a board
     * 
     * @return ArrayList the set of pieces on the board
     */
    public ArrayList<Piece> copyBoard() {
        ArrayList<Piece> pieces = new ArrayList<>();
        for (Piece i : this.getBoard()) {
            int xPos = i.getLoc().getXPos();
            int yPos = i.getLoc().getYPos();
            boolean color = i.getColor();
            if (i instanceof Pawn) {
                pieces.add(new Pawn(xPos, yPos, color));
            }
            if (i instanceof Knight) {
                pieces.add(new Knight(xPos, yPos, color));
            }
            if (i instanceof Bishop) {
                pieces.add(new Bishop(xPos, yPos, color));
            }
            if (i instanceof Queen) {
                pieces.add(new Queen(xPos, yPos, color));
            }
            if (i instanceof Rook) {
                pieces.add(new Rook(xPos, yPos, color, true));
            }
            if (i instanceof King) {
                pieces.add(new King(xPos, yPos, color, false));
            }
        }
        return pieces;
    }

    /**
     * Return true if there is no piece at a given location, false otherwise
     * 
     * @param loc the location to check
     * @return Boolean
     */
    public Boolean check(Location loc) {
        int x = loc.getXPos();
        int y = loc.getYPos();
        return !isOccupied[x][y];
    }

    public Boolean check( int x, int y)
    {
        return !isOccupied[x][y];
    }

    /**
     * returns the set of all possible moves for a side(color is true = white, false
     * = black), removes illegal moves, returns null if the current position is
     * checkmate
     * 
     * @param color true if it is white's turn, false otherwise
     * @return ArrayList the set of all possible resulting Boards
     */
    public ArrayList<Board> getPossibleMoves(boolean color) {

        ArrayList<Board> allNextMoves = new ArrayList<>();

        for (int x = 0; x < 64; x++) {
            Piece p = this.getPiece(new Location(searchOrder[x][0], searchOrder[x][1]));
            if (p != null && p.getColor() == color) {
                ArrayList<Board> temp = p.findMoves(this, this.getBoard().indexOf(p));

                if (temp == null) {
                    continue;
                }

                for (Board board : temp) {
                    boolean bad = false;
                    for (int j = 0; j < board.getBoard().size(); j++) {
                        if (board.getBoard().get(j).getColor() != color
                                && board.getBoard().get(j).isInCheck(board, j) == true) {
                            bad = true;
                            break;
                        }
                    }
                    if (bad) {
                        continue;
                    }
                    allNextMoves.add(board);
                }
            }
        }

        // for (int i = 0; i < this.getBoard().size(); i++) {
        //     if (this.getBoard().get(i).getColor() == color) {
        //         ArrayList<Board> temp = this.getBoard().get(i).findMoves(this, i);

        //         if (temp == null) {
        //             continue;
        //         }

        //         for (Board board : temp) {
        //             boolean bad = false;
        //             for (int j = 0; j < board.getBoard().size(); j++) {
        //                 if (board.getBoard().get(j).getColor() != color
        //                         && board.getBoard().get(j).isInCheck(board, j) == true) {
        //                     bad = true;
        //                     break;
        //                 }
        //             }
        //             if (bad) {
        //                 continue;
        //             }
        //             allNextMoves.add(board);
        //         }

        //     }
        // }
        return allNextMoves;
    }

    /**
     * Returns the best next possible board based on the material evalution of the
     * board for a given color
     * 
     * @param color true if it white to move, false otherwise
     * @return Board
     */
    public Board getBestBoard(boolean color) {
        Board best = null;
        for (Board board : getPossibleMoves(color)) {
            if (best == null) {
                best = board;
            } else {
                if (best.getValue() < board.getValue()) {
                    best = board;
                }
            }
        }
        return best;
    }

    /**
     * updates the board, removes the piece p1, and adds the piece p2, takes care of
     * the possibility of p2 is taking another piece, p1 and p2 will be the same
     * type
     * 
     * @param p1 the piece to remove
     * @param p2 the piece to put on the board
     * @return Board, the updated Board
     */
    public Board updateBoard(Piece p1, Piece p2) {
        ArrayList<Piece> pieces = this.getBoard();

        pieces.remove(p1);
        if (p2 != null) {
            for (int i = 0; i < pieces.size(); i++) {
                if (pieces.get(i).getLoc().getXPos() == p2.getLoc().getXPos()
                        && pieces.get(i).getLoc().getYPos() == p2.getLoc().getYPos()) {
                    pieces.remove(i);
                    break;
                }
            }

            pieces.add(p2);
        }
        return new Board(pieces);
    }

    /**
     * returns a matrix of the current Board, capital letter for a white piece,
     * lowercase for black
     * 
     * @return char[][]
     */
    public char[][] charBoard() {
        char[][] board = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = ' ';
            }
        }
        ArrayList<Piece> pieces = getBoard();
        for (Piece i : pieces) {
            board[i.getLoc().getXPos()][i.getLoc().getYPos()] = i.getCode();
        }
        return board;
    }

    public String hash() {
        String hash = "";
        ArrayList<Piece> pieces = getBoard();
        int row = 0;
        for (Boolean[] b : isOccupied) {
            int col = 0;
            for (Boolean i : b) {
                if (this.check(new Location(row, col))) {
                    hash += "0";
                } else {
                    hash += this.getPiece(new Location(row, col)).getCode();
                }
                col++;
            }
            row++;
        }
        return hash;
    }

    /**
     * prints out the currentBoard, uppercase for white, lowercase for black, prints
     * out the rows and columns as well
     */
    public void printBoard() {
        char[][] board = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = ' ';
            }
        }
        ArrayList<Piece> pieces = getBoard();
        for (Piece i : pieces) {

            if (i instanceof Pawn && i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = '\u2659';
            } else if (i instanceof Pawn && !i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = '\u265F';
            }
            if (i instanceof Knight && i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = '\u2658';
            } else if (i instanceof Knight && !i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = '\u265E';
            }
            if (i instanceof Bishop && i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = '\u2657';
            } else if (i instanceof Bishop && !i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = '\u265D';
            }
            if (i instanceof Queen && i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = '\u2655';
            } else if (i instanceof Queen && !i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = '\u265B';
            }
            if (i instanceof Rook && i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = '\u2656';
            } else if (i instanceof Rook && !i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = '\u265C';
            }
            if (i instanceof King && i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = '\u2654';
            } else if (i instanceof King && !i.getColor()) {
                board[i.getLoc().getXPos()][i.getLoc().getYPos()] = '\u265A';
            }
        }
        System.out.println("  1 2 3 4 5 6 7 8");
        char[] letterConvs = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                if (j == 0) {
                    System.out.print(letterConvs[i] + " ");
                }

                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

    }

    public Board createOpening(String s) {
        switch (s) {
            case "King's Pawn":
                System.out.println("You are playing against a King's Pawn Opening!");
                return this.updateBoard(this.getPiece(new Location(4, 1)), new Pawn(4, 3, true));
            case "Queen's Pawn":
                System.out.println("You are playing against a Queen's Pawn Opening!");

                return this.updateBoard(this.getPiece(new Location(3, 1)), new Pawn(3, 3, true));
            case "English":
                System.out.println("You are playing against an English Opening!");

                return this.updateBoard(this.getPiece(new Location(2, 1)), new Pawn(2, 3, true));
            default:
                return this;
        }

    }
}
