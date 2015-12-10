package Pentomino;

import java.awt.Color;

public class Square 
{
	private int x,y,z;
	private Color c;
	
	/**
	 * constructor for the square
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Square(int x, int y)
	{
		c = Color.GRAY;
		this.x=x;
		this.y=y;
	}
	
	/**
	 * sets x
	 * @param x
	 */
	public void setX(int x)
	{
		this.x=x;
	}
	/**
	 * sets y
	 * @param y
	 */
	public void setY(int y)
	{
		this.y=y;
	}
	/**
	 * sets z
	 * @param z
	 */
	public void setZ(int z){
		this.z=z;
	}
	/**
	 * sets the color
	 * @param c color
	 */
	public void setC(Color c)
	{
		this.c=c;
	}
	/**
	 * sets the color
	 * @param c colorE
	 */
	public void setC(ColorE c)
	{
		this.c=c;
	}
	/**
	 * getter for x
	 * @return x
	 */
	public int getX()
	{
		return x;
	}
	/**
	 * getter for y
	 * @return y
	 */
	public int getY()
	{
		return y;
	}
	/**
	 * getter for z
	 * @return z
	 */
	public int getZ()
	{
		return z;
	}
	/**
	 * getter for the color
	 * @return the color
	 */
	public Color getC()
	{
		return c;
	}
	/**
	 * makes a copy of the square
	 * @return the new square
	 */
	public Square copy() 
	{
		Square s = new Square(x,y);
		s.z = z;
		s.c = new Color(c.getRGB());
		return s;
	}
}