package glowredman.modularmaterials.util;

import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;

public class TagHelper {
	
	public static <T> boolean hasTag(RegistryAccess registryAccess, T object, TagKey<T> tag) {
		Registry<T> registry = registryAccess.registryOrThrow(tag.registry());
		return registry.getHolderOrThrow(ResourceKey.create(tag.registry(), registry.getKey(object))).is(tag);
	}

}
