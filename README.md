Modular Materials
=================
Requires Resource Loader by Lumien231 ([GitHub](https://github.com/lumien231/Resource-Loader) | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/resource-loader))

*Coding and code-structuring advice highly appreciated :)*

#### Features

 - [ ] Automatic Item, Block and Fluid Registration, customizable in `./config/modularmaterials/types.json` (only Items are already implemented)
    - [x] Category (If the Type should be an Item, Block, Fluid or Ore)
    - [x] OreDictionary Prefix (for Example the "ingot" of "ingotCopper")
    - [x] Unit Value (how much the Type should be "worth", see below)
    - [x] Localization-Syntax
 - [ ] Per-Material Tweaks (`./config/modularmaterials/materials.json`):
    - [ ] Block Hardness
    - [ ] Block Harvest-Level
    - [ ] Block Light-Level
    - [ ] Block Resistance
    - [ ] Boiling Temperature
    - [x] Color (alpha, red, green, blue)
    - [x] Ability to disable the Material completely
    - [x] Ability to disable the Material for certain Types
    - [ ] Gas Density
    - [ ] Gas Viscosity
    - [ ] If the Material can be used as a Beacon-Payment
    - [ ] Liquid Density
    - [ ] Liquid Viscosity
    - [ ] Melting Temperature
    - [x] Meta Value
    - [x] Name
    - [x] OreDictionary Entries
    - [ ] Ore Hardness
    - [ ] Ore Harvest-Level
    - [ ] Ore Light-Level
    - [ ] Ore Resistance
    - [ ] State of Matter
    - [ ] Temperature
    - [x] Texture
    - [x] Tooltip
 - [x] Unit-based OreDictionary: each Type has a value associated with it (for Example Ingots, Plates, Dusts 1u, Nuggets 1/9u or Blocks 9u). This allows for more precise Inputs, because you could, for Example, set "1uCopper" as an Input for an Alloy Smelter and it would accept all Copper Ingots, Dusts, Plates, etc. This makes sense, because in this case it doesn't really matter what Shape or Form the Items have, as long as they are the same physical Amount
 - [x] Automatic creation of Lang- and Model-Files
 - [x] Support for custom Textures/Texture Sets
 - [x] Custom Tooltip-Formatting:
    - [x] Animated Segments
    - [x] Ability to show the Line only when SHIFT, CTRL or ALT is pressed (or only when they are not pressed)
 - [ ] GregTech-like Ore-Generation
