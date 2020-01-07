package glowredman.modularmaterials.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockStorage  extends ItemBlock {

	public ItemBlockStorage(Block block) {
		super(block);
		if (!(block instanceof IMetaBlockName)) {
			throw new IllegalArgumentException(String.format("The given Block %s is not an instance of IMetaBlockName!", block.getUnlocalizedName()));
		}
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack);
	}

}
