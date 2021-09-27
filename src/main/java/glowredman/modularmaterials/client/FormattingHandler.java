package glowredman.modularmaterials.client;

import static glowredman.modularmaterials.MM_Reference.*;

import java.util.List;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

public class FormattingHandler {
	
	public static Component formatTooltipLine(String line) {
		
		//key-dependant formatting
		line = formatKey(line, triggerAltIsNotPressedFormatting, InputConstants.KEY_LALT, InputConstants.KEY_RALT, true);
		line = formatKey(line, triggerAltIsPressedFormatting, InputConstants.KEY_LALT, InputConstants.KEY_RALT, false);
		line = formatKey(line, triggerCtrlIsNotPressedFormatting, InputConstants.KEY_LCONTROL, InputConstants.KEY_RCONTROL, true);
		line = formatKey(line, triggerCtrlIsPressedFormatting, InputConstants.KEY_LCONTROL, InputConstants.KEY_RCONTROL, false);
		line = formatKey(line, triggerShiftIsNotPressedFormatting, InputConstants.KEY_LSHIFT, InputConstants.KEY_RSHIFT, true);
		line = formatKey(line, triggerShiftIsPressedFormatting, InputConstants.KEY_LSHIFT, InputConstants.KEY_RSHIFT, false);
		
		//animated formatting
		while(line.contains(triggerAnimatedFormatting)) {
			//init vars
			int formattingCharLenght = triggerAnimatedFormatting.length();
			int startOfSequence = line.indexOf(triggerAnimatedFormatting);
			int endOfSequence = line.indexOf(triggerAnimatedFormatting, startOfSequence + formattingCharLenght);
			//assemble string
			String finishedLine = line.substring(0, startOfSequence);
			finishedLine += formatAnimated(line.substring(startOfSequence + formattingCharLenght, endOfSequence));
			line = finishedLine + line.substring(endOfSequence + formattingCharLenght);
		}
		
		return new TextComponent(line);
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
		String[] segments = string.split(":", 4);
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
	
	public static String listToString(List<String> list) {
		
		// no entries = []
		if(list == null || list.size() == 0) {
			return "[]";
		}
		
		// one entry = ["abc"]
		if(list.size() == 1) {
			return "[\"" + list.get(0) + "\"]";
		}
		
		// multiple entries = ["abc", "def", ...]
		String ret = "[\"" + list.get(0);
		for(int i = 1; i < list.size(); i++) {
			ret += "\", \"" + list.get(i);
		}
		return ret += "\"]";
	}
	
	public static String arrayToString(String[] array) {
		
		// no entries = []
		if(array == null || array.length == 0) {
			return "[]";
		}
		
		// one entry = ["abc"]
		if(array.length == 1) {
			return "[\"" + array[0] + "\"]";
		}
		
		// multiple entries = ["abc", "def", ...]
		String ret = "[\"" + array[0];
		for(int i = 1; i < array.length; i++) {
			ret += "\", \"" + array[i];
		}
		return ret += "\"]";
	}

}
