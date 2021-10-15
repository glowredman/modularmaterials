package glowredman.modularmaterials.worldgen;

import java.util.Random;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.data.object.MM_OreVein;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.world.level.WorldGenLevel;

public class WorldGenVeinLayer {
	
	public static Long2ObjectOpenHashMap<MM_OreVein> validVeins = new Long2ObjectOpenHashMap<>();
	
	private static final MM_OreVein NO_ORES_IN_VEIN = null;
	
	public static void generate(WorldGenLevel world, int chunkX, int chunkZ, int oreSeedX, int oreSeedZ) {
		long oreVeinSeed = getOreVeinSeed(world, oreSeedX, oreSeedZ);
		Random oreVeinRNG = MM_Reference.NEW_RAND.apply(oreVeinSeed);
		
		ModularMaterials.debug("Finding orevein for chunk[" + chunkX + ", " + chunkZ + "], seed[" + oreSeedX + ", " + oreSeedZ + "], oreVeinSeed=" + oreVeinSeed);
		
		if(validVeins.containsKey(oreVeinSeed)) {
			// oreseed is located in the previously processed table
			MM_OreVein vein = validVeins.get(oreVeinSeed);
			if(vein == null) return;
			oreVeinRNG.setSeed(oreVeinSeed ^ vein.primary.hashCode());
			vein.generateChunkified(world, oreVeinRNG, chunkX * 16, chunkZ * 16, oreSeedX * 16, oreSeedZ * 16);
		
		} else {
			float oreVeinRoll = oreVeinRNG.nextFloat(); // Roll the dice, see if we get an orevein here at all
			if(oreVeinRoll < MM_Reference.CONFIG.veinGenerationProbability) {
				int placementAttempts = 0;
				boolean oreVeinFound = false;
				
				for(int i = 0; i < MM_Reference.CONFIG.maxFindingAttempts && !oreVeinFound && placementAttempts < MM_Reference.CONFIG.maxPlacementAttempts; i++) {
					int randomWeight = oreVeinRNG.nextInt(FeatureHandler.totalWeight);
					for(MM_OreVein vein : MM_Reference.ORE_VEINS.values()) {
						randomWeight -= vein.weight;
						if(randomWeight <= 0) {
							// Adjust the seed so that this vein has a series of unique random numbers.  Otherwise multiple attempts at this same oreseed will get the same offset and X/Z values. If an orevein failed, any orevein with the
							// same minimum heights would fail as well.  This prevents that, giving each orevein a unique height each pass through here.
							VeinLayerResult placementResult = vein.generateChunkified(world, MM_Reference.NEW_RAND.apply(oreVeinSeed ^ vein.primary.hashCode()), chunkX * 16, chunkZ * 16, oreSeedX * 16, oreSeedZ * 16);
							switch (placementResult) {
							case SUCCESS:
								validVeins.put(oreVeinSeed, vein);
								oreVeinFound = true;
								break;
							case INCR_ATTEMPTS:
								placementAttempts++;
								break; // Should do retry in this case until out of chances
							case FIND_NEW_VEIN:
								break;
							}
							break; // Try the next orevein
						}
					}
				}
				// Only add an empty orevein if unable to place a vein at the oreseed chunk.
				if(!oreVeinFound && chunkX == oreSeedX && chunkZ == oreSeedZ) {
					ModularMaterials.debug("Ran out of attempts, empty orevein");
					validVeins.put(oreVeinSeed, NO_ORES_IN_VEIN);
				}
			} else {
				ModularMaterials.debug("Bad luck, empty orevein");
				validVeins.put(oreVeinSeed, NO_ORES_IN_VEIN);
			}
		}
	}
	
	private static long getOreVeinSeed(WorldGenLevel world, int oreSeedX, int oreSeedZ) {
		// world.getSeed() << 16    Deep Dark does two oregen passes, one with getSeed set to +1 the original world seed.  This pushes that +1 off the low bits of oreSeedZ, so that the hashes are far apart for the two passes.
		// (world.getLevel().dimension().location().hashCode() & 0xffL) << 56    Puts the dimension in the top bits of the hash, to make sure to get unique hashes per dimension
		// (oreSeedX & 0x000000000fffffffL) << 28    Puts the chunk X in the bits 29-55. Cuts off the top few bits of the chunk so we have bits for dimension.
		// oreSeedZ & 0x000000000fffffffL    Puts the chunk Z in the bits 0-27. Cuts off the top few bits of the chunk so we have bits for dimension.
		return world.getSeed() << 16 ^ ((world.getLevel().dimension().location().hashCode() & 0xffL) << 56 | (oreSeedX & 0x000000000fffffffL) << 28 | oreSeedZ & 0x000000000fffffffL);
	}

}
