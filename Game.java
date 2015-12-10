package Pentomino;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Pentomino.Interfaces.ConfigurationInterface;
import Pentomino.Interfaces.Control;
import Pentomino.Interfaces.Display;
import Pentomino.Interfaces.TetrisGame;

public class Game implements TetrisGame
{
	Control c;	
	Display d;
	Board b;
	ConfigurationInterface CI;
	private Timer t1;
	private Timer t2;
	private int points=0;
	
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
			CI = this.CI;
	   }
	   else
	   {
		   this.CI = CI;
	   }
	   b = new Board(CI.getBoardWidth(), CI.GetBoardHeight());
	   //b.board[4][4].setC(Color.CYAN);
	   /*
	   b.board[4][11].setC(Color.green);
	   b.board[0][4].setC(Color.BLUE);
	   b.board[2][2].setC(Color.RED);*/
	   d.setData(b);
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
	
		PentominoMain.scoreLabel2.setText("                  " +b.currentScore);
		t1 = new javax.swing.Timer(CI.getSpeedOfStep(), new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("tick");
			
				b.moveLivingPentominoOneTick();
				if (Collision())
				{
					b.moveLivingPentomino(-1,0);
					b.setLivingPentominoDown(); //if endgame aktivates, it maybe removes full lines
					if (b.isEndgame()) 
					{
						b.score.updateHighscore(b.currentScore);
						t1.stop(); 
						t2.stop();
					}
					points +=b.removeFullLines(b.checkForFullLines());
				}
				d.refresh();
			}
		});
		t2 = new javax.swing.Timer(CI.getSpeedOfControl(), new ActionListener() 
		{	
			public void actionPerformed(ActionEvent e) 
			{
				MoveControl(c);
			
				d.refresh();
			}
		});
		t1.start();
		t2.start();
	}
	/**
	 * creates standard configuration interface
	 */
	private void createStandartConfigurationInterface() 
	{
		CI = new ConfigurationInterface() 
		{
			int sc = 800/50;
			int ss = 1000/1;
			
			public int getSpeedOfControl() 
			{
				return sc;
			}

			public int getSpeedOfStep() 
			{			
				return ss;
			}

			public void setSpeedOfControl(int s) 
			{
				sc = s;
				t1.setDelay(s);
			}

			public void setSpeedOfStep(int s) 
			{
				ss = s;
				t2.setDelay(s);			
			}

			public int getBoardWidth() 
			{
				return 5;
			}

			public int GetBoardHeight() 
			{			
				return 15;
			}
		};
	}
	/**
	 * moves the pentomino down
	 * @param c the control interface parameter
	 */
	public void MoveControl(Control c) 
	{		b.moveLivingPentomino(c,false);		}

	/**
	 * moves the pentomino down
	 */
	public void MoveTime() 
	{		b.moveLivingPentominoOneTick();		}
	/**
	 * checks for collision
	 */
	public boolean Collision() 
	{		return b.isCollision();		}
	/**
	 * places the piece
	 */
	public boolean PlacePiece()
	{		return false;	}
	/**
	 * sets the control interface
	 * @param c the control interface parameter
	 */
	protected void setC(Control c) 
	{		this.c = c;		}
	/**
	 * returns the number of deleted lines
	 */
	public long getDestroyedLines() 
	{		return points;	}
	/**
	 * returns points
	 * @return
	 */
	public int getPoints() 
	{		return points;	}
	
	
	public void NextPiece(){}
	
	public void UpdateScore(){}
}