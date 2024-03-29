package glowredman.modularmaterials.item;

import java.util.List;

import javax.annotation.Nullable;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.data.TagHandler;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_Type;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class MetaItem extends Item {
	
	public final MM_Material material;
	public final MM_Type type;
	public final ResourceLocation registryName;

	public MetaItem(MM_Material material, MM_Type type, ResourceLocation registryName) {
		super(new Properties()
				.setNoRepair()
				.rarity(material.item.rarity.get()));
		this.material = material;
		this.type = type;
		this.registryName = registryName;
		MM_Reference.ITEMS.add(this);
	}
	
	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
		if(type.hasTooltip) material.createTooltip(pTooltipComponents);
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
		return (int) (material.burnTime * type.burnTimeMultiplier);
	}
	
	@Override
	public int getEntityLifespan(ItemStack itemStack, Level world) {
		return material.item.lifespan;
	}
	
	@Override
	public boolean isFireResistant() {
		return material.item.isFireResistant;
	}
	
	@Override
	public boolean isFoil(ItemStack pStack) {
		return material.item.isFoil;
	}
	
	public String getTypeIdentifier() {
		String s = this.registryName.getPath();
		return s.substring(0, s.indexOf("."));
	}
	
	public String getMaterialIdentifier() {
		String s = this.registryName.getPath();
		return s.substring(s.indexOf(".") + 1);
	}
	
	public String getLocalizedName() {
		return type.nameSyntax.replace(TagHandler.PARAM_MATERIAL, material.name);
	}

}
