package glowredman.modularmaterials.block;

import static glowredman.modularmaterials.Main.logger;
import static glowredman.modularmaterials.Main.proxy;
import static glowredman.modularmaterials.Reference.*;

import java.util.Map.Entry;

import glowredman.modularmaterials.gen.OreGenHandler;
import glowredman.modularmaterials.object.JMaterial;
import glowredman.modularmaterials.object.JOreVariant;
import glowredman.modularmaterials.object.JType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlockHandler {
	
	public static void registerBlocks() {
		int count = 0;
		long time = System.currentTimeMillis();
		for(Entry<String, JType> typeEntry : types.entrySet()) {
			JType type = typeEntry.getValue();
			String typeKey = typeEntry.getKey();
			if((type.enabled || enableAll) && type.category.equals("block")) {
				for(Entry<String, JMaterial> materialEntry : materials.entrySet()) {
					JMaterial material = materialEntry.getValue();
					String materialKey = materialEntry.getKey();
					if(enableAll ||(material.enabled && material.isTypeEnabled(typeKey))) {
						MetaBlock block = new MetaBlock(material, typeKey, materialKey);
						ForgeRegistries.BLOCKS.register(block);
						ForgeRegistries.ITEMS.register(block.createItemBlock());
						proxy.registerItemRenderer(Item.getItemFromBlock(block), material.texture + '/' + typeKey);
						metaBlocks.add(block);
						count++;
					}
				}
			}
		}
		logger.info("Registered " + count + " blocks. Took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	public static void registerOres() {
		int count = 0;
		long time = System.currentTimeMillis();
		for(Entry<String, JType> typeEntry : types.entrySet()) {
			JType type = typeEntry.getValue();
			String typeKey = typeEntry.getKey();
			if((type.enabled || enableAll) && type.category.equals("ore")) {
				for(Entry<String, JMaterial> materialEntry : materials.entrySet()) {
					JMaterial material = materialEntry.getValue();
					String materialKey = materialEntry.getKey();
					if((material.enabled && material.isTypeEnabled(typeKey)) || enableAll) {
						for(Entry<String, JOreVariant> oreEntry : oreVariants.entrySet()) {
							JOreVariant ore = oreEntry.getValue();
							String oreKey = oreEntry.getKey();
							IBlockState blockBaseState = OreGenHandler.getBlockStateFromBlockName(ore.baseBlock);
							if(ore.enabled || enableAll) {
								if(ore.obeysGravity) {
									MetaOreFalling oreBlock = new MetaOreFalling(material, ore, typeKey, oreKey, materialKey);
									ForgeRegistries.BLOCKS.register(oreBlock);
									ForgeRegistries.ITEMS.register(oreBlock.createItemBlock());
									proxy.registerItemRenderer(Item.getItemFromBlock(oreBlock), material.texture + '/' + typeKey + '/' + oreKey);
									metaOresFalling.add(oreBlock);
									OreGenHandler.addToStateOreMapping(blockBaseState, materialKey, oreBlock);
									count++;
								} else {
									MetaOre oreBlock = new MetaOre(material, ore, typeKey, oreKey, materialKey);
									ForgeRegistries.BLOCKS.register(oreBlock);
									ForgeRegistries.ITEMS.register(oreBlock.createItemBlock());
									proxy.registerItemRenderer(Item.getItemFromBlock(oreBlock), material.texture + '/' + typeKey + '/' + oreKey);
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
		logger.info("Registered " + count + " ores. Took " + (System.currentTimeMillis() - time) + "ms.");
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
