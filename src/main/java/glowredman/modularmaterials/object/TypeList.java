package glowredman.modularmaterials.object;

import java.util.HashMap;

public class TypeList {
	
	public HashMap<String, Type> types = new HashMap<String, Type>();
	
	public TypeList() {
	}

	public HashMap<String, Type> getTypes() {
		return types;
	}

	public void setTypes(HashMap<String, Type> types) {
		this.types = types;
	}

}
