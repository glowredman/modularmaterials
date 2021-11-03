package glowredman.modularmaterials.data.legacy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.reflect.TypeToken;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import glowredman.modularmaterials.data.JSONHandler;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_OreVariant;
import glowredman.modularmaterials.data.object.MM_OreVein;
import glowredman.modularmaterials.data.object.MM_Type;
import glowredman.modularmaterials.data.object.sub.Category;
import glowredman.modularmaterials.data.object.sub.ChemicalState;
import glowredman.modularmaterials.util.FileHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TextComponent;

public class LegacyHandler {

	public static final File LEGACY_DIR = new File(JSONHandler.CONFIG_DIR, "legacy");
	
	public static ChemicalState state(String state) {
		if(state == null) return null;
		switch (state) {
		case "solid":
			return ChemicalState.SOLID;
		case "liquid":
			return ChemicalState.LIQUID;
		case "gaseous":
			return ChemicalState.GASEOUS;
		default:
			return ChemicalState.SOLID;
		}
	}
	
	public static Category category(String category) {
		if(category == null) return null;
		switch (category) {
		case "item":
			return Category.ITEM;
		case "block":
			return Category.BLOCK;
		case "fluid":
			return Category.FLUID;
		case "ore":
			return null;
		default:
			return Category.ITEM;
		}
	}
	
	public static List<String> enabledTypes(Map<String, Boolean> enabledTypes) {
		List<String> ret = new ArrayList<>();
		if(enabledTypes == null) return ret;
		for(Entry<String, Boolean> e : enabledTypes.entrySet()) {
			if(e.getValue()) {
				ret.add(e.getKey());
			}
		}
		return ret;
	}
	
	public static String harvestLevel(int level) {
		switch (level) {
		case 0:
			return "forge:needs_wood_tool";
		case 1:
			return "minecraft:needs_stone_tool";
		case 2:
			return "minecraft:needs_iron_tool";
		case 3:
			return "minecraft:needs_diamond_tool";
		case 4:
			return "forge:needs_netherite_tool";
		default:
			return null;
		}
	}
	
	public static String baseBlock(String baseBlock, CommandSourceStack css) {
		if(baseBlock == null) return null;
		String[] split = baseBlock.split(":", 3);
		if(split.length == 1) {
			return "minecraft:" + split[0];
		}
		if(split.length == 2) {
			try {
				Integer.parseInt(split[1]);
				css.sendSuccess(new TextComponent(baseBlock).withStyle(ChatFormatting.WHITE)
						.append(new TextComponent(" has a meta-value. This information will be lost in the conversion.").withStyle(ChatFormatting.GOLD)), false);
				return "minecraft:" + split[0];
			} catch (Exception e) {
				return split[0] + ":" + split[1];
			}
		}
		css.sendSuccess(new TextComponent(baseBlock).withStyle(ChatFormatting.WHITE)
				.append(new TextComponent(" has a meta-value. This information will be lost in the conversion.").withStyle(ChatFormatting.GOLD)), false);
		return split[0] + ":" + split[1];
	}
	
	public static String[] lowerCase(String[] s) {
		if(s == null) return new String[0];
		String[] ret = new String[s.length];
		for(int i = 0; i < s.length; i++) {
			ret[i] = s[i].toLowerCase();
		}
		return ret;
	}
	
