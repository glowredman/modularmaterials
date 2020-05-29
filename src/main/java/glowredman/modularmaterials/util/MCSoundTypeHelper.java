package glowredman.modularmaterials.util;

import net.minecraft.block.SoundType;
import static net.minecraft.block.SoundType.*;
import net.minecraft.block.material.Material;

public class MCSoundTypeHelper {
	
	public static SoundType getMaterialFromString(String material) {
		switch (material) {
		case "ANVIL":
			return ANVIL;
		case "CACTUS":
			return CLOTH;
		case "CAKE":
			return CLOTH;
		case "CARPET":
			return CLOTH;
		case "CIRCUITS":
			return WOOD;
		case "CLAY":
			return GROUND;
		case "CLOTH":
			return CLOTH;
		case "CRAFTED_SNOW":
			return SNOW;
		case "GLASS":
			return GLASS;
		case "GOURD":
			return WOOD;
		case "GRASS":
			return PLANT;
		case "GROUND":
			return GROUND;
		case "ICE":
			return GLASS;
		case "IRON":
			return METAL;
		case "LADDER":
			return LADDER;
		case "LEAVES":
			return PLANT;
		case "PACKED_ICE":
			return GLASS;
		case "PLANTS":
			return PLANT;
		case "PORTAL":
			return GLASS;
		case "REDSTONE_LIGHT":
			return GLASS;
		case "ROCK":
			return STONE;
		case "SAND":
			return SAND;
		case "SNOW":
			return SNOW;
		case "SPONGE":
			return PLANT;
		case "TNT":
			return PLANT;
		case "VINE":
			return PLANT;
		case "WOOD":
			return WOOD;
		default:
			return STONE;
		}
	}

}
