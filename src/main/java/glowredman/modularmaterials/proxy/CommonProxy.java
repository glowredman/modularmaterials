package glowredman.modularmaterials.proxy;

import glowredman.modularmaterials.block.BlockStorage;
import glowredman.modularmaterials.item.ItemIngot;
import glowredman.modularmaterials.util.ConfigHandler;
import glowredman.modularmaterials.util.JSONHandler;
import glowredman.modularmaterials.util.FileHandler;
import glowredman.modularmaterials.util.OreDictHandler;
import glowredman.modularmaterials.util.TagHandler;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.preInit1(event);
		ConfigHandler.readConfig();
		ConfigHandler.preInit2();
		JSONHandler.preInit(event);
		TagHandler.checkMetaValues();
		BlockStorage.preInit();
		FileHandler.createLangFile(event);
		FileHandler.createModelFiles(event);
	}

	public void init(FMLInitializationEvent event) {
	}

	public void postInit(FMLPostInitializationEvent event) {
		OreDictHandler.postInit();
	}
	
	public void registerItemRenderer(Item item, String name, int meta) {
	}

	public void registerItemRenderer(Item item, String name) {		
	}

}
