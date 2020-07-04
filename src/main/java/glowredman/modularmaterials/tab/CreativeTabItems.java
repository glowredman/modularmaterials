package glowredman.modularmaterials.tab;

import static glowredman.modularmaterials.Reference.MODID;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CreativeTabItems extends CreativeTabs {

	public CreativeTabItems() {
		super(MODID + ".items");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Items.IRON_INGOT);
	}

}
