package glowredman.modularmaterials.data.object.sub;

import static glowredman.modularmaterials.MM_Reference.RAND;

import java.util.ArrayList;
import java.util.List;

import glowredman.modularmaterials.client.FormattingHandler;

public class OreProperties {

	public float hardness = 3.0f;
	public int lightLevel = 0;
	public float resistance = 0.0f;
	public List<String> typeEnabledTags = new ArrayList<>();
	public List<String> tags = new ArrayList<>();
	
	@Override
	public String toString() {
		return String.format("{hardness: %f, lightLevel: %d, resistance: %f, typeEnabledTags: %s, tags: %s}", hardness, lightLevel, resistance, FormattingHandler.listToString(typeEnabledTags), FormattingHandler.listToString(tags));
	}
	
	public static OreProperties random() {
		OreProperties o = new OreProperties();
		o.hardness = RAND.nextFloat() * 10.0f;
		o.lightLevel = RAND.nextInt(16);
		o.resistance = RAND.nextFloat() * 10.0f;
		return o;
	}
	
}
