package glowredman.modularmaterials.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.information.PropertiesItems.Items;
import glowredman.modularmaterials.information.PropertiesMaterials.Materials;
import glowredman.modularmaterials.information.Reference;
import glowredman.modularmaterials.json.JColor;
import glowredman.modularmaterials.json.JItem;
import glowredman.modularmaterials.json.JItemList;
import glowredman.modularmaterials.json.JMaterial;
import glowredman.modularmaterials.json.JMaterialList;
import glowredman.modularmaterials.json.JText;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class JSONHandler {
	
	public static void preInit(FMLPreInitializationEvent event) {
		
		//variables
		File fileItems = new File(event.getModConfigurationDirectory().getPath() + '/' + Reference.MODID, Reference.ITEMCONFIGNAME);
		File fileMaterials = new File(event.getModConfigurationDirectory().getPath() + '/' + Reference.MODID, Reference.MATERIALCONFIGNAME);
		JItemList items = new JItemList();
		JMaterialList materials = new JMaterialList();
		
		//iterate through "PropertiesItems.Items" and add the data to "items"
		for (int i = 0; i < Items.values().length; i++) {
			
			JItem item = new JItem();
			
			item.color = Items.values()[i].getColor();
			item.disabled = Items.values()[i].isDisabled();
			item.iconSet = Items.values()[i].getIconSet();
			item.meta = Items.values()[i].getMeta();
			item.name = Items.values()[i].getName();
			item.oreDict = Items.values()[i].getOreDict();
			item.texture = Items.values()[i].getTexture();
			item.tooltip = Items.values()[i].getTooltip();
			item.useCustomTexture = Items.values()[i].usesCustomTexture();
			
			items.items.add(item);
			
		}
		
		//iterate through "PropertiesMaterials.Materials" and add the data to "materials"
		for (int i = 0; i < Materials.values().length; i++) {
			
			JMaterial material = new JMaterial();
			JColor color = new JColor();
			
			color.r = Materials.values()[i].getRed();
			color.g = Materials.values()[i].getGreen();
			color.b = Materials.values()[i].getBlue();
			color.a = Materials.values()[i].getAlpha();
			
			material.blockHardness = Materials.values()[i].getBlockHardness();
			material.blockResistance = Materials.values()[i].getBlockResistance();
			material.blockTexture = Materials.values()[i].getBlockTexture();
			material.boilingTemperature = Materials.values()[i].getBolingTemperature();
			material.color = color;
			material.density = Materials.values()[i].getDensity();
			material.disabled = Materials.values()[i].isDisabled();
			material.fluidTexture = Materials.values()[i].getFluidTexture();
			material.harvestLevel = Materials.values()[i].getLightLevel();
			material.lightLevel = Materials.values()[i].getLightLevel();
			material.meltingTemperature = Materials.values()[i].getMeltigTemperature();
			material.meta = Materials.values()[i].getMeta();
			material.name = Materials.values()[i].getName();
			material.oreDict = Materials.values()[i].getOreDict();
			material.oreHardness = Materials.values()[i].getOreHardness();
			material.oreResistance = Materials.values()[i].getOreResistance();
			material.state = Materials.values()[i].getState();
			material.tags = Materials.values()[i].getTags();
			material.temperature = Materials.values()[i].getTemperature();
			material.textureSet = Materials.values()[i].getTextureSet();
			material.tooltip = Materials.values()[i].getTooltip();
			material.viscosity = Materials.values()[i].getViscosity();
			
			materials.materials.add(material);
			
		}
		
		//create the files (if they don't already exist)
		if(fileItems.exists()) {
			Main.logger.info("The file \"" + fileItems.getPath() + "\" exists. Won't create a new one.");
		} else {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileItems));
				
				writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(items));
				writer.close();
				
				Main.logger.info("Succesfully created file \"" + fileItems + "\".");
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		if(fileMaterials.exists()) {
			Main.logger.info("The file \"" + fileMaterials.getPath() + "\" exists. Won't create a new one.");
		} else {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileMaterials));
				
				writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(materials));
				writer.close();
				
				Main.logger.info("Succesfully created file \"" + fileMaterials + "\".");
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		//update "Reference.materialList" and "Reference.itemList"
		try {
			Reference.materialList = new Gson().fromJson(readFile(fileMaterials.getPath(), Charset.defaultCharset()), JMaterialList.class);
			Reference.itemList = new Gson().fromJson(readFile(fileItems.getPath(), Charset.defaultCharset()), JItemList.class);
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
