package glowredman.modularmaterials.item;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.Reference;
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
		this.setRegistryName(Reference.MODID, type);
		this.setCreativeTab(Reference.TAB_ITEMS);
		
		Iterator i = MaterialHandler.getIterator(Reference.materials);
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
		if(Reference.idMapping.containsKey(stack.getMetadata())) {
			String[] lines = Reference.materials.get(Reference.idMapping.get(stack.getMetadata())).getTooltip();
			if(lines != null) {
				for(String line : lines) {
					try {
						String s = FormattingHandler.formatTooltipLine(line);
						if(s != null) {
							tooltip.add(s);
						}
					} catch (Exception e) {
						if(Reference.enableFormattingDebugger) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		Iterator i = MaterialHandler.getIterator(Reference.materials);
		while(i.hasNext()) {
			Entry<String, Material> entry = (Entry<String, Material>) i.next();
			Material material = entry.getValue();
			if((!material.isDisabled() && material.isTypeEnabled(this.getType())) || Reference.enableAll) {
				items.add(new ItemStack(this, 1, material.getMeta()));
			}
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		String s = "";
		try {
			s = "item." + Reference.MODID + '.' + this.getType() + '.' + Reference.idMapping.get(stack.getMetadata());
		} catch (Exception e) {
			s = "item." + Reference.MODID + ".debug";
		}
		return s;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerColors() {
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
			
			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {
				if(tintIndex == 0) {
					return Reference.materials.get(Reference.idMapping.get(stack.getMetadata())).getColor().getARGB();
				} else {
					return 0xFFFFFF;
				}
			}
		}, this);
	}

}
