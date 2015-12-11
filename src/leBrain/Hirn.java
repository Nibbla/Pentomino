package leBrain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;

import Pentomino.Board;
import Pentomino.Controller;
import Pentomino.Pentomino;
import Pentomino.PentominoMain;
import Pentomino.Square;
import Pentomino.Interfaces.Control;
import Pentomino.Interfaces.Display;
import Pentomino.Interfaces.TetrisGame;
import Pentomino.Interfaces.Control.Buttons;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Markus
 *	This class directly starts a game and plays it.
 *	It does this by directly manipulating the board.
 *	Experiments with Perceptron did not work sufficently.
 *	Frame Work for neurons and input and output notes and weights have been created.
 *  Every Neuron of the Inputnode is to be connected with one pixel of Display.
 *  This pixel has to be connected over several weights with each output Neuron. Those output neurons
 *  if firing press buttons of control or release otherwise.
 *  Implementing of a genetic algorithm would consume to much time,
 *  and was not fully completed.
 *  The adjusting of the weights was supposed to be done via an genetic algorithm.
 *  The idea of eroding the weights individually during Playtime would put the faktor age into display
 *  and widen up the search space. That combined with the Symboid could maybe achieve results.
 *  Choosing Phaenotype as an extension Class for Weights was a bad design choice due to code readability
 *  
 *   
 *  
 */
public class Hirn extends ArrayList<Neurons> implements KeyListener {
	public static class RandomM  extends Random{
		static HashMap<Long, Random> ttt  = new HashMap<Long, Random>();;
		public static boolean getTick(long val1) {
			Random ran0 = ttt.get(val1);
			if (ran0==null) ran0 = new Random(val1);
			ttt.put(val1, ran0);
			return ran0.nextBoolean();
		}

	}

	ArrayList<OutputNeuron> op;
	ArrayList<ColorNeuron> screenZeugs;
	ArrayList<InputNeuron> ip;
	Control control;
	private javax.swing.Timer outputTimer;
	private int weightCount;
	

	
	
	public Hirn(int i) {
		super(i);
	}

	public void addInput(final Control control, Canvas p) {
		this.control = control;
		//tetrisGame.addInputListener(inputChanged);
		ip = new ArrayList<InputNeuron>(Control.Buttons.values().length);
		for (int i = 0; i<Control.Buttons.values().length; i++){
			ip.add(new InputNeuron(Control.Buttons.values()[i]));
			
		}
		p.addKeyListener(this);


	   
		
	}
	
	protected int getWeightCount() {
		return weightCount;
	}

	public void addInputScreen(Board board, Display p) {
		screenZeugs = new ArrayList<ColorNeuron>(Control.Buttons.values().length);
		
		//Display d;
		p.refresh();
		Square[][] pixel = board.getFullBoard();
		for (Square[] coloumns : pixel) {
			for (Square actualPixel : coloumns){
				screenZeugs.add(new ColorNeuron(actualPixel));
			}
		}
			
		
	}
	
	public void refreshColorNeurons(Board board, Display p){
		
		//Display d;
		int count=0;
		p.refresh();
		Square[][] pixel = board.getFullBoard();
		for (Square[] coloumns : pixel) {
			for (Square actualPixel : coloumns){
				System.out.println("Red in NeuronPixel " + count+ ": "+actualPixel.getC().getRed());
				screenZeugs.get(count).setActualPixel(actualPixel);
				count++;
			}
		}
	}

	public void addOutput(TetrisGame tetrisGame) {
		
		op = new ArrayList<OutputNeuron>(Control.Buttons.values().length);
		for (int i = 0; i<Control.Buttons.values().length; i++){
			op.add(new OutputNeuron(Control.Buttons.values()[i]));
			
		}
		
		
	}

	public boolean isReadyToLearn() {
		if (ip!= null && screenZeugs.size()!=0 ) return true;
		return false;
	}

	public boolean hasInput() {
		if (ip.size()!= 0 && screenZeugs.size()!=0 ) return true;
		return false;
	}

	public void learn() {
		ActionListener screenChanged = new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		
		//tetrisGame.addScreenListener(screenChanged);
	}

