####Material

|value|vanilla example|properties|
|:---|:---|:---:|
|**AIR**|Air|replaceable, not solid, blocks no light, blocks no movement|
|**ANVIL**|Anvil|requires tool, immovable|
|**BARRIER**|Barrier|requires tool, immovable|
|**CACTUS**|Cactus|translucent, unpushable|
|**CAKE**|Cake|unpushable|
|**CARPET**|Carpet|can burn, adventure mode exempt, is not solid, blocks no light, blocks no movement|
|**CIRCUITS**|Button, Skull|unpushable, adventure mode exempt, is not solid, blocks no light, blocks no movement|
|**CLAY**|Clay, Monster Egg||
|**CLOTH**|Bed|can burn|
|**CORAL**||unpushable|
|**CRAFTED_SNOW**|Snow|requires tool|
|**DRAGON_EGG**|Dragon Egg|unpushable|
|**FIRE**|Fire|unpushable, replaceable, not solid, blocks no light, blocks no movement|
|**GLASS**|Glass, Beacon|translucent, adventure mode exempt|
|**GOURD**|Melon, Pumpkin|unpushable|
|**GRASS**|Grass Block, Mycelium||
|**GROUND**|Dirt, Farmland||
|**ICE**|Ice|translucent, adventure mode exempt|
|**IRON**|Block Of Iron, Hopper|requires tool|
|**LAVA**|Lava|unpushable, replaceable, unpushable, liquid, blocks no movement, not solid|
|**LEAVES**|Leaves|can burn, translucent, unpushable|
|**PACKED_ICE**|Packed Ice|adventure mode exempt|
|**PISTON**|Piston, Sticky Piston|immovable|
|**PLANTS**|Sugar Cane, Cocoa|unpushable, adventure mode exempt, is not solid, blocks no light, blocks no movement|
|**PORTAL**|Portal|immovable, not solid, blocks no light, blocks no movement|
|**REDSTONE_LIGHT**|Redstone Lamp|adventure mode exempt|
|**ROCK**|Stone, Obsidian|requires tool|
|**SAND**|Concrete Powder, Soul Sand||
|**SNOW**|Snow Layer|replaceable, translucent, requires tool, unpushable, adventure mode exempt, is not solid, blocks no light, blocks no movement|
|**SPONGE**|Sponge||
|**STRUCTURE_VOID**|Structure Void|replaceable, not solid, blocks no light, blocks no movement|
|**TNT**|TNT|can burn, translucent|
|**VINE**|Flowers, Vine|can burn, unpushable, replaceable, adventure mode exempt, is not solid, blocks no light, blocks no movement|
|**WATER**|Water|unpushable, replaceable, unpushable, liquid, blocks no movement, not solid|
|**WEB**|Cobweb|blocks no movement, requires tool, unpushable|
|**WOOD**|Logs, Planks|can burn|

- *adventure mode exempt:* this block doesn't obey the special rules of the adventure mode
- *blocks no light:* dirt underneath this block can turn to grass and grass underneath won't turn to dirt
- *blocks no movements:* entities can pass through this block
- *can burn:* the block can burn (which might destroy the block) and spreads fire
- *immovable:* the block can't be pushed and pistons can't move over it
- *liquid:* the block is treated as a fluid
- *not solid:* the block is not considered solid
- *replaceable:* the block will be destroyed by placing an other block at its position
- *requires tool:* the block requires the correct tool to be harvested
- *translucent:* the block is considered translucent
- *unpushable:* the block can't be pushed, but pistons can move over it


####SoundType

|value|vanilla example|
|:---|:---|
|**ANVIL**|Anvil|
|**CLOTH**|Wool, Carpet|
|**GLASS**|Glass, Ice|
|**GROUND**|Dirt, Gravel|
|**LADDER**|Ladder|
|**METAL**|Block Of Iron, Hopper|
|**PLANT**|Flowers, Grass Block|
|**SAND**|Sand, Concrete Powder|
|**SLIME**|Slime Block|
|**SNOW**|Snow, Snow Layer|
|**STONE**|Stone, Iron Ore|
|**WOOD**|Logs, Planks|


####MapColor

