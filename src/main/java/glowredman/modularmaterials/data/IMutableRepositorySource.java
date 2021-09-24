/*
 * The content of this file has been taken & modified from:
 * https://github.com/Darkhax-Minecraft/Open-Loader/blob/fabric-1.17.1/src/main/java/net/darkhax/openloader/resource/IMutableResourcePackManager.java
 * by Darkhax.
 * The license of the original file can be found here:
 * https://github.com/Darkhax-Minecraft/Open-Loader/blob/fabric-1.17.1/LICENSE
 */

package glowredman.modularmaterials.data;

import org.spongepowered.asm.mixin.Unique;

import net.minecraft.server.packs.repository.RepositorySource;

public interface IMutableRepositorySource {

    @Unique
    public void modularmaterials$addPackFinder(RepositorySource packFinder);

}
