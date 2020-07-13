package glowredman.modularmaterials.block;

import static glowredman.modularmaterials.Reference.*;
import static glowredman.modularmaterials.util.MaterialHandler.getItemStackFromString;

import java.util.List;

import javax.annotation.Nullable;

import glowredman.modularmaterials.item.AdvItemBlock;
import glowredman.modularmaterials.object.JColor;
import glowredman.modularmaterials.object.JDrop;
import glowredman.modularmaterials.object.JMaterial;
import glowredman.modularmaterials.object.JOreVariant;
import glowredman.modularmaterials.object.JType;
import glowredman.modularmaterials.util.FormattingHandler;
import glowredman.modularmaterials.util.MinecraftHelper;
import net.minecraft.block.BlockFalling;
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

public class MetaOreFalling extends BlockFalling {
	
	public JMaterial material;
	public JOreVariant ore;
	public String type;
	public String variant;
	private boolean hasTooltip;
	private boolean isBeaconBase;
	private boolean isBeaconPayment;
	
	public MetaOreFalling(JMaterial material, JOreVariant ore, String type, String variant, String name) {
		super(MinecraftHelper.getMaterial(ore.material));
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
		this.setSoundType(MinecraftHelper.getSoundType(ore.sound));
		this.setUnlocalizedName(MODID + '.' + type + '.' + variant + '.' + name);
	}
	
	public Item createItemBlock() {
		return new AdvItemBlock(this, isBeaconPayment);
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
		if(hasTooltip) {
			String[] lines = material.tooltip;
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
