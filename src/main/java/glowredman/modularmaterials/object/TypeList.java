package glowredman.modularmaterials.object;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class TypeList {
	
	public LinkedHashMap<String, Type> types = new LinkedHashMap<String, Type>();
	
	public TypeList() {
	}

	public LinkedHashMap<String, Type> getTypes() {
		return types;
	}

	public void setTypes(LinkedHashMap<String, Type> types) {
		this.types = types;
	}

}
