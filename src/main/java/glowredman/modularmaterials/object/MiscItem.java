package glowredman.modularmaterials.object;

import static glowredman.modularmaterials.Reference.*;

public class MiscItem {
	
	public Color color = new Color();
	public boolean enabled = iEnabled;
	public boolean isBeaconPayment = iIsBeaconPayment;
	public short meta = (short) (Math.random() * Short.MAX_VALUE);
	public String name = "item_" + meta;
	public String[] oreDict = new String[] {};
	public String texture = iTexture;
	public String[] tooltip = new String[] {};
	public boolean useColor = iUseColor;

}
