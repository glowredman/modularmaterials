package glowredman.modularmaterials.api;

import com.sun.jna.platform.KeyboardUtils;

import glowredman.modularmaterials.Main;
import net.minecraft.client.Minecraft;

public class Formatting {
	
	public static String formatTooltipLine(String line) throws IndexOutOfBoundsException, NumberFormatException, NullPointerException {
		
		while(line.contains(Reference.triggerAnimatedFormattingChar)) {
			//init vars
			String finishedLine = "";
			int startOfSequence = line.indexOf(Reference.triggerAnimatedFormattingChar);
			int endOfSequence = line.indexOf(Reference.triggerAnimatedFormattingChar, startOfSequence + Reference.triggerAnimatedFormattingChar.length());
			//assemble string
			finishedLine = line.substring(0, startOfSequence);
			finishedLine = finishedLine + format_animated(line.substring(startOfSequence, endOfSequence));
			line = finishedLine + line.substring(endOfSequence + Reference.triggerAnimatedFormattingChar.length(), line.length());
		}
		
		while(line.contains(Reference.triggerKeyNotPressedFormattingChar)) {
			//init vars
			String finishedLine = "";
			int startOfSequence = line.indexOf(Reference.triggerKeyNotPressedFormattingChar);
			int endOfSequence = line.indexOf(Reference.triggerKeyNotPressedFormattingChar, startOfSequence + Reference.triggerKeyNotPressedFormattingChar.length());
			//assemble string
			finishedLine = line.substring(0, startOfSequence);
			finishedLine = finishedLine + format_keyIsNotPressed(line.substring(startOfSequence, endOfSequence));
			line = finishedLine + line.substring(endOfSequence + Reference.triggerKeyNotPressedFormattingChar.length(), line.length());
		}
		
		while(line.contains(Reference.triggerKeyPressedFormattingChar)) {
			//init vars
			String finishedLine = "";
			int startOfSequence = line.indexOf(Reference.triggerKeyPressedFormattingChar);
			int endOfSequence = line.indexOf(Reference.triggerKeyPressedFormattingChar, startOfSequence + Reference.triggerKeyPressedFormattingChar.length());
			//assemble string
			finishedLine = line.substring(0, startOfSequence);
			finishedLine = finishedLine + format_keyIsPressed(line.substring(startOfSequence, endOfSequence));
			line = finishedLine + line.substring(endOfSequence + Reference.triggerKeyPressedFormattingChar.length(), line.length());
		}
		return line;
	}
	
	public static String format_keyIsPressed(String string) throws NumberFormatException { //§pNUM_KEY:STRING§p
		if(KeyboardUtils.isPressed(Integer.decode(string.substring(0, string.indexOf(':'))))) {
			return string.substring(string.indexOf(':') + 1);
		} else {
			return "";
		}
	}
	
	public static String format_keyIsNotPressed(String string) throws NumberFormatException {
		if(!KeyboardUtils.isPressed(Integer.decode(string.substring(0, string.indexOf(':'))))) { //§qNUM_KEY:STRING§q
			return string.substring(string.indexOf(':') + 1);
		} else {
			return "";
		}
	}
	
	public static String format_animated(String string) throws NumberFormatException, NullPointerException { //§sSTEP|POSSTEP|DELAY|COLORS:STRING§s
		//extract
		int step = Integer.parseInt(string.substring(0, string.indexOf('|') - 1));
		string = string.substring(string.indexOf('|') + 1);
		int posstep = Integer.parseInt(string.substring(0, string.indexOf('|') - 1));
		string = string.substring(string.indexOf('|') + 1);
		double delay = Double.parseDouble(string.substring(0, string.indexOf('|') - 1));
		string = string.substring(string.indexOf('|') + 1);
		String[] colorArray = string.substring(0, string.indexOf(':') - 1).split(",");
		string = string.substring(string.indexOf(':') + 1);
		
		StringBuilder sb = new StringBuilder(string.length() * 3);
		
		//calculate
		if(delay <= 0) {
			delay = 0.001;
		}
		
		int offset = (int) (Math.floor(Minecraft.getSystemTime() / delay) % colorArray.length);
		
		for(int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			int indexColorArray = ((i * posstep) + colorArray.length - offset) % colorArray.length;
			
			//assemble
			sb.append(colorArray[indexColorArray]);
			sb.append(c);
		}
		return sb.toString();
	}

}
