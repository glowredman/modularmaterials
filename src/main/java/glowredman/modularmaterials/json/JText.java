package glowredman.modularmaterials.json;

import glowredman.modularmaterials.information.Properties;
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
		this.format = new String[]{Properties.R1};
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
		case Properties.R1:
			return EnumRarity.COMMON;
		case Properties.R2:
			return EnumRarity.UNCOMMON;
		case Properties.R3:
			return EnumRarity.RARE;
		case Properties.R4:
			return EnumRarity.EPIC;
		//custom
		case Properties.TXT_AQUA:
			return RarityHandler.AQUA;
		case Properties.TXT_BLACK:
			return RarityHandler.BLACK;
		case Properties.TXT_BLUE:
			return RarityHandler.BLUE;
		case Properties.TXT_DARK_AQUA:
			return RarityHandler.DARK_AQUA;
		case Properties.TXT_DARK_BLUE:
			return RarityHandler.DARK_BLUE;
		case Properties.TXT_DARK_GRAY:
			return RarityHandler.DARK_GRAY;
		case Properties.TXT_DARK_GREEN:
			return RarityHandler.DARK_GREEN;
		case Properties.TXT_DARK_PURPLE:
			return RarityHandler.DARK_PURPLE;
		case Properties.TXT_DARK_RED:
			return RarityHandler.DARK_RED;
		case Properties.TXT_GOLD:
			return RarityHandler.GOLD;
		case Properties.TXT_GRAY:
			return RarityHandler.GRAY;
		case Properties.TXT_GREEN:
			return RarityHandler.GREEN;
		case Properties.TXT_LIGHT_PURPLE:
			return RarityHandler.LIGHT_PURPLE;
		case Properties.TXT_RED:
			return RarityHandler.RED;
		case Properties.TXT_WHITE:
			return RarityHandler.WHITE;
		case Properties.TXT_YELLOW:
			return RarityHandler.YELLOW;
		default:
			return EnumRarity.COMMON;
		}
	}

}
