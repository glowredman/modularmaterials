package glowredman.modularmaterials.block;

import org.apache.commons.lang3.math.NumberUtils;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.information.Reference;
import glowredman.modularmaterials.util.TagHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class BlockMeta extends Block implements IMetaBlockName {
	
	public static final PropertyInteger META = PropertyInteger.create("meta", 0, 15);

	public BlockMeta(Material materialIn) {
		super(materialIn);
		this.setDefaultState(this.blockState.getBaseState().withProperty(META, 0));
	}
	
	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(this.getRegistryName());
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {META});
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(META);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(META, meta);
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (int i = 0; i < 16; i++) {
			items.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public String getSpecialName(ItemStack stack, String name) {
		String hexadecimalblock;
		try {
			hexadecimalblock = stack.getUnlocalizedName().substring(21);
			hexadecimalblock = hexadecimalblock.substring(0, name.indexOf("."));
		} catch (Exception e) {
			hexadecimalblock = "0";
			Main.logger.error("An Error (" + e + ") occured while getting the special name for \"" + stack.getUnlocalizedName() + "\". Returning " + stack.getItemDamage());
		}
		
		return TagHandler.getNameFromMeta(NumberUtils.toInt(hexadecimalblock) * 16 + stack.getItemDamage());
		
	}

}
