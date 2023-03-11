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
import glowredman.modularmaterials.data.object.sub.ChemicalState;
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
		long time = System.currentTimeMillis();
		int count = 0;
		
		if(MM_Reference.CONFIG.overrideModelFiles) {
			FileHelper.cleanDir(new File(ResourceLoader.RESOURCES_DIR, "assets/" + MM_Reference.MODID + "/models"));
		}
		
		File models_item = new File(ResourceLoader.RESOURCES_DIR, "assets/" + MM_Reference.MODID + "/models/item");
		models_item.mkdirs();
		
		for(MetaItem item : MM_Reference.ITEMS) {
			File modelFile = new File(models_item, item.registryName.getPath() + ".json");
			if(!modelFile.exists()) {
				FileHelper.write(modelFile, Templates.MODEL_ITEM.format(item.material.texture, item.getTypeIdentifier()));
				count++;
			}
		}
		
		for(MetaBucketItem item : MM_Reference.BUCKETS) {
			File modelFile = new File(models_item, item.registryName.getPath() + ".json");
			if(!modelFile.exists()) {
				if(item.fluid().material.state == ChemicalState.SOLID && item.fluid().type.state == ChemicalState.LIQUID) {
					FileHelper.write(modelFile, Templates.MODEL_BUCKET_DRIP.format(item.fluid().registryName));
				} else {
					FileHelper.write(modelFile, Templates.MODEL_BUCKET.format(item.fluid().registryName));
				}
				count++;
			}
		}
		
		for(MetaBlock block : MM_Reference.BLOCKS) {
			String type = block.getTypeIdentifier();
			File modelFileInventory = new File(models_item, block.registryName.getPath() + ".json");
			File modelFileNormal = new File(ResourceLoader.RESOURCES_DIR, "assets/" + MM_Reference.MODID + "/models/block/" + block.material.texture + "/" + type + ".json");
			if(!modelFileInventory.exists()) {
				FileHelper.write(modelFileInventory, Templates.MODEL_BLOCK.format(block.material.texture, type));
				count++;
			}
			modelFileNormal.getParentFile().mkdirs();
			if(!modelFileNormal.exists()) {
				FileHelper.write(modelFileNormal, Templates.MODEL_BLOCK.format(block.material.texture, type));
				count++;
			}
		}
		
		for(IMetaOre ore : MM_Reference.ORES.values()) {
			String texture = ore.getMaterial().texture;
			String baseTexture = ore.getVariant().baseTexture;
			File modelFileInventory = new File(models_item, ore.getRegistryName().getPath() + ".json");
			File modelFileNormal = new File(ResourceLoader.RESOURCES_DIR, "assets/" + MM_Reference.MODID + "/models/block/" + texture + "/ore/" + ore.getVariantIdentifier() + ".json");
			if(!modelFileInventory.exists()) {
				FileHelper.write(modelFileInventory, Templates.MODEL_ORE.format(texture, baseTexture));
				count++;
			}
			modelFileNormal.getParentFile().mkdirs();
			if(!modelFileNormal.exists()) {
				FileHelper.write(modelFileNormal, Templates.MODEL_ORE.format(texture, baseTexture));
				count++;
			}
		}
		
		ModularMaterials.LOGGER.info("Created {} model-files in {}ms.", count, System.currentTimeMillis() - time);
	}
	
	private static void generateBlockstateFiles() {
		long time = System.currentTimeMillis();
		int count = 0;
		
		if(MM_Reference.CONFIG.overrideBlockstateFiles) {
			FileHelper.cleanDir(new File(ResourceLoader.RESOURCES_DIR, "assets/" + MM_Reference.MODID + "/blockstates"));
		}
		
		File blockstates = new File(ResourceLoader.RESOURCES_DIR, "assets/" + MM_Reference.MODID + "/blockstates");
		blockstates.mkdirs();
		
		for(MetaBlock block : MM_Reference.BLOCKS) {
			File blockstateFile = new File(blockstates, block.registryName.getPath() + ".json");
			if(!blockstateFile.exists()) {
				FileHelper.write(blockstateFile, Templates.BLOCKSTATE_BLOCK.format(block.material.texture, block.getTypeIdentifier()));
				count++;
			}
		}
		
		for(IMetaOre ore : MM_Reference.ORES.values()) {
			File blockstateFile = new File(blockstates, ore.getRegistryName().getPath() + ".json");
			if(!blockstateFile.exists()) {
				FileHelper.write(blockstateFile, Templates.BLOCKSTATE_ORE.format(ore.getMaterial().texture, ore.getVariantIdentifier()));
				count++;
			}
		}
		
		ModularMaterials.LOGGER.info("Created {} blockstate-files in {}ms.", count, System.currentTimeMillis() - time);
	}
	
	private static void generateLangFile() {
		long time = System.currentTimeMillis();
		
		File langFile = new File(ResourceLoader.RESOURCES_DIR, "assets/" + MM_Reference.MODID + "/lang/en_us.json");
		
		Type type = new TypeToken<Map<String, String>>() {
			private static final long serialVersionUID = 5878402630771915782L;}.getType();
		
		if(langFile.exists()) {
			
			try {
				Map<String, String> lang = JSONHandler.GSON.fromJson(FileHelper.readFile(langFile.toPath()), type);
				int startSize = lang.size();
				
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
					String key = "fluid." + MM_Reference.MODID + "." + fluid.registryName.getPath();
					if(!lang.containsKey(key)) {
						lang.put(key, fluid.getLocalizedName());
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
				
				langFile.delete();
				FileHelper.write(langFile, JSONHandler.GSON.toJson(lang, type));
				
				ModularMaterials.info(String.format("Added %d new localizations in %dms.", lang.size() - startSize, System.currentTimeMillis() - time));
				
			} catch (JsonIOException | JsonSyntaxException | IOException e) {
				e.printStackTrace();
			}
			
		} else {
			langFile.getParentFile().mkdirs();
			
			try {
				Map<String, String> lang = new LinkedHashMap<>();
				
				for(MetaItem item : MM_Reference.ITEMS) {
					lang.put("item." + MM_Reference.MODID + "." + item.registryName.getPath(), item.getLocalizedName());
				}
				
				for(MetaBucketItem item : MM_Reference.BUCKETS) {
					lang.put("item." + MM_Reference.MODID + "." + item.registryName.getPath(), item.getLocalizedName());
				}
				
				for(MetaFluid fluid : MM_Reference.FLUIDS) {
					lang.put("fluid." + MM_Reference.MODID + "." + fluid.registryName.getPath(), fluid.getLocalizedName());
				}
				
				for(MetaBlock block : MM_Reference.BLOCKS) {
					lang.put("block." + MM_Reference.MODID + "." + block.registryName.getPath(), block.getLocalizedName());
				}
				
				for(IMetaOre ore : MM_Reference.ORES.values()) {
					lang.put("block." + MM_Reference.MODID + "." + ore.getRegistryName().getPath(), ore.getLocalizedName());
				}
				
				FileHelper.write(langFile, new GsonBuilder().setPrettyPrinting().create().toJson(lang, type));
				
				ModularMaterials.info(String.format("Created %d localizations in %dms.", lang.size(), System.currentTimeMillis() - time));
				
			} catch (JsonIOException | JsonSyntaxException e) {
				e.printStackTrace();
			}
		}
	}

}
