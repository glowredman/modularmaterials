package glowredman.modularmaterials.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OreVeinList {
	
	public List<String> asteroidDims = new ArrayList<String>();
	public HashMap<String, OreVein> oreGeneration = new HashMap<String, OreVein>();

	public HashMap<String, OreVein> getOreGeneration() {
		return oreGeneration;
	}

	public void setOreGeneration(HashMap<String, OreVein> oreGeneration) {
		this.oreGeneration = oreGeneration;
	}

}
