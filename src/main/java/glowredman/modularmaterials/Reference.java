package glowredman.modularmaterials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import glowredman.modularmaterials.block.MetaBlock;
import glowredman.modularmaterials.block.MetaOre;
import glowredman.modularmaterials.block.MetaOreFalling;
import glowredman.modularmaterials.block.MiscBlock;
import glowredman.modularmaterials.block.MiscBlockFalling;
import glowredman.modularmaterials.item.MetaItem;
import glowredman.modularmaterials.item.MiscItem;
import glowredman.modularmaterials.object.JMaterial;
import glowredman.modularmaterials.object.JMiscBlock;
import glowredman.modularmaterials.object.JMiscFluid;
import glowredman.modularmaterials.object.JMiscItem;
import glowredman.modularmaterials.object.JOreVariant;
import glowredman.modularmaterials.object.JOreVein;
import glowredman.modularmaterials.object.JType;
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
	public static final String MODDEPENDENCIES = "";
	public static final String MODID = "modularmaterials";
	public static final String MODNAME = "Modular Materials";
	public static final String MODVERSION = "0.5-beta";
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
	public static final String CONFIGNAME_MISC = "misc.json";
	public static final String CONFIGNAME_OREGENERATION = "oreGeneration.json";
	public static final String CONFIGNAME_OREVARIANTS = "oreVariants.json";
	public static final String CONFIGNAME_TYPES = "types.json";
	
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
	public static String commandName = "mm";
	
	//core formatting
	public static String triggerShiftIsPressedFormatting = "\00a7p";
	public static String triggerShiftIsNotPressedFormatting = "\00a7P";
	public static String triggerCtrlIsPressedFormatting = "\00a7q";
	public static String triggerCtrlIsNotPressedFormatting = "\00a7Q";
	public static String triggerAltIsPressedFormatting = "\00a7t";
	public static String triggerAltIsNotPressedFormatting = "\00a7T";
	public static String triggerAnimatedFormattingChar = "\00a7s";
	public static boolean enableFormattingDebugger = false;
	
	//defaults block
	public static String bEffectiveTool = "pickaxe";
	public static boolean bEnabled = false;
	public static float bHardness = 5.0f;
	public static int bHarvestLevel = 1;
	public static boolean bIsBeaconBase = false;
	public static boolean bIsBeaconPayment = false;
	public static byte bLightLevel = 0;
	public static String bMapColor = "AUTO";
	public static String bMaterial = "IRON";
	public static boolean bObeysGravity = false;
	public static float bResistance = 6.0f;
	public static String bSound = "METAL";
	public static boolean bUseColor = false;
	
	//defaults color
	public static int cAlpha = 0;
	public static int cRed = 255;
	public static int cGreen = 255;
	public static int cBlue = 255;
	
	//defaults fluid
	public static int fDensity = 1000;
	public static boolean fEnabled = false;
	public static boolean fIsGaseous = false;
	public static byte fLightLevel = 0;
	public static int fTemperature = 293;
	public static String fTexture = MODID + ":void";
	public static boolean fUseColor = false;
	public static int fViscosity = 1000;
	
	//defaults item
	public static boolean iEnabled = false;
	public static boolean iIsBeaconPayment = false;
	public static boolean iUseColor = false;
	
	//defaults material
	public static float mBlockHardness = 5.0f;
	public static int mBlockHarvestLevel = 1;
	public static byte mBlockLightLevel = 0;
	public static String mBlockMapColor = "AUTO";
	public static String mBlockMaterial = "IRON";
	public static float mBlockResistance = 6.0f;
	public static String mBlockSound = "METAL";
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
	public static String oMapColor =  "STONE";
	public static String oMaterial = "ROCK";
	public static boolean oObeysGravity = false;
	public static String oOreDictPrefix = "";
	public static String oSound = "STONE";
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
	
	//defaults texture
	public static String xModel = "void";
	public static String xParent = "block/block";
	public static String xParticle = MODID + ":void";
	public static String xTexture = MODID + ":void";
	
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
	//sources
	public static Map<String, JMaterial> materials = new LinkedHashMap<String, JMaterial>(); //materialKey, material
	public static Map<String, JType> types = new LinkedHashMap<String, JType>(); //typeKey, type
	public static Map<String, JMiscBlock> miscBlockMap = new LinkedHashMap<String, JMiscBlock>(); //blockKey, block
	public static Map<String, JMiscFluid> miscFluidMap = new LinkedHashMap<String, JMiscFluid>(); //fluidKey, fluid
	public static Map<String, JMiscItem> miscItemMap = new LinkedHashMap<String, JMiscItem>(); //itemKey, item
	public static Map<String, JOreVariant> oreVariants = new HashMap<String, JOreVariant>(); //oreVariantKey, oreVariant
	public static Map<String, JOreVein> oreVeins = new HashMap<String, JOreVein>(); //oreVeinKey, oreVein
	//mappings
	public static Map<Short, String> idMaterialMapping = new HashMap<Short, String>(); //meta, materialKey
	public static Map<Short, String> idMiscMapping = new HashMap<Short, String>(); //meta, miscItem
	public static Map<IBlockState, HashMap<String, Block>> stateOreMapping = new HashMap<IBlockState, HashMap<String, Block>>(); //blockState, materialKey, ore
	//registered objects lists
	public static List<MetaBlock> metaBlocks = new ArrayList<MetaBlock>();
	public static List<MetaItem> metaItems = new ArrayList<MetaItem>();
	public static List<MetaOre> metaOres = new ArrayList<MetaOre>();
	public static List<MetaOreFalling> metaOresFalling = new ArrayList<MetaOreFalling>();
	public static List<Fluid> fluids = new ArrayList<Fluid>();
	public static List<MiscBlock> miscBlocks = new ArrayList<MiscBlock>();
	public static List<MiscBlockFalling> miscBlocksFalling = new ArrayList<MiscBlockFalling>();
	public static List<Fluid> miscFluids = new ArrayList<Fluid>();
	public static final MiscItem MISC_ITEM = new MiscItem();
	//ore gen
	public static int weight = 0;
}
