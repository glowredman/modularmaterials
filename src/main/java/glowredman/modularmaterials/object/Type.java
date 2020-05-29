package glowredman.modularmaterials.object;

public class Type {
	
	public String category;
	public boolean enabled;
	public boolean hasTooltip;
	public boolean isBeaconBase;
	public boolean isBeaconPayment;
	public String oreDictPrefix;
	public String state;
	public String syntax;
	public String unitValue;
	
	public Type() {
	}

	public String getCategory() {
		return category;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean hasTooltip() {
		return hasTooltip;
	}

	public boolean isBeaconBase() {
		return isBeaconBase;
	}

	public boolean isBeaconPayment() {
		return isBeaconPayment;
	}

	public String getOreDictPrefix() {
		return oreDictPrefix;
	}

	public String getState() {
		return state;
	}

	public String getSyntax() {
		return syntax;
	}

	public String getUnitValue() {
		return unitValue;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setHasTooltip(boolean hasTooltip) {
		this.hasTooltip = hasTooltip;
	}

	public void setBeaconBase(boolean isBeaconBase) {
		this.isBeaconBase = isBeaconBase;
	}

	public void setBeaconPayment(boolean isBeaconPayment) {
		this.isBeaconPayment = isBeaconPayment;
	}

	public void setOreDictPrefix(String oreDictPrefix) {
		this.oreDictPrefix = oreDictPrefix;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

	public void setUnitValue(String unitValue) {
		this.unitValue = unitValue;
	}
	
}
