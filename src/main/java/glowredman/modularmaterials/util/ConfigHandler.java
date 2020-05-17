package glowredman.modularmaterials.util;

import static glowredman.modularmaterials.Reference.*;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler {
	
	// ---CATEGORIES---
	public static final String GENERAL = "general";
	public static final String FORMATTING = "formatting";
	
	// ---PHASES---
	public static void initConfigs(FMLPreInitializationEvent event) {
		config_core = new Configuration(new File(event.getModConfigurationDirectory().getPath() + "/" + MODID, CONFIGNAME_CORE));
	}
	
	public static void readConfigs() {
		readCoreConfig();
	}
	
	public static void saveConfigs() {
		updateConfig(config_core);
	}
	
	// ---INIT CONFIGS---
	private static void initCoreConfig() {
		config_core.addCustomCategoryComment(GENERAL, "");
		config_core.setCategoryRequiresMcRestart(GENERAL, true);
		
		//general
		enableAll = config_core.getBoolean("enableAll", GENERAL, false, "Set to 'true' to ignore ALL other restrictions -> all items/blocks/fluids will be enabled. USE WITH CAUTION!");
		overrideModelFiles = config_core.getBoolean("overrideModelFiles", GENERAL, false, "Set to true to regenerate all files in the /resources/models/-directory. ATTENTION: requires an additional restart to create the model files!");
		overrideLangFile = config_core.getBoolean("overrideLangFile", GENERAL, false, "Set to true to regenerate the lang-file. ATTENTION: requires an additional restart to create the model files!");
		enableUnitOreDict = config_core.getBoolean("enableUnitOreDict", GENERAL, true, "Enables a unit-based oreDict system where each type is a specified amount worth (for example ingots, dusts, plates are 1u, nuggets 1/9u and blocks 9u). This allows for more precise specification of inputs (for example \"1uCopper\" could be used as an input in the Alloy Smelter because what difference makes the appearance of a material if it will be molten?)");
		suppressTypeMissingWarnings = config_core.getBoolean("suppressTypeMissingWarnings", GENERAL, false, "Set to 'true' to suppress any warnings, that indicate missing information in the " + CONFIGNAME_TYPES + "-config.");
		
		//formatting
		triggerAnimatedFormattingChar = config_core.getString("triggerAnimatedFormattingChar", FORMATTING, "\u00a7s", "String, to trigger animated formatting. Formatting is: \u00a7sPOSSTEP:DELAY:COLORS:STRING\u00a7s where STEP and POSSTEP integers are, DELAY a double is and COLORS an array of Minecraft-formatting-codes seperated by commas (-> https://minecraft.gamepedia.com/Formatting_codes).  (works only for tooltips)");
		triggerCtrlIsNotPressedFormatting = config_core.getString("triggerCtrlIsNotPressedFormatting", FORMATTING, "\u00a7Q", "String, to show the line only when 'Ctrl' is not pressed");
		triggerCtrlIsPressedFormatting = config_core.getString("triggerCtrlIsPressedFormatting", FORMATTING, "\u00a7q", "String, to show the line only when 'Ctrl' is pressed");
		triggerShiftIsNotPressedFormatting = config_core.getString("triggerShiftIsNotPressedFormatting", FORMATTING, "\u00a7P", "String, to show the line only when 'Shift' is not pressed");
		triggerShiftIsPressedFormatting = config_core.getString("triggerShiftIsPressedFormatting", FORMATTING, "\u00a7p", "String, to show the line only when 'Shift' is pressed");
		triggerAltIsNotPressedFormatting = config_core.getString("triggerAltIsNotPressedFormatting", FORMATTING, "\u00a7T", "String, to show the line only when 'Alt' is not pressed");
		triggerAltIsPressedFormatting = config_core.getString("triggerAltIsPressedFormatting", FORMATTING, "\u00a7t", "String, to show the line only when 'Alt' is pressed");
		animatedFormattingDelayFallback = config_core.getFloat("animatedFormattingDelayFallback", FORMATTING, 0.001f, 1E-45f, Float.MAX_VALUE, "If the delay set when using animated formatting is below or equal to 0, it is set to this value.");
		enableFormattingDebugger = config_core.getBoolean("eanbleFormattingDebugger", FORMATTING, false, "Prints exceptions cought while formatting the tooltips to the console. this can be used to ensure that your tooltips are properly formatted. THIS WILL SPAM YOUR CONSOLE!");
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
	
	// ---UPDATE CONFIGS---
	private static void updateConfig(Configuration cfg) {
		if (cfg.hasChanged()) {
			cfg.save();
		}
	}

}
