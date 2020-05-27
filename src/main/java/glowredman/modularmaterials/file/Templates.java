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
	
	public static final String[] BLOCKSTATE_ORE = new String[] {
			"{",
			"\t\"variants\": {",
			"\t\t\"normal\": {",
			"\t\t\t\"model\": \"" + MODID + ":%x/%t/%o\"",
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
	
	public static final String[] MODEL_BLOCK = new String[] {
			"{",
			"\t\"parent\": \"block/block\",",
			"\t\"textures\": {",
			"\t\t\"0\": \"" + MODID + ":blocks/%x/%t\",",
			"\t\t\"1\": \"" + MODID + ":blocks/%x/%t_overlay\",",
			"\t\t\"particle\": \"" + MODID + ":blocks/%x/%t\"",
			"\t},",
			"\t\"elements\": [",
			"\t\t{",
			"\t\t\t\"from\": [0, 0, 0],",
			"\t\t\t\"to\": [16, 16, 16],",
			"\t\t\t\"faces\": {",
			"\t\t\t\t\"north\": {\"texture\": \"#0\", \"tintindex\": 0},",
			"\t\t\t\t\"east\": {\"texture\": \"#0\", \"tintindex\": 0},",
			"\t\t\t\t\"south\": {\"texture\": \"#0\", \"tintindex\": 0},",
			"\t\t\t\t\"west\": {\"texture\": \"#0\", \"tintindex\": 0},",
			"\t\t\t\t\"up\": {\"texture\": \"#0\", \"tintindex\": 0},",
			"\t\t\t\t\"down\": {\"texture\": \"#0\", \"tintindex\": 0}",
			"\t\t\t}",
			"\t\t},",
			"\t\t{",
			"\t\t\t\"from\": [0, 0, 0],",
			"\t\t\t\"to\": [16, 16, 16],",
			"\t\t\t\"faces\": {",
			"\t\t\t\t\"north\": {\"texture\": \"#1\", \"tintindex\": -1},",
			"\t\t\t\t\"east\": {\"texture\": \"#1\", \"tintindex\": -1},",
			"\t\t\t\t\"south\": {\"texture\": \"#1\", \"tintindex\": -1},",
			"\t\t\t\t\"west\": {\"texture\": \"#1\", \"tintindex\": -1},",
			"\t\t\t\t\"up\": {\"texture\": \"#1\", \"tintindex\": -1},",
			"\t\t\t\t\"down\": {\"texture\": \"#1\", \"tintindex\": -1}",
			"\t\t\t}",
			"\t\t}",
			"\t]",
			"}"
	};
	
	public static final String[] MODEL_ORE = new String[] {
			"{",
			"\t\"parent\": \"block/block\",",
			"\t\"textures\": {",
			"\t\t\"0\": \"%b\",",
			"\t\t\"1\": \"" + MODID + ":blocks/%x/%t\",",
			"\t\t\"particle\": \"%b\"",
			"\t},",
			"\t\"elements\": [",
			"\t\t{",
			"\t\t\t\"from\": [0, 0, 0],",
			"\t\t\t\"to\": [16, 16, 16],",
			"\t\t\t\"faces\": {",
			"\t\t\t\t\"north\": {\"texture\": \"#0\", \"tintindex\": -1},",
			"\t\t\t\t\"east\": {\"texture\": \"#0\", \"tintindex\": -1},",
			"\t\t\t\t\"south\": {\"texture\": \"#0\", \"tintindex\": -1},",
			"\t\t\t\t\"west\": {\"texture\": \"#0\", \"tintindex\": -1},",
			"\t\t\t\t\"up\": {\"texture\": \"#0\", \"tintindex\": -1},",
			"\t\t\t\t\"down\": {\"texture\": \"#0\", \"tintindex\": -1}",
			"\t\t\t}",
			"\t\t},",
			"\t\t{",
			"\t\t\t\"from\": [0, 0, 0],",
			"\t\t\t\"to\": [16, 16, 16],",
			"\t\t\t\"faces\": {",
			"\t\t\t\t\"north\": {\"texture\": \"#1\", \"tintindex\": 0},",
			"\t\t\t\t\"east\": {\"texture\": \"#1\", \"tintindex\": 0},",
			"\t\t\t\t\"south\": {\"texture\": \"#1\", \"tintindex\": 0},",
			"\t\t\t\t\"west\": {\"texture\": \"#1\", \"tintindex\": 0},",
			"\t\t\t\t\"up\": {\"texture\": \"#1\", \"tintindex\": 0},",
			"\t\t\t\t\"down\": {\"texture\": \"#1\", \"tintindex\": 0}",
			"\t\t\t}",
			"\t\t}",
			"\t]",
			"}"
	};

}
