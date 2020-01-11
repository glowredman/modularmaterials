package glowredman.modularmaterials.item;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import cofh.core.item.ItemMulti;
import cofh.core.util.core.IInitializer;
import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.information.PropertiesMaterials;
import glowredman.modularmaterials.information.Reference;
import glowredman.modularmaterials.json.JMaterial;
import glowredman.modularmaterials.util.MiscUtils;
import glowredman.modularmaterials.util.TagHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class ItemPlate extends ItemMulti implements IInitializer {
	
	private static ItemStack plate;
	private static List<ItemStack> plates = new ArrayList<ItemStack>();
	
	public ItemPlate() {
		super(Reference.MODID);
		this.func_77655_b("meta");
	}

	@Override
	public boolean preInit() {
		
		long time = System.nanoTime();
		int plateCount = 0;
		
		this.setRegistryName("plate");
		
		//register base-item
		ForgeRegistries.ITEMS.register(this);
		
		for (JMaterial material : Reference.materialList.getMaterials()) {
			if (TagHandler.isValidPlate(material)) {
				//add item with custom formatting
				try {
					if (material.getName().getFormat()[0].contains(PropertiesMaterials.AN)) {
						//TODO implement custom text
						plate = this.addItem(material.getMeta(), "plate" + material.getName().getText().replace(' ', '_'), material.getName().getRarity());
					} //add item with vanilla formatting
					else {
						plate = this.addItem(material.getMeta(), "plate" + material.getName().getText().replace(' ', '_'), material.getName().getRarity());
					}
					plates.add(plate);
					plateCount++;
				} catch (Exception e) {
					Main.logger.error(e + " (material \"" + material.getName().getText() + "\" has no formatting for its name!)");
				}
				
				Main.proxy.registerItemRenderer(this, "plate/" + material.getTextureSet(), material.getMeta());
				Main.logger.log(Level.DEBUG, "Registering '" + material.getName() + "' with meta " + material.getMeta() + '.');
				plate.getItem().setCreativeTab(Reference.TAB_ITEMS);
				for (String s : material.getOreDict()) {
					OreDictionary.registerOre("plate" + s, plate);
				}
			}
		}
		
		Main.logger.info("Finished registering all " + plateCount + " plates. Took " + MiscUtils.timer(time));
		
		return true;
	}

	@Override
	public boolean initialize() {
		return true;
	}

}
