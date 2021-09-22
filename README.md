[![](http://cf.way2muchnoise.eu/381517.svg)](https://www.curseforge.com/minecraft/mc-mods/modular-materials) [![](http://cf.way2muchnoise.eu/versions/381517.svg)](https://www.curseforge.com/minecraft/mc-mods/modular-materials)

Modular Materials ([CurseForge](https://www.curseforge.com/minecraft/mc-mods/modular-materials))
=================

#### Features

 - [x] Automatic Item, Block and Fluid Registration, customizable in `./config/modularmaterials/types.json`
    - [x] Enabled / Disabled
    - [x] Name
    - [x] Category (Item/Block/Fluid/Ore)
    - [x] Burn Time Multiplier
    - [x] If Items of this Type should have Tooltips
    - [x] Name Syntax
    - [x] Tags
 - [ ] Per-Material Tweaks (`./config/modularmaterials/materials.json`):
    - [x] Name
    - [x] Tag Names
    - [x] Enabled / Disabled
    - [x] Enabled for specific Types
    - [x] Color (Red, Green, Blue, Alpha)
    - [x] Burn Time
    - [x] State
    - [x] Texture
    - [x] Tooltip
    - [ ] Block-specific:
      - [ ] Enchantment Power Bonus
      - [ ] Fire Spread Speed
      - [ ] Flammability
      - [ ] Friction
      - [ ] Hardness
      - [ ] Jump Factor
      - [ ] Light Level
      - [ ] Map Color
      - [ ] Material
      - [ ] If a Tool is required to break this Block
      - [ ] Blast Resistance
      - [ ] Sound
      - [ ] Speed Factor
      - [ ] If the Block is sticky (like Honey or Slime Blocks)
      - [ ] Tags
    - [ ] Fluid-specific:
      - [ ] Boiling Temperature
      - [ ] Melting Temperature
      - [ ] Current Temperature
      - [ ] Type-specific:
        - [ ] Density
        - [ ] Viscosity
        - [ ] Light Level
    - [x] Item-specific:
      - [x] If the Item is Fire Resistant (like Netherite Tools)
      - [x] If the Item has a holographic Effect (like enchanted Items)
      - [x] Lifespan (time it takes until the Item despawns if left on the Ground)
      - [x] Rarity (what color the name has)
      - [x] Tags
    - [ ] Ore-specific:
      - [ ] Hardness
      - [ ] Light level
      - [ ] Blast Resistance
      - [ ] Tags
 - [ ] Unit-based Tags: each Type has a value associated with it (for Example Ingots, Plates, Dusts 1u, Nuggets 1/9u or Blocks 9u). This allows for more precise Inputs, because you could, for Example, set "1uCopper" as an Input for an Alloy Smelter and it would accept all Copper Ingots, Dusts, Plates, etc. This makes sense, because in this case it doesn't really matter what Shape or Form the Items have, as long as they are the same physical Amount
 - [ ] Automatic creation of Lang-, Blockstate-, Tag- and Model-Files
 - [ ] Support for custom Textures/Texture Sets
 - [x] Custom Tooltip-Formatting:
    - [x] Animated Segments
    - [x] Ability to show the Line only when SHIFT, CTRL or ALT is pressed (or only when they are not pressed)
    - [x] Customize Background & Border Colors
 - [ ] GregTech 5-like Ore-Generation
 - [ ] GT:NH-like Ore-Generation
 - [ ] Option to disable Vanilla Ore-Generation
 - [ ] [Presets](presets/) for Types, Ores and Materials
 - [ ] Wiki
