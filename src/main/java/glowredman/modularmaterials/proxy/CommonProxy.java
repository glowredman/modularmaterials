package glowredman.modularmaterials.proxy;

import static glowredman.modularmaterials.Main.logger;

import glowredman.modularmaterials.Reference;
import glowredman.modularmaterials.material.MaterialHandler;
import glowredman.modularmaterials.util.ConfigHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.initConfigs(event);
		ConfigHandler.readConfigs();
		ConfigHandler.saveConfigs();
		//TODO material config
		if(Reference.enableAll) {logger.warn("\"enableAll\" is set to true, registering EVERYTHING!");}
		MaterialHandler.initTypes();
		MaterialHandler.fillMaterialListIfEmpty();
		MaterialHandler.createIDMapping();
		//TODO actually registering the items/blocks/fluids
	}
	
	public void init(FMLInitializationEvent event) {
	}
	
	public void postinit(FMLPostInitializationEvent event) {
		
	}

}
