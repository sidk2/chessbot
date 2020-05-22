import java.util.ArrayList;

public class King implements Piece
{
    private Location loc;
    private boolean color;
    public final int VALUE =900;
    public King(int xPos, int yPos, boolean color) {
        loc = new Location(xPos, yPos);
        this.color = color;
    }
    public int getValue()
    {
    	if(color)
    		return VALUE;
    	else
    		return -1*VALUE;
    }
    
    @Override
    public ArrayList<Board> findMoves( Board b, int index )
    {
        //ADD TAKING
        ArrayList<Piece> pieces = b.getBoard();
        ArrayList<Piece> temp = new ArrayList<>();
        for(Piece i : pieces) {
            temp.add( i );
        }
        ArrayList<Board> ans = new ArrayList<>();
        temp.remove(index);
        
        int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
        
        int curX = pieces.get( index ).getLoc().getXPos();
        int curY = pieces.get( index ).getLoc().getYPos();
        
        for(int i = 0; i < 8; i++) {
            //System.out.println((curX + dx[i]) + " " + (curY + dy[i]));
            if(curX + dx[i] >= 0 && curX + dx[i] < 8 && curY + dy[i] >= 0 && curY + dy[i] < 8 && (b.check(new Location(curX + dx[i], curY + dy[i])) == true)) {
                ArrayList<Piece> nextPos = new ArrayList<>();
                for(Piece r : temp) {
                    nextPos.add( r );
                }
                nextPos.add( new King(curX + dx[i], curY + dy[i], pieces.get( index ).getColor()) );
                ans.add( new Board(nextPos) );
            }
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
        return false;
    }


    @Override
    public boolean getColor()
    {
        return color;
    }

}
