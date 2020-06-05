package glowredman.modularmaterials.block;

import java.util.Iterator;
import java.util.Map.Entry;

import glowredman.modularmaterials.Main;
import static glowredman.modularmaterials.Reference.*;
import glowredman.modularmaterials.gen.OreGenHandler;
import glowredman.modularmaterials.object.Material;
import glowredman.modularmaterials.object.OreVariant;
import glowredman.modularmaterials.object.Type;
import glowredman.modularmaterials.util.MaterialHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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
			if((type.enabled || enableAll) && type.category.equals("block")) {
				Iterator materialIterator = MaterialHandler.getIterator(materials);
				while(materialIterator.hasNext()) {
					Entry<String, Material> materialEntry = (Entry<String, Material>) materialIterator.next();
					Material material = materialEntry.getValue();
					String materialKey = materialEntry.getKey();
					if(enableAll ||(material.enabled && material.isTypeEnabled(typeKey))) {
						MetaBlock block = new MetaBlock(material, typeKey, materialKey);
						ForgeRegistries.BLOCKS.register(block);
						ForgeRegistries.ITEMS.register(block.createItemBlock());
						Main.proxy.registerItemRenderer(Item.getItemFromBlock(block), material.texture + '/' + typeKey);
						metaBlocks.add(block);
						count++;
					}
				}
			}
		}
		Main.logger.info("Registered " + count + " blocks. Took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	public static void registerOres() {
		int count = 0;
		long time = System.currentTimeMillis();
		Iterator typeIterator = MaterialHandler.getIterator(types);
		while(typeIterator.hasNext()) {
			Entry<String, Type> typeEntry = (Entry<String, Type>) typeIterator.next();
			Type type = typeEntry.getValue();
			String typeKey = typeEntry.getKey();
			if((type.enabled || enableAll) && type.category.equals("ore")) {
				Iterator materialIterator = MaterialHandler.getIterator(materials);
				while(materialIterator.hasNext()) {
					Entry<String, Material> materialEntry = (Entry<String, Material>) materialIterator.next();
					Material material = materialEntry.getValue();
					String materialKey = materialEntry.getKey();
					if ((material.enabled && material.isTypeEnabled(typeKey)) || enableAll) {
						Iterator oreIterator = MaterialHandler.getIterator(oreVariants);
						while (oreIterator.hasNext()) {
							Entry<String, OreVariant> oreEntry = (Entry<String, OreVariant>) oreIterator.next();
							OreVariant ore = oreEntry.getValue();
							String oreKey = oreEntry.getKey();
							IBlockState blockBaseState = OreGenHandler.getBlockStateFromBlockName(ore.baseBlock);
							if(ore.enabled || enableAll) {
								if(ore.obeysGravity) {
									MetaOreFalling oreBlock = new MetaOreFalling(material, ore, typeKey, oreKey, materialKey);
									ForgeRegistries.BLOCKS.register(oreBlock);
									ForgeRegistries.ITEMS.register(oreBlock.createItemBlock());
									Main.proxy.registerItemRenderer(Item.getItemFromBlock(oreBlock), material.texture + '/' + typeKey + '/' + oreKey);
									metaOresFalling.add(oreBlock);
									OreGenHandler.addToStateOreMapping(blockBaseState, materialKey, oreBlock);
									count++;
								} else {
									MetaOre oreBlock = new MetaOre(material, ore, typeKey, oreKey, materialKey);
									ForgeRegistries.BLOCKS.register(oreBlock);
									ForgeRegistries.ITEMS.register(oreBlock.createItemBlock());
									Main.proxy.registerItemRenderer(Item.getItemFromBlock(oreBlock), material.texture + '/' + typeKey + '/' + oreKey);
									metaOres.add(oreBlock);
									OreGenHandler.addToStateOreMapping(blockBaseState, materialKey, oreBlock);
									count++;
								}
							}
						} 
					}
				}
			}
		}
		Main.logger.info("Registered " + count + " ores. Took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	public static void initColors() {
		for(MetaBlock block : metaBlocks) {
			block.registerColor();
		}
		for(MetaOre ore : metaOres) {
			ore.registerColor();
		}
		for(MetaOreFalling ore : metaOresFalling) {
			ore.registerColor();
		}
	}
	
}
