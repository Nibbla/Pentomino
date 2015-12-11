package leBrain;
import java.util.Random;


public class Weights{
	
	float startingValue;
	float degradationValue;
	private float actualValue;
	protected int numberOfCs;
	float value;
	
	
	public Weights(float startingValue, float degradationValue, int numberOfCs, Random r) {
		this.numberOfCs = numberOfCs;
		this.startingValue = startingValue;
		this.degradationValue = degradationValue;
		float someValue = this.startingValue;
		for (int i = 0; i<numberOfCs;i++){
			someValue -= degradationValue*r.nextFloat();
			actualValue += Math.abs(someValue);
		}
		
	}
	
	public void pull(float i) {
		value = actualValue*i;
	}
	
	protected float getValue() {
		return value;
	}
	
	

}
