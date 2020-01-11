package glowredman.modularmaterials.information;

import glowredman.modularmaterials.json.JColor;
import glowredman.modularmaterials.json.JItem;
import glowredman.modularmaterials.json.JText;

public class PropertiesItems {
	
	public static enum Items {
		//Circuits
		VACUUM_TUBE("Vacuum Tube", 0, new String[]{"circuitPrimitive", "circuitULV"}, false, "circuit/vacuum_tube"),
		ELECTRONIC_CIRCUIT("Electronic Circuit", 1, new String[]{"circuitBasic", "circuitLV"}, false, "circuit/electronic_circuit"),
		GOOD_ELECTRONIC_CIRCUIT("Good Electronic Circuit", 2, new String[]{"circuitGood", "circuitMV"}, false, "circuit/good_electronic_circuit"),
		NAND_CHIP("NAND Chip", 3, new String[]{"circuitPrimitive", "circuitULV", "chipNAND"}, false, "circuit/nand_chip"),
		INTEGRATED_LOGIC_CIRCUIT("Integrated Logic Circuit", 4, new String[]{"circuitBasic", "circuitLV"}, false, "circuit/integrated_logic_circuit"),
		GOOD_INTEGRATED_CIRCUIT("Good Integrated Circuit", 5, new String[]{"circuitGood", "circuitMV"}, false, "circuit/good_integrated_circuit"),
		ADVANCED_CIRCUIT("Advanced Circuit", 6, new String[]{"circuitAdvanced", "circuitHV"}, false, "circuit/advanced_circuit"),
		MICROPROCESSOR("Microprocessor", 7, new String[]{"circuitBasic", "circuitLV"}, false, "circuit/microprocessor"),
		INTEGRATED_PROCESSOR("Integrated Processor", 8, new String[]{"circuitGood", "circuitMV"}, false, "circuit/integrated_processor"),
		PROCESSOR_ASSEMBLY("Processor Assembly", 9, new String[]{"circuitAdvanced", "circuitHV"}, false, "circuit/processor_assembly"),
		WORKSTATION("Workstation", 10, new String[]{"circuitData", "circuitEV"}, false, "circuit/workstation"),
		MAINFRAME("Mainframe", 11, new String[]{"circuitElite", "circuitIV"}, false, "circuit/mainframe"),
		NANOPROCESSOR("Nanoprocessor", 12, new String[]{"circuitAdvanced", "circuitHV"}, false, "circuit/nanoprocessor"),
		NANOPROCESSOR_ASSEMBLY("Nanoprocessor Assembly", 13, new String[]{"circuitData", "circuitEV"}, false, "circuit/nanoprocessor_assembly"),
		ELITE_NANOCOMPUTER("Elite Nanocomputer", 14, new String[]{"circuitElite", "circuitIV"}, false, "circuit/elite_nanocomputer"),
		NANOPROCESSOR_MAINFRAME("Nanoprocessor Mainframe", 15, new String[]{"circuitMaster", "circuitLuV"}, false, "circuit/nanoprocessor_mainframe"),
		QUANTUMPROCESSOR("Quantumprocessor", 16, new String[]{"circuitData", "circuitEV"}, false, "circuit/quantumprocessor"),
		QUANTUMPROCESSOR_ASSEMBLY("Quantumprocessor Assembly", 17, new String[]{"circuitElite", "circuitIV"}, false, "circuit/quantumprocessor_assembly"),
		MASTER_QUANTUMCOMPUTER("Master Quantumcomputer", 18, new String[]{"circuitMaster", "circuitLuV"}, false, "circuit/master_quantumcomputer"),
		QUANTUMPROCESSOR_MAINFRAME("Quantumprocessor Mainframe", 19, new String[]{"circuitUltimate", "circuitZPM"}, false, "circuit/quantumprocessor_mainframe"),
		CRYSTALPROCESSOR("Crystalprocessor", 20, new String[]{"circuitElite", "circuitIV"}, false, "circuit/crystalprocessor"),
		CRYSTALPROCESSOR_ASSEMBLY("Crystalprocessor Assembly", 21, new String[]{"circuitMaster", "circuitLuV"}, false, "circuit/crystalprocessor_assembly"),
		ULTIMATE_CRYSTALCOMPUTER("Ultimate Crystalcomputer", 22, new String[]{"circuitUltimate", "circuitZPM"}, false, "circuit/ultimate_crystalcomputer"),
		CRYSTALPROCESSOR_MAINFRAME("Crystalprocessor Mainframe", 23, new String[]{"circuitSuperconductor", "circuitUV"}, false, "circuit/crystalprocessor_mainframe"),
		WETWAREPROCESSOR("Wetwareprocessor", 24, new String[]{"circuitMaster", "circuitLuV"}, false, "circuit/wetwareprocessor"),
		WETWAREPROCESSOR_ASSEMBLY("Wetwareprocessor Assembly", 25, new String[]{"circuitUltimate", "circuitZPM"}, false, "circuit/wetwareprocessor_assembly"),
		WETWARE_SUPERCOMPUTER("Wetware Supercomputer", 26, new String[]{"circuitSuperconductor", "circuitUV"}, false, "circuit/wetware_supercomputer"),
		WETWARE_MAINFRAME("Wetware Mainframe", 27, new String[]{"circuitInfinite", "circuitUHV"}, false, "circuit/wetware_mainframe"),
		BIOWAREPROCESSOR("Biowareprocessor", 28, new String[]{"circuitUltimate", "circuitZPM"}, false, "circuit/biowareprocessor"),
		BIOWAREPROCESSOR_ASSEMBLY("Biowareprocessor Assembly", 29, new String[]{"circuitSuperconductor", "circuitUV"}, false, "circuit/biowareprocessor_assembly"),
		BIOWARE_SUPERCOMPUTER("Bioware Supercomputer", 30, new String[]{"circuitInfinite", "circuitUHV"}, false, "circuit/bioware_supercomputer"),
		BIOWARE_MAINFRAME("Bioware Mainframe", 31, new String[]{"circuitBio", "circuitUEV"}, false, "circuit/bioware_mainframe"),
		PROCESSOR_OF_DOOM("Processor Of DOOM", 32, new String[]{"circuitSuperconductor", "CircuitUV"}, false, "circuit/processor_of_doom"),
		PROCESSOR_ASSEMBLY_OF_DOOM("Processor Assembly Of DOOM", 33, new String[]{"circuitInfinite", "circuitUHV"}, false, "circuit/processor_assembly_of_doom"),
		SUPERCOMPUTER_OF_DOOM("Supercomputer Of DOOM", 34, new String[]{"circuitBio", "circuitUEV"}, false, "circuit/supercomputer_of_doom"),
		MAINFRAME_OF_DOOM("Mainframe Of DOOM", 35, new String[]{"circuitDoom", "circuitUIV"}, false, "circuit/mainframe_of_doom"),
		ALIENPROCESSOR("Alienprocessor", 36, new String[]{"circuitInfinite", "circuitUHV"}, false, "circuit/alienprocessor"),
		ALIENPROCESSOR_ASSEMBLY("Alienprocessor Assembly", 37, new String[]{"circuitBio", "circuitUEV"}, false, "circuit/alienprocessor_assembly"),
		ALIEN_SUPERCOMPUTER("Alien Supercomputer", 38, new String[]{"circuitDoom", "circuitUIV"}, false, "circuit/alien_supercomputer"),
		ALIEN_MAINFRAME("Alien Mainframe", 39, new String[]{"circuitAlien", "circuitUMV"}, false, "circuit/alien_mainframe"),
		EMU_PROCESSOR("E.M.U. Processor", 40, new String[]{"circuitBio", "circuitUEV"}, false, "circuit/emu_processor"),
		EMU_PROCESSOR_ASSEMBLY("E.M.U. Processor Assembly", 41, new String[]{"circuitDoom", "circuitUIV"}, false, "circuit/emu_processor_assembly"),
		EMU_SUPERCOMPUTER("E.M.U. Supercomputer", 42, new String[]{"circuitAlien", "circuitUMV"}, false, "circuit/emu_supercomputer"),
		EMU_MAINFRAME("E.M.U. Mainframe", 43, new String[]{"circuitEMU", "circuitUXV"}, false, "circuit/emu_mainframe"),
		OVERPOWERED_PROCESSOR("Overpowered Processor", 44, new String[]{"circuitDoom", "circuitUIV"}, false, "circuit/overpowered_processor"),
		OVERPOWERED_PROCESSOR_ASSEMBLY("Overpowered Processor Assembly", 45, new String[]{"circuitAlien", "circuitUMV"}, false, "circuit/overpowered_processor_assembly"),
		OVERPOWERED_SUPERCOMPUTER("Overpowered Supercomputer", 46, new String[]{"circuitEMU", "circuitUXV"}, false, "circuit/overpowered_supercomputer"),
		OVERPOWERED_MAINFRAME("Overpowered Mainframe", 47, new String[]{"circuitOverpowered", "circuitOpV"}, false, "circuit/overpowered_mainframe"),
		MAXIMUM_PROCESSOR("Maximum Processor", 48, new String[]{"circuitAlien", "circuitUMV"}, false, "circuit/maximum_processor"),
		MAXIMUM_PROCESSOR_ASSEMBLY("Maximum Processor Assembly", 49, new String[]{"circuitEMU", "circuitUXV"}, false, "circuit/maximum_processor_assembly"),
		MAXIMUM_SUPERCOMPUTER("Maximum Supercomputer", 50, new String[]{"circuitOverpowered", "circuitOpV"}, false, "circuit/maximum_supercomputer"),
		MAXIMUM_MAINFRAME("Maximum Mainframe", 51, new String[]{"circuitMaximum", "circuitMAX"}, false, "circuit/maximum_mainframe"),
		
