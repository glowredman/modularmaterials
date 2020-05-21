package glowredman.modularmaterials.block;

import java.util.Iterator;
import java.util.Map.Entry;

import glowredman.modularmaterials.Main;
import static glowredman.modularmaterials.Reference.*;
import glowredman.modularmaterials.object.Material;
import glowredman.modularmaterials.object.Type;
import glowredman.modularmaterials.util.MaterialHandler;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlockHandler {
	
	public static void registerBlocks() {
		int count = 0;
		long time = System.currentTimeMillis();
		Iterator typeIterator = MaterialHandler.getIterator(types);
		while(typeIterator.hasNext()) {
			Entry<String, Type> typeEntry = (Entry<String, Type>) typeIterator.next();
			Type type = typeEntry.getValue();
			String typeKey = typeEntry.getKey();
			if((type.isEnabled() || enableAll) && type.getCategory().equals("block")) {
				Iterator materialIterator = MaterialHandler.getIterator(materials);
				while(materialIterator.hasNext()) {
					Entry<String, Material> materialEntry = (Entry<String, Material>) materialIterator.next();
					Material material = materialEntry.getValue();
					String materialKey = materialEntry.getKey();
					if(enableAll ||(material.isEnabled() && material.isTypeEnabled(typeKey))) {
						MetaBlock block = new MetaBlock(material, typeKey, materialKey);
						ForgeRegistries.BLOCKS.register(block);
						ForgeRegistries.ITEMS.register(block.createItemBlock());
						Main.proxy.registerItemRenderer(Item.getItemFromBlock(block), material.getTexture() + '/' + typeKey);
						metaBlocks.add(block);
						count++;
					}
				}
			}
		}
		Main.logger.info("Registered " + count + " blocks. Took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	public static void initColors() {
		for(MetaBlock block : metaBlocks) {
			block.registerColor();
		}
	}
	
}
