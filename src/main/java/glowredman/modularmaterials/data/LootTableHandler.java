package glowredman.modularmaterials.data;

import java.io.File;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.block.IMetaOre;
import glowredman.modularmaterials.block.MetaBlock;
import glowredman.modularmaterials.util.FileHelper;

public class LootTableHandler {
	
	public static void generateBlockDrops() {
		long time = System.currentTimeMillis();
		int count = 0;
		
		File lootTables = new File(ResourceLoader.DATA_DIR, "data/" + MM_Reference.MODID + "/loot_tables/blocks");
		
		if(MM_Reference.CONFIG.overrideLootTableFiles) {
			FileHelper.cleanDir(lootTables);
		}
		
		lootTables.mkdirs();
		
		for(MetaBlock block : MM_Reference.BLOCKS) {;
			File lootTable = new File(lootTables, block.registryName.getPath() + ".json");
			if(!lootTable.exists()) {
				FileHelper.write(lootTable, Templates.LOOTTABLE_BLOCKS.format(block.registryName));
				count++;
			}
		}
		
		for(IMetaOre ore : MM_Reference.ORES.values()) {
			File lootTable = new File(lootTables, ore.getRegistryName().getPath() + ".json");
			if(!lootTable.exists()) {
				FileHelper.write(lootTable, Templates.LOOTTABLE_BLOCKS.format(ore.getRegistryName()));
				count++;
			}
		}
		
		ModularMaterials.LOGGER.info("Generated {} loot tables in {}ms.", count, System.currentTimeMillis() - time);
	}

}
