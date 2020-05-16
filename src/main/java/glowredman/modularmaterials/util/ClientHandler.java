package glowredman.modularmaterials.util;

import glowredman.modularmaterials.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientHandler {
	
	@SubscribeEvent
	public void onTextureStitchEvent(TextureStitchEvent.Pre event) {
		for(Fluid fluid : Reference.fluids) {
			event.getMap().registerSprite(new ResourceLocation(fluid.getStill().getResourceDomain(), fluid.getStill().getResourcePath()));
			event.getMap().registerSprite(new ResourceLocation(fluid.getFlowing().getResourceDomain(), fluid.getFlowing().getResourcePath()));
		}
	}

}