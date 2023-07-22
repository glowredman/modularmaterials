package glowredman.modularmaterials.data.object.sub;

public enum PulseMode {
    NONE,
    POS,
    NEG;
    
    public static int colorDeviation = 0;
    
    public int pulse(int channel) {
        return switch (this) {
            case NONE -> channel;
            case POS -> clamp(channel + colorDeviation);
            case NEG -> clamp(channel - colorDeviation);
        };
    }
    
    private static int clamp(int i) {
        if(i < 0) return 0;
        if(i > 255) return 255;
        return i;
    }
}
