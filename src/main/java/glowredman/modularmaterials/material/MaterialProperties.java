package glowredman.modularmaterials.material;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class MaterialProperties {
	
	public static void loadMaterials() {
		
		Material m0 = new Material();
		Material m1 = new Material();
		Material m2 = new Material();
		Material m3 = new Material();
		
		m0.setDisabled(false);
		m0.setEnabledTypes(new Material().getAllTypesEqualHashMap(true));
		m0.setMeta(0);
		m0.setName("m0");
		m0.setTexture("null");
		m0.setTooltip(new String[] {"§00 §11 §22 §33 §44 §55 §66 §77 §88 §99 §aa §bb §cc §dd §ee §ff", "k §kMinecraft", "l §lMinecraft", "m §mMinecraft", "n §nMinecraft", "o §oMinecraft", "§pp SHIFT", "§PP UN-SHIFT", "§qq CTRL", "§QQ UN-CTRL", "s §s1:40:§0,§1,§2,§3,§4,§5,§6,§7,§8,§9,§a,§b,§c,§d,§e,§f:Minecraft§s", "§tt ALT", "§TT UN-ALT"});
		
		m1.setDisabled(false);
		m1.setEnabledTypes(new Material().getAllTypesEqualHashMap(true));
		m1.setMeta(1);
		m1.setName("m1");
		m1.setTexture("null");
		m1.setTooltip(new String[] {"§00 §11 §22 §33 §44 §55 §66 §77 §88 §99 §aa §bb §cc §dd §ee §ff", "k §kMinecraft", "l §lMinecraft", "m §mMinecraft", "n §nMinecraft", "o §oMinecraft", "§pp SHIFT", "§PP UN-SHIFT", "§qq CTRL", "§QQ UN-CTRL", "s §s1:40:§0,§1,§2,§3,§4,§5,§6,§7,§8,§9,§a,§b,§c,§d,§e,§f:Minecraft§s", "§tt ALT", "§TT UN-ALT"});
		
		m2.setDisabled(false);
		m2.setEnabledTypes(new Material().getAllTypesEqualHashMap(true));
		m2.setMeta(2);
		m2.setName("m2");
		m2.setTexture("null");
		m2.setTooltip(new String[] {"§00 §11 §22 §33 §44 §55 §66 §77 §88 §99 §aa §bb §cc §dd §ee §ff", "k §kMinecraft", "l §lMinecraft", "m §mMinecraft", "n §nMinecraft", "o §oMinecraft", "§pp SHIFT", "§PP UN-SHIFT", "§qq CTRL", "§QQ UN-CTRL", "s §s1:40:§0,§1,§2,§3,§4,§5,§6,§7,§8,§9,§a,§b,§c,§d,§e,§f:Minecraft§s", "§tt ALT", "§TT UN-ALT"});
		
		m3.setDisabled(false);
		m3.setEnabledTypes(new Material().getAllTypesEqualHashMap(true));
		m3.setMeta(3);
		m3.setName("m3");
		m3.setTexture("null");
		m3.setTooltip(new String[] {"§00 §11 §22 §33 §44 §55 §66 §77 §88 §99 §aa §bb §cc §dd §ee §ff", "k §kMinecraft", "l §lMinecraft", "m §mMinecraft", "n §nMinecraft", "o §oMinecraft", "§pp SHIFT", "§PP UN-SHIFT", "§qq CTRL", "§QQ UN-CTRL", "s §s1:40:§0,§1,§2,§3,§4,§5,§6,§7,§8,§9,§a,§b,§c,§d,§e,§f:Minecraft§s", "§tt ALT", "§TT UN-ALT"});
		
		MaterialList.materials.put("m0", m0);
		MaterialList.materials.put("m1", m1);
		MaterialList.materials.put("m2", m2);
		MaterialList.materials.put("m3", m3);
		
	}

}
