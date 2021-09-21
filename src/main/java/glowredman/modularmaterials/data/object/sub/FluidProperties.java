package glowredman.modularmaterials.data.object.sub;

import static glowredman.modularmaterials.util.XSTR.XSTR_INSTANCE;

public class FluidProperties {
	
	public int boilingTemperature = 374;
	public int meltingTemperature = 274;
	public int currentTemperature = 288;
	public StateBasedProperties gas = StateBasedProperties.random();
	public StateBasedProperties liquid = StateBasedProperties.random();
	
	@Override
	public String toString() {
		return String.format("{boilingTemperature: %d, meltingTemperature: %d, currentTemperature: %d, gas: %s, liquid: %s}",
				boilingTemperature, meltingTemperature, currentTemperature, gas, liquid);
	}
	
	public static FluidProperties random() {
		FluidProperties fluid = new FluidProperties();
		fluid.boilingTemperature = XSTR_INSTANCE.nextInt(Integer.MAX_VALUE);
		fluid.meltingTemperature = XSTR_INSTANCE.nextInt(Integer.MAX_VALUE - fluid.boilingTemperature);
		fluid.currentTemperature = XSTR_INSTANCE.nextInt(Integer.MAX_VALUE);
		fluid.gas = StateBasedProperties.random();
		fluid.liquid = StateBasedProperties.random();
		return fluid;
	}
	
	public static class StateBasedProperties {
		
		public int density = 1000;
		public int viscosity = 1000;
		public int lightLevel = 0;
		
		@Override
		public String toString() {
			return String.format("{density: %d, lightLevel: %d, viscosity: %d}", density, lightLevel, viscosity);
		}
		
		public static StateBasedProperties random() {
			StateBasedProperties s = new StateBasedProperties();
			s.density = XSTR_INSTANCE.nextInt();
			s.lightLevel = XSTR_INSTANCE.nextInt(16);
			s.viscosity = XSTR_INSTANCE.nextInt();
			return s;
		}
		
	}

}
