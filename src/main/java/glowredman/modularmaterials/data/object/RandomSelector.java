package glowredman.modularmaterials.data.object;

import java.util.Random;
import java.util.function.Function;

import glowredman.modularmaterials.util.RandomXSTR;
import glowredman.modularmaterials.util.RandomXoshiro256StarStar;

public enum RandomSelector {
	Standard(seed -> new Random(seed)),
	XSTR(seed -> new RandomXSTR(seed)),
	Xoshiro256StarStar(seed -> new RandomXoshiro256StarStar(seed));
	
	private Function<Long, Random> rand;
	
	private RandomSelector(Function<Long, Random> rand) {
		this.rand = rand;
	}
	
	public Function<Long, Random> get() {
		return rand;
	}

}
