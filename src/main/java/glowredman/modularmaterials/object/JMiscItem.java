package glowredman.modularmaterials.object;

import static glowredman.modularmaterials.Reference.*;

public class JMiscItem {
	
	public JColor color = new JColor();
	public boolean enabled = iEnabled;
	public boolean isBeaconPayment = iIsBeaconPayment;
	public short meta = (short) (Math.random() * Short.MAX_VALUE);
	public String name = "item_" + meta;
	public String[] oreDict = new String[] {};
	public JTexture texture = new JTexture();
	public String[] tooltip = new String[] {};
	public boolean useColor = iUseColor;

}
