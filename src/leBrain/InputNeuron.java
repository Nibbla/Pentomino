package leBrain;
import java.util.ArrayList;

import Pentomino.Interfaces.Control.Buttons;


public class InputNeuron extends Neurons {

	private Buttons button;
	

	public InputNeuron(Buttons buttons) {
		this.button = buttons;
	}
	
	public String toString(){
		return button.name();
		
	}

	

	@Override
	public void add(Weights weights) {
		this.weightsInput.add(weights);
		
	}
	
	@Override
	public void addAll(ArrayList<Weights> weights) {
		this.weightsInput.addAll(weights);
	}

	@Override
	public ArrayList<Weights> getWeight() {
		return weightsInput;
		
		//return this.weightsInput;
	}

	@Override
	public void pull() {
			
			for (Weights weights : this.weightsInput) {
				weights.pull(1);
			}

		
	}

	@Override
	public float getValue() {
		
		float value = 0;
		for (Weights w : weightsOutput) {
			value+=w.getValue();
		}
		
		return value;
	}

	
}
