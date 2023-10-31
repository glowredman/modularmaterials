package glowredman.modularmaterials;

import java.io.IOException;
import java.nio.file.Files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import glowredman.modularmaterials.block.BlockHandler;
import glowredman.modularmaterials.client.ClientHandler;
import glowredman.modularmaterials.data.LootTableHandler;
import glowredman.modularmaterials.data.ResourceLoader;
import glowredman.modularmaterials.data.TagHandler;
import glowredman.modularmaterials.data.legacy.LegacyHandler;
import glowredman.modularmaterials.fluid.FluidHandler;
import glowredman.modularmaterials.item.ItemHandler;
import glowredman.modularmaterials.worldgen.FeatureVeinLayer;
import net.minecraft.server.packs.PackType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddPackFindersEvent;

@Mod(MM_Reference.MODID)
public class ModularMaterials {
	
	public static final Logger LOGGER = LogManager.getLogger(MM_Reference.MODID);
	
	public ModularMaterials() {
		NeoForge.EVENT_BUS.register(new MM_Commands());
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		modBus.register(this);
		modBus.register(new FluidHandler());
		modBus.register(new BlockHandler());
		modBus.register(new ItemHandler());
		if(FMLEnvironment.dist.isClient()) {
            modBus.register(new ClientHandler());
		}
	}
	
	@SubscribeEvent
	public void commonSetup(FMLCommonSetupEvent event) {
		TagHandler.execute();
		LootTableHandler.generateBlockDrops();
		FeatureVeinLayer.calculateTotalWeight();
		try {
            Files.createDirectories(LegacyHandler.LEGACY_DIR);
        } catch (IOException e) {
            LOGGER.warn("Failed to create legacy directory", e);
        }
	}
	
	@SubscribeEvent
	public void registerResourceLoaders(AddPackFindersEvent event) {
		if(event.getPackType() == PackType.CLIENT_RESOURCES) {
			event.addRepositorySource(new ResourceLoader(false));
		} else {
			event.addRepositorySource(new ResourceLoader(true));
		}
	}
}