		//Circuit Boards
		CARDBOARD_CIRCUIT_BOARD("Cardboard Circuit Board", 60, new String[]{}, "cube:circuit_board/cardboard"),
		WOODEN_CIRCUIT_BOARD("Wooden Circuit Board", 61, new String[]{}, "cube:circuit_board/wooden"),
		COATED_CIRCUIT_BOARD("Coated Circuit Board", 62, new String[]{}, "cube:circuit_board/coated"),
		PHENOLIC_CIRCUIT_BOARD("Phenolic Circuit Board", 63, new String[]{}, "cube:circuit_board/phenolic"),
		EPOXY_CIRCUIT_BOARD("Epoxy Circuit Board", 64, new String[]{}, "cube:circuit_board/epoxy"),
		PLASTIC_CIRCUIT_BOARD("Plastc Circuit Board", 65, new String[]{}, "cube:circuit_board/plastic"),
		FIBERREINFORCED_CIRCUIT_BOARD("Fiber-Reinforced Circuit Board", 66, new String[]{}, "cube:circuit_board/fiber-reinforced"),
		MULITLAYER_FIBERREINFORCED_CIRCUIT_BOARD("Multilayer Fiber-Reinforced Circuit Board", 67, new String[]{}, "cube:circuit_board/multilayer_fiber-reinforced"),
		WETWARE_LIFESUPPORT_CIRCUIT_BOARD("Wetware Lifesupport Circuit Board", 68, new String[]{}, "cube:circuit_board/wetware"),
		ULTRA_BIO_MUTATED_CIRCUIT_BPARD("Ultra Bio Mutated Circuit Board", 69, new String[]{}, "cube:circuit_board/bio"),
		DOOMSDAY_CIRCUIT_BOARD("Doomsday Circuit Board", 70, new String[]{}, "cube:circuit_board/doomsday"),
		ALIENTECHNOLOGY_CIRCUIT_BOARD("Alientechnology Circuit Board", 71, new String[]{}, "cube:circuit_board/alientechnology"),
		CRYOGNEIC_COOLED_CIRCUIT_BOARD("Cryogenic Cooled Circuit Board", 72, new String[]{}, "cube:circuit_board/cryogenic"),
		QUANTUM_LEVITATED_CIRCUIT_BOARD("Quantum Levitated Circuit Board", 73, new String[]{}, "cube:circuit_board/quantum_levitated"),
		CIRCUIT_BOARD_SINGULARITY("Circuit Board Connected To A Singularity", 74, new String[]{}, "cube:circuit_board/singularity"),
		
