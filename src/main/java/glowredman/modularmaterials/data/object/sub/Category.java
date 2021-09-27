package glowredman.modularmaterials.data.object.sub;

import glowredman.modularmaterials.MM_Reference;

public enum Category {
	ITEM,
	BLOCK,
	ORE;
	
	public static Category random() {
		return values()[MM_Reference.RAND.nextInt(values().length)];
	}

}
