package glowredman.modularmaterials.data;

import org.spongepowered.asm.mixin.Unique;

import net.minecraft.server.packs.repository.RepositorySource;

public interface IMutableRepositorySource {

    @Unique
    public void modularmaterials$addPackFinder(RepositorySource packFinder);

}
