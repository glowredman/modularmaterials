package glowredman.modularmaterials.data.object.sub;

import static glowredman.modularmaterials.util.XSTR.XSTR_INSTANCE;

import java.util.ArrayList;
import java.util.List;

import glowredman.modularmaterials.client.FormattingHandler;

public class ItemProperties {
	
	public boolean isFireResistant = false;
	public boolean isFoil = false;
	public boolean isPiglinCurrency = false;
	public int lifespan = 6000;
	public boolean makesPiglinsNeutral = false;
	public AdvRarity rarity = AdvRarity.COMMON;
	public List<String> typeEnabledTags = new ArrayList<>();
	public List<String> tags = new ArrayList<>();
	
	@Override
	public String toString() {
		return String.format("{isFireResistant: %b, isFoil: %b, isPiglinCurrency: %b, lifespan: %d, makesPiglinsNeutral: %b, rarity: %s, typeEnabledTags: %s, tags: %s}",
				isFireResistant, isFoil, isPiglinCurrency, lifespan, makesPiglinsNeutral, rarity, FormattingHandler.listToString(typeEnabledTags), FormattingHandler.listToString(tags));
	}
	
	public static ItemProperties random() {
		ItemProperties i = new ItemProperties();
		i.isFireResistant = XSTR_INSTANCE.nextBoolean();
		i.isFoil = XSTR_INSTANCE.nextBoolean();
		i.isPiglinCurrency = XSTR_INSTANCE.nextBoolean();
		i.lifespan = XSTR_INSTANCE.nextInt(10000);
		i.makesPiglinsNeutral = XSTR_INSTANCE.nextBoolean();
		i.rarity = AdvRarity.random();
		return i;
	}

}
