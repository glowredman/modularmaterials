package glowredman.modularmaterials.data.object.sub;

import glowredman.modularmaterials.client.FormattingHandler;

public class TooltipProperties {
	
    public String background  = Integer.toHexString(0xF0100010).toUpperCase();
    public String borderStart = Integer.toHexString(0x505000FF).toUpperCase();
    public String borderEnd   = Integer.toHexString(0x5028007F).toUpperCase();
    public String[] text = new String[0];
    
    @Override
    public String toString() {
    	return String.format("[background: %s, borderStart: %s, borderEnd: %s, text: %s]", background, borderStart, borderEnd, FormattingHandler.arrayToString(text));
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
			return Integer.parseUnsignedInt(borderStart, 16);
		} catch (Exception e) {
			return 0x505000FF;
		}
    }
    
    public int getBorderEnd() {
    	try {
			return Integer.parseUnsignedInt(borderEnd, 16);
		} catch (Exception e) {
			return 0x5028007F;
		}
    }

}
