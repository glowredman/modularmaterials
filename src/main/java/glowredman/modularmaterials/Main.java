package glowredman.modularmaterials;

import static glowredman.modularmaterials.Reference.*;

import org.apache.logging.log4j.Logger;

import glowredman.modularmaterials.proxy.CommonProxy;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(acceptedMinecraftVersions = MCVERSION, dependencies = MODDEPENDENCIES, modid = MODID, name = MODNAME, version = MODVERSION)
public class Main {
	
	@Instance
	public static Main instance;
	
	public static Logger logger;
	
	@SidedProxy(clientSide = CLIENT, serverSide = SERVER, modId = MODID)
	public static CommonProxy proxy;
	
	static {FluidRegistry.enableUniversalBucket();}
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		logger.info("Hello cyberspace!");
		proxy.preInit(event);
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		proxy.init(event);
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		proxy.postinit(event);
	}
	
	@EventHandler
	public static void serverStarting(FMLServerStartingEvent event) {
		proxy.serverStarting(event);
	}
	
	@EventHandler
	public static void serverStarted(FMLServerStartedEvent event) {
		proxy.serverStarted(event);
	}
	
}
