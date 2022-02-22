package glowredman.modularmaterials.data.object;

import java.util.ArrayList;
import java.util.List;

import glowredman.modularmaterials.data.TagHandler;
import glowredman.modularmaterials.data.object.sub.Category;
import glowredman.modularmaterials.data.object.sub.ChemicalState;

public class MM_Type {
	
	public boolean enabled = false;
	public String tagName = "type_" + this.hashCode();
	public Category category = Category.ITEM;
	public ChemicalState state = ChemicalState.SOLID;
	public float burnTimeMultiplier = 1.0f;
	public float enchantPowerBonusMultiplier = 1.0f;
	public float fireSpreadSpeedMultiplier = 1.0f;
	public float flammabilityMultiplier = 1.0f;
	public float jumpFactorMultiplier = 1.0f;
	public float lightLevelMultiplier = 1.0f;
	public float resistanceMultiplier = 1.0f;
	public float speedFactorMultiplier = 1.0f;
	public boolean hasTooltip = true;
	public String nameSyntax = TagHandler.PARAM_MATERIAL;
	public List<String> tags = new ArrayList<>();
	
	@Override
	public String toString() {
		return String.format("{enabled: %b, tagNames: %s, category: %s, state: %s, burnTimeMultiplier: %f, enchantPowerBonusMultiplier: %f, fireSpreadSpeedMultiplier: %f, flammabilityMultiplier: %f, jumpFactorMultiplier: %f, lightLevelMultiplier: %f, resistanceMultiplier: %f, speedFactorMultiplier: %f, hasTooltip: %b, nameSyntax: %s, tags: %s}",
				enabled, tagName, category, state, burnTimeMultiplier, enchantPowerBonusMultiplier, fireSpreadSpeedMultiplier, flammabilityMultiplier, jumpFactorMultiplier, lightLevelMultiplier, resistanceMultiplier, speedFactorMultiplier, hasTooltip, nameSyntax, tags);
	}

}
