package glowredman.modularmaterials.material;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class MaterialList {
	
	public LinkedHashMap<String, Material> materials = new LinkedHashMap<String, Material>();
	
	public LinkedHashMap<String, Material> getMaterials() {
		return this.materials;
	}

}
