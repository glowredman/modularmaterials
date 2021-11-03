package glowredman.modularmaterials.data;

import java.io.File;
import java.util.function.Consumer;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.util.FileHelper;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.Pack.PackConstructor;
import net.minecraft.server.packs.repository.Pack.Position;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;

public class ResourceLoader implements RepositorySource {

	public static final File DATA_DIR = new File(JSONHandler.CONFIG_DIR, "datapack");
	public static final File RESOURCES_DIR = new File(JSONHandler.CONFIG_DIR, "resourcepack");
	
	private final boolean data;
	
	public ResourceLoader(boolean data) {
		this.data = data;
	}

	@Override
	public void loadPacks(Consumer<Pack> consumer, PackConstructor constructor) {
		if(data) {
			ModularMaterials.info("Loading datapack.");
			createPackMeta(DATA_DIR);
			consumer.accept(Pack.create(MM_Reference.MODID + "_data", true, () -> new PackResources(DATA_DIR, "Modular Materials Data"), constructor, Position.BOTTOM, PackSource.DEFAULT));
		} else {
			ModularMaterials.info("Loading resourcepack.");
			createPackMeta(RESOURCES_DIR);
			consumer.accept(Pack.create(MM_Reference.MODID + "_resources", true, () -> new PackResources(RESOURCES_DIR, "Modular Materials Resources"), constructor, Position.BOTTOM, PackSource.DEFAULT));
		}
	}
	
	private static void createPackMeta(File dir) {
		final File packMeta = new File(dir, "pack.mcmeta");
		packMeta.getParentFile().mkdirs();
		if(!packMeta.exists()) {
			FileHelper.write(packMeta, "{\"pack\":{\"description\":\"Auto-generated assets for Modular Materials\",\"pack_format\":7}}");
		}
	}

}
