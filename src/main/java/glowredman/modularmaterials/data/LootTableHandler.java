package glowredman.modularmaterials.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.file.PathUtils;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.block.IMetaOre;
import glowredman.modularmaterials.block.MetaBlock;

public class LootTableHandler {
	
	public static void generateBlockDrops() {
		long time = System.currentTimeMillis();
		int count = 0;
		
		Path lootTables = ResourceLoader.DATA_DIR.resolve("data").resolve(MM_Reference.MODID).resolve("loot_tables").resolve("blocks");
		
		if(MM_Reference.CONFIG.overrideLootTableFiles) {
			try {
                PathUtils.cleanDirectory(lootTables);
            } catch (IOException e) {
                ModularMaterials.LOGGER.warn("Failed to clean loottable directory", e);
            }
		}
		
		try {
            Files.createDirectories(lootTables);
        } catch (IOException e) {
            ModularMaterials.LOGGER.error("Failed to create loottable directory", e);
            return;
        }
		
		for(MetaBlock block : MM_Reference.BLOCKS) {;
			Path lootTable = lootTables.resolve(block.registryName.getPath() + ".json");
			if(Files.notExists(lootTable)) {
				try {
                    Files.writeString(lootTable, Templates.LOOTTABLE_BLOCKS.format(block.registryName));
                    count++;
                } catch (IOException e) {
                    ModularMaterials.LOGGER.warn("Failed to create loottable for " + block.registryName, e);
                }
			}
		}
		
		for(IMetaOre ore : MM_Reference.ORES.values()) {
		    Path lootTable = lootTables.resolve(ore.getRegistryName().getPath() + ".json");
			if(Files.notExists(lootTable)) {
			    try {
                    Files.writeString(lootTable, Templates.LOOTTABLE_BLOCKS.format(ore.getRegistryName()));
                    count++;
                } catch (IOException e) {
                    ModularMaterials.LOGGER.warn("Failed to create loottable for " + ore.getRegistryName(), e);
                }
			}
		}
		
		ModularMaterials.LOGGER.info("Generated {} loot tables in {}ms.", count, System.currentTimeMillis() - time);
	}

}
