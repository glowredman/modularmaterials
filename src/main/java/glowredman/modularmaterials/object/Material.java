package glowredman.modularmaterials.object;

import static glowredman.modularmaterials.Reference.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nonnegative;

import glowredman.modularmaterials.util.MaterialHandler;

public class Material {
	
	public float blockHardness = mBlockHardness;
	public int blockHarvestLevel = mBlockHarvestLevel;
	public byte blockLightLevel = mBlockLightLevel;
	public String blockMaterialSound = mBlockMaterialSound;
	public float blockResistance = mBlockResistance;
	public int boilingTemperature = mBoilingTemperature;
	public Color color = new Color();
	public List<Drop> drops = new ArrayList<Drop>();
	public boolean enabled = mEnabled;
	public HashMap<String, Boolean> enabledTypes = MaterialHandler.getAllTypesEqualHashMap(mTypeEnabled); //typeKey, isEnabled
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

	public boolean isTypeEnabled(String type) {
		if(enabledTypes.containsKey(type)) {
			return enabledTypes.get(type);
		} else {
			return mTypeEnabled;
		}
	}
	
}
