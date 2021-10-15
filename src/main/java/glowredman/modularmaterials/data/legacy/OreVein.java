package glowredman.modularmaterials.data.legacy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import glowredman.modularmaterials.data.object.MM_OreVein;

public class OreVein {
	
	public String primary = "lithium";
	public String secondary = "beryllium";
	public String inbetween = "boron";
	public String sporadic = "carbon";
	public boolean enabled = false;
	public short density = 2;
	public short maxHeight = 250;
	public short minHeight = 5;
	public short size = 32;
	public short weight = 100;
	public boolean invertDimensions = false;
	public List<String> dimensions = Arrays.asList("overworld");
	public boolean invertBiomes = true;
	public List<String> biomes = new ArrayList<>();
	
	public MM_OreVein convert(String name) {
		MM_OreVein v = new MM_OreVein();
		v.biomes = biomes;
		v.density = density;
		v.dimensions = dimensions;
		v.enabled = enabled;
		v.inbetween = inbetween;
		v.invertBiomes = invertBiomes;
		v.invertDimensions = invertDimensions;
		v.maxY = maxHeight;
		v.minY = minHeight;
		v.name = name;
		v.primary = primary;
		v.secondary = secondary;
		v.size = size;
		v.sporadic = sporadic;
		v.weight = weight;
		return v;
	}

}
