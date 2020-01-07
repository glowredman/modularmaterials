package glowredman.modularmaterials.block;

import java.util.ArrayList;
import java.util.List;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.information.Reference;
import glowredman.modularmaterials.json.Material;
import glowredman.modularmaterials.util.MiscUtils;
import glowredman.modularmaterials.util.TagHandler;
import net.minecraftforge.oredict.OreDictionary;

public class BlockStorage extends BlockMeta {
	
	public BlockStorage() {
		super(net.minecraft.block.material.Material.IRON);
	}

	public static void preInit() {
		
		long time = System.nanoTime();
		int blockCount = 0;
		
		List<Integer> blockSets = new ArrayList<Integer>();
		
		for (Material material : Reference.materialList.getMaterials()) {
			int blockSet = (int) Math.floor(material.getMeta() / 16);
			if (TagHandler.isValidBlock(material) && (blockSets.contains(blockSet) == false)) {
				BlockBase block = new BlockBase("block" + blockSet);
				ModBlocks.blocks.add(block);
				blockSets.add(blockSet);
				blockCount++;
			}
		}
		
		Main.logger.info("Finished registering all " + blockCount + " blocks. Took " + MiscUtils.timer(time));
		
	}

}
