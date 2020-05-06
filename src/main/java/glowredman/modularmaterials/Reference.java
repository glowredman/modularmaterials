package glowredman.modularmaterials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import glowredman.modularmaterials.item.MetaItem;
import glowredman.modularmaterials.object.Material;
import glowredman.modularmaterials.object.Type;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Configuration;

public class Reference {
	
	//MOD INFORMATION
	public static final String MCVERSION = "1.12.2";
	public static final String MODDEPENDENCIES = "required-after:resourceloader";
	public static final String MODID = "modularmaterials";
	public static final String MODNAME = "Modular Materials";
	public static final String MODVERSION = "0.1";
	public static final String CLIENT = "glowredman.modularmaterials.proxy.ClientProxy";
	public static final String SERVER = "glowredman.modularmaterials.proxy.CommonProxy";
	
	//CREATIVE TABS
	public static final CreativeTabs TAB_ITEMS = CreativeTabs.MATERIALS; //TODO
	
	//CONFIG
	public static Configuration config_core;
	
	//files:
	public static final String CONFIGNAME_CORE = "core.cfg";
	public static final String MATERIALCONFIGNAME = "materials.json";
	public static final String TYPECONFIGNAME = "types.json";
	
	//values:
	//general
	public static boolean enableAll = false;
	public static boolean overrideModelFiles = false;
	public static boolean overrideLangFile = false;
	public static boolean enableUnitOreDict = true;
	
	//formatting
	public static String triggerShiftIsPressedFormatting = "§p";
	public static String triggerShiftIsNotPressedFormatting = "§P";
	public static String triggerCtrlIsPressedFormatting = "§q";
	public static String triggerCtrlIsNotPressedFormatting = "§Q";
	public static String triggerAltIsPressedFormatting = "§t";
	public static String triggerAltIsNotPressedFormatting = "§T";
	public static String triggerAnimatedFormattingChar = "§s";
	public static float animatedFormattingDelayFallback = 0.001f;
	public static boolean enableFormattingDebugger = false;
	
	//INTERNAL
	public static LinkedHashMap<String, Material> materials = new LinkedHashMap<String, Material>();
	public static HashMap<Integer, String> idMapping = new HashMap<Integer, String>(); 
	public static HashMap<String, Type> types = new HashMap<String, Type>();
	public static List<MetaItem> metaItems = new ArrayList<MetaItem>();
}