	public void play( final PentominoMain p) {
		
		final HashMap<Long, Pheanotype> potential = new HashMap<Long,Pheanotype>();
				
		outputTimer = new javax.swing.Timer(500, new ActionListener() {
			
			private Long tick = MainRandom.getR().nextLong();
			
			public void actionPerformed(ActionEvent e) {
				if (control==null||p==null)return;
				see(p);
				
					// tick(String move)	
					//p.getGame().
					//System.out.println(op.get(i).getButton().toString()+": "+op.get(i).isFiring());
					int down = 0;
						//while (!p.getGame().tick().endsWith("puff")){
							Pentomino pentomino = p.getGame().getBoard().getLivingPentomino();
							if (pentomino==null)return;//continue;
							
							
						
							/*Comparator<Square[][]> c = new Comparator<Square[][]>(){
								@Override
								public int compare(Square[][] o1, Square[][] o2) {
									double pot1 = Square.calculatePotential(o1, 3, 2,1);
									double pot2 = Square.calculatePotential(o2, 3, 2,1);
									if (pot1>pot2)return 1;
									if (pot1==pot2)return 0;
									if (pot1<pot2)return -1;
									return 1;
								}
							};*/
							//tryMove();
							tryMove2(pentomino);
							try {
							    Thread.sleep(250);                 //1000 milliseconds is one second.
							} catch(InterruptedException ex) {
							    Thread.currentThread().interrupt();
							}
							p.getGame().tick("move");
							p.getGame().tick();
							/*
							ArrayList<Square> Goal = new ArrayList<Square>();
							
							int count = 0;
							Collections.sort(allPossibilities, c);
							do{
							
								Goal =  getBlack(allPossibilities.get(count++));
							}while(Goal.size() < 5);
							if (Goal.size() < 5) continue;
							for (int i = 0; i<Goal.size();i++) {
								
								p.getGame().getBoard().getLivingPentomino().getSquares()[i].setX(Goal.get(i).getX());
								p.getGame().getBoard().getLivingPentomino().getSquares()[i].setY(Goal.get(i).getY());
							}
							*/
							
							
						
							/*
							control.overRideButton(Buttons.Left, i>p.getRandom().nextInt(100));
							control.overRideButton(Buttons.Right, j>p.getRandom().nextInt(100));
							control.overRideButton(Buttons.RotateLeft, k>p.getRandom().nextInt(100));
							control.overRideButton(Buttons.Down, false);
							tick++;
						*/
							
							/*int[] pot3 = {Math.max(10, 60-down),Math.max(10, 10-down),Math.max(10, 70-down)};
							fineTunePotential(pot3,"fresh");
							int[] pot = {Math.max(10, 20-down),Math.max(10, 60-down),Math.max(10, 30-down)};
							fineTunePotential(pot,"boing");
							int[] pot2 = {Math.max(10, 20-down),Math.max(10, 80-down),Math.max(10, 50-down)};
							fineTunePotential(pot2,"bunk");
							*/
							if (!p.getBoard().isEndgame()){
								try {
								    Thread.sleep(250);                 //1000 milliseconds is one second.
								} catch(InterruptedException ex) {
								    Thread.currentThread().interrupt();
								}
								
							}else{
								PentominoMain.startNewGame(p,"nichts");
							}
							
							
						//}
							
					
					
				
					//System.out.println("The beauty of the board is:" + Square.calculatePotential(p.getBoard().getFullBoard()));
					//control.overRideButton(op.get(i).getButton(),op.get(i).isFiring());
					
				
				
				
			}

			private void tryMove2(Pentomino lp) {
				ArrayList<Square[][]> allPossibilities = getAllePossibleGoalPieces();
				ArrayList<Double> movePotential = new ArrayList<Double>();
				for (Square[][] boards : allPossibilities) {
					movePotential.add(Square.calculatePotential(boards, 10, 0, 0));
				}
				int bestMove = movePotential.indexOf(Collections.max(movePotential));
				
				p.getGame().getBoard().setFullBoard(allPossibilities.get(bestMove));
				
				p.getGame().getBoard().setLivingPentomino(null);
				
				/*
				int x = (bestMove)%5;
				bestMove=bestMove/5;
				int y = (bestMove)%15;
				int r =bestMove/15;
				
				x=0;
				y=14;
				
				
				control.overRideButton(Buttons.Left, false);
				control.overRideButton(Buttons.Right, false);
				control.overRideButton(Buttons.RotateLeft, false);
				control.overRideButton(Buttons.Down, false);
				p.getGame().tick("move");
				
				
				if (r!=0){
					control.overRideButton(Buttons.RotateLeft, true);
					return;
				}
				int[] newi = {y,x};
				newi = lp.getGeometricalTranslation(newi);
				lp.moveX(newi[1]);
				lp.moveY(newi[0]);
				/*
				if (lp.left(x)){
					control.overRideButton(Buttons.Right, true);
					return;
				}
				if (lp.completlyRight(x)){
					control.overRideButton(Buttons.Left, true);
					return;
				}
				if (lp.completlyAbove(y)){
					control.overRideButton(Buttons.Down, true);
					return;
				}
				*/
				
			}

			private void tryMove() {
				ArrayList<Double> movePossibilities = p.getGame().getBoard().getNextMovePieces(0, 2, 5);
				int bestMove = movePossibilities.indexOf(Collections.max(movePossibilities));
				control.overRideButton(Buttons.Left, false);
				control.overRideButton(Buttons.Right, false);
				control.overRideButton(Buttons.RotateLeft, false);
				control.overRideButton(Buttons.Down, false);
				p.getGame().tick("move");
				switch (bestMove) {
				case 0:
					control.overRideButton(Buttons.Right, true);
					break;
				case 1:
				control.overRideButton(Buttons.Left, true);
					break;
				case 2:
					control.overRideButton(Buttons.Down, true);
					break;
				case 3:
					control.overRideButton(Buttons.RotateLeft, true);
					
				default:
					break;
				}
				p.getGame().tick("move");
			}

			private int[] getFirstFreeBloock(Square[][] squares) {
				int[] coor = new int[2];  
				for (int i=squares.length-1;i>=0;i--){
					for (int j=0;j<squares[0].length;j++){
						if (squares[i][j].getC()==Color.GRAY){
						   coor[0]=i;
						   coor[1]=j;
						}
					}
				}
				return coor;
			}

			private ArrayList<Square> getBlack(Square[][] squares) {
				ArrayList<Square> s = new ArrayList<Square>();
				int count = 0;
				for (int i = 0;i<squares.length;i++){
					for (int j = 0;j<squares[0].length;j++){
						if (squares[i][j].getC().equals(Color.BLACK)) s.add(squares[i][j]);
					}
				}
				return s;
			}

			private ArrayList<Square[][]> getAllePossibleGoalPieces() {
				ArrayList<Square[][]> allPos = new ArrayList<Square[][]>();
				Pentomino lp = p.getGame().getBoard().getLivingPentomino();
				//int[] yx = getFirstFreeBloock(p.getGame().getBoard().getFullBoard());
				
				p.getGame().getBoard().moveLivingPentomino(0, 3);
				for (int i = 0; i<4;i++){ //4 possible rotations
					
					
						for (int x = 0; x<5;x++){ //5 collumns
							yLoop:for (int y = 14; y>=0;y--){ //15 row
							int[] cordinates = {y,x};
							cordinates = lp.getGeometricalTranslation(cordinates);
							
							p.getGame().getBoard().moveLivingPentomino(cordinates[0], cordinates[1]);
							Square[][] s =p.getGame().getBoard().getFullBoardShadow();
							allPos.add(s); //
							
							p.getGame().getBoard().moveLivingPentomino(-cordinates[0], -cordinates[1]);
							if (s!=null) continue yLoop;
							}
						
						}
					
					lp.rotate(false);
					
				}
				//p.getGame().getBoard().moveLivingPentomino(-deltaY, -deltaX);
				return allPos;
			}

			private void fineTunePotential(int[] pot, String string) {
				int tries = 0;
				while (tries++<1&&!p.getGame().tick("move").endsWith(string)){
					selectMove(pot[0],pot[1],pot[2], potential,tick);
					
				}
				
			}
		});
		outputTimer.start();
		
	}

	

	

	

