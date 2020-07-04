package glowredman.modularmaterials.util;

import static glowredman.modularmaterials.Main.logger;
import static glowredman.modularmaterials.Reference.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import glowredman.modularmaterials.block.MetaBlock;
import glowredman.modularmaterials.block.MetaOre;
import glowredman.modularmaterials.block.MetaOreFalling;
import glowredman.modularmaterials.item.MetaItem;
import glowredman.modularmaterials.object.Material;
import glowredman.modularmaterials.object.Type;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class MaterialHandler {
	
	public static void createIDMapping() {
		long time = System.currentTimeMillis();
		for(Entry<String, Material> entry : materials.entrySet()) {
			String key = entry.getKey();
			short meta = entry.getValue().meta;
			if(idMapping.containsKey(meta)) {
				logger.error("Duplicate meta detected (" + meta + ")! Change the meta of " + idMapping.get(meta) + " or " + key + " to resolve this issue! " + key + " won't be registered.");
			} else {
				idMapping.put(meta, key);
			}
		}
		logger.info("Finished mapping a total of " + idMapping.size() + " materials to their meta-values. Took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	public static Material getMaterialFromID(short meta) {
		return materials.get(idMapping.get(meta));
	}
	
	public static ItemStack getItemStackFromString(String item, int amount, Block itself) {
		String[] parts = item.split(":");
		if (item.equals("itself")) {
			return new ItemStack(itself, amount);
		} else {
			switch (parts.length) {
			case 1:
				return new ItemStack(Item.getByNameOrId(item), amount);
			case 2:
				try {
					int meta = Integer.parseInt(parts[1]);
					return new ItemStack(Item.getByNameOrId(parts[0]), amount, Integer.parseInt(parts[1]));
				} catch (Exception e) {
					return new ItemStack(Item.getByNameOrId(item), amount);
				}
			case 3:
				try {
					return new ItemStack(Item.getByNameOrId(parts[0] + ':' + parts[1]), amount,
							Integer.parseInt(parts[2]));
				} catch (Exception e) {
					return null;
				}
			}
		}
		return null;
	}
	
	public static List<String> getListFormArray(String[] array){
		List<String> out = new ArrayList<String>();
		for(String s : array) {
			out.add(s);
		}
		return out;
	}
	
	public static void addOreDictTags() {
		long time = System.currentTimeMillis();
		int count = 0;
		
		//items
		for(MetaItem item : metaItems) {
			String oreDictPrefix = types.get(item.type).oreDictPrefix;
			String type = item.type;
			for(Entry<String, Material> materialEntry : materials.entrySet()) {
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
				for(Entry<String, Material> materialEntry : materials.entrySet()) {
					for(String oreDict : materialEntry.getValue().oreDict) {
						for(ItemStack item : OreDictionary.getOres(oreDictPrefix + oreDict)) {
							OreDictionary.registerOre(unitValue + oreDict, item);
							count++;
						}
					}
				}
			}
		}
		logger.info("Registered " + count + " entries to the OreDictionary. Took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	public static void fillMaterialListIfEmpty() {
		if(materials.isEmpty()) {
			Material material = new Material();
			material.enabled = false;
			material.enabledTypes = getAllTypesEqualHashMap(true);
			material.meta = 0;
			material.name = "placeholder";
			materials.put("placeholder", material);
			logger.warn("The material-list was empty. Check your configuration file or report this issue to the mod-author!");
		}
	}
	
	public static HashMap<String, Boolean> getAllTypesEqualHashMap(boolean value) {
		HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
		for(int i = 0; i < types.size(); i++) {
			hashMap.put((String) types.keySet().toArray()[i], value);
		}
		return hashMap;
	}

}
