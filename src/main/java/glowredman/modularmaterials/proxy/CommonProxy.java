package glowredman.modularmaterials.proxy;

import static glowredman.modularmaterials.Main.logger;
import static glowredman.modularmaterials.Reference.enableAll;

import glowredman.modularmaterials.block.BlockHandler;
import glowredman.modularmaterials.command.Command;
import glowredman.modularmaterials.file.JSONHandler;
import glowredman.modularmaterials.fluid.FluidHandler;
import glowredman.modularmaterials.gen.OreGenHandler;
import glowredman.modularmaterials.gen.WorldGenerator;
import glowredman.modularmaterials.item.ItemHandler;
import glowredman.modularmaterials.util.ConfigHandler;
import glowredman.modularmaterials.util.MaterialHandler;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

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
		//REGISTERING
		FluidHandler.registerFluids();
		ItemHandler.registerItems();
		BlockHandler.registerBlocks();
		BlockHandler.registerOres();
	}
	
	public void init(FMLInitializationEvent event) {
		//ORE DICT
		MaterialHandler.addOreDictTags();
		//ORE GEN
		OreGenHandler.initWeight();
		WorldGenerator.register();
	}
	
	public void postinit(FMLPostInitializationEvent event) {}
	
	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new Command());
	}

	public void serverStarted(FMLServerStartedEvent event) {}
	
	public void registerItemRenderer(Item item, String name, int meta) {}
		
	public void registerItemRenderer(Item item, String name) {}

}
