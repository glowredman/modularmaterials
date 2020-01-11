package glowredman.modularmaterials.json;

public class JItem {

	public JText name = new JText();
	public int meta;
	public String[] oreDict = new String[]{};
	public boolean disabled;
	public String texture;
	public boolean useCustomTexture;
	public String iconSet;
	public JColor color = new JColor();
	public JText[] tooltip = new JText[]{};
	
	public JItem(JText name, int meta, String[] oreDict, boolean disabled, String texture, JText[] tooltip) {
		this.color = new JColor();
		this.disabled = disabled;
		this.iconSet = "";
		this.meta = meta;
		this.name = name;
		this.oreDict = oreDict;
		this.texture = texture;
		this.tooltip = tooltip;
		this.useCustomTexture = true;
	}
	
	public JItem(String name, int meta, String[] oreDict, boolean disabled, String texture, JText[] tooltip) {
		this.color = new JColor();
		this.disabled = disabled;
		this.iconSet = "";
		this.meta = meta;
		this.name = new JText(name);
		this.oreDict = oreDict;
		this.texture = texture;
		this.tooltip = tooltip;
		this.useCustomTexture = true;
	}
	
	public JItem(JText name, int meta, String[] oreDict, boolean disabled, String texture) {
		this.color = new JColor();
		this.disabled = disabled;
		this.iconSet = "";
		this.meta = meta;
		this.name = name;
		this.oreDict = oreDict;
		this.texture = texture;
		this.tooltip = new JText[]{};
		this.useCustomTexture = true;
	}
	
	public JItem(String name, int meta, String[] oreDict, boolean disabled, String texture) {
		this.color = new JColor();
		this.disabled = disabled;
		this.iconSet = "";
		this.meta = meta;
		this.name = new JText(name);
		this.oreDict = oreDict;
		this.texture = texture;
		this.tooltip = new JText[]{};
		this.useCustomTexture = true;
	}
	
	public JItem(JText name, int meta, String[] oreDict, boolean disabled, String iconSet, JColor color, JText[] tooltip) {
		this.color = color;
		this.disabled = disabled;
		this.iconSet = iconSet;
		this.meta = meta;
		this.name = name;
		this.oreDict = oreDict;
		this.texture = "";
		this.tooltip = tooltip;
		this.useCustomTexture = false;
	}
	
	public JItem(String name, int meta, String[] oreDict, boolean disabled, String iconSet, JColor color, JText[] tooltip) {
		this.color = color;
		this.disabled = disabled;
		this.iconSet = iconSet;
		this.meta = meta;
		this.name = new JText(name);
		this.oreDict = oreDict;
		this.texture = "";
		this.tooltip = tooltip;
		this.useCustomTexture = true;
	}
	
	public JItem(JText name, int meta, String[] oreDict, boolean disabled, String iconSet, JColor color) {
		this.color = color;
		this.disabled = disabled;
		this.iconSet = iconSet;
		this.meta = meta;
		this.name = name;
		this.oreDict = oreDict;
		this.texture = "";
		this.tooltip = new JText[]{};
		this.useCustomTexture = true;
	}
	
	public JItem(String name, int meta, String[] oreDict, boolean disabled, String iconSet, JColor color) {
		this.color = color;
		this.disabled = disabled;
		this.iconSet = iconSet;
		this.meta = meta;
		this.name = new JText(name);
		this.oreDict = oreDict;
		this.texture = "";
		this.tooltip = new JText[]{};
		this.useCustomTexture = true;
	}
	
	public JItem() {
	}

	public JColor getColor() {
		return this.color;
	}
	
	public boolean isDisabled() {
		return this.disabled;
	}
	
	public String getIconSet() {
		return this.iconSet;
	}
	
	public int getMeta() {
		return this.meta;
	}
	
	public JText getName() {
		return this.name;
	}
	
	public String[] getOreDict() {
		return this.oreDict;
	}
	
	public String getTexture() {
		return this.texture;
	}
	
	public JText[] getTooltip() {
		return this.tooltip;
	}
	
	public boolean usesCustomTexture() {
		return this.useCustomTexture;
	}

}
