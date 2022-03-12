package glowredman.modularmaterials;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import glowredman.modularmaterials.data.PresetHandler;
import glowredman.modularmaterials.data.legacy.LegacyHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.TagKey;
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

		event.getDispatcher().register(Commands.literal("mm")
				
				.requires(commandSourceStack -> commandSourceStack.hasPermission(MM_Reference.CONFIG.commandPermissionLevel))
				
				.then(Commands.literal("hand").executes(context -> {

					CommandSourceStack commandSourceStack = context.getSource();

					ItemStack stack = null;
					
					try {
						stack = commandSourceStack.getPlayerOrException().getMainHandItem();
					} catch (Exception e) {}

					if (stack == null || stack.getItem().getRegistryName().equals(Blocks.AIR.getRegistryName())) {

						commandSourceStack.sendFailure(new TextComponent("Your main hand is empty!")
								.withStyle(style -> style.withColor(ChatFormatting.RED).withItalic(true)));

					} else {
						commandSourceStack.sendSuccess(copyable(stack.getItem().getRegistryName().toString()).withStyle(ChatFormatting.GREEN), false);
						stack.getTags().forEach(rl -> commandSourceStack.sendSuccess(new TextComponent("  > ").append(copyable(rl.location().toString())), false));
					}
					return 0;

				})).then(Commands.literal("posinfo").executes(context -> {
					CommandSourceStack commandSourceStack = context.getSource();
					Entity entity = commandSourceStack.getEntity();
					if(entity == null) return 0;
					return getInfo(commandSourceStack, new BlockPos(entity.getBlockX(), entity.getBlockY(), entity.getBlockZ()));
				}).then(Commands.argument("location", BlockPosArgument.blockPos()).executes(context -> {
					return getInfo(context.getSource(), BlockPosArgument.getLoadedBlockPos(context, "location"));
				})))
				
				.then(Commands.literal("convert").requires(commandSourceStack -> commandSourceStack.hasPermission(4)).executes(context -> {
					return LegacyHandler.convert(context.getSource());
				}))
				
				.then(Commands.literal("install").requires(commandSourceStack -> commandSourceStack.hasPermission(4))
						.then(Commands.argument("preset", new PresetArgumentType()).executes(context -> {
							return PresetHandler.execute(context.getSource(), context.getArgument("preset", String.class));
						}))));
	}
	
	private static int getInfo(CommandSourceStack commandSourceStack, BlockPos pos) {
		Level level = commandSourceStack.getLevel();
		ChunkPos chunkPos = level.getChunkAt(pos).getPos();
		Holder<Biome> biome = level.getBiome(pos);
		
		BlockState blockstate = level.getBlockState(pos);
		Block block = blockstate.getBlock();
		String blockRegName = block.getRegistryName().toString();
		List<TagKey<Block>> blockTags = block.builtInRegistryHolder().tags().toList();
		
		FluidState fluidstate = level.getFluidState(pos);
		Fluid fluid = fluidstate.getType();
		String fluidRegName = fluid.getRegistryName().toString();
		List<TagKey<Fluid>> fluidTags = fluid.builtInRegistryHolder().tags().toList();
		FluidAttributes fluidAttributes = fluid.getAttributes();

		commandSourceStack.sendSuccess(new TextComponent("Information for ").withStyle(ChatFormatting.GOLD)
				.append(new TextComponent(String.format("[%d, %d, %d]", pos.getX(), pos.getY(), pos.getZ())).withStyle(ChatFormatting.GREEN))
				.append(new TextComponent(" in Chunk ").withStyle(ChatFormatting.GOLD))
				.append(new TextComponent(String.format("[%d, %d]", chunkPos.x, chunkPos.z)).withStyle(ChatFormatting.GREEN))
				.append(new TextComponent(":")).withStyle(ChatFormatting.GOLD), false);
		
		commandSourceStack.sendSuccess(new TextComponent("Biome: ").withStyle(ChatFormatting.BLUE)
				.append(copyable(biome.value().getRegistryName().toString())), false);

		commandSourceStack.sendSuccess(new TextComponent("Biome Category: ").withStyle(ChatFormatting.BLUE)
				.append(copyable(Biome.getBiomeCategory(biome).getName())), false);

		commandSourceStack.sendSuccess(new TextComponent("Dimension: ").withStyle(ChatFormatting.BLUE)
				.append(copyable(level.dimension().location().toString())), false);

		commandSourceStack.sendSuccess(new TextComponent("Block: ").withStyle(ChatFormatting.BLUE)
				.append(copyable(blockRegName)), false);
		
		if(!blockstate.isAir()) {

			commandSourceStack.sendSuccess(new TextComponent("  Enchantment Power Bonus: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(blockstate.getEnchantPowerBonus(level, pos))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Explosion Resistance: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(blockstate.getExplosionResistance(level, pos, null))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Fire Spread Speed: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(blockstate.getFireSpreadSpeed(level, pos, Direction.UP))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Flammability: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(blockstate.getFlammability(level, pos, Direction.UP))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Friction: ").withStyle(ChatFormatting.AQUA)
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
			blockTags.forEach(rl -> commandSourceStack.sendSuccess(copyable(rl.location().toString()), false));
			
		}
		
		commandSourceStack.sendSuccess(new TextComponent("Fluid: ").withStyle(ChatFormatting.BLUE)
				.append(copyable(fluidRegName)), false);
		
		if(!fluidstate.isEmpty()) {
			
			commandSourceStack.sendSuccess(new TextComponent("  Amount: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent(format(fluidstate.getAmount())).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(new TextComponent("  Color: ").withStyle(ChatFormatting.AQUA)
					.append(new TextComponent("#" + Integer.toHexString(fluidAttributes.getColor(level, pos)).toUpperCase()).withStyle(ChatFormatting.WHITE)), false);
			
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
			fluidTags.forEach(rl -> commandSourceStack.sendSuccess(new TextComponent("  > ").append(copyable(rl.location().toString())), false));
			
		}

		return 0;
	}
	
	private static String format(long number) {
		return NumberFormat.getInstance(Locale.ENGLISH).format(number);
	}
	
	private static String format(double number) {
		return NumberFormat.getInstance(Locale.ENGLISH).format(number);
	}
	
	private static MutableComponent copyable(String text) {
		return new TextComponent(text).withStyle(style -> style
				.withColor(ChatFormatting.WHITE)
				.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, text))
				.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableComponent("chat.copy.click")))
				.withInsertion(text));
	}
	
	private static class PresetArgumentType implements ArgumentType<String> {

		@Override
		public String parse(StringReader reader) throws CommandSyntaxException {
			return reader.readString();
		}
		
		@Override
		public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
			if (!(context.getSource() instanceof SharedSuggestionProvider)) {
				return Suggestions.empty();
			}
			return SharedSuggestionProvider.suggest(MM_Reference.PRESETS, builder);
		}
		
	}

}
