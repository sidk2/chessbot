/**
 * A class for the location of a piece, has an xCoordinate and a yCoordinate, 0
 * indexed
 *
 */
public class Location
{
    /**
     * the x coordinate of the piece
     */
    private int xPos;

    /**
     * the y coordinate of the piece
     */
    private int yPos;


    /**
     * @param xCoord
     *            the x coordinate of the piece
     * @param yCoord
     *            the y coordinate of the piece constructor for the location
     *            class, instantiates fields
     */
    public Location( int xCoord, int yCoord )
    {
        this.xPos = xCoord;
        this.yPos = yCoord;
    }


    /**
     * returns the x coordinate
     * 
     * @return int
     */
    public int getXPos()
    {
        return xPos;
    }


    /**
     * returns true if two locations are equal, false otherwise
     * 
     * @param other
     *            the other location to compare to
     * @return boolean
     */
    public boolean equals( Location other )
    {
        return getXPos() == other.getXPos() && getYPos() == other.getYPos();
    }


    /**
     * returns the y coordinate of the location
     * 
     * @return int
     */
    public int getYPos()
    {
        return yPos;
    }
}
