package glowredman.modularmaterials.fluid;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.data.TagHandler;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_Type;
import glowredman.modularmaterials.data.object.sub.ChemicalState;
import glowredman.modularmaterials.data.object.sub.FluidProperties.StateBasedProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class MetaFluid extends ForgeFlowingFluid {
	
	public final MM_Type type;
	public final MM_Material material;
	public final ResourceLocation registryName;
	
	public MetaFluid(MM_Material material, MM_Type type, Properties properties, ResourceLocation registryName) {
		super(properties);
		this.material = material;
		this.type = type;
		this.registryName = registryName;
		addToList();
	}

	@Override
	public int getAmount(FluidState pState) {
		return 8;
	}

	@Override
	public boolean isSource(FluidState pState) {
		return true;
	}
	
	protected void addToList() {
		MM_Reference.FLUIDS.add(this);
	}
    
    public String getTypeIdentifier() {
        String s = this.registryName.getPath();
        return s.substring(0, s.indexOf("."));
    }
    
    public String getMaterialIdentifier() {
        String s = this.registryName.getPath();
        return s.substring(s.indexOf(".") + 1);
    }
    
    public String getLocalizedName() {
    	// GASEOUS/GASEOUS, LIQUID/LIQUID and SOLID/SOLID (although fluid types *should* not be configured as SOLID...):
    	if(material.state == type.state) {
    		return type.nameSyntax.replace(TagHandler.PARAM_MATERIAL, material.name);
    	}
    	// LIQUID/GASEOUS and SOLID/GASEOUS:
    	if(type.state == ChemicalState.GASEOUS) {
    		return "Gaseous " + type.nameSyntax.replace(TagHandler.PARAM_MATERIAL, material.name);
    	}
    	if(type.state == ChemicalState.LIQUID) {
    		// GASEOUS/LIQUID:
    		if(material.state == ChemicalState.GASEOUS) {
        		return "Liquid " + type.nameSyntax.replace(TagHandler.PARAM_MATERIAL, material.name);
        	}
    		// SOLID/LIQUID:
        	if(material.state == ChemicalState.SOLID) {
        		return "Molten " + type.nameSyntax.replace(TagHandler.PARAM_MATERIAL, material.name);
        	}
    	}
    	// [fallback] GASEOUS/SOLID and LIQUID/SOLID (although fluid types *should* not be configured as SOLID...):
		return "Solid " + type.nameSyntax.replace(TagHandler.PARAM_MATERIAL, material.name);
    }

	public StateBasedProperties stateBasedProperties() {
		return material.fluid.propertiesOfState(type.state);
	}

}
