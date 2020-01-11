package glowredman.modularmaterials.util;

import java.io.File;

import glowredman.modularmaterials.information.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler {
	
	// category names
	public static final String GENERAL = "general";
	public static final String OREDICT = "oreDictionary";
	public static final String TYPES = "types";
	
	public static void preInit1(FMLPreInitializationEvent event) {
		
		//create config file(s)
		Reference.generalConfig = new Configuration(new File(event.getModConfigurationDirectory().getPath() + "/" + Reference.MODID, Reference.GENERALCONFIGNAME));
	}
	
	public static void preInit2() {
		if (Reference.generalConfig.hasChanged()) {
			Reference.generalConfig.save();
		}
	}
	
	public static void readConfig() {
		Configuration general = Reference.generalConfig;
		try {
			general.load();
			initGeneralConfig(general);
		} 
		catch (Exception e) {
		} 
		finally {
			if (general.hasChanged()) {
				general.save();
			}
		}
	}
	
	private static void initGeneralConfig(Configuration cfg) {
		cfg.addCustomCategoryComment(GENERAL, "");
		cfg.addCustomCategoryComment(TYPES, "");
		
		//general
		Reference.createModelFiles = cfg.getBoolean("createModelFiles", GENERAL, false, "Creates model files for all different texture-sets.");
		Reference.defaultDensityOffset = cfg.getInt("defaultDensityOffset", GENERAL, -10000, Integer.MIN_VALUE, 0, "Density of Water is 1000. Values for gaseous fluids must be negative, although they are not negative in reality. To transform positive number into negative ones, the density value of all gases will be offset by this number. Set to 0 to disable this behaviour.");
		Reference.defaultMoltenMaterialLightLevel = cfg.getInt("defaultMoltenMaterialLightLevel", GENERAL, 12, 0, 15, "The default light-level of all molten fluids (like \"Molten Iron\")");
		Reference.dumpLang = cfg.getBoolean("dumpLangFile", GENERAL, false, "Dumps a lang file to the ./config/" + Reference.MODID + " containing all unlocalized names of all possible items/blocks/fluids (only of materials specified in " + Reference.MATERIALCONFIGNAME + ")");
		Reference.enableAll = cfg.getBoolean("enableAll", GENERAL, false, "Set to 'true' to ignore ALL other restrictions -> all items/blocks/fluids will be enabled");
		Reference.normalTemp = cfg.getInt("normalTemp", GENERAL, 288, Integer.MIN_VALUE, Integer.MAX_VALUE, "\"Room temperature\": temperature all fluids will have, if they aren't cooled/heated (Minecraft default is 300)");
		Reference.registerUnitOreDictionary = cfg.getBoolean("registerUnitOreDictionary", GENERAL, true, "If set to true, every item will get additional OreDictionary entries (example: ingotIron = 1uIron = plateIron; nuggetIron = 1/9uIron). The value for each type of item can be configured (see category \"" + OREDICT + "\"). This system can be used, for example, when smelting different items back into nuggets/ingots.");
		
		//oreDict
		Reference.unitBlock = cfg.getString("unitBlock", OREDICT, "9u", "");
		Reference.unitIngot = cfg.getString("unitIngot", OREDICT, "1u", "");
		Reference.unitPlate = cfg.getString("unitPlate", OREDICT, "1u", "");
		
		//types
		Reference.enableBlocks = cfg.getBoolean("enableBlocks", TYPES, true, "");
		Reference.enableFluids = cfg.getBoolean("enableFluids", TYPES, true, "");
		Reference.enableIngots = cfg.getBoolean("enableIngots", TYPES, true, "");
		Reference.enableMiscItems = cfg.getBoolean("enableMiscItems", TYPES, true, "");
		Reference.enablePlates = cfg.getBoolean("enablePlates", TYPES, true, "");
	}
	
}
