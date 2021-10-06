package glowredman.modularmaterials.data.object.sub;

import static glowredman.modularmaterials.MM_Reference.RAND;

import java.util.ArrayList;
import java.util.List;

import glowredman.modularmaterials.client.FormattingHandler;

public class FluidProperties {
	
	public int boilingTemperature = 374;
	public int meltingTemperature = 274;
	public int currentTemperature = 288;
	public StateBasedProperties gas = StateBasedProperties.random();
	public StateBasedProperties liquid = StateBasedProperties.random();
	public List<String> typeEnabledTags = new ArrayList<>();
	public List<String> tags = new ArrayList<>();
	
	@Override
	public String toString() {
		return String.format("{boilingTemperature: %d, meltingTemperature: %d, currentTemperature: %d, gas: %s, liquid: %s, typeEnabledTags: %s, tags: %s}",
				boilingTemperature, meltingTemperature, currentTemperature, gas, liquid, FormattingHandler.listToString(typeEnabledTags), FormattingHandler.listToString(tags));
	}
	
	public int getTemperature(ChemicalState currentState, ChemicalState reqState) {
		if(currentState == reqState) {
			return currentTemperature;
		}
		else if(reqState == ChemicalState.GASEOUS) {
			return boilingTemperature;
		}
		else if(reqState == ChemicalState.LIQUID) {
			return meltingTemperature;
		}
		//should only be returned if either currentState xor reqState is SOLID
		return currentTemperature;
	}
	
	public StateBasedProperties propertiesOfState(ChemicalState state) {
		return state == ChemicalState.GASEOUS ? gas : liquid;
	}
	
	public static FluidProperties random() {
		FluidProperties fluid = new FluidProperties();
		fluid.boilingTemperature = RAND.nextInt(Integer.MAX_VALUE);
		fluid.meltingTemperature = RAND.nextInt(Integer.MAX_VALUE - fluid.boilingTemperature);
		fluid.currentTemperature = RAND.nextInt(Integer.MAX_VALUE);
		fluid.gas = StateBasedProperties.random();
		fluid.liquid = StateBasedProperties.random();
		return fluid;
	}
	
	public static class StateBasedProperties {
		
		public int density = 1000;
		public int viscosity = 1000;
		public int luminosity;
		public boolean propagatesSkylightDown;
		public boolean isPathfindable;
		
		@Override
		public String toString() {
			return String.format("{density: %d, viscosity: %d, luminosity: %d, propagatesSkylightDown: %b, isPathfindable: %b}",
					density, viscosity, luminosity, propagatesSkylightDown, isPathfindable);
		}
		
		public static StateBasedProperties random() {
			StateBasedProperties s = new StateBasedProperties();
			s.density = RAND.nextInt();
			s.viscosity = RAND.nextInt();
			s.luminosity = RAND.nextInt(16);
			s.propagatesSkylightDown = RAND.nextBoolean();
			s.isPathfindable = RAND.nextBoolean();
			return s;
		}
		
	}

}
