package glowredman.modularmaterials.client;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.block.MetaBlock;
import glowredman.modularmaterials.data.ResourceLoader;
import glowredman.modularmaterials.item.MetaBlockItem;
import glowredman.modularmaterials.item.MetaItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ClientHandler {
	
	public ClientHandler() {
		Minecraft.getInstance().getResourcePackRepository().addPackFinder(new ResourceLoader(false));
		MinecraftForge.EVENT_BUS.addListener(this::colorTooltips);
	}
	
	@SubscribeEvent
	public void setupCommon(FMLCommonSetupEvent event) {
		AssetHandler.execute();
	}
	
	@SubscribeEvent
	public void setupClient(FMLClientSetupEvent event) {
		for(MetaBlock block : MM_Reference.BLOCKS) {
			ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped());
		}
	}
	
	@SubscribeEvent
	public void colorItems(ColorHandlerEvent.Item event) {
		
		//ITEMS
		for(MetaItem item : MM_Reference.ITEMS) {
			event.getItemColors().register((stack, tintIndex) -> {
				if(tintIndex == 0) {
					return item.material.color.getARGB();
				} else {
					return 0xFFFFFF;
				}
			}, item);
		}
		
		//BLOCKS
		for(MetaBlock block : MM_Reference.BLOCKS) {
			event.getItemColors().register((stack, tintIndex) -> {
				if(tintIndex == 0) {
					return block.material.color.getRGB();
				} else {
					return 0xFFFFFF;
				}
			}, block);
		}
	}
	
	@SubscribeEvent
	public void colorBlocks(ColorHandlerEvent.Block event) {
		
		//BLOCKS
		for(MetaBlock block : MM_Reference.BLOCKS) {
			event.getBlockColors().register((state, blockAndTintGetter, pos, tintIndex) -> {
				if(tintIndex == 0) {
					return block.material.color.getRGB();
				} else {
					return 0xFFFFFF;
				}
			}, block);
		}
	}
	
	private void colorTooltips(RenderTooltipEvent.Color event) {
		if(event.getStack().getItem() instanceof MetaItem) {
			MetaItem item = (MetaItem) event.getStack().getItem();
			if(item.type.hasTooltip) {
				event.setBackground(item.material.tooltip.getBackground());
				event.setBorderStart(item.material.tooltip.getBorderStart());
				event.setBorderEnd(item.material.tooltip.getBorderEnd());
			}
		}
		else if(event.getStack().getItem() instanceof MetaBlockItem) {
			MetaBlockItem item = (MetaBlockItem) event.getStack().getItem();
			if(item.block.type.hasTooltip) {
				event.setBackground(item.block.material.tooltip.getBackground());
				event.setBorderStart(item.block.material.tooltip.getBorderStart());
				event.setBorderEnd(item.block.material.tooltip.getBorderEnd());
			}
		}
	}

}
