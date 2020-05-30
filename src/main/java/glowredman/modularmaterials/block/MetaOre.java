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
		super(MCMaterialHelper.getMaterialFromString(ore.getMaterialSound()));
		this.material = material;
		this.ore = ore;
		this.type = type;
		this.variant = variant;
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
		this.setCreativeTab(TAB_ORES);
		this.setHardness(material.getOreHardness());
		this.setHarvestLevel(ore.getEffectiveTool(), material.getOreHarvestLevel());
		this.setLightLevel(material.getOreLightLevel() / 15);
		this.setRegistryName(MODID, type + '.' + variant + '.' + name);
		this.setSoundType(MCSoundTypeHelper.getMaterialFromString(ore.getMaterialSound()));
		this.setUnlocalizedName(MODID + '.' + type + '.' + variant + '.' + name);
	}
	
	public Item createItemBlock() {
		return new MetaItemBlock(this, this.isBeaconPayment);
	}
	
	public Material getMaterial() {
		return this.material;
	}
	
	public OreVariant getOre() {
		return this.ore;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getVariant() {
		return this.variant;
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
