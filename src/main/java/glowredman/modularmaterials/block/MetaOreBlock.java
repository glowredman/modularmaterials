package glowredman.modularmaterials.block;

import java.util.List;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.data.TagHandler;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_OreVariant;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class MetaOreBlock extends Block implements IMetaOre {
	
	private final MM_Material material;
	private final MM_OreVariant variant;

	public MetaOreBlock(MM_Material material, MM_OreVariant ore, String uniqueMM_MaterialName) {
		super(BlockHandler.getOreBlockProperties(material, ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ore.baseBlock)), uniqueMM_MaterialName));
		this.material = material;
		this.variant = ore;
		MM_Reference.ORES.put(ForgeRegistries.BLOCKS.getValue(new ResourceLocation(ore.baseBlock)), uniqueMM_MaterialName, this);
	}
	
	@Override
	public void appendHoverText(ItemStack pStack, BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
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
		return false;
	}

	@Override
	public Block getBlock() {
		return this;
	}

	@Override
	public String getVariantIdentifier() {
		String s = this.getRegistryName().getPath().replace("ore.", "");
		return s.substring(0, s.indexOf("."));
	}
	
	@Override
	public String getMaterialIdentifier() {
		String s = this.getRegistryName().getPath().replace("ore.", "");
		return s.substring(s.indexOf(".") + 1);
	}

	@Override
	public String getLocalizedName() {
		return variant.nameSyntax.replace(TagHandler.PARAM_MATERIAL, material.name);
	}

}
