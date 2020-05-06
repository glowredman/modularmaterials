package glowredman.modularmaterials.object;

public class Type {
	
	public boolean disabled;
	public String category;
	public String oreDictPrefix;
	public String syntax;
	public String unitValue;
	
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
	public String getUnitValue() {
		return unitValue;
	}
	public void setUnitValue(String unitValue) {
		this.unitValue = unitValue;
	}
	public String getCategory() {
		return category;
	}
	public String getOreDictPrefix() {
		return oreDictPrefix;
	}
	public String getSyntax() {
		return syntax;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setOreDictPrefix(String oreDictPrefix) {
		this.oreDictPrefix = oreDictPrefix;
	}
	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}
	
}
