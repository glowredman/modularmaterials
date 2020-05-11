package glowredman.modularmaterials.fluid;

import java.util.Iterator;
import java.util.Map.Entry;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.Reference;
import glowredman.modularmaterials.object.Material;
import glowredman.modularmaterials.util.MaterialHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidHandler {
	
	public static void registerFluids() {
		long time = System.currentTimeMillis();
		int counter = 0;
		
		//iterate through all materials
		Iterator materials = MaterialHandler.getIterator(Reference.materials);
		while(materials.hasNext()) {
			Entry<String, Material> materialEntry = (Entry<String, Material>) materials.next();
			Material material = materialEntry.getValue();
			
			//check, if the material should be registered
			if(!material.isDisabled() || Reference.enableAll) {
				Iterator i = MaterialHandler.getIterator(material.getEnabledTypes());
				while(i.hasNext()) {
					Entry<String, Boolean> typeEntry = (Entry<String, Boolean>) i.next();
					String type = typeEntry.getKey();
					
					//check if the type is a fluid and enabled
					boolean b = false;
					try {
						b = (typeEntry.getValue() && Reference.types.get(typeEntry.getKey()).getCategory().equals("fluid") && !Reference.types.get(typeEntry.getKey()).isDisabled()) ? true : false;
					} catch (Exception e) {
						if(!Reference.suppressTypeMissingWarnings) {
							Main.logger.error(Reference.CONFIGNAME_TYPES + " does not contain information for the type \"" + type + "\"! Add \"" + type + "\" to " + Reference.CONFIGNAME_TYPES + " or enable 'suppressMissingTypeWarnings' in " + Reference.CONFIGNAME_CORE + '.');
						}
					}
					if(b) {
						String texture = "blocks/fluid/" + material.getTexture() + '/' + type;
						String name = materialEntry.getKey().toLowerCase().replace(' ', '_');
						String unlocalizedName = name;
						int temperature = material.getTemperature();
						int color = material.getColor().getARGB();
						int density = 1000;
						boolean isGaseous = false;
						int luminosity = 0;
						int viscosity = 1000;
						
						switch (Reference.types.get(type).getState()) {
						case "gaseous":
							
							//if the materials state is also gaseous, the stats stay the same, otherwise do this:
							if(!material.getState().equals("gaseous")) {
								name = "gaseous_" + name;
								temperature = material.getBoilingTemperature();
							}
							density = material.getGasDensity();
							isGaseous = true;
							luminosity = material.getGasLightLevel();
							viscosity = material.getGasViscosity();
							break;
						case "liquid":
							switch (material.getState()) {
							case "gaseous":
								name = "liquid_" + name;
								temperature = material.getMeltingTemperature();
								break;
							case "liquid":
								break;
							default:
								temperature = material.getMeltingTemperature();
								break;
							}
							density = material.getLiquidDensity();
							luminosity = material.getLiquidLightLevel();
							viscosity = material.getLiquidViscosity();
							break;
						default:
							name = Reference.types.get(type).getState() + name;
							break;
						}
						
						Fluid fluid = new Fluid(name, new ResourceLocation(Reference.MODID, texture + "_still"), new ResourceLocation(Reference.MODID, texture + "_flowing"), color);
						System.out.println(fluid.getStill().getResourceDomain() + " | " + fluid.getStill().getResourcePath());
						fluid.setDensity(density);
						fluid.setGaseous(isGaseous);
						fluid.setLuminosity(luminosity);
						fluid.setTemperature(temperature);
						fluid.setUnlocalizedName(Reference.MODID + '.' + type + '.' + unlocalizedName);
						fluid.setViscosity(viscosity);
						FluidRegistry.registerFluid(fluid);
						FluidRegistry.addBucketForFluid(fluid);
						counter++;
					}
				}
			}
		}
		Main.logger.info("Registered " + counter + " fluids in " + (System.currentTimeMillis() - time) + "ms.");
	}
	
}
