package glowredman.modularmaterials.object;

import javax.annotation.Nonnegative;

public class MiscFluid {
	
	public Color color = new Color();
	public int density;
	public boolean enabled;
	public boolean isGaseous;
	public byte lightLevel;
	public String name = "fluid_" + hashCode();
	public int temperature;
	public String texture;
	public boolean useColor;
	@Nonnegative
	public int viscosity;

}
