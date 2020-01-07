package glowredman.modularmaterials.json;

import org.apache.commons.lang3.math.NumberUtils;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.information.Properties;
import net.minecraft.util.text.TextFormatting;

public class TextFormat {
	
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
	public TextFormat convertConfigEntryToTextFormat(String[] entry) {
		TextFormat t = null;
		TextFormatting[] colors = null;
		String firstLine = null;
		String delayString = null;
		String stepString = null;
		String posstepString = null;
		try {
			//getting first line from array and removing "animated:"
			firstLine = entry[0];
			firstLine = firstLine.replace(Properties.AN, "");
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
		case Properties.TXT_AQUA:
			return TextFormatting.AQUA;
		case Properties.TXT_BLACK:
			return TextFormatting.BLACK;
		case Properties.TXT_BLUE:
			return TextFormatting.BLUE;
		case Properties.TXT_DARK_AQUA:
			return TextFormatting.DARK_AQUA;
		case Properties.TXT_DARK_BLUE:
			return TextFormatting.DARK_BLUE;
		case Properties.TXT_DARK_GRAY:
			return TextFormatting.DARK_GRAY;
		case Properties.TXT_DARK_GREEN:
			return TextFormatting.DARK_GREEN;
		case Properties.TXT_DARK_PURPLE:
			return TextFormatting.DARK_PURPLE;
		case Properties.TXT_DARK_RED:
			return TextFormatting.DARK_RED;
		case Properties.TXT_GOLD:
			return TextFormatting.GOLD;
		case Properties.TXT_GRAY:
			return TextFormatting.GRAY;
		case Properties.TXT_GREEN:
			return TextFormatting.GREEN;
		case Properties.TXT_LIGHT_PURPLE:
			return TextFormatting.LIGHT_PURPLE;
		case Properties.TXT_RED:
			return TextFormatting.RED;
		case Properties.TXT_WHITE:
			return TextFormatting.WHITE;
		case Properties.TXT_YELLOW:
			return TextFormatting.YELLOW;
		default:
			return TextFormatting.WHITE;
		}
	}

}
