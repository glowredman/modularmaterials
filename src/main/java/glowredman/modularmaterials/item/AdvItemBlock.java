package glowredman.modularmaterials.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class AdvItemBlock extends ItemBlock {
	
	private boolean isBeaconPayment;

	public AdvItemBlock(Block block, boolean isBeaconPayment) {
		super(block);
		this.isBeaconPayment = isBeaconPayment;
		this.setRegistryName(block.getRegistryName());
	}
	
	@Override
	public boolean isBeaconPayment(ItemStack stack) {
		return this.isBeaconPayment;
	}

}
