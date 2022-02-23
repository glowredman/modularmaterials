package glowredman.modularmaterials.data.object;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.data.JSONHandler;
import glowredman.modularmaterials.util.FileHelper;
import glowredman.modularmaterials.util.RandomXSTR;
import glowredman.modularmaterials.util.RandomXoshiro256StarStar;

public class MM_Config {

	// misc
	public int commandPermissionLevel = 2;
	public boolean enableAll = false;
	public boolean enableBuckets = true;
	public boolean enableOres = true;
	public String presetURL = "https://raw.githubusercontent.com/glowredman/modularmaterials/dev-1.18/presets/";
	public String random = "XSTR";

	// files
	public boolean overrideBlockstateFiles = false;
	public boolean overrideLootTableFiles = false;
	public boolean overrideModelFiles = false;

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
	public boolean oresInheritMaterial = true;
	public boolean oresInheritResistance = true;
	public boolean oresInheritSoundType = true;
	public boolean oresInheritSpeedFactor = true;

	// world gen
	public int maxFindingAttempts = 64;
	public int maxPlacementAttempts = 8;
	public int maxVeinSize = 32;
	public List<String> removeOres = Arrays.asList("ore_gold_deltas", "ore_quartz_deltas", "ore_gold_nether",
			"ore_quartz_nether", "ore_coal_upper", "ore_coal_lower", "ore_iron_upper", "ore_iron_middle",
			"ore_iron_small", "ore_gold_extra", "ore_gold", "ore_gold_lower", "ore_redstone", "ore_redstone_lower",
			"ore_diamond", "ore_diamond_large", "ore_diamond_buried", "ore_lapis", "ore_lapis_buried", "ore_emerald",
			"ore_ancient_debris_large", "ore_debris_small", "ore_copper", "ore_copper_large");
	public float veinGenerationProbability = 0.75f;

	public static MM_Config read() {
		long time = System.currentTimeMillis();
		try {
			MM_Config cfg;
			File file = new File(JSONHandler.CONFIG_DIR, "config.json");
			if (file.exists()) {
				cfg = JSONHandler.GSON.fromJson(FileHelper.readFile(file.toPath()), MM_Config.class);
			} else {
				file.getParentFile().mkdirs();
				FileHelper.write(file, JSONHandler.GSON.toJson(new MM_Config()));
				cfg = new MM_Config();
			}
			ModularMaterials.info("Parsed config.json in " + (System.currentTimeMillis() - time) + "ms");
			return cfg;
		} catch (Exception e) {
			ModularMaterials.fatal("Parsing config.json failed:");
			e.printStackTrace();
			return null;
		}
	}
	
	public Function<Long, Random> getRandom() {
		if("XSTR".equals(random)) {
			ModularMaterials.info("Using XSTR as RNG");
			return seed -> new RandomXSTR(seed);
		}
		if("Xoshiro256**".equals(random)) {
			ModularMaterials.info("Using Xoshiro256** as RNG");
			return seed -> new RandomXoshiro256StarStar(seed);
		}
		if("Standard".equals(random)) {
			ModularMaterials.info("Using java.util.Random as RNG");
			return seed -> new Random(seed);
		}
		
		try {
			// get constructor
			Constructor<?> c = Class.forName(random).getConstructor();
			// test
			Random testRNG = (Random) c.newInstance();
			testRNG.setSeed(System.nanoTime());
			
			ModularMaterials.info("Using " + random + " as RNG");
			
			return seed -> {
				try {
					Random rng = (Random) c.newInstance();
					rng.setSeed(seed);
					return rng;
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			};
		} catch (Exception e) {
			ModularMaterials.error("Failed to construct the RNG:");
			throw new RuntimeException(e);
		}
	}

}
