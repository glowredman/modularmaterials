package glowredman.modularmaterials.proxy;

import static glowredman.modularmaterials.Main.logger;
import glowredman.modularmaterials.api.Reference;
import glowredman.modularmaterials.material.MaterialHandler;
import glowredman.modularmaterials.util.ConfigHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
	public void drawPhase(FMLPreInitializationEvent event) {
		ConfigHandler.initConfigs(event);
		ConfigHandler.readConfigs();
		ConfigHandler.saveConfigs();
		if(Reference.enableAll) {logger.warn("\"enableAll\" is set to true, registering EVERYTHING!");}
	}
	
	public void standbyPhase(FMLPreInitializationEvent event) {
		MaterialHandler.initTypes();
	}
	
	public void mainPhase1(FMLInitializationEvent event) {
		
	}
	
	public void battlePhase(FMLInitializationEvent event) {
		
	}
	
	public void mainPhase2(FMLInitializationEvent event) {
		
	}
	
	public void endPhase(FMLPostInitializationEvent event) {
		
	}

}
