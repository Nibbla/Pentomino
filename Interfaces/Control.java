package Pentomino.Interfaces;



public interface Control {
	public enum Buttons{
		Up,Left,Right,Down,Select,AllTheWayDown,RotateRight,RotateLeft
	}			
	public boolean isButtonPressed(Buttons b);
	public void overRideButton(Buttons b, boolean newValue);
		
}
