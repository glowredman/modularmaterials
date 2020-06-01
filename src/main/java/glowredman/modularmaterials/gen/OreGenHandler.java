package glowredman.modularmaterials.gen;

import java.util.Arrays;
import java.util.List;

import glowredman.modularmaterials.Reference;
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
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onVanillaOreGenerates(OreGenEvent.GenerateMinable event) {
		if(Reference.disableVanillaOreGen && VANILLA_ORES.contains(event.getType())) {
			event.setResult(Result.DENY);
		}
	}

}
