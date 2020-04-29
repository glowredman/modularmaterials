package glowredman.modularmaterials.util;

import static glowredman.modularmaterials.Reference.*;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler {
	
	// ---CATEGORIES---
	public static final String GENERAL = "general";
	
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
		overrideLangFile = config_core.getBoolean("overrideLangFile", GENERAL, false, "Set to true to regenerate the lang-file.");
		triggerAnimatedFormattingChar = config_core.getString("triggerAnimatedFormattingChar", GENERAL, "§s", "String, that has to be put behind '§' to trigger animated formatting. Formatting is: §sSTEP|POSSTEP|DELAY|COLORS:STRING§s where STEP and POSSTEP integers are, DELAY a double is and COLORS an array of Minecraft-formatting-codes seperated by commas (-> https://minecraft.gamepedia.com/Formatting_codes).  (works only for tooltips)");
		triggerKeyNotPressedFormattingChar = config_core.getString("triggerKeyNotPressedFormattingChar", GENERAL, "§q", "String, that has to be put behind '§' to trigger is-key-not-pressed-sensitive formatting. Formatting is: §qKEYCODE:STRING§q where KEYCODE is an integer (-> https://minecraft.gamepedia.com/Key_codes#Full_table). (works only for tooltips)");
		triggerKeyPressedFormattingChar = config_core.getString("triggerKeyPressedFormattingChar", GENERAL, "§p", "String, that has to be put behined '§' to trigger is-key-pressed-sensitive formatting. Formatting is: §pKEYCODE:STRING§p where KEYCODE is an integer (-> https://minecraft.gamepedia.com/Key_codes#Full_table). (works only for tooltips");
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
	
	// ------
	private static void updateConfig(Configuration cfg) {
		if (cfg.hasChanged()) {
			cfg.save();
		}
	}

}
