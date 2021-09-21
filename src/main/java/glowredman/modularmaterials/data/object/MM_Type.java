package glowredman.modularmaterials.data.object;

import static glowredman.modularmaterials.util.XSTR.XSTR_INSTANCE;

import java.util.ArrayList;
import java.util.List;

import glowredman.modularmaterials.client.FormattingHandler;
import glowredman.modularmaterials.data.TagHandler;
import glowredman.modularmaterials.data.object.sub.Category;

public class MM_Type {
	
	public boolean enabled = false;
	public String tagName = "type_" + this.hashCode();
	public Category category = Category.ITEM;
	public float burnTimeMultiplier = 1.0f;
	public boolean hasTooltip = true;
	public String nameSyntax = TagHandler.PARAM_MATERIAL;
	public List<String> enableTags = new ArrayList<>();
	public List<String> tags = new ArrayList<>();
	
	@Override
	public String toString() {
		return String.format("{enabled: %b, tagNames: %s, category: %s, burnTimeMultiplier: %f, hasTooltip: %b, enableTags: %s, tags: %s}", enabled, tagName, category, burnTimeMultiplier, hasTooltip, FormattingHandler.listToString(enableTags), FormattingHandler.listToString(tags));
	}
	
	public static MM_Type random() {
		MM_Type t = new MM_Type();
		t.burnTimeMultiplier = XSTR_INSTANCE.nextFloat();
		t.category = Category.random();
		t.enabled = XSTR_INSTANCE.nextBoolean();
		t.hasTooltip = XSTR_INSTANCE.nextBoolean();
		return t;
	}

}
