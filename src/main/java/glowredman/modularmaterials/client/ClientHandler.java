package glowredman.modularmaterials.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class ClientHandler {
	
	public ClientHandler() {
		Minecraft.getInstance().getResourcePackRepository().addPackFinder(new ResourceLoader(false));
	}
	}

}
