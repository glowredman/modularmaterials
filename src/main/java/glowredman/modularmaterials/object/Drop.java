package glowredman.modularmaterials.object;

import java.util.HashMap;

import javax.annotation.Nonnegative;

public class Drop {
	
	public String mode;
	public String item;
	@Nonnegative
	public int amount;
	@Nonnegative
	public int n;
	@Nonnegative
	public float p;
	@Nonnegative
	public int min;
	@Nonnegative
	public int max;
	public HashMap<Integer, Float> table = new HashMap<Integer, Float>();
	
	public float getProbability(int fortune) {
		if(table.containsKey(fortune)) {
			return table.get(fortune);
		} else if(table.isEmpty()) {
			return 0;
		} else {
			if(fortune == 0) {
				//gets the smallest key (i.e. the closest to 0)
				int least = -1;
				for(int i : table.keySet()) {
					least = least == -1 ? i : least;
					least = least > i ? i : least;
				}
				return table.get(least);
			} else {
				//gets the highest key, that is smaller than fortune
				int carry = -1;
				for(int i : table.keySet()) {
					carry = carry == -1 ? i : carry;
					carry = i < fortune && carry < i ? i : carry;
				}
				return table.get(carry);
			}
		}
	}

}
