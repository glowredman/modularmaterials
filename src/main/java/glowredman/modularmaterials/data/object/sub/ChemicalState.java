package glowredman.modularmaterials.data.object.sub;

import glowredman.modularmaterials.util.XSTR;

public enum ChemicalState {
	SOLID,
	LIQUID,
	GASEOUS;
	
	public static ChemicalState random() {
		return values()[XSTR.XSTR_INSTANCE.nextInt(values().length)];
	}
}
