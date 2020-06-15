package glowredman.modularmaterials.util;

import static glowredman.modularmaterials.Reference.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class ClientHandler {
	
	public static boolean firstLogin = true;
	
	public static final ClientHandler INSTANCE = new ClientHandler();
	
	public static void register() {
		MinecraftForge.EVENT_BUS.register(INSTANCE);
	}
	
	@SubscribeEvent
	public void onTextureStitchEvent(TextureStitchEvent.Pre event) {
		for(Fluid fluid : fluids) {
			event.getMap().registerSprite(new ResourceLocation(fluid.getStill().getResourceDomain(), fluid.getStill().getResourcePath()));
			event.getMap().registerSprite(new ResourceLocation(fluid.getFlowing().getResourceDomain(), fluid.getFlowing().getResourcePath()));
		}
	}
	
	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		if(firstLogin) {
			firstLogin = false;
			if(!Loader.isModLoaded("resourceloader")) {
				event.player.sendMessage(new TextComponentString(TextFormatting.RED + MODNAME + " needs Resource Loader! Without it no textures will be displayed."));
			}
		}
	}

}
