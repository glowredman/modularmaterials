package glowredman.modularmaterials.fluid;

import glowredman.modularmaterials.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class MetaFluid extends Fluid {

	public MetaFluid(String fluidName, String texture, int color) {
		super(fluidName, new ResourceLocation(Reference.MODID, texture + "_still"), new ResourceLocation(Reference.MODID, texture + "_flowing"), color);
	}
	
}
