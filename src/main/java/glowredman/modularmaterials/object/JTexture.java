package glowredman.modularmaterials.object;

import static glowredman.modularmaterials.Reference.*;

import java.util.HashMap;

public class JTexture {
	
	public String model = xModel;
	public String parent = xParent;
	public String particle = xParticle;
	public HashMap<String, String> textures = new HashMap<String, String>();
	
	public JTexture(String texture) {
		textures.put("all", texture);
	}
	
	public JTexture() {
		textures.put("all", xTexture);
	}
	
	public String getTexture() {
		if(textures.containsKey("all")) {
			return textures.get("all");
		} else {
			return "";
		}
	}

}
