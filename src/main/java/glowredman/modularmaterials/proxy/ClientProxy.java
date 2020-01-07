package glowredman.modularmaterials.proxy;

import glowredman.modularmaterials.information.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void registerItemRenderer(Item item, String name, int meta){
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Reference.MODID + ":" + name, "inventory"));
	}
	
	@Override
	public void registerItemRenderer(Item item, String name) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Reference.MODID + ":" + name, "inventory"));
	}

}
