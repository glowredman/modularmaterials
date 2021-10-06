package glowredman.modularmaterials.data.object;

import java.util.Random;

import glowredman.modularmaterials.util.RandomXSTR;
import glowredman.modularmaterials.util.RandomXoshiro256StarStar;

public enum RandomSelector {
	Standard(new Random()),
	XSTR(new RandomXSTR()),
	Xoshiro256StarStar(new RandomXoshiro256StarStar());
	
	private Random rand;
	
	private RandomSelector(Random rand) {
		this.rand = rand;
	}
	
	public static Random get(String name) {
		return valueOf(name).rand;
	}

}
