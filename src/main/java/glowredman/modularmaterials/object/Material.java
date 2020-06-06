package glowredman.modularmaterials.object;

import java.util.HashMap;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import glowredman.modularmaterials.util.MaterialHandler;

import static glowredman.modularmaterials.Reference.*;

public class Material {
	
	public float blockHardness = mBlockHardness;
	public int blockHarvestLevel = mBlockHarvestLevel;
	public byte blockLightLevel = mBlockLightLevel;
	public String blockMaterialSound = mBlockMaterialSound;
	public float blockResistance = mBlockResistance;
	public int boilingTemperature = mBoilingTemperature;
	public Color color = new Color();
	public boolean enabled = mEnabled;
	public HashMap<String, Boolean> enabledTypes = MaterialHandler.getAllTypesEqualHashMap(mTypeEnabled);
	public int gasDensity = mGasDenisity;
	public byte gasLightLevel = mGasLightLevel;
	@Nonnegative
	public int gasViscosity = mGasViscosity;
	public boolean isBeaconBase = mIsBeaconBase;
	public boolean isBeaconPayment = mIsBeaconPayment;
	public int liquidDensity = mLiquidDensity;
	public byte liquidLightLevel = mLiquidLightLevel;
	@Nonnegative
	public int liquidViscosity = mLiquidViscosity;
	public int meltingTemperature = mMeltingTemperature;
	@Nonnegative
	@Nonnull
	public short meta = (short) (Math.random() * Short.MAX_VALUE);
	public String name = "material_" + meta;
	public String[] oreDict = new String[] {};
	public float oreHardness = mOreHardness;
	public int oreHarvestLevel = mOreHarvestLevel;
	public byte oreLightLevel = mOreLightLevel;
	public float oreResistance = mOreResistance;
	public String state = mState;
	public int temperature = mTemperature;
	public String texture = mTexture;
	public String[] tooltip = new String[] {};

	public void setType(String type, boolean state) {
		enabledTypes.put(type, state);
	}

	public boolean isTypeEnabled(String type) {
		if(enabledTypes.containsKey(type)) {
			return enabledTypes.get(type);
		} else {
			return mTypeEnabled;
		}
	}
	
	public void enableType(String type) {
		enabledTypes.put(type, true);
	}

	public void setColor(java.awt.Color color) {
		this.color.setColor(color);
	}
	
}
