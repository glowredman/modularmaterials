package glowredman.modularmaterials.client;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.file.PathUtils;
import org.apache.logging.log4j.Level;

import com.google.common.reflect.TypeToken;
import com.google.gson.GsonBuilder;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.block.IMetaOre;
import glowredman.modularmaterials.block.MetaBlock;
import glowredman.modularmaterials.data.JSONHandler;
import glowredman.modularmaterials.data.ResourceLoader;
import glowredman.modularmaterials.data.Templates;
import glowredman.modularmaterials.data.object.sub.ChemicalState;
import glowredman.modularmaterials.fluid.MetaFluid;
import glowredman.modularmaterials.item.MetaBucketItem;
import glowredman.modularmaterials.item.MetaItem;

public class AssetHandler {
	
	static void execute() {
		generateModelFiles();
		generateBlockstateFiles();
		generateLangFile();
	}
	
	private static void generateModelFiles() {
		long time = System.currentTimeMillis();
		int count = 0;
		
		Path models = ResourceLoader.RESOURCES_DIR.resolve("assets").resolve(MM_Reference.MODID).resolve("models");
		
		if(MM_Reference.CONFIG.overrideModelFiles) {
		    try {
                PathUtils.cleanDirectory(models);
            } catch (IOException e) {
                ModularMaterials.LOGGER.warn("Failed to clean " + models, e);
            }
		}
		
		Path models_item = models.resolve("item");
		try {
            Files.createDirectories(models_item);
        } catch (IOException e) {
            ModularMaterials.LOGGER.error("Failed to create item model directory", e);
            return;
        }
		
		for(MetaItem item : MM_Reference.ITEMS) {
			Path modelPath = models_item.resolve(item.registryName.getPath() + ".json");
			if(Files.notExists(modelPath)) {
			    try {
                    Files.writeString(modelPath, Templates.MODEL_ITEM.format(item.material.texture, item.getTypeIdentifier()));
                    count++;
                } catch (IOException e) {
                    ModularMaterials.LOGGER.warn("Failed to generate model file for " + item.registryName, e);
                }
			}
		}
		
		for(MetaBucketItem item : MM_Reference.BUCKETS) {
            Path modelPath = models_item.resolve(item.registryName.getPath() + ".json");
			if(Files.notExists(modelPath)) {
				try {
                    if(item.fluid().material.state == ChemicalState.SOLID && item.fluid().type.state == ChemicalState.LIQUID) {
                        Files.writeString(modelPath, Templates.MODEL_BUCKET_DRIP.format(item.fluid().registryName));
                    } else {
                        Files.writeString(modelPath, Templates.MODEL_BUCKET.format(item.fluid().registryName));
                    }
                    count++;
                } catch (IOException e) {
                    ModularMaterials.LOGGER.warn("Failed to generate model file for " + item.registryName, e);
                }
			}
		}
		
		Path models_block = models.resolve("block");
        try {
            Files.createDirectories(models_block);
        } catch (IOException e) {
            ModularMaterials.LOGGER.error("Failed to create block model directory", e);
            return;
        }
		
		for(MetaBlock block : MM_Reference.BLOCKS) {
			String type = block.getTypeIdentifier();
			Path modelFileInventory = models_item.resolve(block.registryName.getPath() + ".json");
			if(Files.notExists(modelFileInventory)) {
                try {
                    Files.writeString(modelFileInventory, Templates.MODEL_BLOCK.format(block.material.texture, type));
                    count++;
                } catch (IOException e) {
                    ModularMaterials.LOGGER.warn("Failed to generate inventory model file for " + block.registryName, e);
                }
			}
			Path modelTextureDir = models_block.resolve(block.material.texture);
			try {
                Files.createDirectory(modelTextureDir);
                Path modelFileNormal = modelTextureDir.resolve(type + ".json");
                if(Files.notExists(modelFileNormal)) {
                    Files.writeString(modelFileNormal, Templates.MODEL_BLOCK.format(block.material.texture, type));
                	count++;
                }
            } catch (IOException e) {
                ModularMaterials.LOGGER.warn("Failed to generate model file for " + block.registryName, e);
            }
		}
		
		for(IMetaOre ore : MM_Reference.ORES.values()) {
			String texture = ore.getMaterial().texture;
			String baseTexture = ore.getVariant().baseTexture;
            Path modelFileInventory = models_item.resolve(ore.getRegistryName().getPath() + ".json");
			if(Files.notExists(modelFileInventory)) {
				try {
                    Files.writeString(modelFileInventory, Templates.MODEL_ORE.format(texture, baseTexture));
                    count++;
                } catch (IOException e) {
                    ModularMaterials.LOGGER.warn("Failed to generate inventory model file for " + ore.getRegistryName(), e);
                }
			}
            Path modelTextureDir = models_block.resolve(texture).resolve("ore");
			try {
                Files.createDirectories(modelTextureDir);
                Path modelFileNormal = modelTextureDir.resolve(ore.getVariantIdentifier() + ".json");
                if(Files.notExists(modelFileNormal)) {
                	Files.writeString(modelFileNormal, Templates.MODEL_ORE.format(texture, baseTexture));
                	count++;
                }
            } catch (IOException e) {
                ModularMaterials.LOGGER.warn("Failed to generate model file for " + ore.getRegistryName(), e);
            }
		}
		
		ModularMaterials.LOGGER.info("Created {} model-files in {}ms.", count, System.currentTimeMillis() - time);
	}
	