	public static int convert(CommandSourceStack css) {
		long time = System.currentTimeMillis();
		
		File materialsL = new File(LEGACY_DIR, "materials.json");
		File oreGenerationL = new File(LEGACY_DIR, "oreGeneration.json");
		File oreVariantsL = new File(LEGACY_DIR, "oreVariants.json");
		File typesL = new File(LEGACY_DIR, "types.json");
		
		boolean foundMaterials = false;
		boolean foundOreVariants = false;
		boolean foundOreVeins = false;
		boolean foundTypes = false;
		
		MaterialList materialList = new MaterialList();
		OreVariantList oreVariantList = new OreVariantList();
		OreVeinList oreVeinList = new OreVeinList();
		TypeList typeList = new TypeList();
		
		
		//read files
		if(materialsL.exists()) {
			try {
				materialList = read(materialsL, MaterialList.class);
				foundMaterials = true;
			} catch (Exception e) {
				errorFileReadMsg(css, e);
				e.printStackTrace();
			}
		} else {
			missingFileMsg(css, materialsL);
		}
		if(oreGenerationL.exists()) {
			try {
				oreVeinList = read(oreGenerationL, OreVeinList.class);
				foundOreVariants = true;
			} catch (Exception e) {
				errorFileReadMsg(css, e);
				e.printStackTrace();
			}
		} else {
			missingFileMsg(css, oreGenerationL);
		}
		if(oreVariantsL.exists()) {
			try {
				oreVariantList = read(oreVariantsL, OreVariantList.class);
				foundOreVeins = true;
			} catch (Exception e) {
				errorFileReadMsg(css, e);
				e.printStackTrace();
			}
		} else {
			missingFileMsg(css, oreVariantsL);
		}
		if(typesL.exists()) {
			try {
				typeList = read(typesL, TypeList.class);
				foundTypes = true;
			} catch (Exception e) {
				errorFileReadMsg(css, e);
				e.printStackTrace();
			}
		} else {
			missingFileMsg(css, typesL);
		}
		
		
		//exit early if no files are present
		if(!(foundMaterials || foundOreVariants || foundOreVeins || foundTypes)) {
			css.sendSuccess(new TextComponent("Finished without any changes.").withStyle(ChatFormatting.BLUE), false);
			return 0;
		}
		
		//write new files
		if(foundOreVariants) {
			Map<String, MM_OreVariant> variants = new LinkedHashMap<>();
			for(Entry<String, OreVariant> e : oreVariantList.oreVariants.entrySet()) {
				variants.put(e.getKey(), e.getValue().convert(e.getKey(), css));
			}
			write("orevariants.json", variants, css);
		}
		if(foundOreVeins) {
			Map<String, MM_OreVein> veins = new LinkedHashMap<>();
			for(Entry<String, OreVein> e : oreVeinList.oreGeneration.entrySet()) {
				veins.put(e.getKey(), e.getValue().convert(e.getKey()));
			}
			write("oreveins.json", veins, css);
		}
		if(foundTypes) {
			Map<String, MM_Type> types = new LinkedHashMap<>();
			for(Entry<String, Type> e : typeList.types.entrySet()) {
				types.put(e.getKey(), e.getValue().convert());
			}
			write("types.json", types, css);
		}
		if(foundMaterials) {
			Map<String, MM_Material> materials = new LinkedHashMap<>();
			for(Entry<String, Material> e : materialList.materials.entrySet()) {
				materials.put(e.getKey(), e.getValue().convert(typeList));
			}
			write("materials.json", materials, css);
		}
		
		css.sendSuccess(new TextComponent("Done! Took " + (System.currentTimeMillis() - time) + "ms.").withStyle(ChatFormatting.GREEN), false);
		
		return 0;
	}
	
	private static void missingFileMsg(CommandSourceStack css, File file) {
		css.sendSuccess(new TextComponent("Could not find ").withStyle(ChatFormatting.GOLD)
				.append(new TextComponent(file.getPath()).withStyle(ChatFormatting.WHITE))
				.append(new TextComponent(", skipping.").withStyle(ChatFormatting.GOLD)), false);
	}
	
	private static void errorFileReadMsg(CommandSourceStack css, Exception e) {
		css.sendSuccess(new TextComponent(e.toString()).withStyle(ChatFormatting.RED), false);
	}
	
	private static <T> T read(File file, Class<T> clazz) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		return JSONHandler.GSON.fromJson(new BufferedReader(new FileReader(file)), clazz);
	}
	
	private static <K, V> void write(String file, Map<K, V> values, CommandSourceStack css) {
		File f = new File(JSONHandler.CONFIG_DIR, file);
		if(f.exists()) f.delete();
		if(!FileHelper.write(f, JSONHandler.GSON.toJson(values, new TypeToken<Map<K, V>>() {
			private static final long serialVersionUID = 6306948798714244240L;}.getType()))) {
			css.sendFailure(new TextComponent("Failed creating " + file).withStyle(ChatFormatting.RED));
		}
	}

}
