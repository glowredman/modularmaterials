package glowredman.modularmaterials.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class PresetHandler {

	public static List<String> getPresets() {
		List<String> l = new ArrayList<>();
		BufferedReader r = null;
		try {
			URL url = new URL(MM_Reference.CONFIG.presetURL + "index.txt");
			r = new BufferedReader(new InputStreamReader(url.openStream()));
			r.lines().filter(s -> !s.isBlank() && !s.startsWith("#")).forEachOrdered(s -> l.add('"' + s + '"'));
			ModularMaterials.LOGGER.debug("Found these presets: " + MM_Reference.PRESETS);
		} catch (Exception e) {
			ModularMaterials.LOGGER.warn("Could not get presets from " + MM_Reference.CONFIG.presetURL + "index.txt");
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(r);
		}
		return l;
	}

	public static int execute(CommandSourceStack stack, String preset) {
		if(downloadConfigFile(preset, "materials.json")) {
			stack.sendSuccess(Component.literal("Successfully downloaded materials.json").withStyle(ChatFormatting.GREEN), false);
		} else {
			stack.sendFailure(Component.literal("Downloading materials.json failed. Either no connection could be established or the preset does not have this file defined.").withStyle(ChatFormatting.RED));
		}
		if(downloadConfigFile(preset, "types.json")) {
			stack.sendSuccess(Component.literal("Successfully downloaded types.json").withStyle(ChatFormatting.GREEN), false);
		} else {
			stack.sendFailure(Component.literal("Downloading types.json failed. Either no connection could be established or the preset does not have this file defined.").withStyle(ChatFormatting.RED));
		}
		if(downloadConfigFile(preset, "orevariants.json")) {
			stack.sendSuccess(Component.literal("Successfully downloaded orevariants.json").withStyle(ChatFormatting.GREEN), false);
		} else {
			stack.sendFailure(Component.literal("Downloading orevariants.json failed. Either no connection could be established or the preset does not have this file defined.").withStyle(ChatFormatting.RED));
		}
		if(downloadConfigFile(preset, "oreveins.json")) {
			stack.sendSuccess(Component.literal("Successfully downloaded oreveins.json").withStyle(ChatFormatting.GREEN), false);
		} else {
			stack.sendFailure(Component.literal("Downloading oreveins.json failed. Either no connection could be established or the preset does not have this file defined.").withStyle(ChatFormatting.RED));
		}
		stack.sendSuccess(Component.literal("Finished installing preset. Restart the game for the changes to take effect."), false);
		return 0;
	}
	
	private static boolean downloadConfigFile(String preset, String file) {
		try {
			FileUtils.copyURLToFile(new URL(MM_Reference.CONFIG.presetURL + preset + "/" + file), new File(JSONHandler.CONFIG_DIR, file));
			return true;
		} catch (Exception e) {
			ModularMaterials.error("An error occured while trying to download " + file + " from " + MM_Reference.CONFIG.presetURL + preset + "."
					+ "This might not be an error, if the preset has no " + file + " defined.");
			e.printStackTrace();
		}
		return false;
	}

}
