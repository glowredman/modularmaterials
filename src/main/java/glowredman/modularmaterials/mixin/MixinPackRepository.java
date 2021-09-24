/*
 * The content of this file has been taken & modified from:
 * https://github.com/Darkhax-Minecraft/Open-Loader/blob/fabric-1.17.1/src/main/java/net/darkhax/openloader/mixin/MixinResourcePackManager.java
 * by Darkhax.
 * The license of the original file can be found here:
 * https://github.com/Darkhax-Minecraft/Open-Loader/blob/fabric-1.17.1/LICENSE
 */

package glowredman.modularmaterials.mixin;

import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import glowredman.modularmaterials.data.IMutableRepositorySource;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.packs.repository.RepositorySource;

@Mixin(PackRepository.class)
public class MixinPackRepository implements IMutableRepositorySource {

    @Shadow
    private Set<RepositorySource> sources;

    @Override
    public void modularmaterials$addPackFinder(RepositorySource packFinder) {
        this.sources.add(packFinder);
    }

}
