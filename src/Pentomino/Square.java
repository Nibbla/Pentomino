package Pentomino;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.text.StyledEditorKit.BoldAction;

public class Square implements Cloneable{
	private int x,y,z;
	private Color c;
	/*
	public Square(int x, int y, int z, Color c){
		this.x=x;
		this.y=y;
		this.z=z;
		this.c=c;
	}*/
	public Square(int x, int y){
		c = Color.GRAY;
		this.x=x;
		this.y=y;
	}
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	public void setZ(int z){
		this.z=z;
	}
	public void setC(Color c){
		this.c=c;
	}
	public void setC(ColorE c){
		this.c=c;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getZ(){
		return z;
	}
	public Color getC(){
		return c;
	}
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
