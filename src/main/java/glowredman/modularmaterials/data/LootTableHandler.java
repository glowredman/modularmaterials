package glowredman.modularmaterials.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.block.MetaBlock;
import glowredman.modularmaterials.util.FileHelper;
import net.minecraft.resources.ResourceLocation;

public class LootTableHandler {
	
	public static void generateBlockDrops() {
		ModularMaterials.info("Generating loot tables for block drops...");
		long time = System.currentTimeMillis();
		int count = 0;
		
		File lootTables = new File(ResourceLoader.DATA_DIR, "data/" + MM_Reference.MODID + "/loot_tables/blocks");
		
		if(MM_Reference.overrideLootTableFiles) {
			FileHelper.cleanDir(lootTables);
		}
		
		lootTables.mkdirs();
		
		for(MetaBlock block : MM_Reference.BLOCKS) {
			ResourceLocation regName = block.getRegistryName();
			File lootTable = new File(lootTables, regName.getPath() + ".json");
			try {
				if(!lootTable.exists()) {
					BufferedWriter w = new BufferedWriter(new FileWriter(lootTable));
					w.write(Templates.LOOTTABLE_BLOCKS.format(regName));
					w.close();
					count++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		ModularMaterials.info("Done! Generated " + count + " loot tables in " + (System.currentTimeMillis() - time) + "ms.");
	}

}
