package glowredman.modularmaterials.data.legacy;

import glowredman.modularmaterials.data.TagHandler;
import glowredman.modularmaterials.data.object.MM_OreVariant;
import net.minecraft.commands.CommandSourceStack;

public class OreVariant {
	
	public String baseBlock = "stone:0";
	public String baseTexture = "blocks/stone";
	public String effectiveTool = "pickaxe";
	public boolean enabled = false;
	public String materialSound = "STONE";
	public boolean obeysGravity = false;
	public String oreDictPrefix = "";
	public String syntax = "%s Ore";
	
	public MM_OreVariant convert(String name, CommandSourceStack css) {
		MM_OreVariant v = new MM_OreVariant();
		v.baseBlock = LegacyHandler.baseBlock(baseBlock, css);
		v.baseTexture = baseTexture;
		v.enabled = enabled;
		v.falling = obeysGravity;
		v.nameSyntax = syntax.replace("%s", TagHandler.PARAM_MATERIAL);
		v.variantName = name;
		v.tags.add("forge:ores");
		v.tags.add("minecraft:mineable/" + effectiveTool);
		if(oreDictPrefix.isEmpty())
			v.tags.add("forge:ores/" + TagHandler.PARAM_MATERIAL);
		else
			v.tags.add("forge:ores/" + oreDictPrefix.toLowerCase() + "/" + TagHandler.PARAM_MATERIAL);
		return v;
	}

}
