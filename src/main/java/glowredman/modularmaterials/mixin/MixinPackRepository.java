package glowredman.modularmaterials.mixin;

import com.google.common.collect.ImmutableSet;
import glowredman.modularmaterials.data.IMutableRepositorySource;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.packs.repository.RepositorySource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.HashSet;
import java.util.Set;

@Mixin(PackRepository.class)
public class MixinPackRepository implements IMutableRepositorySource {

    @Shadow
    private Set<RepositorySource> sources;

    @Override
    public void modularmaterials$addPackFinder(RepositorySource packFinder) {
        this.sources.add(packFinder);
    }

}
