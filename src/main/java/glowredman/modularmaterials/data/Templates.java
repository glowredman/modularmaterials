package glowredman.modularmaterials.data;

import glowredman.modularmaterials.MM_Reference;

public enum Templates {
	MODEL_ITEM(
			"{",
			"  \"parent\": \"item/generated\",",
			"  \"textures\": {",
			"    \"layer0\": \"" + MM_Reference.MODID + ":items/%1$s/%2$s\",",
			"    \"layer1\": \"" + MM_Reference.MODID + ":items/%1$s/%2$s_overlay\"", "  }",
			"}");

	private String[] template;

	private Templates(String... template) {
		this.template = template;
	}
	
	/**
	 * %1$s = texture, %2$s = type, %3$s = particle
	 */
	public String format(Object... args) {
		return String.format(this.toString(), args);
	}

	@Override
	public String toString() {
		String out = template[0];
		for (int i = 1; i < template.length; i++) {
			out += '\n' + template[i];
		}
		return out;
	}

}
