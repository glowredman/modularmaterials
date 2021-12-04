package glowredman.modularmaterials;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import glowredman.modularmaterials.block.BlockHandler;
import glowredman.modularmaterials.client.ClientHandler;
import glowredman.modularmaterials.data.LootTableHandler;
import glowredman.modularmaterials.data.ResourceLoader;
import glowredman.modularmaterials.data.TagHandler;
import glowredman.modularmaterials.data.legacy.LegacyHandler;
import glowredman.modularmaterials.item.ItemHandler;
import glowredman.modularmaterials.worldgen.FeatureHandler;
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
		IEventBus forgeBus = MinecraftForge.EVENT_BUS;
		forgeBus.register(new MM_Commands());
		if(!MM_Reference.ORE_VEINS.isEmpty()) forgeBus.register(new FeatureHandler());
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		modBus.register(instance);
		modBus.register(new BlockHandler());
		modBus.register(new ItemHandler());
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			modBus.register(new ClientHandler());
		});
	}
	
	@SubscribeEvent
	public void commonSetup(FMLCommonSetupEvent event) {
		TagHandler.execute();
		LootTableHandler.generateBlockDrops();
		FeatureHandler.calculateTotalWeight();
		LegacyHandler.LEGACY_DIR.mkdirs();
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
