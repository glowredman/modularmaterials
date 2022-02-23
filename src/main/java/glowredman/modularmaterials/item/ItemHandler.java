package glowredman.modularmaterials.item;

import java.util.Map.Entry;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.block.IMetaOre;
import glowredman.modularmaterials.block.MetaBlock;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_Type;
import glowredman.modularmaterials.data.object.sub.Category;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemHandler {
	
	@SubscribeEvent
	public void registerItems(Register<Item> event) {
		long time = System.currentTimeMillis();
		
		for(Entry<String, MM_Type> eType : MM_Reference.TYPES.entrySet()) {
			String typeName = eType.getKey();
			MM_Type type = eType.getValue();
			if(type.category == Category.ITEM && (type.enabled || MM_Reference.CONFIG.enableAll)) {
				for(Entry<String, MM_Material> eMaterial : MM_Reference.MATERIALS.entrySet()) {
					String materialName = eMaterial.getKey();
					MM_Material material = eMaterial.getValue();
					if((material.enabled && material.enabledTypes.contains(typeName)) || MM_Reference.CONFIG.enableAll) {
						MetaItem item = new MetaItem(material, type);
						item.setRegistryName(MM_Reference.MODID, typeName + "." + materialName);
						event.getRegistry().register(item);
					}
				}
			}
		}
		
		for(MetaBlock block : MM_Reference.BLOCKS) {
			event.getRegistry().register(new MetaBlockItem(block));
		}
		
		for(IMetaOre ore : MM_Reference.ORES.values()) {
			event.getRegistry().register(new MetaOreBlockItem(ore));
		}
		
		ModularMaterials.info("Registered " + MM_Reference.ITEMS.size() + " items. Took " + (System.currentTimeMillis() - time) + "ms.");
	}

}
