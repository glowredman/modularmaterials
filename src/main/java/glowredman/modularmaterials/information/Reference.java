package glowredman.modularmaterials.information;

import glowredman.modularmaterials.json.JItemList;
import glowredman.modularmaterials.json.JMaterialList;
import glowredman.modularmaterials.tab.CreativeTabItems;
import glowredman.modularmaterials.tab.CreativeTabOres;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;

public class Reference {
	
	//MOD INFORMATION
	public static final String MODID = "modularmaterials";
	public static final String MODNAME = "Modular Materials";
	public static final String VERSION = "0.1";
	public static final String DEPENDENCIES = "required-after:cofhcore;required-after:thermalfoundation"; //"required-after:cofhcore@[4.6.0,4.7.0);required-after:cofhworld@[1.2.0,2.0.0);before:enderio;before:immersiveengineering"
	public static final String CLIENT = "glowredman.modularmaterials.proxy.ClientProxy";
	public static final String SERVER = "glowredman.modularmaterials.proxy.CommonProxy";
	
	//CREATIVE TABS
	public static final CreativeTabs TAB_ITEMS = new CreativeTabItems();
	public static final CreativeTabs TAB_BLOCKS = TAB_ITEMS; //TODO make custom creativeTab
	public static final CreativeTabs TAB_ORES = new CreativeTabOres();
	public static final CreativeTabs TAB_VARIOUS = TAB_ITEMS; //TODO make custom creativeTab
	
	//LISTS
	public static JItemList itemList = new JItemList();
	public static JMaterialList materialList = new JMaterialList();
	
	//CONFIG
	public static final String GENERALCONFIGNAME = "general.cfg";
	public static final String ITEMCONFIGNAME = "items.json";
	public static final String LANGFILENAME = "en_us.lang";
	public static final String MATERIALCONFIGNAME = "materials.json";
	
	public static Configuration generalConfig;
	
	//config values:
	//general
	public static boolean createModelFiles = false;
	public static int defaultDensityOffset = -10000;
	public static int defaultMoltenMaterialLightLevel = 12;
	public static boolean dumpLang = false;
	public static int normalTemp = 287;
	public static boolean registerUnitOreDictionary = true;
	
	//oreDict
	public static String unitBlock = "9u";
	public static String unitIngot = "1u";
	public static String unitPlate = "1u";
	
	//types
	public static boolean enableAll = false;
	public static boolean enableBlocks = true;
	public static boolean enableFluids = true;
	public static boolean enableIngots = true;
	public static boolean enableMiscItems = true;
	public static boolean enablePlates = true;

}