		//Machine Casings
		ULV_MACHINE_CASING("ULV Machine Casing", 100, "casingULV", "casing/ulv"),
		LV_MACHINE_CASING("LV Machine Casing", 101, "casingLV", "casing/lv"),
		MV_MACHINE_CASING("MV Machine Casing", 102, "casingMV", "casing/mv"),
		HV_MACHINE_CASING("HV Machine Casing", 103, "casingHV", "casing/hv"),
		EV_MACHINE_CASING("EV Machine Casing", 104, "casingEV", "casing/ev"),
		IV_MACHINE_CASING("IV Machine Casing", 105, "casingIV", "casing/iv"),
		LUV_MACHINE_CASING("LuV Machine Casing", 106, "casingLuV", "casing/luv"),
		ZPM_MACHINE_CASING("ZPM Machine Casing", 107, "casingZPM", "casing/zpm"),
		UV_MACHINE_CASING("UV Machine Casing", 108, "casingUV", "casing/uv"),
		UHV_MACHINE_CASING("UHV Machine Casing", 109, "casingUHV", "casing/uhv"),
		UEV_MACHINE_CASING("UEV Machine Casing", 110, "casingUEV", "casing/uev"),
		UIV_MACHINE_CASING("UIV Machine Casing", 111, "casingUIV", "casing/uiv"),
		UMV_MACHINE_CASING("UMV Machine Casing", 112, "casingUMV", "casing/umv"),
		UXV_MACHINE_CASING("UXV Machine Casing", 113, "casingUXV", "casing/uxv"),
		OPV_MACHINE_CASING("OpV Machine Casing", 114, "casingOpV", "casing/opv"),
		MAX_MACHINE_CASING("MAX Machine Casing", 115, "casingMAX", "casing/max"),
		;

