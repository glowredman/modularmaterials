package glowredman.modularmaterials.data.object;

import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.function.Function;

import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.data.JSONHandler;
import glowredman.modularmaterials.util.RandomXSTR;
import glowredman.modularmaterials.util.RandomXoshiro256StarStar;

public class MM_Config {

	// misc
	public int commandPermissionLevel = 2;
	public boolean enableAll = false;
	public boolean enableBuckets = true;
	public boolean enableOres = true;
	public String presetURL = "https://raw.githubusercontent.com/glowredman/modularmaterials/dev-1.20/presets/";
	public String random = "XSTR";

	// files
	public boolean overrideBlockstateFiles = false;
	public boolean overrideLootTableFiles = false;
	public boolean overrideModelFiles = false;
	public boolean overrideTagFiles = false;

	// rendering
	public boolean blocksHaveFoilEffect = true;
	public boolean bucketsHaveFoilEffect = true;
	public boolean oresHaveFoilEffect = true;
	public boolean oresHaveTooltip = true;

	// ores
	public boolean oresInheritFriction = true;
	public boolean oresInheritHardness = true;
	public boolean oresInheritHarvestLevel = false;
	public boolean oresInheritJumpFactor = true;
	public boolean oresInheritLightLevel = false;
	public boolean oresInheritMapColor = true;
    public boolean oresInheritNoteblockInstrument = true;
	public boolean oresInheritResistance = true;
	public boolean oresInheritSoundType = true;
	public boolean oresInheritSpeedFactor = true;

	// world gen
	public int maxFindingAttempts = 64;
	public int maxPlacementAttempts = 8;
	public int maxVeinSize = 32;
	public float veinGenerationProbability = 0.75f;

	public static MM_Config read() {
		long time = System.currentTimeMillis();
		try {
			MM_Config cfg;
			Path file = JSONHandler.CONFIG_DIR.resolve("config.json");
			if (Files.exists(file)) {
				cfg = JSONHandler.GSON.fromJson(Files.readString(file), MM_Config.class);
			} else {
				Files.createDirectories(file.getParent());
				Files.writeString(file, JSONHandler.GSON.toJson(new MM_Config()));
				cfg = new MM_Config();
			}
			ModularMaterials.LOGGER.info("Parsed config.json in {}ms", System.currentTimeMillis() - time);
			return cfg;
		} catch (Exception e) {
			ModularMaterials.LOGGER.fatal("Failed to parse config.json", e);
			return null;
		}
	}
	
	public Function<Long, Random> getRandom() {
		if("XSTR".equals(random)) {
			ModularMaterials.LOGGER.info("Using XSTR as RNG");
			return RandomXSTR::new;
		}
		if("Xoshiro256**".equals(random)) {
			ModularMaterials.LOGGER.info("Using Xoshiro256** as RNG");
			return RandomXoshiro256StarStar::new;
		}
		if("Standard".equals(random)) {
			ModularMaterials.LOGGER.info("Using java.util.Random as RNG");
			return Random::new;
		}
		
		try {
			// get constructor
			Constructor<?> c = Class.forName(random).getConstructor();
			// test
			Random testRNG = (Random) c.newInstance();
			testRNG.setSeed(System.nanoTime());
			
			ModularMaterials.LOGGER.info("Using {} as RNG", random);
			
			return seed -> {
				try {
					Random rng = (Random) c.newInstance();
					rng.setSeed(seed);
					return rng;
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			};
		} catch (Exception e) {
			ModularMaterials.LOGGER.error("Failed to construct RNG", e);
		}
        return RandomXSTR::new;
	}

}
