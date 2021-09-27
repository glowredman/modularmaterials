package glowredman.modularmaterials;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import glowredman.modularmaterials.block.MetaBlock;
import glowredman.modularmaterials.data.JSONHandler;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_Type;
import glowredman.modularmaterials.data.object.RandomSelector;
import glowredman.modularmaterials.item.MetaItem;

public class MM_Reference {
	
	//Formatting
	public static String triggerShiftIsPressedFormatting = "\u00a7p";
	public static String triggerShiftIsNotPressedFormatting = "\u00a7P";
	public static String triggerCtrlIsPressedFormatting = "\u00a7q";
	public static String triggerCtrlIsNotPressedFormatting = "\u00a7Q";
	public static String triggerAltIsPressedFormatting = "\u00a7t";
	public static String triggerAltIsNotPressedFormatting = "\u00a7T";
	public static String triggerAnimatedFormatting = "\u00a7s";
	public static boolean enableFormattingDebugger = false;
	
	//Config
	public static boolean enableAll = false;
	public static RandomSelector randomizer = RandomSelector.XSTR;
	public static int commandPermissionLevel = 2;
	public static boolean overrideModelFiles = true;
	public static boolean overrideBlockstateFiles = true;
	public static boolean overrideTagFiles = true;
	public static boolean overrideLootTableFiles = true;
	public static boolean blocksHaveFoilEffect = true;
	public static boolean fluidContainersHaveFoilEffect = true;
	public static boolean oresInheritHardness = true;
	public static boolean oresInheritHarvestLevel = false;
	public static boolean oresInheritLightLevel = false;
	public static boolean oresInheritResistance = true;
	
	//Object Holders
	public static final Map<String, MM_Type> TYPES = JSONHandler.getTypes();
	public static final Map<String, MM_Material> MATERIALS = JSONHandler.getMaterials();
	public static final List<MetaItem> ITEMS = new LinkedList<>();
	public static final List<MetaBlock> BLOCKS = new LinkedList<>();
	
	//Mod
	public static final String MODID = "modularmaterials";
	public static final Random RAND = randomizer.get();

}
