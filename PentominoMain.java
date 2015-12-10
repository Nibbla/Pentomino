package Pentomino;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import javax.swing.*;


import Pentomino.Interfaces.Control;
import Pentomino.Interfaces.Display;
import Pentomino.Interfaces.TetrisGame;

public class PentominoMain extends Canvas implements Runnable,Display{
	
	protected static final int WIDTH=500, HEIGHT=750;
	
	protected TetrisGame game;
	private Controller controller;
	private static Board board;
	private int endgamecount;
	private Timer timerEndgame;
	private boolean endGame2;
	private static PentominoMain pm ;
	
	protected static JLabel scoreLabel2 = new JLabel();

	/**
	 * the main method where the main JFrame is created and most methods are called
	 * @param args
	 */
	public static void main(String[] args){
		
		pm = new PentominoMain();
		pm.controller = new Controller();
		final JFrame frame = new JFrame("Pentomino");
		
		frame.setSize(WIDTH,HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		
		KeyGetter.loadKeys();
		try {
			Configuration.loadConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JMenuBar bar = new JMenuBar();
		bar.setBounds(0, 0, WIDTH, 25);
		
		JMenu file = new JMenu("File");
		file.setBounds(0,0,45,24);
		
		JMenuItem newGame = new JMenuItem("New Game");
		
		newGame.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				startNewGame(pm,"normal");
				System.out.println("Starting New Game...");
			}
		});
		
		JMenuItem optimalSolution = new JMenuItem("Optimal Solution");
		
