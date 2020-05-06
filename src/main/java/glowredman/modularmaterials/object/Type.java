package glowredman.modularmaterials.object;

public class Type {
	
	public String category;
	public boolean disabled;
	public String oreDictPrefix;
	public String state;
	public String syntax;
	public String unitValue;
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
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
