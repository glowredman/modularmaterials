package glowredman.modularmaterials.object;

public class Color {
	
	public int alpha;
	public int red;
	public int green;
	public int blue;
	
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
