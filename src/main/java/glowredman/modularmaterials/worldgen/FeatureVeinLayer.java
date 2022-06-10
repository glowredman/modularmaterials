package glowredman.modularmaterials.worldgen;

import java.util.List;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.ForgeRegistries;

public class FeatureVeinLayer extends Feature<NoneFeatureConfiguration> {

	public FeatureVeinLayer() {
		super(NoneFeatureConfiguration.CODEC);
		ForgeRegistries.FEATURES.register(new ResourceLocation(MM_Reference.MODID, "orevein"), this);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
		long time = System.nanoTime();
		BlockPos origin = pContext.origin();
		int chunkX = origin.getX() >> 4;
		int chunkZ = origin.getZ() >> 4;
		for(Tuple<Integer, Integer> seed : getVeinSeeds(chunkX, chunkZ)) {
			WorldGenVeinLayer.generate(pContext.level(), chunkX, chunkZ, seed.getA(), seed.getB());
		}
		ModularMaterials.debug("Took " + (System.nanoTime() - time) + "ns");
		return true;
	}
	
	private static List<Tuple<Integer, Integer>> getVeinSeeds(int chunkX, int chunkZ) {
		// Determine bounding box on how far out to check for ore veins affecting this chunk
		int wX = chunkX - (MM_Reference.CONFIG.maxVeinSize / 16);
		int eX = chunkX + (MM_Reference.CONFIG.maxVeinSize / 16 + 1); // Need to add 1 since it is compared using a <
		int nZ = chunkZ - (MM_Reference.CONFIG.maxVeinSize / 16);
		int sZ = chunkZ + (MM_Reference.CONFIG.maxVeinSize / 16 + 1);
		List<Tuple<Integer, Integer>> res = new ObjectArrayList<>();
		// Search for oreVein seeds and add to the list;
		for(int x = wX; x < eX; x++) {
			for(int z = nZ; z < sZ; z++) {
				if((Math.abs(x) % 3 == 1) && (Math.abs(z) % 3 == 1)) {
					res.add(new Tuple<>(x, z));
				}
			}
		}
		return res;
	}

}
