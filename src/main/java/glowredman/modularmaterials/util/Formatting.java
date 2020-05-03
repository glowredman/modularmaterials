package glowredman.modularmaterials.util;

import org.lwjgl.input.Keyboard;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.Reference;
import net.minecraft.client.Minecraft;

public class Formatting {
	
	public static String formatTooltipLine(String line) throws IndexOutOfBoundsException, NumberFormatException, NullPointerException {
		
		//key dependent formatting
		line = formatKey(line, Reference.triggerAltIsNotPressedFormatting, Keyboard.KEY_LMENU, Keyboard.KEY_RMENU, true);
		line = formatKey(line, Reference.triggerAltIsPressedFormatting, Keyboard.KEY_LMENU, Keyboard.KEY_RMENU, false);
		line = formatKey(line, Reference.triggerCtrlIsNotPressedFormatting, Keyboard.KEY_LCONTROL, Keyboard.KEY_RCONTROL, true);
		line = formatKey(line, Reference.triggerCtrlIsPressedFormatting, Keyboard.KEY_LCONTROL, Keyboard.KEY_RCONTROL, false);
		line = formatKey(line, Reference.triggerShiftIsNotPressedFormatting, Keyboard.KEY_LSHIFT, Keyboard.KEY_RSHIFT, true);
		line = formatKey(line, Reference.triggerShiftIsPressedFormatting, Keyboard.KEY_LSHIFT, Keyboard.KEY_RSHIFT, false);
		
		//animated formatting
		while(line.contains(Reference.triggerAnimatedFormattingChar)) {
			//init vars
			int startOfSequence = line.indexOf(Reference.triggerAnimatedFormattingChar);
			int endOfSequence = line.indexOf(Reference.triggerAnimatedFormattingChar, startOfSequence + Reference.triggerAnimatedFormattingChar.length());
			//assemble string
			String finishedLine = line.substring(0, startOfSequence);
			finishedLine = finishedLine + formatAnimated(line.substring(startOfSequence + Reference.triggerAnimatedFormattingChar.length(), endOfSequence));
			line = finishedLine + line.substring(endOfSequence + Reference.triggerAnimatedFormattingChar.length(), line.length());
		}

		return line;
	}
	
	public static String formatKey(String line, String trigger, int key1, int key2, boolean invert) throws NullPointerException {
		if(line != null) {
			if(line.contains(trigger)) {
				if((Keyboard.isKeyDown(key1) || Keyboard.isKeyDown(key2)) ^ invert) {
					return line.replace(trigger, "");
				} else {
					return null;
				}
			} else {
				return line;
			}
		} else {
			return null;
		}
	}
	
	public static String formatAnimated(String string) throws NumberFormatException, NullPointerException { //format: §sPOSSTEP:DELAY:COLORS:STRING§s
		//extract
		String[] segments = string.split(":", 4);
		int posstep = Integer.parseInt(segments[0]);
		double delay = Double.parseDouble(segments[1]);
		String[] colorArray = segments[2].split(",");
		String text = segments[3];
		
		StringBuilder sb = new StringBuilder(string.length() * 3);
		
		//calculate
		if(delay <= 0) {
			delay = Reference.animatedFormattingDelayFallback;
		}
		
		int offset = (int) (Math.floor(Minecraft.getSystemTime() / delay) % colorArray.length);
		
		for(int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			int indexColorArray = ((i * posstep) + colorArray.length - offset) % colorArray.length;
			
			//assemble
			sb.append(colorArray[indexColorArray]);
			sb.append(c);
		}
		return sb.toString();
	}

}
