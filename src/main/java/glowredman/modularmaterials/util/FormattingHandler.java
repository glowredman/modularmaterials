package glowredman.modularmaterials.util;

import static glowredman.modularmaterials.Reference.*;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;

public class FormattingHandler {
	
	public static String formatTooltipLine(String line) throws IndexOutOfBoundsException, NumberFormatException, NullPointerException {
		
		//key dependent formatting
		line = formatKey(line, triggerAltIsNotPressedFormatting, Keyboard.KEY_LMENU, Keyboard.KEY_RMENU, true);
		line = formatKey(line, triggerAltIsPressedFormatting, Keyboard.KEY_LMENU, Keyboard.KEY_RMENU, false);
		line = formatKey(line, triggerCtrlIsNotPressedFormatting, Keyboard.KEY_LCONTROL, Keyboard.KEY_RCONTROL, true);
		line = formatKey(line, triggerCtrlIsPressedFormatting, Keyboard.KEY_LCONTROL, Keyboard.KEY_RCONTROL, false);
		line = formatKey(line, triggerShiftIsNotPressedFormatting, Keyboard.KEY_LSHIFT, Keyboard.KEY_RSHIFT, true);
		line = formatKey(line, triggerShiftIsPressedFormatting, Keyboard.KEY_LSHIFT, Keyboard.KEY_RSHIFT, false);
		
		//animated formatting
		while(line.contains(triggerAnimatedFormattingChar)) {
			//init vars
			int startOfSequence = line.indexOf(triggerAnimatedFormattingChar);
			int endOfSequence = line.indexOf(triggerAnimatedFormattingChar, startOfSequence + triggerAnimatedFormattingChar.length());
			//assemble string
			String finishedLine = line.substring(0, startOfSequence);
			finishedLine = finishedLine + formatAnimated(line.substring(startOfSequence + triggerAnimatedFormattingChar.length(), endOfSequence));
			line = finishedLine + line.substring(endOfSequence + triggerAnimatedFormattingChar.length(), line.length());
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
			delay = 0.001;
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
