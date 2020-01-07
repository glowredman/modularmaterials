package glowredman.modularmaterials;

import org.apache.logging.log4j.Logger;

import glowredman.modularmaterials.block.ModBlocks;
import glowredman.modularmaterials.fluid.ModFluids;
import glowredman.modularmaterials.information.Reference;
import glowredman.modularmaterials.item.ModItems;
import glowredman.modularmaterials.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)
public class Main {
	
	@Instance
	public static Main instance;
	
	public static Logger logger;
	
	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.SERVER)
	public static CommonProxy proxy;
	
	static { FluidRegistry.enableUniversalBucket(); }
	
	@Mod.EventBusSubscriber
	public static class RegistrationHandler {
		
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			ModBlocks.register(event.getRegistry());
		}
		
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			ModBlocks.registerItemBlocks(event.getRegistry());
		}
		
		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event) {
			ModBlocks.registerModels();
		}
		
	}
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		if(Reference.enableAll) { logger.warn("'enableAll' is set to true, registering EVERYTHING!"); }
		
		logger = event.getModLog();
		proxy.preInit(event);
		ModFluids.preInit();
		ModItems.preInit();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		proxy.init(event);
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

}
