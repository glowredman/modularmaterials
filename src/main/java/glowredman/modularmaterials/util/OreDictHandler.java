package glowredman.modularmaterials.util;

import java.util.ArrayList;
import java.util.List;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.information.Reference;
import glowredman.modularmaterials.json.JMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHandler {
	
	private static float count = 0;
	
	public static void postInit() {
		
		long time = System.nanoTime();
		
		for (JMaterial material : Reference.materialList.getMaterials()) {

			registerUnitOreDict(material.getOreDict(), Reference.unitBlock, "block");
			registerUnitOreDict(material.getOreDict(), Reference.unitIngot, "ingot");
			registerUnitOreDict(material.getOreDict(), Reference.unitPlate, "plate");
		}
		
		Main.logger.info("Finished registering " + count + " additional OreDict-Entries. Took " + MiscUtils.timer(time));
		
	}
	
	
	public static void registerUnitOreDict(String[] oreDict, String unit, String preFix) {
		for (String material : oreDict) {
			for (ItemStack itemStack : OreDictionary.getOres(preFix + material)) {
				OreDictionary.registerOre(unit + material, itemStack);
				count++;
			}
			
		}
		
	}

}
