package glowredman.modularmaterials.object;

import static glowredman.modularmaterials.Reference.*;

public class OreVariant {

	public String baseBlock = "";
	public String baseTexture = "";
	public String effectiveTool = effectiveToolFallback;
	public boolean enabled;
	public String materialSound = "ROCK";
	public boolean obeysGravity;
	public String oreDictPrefix = "";
	public String syntax = "%s Ore";
	
	public OreVariant() {
	}

	public String getBaseBlock() {
		return baseBlock;
	}

	public String getBaseTexture() {
		return baseTexture;
	}

	public String getEffectiveTool() {
		return effectiveTool;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public String getMaterialSound() {
		return materialSound;
	}

	public boolean obeysGravity() {
		return obeysGravity;
	}

	public String getOreDictPrefix() {
		return oreDictPrefix;
	}

	public void setBaseBlock(String baseBlock) {
		this.baseBlock = baseBlock;
	}

	public void setBaseTexture(String baseTexture) {
		this.baseTexture = baseTexture;
	}

	public void setEffectiveTool(String effectiveTool) {
		this.effectiveTool = effectiveTool;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setMaterialSound(String materialSound) {
		this.materialSound = materialSound;
	}

	public void setObeysGravity(boolean obeysGravity) {
		this.obeysGravity = obeysGravity;
	}

	public void setOreDictPrefix(String oreDictPrefix) {
		this.oreDictPrefix = oreDictPrefix;
	}

	public String getSyntax() {
		return syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}
	
}
