package glowredman.modularmaterials.data.object.sub;

import glowredman.modularmaterials.MM_Reference;

public enum ChemicalState {
	SOLID,
	LIQUID,
	GASEOUS;
	
	public static ChemicalState random() {
		return values()[MM_Reference.RAND.nextInt(values().length)];
	}
}
