/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

public abstract class ChessPiece extends Actor
{
	
	private boolean isAffected = false;

    /**
     * Constructs a bug of a given color.
     * @param bugColor the color for this bug
     */
    public ChessPiece(Color color)
    {
        setColor(color);
    }

    /**
     * Moves if it can move, turns otherwise.
     */
    public void act()
    {
        if (canMove())
            move();
    }

//    /**
//     * Turns the bug 45 degrees to the right without changing its location.
//     */
//    public void turn()
//    {
//        setDirection(getDirection() + Location.RIGHT);
//    }


    public void move()
    {
    	
    }

    public void touched() {
    	isAffected = !isAffected;
    }
    
    public boolean canMove()
    {
    	return isAffected;
    }
    
    public boolean capture() {
    	return false;
    }
    
    public boolean captured() {
    	return false;
    }
}
