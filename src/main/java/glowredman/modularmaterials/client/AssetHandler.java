package glowredman.modularmaterials.client;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.block.IMetaOre;
import glowredman.modularmaterials.block.MetaBlock;
import glowredman.modularmaterials.data.JSONHandler;
import glowredman.modularmaterials.data.ResourceLoader;
import glowredman.modularmaterials.data.Templates;
import glowredman.modularmaterials.fluid.MetaFluid;
import glowredman.modularmaterials.item.MetaBucketItem;
import glowredman.modularmaterials.item.MetaItem;
import glowredman.modularmaterials.util.FileHelper;

public class AssetHandler {
	
	static void execute() {
		generateModelFiles();
		generateBlockstateFiles();
		generateLangFile();
	}
	
	private static void generateModelFiles() {
		ModularMaterials.info("Generating model files...");
		long time = System.currentTimeMillis();
		int count = 0;
		
		if(MM_Reference.CONFIG.overrideModelFiles) {
			FileHelper.cleanDir(new File(ResourceLoader.RESOURCES_DIR, "assets/" + MM_Reference.MODID + "/models"));
		}
		
		File models_item = new File(ResourceLoader.RESOURCES_DIR, "assets/" + MM_Reference.MODID + "/models/item");
		models_item.mkdirs();
		
		for(MetaItem item : MM_Reference.ITEMS) {
			File modelFile = new File(models_item, item.getRegistryName().getPath() + ".json");
			try {
				if(!modelFile.exists()) {
					FileHelper.write(modelFile, Templates.MODEL_ITEM.format(item.material.texture, item.getTypeIdentifier()));
					count++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for(MetaBucketItem item : MM_Reference.BUCKETS) {
			File modelFile = new File(models_item, item.getRegistryName().getPath() + ".json");
			try {
				if(!modelFile.exists()) {
					if(item.getFluid().getAttributes().isLighterThanAir() || item.fluid().material.state == item.fluid().type.state) {
						FileHelper.write(modelFile, Templates.MODEL_BUCKET.format(item.getFluid().getRegistryName()));
					} else {
						FileHelper.write(modelFile, Templates.MODEL_BUCKET_DRIP.format(item.getFluid().getRegistryName()));
					}
					count++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for(MetaBlock block : MM_Reference.BLOCKS) {
			String type = block.getTypeIdentifier();
			File modelFileInventory = new File(models_item, block.getRegistryName().getPath() + ".json");
			File modelFileNormal = new File(ResourceLoader.RESOURCES_DIR, "assets/" + MM_Reference.MODID + "/models/block/" + block.material.texture + "/" + type + ".json");
			try {
				if(!modelFileInventory.exists()) {
					FileHelper.write(modelFileInventory, Templates.MODEL_BLOCK.format(block.material.texture, type));
					count++;
				}
				modelFileNormal.getParentFile().mkdirs();
				if(!modelFileNormal.exists()) {
					FileHelper.write(modelFileNormal, Templates.MODEL_BLOCK.format(block.material.texture, type));
					count++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for(IMetaOre ore : MM_Reference.ORES.values()) {
			String texture = ore.getMaterial().texture;
			String baseTexture = ore.getVariant().baseTexture;
			File modelFileInventory = new File(models_item, ore.getBlock().getRegistryName().getPath() + ".json");
			File modelFileNormal = new File(ResourceLoader.RESOURCES_DIR, "assets/" + MM_Reference.MODID + "/models/block/" + texture + "/ore/" + ore.getVariantIdentifier() + ".json");
			try {
				if(!modelFileInventory.exists()) {
					FileHelper.write(modelFileInventory, Templates.MODEL_ORE.format(texture, baseTexture));
					count++;
				}
				modelFileNormal.getParentFile().mkdirs();
				if(!modelFileNormal.exists()) {
					FileHelper.write(modelFileNormal, Templates.MODEL_ORE.format(texture, baseTexture));
					count++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		ModularMaterials.info("Done! Created " + count + " model-files in " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	private static void generateBlockstateFiles() {
		ModularMaterials.info("Generating model files...");
		long time = System.currentTimeMillis();
		int count = 0;
		
		if(MM_Reference.CONFIG.overrideBlockstateFiles) {
			FileHelper.cleanDir(new File(ResourceLoader.RESOURCES_DIR, "assets/" + MM_Reference.MODID + "/blockstates"));
		}
		
		File blockstates = new File(ResourceLoader.RESOURCES_DIR, "assets/" + MM_Reference.MODID + "/blockstates");
		blockstates.mkdirs();
		
		for(MetaBlock block : MM_Reference.BLOCKS) {
			File blockstateFile = new File(blockstates, block.getRegistryName().getPath() + ".json");
			try {
				if(!blockstateFile.exists()) {
					FileHelper.write(blockstateFile, Templates.BLOCKSTATE_BLOCK.format(block.material.texture, block.getTypeIdentifier()));
					count++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for(IMetaOre ore : MM_Reference.ORES.values()) {
			File blockstateFile = new File(blockstates, ore.getBlock().getRegistryName().getPath() + ".json");
			try {
				if(!blockstateFile.exists()) {
					FileHelper.write(blockstateFile, Templates.BLOCKSTATE_ORE.format(ore.getMaterial().texture, ore.getVariantIdentifier()));
					count++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		ModularMaterials.info("Done! Created " + count + " model-files in " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	private static void generateLangFile() {
		ModularMaterials.info("Generating lang file...");
		long time = System.currentTimeMillis();
		
		File langFile = new File(ResourceLoader.RESOURCES_DIR, "assets/" + MM_Reference.MODID + "/lang/en_us.json");
		
		Type type = new TypeToken<Map<String, String>>() {
			private static final long serialVersionUID = 5878402630771915782L;}.getType();
		
		if(langFile.exists()) {
			
			try {
				Map<String, String> lang = JSONHandler.GSON.fromJson(FileHelper.readFile(langFile.toPath()), type);
				int startSize = lang.size();
				
				for(MetaItem item : MM_Reference.ITEMS) {
					String key = "item." + MM_Reference.MODID + "." + item.getRegistryName().getPath();
					if(!lang.containsKey(key)) {
						lang.put(key, item.getLocalizedName());
					}
				}
				
				for(MetaBucketItem item : MM_Reference.BUCKETS) {
					String key = "item." + MM_Reference.MODID + "." + item.getRegistryName().getPath();
					if(!lang.containsKey(key)) {
						lang.put(key, item.getLocalizedName());
					}
				}
				
				for(MetaFluid fluid : MM_Reference.FLUIDS) {
					String key = "fluid." + MM_Reference.MODID + "." + fluid.getRegistryName().getPath();
					if(!lang.containsKey(key)) {
						lang.put(key, fluid.getLocalizedName());
					}
				}
				
				for(MetaBlock block : MM_Reference.BLOCKS) {
					String key = "block." + MM_Reference.MODID + "." + block.getRegistryName().getPath();
					if(!lang.containsKey(key)) {
						lang.put(key, block.getLocalizedName());
					}
				}
				
				for(IMetaOre ore : MM_Reference.ORES.values()) {
					String key = "block." + MM_Reference.MODID + "." + ore.getBlock().getRegistryName().getPath();
					if(!lang.containsKey(key)) {
						lang.put(key, ore.getLocalizedName());
					}
				}
				
				langFile.delete();
				FileHelper.write(langFile, JSONHandler.GSON.toJson(lang, type));
				
				ModularMaterials.info(String.format("Done! Added %d new entries in %dms.", lang.size() - startSize, System.currentTimeMillis() - time));
				
			} catch (JsonIOException | JsonSyntaxException | IOException e) {
				e.printStackTrace();
			}
			
		} else {
			langFile.getParentFile().mkdirs();
			
			try {
				Map<String, String> lang = new LinkedHashMap<>();
				
				for(MetaItem item : MM_Reference.ITEMS) {
					lang.put("item." + MM_Reference.MODID + "." + item.getRegistryName().getPath(), item.getLocalizedName());
				}
				
				FileHelper.write(langFile, new GsonBuilder().setPrettyPrinting().create().toJson(lang, type));
				
				ModularMaterials.info(String.format("Done! Created %d entries in %dms.", lang.size(), System.currentTimeMillis() - time));
				
			} catch (JsonIOException | JsonSyntaxException | IOException e) {
				e.printStackTrace();
			}
		}
	}

}
