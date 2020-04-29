package glowredman.modularmaterials.material;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class MaterialList {
	
	public static LinkedHashMap<String, Material> materials = new LinkedHashMap<String, Material>();
	
	public static Iterator getIterator(HashMap map) {
		return map.entrySet().iterator();
	}

}
