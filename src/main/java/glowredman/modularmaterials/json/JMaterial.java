package glowredman.modularmaterials.json;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnegative;

import glowredman.modularmaterials.information.Properties;
import glowredman.modularmaterials.information.Reference;
import net.minecraft.item.EnumRarity;

public class JMaterial {

	public JText name = new JText();
	public int meta;
	public boolean disabled;
	public JColor color = new JColor();
	public String[] oreDict = new String[]{};
	public String textureSet;
	public int blockTexture;
	public String fluidTexture;
	public int harvestLevel;
	public float blockHardness;
	public float blockResistance;
	public float oreHardness;
	public float oreResistance;
	public int temperature;
	public int meltingTemperature;
	public int boilingTemperature;
	public int density;
	@Nonnegative
	public int viscosity;
	public String state;
	public int lightLevel;
	public String[] tags;
	public JText[] tooltip = new JText[]{};
	
	public float getBlockHardness() {
		return this.blockHardness;
	}
	
	public float getBlockResistance() {
		return this.blockResistance;
	}
	
	public int getBlockTexture() {
		return this.blockTexture;
	}
	
	public int getBoilingTemperature() {
		return this.boilingTemperature;
	}
	
	public JColor getColor() {
		return this.color;
	}
	
	public int getDensity() {
		return this.density;
	}
	
	public boolean isDisabled() {
		return this.disabled;
	}
	
	public String getFluidTexture() {
		return this.fluidTexture;
	}
	
	public int getHarvestLevel() {
		return this.harvestLevel;
	}
	
	public int getLightLevel() {
		return this.lightLevel;
	}
	
	public int getMeltigTemperature() {
		return this.meltingTemperature;
	}
	
	public int getMeta() {
		return this.meta;
	}
	
	public JText getName() {
		return this.name;
	}
	public String[] getOreDict() {
		return this.oreDict;
	}
	
	public float getOreHardness() {
		return this.oreHardness;
	}
	
	public float getOreResistance() {
		return this.oreResistance;
	}
	
	public String getState() {
		return this.state;
	}
	
	public String[] getTags() {
		return this.tags;
	}
	
	public int getTemperature() {
		return this.temperature;
	}
	
	public JText[] getTooltip() {
		return this.tooltip;
	}
	
	public String getTextureSet() {
		return this.textureSet;
	}
	
	public int getViscosity() {
		return this.viscosity;
	}

}
