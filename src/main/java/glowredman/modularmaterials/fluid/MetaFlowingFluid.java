package glowredman.modularmaterials.fluid;

import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_Type;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;

public class MetaFlowingFluid extends MetaFluid {
	
	private static final int FLOWING_LENGHT = "flowing_".length();

	public MetaFlowingFluid(MM_Material material, MM_Type type, Properties properties) {
		super(material, type, properties);
        registerDefaultState(getStateDefinition().any().setValue(LEVEL, 7));
	}

	@Override
	public int getAmount(FluidState pState) {
		return pState.getValue(LEVEL);
	}

	@Override
	public boolean isSource(FluidState pState) {
		return false;
	}
	
	@Override
	protected void createFluidStateDefinition(Builder<Fluid, FluidState> pBuilder) {
		super.createFluidStateDefinition(pBuilder);
		pBuilder.add(LEVEL);
	}
    
	@Override
    public String getTypeIdentifier() {
        String s = this.getRegistryName().getPath();
        return s.substring(FLOWING_LENGHT, s.indexOf("."));
    }
    
	@Override
    public String getMaterialIdentifier() {
        String s = this.getRegistryName().getPath();
        return s.substring(s.indexOf(".") + 1);
    }
	
	@Override
	protected void addToList() {}
	
}