		private JText name = new JText();
		private int meta;
		private String[] oreDict = new String[]{};
		private boolean disabled;
		private String texture; //is the item's texture when useCustomTexture=true, otherwise it's the type of the iconSet (e.g. "ingot" or "plate")
		private boolean useCustomTexture;
		private String iconSet;
		private JColor color = new JColor();
		private JText[] tooltip = new JText[]{};
		
		private Items(JText name, int meta, String[] oreDict, boolean disabled, String texture, JText[] tooltip) {
			this.color = null;
			this.disabled = disabled;
			this.iconSet = null;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.texture = texture;
			this.tooltip = tooltip;
			this.useCustomTexture = true;
		}
		
		private Items(String name, int meta, String[] oreDict, boolean disabled, String texture, JText[] tooltip) {
			this.color = null;
			this.disabled = disabled;
			this.iconSet = null;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.texture = texture;
			this.tooltip = tooltip;
			this.useCustomTexture = true;
		}
		
		private Items(JText name, int meta, String[] oreDict, boolean disabled, String texture) {
			this.color = null;
			this.disabled = disabled;
			this.iconSet = null;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.texture = texture;
			this.tooltip = null;
			this.useCustomTexture = true;
		}
		
		private Items(String name, int meta, String[] oreDict, boolean disabled, String texture) {
			this.color = null;
			this.disabled = disabled;
			this.iconSet = null;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.texture = texture;
			this.tooltip = new JText[]{};
			this.useCustomTexture = true;
		}
		
		private Items(JText name, int meta, String[] oreDict, boolean disabled, String texture, String iconSet, JColor color, JText[] tooltip) {
			this.color = color;
			this.disabled = disabled;
			this.iconSet = iconSet;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.texture = texture;
			this.tooltip = tooltip;
			this.useCustomTexture = false;
		}
		
		private Items(String name, int meta, String[] oreDict, boolean disabled, String texture, String iconSet, JColor color, JText[] tooltip) {
			this.color = color;
			this.disabled = disabled;
			this.iconSet = iconSet;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.texture = texture;
			this.tooltip = tooltip;
			this.useCustomTexture = true;
		}
		
		private Items(JText name, int meta, String[] oreDict, boolean disabled, String texture, String iconSet, JColor color) {
			this.color = color;
			this.disabled = disabled;
			this.iconSet = iconSet;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.texture = texture;
			this.tooltip = new JText[]{};
			this.useCustomTexture = true;
		}
		
		private Items(String name, int meta, String[] oreDict, boolean disabled, String texture, String iconSet, JColor color) {
			this.color = color;
			this.disabled = disabled;
			this.iconSet = iconSet;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.texture = texture;
			this.tooltip = new JText[]{};
			this.useCustomTexture = true;
		}
		
		private Items(JText name, int meta, String[] oreDict, String texture, JText[] tooltip) {
			this.color = null;
			this.disabled = false;
			this.iconSet = null;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.texture = texture;
			this.tooltip = tooltip;
			this.useCustomTexture = true;
		}
		
		private Items(String name, int meta, String[] oreDict, String texture, JText[] tooltip) {
			this.color = null;
			this.disabled = false;
			this.iconSet = null;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.texture = texture;
			this.tooltip = tooltip;
			this.useCustomTexture = true;
		}
		
		private Items(JText name, int meta, String[] oreDict, String texture) {
			this.color = null;
			this.disabled = false;
			this.iconSet = null;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.texture = texture;
			this.tooltip = null;
			this.useCustomTexture = true;
		}
		
		private Items(String name, int meta, String[] oreDict, String texture) {
			this.color = null;
			this.disabled = false;
			this.iconSet = null;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.texture = texture;
			this.tooltip = new JText[]{};
			this.useCustomTexture = true;
		}
		
