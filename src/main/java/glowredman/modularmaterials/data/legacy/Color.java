package glowredman.modularmaterials.data.legacy;

import glowredman.modularmaterials.data.object.sub.ColorProperties;

public class Color {
	
	public int alpha = 0;
	public int red = 255;
	public int green = 255;
	public int blue = 255;
	
	public ColorProperties convert() {
		if(alpha == 0) alpha = 255;
		return new ColorProperties(red, green, blue, alpha);
	}

}
