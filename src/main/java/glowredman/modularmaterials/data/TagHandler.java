package glowredman.modularmaterials.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.data.object.sub.TagFile;
import glowredman.modularmaterials.item.MetaItem;
import net.minecraft.resources.ResourceLocation;

public class TagHandler {
	
	//Parameters
	public static final String PARAM_MATERIAL = "<m>";
	public static final String PARAM_TYPE = "<t>";
	
	//Filters
	public static final String FILTER_TYPE_START = "[";
	public static final String FILTER_TYPE_END = "]";
	
	public static void generateItemTags() {
		long time = System.currentTimeMillis();
		
		ModularMaterials.info("Generating item tags...");
		
		Map<String, List<String>> tags = new HashMap<>();
		
		for(MetaItem item : MM_Reference.ITEMS) {
			
			String typeIdentifier = item.getTypeIdentifier();
			
			for(String s : item.material.item.typeEnabledTags) {
				
				//which types?
				//if no type filters are given -> all types allowed
				if(s.contains(FILTER_TYPE_START) && s.contains(FILTER_TYPE_END)) {
					boolean forThisType = false;
					int start = s.indexOf(FILTER_TYPE_START);
					int end = s.indexOf(FILTER_TYPE_END);
					for(String type : s.substring(start, end - 1).split(",")) {
						if(type.equals(typeIdentifier)) {
							forThisType = true;
							break;
						}
					}
					s = s.substring(end);
					if(!forThisType || !item.type.enableTags.contains(s)) continue;
				}
				
				s = s.replace(PARAM_TYPE, item.type.tagName);
				
				for(String name : item.material.tagNames) {
					String finalTag = s.replace(PARAM_MATERIAL, name);
					addItemToTag(tags, finalTag, item.getRegistryName().toString());
				}
				
			}
			
			for(String s : item.material.item.tags) {
				
				//which types?
				//if no type filters are given -> all types allowed
				if(s.contains(FILTER_TYPE_START) && s.contains(FILTER_TYPE_END)) {
					boolean forThisType = false;
					int start = s.indexOf(FILTER_TYPE_START);
					int end = s.indexOf(FILTER_TYPE_END);
					for(String type : s.substring(start, end - 1).split(",")) {
						if(type.equals(typeIdentifier)) {
							forThisType = true;
							break;
						}
					}
					s = s.substring(end);
					if(!forThisType) continue;
				}
				
				s = s.replace(PARAM_TYPE, item.type.tagName);
				
				for(String name : item.material.tagNames) {
					String finalTag = s.replace(PARAM_MATERIAL, name);
					addItemToTag(tags, finalTag, item.getRegistryName().toString());
				}
				
			}
			
			for(String s : item.type.tags) {
				s = s.replace(PARAM_TYPE, item.type.tagName);
				
				for(String name : item.material.tagNames) {
					String finalTag = s.replace(PARAM_MATERIAL, name);
					addItemToTag(tags, finalTag, item.getRegistryName().toString());
				}
				
			}
			
		}
		
		for(Entry<String, List<String>> e : tags.entrySet()) {
			ResourceLocation tag = new ResourceLocation(e.getKey());
			
			File file = new File(ResourceLoader.DATA_DIR, "data/" + tag.getNamespace() + "/tags/items/" + tag.getPath() + ".json");
			
			if(!file.exists()) {
				try {
					file.getParentFile().mkdirs();
					BufferedWriter w = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8));
					w.write(JSONHandler.GSON.toJson(new TagFile(e.getValue())));
					w.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		
		ModularMaterials.info(String.format("Done! Generated %d tags in %dms", tags.size(), System.currentTimeMillis() - time));
	}
	
	private static void addItemToTag(Map<String, List<String>> map, String tag, String item) {
		if(map.containsKey(tag)) {
			List<String> l = map.get(tag);
			if(!l.contains(item))
				l.add(item);
		} else {
			List<String> l = new ArrayList<>();
			l.add(item);
			map.put(tag, l);
		}
	}

}
