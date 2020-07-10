package glowredman.modularmaterials.block;

import static glowredman.modularmaterials.Reference.*;
import static glowredman.modularmaterials.util.MaterialHandler.getItemStackFromString;

import java.util.List;

import javax.annotation.Nullable;

import glowredman.modularmaterials.item.MetaItemBlock;
import glowredman.modularmaterials.object.JDrop;
import glowredman.modularmaterials.object.JMaterial;
import glowredman.modularmaterials.object.JOreVariant;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MetaOre extends Block {
	
	public JMaterial material;
	public JOreVariant ore;
	public String type;
	public String variant;
	private boolean hasTooltip;
	private boolean isBeaconBase;
	private boolean isBeaconPayment;

	public MetaOre(JMaterial material, JOreVariant ore, String type, String variant, String name) {
		super(MaterialHelper.getMaterialFromString(ore.materialSound));
		JType t = types.get(type);
		this.material = material;
		this.ore = ore;
		this.type = type;
		this.variant = variant;
		this.hasTooltip = t.hasTooltip && material.tooltip != null;
		this.isBeaconBase = t.isBeaconBase && material.isBeaconBase;
		this.isBeaconPayment = t.isBeaconPayment && material.isBeaconPayment;
		this.lightValue = material.oreLightLevel;
		this.setCreativeTab(TAB_ORES);
		this.setHardness(material.oreHardness);
		this.setHarvestLevel(ore.effectiveTool, material.oreHarvestLevel);
		this.setRegistryName(MODID, type + '.' + variant + '.' + name);
		this.setSoundType(SoundTypeHelper.getMaterialFromString(ore.materialSound));
		this.setUnlocalizedName(MODID + '.' + type + '.' + variant + '.' + name);
	}
	
	public Item createItemBlock() {
		return new MetaItemBlock(this, isBeaconPayment);
	}
	
	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return true;
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		for(JDrop drop : material.drops) {
			ItemStack item;
			switch (drop.mode) {
			case "amount":
				item = getItemStackFromString(drop.item, drop.amount, this);
				if(item != null) drops.add(item);
				break;
			case "binomial":
				int amount = drop.amount;
				for(int i = 0; i < drop.n; i++) {
					amount += Math.random() < drop.p ? 1 : 0;
				}
				item = getItemStackFromString(drop.item, amount, this);
				if(item != null) drops.add(item);
				break;
			case "percentage_table":
				item = getItemStackFromString(drop.item, drop.amount, this);
				if(item != null && Math.random() < drop.getProbability(fortune)) drops.add(item);
				break;
			default:
				drops.add(new ItemStack(this));
				break;
			}
		}
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
