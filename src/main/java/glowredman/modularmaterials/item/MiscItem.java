package glowredman.modularmaterials.item;

import static glowredman.modularmaterials.Reference.*;

import java.util.List;

import javax.annotation.Nullable;

import glowredman.modularmaterials.object.JMiscItem;
import glowredman.modularmaterials.util.FormattingHandler;
import glowredman.modularmaterials.util.MaterialHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MiscItem extends Item {
	
	public MiscItem() {
		this.setHasSubtypes(true);
		this.setRegistryName(MODID, "miscItem");
		for(JMiscItem item : miscItems.values()) {
			if(enableAll || item.enabled) {
				ModelLoader.setCustomModelResourceLocation(this, item.meta, new ModelResourceLocation(item.texture, "inventory"));
			}
		}
	}
	
	@Override
	public boolean isBeaconPayment(ItemStack stack) {
		return MaterialHandler.getMiscItemFromID((short) stack.getMetadata()).isBeaconPayment;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		try {
			JMiscItem item = MaterialHandler.getMiscItemFromID((short) stack.getMetadata());
			if((enableAll || item.enabled) && item.tooltip != null) {
				for(String line : item.tooltip) {
					try {
						String s = FormattingHandler.formatTooltipLine(line);
						if(s != null) {
							tooltip.add(s);
						}
					} catch (Exception e) {
						if (enableFormattingDebugger) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
		}
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if(tab.equals(TAB_ITEMS) || tab.equals(CreativeTabs.SEARCH)) {
			for(JMiscItem item : miscItems.values()) {
				if(enableAll || item.enabled) {
					items.add(new ItemStack(this, 1, item.meta));
				}
			}
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		short meta = (short) stack.getMetadata();
		String s;
		try {
			JMiscItem item = MaterialHandler.getMiscItemFromID(meta);
			if(enableAll || item.enabled) {
				s = "item." + MODID + ".miscItem." + idMiscMapping.get(meta);
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
					JMiscItem item = MaterialHandler.getMiscItemFromID((short) stack.getMetadata());
					return item.useColor ? item.color.getARGB() : 0xFFFFFFFF;
				} else {
					return 0xFFFFFFFF;
				}
			}
		}, this);
	}

}
