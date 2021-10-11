package glowredman.modularmaterials.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.data.object.MM_OreVein;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.DecoratedFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.DecoratedFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class FeatureHandler {
	
	public static final List<Block> NO_SPAWN = new ArrayList<>();
	public static Set<Block> blockWithVariants;
	public static int totalWeight;
	
	public static final Feature<NoneFeatureConfiguration> OREVEIN = new FeatureVeinLayer();
	public static final ConfiguredFeature<?, ?> CONFIGURED_OREVEIN = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(MM_Reference.MODID, "configured_orevein"), OREVEIN.configured(NoneFeatureConfiguration.INSTANCE));
	
	public static void initOresForBlocksList() {
		blockWithVariants = MM_Reference.ORES.rowKeySet();
	}
	
	public static void calculateTotalWeight() {
		for(MM_OreVein vein : MM_Reference.ORE_VEINS.values()) {
			totalWeight += vein.weight;
		}
		ModularMaterials.info("Total orevein weight is " + totalWeight);
	}
	
	public static void initNoSpawnList() {
		if(!MM_Reference.ORES.isEmpty()) {
			for(String s : MM_Reference.CONFIG.removeOres) {
				Block b = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(s));
				if(b != null) {
					NO_SPAWN.add(b);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void loadBiome(BiomeLoadingEvent event) {
		ModularMaterials.debug("Configuring biome " + event.getName().toString() + "...");
		event.getGeneration().addFeature(Decoration.UNDERGROUND_ORES, CONFIGURED_OREVEIN);
		event.getGeneration().getFeatures(Decoration.UNDERGROUND_ORES).removeIf(f -> isDecoratedFeatureDisabled(f.get()));
	}

	private static boolean isDecoratedFeatureDisabled(ConfiguredFeature<?, ?> configuredFeature) {
		if(configuredFeature.config instanceof DecoratedFeatureConfiguration) {
			FeatureConfiguration config = configuredFeature.config;
			Feature<?> feature = null;
			while(config instanceof DecoratedFeatureConfiguration) {
				feature = ((DecoratedFeatureConfiguration) config).feature.get().feature;
				config = ((DecoratedFeatureConfiguration) config).feature.get().config;
				if(!(feature instanceof DecoratedFeature)) {
					break;
				}
			}
			if(feature instanceof OreFeature) {
				boolean ret = false;
				for(OreConfiguration.TargetBlockState t : ((OreConfiguration) config).targetStates) {
					ret |= NO_SPAWN.contains(t.state.getBlock());
				}
				return ret;
			}
		}
		return false;
	}

}
