package glowredman.modularmaterials.util.mc;

import static net.minecraft.block.material.Material.*;

import net.minecraft.block.material.Material;

public class MaterialHelper {
	
	public static Material getMaterialFromString(String material) {
		switch (material) {
		case "AIR":
			return AIR;
		case "ANVIL":
			return ANVIL;
		case "BARRIER":
			return BARRIER;
		case "CACTUS":
			return CACTUS;
		case "CAKE":
			return CAKE;
		case "CARPET":
			return CARPET;
		case "CIRCUITS":
			return CIRCUITS;
		case "CLAY":
			return CLAY;
		case "CLOTH":
			return CLOTH;
		case "CORAL":
			return CORAL;
		case "CRAFTED_SNOW":
			return CRAFTED_SNOW;
		case "DRAGON_EGG":
			return DRAGON_EGG;
		case "FIRE":
			return FIRE;
		case "GLASS":
			return GLASS;
		case "GOURD":
			return GOURD;
		case "GRASS":
			return GRASS;
		case "GROUND":
			return GROUND;
		case "ICE":
			return ICE;
		case "IRON":
			return IRON;
		case "LADDER":
			return WOOD;
		case "LAVA":
			return LAVA;
		case "LEAVES":
			return LEAVES;
		case "PACKED_ICE":
			return PACKED_ICE;
		case "PISTON":
			return PISTON;
		case "PLANTS":
			return PLANTS;
		case "PORTAL":
			return PORTAL;
		case "REDSTONE_LIGHT":
			return REDSTONE_LIGHT;
		case "ROCK":
			return ROCK;
		case "SAND":
			return SAND;
		case "SNOW":
			return SNOW;
		case "SPONGE":
			return SPONGE;
		case "STRUCTURE_VOID":
			return STRUCTURE_VOID;
		case "TNT":
			return TNT;
		case "VINE":
			return VINE;
		case "WATER":
			return WATER;
		case "WEB":
			return WEB;
		case "WOOD":
			return WOOD;
		default:
			return ROCK;
		}
	}

}
