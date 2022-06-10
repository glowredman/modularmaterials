package glowredman.modularmaterials.data;

import java.io.File;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.block.IMetaOre;
import glowredman.modularmaterials.block.MetaBlock;
import glowredman.modularmaterials.data.object.sub.TagFile;
import glowredman.modularmaterials.fluid.MetaFluid;
import glowredman.modularmaterials.item.MetaBucketItem;
import glowredman.modularmaterials.item.MetaItem;
import glowredman.modularmaterials.util.FileHelper;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.resources.ResourceLocation;

public class TagHandler {
	
	public static void execute() {
		long time = System.currentTimeMillis();
		Multimap<Pair<Subdir, String>, String> tags = ArrayListMultimap.create();
		
		if(MM_Reference.CONFIG.overrideTagFiles) cleanTags();
		
		generateBlockTags(tags);
		generateBucketTags(tags);
		generateFluidTags(tags);
		generateOreTags(tags);
		generateItemTags(tags);
		
		generateFiles(tags);
		
    	ModularMaterials.info(String.format("Generated %d tags in %dms", tags.size(), System.currentTimeMillis() - time));
	}
    
    //Parameters
    public static final String PARAM_MATERIAL = "<m>";
    public static final String PARAM_TYPE = "<t>";
    public static final String PARAM_ORE_VARIANT = "<o>"; //Only for Ores!
    
    //Filters
    public static final String FILTER_TYPE_START = "[";
    public static final String FILTER_TYPE_END = "]";
    
    private static void generateItemTags(Multimap<Pair<Subdir, String>, String> tags) {
        for(MetaItem item : MM_Reference.ITEMS) {
            
            String typeIdentifier = item.getTypeIdentifier();
            
            for(String s : item.material.item.tags) {
                int start = s.indexOf(FILTER_TYPE_START);
                int end = s.indexOf(FILTER_TYPE_END);
                
                //which types?
                //if no type filters are given -> all types allowed
                if(start >= 0 && end > start) {
                    boolean forThisType = false;
                    for(String type : s.substring(start + 1, end).split(",")) {
                        if(type.equals(typeIdentifier)) {
                            forThisType = true;
                            break;
                        }
                    }
                    s = s.substring(end + 1);
                    if(!forThisType) continue;
                }
                
                s = s.replace(PARAM_TYPE, item.type.tagName);
                
                for(String name : item.material.tagNames) {
                    tags.put(Pair.of(Subdir.ITEMS, s.replace(PARAM_MATERIAL, name)), item.registryName.toString());
                }
                
            }
            
            for(String s : item.type.tags) {
                s = s.replace(PARAM_TYPE, item.type.tagName);
                for(String name : item.material.tagNames) {
                    tags.put(Pair.of(Subdir.ITEMS, s.replace(PARAM_MATERIAL, name)), item.registryName.toString());
                }
            }
        }
    }
    
    private static void generateBucketTags(Multimap<Pair<Subdir, String>, String> tags) {
        for(MetaBucketItem item : MM_Reference.BUCKETS) {
            for(String s : item.fluid().material.fluid.tags) {
                int start = s.indexOf(FILTER_TYPE_START);
                int end = s.indexOf(FILTER_TYPE_END);
                
                //which types?
                //if no type filters are given -> all types allowed
                if(start >= 0 && end > start) {
                    boolean forThisType = false;
                    for(String type : s.substring(start + 1, end).split(",")) {
                        if(type.equals("bucket")) {
                            forThisType = true;
                            break;
                        }
                    }
                    s = s.substring(end + 1);
                    if(!forThisType) continue;
                    
                    s = s.replace(PARAM_TYPE, item.fluid().type.tagName);
                    
                    for(String name : item.fluid().material.tagNames) {
                        tags.put(Pair.of(Subdir.ITEMS, s.replace(PARAM_MATERIAL, name)), item.registryName.toString());
                    }
                }
            }
        }
    }
    
    private static void generateFluidTags(Multimap<Pair<Subdir, String>, String> tags) {
        for(MetaFluid fluid : MM_Reference.FLUIDS) {
            
            String typeIdentifier = fluid.getTypeIdentifier();
            String regNameS = fluid.registryName.toString();
            String regNameF = regNameS.replace(MM_Reference.MODID + ":", MM_Reference.MODID + ":flowing_");
            
            for(String s : fluid.material.fluid.tags) {
                int start = s.indexOf(FILTER_TYPE_START);
                int end = s.indexOf(FILTER_TYPE_END);
                
                //which types?
                //if no type filters are given -> all types allowed
                if(start >= 0 && end > start) {
                    boolean forThisType = false;
                    for(String type : s.substring(start + 1, end).split(",")) {
                        if(type.equals(typeIdentifier)) {
                            forThisType = true;
                            break;
                        }
                    }
                    s = s.substring(end + 1);
                    if(!forThisType) continue;
                }
                
                s = s.replace(PARAM_TYPE, fluid.type.tagName);
                
                for(String name : fluid.material.tagNames) {
                    String finalTag = s.replace(PARAM_MATERIAL, name);
                    tags.put(Pair.of(Subdir.FLUIDS, finalTag), regNameS);
                    tags.put(Pair.of(Subdir.FLUIDS, finalTag), regNameF);
                }
                
            }
            
            for(String s : fluid.type.tags) {
                s = s.replace(PARAM_TYPE, fluid.type.tagName);
                for(String name : fluid.material.tagNames) {
                    String finalTag = s.replace(PARAM_MATERIAL, name);
                    tags.put(Pair.of(Subdir.FLUIDS, finalTag), regNameS);
                    tags.put(Pair.of(Subdir.FLUIDS, finalTag), regNameF);
                }
            }
        }
    }

