package glowredman.modularmaterials.block;

import glowredman.modularmaterials.Main;
import glowredman.modularmaterials.information.Reference;
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

public class BlockBase extends Block {
	
	public static final IProperty<Integer> META = PropertyInteger.create("meta", 0, 15);
	
	protected String name;

	public BlockBase(String name) {
		super(Material.IRON);
		
		this.name = name;
		
		this.setRegistryName(Reference.MODID, name);
		this.setDefaultState(this.blockState.getBaseState().withProperty(META, 0));
	}

	public void registerItemModel(Item itemBlock) {
		Main.proxy.registerItemRenderer(itemBlock, name, 0);
	}
	
	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(this.getRegistryName());
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, META);
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

}
