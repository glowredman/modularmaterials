package glowredman.modularmaterials.data.object.sub;

public class RawLootTable {
    
    public final String rawJSON;
    
    public RawLootTable(String rawJSON) {
        this.rawJSON = rawJSON;
    }
    
    @Override
    public String toString() {
        return this.rawJSON;
    }
    
}
