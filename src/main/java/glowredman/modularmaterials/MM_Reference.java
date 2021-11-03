package glowredman.modularmaterials;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import glowredman.modularmaterials.block.IMetaOre;
import glowredman.modularmaterials.block.MetaBlock;
import glowredman.modularmaterials.data.JSONHandler;
import glowredman.modularmaterials.data.PresetHandler;
import glowredman.modularmaterials.data.object.MM_Config;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_OreVariant;
import glowredman.modularmaterials.data.object.MM_OreVein;
import glowredman.modularmaterials.data.object.MM_Type;
import glowredman.modularmaterials.fluid.MetaFluid;
import glowredman.modularmaterials.item.MetaBucketItem;
import glowredman.modularmaterials.item.MetaItem;
import net.minecraft.world.level.block.Block;

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
	public static final Function<Long, Random> NEW_RAND = CONFIG.random.get();
	public static final Random RAND = NEW_RAND.apply(System.nanoTime());
	
	//Object Holders
	public static final Map<String, MM_Type> TYPES = JSONHandler.getTypes();
	public static final Map<String, MM_Material> MATERIALS = JSONHandler.getMaterials();
	public static final Map<String, MM_OreVariant> ORE_VARIANTS = JSONHandler.getOreVariants();
	public static final Map<String, MM_OreVein> ORE_VEINS = JSONHandler.getOreVeins();
	public static final List<String> PRESETS = PresetHandler.getPresets();
	public static final List<MetaItem> ITEMS = new LinkedList<>();
	public static final List<MetaBucketItem> BUCKETS = new LinkedList<>();
	public static final List<MetaBlock> BLOCKS = new LinkedList<>();
	public static final List<MetaFluid> FLUIDS = new LinkedList<>();
	public static final Table<Block, String, IMetaOre> ORES = HashBasedTable.create();

}
