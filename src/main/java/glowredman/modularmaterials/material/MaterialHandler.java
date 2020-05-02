package glowredman.modularmaterials.material;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import glowredman.modularmaterials.Main;
import static glowredman.modularmaterials.Reference.*;

public class MaterialHandler {
	
	public static void initTypes() {
		//items
		itemTypes.put("ingot", "%s Ingot");
		itemTypes.put("plate", "%s Plate");
		itemTypes.put("dust", "%s Dust");
		itemTypes.put("gem", "%s");
		//blocks
		blockTypes.put("block", "%s Block");
		blockTypes.put("ore", "%s Ore");
		//liquids
		fluidTypes.put("liquid", "");
		fluidTypes.put("gas", "");
	}
	
	public static void createIDMapping() {
		long time = System.currentTimeMillis();
		Iterator i = getIterator(MaterialList.materials);
		while(i.hasNext()) {
			Entry<String, Material> entry = (Entry<String, Material>) i.next();
			idMapping.put(entry.getValue().getMeta(), entry.getKey());
		}
		Main.logger.info("Finished mapping a total of " + idMapping.size() + " materials to their meta-values. Took " + (System.currentTimeMillis() - time) + "ms.");
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
	
	public static Iterator getIterator(HashMap map) {
		return map.entrySet().iterator();
	}

}
