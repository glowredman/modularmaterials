package glowredman.modularmaterials.file;

import static glowredman.modularmaterials.Main.logger;
import static glowredman.modularmaterials.Reference.*;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import glowredman.modularmaterials.object.JColor;
import glowredman.modularmaterials.object.JDrop;
import glowredman.modularmaterials.object.JMaterial;
import glowredman.modularmaterials.object.JMaterialList;
import glowredman.modularmaterials.object.JMiscBlock;
import glowredman.modularmaterials.object.JMiscFluid;
import glowredman.modularmaterials.object.JMiscItem;
import glowredman.modularmaterials.object.JMiscLists;
import glowredman.modularmaterials.object.JOreVariant;
import glowredman.modularmaterials.object.JOreVariantList;
import glowredman.modularmaterials.object.JOreVein;
import glowredman.modularmaterials.object.JOreVeinList;
import glowredman.modularmaterials.object.JTexture;
import glowredman.modularmaterials.object.JType;
import glowredman.modularmaterials.object.JTypeList;
import glowredman.modularmaterials.util.MaterialHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class JSONHandler {
	
	public static void initOreVariantsFile(FMLPreInitializationEvent event) {
		try {
			File fileOreVariants = new File(event.getModConfigurationDirectory().getPath() + '/' + MODID, CONFIGNAME_OREVARIANTS);
			JOreVariantList oreVariantList = new JOreVariantList();

			//check if the file already exists, if not create it
			if(!fileOreVariants.exists()) {
				long time = System.currentTimeMillis();
				//create an example to put in the file
				JOreVariant example = new JOreVariant();
				example.baseBlock = "minecraft:stone:0";
				example.baseTexture = "minecraft:blocks/stone";
				example.effectiveTool = "pickaxe";
				example.enabled = true;
				example.material = "ROCK";
				example.obeysGravity = false;
				example.oreDictPrefix = "";
				example.sound = "STONE";
				example.syntax = "%s Ore";
				oreVariantList.oreVariants.put("stone", example);
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileOreVariants));
				writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(oreVariantList));
				writer.close();
				
				logger.info("Succesfully created \"" + fileOreVariants + "\" in " + (System.currentTimeMillis() - time) + "ms.");
			}

			//transfer config information
			oreVariants = new Gson().fromJson(readFile(fileOreVariants.getPath()), JOreVariantList.class).oreVariants;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void initTypeFile(FMLPreInitializationEvent event) {
		try {
			
			File fileTypes = new File(event.getModConfigurationDirectory().getPath() + '/' + MODID, CONFIGNAME_TYPES);
			JTypeList typeList = new JTypeList();

			//check if the file already exists, if not create it
			if(!fileTypes.exists()) {
				long time = System.currentTimeMillis();
				JType example = new JType();
				//create an example to put in the file
				example.category = "item";
				example.effectiveTool = "pickaxe";
				example.enabled = true;
				example.hasTooltip = true;
				example.isBeaconBase = false;
				example.isBeaconPayment = true;
				example.oreDictPrefix = "example";
				example.state = "solid";
				example.syntax = "%s Thingy";
				example.unitValue = "1u";
				typeList.types.put("example", example);
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileTypes));
				writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(typeList));
				writer.close();
				
				logger.info("Succesfully created \"" + fileTypes + "\" in " + (System.currentTimeMillis() - time) + "ms.");
			}

			//transfer config information
			types = new Gson().fromJson(readFile(fileTypes.getPath()), JTypeList.class).types;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void initMaterialFile(FMLPreInitializationEvent event) {
		try {
			
			File fileMaterials = new File(event.getModConfigurationDirectory().getPath() + '/' + MODID, CONFIGNAME_MATERIALS);
			JMaterialList materialList = new JMaterialList();

			//check if the file already exists, if not create it
			if(!fileMaterials.exists()) {
				long time = System.currentTimeMillis();
				JMaterial example = new JMaterial();
				//create an example to put in the file
				example.blockHardness = (float) (Math.random() * Float.MAX_VALUE);
				example.blockHarvestLevel = (int) (Math.random() * 3);
				example.blockLightLevel = (byte) (Math.random() * 15);
				example.blockMaterial = "ROCK";
				example.blockResistance = (float) (Math.random() * Float.MAX_VALUE);
				example.blockSound = "STONE";
				example.boilingTemperature = (int) (Math.random() * Integer.MAX_VALUE - Math.random() * Integer.MAX_VALUE);
				example.color.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random(), (float) Math.random()));
				example.drops = new ArrayList<JDrop>();
				example.enabled = true;
				example.enabledTypes = MaterialHandler.getAllTypesEqualHashMap(true);
				example.gasDensity = (int) (Math.random() * Integer.MAX_VALUE - Math.random() * Integer.MAX_VALUE);
				example.gasLightLevel = (byte) (Math.random() * 15);
				example.gasViscosity = (int) (Math.random() * Integer.MAX_VALUE);
				example.isBeaconBase = false;
				example.isBeaconPayment = false;
				example.liquidDensity = (int) ((Math.random() - Math.random()) * Integer.MAX_VALUE);
				example.liquidLightLevel = (byte) (Math.random() * 15);
				example.liquidViscosity = (int) (Math.random() * Integer.MAX_VALUE);
				example.meltingTemperature = (int) ((Math.random() - Math.random()) * Integer.MAX_VALUE);
				example.meta = (short) (Math.random() * Short.MAX_VALUE);
				example.name = "Example Material";
				example.oreDict = new String[] {"Example", "ExampleButDifferent"};
				example.oreHardness = (float) (Math.random() * Float.MAX_VALUE);
				example.oreHarvestLevel = (int) (Math.random() * 3);
				example.oreLightLevel = (byte) (Math.random() * 15);
				example.oreResistance =  (float) (Math.random() * Float.MAX_VALUE);
				example.state = "solid";
				example.temperature = (int) (Math.random() * Integer.MAX_VALUE - Math.random() * Integer.MAX_VALUE);
				example.texture = "example_texture";
				example.tooltip = new String[] {"§0Black §1Dark Blue §2Dark Green §3Dark Aqua", "§4Dark Red §5Dark Purple §6Gold §7Gray", "§8Dark Gray §9Blue §aGreen §bAqua", "§cRed §dLight Purple §eYellow §fWhite", "§kObfuscated", "§lBold", "§mStrikethrough", "§nUnderline", "§oItalic", "§PPress SHIFT", "§pRelease SHIFT", "§QPress CTRL", "§qRelease CTRL", "§TPress ALT", "§tRelease ALT", "§s1:50:§0,§1,§2,§3,§4,§5,§6,§7,§8,§9,§a,§b,§c,§d,§e,§f:Animated!§s", "§s1:50:§r§n,§r,§r,§r,§r,§r,§r,§r,§r,§r,§r,§r,§r,§r,§r,§r:This also works!§s"};
				
				materialList.materials.put("example", example);
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileMaterials));
				writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(materialList));
				writer.close();
				
				logger.info("Succesfully created \"" + fileMaterials + "\" in " + (System.currentTimeMillis() - time) + "ms.");
			}
			
			//transfer config information
			materials = new Gson().fromJson(readFile(fileMaterials.getPath()), JMaterialList.class).materials;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void initOreGenerationFile(FMLPreInitializationEvent event) {
		try {
			File fileOreGeneration = new File(event.getModConfigurationDirectory().getPath() + '/' + MODID, CONFIGNAME_OREGENERATION);
			JOreVeinList oreVeinList = new JOreVeinList();
			JOreVein example = new JOreVein();

			//check if the file already exists, if not create it
			if(!fileOreGeneration.exists()) {
				long time = System.currentTimeMillis();
				//create an example to put in the file
				example.biomes = new ArrayList<String>();
				example.denisty = 2;
				example.dimensions = Arrays.asList("overworld");
				example.enabled = true;
				example.inbewteen = "gold";
				example.invertBiomes = true;
				example.invertDimensions = false;
				example.maxHeight = 255;
				example.minHeight = 1;
				example.primary = "coal";
				example.secondary = "iron";
				example.size = 32;
				example.sporadic = "diamond";
				example.weight = 10;
				
				oreVeinList.oreGeneration.put("example", example);
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileOreGeneration));
				writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(oreVeinList));
				writer.close();

				logger.info("Succesfully created \"" + fileOreGeneration + "\" in " + (System.currentTimeMillis() - time) + "ms.");
			}

			//transfer config information
			oreVeins = new Gson().fromJson(readFile(fileOreGeneration.getPath()), JOreVeinList.class).oreGeneration;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void initMiscFile(FMLPreInitializationEvent event) {
		try {
			File fileMisc = new File(event.getModConfigurationDirectory().getPath() + '/' + MODID, CONFIGNAME_MISC);
			JMiscLists miscLists = new JMiscLists();
			JMiscBlock exampleBlock = new JMiscBlock();
			JMiscFluid exampleFluid = new JMiscFluid();
			JMiscItem exampleItem = new JMiscItem();
			
			//check if the file already exists, if not create it
			if(true/*!fileMisc.exists()*/) {
				long time = System.currentTimeMillis();
				
				//create examples to put in the file
				exampleBlock.color = new JColor().setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
				exampleBlock.drops.add(new JDrop());
				exampleBlock.effectiveTool = "pickaxe";
				exampleBlock.enabled = true;
				exampleBlock.hardness = (float) (Math.random() * Float.MAX_VALUE);
				exampleBlock.harvestLevel = (int) (Math.random() * 3);
				exampleBlock.isBeaconBase = false;
				exampleBlock.isBeaconPayment = false;
				exampleBlock.lightLevel = (byte) (Math.random() * 15);
				exampleBlock.material = "ROCK";
				exampleBlock.name = "Example Block";
				exampleBlock.obeysGravity = false;
				exampleBlock.oreDict = new String[] {"blockExample", "example"};
				exampleBlock.resistance = (float) (Math.random() * Float.MAX_VALUE);
				exampleBlock.sound = "STONE";
				exampleBlock.texture = new JTexture(MODID + ":blocks/rough/block");
				exampleBlock.tooltip = new String[] {"§s1:50:§0§1§2§3§4§5§6§7§8§9§a§b§c§d§e§f:§sThis is an example!", "§p§q§t§oYou uncovered a secret!"};
				exampleBlock.useColor = true;
				
				exampleFluid.color = new JColor().setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
				exampleFluid.density = (int) (Math.random() * Integer.MAX_VALUE - Math.random() * Integer.MAX_VALUE);
				exampleFluid.enabled = true;
				exampleFluid.isGaseous = exampleFluid.density < 0 ? true : false;
				exampleFluid.lightLevel = (byte) (Math.random() * 15);
				exampleFluid.name = "Example Fluid";
				exampleFluid.temperature = (int) (Math.random() * Integer.MAX_VALUE - Math.random() * Integer.MAX_VALUE);
				exampleFluid.texture = MODID + ":fluids/water/" + (exampleFluid.isGaseous ? "gas" : "liquid");
				exampleFluid.useColor = true;
				exampleFluid.viscosity = (int) (Math.random() * Integer.MAX_VALUE);
				
				exampleItem.color = new JColor().setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
				exampleItem.enabled = true;
				exampleItem.isBeaconPayment = false;
				exampleItem.meta = 0;
				exampleItem.name = "Example Item";
				exampleItem.oreDict = new String[] {"itemExample", "example"};
				exampleItem.texture = new JTexture(MODID + ":items/netherstar/gem");
				exampleItem.tooltip = new String[] {"You can't read this:", "§kHey! Stop it!"};
				exampleItem.useColor = true;
				
				miscLists.blocks.put("example_block", exampleBlock);
				miscLists.fluids.put("example_fluid", exampleFluid);
				miscLists.items.put("example_item", exampleItem);
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileMisc));
				writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(miscLists));
				writer.close();

				logger.info("Succesfully created \"" + fileMisc + "\" in " + (System.currentTimeMillis() - time) + "ms.");
			}
			
			//transfer config information
			JMiscLists list = new Gson().fromJson(readFile(fileMisc.getPath()), JMiscLists.class);
			miscBlockMap = list.blocks;
			miscFluidMap = list.fluids;
			miscItemMap = list.items;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String readFile(String path) throws Exception {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, StandardCharsets.UTF_8);
	}

}
