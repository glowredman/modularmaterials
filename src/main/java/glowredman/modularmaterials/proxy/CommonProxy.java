package glowredman.modularmaterials.proxy;

import static glowredman.modularmaterials.Main.logger;
import static glowredman.modularmaterials.Reference.enableAll;
import static glowredman.modularmaterials.Reference.modGenerationWeight;
import glowredman.modularmaterials.block.BlockHandler;
import glowredman.modularmaterials.file.AssetHandler;
import glowredman.modularmaterials.file.JSONHandler;
import glowredman.modularmaterials.fluid.FluidHandler;
import glowredman.modularmaterials.gen.OreGenHandler;
import glowredman.modularmaterials.gen.OreGenTest;
import glowredman.modularmaterials.item.ItemHandler;
import glowredman.modularmaterials.util.ConfigHandler;
import glowredman.modularmaterials.util.MaterialHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent event) {
		//SUBSCRIBE EVENTS
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
	
	public void init(FMLInitializationEvent event) {
		MaterialHandler.addOreDictTags();
		ItemHandler.initColors();
		BlockHandler.initColors();
		GameRegistry.registerWorldGenerator(new OreGenTest(), modGenerationWeight);
	}
	
	public void postinit(FMLPostInitializationEvent event) {}
	
	public void registerItemRenderer(Item item, String name, int meta) {}
		
	public void registerItemRenderer(Item item, String name) {}

}
