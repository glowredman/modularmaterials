package glowredman.modularmaterials.data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.file.PathUtils;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class PresetHandler {
    
    private static final String[] FILENAMES = {"materials.json", "types.json", "orevariants.json", "oreveins.json"};

	public static List<String> getPresets() {
		List<String> l = new ArrayList<>();
		BufferedReader r = null;
		try {
			URL url = new URL(MM_Reference.CONFIG.presetURL + "index.txt");
			r = new BufferedReader(new InputStreamReader(url.openStream()));
			r.lines().filter(s -> !s.isBlank() && !s.startsWith("#")).forEachOrdered(s -> l.add('"' + s + '"'));
			ModularMaterials.LOGGER.debug("Found these presets: " + MM_Reference.PRESETS);
		} catch (Exception e) {
			ModularMaterials.LOGGER.warn("Could not get presets from " + MM_Reference.CONFIG.presetURL + "index.txt", e);
		} finally {
			IOUtils.closeQuietly(r);
		}
		return l;
	}

	public static int execute(CommandSourceStack stack, String preset) {
	    for (String fileName : FILENAMES) {
	        if(downloadConfigFile(preset, fileName)) {
	            stack.sendSuccess(Component.literal("Successfully downloaded " + fileName).withStyle(ChatFormatting.GREEN), false);
	        } else {
	            stack.sendFailure(Component.literal("Downloading " + fileName + " failed. Either no connection could be established or the preset does not have this file defined.").withStyle(ChatFormatting.RED));
	        }
	    }
		return 0;
	}
	
	private static boolean downloadConfigFile(String preset, String file) {
		try {
		    PathUtils.copyFile(new URL(MM_Reference.CONFIG.presetURL + preset + "/" + file), JSONHandler.CONFIG_DIR.resolve(file), StandardCopyOption.REPLACE_EXISTING);
			return true;
		} catch (Exception e) {
			ModularMaterials.LOGGER.error("An error occured while trying to download " + file + " from " + MM_Reference.CONFIG.presetURL + preset + "."
					+ "This might not be an error, if the preset has no " + file + " defined.", e);
		}
		return false;
	}

}
