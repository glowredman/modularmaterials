package glowredman.modularmaterials.item;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import cofh.core.item.ItemMulti;
import cofh.core.util.core.IInitializer;
import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.information.PropertiesMaterials;
import glowredman.modularmaterials.information.Reference;
import glowredman.modularmaterials.json.JItem;
import glowredman.modularmaterials.util.MiscUtils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class ItemMisc extends ItemMulti implements IInitializer{
	
	private static ItemStack item;
	private static List<ItemStack> items = new ArrayList<ItemStack>();
	
	public ItemMisc() {
		super(Reference.MODID);
		this.func_77655_b("misc");
	}

	@Override
	public boolean preInit() {
		
		long time = System.nanoTime();
		int itemCount = 0;
		
		this.setRegistryName("misc");
		
		ForgeRegistries.ITEMS.register(this);
		
		for (JItem jItem : Reference.itemList.getItems()) {
			try {
				if (jItem.getName().getFormat()[0].contains(PropertiesMaterials.AN)) {
					//TODO implement custom text
					item = this.addItem(jItem.getMeta(), jItem.getName().getText().replace(' ', '_'), jItem.getName().getRarity());
				} // add item with vanilla formatting
				else {
					item = this.addItem(jItem.getMeta(), jItem.getName().getText().replace(' ', '_'), jItem.getName().getRarity());
				}
				items.add(item);
				itemCount++;
			} catch (Exception e) {
				Main.logger.error(e + " (item \"" + jItem.getName().getText() + "\" has no formatting for its name!)");
			}
			if (jItem.usesCustomTexture()) {
				Main.proxy.registerItemRenderer(this, jItem.getTexture(), jItem.getMeta());
			} else {
				Main.proxy.registerItemRenderer(this, jItem.getTexture() + '/' + jItem.getIconSet(), jItem.getMeta());
			}
			Main.logger.log(Level.DEBUG, "Registering '" + jItem.getName().getText() + "' with meta " + jItem.getMeta() + '.');
			item.getItem().setCreativeTab(Reference.TAB_VARIOUS);
			for (String s : jItem.getOreDict()) {
				OreDictionary.registerOre(s, item);
			}
		}
		
		Main.logger.info("Finished registering all " + itemCount + " miscelanious items. Took " + MiscUtils.timer(time));
		
		return true;
	}

	@Override
	public boolean initialize() {
		return false;
	}

}
