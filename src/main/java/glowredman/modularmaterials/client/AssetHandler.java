package glowredman.modularmaterials.client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.data.JSONHandler;
import glowredman.modularmaterials.data.ResourceLoader;
import glowredman.modularmaterials.data.Templates;
import glowredman.modularmaterials.item.MetaItem;

public class AssetHandler {
	
	static void execute() {
		generateModelFiles();
		generateLangFile();
	}
	
	private static void generateModelFiles() {
		ModularMaterials.info("Generating model files...");
		long time = System.currentTimeMillis();
		int count = 0;
		
		for(MetaItem item : MM_Reference.ITEMS) {
			File modelFile = new File(ResourceLoader.RESOURCES_DIR, "assets/" + MM_Reference.MODID + "/models/item/" + item.getRegistryName().getPath() + ".json");
			try {
				modelFile.getParentFile().mkdirs();
				if(!modelFile.exists() || MM_Reference.overrideModelFiles) {
					if(modelFile.exists()) {
						modelFile.delete();
					}
					BufferedWriter w = new BufferedWriter(new FileWriter(modelFile, StandardCharsets.UTF_8));
					w.write(Templates.MODEL_ITEM.format(item.material.texture, item.getTypeIdentifier()));
					w.close();
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
				Map<String, String> lang = JSONHandler.GSON.fromJson(new FileReader(langFile, StandardCharsets.UTF_8), type);
				int startSize = lang.size();
				
				for(MetaItem item : MM_Reference.ITEMS) {
					String key = "item." + MM_Reference.MODID + "." + item.getRegistryName().getPath();
					if(!lang.containsKey(key)) {
						lang.put(key, item.getLocalizedName());
					}
				}
				
				langFile.delete();
				
				BufferedWriter w = new BufferedWriter(new FileWriter(langFile, StandardCharsets.UTF_8));
				w.write(JSONHandler.GSON.toJson(lang, type));
				w.close();
				
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
				
				BufferedWriter w = new BufferedWriter(new FileWriter(langFile, StandardCharsets.UTF_8));
				w.write(new GsonBuilder().setPrettyPrinting().create().toJson(lang, type));
				w.close();
				
				ModularMaterials.info(String.format("Done! Created %d entries in %dms.", lang.size(), System.currentTimeMillis() - time));
				
			} catch (JsonIOException | JsonSyntaxException | IOException e) {
				e.printStackTrace();
			}
		}
	}

}
