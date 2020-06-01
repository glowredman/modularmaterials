package glowredman.modularmaterials.file;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import glowredman.modularmaterials.Main;
import static glowredman.modularmaterials.Reference.*;
import glowredman.modularmaterials.object.Material;
import glowredman.modularmaterials.object.MaterialList;
import glowredman.modularmaterials.object.OreVariant;
import glowredman.modularmaterials.object.OreVariantList;
import glowredman.modularmaterials.object.OreVein;
import glowredman.modularmaterials.object.OreVeinList;
import glowredman.modularmaterials.object.Type;
import glowredman.modularmaterials.object.TypeList;
import glowredman.modularmaterials.util.MaterialHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class JSONHandler {
	
	public static void initOreVariantsFile(FMLPreInitializationEvent event) {
		try {
			File fileOreVariants = new File(event.getModConfigurationDirectory().getPath() + '/' + MODID, CONFIGNAME_OREVARIANTS);
			OreVariantList oreVariantList = new OreVariantList();

			//check if the file already exists, if not create it
			if(!fileOreVariants.exists()) {
				long time = System.currentTimeMillis();
				//create an example to put in the file
				OreVariant example = new OreVariant();
				example.setBaseBlock("minecraft:stone");
				example.setBaseTexture("minecraft:blocks/stone");
				example.setEffectiveTool("pickaxe");
				example.setEnabled(true);
				example.setMaterialSound("ROCK");
				example.setObeysGravity(false);
				example.setOreDictPrefix("");
				example.setSyntax("%s Ore");
				oreVariantList.oreTypes.put("stone", example);
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileOreVariants));
				writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(oreVariantList));
				writer.close();
				
				Main.logger.info("Succesfully created \"" + fileOreVariants + "\" in " + (System.currentTimeMillis() - time) + "ms.");
			}

			//transfer config information
			oreVariants = new Gson().fromJson(readFile(fileOreVariants.getPath(), StandardCharsets.UTF_8), OreVariantList.class).getOreVariants();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void initTypeFile(FMLPreInitializationEvent event) {
		try {
			
			File fileTypes = new File(event.getModConfigurationDirectory().getPath() + '/' + MODID, CONFIGNAME_TYPES);
			TypeList typeList = new TypeList();

			//check if the file already exists, if not create it
			if(!fileTypes.exists()) {
				long time = System.currentTimeMillis();
				Type example = new Type();
				//create an example to put in the file
				example.setEnabled(true);
				example.setCategory("item");
				example.setOreDictPrefix("example");
				example.setSyntax("%s Thingy");
				example.setUnitValue("1u");
				typeList.types.put("example", example);
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileTypes));
				writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(typeList));
				writer.close();
				
				Main.logger.info("Succesfully created \"" + fileTypes + "\" in " + (System.currentTimeMillis() - time) + "ms.");
			}

			//transfer config information
			types = new Gson().fromJson(readFile(fileTypes.getPath(), StandardCharsets.UTF_8), TypeList.class).getTypes();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void initMaterialFile(FMLPreInitializationEvent event) {
		try {
			
			File fileMaterials = new File(event.getModConfigurationDirectory().getPath() + '/' + MODID, CONFIGNAME_MATERIALS);
			MaterialList materialList = new MaterialList();

			//check if the file already exists, if not create it
			if(!fileMaterials.exists()) {
				long time = System.currentTimeMillis();
				Material example = new Material();
				//create an example to put in the file
				example.setBeaconBase(true);
				example.setBeaconPayment(true);
				example.setBlockHardness((float) (Math.random() * Float.MAX_VALUE));
				example.setBlockHarvestLevel((int) (Math.random() * 3));
				example.setBlockLightLevel((int) (Math.random() * 25));
				example.setBlockResistance((float) (Math.random() * Float.MAX_VALUE));
				example.setBoilingTemperature((int) (Math.random() * Integer.MAX_VALUE - 2 * Math.random() * Integer.MAX_VALUE));
				example.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random(), (float) Math.random()));
				example.setEnabled(true);
				example.setEnabledTypes(MaterialHandler.getAllTypesEqualHashMap(true));
				example.setGasDensity((int) (Math.random() * Integer.MAX_VALUE - 2 * Math.random() * Integer.MAX_VALUE));
				example.setGasLightLevel((int) (Math.random() * 15));
				example.setGasViscosity((int) (Math.random() * Integer.MAX_VALUE));
				example.setLiquidDensity((int) (Math.random() * Integer.MAX_VALUE - 2 * Math.random() * Integer.MAX_VALUE));
				example.setLiquidLightLevel((int) (Math.random() * 15));
				example.setLiquidViscosity((int) (Math.random() * Integer.MAX_VALUE));
				example.setMeltingTemperature((int) (Math.random() * Integer.MAX_VALUE - 2 * Math.random() * Integer.MAX_VALUE));
				example.setMeta((short) 0);
				example.setName("Example Material");
				example.setOreDict(new String[] {"Example", "ExampleButDifferent"});
				example.setOreHardness((float) (Math.random() * Float.MAX_VALUE));
				example.setOreHarvestLevel((int) (Math.random() * 3));
				example.setOreHarvestLevel((int) (Math.random() * 3));
				example.setOreLightLevel((int) (Math.random() * 15));
				example.setOreResistance((float) (Math.random() * Float.MAX_VALUE));
				example.setState("solid");
				example.setTemperature((int) (Math.random() * Integer.MAX_VALUE - 2 * Math.random() * Integer.MAX_VALUE));
				example.setTexture("example_texture");
				example.setTooltip(new String[] {"§0Black §1Dark Blue §2Dark Green §3Dark Aqua", "§4Dark Red §5Dark Purple §6Gold §7Gray", "§8Dark Gray §9Blue §aGreen §bAqua", "§cRed §dLight Purple §eYellow §fWhite", "§kObfuscated", "§lBold", "§mStrikethrough", "§nUnderline", "§oItalic", "§PPress SHIFT", "§pRelease SHIFT", "§QPress CTRL", "§qRelease CTRL", "§TPress ALT", "§tRelease ALT", "§s1:50:§0,§1,§2,§3,§4,§5,§6,§7,§8,§9,§a,§b,§c,§d,§e,§f:Animated!§s", "§s1:50:§r§n,§r,§r,§r,§r,§r,§r,§r,§r,§r,§r,§r,§r,§r,§r,§r:This also works!§s"});
				
				materialList.materials.put("example", example);
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileMaterials));
				writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(materialList));
				writer.close();
				
				Main.logger.info("Succesfully created \"" + fileMaterials + "\" in " + (System.currentTimeMillis() - time) + "ms.");
			}
			
			//transfer config information
			materials = new Gson().fromJson(readFile(fileMaterials.getPath(), StandardCharsets.UTF_8), MaterialList.class).getMaterials();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void initOreGenerationFile(FMLPreInitializationEvent event) {
		try {
			File fileOreGeneration = new File(event.getModConfigurationDirectory().getPath() + '/' + MODID, CONFIGNAME_OREGENERATION);
			OreVeinList oreVeinList = new OreVeinList();
			OreVein example = new OreVein();

			//check if the file already exists, if not create it
			if(!fileOreGeneration.exists()) {
				long time = System.currentTimeMillis();
				//create an example to put in the file
				example.setBiomes(Arrays.asList());
				example.setDenisty(2);
				example.setDimensions(Arrays.asList("overworld"));
				example.setEnabled(true);
				example.setInbewteen("platinum");
				example.setInvertBiomes(true);
				example.setInvertDimensions(false);
				example.setMaxHeight(250);
				example.setMinHeight(5);
				example.setPrimary("gold");
				example.setSecondary("iridium");
				example.setSize(32);
				example.setSporadic("osmium");
				example.setWeight(100);
				
				oreVeinList.oreGeneration.put("example", example);
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileOreGeneration));
				writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(oreVeinList));
				writer.close();

				Main.logger.info("Succesfully created \"" + fileOreGeneration + "\" in " + (System.currentTimeMillis() - time) + "ms.");
			}

			//transfer config information
			oreVeins = new Gson().fromJson(readFile(fileOreGeneration.getPath(), StandardCharsets.UTF_8), OreVeinList.class).getOreGeneration();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String readFile(String path, Charset encoding) throws IOException, OutOfMemoryError, SecurityException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
