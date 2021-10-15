package glowredman.modularmaterials.data.object.sub;

import glowredman.modularmaterials.client.FormattingHandler;
import net.minecraftforge.fmlclient.gui.GuiUtils;

public class TooltipProperties {
	
    public String background  = Integer.toHexString(GuiUtils.DEFAULT_BACKGROUND_COLOR).toUpperCase();
    public String borderStart = Integer.toHexString(GuiUtils.DEFAULT_BORDER_COLOR_START).toUpperCase();
    public String borderEnd   = Integer.toHexString(GuiUtils.DEFAULT_BORDER_COLOR_END).toUpperCase();
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
