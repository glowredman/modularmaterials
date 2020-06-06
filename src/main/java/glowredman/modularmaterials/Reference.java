package glowredman.modularmaterials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import glowredman.modularmaterials.block.MetaBlock;
import glowredman.modularmaterials.block.MetaOre;
import glowredman.modularmaterials.block.MetaOreFalling;
import glowredman.modularmaterials.item.MetaItem;
import glowredman.modularmaterials.object.Material;
import glowredman.modularmaterials.object.OreVariant;
import glowredman.modularmaterials.object.OreVein;
import glowredman.modularmaterials.object.Type;
import glowredman.modularmaterials.tab.CreativeTabBlocks;
import glowredman.modularmaterials.tab.CreativeTabItems;
import glowredman.modularmaterials.tab.CreativeTabOres;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;

public class Reference {
	
	//MOD INFORMATION
	public static final String MCVERSION = "1.12.2";
	public static final String MODDEPENDENCIES = "required-after:resourceloader";
	public static final String MODID = "modularmaterials";
	public static final String MODNAME = "Modular Materials";
	public static final String MODVERSION = "0.3-alpha";
	public static final String CLIENT = "glowredman.modularmaterials.proxy.ClientProxy";
	public static final String SERVER = "glowredman.modularmaterials.proxy.CommonProxy";
	
	//CREATIVE TABS
	public static final CreativeTabs TAB_BLOCKS = new CreativeTabBlocks();
	public static final CreativeTabs TAB_ITEMS = new CreativeTabItems();
	public static final CreativeTabs TAB_ORES = new CreativeTabOres();
	
	//CONFIG
	public static Configuration config_core;
	public static Configuration config_defaults;
	
	//files:
	public static final String CONFIGNAME_CORE = "core.cfg";
	public static final String CONFIGNAME_DEFAULTS = "defaults.cfg";
	public static final String CONFIGNAME_MATERIALS = "materials.json";
	public static final String CONFIGNAME_TYPES = "types.json";
	public static final String CONFIGNAME_OREVARIANTS = "oreVariants.json";
	public static final String CONFIGNAME_OREGENERATION = "oreGeneration.json";
	
	//values:
	//core general
	public static boolean enableAll = false;
	public static boolean overrideModelFiles = false;
	public static boolean overrideLangFile = false;
	public static boolean overrideBlockStateFiles = false;
	public static boolean enableUnitOreDict = true;
	public static boolean suppressTypeMissingWarnings = false;
	public static boolean enableOreGen = true;
	public static int modGenerationWeight = 0;
	public static boolean disableVanillaOreGen = true;
	public static float veinGenerationProbability = 0.75f;
	
	//core formatting
	public static String triggerShiftIsPressedFormatting = "§p";
	public static String triggerShiftIsNotPressedFormatting = "§P";
	public static String triggerCtrlIsPressedFormatting = "§q";
	public static String triggerCtrlIsNotPressedFormatting = "§Q";
	public static String triggerAltIsPressedFormatting = "§t";
	public static String triggerAltIsNotPressedFormatting = "§T";
	public static String triggerAnimatedFormattingChar = "§s";
	public static float animatedFormattingDelayFallback = 0.001f;
	public static boolean enableFormattingDebugger = false;
	
	//defaults color
	public static int cAlpha = 0;
	public static int cRed = 255;
	public static int cGreen = 255;
	public static int cBlue = 255;
	
	//defaults material
	public static float mBlockHardness = 5.0f;
	public static int mBlockHarvestLevel = 1;
	public static byte mBlockLightLevel = 0;
	public static String mBlockMaterialSound = "IRON";
	public static float mBlockResistance = 6.0f;
	public static int mBoilingTemperature = 373;
	public static boolean mEnabled = false;
	public static boolean mTypeEnabled = false;
	public static int mGasDenisity = -1000;
	public static byte mGasLightLevel = 0;
	public static int mGasViscosity = 1;
	public static boolean mIsBeaconBase = false;
	public static boolean mIsBeaconPayment = false;
	public static int mLiquidDensity = 1000;
	public static byte mLiquidLightLevel = 0;
	public static int mLiquidViscosity = 1000;
	public static int mMeltingTemperature = 273;
	public static float mOreHardness = 3.0f;
	public static int mOreHarvestLevel = 1;
	public static byte mOreLightLevel = 0;
	public static float mOreResistance = 0.0f;
	public static String mState = "solid";
	public static int mTemperature = 293;
	public static String mTexture = "metallic";
	
	//defaults oreVariant
	public static String oBaseBlock = "stone:0";
	public static String oBaseTexture = "blocks/stone";
	public static String oEffectiveTool = "pickaxe";
	public static boolean oEnabled = false;
	public static String oMaterialSound = "ROCK";
	public static boolean oObeysGravity = false;
	public static String oOreDictPrefix = "";
	public static String oSyntax = "%s Ore";
	
	//defaults oreVein
	public static String vPrimary = "lithium";
	public static String vSecondary = "beryllium";
	public static String vInbetween = "boron";
	public static String vSporadic = "carbon";
	public static boolean vEnabled = false;
	public static short vDensity = 2;
	public static short vMaxHeight = 250;
	public static short vMinHeight = 5;
	public static short vSize = 32;
	public static short vWeight = 100;
	public static boolean vInvertDimensions = false;
	public static String[] vDimensions = new String[] {"overworld"};
	public static boolean vInvertBiomes = true;
	public static String[] vBiomes = new String[] {};
	
	//defaults type
	public static String tCategory = "item";
	public static String tEffectiveTool = "pickaxe";
	public static boolean tEnabled = false;
	public static boolean tHasTooltip = true;
	public static boolean tIsBeaconBase = false;
	public static boolean tIsBeaconPayment = false;
	public static String tOreDictPrefix = "";
	public static String tState = "solid";
	public static String tSyntax = "%s";
	public static String tUnitValue = "1u";
	
	//INTERNAL
	public static LinkedHashMap<String, Material> materials = new LinkedHashMap<String, Material>();
	public static HashMap<Integer, String> idMapping = new HashMap<Integer, String>(); 
	public static LinkedHashMap<String, Type> types = new LinkedHashMap<String, Type>();
	public static HashMap<String, OreVariant> oreVariants = new HashMap<String, OreVariant>();
	public static HashMap<String, OreVein> oreVeins = new HashMap<String, OreVein>();
	public static HashMap<IBlockState, HashMap<String, Block>> stateOreMapping = new HashMap<IBlockState, HashMap<String, Block>>();
	public static List<MetaBlock> metaBlocks = new ArrayList<MetaBlock>();
	public static List<MetaItem> metaItems = new ArrayList<MetaItem>();
	public static List<MetaOre> metaOres = new ArrayList<MetaOre>();
	public static List<MetaOreFalling> metaOresFalling = new ArrayList<MetaOreFalling>();
	public static List<Fluid> fluids = new ArrayList<Fluid>();
	public static int weight = 0;
}
