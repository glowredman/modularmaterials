package glowredman.modularmaterials.item;

import glowredman.modularmaterials.information.Reference;
import net.minecraft.item.Item;

public class ItemTest extends Item {
	
	public ItemTest(String name) {
		this.setRegistryName(Reference.MODID, name);
		this.setUnlocalizedName(Reference.MODID + "." + name);
		
	}

}
