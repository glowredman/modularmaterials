package glowredman.modularmaterials.object;

import java.util.ArrayList;
import java.util.List;

public class OreVein {
	
	public String primary = "";
	public String secondary = "";
	public String inbewteen = "";
	public String sporadic = "";
	public boolean enabled;
	public int denisty;
	public int maxHeight;
	public int minHeight;
	public int size;
	public int weight;
	public boolean invertDimensions;
	public List<String> dimensions = new ArrayList<String>();
	public boolean invertBiomes;
	public List<String> biomes = new ArrayList<String>();
	
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
