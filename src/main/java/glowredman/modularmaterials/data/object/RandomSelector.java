package glowredman.modularmaterials.data.object;

import java.util.Random;

import glowredman.modularmaterials.util.RandomXSTR;
import glowredman.modularmaterials.util.RandomXoshiro256StarStar;

public enum RandomSelector {
	Random(new Random()),
	XSTR(new RandomXSTR()),
	Xoshiro256StarStar(new RandomXoshiro256StarStar());
	
	private Random rand;
	
	private RandomSelector(Random rand) {
		this.rand = rand;
	}
	
	public Random get() {
		return rand;
	}

}
