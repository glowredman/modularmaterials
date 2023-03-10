package glowredman.modularmaterials.item;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.block.IMetaOre;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MetaOreBlockItem extends BlockItem {
    
    public final IMetaOre ore;

    public MetaOreBlockItem(IMetaOre ore) {
        super(ore.getBlock(), new Properties()
                .setNoRepair()
                .rarity(ore.getMaterial().item.rarity.get()));
        this.ore = ore;
    }
    
    @Override
    public int getEntityLifespan(ItemStack itemStack, Level world) {
        return ore.getMaterial().item.lifespan;
    }
    
    @Override
    public boolean isFireResistant() {
        return ore.getMaterial().item.isFireResistant;
    }
    
    @Override
    public boolean isFoil(ItemStack pStack) {
        return MM_Reference.CONFIG.oresHaveFoilEffect && ore.getMaterial().item.isFoil;
    }

}
