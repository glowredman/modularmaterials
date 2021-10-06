package glowredman.modularmaterials.data.object;

import java.io.File;

import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.data.JSONHandler;
import glowredman.modularmaterials.util.FileHelper;

public class MM_Config {
	
	public boolean enableAll = false;
	public String randomizer = "XSTR";
	public int commandPermissionLevel = 2;
	public boolean overrideModelFiles = false;
	public boolean overrideBlockstateFiles = false;
	public boolean overrideTagFiles = false;
	public boolean overrideLootTableFiles = false;
	public boolean blocksHaveFoilEffect = true;
	public boolean bucketsHaveFoilEffect = true;
	public boolean oresInheritHardness = true;
	public boolean oresInheritHarvestLevel = false;
	public boolean oresInheritLightLevel = false;
	public boolean oresInheritResistance = true;
	
	public static MM_Config read() {
		long time = System.currentTimeMillis();
		try {
			MM_Config cfg;
			File file = new File(JSONHandler.CONFIG_DIR, "config.json");
			ModularMaterials.info("Parsing " + file.getPath() + " ...");
			if(file.exists()) {
				cfg = JSONHandler.GSON.fromJson(FileHelper.readFile(file.toPath()), MM_Config.class);
			} else {
				file.getParentFile().mkdirs();
				FileHelper.write(file, JSONHandler.GSON.toJson(new MM_Config()));
				cfg = new MM_Config();
			}
			ModularMaterials.info("Done! Took " + (System.currentTimeMillis() - time) + "ms");
			return cfg;
		} catch (Exception e) {
			ModularMaterials.fatal("Parsing config.json failed:");
			e.printStackTrace();
			return null;
		}
	}
	
}
