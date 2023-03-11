package glowredman.modularmaterials.data;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_OreVariant;
import glowredman.modularmaterials.data.object.MM_OreVein;
import glowredman.modularmaterials.data.object.MM_Type;
import net.minecraftforge.fml.loading.FMLPaths;

public class JSONHandler {
	
	public static final Path CONFIG_DIR = FMLPaths.CONFIGDIR.get().resolve(MM_Reference.MODID);
	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	
	public static Map<String, MM_Material> getMaterials() {
		long time = System.currentTimeMillis();
		Map<String, MM_Material> materials = new LinkedHashMap<>();
		
		try {
		    Path path = CONFIG_DIR.resolve("materials.json");
			
			MM_Material example = new MM_Material();
			example.enabled = true;
			example.name = "Example";
			createExample(path, example);
			
			Type listType = new TypeToken<LinkedHashMap<String, MM_Material>>() {
                private static final long serialVersionUID = 5500293462812712215L;}.getType();
				
			materials = GSON.fromJson(Files.readString(path), listType);
			
			ModularMaterials.LOGGER.info("Parsed materials.json in {}ms", System.currentTimeMillis() - time);
			ModularMaterials.LOGGER.printf(Level.DEBUG, "Materials (%d):", materials.size());
			materials.entrySet().forEach(ModularMaterials.LOGGER::debug);
		} catch (Exception e) {
			ModularMaterials.LOGGER.fatal("Failed to parse materials.json", e);
			e.printStackTrace();
		}
		
		return materials;
	}
	
	public static Map<String, MM_Type> getTypes() {
		long time = System.currentTimeMillis();
		Map<String, MM_Type> types = new LinkedHashMap<>();
		
		try {
            Path path = CONFIG_DIR.resolve("types.json");
			
			MM_Type example = new MM_Type();
			example.nameSyntax = TagHandler.PARAM_MATERIAL + " Item";
			createExample(path, example);
			
			Type listType = new TypeToken<LinkedHashMap<String, MM_Type>>() {
				private static final long serialVersionUID = -6168799421344577955L;}.getType();
				
			types = GSON.fromJson(Files.readString(path), listType);

            ModularMaterials.LOGGER.info("Parsed types.json in {}ms", System.currentTimeMillis() - time);
            ModularMaterials.LOGGER.printf(Level.DEBUG, "Materials (%d):", types.size());
            types.entrySet().forEach(ModularMaterials.LOGGER::debug);
        } catch (Exception e) {
            ModularMaterials.LOGGER.fatal("Failed to parse types.json", e);
            e.printStackTrace();
		}
		
		return types;
	}
	
	public static Map<String, MM_OreVariant> getOreVariants() {
		long time = System.currentTimeMillis();
		Map<String, MM_OreVariant> variants = new LinkedHashMap<>();
		
		try {
            Path path = CONFIG_DIR.resolve("orevariants.json");
			
			MM_OreVariant example = new MM_OreVariant();
			example.enabled = true;
			createExample(path, new MM_OreVariant());
			
			Type listType = new TypeToken<LinkedHashMap<String, MM_OreVariant>>() {
				private static final long serialVersionUID = 8305381304760332638L;}.getType();
				
			variants = GSON.fromJson(Files.readString(path), listType);

            ModularMaterials.LOGGER.info("Parsed orevariants.json in {}ms", System.currentTimeMillis() - time);
            ModularMaterials.LOGGER.printf(Level.DEBUG, "Materials (%d):", variants.size());
            variants.entrySet().forEach(ModularMaterials.LOGGER::debug);
        } catch (Exception e) {
            ModularMaterials.LOGGER.fatal("Failed to parse orevariants.json", e);
            e.printStackTrace();
		}
		
		return variants;
	}
	
	public static Map<String, MM_OreVein> getOreVeins() {
		long time = System.currentTimeMillis();
		Map<String, MM_OreVein> veins = new LinkedHashMap<>();
		
		try {
            Path path = CONFIG_DIR.resolve("oreveins.json");
			
			MM_OreVein example = new MM_OreVein();
			example.enabled = true;
			createExample(path, new MM_OreVein());
			
			Type listType = new TypeToken<LinkedHashMap<String, MM_OreVein>>() {
				private static final long serialVersionUID = -6019676943621850611L;}.getType();
				
			veins = GSON.fromJson(Files.readString(path), listType);

            ModularMaterials.LOGGER.info("Parsed oreveins.json in {}ms", System.currentTimeMillis() - time);
            ModularMaterials.LOGGER.printf(Level.DEBUG, "Materials (%d):", veins.size());
            veins.entrySet().forEach(ModularMaterials.LOGGER::debug);
        } catch (Exception e) {
            ModularMaterials.LOGGER.fatal("Failed to parse oreveins.json", e);
            e.printStackTrace();
		}
		
		return veins;
	}
	
	private static <T> void createExample(Path path, T example) {
		if(Files.exists(path)) return;
		
        try {
            Files.createDirectories(path.getParent());
            Map<String, T> map = new LinkedHashMap<>();
            map.put("example", example);
            Files.writeString(path, GSON.toJson(map));
        } catch (IOException e) {
            ModularMaterials.LOGGER.warn("Failed to create example file", e);
        }
	}

}
