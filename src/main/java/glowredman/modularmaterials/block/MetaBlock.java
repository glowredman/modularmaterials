package glowredman.modularmaterials.block;

import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_Type;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class MetaBlock extends Block {
	
	public final MM_Material material;
	public final MM_Type type;

	public MetaBlock(MM_Material material, MM_Type type, String uniqueMM_MaterialName) {
		super(BlockHandler.getBlockProperties(material, uniqueMM_MaterialName));
		this.material = material;
		this.type = type;
	}
	
	@Override
	public float getEnchantPowerBonus(BlockState state, LevelReader world, BlockPos pos) {
		return material.block.enchantPowerBonus;
	}
	
	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return material.block.fireSpreadSpeed;
	}
	
	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return material.block.flammability;
	}
	
	@Override
	public boolean isStickyBlock(BlockState state) {
		return material.block.sticky;
	}

}
