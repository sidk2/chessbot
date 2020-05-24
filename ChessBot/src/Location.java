
public class Location
{
    private int xPos;
    private int yPos;
    
    public Location(int xCoord, int yCoord) {
        this.xPos = xCoord;
        this.yPos = yCoord;
    }
    
    public int getXPos() {
        return xPos;
    }
    public boolean equals(Location other)
    {
    	return getXPos() == other.getXPos() && getYPos()==other.getYPos();
    }
    public int getYPos() {
        return yPos;
    }
}
