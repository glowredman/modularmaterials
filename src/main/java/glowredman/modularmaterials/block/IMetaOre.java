package glowredman.modularmaterials.block;

import glowredman.modularmaterials.data.TagHandler;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_OreVariant;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public interface IMetaOre {
	
	MM_Material getMaterial();
	
	MM_OreVariant getVariant();
	
	boolean obeysGravity();
	
	Block getBlock();
    
    ResourceLocation getRegistryName();
	
	default String getVariantIdentifier() {
        String s = this.getRegistryName().getPath().replace("ore.", "");
        return s.substring(0, s.indexOf("."));
	}
	
	default String getMaterialIdentifier() {
        String s = this.getRegistryName().getPath().replace("ore.", "");
        return s.substring(s.indexOf(".") + 1);
	}
	
	default String getLocalizedName() {
	    return this.getVariant().nameSyntax.replace(TagHandler.PARAM_MATERIAL, this.getMaterial().name);
	}

}
