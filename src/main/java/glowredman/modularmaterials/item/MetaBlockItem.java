package glowredman.modularmaterials.item;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.block.MetaBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class MetaBlockItem extends BlockItem {
	
	public final MetaBlock block;

	public MetaBlockItem(MetaBlock block) {
		super(block, new Properties()
				.setNoRepair()
				.rarity(block.material.item.rarity.get()));
		this.block = block;
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
		return (int) (block.material.burnTime * block.type.burnTimeMultiplier);
	}
	
	@Override
	public int getEntityLifespan(ItemStack itemStack, Level world) {
		return block.material.item.lifespan;
	}
	
	@Override
	public boolean isFireResistant() {
		return block.material.item.isFireResistant;
	}
	
	@Override
	public boolean isFoil(ItemStack pStack) {
		return MM_Reference.CONFIG.blocksHaveFoilEffect && block.material.item.isFoil;
	}

}
