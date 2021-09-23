package glowredman.modularmaterials;

import java.text.NumberFormat;
import java.util.Set;

import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.Commands.CommandSelection;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidAttributes;

public class MM_Commands {

	@SubscribeEvent
	public void register(RegisterCommandsEvent event) {

		if (event.getEnvironment() != CommandSelection.DEDICATED) {

			event.getDispatcher().register(Commands.literal("mm")
					
					.requires(commandSourceStack -> commandSourceStack.hasPermission(MM_Reference.commandPermissionLevel))
					
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

					})).then(Commands.literal("posinfo").executes(context -> {
						CommandSourceStack commandSourceStack = context.getSource();
						Entity entity = commandSourceStack.getEntity();
						if(entity == null) return 0;
						return getInfo(commandSourceStack, new BlockPos(entity.getBlockX(), entity.getBlockY(), entity.getBlockZ()));
					}).then(Commands.argument("location", BlockPosArgument.blockPos()).executes(context -> {
						return getInfo(context.getSource(), BlockPosArgument.getLoadedBlockPos(context, "location"));
					}))));
		}
	}
	
	private static int getInfo(CommandSourceStack commandSourceStack, BlockPos pos) {
		Level level = commandSourceStack.getLevel();
		ChunkPos chunkPos = level.getChunkAt(pos).getPos();
		Biome biome = level.getBiome(pos);
		
		BlockState blockstate = level.getBlockState(pos);
		Block block = blockstate.getBlock();
		String blockRegName = block.getRegistryName().toString();
		Set<ResourceLocation> blockTags = block.getTags();
		
		FluidState fluidstate = level.getFluidState(pos);
		Fluid fluid = fluidstate.getType();
		String fluidRegName = fluid.getRegistryName().toString();
		Set<ResourceLocation> fluidTags = fluid.getTags();
		FluidAttributes fluidAttributes = fluid.getAttributes();

		commandSourceStack.sendSuccess(new TextComponent("Information for ").withStyle(ChatFormatting.GOLD)
				.append(new TextComponent(String.format("[%d, %d, %d]", pos.getX(), pos.getY(), pos.getZ())).withStyle(ChatFormatting.GREEN))
				.append(new TextComponent(" in Chunk ").withStyle(ChatFormatting.GOLD))
				.append(new TextComponent(String.format("[%d, %d]", chunkPos.x, chunkPos.z)).withStyle(ChatFormatting.GREEN))
				.append(new TextComponent(":")).withStyle(ChatFormatting.GOLD), false);
		
		commandSourceStack.sendSuccess(new TextComponent("Biome: ").withStyle(ChatFormatting.BLUE)
				.append(new TextComponent(biome.getRegistryName().toString()).withStyle(ChatFormatting.WHITE)), false);

		commandSourceStack.sendSuccess(new TextComponent("Biome Category: ").withStyle(ChatFormatting.BLUE)
				.append(new TextComponent(biome.getBiomeCategory().getName()).withStyle(ChatFormatting.WHITE)), false);

		commandSourceStack.sendSuccess(new TextComponent("Dimension: ").withStyle(ChatFormatting.BLUE)
				.append(new TextComponent(level.dimension().location().toString()).withStyle(ChatFormatting.WHITE)), false);

		commandSourceStack.sendSuccess(new TextComponent("Block: ").withStyle(ChatFormatting.BLUE)
				.append(new TextComponent(blockRegName).withStyle(style -> style
						.withColor(ChatFormatting.WHITE)
						.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, blockRegName))
						.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableComponent("chat.copy.click"))))), false);
		
		if(!blockstate.isAir()) {

			commandSourceStack.sendSuccess(new TextComponent("  Enchantment Power Bonus: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(blockstate.getEnchantPowerBonus(level, pos))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Explosion Resistance: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(blockstate.getExplosionResistance(level, pos, null))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Fire Spread Speed: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(blockstate.getFireSpreadSpeed(level, pos, Direction.UP))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Flammability: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(blockstate.getFlammability(level, pos, Direction.UP))).withStyle(ChatFormatting.WHITE)), false);
			
			if(commandSourceStack.getEntity() != null) commandSourceStack.sendSuccess(new TextComponent("  Friction: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(blockstate.getFriction(level, pos, commandSourceStack.getEntity()))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Hardness: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(block.defaultDestroyTime())).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Jump Factor: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(block.getJumpFactor())).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Light Emission: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(blockstate.getLightEmission(level, pos))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Speed Factor: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(block.getSpeedFactor())).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Sticky: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(String.valueOf(blockstate.isStickyBlock())).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Tool Required: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(String.valueOf(blockstate.requiresCorrectToolForDrops())).withStyle(ChatFormatting.WHITE)), false);
			
			if(!blockTags.isEmpty()) commandSourceStack.sendSuccess(new TextComponent("  Tags: ").withStyle(ChatFormatting.AQUA), false);
			blockTags.forEach(rl -> commandSourceStack.sendSuccess(new TextComponent("  > " + rl.toString()).withStyle(style -> style
					.withColor(ChatFormatting.WHITE)
					.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, rl.toString()))
					.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableComponent("chat.copy.click")))), false));
			
		}
		
		commandSourceStack.sendSuccess(new TextComponent("Fluid: ").withStyle(ChatFormatting.BLUE)
				.append(new TextComponent(fluidRegName).withStyle(style -> style
						.withColor(ChatFormatting.WHITE)
						.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, fluidRegName))
						.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableComponent("chat.copy.click"))))), false);
		
		if(!fluidstate.isEmpty()) {
			
			commandSourceStack.sendSuccess(new TextComponent("  Amount: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(fluidstate.getAmount())).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Color: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(Integer.toHexString(fluidAttributes.getColor(level, pos)).toUpperCase()).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Density: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(fluidAttributes.getDensity(level, pos))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Explosion Resistance: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(fluidstate.getExplosionResistance(level, pos, null))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Gaseous: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(String.valueOf(fluidAttributes.isGaseous(level, pos))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Height: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(fluidstate.getHeight(level, pos))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Light Emission: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(fluidAttributes.getLuminosity(level, pos))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Lighter Than Air: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(String.valueOf(fluidAttributes.isLighterThanAir())).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Own Height: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(fluidstate.getOwnHeight())).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Source: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(String.valueOf(fluidstate.isSource())).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Temperature: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(fluidAttributes.getTemperature(level, pos))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Viscosity: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(fluidAttributes.getViscosity(level, pos))).withStyle(ChatFormatting.WHITE)), false);
			
			if(!fluidTags.isEmpty()) commandSourceStack.sendSuccess(new TextComponent("  Tags: ").withStyle(ChatFormatting.AQUA), false);
			fluidTags.forEach(rl -> commandSourceStack.sendSuccess(new TextComponent("  > " + rl.toString()).withStyle(style -> style
					.withColor(ChatFormatting.WHITE)
					.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, rl.toString()))
					.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableComponent("chat.copy.click")))), false));
			
		}

		return 0;
	}
	
	private static String format(long number) {
		return NumberFormat.getInstance().format(number);
	}
	
	private static String format(double number) {
		return NumberFormat.getInstance().format(number);
	}

}
