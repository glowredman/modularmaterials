package glowredman.modularmaterials.item;

import java.util.Iterator;
import java.util.Map.Entry;

import glowredman.modularmaterials.Reference;
import glowredman.modularmaterials.object.Type;
import glowredman.modularmaterials.util.MaterialHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemHandler {
	
	public static void registeritems() {
		Iterator types = MaterialHandler.getIterator(Reference.types);
		while(types.hasNext()) {
			Entry<String, Type> type = (Entry<String, Type>) types.next();
			if(type.getValue().getCategory().equals("item")) {
				MetaItem item = new MetaItem(type.getKey());
				item.setCreativeTab(Reference.TAB_ITEMS);
				ForgeRegistries.ITEMS.register(item);
				Reference.metaItems.add(item);
			}	
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void initColors() {
		for(MetaItem item : Reference.metaItems) {
			item.registerColors();
		}
	}

}
