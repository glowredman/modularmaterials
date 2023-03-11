package glowredman.modularmaterials;

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
	
	public static final Logger LOGGER = LogManager.getLogger(MM_Reference.MODID);
	
	public ModularMaterials() {
		MinecraftForge.EVENT_BUS.register(new MM_Commands());
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		modBus.register(this);
		modBus.register(new FluidHandler());
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
		FeatureVeinLayer.calculateTotalWeight();
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
}
