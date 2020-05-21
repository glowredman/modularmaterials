package glowredman.modularmaterials.object;

import java.util.HashMap;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

public class Material {
	
	public float blockHardness;
	public int blockHarvestLevel;
	public int blockLightLevel;
	public float blockResistance;
	public int boilingTemperature;
	public Color color = new Color();
	public boolean enabled;
	public HashMap<String, Boolean> enabledTypes = new HashMap<String, Boolean>();
	public int gasDensity;
	public int gasLightLevel;
	@Nonnegative
	public int gasViscosity;
	public boolean isBeaconPayment;
	public int liquidDensity;
	public int liquidLightLevel;
	@Nonnegative
	public int liquidViscosity;
	public int meltingTemperature;
	@Nonnegative
	@Nonnull
	public int meta;
	public String name;
	public String[] oreDict = new String[] {};
	public float oreHardness;
	public int oreHarvestLevel;
	public int oreLightLevel;
	public float oreResistance;	
	public String state;
	public int temperature;
	public String texture;
	public String[] tooltip = new String[] {};
	
	
	public Material() {
	}

	public int getGasLightLevel() {
		return gasLightLevel;
	}

	public int getLiquidLightLevel() {
		return liquidLightLevel;
	}

	public void setGasLightLevel(int gasLightLevel) {
		this.gasLightLevel = gasLightLevel;
	}

	public void setLiquidLightLevel(int liquidLightLevel) {
		this.liquidLightLevel = liquidLightLevel;
	}

	public float getBlockHardness() {
		return blockHardness;
	}

	public int getBlockHarvestLevel() {
		return blockHarvestLevel;
	}

	public int getBlockLightLevel() {
		return blockLightLevel;
	}

	public float getBlockResistance() {
		return blockResistance;
	}

	public String getTexture() {
		return texture;
	}

	public int getBoilingTemperature() {
		return boilingTemperature;
	}

	public Color getColor() {
		return color;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public HashMap<String, Boolean> getEnabledTypes() {
		return enabledTypes;
	}

	public int getGasDensity() {
		return gasDensity;
	}

	public int getGasViscosity() {
		return gasViscosity;
	}

	public boolean isBeaconPayment() {
		return isBeaconPayment;
	}

	public int getLiquidDensity() {
		return liquidDensity;
	}

	public int getLiquidViscosity() {
		return liquidViscosity;
	}

	public int getMeltingTemperature() {
		return meltingTemperature;
	}

	public int getMeta() {
		return meta;
	}

	public String getName() {
		return name;
	}

	public String[] getOreDict() {
		return oreDict;
	}

	public float getOreHardness() {
		return oreHardness;
	}

	public int getOreHarvestLevel() {
		return oreHarvestLevel;
	}

	public int getOreLightLevel() {
		return oreLightLevel;
	}

	public float getOreResistance() {
		return oreResistance;
	}

	public String getState() {
		return state;
	}

	public int getTemperature() {
		return temperature;
	}

	public String[] getTooltip() {
		return tooltip;
	}

	public void setBlockHardness(float blockHardness) {
		this.blockHardness = blockHardness;
	}

	public void setBlockHarvestLevel(int blockHarvestLevel) {
		this.blockHarvestLevel = blockHarvestLevel;
	}

	public void setBlockLightLevel(int blockLightLevel) {
		this.blockLightLevel = blockLightLevel;
	}

	public void setBlockResistance(float blockResistance) {
		this.blockResistance = blockResistance;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}

	public void setBoilingTemperature(int boilingTemperature) {
		this.boilingTemperature = boilingTemperature;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setEnabledTypes(HashMap<String, Boolean> enabledTypes) {
		this.enabledTypes = enabledTypes;
	}

	public void setEnabledTypes(String type, boolean state) {
		this.enabledTypes.put(type, state);
	}

	public void setGasDensity(int gasDensity) {
		this.gasDensity = gasDensity;
	}

	public void setGasViscosity(int gasViscosity) {
		this.gasViscosity = gasViscosity;
	}

	public void setBeaconPayment(boolean isBeaconPayment) {
		this.isBeaconPayment = isBeaconPayment;
	}

	public void setLiquidDensity(int liquidDensity) {
		this.liquidDensity = liquidDensity;
	}

	public void setLiquidViscosity(int liquidViscosity) {
		this.liquidViscosity = liquidViscosity;
	}

	public void setMeltingTemperature(int meltingTemperature) {
		this.meltingTemperature = meltingTemperature;
	}
	
	public void setMeta(short meta) {
		this.meta = meta;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOreDict(String[] oreDict) {
		this.oreDict = oreDict;
	}

	public void setOreHardness(float oreHardness) {
		this.oreHardness = oreHardness;
	}

	public void setOreHarvestLevel(int oreHarvestLevel) {
		this.oreHarvestLevel = oreHarvestLevel;
	}

	public void setOreLightLevel(int oreLightLevel) {
		this.oreLightLevel = oreLightLevel;
	}

	public void setOreResistance(float oreResistance) {
		this.oreResistance = oreResistance;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public void setTooltip(String[] tooltip) {
		this.tooltip = tooltip;
	}

	public boolean isTypeEnabled(String type) {
		if(this.getEnabledTypes().containsKey(type)) {
			return this.enabledTypes.get(type);
		} else {
			return false;
		}
	}
	
	public void enableType(String type) {
		this.enabledTypes.put(type, true);
	}

	public void setColor(java.awt.Color color) {
		this.color.setColor(color);
	}
	
}
