package glowredman.modularmaterials.object;

import java.util.HashMap;

public class OreVeinList {
	
	public HashMap<String, OreVein> oreGeneration = new HashMap<String, OreVein>();

	public HashMap<String, OreVein> getOreGeneration() {
		return oreGeneration;
	}

	public void setOreGeneration(HashMap<String, OreVein> oreGeneration) {
		this.oreGeneration = oreGeneration;
	}

}
