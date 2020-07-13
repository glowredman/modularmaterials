package glowredman.modularmaterials.object;

import static glowredman.modularmaterials.Reference.*;

import java.awt.Color;

public class JColor {
	
	public int alpha = cAlpha;
	public int red = cRed;
	public int green = cGreen;
	public int blue = cBlue;
	
	public JColor setColor(int alpha, int red, int green, int blue) {
		this.alpha = alpha;
		this.blue = blue;
		this.green = green;
		this.red = red;
		return this;
	}
	
	public JColor setColor(int red, int green, int blue) {
		this.alpha = 0;
		this.blue = blue;
		this.green = green;
		this.red = red;
		return this;
	}
	
	public JColor setColor(Color color) {
		this.alpha = color.getAlpha();
		this.blue = color.getBlue();
		this.green = color.getGreen();
		this.red = color.getRed();
		return this;
	}
	
	public int getARGB() {
		return alpha * 0x1000000 + red * 0x10000 + green * 0x100 + blue;
	}
	
	public int getRGB() {
		return 0xFF000000 + red * 0x10000 + green * 0x100 + blue;
	}
	
	public Color getColor() {
		return new Color(red, green, blue, alpha);
	}

}
