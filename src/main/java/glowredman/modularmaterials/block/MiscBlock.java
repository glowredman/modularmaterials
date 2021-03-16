package glowredman.modularmaterials.block;

import static glowredman.modularmaterials.Reference.*;

import java.util.List;

import glowredman.modularmaterials.item.AdvItemBlock;
import glowredman.modularmaterials.object.JMiscBlock;
import glowredman.modularmaterials.util.FormattingHandler;
import glowredman.modularmaterials.util.MinecraftHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MiscBlock extends Block {

	public JMiscBlock block;

	public MiscBlock(JMiscBlock block, String name) {
		super(MinecraftHelper.getMaterial(block.material), MinecraftHelper.getMapColor(block.mapColor, block.color));
		this.block = block;
		this.lightValue = block.lightLevel;
		this.setCreativeTab(TAB_BLOCKS);
		this.setHardness(block.hardness);
		this.setHarvestLevel(block.effectiveTool, block.harvestLevel);
		this.setRegistryName(MODID, "miscblock." + name);
		this.setResistance(block.resistance);
		this.setSoundType(MinecraftHelper.getSoundType(block.sound));
		this.setUnlocalizedName(MODID + ".miscBlock." + name);
	}

	public Item createItemBlock() {
		return new AdvItemBlock(this, block.isBeaconPayment);
	}

	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
		return block.isBeaconBase;
	}

	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
		for (String line : block.tooltip) {
			try {
				String s = FormattingHandler.formatTooltipLine(line);
				if (s != null) {
					tooltip.add(s);
				}
			} catch (Exception e) {
				if (enableFormattingDebugger) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
		return true;
	}

	public void registerColor() {
		if (block.useColor) {
			Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(
					(state, world, pos, tintIndex) -> tintIndex == 0 ? block.color.getRGB() : 0xFFFFFF, this);
			Minecraft.getMinecraft().getItemColors().registerItemColorHandler(
					(stack, tintIndex) -> tintIndex == 0 ? block.color.getARGB() : 0xFFFFFF, this);
		}
	}

}
