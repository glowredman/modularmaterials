package glowredman.modularmaterials.block;

import java.util.List;

import javax.annotation.Nullable;

import static glowredman.modularmaterials.Reference.*;
import glowredman.modularmaterials.object.Material;
import glowredman.modularmaterials.util.FormattingHandler;
import glowredman.modularmaterials.util.MaterialSoundHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MetaBlock extends Block {
	
	public Material material;
	public String type;

	public MetaBlock(Material material, String type, String name) {
		super(MaterialSoundHelper.getMaterialFromString(material.getMaterialSound()));
		this.material = material;
		this.type = type;
		this.setCreativeTab(TAB_BLOCKS);
		this.setHardness(material.getBlockHardness());
		this.setHarvestLevel("pickaxe", material.getBlockHarvestLevel());
		this.setLightLevel(material.getBlockLightLevel() / 15);
		this.setRegistryName(MODID, type + '.' + name);
		this.setResistance(material.getBlockResistance());
		this.setUnlocalizedName(MODID + '.' + type + '.' + name);
	}

	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(this.getRegistryName());
	}
	
	public Material getMaterial() {
		return this.material;
	}
	
	public String getType() {
		return this.type;
	}
	
	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
		return this.getMaterial().isBeaconBase();
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		String[] lines = this.material.getTooltip();
		if(lines != null) {
			for(String line : lines) {
				try {
					String s = FormattingHandler.formatTooltipLine(line);
					if(s != null) {
						tooltip.add(s);
					}
				} catch (Exception e) {
					if(enableFormattingDebugger) {
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
	
	public void registerColor() {
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(new IBlockColor() {
			
			@Override
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
				return tintIndex == 0 ? material.getColor().getRGB() : 0xFFFFFF;
			}
		}, this);
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
			
			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {
				return tintIndex == 0 ? material.getColor().getARGB() : 0xFFFFFFFF;
			}
		}, this);
	}

}
