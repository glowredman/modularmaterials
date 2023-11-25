package glowredman.modularmaterials.block;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import glowredman.modularmaterials.MM_Reference;
import glowredman.modularmaterials.ModularMaterials;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_OreVariant;
import glowredman.modularmaterials.data.object.MM_Type;
import glowredman.modularmaterials.data.object.sub.Category;
import glowredman.modularmaterials.data.object.sub.ColorProperties;
import glowredman.modularmaterials.worldgen.FeatureVeinLayer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

public class BlockHandler {
    
    private static final Map<String, NoteBlockInstrument> INSTRUMENTS = Arrays.stream(NoteBlockInstrument.values()).collect(Collectors.toMap(NoteBlockInstrument::getSerializedName, nbi -> nbi));
    
    @SubscribeEvent
    public void registerBlocks(RegisterEvent event) {
    	if(!event.getRegistryKey().equals(Registries.BLOCK)) {
    		return;
    	}
    	
        long time = System.currentTimeMillis();
        
        // BLOCKS
        for(Entry<String, MM_Type> eType : MM_Reference.TYPES.entrySet()) {
            String typeName = eType.getKey();
            MM_Type type = eType.getValue();
            
            if(type.category == Category.BLOCK && (type.enabled || MM_Reference.CONFIG.enableAll)) {
                for(Entry<String, MM_Material> eMaterial : MM_Reference.MATERIALS.entrySet()) {
                    String materialName = eMaterial.getKey();
                    MM_Material material = eMaterial.getValue();
                    if((material.enabled && material.enabledTypes.contains(typeName)) || MM_Reference.CONFIG.enableAll) {
                    	ResourceLocation regName = new ResourceLocation(MM_Reference.MODID, typeName + "." + materialName);
                    	Registry.register(BuiltInRegistries.BLOCK, regName, new MetaBlock(material, type, materialName, regName));
                    }
                }
            }
        }
        
        // ORES
        for(Entry<String, MM_Material> eMaterial : MM_Reference.MATERIALS.entrySet()) {
        	MM_Material material = eMaterial.getValue();
        	if((material.enabled && material.enabledTypes.contains("ore")) || MM_Reference.CONFIG.enableAll) {
        		String materialName = eMaterial.getKey();
        		for(Entry<String, MM_OreVariant> eVariant : MM_Reference.ORE_VARIANTS.entrySet()) {
        			MM_OreVariant variant = eVariant.getValue();
        			if(variant.enabled || MM_Reference.CONFIG.enableAll) {
        				ResourceLocation regName = new ResourceLocation(MM_Reference.MODID, "ore." + eVariant.getKey() + "." + materialName);
        				if(variant.falling) {
        				    Registry.register(BuiltInRegistries.BLOCK, regName, new MetaFallingOreBlock(material, variant, materialName, regName));
        				} else {
        				    Registry.register(BuiltInRegistries.BLOCK, regName, new MetaOreBlock(material, variant, materialName, regName));
        				}
        			}
        		}
        	}
        }
        
        FeatureVeinLayer.initOresForBlocksList();
        
        ModularMaterials.LOGGER.info("Registered {} blocks. Took {}ms.", MM_Reference.BLOCKS.size() + MM_Reference.ORES.size(), System.currentTimeMillis() - time);
    }
    
