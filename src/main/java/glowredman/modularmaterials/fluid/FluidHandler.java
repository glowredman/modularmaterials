package glowredman.modularmaterials.fluid;

import java.util.Map.Entry;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_Type;
import glowredman.modularmaterials.data.object.sub.Category;
import glowredman.modularmaterials.item.MetaBucketItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.neoforged.neoforge.registries.RegistryObject;

public class FluidHandler {
    
    private static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, MM_Reference.MODID);
    
    
    public FluidHandler() {
        FLUID_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    
    @SubscribeEvent
    public void registerBlocks(RegisterEvent event) {
        if(!event.getRegistryKey().equals(ForgeRegistries.FLUIDS.getRegistryKey())) {
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
                        RegistryObject<MetaFluid> fluidS = RegistryObject.create(new ResourceLocation(MM_Reference.MODID, typeName + "." + materialName), ForgeRegistries.FLUIDS);
                        RegistryObject<MetaFluid> fluidF = RegistryObject.create(new ResourceLocation(MM_Reference.MODID, "flowing_" + typeName + "." + materialName), ForgeRegistries.FLUIDS);
                        RegistryObject<MetaFluidType> fluidType = FLUID_TYPES.register(typeName + "." + materialName, () -> new MetaFluidType(material, type, typeName));
                        BaseFlowingFluid.Properties p = new BaseFlowingFluid.Properties(fluidType, fluidS, fluidF);
                        
                        if(MM_Reference.CONFIG.enableBuckets) {
                            ResourceLocation regName = new ResourceLocation(MM_Reference.MODID, "bucket." + fluidS.getId().getPath());
                            Item bucket = new MetaBucketItem(fluidS, material, regName);
                            p.bucket(() -> bucket);
                            ForgeRegistries.ITEMS.register(regName, bucket);
                        }
                        
                        ForgeRegistries.FLUIDS.register(fluidS.getId(), new MetaFluid(material, type, p, fluidS.getId()));
                        ForgeRegistries.FLUIDS.register(fluidF.getId(), new MetaFlowingFluid(material, type, p, fluidF.getId()));
                        
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
