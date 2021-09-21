package glowredman.modularmaterials;

import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.Commands.CommandSelection;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MM_Commands {

	@SubscribeEvent
	public void register(RegisterCommandsEvent event) {

		if (event.getEnvironment() != CommandSelection.DEDICATED) {

			event.getDispatcher().register(Commands.literal("mm")

					.then(Commands.literal("hand").executes(context -> {

						CommandSourceStack commandSourceStack = context.getSource();

						ItemStack stack = commandSourceStack.getPlayerOrException().getMainHandItem();

						if (stack == null || stack.getItem().getRegistryName().equals(Blocks.AIR.getRegistryName())) {

							commandSourceStack.sendFailure(new TextComponent("Your main hand is empty!")
									.withStyle(style -> style.withColor(ChatFormatting.RED).withItalic(true)));

						} else {

							String regName = stack.getItem().getRegistryName().toString();

							commandSourceStack.sendSuccess(new TextComponent(regName).withStyle(style -> style
									.withColor(ChatFormatting.GREEN)
									.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, regName))
									.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableComponent("chat.copy.click")))
									.withInsertion(regName)), false);

							stack.getItem().getTags()
									.forEach(r -> commandSourceStack.sendSuccess(new TextComponent("> ")
											.append(new TextComponent(r.toString()).withStyle(style -> style
													.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, r.toString()))
													.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableComponent("chat.copy.click")))
													.withInsertion(r.toString()))), false));
						}
						return 0;

					})).then(Commands.literal("here").executes(context -> {

						CommandSourceStack commandSourceStack = context.getSource();

						Entity entity = commandSourceStack.getEntity();
						Level level = commandSourceStack.getLevel();

						int x = entity.getBlockX();
						int y = entity.getBlockY();
						int z = entity.getBlockZ();

						Biome biome = level.getBiome(new BlockPos(x, y, z));

						commandSourceStack.sendSuccess(new TextComponent("Information for ").withStyle(ChatFormatting.GOLD)
										.append(new TextComponent(String.format("[%d, %d, %d]", x, y, z)).withStyle(ChatFormatting.AQUA))
										.append(new TextComponent(":")).withStyle(ChatFormatting.GOLD), false);

						commandSourceStack.sendSuccess(new TextComponent("Biome: ").withStyle(ChatFormatting.BLUE)
								.append(new TextComponent(biome.getRegistryName().toString()).withStyle(ChatFormatting.WHITE)), false);

						commandSourceStack.sendSuccess(new TextComponent("Biome Category: ").withStyle(ChatFormatting.BLUE)
										.append(new TextComponent(biome.getBiomeCategory().getName()).withStyle(ChatFormatting.WHITE)), false);

						commandSourceStack.sendSuccess(new TextComponent("Dimension: ").withStyle(ChatFormatting.BLUE)
								.append(new TextComponent(level.dimension().location().toString()).withStyle(ChatFormatting.WHITE)), false);

						return 0;

					})));
		}
	}

}
