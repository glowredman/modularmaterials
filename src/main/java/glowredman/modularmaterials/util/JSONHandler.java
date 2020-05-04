package glowredman.modularmaterials.util;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.Reference;
import glowredman.modularmaterials.material.Material;
import glowredman.modularmaterials.material.MaterialList;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class JSONHandler {
	
	public static void initFiles(FMLPreInitializationEvent event) {
		
		File fileMaterials = new File(event.getModConfigurationDirectory().getPath() + '/' + Reference.MODID, Reference.MATERIALCONFIGNAME);
		
		//an example to put in the file
		Material example = new Material();
		example.setBeaconPayment(true);
		example.setBlockHardness((float) (Math.random() * Float.MAX_VALUE));
		example.setBlockHarvestLevel((int) (Math.random() * 3));
		example.setBlockLightLevel((int) (Math.random() * 25));
		example.setBlockResistance((float) (Math.random() * Float.MAX_VALUE));
		example.setBoilingTemperature((int) (Math.random() * Integer.MAX_VALUE - 2 * Math.random() * Integer.MAX_VALUE));
		example.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random(), (float) Math.random()));
		example.setDisabled(false);
		example.setEnabledTypes(new Material().getAllTypesEqualHashMap(true));
		example.setGasDensity((int) (Math.random() * Integer.MAX_VALUE - 2 * Math.random() * Integer.MAX_VALUE));
		example.setGasViscosity((int) (Math.random() * Integer.MAX_VALUE));
		example.setLiquidDensity((int) (Math.random() * Integer.MAX_VALUE - 2 * Math.random() * Integer.MAX_VALUE));
		example.setLiquidViscosity((int) (Math.random() * Integer.MAX_VALUE));
		example.setMeltingTemperature((int) (Math.random() * Integer.MAX_VALUE - 2 * Math.random() * Integer.MAX_VALUE));
		example.setMeta(0);
		example.setName("Example Material");
		example.setOreDict(new String[] {"Example", "ExampleButDifferent"});
		example.setOreHardness((float) (Math.random() * Float.MAX_EXPONENT));
		example.setOreHarvestLevel((int) (Math.random() * 3));
		example.setOreHarvestLevel((int) (Math.random() * 3));
		example.setOreLightLevel((int) (Math.random() * 15));
		example.setOreResistance((float) (Math.random() * Float.MAX_VALUE));
		example.setState("solid");
		example.setTemperature((int) (Math.random() * Integer.MAX_VALUE - 2 * Math.random() * Integer.MAX_VALUE));
		example.setTooltip(new String[] {"§0Black §1Dark Blue §2Dark Green §3Dark Aqua", "§4Dark Red §5Dark Purple §6Gold §7Gray", "§8Dark Gray §9Blue §aGreen §bAqua", "§cRed §dLight Purple §eYellow §fWhite", "§kObfuscated", "§lBold", "§mStrikethrough", "§nUnderline", "§oItalic", "§PPress SHIFT", "§pRelease SHIFT", "§QPress CTRL", "§qRelease CTRL", "§TPress ALT", "§tRelease ALT", "§s1:50:§0,§1,§2,§3,§4,§5,§6,§7,§8,§9,§a,§b,§c,§d,§e,§f:Animated!§s", "§s1:50:§r§n,§r,§r,§r,§r,§r,§r,§r,§r,§r,§r,§r,§r,§r,§r,§r:This also works!§s"});
		
		MaterialList materialList = new MaterialList();
		materialList.materials.put("example", example);
		
		if(!fileMaterials.exists()) {
			try {
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileMaterials));
				
				writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(materialList));
				writer.close();
				
				Main.logger.info("Succesfully created \"" + fileMaterials + "\".");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			materialList = new Gson().fromJson(readFile(fileMaterials.getPath(), Charset.defaultCharset()), MaterialList.class);
			Reference.materials = materialList.getMaterials();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
