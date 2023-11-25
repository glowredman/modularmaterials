package glowredman.modularmaterials.item;

import java.util.Map.Entry;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.block.IMetaOre;
import glowredman.modularmaterials.block.MetaBlock;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_Type;
import glowredman.modularmaterials.data.object.sub.Category;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTab.TabVisibility;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

public class ItemHandler {
	
	private static CreativeModeTab tabBlocks;
	private static CreativeModeTab tabOres;
	private static CreativeModeTab tabItems;
	private static CreativeModeTab tabFluids;
	
	@SubscribeEvent
	public void registerItems(RegisterEvent event) {
		if(!event.getRegistryKey().equals(Registries.ITEM)) {
			return;
		}
		
		long time = System.currentTimeMillis();
		
		for(Entry<String, MM_Type> eType : MM_Reference.TYPES.entrySet()) {
			String typeName = eType.getKey();
			MM_Type type = eType.getValue();
			if(type.category == Category.ITEM && (type.enabled || MM_Reference.CONFIG.enableAll)) {
				for(Entry<String, MM_Material> eMaterial : MM_Reference.MATERIALS.entrySet()) {
					String materialName = eMaterial.getKey();
					MM_Material material = eMaterial.getValue();
					if((material.enabled && material.enabledTypes.contains(typeName)) || MM_Reference.CONFIG.enableAll) {
						ResourceLocation regName = new ResourceLocation(MM_Reference.MODID, typeName + "." + materialName);
						Registry.register(BuiltInRegistries.ITEM, regName, new MetaItem(material, type, regName));
					}
				}
			}
		}
		
		for(MetaBlock block : MM_Reference.BLOCKS) {
		    Registry.register(BuiltInRegistries.ITEM, block.registryName, new MetaBlockItem(block));
		}
		
		for(IMetaOre ore : MM_Reference.ORES.values()) {
		    Registry.register(BuiltInRegistries.ITEM, ore.getRegistryName(), new MetaOreBlockItem(ore));
		}
		
		ModularMaterials.LOGGER.info("Registered {} items. Took {}ms.", MM_Reference.ITEMS.size(), System.currentTimeMillis() - time);
	}
	
	@SubscribeEvent
	public void populateCreativeTabs(RegisterEvent event) {
        if(!event.getRegistryKey().equals(Registries.CREATIVE_MODE_TAB)) {
            return;
        }
        if(!MM_Reference.BLOCKS.isEmpty()) {
            tabBlocks = createCreativeModeTab("blocks", Blocks.IRON_BLOCK);
        }
        if(!MM_Reference.ORES.isEmpty()) {
            tabOres = createCreativeModeTab("ores", Blocks.IRON_ORE);
        }
        if(!MM_Reference.ITEMS.isEmpty()) {
            tabItems = createCreativeModeTab("items", Items.IRON_INGOT);
        }
        if(!MM_Reference.BUCKETS.isEmpty()) {
            tabFluids = createCreativeModeTab("fluids", Items.WATER_BUCKET);
        }
	}
	
	@SubscribeEvent
	public void populateCreativeTabs(BuildCreativeModeTabContentsEvent event) {
		if(event.getTab() == tabBlocks) {
			for(MetaBlock block : MM_Reference.BLOCKS) {
				event.accept(new ItemStack(block), TabVisibility.PARENT_AND_SEARCH_TABS);
			}
		} else if(event.getTab() == tabOres) {
			for(IMetaOre ore : MM_Reference.ORES.values()) {
				event.accept(new ItemStack(ore.getBlock()), TabVisibility.PARENT_AND_SEARCH_TABS);
			}
			
		} else if(event.getTab() == tabItems) {
			for(MetaItem item : MM_Reference.ITEMS) {
				event.accept(new ItemStack(item), TabVisibility.PARENT_AND_SEARCH_TABS);
			}
			
		} else if(event.getTab() == tabFluids) {
			for(MetaBucketItem bucket : MM_Reference.BUCKETS) {
				event.accept(new ItemStack(bucket), TabVisibility.PARENT_AND_SEARCH_TABS);
			}
			
		}
	}
	
	private static CreativeModeTab createCreativeModeTab(String name, ItemLike icon) {
	    CreativeModeTab tab = CreativeModeTab.builder().icon(() -> new ItemStack(icon)).title(Component.translatable("itemGroup." + MM_Reference.MODID + "." + name)).build();
	    return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(MM_Reference.MODID, name), tab);
	}

}
