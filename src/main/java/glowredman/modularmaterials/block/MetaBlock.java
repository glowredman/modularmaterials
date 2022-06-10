package glowredman.modularmaterials.block;

import java.util.List;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.data.TagHandler;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_Type;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class MetaBlock extends Block {
	
	public final MM_Material material;
	public final MM_Type type;
	public final ResourceLocation registryName;

	public MetaBlock(MM_Material material, MM_Type type, String uniqueMM_MaterialName, ResourceLocation registryName) {
		super(BlockHandler.getBlockProperties(material, type, uniqueMM_MaterialName));
		this.material = material;
		this.type = type;
		this.registryName = registryName;
		MM_Reference.BLOCKS.add(this);
	}
	
	@Override
	public float getEnchantPowerBonus(BlockState state, LevelReader world, BlockPos pos) {
		return material.enchantPowerBonus * type.enchantPowerBonusMultiplier;
	}
	
	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return (int) (material.fireSpreadSpeed * type.fireSpreadSpeedMultiplier);
	}
	
	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return (int) (material.flammability * type.flammabilityMultiplier);
	}
	
	@Override
	public boolean isStickyBlock(BlockState state) {
		return material.block.sticky;
	}
	
	@Override
	public void appendHoverText(ItemStack pStack, BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
		if(type.hasTooltip) material.createTooltip(pTooltip);
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
		return type.nameSyntax.replace(TagHandler.PARAM_MATERIAL, material.name);
	}

}
