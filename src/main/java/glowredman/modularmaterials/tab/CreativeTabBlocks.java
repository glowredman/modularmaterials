package glowredman.modularmaterials.tab;

import static glowredman.modularmaterials.Reference.MODID;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class CreativeTabBlocks extends CreativeTabs {

	public CreativeTabBlocks() {
		super(MODID + ".blocks");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Blocks.IRON_BLOCK);
	}

}
