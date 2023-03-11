package glowredman.modularmaterials.data;

import java.io.File;
import java.util.function.Consumer;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.Pack.Info;
import net.minecraft.server.packs.repository.Pack.Position;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;
import net.minecraft.world.flag.FeatureFlagSet;

public class ResourceLoader implements RepositorySource {

	public static final File DATA_DIR = new File(JSONHandler.CONFIG_DIR, "datapack");
	public static final File RESOURCES_DIR = new File(JSONHandler.CONFIG_DIR, "resourcepack");
	
	private final boolean data;
	
	public ResourceLoader(boolean data) {
		this.data = data;
	}

	@Override
	public void loadPacks(Consumer<Pack> pOnLoad) {
		if(data) {
			ModularMaterials.LOGGER.info("Loading datapack.");
			pOnLoad.accept(Pack.create(
					MM_Reference.MODID + "_data", // id
					Component.literal("Modular Materials Data"), // title
					true, // required
					p_251717_ -> new PathPackResources( // resources
							"Modular Materials Data", // name
							DATA_DIR.toPath(), // root
							false), // isBuiltin
					new Info( // info
							Component.empty(), // description
							8, // dataFormat
							8, // resourceFormat
							FeatureFlagSet.of(), // requestedFeatures
							true), // hidden
					PackType.SERVER_DATA,
					Position.BOTTOM, // defaultPosition
					true, // fixedPosition
					PackSource.DEFAULT)); // packSource
		} else {
			ModularMaterials.LOGGER.info("Loading resourcepack.");
			pOnLoad.accept(Pack.create(
					MM_Reference.MODID + "_resources", // id
					Component.literal("Modular Materials Resources"), // title
					true, // required
					p_251717_ -> new PathPackResources( // resources
							"Modular Materials Resources", // name
							RESOURCES_DIR.toPath(), // root
							false), // isBuiltin
					new Info( // info
							Component.empty(), // description
							8, // dataFormat
							8, // resourceFormat
							FeatureFlagSet.of(), // requestedFeatures
							true), // hidden
					PackType.CLIENT_RESOURCES,
					Position.BOTTOM, // defaultPosition
					true, // fixedPosition
					PackSource.DEFAULT)); // packSource
		}
	}

}
