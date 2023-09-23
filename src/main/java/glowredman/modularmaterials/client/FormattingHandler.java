package glowredman.modularmaterials.client;

import static glowredman.modularmaterials.MM_Reference.*;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import glowredman.modularmaterials.MM_Reference;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public class FormattingHandler {
	
	public static Component formatTooltipLine(String line) {
		
		//key-dependant formatting
		line = formatKey(line, ALT_NOT_PRESSED_FORMATTING, InputConstants.KEY_LALT, InputConstants.KEY_RALT, true);
		line = formatKey(line, ALT_PRESSED_FORMATTING, InputConstants.KEY_LALT, InputConstants.KEY_RALT, false);
		line = formatKey(line, CTRL_NOT_PRESSED_FORMATTING, InputConstants.KEY_LCONTROL, InputConstants.KEY_RCONTROL, true);
		line = formatKey(line, CTRL_PRESSED_FORMATTING, InputConstants.KEY_LCONTROL, InputConstants.KEY_RCONTROL, false);
		line = formatKey(line, SHIFT_NOT_PRESSED_FORMATTING, InputConstants.KEY_LSHIFT, InputConstants.KEY_RSHIFT, true);
		line = formatKey(line, SHIFT_PRESSED_FORMATTING, InputConstants.KEY_LSHIFT, InputConstants.KEY_RSHIFT, false);
		
		//animated formatting
		while(line.contains(ANIMATED_FORMATTING)) {
			//init vars
			int formattingCharLenght = ANIMATED_FORMATTING.length();
			int startOfSequence = line.indexOf(ANIMATED_FORMATTING);
			int endOfSequence = line.indexOf(ANIMATED_FORMATTING, startOfSequence + formattingCharLenght);
			//assemble string
			String finishedLine = line.substring(0, startOfSequence);
			finishedLine += formatAnimated(line.substring(startOfSequence + formattingCharLenght, endOfSequence));
			line = finishedLine + line.substring(endOfSequence + formattingCharLenght);
		}
		
		return Component.literal(line).withStyle(ChatFormatting.GRAY);
	}

	private static String formatKey(String line, String trigger, int key1, int key2, boolean invert) {
		if(line == null) return null;
		if(line.contains(trigger)) {
			if((isKeyDown(key1) || isKeyDown(key2) ^ invert)) {
				return line.replace(trigger, "");
			}
			return null;
		}
		return line;
	}

	private static String formatAnimated(String string) {
		//extract
		String[] segments = MM_Reference.COLON_SPLITTER.split(string, 4);
		int posstep = Integer.parseInt(segments[0]);
		double delay = Double.parseDouble(segments[1]); //delay between each shift in ms
		String[] formattingArray = segments[2].split(",");
		String text = segments[3];
		
		//Taken and adapted from https://github.com/Morpheus1101/Avaritia/blob/master/src/main/java/morph/avaritia/util/TextUtils.java
		// (Licensed under MIT)
		StringBuilder sb = new StringBuilder(text.length() * 3);
		
		//calculate
		if(delay <= 0) delay = 0.001;
		
		int offset = (int) ((System.currentTimeMillis() / delay) % formattingArray.length);
		
		for(int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			int indexColorArray = (i * posstep + formattingArray.length - offset) % formattingArray.length;
			
			sb.append(formattingArray[indexColorArray]);
			sb.append(c);
		}
		
		return sb.toString();
	}
	
	private static boolean isKeyDown(int key) {
		return InputConstants.isKeyDown(GLFW.glfwGetCurrentContext(), key);
	}

}
