package glowredman.modularmaterials.json;

public class Color {
	
	public int r;
	public int g;
	public int b;
	public int a;
	
	public java.awt.Color getColorRGB() {
		return new java.awt.Color(r, g, b);
	}
	
	public java.awt.Color getColorRGBA() {
		return new java.awt.Color(r, g, b, a);
	}

}
