package leBrain;



public class ActivationFunction {
	private float value;

	public ActivationFunction(Functiontype stepfunction, float f) {
		this.value = f;
	}

	public enum Functiontype {
		STEPFUNCTION;

		
	}

	public boolean isFiring(float value) {
		if (value>=this.value)return true;
		return false;
		
	}
}
