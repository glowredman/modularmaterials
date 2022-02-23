package glowredman.modularmaterials.data.object.sub;

import java.util.ArrayList;
import java.util.Collection;

public class TagFile {
	
	public boolean replace = false;
	public Collection<String> values = new ArrayList<>();
	
	public TagFile(Collection<String> values) {
		this.values = values;
	}

}
