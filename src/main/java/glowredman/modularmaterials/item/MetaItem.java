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
		this.setType(type);
		this.setHasSubtypes(true);
		this.setRegistryName(MODID, type);
		this.setCreativeTab(TAB_ITEMS);
		
		Iterator i = MaterialHandler.getIterator(materials);
		while(i.hasNext()) {
			Entry<Integer, Material> entry = (Entry<Integer, Material>) i.next();
			Main.proxy.registerItemRenderer(this, entry.getValue().getTexture() + '/' + type, entry.getValue().getMeta());
		}
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(idMapping.containsKey(stack.getMetadata())) {
			String[] lines = MaterialHandler.getMaterialFromID(stack.getMetadata()).getTooltip();
			if(lines != null) {
				for(String line : lines) {
					try {
						String s = FormattingHandler.formatTooltipLine(line);
						if(s != null) {
							tooltip.add(s);
						}
					} catch (Exception e) {
						if(enableFormattingDebugger) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if(tab.equals(TAB_ITEMS)) {
			Iterator i = MaterialHandler.getIterator(materials);
			while(i.hasNext()) {
				Entry<String, Material> entry = (Entry<String, Material>) i.next();
				Material material = entry.getValue();
				if((material.isEnabled() && material.isTypeEnabled(this.getType())) || enableAll) {
					items.add(new ItemStack(this, 1, material.getMeta()));
				}
			}
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		String s = "";
		try {
			s = "item." + MODID + '.' + this.getType() + '.' + idMapping.get(stack.getMetadata());
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
					return MaterialHandler.getMaterialFromID(stack.getMetadata()).getColor().getARGB();
				} else {
					return 0xFFFFFF;
				}
			}
		}, this);
	}

}
