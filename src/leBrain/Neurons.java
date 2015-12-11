package leBrain;
import java.util.ArrayList;

//next Time make blub extend Neurons
public abstract class Neurons {
	ArrayList<Weights> weightsInput = new ArrayList<Weights>(2);
	ArrayList<Weights> weightsOutput = new ArrayList<Weights>(2);; 
	
	

	public void setOn(){
		System.out.println("Brain:");
		System.out.println(this.toString());
	}

	public abstract void pull();
	public abstract void add(Weights weights);
	public abstract void addAll(ArrayList<Weights> weights);
	public abstract ArrayList<Weights> getWeight();

	public abstract float getValue();

	
}
