package glowredman.modularmaterials.item;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;

import glowredman.modularmaterials.Main;
import static glowredman.modularmaterials.Reference.*;
import glowredman.modularmaterials.object.Material;
import glowredman.modularmaterials.object.Type;
import glowredman.modularmaterials.util.MaterialHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemHandler {
	
	public static void registerItems() {
		int count = 0;
		long time = System.currentTimeMillis();
		Iterator typeIterator = MaterialHandler.getIterator(types);
		while(typeIterator.hasNext()) {
			Entry<String, Type> type = (Entry<String, Type>) typeIterator.next();
			if(type.getValue().getCategory().equals("item") && ((type.getValue().isEnabled() == true) || enableAll)) {
				MetaItem item = new MetaItem(type.getKey());
				ForgeRegistries.ITEMS.register(item);
				metaItems.add(item);
				count++;
			}	
		}
		Main.logger.info("Registered " + count + " items. Took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	public static void addOreDictTags() {
		long time = System.currentTimeMillis();
		int count = 0;
		
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
	
	@SideOnly(Side.CLIENT)
	public static void initColors() {
		for(MetaItem item : metaItems) {
			item.registerColors();
		}
	}

}
