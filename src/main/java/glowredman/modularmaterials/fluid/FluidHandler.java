package glowredman.modularmaterials.fluid;

import static glowredman.modularmaterials.Main.logger;
import static glowredman.modularmaterials.Reference.*;

import java.util.Map.Entry;

import glowredman.modularmaterials.object.JMaterial;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidHandler {
		
	public static void registerFluids() {
		long time = System.currentTimeMillis();
		int counter = 0;
		
		//iterate through all materials
		for(Entry<String, JMaterial> materialEntry : materials.entrySet()) {
			JMaterial material = materialEntry.getValue();
			
			//check, if the material should be registered
			if(material.enabled || enableAll) {
				for(Entry<String, Boolean> typeEntry : material.enabledTypes.entrySet()) {
					String type = typeEntry.getKey();
					
					//check if the type is a fluid and enabled
					boolean b = false;
					try {
						b = typeEntry.getValue() && types.get(typeEntry.getKey()).category.equals("fluid") && types.get(typeEntry.getKey()).enabled;
					} catch (Exception e) {
						if(!suppressTypeMissingWarnings) {
							logger.error(CONFIGNAME_TYPES + " does not contain information for the type \"" + type + "\"! Add \"" + type + "\" to " + CONFIGNAME_TYPES + " or enable 'suppressMissingTypeWarnings' in " + CONFIGNAME_CORE + '.');
						}
					}
					if(b) {
						String texture = "fluids/" + material.texture + '/' + type;
						String name = materialEntry.getKey().toLowerCase().replace(' ', '_');
						String unlocalizedName = name;
						int temperature = material.temperature;
						int color = material.color.getARGB();
						int density = 1000;
						boolean isGaseous = false;
						int luminosity = 0;
						int viscosity = 1000;
						
						switch (types.get(type).state) {
						case "gaseous":
							
							//if the materials state is also gaseous, the stats stay the same, otherwise do this:
							if(!material.state.equals("gaseous")) {
								name = "gaseous_" + name;
								temperature = material.boilingTemperature;
							}
							density = material.gasDensity;
							isGaseous = true;
							luminosity = material.gasLightLevel;
							viscosity = material.gasViscosity;
							break;
						case "liquid":
							switch (material.state) {
							case "gaseous":
								name = "liquid_" + name;
								temperature = material.meltingTemperature;
								break;
							case "liquid":
								break;
							default:
								temperature = material.meltingTemperature;
								break;
							}
							density = material.liquidDensity;
							luminosity = material.liquidLightLevel;
							viscosity = material.liquidViscosity;
							break;
						default:
							name = types.get(type).state + name;
							break;
						}
						
						Fluid fluid = new Fluid(name, new ResourceLocation(MODID, texture + "_still"), new ResourceLocation(MODID, texture + "_flowing"), color);
						fluid.setDensity(density);
						fluid.setGaseous(isGaseous);
						fluid.setLuminosity(luminosity);
						fluid.setTemperature(temperature);
						fluid.setUnlocalizedName(MODID + '.' + type + '.' + unlocalizedName);
						fluid.setViscosity(viscosity);
						FluidRegistry.registerFluid(fluid);
						FluidRegistry.addBucketForFluid(fluid);
						fluids.add(fluid);
						counter++;
					}
				}
			}
		}
		logger.info("Registered " + counter + " fluids in " + (System.currentTimeMillis() - time) + "ms.");
	}
	
}
