package glowredman.modularmaterials.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;
import static net.minecraft.util.text.TextFormatting.*;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.json.JText;

public class TextHandler {
	
	public static String animatedFormatting(String input, TextFormatting[] colors, double delay, int step, int posstep) {
		
		StringBuilder sb = new StringBuilder(input.length() * 3);
		
		if (delay <= 0) {
			delay = 0.001;
		}
		
		int offset = (int) (Math.floor(Minecraft.getSystemTime() / delay) % colors.length);
		
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			int col = ((i * posstep) + colors.length - offset) % colors.length;
			
			sb.append(colors[col].toString());
			sb.append(c);
		}
		
		return sb.toString();
		
	}

}