		private Items(JText name, int meta, String[] oreDict, String texture, String iconSet, JColor color, JText[] tooltip) {
			this.color = color;
			this.disabled = false;
			this.iconSet = iconSet;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.texture = texture;
			this.tooltip = tooltip;
			this.useCustomTexture = false;
		}
		
		private Items(String name, int meta, String[] oreDict, String texture, String iconSet, JColor color, JText[] tooltip) {
			this.color = color;
			this.disabled = false;
			this.iconSet = iconSet;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.texture = texture;
			this.tooltip = tooltip;
			this.useCustomTexture = true;
		}
		
		private Items(JText name, int meta, String[] oreDict, String texture, String iconSet, JColor color) {
			this.color = color;
			this.disabled = false;
			this.iconSet = iconSet;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.texture = texture;
			this.tooltip = new JText[]{};
			this.useCustomTexture = true;
		}
		
		private Items(String name, int meta, String[] oreDict, String texture, String iconSet, JColor color) {
			this.color = color;
			this.disabled = false;
			this.iconSet = iconSet;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.texture = texture;
			this.tooltip = new JText[]{};
			this.useCustomTexture = true;
		}
		
		private Items(JText name, int meta, String oreDict, String texture, JText[] tooltip) {
			this.color = null;
			this.disabled = false;
			this.iconSet = null;
			this.meta = meta;
			this.name = name;
			this.oreDict = new String[]{oreDict};
			this.texture = texture;
			this.tooltip = tooltip;
			this.useCustomTexture = true;
		}
		
		private Items(String name, int meta, String oreDict, String texture, JText[] tooltip) {
			this.color = null;
			this.disabled = false;
			this.iconSet = null;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = new String[]{oreDict};
			this.texture = texture;
			this.tooltip = tooltip;
			this.useCustomTexture = true;
		}
		
		private Items(JText name, int meta, String oreDict, String texture) {
			this.color = null;
			this.disabled = false;
			this.iconSet = null;
			this.meta = meta;
			this.name = name;
			this.oreDict = new String[]{oreDict};
			this.texture = texture;
			this.tooltip = null;
			this.useCustomTexture = true;
		}
		
		private Items(String name, int meta, String oreDict, String texture) {
			this.color = null;
			this.disabled = false;
			this.iconSet = null;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = new String[]{oreDict};
			this.texture = texture;
			this.tooltip = new JText[]{};
			this.useCustomTexture = true;
		}
		
		private Items(JText name, int meta, String oreDict, String texture, String iconSet, JColor color, JText[] tooltip) {
			this.color = color;
			this.disabled = false;
			this.iconSet = iconSet;
			this.meta = meta;
			this.name = name;
			this.oreDict = new String[]{oreDict};
			this.texture = texture;
			this.tooltip = tooltip;
			this.useCustomTexture = false;
		}
		
		private Items(String name, int meta, String oreDict, String texture, String iconSet, JColor color, JText[] tooltip) {
			this.color = color;
			this.disabled = false;
			this.iconSet = iconSet;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = new String[]{oreDict};
			this.texture = texture;
			this.tooltip = tooltip;
			this.useCustomTexture = true;
		}
		
		private Items(JText name, int meta, String oreDict, String texture, String iconSet, JColor color) {
			this.color = color;
			this.disabled = false;
			this.iconSet = iconSet;
			this.meta = meta;
			this.name = name;
			this.oreDict = new String[]{oreDict};
			this.texture = texture;
			this.tooltip = new JText[]{};
			this.useCustomTexture = true;
		}
		
		private Items(String name, int meta, String oreDict, String texture, String iconSet, JColor color) {
			this.color = color;
			this.disabled = false;
			this.iconSet = iconSet;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = new String[]{oreDict};
			this.texture = texture;
			this.tooltip = new JText[]{};
			this.useCustomTexture = true;
		}
		
		public JColor getColor() {
			return this.color;
		}
		
		public boolean isDisabled() {
			return this.disabled;
		}
		
		public String getIconSet() {
			return this.iconSet;
		}
		
		public int getMeta() {
			return this.meta;
		}
		
		public JText getName() {
			return this.name;
		}
		
		public String[] getOreDict() {
			return this.oreDict;
		}
		
		public String getTexture() {
			return this.texture;
		}
		
		public JText[] getTooltip() {
			return this.tooltip;
		}
		
		public boolean usesCustomTexture() {
			return this.useCustomTexture;
		}
		
	}

}
