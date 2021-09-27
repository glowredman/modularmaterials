package glowredman.modularmaterials.data.object.sub;

import static glowredman.modularmaterials.MM_Reference.RAND;

import java.util.ArrayList;
import java.util.List;

import glowredman.modularmaterials.client.FormattingHandler;

public class ItemProperties {
	
	public boolean isFireResistant = false;
	public boolean isFoil = false;
	public int lifespan = 6000;
	public AdvRarity rarity = AdvRarity.COMMON;
	public List<String> typeEnabledTags = new ArrayList<>();
	public List<String> tags = new ArrayList<>();
	
	@Override
	public String toString() {
		return String.format("{isFireResistant: %b, isFoil: %b, lifespan: %d, rarity: %s, typeEnabledTags: %s, tags: %s}",
				isFireResistant, isFoil, lifespan, rarity, FormattingHandler.listToString(typeEnabledTags), FormattingHandler.listToString(tags));
	}
	
	public static ItemProperties random() {
		ItemProperties i = new ItemProperties();
		i.isFireResistant = RAND.nextBoolean();
		i.isFoil = RAND.nextBoolean();
		i.lifespan = RAND.nextInt(10000);
		i.rarity = AdvRarity.random();
		return i;
	}

}
