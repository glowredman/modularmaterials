package glowredman.modularmaterials.data.object.sub;

import glowredman.modularmaterials.MM_Reference;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;

public enum AdvRarity {
	COMMON(Rarity.COMMON),
	UNCOMMON(Rarity.UNCOMMON),
	RARE(Rarity.RARE),
	EPIC(Rarity.EPIC),
	BLACK(ChatFormatting.BLACK),
	DARK_BLUE(ChatFormatting.DARK_BLUE),
	DARK_GREEN(ChatFormatting.DARK_GREEN),
	DARK_AQUA(ChatFormatting.DARK_AQUA),
	DARK_RED(ChatFormatting.DARK_RED),
	DARK_PURPLE(ChatFormatting.DARK_PURPLE),
	GOLD(ChatFormatting.GOLD),
	GRAY(ChatFormatting.GRAY),
	DARK_GRAY(ChatFormatting.DARK_GRAY),
	BLUE(ChatFormatting.BLUE),
	GREEN(ChatFormatting.GREEN),
	
	/**@deprecated Use {@link #RARE}*/
	@Deprecated
	AQUA(ChatFormatting.AQUA),
	
	RED(ChatFormatting.RED),
	
	/**@deprecated Use {@link #EPIC}*/
	@Deprecated
	LIGHT_PURPLE(ChatFormatting.LIGHT_PURPLE),
	
	/**@deprecated Use {@link #UNCOMMON}*/
	@Deprecated
	YELLOW(ChatFormatting.YELLOW),
	
	/**@deprecated Use {@link #COMMON}*/
	@Deprecated
	WHITE(ChatFormatting.WHITE),
	
	OBFUSCATED(ChatFormatting.OBFUSCATED),
	BOLD(ChatFormatting.BOLD),
	STRIKETHROUGH(ChatFormatting.STRIKETHROUGH),
	UNDERLINE(ChatFormatting.UNDERLINE),
	ITALIC(ChatFormatting.ITALIC);
	
	private Rarity rarity;
	
	private AdvRarity(Rarity rarity) {
		this.rarity = rarity;
	}
	
	private AdvRarity(ChatFormatting color) {
		this.rarity = Rarity.create(color.name(), color);
	}
	
	public Rarity get() {
		return this.rarity;
	}
	
	private static final AdvRarity[] RARITIES = new AdvRarity[] {COMMON, UNCOMMON, RARE, EPIC, BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, RED, OBFUSCATED, BOLD, STRIKETHROUGH, UNDERLINE, ITALIC};
	
	public static AdvRarity random() {
		return RARITIES[MM_Reference.RAND.nextInt(RARITIES.length)];
	}
	
}
