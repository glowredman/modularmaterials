package glowredman.modularmaterials.tab;

import static glowredman.modularmaterials.Reference.MODID;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class CreativeTabOres extends CreativeTabs {

	public CreativeTabOres() {
		super(MODID + ".ores");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Blocks.IRON_ORE);
	}

}
