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
	
	@SidedProxy(clientSide = CLIENT, serverSide = SERVER)
	public static CommonProxy proxy;
	
	static {FluidRegistry.enableUniversalBucket();}
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		logger.info("Draw-Phase...");
		proxy.drawPhase(event);
		logger.info("Standby-Phase...");
		proxy.standbyPhase(event);
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		logger.info("Main-Phase 1...");
		proxy.mainPhase1(event);
		logger.info("Battle Phase...");
		proxy.battlePhase(event);
		logger.info("Main-Phase 2...");
		proxy.mainPhase2(event);
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		logger.info("End-Phase...");
		proxy.endPhase(event);
		logger.info("Ending the Turn.");
	}
	

}
