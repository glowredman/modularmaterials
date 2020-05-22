package glowredman.modularmaterials.item;

import java.util.Iterator;
import java.util.Map.Entry;

import glowredman.modularmaterials.Main;
import static glowredman.modularmaterials.Reference.*;
import glowredman.modularmaterials.object.Type;
import glowredman.modularmaterials.util.MaterialHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
	
	@SideOnly(Side.CLIENT)
	public static void initColors() {
		for(MetaItem item : metaItems) {
			item.registerColors();
		}
	}

}
