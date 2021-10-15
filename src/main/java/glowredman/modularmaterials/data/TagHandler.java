package glowredman.modularmaterials.data;

import java.io.File;
import java.util.Collection;
import java.util.Map.Entry;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.block.IMetaOre;
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
		generateOreTags();
		generateItemTags();
	}
    
    //Parameters
    public static final String PARAM_MATERIAL = "<m>";
    public static final String PARAM_TYPE = "<t>";
    public static final String PARAM_ORE_VARIANT = "<o>"; //Only for Ores!
    
    //Filters
    public static final String FILTER_TYPE_START = "[";
    public static final String FILTER_TYPE_END = "]";
    
    private static void generateItemTags() {
        long time = System.currentTimeMillis();

        cleanDataDir();
        
        ModularMaterials.info("Generating item tags...");
        
        ListMultimap<String, String> tags = ArrayListMultimap.create();
        
        for(MetaItem item : MM_Reference.ITEMS) {
            
            String typeIdentifier = item.getTypeIdentifier();
            String regName = item.getRegistryName().toString();
            
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
                    if(!tags.containsEntry(finalTag, regName)) {
                        tags.put(finalTag, regName);
                    }
                }
                
            }
            
            for(String s : item.type.tags) {
                s = s.replace(PARAM_TYPE, item.type.tagName);
                
                for(String name : item.material.tagNames) {
                    String finalTag = s.replace(PARAM_MATERIAL, name);
                    if(!tags.containsEntry(finalTag, regName)) {
                        tags.put(finalTag, regName);
                    }
                }
                
            }
            
        }
        
        for(Entry<String, Collection<String>> e : tags.asMap().entrySet()) {
            ResourceLocation tag = new ResourceLocation(e.getKey());
            
            File file = new File(ResourceLoader.DATA_DIR, "data/" + tag.getNamespace() + "/tags/items/" + tag.getPath() + ".json");
            
            if(!file.exists()) {
                try {
                    file.getParentFile().mkdirs();
                    FileHelper.write(file, JSONHandler.GSON.toJson(new TagFile(e.getValue())));
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
        
        ListMultimap<String, String> tags = ArrayListMultimap.create();
        
        for(MetaBucketItem item : MM_Reference.BUCKETS) {
        	
        	String regName = item.getRegistryName().toString();
        	
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
                        if(!tags.containsEntry(finalTag, regName)) {
                            tags.put(finalTag, regName);
                        }
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
        
        ListMultimap<String, String> tags = ArrayListMultimap.create();
        
        for(MetaFluid fluid : MM_Reference.FLUIDS) {
            
            String typeIdentifier = fluid.getTypeIdentifier();
            String regNameS = fluid.getRegistryName().toString();
            String regNameF = regNameS.replace(MM_Reference.MODID + ":", MM_Reference.MODID + ":flowing_");
            
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
                    if(!tags.containsEntry(finalTag, regNameS)) {
                        tags.put(finalTag, regNameS);
                    }
                    if(!tags.containsEntry(finalTag, regNameS)) {
                        tags.put(finalTag, regNameF);
                    }
                }
                
            }
            
            for(String s : fluid.type.tags) {
                s = s.replace(PARAM_TYPE, fluid.type.tagName);
                
                for(String name : fluid.material.tagNames) {
                    String finalTag = s.replace(PARAM_MATERIAL, name);
                    if(!tags.containsEntry(finalTag, regNameS)) {
                        tags.put(finalTag, regNameS);
                    }
                    if(!tags.containsEntry(finalTag, regNameS)) {
                        tags.put(finalTag, regNameF);
                    }
                }
                
            }
        	
        }
        
        for(Entry<String, Collection<String>> e : tags.asMap().entrySet()) {
            ResourceLocation tag = new ResourceLocation(e.getKey());
            
            File file = new File(ResourceLoader.DATA_DIR, "data/" + tag.getNamespace() + "/tags/fluids/" + tag.getPath() + ".json");
            
            if(!file.exists()) {
                try {
                    file.getParentFile().mkdirs();
                    FileHelper.write(file, JSONHandler.GSON.toJson(new TagFile(e.getValue())));
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
        
        ListMultimap<String, String> tags = ArrayListMultimap.create();
        
        for(MetaBlock block : MM_Reference.BLOCKS) {
            
            String typeIdentifier = block.getTypeIdentifier();
            String regName = block.getRegistryName().toString();
            
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
                    if(!tags.containsEntry(finalTag, regName)) {
                        tags.put(finalTag, regName);
                    }
                }
                
            }
            
            for(String s : block.type.tags) {
                s = s.replace(PARAM_TYPE, block.type.tagName);
                
                for(String name : block.material.tagNames) {
                    String finalTag = s.replace(PARAM_MATERIAL, name);
                    if(!tags.containsEntry(finalTag, regName)) {
                        tags.put(finalTag, regName);
                    }
                }
                
            }
            
        }
        
        for(Entry<String, Collection<String>> e : tags.asMap().entrySet()) {
            ResourceLocation tag = new ResourceLocation(e.getKey());
            
            File fileItems = new File(ResourceLoader.DATA_DIR, "data/" + tag.getNamespace() + "/tags/items/" + tag.getPath() + ".json");
            File fileBlocks = new File(ResourceLoader.DATA_DIR, "data/" + tag.getNamespace() + "/tags/blocks/" + tag.getPath() + ".json");
            
            if(!fileItems.exists()) {
                try {
                    fileItems.getParentFile().mkdirs();
                    FileHelper.write(fileItems, JSONHandler.GSON.toJson(new TagFile(e.getValue())));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            if(!fileBlocks.exists()) {
                try {
                    fileBlocks.getParentFile().mkdirs();
                    FileHelper.write(fileBlocks, JSONHandler.GSON.toJson(new TagFile(e.getValue())));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        
        ModularMaterials.info(String.format("Done! Generated %d tags in %dms", tags.size(), System.currentTimeMillis() - time));
    }
    
    private static void generateOreTags() {
        long time = System.currentTimeMillis();

        cleanDataDir();
        
        ModularMaterials.info("Generating ore tags...");
        
        ListMultimap<String, String> tags = ArrayListMultimap.create();
        
        for(IMetaOre ore : MM_Reference.ORES.values()) {
            
            String variantIdentifier = ore.getVariantIdentifier();
            String regName = ore.getBlock().getRegistryName().toString();
            
            for(String s : ore.getMaterial().ore.tags) {
                
                //which variant?
                //if no variant filters are given -> all variants allowed
                if(s.contains(FILTER_TYPE_START) && s.contains(FILTER_TYPE_END)) {
                    boolean forThisVariant = false;
                    int start = s.indexOf(FILTER_TYPE_START);
                    int end = s.indexOf(FILTER_TYPE_END);
                    for(String variant : s.substring(start, end - 1).split(",")) {
                        if(variant.equals(variantIdentifier)) {
                            forThisVariant = true;
                            break;
                        }
                    }
                    s = s.substring(end);
                    if(!forThisVariant) continue;
                }
                
                s = s.replace(PARAM_ORE_VARIANT, ore.getVariant().variantName);
                
                for(String name : ore.getMaterial().tagNames) {
                    String finalTag = s.replace(PARAM_MATERIAL, name);
                    if(!tags.containsEntry(finalTag, regName)) {
                        tags.put(finalTag, regName);
                    }
                }
                
            }
            
            for(String s : ore.getVariant().tags) {
                s = s.replace(PARAM_ORE_VARIANT, ore.getVariant().variantName);
                
                for(String name : ore.getMaterial().tagNames) {
                    String finalTag = s.replace(PARAM_MATERIAL, name);
                    if(!tags.containsEntry(finalTag, regName)) {
                        tags.put(finalTag, regName);
                    }
                }
                
            }
            
        }
        
        for(Entry<String, Collection<String>> e : tags.asMap().entrySet()) {
            ResourceLocation tag = new ResourceLocation(e.getKey());
            
            File fileItems = new File(ResourceLoader.DATA_DIR, "data/" + tag.getNamespace() + "/tags/items/" + tag.getPath() + ".json");
            File fileBlocks = new File(ResourceLoader.DATA_DIR, "data/" + tag.getNamespace() + "/tags/blocks/" + tag.getPath() + ".json");
            
            if(!fileItems.exists()) {
                try {
                    fileItems.getParentFile().mkdirs();
                    FileHelper.write(fileItems, JSONHandler.GSON.toJson(new TagFile(e.getValue())));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            if(!fileBlocks.exists()) {
                try {
                    fileBlocks.getParentFile().mkdirs();
                    FileHelper.write(fileBlocks, JSONHandler.GSON.toJson(new TagFile(e.getValue())));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        
        ModularMaterials.info(String.format("Done! Generated %d tags in %dms", tags.size(), System.currentTimeMillis() - time));
    }
    
    private static void cleanDataDir() {
        if(MM_Reference.CONFIG.overrideTagFiles) {
            MM_Reference.CONFIG.overrideTagFiles = false;
            FileHelper.cleanDir(new File(ResourceLoader.DATA_DIR, "data/" + MM_Reference.MODID + "/tags"));
        }
    }

}
