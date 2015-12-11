package Pentomino;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.BoldAction;

public class Square implements Cloneable{
	private int x,y,z;
	private Color c;
	/**
	 * constructor for the square
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Square(int x, int y){
		c = Color.GRAY;
		this.x=x;
		this.y=y;
	}
	/**
	 * sets x
	 * @param x
	 */
	public void setX(int x){
		this.x=x;
	}
	/**
	 * sets y
	 * @param y
	 */
	public void setY(int y){
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
	public void setC(Color c){
		this.c=c;
	}
	/**
	 * sets the color
	 * @param c colorE
	 */
	public void setC(ColorE c){
		this.c=c;
	}
	/**
	 * getter for x
	 * @return x
	 */
	public int getX(){
		return x;
	}
	/**
	 * getter for y
	 * @return y
	 */
	public int getY(){
		return y;
	}
	/**
	 * getter for z
	 * @return z
	 */
	public int getZ(){
		return z;
	}
	/**
	 * getter for the color
	 * @return the color
	 */
	public Color getC(){
		return c;
	}
	/**
	 * makes a copy of the square
	 * @return the new square
	 */
	public Square copy() {
		Square s = new Square(x,y);
		s.z = z;
		s.c = new Color(c.getRGB());
		return s;
	}
	public static double calculatePotential(Square[][] board,int weightY, int weightX, int weightLines) {
		if (board == null) return -1;
		double potential = 0;
		potential += potentialX(board)*weightX;
		potential += potentialY(board)*weightY;
		potential += potentialLines(board)*weightLines;
		
		return potential;
	}
	private static double potentialLines(Square[][] board) {
		double potential = 0;
		double pieceNumber = 0;
		ArrayList<Integer> lines = Board.checkForFullLines(board);
		
		for (Integer i : lines) {
			potential =+  board[0].length / 3 * (board.length-i); 
			pieceNumber++;
		}
		
		 
		return potential;
	}
	private static double potentialY(Square[][] board) {
		double potential = 0;
		double pieceNumber = 0;
		for (int i = 0;i<board.length;i++){
			for (int j = 0;j<board[0].length;j++){
				if (!board[i][j].getC().equals(Color.GRAY)){
					potential = potential + (i+1);
					//pieceNumber++;
				}
			}
		}
		//potential /= pieceNumber;
		return potential;
	}
	private static double potentialX(Square[][] board) {
		int potential = 0;
		int pieceNumber = 0;
		for (int i = 0;i<board.length;i++){
			for (int j = 0;j<board[0].length;j++){
				if (!board[i][j].getC().equals(Color.GRAY)){
					potential = potential + (board[0].length-j-1);
					//pieceNumber++;
				}
			}
		}
		//potential /= pieceNumber;
		return potential;
	}
	
}