|value|index|color|
|:---|:---:|:---:|
|**AUTO**||calculates nearest mapColor from the configured color|
|**AIR**|0|transparent|
|**GRASS**|1|[#6D9930](https://image-color.com/color-picker.html#6D9930)|
|**SAND**|2|[#F7E9A3](https://image-color.com/color-picker.html#F7E9A3)|
|**CLOTH**|3|[#696969](https://image-color.com/color-picker.html#696969)|
|**TNT**|4|[#DC0000](https://image-color.com/color-picker.html#DC0000)|
|**ICE**|5|[#8A8ADC](https://image-color.com/color-picker.html#8A8ADC)|
|**IRON**|6|[#909090](https://image-color.com/color-picker.html#909090)|
|**FOLIAGE**|7|[#006A00](https://image-color.com/color-picker.html#006A00)|
|**SNOW**|8|[#DCDCDC](https://image-color.com/color-picker.html#DCDCDC)|
|**CLAY**|9|[#8D909E](https://image-color.com/color-picker.html#8D909E)|
|**DIRT**|10|[#825E42](https://image-color.com/color-picker.html#825E42)|
|**STONE**|11|[#606060](https://image-color.com/color-picker.html#606060)|
|**WATER**|12|[#3737DC](https://image-color.com/color-picker.html#3737DC)|
|**WOOD**|13|[#7B663E](https://image-color.com/color-picker.html#7B663E)|
|**QUARTZ**|14|[#DCD9D3](https://image-color.com/color-picker.html#DCD9D3)|
|**ORANGE**|15|[#BA6D2C](https://image-color.com/color-picker.html#BA6D2C)|
|**MAGENTA**|16|[#9941BA](https://image-color.com/color-picker.html#9941BA)|
|**LIGHT_BLUE**|17|[#5884BA](https://image-color.com/color-picker.html#5884BA)|
|**YELLOW**|18|[#C5C52C](https://image-color.com/color-picker.html#C5C52C)|
|**LIME**|19|[#6DB015](https://image-color.com/color-picker.html#6DB015)|
|**PINK**|20|[#D06D8E](https://image-color.com/color-picker.html#D06D8E)|
|**GRAY**|21|[#414141](https://image-color.com/color-picker.html#414141)|
|**SILVER**|22|[#848484](https://image-color.com/color-picker.html#848484)|
|**CYAN**|23|[#416D84](https://image-color.com/color-picker.html#416D84)|
|**PURPLE**|24|[#6D3699](https://image-color.com/color-picker.html#6D3699)|
|**BLUE**|25|[#2C4199](https://image-color.com/color-picker.html#2C4199)|
|**BROWN**|26|[#58412C](https://image-color.com/color-picker.html#58412C)|
|**GREEN**|27|[#586D2C](https://image-color.com/color-picker.html#586D2C)|
|**RED**|28|[#842C2C](https://image-color.com/color-picker.html#842C2C)|
|**BLACK**|29|[#151515](https://image-color.com/color-picker.html#151515)|
|**GOLD**|30|[#D7CD42](https://image-color.com/color-picker.html#D7CD42)|
|**DIAMOND**|31|[#4FBCB7](https://image-color.com/color-picker.html#4FBCB7)|
|**LAPIS**|32|[#3F6EDC](https://image-color.com/color-picker.html#3F6EDC)|
|**EMERALD**|33|[#00BB32](https://image-color.com/color-picker.html#00BB32)|
|**OBSIDIAN**|34|[#6F4A2A](https://image-color.com/color-picker.html#6F4A2A)|
|**NETHERRACK**|35|[#600100](https://image-color.com/color-picker.html#600100)|
|**WHITE_CLAY**|36|[#B4988A](https://image-color.com/color-picker.html#B4988A)|
|**ORANGE_CLAY**|37|[#89461F](https://image-color.com/color-picker.html#89461F)|
|**MAGENTA_CLAY**|38|[#804B5D](https://image-color.com/color-picker.html#804B5D)|
|**LIGHT_BLUE_CLAY**|39|[#605D77](https://image-color.com/color-picker.html#605D77)|
|**YELLOW_CLAY**|40|[#A0721F](https://image-color.com/color-picker.html#A0721F)|
|**LIME_CLAY**|41|[#58642D](https://image-color.com/color-picker.html#58642D)|
|**PINK_CLAY**|42|[#8A4243](https://image-color.com/color-picker.html#8A4243)|
|**GRAY_CLAY**|43|[#31231E](https://image-color.com/color-picker.html#31231E)|
|**SILVER_CLAY**|44|[#745C54](https://image-color.com/color-picker.html#745C54)|
|**CYAN_CLAY**|45|[#4B3B3B](https://image-color.com/color-picker.html#4B3B3B)|
|**PURPLE_CLAY**|46|[#693E4B](https://image-color.com/color-picker.html#693E4B)|
|**BLUE_CLAY**|47|[#41354F](https://image-color.com/color-picker.html#41354F)|
|**BROWN_CLAY**|48|[#412B1E](https://image-color.com/color-picker.html#412B1E)|
|**GREEN_CLAY**|49|[#414624](https://image-color.com/color-picker.html#414624)|
|**RED_CLAY**|50|[#7A3327](https://image-color.com/color-picker.html#7A3327)|
|**BLACK_CLAY**|51|[#1F120D](https://image-color.com/color-picker.html#1F120D)|
