package glowredman.modularmaterials.json;

import glowredman.modularmaterials.information.PropertiesMaterials;
import glowredman.modularmaterials.util.RarityHandler;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.text.TextFormatting;

public class JText {
	
	public String text;
	public String[] format = new String[]{}; //common, uncommon, rare or epic for vanilla rarity; custom:COLOR for a custom colored text; animated:DELAY|STEP|POSSTEP as double|int|int followed by an String[] for the animation
	
	public JText(String text, String[] format) {
		this.text = text;
		this.format = format;
	}
	
	public JText(String text, String rarity) {
		this.text = text;
		this.format = new String[]{rarity};
	}
	
	public JText(String text) {
		this.text = text;
		this.format = new String[]{PropertiesMaterials.R1};
	}
	
	public JText() {
	}

	public String getText() {
		return this.text;
	}
	
	public String[] getFormat() {
		return this.format;
	}
	
	public EnumRarity getRarity() {
		switch (this.format[0]) {
		//vanilla
		case PropertiesMaterials.R1:
			return EnumRarity.COMMON;
		case PropertiesMaterials.R2:
			return EnumRarity.UNCOMMON;
		case PropertiesMaterials.R3:
			return EnumRarity.RARE;
		case PropertiesMaterials.R4:
			return EnumRarity.EPIC;
		//custom formatting
		case PropertiesMaterials.AQUA:
			return RarityHandler.AQUA;
		case PropertiesMaterials.BLACK:
			return RarityHandler.BLACK;
		case PropertiesMaterials.BLUE:
			return RarityHandler.BLUE;
		case PropertiesMaterials.BOLD:
			return RarityHandler.BOLD;
		case PropertiesMaterials.DARK_AQUA:
			return RarityHandler.DARK_AQUA;
		case PropertiesMaterials.DARK_BLUE:
			return RarityHandler.DARK_BLUE;
		case PropertiesMaterials.DARK_GRAY:
			return RarityHandler.DARK_GRAY;
		case PropertiesMaterials.DARK_GREEN:
			return RarityHandler.DARK_GREEN;
		case PropertiesMaterials.DARK_PURPLE:
			return RarityHandler.DARK_PURPLE;
		case PropertiesMaterials.DARK_RED:
			return RarityHandler.DARK_RED;
		case PropertiesMaterials.GOLD:
			return RarityHandler.GOLD;
		case PropertiesMaterials.GRAY:
			return RarityHandler.GRAY;
		case PropertiesMaterials.GREEN:
			return RarityHandler.GREEN;
		case PropertiesMaterials.ITALIC:
			return RarityHandler.ITALIC;
		case PropertiesMaterials.LIGHT_PURPLE:
			return RarityHandler.LIGHT_PURPLE;
		case PropertiesMaterials.OBFUSCATED:
			return RarityHandler.OBFUSCATED;
		case PropertiesMaterials.RED:
			return RarityHandler.RED;
		case PropertiesMaterials.RESET:
			return RarityHandler.RESET;
		case PropertiesMaterials.STRIKETHROUGH:
			return RarityHandler.STRIKETHROUGH;
		case PropertiesMaterials.UNDERLINE:
			return RarityHandler.UNDERLINE;
		case PropertiesMaterials.WHITE:
			return RarityHandler.WHITE;
		case PropertiesMaterials.YELLOW:
			return RarityHandler.YELLOW;
		default:
			return EnumRarity.COMMON;
		}
	}

}
