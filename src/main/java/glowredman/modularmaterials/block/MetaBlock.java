package glowredman.modularmaterials.block;

import static glowredman.modularmaterials.Reference.*;

import java.util.List;

import javax.annotation.Nullable;

import glowredman.modularmaterials.item.AdvItemBlock;
import glowredman.modularmaterials.object.JMaterial;
import glowredman.modularmaterials.object.JType;
import glowredman.modularmaterials.util.FormattingHandler;
import glowredman.modularmaterials.util.mc.MaterialHelper;
import glowredman.modularmaterials.util.mc.SoundTypeHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MetaBlock extends Block {
	
	public JMaterial material;
	public String type;
	private boolean hasTooltip;
	private boolean isBeaconBase;
	private boolean isBeaconPayment;

	public MetaBlock(JMaterial material, String type, String name) {
		super(MaterialHelper.getMaterialFromString(material.blockMaterialSound));
		JType type_ = types.get(type);
		this.material = material;
		this.type = type;
		this.hasTooltip = type_.hasTooltip && material.tooltip != null;
		this.isBeaconBase = type_.isBeaconBase && material.isBeaconBase;
		this.isBeaconPayment = type_.isBeaconPayment && material.isBeaconPayment;
		this.lightValue = material.blockLightLevel;
		this.setHarvestLevel(type_.effectiveTool, material.blockHarvestLevel);
		this.setCreativeTab(TAB_BLOCKS);
		this.setHardness(material.blockHardness);
		this.setRegistryName(MODID, type + '.' + name);
		this.setResistance(material.blockResistance);
		this.setSoundType(SoundTypeHelper.getMaterialFromString(material.blockMaterialSound));
		this.setUnlocalizedName(MODID + '.' + type + '.' + name);
	}

	public Item createItemBlock() {
		return new AdvItemBlock(this, isBeaconPayment);
	}
	
	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
		return isBeaconBase;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(this.hasTooltip) {
			for (String line : material.tooltip) {
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
	}
	
	@Override
	public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerColor() {
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new IBlockColor() {
			
			@Override
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
				return tintIndex == 0 ? material.color.getRGB() : 0xFFFFFF;
			}
		}, this);
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
			
			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {
				return tintIndex == 0 ? material.color.getARGB() : 0xFFFFFFFF;
			}
		}, this);
	}

}
