package Pentomino;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.JOptionPane;
public class Highscore
{
	private static int score;
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

	public Highscore(int score){
		this.score=score;
	}
	public int getScore1(){
		return score1;
	}
	public int getScore2(){
		return score2;
	}
	public int getScore3(){
		return score3;
	}
	public int getScore4(){
		return score4;
	}
	public int getScore5(){
		return score5;
	}
	
	public String getName1(){
		return name1;
	}
	public String getName2(){
		return name2;
	}
	public String getName3(){
		return name3;
	}
	public String getName4(){
		return name4;
	}
	public String getName5(){
		return name5;
	}
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
	
	public void updateHighscore(int score)
	{
		this.score=score;
		
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
			try {
				saveHighscore();
			} catch (Exception e) {
				System.out.println("THERE IS AN EXCEPTION FOR SAVE HIGHSCORE!!!!!!!!");
				e.printStackTrace();
			}
		}
	}
 	public int score(int score, long lines) 
	{	
 		this.score= score;
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
	
	public void loadHighscore() throws Exception
	{
		String path = Paths.get(".").toAbsolutePath().normalize().toString();
		File directory = new File(path, "/Pentomino");
		if (!directory.exists())
		{
			directory.mkdirs();
		}
		File highscore = new File(directory, "/highscore.txt");
		if (!highscore.exists())
		{
			highscore.createNewFile();
			return;
		}
		
		Scanner in = new Scanner(highscore);
		String s=in.nextLine();
			String entries1[] = s.split(": ");			
			name1 = entries1[0];
			score1= Integer.parseInt(entries1[1]);
			
			String entries2[] = in.nextLine().split(": ");
			name2 = entries2[0];
			score2= Integer.parseInt(entries2[1]);
			
			String entries3[] = in.nextLine().split(": ");
			name3 = entries3[0];
			score3= Integer.parseInt(entries3[1]);
			
			String entries4[] = in.nextLine().split(": ");
			name4 = entries4[0];
			score4= Integer.parseInt(entries4[1]);
			
			String entries5[] = in.nextLine().split(": ");		
			name5 = entries5[0];
			score5= Integer.parseInt(entries5[1]);
		
		in.close();
	}
		
	public static void saveHighscore() throws Exception
	{
		String path = Paths.get(".").toAbsolutePath().normalize().toString();
			
		File directory = new File(path, "/Text Files");
		if (!directory.exists())
		{
			directory.mkdirs();
		}
		File highscore = new File(directory, "/highscore.txt");
		if (!highscore.exists())
		{
			highscore.createNewFile();
			return;
		}
		
		PrintWriter pw = new PrintWriter(highscore);
		pw.println(name1 + ": " + score1);
		pw.println(name2 + ": " + score2);
		pw.println(name3 + ": " + score3);
		pw.println(name4 + ": " + score4);
		pw.println(name5 + ": " + score5);
		pw.close();
		
	}
	
/*	public static void init() 
	{
		try {
			saveHighscore();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		try {
			loadHighscore();
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
}




	
	

			
		

	
	

	
	
	
	

