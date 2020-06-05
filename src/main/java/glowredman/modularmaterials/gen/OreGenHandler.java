package glowredman.modularmaterials.gen;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import glowredman.modularmaterials.Main;
import static glowredman.modularmaterials.Reference.*;
import glowredman.modularmaterials.object.OreVein;
import glowredman.modularmaterials.object.XSTR;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.*;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class OreGenHandler {
	
	public static final OreGenHandler INSTANCE = new OreGenHandler();
	
	private static final List<EventType> VANILLA_ORES = Arrays.asList(COAL, DIAMOND, EMERALD, GOLD, IRON, LAPIS, QUARTZ, REDSTONE);
	
	public static void register() {
		MinecraftForge.ORE_GEN_BUS.register(INSTANCE);
	}
	
	public static void initWeight() {
		for(OreVein oreVein : oreVeins.values()) {
			weight += oreVein.enabled ? oreVein.weight : 0;
		}
		Main.logger.debug("The ore veins have a combined weight of " + weight + '.');
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onVanillaOreGenerates(OreGenEvent.GenerateMinable event) {
		if(disableVanillaOreGen && VANILLA_ORES.contains(event.getType())) {
			event.setResult(Result.DENY);
		}
	}
	
	public static int getCenterCoordinate(int chunkPos) {
		return chunkPos * 16 + (int) Math.copySign(8, chunkPos);
	}
	
	public static Random getRandom(World world, int xChunk, int zChunk) {
		long worldSeed = world.getSeed();
		Random random = new Random(worldSeed);
		long xSeed = random.nextLong() >> 2 + 1;
		long zSeed = random.nextLong() >> 2 + 1;
		long chunkSeed = (xSeed * xChunk + zSeed * zChunk) ^ worldSeed;
		random.setSeed(chunkSeed);
		return new XSTR(random.nextInt());
	}
	
	public static void addToStateOreMapping(IBlockState blockState, String materialKey, Block ore) {
		if(blockState != null) {
			if(stateOreMapping.containsKey(blockState)) {
				HashMap<String, Block> map = stateOreMapping.get(blockState);
				map.put(materialKey, ore);
				stateOreMapping.put(blockState, map);
			} else {
				HashMap<String, Block> map = new HashMap<String, Block>();
				map.put(materialKey, ore);
				stateOreMapping.put(blockState, map);
			}
		}
	}
	
	public static IBlockState getBlockStateFromBlockName(String baseBlock) {
		String[] parts = baseBlock.split(":");
		switch (parts.length) {
		case 1:
			// example: "gravel"
			return Block.getBlockFromName(baseBlock).getDefaultState();
		case 2:
			try {
				// example: "stone:0"
				return Block.getBlockFromName(parts[0]).getStateFromMeta(Integer.parseInt(parts[1]));
			} catch (Exception e) {
				// example "minecraft:gravel"
				return Block.getBlockFromName(baseBlock).getDefaultState();
			}
		case 3:
			try {
				// example: "minecraft:stone:0"
				return Block.getBlockFromName(parts[0] + ':' + parts[1]).getStateFromMeta(Integer.parseInt(parts[2]));
			} catch (Exception e) {
				break;
			}
		}
		return null;
	}
	
	public static IBlockState getOreForBaseBlock(IBlockState baseBlock, String materialKey) {
		IBlockState air = Blocks.AIR.getDefaultState();
		if(baseBlock == air || !stateOreMapping.containsKey(baseBlock)) {
			return air;
		} else {
			return stateOreMapping.get(baseBlock).get(materialKey).getDefaultState();
		}
	} 

}
