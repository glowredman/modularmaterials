package glowredman.modularmaterials.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_Type;
import net.minecraftforge.fml.loading.FMLPaths;

public class JSONHandler {
	
	public static final String CONFIG_DIR = FMLPaths.CONFIGDIR.get().toString() + "/" + MM_Reference.MODID;
	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	
	public static Map<String, MM_Material> getMaterials() {
		long time = System.currentTimeMillis();
		Map<String, MM_Material> types = new LinkedHashMap<>();
		
		try {
			File file = new File(CONFIG_DIR, "materials.json");
			ModularMaterials.info("Parsing " + file.getPath() + " ...");
			
			MM_Material example = MM_Material.random();
			example.enabled = true;
			example.name = "Example";
			createExample(file, example);
			
			Type listType = new TypeToken<LinkedHashMap<String, MM_Material>>() {
				private static final long serialVersionUID = 3292456296675220350L;}.getType();
				
			types = GSON.fromJson(readFile(file.getPath()), listType);
			
			ModularMaterials.info("Done! Took " + (System.currentTimeMillis() - time) + "ms");
			
			ModularMaterials.debug(String.format("Types (%d):", types.size()));
			types.entrySet().forEach(e -> ModularMaterials.debug(e.getKey() + e.getValue().toString()));
		} catch (Exception e) {
			ModularMaterials.error("Parsing materials.json failed:");
			e.printStackTrace();
		}
		
		return types;
	}
	
	public static Map<String, MM_Type> getTypes() {
		long time = System.currentTimeMillis();
		Map<String, MM_Type> types = new LinkedHashMap<>();
		
		try {
			File file = new File(CONFIG_DIR, "types.json");
			ModularMaterials.info("Parsing " + file.getPath() + " ...");
			
			MM_Type example = MM_Type.random();
			example.enabled = true;
			example.nameSyntax = TagHandler.PARAM_MATERIAL + " Item";
			createExample(file, example);
			
			Type listType = new TypeToken<LinkedHashMap<String, MM_Type>>() {
				private static final long serialVersionUID = 3292456296675220350L;}.getType();
				
			types = GSON.fromJson(readFile(file.getPath()), listType);
			
			ModularMaterials.info("Done! Took " + (System.currentTimeMillis() - time) + "ms");
			
			ModularMaterials.debug(String.format("Types (%d):", types.size()));
			types.entrySet().forEach(e -> ModularMaterials.debug(e.getKey() + e.getValue().toString()));
		} catch (Exception e) {
			ModularMaterials.error("Parsing types.json failed:");
			e.printStackTrace();
		}
		
		return types;
	}
	
	private static <T> void createExample(File file, T example) throws IOException {
		if(!file.exists()) {
			file.getParentFile().mkdirs();
			Map<String, T> map = new LinkedHashMap<>();
			map.put("example", example);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8));
			writer.write(GSON.toJson(map));
			writer.close();
		}
	}
	
	private static String readFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, StandardCharsets.UTF_8);
	}

}