package glowredman.modularmaterials.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;

import glowredman.modularmaterials.ModularMaterials;

public class FileHelper {
	
	public static void cleanDir(File dir) {
		long time = System.currentTimeMillis();
		try {
			FileUtils.deleteDirectory(dir);
			ModularMaterials.info("Cleaned \"" + dir.getPath() + "\" in " + (System.currentTimeMillis() - time) + "ms.");
		} catch (Exception e) {
			ModularMaterials.error("An Error occured while cleaning \"" + dir.getPath() + "\":");
			e.printStackTrace();
		}
	}
	
	public static String readFile(Path path) throws IOException {
		byte[] encoded = Files.readAllBytes(path);
		return new String(encoded, StandardCharsets.UTF_8);
	}
	
	public static void write(File file, String content) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8));
		writer.write(content);
		writer.close();
	}

}
