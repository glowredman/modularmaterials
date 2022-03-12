package glowredman.modularmaterials.block;

import java.util.Optional;
import java.util.function.Supplier;

import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_Type;
import glowredman.modularmaterials.fluid.MetaFluid;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.event.ForgeEventFactory;

public class MetaFluidBlock extends LiquidBlock {

	public MetaFluidBlock(Supplier<? extends MetaFluid> fluid, MM_Material material, MM_Type type, String uniqueMM_MaterialName) {
		super(fluid, BlockHandler.getFluidBlockProperties(material, type, uniqueMM_MaterialName));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
		return fluid().stateBasedProperties().propagatesSkylightDown;
	}

	@Override
	public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
		return fluid().stateBasedProperties().isPathfindable;
	}

	@Override
	public float getEnchantPowerBonus(BlockState state, LevelReader world, BlockPos pos) {
		return fluid().material.enchantPowerBonus * fluid().type.enchantPowerBonusMultiplier;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return (int) (fluid().material.fireSpreadSpeed * fluid().type.fireSpreadSpeedMultiplier);
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		return (int) (fluid().material.flammability * fluid().type.flammabilityMultiplier);
	}

	public MetaFluid fluid() {
		return (MetaFluid) getFluid();
	}

	// Override methods to use fluid supplier:

	@Override
	public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
		return pContext.isAbove(STABLE_SHAPE, pPos, true) && pState.getValue(LEVEL) == 0 && pContext.canStandOnFluid(pLevel.getFluidState(pPos.above()), this.getFluid().defaultFluidState()) ? STABLE_SHAPE : Shapes.empty();
	}

	@Override
	public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pSide) {
		return pAdjacentBlockState.getFluidState().getType().isSame(this.getFluid());
	}

	@Override
	public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
		if (this.shouldSpreadLiquid(pLevel, pPos, pState)) {
			pLevel.scheduleTick(pPos, pState.getFluidState().getType(), this.getFluid().getTickDelay(pLevel));
		}
	}

	@Override
	public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
		if (pState.getFluidState().isSource() || pFacingState.getFluidState().isSource()) {
			pLevel.scheduleTick(pCurrentPos, pState.getFluidState().getType(), this.getFluid().getTickDelay(pLevel));
		}
		return pState;
	}

	@Override
	public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
		if (this.shouldSpreadLiquid(pLevel, pPos, pState)) {
			pLevel.scheduleTick(pPos, pState.getFluidState().getType(), this.getFluid().getTickDelay(pLevel));
		}
	}

	private boolean shouldSpreadLiquid(Level pLevel, BlockPos pPos, BlockState pState) {
		if (this.getFluid().is(FluidTags.LAVA)) {
			boolean flag = pLevel.getBlockState(pPos.below()).is(Blocks.SOUL_SOIL);

			for (Direction direction : POSSIBLE_FLOW_DIRECTIONS) {
				BlockPos blockpos = pPos.relative(direction.getOpposite());
				if (pLevel.getFluidState(blockpos).is(FluidTags.WATER)) {
					Block block = pLevel.getFluidState(pPos).isSource() ? Blocks.OBSIDIAN : Blocks.COBBLESTONE;
					pLevel.setBlockAndUpdate(pPos, ForgeEventFactory.fireFluidPlaceBlockEvent(pLevel, pPos, pPos, block.defaultBlockState()));
					this.fizz(pLevel, pPos);
					return false;
				}

				if (flag && pLevel.getBlockState(blockpos).is(Blocks.BLUE_ICE)) {
					pLevel.setBlockAndUpdate(pPos, ForgeEventFactory.fireFluidPlaceBlockEvent(pLevel, pPos, pPos, Blocks.BASALT.defaultBlockState()));
					this.fizz(pLevel, pPos);
					return false;
				}
			}
		}

		return true;
	}

	private void fizz(LevelAccessor pLevel, BlockPos pPos) {
		pLevel.levelEvent(1501, pPos, 0);
	}
	
	@Override
	public ItemStack pickupBlock(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
		if (pState.getValue(LEVEL) == 0) {
			pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 11);
			return new ItemStack(this.getFluid().getBucket());
		} else {
			return ItemStack.EMPTY;
		}
	}
	
	@Override
	public Optional<SoundEvent> getPickupSound() {
		return this.getFluid().getPickupSound();
	}
}