	private static void generateBlockstateFiles() {
		long time = System.currentTimeMillis();
		int count = 0;
		
		Path blockstates = ResourceLoader.RESOURCES_DIR.resolve("assets").resolve(MM_Reference.MODID).resolve("blockstates");
		
		if(MM_Reference.CONFIG.overrideBlockstateFiles) {
		    try {
                PathUtils.cleanDirectory(blockstates);
            } catch (IOException e) {
                ModularMaterials.LOGGER.warn("Failed to clean " + blockstates, e);
            }
		}
		
		try {
            Files.createDirectories(blockstates);
        } catch (IOException e) {
            ModularMaterials.LOGGER.error("Failed to create blockstate directory", e);
            return;
        }
		
		for(MetaBlock block : MM_Reference.BLOCKS) {
			Path blockstateFile = blockstates.resolve(block.registryName.getPath() + ".json");
			if(Files.notExists(blockstateFile)) {
				try {
                    Files.writeString(blockstateFile, Templates.BLOCKSTATE_BLOCK.format(block.material.texture, block.getTypeIdentifier()));
                    count++;
                } catch (IOException e) {
                    ModularMaterials.LOGGER.warn("Failed to generate blockstate file for " + block.registryName, e);
                }
			}
		}
		
		for(IMetaOre ore : MM_Reference.ORES.values()) {
            Path blockstateFile = blockstates.resolve(ore.getRegistryName().getPath() + ".json");
			if(Files.notExists(blockstateFile)) {
				try {
                    Files.writeString(blockstateFile, Templates.BLOCKSTATE_ORE.format(ore.getMaterial().texture, ore.getVariantIdentifier()));
                    count++;
                } catch (IOException e) {
                    ModularMaterials.LOGGER.warn("Failed to generate blockstate file for " + ore.getRegistryName(), e);
                }
			}
		}
		
		ModularMaterials.LOGGER.info("Created {} blockstate-files in {}ms.", count, System.currentTimeMillis() - time);
	}
	
