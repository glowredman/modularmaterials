package glowredman.modularmaterials;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import glowredman.modularmaterials.block.MetaBlock;
import glowredman.modularmaterials.data.JSONHandler;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_Type;
import glowredman.modularmaterials.item.MetaItem;

public class MM_Reference {
	
	//Mod
	public static final String MODID = "modularmaterials";
	
	//Formatting
	public static String triggerShiftIsPressedFormatting = "\u00a7p";
	public static String triggerShiftIsNotPressedFormatting = "\u00a7P";
	public static String triggerCtrlIsPressedFormatting = "\u00a7q";
	public static String triggerCtrlIsNotPressedFormatting = "\u00a7Q";
	public static String triggerAltIsPressedFormatting = "\u00a7t";
	public static String triggerAltIsNotPressedFormatting = "\u00a7T";
	public static String triggerAnimatedFormattingChar = "\u00a7s";
	public static boolean enableFormattingDebugger = false;
	
	//Config
	public static boolean enableAll = false;
	public static boolean overrideModelFiles = false;
	public static boolean cleanDataDir = true;
	public static boolean oresInheritHardness = true;
	public static boolean oresInheritHarvestLevel = false;
	public static boolean oresInheritLightLevel = false;
	public static boolean oresInheritResistance = true;
	
	
	public static final Map<String, MM_Type> TYPES = JSONHandler.getTypes();
	public static final Map<String, MM_Material> MATERIALS = JSONHandler.getMaterials();
	public static final List<MetaItem> ITEMS = new LinkedList<>();
	public static final List<MetaBlock> BLOCKS = new LinkedList<>();

}
