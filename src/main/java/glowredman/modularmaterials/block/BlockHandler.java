package glowredman.modularmaterials.block;

import java.util.Map.Entry;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_OreVariant;
import glowredman.modularmaterials.data.object.MM_Type;
import glowredman.modularmaterials.data.object.sub.Category;
import glowredman.modularmaterials.data.object.sub.ChemicalState;
import glowredman.modularmaterials.data.object.sub.ColorProperties;
import glowredman.modularmaterials.fluid.MetaFlowingFluid;
import glowredman.modularmaterials.fluid.MetaFluid;
import glowredman.modularmaterials.item.MetaBucketItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockHandler {
    
    @SubscribeEvent
    public void registerBlocks(Register<Block> event) {
        long time = System.currentTimeMillis();
        
        ModularMaterials.info("Registering blocks and fluids...");
        
        for(Entry<String, MM_Type> eType : MM_Reference.TYPES.entrySet()) {
            String typeName = eType.getKey();
            MM_Type type = eType.getValue();
            
            // BLOCKS
            if(type.category == Category.BLOCK && (type.enabled || MM_Reference.CONFIG.enableAll)) {
                for(Entry<String, MM_Material> eMaterial : MM_Reference.MATERIALS.entrySet()) {
                    String materialName = eMaterial.getKey();
                    MM_Material material = eMaterial.getValue();
                    if((material.enabled && material.enabledTypes.contains(typeName)) || MM_Reference.CONFIG.enableAll) {
                        MetaBlock block = new MetaBlock(material, type, materialName);
                        block.setRegistryName(MM_Reference.MODID, typeName + "." + materialName);
                        event.getRegistry().register(block);
                    }
                }
            }
            
            // FLUIDS
            if(type.category == Category.FLUID && (type.enabled || MM_Reference.CONFIG.enableAll)) {
                for(Entry<String, MM_Material> eMaterial : MM_Reference.MATERIALS.entrySet()) {
                    String materialName = eMaterial.getKey();
                    MM_Material material = eMaterial.getValue();
                    if((material.enabled && material.enabledTypes.contains(typeName)) || MM_Reference.CONFIG.enableAll) {
                        RegistryObject<MetaFluid> fluidS = RegistryObject.of(new ResourceLocation(MM_Reference.MODID, typeName + "." + materialName), ForgeRegistries.FLUIDS);
                        RegistryObject<MetaFluid> fluidF = RegistryObject.of(new ResourceLocation(MM_Reference.MODID, "flowing_" + typeName + "." + materialName), ForgeRegistries.FLUIDS);
                        
                        FluidAttributes.Builder b = FluidAttributes.builder(
                                new ResourceLocation(MM_Reference.MODID, "fluids/" + material.texture + "/" + typeName + "_still"),
                                new ResourceLocation(MM_Reference.MODID, "fluids/" + material.texture + "/" + typeName + "_flowing"))
                                .color(material.color.getARGB())
                                .density(material.fluid.propertiesOfState(type.state).density)
                                .luminosity(material.fluid.propertiesOfState(type.state).luminosity)
                                .rarity(material.item.rarity.get())
                                .temperature(material.fluid.getTemperature(material.state, type.state))
                                .viscosity(material.fluid.propertiesOfState(type.state).viscosity);
                        if(type.state == ChemicalState.GASEOUS) b.gaseous();
                        
                        ForgeFlowingFluid.Properties p = new ForgeFlowingFluid.Properties(fluidS, fluidF, b);
                        
                        if(MM_Reference.CONFIG.enableBuckets) {
                            Item bucket = new MetaBucketItem(fluidS, material).setRegistryName(MM_Reference.MODID, "bucket." + fluidS.getId().getPath());
                        	p.bucket(() -> bucket);
                            ForgeRegistries.ITEMS.register(bucket);
                        }
                        
                        ForgeRegistries.FLUIDS.register(new MetaFluid(material, type, p).setRegistryName(fluidS.getId()));
                        ForgeRegistries.FLUIDS.register(new MetaFlowingFluid(material, type, p).setRegistryName(fluidF.getId()));
                        
                        //TODO
                        //MetaFluidBlock block = new MetaFluidBlock(fluidS, material, type, materialName);
                        //block.setRegistryName(MM_Reference.MODID, typeName + "." + materialName);
                        //event.getRegistry().register(block);
                    }
                }
            }
        }
        
        for(Entry<String, MM_Material> eMaterial : MM_Reference.MATERIALS.entrySet()) {
        	MM_Material material = eMaterial.getValue();
        	if((material.enabled && material.enabledTypes.contains("ore")) || MM_Reference.CONFIG.enableAll) {
        		String materialName = eMaterial.getKey();
        		for(Entry<String, MM_OreVariant> eVariant : MM_Reference.ORE_VARIANTS.entrySet()) {
        			MM_OreVariant variant = eVariant.getValue();
        			if(variant.enabled || MM_Reference.CONFIG.enableAll) {
        				String regName = "ore." + eVariant.getKey() + "." + materialName;
        				if(variant.falling) {
        					event.getRegistry().register(new MetaFallingOreBlock(material, variant, materialName).setRegistryName(MM_Reference.MODID, regName));
        				} else {
        					event.getRegistry().register(new MetaOreBlock(material, variant, materialName).setRegistryName(MM_Reference.MODID, regName));
        				}
        			}
        		}
        	}
        }
        
        ModularMaterials.info("Registered " + (MM_Reference.BLOCKS.size() + MM_Reference.ORES.size()) + " blocks and " + MM_Reference.FLUIDS.size() + " fluids. Took " + (System.currentTimeMillis() - time) + "ms.");
    }
    
    public static BlockBehaviour.Properties getBlockProperties(MM_Material material, MM_Type type, String uniqueMM_MaterialName) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of(getMaterial(material.block.material, uniqueMM_MaterialName));
        properties.color(getMaterialColor(material.block.mapColor, material.color, uniqueMM_MaterialName));
        properties.destroyTime(material.block.hardness);
        properties.explosionResistance(material.resistance * type.resistanceMultiplier);
        properties.friction(material.block.friction);
        properties.jumpFactor(material.jumpFactor * type.jumpFactorMultiplier);
        properties.lightLevel(state -> (int) (material.lightLevel * type.lightLevelMultiplier));
        if(material.block.requiresToolForDrops) properties.requiresCorrectToolForDrops();
        properties.sound(getSoundType(material.block.sound, uniqueMM_MaterialName));
        properties.speedFactor(material.speedFactor * type.speedFactorMultiplier);
        return properties;
    }
    
    public static BlockBehaviour.Properties getFluidBlockProperties(MM_Material material, MM_Type type, String uniqueMM_MaterialName) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of(Material.WATER);
        properties.color(getMaterialColor("AUTO", material.color, uniqueMM_MaterialName));
        properties.destroyTime(material.block.hardness);
        properties.explosionResistance(material.resistance * type.resistanceMultiplier);
        properties.jumpFactor(material.jumpFactor * type.jumpFactorMultiplier);
        properties.lightLevel(state -> (int) (material.lightLevel * type.lightLevelMultiplier));
        properties.noCollission();
        properties.noDrops();
        properties.randomTicks();
        properties.speedFactor(material.speedFactor * type.speedFactorMultiplier);
        return properties;
    }
    
    @SuppressWarnings("deprecation")
    public static BlockBehaviour.Properties getOreBlockProperties(MM_Material material, Block parent, String uniqueMM_MaterialName) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of(
                MM_Reference.CONFIG.oresInheritMaterial                          ? parent.defaultBlockState().getMaterial()      : getMaterial(material.ore.material, uniqueMM_MaterialName));
        properties.color(MM_Reference.CONFIG.oresInheritMapColor                 ? parent.defaultMaterialColor()                 : getMaterialColor(material.ore.mapColor, material.color, uniqueMM_MaterialName));
        properties.sound(MM_Reference.CONFIG.oresInheritSoundType                ? parent.defaultBlockState().getSoundType()     : getSoundType(material.ore.soundType, uniqueMM_MaterialName));
        properties.destroyTime(MM_Reference.CONFIG.oresInheritHardness           ? parent.defaultDestroyTime()                   : material.ore.hardness);
        properties.explosionResistance(MM_Reference.CONFIG.oresInheritResistance ? parent.getExplosionResistance()               : material.ore.resistance);
        properties.friction(MM_Reference.CONFIG.oresInheritFriction              ? parent.getFriction()                          : material.ore.friction);
        properties.jumpFactor(MM_Reference.CONFIG.oresInheritJumpFactor          ? parent.getJumpFactor()                        : material.ore.jumpFactor);
        properties.lightLevel(state -> MM_Reference.CONFIG.oresInheritLightLevel ? parent.defaultBlockState().getLightEmission() : material.ore.lightLevel);
        properties.speedFactor(MM_Reference.CONFIG.oresInheritSpeedFactor        ? parent.getSpeedFactor()                       : material.ore.speedFactor);
        if(material.ore.requiresToolForDrops) properties.requiresCorrectToolForDrops();
        return properties;
    }
    
    private static Material getMaterial(String name, String uniqueMM_MaterialName) {
        switch (name.toUpperCase()) {
        case "AIR": 
            return Material.AIR;
        case "AMETHYST": 
            return Material.AMETHYST;
        case "BAMBOO": 
            return Material.BAMBOO;
        case "BAMBOO_SAPLING": 
            return Material.BAMBOO_SAPLING;
        case "BARRIER": 
            return Material.BARRIER;
        case "BUBBLE_COLUMN": 
            return Material.BUBBLE_COLUMN;
        case "BUILDABLE_GLASS": 
            return Material.BUILDABLE_GLASS;
        case "CACTUS": 
            return Material.CACTUS;
        case "CAKE": 
            return Material.CAKE;
        case "CLAY": 
            return Material.CLAY;
        case "CLOTH_DECORATION": 
            return Material.CLOTH_DECORATION;
        case "DECORATION": 
            return Material.DECORATION;
        case "DIRT": 
            return Material.DIRT;
        case "EGG": 
            return Material.EGG;
        case "EXPLOSIVE": 
            return Material.EXPLOSIVE;
        case "FIRE": 
            return Material.FIRE;
        case "GLASS": 
            return Material.GLASS;
        case "GRASS": 
            return Material.GRASS;
        case "HEAVY_METAL": 
            return Material.HEAVY_METAL;
        case "ICE": 
            return Material.ICE;
        case "ICE_SOLID": 
            return Material.ICE_SOLID;
        case "LAVA": 
            return Material.LAVA;
        case "LEAVES": 
            return Material.LEAVES;
        case "METAL": 
            return Material.METAL;
        case "MOSS": 
            return Material.MOSS;
        case "NETHER_WOOD": 
            return Material.NETHER_WOOD;
        case "PISTON": 
            return Material.PISTON;
        case "PLANT": 
            return Material.PLANT;
        case "PORTAL":
            return Material.PORTAL;
        case "POWDER_SNOW":
            return Material.POWDER_SNOW;
        case "REPLACEABLE_FIREPROOF_PLANT":
            return Material.REPLACEABLE_FIREPROOF_PLANT;
        case "REPLACEABLE_PLANT":
            return Material.REPLACEABLE_PLANT;
        case "REPLACEABLE_WATER_PLANT":
            return Material.REPLACEABLE_WATER_PLANT;
        case "SAND":
            return Material.SAND;
        case "SCULK":
            return Material.SCULK;
        case "SHULKER_SHELL":
            return Material.SHULKER_SHELL;
        case "SNOW":
            return Material.SNOW;
        case "SPONGE":
            return Material.SPONGE;
        case "STONE":
            return Material.STONE;
        case "STRUCTURAL_AIR":
            return Material.STRUCTURAL_AIR;
        case "TOP_SNOW":
            return Material.TOP_SNOW;
        case "VEGETABLE":
            return Material.VEGETABLE;
        case "WATER":
            return Material.WATER;
        case "WATER_PLANT":
            return Material.WATER_PLANT;
        case "WEB":
            return Material.WEB;
        case "WOOD":
            return Material.WOOD;
        case "WOOL":
            return Material.WOOL;
        default:
            ModularMaterials.warn("Unknown material property \"" + name + "\" of material \"" + uniqueMM_MaterialName + "\"!");
            return Material.STONE;
        }
    }
    
    private static MaterialColor getMaterialColor(String name, ColorProperties color, String uniqueMM_MaterialName) {

        switch (name.toUpperCase()) {
        case "AUTO":
            int index = 0;
            int distance = distanceSq(color, MaterialColor.MATERIAL_COLORS[0].col);
            for(int i = 1; i < 62; i++) {
                int currentDistance = distanceSq(color, MaterialColor.MATERIAL_COLORS[i].col);
                if(currentDistance < distance) {
                    index = i;
                    distance = currentDistance;
                }
            }
            MaterialColor ret = MaterialColor.MATERIAL_COLORS[index];
            ModularMaterials.debug("Found " + Integer.toHexString(ret.col).toUpperCase() + " as the nearest color for \"" + uniqueMM_MaterialName + "\" (" + Integer.toHexString(color.getRGB()).toUpperCase() + "). Distance: " + Math.sqrt(distance));
            return ret;
        case "CLAY":
            return MaterialColor.CLAY;
        case "COLOR_BLACK":
            return MaterialColor.COLOR_BLACK;
        case "COLOR_BLUE":
            return MaterialColor.COLOR_BLUE;
        case "COLOR_BROWN":
            return MaterialColor.COLOR_BROWN;
        case "COLOR_CYAN":
            return MaterialColor.COLOR_CYAN;
        case "COLOR_GRAY":
            return MaterialColor.COLOR_GRAY;
        case "COLOR_GREEN":
            return MaterialColor.COLOR_GREEN;
        case "COLOR_LIGHT_BLUE":
            return MaterialColor.COLOR_LIGHT_BLUE;
        case "COLOR_LIGHT_GRAY":
            return MaterialColor.COLOR_LIGHT_GRAY;
        case "COLOR_LIGHT_GREEN":
            return MaterialColor.COLOR_LIGHT_GREEN;
        case "COLOR_MAGENTA":
            return MaterialColor.COLOR_MAGENTA;
        case "COLOR_ORANGE":
            return MaterialColor.COLOR_ORANGE;
        case "COLOR_PINK":
            return MaterialColor.COLOR_PINK;
        case "COLOR_PURPLE":
            return MaterialColor.COLOR_PURPLE;
        case "COLOR_RED":
            return MaterialColor.COLOR_RED;
        case "COLOR_YELLOW":
            return MaterialColor.COLOR_YELLOW;
        case "CRIMSON_HYPHAE":
            return MaterialColor.CRIMSON_HYPHAE;
        case "CRIMSON_NYLIUM":
            return MaterialColor.CRIMSON_NYLIUM;
        case "CRIMSON_STEM":
            return MaterialColor.CRIMSON_STEM;
        case "DEEPSLATE":
            return MaterialColor.DEEPSLATE;
        case "DIAMOND":
            return MaterialColor.DIAMOND;
        case "DIRT":
            return MaterialColor.DIRT;
        case "EMERALD":
            return MaterialColor.EMERALD;
        case "FIRE":
            return MaterialColor.FIRE;
        case "GLOW_LICHEN":
            return MaterialColor.GLOW_LICHEN;
        case "GOLD":
            return MaterialColor.GOLD;
        case "GRASS":
            return MaterialColor.GRASS;
        case "ICE":
            return MaterialColor.ICE;
        case "LAPIS":
            return MaterialColor.LAPIS;
        case "METAL":
            return MaterialColor.METAL;
        case "NETHER":
            return MaterialColor.NETHER;
        case "NONE":
            return MaterialColor.NONE;
        case "PLANT":
            return MaterialColor.PLANT;
        case "PODZOL":
            return MaterialColor.PODZOL;
        case "QUARTZ":
            return MaterialColor.QUARTZ;
        case "RAW_IRON":
            return MaterialColor.RAW_IRON;
        case "SAND":
            return MaterialColor.SAND;
        case "SNOW":
            return MaterialColor.SNOW;
        case "STONE":
            return MaterialColor.STONE;
        case "TERRACOTTA_BLACK":
            return MaterialColor.TERRACOTTA_BLACK;
        case "TERRACOTTA_BLUE":
            return MaterialColor.TERRACOTTA_BLUE;
        case "TERRACOTTA_BROWN":
            return MaterialColor.TERRACOTTA_BROWN;
        case "TERRACOTTA_CYAN":
            return MaterialColor.TERRACOTTA_CYAN;
        case "TERRACOTTA_GRAY":
            return MaterialColor.TERRACOTTA_GRAY;
        case "TERRACOTTA_GREEN":
            return MaterialColor.TERRACOTTA_GREEN;
        case "TERRACOTTA_LIGHT_BLUE":
            return MaterialColor.TERRACOTTA_LIGHT_BLUE;
        case "TERRACOTTA_LIGHT_GRAY":
            return MaterialColor.TERRACOTTA_LIGHT_GRAY;
        case "TERRACOTTA_LIGHT_GREEN":
            return MaterialColor.TERRACOTTA_LIGHT_GREEN;
        case "TERRACOTTA_MAGENTA":
            return MaterialColor.TERRACOTTA_MAGENTA;
        case "TERRACOTTA_ORANGE":
            return MaterialColor.TERRACOTTA_ORANGE;
        case "TERRACOTTA_PINK":
            return MaterialColor.TERRACOTTA_PINK;
        case "TERRACOTTA_PURPLE":
            return MaterialColor.TERRACOTTA_PURPLE;
        case "TERRACOTTA_RED":
            return MaterialColor.TERRACOTTA_RED;
        case "TERRACOTTA_WHITE":
            return MaterialColor.TERRACOTTA_WHITE;
        case "TERRACOTTA_YELLOW":
            return MaterialColor.TERRACOTTA_YELLOW;
        case "WARPED_HYPHAE":
            return MaterialColor.WARPED_HYPHAE;
        case "WARPED_NYLIUM":
            return MaterialColor.WARPED_NYLIUM;
        case "WARPED_STEM":
            return MaterialColor.WARPED_STEM;
        case "WARPED_WART_BLOCK":
            return MaterialColor.WARPED_WART_BLOCK;
        case "WATER":
            return MaterialColor.WATER;
        case "WOOD":
            return MaterialColor.WOOD;
        case "WOOL":
            return MaterialColor.WOOL;
        default:
            ModularMaterials.warn("Unknown map color \"" + name + "\" of material \"" + uniqueMM_MaterialName + "\"!");
            return getMaterialColor("AUTO", color, uniqueMM_MaterialName);
        }
    }
    
    private static SoundType getSoundType(String name, String uniqueMM_MaterialName) {
        switch (name.toUpperCase()) {
        case "AMETHYST":
            return SoundType.AMETHYST;
        case "AMETHYST_CLUSTER":
            return SoundType.AMETHYST_CLUSTER;
        case "ANCIENT_DEBRIS":
            return SoundType.ANCIENT_DEBRIS;
        case "ANVIL":
            return SoundType.ANVIL;
        case "AZALEA":
            return SoundType.AZALEA;
        case "AZALEA_LEAVES":
            return SoundType.AZALEA_LEAVES;
        case "BAMBOO":
            return SoundType.BAMBOO;
        case "BAMBOO_SAPLING":
            return SoundType.BAMBOO_SAPLING;
        case "BASALT":
            return SoundType.BASALT;
        case "BIG_DRIPLEAF":
            return SoundType.BIG_DRIPLEAF;
        case "BONE_BLOCK":
            return SoundType.BONE_BLOCK;
        case "CALCITE":
            return SoundType.CALCITE;
        case "CANDLE":
            return SoundType.CANDLE;
        case "CAVE_VINES":
            return SoundType.CAVE_VINES;
        case "CHAIN":
            return SoundType.CHAIN;
        case "COPPER":
            return SoundType.COPPER;
        case "CORAL_BLOCK":
            return SoundType.CORAL_BLOCK;
        case "CROP":
            return SoundType.CROP;
        case "DEEPSLATE":
            return SoundType.DEEPSLATE;
        case "DEEPSLATE_BRICKS":
            return SoundType.DEEPSLATE_BRICKS;
        case "DEEPSLATE_TILES":
            return SoundType.DEEPSLATE_TILES;
        case "DRIPSTONE_BLOCK":
            return SoundType.DRIPSTONE_BLOCK;
        case "FLOWERING_AZALEA":
            return SoundType.FLOWERING_AZALEA;
        case "FUNGUS":
            return SoundType.FUNGUS;
        case "GILDED_BLACKSTONE":
            return SoundType.GILDED_BLACKSTONE;
        case "GLASS":
            return SoundType.GLASS;
        case "GLOW_LICHEN":
            return SoundType.GLOW_LICHEN;
        case "GRASS":
            return SoundType.GRASS;
        case "GRAVEL":
            return SoundType.GRAVEL;
        case "HANGING_ROOTS":
            return SoundType.HANGING_ROOTS;
        case "HARD_CROP":
            return SoundType.HARD_CROP;
        case "HONEY_BLOCK":
            return SoundType.HONEY_BLOCK;
        case "LADDER":
            return SoundType.LADDER;
        case "LANTERN":
            return SoundType.LANTERN;
        case "LARGE_AMETHYST_BUD":
            return SoundType.LARGE_AMETHYST_BUD;
        case "LILY_PAD":
            return SoundType.LILY_PAD;
        case "LODESTONE":
            return SoundType.LODESTONE;
        case "MEDIUM_AMETHYST_BUD":
            return SoundType.MEDIUM_AMETHYST_BUD;
        case "METAL":
            return SoundType.METAL;
        case "MOSS":
            return SoundType.MOSS;
        case "MOSS_CARPET":
            return SoundType.MOSS_CARPET;
        case "NETHER_BRICKS":
            return SoundType.NETHER_BRICKS;
        case "NETHER_GOLD_ORE":
            return SoundType.NETHER_GOLD_ORE;
        case "NETHER_ORE":
            return SoundType.NETHER_ORE;
        case "NETHER_SPROUTS":
            return SoundType.NETHER_SPROUTS;
        case "NETHER_WART":
            return SoundType.NETHER_WART;
        case "NETHERITE_BLOCK":
            return SoundType.NETHERITE_BLOCK;
        case "NETHERRACK":
            return SoundType.NETHERRACK;
        case "NYLIUM":
            return SoundType.NYLIUM;
        case "POINTED_DRIPSTONE":
            return SoundType.POINTED_DRIPSTONE;
        case "POLISHED_DEEPSLATE":
            return SoundType.POLISHED_DEEPSLATE;
        case "POWDER_SNOW":
            return SoundType.POWDER_SNOW;
        case "ROOTED_DIRT":
            return SoundType.ROOTED_DIRT;
        case "ROOTS":
            return SoundType.ROOTS;
        case "SAND":
            return SoundType.SAND;
        case "SCAFFOLDING":
            return SoundType.SCAFFOLDING;
        case "SCULK_SENSOR":
            return SoundType.SCULK_SENSOR;
        case "SHROOMLIGHT":
            return SoundType.SHROOMLIGHT;
        case "SLIME_BLOCK":
            return SoundType.SLIME_BLOCK;
        case "SMALL_AMETHYST_BUD":
            return SoundType.SMALL_AMETHYST_BUD;
        case "SMALL_DRIPLEAF":
            return SoundType.SMALL_DRIPLEAF;
        case "SNOW":
            return SoundType.SNOW;
        case "SOUL_SAND":
            return SoundType.SOUL_SAND;
        case "SOUL_SOIL":
            return SoundType.SOUL_SOIL;
        case "SPORE_BLOSSOM":
            return SoundType.SPORE_BLOSSOM;
        case "STEM":
            return SoundType.STEM;
        case "STONE":
            return SoundType.STONE;
        case "SWEET_BERRY_BUSH":
            return SoundType.SWEET_BERRY_BUSH;
        case "TUFF":
            return SoundType.TUFF;
        case "TWISTING_VINES":
            return SoundType.TWISTING_VINES;
        case "VINE":
            return SoundType.VINE;
        case "WART_BLOCK":
            return SoundType.WART_BLOCK;
        case "WEEPING_VINES":
            return SoundType.WEEPING_VINES;
        case "WET_GRASS":
            return SoundType.WET_GRASS;
        case "WOOD":
            return SoundType.WOOD;
        case "WOOL":
            return SoundType.WOOL;
        default:
            ModularMaterials.warn("Unknown sound \"" + name + "\" of material \"" + uniqueMM_MaterialName + "\"!");
            return SoundType.METAL;
        }
    }
    
    private static int distanceSq(ColorProperties color1, int color2) {
        int dR = color1.red - (color2 >> 16 & 255);
        int dG = color1.green - (color2 >> 8 & 255);
        int dB = color1.blue - (color2 & 255);
        return dR * dR + dG * dG + dB * dB;
    }

}
