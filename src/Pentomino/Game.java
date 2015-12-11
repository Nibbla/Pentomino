package Pentomino;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Pentomino.Interfaces.ConfigurationInterface;
import Pentomino.Interfaces.Control;
import Pentomino.Interfaces.Display;
import Pentomino.Interfaces.TetrisGame;

/** The Gameclass represents a running game.
 *  It combines a Control, a Display, and the Gamerules.
 *  
 * @author Nibbla
 *
 */
public class Game implements TetrisGame
{
	Control c;
	Display d;
	Board b;
	
	ConfigurationInterface CI;
	private Timer t1;
	private Timer t2;

	private int points=0;

	private String move;
	
	/**
	 *  contructor to create game in beginning
	 * @param c the control parameter
	 * @param d the display parameter
	 * @param CI the configuration interface parameter
	 */
   public Game(Control c, Display d,ConfigurationInterface CI)
   {
	   this.c = c;
	   this.d = d;
	  
	   if (CI == null)
	   {
			createStandartConfigurationInterface();
			CI=this.CI;
		}else{this.CI=CI;}
	   b = new Board(CI.getBoardWidth(), CI.GetBoardHeight());
	  
	   d.setData(b);
   }
   
   public Board getBoard() {
		return b;
	}

	/**
	 * starts the game
	 */
public void start() 
	{
	points = 0;
	b.currentScore=0;
		try 
		{
			b.score.loadHighscore();
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
		
	t1 = new javax.swing.Timer(CI.getSpeedOfStep(), new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			tick();
			PentominoMain.scoreLabel2.setText("                  " +b.currentScore);
		}
	});
	 t2 = new javax.swing.Timer(CI.getSpeedOfControl(), new ActionListener() {	
		public void actionPerformed(ActionEvent e) {
			tick("move");		
			
		}
	});
	t1.start();
	t2.start();
	
}



protected void setC(Control c) {
	this.c = c;
}
private void createStandartConfigurationInterface() {
	CI = new ConfigurationInterface() {
		int sc = 800/20;
		int ss = 1000/1;
		public int getSpeedOfControl() {
			return sc;
		}

		public int getSpeedOfStep() {
			
			return ss;
		}

		public void setSpeedOfControl(int s) {
			sc = s;
			t1.setDelay(s);
		}

		public void setSpeedOfStep(int s) {
			ss = s;
			t2.setDelay(s);
			
		}

		public int getBoardWidth() {
			
			return 5;
		}

		public int GetBoardHeight() {
			
			return 15;
		}
	};
	
}
public String MoveControl(Control c) {
	b.moveLivingPentomino(c,false);
	d.refresh();
	return b.getMove();
}

public void MoveTime() {
	b.moveLivingPentominoOneTick();
	
}
public boolean Collision() {
	return b.isCollision();
	
}
public boolean PlacePiece() {
	// TODO Auto-generated method stub
	return false;
}
public void NextPiece() {
	// TODO Auto-generated method stub
	
}
public void UpdateScore() {
	// TODO Auto-generated method stub
	
}
public long getScore() {
	// TODO Auto-generated method stub
	return 0;
}

public String tick() {

	
	String s = "tick";
	b.moveLivingPentominoOneTick();
	if (Collision()){
		b.moveLivingPentomino(-1,0);
		b.setLivingPentominoDown(); //if endgame aktivates, it maybe removes full lines
		s += "puff";
		if (b.isEndgame()) {
			b.score.updateHighscore(b.currentScore);
			t1.stop();
			t2.stop();
			s += "puffpuff";
		}
		
	}
	points +=b.removeFullLinesAndReturnNumberOfPoints(b.checkForFullLines());
	d.refresh();
	return s;
}

public String tick(String move) {
	if (b.isEndgame())return "|";
	b.setMove(move);
	String r = MoveControl(c);
	return r;	
}

public long getDestroyedLines() {
	
	return points;
}

}
