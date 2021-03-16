package glowredman.modularmaterials.item;

import static glowredman.modularmaterials.Main.logger;
import static glowredman.modularmaterials.Main.proxy;
import static glowredman.modularmaterials.Reference.*;

import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import glowredman.modularmaterials.object.JMaterial;
import glowredman.modularmaterials.util.FormattingHandler;
import glowredman.modularmaterials.util.MaterialHandler;
import net.minecraft.client.Minecraft;
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
		for (JMaterial material : materials.values()) {
			if (enableAll || (material.isTypeEnabled(type) && material.enabled)) {
				proxy.registerItemRenderer(this, material.texture + '/' + type, material.meta);
			}
		}
	}

	@Override
	public boolean isBeaconPayment(ItemStack stack) {
		return MaterialHandler.getMaterialFromID((short) stack.getMetadata()).isBeaconPayment;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		try {
			JMaterial material = MaterialHandler.getMaterialFromID((short) stack.getMetadata());
			if ((enableAll || (material.enabled && material.isTypeEnabled(type))) && material.tooltip != null) {
				for (String line : material.tooltip) {
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
		} catch (Exception e) {
		}
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (tab.equals(TAB_ITEMS) || tab.equals(CreativeTabs.SEARCH)) {
			for (JMaterial material : materials.values()) {
				try {
					if (enableAll || (material.enabled && material.isTypeEnabled(type) && types.get(type).enabled)) {
						items.add(new ItemStack(this, 1, material.meta));
					}
				} catch (Exception e) {
					logger.warn(CONFIGNAME_TYPES + " does not contain information for the type \"" + type + "\"! Add \""
							+ type + "\" to " + CONFIGNAME_TYPES + " or enable 'suppressMissingTypeWarnings' in "
							+ CONFIGNAME_CORE + '.');
				}
			}
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		short meta = (short) stack.getMetadata();
		String s;
		try {
			JMaterial material = MaterialHandler.getMaterialFromID(meta);
			if (enableAll || (material.enabled && material.isTypeEnabled(type))) {
				s = "item." + MODID + '.' + type + '.' + idMaterialMapping.get(meta);
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
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
			if (tintIndex == 0) {
				return MaterialHandler.getMaterialFromID((short) stack.getMetadata()).color.getARGB();
			} else {
				return 0xFFFFFF;
			}
		}, this);
	}

}
