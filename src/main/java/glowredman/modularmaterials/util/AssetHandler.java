package glowredman.modularmaterials.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.Reference;
import glowredman.modularmaterials.material.Material;
import glowredman.modularmaterials.material.MaterialHandler;
import glowredman.modularmaterials.material.MaterialList;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AssetHandler {
	
	public static List<String> itemTextures = new ArrayList<String>();
	
	public static void initItemTextureList() {
		int textureCount = 0;
		long time = System.currentTimeMillis();
		
		Iterator i = MaterialHandler.getIterator(MaterialList.materials);
		while(i.hasNext()) {
			Entry<String, Material> e = (Entry<String, Material>) i.next();
			String texture = e.getValue().getTexture();
			if(!itemTextures.contains(texture)) {
				itemTextures.add(texture);
				textureCount++;
			}
		}
		Main.logger.info("Detected " + textureCount + " different meta-item-textures in " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	@SideOnly(Side.CLIENT)
	public static void createModelFiles() {
		long time = System.currentTimeMillis();
		int modelFileCount = 0;
		
		//iterate through all types
		Iterator i = MaterialHandler.getIterator(Reference.itemTypes);
		while(i.hasNext()) {
			Entry<String, String> entry = (Entry<String, String>) i.next();
			String key = entry.getKey();
			String value = entry.getValue();
			
			//if the type is an item, iterate through all materials
			for(String texture : itemTextures) {
				File dir = new File(Minecraft.getMinecraft().mcDataDir + "/resources/" + Reference.MODID + "/models/item/" + key);
				File file = new File(dir, texture + ".json");
				try {
					dir.mkdirs();
					
					//if the file does not already exist or should be overridden, create it
					if(!file.exists() || Reference.overrideModelFiles) {
						if(file.exists() && Reference.overrideModelFiles) {
							file.delete();
						}
						BufferedWriter writer = new BufferedWriter(new FileWriter(file));
						writer.write("{");
						writer.newLine();
						writer.write("\t\"parent\": \"item/generated\",");
						writer.newLine();
						writer.write("\t\"textures\": {");
						writer.newLine();
						writer.write("\t\t\"layer0\": \"" + Reference.MODID + ":items/" + texture + "/" + key + "\",");
						writer.newLine();
						writer.write("\t\t\"layer1\": \"" + Reference.MODID + ":items/" + texture + "/" + key + "_overlay\"");
						writer.newLine();
						writer.write("\t}");
						writer.newLine();
						writer.write("}");
						writer.close();
						modelFileCount++;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
				
		}
		
		//TODO blocks
		
		//TODO fluids
		
		Main.logger.info("Created " + modelFileCount + " model-files in " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	@SideOnly(Side.CLIENT)
	public static void createLangFile() {
		long time = System.currentTimeMillis();
		int lineCount = 0;
		File dir = new File(Minecraft.getMinecraft().mcDataDir + "/resources/" + Reference.MODID + "/lang/");
		File file = new File(dir, "en_us.lang");
		
		if(!file.exists() || Reference.overrideLangFile) {
			if(file.exists() && Reference.overrideLangFile) {
				file.delete();
			}
			try {
				dir.mkdirs();
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				
				//header
				writer.write("# automaticly generated by " + Reference.MODNAME);
				writer.newLine();
				writer.newLine();
				writer.write("item." + Reference.MODID + ".debug.name=§cInvalid Item");
				writer.newLine();
				
				Iterator i = MaterialHandler.getIterator(Reference.itemTypes);
				while(i.hasNext()) {
					Entry<String, String> e = (Entry<String, String>) i.next();
					String type = e.getKey();
					String syntax = e.getValue();
					
					newParagraph(writer);
					
					Iterator j = MaterialHandler.getIterator(MaterialList.materials);
					while(j.hasNext()) {
						Entry<String, Material> f = (Entry<String, Material>) j.next();
						String name = f.getValue().getName();
						writer.write("item." + Reference.MODID + '.' +  type + '.' + name.replace(' ', '_') + ".name=" + syntax.replace("%s", name));
						writer.newLine();
					}
				}
				
				
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	static void newParagraph(BufferedWriter writer) throws IOException {
		writer.newLine();
		writer.newLine();
		writer.newLine();
		
	}

}
