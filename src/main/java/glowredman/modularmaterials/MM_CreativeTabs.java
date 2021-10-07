package glowredman.modularmaterials;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class MM_CreativeTabs {
	
	public static final CreativeModeTab TAB_BLOCKS = new CreativeModeTab(MM_Reference.MODID + ".blocks") {
		
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(Blocks.IRON_BLOCK);
		}
	};
	
	public static final CreativeModeTab TAB_ORES = new CreativeModeTab(MM_Reference.MODID + ".ores") {
		
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(Blocks.IRON_ORE);
		}
	};
	
	public static final CreativeModeTab TAB_ITEMS = new CreativeModeTab(MM_Reference.MODID + ".items") {
		
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(Items.IRON_INGOT);
		}
	};
	
	public static final CreativeModeTab TAB_FLUIDS = new CreativeModeTab(MM_Reference.MODID + ".fluids") {
		
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(Items.WATER_BUCKET);
		}
	};

}
