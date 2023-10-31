package glowredman.modularmaterials.client;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.block.IMetaOre;
import glowredman.modularmaterials.block.MetaBlock;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.sub.ChemicalState;
import glowredman.modularmaterials.data.object.sub.PulseMode;
import glowredman.modularmaterials.fluid.MetaFluid;
import glowredman.modularmaterials.item.MetaBlockItem;
import glowredman.modularmaterials.item.MetaBucketItem;
import glowredman.modularmaterials.item.MetaItem;
import glowredman.modularmaterials.item.MetaOreBlockItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BucketItem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.event.TickEvent.Phase;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

public class ClientHandler {

    private boolean pulseDirectionPos = true;
	
	public ClientHandler() {
	    NeoForge.EVENT_BUS.addListener(this::colorTooltips);
	    NeoForge.EVENT_BUS.addListener(this::modifyTooltips);
	    NeoForge.EVENT_BUS.addListener(this::tickClient);
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
					return item.material.color.getPulseRGB();
				} else {
					return 0xFFFFFFFF;
				}
			}, item);
		}
		
		//BUCKETS
		for(MetaBucketItem bucket : MM_Reference.BUCKETS) {
			event.register((stack, tintIndex) -> {
				if(tintIndex == 1) {
				    MetaFluid fluid = bucket.fluid();
				    MM_Material material = fluid.material;
					return material.state == ChemicalState.SOLID && fluid.type.state == ChemicalState.LIQUID
					        ? material.color.getPulseMoltenARGB()
					        : material.color.getPulseARGB();
				} else {
					return 0xFFFFFFFF;
				}
			}, bucket);
		}
		
		//BLOCKS
		for(MetaBlock block : MM_Reference.BLOCKS) {
			event.register((stack, tintIndex) -> {
				if(tintIndex == 0) {
					return block.material.color.getPulseRGB();
				} else {
					return 0xFFFFFF;
				}
			}, block);
		}
		
		//ORES
		for(IMetaOre ore : MM_Reference.ORES.values()) {
			event.register((stack, tintIndex) -> {
				if(tintIndex == 1) {
					return ore.getMaterial().color.getPulseRGB();
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
					return block.material.color.getPulseRGB();
				} else {
					return 0xFFFFFF;
				}
			}, block);
		}
		
		//ORES
		for(IMetaOre ore : MM_Reference.ORES.values()) {
			event.register((state, blockAndTintGetter, pos, tintIndex) -> {
				if(tintIndex == 1) {
					return ore.getMaterial().color.getPulseRGB();
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
		if(event.getItemStack().getItem() instanceof BucketItem bucket) {
			event.getToolTip().add(Component.translatable("item.modularmaterials.bucket.desc", bucket.getFluid().getFluidType().getTemperature()).withStyle(ChatFormatting.GRAY));
		}
	}

    private void tickClient(TickEvent.ClientTickEvent event) {
        if(event.phase == Phase.START) return;
        
        if(this.pulseDirectionPos) {
            PulseMode.colorDeviation++;
            if(PulseMode.colorDeviation >= 50) {
                this.pulseDirectionPos = !this.pulseDirectionPos;
            }
        } else {
            PulseMode.colorDeviation--;
            if(PulseMode.colorDeviation <= 0) {
                this.pulseDirectionPos = !this.pulseDirectionPos;
            }
        }
    }
}
