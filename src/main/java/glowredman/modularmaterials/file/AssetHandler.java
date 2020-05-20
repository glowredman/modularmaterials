package glowredman.modularmaterials.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import glowredman.modularmaterials.Main;
import static glowredman.modularmaterials.Reference.*;
import static glowredman.modularmaterials.file.Templates.*;
import glowredman.modularmaterials.object.Material;
import glowredman.modularmaterials.object.Type;
import glowredman.modularmaterials.util.MaterialHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AssetHandler {
	
	public static List<String> itemTextures = new ArrayList<String>();
	
	public static void initItemTextureList() {
		int textureCount = 0;
		long time = System.currentTimeMillis();
		
		for(Material materialIterator : materials.values()) {
			String texture = materialIterator.getTexture();
			if(!itemTextures.contains(texture)) {
				itemTextures.add(texture);
				textureCount++;
			}
		}
		Main.logger.info("Detected " + textureCount + " different meta-item-textures-sets. Took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	@SideOnly(Side.CLIENT)
	public static void createModelFiles() {
		long time = System.currentTimeMillis();
		int modelFileCount = 0;
		int blockStateCount = 0;
		
		//iterate through all materials
		Iterator materialIterator = MaterialHandler.getIterator(materials);
		while(materialIterator.hasNext()) {
			Entry<String, Material> materialEntry = (Entry<String, Material>) materialIterator.next();
			Material material = materialEntry.getValue();
			String texture = material.getTexture();
			
			//check if the material should even be registered
			if(material.isEnabled() || enableAll) {

				//iterate through all of the materials types
				Iterator typeIterator = MaterialHandler.getIterator(material.getEnabledTypes());
				while(typeIterator.hasNext()) {
					Entry<String, Boolean> typeEntry = (Entry<String, Boolean>) typeIterator.next();
					String type = typeEntry.getKey();
					
					//check, if there should be an item of this type and material
					boolean b = false;
					try {
						b = ((typeEntry.getValue()  && types.get(type).getCategory().equals("item") && types.get(type).isEnabled()) || enableAll) ? true : false;
					} catch (Exception e) {
						if(!suppressTypeMissingWarnings) {
							Main.logger.error(CONFIGNAME_TYPES + " does not contain information for the type \"" + type + "\"! Add \"" + type + "\" to " + CONFIGNAME_TYPES + " or enable 'suppressMissingTypeWarnings' in " + CONFIGNAME_CORE + '.');
						}
					}
					if(b) {
						File dir = new File(Minecraft.getMinecraft().mcDataDir + "/resources/" + MODID + "/models/item/" + texture);
						File file = new File(dir, type + ".json");
						try {
							dir.mkdirs();
							
							//if the file does not already exist or should be overridden, create it
							if(!file.exists() || overrideModelFiles) {
								if(file.exists()) {
									file.delete();
								}
								BufferedWriter writer = new BufferedWriter(new FileWriter(file));
								writer.write(getTemplateAsString(MODEL_ITEM).replace("%x", texture).replace("%t", type));
								writer.newLine();
								writer.close();
								modelFileCount++;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					//check, if there should be a block of this type and material
					b = false;
					try {
						b = ((typeEntry.getValue()  && types.get(type).getCategory().equals("block") && types.get(type).isEnabled()) || enableAll) ? true : false;
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(b) {
						File dir = new File(Minecraft.getMinecraft().mcDataDir + "/resources/" + MODID + "/blockstates");
						File file = new File(dir, type + '.' + materialEntry.getKey() + ".json");
						try {
							dir.mkdirs();
							
							if(!file.exists() || overrideBlockStateFiles) {
								if(file.exists()) {
									file.delete();
								}
								BufferedWriter writer = new BufferedWriter(new FileWriter(file));
								writer.write(getTemplateAsString(BLOCKSTATE_BLOCK).replace("%x", texture).replace("%t", type));
								writer.newLine();
								writer.close();
								blockStateCount++;
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		Main.logger.info("Created " + modelFileCount + " model-files and " + blockStateCount + " blockstate-files. Took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	@SideOnly(Side.CLIENT)
	public static void createLangFile() {
		long time = System.currentTimeMillis();
		int counter = 0;
		File dir = new File(Minecraft.getMinecraft().mcDataDir + "/resources/" + MODID + "/lang/");
		File file = new File(dir, "en_us.lang");
		
		if(!file.exists() || overrideLangFile) {
			if(file.exists()) {
				file.delete();
			}
			try {
				dir.mkdirs();
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				
				//header
				writer.write("# automaticly generated by " + MODNAME);
				writer.newLine();
				writer.newLine();
				
				Iterator materialIterator = MaterialHandler.getIterator(materials);
				while(materialIterator.hasNext()) {
					Entry<String, Material> materialEntry = (Entry<String, Material>) materialIterator.next();
					Material material = materialEntry.getValue();
					newParagraph(writer);
					writer.write("# -=-=- " + materialEntry.getKey() + " -=-=-");
					writer.newLine();
					Iterator typeIterator = MaterialHandler.getIterator(material.getEnabledTypes());
					while(typeIterator.hasNext()) {
						Entry<String, Boolean> typeEntry = (Entry<String, Boolean>) typeIterator.next();
						if(types.containsKey(typeEntry.getKey()) && typeEntry.getValue()) {
							Type type = types.get(typeEntry.getKey());
							switch (type.getCategory()) {
							case "item":
								writer.write("item." + MODID + '.' + typeEntry.getKey() + '.' + materialEntry.getKey() + ".name=" + type.getSyntax().replace("%s", material.getName()));
								writer.newLine();
								counter++;
								break;
							case "fluid":
								if(type.getState().equals(material.getState())) {
									writer.write("fluid." + MODID + '.' + typeEntry.getKey() + '.' + materialEntry.getKey() + '=' + type.getSyntax().replace("%s", material.getName()));
									writer.newLine();
									counter++;
								} else if (type.getState().equals("gaseous")) {
									writer.write("fluid." + MODID + '.' + typeEntry.getKey() + '.' + materialEntry.getKey() + "=Gaseous " + type.getSyntax().replace("%s", material.getName()));
									writer.newLine();
									counter++;
								} else if (type.getState().equals("liquid")) {
									if(material.getState().equals("solid")) {
										writer.write("fluid." + MODID + '.' + typeEntry.getKey() + '.' + materialEntry.getKey() + "=Molten " + type.getSyntax().replace("%s", material.getName()));
										writer.newLine();
										counter++;
									} else {
										writer.write("fluid." + MODID + '.' + typeEntry.getKey() + '.' + materialEntry.getKey() + "=Liquid " + type.getSyntax().replace("%s", material.getName()));
										writer.newLine();
										counter++;
									}
								}
							case "block":
								writer.write("tile." + MODID + '.' + typeEntry.getKey() + '.' + materialEntry.getKey() + ".name=" + type.getSyntax().replace("%s", material.getName()));
								writer.newLine();
								counter++;
							default:
								break;
							}
						}
					}
				}
				writer.close();
				Main.logger.info("Created " + counter + " localizations. Took " + (System.currentTimeMillis() - time) + "ms.");
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
