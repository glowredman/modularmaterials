package glowredman.modularmaterials.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.information.Properties;
import glowredman.modularmaterials.information.Reference;
import glowredman.modularmaterials.json.JMaterial;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class FileHandler {
	
	private static long lineCount = 0;
	
	public static void createLangFile(FMLPreInitializationEvent event) {
		
		if (Reference.dumpLang) {
			
			File file = new File(event.getModConfigurationDirectory().getPath() + "/" + Reference.MODID, Reference.LANGFILENAME);
			long time = System.nanoTime();
			
			try {
				
				if (file.exists()) {file.delete();}
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				
				//header
				writer.write("# automaticly generated by " + Reference.MODNAME);
				writer.newLine();
				
				//blocks
				newParagraph(writer, "BLOCKS");
				translation(writer, "tile." + Reference.MODID + ".block", "Block");
				writer.newLine();
				
				//ingots
				newParagraph(writer, "INGOTS");
				translation(writer, "item." + Reference.MODID + ".meta.ingot", "Ingot");
				writer.newLine();
				
				//plates
				newParagraph(writer, "PLATES");
				translation(writer, "item." + Reference.MODID + ".meta.plate", "Plate");
				writer.newLine();
				
				//liquid
				newParagraph(writer, "LIQUIDS");
				translation2(writer, "fluid." + Reference.MODID + ".liquid_", "Liquid");
				writer.newLine();
				
				//generic fluids
				newParagraph(writer, "FLUIDS");
				translation2(writer, "fluid." + Reference.MODID + ".");
				writer.newLine();
				
				//gases
				newParagraph(writer, "GASES");
				translation2(writer, "fluid." + Reference.MODID + ".gaseous_", "Gaseous");
				writer.newLine();
				
				writer.close();
				
				Main.logger.info("Created \"" + Reference.LANGFILENAME + "\" with " + lineCount + " entries. Took " + MiscUtils.timer(time));
				
			} catch (Exception e) {
				Main.logger.error("An error occured while creating \"" + file.getPath() + "\"! (" + e + ")");
			}
			
		}
		
	}
	
	static void translation2(BufferedWriter writer, String prefix, String typeName) throws IOException {
		for (JMaterial material : Reference.materialList.getMaterials()) {
			writer.write(prefix + material.getName().getText().replace(' ', '_').toLowerCase() + ".name=" + typeName + ' ' + material.getName().getText());
			lineCount++;
			writer.newLine();
		}
	}
	
	static void translation2(BufferedWriter writer, String prefix) throws IOException {
		for (JMaterial material : Reference.materialList.getMaterials()) {
			if (material.getState().contentEquals(Properties.S1)) {
				writer.write(prefix + material.getName().getText().replace(' ', '_').toLowerCase() + ".name=Molten " + material.getName().getText());
			} else {
				writer.write(prefix + material.getName().getText().replace(' ', '_').toLowerCase() + ".name=" + material.getName().getText());
			}
			lineCount++;
			writer.newLine();
		}
	}
	
	static void translation(BufferedWriter writer, String prefix, String typeName) throws IOException {
		for (JMaterial material : Reference.materialList.getMaterials()) {
			writer.write(prefix + material.getName().getText().replace(' ', '_') + ".name=" + material.getName().getText() + ' ' + typeName);
			lineCount++;
			writer.newLine();
		}
	}
	
	static void newParagraph(BufferedWriter writer, String header) throws IOException {
		writer.newLine();
		writer.newLine();
		writer.write("#  -----  " + header + "  -----");
		writer.newLine();
		writer.newLine();
		
	}

}
