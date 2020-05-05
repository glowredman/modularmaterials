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
		return new java.awt.Color(red, green, blue).getRGB();
	}

	public int getAlpha() {
		return alpha;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

}
