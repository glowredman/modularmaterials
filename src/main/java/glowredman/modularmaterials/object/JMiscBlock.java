package glowredman.modularmaterials.object;

import static glowredman.modularmaterials.Reference.*;

import java.util.ArrayList;
import java.util.List;

public class JMiscBlock {
	
	public JColor color = new JColor();
	public List<JDrop> drops = new ArrayList<JDrop>();
	public String effectiveTool = bEffectiveTool;
	public boolean enabled = bEnabled;
	public float hardness = bHardness;
	public int harvestLevel = bHarvestLevel;
	public boolean isBeaconBase = bIsBeaconBase;
	public boolean isBeaconPayment = bIsBeaconPayment;
	public byte lightLevel = bLightLevel;
	public String mapColor = bMapColor;
	public String material = bMaterial;
	public String name = "block_" + hashCode();
	public boolean obeysGravity = bObeysGravity;
	public String[] oreDict = new String[] {};
	public float resistance = bResistance;
	public String sound = bSound;
	public String texture = bTexture;
	public String[] tooltip = new String[] {};
	public boolean useColor = bUseColor;

}
