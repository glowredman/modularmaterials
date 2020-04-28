package glowredman.modularmaterials.api;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import static glowredman.modularmaterials.api.MaterialList.*;

import glowredman.modularmaterials.Main;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class MetaItem extends Item {
	
	public String type;
	
	public MetaItem(String type) {
		this.setType(type);
		this.setHasSubtypes(true);
		this.setRegistryName(Reference.MODID, type);
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		//find the right entry
		Iterator i = getIterator(materials);
		while(i.hasNext()) {
			Entry entry = (Entry) i.next();
			Material material = (Material) entry.getValue();
			if((material.getMeta() == stack.getMetadata())) {
				//transfer the tooltip-information
				for(String line : material.getTooltip()) {
					try {
						tooltip.add(Formatting.formatTooltipLine(line));
					} catch (Exception e) {
						Main.logger.error("Failed formatting \"" + line + "\" of material \"" + entry.getKey() + "\":");
					}
				}
				break;
			}
		}
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		Iterator i = getIterator(materials);
		while(i.hasNext()) {
			Material material = (Material) ((Entry) i.next()).getValue();
			items.add(new ItemStack(this, 1, material.getMeta()));
		}
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "item." + Reference.MODID + this.getType() + '.' + idMapping.get(stack.getMetadata() + ".name");
	}

}
