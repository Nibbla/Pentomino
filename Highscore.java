package Pentomino;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.JOptionPane;
public class Highscore
{
	private static int score1;
	private static int score2;
	private static int score3;
	private static int score4;	
	private static int score5;
	private static String name1 = "";
	private static String name2 = "";
	private static String name3 = "";
	private static String name4 = "";
	private static String name5 = "";
	private int level;
	
	public long clearedLines;
	private static String name;
	/**
	 * point system for the game
	 * @param score the current score in the game
	 * @param lines the lines that were cleared
	 * @return returns the current games score
	 */
	public int score(int score, long lines) 
	{	
		this.clearedLines = lines;
			
		if (clearedLines == 1)
			score = score + (50*(level+1));
		
		if (clearedLines == 2)
			score = score + (150*(level+1));
			
		if (clearedLines == 3)
			score = score + (350*(level+1));
		
		if (clearedLines == 4)
			score = score + (1000*(level+1));
		
		if (clearedLines == 5)
			score = score + (2350*(level+1));
	
		return score;		
	}
	
	/**
	 * updates high score
	 * @param score the current games score to be stored in high score
	 */
	public void updateHighscore(int score)
	{		
		if (score > score5)
		{
			JOptionPane.showMessageDialog(null, "New highscore!");
	        name = JOptionPane.showInputDialog("Name: ");
			
			if (score > score1) 
			{
				name5 = name4;
				name4 = name3;				
				name3 = name2;
				name2 = name1;
				name1 = name;
				score5 = score4;
				score4 = score3;
				score3 = score2;
				score2 = score1;
				score1 = score;
			}		
			else if (score > score2)
			{
				name5 = name4;
				name4 = name3;				
				name3 = name2;
				name2 = name;
				score5 = score4;
				score4 = score3;
				score3 = score2;
				score2 = score;
			}
			else if (score > score3)
			{
				name5 = name4;
				name4 = name3;				
				name3 = name;
				score5 = score4;
				score4 = score3;
				score3 = score;		
			}	
			else if (score > score4)
			{
				name5 = name4;
				name4 = name;				
				score5 = score4;
				score4 = score;
			}
			else if (score > score5)
			{
				name5 = name;
				score5 = score;
			}
			
			try 
			{
				saveHighscore();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
	/**
	 * loads high score from text file
	 * @throws Exception
	 */
	public void loadHighscore() throws Exception
	{
		String path = Paths.get(".").toAbsolutePath().normalize().toString();
		File directory = new File(path, "/Text Files");
		if (!directory.exists())
			directory.mkdirs();		
		
		File highscore = new File(directory, "/highscore.txt");
		if (!highscore.exists())
		{
			highscore.createNewFile();
			return;
		}
		
		Scanner in = new Scanner(new FileReader(highscore));
		String[] entries = new String[2];

		entries = in.next().split(":");			
		name1 = entries[0];
		score1= Integer.parseInt(entries[1]);
		
		entries = in.next().split(":");
		name2 = entries[0];
		score2= Integer.parseInt(entries[1]);
		
		entries = in.next().split(":");
		name3 = entries[0];
		score3= Integer.parseInt(entries[1]);
			
		entries = in.next().split(":");
		name4 = entries[0];
		score4= Integer.parseInt(entries[1]);
			
		entries = in.next().split(":");		
		name5 = entries[0];
		score5= Integer.parseInt(entries[1]);
		
		in.close();
	}
	/**
	 * saves high score to a text file
	 * @throws Exception
	 */
	public static void saveHighscore() throws Exception
	{
		String path = Paths.get(".").toAbsolutePath().normalize().toString();
			
		File directory = new File(path, "/Text Files");
		if (!directory.exists())
			directory.mkdirs();
		
		File highscore = new File(directory, "/highscore.txt");
		if (!highscore.exists())
		{
			highscore.createNewFile();
			return;
		}
		
		PrintWriter pw = new PrintWriter(highscore);
		pw.println(name1 + ":" + score1);
		pw.println(name2 + ":" + score2);
		pw.println(name3 + ":" + score3);
		pw.println(name4 + ":" + score4);
		pw.println(name5 + ":" + score5);
		pw.close();
		
	}
	/**
	 * clears high score
	 */
	public void clearHighscore()
	{
		name1 = "";
		name2 = "";
		name3 = "";
		name4 = "";
		name5 = "";
		score1 = 0;
		score2 = 0;
		score3 = 0;
		score4 = 0;
		score5 = 0;
	}
	/**
	 * getter for score
	 * @return scores in high score
	 */
	public int getScore1()
	{		return score1;	}
	/**
	 * getter for score
	 * @return scores in high score
	 */
	public int getScore2()
	{		return score2;	}
	/**
	 * getter for score
	 * @return scores in high score
	 */
	public int getScore3()
	{		return score3;	}
	/**
	 * getter for score
	 * @return scores in high score
	 */
	public int getScore4()
	{		return score4;	}
	/**
	 * getter for score
	 * @return scores in high score
	 */
	public int getScore5()
	{		return score5;	}
	/**
	 * getter for name
	 * @return name in high score
	 */
	public String getName1()
	{		return name1;	}
	/**
	 * getter for name
	 * @return name in high score
	 */
	public String getName2()
	{		return name2;	}
	/**
	 * getter for name
	 * @return name in high score
	 */
	public String getName3()
	{		return name3;	}
	/**
	 * getter for name
	 * @return name in high score
	 */
	public String getName4()
	{		return name4;	}
	/**
	 * getter for name
	 * @return name in high score
	 */
	public String getName5()
	{		return name5;	}

}




	
	

			
		

	
	

	
	
	
	

