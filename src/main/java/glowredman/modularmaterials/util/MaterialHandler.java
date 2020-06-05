package glowredman.modularmaterials.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.block.MetaBlock;
import glowredman.modularmaterials.block.MetaOre;
import glowredman.modularmaterials.block.MetaOreFalling;
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
			String key = entry.getKey();
			int meta = entry.getValue().meta;
			if(idMapping.containsKey(meta)) {
				Main.logger.error("Duplicate meta detected (" + meta + ")! Change the meta of " + idMapping.get(meta) + " or " + key + " to resolve this issue! " + key + " won't be registered.");
			} else {
				idMapping.put(meta, key);
			}
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
			String oreDictPrefix = types.get(item.type).oreDictPrefix;
			String type = item.type;
			Iterator materialIterator = MaterialHandler.getIterator(materials);
			while(materialIterator.hasNext()) {
				Entry<String, Material> materialEntry = (Entry<String, Material>) materialIterator.next();
				Material material = materialEntry.getValue();
				if(material.enabled && material.isTypeEnabled(type)) {
					for(String oreDict : material.oreDict) {
						OreDictionary.registerOre(oreDictPrefix + oreDict, new ItemStack(item, 1, material.meta));
						count++;
					}
				}
			}
		}
		
		//blocks
		for(MetaBlock block : metaBlocks) {
			String oreDictPrefix = types.get(block.type).oreDictPrefix;
			for(String oreDict : block.material.oreDict) {
				OreDictionary.registerOre(oreDictPrefix + oreDict, new ItemStack(block, 1));
				count++;
			}
		}
		
		//ores
		for(MetaOre ore : metaOres) {
			String oreOreDictPrefix = ore.ore.oreDictPrefix;
			String typeOrePrefix = types.get(ore.type).oreDictPrefix;
			for(String oreDict : ore.material.oreDict) {
				OreDictionary.registerOre(typeOrePrefix + oreOreDictPrefix + oreDict, ore);
				count++;
			}
		}
		for(MetaOreFalling ore : metaOresFalling) {
			String oreOreDictPrefix = ore.ore.oreDictPrefix;
			String typeOrePrefix = types.get(ore.type).oreDictPrefix;
			for(String oreDict : ore.material.oreDict) {
				OreDictionary.registerOre(typeOrePrefix + oreOreDictPrefix + oreDict, ore);
				count++;
			}
		}

		if(enableUnitOreDict) {
			Collection<Type> typeList = types.values();
			for(Type type : typeList) {
				String oreDictPrefix = type.oreDictPrefix;
				String unitValue = type.unitValue;
				Iterator materialIterator = MaterialHandler.getIterator(materials);
				while(materialIterator.hasNext()) {
					Entry<String, Material> materialEntry = (Entry<String, Material>) materialIterator.next();
					for(String oreDict : materialEntry.getValue().oreDict) {
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
			material.enabled = false;
			material.enabledTypes = getAllTypesEqualHashMap(true);
			material.meta = 0;
			material.name = "placeholder";
			materials.put("placeholder", material);
			Main.logger.warn("The material-list was empty. Check your configuration file or report this issue to the mod-author!");
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
