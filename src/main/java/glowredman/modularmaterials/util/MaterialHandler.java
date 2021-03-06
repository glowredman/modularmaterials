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
import glowredman.modularmaterials.object.JMaterial;
import glowredman.modularmaterials.object.JMiscItem;
import glowredman.modularmaterials.object.JType;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class MaterialHandler {
	
	public static void createIDMapping() {
		long time = System.currentTimeMillis();
		
		//materials
		for(Entry<String, JMaterial> entry : materials.entrySet()) {
			String key = entry.getKey();
			short meta = entry.getValue().meta;
			if(idMaterialMapping.containsKey(meta)) {
				logger.error("Duplicate meta detected (" + meta + ")! Change the meta of material \"" + idMaterialMapping.get(meta) + "\" or \"" + key + "\" to resolve this issue! \"" + key + "\" won't be registered.");
			} else {
				idMaterialMapping.put(meta, key);
			}
		}
		
		//miscItems
		for(Entry<String, JMiscItem> entry : miscItemMap.entrySet()) {
			String key = entry.getKey();
			short meta = entry.getValue().meta;
			if(idMiscMapping.containsKey(meta)) {
				logger.error("Duplicate meta detected (" + meta + ")! Change the meta of item \"" + idMiscMapping.get(meta) + "\" or \"" + key + "\" to resolve this issue! \"" + key + "\" won't be registered.");
			} else {
				idMiscMapping.put(meta, key);
			}
		}
		
		logger.info("Finished mapping a total of " + idMaterialMapping.size() + " materials and " + idMiscMapping.size() + " misc items to their meta-values. Took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	public static JMaterial getMaterialFromID(short meta) {
		return materials.get(idMaterialMapping.get(meta));
	}
	
	public static JMiscItem getMiscItemFromID(short meta) {
		return miscItemMap.get(idMiscMapping.get(meta));
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
			for(Entry<String, JMaterial> materialEntry : materials.entrySet()) {
				JMaterial material = materialEntry.getValue();
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
			Collection<JType> typeList = types.values();
			for(JType type : typeList) {
				String oreDictPrefix = type.oreDictPrefix;
				String unitValue = type.unitValue;
				for(Entry<String, JMaterial> materialEntry : materials.entrySet()) {
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
			JMaterial material = new JMaterial();
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
