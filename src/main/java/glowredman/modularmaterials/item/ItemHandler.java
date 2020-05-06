package glowredman.modularmaterials.item;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.Reference;
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
	
	public static void registeritems() {
		Iterator types = MaterialHandler.getIterator(Reference.types);
		while(types.hasNext()) {
			Entry<String, Type> type = (Entry<String, Type>) types.next();
			if(type.getValue().getCategory().equals("item") && (type.getValue().isDisabled() == false)) {
				MetaItem item = new MetaItem(type.getKey());
				item.setCreativeTab(Reference.TAB_ITEMS);
				ForgeRegistries.ITEMS.register(item);
				Reference.metaItems.add(item);
			}	
		}
	}
	
	public static void addOreDictTags() {
		long time = System.currentTimeMillis();
		int count = 0;
		
		for(MetaItem item : Reference.metaItems) {
			String oreDictPrefix = Reference.types.get(item.getType()).getOreDictPrefix();
			String type = item.getType();
			Iterator materials = MaterialHandler.getIterator(Reference.materials);
			while(materials.hasNext()) {
				Entry<String, Material> materialEntry = (Entry<String, Material>) materials.next();
				Material material = materialEntry.getValue();
				if(!material.isDisabled() && material.isTypeEnabled(type)) {
					for(String oreDict : material.getOreDict()) {
						OreDictionary.registerOre(oreDictPrefix + oreDict, new ItemStack(item, 1, material.getMeta()));
						count++;
					}
				}
			}
		}

		if(Reference.enableUnitOreDict) {
			Collection<Type> typeList = Reference.types.values();
			for(Type type : typeList) {
				String oreDictPrefix = type.getOreDictPrefix();
				String unitValue = type.getUnitValue();
				Iterator materials = MaterialHandler.getIterator(Reference.materials);
				while(materials.hasNext()) {
					Entry<String, Material> materialEntry = (Entry<String, Material>) materials.next();
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
		for(MetaItem item : Reference.metaItems) {
			item.registerColors();
		}
	}

}
