package glowredman.modularmaterials.proxy;

import glowredman.modularmaterials.Reference;
import glowredman.modularmaterials.util.ClientHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new ClientHandler());
		super.preInit(event);
	}
	
	@Override
	public void registerItemRenderer(Item item, String name, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Reference.MODID + ':' + name, "inventory"));
	}
		
	@Override
	public void registerItemRenderer(Item item, String name) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Reference.MODID + ':' + name, "inventory"));
	}

}
