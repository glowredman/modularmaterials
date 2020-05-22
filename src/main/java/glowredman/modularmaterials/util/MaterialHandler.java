package glowredman.modularmaterials.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.block.MetaBlock;
import glowredman.modularmaterials.item.MetaItem;

import static glowredman.modularmaterials.Reference.*;
import glowredman.modularmaterials.object.Material;
import glowredman.modularmaterials.object.Type;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

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
	
	public static Material getMaterialFromID(int meta) {
		return materials.get(idMapping.get(meta));
	}
	
	public static void addOreDictTags() {
		long time = System.currentTimeMillis();
		int count = 0;
		
		//items
		for(MetaItem item : metaItems) {
			String oreDictPrefix = types.get(item.getType()).getOreDictPrefix();
			String type = item.getType();
			Iterator materialIterator = MaterialHandler.getIterator(materials);
			while(materialIterator.hasNext()) {
				Entry<String, Material> materialEntry = (Entry<String, Material>) materialIterator.next();
				Material material = materialEntry.getValue();
				if(material.isEnabled() && material.isTypeEnabled(type)) {
					for(String oreDict : material.getOreDict()) {
						OreDictionary.registerOre(oreDictPrefix + oreDict, new ItemStack(item, 1, material.getMeta()));
						count++;
					}
				}
			}
		}
		
		//blocks
		for(MetaBlock block : metaBlocks) {
			Material material = block.getMaterial();
			String oreDictPrefix = types.get(block.getType()).getOreDictPrefix();
			for(String oreDict : material.getOreDict()) {
				OreDictionary.registerOre(oreDictPrefix + oreDict, new ItemStack(block, 1));
				count++;
			}
		}

		if(enableUnitOreDict) {
			Collection<Type> typeList = types.values();
			for(Type type : typeList) {
				String oreDictPrefix = type.getOreDictPrefix();
				String unitValue = type.getUnitValue();
				Iterator materialIterator = MaterialHandler.getIterator(materials);
				while(materialIterator.hasNext()) {
					Entry<String, Material> materialEntry = (Entry<String, Material>) materialIterator.next();
					for(String oreDict : materialEntry.getValue().getOreDict()) {
						for(ItemStack item : OreDictionary.getOres(oreDictPrefix + oreDict)) {
							OreDictionary.registerOre(unitValue + oreDict, item);
							count++;
						}
					}
				}
			}
		}
		Main.logger.info("Registered " + count + " entries to the OreDictionary. Took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	public static void fillMaterialListIfEmpty() {
		if(materials.isEmpty()) {
			Material material = new Material();
			material.setEnabled(false);
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
		for(int i = 0; i < types.size(); i++) {
			hashMap.put((String) types.keySet().toArray()[i], value);
		}
		return hashMap;
	}

}
