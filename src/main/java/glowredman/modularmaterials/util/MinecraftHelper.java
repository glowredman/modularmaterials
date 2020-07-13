package glowredman.modularmaterials.util;

import static glowredman.modularmaterials.Main.logger;
import static net.minecraft.block.material.MapColor.*;
import static net.minecraft.block.material.Material.*;

import java.awt.Color;

import glowredman.modularmaterials.object.JColor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.MathHelper;

public class MinecraftHelper {
	
	public static Material getMaterial(String material) {
		switch (material) {
		case "AIR":
			return Material.AIR;
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
			return Material.CLAY;
		case "CLOTH":
			return Material.CLOTH;
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
			return Material.GRASS;
		case "GROUND":
			return GROUND;
		case "ICE":
			return Material.ICE;
		case "IRON":
			return Material.IRON;
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
			return Material.SAND;
		case "SNOW":
			return Material.SNOW;
		case "SPONGE":
			return SPONGE;
		case "STRUCTURE_VOID":
			return STRUCTURE_VOID;
		case "TNT":
			return Material.TNT;
		case "VINE":
			return VINE;
		case "WATER":
			return Material.WATER;
		case "WEB":
			return WEB;
		case "WOOD":
			return Material.WOOD;
		default:
			logger.error('"' + material + "\" is not a known material!");
			return ROCK;
		}
	}
	
	public static SoundType getSoundType(String sound) {
		switch (sound) {
		case "ANVIL":
			return SoundType.ANVIL;
		case "CLOTH":
			return SoundType.CLOTH;
		case "GLASS":
			return SoundType.GLASS;
		case "GROUND":
			return SoundType.GROUND;
		case "LADDER":
			return SoundType.LADDER;
		case "METAL":
			return SoundType.METAL;
		case "PLANT":
			return SoundType.PLANT;
		case "SAND":
			return SoundType.SAND;
		case "SLIME":
			return SoundType.SLIME;
		case "SNOW":
			return SoundType.SNOW;
		case "STONE":
			return SoundType.STONE;
		case "WOOD":
			return SoundType.WOOD;
		default:
			logger.error('"' + sound + "\" is not a known soundType!");
			return SoundType.STONE;
		}
	}
	
	public static MapColor getMapColor(String mapColor, JColor color) {
		switch (mapColor) {
		case "AUTO":
			if(color == new JColor().setColor(96, 96, 96)){
				return MapColor.STONE;
			} else {
				int index = 1;
				int distance = distanceSq(color, COLORS[1].colorValue);
				for(int i = 2; i < COLORS.length; i++) {
					MapColor current = COLORS[i];
					if(current == null) continue; else {
						int currentDistance = distanceSq(color, current.colorValue);
						if(currentDistance < distance) {
							index = i;
							distance = currentDistance;
						}
					}
				}
				MapColor ret = COLORS[index];
				logger.debug("Found " + ret.toString() + " as the nearest color for [" + color.red + ", " + color.green + ", " + color.blue + "]. Distance: " + MathHelper.sqrt(distance));
				return ret;
			}
		case "ORANGE":
			return ADOBE;
		case "AIR":
			return MapColor.AIR;
		case "BLACK":
			return BLACK;
		case "BLACK_CLAY":
			return BLACK_STAINED_HARDENED_CLAY;
		case "BLUE":
			return BLUE;
		case "BLUE_CLAY":
			return BLUE_STAINED_HARDENED_CLAY;
		case "BROWN":
			return BROWN;
		case "BROWN_CLAY":
			return BROWN_STAINED_HARDENED_CLAY;
		case "CLAY":
			return MapColor.CLAY;
		case "CLOTH":
			return MapColor.CLOTH;
		case "CYAN":
			return CYAN;
		case "CYAN_CLAY":
			return CYAN_STAINED_HARDENED_CLAY;
		case "DIAMOND":
			return DIAMOND;
		case "DIRT":
			return DIRT;
		case "EMERALD":
			return EMERALD;
		case "FOLIAGE":
			return FOLIAGE;
		case "GOLD":
			return GOLD;
		case "GRASS":
			return MapColor.GRASS;
		case "GRAY":
			return GRAY;
		case "GRAY_CLAY":
			return GRAY_STAINED_HARDENED_CLAY;
		case "GREEN":
			return GREEN;
		case "GREEN_CLAY":
			return GREEN_STAINED_HARDENED_CLAY;
		case "ICE":
			return MapColor.ICE;
		case "IRON":
			return MapColor.IRON;
		case "LAPIS":
			return LAPIS;
		case "LIGHT_BLUE":
			return LIGHT_BLUE;
		case "LIGHT_BLUE_CLAY":
			return LIGHT_BLUE_STAINED_HARDENED_CLAY;
		case "LIME":
			return LIME;
		case "LIME_CLAY":
			return LIME_STAINED_HARDENED_CLAY;
		case "MAGENTA":
			return MAGENTA;
		case "MAGENTA_CLAY":
			return MAGENTA_STAINED_HARDENED_CLAY;
		case "NETHERRACK":
			return NETHERRACK;
		case "OBSIDIAN":
			return OBSIDIAN;
		case "ORANGE_CLAY":
			return ORANGE_STAINED_HARDENED_CLAY;
		case "PINK":
			return PINK;
		case "PINK_CLAY":
			return PINK_STAINED_HARDENED_CLAY;
		case "PURPLE":
			return PURPLE;
		case "PURPLE_CLAY":
			return PURPLE_STAINED_HARDENED_CLAY;
		case "QUARTZ":
			return QUARTZ;
		case "RED":
			return RED;
		case "RED_CLAY":
			return RED_STAINED_HARDENED_CLAY;
		case "SAND":
			return MapColor.SAND;
		case "SILVER":
			return SILVER;
		case "SILVER_CLAY":
			return SILVER_STAINED_HARDENED_CLAY;
		case "SNOW":
			return MapColor.SNOW;
		case "STONE":
			return STONE;
		case "TNT":
			return MapColor.TNT;
		case "WATER":
			return MapColor.WATER;
		case "WHITE_CLAY":
			return WHITE_STAINED_HARDENED_CLAY;
		case "WOOD":
			return MapColor.WOOD;
		case "YELLOW":
			return YELLOW;
		case "YELLOW_CLAY":
			return YELLOW_STAINED_HARDENED_CLAY;
		default:
			return getMapColor("AUTO", color);
		}
	}
	
	private static int distanceSq(JColor color1, int color2) {
		int dr = color1.red - (color2 >> 16 & 255);
		int dg = color1.green - (color2 >> 8 & 255);
		int db = color1.blue - (color2 & 255);
		
		return dr * dr + dg * dg + db * db;
	}

}
