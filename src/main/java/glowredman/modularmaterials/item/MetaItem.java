package glowredman.modularmaterials.item;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import glowredman.modularmaterials.Main;
import static glowredman.modularmaterials.Reference.*;
import glowredman.modularmaterials.object.Material;
import glowredman.modularmaterials.util.FormattingHandler;
import glowredman.modularmaterials.util.MaterialHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MetaItem extends Item {
	
	public String type;
	
	public MetaItem(String type) {
		this.type = type;
		this.setHasSubtypes(true);
		this.setRegistryName(MODID, type);
		this.setCreativeTab(TAB_ITEMS);
		for(Material material : materials.values()) {
			if(enableAll || (material.isTypeEnabled(type) && material.enabled)) {
				Main.proxy.registerItemRenderer(this, material.texture + '/' + type, material.meta);
			}
		}
	}
	
	@Override
	public boolean isBeaconPayment(ItemStack stack) {
		return MaterialHandler.getMaterialFromID(stack.getMetadata()).isBeaconPayment;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		try {
			Material material = MaterialHandler.getMaterialFromID(stack.getMetadata());
			if (enableAll || (material.enabled && material.isTypeEnabled(type))) {
				String[] lines = MaterialHandler.getMaterialFromID(stack.getMetadata()).tooltip;
				if (lines != null) {
					for (String line : lines) {
						try {
							String s = FormattingHandler.formatTooltipLine(line);
							if (s != null) {
								tooltip.add(s);
							}
						} catch (Exception e) {
							if (enableFormattingDebugger) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		} catch (Exception e) {
		} 
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if(tab.equals(TAB_ITEMS)) {
			Iterator i = MaterialHandler.getIterator(materials);
			while(i.hasNext()) {
				Entry<String, Material> entry = (Entry<String, Material>) i.next();
				Material material = entry.getValue();
				try {
					if(enableAll || (material.enabled && material.isTypeEnabled(type) && types.get(type).enabled)) {
						items.add(new ItemStack(this, 1, material.meta));
					}
				} catch (Exception e) {
					Main.logger.warn(CONFIGNAME_TYPES + " does not contain information for the type \"" + type + "\"! Add \"" + type + "\" to " + CONFIGNAME_TYPES + " or enable 'suppressMissingTypeWarnings' in " + CONFIGNAME_CORE + '.');
				}
			}
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int meta = stack.getMetadata();
		String s = "";
		try {
			Material material = MaterialHandler.getMaterialFromID(meta);
			if(enableAll || (material.enabled && material.isTypeEnabled(type))) {
				s = "item." + MODID + '.' + type + '.' + idMapping.get(meta);
			} else {
				s = "item." + MODID + ".debug";
			}
		} catch (Exception e) {
			s = "item." + MODID + ".debug";
		}
		return s;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerColors() {
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
			
			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {
				if(tintIndex == 0) {
					return MaterialHandler.getMaterialFromID(stack.getMetadata()).color.getARGB();
				} else {
					return 0xFFFFFF;
				}
			}
		}, this);
	}

}
