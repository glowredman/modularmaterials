/*
 * The content of this file has been taken & modified from:
 * https://github.com/Darkhax-Minecraft/Open-Loader/blob/fabric-1.17.1/src/main/java/net/darkhax/openloader/mixin/MixinMinecraftServer.java
 * by Darkhax.
 * The license of the original file can be found here:
 * https://github.com/Darkhax-Minecraft/Open-Loader/blob/fabric-1.17.1/LICENSE
 */

package glowredman.modularmaterials.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.data.IMutableRepositorySource;
import glowredman.modularmaterials.data.ResourceLoader;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.DataPackConfig;

@Mixin(value = MinecraftServer.class, priority = 1001)
public class MixinMinecraftServer {

    @Inject(method = "configurePackRepository", at = @At("HEAD"))
    private static void configurePackRepository(PackRepository manager, DataPackConfig settings, boolean safe, CallbackInfoReturnable<DataPackConfig> info) {
        if (!safe) {
            ModularMaterials.info("Injecting datapack provider for server.");
            final IMutableRepositorySource mutable = (IMutableRepositorySource) manager;
            mutable.modularmaterials$addPackFinder(new ResourceLoader(true));
        }
    }

}
