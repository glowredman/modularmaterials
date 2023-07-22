package glowredman.modularmaterials.data.object.sub;

public class ColorProperties {
	
	public int red = 255;
	public int green = 255;
	public int blue = 255;
	public int alpha = 255;
	public PulseMode pulseModeRed = PulseMode.NONE;
    public PulseMode pulseModeGreen = PulseMode.NONE;
    public PulseMode pulseModeBlue = PulseMode.NONE;
    public PulseMode pulseModeAlpha = PulseMode.NONE;
    public PulseMode pulseModeMoltenRed = PulseMode.NONE;
    public PulseMode pulseModeMoltenGreen = PulseMode.NONE;
    public PulseMode pulseModeMoltenBlue = PulseMode.NONE;
    public PulseMode pulseModeMoltenAlpha = PulseMode.NONE;
	
	public ColorProperties() {}
	
	public ColorProperties(int red, int green, int blue, int alpha) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}
	
	public ColorProperties(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public int getARGB() {
	    return alpha << 24 | red << 16 | green << 8 | blue;
	}
	
	public int getRGB() {
	    return red << 16 | green << 8 | blue;
	}
	
	public int getPulseARGB() {
		return pulseModeAlpha.pulse(alpha) << 24
		        | pulseModeRed.pulse(red) << 16
		        | pulseModeGreen.pulse(green) << 8
		        | pulseModeBlue.pulse(blue);
	}
	
	public int getPulseRGB() {
		return pulseModeRed.pulse(red) << 16
                | pulseModeGreen.pulse(green) << 8
                | pulseModeBlue.pulse(blue);
	}
    
    public int getPulseMoltenARGB() {
        return pulseModeMoltenAlpha.pulse(alpha) << 24
                | pulseModeMoltenRed.pulse(red) << 16
                | pulseModeMoltenGreen.pulse(green) << 8
                | pulseModeMoltenBlue.pulse(blue);
    }
    
    public int getPulseMoltenRGB() {
        return pulseModeMoltenRed.pulse(red) << 16
                | pulseModeMoltenGreen.pulse(green) << 8
                | pulseModeMoltenBlue.pulse(blue);
    }
	
	@Override
	public String toString() {
		return String.format("{red: %d, green: %d, blue: %d, alpha: %d}", red, green, blue, alpha);
	}

}
