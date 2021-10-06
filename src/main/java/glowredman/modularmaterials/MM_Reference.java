package glowredman.modularmaterials;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import glowredman.modularmaterials.block.MetaBlock;
import glowredman.modularmaterials.data.JSONHandler;
import glowredman.modularmaterials.data.object.MM_Config;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_Type;
import glowredman.modularmaterials.data.object.RandomSelector;
import glowredman.modularmaterials.fluid.MetaFluid;
import glowredman.modularmaterials.item.MetaBucketItem;
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
	
	//Mod
	public static final String MODID = "modularmaterials";
	public static final MM_Config CONFIG = MM_Config.read();
	public static final Random RAND = RandomSelector.get(CONFIG.randomizer);
	
	//Object Holders
	public static final Map<String, MM_Type> TYPES = JSONHandler.getTypes();
	public static final Map<String, MM_Material> MATERIALS = JSONHandler.getMaterials();
	public static final List<MetaItem> ITEMS = new LinkedList<>();
	public static final List<MetaBucketItem> BUCKETS = new LinkedList<>();
	public static final List<MetaBlock> BLOCKS = new LinkedList<>();
	public static final List<MetaFluid> FLUIDS = new LinkedList<>();

}