		optimalSolution.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startNewGame(pm, "special");
				System.out.println("Starting optimal solution...");
			}
		});
		
		JMenuItem highScore = new JMenuItem("High Score");
		highScore.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				
				final JFrame alert = new JFrame("High Score");
				alert.setSize(250, 300);
				alert.setLayout(null);
				alert.setLocationRelativeTo(null);
				alert.setAlwaysOnTop(true);
				
				/*JLabel score = new JLabel();
				String scoreText = "The high scores are: \n" + board.score.getName1() + ":   " + board.score.getScore1();
				scoreText += "\n" + board.score.getName2() + ":   " + board.score.getScore2();
				scoreText += "\n" + board.score.getName3() + ":   " + board.score.getScore3();
				scoreText += "\n" + board.score.getName4() + ":   " + board.score.getScore4();
				scoreText += "\n" + board.score.getName5() + ":   " + board.score.getScore5();
				score.setBounds(0,0,100,300);
				score.setText(scoreText);
				*/
				
				alert.setLayout(new GridLayout(7,1));
				
				
				
				
				JLabel label1 = new JLabel("The high scores are: \n" );
				label1.setBounds(50, 0, 100, 25);
				alert.add(label1);
				
				JLabel label2 = new JLabel(board.score.getName1() + ":   " + board.score.getScore1());
				JLabel label3 = new JLabel(board.score.getName2() + ":   " + board.score.getScore2());
				JLabel label4 = new JLabel(board.score.getName3() + ":   " + board.score.getScore3());
				JLabel label5 = new JLabel(board.score.getName4() + ":   " + board.score.getScore4());
				JLabel label6 = new JLabel(board.score.getName5() + ":   " + board.score.getScore5());
				
				alert.add(label2);//.setBounds(0, 25, 100, 25));
				alert.add(label3);//.setBounds(0, 50, 100, 25));
				alert.add(label4);//.setBounds(0, 75, 100, 25));
				alert.add(label5);//.setBounds(0, 100, 100, 25));
				alert.add(label6);//.setBounds(0, 125, 100, 25));
				
				JButton okayButton = new JButton("Okay");
				okayButton.setBounds(50, 120, 100, 30);
				okayButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						alert.dispose();
					}
				});
				
				//alert.add(score);
				
				alert.add(okayButton);
				alert.setResizable(false);
				alert.setVisible(true);
			}			
		});
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Closing...");
				System.exit(0);
			}
		});
		
		JMenuItem options = new JMenuItem("Options");
		options.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Configuration.openConfig(frame);
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBounds(250, 0, 250, 750);
		panel.setLayout(new GridLayout(3,1));
		
		JLabel scoreLabel = new JLabel();
		scoreLabel.setText("                            Your score is:                     " );
		
		
		JPanel smallPanel = new JPanel();
		smallPanel.setLayout(new GridLayout(2,1));
		smallPanel.add(scoreLabel);
		smallPanel.add(scoreLabel2);
		panel.add(smallPanel);
		
		pm.setBounds(0, 25, WIDTH, HEIGHT-25);
		
		frame.add(pm, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.EAST);
		bar.add(file);
		file.add(newGame);
		file.add(optimalSolution);
		file.add(highScore);
		file.add(options);
		file.add(exit);
		frame.add(bar, BorderLayout.NORTH);
		frame.setVisible(true);
		
		

		pm.start();
		// scoreLabel2.setText("                    "+board.getcurrentScore());
		
	}
	
	/**
	 * starts a new game when "new game" or "optimal solution" is clicked
	 * @param pm Pentomino Main interface parameter
	 */
	protected static void startNewGame(final PentominoMain pm, String special) {
		pm.game = new Game((Control)pm.controller, (Display)pm, null);
		//Pentomino.counter=0;
		pm.endgamecount=0;
		pm.endGame2 = false;
		if (pm.timerEndgame != null) pm.timerEndgame.stop();
		pm.timerEndgame = new Timer(1000/60, new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				pm.endgamecount++;
				//System.out.println(pm.endgamecount);
				
			}
		});
		if (special.equals("special"))
			Pentomino.special=true;
		else 
			Pentomino.special=false;
		
		pm.game.start();
	}

	/**
	 * starts the timer
	 */
	public void start() {
		Thread t = new Thread(this);
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();
	}
	/**
	 * does the rendering
	 */
	public void run(){
		boolean running = true;
		initialize();
		while( running ){
			update();
			BufferStrategy buf = getBufferStrategy();
			if (buf == null){
				createBufferStrategy(3);
				continue;
			}
			Graphics2D g = (Graphics2D) buf.getDrawGraphics();
			render(g);
			buf.show();
		}
	}
	
	public void update(){
		
	}
	/**
	 * initializes controller
	 */
	public void initialize(){
		
		this.addKeyListener(controller);
		requestFocus();
		
	}
	/**
	 * draws the welcome screen and calls the method to draw the board
	 * @param g the graphics 
	 */
	public void render(Graphics2D g){
	if (board==null){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.MAGENTA);
		g.setFont(new Font("Calibri", Font.PLAIN, 28));
		g.drawString("Pentomino", 95, 250);
		}else{
		drawBoard(g,WIDTH,HEIGHT-25,board);
		}
	}

	/**
	 * draws the board
	 * @param g the graphics 
	 * @param width2 the width
	 * @param height2 the height
	 * @param board2 the board
	 */
	private void drawBoard(Graphics2D g, int width2, int height2, Board board2) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, width2, height2);
		width2 = pm.getWidth();
		height2 = pm.getHeight()-30;
		if (board2==null)return;
		Square[][] s = board2.getFullBoard();
		
		int sH = s.length;
		int sW = s[0].length;
		int squareWidth = width2/sW; 
		int squareHeight = height2/sH;
		
		for (int i = 0; i<sW;i++){
			for (int j = 0; j <sH;j++){
				g.setColor(s[j][i].getC());
				g.fillRect(squareWidth*i, squareHeight*j, squareWidth, squareHeight);
				g.setColor(Color.BLACK);
				g.drawRect(squareWidth*i, squareHeight*j, squareWidth, squareHeight);				
			}
		}
		
		Pentomino p = board2.getLivingPentomino();
		if (p==null) return;
		Square[] ps= p.getSquares();
		
		for (int i = 0; i<ps.length;i++){
			g.setColor(ps[i].getC());
			g.fillRect(squareWidth*ps[i].getX(), squareHeight*ps[i].getY(), squareWidth, squareHeight);
			g.setColor(Color.BLACK);
			g.drawRect(squareWidth*ps[i].getX(), squareHeight*ps[i].getY(), squareWidth, squareHeight);	
		}
		if (board2.isEndgame()){
			
			timerEndgame.start();
			drawEndgame(g, width2, height2, board2);
		}
	}

	/**
	 * draws the ending animation, the rainbow
	 * @param g the graphics
	 * @param width2 the width of the board
	 * @param height2 the height of the board
	 * @param board2 the board
	 */
	private void drawEndgame(Graphics2D g, int width2, int height2, Board board2) {
		Square[][] s = board2.getFullBoard();
		int count = 0;
		
		int sH = s.length;
		int sW = s[0].length;

		int squareWidth = width2/sW; 
		int squareHeight = height2/sH;
		int squares = s.length*s[0].length;
		
		for (int j = sH-1; j >=0;j--){
			for (int i = sW-1; i>=0;i--){
				count++;
				Color m = ColorE.colorM();
				if(endgamecount>squares){
					endgamecount=0;
					endGame2=true;
				}
				if(endGame2!=true){
				if (s[j][i].getC().equals(Color.GRAY)){
					//System.out.println("something");
					
					if (count++<endgamecount){
						s[j][i].setC(m);
						return;
					}
					
					//g.setColor(m);
					
				/*g.fillRect(squareWidth*i, squareHeight*j, squareWidth, squareHeight);
				g.setColor(Color.BLACK);
				g.drawRect(squareWidth*i, squareHeight*j, squareWidth, squareHeight);		*/	
				}else{
					//System.out.println(endgamecount + "notsomething");
				}
				
				}else{
					if (!s[j][i].getC().equals(Color.GRAY)){
						//System.out.println("engame2");
						
						if (count++<endgamecount){
							s[j][i].setC(Color.GRAY);
							return;
						}
					}
				}
			}
		}
		
		Pentomino p = board2.getLivingPentomino();
		if (p==null) return;
		Square[] ps= p.getSquares();
		if (endGame2){
			
			 //board2.livingPentomino=null;
			
		}else{
		for (int i = 0; i<ps.length;i++){
			g.setColor(ps[i].getC());
			g.fillRect(squareWidth*ps[i].getX(), squareHeight*ps[i].getY(), squareWidth, squareHeight);
			g.setColor(Color.BLACK);
			g.drawRect(squareWidth*ps[i].getX(), squareHeight*ps[i].getY(), squareWidth, squareHeight);	
		}
		}
		
		
	}

	/**
	 * sets the board
	 */
	public void setData(Board b) {
		this.board = b;
		
	}

	
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * getter for the display interface
	 * @return the interface parameter
	 */
	public static Display getDisplayInstance() {
		
		return pm;
	}
	/**
	 * getter for the pentomino main instance
	 * @return the interface parameter
	 */
	public static PentominoMain getInstance() {
		
		return pm;
	}
	public void setColorMode(String[] args) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * closes the program
	 */
	public void close() {
		System.out.println("Closing...");
		System.exit(0);
	  
		
	}

	/**
	 * getter for the controller
	 * @return the controller
	 */
	public Control getController() {
		
		return controller;
	}

	/**
	 * getter for the board
	 * @return the board
	 */
	public Board getBoard() {
		return this.board;
		
	}
	/**
	 * getter for the game
	 * @return game interface
	 */
	public TetrisGame getGame() {
		
		return game;
	}

}