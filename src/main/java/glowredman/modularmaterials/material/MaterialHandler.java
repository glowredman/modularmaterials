package glowredman.modularmaterials.material;

import java.util.Iterator;
import java.util.Map.Entry;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.api.Material;
import glowredman.modularmaterials.api.MaterialList;

public class MaterialHandler {
	
	public static void initTypes() {
		MaterialList.types.put("ingot", "item");
	}
	
	public static void createIDMapping() {
		long time = System.currentTimeMillis();
		Iterator i = MaterialList.getIterator(MaterialList.materials);
		while(i.hasNext()) {
			Entry entry = (Entry) i.next();
			MaterialList.idMapping.put(((Material) entry.getValue()).getMeta(), (String) entry.getKey());
		}
		Main.logger.info("Finished mapping a total of " + MaterialList.idMapping.size() + " materials to their meta-values. Took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	public static void fillMaterialListIfEmpty() {
		if(MaterialList.materials.isEmpty()) {
			Material material = new Material();
			material.setDisabled(true);
			material.setEnabledTypes(new Material().getAllTypesEqualHashMap(true));
			material.setMeta(0);
			material.setName("null");
			MaterialList.materials.put("null", material);
			Main.logger.warn("The material-list was empty. Check your configuration file or report this to the mod-author!");
		}
	}

}
