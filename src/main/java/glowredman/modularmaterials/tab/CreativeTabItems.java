package glowredman.modularmaterials.tab;

import glowredman.modularmaterials.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CreativeTabItems extends CreativeTabs {

	public CreativeTabItems() {
		super(Reference.MODID + ".items");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Items.IRON_INGOT);
	}

}
