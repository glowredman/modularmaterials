package glowredman.modularmaterials.data.legacy;

import java.util.HashMap;
import java.util.Map.Entry;

import glowredman.modularmaterials.data.TagHandler;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.sub.BlockProperties;
import glowredman.modularmaterials.data.object.sub.FluidProperties;
import glowredman.modularmaterials.data.object.sub.FluidProperties.StateBasedProperties;
import glowredman.modularmaterials.data.object.sub.ItemProperties;
import glowredman.modularmaterials.data.object.sub.OreProperties;
import glowredman.modularmaterials.data.object.sub.TooltipProperties;

public class Material {
	
	public float blockHardness = 5.0f;
	public int blockHarvestLevel = 1;
	public byte blockLightLevel = 0;
	public String blockMapColor = "AUTO";
	public String blockMaterial = "IRON";
	public float blockResistance = 6.0f;
	public String blockSound = "METAL";
	public int boilingTemperature = 373;
	public Color color = new Color();
	public boolean enabled = false;
	public HashMap<String, Boolean> enabledTypes = new HashMap<>();
	public int gasDensity = -1000;
	public int gasLightLevel = 0;
	public int gasViscosity = 1;
	public boolean isBeaconBase = false;
	public boolean isBeaconPayment = false;
	public int liquidDensity = 1000;
	public byte liquidLightLevel = 0;
	public int liquidViscosity = 1000;
	public int meltingTemperature = 273;
	public String name = "material_" + this.hashCode();
	public String[] oreDict = new String[] {};
	public float oreHardness = 3.0f;
	public int oreHarvestLevel = 1;
	public byte oreLightLevel = 0;
	public float oreResistance = 0.0f;
	public String state = "solid";
	public int temperature = 293;
	public String texture = "metallic";
	public String[] tooltip = new String[] {};
	
	public MM_Material convert(TypeList types) {
		MM_Material m = new MM_Material();
		BlockProperties b = new BlockProperties();
		b.hardness = blockHardness;
		b.mapColor = blockMapColor;
		b.material = blockMaterial;
		b.sound = blockSound;
		String mAndTAreBB = "";
		if(isBeaconBase)
			for(Entry<String, Type> e : types.types.entrySet())
				if(e.getValue().isBeaconBase)
					mAndTAreBB += "," + e.getKey();
		if(!mAndTAreBB.isEmpty())
			b.tags.add(TagHandler.FILTER_TYPE_START + mAndTAreBB.substring(1) + TagHandler.FILTER_TYPE_END + "minecraft:beacon_base_blocks");
		String bhl = LegacyHandler.harvestLevel(blockHarvestLevel);
		if(bhl != null) b.tags.add(bhl);
		b.tags.add("minecraft:mineable/pickaxe");
		m.block = b;
		m.color = color.convert();
		m.enabled = enabled;
		m.enabledTypes = LegacyHandler.enabledTypes(enabledTypes);
		FluidProperties f = new FluidProperties();
		f.boilingTemperature = boilingTemperature;
		f.currentTemperature = temperature;
		f.meltingTemperature = meltingTemperature;
		StateBasedProperties g = new StateBasedProperties();
		g.density = gasDensity;
		g.luminosity = gasLightLevel;
		g.viscosity = gasViscosity;
		f.gas = g;
		StateBasedProperties l = new StateBasedProperties();
		l.density = liquidDensity;
		l.luminosity = liquidLightLevel;
		l.viscosity = liquidViscosity;
		f.liquid = l;
		m.fluid = f;
		ItemProperties i = new ItemProperties();
		String mAndTAreBP = "";
		if(isBeaconPayment)
			for(Entry<String, Type> e : types.types.entrySet())
				if(e.getValue().isBeaconPayment)
					mAndTAreBP += "," + e.getKey();
		if(!mAndTAreBP.isEmpty())
			i.tags.add(TagHandler.FILTER_TYPE_START + mAndTAreBP.substring(1) + TagHandler.FILTER_TYPE_END + "minecraft:beacon_payment_items");
		m.item = i;
		m.lightLevel = blockLightLevel;
		m.name = name;
		OreProperties o = new OreProperties();
		o.hardness = oreHardness;
		o.lightLevel = oreLightLevel;
		o.mapColor = blockMapColor;
		o.material = blockMaterial;
		o.resistance = oreResistance;
		o.sound = blockSound;
		String ohl = LegacyHandler.harvestLevel(oreHarvestLevel);
		if(ohl != null) o.tags.add(ohl);
		m.ore = o;
		m.resistance = blockResistance;
		m.state = LegacyHandler.state(state);
		m.tagNames = LegacyHandler.lowerCase(oreDict);
		m.texture = texture;
		TooltipProperties t = new TooltipProperties();
		t.text = tooltip;
		m.tooltip = t;
		return m;
	}

}
