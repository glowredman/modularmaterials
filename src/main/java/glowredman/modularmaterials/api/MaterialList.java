package glowredman.modularmaterials.api;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class MaterialList {
	
	public static LinkedHashMap<String, Material> materials = new LinkedHashMap<String, Material>();
	public static HashMap<Integer, String> idMapping = new HashMap<Integer, String>(); 
	public static HashMap<String, String> types = new HashMap<String, String>();
	
	public static Iterator getIterator(HashMap map) {
		return map.entrySet().iterator();
	}
	
	public void initMaterialList() {
		Material material = new Material();
		material.disabled = false;
		material.enabledTypes = new Material().getAllTypesEqualMashMap(true);
		material.meta = 0;
		material.name = "null";
		materials.put("null", material);
	}

}
