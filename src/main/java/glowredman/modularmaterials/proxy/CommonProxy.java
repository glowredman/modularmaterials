package glowredman.modularmaterials.proxy;

import static glowredman.modularmaterials.Main.logger;

import glowredman.modularmaterials.Reference;
import glowredman.modularmaterials.item.ItemHandler;
import glowredman.modularmaterials.material.MaterialHandler;
import glowredman.modularmaterials.material.MaterialProperties;
import glowredman.modularmaterials.util.AssetHandler;
import glowredman.modularmaterials.util.ConfigHandler;
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
		//TODO material config
		//ENABLE_ALL-CHECK
		if(Reference.enableAll) {logger.warn("\"enableAll\" is set to true, registering EVERYTHING!");}
		//TYPES
		MaterialHandler.initTypes();
		//MATERIALS
		MaterialProperties.loadMaterials();
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
	}
	
	public void postinit(FMLPostInitializationEvent event) {
		
	}
	
	public void registerItemRenderer(Item item, String name, int meta) {}
		
	public void registerItemRenderer(Item item, String name) {}

}
