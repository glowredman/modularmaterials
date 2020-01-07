package glowredman.modularmaterials.tab;

import glowredman.modularmaterials.information.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class CreativeTabOres extends CreativeTabs{

	public CreativeTabOres() {
		super(Reference.MODID + ".ores");
		this.setNoTitle();
		this.setBackgroundImageName("item_search.png");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Blocks.IRON_ORE);
	}
	
	@Override
	public boolean hasSearchBar() {
		return true;
	}

}
