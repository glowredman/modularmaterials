package glowredman.modularmaterials.block;

import java.util.List;

import javax.annotation.Nullable;

import static glowredman.modularmaterials.Reference.*;
import glowredman.modularmaterials.item.MetaItemBlock;
import glowredman.modularmaterials.object.Material;
import glowredman.modularmaterials.object.Type;
import glowredman.modularmaterials.util.FormattingHandler;
import glowredman.modularmaterials.util.MCMaterialHelper;
import glowredman.modularmaterials.util.MCSoundTypeHelper;
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

public class MetaBlock extends Block {
	
	public Material material;
	public String type;
	private boolean hasTooltip;
	private boolean isBeaconBase;
	private boolean isBeaconPayment;

	public MetaBlock(Material material, String type, String name) {
		super(MCMaterialHelper.getMaterialFromString(material.getMaterialSound()));
		this.material = material;
		this.type = type;
		try {
			Type t = types.get(type);
			this.hasTooltip = t.hasTooltip() && material.getTooltip() != null;
			this.isBeaconBase = t.isBeaconBase() && material.isBeaconBase();
			this.isBeaconPayment = t.isBeaconPayment() && material.isBeaconPayment();
		} catch (Exception e) {
			this.hasTooltip = false;
			this.isBeaconBase = false;
			this.isBeaconPayment = false;
		}
		this.setCreativeTab(TAB_BLOCKS);
		this.setHardness(material.getBlockHardness());
		this.setHarvestLevel("pickaxe", material.getBlockHarvestLevel());
		this.setLightLevel(material.getBlockLightLevel() / 15);
		this.setRegistryName(MODID, type + '.' + name);
		this.setResistance(material.getBlockResistance());
		this.setSoundType(MCSoundTypeHelper.getMaterialFromString(material.getMaterialSound()));
		this.setUnlocalizedName(MODID + '.' + type + '.' + name);
	}

	public Item createItemBlock() {
		return new MetaItemBlock(this, this.isBeaconPayment);
	}
	
	public Material getMaterial() {
		return this.material;
	}
	
	public String getType() {
		return this.type;
	}
	
	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
		return this.isBeaconBase;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(this.hasTooltip) {
			String[] lines = this.material.getTooltip();
			for (String line : lines) {
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
