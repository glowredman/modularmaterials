package glowredman.modularmaterials.worldgen;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.JsonElement;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.data.object.MM_OreVein;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FeatureHandler {

	private static Set<DataResult<JsonElement>> featuresToRemove;
	public static Set<Block> blockWithVariants;
	public static int totalWeight;
	
	public static final Feature<NoneFeatureConfiguration> OREVEIN = new FeatureVeinLayer();
	public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CONFIGURED_OREVEIN = FeatureUtils.register(MM_Reference.MODID + ":configured_orevein", OREVEIN);
	public static final Holder<PlacedFeature> PLACED_OREVEIN = PlacementUtils.register(MM_Reference.MODID + ":placed_orevein", CONFIGURED_OREVEIN);
	
	public static void initOresForBlocksList() {
		blockWithVariants = MM_Reference.ORES.rowKeySet();
	}
	
	public static void calculateTotalWeight() {
		for(MM_OreVein vein : MM_Reference.ORE_VEINS.values()) {
			totalWeight += vein.weight;
		}
		ModularMaterials.info("Total orevein weight is " + totalWeight);
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void loadBiome(BiomeLoadingEvent event) {
		ModularMaterials.debug("Configuring biome " + event.getName().toString() + "...");
		event.getGeneration().getFeatures(Decoration.UNDERGROUND_ORES).removeIf(FeatureHandler::checkFeature);
		event.getGeneration().addFeature(Decoration.UNDERGROUND_ORES, PLACED_OREVEIN);
	}
	
	private static boolean checkFeature(Holder<PlacedFeature> feature) {
		if(featuresToRemove == null) {
			featuresToRemove = new HashSet<>();
			for(String s : MM_Reference.CONFIG.removeOres) {
				PlacedFeature p = BuiltinRegistries.PLACED_FEATURE.get(new ResourceLocation(s));
				if(p == null) {
					ModularMaterials.warn(s + " is not a valid PlacedFeature!");
					continue;
				}
				featuresToRemove.add(PlacedFeature.DIRECT_CODEC.encodeStart(JsonOps.INSTANCE, p));
			}
		}
		return featuresToRemove.contains(PlacedFeature.DIRECT_CODEC.encodeStart(JsonOps.INSTANCE, feature.value()));
	}

}
