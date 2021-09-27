package glowredman.modularmaterials.data.object.sub;

import static glowredman.modularmaterials.MM_Reference.RAND;
import static net.minecraft.ChatFormatting.*;

import glowredman.modularmaterials.client.FormattingHandler;

public class TooltipProperties {
	
    public String background  = "F0100010";
    public String borderStart = "505000FF";
    public String borderEnd   = "5028007F";
    public String[] text = new String[0];
    
    @Override
    public String toString() {
    	return String.format("[background: %s, borderStart: %s, borderEnd: %s, text: %s]", background, borderStart, borderEnd, FormattingHandler.arrayToString(text));
    }
    
    public static TooltipProperties random() {
    	TooltipProperties t = new TooltipProperties();
    	t.background = Integer.toHexString(RAND.nextInt()).toUpperCase();
    	t.borderStart = Integer.toHexString(RAND.nextInt()).toUpperCase();
    	t.borderEnd = Integer.toHexString(RAND.nextInt()).toUpperCase();
    	t.text = new String[] {"\u00a7s1:80.0:" + RED + "," + GOLD + "," + YELLOW + "," + GREEN + "," + AQUA + "," + BLUE + "," + LIGHT_PURPLE + ":Animated Example\u00a7s"};
    	return t;
    }
    
    public int getBackground() {
    	try {
			return Integer.parseUnsignedInt(background, 16);
		} catch (Exception e) {
			return 0xF0100010;
		}
    }
    
    public int getBorderStart() {
    	try {
			return Integer.parseUnsignedInt(background, 16);
		} catch (Exception e) {
			return 0x505000FF;
		}
    }
    
    public int getBorderEnd() {
    	try {
			return Integer.parseUnsignedInt(background, 16);
		} catch (Exception e) {
			return 0x5028007F;
		}
    }

}
