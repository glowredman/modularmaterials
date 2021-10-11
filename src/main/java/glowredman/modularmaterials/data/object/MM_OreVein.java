package glowredman.modularmaterials.data.object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.block.IMetaOre;
import glowredman.modularmaterials.worldgen.FeatureHandler;
import glowredman.modularmaterials.worldgen.VeinLayerResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class MM_OreVein {
	
	public String name = "vein_" + this.hashCode();
	public boolean enabled = false;
	public String primary = "example";
	public String secondary = "example";
	public String inbetween = "example";
	public String sporadic = "example";
	public int density = 2;
	public int minY = 5;
	public int maxY = 250;
	public int size = 32;
	public int weight = 100;
	public boolean invertDimensions = false;
	public List<String> dimensions = Arrays.asList("minecraft:overworld");
	public boolean invertBiomes = true;
	public List<String> biomes = new ArrayList<>();
	
	public VeinLayerResult generateChunkified(WorldGenLevel world, Random rand, int posX, int posZ, int seedX, int seedZ) {
		//check dimension
		String dimension = world.getLevel().dimension().getRegistryName().toString();
		if((dimensions.contains(dimension)
				|| dimensions.contains(dimension.replace("minecraft:", ""))) ^ invertDimensions) {
			return VeinLayerResult.FIND_NEW_VEIN;
		}
		
		//check biome
		Biome biome = world.getChunk(posX / 16, posZ / 16).getBiomes().getPrimaryBiome(new ChunkPos(posX / 16, posZ / 16));
		if((biomes.contains(biome.getRegistryName().toString())
				|| biomes.contains(biome.getRegistryName().toString().replace("minecraft:", ""))
				|| biomes.contains(biome.getBiomeCategory().getName())) ^ invertBiomes) {
			return VeinLayerResult.FIND_NEW_VEIN;
		}
		
		int tMinY = minY + rand.nextInt(maxY - minY - 5);
		
		// Determine West/East ends of orevein
		int wXVein = seedX - rand.nextInt(16);
		int eXVein = seedX + 16 + rand.nextInt(16);
		// Limit OreVein to only blocks present in the current chunk
		int wX = Math.max(wXVein, posX);
		int eX = Math.min(eXVein, posX + 16);
		
		if(wX >= eX) {  //No overlap between orevein and this chunk exists in X
			if(testVein(world, posX, tMinY, posZ)) {
				return VeinLayerResult.SUCCESS; // Didn't reach, but could have placed. Save orevein for future use.
			} else {
				return VeinLayerResult.INCR_ATTEMPTS; // Didn't reach, but couldn't place in test spot anyways, try for another orevein
			}
		}
		
		// Determine West/East ends of orevein
		int nZVein = seedZ - rand.nextInt(16);
		int sZVein = seedZ + 16 + rand.nextInt(16);
		// Limit OreVein to only blocks present in the current chunk
		int nZ = Math.max(nZVein, posZ);
		int sZ = Math.min(sZVein, posZ + 16);
		
		if(nZ >= sZ) {  //No overlap between orevein and this chunk exists in Z
			if(testVein(world, posX, tMinY, posZ)) {
				return VeinLayerResult.SUCCESS; // Didn't reach, but could have placed. Save orevein for future use.
			} else {
				return VeinLayerResult.INCR_ATTEMPTS; // Didn't reach, but couldn't place in test spot anyways, try for another orevein
			}
		}
		if(generateByFunction(world, rand, tMinY, wXVein, eXVein, nZVein, sZVein, wX, eX, nZ, sZ)) {
			return VeinLayerResult.SUCCESS;
		} else {
			return VeinLayerResult.INCR_ATTEMPTS;
		}
		
	}
	
	private boolean generateByFunction(WorldGenLevel world, Random rand,
			int tMinY, int wXVein, int eXVein, int nZVein, int sZVein, // vein
			int wX, int eX, int nZ, int sZ) { // vein/current chunk intersection
		
		MutableBlockPos pos = new MutableBlockPos();
		int placeCount = 0;
		final int centerX = (wXVein + eXVein) / 2;
		final int centerY = tMinY + 4;
		final int centerZ = (nZVein + sZVein) / 2;
		// Elliptic shape defined as ax^2 + by^2 + cz^2 = 1
		final double a = 4.0 / ((wXVein - eXVein) * (wXVein - eXVein));
		final double b = 0.04;
		final double c = 4.0 / ((nZVein - sZVein) * (nZVein - sZVein));
		for(int y = tMinY - 1; y < (tMinY + 8); y++) {
			for(int x = wX; x < eX; x++) {
				for(int z = nZ; z < sZ; z++) {
					double p = 1.0 - a*(centerX - x)*(centerX - x) - b*(centerY - y)*(centerY - y) - c*(centerZ - z)*(centerZ - z);
					if(p <= 0) {
						continue;
					}
					if(rand.nextInt(100) > 100 * p) {
						continue; // rolled outside the probability function
					}
					if(rand.nextInt(12) > density) {
						continue;
					}
					pos.set(x, y, z);
					if(rand.nextInt(100) < 10) { // let each 10th be sporadic
						placeCount += setOre(world, pos, sporadic);
					} else {
						if(p > 0.5) {
							placeCount += setOre(world, pos, primary);
						} else {
							if(p > 0.2) {
								placeCount += setOre(world, pos, secondary);
							} else {
								placeCount += setOre(world, pos, inbetween);
							}
						}
					}
				}
			}
			if(y == tMinY + 1 && placeCount == 0) { // early bail out test
				return false;
			}
		}
		ModularMaterials.debug("Placed orevein \"" + name + "\" with " + placeCount + " ores at [" + centerX + ", " + centerY + ", " + centerZ + "]");
		return true;
	}
	
	private static boolean testVein(WorldGenLevel world, int posX, int posY, int posZ) {
		BlockState nw = world.getBlockState(new BlockPos(posX,      posY, posZ));
		BlockState ne = world.getBlockState(new BlockPos(posX + 16, posY, posZ));
		BlockState sw = world.getBlockState(new BlockPos(posX,      posY, posZ + 16));
		BlockState se = world.getBlockState(new BlockPos(posX + 16, posY, posZ + 16));
		return (!nw.isAir() || !ne.isAir() || !sw.isAir() || !se.isAir()) && 
				(FeatureHandler.blockWithVariants.contains(nw.getBlock()) || FeatureHandler.blockWithVariants.contains(ne.getBlock()) || FeatureHandler.blockWithVariants.contains(sw.getBlock()) || FeatureHandler.blockWithVariants.contains(se.getBlock()));
	}
	
	private static int setOre(WorldGenLevel world, BlockPos pos, String material) {
		Block base = world.getBlockState(pos).getBlock();
		IMetaOre ore = MM_Reference.ORES.get(base, material);
		if(ore == null) return 0;
		if(world.setBlock(pos.above(128), ore.getBlock().defaultBlockState(), 0)) return 1;
		return 0;
	}

}
