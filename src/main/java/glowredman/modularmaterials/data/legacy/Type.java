package glowredman.modularmaterials.data.legacy;

import glowredman.modularmaterials.data.TagHandler;
import glowredman.modularmaterials.data.object.MM_Type;

public class Type {
	
	public String category = "item";
	public String effectiveTool = "pickaxe";
	public boolean enabled = false;
	public boolean hasTooltip = true;
	public boolean isBeaconBase = false;
	public boolean isBeaconPayment = false;
	public String oreDictPrefix = "";
	public String state = "solid";
	public String syntax = "%s";
	public String unitValue = "1u";
	
	public MM_Type convert() {
		MM_Type t = new MM_Type();
		t.category = LegacyHandler.category(category);
		t.enabled = enabled;
		t.hasTooltip = hasTooltip;
		t.nameSyntax = syntax.replace("%s", TagHandler.PARAM_MATERIAL).replace("%l", TagHandler.PARAM_MATERIAL);
		t.state = LegacyHandler.state(state);
		t.tagName = oreDictPrefix.toLowerCase() + "s";
		if(!category.equals("fluid")) {
			t.tags.add("forge:" + TagHandler.PARAM_TYPE);
			t.tags.add("forge:" + TagHandler.PARAM_TYPE + "/" + TagHandler.PARAM_MATERIAL);
		}
		return t;
	}

}
