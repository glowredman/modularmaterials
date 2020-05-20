package glowredman.modularmaterials.proxy;

import static glowredman.modularmaterials.Main.logger;
import static glowredman.modularmaterials.Reference.enableAll;
import glowredman.modularmaterials.block.BlockHandler;
import glowredman.modularmaterials.file.AssetHandler;
import glowredman.modularmaterials.file.JSONHandler;
import glowredman.modularmaterials.fluid.FluidHandler;
import glowredman.modularmaterials.item.ItemHandler;
import glowredman.modularmaterials.util.ConfigHandler;
import glowredman.modularmaterials.util.MaterialHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent event) {
		//CONFIG
		ConfigHandler.initConfigs(event);
		ConfigHandler.readConfigs();
		ConfigHandler.saveConfigs();
		//ENABLE_ALL-CHECK
		if(enableAll) {logger.warn("\"enableAll\" is set to true, registering EVERYTHING!");}
		//MATERIALS, TYPES
		JSONHandler.initTypeFile(event);
		JSONHandler.initMaterialFile(event);
		MaterialHandler.fillMaterialListIfEmpty();
		MaterialHandler.createIDMapping();
		//ASSETS
		AssetHandler.initItemTextureList();
		AssetHandler.createModelFiles();
		AssetHandler.createLangFile();
		//TODO actually registering the items/blocks/fluids
		FluidHandler.registerFluids();
		ItemHandler.registerItems();
		BlockHandler.registerBlocks();
	}
	
	public void init(FMLInitializationEvent event) {
		ItemHandler.addOreDictTags();
		ItemHandler.initColors();
		BlockHandler.initColors();
	}
	
	public void postinit(FMLPostInitializationEvent event) {}
	
	public void registerItemRenderer(Item item, String name, int meta) {}
		
	public void registerItemRenderer(Item item, String name) {}

}
