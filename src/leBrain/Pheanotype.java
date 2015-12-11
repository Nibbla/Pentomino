package leBrain;
import java.util.ArrayList;
import java.util.Random;




public class Pheanotype extends Weights{
	ArrayList<Weights> storage; //static would be interesting
	Genotype genotype;
	//private int numberOfCLayers;
	private int numberOfConnections;
	private ActivationFunction activationFunction;
	
	
	
	protected ActivationFunction getActivationFunction() {
		return activationFunction;
	}

	public ArrayList<Weights> getNewSetOfWeights(){
		storage = new ArrayList<Weights>(numberOfConnections);		
		for(int i=0;i<numberOfConnections;i++){	
			storage.add(new Weights(startingValue,degradationValue, numberOfCs, MainRandom.getR()));
		}
		
		return storage;
	}
	
	public Weights get(){
		return this;
	}
	
	
	/** weights are waiting in storage
	 * @param numberOfCs
	 * @param startingValue
	 * @param degradingValue
	 * @param activationValue 
	 */
	public Pheanotype(int numberCLayers, int numberOfCs,float startingValue,float degradingValue, float activationValue){
		super(startingValue, degradingValue,numberOfCs, MainRandom.getR());
		this.numberOfConnections = numberCLayers;
		storage = new ArrayList<Weights>();
		activationFunction = new ActivationFunction(ActivationFunction.Functiontype.STEPFUNCTION, activationValue); 
		for(int i=0;i<numberCLayers;i++){	
			storage.add(new Weights(startingValue, degradingValue,numberOfCs, MainRandom.getR()));
		}
		
	}
	
	public Pheanotype(int numberOfCLayers255, int numberOfCperWeight255, int startValue255, int degrationValue15, int stepLevel15){
		this(val255ToInt(numberOfCLayers255,1,3),val255ToInt(numberOfCperWeight255,1,5), val255ToFloat(startValue255), val15ToFloat(degrationValue15),val15ToFloat(stepLevel15));
		
		
		
	}

	public Pheanotype(long seed) {
		this(new Genotype(seed));	
			
	}


	public Pheanotype(Genotype genotype) {
		this(genotype.lettersToValue(0, 1), genotype.lettersToValue(2, 3),genotype.lettersToValue(4, 5),genotype.lettersToValue(6, 6),genotype.lettersToValue(7, 7));
		this.genotype = genotype;
	}

	public int getNumberOfConnectionsBewtweenTwoNodes() {
		
		return numberOfConnections;
	}
	
	public static int val255ToInt(int val, int min, int max){
		int d = max - min;
		int i = Math.round(min+d/255*val);
		return i;
	}
	
	public static float val255ToFloat(int val255){
		float f = val255/255.f; 
		return f;
	}public static float val15ToFloat(int val15){
		float f = val15/15.f; 
		return f;
	}

	public Random getRandom() {
		
		return MainRandom.getR();
	}
}