	private static void generateLangFile() {
		long time = System.currentTimeMillis();
		
		Path langFile = ResourceLoader.RESOURCES_DIR.resolve("assets").resolve(MM_Reference.MODID).resolve("lang").resolve("en_us.json");
		
		Type type = new TypeToken<Map<String, String>>() {
			private static final long serialVersionUID = 5878402630771915782L;}.getType();
		
		if(Files.exists(langFile)) {
		    
		    Map<String, String> lang = null;
		    int startSize = 0;
			
			try {
				lang = JSONHandler.GSON.fromJson(Files.readString(langFile), type);
				startSize = lang.size();
			} catch (Exception e) {
			    ModularMaterials.LOGGER.error("Failed to read existing lang file", e);
				return;
			}
            
            for(MetaItem item : MM_Reference.ITEMS) {
                String key = "item." + MM_Reference.MODID + "." + item.registryName.getPath();
                if(!lang.containsKey(key)) {
                    lang.put(key, item.getLocalizedName());
                }
            }
            
            for(MetaBucketItem item : MM_Reference.BUCKETS) {
                String key = "item." + MM_Reference.MODID + "." + item.registryName.getPath();
                if(!lang.containsKey(key)) {
                    lang.put(key, item.getLocalizedName());
                }
            }
            
            for(MetaFluid fluid : MM_Reference.FLUIDS) {
                String keyNormal = "fluid." + MM_Reference.MODID + "." + fluid.registryName.getPath();
                if(!lang.containsKey(keyNormal)) {
                    lang.put(keyNormal, fluid.getLocalizedName());
                }
                String keyType = "fluid_type." + MM_Reference.MODID + "." + fluid.registryName.getPath();
                if(!lang.containsKey(keyType)) {
                    lang.put(keyType, fluid.getLocalizedName());
                }
            }
            
            for(MetaBlock block : MM_Reference.BLOCKS) {
                String key = "block." + MM_Reference.MODID + "." + block.registryName.getPath();
                if(!lang.containsKey(key)) {
                    lang.put(key, block.getLocalizedName());
                }
            }
            
            for(IMetaOre ore : MM_Reference.ORES.values()) {
                String key = "block." + MM_Reference.MODID + "." + ore.getRegistryName().getPath();
                if(!lang.containsKey(key)) {
                    lang.put(key, ore.getLocalizedName());
                }
            }
            
            try {
                Files.delete(langFile);
                Files.writeString(langFile, JSONHandler.GSON.toJson(lang, type));
            } catch (IOException e) {
                ModularMaterials.LOGGER.warn("Failed to rewrite lang file", e);
            }
            
            ModularMaterials.LOGGER.printf(Level.INFO, "Added %d new localizations in %dms.", lang.size() - startSize, System.currentTimeMillis() - time);
			return;
		}
		
        try {
            Files.createDirectories(langFile.getParent());
        } catch (IOException e) {
            ModularMaterials.LOGGER.error("Failed to create lang directory", e);
            return;
        }
        
        Map<String, String> lang = new LinkedHashMap<>();
        
        for(MetaItem item : MM_Reference.ITEMS) {
            lang.put("item." + MM_Reference.MODID + "." + item.registryName.getPath(), item.getLocalizedName());
        }
        
        for(MetaBucketItem item : MM_Reference.BUCKETS) {
            lang.put("item." + MM_Reference.MODID + "." + item.registryName.getPath(), item.getLocalizedName());
        }
        
        for(MetaFluid fluid : MM_Reference.FLUIDS) {
            lang.put("fluid." + MM_Reference.MODID + "." + fluid.registryName.getPath(), fluid.getLocalizedName());
            lang.put("fluid_type." + MM_Reference.MODID + "." + fluid.registryName.getPath(), fluid.getLocalizedName());
        }
        
        for(MetaBlock block : MM_Reference.BLOCKS) {
            lang.put("block." + MM_Reference.MODID + "." + block.registryName.getPath(), block.getLocalizedName());
        }
        
        for(IMetaOre ore : MM_Reference.ORES.values()) {
            lang.put("block." + MM_Reference.MODID + "." + ore.getRegistryName().getPath(), ore.getLocalizedName());
        }
        
        try {
            Files.writeString(langFile, new GsonBuilder().setPrettyPrinting().create().toJson(lang, type));
        } catch (IOException e) {
            ModularMaterials.LOGGER.error("Failed to create lang file", e);
            return;
        }
        
        ModularMaterials.LOGGER.printf(Level.INFO, "Created %d localizations in %dms.", lang.size(), System.currentTimeMillis() - time);
	}

}
