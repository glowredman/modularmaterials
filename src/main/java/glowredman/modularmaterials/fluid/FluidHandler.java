package glowredman.modularmaterials.fluid;

import java.util.Map.Entry;
import java.util.function.Supplier;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_Type;
import glowredman.modularmaterials.data.object.sub.Category;
import glowredman.modularmaterials.item.MetaBucketItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;

public class FluidHandler {
    
    private static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, MM_Reference.MODID);
    
    
    public FluidHandler() {
        FLUID_TYPES.register(ModularMaterials.getInstance().modBus);
    }
    
    @SubscribeEvent
    public void registerBlocks(RegisterEvent event) {
        if(!event.getRegistryKey().equals(Registries.FLUID)) {
            return;
        }
        
        long time = System.currentTimeMillis();
        
        for(Entry<String, MM_Type> eType : MM_Reference.TYPES.entrySet()) {
            String typeName = eType.getKey();
            MM_Type type = eType.getValue();
            
            // FLUIDS
            if(type.category == Category.FLUID && (type.enabled || MM_Reference.CONFIG.enableAll)) {
                for(Entry<String, MM_Material> eMaterial : MM_Reference.MATERIALS.entrySet()) {
                    String materialName = eMaterial.getKey();
                    MM_Material material = eMaterial.getValue();
                    if((material.enabled && material.enabledTypes.contains(typeName)) || MM_Reference.CONFIG.enableAll) {
                        DeferredHolder<Fluid, MetaFluid> fluidS = DeferredHolder.create(Registries.FLUID, new ResourceLocation(MM_Reference.MODID, typeName + "." + materialName));
                        DeferredHolder<Fluid, MetaFluid> fluidF = DeferredHolder.create(Registries.FLUID, new ResourceLocation(MM_Reference.MODID, "flowing_" + typeName + "." + materialName));
                        Supplier<MetaFluidType> fluidType = FLUID_TYPES.register(typeName + "." + materialName, () -> new MetaFluidType(material, type, typeName));
                        BaseFlowingFluid.Properties p = new BaseFlowingFluid.Properties(fluidType, fluidS, fluidF);
                        
                        if(MM_Reference.CONFIG.enableBuckets) {
                            ResourceLocation regName = new ResourceLocation(MM_Reference.MODID, "bucket." + fluidS.getId().getPath());
                            Item bucket = new MetaBucketItem(fluidS, material, regName);
                            p.bucket(() -> bucket);
                            Registry.register(BuiltInRegistries.ITEM, regName, bucket);
                        }

                        Registry.register(BuiltInRegistries.FLUID, fluidS.getId(), new MetaFluid(material, type, p, fluidS.getId()));
                        Registry.register(BuiltInRegistries.FLUID, fluidF.getId(), new MetaFlowingFluid(material, type, p, fluidF.getId()));
                        
                        //TODO
                        //MetaFluidBlock block = new MetaFluidBlock(fluidS, material, type, materialName);
                        //block.setRegistryName(MM_Reference.MODID, typeName + "." + materialName);
                        //event.getRegistry().register(block);
                    }
                }
            }
        }
        
        ModularMaterials.LOGGER.info("Registered {} fluids. Took {}ms.", MM_Reference.FLUIDS.size(), System.currentTimeMillis() - time);
    }

}
