package glowredman.modularmaterials.object;

import java.util.ArrayList;
import java.util.List;

public class MiscBlock {
	
	public Color color = new Color();
	public List<Drop> drops = new ArrayList<Drop>();
	public String effectiveTool;
	public boolean enabled;
	public float hardness;
	public int harvestLevel;
	public boolean isBeaconBase;
	public boolean isBeaconPayment;
	public byte lightLevel;
	public String materialSound;
	public String name = "block_" + hashCode();
	public String[] oreDict = new String[] {};
	public float resistance;
	public String texture;
	public String[] tooltip = new String[] {};
	public boolean useColor;

}
