package glowredman.modularmaterials.item;

import java.util.ArrayList;
import java.util.Iterator;

import cofh.core.util.core.IInitializer;
import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.information.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static final ModItems INSTANCE = new ModItems();
	private static ArrayList<IInitializer> initList = new ArrayList<>();
	
	//  all different item-types
	public static ItemIngot ingot;
	public static ItemMisc itemMisc;
	public static ItemPlate plate;
	
	public static void preInit() {
		
		//adds the different item-types to 'initList' depending on configuration
		if(Reference.enableIngots || Reference.enableAll) {
			ingot = new ItemIngot();
			initList.add(ingot);
		}
		if(Reference.enablePlates || Reference.enableAll) {
			plate = new ItemPlate();
			initList.add(plate);
		}
		if(Reference.enableMiscItems || Reference.enableAll) {
			itemMisc = new ItemMisc();
			initList.add(itemMisc);
		}
		
		Iterator arg = initList.iterator();
		
		while (arg.hasNext()) {
			IInitializer init = (IInitializer) arg.next();
			init.preInit();
		}
		
		MinecraftForge.EVENT_BUS.register(INSTANCE);
		
	}
	
	// -=-=-=-=-=-=-=-
	
	public static void init() {
		
	}
	
	public static void register() {
		
	}
	
	public static void registerRenders() {
		
	}
	
	public static void RegisterRender(Item item, String textureName) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(Reference.MODID, textureName), "inventory"));
	}

}
