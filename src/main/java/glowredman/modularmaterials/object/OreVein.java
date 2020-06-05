package glowredman.modularmaterials.object;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static glowredman.modularmaterials.Reference.*;
import glowredman.modularmaterials.block.MetaOre;
import glowredman.modularmaterials.block.MetaOreFalling;
import glowredman.modularmaterials.gen.OreGenHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class OreVein {
	
	public String primary = "";
	public String secondary = "";
	public String inbewteen = "";
	public String sporadic = "";
	public boolean enabled;
	public short denisty;
	public short maxHeight;
	public short minHeight;
	public short size;
	public short weight;
	public boolean invertDimensions;
	public List<String> dimensions = new ArrayList<String>();
	public boolean invertBiomes;
	public List<String> biomes = new ArrayList<String>();

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
					System.out.println(primary + " has no ore!");
				}
				if(!hasSecondary) {
					System.out.println(secondary + " has no ore!");
				}
				if(!hasInbetweent) {
					System.out.println(inbewteen + " has no ore!");
				}
				if(!hasSporadic) {
					System.out.println(sporadic + " has no ore!");
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
	
	public String getPrimary() {
		return primary;
	}
	
	public String getSecondary() {
		return secondary;
	}
	
	public String getInbewteen() {
		return inbewteen;
	}
	
	public String getSporadic() {
		return sporadic;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public int getDenisty() {
		return denisty;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getMinHeight() {
		return minHeight;
	}
	
	public int getMaxHeight() {
		return maxHeight;
	}
	
	public boolean isInvertDimensions() {
		return invertDimensions;
	}
	
	public List<String> getDimensions() {
		return dimensions;
	}
	
	public boolean isInvertBiomes() {
		return invertBiomes;
	}
	
	public List<String> getBiomes() {
		return biomes;
	}
	
	public void setPrimary(String primary) {
		this.primary = primary;
	}
	
	public void setSecondary(String secondary) {
		this.secondary = secondary;
	}
	
	public void setInbewteen(String inbewteen) {
		this.inbewteen = inbewteen;
	}
	
	public void setSporadic(String sporadic) {
		this.sporadic = sporadic;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public void setDenisty(int denisty) {
		this.denisty = denisty;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}
	
	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}
	
	public void setInvertDimensions(boolean invertDimensions) {
		this.invertDimensions = invertDimensions;
	}
	
	public void setDimensions(List<String> dimensions) {
		this.dimensions = dimensions;
	}
	
	public void setInvertBiomes(boolean invertBiomes) {
		this.invertBiomes = invertBiomes;
	}
	
	public void setBiomes(List<String> biomes) {
		this.biomes = biomes;
	}

}
