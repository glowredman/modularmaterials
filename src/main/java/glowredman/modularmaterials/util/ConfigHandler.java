package glowredman.modularmaterials.util;

import static glowredman.modularmaterials.Reference.*;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler {
	
	// ---CATEGORIES---
	//core
	public static final String GENERAL = "general";
	public static final String FORMATTING = "formatting";
	
	//defaults
	public static final String BLOCK = "block";
	public static final String COLOR = "color";
	public static final String FLUID = "fluid";
	public static final String ITEM = "item";
	public static final String MATERIAL = "material";
	public static final String VARIANT = "oreVariant";
	public static final String VEIN = "oreVein";
	public static final String TYPE = "type";
	
	// ---PHASES---
	public static void initConfigs(FMLPreInitializationEvent event) {
		config_core = new Configuration(new File(event.getModConfigurationDirectory().getPath() + '/' + MODID, CONFIGNAME_CORE));
		config_defaults = new Configuration(new File(event.getModConfigurationDirectory().getPath() + '/' + MODID, CONFIGNAME_DEFAULTS));
	}
	
	public static void readConfigs() {
		readCoreConfig();
		readDefaultsConfig();
	}
	
	public static void saveConfigs() {
		updateConfig(config_core);
		updateConfig(config_defaults);
	}
	
	// ---INIT CONFIGS---
	private static void initCoreConfig() {
		config_core.setCategoryRequiresMcRestart(FORMATTING, true);
		config_core.setCategoryRequiresMcRestart(GENERAL, true);
		
		//general
		enableAll = config_core.getBoolean("enableAll", GENERAL, false, "Set to true to ignore ALL other restrictions -> all items/blocks/fluids will be enabled. USE WITH CAUTION!");
		overrideModelFiles = config_core.getBoolean("overrideModelFiles", GENERAL, false, "Set to true to regenerate all files in the /resources/models/-directory. ATTENTION: requires an additional restart to create the model files (or use F3+T ingame)!");
		overrideLangFile = config_core.getBoolean("overrideLangFile", GENERAL, false, "Set to true to regenerate the lang-file. ATTENTION: requires an additional restart to create the lang file (or use F3+T ingame)!");
		enableUnitOreDict = config_core.getBoolean("enableUnitOreDict", GENERAL, true, "Enables a unit-based oreDict system where each type is a specified amount worth (for example ingots, dusts, plates are 1u, nuggets 1/9u and blocks 9u). This allows for more precise specification of inputs (for example \"1uCopper\" could be used as an input in the Alloy Smelter because what difference makes the appearance/shape of a material if it will be molten?)");
		suppressTypeMissingWarnings = config_core.getBoolean("suppressTypeMissingWarnings", GENERAL, false, "Set to true to suppress any warnings, that indicate missing information in the " + CONFIGNAME_TYPES + "-config.");
		overrideBlockStateFiles = config_core.getBoolean("overrideBlockStateFiles", GENERAL, false, "Set to true to regenerate all files in the /resources/blockstates/-directory. ATTENTION: requires an additional restart to create the blockstate files (or use F3+T ingame)!");
		enableOreGen = config_core.getBoolean("enableOreGen", GENERAL, true, "Enables ore generation. The ore-gen is configured in " + CONFIGNAME_OREGENERATION + " and is GT5-styled. If you want vanilla-like ore-gen, use CoFH World.");
		modGenerationWeight = config_core.getInt("modGenerationWeight", GENERAL, 0, 0, Integer.MAX_VALUE, "The priority this mod is given while generating terrain, a lower number means a higher priority. DO NOT TOUCH THIS IF YOU DON'T KNOW WHAT YOU'RE DOING!");
		disableVanillaOreGen = config_core.getBoolean("disableVanillaOreGen", GENERAL, true, "");
		veinGenerationProbability = config_core.getFloat("veinGenerationProbability", GENERAL, 0.75f, 0, 1, "The probility, that there is a vein in a vein location.");
		commandName = config_core.getString("commandName", GENERAL, "mm", "Change this if the command \"/mm\" is already taken.");
		
		//formatting
		triggerAnimatedFormattingChar = config_core.getString("triggerAnimatedFormattingChar", FORMATTING, "\u00a7s", "String, to trigger animated formatting. Formatting is: \u00a7sPOSSTEP:DELAY:COLORS:STRING\u00a7s where  POSSTEP an integers is, DELAY a double is and COLORS an array of Minecraft-formatting-codes seperated by commas (-> https://minecraft.gamepedia.com/Formatting_codes).  (works only for tooltips)");
		triggerCtrlIsNotPressedFormatting = config_core.getString("triggerCtrlIsNotPressedFormatting", FORMATTING, "\u00a7Q", "String, to show the line only when 'Ctrl' is not pressed");
		triggerCtrlIsPressedFormatting = config_core.getString("triggerCtrlIsPressedFormatting", FORMATTING, "\u00a7q", "String, to show the line only when 'Ctrl' is pressed");
		triggerShiftIsNotPressedFormatting = config_core.getString("triggerShiftIsNotPressedFormatting", FORMATTING, "\u00a7P", "String, to show the line only when 'Shift' is not pressed");
		triggerShiftIsPressedFormatting = config_core.getString("triggerShiftIsPressedFormatting", FORMATTING, "\u00a7p", "String, to show the line only when 'Shift' is pressed");
		triggerAltIsNotPressedFormatting = config_core.getString("triggerAltIsNotPressedFormatting", FORMATTING, "\u00a7T", "String, to show the line only when 'Alt' is not pressed");
		triggerAltIsPressedFormatting = config_core.getString("triggerAltIsPressedFormatting", FORMATTING, "\u00a7t", "String, to show the line only when 'Alt' is pressed");
		enableFormattingDebugger = config_core.getBoolean("eanbleFormattingDebugger", FORMATTING, false, "Prints exceptions cought while formatting the tooltips to the console. this can be used to ensure that your tooltips are properly formatted. THIS WILL SPAM YOUR CONSOLE!");
	}
	
	private static void initDefaultsConfig() {
		config_defaults.addCustomCategoryComment(BLOCK, "Default values for Block objects. These are used when no value is set.");
		config_defaults.addCustomCategoryComment(COLOR, "Default values for Color objects. These are used when no value is set.");
		config_defaults.addCustomCategoryComment(FLUID, "Default values for Fluid objects. These are used when no value is set.");
		config_defaults.addCustomCategoryComment(ITEM, "Default values for Item objects. These are used when no value is set.");
		config_defaults.addCustomCategoryComment(MATERIAL, "Default values for Material objects. These are used when no other value is set.");
		config_defaults.addCustomCategoryComment(VARIANT, "Default values for OreVariant objects. These are used when no other value is set.");
		config_defaults.addCustomCategoryComment(VEIN, "Default values for OreVein objects. These are used when no other value is set.");
		config_defaults.addCustomCategoryComment(TYPE, "Default values for Type objects. These are used when no other value is set.");
		
		//block
		bEffectiveTool = config_defaults.getString("effectiveTool", BLOCK, "pickaxe", "Which tool the player has to use to break the block.");
		bEnabled = config_defaults.getBoolean("enabled", BLOCK, false, "Whether or not the block is enabled.");
		bHardness = config_defaults.getFloat("hardness", BLOCK, 5, -1, Integer.MAX_VALUE, "Hardness effects how long it takes to break the block. -1 means unbreakable.");
		bHardness = bHardness < 0 ? -1 : bHardness; //assures that every negative value is -1
		bHarvestLevel = config_defaults.getInt("harvestLevel", BLOCK, 1, 0, Integer.MAX_VALUE, "0 = wood, 1 = stone, 2 = iron, 3 = diamond");
		bIsBeaconBase = config_defaults.getBoolean("isBeaconBase", BLOCK, false, "Whether or not this block can be used as a beacon base.");
		bIsBeaconPayment = config_defaults.getBoolean("isBeaconPayment", BLOCK, false, "Whether or not the material can be used to activate a beacon.");
		bLightLevel = (byte) config_defaults.getInt("lightLevel", BLOCK, 0, 0, 15, "How much light the block emits.");
		bMapColor = config_defaults.getString("mapColor", BLOCK, "AUTO", "How the blocks appears on maps. See https://github.com/glowredman/modularmaterials/blob/new-api/presets/MATERIALS-SOUNDTYPES-MAPCOLORS.md");
		bMaterial = config_defaults.getString("materialSound", BLOCK, "IRON", "Use with caution, as the material can have unwanted interactions with other game-mechanics. See https://github.com/glowredman/modularmaterials/blob/new-api/presets/MATERIALS-SOUNDTYPES-MAPCOLORS.md");
		bObeysGravity = config_defaults.getBoolean("obeysGravity", BLOCK, false, "Whether or not the ore falls down, if there is no block under it.");
		bResistance = config_defaults.getFloat("resistance", BLOCK, 6, 0, Float.MAX_VALUE, "This effects how resistant the block is against explosions.");
		bSound = config_defaults.getString("sound", BLOCK, "METAL", "How the block sounds when breaking it, falling on it or walking on it. See https://github.com/glowredman/modularmaterials/blob/new-api/presets/MATERIALS-SOUNDTYPES-MAPCOLORS.md");
		bTexture = config_defaults.getString("texture", BLOCK, MODID + ":void", "The block's texture.");
		bUseColor = config_defaults.getBoolean("useColor", BLOCK, false, "Whether or not the texture should be colored.");
		
		//color
		cAlpha = config_defaults.getInt("alpha", COLOR, 0, 0, 255, "The color's alpha value.");
		cRed = config_defaults.getInt("red", COLOR, 255, 0, 255, "The color's red component.");
		cGreen = config_defaults.getInt("green", COLOR, 255, 0, 255, "The color's green component.");
		cBlue = config_defaults.getInt("blue", COLOR, 255, 0, 255, "The color's blue component.");
		
		//fluid
		fDensity = config_defaults.getInt("density", FLUID, 1000, Integer.MIN_VALUE, Integer.MAX_VALUE, "Unit is kg/m\u00b3, negative means lighter than air. (Water is 1000)");
		fEnabled = config_defaults.getBoolean("enabled", FLUID, false, "Whether or not the fluid is enabled.");
		fIsGaseous = config_defaults.getBoolean("isBoolean", FLUID, false, "Whether or not this fluid should be treated as a gas.");
		fLightLevel = (byte) config_defaults.getInt("lightLevel", FLUID, 0, 0, 15, "How much light the fluid emits.");
		fTemperature = config_defaults.getInt("temperature", FLUID, 293, Integer.MIN_VALUE, Integer.MAX_VALUE, "Unit is Kelvin (373K = 100\u00b0C = 212\u00b0F)");
		fTexture = config_defaults.getString("texture", FLUID, MODID + ":void", "The fluid's texture. \"_still\" or \"_flowing\" will be appended depending on the fluid's state.");
		fUseColor = config_defaults.getBoolean("useColor", FLUID, false, "Whether or not the texture should be colored.");
		fViscosity = config_defaults.getInt("viscosity", FLUID, 1000, 0, Integer.MAX_VALUE, "Unit is 10\u207b\u00b3m/s\u00b2, higher means the fluid moves more slowly (Water is 1000)");
		
		//item
		iEnabled = config_defaults.getBoolean("enabled", ITEM, false, "Wheter or not the item is enabled.");
		iIsBeaconPayment = config_defaults.getBoolean("isBeaconPayment", ITEM, false, "Whether or not the material can be used to activate a beacon.");
		iTexture = config_defaults.getString("texture", ITEM, MODID + ":void", "The item's texture.");
		iUseColor = config_defaults.getBoolean("useColor", ITEM, false, "Whether or not the texture should be colored.");
		
		//material
		mBlockHardness = config_defaults.getFloat("blockHardness", MATERIAL, 5, -1, Float.MAX_VALUE, "Hardness effects how long it takes to break the block. -1 means unbreakable.");
		mBlockHardness = mBlockHardness < 0 ? -1 : mBlockHardness; //assures that every negative value is -1
		mBlockHarvestLevel = config_defaults.getInt("blockHarvestLevel", MATERIAL, 1, 0, Integer.MAX_VALUE, "0 = wood, 1 = stone, 2 = iron, 3 = diamond");
		mBlockLightLevel = (byte) config_defaults.getInt("blockLightLevel", MATERIAL, 0, 0, 15, "How much light the block emits.");
		mBlockMapColor = config_defaults.getString("blockMapColor", MATERIAL, "AUTO", "How the blocks appears on maps. See https://github.com/glowredman/modularmaterials/blob/new-api/presets/MATERIALS-SOUNDTYPES-MAPCOLORS.md");
		mBlockMaterial = config_defaults.getString("blockMaterialSound", MATERIAL, "IRON", "Use with caution, as the material can have unwanted interactions with other game-mechanics. See https://github.com/glowredman/modularmaterials/blob/new-api/presets/MATERIALS-SOUNDTYPES-MAPCOLORS.md");
		mBlockResistance = config_defaults.getFloat("blockResistance", MATERIAL, 6, 0, Float.MAX_VALUE, "This effects how resistant the block is against explosions.");
		mBlockSound = config_defaults.getString("blockSound", MATERIAL, "METAL", "How the block sounds when breaking it, falling on it or walking on it. See https://github.com/glowredman/modularmaterials/blob/new-api/presets/MATERIALS-SOUNDTYPES-MAPCOLORS.md");
		mBoilingTemperature = config_defaults.getInt("boilingTemperature", MATERIAL, 373, Integer.MIN_VALUE, Integer.MAX_VALUE, "Unit is Kelvin (373K = 100\u00b0C = 212\u00b0F)");
		mEnabled = config_defaults.getBoolean("enabled", MATERIAL, false, "Whether or not the material is enabled.");
		mTypeEnabled = config_defaults.getBoolean("typeEnabled", MATERIAL, false, "Wheter or not the types are enabled.");
		mGasDenisity = config_defaults.getInt("gasDensity", MATERIAL, -1000, Integer.MIN_VALUE, Integer.MAX_VALUE, "Unit is kg/m\u00b3, negative means lighter than air. (Water is 1000)");
		mGasLightLevel = (byte) config_defaults.getInt("gasLightLevel", MATERIAL, 0, 0, 15, "How much light the gas emits.");
		mGasViscosity = config_defaults.getInt("gasViscosity", MATERIAL, 0, 0, Integer.MAX_VALUE, "Unit is 10\u207b\u00b3m/s\u00b2, higher means the fluid moves more slowly (Water is 1000)");
		mIsBeaconBase = config_defaults.getBoolean("isBeaconBase", MATERIAL, false, "Whether or not the material can be used as a beacon base.");
		mIsBeaconPayment = config_defaults.getBoolean("isBeaconPayment", MATERIAL, false, "Whether or not the material can be used to activate a beacon.");
		mLiquidDensity = config_defaults.getInt("liquidDensity", MATERIAL, 1000, Integer.MIN_VALUE, Integer.MAX_VALUE, "Unit is kg/m\u00b3, negative means lighter than air. (Water is 1000)");
		mLiquidLightLevel = (byte) config_defaults.getInt("liquidLightLevel", MATERIAL, 0, 0, 15, "How much light the liquid emits.");
		mLiquidViscosity = config_defaults.getInt("liquidViscosity", MATERIAL, 1000, Integer.MIN_VALUE, Integer.MAX_VALUE, "Unit is 10\u207b\u00b3m/s\u00b2, higher means the fluid moves more slowly (Water is 1000)");
		mMeltingTemperature = config_defaults.getInt("meltingTemperature", MATERIAL, 273, Integer.MIN_VALUE, Integer.MAX_VALUE, "Unit is Kelvin (273K = 0\u00b0C = 32\u00b0F)");
		mOreHardness = config_defaults.getFloat("oreHardness", MATERIAL, 3, -1, Float.MAX_VALUE, "Hardness effects how long it takes to break the ore. -1 means unbreakable.");
		mOreHardness = mOreHardness < 0 ? -1 : mOreHardness; //assures that every negative value is -1
		mOreHarvestLevel = config_defaults.getInt("oreHarvestLevel", MATERIAL, 1, 0, Integer.MAX_VALUE, "0 = wood, 1 = stone, 2 = iron, 3 = diamond");
		mOreLightLevel = (byte) config_defaults.getInt("oreLightLevel", MATERIAL, 0, 0, 15, "How much light the ore emits.");
		mOreResistance = config_defaults.getFloat("oreResistance", MATERIAL, 0, 0, Float.MAX_VALUE, "");
		mState = config_defaults.getString("state", MATERIAL, "solid", "The physical state the material has at room temperature.");
		mTemperature = config_defaults.getInt("temperature", MATERIAL, 293, Integer.MIN_VALUE, Integer.MAX_VALUE, "Unit is Kelvin (293K = 20\u00b0C = 68\u00b0F)");
		mTexture = config_defaults.getString("texture", MATERIAL, "metallic", "Which texture set to use");
		
		//oreVariant
		oBaseBlock = config_defaults.getString("baseBlock", VARIANT, "stone:0", "Which block the ore replaces. Vanilla blocks don't need the \"minecraft:\"-prefix. If the metadata is left out, all blockstates of that block will be used (example: \"stone\" -> Stone, (Polished) Andesite, (Polished) Granite, (Polished) Diorite)");
		oBaseTexture = config_defaults.getString("baseTexture", VARIANT, "blocks/stone", "The baseBlock's texture. Vanilla textures don't need the \"minecraft\"-prefix.");
		oEffectiveTool = config_defaults.getString("effectiveTool", VARIANT, "pickaxe", "Which tool the player has to use to break the ore.");
		oEnabled = config_defaults.getBoolean("enabled", VARIANT, false, "Whether or not the ore variant is enabled.");
		oMapColor = config_defaults.getString("mapColor", VARIANT, "STONE", "How the blocks appears on maps. See https://github.com/glowredman/modularmaterials/blob/new-api/presets/MATERIALS-SOUNDTYPES-MAPCOLORS.md");
		oMaterial = config_defaults.getString("material", VARIANT, "ROCK", "Use with caution, as the material can have unwanted interactions with other game-mechanics. See https://github.com/glowredman/modularmaterials/blob/new-api/presets/MATERIALS-SOUNDTYPES-MAPCOLORS.md");
		oObeysGravity = config_defaults.getBoolean("obeysGravity", VARIANT, false, "Whether or not the ore falls down, if there is no block under it.");
		oOreDictPrefix = config_defaults.getString("oreDictPrefix", VARIANT, "", "The ores will be registered to the OreDictionary in this format: type's oreDictPrefix + oreVariant's oreDictPrefix + material's oreDictName");
		oSound = config_defaults.getString("sound", VARIANT, "STONE", "How the block sounds when breaking it, falling on it or walking on it. See https://github.com/glowredman/modularmaterials/blob/new-api/presets/MATERIALS-SOUNDTYPES-MAPCOLORS.md");
		oSyntax = config_defaults.getString("syntax", VARIANT, "%s Ore", "%s will be replaced by the material's name when generating the lang file.");
		
		//type
		tCategory = config_defaults.getString("category", TYPE, "item", "Allowed values: block, fluid, item, ore", new String[] {"item", "block", "fluid", "ore"});
		tEffectiveTool = config_defaults.getString("effectiveTool", TYPE, "pickaxe", "Which tool the player has to use to break the block/ore.");
		tEnabled = config_defaults.getBoolean("enabled", TYPE, false, "Whether or not the type is enabled.");
		tHasTooltip = config_defaults.getBoolean("hasTooltip", TYPE, true, "Whether or not the type's tooltip is displayed.");
		tIsBeaconBase = config_defaults.getBoolean("isBeaconBase", TYPE, false, "Whether or not the type can be used as a beacon base.");
		tOreDictPrefix = config_defaults.getString("oreDictPrefix", TYPE, "", "The prefix to use before the material's oreDict name.");
		tState = config_defaults.getString("state", TYPE, "solid", "The type's physical state.");
		tSyntax = config_defaults.getString("syntax", TYPE, "%s", "%s will be replaced by the material's name when generating the lang file.");
		tUnitValue = config_defaults.getString("unitValue", TYPE, "1u", "How much the type is \"worth\" in units.");
		
		//oreVein
		vBiomes = config_defaults.getStringList("biomes", VEIN, new String[] {}, "All biomes the ore vein is allowed to spawn in. Can be inverted.");
		vDensity = (short) config_defaults.getInt("density", VEIN, 2, 1, Short.MAX_VALUE, "How dense the ore vein is, higher means higher density.");
		vDimensions = config_defaults.getStringList("dimensions", VEIN, new String[] {"overworld"}, "All dimensions the ore vein is allowed to spawn in. Can be inverted.");
		vEnabled = config_defaults.getBoolean("enabled", VEIN, false, "Whether the ore vein is enabled.");
		vInbetween = config_defaults.getString("inbetween", VEIN, "boron", "The ore that spawns in the middle layer and the layer above. Only ores enabled in " + CONFIGNAME_MATERIALS + " are permitted.");
		vInvertBiomes = config_defaults.getBoolean("invertBiomes", VEIN, true, "If true, \"biomes\" acts as a blacklist, if false, as a whitelist.");
		vInvertDimensions = config_defaults.getBoolean("invertDimensions", VEIN, false, "If true, \"dimensions\" acts as a blacklist, if false, as a whitelist.");
		vMaxHeight = (short) config_defaults.getInt("maxHeight", VEIN, 250, 0, 255, "The maximum height the vein can spawn at.");
		vMinHeight = (short) config_defaults.getInt("minHeight", VEIN, 5, 0, 255, "The minimum height the vein can spawn at.");
		vPrimary = config_defaults.getString("primary", VEIN, "lithium", "The ore that spawns in the three upper layers. Only ores enabled in " + CONFIGNAME_MATERIALS + " are permitted.");
		vSecondary = config_defaults.getString("secondary", VEIN, "beryllium", "The ore that spawns in the lower three layers. Only ores enabled in " + CONFIGNAME_MATERIALS + " are permitted.");
		vSize = (short) config_defaults.getInt("size", VEIN, 32, 1, Short.MAX_VALUE, "How many blocks the ore vein extends at most in each direction from the center chunk. The final size is random and between 16 and 2 * size + 16. CAUTION: higher values can lead to more world cascading lag or infinite chunk generation loops!");
		vSporadic = config_defaults.getString("sporadic", VEIN, "carbon", "The ore that spawns sporadicly throughout the whole ore vein.");
		vWeight = (short) config_defaults.getInt("weight", VEIN, 100, 0, Short.MAX_VALUE, "How likely the ore vein generates. A higher value means the ore vein attempts to generate more often relative to ore veins with a lower value. How easy it is to find the ore vein is influenced by the allowed biomes/dimensions, as well as the min/max height.");
	}
	
	// ---READ CONFIGS---
	private static void readCoreConfig() {
		try {
			config_core.load();
			initCoreConfig();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			updateConfig(config_core);
		}
	}
	
	private static void readDefaultsConfig() {
		try {
			config_defaults.load();
			initDefaultsConfig();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			updateConfig(config_defaults);
		}
	}
	
	// ---UPDATE CONFIGS---
	private static void updateConfig(Configuration cfg) {
		if (cfg.hasChanged()) {
			cfg.save();
		}
	}

}
