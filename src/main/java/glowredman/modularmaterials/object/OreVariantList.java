package glowredman.modularmaterials.object;

import java.util.HashMap;

public class OreVariantList {
	
	public HashMap<String, OreVariant> oreTypes = new HashMap<String, OreVariant>();
	
	public OreVariantList() {
	}
	
	public HashMap<String, OreVariant> getOreVariants() {
		return oreTypes;
	}

}
