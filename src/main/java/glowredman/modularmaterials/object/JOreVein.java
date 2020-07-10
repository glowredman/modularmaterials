package glowredman.modularmaterials.object;

import static glowredman.modularmaterials.Main.logger;
import static glowredman.modularmaterials.Reference.*;

import java.util.List;
import java.util.Random;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import glowredman.modularmaterials.gen.OreGenHandler;
import glowredman.modularmaterials.util.MaterialHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class JOreVein {
	
	public String primary = vPrimary;
	public String secondary = vSecondary;
	public String inbewteen = vInbetween;
	public String sporadic = vSporadic;
	public boolean enabled = vEnabled;
	@Nonnegative
	@Nonnull
	public short denisty = vDensity;
	public short maxHeight = vMaxHeight;
	public short minHeight = vMinHeight;
	@Nonnegative
	@Nonnull
	public short size = vSize;
	@Nonnegative
	public short weight = vWeight;
	public boolean invertDimensions = vInvertDimensions;
	public List<String> dimensions = MaterialHandler.getListFormArray(vDimensions);
	public boolean invertBiomes = vInvertBiomes;
	public List<String> biomes = MaterialHandler.getListFormArray(vBiomes);

	public boolean executeOreGen(World world, Random random, String biome, String dimension, int chunkX, int chunkZ) {
		//check if biome and dimension are correct
		if(((biomes.contains(biome) || biomes.contains(biome.replace("minecraft:", ""))) ^ invertBiomes) && (dimensions.contains(dimension) ^ invertDimensions)) {
			//check if the vein-parts are valid
			boolean hasPrimary = hasOre(primary);
			boolean hasInbetweent = hasOre(inbewteen);
			boolean hasSecondary = hasOre(secondary);
			boolean hasSporadic = hasOre(sporadic);
			if (hasPrimary && hasInbetweent && hasSecondary && hasSporadic) {
				//calculate the height of the vein
				int minY = minHeight + random.nextInt(maxHeight - minHeight - 5);
				//calculate the block coords of the given chunk
				int x = chunkX * 16;
				int z = chunkZ * 16;
				//calulate upper and lower bounds (x-axis)
				int lowerX = x - random.nextInt(size);
				int upperX = x + 16 + random.nextInt(size);
				for (int currentX = lowerX; currentX <= upperX; currentX++) {
					//calculate upper and lower bounds (z-axis)
					int lowerZ = z - random.nextInt(size);
					int upperZ = z + 16 + random.nextInt(size);
					for (int currentZ = lowerZ; currentZ <= upperZ; currentZ++) {
						//secondary
						for (int y = minY - 1; y < minY + 2; y++) {
							if ((random.nextInt(Math.max(1, Math.max(MathHelper.abs(lowerX - currentX), MathHelper.abs(upperX - currentX)) / denisty)) == 0) || (random.nextInt(Math.max(1, Math.max(MathHelper.abs(lowerZ - currentZ), MathHelper.abs(upperZ - currentZ)) / denisty)) == 0)) {
								setOreBlock(world, currentX, y, currentZ, secondary);
							} 
						}
						//primary
						for(int y = minY + 3; y < minY + 6; y++) {
							if ((random.nextInt(Math.max(1, Math.max(MathHelper.abs(lowerX - currentX), MathHelper.abs(upperX - currentX)) / denisty)) == 0) || (random.nextInt(Math.max(1, Math.max(MathHelper.abs(lowerZ - currentZ), MathHelper.abs(upperZ - currentZ)) / denisty)) == 0)) {
								setOreBlock(world, currentX, y, currentZ, primary);
							} 
						}
						//inbetween
						if ((random.nextInt(Math.max(1, Math.max(MathHelper.abs(lowerX - currentX), MathHelper.abs(upperX - currentX)) / denisty)) == 0) || (random.nextInt(Math.max(1, Math.max(MathHelper.abs(lowerZ - currentZ), MathHelper.abs(upperZ - currentZ)) / denisty)) == 0)) {
							setOreBlock(world, currentX, minY + 2 + random.nextInt(2), currentZ, inbewteen);
						}
						//sporadic
						if ((random.nextInt(Math.max(1, Math.max(MathHelper.abs(lowerX - currentX), MathHelper.abs(upperX - currentX)) / denisty)) == 0) || (random.nextInt(Math.max(1, Math.max(MathHelper.abs(lowerZ - currentZ), MathHelper.abs(upperZ - currentZ)) / denisty)) == 0)) {
							setOreBlock(world, currentX, minY - 1 + random.nextInt(7), currentZ, sporadic);
						} 
						
					}
				}
				return true;
			} else {
				if(!hasPrimary) {
					logger.warn(primary + " has no ore!");
				}
				if(!hasSecondary) {
					logger.warn(secondary + " has no ore!");
				}
				if(!hasInbetweent) {
					logger.warn(inbewteen + " has no ore!");
				}
				if(!hasSporadic) {
					logger.warn(sporadic + " has no ore!");
				}
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean setOreBlock(World world, int x, int y, int z, String materialKey) {
		//assures, that the y value is inside the world
		y = Math.min(world.getActualHeight(), Math.max(y, 1));
		IBlockState blockState = world.getBlockState(new BlockPos(x, y, z));
		IBlockState ore = OreGenHandler.getOreForBaseBlock(blockState, materialKey);
		if(ore != Blocks.AIR.getDefaultState()) {
			world.setBlockState(new BlockPos(x, y, z), stateOreMapping.get(blockState).get(materialKey).getDefaultState());
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hasOre(String materialKey) {
		if(materials.containsKey(materialKey)) {
			return materials.get(materialKey).isTypeEnabled("ore");
		} else {
			return false;
		}
	}
	
	public boolean isDimensionEnabled(String dim) {
		return dimensions.contains(dim) ^ invertDimensions;
	}
	
	public boolean isBiomeEnabled(String biome) {
		return biomes.contains(biome) ^ invertBiomes;
	}

}
