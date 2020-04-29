package glowredman.modularmaterials;

import org.apache.logging.log4j.Logger;

import static glowredman.modularmaterials.Reference.*;
import glowredman.modularmaterials.proxy.CommonProxy;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;

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
	

}
