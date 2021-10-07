package glowredman.modularmaterials.item;

import java.util.List;
import java.util.function.Supplier;

import glowredman.modularmaterials.MM_CreativeTabs;
import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.fluid.MetaFluid;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;

public class MetaBucketItem extends BucketItem {

	public MetaBucketItem(Supplier<MetaFluid> fluid, MM_Material material) {
		super(fluid, new Properties()
				.craftRemainder(Items.BUCKET)
				.setNoRepair()
				.rarity(material.item.rarity.get())
				.stacksTo(1)
				.tab(MM_CreativeTabs.TAB_FLUIDS));
		MM_Reference.BUCKETS.add(this);
	}

	@Override
	public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
		if (fluid().type.hasTooltip) {
			fluid().material.createTooltip(pTooltipComponents);
		}
	}

	@Override
	public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
		return (int) (fluid().material.burnTime * fluid().type.burnTimeMultiplier);
	}

	@Override
	public int getEntityLifespan(ItemStack itemStack, Level world) {
		return fluid().material.item.lifespan;
	}

	@Override
	public boolean isFireResistant() {
		return fluid().material.item.isFireResistant;
	}

	@Override
	public boolean isFoil(ItemStack pStack) {
		return MM_Reference.CONFIG.bucketsHaveFoilEffect && fluid().material.item.isFoil;
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
		return new FluidBucketWrapper(stack);
	}

	public String getFluidTypeIdentifier() {
		String s = this.getRegistryName().getPath().replace("bucket.", "");
		return s.substring(0, s.indexOf("."));
	}

	public String getMaterialIdentifier() {
		String s = this.getRegistryName().getPath().replace("bucket.", "");
		return s.substring(s.indexOf(".") + 1);
	}

	public String getLocalizedName() {
		return fluid().getLocalizedName() + " Bucket";
	}
	
	public MetaFluid fluid() {
		return (MetaFluid) getFluid();
	}

	// Override methods to use fluid supplier:

	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
		ItemStack itemstack = pPlayer.getItemInHand(pHand);
		BlockHitResult blockhitresult = getPlayerPOVHitResult(pLevel, pPlayer, this.getFluid() == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
		InteractionResultHolder<ItemStack> ret = ForgeEventFactory.onBucketUse(pPlayer, pLevel, itemstack, blockhitresult);
		if (ret != null)
			return ret;
		if (blockhitresult.getType() == HitResult.Type.MISS) {
			return InteractionResultHolder.pass(itemstack);
		} else if (blockhitresult.getType() != HitResult.Type.BLOCK) {
			return InteractionResultHolder.pass(itemstack);
		} else {
			BlockPos blockpos = blockhitresult.getBlockPos();
			Direction direction = blockhitresult.getDirection();
			BlockPos blockpos1 = blockpos.relative(direction);
			if (pLevel.mayInteract(pPlayer, blockpos) && pPlayer.mayUseItemAt(blockpos1, direction, itemstack)) {
				if (this.getFluid() == Fluids.EMPTY) {
					BlockState blockstate1 = pLevel.getBlockState(blockpos);
					if (blockstate1.getBlock() instanceof BucketPickup) {
						BucketPickup bucketpickup = (BucketPickup) blockstate1.getBlock();
						ItemStack itemstack1 = bucketpickup.pickupBlock(pLevel, blockpos, blockstate1);
						if (!itemstack1.isEmpty()) {
							pPlayer.awardStat(Stats.ITEM_USED.get(this));
							bucketpickup.getPickupSound().ifPresent((p_150709_) -> {
								pPlayer.playSound(p_150709_, 1.0F, 1.0F);
							});
							pLevel.gameEvent(pPlayer, GameEvent.FLUID_PICKUP, blockpos);
							ItemStack itemstack2 = ItemUtils.createFilledResult(itemstack, pPlayer, itemstack1);
							if (!pLevel.isClientSide) {
								CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer) pPlayer, itemstack1);
							}

							return InteractionResultHolder.sidedSuccess(itemstack2, pLevel.isClientSide());
						}
					}

					return InteractionResultHolder.fail(itemstack);
				} else {
					BlockState blockstate = pLevel.getBlockState(blockpos);
					BlockPos blockpos2 = canBlockContainFluid(pLevel, blockpos, blockstate) ? blockpos : blockpos1;
					if (this.emptyContents(pPlayer, pLevel, blockpos2, blockhitresult)) {
						this.checkExtraContent(pPlayer, pLevel, itemstack, blockpos2);
						if (pPlayer instanceof ServerPlayer) {
							CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) pPlayer, blockpos2, itemstack);
						}

						pPlayer.awardStat(Stats.ITEM_USED.get(this));
						return InteractionResultHolder.sidedSuccess(getEmptySuccessItem(itemstack, pPlayer), pLevel.isClientSide());
					} else {
						return InteractionResultHolder.fail(itemstack);
					}
				}
			} else {
				return InteractionResultHolder.fail(itemstack);
			}
		}
	}

	@Override
	public boolean emptyContents(Player pPlayer, Level pLevel, BlockPos pPos, BlockHitResult pResult) {
		BlockState blockstate = pLevel.getBlockState(pPos);
		Block block = blockstate.getBlock();
		Material material = blockstate.getMaterial();
		boolean flag = blockstate.canBeReplaced(this.getFluid());
		boolean flag1 = blockstate.isAir() || flag || block instanceof LiquidBlockContainer && ((LiquidBlockContainer) block).canPlaceLiquid(pLevel, pPos, blockstate, this.getFluid());
		if (!flag1) {
			return pResult != null && this.emptyContents(pPlayer, pLevel, pResult.getBlockPos().relative(pResult.getDirection()), (BlockHitResult) null);
		} else if (pLevel.dimensionType().ultraWarm() && this.getFluid().is(FluidTags.WATER)) {
			int i = pPos.getX();
			int j = pPos.getY();
			int k = pPos.getZ();
			pLevel.playSound(pPlayer, pPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (pLevel.random.nextFloat() - pLevel.random.nextFloat()) * 0.8F);

			for (int l = 0; l < 8; ++l) {
				pLevel.addParticle(ParticleTypes.LARGE_SMOKE, (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0D, 0.0D, 0.0D);
			}

			return true;
		} else if (block instanceof LiquidBlockContainer && ((LiquidBlockContainer) block).canPlaceLiquid(pLevel, pPos, blockstate, getFluid())) {
			((LiquidBlockContainer) block).placeLiquid(pLevel, pPos, blockstate, ((FlowingFluid) this.getFluid()).getSource(false));
			this.playEmptySound(pPlayer, pLevel, pPos);
			return true;
		} else {
			if (!pLevel.isClientSide && flag && !material.isLiquid()) {
				pLevel.destroyBlock(pPos, true);
			}

			if (!pLevel.setBlock(pPos, this.getFluid().defaultFluidState().createLegacyBlock(), 11) && !blockstate.getFluidState().isSource()) {
				return false;
			} else {
				this.playEmptySound(pPlayer, pLevel, pPos);
				return true;
			}
		}
	}

	@Override
	protected void playEmptySound(Player pPlayer, LevelAccessor pLevel, BlockPos pPos) {
		SoundEvent soundevent = this.getFluid().getAttributes().getEmptySound();
		if (soundevent == null) soundevent = this.getFluid().is(FluidTags.LAVA) ? SoundEvents.BUCKET_EMPTY_LAVA : SoundEvents.BUCKET_EMPTY;
		pLevel.playSound(pPlayer, pPos, soundevent, SoundSource.BLOCKS, 1.0F, 1.0F);
		pLevel.gameEvent(pPlayer, GameEvent.FLUID_PLACE, pPos);
	}

	private boolean canBlockContainFluid(Level worldIn, BlockPos posIn, BlockState blockstate) {
		return blockstate.getBlock() instanceof LiquidBlockContainer && ((LiquidBlockContainer) blockstate.getBlock()).canPlaceLiquid(worldIn, posIn, blockstate, this.getFluid());
	}

}
