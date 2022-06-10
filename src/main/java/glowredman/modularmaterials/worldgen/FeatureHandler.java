package glowredman.modularmaterials.worldgen;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.data.object.MM_OreVein;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

public class FeatureHandler {

	public static Set<Block> blocksWithVariants;
	public static int totalWeight;
	
	public static final Feature<NoneFeatureConfiguration> OREVEIN = new FeatureVeinLayer();
	public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CONFIGURED_OREVEIN = FeatureUtils.register(MM_Reference.MODID + ":configured_orevein", OREVEIN);
	public static final Holder<PlacedFeature> PLACED_OREVEIN = PlacementUtils.register(MM_Reference.MODID + ":placed_orevein", CONFIGURED_OREVEIN);
	
	public static void initOresForBlocksList() {
		blocksWithVariants = MM_Reference.ORES.rowKeySet();
	}
	
	public static void calculateTotalWeight() {
		for(MM_OreVein vein : MM_Reference.ORE_VEINS.values()) {
			totalWeight += vein.weight;
		}
		ModularMaterials.info("Total orevein weight is " + totalWeight);
	}
	
	@SubscribeEvent
	public void registerBiomeModifiers(RegisterEvent event) {
		if(!event.getRegistryKey().equals(Keys.BIOME_MODIFIER_SERIALIZERS)) {
			return;
		}
		event.getForgeRegistry().register(new ResourceLocation(MM_Reference.MODID, "orevein_modifier"), OreVeinBiomeModifier.makeCodec());
	}
	
	public record OreVeinBiomeModifier(boolean enabled) implements BiomeModifier {
		
		private static final RegistryObject<Codec<? extends BiomeModifier>> SERIALIZER = RegistryObject.create(new ResourceLocation(MM_Reference.MODID, "orevein_modifier"), Keys.BIOME_MODIFIER_SERIALIZERS, MM_Reference.MODID);
		private static Set<DataResult<JsonElement>> featuresToRemove;

		@Override
		public void modify(Holder<Biome> biome, Phase phase, Builder builder) {
			if(phase == Phase.ADD && enabled) {
				builder.getGenerationSettings().addFeature(Decoration.UNDERGROUND_ORES, PLACED_OREVEIN);
			}
			else if(phase == Phase.REMOVE && enabled) {
				builder.getGenerationSettings().getFeatures(Decoration.UNDERGROUND_ORES).removeIf(OreVeinBiomeModifier::checkFeature);
			}
		}

		@Override
		public Codec<? extends BiomeModifier> codec() {
			return SERIALIZER.get();
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
		
		private static Codec<OreVeinBiomeModifier> makeCodec() {
			return RecordCodecBuilder.create(builder -> builder.group(Codec.BOOL.fieldOf("enabled").forGetter(OreVeinBiomeModifier::enabled)).apply(builder, OreVeinBiomeModifier::new));
		}
		
	}

}
