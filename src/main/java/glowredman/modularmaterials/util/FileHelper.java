package glowredman.modularmaterials.util;

import java.io.File;

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

}