    public static BlockBehaviour.Properties getBlockProperties(MM_Material material, MM_Type type, String uniqueMM_MaterialName) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of();
        properties.destroyTime(material.block.hardness);
        properties.explosionResistance(material.resistance * type.resistanceMultiplier);
        properties.friction(material.block.friction);
        properties.instrument(INSTRUMENTS.getOrDefault(material.noteblockInstrument, NoteBlockInstrument.HARP));
        properties.jumpFactor(material.jumpFactor * type.jumpFactorMultiplier);
        properties.lightLevel(state -> (int) (material.lightLevel * type.lightLevelMultiplier));
        properties.mapColor(getMapColor(material.block.mapColor, material.color, uniqueMM_MaterialName));
        if(material.block.requiresToolForDrops) properties.requiresCorrectToolForDrops();
        properties.sound(getSoundType(material.block.sound, uniqueMM_MaterialName));
        properties.speedFactor(material.speedFactor * type.speedFactorMultiplier);
        return properties;
    }
    
    public static BlockBehaviour.Properties getFluidBlockProperties(MM_Material material, MM_Type type, String uniqueMM_MaterialName) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of();
        properties.destroyTime(material.block.hardness);
        properties.explosionResistance(material.resistance * type.resistanceMultiplier);
        properties.jumpFactor(material.jumpFactor * type.jumpFactorMultiplier);
        properties.lightLevel(state -> (int) (material.lightLevel * type.lightLevelMultiplier));
        properties.mapColor(getMapColor("AUTO", material.color, uniqueMM_MaterialName));
        properties.noCollission();
        properties.noLootTable();
        properties.randomTicks();
        properties.speedFactor(material.speedFactor * type.speedFactorMultiplier);
        return properties;
    }
    
    @SuppressWarnings("deprecation")
    public static BlockBehaviour.Properties getOreBlockProperties(MM_Material material, Block parent, String uniqueMM_MaterialName) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of();
        properties.sound(MM_Reference.CONFIG.oresInheritSoundType                ? parent.defaultBlockState().getSoundType()     : getSoundType(material.ore.sound, uniqueMM_MaterialName));
        properties.destroyTime(MM_Reference.CONFIG.oresInheritHardness           ? parent.defaultDestroyTime()                   : material.ore.hardness);
        properties.explosionResistance(MM_Reference.CONFIG.oresInheritResistance ? parent.getExplosionResistance()               : material.ore.resistance);
        properties.friction(MM_Reference.CONFIG.oresInheritFriction              ? parent.getFriction()                          : material.ore.friction);
        properties.instrument(MM_Reference.CONFIG.oresInheritNoteblockInstrument ? parent.defaultBlockState().instrument()       : INSTRUMENTS.getOrDefault(material.noteblockInstrument, NoteBlockInstrument.HARP));
        properties.jumpFactor(MM_Reference.CONFIG.oresInheritJumpFactor          ? parent.getJumpFactor()                        : material.ore.jumpFactor);
        properties.lightLevel(state -> MM_Reference.CONFIG.oresInheritLightLevel ? parent.defaultBlockState().getLightEmission() : material.ore.lightLevel);
        properties.mapColor(MM_Reference.CONFIG.oresInheritMapColor              ? parent.defaultMapColor()                      : getMapColor(material.ore.mapColor, material.color, uniqueMM_MaterialName));
        properties.speedFactor(MM_Reference.CONFIG.oresInheritSpeedFactor        ? parent.getSpeedFactor()                       : material.ore.speedFactor);
        if(material.ore.requiresToolForDrops) properties.requiresCorrectToolForDrops();
        return properties;
    }
    
    private static MapColor getMapColor(String name, ColorProperties color, String uniqueMM_MaterialName) {

        switch (name.toUpperCase()) {
        case "AUTO":
            MapColor ret = MapColor.NONE; // index 0
            int smallestDistance = distanceSq(color, ret.col);
            for(int i = 1; i < 62; i++) {
                MapColor currentMapColor = MapColor.byId(i);
                int currentDistance = distanceSq(color, currentMapColor.col);
                if(currentDistance < smallestDistance) {
                    ret = currentMapColor;
                    smallestDistance = currentDistance;
                }
            }
            ModularMaterials.LOGGER.debug("Found {} as the nearest color for \"{}\" ({}). Distance: {}", Integer.toHexString(ret.col).toUpperCase(), uniqueMM_MaterialName, Integer.toHexString(color.getRGB()).toUpperCase(), Math.sqrt(smallestDistance));
            return ret;
        case "CLAY":
            return MapColor.CLAY;
        case "COLOR_BLACK":
            return MapColor.COLOR_BLACK;
        case "COLOR_BLUE":
            return MapColor.COLOR_BLUE;
        case "COLOR_BROWN":
            return MapColor.COLOR_BROWN;
        case "COLOR_CYAN":
            return MapColor.COLOR_CYAN;
        case "COLOR_GRAY":
            return MapColor.COLOR_GRAY;
        case "COLOR_GREEN":
            return MapColor.COLOR_GREEN;
        case "COLOR_LIGHT_BLUE":
            return MapColor.COLOR_LIGHT_BLUE;
        case "COLOR_LIGHT_GRAY":
            return MapColor.COLOR_LIGHT_GRAY;
        case "COLOR_LIGHT_GREEN":
            return MapColor.COLOR_LIGHT_GREEN;
        case "COLOR_MAGENTA":
            return MapColor.COLOR_MAGENTA;
        case "COLOR_ORANGE":
            return MapColor.COLOR_ORANGE;
        case "COLOR_PINK":
            return MapColor.COLOR_PINK;
        case "COLOR_PURPLE":
            return MapColor.COLOR_PURPLE;
        case "COLOR_RED":
            return MapColor.COLOR_RED;
        case "COLOR_YELLOW":
            return MapColor.COLOR_YELLOW;
        case "CRIMSON_HYPHAE":
            return MapColor.CRIMSON_HYPHAE;
        case "CRIMSON_NYLIUM":
            return MapColor.CRIMSON_NYLIUM;
        case "CRIMSON_STEM":
            return MapColor.CRIMSON_STEM;
        case "DEEPSLATE":
            return MapColor.DEEPSLATE;
        case "DIAMOND":
            return MapColor.DIAMOND;
        case "DIRT":
            return MapColor.DIRT;
        case "EMERALD":
            return MapColor.EMERALD;
        case "FIRE":
            return MapColor.FIRE;
        case "GLOW_LICHEN":
            return MapColor.GLOW_LICHEN;
        case "GOLD":
            return MapColor.GOLD;
        case "GRASS":
            return MapColor.GRASS;
        case "ICE":
            return MapColor.ICE;
        case "LAPIS":
            return MapColor.LAPIS;
        case "METAL":
            return MapColor.METAL;
        case "NETHER":
            return MapColor.NETHER;
        case "NONE":
            return MapColor.NONE;
        case "PLANT":
            return MapColor.PLANT;
        case "PODZOL":
            return MapColor.PODZOL;
        case "QUARTZ":
            return MapColor.QUARTZ;
        case "RAW_IRON":
            return MapColor.RAW_IRON;
        case "SAND":
            return MapColor.SAND;
        case "SNOW":
            return MapColor.SNOW;
        case "STONE":
            return MapColor.STONE;
        case "TERRACOTTA_BLACK":
            return MapColor.TERRACOTTA_BLACK;
        case "TERRACOTTA_BLUE":
            return MapColor.TERRACOTTA_BLUE;
        case "TERRACOTTA_BROWN":
            return MapColor.TERRACOTTA_BROWN;
        case "TERRACOTTA_CYAN":
            return MapColor.TERRACOTTA_CYAN;
        case "TERRACOTTA_GRAY":
            return MapColor.TERRACOTTA_GRAY;
        case "TERRACOTTA_GREEN":
            return MapColor.TERRACOTTA_GREEN;
        case "TERRACOTTA_LIGHT_BLUE":
            return MapColor.TERRACOTTA_LIGHT_BLUE;
        case "TERRACOTTA_LIGHT_GRAY":
            return MapColor.TERRACOTTA_LIGHT_GRAY;
        case "TERRACOTTA_LIGHT_GREEN":
            return MapColor.TERRACOTTA_LIGHT_GREEN;
        case "TERRACOTTA_MAGENTA":
            return MapColor.TERRACOTTA_MAGENTA;
        case "TERRACOTTA_ORANGE":
            return MapColor.TERRACOTTA_ORANGE;
        case "TERRACOTTA_PINK":
            return MapColor.TERRACOTTA_PINK;
        case "TERRACOTTA_PURPLE":
            return MapColor.TERRACOTTA_PURPLE;
        case "TERRACOTTA_RED":
            return MapColor.TERRACOTTA_RED;
        case "TERRACOTTA_WHITE":
            return MapColor.TERRACOTTA_WHITE;
        case "TERRACOTTA_YELLOW":
            return MapColor.TERRACOTTA_YELLOW;
        case "WARPED_HYPHAE":
            return MapColor.WARPED_HYPHAE;
        case "WARPED_NYLIUM":
            return MapColor.WARPED_NYLIUM;
        case "WARPED_STEM":
            return MapColor.WARPED_STEM;
        case "WARPED_WART_BLOCK":
            return MapColor.WARPED_WART_BLOCK;
        case "WATER":
            return MapColor.WATER;
        case "WOOD":
            return MapColor.WOOD;
        case "WOOL":
            return MapColor.WOOL;
        default:
            ModularMaterials.LOGGER.warn("Unknown map color \"{}\" of material \"{}\"!", name, uniqueMM_MaterialName);
            return getMapColor("AUTO", color, uniqueMM_MaterialName);
        }
    }
    
    //TODO: add missing cases
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
            ModularMaterials.LOGGER.warn("Unknown sound \"{}\" of material \"{}\"!", name, uniqueMM_MaterialName);
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
