package glowredman.modularmaterials.fluid;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.information.Properties;
import glowredman.modularmaterials.information.Reference;
import glowredman.modularmaterials.json.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class FluidBase extends Fluid{

	public FluidBase(String name, boolean isGaseous, int temperature, int luminosity, Material material) {
		super(name, new ResourceLocation(Reference.MODID + ":fluids/" + material.getFluidTexture() + "_still"), new ResourceLocation(Reference.MODID + ":fluids/" + material.getFluidTexture() + "_flow"));
		this.setColor(material.getColor().getColorRGBA()); //0-255 (0x00-0xff) each
		this.setGaseous(isGaseous);
		this.setTemperature(temperature); //default is 300 ("room temperature"), in Kelvin
		this.setUnlocalizedName(Reference.MODID + '.' + name + ".name");
		this.setViscosity(material.getViscosity()); //cannot be negative, default is 1000
		this.setLuminosity(luminosity); //0-15
		
		if (isGaseous && (Reference.defaultDensityOffset != 0)) {
			this.setDensity(Reference.defaultDensityOffset + material.getDensity());
		} else {
			this.setDensity(material.getDensity()); //default (water) is 1000, negative means lighter than air
		}
		try {
			if (material.getName().getFormat()[0].contains(Properties.AN)) {
				//TODO implement custom text
			} else {
				this.setRarity(material.getName().getRarity());
			}
		} catch (Exception e) {
			Main.logger.error(e + " (material \"" + material.getName().getText() + "\" has no formatting for its name!)");
		}
		
	}
	
	public static Fluid getFluidFromStack(FluidStack stack) {
		return stack == null ? null : stack.getFluid();
	}
	
	public static String getFluidName(FluidStack stack) {
		Fluid fluid = getFluidFromStack(stack);
		return getFluidName(fluid);
	}
	
	public static String getFluidName(Fluid fluid) {
		return fluid == null ? "null" : fluid.getName();
	}
	
	public static int getAmount(FluidStack stack) {
		return stack == null ? 0 : stack.amount;
	}

}
