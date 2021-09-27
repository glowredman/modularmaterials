package glowredman.modularmaterials.data.object;

import static glowredman.modularmaterials.MM_Reference.RAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import glowredman.modularmaterials.client.FormattingHandler;
import glowredman.modularmaterials.data.object.sub.*;
import net.minecraft.network.chat.Component;

public class MM_Material {

	public String name = "material_" + this.hashCode();
	public String[] tagNames = new String[] {name};
	public boolean enabled = false;
	public List<String> enabledTypes = new ArrayList<>();
	public ColorProperties color = new ColorProperties();
	public int burnTime = 0;
	public ChemicalState state = ChemicalState.SOLID;
	public String texture = "metallic";
	public TooltipProperties tooltip = new TooltipProperties();
	public BlockProperties block = new BlockProperties();
	public FluidProperties fluid = new FluidProperties();
	public ItemProperties item = new ItemProperties();
	public OreProperties ore = new OreProperties();

	public void createTooltip(List<Component> tooltip) {
		for(String line : this.tooltip.text) {
			tooltip.add(FormattingHandler.formatTooltipLine(line));
		}
	}
	
	@Override
	public String toString() {
		return String.format("{name: %s, tagNames: %s, enabled: %b, enabledTypes: %s, color: %s, burnTime: %d, state: %s, texture: %s, tooltip: %s, block: %s, fluid: %s, item: %s, ore: %s}",
				name, FormattingHandler.arrayToString(tagNames), enabled, FormattingHandler.listToString(enabledTypes), color, burnTime, state, texture, tooltip, block, fluid, item, ore);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof MM_Material)) {
			return false;
		}
		return this.name.equals(((MM_Material) obj).name);
	}
	
	public static MM_Material random() {
		MM_Material m = new MM_Material();
		m.block = BlockProperties.random();
		m.burnTime = RAND.nextInt(10000);
		m.color = ColorProperties.random();
		m.enabled = RAND.nextBoolean();
		m.enabledTypes = Arrays.asList("example");
		m.fluid = FluidProperties.random();
		m.item = ItemProperties.random();
		m.name = "material_" + m.hashCode();
		m.ore = OreProperties.random();
		m.state = ChemicalState.random();
		m.texture = "metallic";
		m.tooltip = TooltipProperties.random();
		return m;
	}

}