    private static void generateBlockTags(Multimap<Pair<Subdir, String>, String> tags) {
        for(MetaBlock block : MM_Reference.BLOCKS) {
            
            String typeIdentifier = block.getTypeIdentifier();
            String regName = block.registryName.toString();
            
            for(String s : block.material.block.tags) {
                int start = s.indexOf(FILTER_TYPE_START);
                int end = s.indexOf(FILTER_TYPE_END);
                
                //which types?
                //if no type filters are given -> all types allowed
                if(start >= 0 && end > start) {
                    boolean forThisType = false;
                    for(String type : s.substring(start + 1, end).split(",")) {
                        if(type.equals(typeIdentifier)) {
                            forThisType = true;
                            break;
                        }
                    }
                    s = s.substring(end + 1);
                    if(!forThisType) continue;
                }
                
                s = s.replace(PARAM_TYPE, block.type.tagName);
                
                for(String name : block.material.tagNames) {
                    tags.put(Pair.of(Subdir.BLOCKS, s.replace(PARAM_MATERIAL, name)), regName);
                    tags.put(Pair.of(Subdir.ITEMS, s.replace(PARAM_MATERIAL, name)), regName);
                }
                
            }
            
            for(String s : block.type.tags) {
                s = s.replace(PARAM_TYPE, block.type.tagName);
                for(String name : block.material.tagNames) {
                    tags.put(Pair.of(Subdir.BLOCKS, s.replace(PARAM_MATERIAL, name)), regName);
                    tags.put(Pair.of(Subdir.ITEMS, s.replace(PARAM_MATERIAL, name)), regName);
                }
            }
        }
    }
    
    private static void generateOreTags(Multimap<Pair<Subdir, String>, String> tags) {
        for(IMetaOre ore : MM_Reference.ORES.values()) {
            
            String variantIdentifier = ore.getVariantIdentifier();
            String regName = ore.getRegistryName().toString();
            
            for(String s : ore.getMaterial().ore.tags) {
                int start = s.indexOf(FILTER_TYPE_START);
                int end = s.indexOf(FILTER_TYPE_END);
                
                //which variant?
                //if no variant filters are given -> all variants allowed
                if(start >= 0 && end > start) {
                    boolean forThisVariant = false;
                    for(String variant : s.substring(start + 1, end).split(",")) {
                        if(variant.equals(variantIdentifier)) {
                            forThisVariant = true;
                            break;
                        }
                    }
                    s = s.substring(end + 1);
                    if(!forThisVariant) continue;
                }
                
                s = s.replace(PARAM_ORE_VARIANT, ore.getVariant().variantName);
                
                for(String name : ore.getMaterial().tagNames) {
                    tags.put(Pair.of(Subdir.BLOCKS, s.replace(PARAM_MATERIAL, name)), regName);
                    tags.put(Pair.of(Subdir.ITEMS, s.replace(PARAM_MATERIAL, name)), regName);
                }
                
            }
            
            for(String s : ore.getVariant().tags) {
                s = s.replace(PARAM_ORE_VARIANT, ore.getVariant().variantName);
                
                for(String name : ore.getMaterial().tagNames) {
                    tags.put(Pair.of(Subdir.BLOCKS, s.replace(PARAM_MATERIAL, name)), regName);
                    tags.put(Pair.of(Subdir.ITEMS, s.replace(PARAM_MATERIAL, name)), regName);
                }
            }
        }
    }
    
    private static void cleanTags() {
    	for(File dir : ResourceLoader.DATA_DIR.listFiles(File::isDirectory)) {
    		if(!dir.getName().equals(MM_Reference.MODID)) {
    			FileHelper.cleanDir(dir);
    		}
    	}
    }
    
    private static void generateFiles(Multimap<Pair<Subdir, String>, String> tags) {
    	tags.asMap().forEach((key, values) -> {
    		File file = key.left().getFile(key.right());

			if (!file.exists()) {
				try {
					file.getParentFile().mkdirs();
					FileHelper.write(file, JSONHandler.GSON.toJson(new TagFile(values)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
    	});
	}
    
    private enum Subdir {
    	ITEMS,
    	BLOCKS,
    	FLUIDS;
    	
    	private File getFile(String tag) {
    		ResourceLocation rl = new ResourceLocation(tag);
    		return new File(ResourceLoader.DATA_DIR, String.format("data/%s/tags/%s/%s.json", rl.getNamespace(), this, rl.getPath()));
    	}
    	
    	@Override
    	public String toString() {
    		return this.name().toLowerCase();
    	}
    }

}
