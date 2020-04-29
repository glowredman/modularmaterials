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
		
		//general
		enableAll = config_core.getBoolean("enableAll", GENERAL, false, "Set to 'true' to ignore ALL other restrictions -> all items/blocks/fluids will be enabled. USE WITH CAUTION!");
		triggerAnimatedFormattingChar = config_core.getString("triggerAnimatedFormattingChar", GENERAL, "§s", "String, that has to be put behind '§' to trigger animated formatting (works only for tooltips)"); //TODO document syntax
		triggerKeyNotPressedFormattingChar = config_core.getString("triggerKeyNotPressedFormattingChar", GENERAL, "§q", "String, that has to be put behind '§' to trigger is-key-not-pressed-sensitive formatting (works only for tooltips)");
		triggerKeyPressedFormattingChar = config_core.getString("triggerKeyPressedFormattingChar", GENERAL, "§p", "String, that has to be put behined '§' to trigger is-key-pressed-sensitive formatting (works only for tooltips");
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
