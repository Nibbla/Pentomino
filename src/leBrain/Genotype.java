package leBrain;
import java.util.ArrayList;


public class Genotype {
	
	
	ArrayList<Long> letters;
	private long seed = 0;
	
	
	public long getSeed(){
		return seed;
		
	}
	
	public Genotype(long seed) {
		this.seed  = seed;
		
		letters = new ArrayList<Long>(16);
		while(seed>15){
			letters.add(seed%16);
			seed=seed/16;
		}
		letters.add(seed);
		while (letters.size()<8){
			letters.add(0, 0L);
		}
	}		
	
	private static final char[] hex = {
	    '0', '1', '2', '3', '4', '5', '6', '7', 
	    '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	
	
	private static char[] zero_255toHex(int i){
		
	    char a = hex[i >>> 4];
	    char b = hex[i & 15];
	    char[] c = {a,b};
		return c;
	    
	}
	
	
	
	/**
	 * @author Formula Vera
	 * @param letters
	 * @param i
	 * @param j
	 * @return
	 */
	public int lettersToValue(int inclusiveLeft, int inclusiveRight) {
		int value = 0;
		int count = 0;
		for (int i = inclusiveRight; i>= inclusiveLeft;i--){
		   value += letters.get(i)*(Math.pow(16, count++));
		}
	   
	   return value;
		
	}
	
	


	
	

	
	

}
