package glowredman.modularmaterials.data.legacy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.reflect.TypeToken;
import com.google.gson.JsonSyntaxException;

import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.data.JSONHandler;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_OreVariant;
import glowredman.modularmaterials.data.object.MM_OreVein;
import glowredman.modularmaterials.data.object.MM_Type;
import glowredman.modularmaterials.data.object.sub.Category;
import glowredman.modularmaterials.data.object.sub.ChemicalState;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class LegacyHandler {

	public static final Path LEGACY_DIR = JSONHandler.CONFIG_DIR.resolve("legacy");
	
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
				css.sendSuccess(Component.literal(baseBlock).withStyle(ChatFormatting.WHITE)
						.append(Component.literal(" has a meta-value. This information will be lost in the conversion.").withStyle(ChatFormatting.GOLD)), false);
				return "minecraft:" + split[0];
			} catch (Exception e) {
				return split[0] + ":" + split[1];
			}
		}
		css.sendSuccess(Component.literal(baseBlock).withStyle(ChatFormatting.WHITE)
				.append(Component.literal(" has a meta-value. This information will be lost in the conversion.").withStyle(ChatFormatting.GOLD)), false);
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
		
		//read files
		MaterialList materialList = getList(css, LEGACY_DIR.resolve("materials.json"), MaterialList.class);
		OreVariantList oreVariantList = getList(css, LEGACY_DIR.resolve("oreGeneration.json"), OreVariantList.class);
		OreVeinList oreVeinList = getList(css, LEGACY_DIR.resolve("oreVariants.json"), OreVeinList.class);
		TypeList typeList = getList(css, LEGACY_DIR.resolve("types.json"), TypeList.class);
		
		//exit early if no files are present
		if(materialList == null && oreVariantList == null && oreVeinList == null && typeList == null) {
			css.sendSuccess(Component.literal("Finished without any changes.").withStyle(ChatFormatting.BLUE), false);
			return 0;
		}
		
		//write new files
		if(oreVariantList != null) {
			Map<String, MM_OreVariant> variants = new LinkedHashMap<>();
			for(Entry<String, OreVariant> e : oreVariantList.oreVariants.entrySet()) {
				variants.put(e.getKey(), e.getValue().convert(e.getKey(), css));
			}
			write("orevariants.json", variants, css);
		}
		if(oreVeinList != null) {
			Map<String, MM_OreVein> veins = new LinkedHashMap<>();
			for(Entry<String, OreVein> e : oreVeinList.oreGeneration.entrySet()) {
				veins.put(e.getKey(), e.getValue().convert(e.getKey()));
			}
			write("oreveins.json", veins, css);
		}
		if(typeList != null) {
			Map<String, MM_Type> types = new LinkedHashMap<>();
			for(Entry<String, Type> e : typeList.types.entrySet()) {
				types.put(e.getKey(), e.getValue().convert());
			}
			write("types.json", types, css);
		}
		if(materialList != null) {
			Map<String, MM_Material> materials = new LinkedHashMap<>();
			for(Entry<String, Material> e : materialList.materials.entrySet()) {
				materials.put(e.getKey(), e.getValue().convert(typeList == null ? new TypeList() : typeList));
			}
			write("materials.json", materials, css);
		}
		
		css.sendSuccess(Component.literal("Done! Took " + (System.currentTimeMillis() - time) + "ms.").withStyle(ChatFormatting.GREEN), false);
		
		return 0;
	}
	
	private static <T> T getList(CommandSourceStack css, Path file, Class<T> clazz) {
        if(Files.exists(file)) {
            try {
                 return read(file, clazz);
            } catch (Exception e) {
                errorFileReadMsg(css, e);
                ModularMaterials.LOGGER.warn("Failed to read file", e);
            }
        } else {
            missingFileMsg(css, file);
        }
        return null;
	}
	
	private static void missingFileMsg(CommandSourceStack css, Path file) {
		css.sendSuccess(Component.literal("Could not find ").withStyle(ChatFormatting.GOLD)
				.append(Component.literal(file.toString()).withStyle(ChatFormatting.WHITE))
				.append(Component.literal(", skipping.").withStyle(ChatFormatting.GOLD)), false);
	}
	
	private static void errorFileReadMsg(CommandSourceStack css, Exception e) {
		css.sendSuccess(Component.literal(e.toString()).withStyle(ChatFormatting.RED), false);
	}
	
	private static <T> T read(Path file, Class<T> clazz) throws JsonSyntaxException, IOException {
		return JSONHandler.GSON.fromJson(Files.readString(file), clazz);
	}
	
	private static <K, V> void write(String file, Map<K, V> values, CommandSourceStack css) {
		Path path = JSONHandler.CONFIG_DIR.resolve(file);
		try {
            Files.deleteIfExists(path);
            Files.writeString(path, JSONHandler.GSON.toJson(values, new TypeToken<Map<K, V>>() {
                private static final long serialVersionUID = 6306948798714244240L;}.getType()));
        } catch (IOException e) {
            ModularMaterials.LOGGER.warn("Failed to write " + path, e);
            css.sendFailure(Component.literal("Failed creating " + file).withStyle(ChatFormatting.RED));
        }
	}

}
