package glowredman.modularmaterials.data.object.sub;

import java.util.ArrayList;
import java.util.List;

import glowredman.modularmaterials.client.FormattingHandler;

public class OreProperties {

	public float friction = 0.0f;
	public float hardness = 3.0f;
	public float jumpFactor = 1.0f;
	public int lightLevel = 0;
	public float resistance = 0.0f;
	public float speedFactor = 1.0f;
	public boolean requiresToolForDrops = true;
	public String mapColor = "AUTO";
	public String material = "STONE";
	public String sound = "STONE";
	public List<String> tags = new ArrayList<>();
	
	@Override
	public String toString() {
		return String.format("{friction: %f, hardness: %f, jumpFactor: %f, lightLevel: %d, resistance: %f, requiresToolForDrops: %b, mapColor: %s, material: %s, soundType: %s, tags: %s}",
				friction, hardness, jumpFactor, lightLevel, resistance, requiresToolForDrops, mapColor, material, sound, FormattingHandler.listToString(tags));
	}
	
}
