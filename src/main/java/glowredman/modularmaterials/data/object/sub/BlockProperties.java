package glowredman.modularmaterials.data.object.sub;

import static glowredman.modularmaterials.MM_Reference.RAND;

import java.util.ArrayList;
import java.util.List;

import glowredman.modularmaterials.client.FormattingHandler;

public class BlockProperties {
	
	public float hardness = 5.0f;
	public float friction = 0.6f;
	public String mapColor = "AUTO";
	public String material = "METAL";
	public boolean requiresToolForDrops = true;
	public String sound = "METAL";
	public boolean sticky = false;
	public List<String> typeEnabledTags = new ArrayList<>();
	public List<String> tags = new ArrayList<>();
	
	@Override
	public String toString() {
		return String.format("{friction: %f, hardness: %f, mapColor: %s, material: %s, sound: %s, sticky: %b, typeEnabledTags: %s, tags: %s}",
				friction, hardness,  mapColor, material, sound, sticky, FormattingHandler.listToString(typeEnabledTags), FormattingHandler.listToString(tags));
	}
		
	public static BlockProperties random() {
		BlockProperties b = new BlockProperties();
		b.friction = RAND.nextFloat();
		b.hardness = RAND.nextFloat() * 10.0f;
		b.mapColor = "AUTO";
		b.material = "METAL";
		b.sound = "METAL";
		b.sticky = RAND.nextBoolean();
		return b;
	}

}
