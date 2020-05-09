package glowredman.modularmaterials.proxy;

import static glowredman.modularmaterials.Main.logger;
import glowredman.modularmaterials.Reference;
import glowredman.modularmaterials.item.ItemHandler;
import glowredman.modularmaterials.util.AssetHandler;
import glowredman.modularmaterials.util.ConfigHandler;
import glowredman.modularmaterials.util.JSONHandler;
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
		if(Reference.enableAll) {logger.warn("\"enableAll\" is set to true, registering EVERYTHING!");}
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
		ItemHandler.registeritems();
	}
	
	public void init(FMLInitializationEvent event) {
		ItemHandler.addOreDictTags();
		ItemHandler.initColors();
	}
	
	public void postinit(FMLPostInitializationEvent event) {}
	
	public void registerItemRenderer(Item item, String name, int meta) {}
		
	public void registerItemRenderer(Item item, String name) {}

}
