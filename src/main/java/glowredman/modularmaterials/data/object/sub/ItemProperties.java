package glowredman.modularmaterials.data.object.sub;

import java.util.ArrayList;
import java.util.List;

public class ItemProperties {
	
	public boolean isFireResistant = false;
	public boolean isFoil = false;
	public int lifespan = 6000;
	public AdvRarity rarity = AdvRarity.COMMON;
	public List<String> tags = new ArrayList<>();
	
	@Override
	public String toString() {
		return String.format("{isFireResistant: %b, isFoil: %b, lifespan: %d, rarity: %s, tags: %s}",
				isFireResistant, isFoil, lifespan, rarity, tags);
	}

}
