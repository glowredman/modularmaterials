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
import glowredman.modularmaterials.object.CTTT;
import glowredman.modularmaterials.object.Material;
import glowredman.modularmaterials.object.Type;
import glowredman.modularmaterials.util.MaterialHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AssetHandler {
	
	public static List<CTTT> cttts = new ArrayList<CTTT>();
	
	@SideOnly(Side.CLIENT)
	public static void initCTTTList() {
		int count = 0;
		long time = System.currentTimeMillis();
		
		for(Material material : materials.values()) {
			if(material.isEnabled() || enableAll) {
				Iterator typeIterator = MaterialHandler.getIterator(material.getEnabledTypes());
				while(typeIterator.hasNext()) {
					Entry<String, Boolean> typeEntry = (Entry<String, Boolean>) typeIterator.next();
					String typeKey = typeEntry.getKey();
					
					//figure out, if there should be an material with that type
					boolean isTypeEnabled = false;
					try {
						isTypeEnabled = (typeEntry.getValue() && types.get(typeKey).isEnabled()) || enableAll;
					} catch (Exception e) {
						if(!suppressTypeMissingWarnings) {
							Main.logger.warn(CONFIGNAME_TYPES + " does not contain information for the type \"" + typeKey + "\"! Add \"" + typeKey + "\" to " + CONFIGNAME_TYPES + " or enable 'suppressMissingTypeWarnings' in " + CONFIGNAME_CORE + '.');
						}
					}
					Type type = types.get(typeKey);
					CTTT cttt = new CTTT(type.getCategory(), material.getTexture(), typeKey);
					if(isTypeEnabled && !cttts.contains(cttt)) {
						cttts.add(cttt);
						count++;
					}
				}
			}
		}
		Main.logger.info("Detected " + count + " different category-texture-type-triples. Took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	@SideOnly(Side.CLIENT)
	public static void createModelFiles() {
		long time = System.currentTimeMillis();
		int count = 0;
		File mcDataDir = Minecraft.getMinecraft().mcDataDir;
		
		for(CTTT cttt : cttts) {
			String texture = cttt.getTexture();
			String type = cttt.getType();
			
			switch (cttt.getCategory()) {
			case "item":
				File dir = new File(mcDataDir + "/resources/" + MODID + "/models/item/" + texture);
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
						count++;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			
			case "block":
				//normal variant
				dir = new File(mcDataDir + "/resources/" + MODID + "/models/block/" + texture);
				file = new File(dir, type + ".json");
				try {
					dir.mkdirs();
					
					if(!file.exists() || overrideModelFiles) {
						if(file.exists()) {
							file.delete();
						}
						BufferedWriter writer = new BufferedWriter(new FileWriter(file));
						writer.write(getTemplateAsString(MODEL_BLOCK).replace("%x", texture).replace("%t", type));
						writer.newLine();
						writer.close();
						count++;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				//inventory variant
				dir = new File(mcDataDir + "/resources/" + MODID + "/models/item/" + texture);
				file = new File(dir, type + ".json");
				try {
					dir.mkdirs();
					
					if(!file.exists() || overrideModelFiles) {
						if(file.exists()) {
							file.delete();
						}
						BufferedWriter writer = new BufferedWriter(new FileWriter(file));
						writer.write(getTemplateAsString(MODEL_BLOCK).replace("%x", texture).replace("%t", type));
						writer.newLine();
						writer.close();
						count++;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			default:
				break;
			}
		}
		Main.logger.info("Created " + count + " model-files. Took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	@SideOnly(Side.CLIENT)
	public static void createBlockStateFiles() {
		long time = System.currentTimeMillis();
		int count = 0;
		
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
					
					//check, if there should be a block of this type and material
					boolean b = false;
					try {
						b = ((typeEntry.getValue() && types.get(type).isEnabled()) || enableAll) && types.get(type).getCategory().equals("block");
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(b) {
						
						//generate the blockstate file
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
								count++;
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		Main.logger.info("Created " + count + " blockstate-files. Took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	@SideOnly(Side.CLIENT)
	public static void createLangFile() {
		long time = System.currentTimeMillis();
		int count = 0;
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
								count++;
								break;
							case "fluid":
								if(type.getState().equals(material.getState())) {
									writer.write("fluid." + MODID + '.' + typeEntry.getKey() + '.' + materialEntry.getKey() + '=' + type.getSyntax().replace("%s", material.getName()));
									writer.newLine();
									count++;
								} else if (type.getState().equals("gaseous")) {
									writer.write("fluid." + MODID + '.' + typeEntry.getKey() + '.' + materialEntry.getKey() + "=Gaseous " + type.getSyntax().replace("%s", material.getName()));
									writer.newLine();
									count++;
								} else if (type.getState().equals("liquid")) {
									if(material.getState().equals("solid")) {
										writer.write("fluid." + MODID + '.' + typeEntry.getKey() + '.' + materialEntry.getKey() + "=Molten " + type.getSyntax().replace("%s", material.getName()));
										writer.newLine();
										count++;
									} else {
										writer.write("fluid." + MODID + '.' + typeEntry.getKey() + '.' + materialEntry.getKey() + "=Liquid " + type.getSyntax().replace("%s", material.getName()));
										writer.newLine();
										count++;
									}
								}
							case "block":
								writer.write("tile." + MODID + '.' + typeEntry.getKey() + '.' + materialEntry.getKey() + ".name=" + type.getSyntax().replace("%s", material.getName()));
								writer.newLine();
								count++;
							default:
								break;
							}
						}
					}
				}
				writer.close();
				Main.logger.info("Created " + count + " localizations. Took " + (System.currentTimeMillis() - time) + "ms.");
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
