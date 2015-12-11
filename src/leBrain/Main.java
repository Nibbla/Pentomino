package leBrain;
import java.util.ArrayList;

import Pentomino.Controller;
import Pentomino.PentominoMain;
import Pentomino.Interfaces.Control;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean learn = true;
		boolean dream = false;
		boolean feedBackLoop = false;
		boolean outPut = true;
		
		int x = 5;
		int y = 15;
		
		
		
		PentominoMain.main(null);
		PentominoMain p = PentominoMain.getInstance();
		PentominoMain.startNewGame(p, "spezial");
		PentominoMain.bot = true;
		Hirn hirn = new Hirn(x*y+4+2+1); //Zahl der Neuronen an einer Ebene		
		hirn.addInput(p.getController(), p);	
		hirn.addInputScreen(p.getGame().getBoard(),p);		
		hirn.addOutput(p.getGame());	
		
		boolean insectBrain = true;
		boolean boringBot = false;
		if (insectBrain){
			long seed = 66776677L;
			Pheanotype pheanotype = new Pheanotype(seed);
			hirn.addWeights(pheanotype);//accesses inputAndOutputNodes
			hirn.addActivationFunction(pheanotype);//accesses inputAndOutputNodes
			System.out.println("Hirn initalisiert. " + hirn.getWeightCount() + " Weights hinzugefügt" );
			hirn.play(p);
			
			
		}
		
		
		if (boringBot){
			
			hirn.work(p);
			
		}
	}

	

	



	private static void dream(Hirn hirn) {
		// TODO Auto-generated method stub
		
	}

	private static void learn(ArrayList<Neurons> hirn) {
		// TODO Auto-generated method stub
		
	}

}
