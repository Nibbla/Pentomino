package leBrain;
import java.util.ArrayList;
import java.util.Random;


import Pentomino.Interfaces.Control.Buttons;


public class OutputNeuron extends Neurons{

	private Buttons button;
	private ActivationFunction af;
	
	
	protected Buttons getButton() {
		
		return button;
	}

	public OutputNeuron(Buttons buttons) {
		this.button = buttons;
	}
	
	public boolean isFiring(){
		float value = 0;
		for (int i = 0; i<weightsOutput.size();i++){
			value+= weightsOutput.get(i).getValue();
		}
		value /= weightsOutput.size();
		System.out.println("someNeuronValue: " + value);
		if (af==null) af = new ActivationFunction(ActivationFunction.Functiontype.STEPFUNCTION,0.5f);
		//sigmoid
		//Random r = new Random();
		
		return af.isFiring(value);
	}

	public void add(Weights weights) {
		this.weightsOutput.add(weights);
	}
	
	@Override
	public float getValue() {
		float value = 0;
		for (Weights w : weightsOutput) {
			value+=w.getValue();
		}
		value /= weightsOutput.size();
		return value;
	}

	public void pull() {
		
		for (Weights weights : this.weightsOutput) {
			weights.pull(1);
		}
		
		
	}
	@Override
	public ArrayList<Weights> getWeight() {
		
		return this.weightsOutput;
	}

	@Override
	public void addAll(ArrayList<Weights> weights) {
			this.weightsOutput.addAll(weights);
			
		
		
	}

	public void addActivationFunction(ActivationFunction activationFunction) {
		af = activationFunction;
		
	}

}
