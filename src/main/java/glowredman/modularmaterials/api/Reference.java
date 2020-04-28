package glowredman.modularmaterials.api;

import net.minecraftforge.common.config.Configuration;

public class Reference {
	
	//MOD INFORMATION
	public static final String MCVERSION = "1.12.2";
	public static final String MODDEPENDENCIES = ""; //TODO have to figure a replacement for this out: required-after:*
	public static final String MODID = "modularmaterials";
	public static final String MODNAME = "Modular Materials";
	public static final String MODVERSION = "0.1";
	public static final String CLIENT = "glowredman.modularmaterials.proxy.ClientProxy";
	public static final String SERVER = "glowredman.modularmaterials.proxy.CommonProxy";
	
	//CONFIG
	//files:
	public static Configuration config_core;
	public static final String CONFIGNAME_CORE = "core.cfg";
	
	//values:
	//general
	public static boolean enableAll = false;
	
	public static String triggerKeyPressedFormattingChar = "§p";
	public static String triggerKeyNotPressedFormattingChar = "§q";
	public static String triggerAnimatedFormattingChar = "§s";

}
