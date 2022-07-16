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
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistries.Keys;

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

					if (stack == null || ForgeRegistries.ITEMS.getKey(stack.getItem()).equals(ForgeRegistries.ITEMS.getKey(Blocks.AIR.asItem()))) {

						commandSourceStack.sendFailure(Component.literal("Your main hand is empty!").withStyle(style -> style.withColor(ChatFormatting.RED).withItalic(true)));

					} else {
						commandSourceStack.sendSuccess(copyable(ForgeRegistries.ITEMS.getKey(stack.getItem()).toString()).withStyle(ChatFormatting.GREEN), false);
						stack.getTags().forEach(rl -> commandSourceStack.sendSuccess(Component.literal("  > ").append(copyable(rl.location().toString())), false));
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
		List<TagKey<Biome>> biomeTags = biome.tags().toList();
		
		BlockState blockstate = level.getBlockState(pos);
		Block block = blockstate.getBlock();
		Registry<Block> blockRegistry = level.registryAccess().registryOrThrow(Keys.BLOCKS);
		ResourceLocation blockRegName = blockRegistry.getKey(block);
		List<TagKey<Block>> blockTags = blockRegistry.getHolderOrThrow(ResourceKey.create(Keys.BLOCKS, blockRegName)).tags().toList();
		
		FluidState fluidstate = level.getFluidState(pos);
		Fluid fluid = fluidstate.getType();
		Registry<Fluid> fluidRegistry = level.registryAccess().registryOrThrow(Keys.FLUIDS);
		ResourceLocation fluidRegName = fluidRegistry.getKey(fluid);
		List<TagKey<Fluid>> fluidTags = fluidRegistry.getHolderOrThrow(ResourceKey.create(Keys.FLUIDS, fluidRegName)).tags().toList();
		FluidType fluidType = fluid.getFluidType();

		commandSourceStack.sendSuccess(Component.literal("Information for ").withStyle(ChatFormatting.GOLD)
				.append(Component.literal(String.format("[%d, %d, %d]", pos.getX(), pos.getY(), pos.getZ())).withStyle(ChatFormatting.GREEN))
				.append(Component.literal(" in Chunk ").withStyle(ChatFormatting.GOLD))
				.append(Component.literal(String.format("[%d, %d]", chunkPos.x, chunkPos.z)).withStyle(ChatFormatting.GREEN))
				.append(Component.literal(":")).withStyle(ChatFormatting.GOLD), false);
		
		commandSourceStack.sendSuccess(Component.literal("Biome: ").withStyle(ChatFormatting.BLUE)
				.append(copyable(level.registryAccess().registryOrThrow(Keys.BIOMES).getKey(biome.value()).toString())), false);
		
		if(!biomeTags.isEmpty()) commandSourceStack.sendSuccess(Component.literal("  Tags: ").withStyle(ChatFormatting.AQUA), false);
		biomeTags.forEach(rl -> commandSourceStack.sendSuccess(Component.literal("  > ").append(copyable(rl.location().toString())), false));

		commandSourceStack.sendSuccess(Component.literal("Dimension: ").withStyle(ChatFormatting.BLUE)
				.append(copyable(level.dimension().location().toString())), false);

		commandSourceStack.sendSuccess(Component.literal("Block: ").withStyle(ChatFormatting.BLUE)
				.append(copyable(blockRegName.toString())), false);
		
		if(!blockstate.isAir()) {

			commandSourceStack.sendSuccess(Component.literal("  Enchantment Power Bonus: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(format(blockstate.getEnchantPowerBonus(level, pos))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(Component.literal("  Explosion Resistance: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(format(blockstate.getExplosionResistance(level, pos, null))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(Component.literal("  Fire Spread Speed: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(format(blockstate.getFireSpreadSpeed(level, pos, Direction.UP))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(Component.literal("  Flammability: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(format(blockstate.getFlammability(level, pos, Direction.UP))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(Component.literal("  Friction: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(format(blockstate.getFriction(level, pos, commandSourceStack.getEntity()))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(Component.literal("  Hardness: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(format(block.defaultDestroyTime())).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(Component.literal("  Jump Factor: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(format(block.getJumpFactor())).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(Component.literal("  Light Emission: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(format(blockstate.getLightEmission(level, pos))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(Component.literal("  Speed Factor: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(format(block.getSpeedFactor())).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(Component.literal("  Sticky: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(String.valueOf(blockstate.isStickyBlock())).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(Component.literal("  Tool Required: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(String.valueOf(blockstate.requiresCorrectToolForDrops())).withStyle(ChatFormatting.WHITE)), false);
			
			if(!blockTags.isEmpty()) commandSourceStack.sendSuccess(Component.literal("  Tags: ").withStyle(ChatFormatting.AQUA), false);
			blockTags.forEach(rl -> commandSourceStack.sendSuccess(Component.literal("  > ").append(copyable(rl.location().toString())), false));
			
		}
		
		commandSourceStack.sendSuccess(Component.literal("Fluid: ").withStyle(ChatFormatting.BLUE)
				.append(copyable(fluidRegName.toString())), false);
		
		if(!fluidstate.isEmpty()) {
			
			commandSourceStack.sendSuccess(Component.literal("  Amount: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(format(fluidstate.getAmount())).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(Component.literal("  Density: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(format(fluidType.getDensity(fluidstate, level, pos))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(Component.literal("  Explosion Resistance: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(format(fluidstate.getExplosionResistance(level, pos, null))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(Component.literal("  Height: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(format(fluidstate.getHeight(level, pos))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(Component.literal("  Light Emission: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(format(fluidType.getLightLevel(fluidstate, level, pos))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(Component.literal("  Lighter Than Air: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(String.valueOf(fluidType.isLighterThanAir())).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(Component.literal("  Own Height: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(format(fluidstate.getOwnHeight())).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(Component.literal("  Source: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(String.valueOf(fluidstate.isSource())).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(Component.literal("  Temperature: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(format(fluidType.getTemperature(fluidstate, level, pos))).withStyle(ChatFormatting.WHITE)), false);
			
			commandSourceStack.sendSuccess(Component.literal("  Viscosity: ").withStyle(ChatFormatting.AQUA)
					.append(Component.literal(format(fluidType.getViscosity(fluidstate, level, pos))).withStyle(ChatFormatting.WHITE)), false);
			
			if(!fluidTags.isEmpty()) commandSourceStack.sendSuccess(Component.literal("  Tags: ").withStyle(ChatFormatting.AQUA), false);
			fluidTags.forEach(rl -> commandSourceStack.sendSuccess(Component.literal("  > ").append(copyable(rl.location().toString())), false));
			
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
		return Component.literal(text).withStyle(style -> style
				.withColor(ChatFormatting.WHITE)
				.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, text))
				.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.translatable("chat.copy.click")))
				.withInsertion(text));
	}
	
	private static class PresetArgumentType implements ArgumentType<String> {
		
		static {
			ArgumentTypeInfos.registerByClass(PresetArgumentType.class, SingletonArgumentInfo.contextFree(PresetArgumentType::new));
		}

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
