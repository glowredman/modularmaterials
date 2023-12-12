package glowredman.modularmaterials.block;

import java.util.List;

import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_OreVariant;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;

public class MetaFallingOreBlock extends FallingBlock implements IMetaOre {
    
    public static final MapCodec<MetaFallingOreBlock> CODEC = simpleCodec(MetaFallingOreBlock::new);
	
	private final MM_Material material;
	private final MM_OreVariant variant;
	private final ResourceLocation registryName;

	public MetaFallingOreBlock(MM_Material material, MM_OreVariant ore, String uniqueMM_MaterialName, ResourceLocation registryName) {
		super(BlockHandler.getOreBlockProperties(material, BuiltInRegistries.BLOCK.get(new ResourceLocation(ore.baseBlock)), uniqueMM_MaterialName));
		this.material = material;
		this.variant = ore;
		this.registryName = registryName;
		MM_Reference.ORES.put(BuiltInRegistries.BLOCK.get(new ResourceLocation(ore.baseBlock)), uniqueMM_MaterialName, this);
	}
    
    public MetaFallingOreBlock(Properties p_53205_) {
        super(p_53205_);
        this.material = new MM_Material();
        this.variant = new MM_OreVariant();
        this.registryName = null;
    }
	
	@Override
	protected MapCodec<? extends FallingBlock> codec() {
	    // I have no idea what this is used for or why it is needed...
	    return CODEC;
	}
	
	@Override
	public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
		if(MM_Reference.CONFIG.oresHaveTooltip) material.createTooltip(pTooltip);
	}

	@Override
	public MM_Material getMaterial() {
		return this.material;
	}

	@Override
	public MM_OreVariant getVariant() {
		return this.variant;
	}

	@Override
	public boolean obeysGravity() {
		return true;
	}

	@Override
	public Block getBlock() {
		return this;
	}

	@Override
	public ResourceLocation getRegistryName() {
		return this.registryName;
	}

}
