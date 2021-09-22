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
