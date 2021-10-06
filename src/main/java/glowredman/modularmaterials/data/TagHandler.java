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
import glowredman.modularmaterials.block.MetaBlock;
import glowredman.modularmaterials.data.object.sub.TagFile;
import glowredman.modularmaterials.fluid.MetaFluid;
import glowredman.modularmaterials.item.MetaBucketItem;
import glowredman.modularmaterials.item.MetaItem;
import glowredman.modularmaterials.util.FileHelper;
import net.minecraft.resources.ResourceLocation;

public class TagHandler {
	
	public static void execute() {
		generateBlockTags();
		generateBucketTags();
		generateFluidTags();
		generateItemTags();
	}
    
    //Parameters
    public static final String PARAM_MATERIAL = "<m>";
    public static final String PARAM_TYPE = "<t>";
    public static final String PARAM_ORE_TYPE = "<o>"; //Only for Ores!
    
    //Filters
    public static final String FILTER_TYPE_START = "[";
    public static final String FILTER_TYPE_END = "]";
    
    private static void generateItemTags() {
        long time = System.currentTimeMillis();

        cleanDataDir();
        
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
    
    private static void generateBucketTags() {
        long time = System.currentTimeMillis();

        cleanDataDir();
        
        ModularMaterials.info("Generating bucket tags...");
        
        Map<String, List<String>> tags = new HashMap<>();
        
        for(MetaBucketItem item : MM_Reference.BUCKETS) {
            
            for(String s : item.fluid().material.fluid.typeEnabledTags) {
                
                //which types?
                //if no type filters are given -> all types allowed
                if(s.contains(FILTER_TYPE_START) && s.contains(FILTER_TYPE_END)) {
                    boolean forThisType = false;
                    int start = s.indexOf(FILTER_TYPE_START);
                    int end = s.indexOf(FILTER_TYPE_END);
                    for(String type : s.substring(start, end - 1).split(",")) {
                        if(type.equals("bucket")) {
                            forThisType = true;
                            break;
                        }
                    }
                    s = s.substring(end);
                    if(!forThisType || !item.fluid().type.enableTags.contains(s)) continue;
                    
                    s = s.replace(PARAM_TYPE, item.fluid().type.tagName);
                    
                    for(String name : item.fluid().material.tagNames) {
                        String finalTag = s.replace(PARAM_MATERIAL, name);
                        addItemToTag(tags, finalTag, item.getRegistryName().toString());
                    }
                }
            }
            
            for(String s : item.fluid().material.fluid.tags) {
                
                //which types?
                //if no type filters are given -> all types allowed
                if(s.contains(FILTER_TYPE_START) && s.contains(FILTER_TYPE_END)) {
                    boolean forThisType = false;
                    int start = s.indexOf(FILTER_TYPE_START);
                    int end = s.indexOf(FILTER_TYPE_END);
                    for(String type : s.substring(start, end - 1).split(",")) {
                        if(type.equals("bucket")) {
                            forThisType = true;
                            break;
                        }
                    }
                    s = s.substring(end);
                    if(!forThisType) continue;
                    
                    s = s.replace(PARAM_TYPE, item.fluid().type.tagName);
                    
                    for(String name : item.fluid().material.tagNames) {
                        String finalTag = s.replace(PARAM_MATERIAL, name);
                        addItemToTag(tags, finalTag, item.getRegistryName().toString());
                    }
                }
            }
        }
        
        ModularMaterials.info(String.format("Done! Generated %d tags in %dms", tags.size(), System.currentTimeMillis() - time));
    	
    }
    
    private static void generateFluidTags() {
        long time = System.currentTimeMillis();

        cleanDataDir();
        
        ModularMaterials.info("Generating item tags...");
        
        Map<String, List<String>> tags = new HashMap<>();
        
        for(MetaFluid fluid : MM_Reference.FLUIDS) {
            
            String typeIdentifier = fluid.getTypeIdentifier();
            
            for(String s : fluid.material.item.typeEnabledTags) {
                
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
                    if(!forThisType || !fluid.type.enableTags.contains(s)) continue;
                }
                
                s = s.replace(PARAM_TYPE, fluid.type.tagName);
                
                for(String name : fluid.material.tagNames) {
                    String finalTag = s.replace(PARAM_MATERIAL, name);
                    addItemToTag(tags, finalTag, fluid.getRegistryName().toString());
                    addItemToTag(tags, finalTag, fluid.getRegistryName().toString().replace(MM_Reference.MODID + ":", MM_Reference.MODID + ":flowing_"));
                }
                
            }
            
            for(String s : fluid.material.item.tags) {
                
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
                
                s = s.replace(PARAM_TYPE, fluid.type.tagName);
                
                for(String name : fluid.material.tagNames) {
                    String finalTag = s.replace(PARAM_MATERIAL, name);
                    addItemToTag(tags, finalTag, fluid.getRegistryName().toString());
                    addItemToTag(tags, finalTag, fluid.getRegistryName().toString().replace(MM_Reference.MODID + ":", MM_Reference.MODID + ":flowing_"));
                }
                
            }
            
            for(String s : fluid.type.tags) {
                s = s.replace(PARAM_TYPE, fluid.type.tagName);
                
                for(String name : fluid.material.tagNames) {
                    String finalTag = s.replace(PARAM_MATERIAL, name);
                    addItemToTag(tags, finalTag, fluid.getRegistryName().toString());
                    addItemToTag(tags, finalTag, fluid.getRegistryName().toString().replace(MM_Reference.MODID + ":", MM_Reference.MODID + ":flowing_"));
                }
                
            }
        	
        }
        
        for(Entry<String, List<String>> e : tags.entrySet()) {
            ResourceLocation tag = new ResourceLocation(e.getKey());
            
            File file = new File(ResourceLoader.DATA_DIR, "data/" + tag.getNamespace() + "/tags/fluids/" + tag.getPath() + ".json");
            
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

    private static void generateBlockTags() {
        long time = System.currentTimeMillis();

        cleanDataDir();
        
        ModularMaterials.info("Generating block tags...");
        
        Map<String, List<String>> tags = new HashMap<>();
        
        for(MetaBlock block : MM_Reference.BLOCKS) {
            
            String typeIdentifier = block.getTypeIdentifier();
            
            for(String s : block.material.block.typeEnabledTags) {
                
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
                    if(!forThisType || !block.type.enableTags.contains(s)) continue;
                }
                
                s = s.replace(PARAM_TYPE, block.type.tagName);
                
                for(String name : block.material.tagNames) {
                    String finalTag = s.replace(PARAM_MATERIAL, name);
                    addItemToTag(tags, finalTag, block.getRegistryName().toString());
                }
                
            }
            
            for(String s : block.material.block.tags) {
                
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
                
                s = s.replace(PARAM_TYPE, block.type.tagName);
                
                for(String name : block.material.tagNames) {
                    String finalTag = s.replace(PARAM_MATERIAL, name);
                    addItemToTag(tags, finalTag, block.getRegistryName().toString());
                }
                
            }
            
            for(String s : block.type.tags) {
                s = s.replace(PARAM_TYPE, block.type.tagName);
                
                for(String name : block.material.tagNames) {
                    String finalTag = s.replace(PARAM_MATERIAL, name);
                    addItemToTag(tags, finalTag, block.getRegistryName().toString());
                }
                
            }
            
        }
        
        for(Entry<String, List<String>> e : tags.entrySet()) {
            ResourceLocation tag = new ResourceLocation(e.getKey());
            
            File fileItems = new File(ResourceLoader.DATA_DIR, "data/" + tag.getNamespace() + "/tags/items/" + tag.getPath() + ".json");
            File fileBlocks = new File(ResourceLoader.DATA_DIR, "data/" + tag.getNamespace() + "/tags/blocks/" + tag.getPath() + ".json");
            
            if(!fileItems.exists()) {
                try {
                    fileItems.getParentFile().mkdirs();
                    BufferedWriter w = new BufferedWriter(new FileWriter(fileItems, StandardCharsets.UTF_8));
                    w.write(JSONHandler.GSON.toJson(new TagFile(e.getValue())));
                    w.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            if(!fileBlocks.exists()) {
                try {
                    fileBlocks.getParentFile().mkdirs();
                    BufferedWriter w = new BufferedWriter(new FileWriter(fileBlocks, StandardCharsets.UTF_8));
                    w.write(JSONHandler.GSON.toJson(new TagFile(e.getValue())));
                    w.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        
        ModularMaterials.info(String.format("Done! Generated %d tags in %dms", tags.size(), System.currentTimeMillis() - time));
    }
    
    private static void cleanDataDir() {
        if(MM_Reference.overrideTagFiles) {
            MM_Reference.overrideTagFiles = false;
            FileHelper.cleanDir(new File(ResourceLoader.DATA_DIR, "data/" + MM_Reference.MODID + "/tags"));
        }
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