	protected void selectMove(int i, int j, int k, HashMap<Long,Pheanotype> potential, Long tick) {
		Pheanotype p = potential.get(tick++%8);
		if (p == null) {
			--tick;
			p= new Pheanotype(tick%8);
			potential.put(tick%8,p);
			tick++;
		}
		control.overRideButton(Buttons.Left, i>p.getRandom().nextInt(100));
		control.overRideButton(Buttons.Right, j>p.getRandom().nextInt(100));
		control.overRideButton(Buttons.RotateLeft, k>p.getRandom().nextInt(100));
		control.overRideButton(Buttons.Down, false);
		
		
	}

	public void keyTyped(KeyEvent e) {
		
		
	}

	public void keyPressed(KeyEvent e) {
		if (ip==null)return;
		for (int i = 0; i<ip.size(); i++){
			if (control.isButtonPressed(Control.Buttons.values()[i])){
				ip.get(i).setOn();
			}
		}
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	

	public void addWeights() {
				ArrayList<ColorNeuron> AllInput = this.screenZeugs;
				ArrayList<OutputNeuron> OutPut = this.op;
				
				addWeights(AllInput,new Pheanotype(3,6,1f,0.8f,0.5f),OutPut);
				
	}
	
	public void addWeights(Pheanotype pheanotype) {
		ArrayList<ColorNeuron> AllInput = this.screenZeugs;
		ArrayList<OutputNeuron> OutPut = this.op;
		addWeights(AllInput,pheanotype,OutPut);
		
}


	

	private void addWeights(ArrayList<ColorNeuron> allInput,
			Pheanotype pheanotype, ArrayList<OutputNeuron> outPut) {
		
		for (OutputNeuron outputNeuron : outPut) {
			for (ColorNeuron intNeuron : allInput) {
				for (int i = 0; i<pheanotype.getNumberOfConnectionsBewtweenTwoNodes();i++){
					ArrayList<Weights> newSet = pheanotype.getNewSetOfWeights();
					intNeuron.addAll(newSet);
					outputNeuron.addAll(newSet);
					weightCount++;
				}
				
			}
		}
		
	}
	
	
	/**messy?
	 * @param allInput
	 * @param pheanotype
	 * @param outPut
	 * @return thisObjectShouldBeTheSameAs allInput and outPut nodes in Elements of class Hirn
	 */
	private ArrayList<Neurons> addWeightsAndStoreTheBrain(ArrayList<InputNeuron> allInput,
			Pheanotype pheanotype, ArrayList<OutputNeuron> outPut) {
		ArrayList<Neurons> allTheBrainReadyForFire = new ArrayList<Neurons>(allInput.size()*outPut.size());
		for (OutputNeuron outputNeuron : outPut) {
			for (InputNeuron intNeuron : allInput) {
				//intNeuron.add(pheanotype);
				//outputNeuron.add(pheanotype);
				allTheBrainReadyForFire.add(intNeuron);
				allTheBrainReadyForFire.add(outputNeuron);
			}
		}
		return allTheBrainReadyForFire;
	}

	public void see(PentominoMain p) {
		int count =0;
		refreshColorNeurons(p.getGame().getBoard(),p);
		
		for (ColorNeuron screenNode : screenZeugs) {
			screenNode.pull();
			 System.out.print(screenNode.getValue() +" ");
		}
		//even there are values above.
		//those are overwritten below.
		for (ColorNeuron screenNode : screenZeugs) {
			 System.out.print(screenNode.getValue() +" ");
			 		
			 count++;
			if (count%5==0) System.out.println();
		}
		
		
		
		
	}

	public void work(final PentominoMain p) {
		outputTimer = new javax.swing.Timer(100, new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				if (control==null||p==null)return;
				//see(p);
				Board[] choices = new Board[20];
				
				for (int i = 0; i < op.size(); i++) {
					
					System.out.println(op.get(i).getButton().toString()+": "+op.get(i).isFiring());
					//System.out.println("The beauty of the board is:" + Square.calculatePotential(p.getBoard().getFullBoard(), i, i, i));
					control.overRideButton(op.get(i).getButton(),op.get(i).isFiring());
					
				}
				
				
			}
		});
		outputTimer.start();
	}

	public void addActivationFunction(Pheanotype pheanotype) {
		for (int i = 0; i < op.size(); i++) {
			op.get(i).addActivationFunction(pheanotype.getActivationFunction());
		}
		
	}
	
	
}
