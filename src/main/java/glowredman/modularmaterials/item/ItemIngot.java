package glowredman.modularmaterials.item;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import cofh.core.item.ItemMulti;
import cofh.core.util.core.IInitializer;
import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.information.Properties;
import glowredman.modularmaterials.information.Reference;
import glowredman.modularmaterials.json.JMaterial;
import glowredman.modularmaterials.util.MiscUtils;
import glowredman.modularmaterials.util.TagHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemIngot extends ItemMulti implements IInitializer{
	
	private static ItemStack ingot;
	private static List<ItemStack> ingots = new ArrayList<ItemStack>();
	
	public ItemIngot() {
		super(Reference.MODID);
		this.func_77655_b("meta");
	}

	@Override
	public boolean preInit() {
		
		long time = System.nanoTime();
		int ingotCount = 0;
		
		this.setRegistryName("ingot");
		
		//register base-item
		ForgeRegistries.ITEMS.register(this);
		
		//register first item (metadata=0) for safety
		/*ingot = this.addItem(0, "ingotNull");
		Main.proxy.registerItemRenderer(this, "ingot/metallic");*/
		
		for (JMaterial material : Reference.materialList.getMaterials()) {
			if (TagHandler.isValidIngot(material)) {
				//add item with custom formatting
				try {
					if (material.getName().getFormat()[0].contains(Properties.AN)) {
						//TODO implement custom text
						ingot = this.addItem(material.getMeta(), "ingot" + material.getName().getText().replace(' ', '_'), material.getName().getRarity());
					} // add item with vanilla formatting
					else {
						ingot = this.addItem(material.getMeta(), "ingot" + material.getName().getText().replace(' ', '_'), material.getName().getRarity());
					}
					ingots.add(ingot);
					ingotCount++;
				} catch (Exception e) {
					Main.logger.error(e + " (material \"" + material.getName().getText() + "\" has no formatting for its name!)");
				}
				
				Main.proxy.registerItemRenderer(this, "ingot/" + material.getTextureSet(), material.getMeta());
				Main.logger.log(Level.DEBUG, "Registering '" + material.getName() + "' with meta " + material.getMeta() + '.');
				ingot.getItem().setCreativeTab(Reference.TAB_ITEMS);
				for (String s : material.getOreDict()) {
					OreDictionary.registerOre("ingot" + s, ingot);
				}
			}
		}

		Main.logger.info("Finished registering all " + ingotCount + " ingots. Took " + MiscUtils.timer(time));
		
		return true;
	}

	@Override
	public boolean initialize() {
		
		initItemColors();
		
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	void initItemColors() {
		//TODO will be implemented, when I understand it
	}

}
