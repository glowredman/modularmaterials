package glowredman.modularmaterials.item;

import static glowredman.modularmaterials.Main.logger;
import static glowredman.modularmaterials.Reference.*;

import java.util.Map.Entry;

import glowredman.modularmaterials.object.JType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHandler {
	
	public static void registerItems() {
		int count = 0;
		long time = System.currentTimeMillis();
		for(Entry<String, JType> type : types.entrySet()) {
			if(type.getValue().category.equals("item") && (type.getValue().enabled || enableAll)) {
				MetaItem item = new MetaItem(type.getKey());
				ForgeRegistries.ITEMS.register(item);
				metaItems.add(item);
				count++;
			}	
		}
		logger.info("Registered " + count + " items. Took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	@SideOnly(Side.CLIENT)
	public static void initColors() {
		for(MetaItem item : metaItems) {
			item.registerColors();
		}
	}

}
