import java.util.ArrayList;

public class Pawn implements Piece 
{
    private Location loc;
    private Boolean color;
    public final int VALUE =10;
    public Pawn( int xPos, int yPos, Boolean color )
    {
        loc = new Location(xPos, yPos);
        this.color = color;
    }
    public boolean equals(Piece other)
    {
    	return other.getLoc().equals(getLoc()) && other.getClass().getCanonicalName().equals(this.getClass().getCanonicalName());
    }
    public int getValue()
    {
    	if(color)
    		return VALUE;
    	else
    		return -1*VALUE;
    }
    @Override
    public ArrayList<Board> findMoves(Board b, int index) {
        ArrayList<Piece> pieces = b.getBoard(); //the ArrayList in b
        ArrayList<Piece> temp = new ArrayList<>();
        for(Piece i : pieces) {
            temp.add( i );
        }
        //System.out.println(pieces.get( index ).getLoc().getXPos());
        
        ArrayList<Board> ans = new ArrayList<>();
        temp.remove(index);
        int curX = pieces.get( index ).getLoc().getXPos();
        int curY = pieces.get( index ).getLoc().getYPos();
        //System.out.println(curX + " " + curY);
        if(pieces.get( index ).getColor() == true) {
            if(curY == 1) {
                if(b.check( new Location(curX, curY + 2) ) == true && b.check( new Location(curX, curY + 1) ) == true) {
                    ArrayList<Piece> nextPos = new ArrayList<>();
                    for(Piece i : temp) {
                        nextPos.add( i );
                    }
                    nextPos.add( new Pawn(curX, curY + 2, pieces.get( index ).getColor()) );
                    ans.add( new Board(nextPos) );
                    //nextPos.remove( nextPos.size() - 1 );
                }
            }
            if(curY + 1 < 7) {
                if(b.check( new Location(curX, curY + 1) ) == true) {
                    ArrayList<Piece> nextPos = new ArrayList<>();
                    for(Piece i : temp) {
                        nextPos.add( i );
                    }
                    nextPos.add( new Pawn(curX, curY + 1, pieces.get( index ).getColor()));
                    Board r = new Board(nextPos);
                    //r.printBoard( r );
                    ans.add( new Board(nextPos) );
                    //nextPos.remove( nextPos.size() - 1 );
                }
            }
            if(curX - 1 >= 0 && curY + 1 < 7 && b.check( new Location(curX - 1, curY + 1) ) == false) {
                for(int i = 0; i < pieces.size(); i++) {
                    if(pieces.get( i ).getLoc().getXPos() == curX - 1 && pieces.get( i ).getLoc().getYPos() == curY + 1 && pieces.get( i ).getColor() != pieces.get( index ).getColor()) {
                        ArrayList<Piece> nextPos = new ArrayList<>();
                        for(Piece r : temp) {
                            if(r.equals( pieces.get( i ) )) {
                                continue;
                            }
                            nextPos.add( r );     
                        }
                        nextPos.add( new Pawn(curX - 1, curY + 1, pieces.get( index ).getColor()) );
                        ans.add( new Board(nextPos) );
                        break;
                    }
                }
            }
            if(curX + 1 < 8 && curY + 1 < 7 && b.check( new Location(curX + 1, curY + 1) ) == false) {
                for(int i = 0; i < pieces.size(); i++) {
                    if(pieces.get( i ).getLoc().getXPos() == curX + 1 && pieces.get( i ).getLoc().getYPos() == curY + 1 && pieces.get( i ).getColor() != pieces.get( index ).getColor()) {
                        ArrayList<Piece> nextPos = new ArrayList<>();
                        for(Piece r : temp) {
                            if(r.equals( pieces.get( i ) )) {
                                continue;
                            }
                            nextPos.add( r );
                        }
                        nextPos.add( new Pawn(curX + 1, curY + 1, pieces.get( index ).getColor()) );
                        ans.add( new Board(nextPos) );
                        break;
                    }
                }
            }
//            if(curY + 1 == 7) {
//                ArrayList<Piece> nextPos1 = temp;
//                nextPos1.add( new Bishop(curX, curY + 1, pieces.get( index ).getColor()) );
//                ArrayList<Piece> nextPos2 = temp;
//                nextPos2.add( new Knight(curX, curY + 1, pieces.get( index ).getColor()) );
//                ArrayList<Piece> nextPos3 = temp;
//                nextPos3.add( new Queen(curX, curY + 1, pieces.get( index ).getColor()) );
//                ans.add( new Board(nextPos1) );
//                ans.add( new Board(nextPos2) );
//                ans.add( new Board(nextPos3) );
//            }
            //ADD TAKING
            //ADD TAKING TO PROMOTION
        }
        else {
            //System.out.println("hi");
//            if(curY == 6) {
//                if(b.check( new Location(curX, curY - 2) ) == true && b.check(new Location(curX, curY - 1)) == true) {
//                    //System.out.println("hi");
//                    ArrayList<Piece> nextPos = temp;
//                    //Board board  = new Board(nextPos);
//                    //board.printBoard( board );
//                    nextPos.add( new Pawn(curX, curY - 2, pieces.get( index ).getColor()) );
//                    Board board  = new Board(nextPos);
//                    //System.out.println(curY - 1 );
//                    board.printBoard( board );
//                    ans.add( new Board(nextPos) );
//                }
//            }
//            if(curY - 1 > 0) {
//                if(b.check( new Location(curX, curY - 1) ) == true) {
//                    ArrayList<Piece> nextPos = temp;
//                    nextPos.add( new Pawn(curX, curY - 1, pieces.get( index ).getColor()));
//                    ans.add( new Board(nextPos) );
//                }
//            }
            if(curY == 6) {
                if(b.check( new Location(curX, curY - 2) ) == true && b.check( new Location(curX, curY - 1) ) == true) {
                    ArrayList<Piece> nextPos = new ArrayList<>();
                    for(Piece i : temp) {
                        nextPos.add( i );
                    }
                    nextPos.add( new Pawn(curX, curY - 2, pieces.get( index ).getColor()) );
                    ans.add( new Board(nextPos) );
                    //nextPos.remove( nextPos.size() - 1 );
                }
            }
            if(curY - 1 > 0) {
                if(b.check( new Location(curX, curY - 1) ) == true) {
                    ArrayList<Piece> nextPos = new ArrayList<>();
                    for(Piece i : temp) {
                        nextPos.add( i );
                    }
                    nextPos.add( new Pawn(curX, curY - 1, pieces.get( index ).getColor()));
                    Board r = new Board(nextPos);
                    //r.printBoard( r );
                    ans.add( new Board(nextPos) );
                    //nextPos.remove( nextPos.size() - 1 );
                }
            }
            if(curX - 1 >= 0 && curY - 1 > 0 && b.check( new Location(curX - 1, curY - 1) ) == false) {
                for(int i = 0; i < pieces.size(); i++) {
                    if(pieces.get( i ).getLoc().getXPos() == curX - 1 && pieces.get( i ).getLoc().getYPos() == curY - 1 && pieces.get( i ).getColor() != pieces.get( index ).getColor()) {
                        ArrayList<Piece> nextPos = new ArrayList<>();
                        for(Piece r : temp) {
                            if(r.equals( pieces.get( i ) )) {
                                continue;
                            }
                            nextPos.add( r );     
                        }
                        nextPos.add( new Pawn(curX - 1, curY - 1, pieces.get( index ).getColor()) );
                        ans.add( new Board(nextPos) );
                        break;
                    }
                }
            }
            if(curX + 1 < 8 && curY - 1 > 0 && b.check( new Location(curX + 1, curY - 1) ) == false) {
                for(int i = 0; i < pieces.size(); i++) {
                    if(pieces.get( i ).getLoc().getXPos() == curX + 1 && pieces.get( i ).getLoc().getYPos() == curY - 1 && pieces.get( i ).getColor() != pieces.get( index ).getColor()) {
                        ArrayList<Piece> nextPos = new ArrayList<>();
                        for(Piece r : temp) {
                            if(r.equals( pieces.get( i ) )) {
                                continue;
                            }
                            nextPos.add( r );     
                        }
                        nextPos.add( new Pawn(curX + 1, curY - 1, pieces.get( index ).getColor()) );
                        ans.add( new Board(nextPos) );
                        break;
                    }
                }
            }
//            if(curY - 1 == 0) {
//                ArrayList<Piece> nextPos1 = temp;
//                nextPos1.add( new Bishop(curX, curY - 1, pieces.get( index ).getColor()) );
//                ArrayList<Piece> nextPos2 = temp;
//                nextPos2.add( new Knight(curX, curY - 1, pieces.get( index ).getColor()) );
//                ArrayList<Piece> nextPos3 = temp;
//                nextPos3.add( new Queen(curX, curY - 1, pieces.get( index ).getColor()) );
//                ans.add( new Board(nextPos1) );
//                ans.add( new Board(nextPos2) );
//                ans.add( new Board(nextPos3) );
//            }
            //ADD TAKING
        }
        
        return ans;
    }

    @Override
    public Location getLoc()
    {
        return loc;
    }

    @Override
    public boolean isInCheck( Board b, int index )
    {
        ArrayList<Piece> pieces = b.getBoard();
        int curX = pieces.get( index ).getLoc().getXPos();
        int curY = pieces.get( index ).getLoc().getYPos();
        if(pieces.get( index ).getColor() == true) {
            for(Piece i : pieces) {
                if(i instanceof King && i.getColor() == false) {
                    if((i.getLoc().getXPos() == curX + 1 && i.getLoc().getYPos() == curY + 1) ||(i.getLoc().getXPos() == curX - 1 && i.getLoc().getYPos() == curY + 1)) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        else {
            for(Piece i : pieces) {
                if(i instanceof King && i.getColor() == true) {
                    if((i.getLoc().getXPos() == curX - 1 && i.getLoc().getYPos() == curY -1) || (i.getLoc().getXPos() == curX + 1 && i.getLoc().getYPos() == curY - 1)) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean getColor()
    {
        return color;
    }
    
}
