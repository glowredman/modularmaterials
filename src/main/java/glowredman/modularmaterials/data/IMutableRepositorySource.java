package glowredman.modularmaterials.data;

import net.minecraft.server.packs.repository.RepositorySource;
import org.spongepowered.asm.mixin.Unique;

public interface IMutableRepositorySource {

    @Unique
    public void modularmaterials$addPackFinder(RepositorySource packFinder);

}
