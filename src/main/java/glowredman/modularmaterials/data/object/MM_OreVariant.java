package glowredman.modularmaterials.data.object;

import java.util.ArrayList;
import java.util.List;

import glowredman.modularmaterials.data.TagHandler;

public class MM_OreVariant {

	public boolean enabled = true;
	public boolean falling = false;
	public String variantName = "variant_" + this.hashCode();
	public String baseBlock = "minecraft:stone";
	public String baseTexture = "minecraft:block/stone";
	public String nameSyntax = TagHandler.PARAM_MATERIAL + " Ore";
	public List<String> tags = new ArrayList<>();
	
	@Override
	public String toString() {
		return String.format("{enabled: %b, falling: %b, variantName: %s, baseBlock: %s, baseTexture: %s, nameSyntax: %s, tags: %s}",
				enabled, falling, variantName, baseBlock, baseTexture, nameSyntax, tags);
	}

}
