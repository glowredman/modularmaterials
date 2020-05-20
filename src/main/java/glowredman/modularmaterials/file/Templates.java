package glowredman.modularmaterials.file;

import static glowredman.modularmaterials.Reference.MODID;

public class Templates {
	
	public static String getTemplateAsString(String[] template) {
		String out = template[0];
		for(int i = 1; i < template.length; i++) {
			out = out + '\n' + template[i];
		}
		return out;
	}
	
	public static final String[] BLOCKSTATE_BLOCK = new String[] {
			"{",
			"\t\"variants\": {",
			"\t\t\"normal\": {",
			"\t\t\t\"model\": \"" + MODID + ":%x/%t\"",
			"\t\t}",
			"\t}",
			"}"
	};
	
	public static final String[] MODEL_ITEM = new String[] {
			"{",
			"\t\"parent\": \"item/generated\",",
			"\t\"textures\": {",
			"\t\t\"layer0\": \"" + MODID + ":items/%x/%t\",",
			"\t\t\"layer1\": \"" + MODID + ":items/%x/%t_overlay\"",
			"\t}",
			"}"
	};

}
