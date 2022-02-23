package glowredman.modularmaterials.data.object.sub;

import java.util.ArrayList;
import java.util.List;

public class FluidProperties {
	
	public int boilingTemperature = 374;
	public int meltingTemperature = 274;
	public int currentTemperature = 288;
	public StateBasedProperties gas = new StateBasedProperties();
	public StateBasedProperties liquid = new StateBasedProperties();
	public List<String> tags = new ArrayList<>();
	
	@Override
	public String toString() {
		return String.format("{boilingTemperature: %d, meltingTemperature: %d, currentTemperature: %d, gas: %s, liquid: %s, tags: %s}",
				boilingTemperature, meltingTemperature, currentTemperature, gas, liquid, tags);
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
		
	}

}
