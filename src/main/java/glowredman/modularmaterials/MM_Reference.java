package glowredman.modularmaterials;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;

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
	public static final String SHIFT_PRESSED_FORMATTING = "\u00a7p";
	public static final String SHIFT_NOT_PRESSED_FORMATTING = "\u00a7P";
	public static final String CTRL_PRESSED_FORMATTING = "\u00a7q";
	public static final String CTRL_NOT_PRESSED_FORMATTING = "\u00a7Q";
	public static final String ALT_PRESSED_FORMATTING = "\u00a7t";
	public static final String ALT_NOT_PRESSED_FORMATTING = "\u00a7T";
	public static final String ANIMATED_FORMATTING = "\u00a7s";
	public static final Pattern COLON_SPLITTER = Pattern.compile(":");
	
	//Mod
	public static final String MODID = "modularmaterials";
	public static final MM_Config CONFIG = MM_Config.read();
	public static final Function<Long, Random> NEW_RAND = CONFIG.getRandom();
	public static final Random RAND = NEW_RAND.apply(System.nanoTime());
	
	//Object Holders
	public static final Map<String, MM_Type> TYPES = JSONHandler.getTypes();
	public static final Map<String, MM_Material> MATERIALS = JSONHandler.getMaterials();
	public static final Map<String, MM_OreVariant> ORE_VARIANTS = JSONHandler.getOreVariants();
	public static final Map<String, MM_OreVein> ORE_VEINS = JSONHandler.getOreVeins();
	public static final Set<String> PRESETS = PresetHandler.getPresets();
	public static final Set<MetaItem> ITEMS = new LinkedHashSet<>();
	public static final Set<MetaBucketItem> BUCKETS = new LinkedHashSet<>();
	public static final Set<MetaBlock> BLOCKS = new LinkedHashSet<>();
	public static final Set<MetaFluid> FLUIDS = new LinkedHashSet<>();
	public static final Table<Block, String, IMetaOre> ORES = HashBasedTable.create();

}
