package glowredman.modularmaterials.item;

import java.util.Iterator;
import java.util.Map.Entry;

import glowredman.modularmaterials.Reference;
import glowredman.modularmaterials.material.MaterialHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemHandler {
	
	public static void registeritems() {
		Iterator types = MaterialHandler.getIterator(Reference.itemTypes);
		while(types.hasNext()) {
			Entry<String, String> type = (Entry<String, String>) types.next();
			ForgeRegistries.ITEMS.register(new MetaItem(type.getKey()));
		}
	}

}
