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
import glowredman.modularmaterials.information.Properties.Materials;
import glowredman.modularmaterials.information.Reference;
import glowredman.modularmaterials.json.Color;
import glowredman.modularmaterials.json.Material;
import glowredman.modularmaterials.json.MaterialList;
import glowredman.modularmaterials.json.Text;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class JSONHandler {
	
	public static void preInit(FMLPreInitializationEvent event) {
		
		//variables
		File file = new File(event.getModConfigurationDirectory().getPath() + "/" + Reference.MODID, Reference.MATERIALCONFIGNAME);
		MaterialList materials = new MaterialList();
		
		//iterate through "Properties.Materials" and add the data to "materials"
		for (int i = 0; i < Materials.values().length; i++) {
			
			Material material = new Material();
			Color color = new Color();
			
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
		
		//create the file (if it doesn't already exist)
		if(file.exists()) {
			Main.logger.info("'" + file.getPath() + "' exists. Won't create a new one.");
		} else {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				
				writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(materials));
				writer.close();
				
				Main.logger.info("Succesfully created file '" + file + "'.");
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		//update "Reference.materialList""
		try {
			Reference.materialList = new Gson().fromJson(readFile(file.getPath(), Charset.defaultCharset()), MaterialList.class);
		} catch (JsonSyntaxException | IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
