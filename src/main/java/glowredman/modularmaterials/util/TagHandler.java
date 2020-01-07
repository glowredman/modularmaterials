package glowredman.modularmaterials.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.Level;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.information.Properties;
import glowredman.modularmaterials.json.Material;
import glowredman.modularmaterials.information.Reference;

public class TagHandler {
	
	//liquids
	public static boolean isValidLiquid(Material material) {
		if(Reference.enableAll) {
			return true;
		} else {
			if(material.isDisabled()) {
				return false;
			} else {
				String[] tags = material.getTags();
				if (isTagInArray(tags, "no_liquid") || isTagInArray(tags, "ore") || isTagInArray(tags, "wood") || isTagInArray(tags, "no_fluid")) {
					return false;
				} else {
					return true;
				}
			}
		}	
	}
	
	//gas
	public static boolean isValidGas(Material material) {
		if(Reference.enableAll) {
			return true;
		} else {
			if(material.isDisabled()) {
				return false;
			} else {
				String[] tags = material.getTags();
				if (isTagInArray(tags, "no_gas") || isTagInArray(tags, "ore") || isTagInArray(tags, "wood") || isTagInArray(tags, "no_fluid")) {
					return false;
				} else {
					return true;
				}
			}
		}	
	}
	
	//liquids
	public static int getValidTemperatureForLiquid(Material material) {
		switch (material.getState()) {
		case Properties.S1:
			return material.getMeltigTemperature();
		case Properties.S2:
			return material.getTemperature();
		case Properties.S3:
			return material.getMeltigTemperature();
		default:
			Main.logger.warn("'" + material.getState() + "' is not a valid state (material: " + material.getName().getText() + ").");
			return Reference.normalTemp;
		}
	}
	
	//gases
	public static int getValidTemperatureForGas(Material material) {
		switch (material.getState()) {
		case Properties.S1:
			return material.getBoilingTemperature();
		case Properties.S2:
			return material.getBoilingTemperature();
		case Properties.S3:
			return material.getTemperature();
		default:
			Main.logger.warn("'" + material.getState() + "' is not a valid state (material: " + material.getName().getText() + ").");
			return Reference.normalTemp;
		}
	}

	//liquids
	public static int getValidLuminosityForLiquid(Material material) {
		if(material.getState() == Properties.S1) {
			return Reference.defaultMoltenMaterialLightLevel;
		} else {
			return material.getLightLevel();
		}
	}
	
	//liquids
	public static String convertToLiquidName(Material material) {
		String name = material.getName().getText();
		String nameOld = name;
		String state = material.getState();
		name = name.toLowerCase().replace(' ', '_');
		
		switch (state) {
		case Properties.S1:
			Main.logger.debug("correct liquid-name for \"" + nameOld + "\" of state \"" + state + "\" is \"" + name + "\".");
			return name;
		case Properties.S2:
			Main.logger.debug("correct liquid-name for \"" + nameOld + "\" of state \"" + state + "\" is \"" + name + "\".");
			return name;
		case Properties.S3:
			Main.logger.debug("correct liquid-name for \"" + nameOld + "\" of state \"" + state + "\" is \"" + name + "\".");
			return "liquid_" + name;

		default:
			Main.logger.warn('"' + nameOld + "\" is not properly defined. Returning \"" + name + "\" as liquid-name.");
			return name;
		}
	}
	
	//gases
	public static String convertToGasName(Material material) {
		String name = material.getName().getText();
		String nameOld = name;
		String state = material.getState();
		name = name.toLowerCase().replace(' ', '_');
		
		switch (state) {
		case Properties.S1:
			Main.logger.debug("correct gas-name for \"" + nameOld + "\" of state \"" + state + "\" is \"" + name + "\".");
			return "gaseous_" + name;
		case Properties.S2:
			Main.logger.debug("correct gas-name for \"" + nameOld + "\" of state \"" + state + "\" is \"" + name + "\".");
			return "gaseous_" + name;
		case Properties.S3:
			Main.logger.debug("correct gas-name for \"" + nameOld + "\" of state \"" + state + "\" is \"" + name + "\".");
			return name;

		default:
			Main.logger.warn('"' + nameOld + "\" is not properly defined. Returning \"" + name + "\" as gas-name.");
			return name;
		}
	}

	//ingots
	public static boolean isValidIngot(Material material) {
		if(Reference.enableAll) {
			return true;
		} else {
			if(Reference.enableIngots) {
				if(material.isDisabled()) {
					return false;
				} else {
					String[] tags = material.getTags();
					if(isTagInArray(tags, "no_ingot") || isTagInArray(tags, "gas") || isTagInArray(tags, "dust") || isTagInArray(tags, "ore") || isTagInArray(tags, "stone") || isTagInArray(tags, "gem") || isTagInArray(tags, "liquid") || isTagInArray(tags, "wood")) {
						Main.logger.debug("'" + material.getName().getText() + "' is not a valid ingot.");
						return false;
					} else {
						return true;
					}
				}
			} else {
				return false;
			}
		}
	}
	
	//blocks
	public static boolean isValidBlock(Material material) {
		if (Reference.enableAll) {
			return true;
		} else {
			if (Reference.enableBlocks && !material.isDisabled()) {
				String[] tags = material.getTags();
				if (isTagInArray(tags, "no_block") || isTagInArray(tags, "gas") || isTagInArray(tags, "ore") || isTagInArray(tags, "stone") || isTagInArray(tags, "liquid") || isTagInArray(tags, "wood")) {
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		}
	}

	public static boolean isValidPlate(Material material) {
		if (Reference.enableAll) {
			return true;
		} else {
			if (Reference.enablePlates && !material.isDisabled()) {
				String[] tags = material.getTags();
				if (isTagInArray(tags, "no_plate") || isTagInArray(tags, "gas") || isTagInArray(tags, "dust") || isTagInArray(tags, "ore") || isTagInArray(tags, "liquid")) {
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		}
	}
	
	//tags
	public static boolean isTagInArray(String[] array, String tag) {
		for (String entry : array) {
			if (entry.contentEquals(tag)) {
				return true;
			}
		}
		return false;
	}
	
	//meta
	public static void checkMetaValues(){
		Main.logger.info("Checking meta-values for duplicates...");
		
		//set up variables
		boolean[] takenMetaValues = new boolean[32768];
		Arrays.fill(takenMetaValues, 0, 32767, false);
		
		//iterate through all materials
		for (Material material : Reference.materialList.getMaterials()) {
			int meta = material.getMeta();
			
			//if the meta-value is already taken, add it to the entry
			if (takenMetaValues[meta]) {
				Main.logger.error("Found a duplicate meta-value (" + meta + "). Change the meta-value of \"" + material.getName().getText() + "\" to resolve this error.");
				
			//if the meta-value is not taken, mark it as taken
			} else {
				takenMetaValues[meta] = true;
			}
		}
		Main.logger.info("Finished checking meta-values.");
	}
	
	//meta
	public static String getNameFromMeta(int meta) {
		for (Material material : Reference.materialList.getMaterials()) {
			if (meta == material.getMeta()) {
				return material.getName().getText();
			}
		}
		return "" + meta;
	}

}
