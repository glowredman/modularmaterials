package glowredman.modularmaterials.data.object.sub;

import java.util.ArrayList;
import java.util.List;

public class TagFile {
	
	public boolean replace = false;
	public List<String> values = new ArrayList<>();
	
	public TagFile() {
	}
	
	public TagFile(List<String> values) {
		this.values = values;
	}

}
