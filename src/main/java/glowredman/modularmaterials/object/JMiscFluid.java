package glowredman.modularmaterials.object;

import static glowredman.modularmaterials.Reference.*;

import javax.annotation.Nonnegative;

public class JMiscFluid {
	
	public JColor color = new JColor();
	public int density = fDensity;
	public boolean enabled = fEnabled;
	public boolean isGaseous = fIsGaseous;
	public byte lightLevel = fLightLevel;
	public String name = "fluid_" + hashCode();
	public int temperature = fTemperature;
	public String texture = fTexture;
	public boolean useColor = fUseColor;
	@Nonnegative
	public int viscosity = fViscosity;

}
