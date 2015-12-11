package leBrain;
import java.awt.Color;
import java.util.ArrayList;

import Pentomino.Square;


public class ColorNeuron extends Neurons {

	private Square actualPixel;

	
	public ColorNeuron(Square actualPixel) {
		this.actualPixel = actualPixel;
	}

	@Override
	public void add(Weights weights) {
		this.weightsInput.add(weights);
		
	}
	
	@Override
	public void addAll(ArrayList<Weights> weights) {
		this.weightsInput.addAll(weights);
		
	}
	
	protected void setActualPixel(Square actualPixel) {
		this.actualPixel = actualPixel;
	}


	public void pull() {
		Color pixel = actualPixel.getC();
		float[] hsb = Color.RGBtoHSB(pixel.getRed(), pixel.getGreen(), pixel.getBlue(), null);
		if (hsb[0]!=0.0){
			System.out.println("red");
		}
		for (Weights weights : this.weightsInput) {
			weights.pull(hsb[0]);
			//weights.pull(hsb[2]);
		}
		return;
		
	}
	
	
	@Override
	public float getValue() {
		float value = 0;
		for (Weights w : weightsInput) {
			value+=w.getValue();
		}
		
		return value/weightsInput.size();
	}

	@Override
	public ArrayList<Weights> getWeight() {
		
		return this.weightsInput;
	}

	

	
	
	
	

}
