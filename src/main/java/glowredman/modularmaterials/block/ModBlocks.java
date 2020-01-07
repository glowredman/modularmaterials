package glowredman.modularmaterials.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {
	
	public static List<BlockBase> blocks = new ArrayList<BlockBase>();
	
	public static void register(IForgeRegistry<Block> registry) {
		for (BlockBase block : blocks) {
			registry.register(block);
		}
		
	}
	
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		for (BlockBase block : blocks) {
			registry.register(block.createItemBlock());
		}
	}
	
	public static void registerModels() {
		for (BlockBase block : blocks) {
			block.registerItemModel(Item.getItemFromBlock(block));
		}
	}
}
