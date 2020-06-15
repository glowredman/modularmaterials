package glowredman.modularmaterials.proxy;

import static glowredman.modularmaterials.Main.logger;
import static glowredman.modularmaterials.Reference.MODID;
import static glowredman.modularmaterials.Reference.enableAll;
import glowredman.modularmaterials.block.BlockHandler;
import glowredman.modularmaterials.file.AssetHandler;
import glowredman.modularmaterials.file.JSONHandler;
import glowredman.modularmaterials.fluid.FluidHandler;
import glowredman.modularmaterials.gen.OreGenHandler;
import glowredman.modularmaterials.gen.WorldGenerator;
import glowredman.modularmaterials.item.ItemHandler;
import glowredman.modularmaterials.util.ClientHandler;
import glowredman.modularmaterials.util.ConfigHandler;
import glowredman.modularmaterials.util.MaterialHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		//SUBSCRIBE EVENTS
		ClientHandler.register();
		OreGenHandler.register();
		//CONFIG
		ConfigHandler.initConfigs(event);
		ConfigHandler.readConfigs();
		ConfigHandler.saveConfigs();
		//ENABLE_ALL-CHECK
		if(enableAll) {logger.warn("\"enableAll\" is set to true, registering EVERYTHING!");}
		//FILES
		JSONHandler.initOreVariantsFile(event);
		JSONHandler.initTypeFile(event);
		JSONHandler.initMaterialFile(event);
		JSONHandler.initOreGenerationFile(event);
		//MATERIAL HANDLING
		MaterialHandler.fillMaterialListIfEmpty();
		MaterialHandler.createIDMapping();
		//ASSETS
		AssetHandler.initCTTTList();
		AssetHandler.createBlockStateFiles();
		AssetHandler.createModelFiles();
		AssetHandler.createLangFile();
		//REGISTERING
		FluidHandler.registerFluids();
		ItemHandler.registerItems();
		BlockHandler.registerBlocks();
		BlockHandler.registerOres();
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		//ORE DICT
		MaterialHandler.addOreDictTags();
		//COLORS
		ItemHandler.initColors();
		BlockHandler.initColors();
		//ORE GEN
		OreGenHandler.initWeight();
		WorldGenerator.register();
	}
	
	@Override
	public void registerItemRenderer(Item item, String name, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(MODID + ':' + name, "inventory"));
	}
		
	@Override
	public void registerItemRenderer(Item item, String name) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(MODID + ':' + name, "inventory"));
	}

}
