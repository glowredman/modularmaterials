package glowredman.modularmaterials.gen;

import java.util.Map.Entry;
import java.util.Random;

import static glowredman.modularmaterials.Reference.*;
import glowredman.modularmaterials.object.OreVein;
import glowredman.modularmaterials.util.MaterialHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class WorldGenerator implements IWorldGenerator {
	
	public static final WorldGenerator INSTANCE = new WorldGenerator();
	
	public static void register() {
		GameRegistry.registerWorldGenerator(INSTANCE, 1073741823); //TODO Reference.modGenerationWeight
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		//get the worlds properties
		String biomeName = Biome.REGISTRY.getNameForObject(world.getBiome(new BlockPos(OreGenHandler.getCenterCoordinate(chunkX), 0, OreGenHandler.getCenterCoordinate(chunkZ)))).toString();
		String dimensionName = world.provider.getDimensionType().getName();
		random = OreGenHandler.getRandom(world, chunkX, chunkZ);
		//ORE VEINS
		//is the chunk in a |3n| + 1 spot?
		if((Math.abs(chunkX) % 3 == 1) && (Math.abs(chunkZ) % 3 == 1)) {
			//sort out a part of the vein locations based on the configured probability
			if(random.nextFloat() < veinGenerationProbability) {
				boolean veinGenerationSuccesfull = false;
				//tries at most 64 times to find and generate a vein
				for(int i = 0; i < 64 && !veinGenerationSuccesfull; i++) {
					//choose a random vein to attempt to generate
					int randomWeight = random.nextInt(weight);
					for(Entry<String, OreVein> oreVeinEntry : oreVeins.entrySet()) {
						OreVein oreVein = oreVeinEntry.getValue();
						randomWeight -= oreVein.weight;
						if(randomWeight <= 0) {
							veinGenerationSuccesfull = oreVein.executeOreGen(world, random, biomeName, dimensionName, chunkX, chunkZ);
							if(veinGenerationSuccesfull) {
								System.out.println("Generated vein in " + chunkX + ", " + chunkZ);
							}
							break;
						}
					}
				}
			}
		}
	}

}
