package glowredman.modularmaterials.object;

import static glowredman.modularmaterials.Reference.*;

public class Color {
	
	public int alpha = cAlpha;
	public int red = cRed;
	public int green = cGreen;
	public int blue = cBlue;
	
	public void setColor(int alpha, int red, int green, int blue) {
		this.alpha = alpha;
		this.blue = blue;
		this.green = green;
		this.red = red;
	}
	
	public void setColor(int red, int green, int blue) {
		this.alpha = 0;
		this.blue = blue;
		this.green = green;
		this.red = red;
	}
	
	public void setColor(java.awt.Color color) {
		this.alpha = color.getAlpha();
		this.blue = color.getBlue();
		this.green = color.getGreen();
		this.red = color.getRed();
	}
	
	public int getARGB() {
		return new java.awt.Color(red, green, blue, alpha).getRGB();
	}
	
	public int getRGB() {
		return red * 0x10000 + green * 0x100 + blue;
	}

}
