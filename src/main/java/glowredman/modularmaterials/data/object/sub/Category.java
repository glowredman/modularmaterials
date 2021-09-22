package glowredman.modularmaterials.data.object.sub;

import static glowredman.modularmaterials.util.XSTR.XSTR_INSTANCE;

public enum Category {
	ITEM,
	BLOCK,
	ORE;
	
	public static Category random() {
		return values()[XSTR_INSTANCE.nextInt(values().length)];
	}

}
