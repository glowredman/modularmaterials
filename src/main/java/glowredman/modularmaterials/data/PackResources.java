package glowredman.modularmaterials.data;

import java.io.File;

import net.minecraft.server.packs.FolderPackResources;

public class PackResources extends FolderPackResources {

	private final String name;

	public PackResources(File file, String name) {
		super(file);
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
