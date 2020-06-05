package glowredman.modularmaterials.object;

import java.util.HashMap;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import static glowredman.modularmaterials.Reference.stateFallback;;

public class Material {
	
	public float blockHardness;
	public int blockHarvestLevel;
	public byte blockLightLevel;
	public String blockMaterialSound = "IRON";
	public float blockResistance;
	public int boilingTemperature;
	public Color color = new Color();
	public boolean enabled;
	public HashMap<String, Boolean> enabledTypes = new HashMap<String, Boolean>();
	public int gasDensity;
	public byte gasLightLevel;
	@Nonnegative
	public int gasViscosity;
	public boolean isBeaconBase;
	public boolean isBeaconPayment;
	public int liquidDensity;
	public byte liquidLightLevel;
	@Nonnegative
	public int liquidViscosity;
	public int meltingTemperature;
	@Nonnegative
	@Nonnull
	public short meta;
	public String name = "material_" + meta;
	public String[] oreDict = new String[] {};
	public float oreHardness;
	public int oreHarvestLevel;
	public byte oreLightLevel;
	public float oreResistance;	
	public String state = stateFallback;
	public int temperature;
	public String texture = "";
	public String[] tooltip = new String[] {};

	public void setEnabledTypes(String type, boolean state) {
		enabledTypes.put(type, state);
	}

	public boolean isTypeEnabled(String type) {
		if(this.enabledTypes.containsKey(type)) {
			return enabledTypes.get(type);
		} else {
			return false;
		}
	}
	
	public void enableType(String type) {
		enabledTypes.put(type, true);
	}

	public void setColor(java.awt.Color color) {
		this.color.setColor(color);
	}
	
}
