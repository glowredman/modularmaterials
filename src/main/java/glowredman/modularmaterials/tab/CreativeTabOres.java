package glowredman.modularmaterials.tab;

import glowredman.modularmaterials.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class CreativeTabOres extends CreativeTabs {

	public CreativeTabOres() {
		super(Reference.MODID + ".ores");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Blocks.IRON_ORE);
	}

}
