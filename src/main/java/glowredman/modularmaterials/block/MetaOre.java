package glowredman.modularmaterials.block;

import java.util.List;

import javax.annotation.Nullable;

import static glowredman.modularmaterials.Reference.*;
import glowredman.modularmaterials.item.MetaItemBlock;
import glowredman.modularmaterials.object.Material;
import glowredman.modularmaterials.object.OreVariant;
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

public class MetaOre extends Block {
	
	public Material material;
	public OreVariant ore;
	public String type;
	public String variant;
	private boolean hasTooltip;
	private boolean isBeaconBase;
	private boolean isBeaconPayment;

	public MetaOre(Material material, OreVariant ore, String type, String variant, String name) {
		super(MCMaterialHelper.getMaterialFromString(ore.materialSound));
		Type t = types.get(type);
		this.material = material;
		this.ore = ore;
		this.type = type;
		this.variant = variant;
		this.hasTooltip = t.hasTooltip && material.tooltip != null;
		this.isBeaconBase = t.isBeaconBase && material.isBeaconBase;
		this.isBeaconPayment = t.isBeaconPayment && material.isBeaconPayment;
		this.setCreativeTab(TAB_ORES);
		this.setHardness(material.oreHardness);
		this.setHarvestLevel(ore.effectiveTool, material.oreHarvestLevel);
		this.setLightLevel(material.oreLightLevel / 15);
		this.setRegistryName(MODID, type + '.' + variant + '.' + name);
		this.setSoundType(MCSoundTypeHelper.getMaterialFromString(ore.materialSound));
		this.setUnlocalizedName(MODID + '.' + type + '.' + variant + '.' + name);
	}
	
	public Item createItemBlock() {
		return new MetaItemBlock(this, isBeaconPayment);
	}
	
	@Override
	public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
		return isBeaconBase;
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(this.hasTooltip) {
			String[] lines = material.tooltip;
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
