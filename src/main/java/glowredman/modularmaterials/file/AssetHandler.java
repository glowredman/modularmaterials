package glowredman.modularmaterials.file;

import static glowredman.modularmaterials.Main.logger;
import static glowredman.modularmaterials.Reference.*;
import static glowredman.modularmaterials.file.Templates.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import glowredman.modularmaterials.object.CTTT;
import glowredman.modularmaterials.object.JMaterial;
import glowredman.modularmaterials.object.JMiscBlock;
import glowredman.modularmaterials.object.JMiscFluid;
import glowredman.modularmaterials.object.JMiscItem;
import glowredman.modularmaterials.object.JOreVariant;
import glowredman.modularmaterials.object.JTexture;
import glowredman.modularmaterials.object.JType;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AssetHandler {
	
	public static List<CTTT> cttts = new ArrayList<CTTT>();
	
	@SideOnly(Side.CLIENT)
	public static void initCTTTList() {
		int count = 0;
		long time = System.currentTimeMillis();
		
		//materials
		for(JMaterial material : materials.values()) {
			if(material.enabled || enableAll) {
				for(Entry<String, Boolean> typeEntry : material.enabledTypes.entrySet()) {
					String typeKey = typeEntry.getKey();
					
					//figure out, if there should be an material with that type
					boolean isTypeEnabled = false;
					try {
						isTypeEnabled = (typeEntry.getValue() && types.get(typeKey).enabled) || enableAll;
					} catch (Exception e) {
						if(!suppressTypeMissingWarnings) {
							logger.warn(CONFIGNAME_TYPES + " does not contain information for the type \"" + typeKey + "\"! Add \"" + typeKey + "\" to " + CONFIGNAME_TYPES + " or enable 'suppressMissingTypeWarnings' in " + CONFIGNAME_CORE + '.');
						}
					}
					JType type = types.get(typeKey);
					CTTT cttt = new CTTT(type.category, new JTexture(material.texture), typeKey);
					if(isTypeEnabled && !cttts.contains(cttt)) {
						cttts.add(cttt);
						count++;
					}
				}
			}
		}
		
		//miscItems
		for(JMiscItem item : miscItemMap.values()) {
			if(item.enabled || enableAll) {
				CTTT cttt = new CTTT("miscItem", item.texture, "" + item.useColor);
				if(!cttts.contains(cttt)) {
					cttts.add(cttt);
					count++;
				}
			}
		}
		
		//miscBlocks
		for(JMiscBlock block : miscBlockMap.values()) {
			if(block.enabled || enableAll) {
				CTTT cttt = new CTTT("miscBlock", block.texture, "" + block.useColor);
				if(!cttts.contains(cttt)) {
					cttts.add(cttt);
					count++;
				}
			}
		}
		
		logger.info("Detected " + count + " different category-texture-type-triples. Took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	@SideOnly(Side.CLIENT)
	public static void createModelFiles() {
		long time = System.currentTimeMillis();
		int count = 0;
		File mcDataDir = Minecraft.getMinecraft().mcDataDir;
		
		for(CTTT cttt : cttts) {
			JTexture texture = cttt.texture;
			String type = cttt.type;
			
			switch (cttt.category) {
			case "item":
				File dir = new File(mcDataDir + "/resources/" + MODID + "/models/item/" + texture.getTexture());
				File file = new File(dir, type + ".json");
				try {
					dir.mkdirs();
					
					//if the file does not already exist or should be overridden, create it
					if(!file.exists() || overrideModelFiles) {
						if(file.exists()) {
							file.delete();
						}
						BufferedWriter writer = new BufferedWriter(new FileWriter(file));
						writer.write(getTemplateAsString(MODEL_ITEM).replace("%x", texture.getTexture()).replace("%t", type));
						writer.newLine();
						writer.close();
						count++;
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			
			case "block":
				//normal variant
				dir = new File(mcDataDir + "/resources/" + MODID + "/models/block/" + texture.getTexture());
				file = new File(dir, type + ".json");
				try {
					dir.mkdirs();
					
					if(!file.exists() || overrideModelFiles) {
						if(file.exists()) {
							file.delete();
						}
						BufferedWriter writer = new BufferedWriter(new FileWriter(file));
						writer.write(getTemplateAsString(MODEL_BLOCK).replace("%x", texture.getTexture()).replace("%t", type));
						writer.newLine();
						writer.close();
						count++;
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				//inventory variant
				dir = new File(mcDataDir + "/resources/" + MODID + "/models/item/" + texture.getTexture());
				file = new File(dir, type + ".json");
				try {
					dir.mkdirs();
					
					if(!file.exists() || overrideModelFiles) {
						if(file.exists()) {
							file.delete();
						}
						BufferedWriter writer = new BufferedWriter(new FileWriter(file));
						writer.write(getTemplateAsString(MODEL_BLOCK).replace("%x", texture.getTexture()).replace("%t", type));
						writer.newLine();
						writer.close();
						count++;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			case "ore":
				for(Entry<String, JOreVariant> oreVariantEntry : oreVariants.entrySet()) {
					String ore = oreVariantEntry.getKey();
					String base = oreVariantEntry.getValue().baseTexture;
					
					//normal variant
					dir = new File(mcDataDir + "/resources/" + MODID + "/models/block/" + texture.getTexture() + '/' + type);
					file = new File(dir, ore + ".json");
					try {
						dir.mkdirs();
						
						if (!file.exists() || overrideModelFiles) {
							if (file.exists()) {
								file.delete();
							}
							BufferedWriter writer = new BufferedWriter(new FileWriter(file));
							writer.write(getTemplateAsString(MODEL_ORE).replace("%x", texture.getTexture()).replace("%t", type).replace("%b", base));
							writer.newLine();
							writer.close();
							count++;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					//inventory variant
					dir = new File(mcDataDir + "/resources/" + MODID + "/models/item/" + texture.getTexture() + '/' + type);
					file = new File(dir, ore + ".json");
					try {
						dir.mkdirs();
						
						if (!file.exists() || overrideModelFiles) {
							if (file.exists()) {
								file.delete();
							}
							BufferedWriter writer = new BufferedWriter(new FileWriter(file));
							writer.write(getTemplateAsString(MODEL_ORE).replace("%x", texture.getTexture()).replace("%t", type).replace("%b", base));
							writer.newLine();
							writer.close();
							count++;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			case "miscItem":
				dir = new File(mcDataDir + "/resources/" + MODID + "/models/item/");
				file = new File(dir, texture.model + ".json");
				try {
					dir.mkdirs();
					
					//if the file does not already exist or should be overridden, create it
					if(!file.exists() || overrideModelFiles) {
						if(file.exists()) {
							file.delete();
						}
						BufferedWriter writer = new BufferedWriter(new FileWriter(file));
						writer.write(getItemModelByJTexture(texture));
						writer.newLine();
						writer.close();
						count++;
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "miscBlock":
				
				//when the texture should be colored, an other model must be used
				if(cttt.type.equals("true")) {
					
					//normal variant
					dir = new File(mcDataDir + "/resources/" + MODID + "models/block/");
					file = new File(dir, texture.model + ".json");
					try {
						dir.mkdirs();

						//if the file does not already exist or should be overridden, create it
						if(!file.exists() || overrideModelFiles) {
							if(file.exists()) {
								file.delete();
							}
							BufferedWriter writer = new BufferedWriter(new FileWriter(file));
							writer.write(getTemplateAsString(MODEL_MISCBLOCK).replace("%t", texture.particle).replace("%t", texture.textures.get("meta")));
							writer.newLine();
							writer.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					//inventory variant
					dir = new File(mcDataDir + "/resources/" + MODID + "models/item/");
					file = new File(dir, texture.model + ".json");
					try {
						dir.mkdirs();

						//if the file does not already exist or should be overridden, create it
						if(!file.exists() || overrideModelFiles) {
							if(file.exists()) {
								file.delete();
							}
							BufferedWriter writer = new BufferedWriter(new FileWriter(file));
							writer.write(getTemplateAsString(MODEL_MISCBLOCK).replace("%t", texture.particle).replace("%t", texture.textures.get("meta")));
							writer.newLine();
							writer.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				} else {

					//normal variant
					dir = new File(mcDataDir + "/resources/" + MODID + "/models/block/");
					file = new File(dir, texture.model + ".json");
					try {
						dir.mkdirs();

						//if the file does not already exist or should be overridden, create it
						if(!file.exists() || overrideModelFiles) {
							if(file.exists()) {
								file.delete();
							}
							BufferedWriter writer = new BufferedWriter(new FileWriter(file));
							writer.write(getBlockModelByJTexture(texture));
							writer.newLine();
							writer.close();
							count++;
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					//inventory variant
					dir = new File(mcDataDir + "/resources/" + MODID + "/models/item/");
					file = new File(dir, texture.model + ".json");
					try {
						dir.mkdirs();

						//if the file does not already exist or should be overridden, create it
						if(!file.exists() || overrideModelFiles) {
							if(file.exists()) {
								file.delete();
							}
							BufferedWriter writer = new BufferedWriter(new FileWriter(file));
							writer.write(getBlockModelByJTexture(texture));
							writer.newLine();
							writer.close();
							count++;
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				break;
			default:
				break;
			}
		}
		logger.info("Created " + count + " model-files. Took " + (System.currentTimeMillis() - time) + "ms.");
	}
	
	@SideOnly(Side.CLIENT)
	public static void createBlockStateFiles() {
		long time = System.currentTimeMillis();
		int count = 0;
		
		//iterate through all materials
		for(Entry<String, JMaterial> materialEntry : materials.entrySet()) {
			JMaterial material = materialEntry.getValue();
			String texture = material.texture;
			
			//check if the material should even be registered
			if(material.enabled || enableAll) {

				//iterate through all of the materials types
				for(Entry<String, Boolean> typeEntry : material.enabledTypes.entrySet()) {
					String type = typeEntry.getKey();
					
					//check, if there should be a block of this type and material
					boolean b = false;
					try {
						b = ((typeEntry.getValue() && types.get(type).enabled) || enableAll) && types.get(type).category.equals("block");
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
					} else {
						
						//check, if there should be a ore of this type and material
						try {
							b = ((typeEntry.getValue() && types.get(type).enabled) || enableAll) && types.get(type).category.equals("ore");
						} catch (Exception e) {
							e.printStackTrace();
						}
						if(b) {
							for(Entry<String, JOreVariant> oreVariantEntry : oreVariants.entrySet()) {
								String ore = oreVariantEntry.getKey();
								if(oreVariantEntry.getValue().enabled || enableAll) {
									
									//generate the blockstate file
									File dir = new File(Minecraft.getMinecraft().mcDataDir + "/resources/" + MODID + "/blockstates");
									File file = new File(dir, type + '.' + ore + '.' + materialEntry.getKey() + ".json");
									try {
										dir.mkdirs();
										
										if(!file.exists() || overrideBlockStateFiles) {
											if(file.exists()) {
												file.delete();
											}
											BufferedWriter writer = new BufferedWriter(new FileWriter(file));
											writer.write(getTemplateAsString(BLOCKSTATE_ORE).replace("%x", texture).replace("%t", type).replace("%o", ore));
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
				}
			}
		}
		
		//miscBlocks
		for(Entry<String, JMiscBlock> blockEntry : miscBlockMap.entrySet()) {
			JMiscBlock block = blockEntry.getValue();
			if(block.enabled || enableAll) {
				String key = blockEntry.getKey();
				File dir = new File(Minecraft.getMinecraft().mcDataDir + "/resources/" + MODID + "/blockstates");
				File file = new File(dir, "miscblock." + key + ".json");
				try {
					dir.mkdirs();
					
					if(!file.exists() || overrideBlockStateFiles) {
						if(file.exists()) {
							file.delete();
						}
						BufferedWriter writer = new BufferedWriter(new FileWriter(file));
						writer.write(getTemplateAsString(BLOCKSTATE_MISCBLOCK).replace("%m", block.texture.model));
						writer.newLine();
						writer.close();
						count++;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		logger.info("Created " + count + " blockstate-files. Took " + (System.currentTimeMillis() - time) + "ms.");
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
				
				//materials
				for(Entry<String, JMaterial> materialEntry : materials.entrySet()) {
					JMaterial material = materialEntry.getValue();
					String materialKey = materialEntry.getKey();
					String materialName = material.name;
					newParagraph(writer);
					writer.write("# -=-=- " + materialKey + " -=-=-");
					writer.newLine();
					for(Entry<String, Boolean> typeEntry : material.enabledTypes.entrySet()) {
						String typeKey = typeEntry.getKey();
						if(types.containsKey(typeKey) && typeEntry.getValue()) {
							JType type = types.get(typeKey);
							String typeSyntax = type.syntax;
							switch (type.category) {
							case "item":
								writer.write("item." + MODID + '.' + typeKey + '.' + materialKey + ".name=" + typeSyntax.replace("%s", materialName));
								writer.newLine();
								count++;
								break;
							case "fluid":
								if(type.state.equals(material.state)) {
									writer.write("fluid." + MODID + '.' + typeKey + '.' + materialKey + '=' + typeSyntax.replace("%s", materialName));
									writer.newLine();
									count++;
								} else if (type.state.equals("gaseous")) {
									writer.write("fluid." + MODID + '.' + typeKey + '.' + materialKey + "=Gaseous " + typeSyntax.replace("%s", materialName));
									writer.newLine();
									count++;
								} else if (type.state.equals("liquid")) {
									if(material.state.equals("solid")) {
										writer.write("fluid." + MODID + '.' + typeKey + '.' + materialKey + "=Molten " + typeSyntax.replace("%s", materialName));
										writer.newLine();
										count++;
									} else {
										writer.write("fluid." + MODID + '.' + typeKey + '.' + materialKey + "=Liquid " + typeSyntax.replace("%s", materialName));
										writer.newLine();
										count++;
									}
								}
								break;
							case "block":
								writer.write("tile." + MODID + '.' + typeKey + '.' + materialKey + ".name=" + typeSyntax.replace("%s", materialName));
								writer.newLine();
								count++;
								break;
							case "ore":
								for(Entry<String, JOreVariant> oreVariantEntry : oreVariants.entrySet()) {
									String oreSyntax = oreVariantEntry.getValue().syntax;
									String oreKey = oreVariantEntry.getKey();
									writer.write("tile." + MODID + '.' + typeKey + '.' + oreKey + '.' + materialKey + ".name=" + oreSyntax.replace("%s", materialName));
									writer.newLine();
									count++;
								}
								break;
							default:
								break;
							}
						}
					}
				}
				
				//misc
				newParagraph(writer);
				writer.write("# -=-=- misc blocks -=-=-");
				writer.newLine();
				for(Entry<String, JMiscBlock> blockEntry : miscBlockMap.entrySet()) {
					JMiscBlock block = blockEntry.getValue();
					if(block.enabled || enableAll) {
						writer.write("tile." + MODID + ".miscBlock." + blockEntry.getKey() + ".name=" + block.name);
						writer.newLine();
						count++;
					}
				}
				newParagraph(writer);
				writer.write("# -=-=- misc fluids -=-=-");
				writer.newLine();
				for(Entry<String, JMiscFluid> fluidEntry : miscFluidMap.entrySet()) {
					JMiscFluid fluid = fluidEntry.getValue();
					if(fluid.enabled || enableAll) {
						writer.write("fluid." + MODID + ".miscFluid." + fluidEntry.getKey() + '=' + fluid.name);
						writer.newLine();
						count++;
					}
				}
				newParagraph(writer);
				writer.write("# -=-=- misc items -=-=-");
				writer.newLine();
				for(Entry<String, JMiscItem> itemEntry : miscItemMap.entrySet()) {
					JMiscItem item = itemEntry.getValue();
					if(item.enabled || enableAll) {
						writer.write("item." + MODID + ".miscItem." + itemEntry.getKey() + ".name=" + item.name);
						writer.newLine();
						count++;
					}
				}
				
				writer.close();
				logger.info("Created " + count + " localizations. Took " + (System.currentTimeMillis() - time) + "ms.");
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
