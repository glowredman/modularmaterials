package glowredman.modularmaterials.block;

import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_OreVariant;
import net.minecraft.world.level.block.Block;

public interface IMetaOre {
	
	public MM_Material getMaterial();
	public MM_OreVariant getVariant();
	public boolean obeysGravity();
	public Block getBlock();
	public String getVariantIdentifier();
	public String getMaterialIdentifier();
	public String getLocalizedName();

}
