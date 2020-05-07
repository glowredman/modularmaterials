package glowredman.modularmaterials.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.Reference;
import glowredman.modularmaterials.object.Material;

import static glowredman.modularmaterials.Reference.*;

public class MaterialHandler {
	
	public static void createIDMapping() {
		long time = System.currentTimeMillis();
		Iterator i = getIterator(materials);
		while(i.hasNext()) {
			Entry<String, Material> entry = (Entry<String, Material>) i.next();
			idMapping.put(entry.getValue().getMeta(), entry.getKey());
		}
		Main.logger.info("Finished mapping a total of " + idMapping.size() + " materials to their meta-values. Took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	public static void fillMaterialListIfEmpty() {
		if(materials.isEmpty()) {
			Material material = new Material();
			material.setDisabled(true);
			material.setEnabledTypes(getAllTypesEqualHashMap(true));
			material.setMeta((short) 0);
			material.setName("null");
			materials.put("null", material);
			Main.logger.warn("The material-list was empty. Check your configuration file or report this to the mod-author!");
		}
	}
	
	public static Iterator getIterator(HashMap map) {
		return map.entrySet().iterator();
	}
	
	public static HashMap<String, Boolean> getAllTypesEqualHashMap(boolean value) {
		HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
		for(int i = 0; i < Reference.types.size(); i++) {
			hashMap.put((String) Reference.types.keySet().toArray()[i], value);
		}
		return hashMap;
	}

}
