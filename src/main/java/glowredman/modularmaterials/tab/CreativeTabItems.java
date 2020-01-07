package glowredman.modularmaterials.tab;

import glowredman.modularmaterials.information.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CreativeTabItems extends CreativeTabs{

	public CreativeTabItems() {
		super(Reference.MODID + ".items");
		this.setNoTitle();
		this.setBackgroundImageName("item_search.png");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Items.IRON_INGOT);
	}
	
	@Override
	public boolean hasSearchBar() {
		return true;
	}

}
