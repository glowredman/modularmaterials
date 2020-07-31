package glowredman.modularmaterials.object;

/**
 * A class, which holds information about a category-texture-type-triple.
 */
public class CTTT {
	
	public String category;
	public JTexture texture;
	public String type;
	
	/**
	 * An object, which holds information about a category-texture-type-triple.
	 */
	public CTTT(String category, JTexture texture, String type) {
		this.category = category;
		this.texture = texture;
		this.type = type;
	}

}
