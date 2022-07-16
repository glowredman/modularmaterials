package glowredman.modularmaterials.client;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.block.IMetaOre;
import glowredman.modularmaterials.block.MetaBlock;
import glowredman.modularmaterials.item.MetaBlockItem;
import glowredman.modularmaterials.item.MetaBucketItem;
import glowredman.modularmaterials.item.MetaItem;
import glowredman.modularmaterials.item.MetaOreBlockItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BucketItem;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ClientHandler {
	
	public ClientHandler() {
		MinecraftForge.EVENT_BUS.addListener(this::colorTooltips);
		MinecraftForge.EVENT_BUS.addListener(this::modifyTooltips);
	}
	
	@SubscribeEvent
	public void setupCommon(FMLCommonSetupEvent event) {
		AssetHandler.execute();
	}
	
	@SubscribeEvent
	public void colorItems(RegisterColorHandlersEvent.Item event) {
		
		//ITEMS
		for(MetaItem item : MM_Reference.ITEMS) {
			event.register((stack, tintIndex) -> {
				if(tintIndex == 0) {
					return item.material.color.getRGB();
				} else {
					return 0xFFFFFFFF;
				}
			}, item);
		}
		
		//BLOCKS
		for(MetaBlock block : MM_Reference.BLOCKS) {
			event.register((stack, tintIndex) -> {
				if(tintIndex == 0) {
					return block.material.color.getRGB();
				} else {
					return 0xFFFFFF;
				}
			}, block);
		}
		
		//ORES
		for(IMetaOre ore : MM_Reference.ORES.values()) {
			event.register((stack, tintIndex) -> {
				if(tintIndex == 1) {
					return ore.getMaterial().color.getRGB();
				} else {
					return 0xFFFFFF;
				}
			}, ore.getBlock());
		}
	}
	
	@SubscribeEvent
	public void colorBlocks(RegisterColorHandlersEvent.Block event) {
		
		//BLOCKS
		for(MetaBlock block : MM_Reference.BLOCKS) {
			event.register((state, blockAndTintGetter, pos, tintIndex) -> {
				if(tintIndex == 0) {
					return block.material.color.getRGB();
				} else {
					return 0xFFFFFF;
				}
			}, block);
		}
		
		//ORES
		for(IMetaOre ore : MM_Reference.ORES.values()) {
			event.register((state, blockAndTintGetter, pos, tintIndex) -> {
				if(tintIndex == 1) {
					return ore.getMaterial().color.getRGB();
				} else {
					return 0xFFFFFF;
				}
			}, ore.getBlock());
		}
	}
	
	private void colorTooltips(RenderTooltipEvent.Color event) {
		if(event.getItemStack().getItem() instanceof MetaItem) {
			MetaItem item = (MetaItem) event.getItemStack().getItem();
			if(item.type.hasTooltip) {
				event.setBackground(item.material.tooltip.getBackground());
				event.setBorderStart(item.material.tooltip.getBorderStart());
				event.setBorderEnd(item.material.tooltip.getBorderEnd());
			}
		}
		else if(event.getItemStack().getItem() instanceof MetaBlockItem) {
			MetaBlockItem item = (MetaBlockItem) event.getItemStack().getItem();
			if(item.block.type.hasTooltip) {
				event.setBackground(item.block.material.tooltip.getBackground());
				event.setBorderStart(item.block.material.tooltip.getBorderStart());
				event.setBorderEnd(item.block.material.tooltip.getBorderEnd());
			}
		}
		else if(event.getItemStack().getItem() instanceof MetaBucketItem) {
			MetaBucketItem item = (MetaBucketItem) event.getItemStack().getItem();
			if(item.fluid().type.hasTooltip) {
				event.setBackground(item.fluid().material.tooltip.getBackground());
				event.setBorderStart(item.fluid().material.tooltip.getBorderStart());
				event.setBorderEnd(item.fluid().material.tooltip.getBorderEnd());
			}
		}
		else if(event.getItemStack().getItem() instanceof MetaOreBlockItem) {
			MetaOreBlockItem item = (MetaOreBlockItem) event.getItemStack().getItem();
			if(MM_Reference.CONFIG.oresHaveTooltip) {
				event.setBackground(item.ore.getMaterial().tooltip.getBackground());
				event.setBorderStart(item.ore.getMaterial().tooltip.getBorderStart());
				event.setBorderEnd(item.ore.getMaterial().tooltip.getBorderEnd());
			}
		}
	}
	
	private void modifyTooltips(ItemTooltipEvent event) {
		if(event.getItemStack().getItem() instanceof BucketItem) {
			event.getToolTip().add(Component.literal("Temperature: " + ((BucketItem) event.getItemStack().getItem()).getFluid().getFluidType().getTemperature() + "K"));
		}
	}

}
