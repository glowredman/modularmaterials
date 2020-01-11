package glowredman.modularmaterials.json;

import org.apache.commons.lang3.math.NumberUtils;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.information.PropertiesMaterials;
import net.minecraft.util.text.TextFormatting;

public class JTextFormat {
	
	double delay;
	int step;
	int posstep;
	TextFormatting[] colors;
	
	public double getDelay() {
		return this.delay;
	}
	
	public int getStep() {
		return this.posstep;
	}
	
	public int getPosstep() {
		return this.step;
	}
	
	public TextFormatting[] getColors() {
		return this.colors;
	}
	
	@SuppressWarnings("null")
	public JTextFormat convertConfigEntryToTextFormat(String[] entry) {
		JTextFormat t = null;
		TextFormatting[] colors = null;
		String firstLine = null;
		String delayString = null;
		String stepString = null;
		String posstepString = null;
		try {
			//getting first line from array and removing "animated:"
			firstLine = entry[0];
			firstLine = firstLine.replace(PropertiesMaterials.AN, "");
			//getting the first number
			delayString = firstLine.substring(0, firstLine.indexOf('|'));
			//removing the first number and the first '|' off the String
			firstLine = firstLine.substring(firstLine.indexOf('|') + 1);
			//getting the second number
			stepString = firstLine.substring(0, firstLine.indexOf('|'));
			//getting the third number
			posstepString = firstLine.substring(firstLine.indexOf('|') + 1);
			//passing the color array
			for (int i = 1; i < entry.length; i++) {
				colors[i - 1] = convertStringToTextFormatting(entry[i]);
			}
		} catch (Exception e) {
			Main.logger.error(e);
		}
		t.colors = colors;
		t.delay = NumberUtils.toDouble(delayString);
		t.step = NumberUtils.toInt(stepString);
		t.posstep = NumberUtils.toInt(posstepString);
		return t;
	}
	
	public static TextFormatting convertStringToTextFormatting(String s) {
		switch (s) {
		case PropertiesMaterials.AQUA:
			return TextFormatting.AQUA;
		case PropertiesMaterials.BLACK:
			return TextFormatting.BLACK;
		case PropertiesMaterials.BLUE:
			return TextFormatting.BLUE;
		case PropertiesMaterials.DARK_AQUA:
			return TextFormatting.DARK_AQUA;
		case PropertiesMaterials.DARK_BLUE:
			return TextFormatting.DARK_BLUE;
		case PropertiesMaterials.DARK_GRAY:
			return TextFormatting.DARK_GRAY;
		case PropertiesMaterials.DARK_GREEN:
			return TextFormatting.DARK_GREEN;
		case PropertiesMaterials.DARK_PURPLE:
			return TextFormatting.DARK_PURPLE;
		case PropertiesMaterials.DARK_RED:
			return TextFormatting.DARK_RED;
		case PropertiesMaterials.GOLD:
			return TextFormatting.GOLD;
		case PropertiesMaterials.GRAY:
			return TextFormatting.GRAY;
		case PropertiesMaterials.GREEN:
			return TextFormatting.GREEN;
		case PropertiesMaterials.LIGHT_PURPLE:
			return TextFormatting.LIGHT_PURPLE;
		case PropertiesMaterials.RED:
			return TextFormatting.RED;
		case PropertiesMaterials.WHITE:
			return TextFormatting.WHITE;
		case PropertiesMaterials.YELLOW:
			return TextFormatting.YELLOW;
		default:
			return TextFormatting.WHITE;
		}
	}

}
