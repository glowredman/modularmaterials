package glowredman.modularmaterials.data.object.sub;

import java.util.ArrayList;
import java.util.List;

public class BlockProperties {
	
	public float hardness = 5.0f;
	public float friction = 0.6f;
	public String mapColor = "AUTO";
	public boolean requiresToolForDrops = true;
	public String sound = "METAL";
	public boolean sticky = false;
	public List<String> tags = new ArrayList<>();
	
	@Override
	public String toString() {
		return String.format("{friction: %f, hardness: %f, mapColor: %s, sound: %s, sticky: %b, tags: %s}",
				friction, hardness,  mapColor, sound, sticky, tags);
	}

}
