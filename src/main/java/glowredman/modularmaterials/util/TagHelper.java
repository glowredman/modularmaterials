package glowredman.modularmaterials.util;

import java.util.List;
import java.util.stream.Stream;

import net.minecraft.tags.TagKey;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.tags.IReverseTag;

public class TagHelper {
	
	public static <T extends IForgeRegistryEntry<T>> List<TagKey<T>> getTags(IForgeRegistry<T> registry, T object) {
		return registry.tags().getReverseTag(object).map(IReverseTag::getTagKeys).orElseGet(Stream::of).toList();
	}
	
	public static <T extends IForgeRegistryEntry<T>> boolean hasTag(IForgeRegistry<T> registry, T object, TagKey<T> tag) {
		return registry.tags().getTag(tag).contains(object);
	}

}
