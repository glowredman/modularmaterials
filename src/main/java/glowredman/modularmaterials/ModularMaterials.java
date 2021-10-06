package glowredman.modularmaterials;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import glowredman.modularmaterials.block.BlockHandler;
import glowredman.modularmaterials.client.ClientHandler;
import glowredman.modularmaterials.data.LootTableHandler;
import glowredman.modularmaterials.data.ResourceLoader;
import glowredman.modularmaterials.data.TagHandler;
import glowredman.modularmaterials.item.ItemHandler;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MM_Reference.MODID)
public class ModularMaterials {
	
	public static ModularMaterials instance;
	private static final Logger LOGGER = LogManager.getLogger();
	private static final String LOG_PREFIX = "[" + MM_Reference.MODID + "] ";
	
	public ModularMaterials() {
		instance = this;
		MinecraftForge.EVENT_BUS.register(new MM_Commands());
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.register(instance);
		bus.register(new BlockHandler());
		bus.register(new ItemHandler());
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			bus.register(new ClientHandler());
		});
	}
	
	@SubscribeEvent
	public void commonSetup(FMLCommonSetupEvent event) {
		TagHandler.execute();
		LootTableHandler.generateBlockDrops();
	}
	
	@SubscribeEvent
	public void registerResourceLoaders(AddPackFindersEvent event) {
		if(event.getPackType() == PackType.CLIENT_RESOURCES) {
			event.addRepositorySource(new ResourceLoader(false));
		} else {
			event.addRepositorySource(new ResourceLoader(true));
		}
	}

	public static void trace(String s) {
		LOGGER.trace(LOG_PREFIX + s);
	}

	public static void debug(String s) {
		LOGGER.debug(LOG_PREFIX + s);
	}
	
	public static void info(String s) {
		LOGGER.info(LOG_PREFIX + s);
	}
	
	public static void warn(String s) {
		LOGGER.warn(LOG_PREFIX + s);
	}
	
	public static void error(String s) {
		LOGGER.error(LOG_PREFIX + s);
	}
	
	public static void fatal(String s) {
		LOGGER.fatal(LOG_PREFIX + s);
	}
}
