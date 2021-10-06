package glowredman.modularmaterials.data.object;

import static glowredman.modularmaterials.MM_Reference.RAND;

import java.util.ArrayList;
import java.util.List;

import glowredman.modularmaterials.client.FormattingHandler;
import glowredman.modularmaterials.data.TagHandler;
import glowredman.modularmaterials.data.object.sub.Category;
import glowredman.modularmaterials.data.object.sub.ChemicalState;

public class MM_Type {
	
	public boolean enabled = false;
	public String tagName = "type_" + this.hashCode();
	public Category category = Category.ITEM;
	public ChemicalState state = ChemicalState.SOLID;
	public float burnTimeMultiplier = 1.0f;
	public float enchantPowerBonuMultipliers = 1.0f;
	public float fireSpreadSpeedMultiplier = 1.0f;
	public float flammabilityMultiplier = 1.0f;
	public float jumpFactorMultiplier = 1.0f;
	public float lightLevelMultiplier = 1.0f;
	public float resistanceMultiplier = 1.0f;
	public float speedFactorMultiplier = 1.0f;
	public boolean hasTooltip = true;
	public String nameSyntax = TagHandler.PARAM_MATERIAL;
	public List<String> enableTags = new ArrayList<>();
	public List<String> tags = new ArrayList<>();
	
	@Override
	public String toString() {
		return String.format("{enabled: %b, tagNames: %s, category: %s, state: %s, burnTimeMultiplier: %f, enchantPowerBonuMultipliers: %f, fireSpreadSpeedMultiplier: %f, flammabilityMultiplier: %f, jumpFactorMultiplier: %f, lightLevelMultiplier: %f, resistanceMultiplier: %f, speedFactorMultiplier: %f, hasTooltip: %b, nameSyntax: %s, enableTags: %s, tags: %s}",
				enabled, tagName, category, state, burnTimeMultiplier, enchantPowerBonuMultipliers, fireSpreadSpeedMultiplier, flammabilityMultiplier, jumpFactorMultiplier, lightLevelMultiplier, resistanceMultiplier, speedFactorMultiplier, hasTooltip, nameSyntax, FormattingHandler.listToString(enableTags), FormattingHandler.listToString(tags));
	}
	
	public static MM_Type random() {
		MM_Type t = new MM_Type();
		t.burnTimeMultiplier = RAND.nextFloat() * 2;
		t.enchantPowerBonuMultipliers = RAND.nextFloat() * 2;
		t.fireSpreadSpeedMultiplier = RAND.nextFloat() * 2;
		t.flammabilityMultiplier = RAND.nextFloat() * 2;
		t.jumpFactorMultiplier = RAND.nextFloat() * 2;
		t.lightLevelMultiplier = RAND.nextFloat() * 2;
		t.resistanceMultiplier = RAND.nextFloat() * 2;
		t.speedFactorMultiplier = RAND.nextFloat() * 2;
		t.category = Category.random();
		t.state = ChemicalState.random();
		t.enabled = RAND.nextBoolean();
		t.hasTooltip = RAND.nextBoolean();
		return t;
	}

}
