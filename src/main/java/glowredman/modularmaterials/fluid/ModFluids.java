package glowredman.modularmaterials.fluid;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.information.Reference;
import glowredman.modularmaterials.json.JMaterial;
import glowredman.modularmaterials.util.MiscUtils;
import glowredman.modularmaterials.util.TagHandler;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids {
	
	public static void preInit() {
		
		long time = System.nanoTime();
		int fluidCount = 0;
		
		for (JMaterial material : Reference.materialList.getMaterials()) {
			
			//liquids
			if(TagHandler.isValidLiquid(material)) {
				FluidBase fluid = new FluidBase(TagHandler.convertToLiquidName(material), false, TagHandler.getValidTemperatureForLiquid(material), TagHandler.getValidLuminosityForLiquid(material), material);
				FluidRegistry.registerFluid(fluid);
				TagHandler.addBucketForFluid(fluid, material);
				fluidCount++;
			}
			//gases
			if (TagHandler.isValidGas(material)) {
				FluidBase fluid = new FluidBase(TagHandler.convertToGasName(material), true, TagHandler.getValidTemperatureForGas(material), material.getLightLevel(), material);
				FluidRegistry.registerFluid(fluid);
				TagHandler.addBucketForFluid(fluid, material);
				fluidCount++;
			}
		
		}
		Main.logger.info("Finished registering all " + fluidCount + " fluids. Took " + MiscUtils.timer(time));
		
	}

}
