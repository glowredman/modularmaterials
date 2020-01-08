package glowredman.modularmaterials.information;

import java.util.List;

import javax.annotation.Nonnegative;

import glowredman.modularmaterials.json.JText;

public class Properties {
	
	public static enum Materials {
		

		//PERIODIC TABLE
		//			| meta	|	  name						|   oreDict					|red  green blue  alpha |tags					|its|bt|ft |hl|bh |oh |br |or|state|dnsty|vscty| tmp| mtmp|btmp|ll|tooltip
		HYDROGEN	(1,     "Hydrogen", 					new String[]{"Hydrogen"}, 	   0,    0, 0xff, 0xf0, "gas",				 	 "", 0, GS,	0, 0f, 0f, 0f, 0f, S3, 	   1,  100, nt,   14,  20,  0, "H"),
		HELIUM		(2,     new JText("Helium", R2),		new String[]{"Helium"},		0xff, 0xff,    0, 0xf0, "gas",					 "", 0, GS, 0, 0f, 0f, 0f, 0f, S3,     1,  100, nt,    1,   4,  0, "He"),
		LITHIUM		(3,     "Lithium",						new String[]{"Lithium"}, 	0xe1, 0xdc, 0xff,    0, "metal", 				 DL, 5, WT, 1, 5f, 3f, 6f, 0f, S1,   530, 1500, nt,  454, 1603, 0, "Li"),
		BERYLLIUM	(4,		"Beryllium", 					new String[]{"Beryllium"},	0x64, 0xb4, 0x64,    0, "metal", 				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  1850, 1500, nt, 1560, 2742, 0, "Be"),
		BORON		(5,		"Boron",						new String[]{"Boron"},		0xd2, 0xfa, 0xd2,    0, "metal",				  S, 1, WT, 1, 5f, 3f, 6f, 0f, S1,  2340, 1500, nt, 2349, 4200, 0, "B"),
		CARBON		(6,		"Carbon",						"Carbon",			0x14, 0x14, 0x14, 0, new String[]{"metal", "no_liquid"}, DL, 1, WT, 1, 5f, 3f, 6f, 0f, S1,  1950,    1, nt, 3915, 3915, 0, "C"),
		NITROGEN	(7,		"Nitrogen",						new String[]{"Nitrogen"},	0x96, 0xc8, 0xf0, 0xf0, "gas", 					 "", 0, GS, 0, 0f, 0f, 0f, 0f, S3,     1,  100, nt,   63,   77, 0, "N"),
		OXYGEN		(8, 	"Oxygen",						new String[]{"Oxygen"},		0x64, 0xc8, 0xf0, 0xf0, "gas",					 "", 0, GS,	0, 0f, 0f, 0f, 0f, S3,     1,  100, nt,   54,   90, 0, "O"),
		FLUORINE	(9,		"Fluorine",						new String[]{"Fluorine"},	0xff, 0xff, 0xff, 0x7f, "gas",					 "", 0, GS,	0, 0f, 0f, 0f, 0f, S3,     2,  100, nt,   53,   85, 0, "F"),
		NEON		(10,	new JText("Neon", R2),			new String[]{"Neon"},		0xff, 0x20,    0, 0xf0, "gas", 					 "", 0, GS,	0, 0f, 0f, 0f, 0f, S3,     1,  100, nt,   25,   27, 0, "Ne"),
		SODIUM		(11, 	"Sodium", 						new String[]{"Sodium"}, 	   0, 0x96,    0,    0, "metal",				  M, 1, WT, 1, 5f, 3f, 6f, 0f, S1,   927, 1500, nt,  371, 1156, 0, "Na"),
		MAGNESIUM	(12,	"Magnesium",					new String[]{"Magnesium"},	0xff, 0xc8, 0xc8,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  1584, 1500, nt,  923, 1363, 0, "Mg"),
		ALUMINIUM	(13,	"Aluminium",					new String[]{"Aluminium", "Aluminum"}, 0x80, 0xc8, 0xf0, 0, "metal",		 DL, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  2375, 1500, nt,  933, 2743, 0, "Al"),
		SILICON		(14,	"Silicon",						new String[]{"Silicon"},	0x3c, 0x3c, 0x50,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  2570, 1500, nt, 1687, 3538, 0, "Si"),
		PHOSPHORUS	(15, 	"Phosphorus",	  			 	"Phosphorus",			 0xff, 0xff, 0, 0, new String[]{"metal", "no_ingot"}, S, 1, WT, 1, 5f, 3f, 6f, 0f, S1,  2286, 1500, nt,  317,  554, 0, "P"),
		SULFUR		(16,	"Sulfur", 						new String[]{"Sulfur", "Sulphur"}, 0xc8, 0xc8, 0, 0, new String[]{"metal", "no_ingot"}, S, 1, WT, 1, 5f, 3f, 6f, 0f, S1, 1983, 1500, nt, 388, 718, 0, "S"),
		CHLORINE	(17,	"Chlorine",						new String[]{"Chlorine"}, 	0xff, 0xff, 0xff,    0, "gas",					 "", 0, GS, 0, 0f, 0f, 0f, 0f, S3, 	   3,  100, nt,  172,  239, 0, "Cl"),
		ARGON		(18,	new JText("Argon", R2),			new String[]{"Argon"},		   0, 0xff,    0,  240, "gas",					 "", 0, GS, 0, 0f, 0f, 0f, 0f, S3, 	   2,  100, nt,   84,   87, 0, "Ar"),
		POTASSIUM	(19,	"Potassium",					new String[]{"Potassium"},	0xfa, 0xfa, 0xfa,    0, "metal",				  M, 1, WT, 1, 5f, 3f, 6f, 0f, S1,   828, 1500, nt,  337, 1032, 0, "K"),
		CALCIUM		(20,	"Calcium",						new String[]{"Calcium"},	0xff, 0xf5, 0xf5,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  1378, 1500, nt, 1115, 1757, 0, "Ca"),
		SCANDIUM	(21,	"Scandium",						new String[]{"Scandium"},	0xb4, 0xb4, 0xba,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  2800, 1500, nt, 1814, 3109, 0, "Sc"),
		TITANIUM	(22,	"Titanium",						new String[]{"Titanium"},	0xdc, 0xa0, 0xf0,    0, "metal",				  M, 5, WT, 2, 5f, 4f, 6f, 0f, S1,  4110, 1500, nt, 1941, 3560, 0, "Ti"),
		VANADIUM	(23,	"Vanadium",						new String[]{"Vanadium"},	0x32, 0x32, 0x32,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  5500, 1500, nt, 2183, 3680, 0, "V"),
		CHROMIUM	(24,	"Chromium",   					new String[]{"Chromium", "Chrome"}, 0xff, 0xe6, 0xe6, 0, "metal",			 SY, 5, WT, 2, 5f, 4f, 6f, 0f, S1,  6300, 1500, nt, 2180, 2944, 0, "Cr"),
		MANGANESE	(25,	"Manganese",					new String[]{"Manganese"},	0xfa, 0xfa, 0xfa,    0, "metal",				 DL, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  5950, 1500, nt, 1519, 2334, 0, "Mn"),
		IRON		(26,	"Iron",	 new String[]{"Iron", "AnyIron"}, 0xc8, 0xc8, 0xc8, 0, new String[]{"metal", "no_ingot", "no_block"}, M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  6980, 1500, nt, 1811, 3134, 0, "Fe"),
		COBALT		(27,	"Cobalt", 						new String[]{"Cobalt"},		0x50, 0x50, 0xfa,    0, "metal",				  M, 5, WT, 2, 5f, 4f, 6f, 0f, S1,  8860, 1500, nt, 1768, 3200, 0, "Co"),
		NICKEL		(28,	"Nickel",						new String[]{"Nickel"},		0xc8, 0xc8, 0xfa,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  7810, 1500, nt, 1728, 3003, 0, "Ni"),
		COPPER		(29,	"Copper",						new String[]{"Copper", "AnyCopper"}, 0xff, 0x64, 0, 0, "metal",				 SY, 5, WT, 1, 5f, 2f, 6f, 0f, S1,  8020, 1500, nt, 1358, 2835, 0, "Cu"),
		ZINC		(30,	"Zinc",							new String[]{"Zinc"},		0xfa, 0xf0, 0xf0,    0, "metal", 				  M, 5, WT, 1, 5f, 2f, 6f, 0f, S1,  6570, 1500, nt,  693, 1180, 0, "Zn"),
		GALLIUM		(31,	"Gallium",						new String[]{"Gallium"},	0xdc, 0xdc, 0xff,    0, "metal",				 SY, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  6095, 1500, nt,  303, 2673, 0, "Ga"),
		GERMANIUM	(32,	new JText("Germanium", R2),		new String[]{"Germanium"},	0xf0, 0xff, 0xbe,    0, "metal",				 SY, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  5600, 1500, nt, 1211, 3106, 0, "Ge"),
		ARSENIC		(33,	new JText("Arsenic", R2),		"Arsenic",  0xb4, 0xb4, 0xba, 0, new String[]{"metal", "no_liquid"},		  S, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  5220,    1, nt,  887,  887, 0, "As"),
		SELENIUM	(34,	new JText("Selenium", R3),		new String[]{"Selenium"},	0x9b, 0x9b, 0x9b,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  3990, 1500, nt,  494,  958, 0, "Se"),
		BROMINE		(35,	new JText("Bromine", R2),		new String[]{"Bromine"},	0x50, 0x0a,    0,    0, "liquid",				 "", 0, WT, 0, 0f, 0f, 0f, 0f, S2,  1028, 1000, nt,  266,  332, 0, "Br"),
		KRYPTON		(36,	new JText("Krypton", R2),		new String[]{"Krypton"},	   0, 0x80, 0xff, 0xf0, "gas",					 "", 0, GS, 0, 0f, 0f, 0f, 0f, S3,     3,  100, nt,  116,  120, 0, "Kr"),
		RUBIDIUM	(37,	"Rubidium",						new String[]{"Rubidium"},	0xf0, 0x1e, 0x1e,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  1460, 1500, nt,  312,  961, 0, "Rb"),
		STRONTIUM	(38,	"Strontium",					new String[]{"Strontium"},	0xc8, 0xc8, 0xc8,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  2375, 1500, nt, 1050, 1650, 0, "Sr"),
		YTTRIUM		(39,	"Yttrium",						new String[]{"Yttrium"},	0xdc, 0xfa, 0xdc,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  4240, 1500, nt, 1799, 3203, 0, "Y"),
		ZIRCONIUM	(40,	"Zirconium",					new String[]{"Zirconium"},	0xf0, 0xf0, 0xf0,    0, "metal",				 SY, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  5800, 1500, nt, 2128, 4650, 0, "Zr"),
		NIOBIUM		(41,	"Niobium",						new String[]{"Niobium"},	0xbe, 0xb4, 0xc8,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  8570, 1500, nt, 2750, 5017, 0, "Nb"),
		MOLYBDENUM	(42,	new JText("Molybdenum", R2),	new String[]{"Molybdenum"}, 0xb4, 0xb4, 0xdc,    0, "metal",				 DL, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  9330, 1500, nt, 2896, 4912, 0, "Mo"),
		TECHNETIUM	(43, 	new JText("Technetium", TXT_GREEN), "Technetium",			0xc8, 0xc8, 0xd2,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 11000, 1500, nt, 2430, 4538, 4, "Tc"),
		RUTHENIUM	(44,	new JText("Ruthenium", R3),		new String[]{"Ruthenium"}, 	0xc8, 0xc8, 0xd2,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 10650, 1500, nt, 2607, 4423, 0, "Ru"),
		RHODIUM		(45,	new JText("Rhodium", R4),		new String[]{"Rhodium"},	0xb4, 0xbe, 0xb4,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 10700, 1500, nt, 2237, 3968, 0, "Rh"),
		PALLADIUM	(46,	new JText("Palladium", R4),		new String[]{"Palladium"},	 128,  128,  128,    0, "metal",				  M, 5, WT, 2, 5f, 5f, 6f, 0f, S1, 10380, 1500, nt, 1828, 3236, 0, "Pd"),
		SILVER		(47,	new JText("Silver", R3),		new String[]{"Silver"},		0xdc, 0xdc, 0xff,    0, "metal",				 SY, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  9320, 1500, nt, 1235, 2435, 0, "Ag"),
		CADMIUM		(48,	new JText("Cadmium", R3),		new String[]{"Cadmium"},	 220,  220,  255,    0, "metal",				 SY, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  7996, 1500, nt,  594, 1040, 0, "Cd"),
		INDIUM		(49,	new JText("Indium", R3),		new String[]{"Indium"},		0x40,    0, 0x00,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  7020, 1500, nt,  430, 2345, 0, "In"),
		TIN			(50,	"Tin",							new String[]{"Tin"},		0xdc, 0xdc, 0xdc,    0, "metal",				 DL, 5, WT, 1, 5f, 2f, 6f, 0f, S1,  6990, 1500, nt,  505, 2875, 0, "Sn"),
		//			| meta	|	  name						|   oreDict					|red  green blue  alpha |tags					|its|bt|ft |hl|bh |oh |br |or|state|dnsty|vscty| tmp| mtmp|btmp|ll|tooltip
		ANTIMONY	(51,	new JText("Antimony", R2),		new String[]{"Antimony"},	0xdc, 0xdc, 0xf0,    0, "metal",				 SY, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  6530, 1500, nt,  904, 1908, 0, "Sb"),
		TELLURIUM	(52,	new JText("Tellurium", R3),		new String[]{"Tellurium"},	0xbe, 0xc8, 0xbe,    0, "metal",				 SY, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  5700, 1500, nt,  723, 1261, 0, "Te"),
		IODINE		(53,	new JText("Iodine", R2),		new String[]{"Iodine"},		0x46, 0x46, 0x50,    0, "metal",				 DL, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  4933, 1500, nt,  387,  457, 0, "I"),
		XENON		(54,	new JText("Xenon", R2),			new String[]{"Xenon"},		0x48, 0x00, 0xff, 0xf0, "gas",					 "", 0, GS, 0, 0f, 0f, 0f, 0f, S3,     4,    1, nt,  161,  165, 0, "Xe"),
		CAESIUM		(55,	"Caesium",						new String[]{"Caesium"},	0xe6, 0xe6, 0xe6,    0, "metal",				 DL, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  1843, 1500, nt,  302,  944, 0, "Cs"),
		BARIUM		(56,	"Barium",						new String[]{"Barium"},		0xe6, 0xe6, 0xe6,    0, "metal",				 SY, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  3338, 1500, nt, 1000, 2118, 0, "Ba"),
		LANTHANUM	(57,	"Lanthanum",					new String[]{"Lanthanum"},	0xf0, 0xf0, 0xf0,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  5940, 1500, nt, 1193, 3737, 0, "La"),
		CERIUM		(58,	"Cerium",						new String[]{"Cerium"},		0xdc, 0xdc, 0xdc,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  6550, 1500, nt, 1068, 3716, 0, "Ce"),
		PRASEODYMIUM(59,	"Praseodymium", 				"Praseodymium",				0xe1, 0xe1, 0xe1,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  6500, 1500, nt, 1208, 3403, 0, "Pr"),
		NEODYMIUM	(60,	"Neodymium",					new String[]{"Neodymium"},	0x64, 0x64, 0x64,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  6890, 1500, nt, 1297, 3347, 0, "Nd"),
		PROMETHIUM	(61,	new JText("Promethium", TXT_GREEN), "Promethium",			0xc8, 0xc8, 0xc8,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  7260, 1500, nt, 1315, 3273, 4, "Pm"),
		SAMARIUM	(62,	"Samarium",						new String[]{"Samarium"},	0xff, 0xff, 0xcc,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  7160, 1500, nt, 1345, 2173, 0, "Sm"),
		EUROPIUM	(63,	"Europium",						new String[]{"Europium"},	0xdc, 0xdc, 0x4b,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  5130, 1500, nt, 1099, 1802, 0, "Eu"),
		GADOLINIUM	(64,	"Gadolinium",					new String[]{"Gadolinium"},	0xbe, 0xbe, 0xfa,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  7400, 1500, nt, 1585, 3273, 0, "Gd"),
		TERBIUM		(65,	new JText("Terbium", R2),		new String[]{"Terbium"},	0x96, 0x8c, 0x78,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  7650, 1500, nt, 1629, 3396, 0, "Tb"),
		DYSPROSIUM	(66,	"Dysprosium",					new String[]{"Dysprosium"},	0xd7, 0xd2, 0xc8,    0, "metal",				 SY, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  8370, 1500, nt, 1680, 2840, 0, "Dy"),
		HOLMIUM		(67,	new JText("Holmium", R2),		new String[]{"Holmium"},	0xd7, 0xd2, 0xc8,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  8340, 1500, nt, 1734, 2873, 0, "Ho"),
		ERBIUM		(68,	"Erbium",						new String[]{"Erbium"},		0xc8, 0xc8, 0xc8,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  8860, 1500, nt, 1802, 3141, 0, "Er"),
		THULIUM		(69,	new JText("Thulium", R2),		new String[]{"Thulium"},	0xaf, 0xaf, 0xaf,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  8560, 1500, nt, 1818, 2223, 0, "Tm"),
		YTTERBIUM	(70,	"Ytterbium",					new String[]{"Ytterbium"},	0xe1, 0xe1, 0xe1,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  6210, 1500, nt, 1097, 1469, 0, "Yb"),
		LUTETIUM	(71,	new JText("Lutetium", R2),		new String[]{"Lutetium"},	0xe1, 0xe1, 0xe1,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  9300, 1500, nt, 1925, 3675, 0, "Lu"),
		HAFNIUM		(72,	"Hafnium",						new String[]{"Hafnium"},	0xe1, 0xe1, 0xe1,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 12000, 1500, nt, 2506, 4876, 0, "Hf"),
		TANTALUM	(73,	new JText("Tantalum", R2),		new String[]{"Tantalum"},	0xe1, 0xe1, 0xe1,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 15000, 1500, nt, 3290, 5731, 0, "Ta"),
		TUNGSTEN	(74,	new JText("Tungsten", R2),		new String[]{"Tungsten"},	0x32, 0x32, 0x32,    0, "metal",				  M, 5, WT, 2, 5f, 4f, 6f, 0f, S1, 17600, 1500, nt, 3695, 6203, 0, "W"),
		RHENIUM		(75,	new JText("Rhenium", R4),		new String[]{"Rhenium"},	0x6e, 0x6e, 0x6e,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 18900, 1500, nt, 3459, 5903, 0, "Re"),
		OSMIUM		(76,	new JText("Osmium", R4),		new String[]{"Osmium"},		0x32, 0x32, 0xff,    0, "metal",				  M, 5, WT, 2, 5f, 5f, 6f, 0f, S1, 21020, 1500, nt, 3306, 5285, 0, "Os"),
		IRIDIUM		(77,	new JText("Iridium", R4),		new String[]{"Iridium"},	0xf0, 0xf0, 0xf5,    0, "metal",				 DL, 5, WT, 2, 5f, 4f, 6f, 0f, S1, 19000, 1500, nt, 2719, 4403, 0, "Ir"),
		PLATINUM	(78,	new JText("Platinum", R3),		new String[]{"Platinum"},	0xff, 0xff, 0xc8,    0, "metal",				 SY, 5, WT, 2, 5f, 5f, 6f, 0f, S1, 19770, 1500, nt, 2041, 4098, 0, "Pt"),
		GOLD		(79,	new JText("Gold", R3), 			"Gold",  0xff, 0xff, 0x1e, 0, new String[]{"metal", "no_ingot", "no_block"}, SY, 5, WT, 2, 5f, 3f, 6f, 0f, S1, 17310, 1500, nt, 1337, 3243, 0, "Au"),
		MERCURY	    (80,	new JText("Mercury", R3), new String[]{"Mercury", "Quicksilver"}, 0xff, 0xdc, 0xdc, 0, "liquid",			 SY, 3, WT, 1, 5f, 3f, 6f, 0f, S2, 13534, 1000, nt,  234,  630, 0, "Hg"),
		THALLIUM	(81,	new JText("Thallium", R2),		new String[]{"Thallium"},	0xdc, 0xe1, 0xe6,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 11220, 1500, nt,  577, 1746, 0, "Tl"),
		LEAD		(82,	"Lead",							new String[]{"Lead"},		0x8c, 0x64, 0x8c,    0, "metal",				 DL, 5, WT, 1, 5f, 2f, 6f, 0f, S1, 10660, 1500, nt,  601, 2022, 0, "Pb"),
		BISMUTH		(83,	new JText("Bismuth", R3),		new String[]{"Bismuth"},	0x64, 0xa0, 0xa0,    0, "metal",				  M, 5, WT, 1, 5f, 2f, 6f, 0f, S1, 10050, 1500, nt,  545, 1837, 0, "Bi"),
		POLONIUM	(84,	new JText("Polonium", TXT_GREEN), new String[]{"Polonium"}, 0xd2, 0xe6, 0x0a,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  9297, 1500, nt,  527, 1235, 0, "Po"),
		ASTATINE	(85,	new JText("Astatine", TXT_GREEN), new String[]{"Astatine"}, 0x1e, 0x28, 0x5a,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  7000, 1500, nt,  575,  610, 0, "At"),
		RADON		(86,	new JText("Radon", TXT_GREEN),	new String[]{"Radon"},		 255,    0,  255,  240, "gas",					 NC, 0, GS, 0, 0f, 0f, 0f, 0f, S3,    10,    1, nt,  202,  212, 4, "Rn"),
		FRANCIUM	(87,	new JText("Francium", TXT_GREEN), new String[]{"Francium"}, 0x5a, 0x3c, 0x1e,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  2480, 1500, nt,  281,  890, 4, "Fr"),
		RADIUM		(88,	new JText("Radium", TXT_GREEN),	new String[]{"Radium"},		0x0a, 0xbe, 0x32,    0, "metal",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  5500, 1500, nt,  973, 2010, 4, "Ra"),
		ACTINIUM	(89,	new JText("Actinium", TXT_GREEN), new String[]{"Actinium"},	0xaa, 0xaa, 0xaa,    0, "metal",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 10000, 1500, nt, 1500, 3500, 4, "Ac"),
		THORIUM		(90,	new JText("Thorium", TXT_GREEN), "Thorium",					   0, 0x1e,    0,    0, "metal",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 11700, 1500, nt, 2023, 5061, 4, "Th"),
		PROTACTINIUM(91,	new JText("Protactinium", TXT_GREEN), "Protactinium",		0xe6, 0xd7, 0x14,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 15370, 1500, nt, 1841, 4300, 4, "Pa"),
		URANIUM238	(92,	new JText("Uranium", TXT_GREEN), new String[]{"Uranium", "Uranium238"}, 0x32, 0xf0, 0x32, 0, "metal",		 NC, 5, WT, 2, 5f, 4f, 6f, 0f, S1, 17300, 1500, nt, 1405, 4404, 4, "U"),
		NEPTUNIUM	(93,	new JText("Neptunium", TXT_GREEN), "Neptunium",				0xcd, 0xcd, 0xcd,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 19380, 1500, nt,  912, 4447, 4, "Np"),
		PLUTONIUM244(94,	new JText("Plutonium", TXT_GREEN), new String[]{"Plutonium", "Plutonium244"}, 0xf0, 0x32, 0x32, 0, "alloy",	 NC, 5, WT, 2, 5f, 4f, 6f, 0f, S1, 16630, 1500, nt,  913, 3505, 4, "Pu"),
		AMERICIUM	(95,	new JText("Americium", TXT_GREEN), "Americium",				0xc8, 0xc8, 0xc8,    0, "alloy",				 NC, 5, WT, 2, 5f, 4f, 6f, 0f, S1, 12000, 1500, nt, 1449, 2880, 4, "Am"),
		CURIUM		(96,	new JText("Curium", TXT_GREEN),	new String[]{"Curium"},		0x87, 0x0a, 0xa0,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 13510, 1500, nt, 1613, 3383, 4, "Cm"),
		BERKELIUM	(97,	new JText("Berkelium", TXT_GREEN), "Berkelium",				0x0a, 0xa0, 0x87,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 13250, 1500, nt, 1259, 2900, 4, "Bk"),
		CALIFORNIUM	(98,	new JText("Californium", TXT_GREEN), "Californium",			0x90, 0xa0, 0xa0,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 15100, 1500, nt, 1173, 1743, 4, "Cf"),
		EINSTEINIUM	(99,	new JText("Einsteinium", TXT_GREEN), "Einsteinium",			0x5a, 0xaf, 0xf0,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  8840, 1500, nt, 1133, 1269, 4, "Es"),
		FERMIUM		(100,	new JText("Fermium", TXT_GREEN), new String[]{"Fermium"},	0xff, 0xff, 0xd2,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  9710, 1500, nt, 1800, 3140, 4, "Fm"),
		//			| meta	|	  name						|   oreDict					|red  green blue  alpha |tags					|its|bt|ft |hl|bh |oh |br |or|state|dnsty|vscty| tmp| mtmp|btmp|ll|tooltip
		MENDELVIUM	(101,	new JText("Mendelvium", TXT_GREEN), "Mendelvium",			0x14, 0x00, 0xd7,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 10370, 1500, nt, 1100, 2000, 4, "Md"),
		NOBELIUM	(102,	new JText("Nobelium", TXT_GREEN), new String[]{"Nobelium"}, 0x55, 0x00, 0xd7,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  9940, 1500, nt, 1100, 2000, 4, "No"),
		LAWRENCIUM	(103,	new JText("Lawrencium", TXT_GREEN), "Lawrencium",			0x96, 0x00, 0xd7,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 16100, 1500, nt, 1900, 3600, 4, "Lr"),
		RUTHERFORDIUM(104,	new JText("Rutherfordium", TXT_GREEN), "Rutherfordium",		0xd7, 0x82,    0,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 23200, 1500, nt, 2400, 5800, 4, "Rf"),
		DUBNIUM		(105, 	new JText("Dubnium", TXT_GREEN), new String[]{"Dubnium"},	0xb7, 0xb7, 0xb7,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 29100, 1500, nt, 3300, 5700, 4, "Db"),
		SEABORGIUM	(106,	new JText("Seaborgium", TXT_GREEN), "Seaborgium",			0xb7, 0xb7, 0xb7,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 35000, 1500, nt, 4000, 6500, 4, "Sg"),
		BOHRIUM		(107, 	new JText("Bohrium", TXT_GREEN), new String[]{"Bohrium"},	0xb7, 0xb7, 0xb7,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 37100, 1500, nt, 3500, 6000, 4, "Bh"),
		HASSIUM		(108, 	new JText("Hassium", TXT_GREEN), new String[]{"Hassium"},	0xb7, 0xb7, 0xb7,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 41000, 1500, nt, 3300, 5300, 4, "Hs"),
		MEITNERIUM	(109,	new JText("Meitnerium", TXT_GREEN), "Meitnerium",			0xb7, 0xb7, 0xb7,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 37400, 1500, nt, 2800, 4500, 4, "Mt"),
		DARMSTADTIUM(110,	new JText("Darmstadtium", TXT_GREEN), "Darmstadtium",		0xb7, 0xb7, 0xb7,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 34800, 1500, nt, 2500, 4500, 4, "Ds"),
		ROENTGENIUM	(111,	new JText("Roentgenium", TXT_GREEN), "Roentgenium",			0xb7, 0xb7, 0xb7,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 28700, 1500, nt, 1500, 3500, 4, "Rg"),
		COPERNICUM	(112,	new JText("Copernicum", TXT_GREEN), "Copernicum",			0xb7, 0xb7, 0xb7,    0, "gas",					 "", 0, GS, 0, 0f, 0f, 0f, 0f, S3, 23700, 1500, nt,  150,  357, 4, "Cn"),
		NIHONIUM	(113,	new JText("Nihonium", TXT_GREEN), "Nihonium",				0xb7, 0xb7, 0xb7,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 16000, 1500, nt,  700, 1430, 4, "Nh"),
		FLEROVIUM	(114,	new JText("Flerovium", TXT_GREEN), "Flerovium",				0xb7, 0xb7, 0xb7,    0, "gas",					 "", 0, GS, 0, 0f, 0f, 0f, 0f, S1, 14000, 1500, nt,  100,  210, 4, "Fl"),
		MOSCOVIUM	(115,	new JText("Moscovium", TXT_GREEN), "Moscovium",				0xb7, 0xb7, 0xb7,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 13500, 1500, nt,  670, 1400, 4, "Mc"),
		LIVERMORIUM	(116,	new JText("Livermorium", TXT_GREEN), "Livermorium",			0xb7, 0xb7, 0xb7,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 12900, 1500, nt,  709, 1085, 4, "Lv"),
		TENNESSINE	(117,	new JText("Tennessine", TXT_GREEN), "Tennesine",			0xb7, 0xb7, 0xb7,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  7200, 1500, nt,  723,  883, 4, "Ts"),
		OGANESSON	(118,	new JText("Oganesson", TXT_GREEN), "Oganesson",				0xb7, 0xb7, 0xb7,    0, "alloy",				 NC, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  5000, 1500, nt,  320,  350, 4, "Og"),
		
		//MINECRAFT
		ANDESITE	(200, 	"Andesite", 					new String[]{"Andesite", "Stone"}, 0x82, 0x82, 0x82, 0, new String[]{"stone", "no_block", "no_fluid"}, R, 0, "", 0, 0f, 0f, 0f, 0f, S1, 0, 0, nt, 0, 0, 0),
		BEDROCK		(201, 	"Bedrock",						new String[]{"Bedrock"},	  88,   88,   88,    0, new String[]{"stone", "no_block"}, R, 0, WT, -1, -1f, 0f, 3600000f, 0f, S1, (int)10E8, (int)10E8, nt, (int)2E9, Integer.MAX_VALUE, 0),
		BLAZE		(202,	"Blaze",						new String[]{"Blaze"},		 255,  200,    0,    0, new String[]{"dust", "no_dust", "no_stick"}, PW, 1, WT, 1, 5f, 0f, 6f, 0f, S1, 2000, 1500, nt, 3500, 5000, 15, "CSMa"),
		BONE		(203,	"Bone", 						new String[]{"Bone"},		 250,  250,  250,    0, new String[]{"dust", "no_dust", "no_block", "no_fluid"}, PW, 0, "", 0, 0f, 0f, 0f, 0f, S1, 0, 0, nt, 0, 0, 0),
		BRICK		(204,   "Brick", 						new String[]{"Brick"},		 155,   86,   67,    0, new String[]{"alloy", "no_ingot", "no_block", "no_fluid"}, R, 0, "", 0, 0f, 0f, 0f, 0f, S1, 0, 0, nt, 0, 0, 0, "Al4Si3O12"),
		BRICK_NETHER(205, 	"Nether Brick", 				new String[]{"BrickNether"}, 100,    0,    0,    0, new String[]{"alloy", "no_block", "no_ingot", "no_fluid"}, R, 0, "", 0, 0f, 0f, 0f, 0f, S1, 0, 0, nt, 0, 0, 0),
		CHARCOAL	(206, 	"Charcoal", 					new String[]{"Charcoal"},	 100,   70,   70,    0, new String[]{"gem", "no_gem", "no_ore", "no_liquid"}, LG, 0, WT, 0, 0f, 0f, 0f, 0f, S1,  1950, 1, nt, CARBON.getMeltigTemperature(), CARBON.getBolingTemperature(), 0, "C"),
		CLAY		(207,	"Clay",		 					new String[]{"Clay"},		 200,  200,  220,    0, new String[]{"stone", "no_block", "no_fluid"}, R, 0, "", 0, 0f, 0f, 0f, 0f, S1, 0, 0, nt, 0, 0, 0, "Na2LiAl2Si2(H2O)6"),
		COAL		(208,	"Coal",	  						new String[]{"Coal"},		  70,   70,   70,    0, new String[]{"gem", "no_gem", "no_block", "no_liquid"}, LG, 0, WT, 1, 0f, 2f, 0f, 0f, S1,  1950, 1, nt, CARBON.getMeltigTemperature(), CARBON.getBolingTemperature(), 0, "C"),
		COCOA		(209,	"Cocoa",						new String[]{"Cocoa"},		 190,   95,    0,    0, new String[]{"dust", "no_gas"}, PW, 1, WT, 1, 5f, 0f, 6f, 0f, S1, 1000, 1000, nt, nt + 100, 0, 0),
		CONCRETE	(210,	"Concrete",						new String[]{"Concrete"},	 100,  100,  100,    0, new String[]{"stone", "no_gas"}, R, 0, WT, 0, 0f, 0f, 0f, 0f, S1, 2500, 3000, nt,   nt, 0, 0),
		DIAMOND		(211,	"Diamond", 						new String[]{"Diamond"},	 200,  255,  255,  127, new String[]{"gem", "no_gem", "no_block", "no_liquid"}, D, 0, WT, 0, 0f, 4f, 0f, 0f, S1, 3515, 1, nt, 4500, 4500, 0, "C"),
		DIORITE		(212,	"Diorite", 						new String[]{"Diorite", "Stone"}, 0xaa, 0xaa, 0xaa, 0, new String[]{"stone", "no_block", "no_fluid"}, R, 0, "", 0, 0f, 0f, 0f, 0f, S1, 0, 0, nt, 0, 0, 0),
		DIRT		(213,	"Dirt",							new String[]{"Dirt"},		 132,   95,   66,    0, new String[]{"dust", "no_block", "no_fluid"}, S, 0, "", 0, 0f, 0f, 0f, 0f, S1, 0, 0, nt, 0, 0, 0),
		DIRT_COARSE	(214,	"Coarse Dirt",					new String[]{"Dirt"},		 119,   86,   60,    0, new String[]{"dust", "no_block", "no_fluid"}, S, 0, "", 0, 0f, 0f, 0f, 0f, S1, 0, 0, nt, 0, 0, 0),
		EMERALD		(215,	"Emerald",	   					new String[]{"Emerald"},	  80,  255,   80,  127, new String[]{"gem", "no_gem", "no_block"}, E, 0, WT, 2, 0f, 3f, 0f, 0f, S1, 2700, 1500, nt, 3000, 4500, 0, "Be3Al2Si6O18"),
		END_STONE	(216,	"End Stone",					new String[]{"Endstone"},	 220,  223,  158,    0, new String[]{"stone", "no_block", "no_fluid"}, DL, 0, "", 0, 0f, 0f, 0f, 0f, S1, 0, 0, nt, 0, 0, 0),
		ENDER_EYE	(217,	"Ender Eye", 	   				new String[]{"EnderEye"},	 160,  250,  230,    0, new String[]{"gem", "no_gem", "no_ore"}, RY, 1, WT, 1, 5f, 0f, 6f, 0f, S1,  3500, 2500, nt, 1000, 2500, 0, "BeK4N5Ma6CSMa"),
		ENDER_PEARL (218,	"Ender",						new String[]{"EnderPearl", "Ender"}, 108, 220, 200, 0, new String[]{"gem", "no_gem", "no_ore"}, RY, 1, WT, 1, 5f, 0f, 6f, 0f, S1,  4000, 2500, nt, 1000, 2500, 3, "BeK4N5Ma6"),
		EXPERIENCE	(219,	"Experience", 					new String[]{"Experience"},	 127,  255,    0,    0, new String[]{"liquid"},	 "", 0, XP, 0, 0f, 0f, 0f, 0f, S2, -200, 200, nt, 400, 1000, 15),
		FLINT		(220,	"Flint",		 				new String[]{"Flint"},		   0,   32,   64,    0, new String[]{"gem", "no_gem", "no_ore", "no_fluid"}, FL, 1, "", 1, 5f, 0f, 6f, 0f, S1, 0, 0, nt, 0, 0, 0, "SiO2"),
		GLASS		(221,	"Glass",		   				new String[]{"Glass"},		 220,  250,  250,  220, new String[]{"gem", "no_block", "no_ore"}, G, 0, WT, 0, 0f, 0f, 0f, 0f, S1,  2500, 2000, nt, 1000, 2500, 0, "SiO2"),
		GLOWSTONE	(222,	"Glowstone",					new String[]{"Glowstone"},	 255,  255,    0,    0, new String[]{"gem", "no_gem", "no_ore", "no_block", "no_dust"}, SY, 0, LV, 0, 0f, 0f, 0f, 0f, S1, -500, 100, nt, 1000, 2500, 15),
		GRANITE		(223,	"Granite",						new String[]{"Granite", "Stone"}, 180, 155, 145, 0, new String[]{"stone", "no_block", "no_fluid"}, R, 0, "", 0, 0f, 0f, 0f, 0f, S1, 0, 0, nt, 0, 0, 0),
		GUNPOWDER	(224,	"Gunpowder",					new String[]{"Gunpowder"},	 128,  128,  128,    0, new String[]{"dust", "no_dust", "no_fluid"}, S, 1, "", 1, 5f, 0f, 6f, 0f, S1, 0, 0, nt, 0, 0, 0, "C2S(KNO3)"),
		ICE			(225,	"Ice",							new String[]{"Ice"},		 200,  200,  255,    0, new String[]{"gem", "no_gem", "no_block", "no_ore", "no_fluid"}, SY, 0, "", 0, 0f, 0f, 0f, 0f, S1, 0, 0, 273, 0, 0, 0, "H2O"),
		ICE_PACKED	(226,	"Packed Ice",					new String[]{"IcePacked"},	 141,  180,  250,    0, new String[]{"gem", "no_gem", "no_block", "no_ore", "no_fluid"}, SY, 0, "", 0, 0f, 0f, 0f, 0f, S1, 0, 0, 273, 0, 0, 0, "H2O"),
		INK			(227,	"Ink",							new String[]{"Ink"}, 		   0,    0,    0,    0, new String[]{"liquid", "no_gas"}, "", 0, WT, 0, 0f, 0f, 0f, 0f, S2,  1000, 1000, nt, 273, 373, 0),
		LAPIS		(228,	"Lapis Lazuli",					new String[]{"Lapis"},		  70,   70,  220,    0, new String[]{"gem", "no_gem", "no_block"}, L, 1, WT, 1, 0f, 2f, 0f, 0f, S1, 2640, 2000, nt, 1300, 3500, 0, "(Al6Si6Ca8Na8)12(Al3Si3Na4Cl)2FeS2CaCO3"),
		//			| meta	|	  name						|   oreDict					|red  green blue  alpha |tags					|its|bt|ft |hl|bh |oh |br |or|state|dnsty|vscty| tmp| mtmp|btmp|ll|tooltip
		NETHER_QUARTZ(229,	"Nether Quartz", 				new String[]{"NetherQuartz", "Quartz"}, 230, 210, 210, 0, new String[]{"gem", "no_gem", "no_block", "no_fluid"}, Q, 0, "", 1, 0f, 2f, 0f, 0f, S1, 0, 0, nt, 0, 0, 0),
		NETHER_STAR	(230,	"Nether Star", 					new String[]{"NetherStar"},	 255,  255,  255,    0, new String[]{"gem", "no_gem", "no_ore"}, N, 1, WT, 3, 5f, 5f, 6f, 0f, S1, 3000, 5000, nt, 5000, 10000, 0),
		NETHERRACK	(231,	"Netherrack",					new String[]{"Natherrack"},	 200,    0,    0,    0, new String[]{"stone", "no_block", "no_fluid"}, R, 0, "", 0, 0f, 0f, 0f, 0f, S1, 0, 0, nt, 0, 0, 0),
		OBSIDIAN	(232,	"Obsidian",						new String[]{"Obsidian"},	  80,   50,  100,    0, new String[]{"stone", "no_block"}, DL, 0, LV, 0, 0f, 0f, 0f, 0f, S1, 2450, 10000, nt, 1400, 4000, 0, "MgFeSi2O8"),
		PAPER		(233,	"Paper", 						new String[]{"Paper"},		 250,  250,  250,    0, new String[]{"alloy", "no_plate", "no_ingot", "no_gas"},  P, 1, "", 1, 5f, 0f, 6f, 0f, S1, 900, 1100, nt, nt, 0, 0),
		PRISMARINE	(234,	"Prismarine",					new String[]{"Prismarine"},	0x64, 0xa1, 0x8a,    0, new String[]{"gem", "no_gem", "no_block"}, SH, 0, WT, 1, 0f, 3f, 0f, 0f, S1,  1500, 1500, nt, 712, 3712, 0),
		PRISMARINE_DARK(235, "Dark Prismarine", 			"PrismarineDark",			0x3b, 0x57, 0x4b,    0, new String[]{"gem", "no_gem", "no_block", "no_ore"}, SH, 0, WT, 0, 0f, 0f, 0f, 0f, S1, 1500, 1500, nt, 800, 3800, 0),
		REDSTONE	(236,   "Redstone", 					new String[]{"Redstone"},	 200,    0,    0,    0, new String[]{"gem", "no_gem", "no_block", "no_dust"}, DL, 0, "", 2, 0f, 3f, 0f, 0f, S1, 1200, 1500, nt, 500, 2000, 6, "Si(FeS2)5CrAl2O3Hg3"),
		SAND		(237,	"Sand",		   					new String[]{"Sand"},		 218,  206,  161,    0, new String[]{"dust", "no_block", "no_fluid"}, S, 0, "", 0, 0f, 0f, 0f, 0f, S1, 0, 0, nt, 0, 0, 0),
		SAND_RED	(238,	"Red Sand",						new String[]{"Sand"},		 190,  102,   33,    0, new String[]{"dust", "no_block", "no_fluid"}, S, 0, "", 0, 0f, 0f, 0f, 0f, S1, 0, 0, nt, 0, 0, 0),
		SANDSTONE	(239,	"Sandstone", 					new String[]{"Sandstone"},	 224,  214,  171,    0, new String[]{"stone", "no_block", "no_fluid"}, S, 0, "", 0, 0f, 0f, 0f, 0f, S1, 0, 0, nt, 0, 0, 0),
		SANDSTONE_RED(240,	"Red Sandstone", 				new String[]{"Sandstone"},	 182,   98,   32,    0, new String[]{"stone", "no_block", "no_fluid"}, S, 0, "", 0, 0f, 0f, 0f, 0f, S1, 0, 0, nt, 0, 0, 0),
		SOUL_SAND	(241,	"Soul Sand",	  				new String[]{"Soulsand"}, 	  82,   62,   51,    0, new String[]{"dust", "no_block", "no_fluid"}, S, 0, "", 0, 0f, 0f, 0f, 0f, S1, 0, 0, nt, 0, 0, 0),
		STONE		(242,	"Stone",	  					new String[]{"Stone"}, 		0x7d, 0x7d, 0x7d,    0, new String[]{"stone", "no_block", "no_fluid"}, R, 0, "", 0, 0f, 0f, 0f, 0f, S1, 0, 0, nt, 0, 0, 0),
		SUGAR		(243,	"Sugar",		   				new String[]{"Sugar"}, 		 250,  250,  250,    0, new String[]{"dust", "no_dust", "no_fluid"}, F, 1, "", 1, 5f, 0f, 6f, 0f, S1, 0, 0, nt, 0, 0, 0, "C2(H2O)5O25"),
		WOOD		(244,	"Wood",							new String[]{"Wood"},		 100,   50,    0,    0, "wood",					  W, 0, "", 0, 0f, 0f, 0f, 0f, S1,   650, 0, nt, 0, 0, 0),
		
		//FANTASY MATERIALS
		ADAMANTIUM	(10001,	new JText("Adamantium", R4),	new String[]{"Adamantium", "Adamantite"}, 0xd8, 0xd8, 0xd8, 0, "metal",		  M, 5, WT, 3, 5f, 6f, 6f, 0f, S1, 50000, 1500, nt, 10000, 20000, 0, "Ad"),
		ARDITE		(10002,	"Ardite",						new String[]{"Ardite"},		0xfa, 0x81, 0x00,    0, "metal",				  M, 5, WT, 3, 10f, 4f, 3f, 0, S1,  5000, 1500, nt, 1600, 3300, 0, "Ai"),
		BEDROCKIUM	(10003,	"Bedrockium",					new String[]{"Bedrockium"},	0x32, 0x32, 0x32,    0, "metal",				  R, 5, WT, 1, 5f, 3f, 6f, 0f, S1, (int)10E8, (int)10E8, nt, 9900, 25000, 0),
		BLACK_PLUTONIUM(10004, "Black Plutonium",			"BlackPlutonium",			0x32, 0x32, 0x32,    0, "metal",				 DL, 5, WT, 2, 50f, 3f, 30f, 0f, S1, 40000, 1500, nt, 9000, 15000, 0, "SpPu"),
		CALLISTO_ICE(10,	"Callisto Ice",					"CallistoIce",				0x1e, 0xb1, 0xff,    0, "metal",				 SY, 5, WT, 2, 25f, 5f, 6f, 0f, S1, 1000, 1000, nt,  300,  500, 0, "SpH2O"),
		COSMIC_NEUTRONIUM(10005, new JText("Cosmic Neutronium", R3), "CosmicNeutronium", 0x32, 0x32, 0x32,   0, "metal",				 DL, 5, WT, 3, 50f, 8f, 1200f, 0f, S1, 100000, 10000, nt, 9900, 25000, 0, "SpNt"),
		DARK_IRON	(10006,	"Dark Iron",					new String[]{"DarkIron"},	0x37, 0x28, 0x3c,    0, "metal",				 DL, 5, WT, 2, 5f, 4f, 6f, 0f, S1,  7000, 1500, nt, 2000, 3500, 0),
		DEEP_IRON	(10007,	"Deep Iron",					new String[]{"DeepIron"},	0x96, 0x8c, 0x8c,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  7000, 1500, nt, 2000, 3500, 0),
		DEMONIC		(10008,	new JText("Demonic", TXT_DARK_RED), "Demonic",				0x46, 0x2d, 0x5a,    0, "alloy",				 DL, 5, WT, 0, 5f, 3f, 6f, 0f, S1,  1300,  666, nt, 666, 1300, 13),
		DESH		(10009,	"Desh",							new String[]{"Desh"},		0x28, 0x28, 0x28,    0, "metal",				  R, 5, WT, 2, 5f, 5f, 6f, 0f, S1, 10000, 1500, nt, 2500, 4444, 0, "De"),
		DRACONIUM	(10010,	"Draconium",					new String[]{"draconium"},	0x7a, 0x44, 0xb0,    0, "metal",				 SY, 5, WT, 3, 10f, 8f, 300f, 0f, S1, 20000, 1500, nt, 7200, 12345, 0, "D"),
		DURALUMIN	(10,	"Duralumin",					new String[]{"Duralumin"},	0xeb, 0xd1, 0xa0,    0, "metal",				 SY, 5, WT, 2, 5f, 4f, 6f, 0f, S1,  5000, 1500, nt, 1600, 2800, 0, "Al6CuMnMg"),
		DURANIUM	(10011,	"Duranium",						new String[]{"Duranium"},	0xff, 0xff, 0xff,    0, "alloy",				  M, 5, WT, 0, 5f, 0f, 6f, 0f, S1, 30000, 3000, nt, 25000, 40000, 0, "Du"),
		ELECTROTINE	(10012, "Electrotine", 					new String[]{"Electrotine", "Nikolite", "Teslatite"}, 0x3c, 0xb4, 0xc8, 0, "metal", M, 5, WT, 1, 5f, 2f, 6f, 0f, S1, 2468, 1234, nt, 420, 1337, 0, "Rp"),
		FROZEN_IRON	(10013,	"Frozen Iron",					new String[]{"FrozenIron"},	0xd7, 0xff, 0xff,    0, "metal",				  M, 5, WT, 2, 5f, 4f, 6f, 0f, S1,  9000, 2000, nt,  800, 2100, 0),
		INFINITY	(10014,	new JText("Infinity", TXT_RED),	new String[]{"Infinity"},	0x00, 0x00, 0x00,    0, "metal",				 RP, 5, WT, 3, 50f, 8f, 1200f, 0f, S1, 150000, 15000, nt, 10800, 33333, 0, "If*"),
		INFINITY_CATALYST(10015, new JText("Infinity Catalyst", R4), "InfinityCatalyst",   0,    0,    0,    0, "metal",				 RP, 5, WT, 3, 50f, 8f, 1200f, 0f, S1, 50000, 5000, nt, 10800, 33333, 0, "If"),
		KREKNORITE	(10016,	"Kreknorite",					new String[]{"Kreknorite"},	0x78, 0x00, 0x00,    0, "metal",				  M, 5, WT, 2, 5f, 4f, 6f, 0f, S1,  3643, 1500, nt, 3333, 5555, 0),
		MAGIC		(10017,	new JText("Magic", R2),			new String[]{"Magic"},		0x64, 0x00, 0xc8,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1,  1000, 1000, nt, 1234, 2345, 0, "Ma"),
		METEORIC_IRON(10018, "Meteoric Iron",			 	"Meteoric Iron",			0x64, 0x32, 0x50,    0, "metal",				  M, 5, WT, 2, 5f, 4f, 6f, 0f, S1,  7500, 1500, nt, 1750, 3100, 0, "SpFe"),
		METEORITE	(10019,	"Meteorite",					new String[]{"Meteorite"},	0x50, 0x23, 0x3c,    0, "metal",				  M, 5, WT, 2, 5f, 4f, 6f, 0f, S1,  7500, 1500, nt, 1800, 3200, 0),
		NAQUADAH	(10020,	new JText("Naquadah", R2),		new String[]{"Naquadah"},	0x32, 0x32, 0x32,    0, "metal",				  M, 5, WT, 2, 5f, 5f, 6f, 0f, S1, 10000, 1500, nt, 5000, 7500, 0, "Nq"),
		NAQUADAH_ENRICHED(10021, new JText("Enriched Naquadah", R2), "NaquadahEnriched", 0x32, 0x32, 0x32,   0, "metal",				  M, 5, WT, 2, 5f, 5f, 6f, 0f, S1, 10000, 1500, nt, 5350, 8000, 0, "Nq+"),
		NAQUADRIA	(10022,	new JText("Naquadria", R3),		new String[]{"Naquadria"},	0x1e, 0x1e, 0x1e,    0, "metal",				 SY, 5, WT, 2, 5f, 5f, 6f, 0f, S1, 10000, 1500, nt, 9000, 15000, 0, "Nq*"),
		NEUTRONIUM	(10023,	new JText("Neutronium", R4),	new String[]{"Neutronium"},	0xfa, 0xfa, 0xfa,    0, "metal",				  M, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 50000, 1500, nt, 9000, 15000, 0, "Nt"),
		QUANTIUM	(10024,	"Quantium",						new String[]{"Quantium"},	0x00, 0xd1, 0x0b,    0, "metal",				 SY, 5, WT, 2, 5f, 5f, 6f, 0f, S1, 10101, 1010, nt, 1010, 10101, 0, "Qt"),
		QUANTUM		(10025,	"Quantum",						new String[]{"Quantum"},	0x1e, 0xbe, 0x1e,    0, "metal",				 SY, 5, WT, 1, 5f, 3f, 6f, 0f, S1,   100, 500, nt, 10500, 25000, 0, "(Co7Cr7Mn4Ti2)3(\u2b1f\u2bc2\u2b22\u2b23\u2bc3\u2bc4)(SiC)GaAmPdBiGe"),
		SHADOWIRON	(10026,	"Shadowiron",					new String[]{"ShadowIron"},	0x78, 0x78, 0x78,    0, "metal",				  M, 5, WT, 2, 5f, 4f, 6f, 0f, S1,  7000, 1500, nt, 2000, 3500, 0, "Fe(FeMa)3"),
		SHADOWMETAL	(10,	"Shadowmetal", 					new String[]{"Shadow"},		0x10, 0x03, 0x42,    0, "metal",				  M, 5, WT, 2, 5f, 5f, 3f, 0f, S1,  5000, 1500, nt, 1800, 2500, 0),
		SUNNARIUM	(10027,	"Sunnarium",					new String[]{"Sunnarium"},	0xff, 0xff, 0x00,    0, "alloy",				 SY, 5, WT, 0, 5f, 0f, 6f, 0f, S1, 10000, 1500, nt, 5000, 10000, 0, "Su"),
		TRINIUM		(10028,	new JText("Trinium", R4),		new String[]{"Trinium"},	0xc8, 0xc8, 0xd2,    0, "metal",				  M, 5, WT, 3, 5f, 8f, 6f, 0f, S1, 21000, 1500, nt, 7200, 15000, 0, "Ke"),
		TRITANIUM	(10029,	new JText("Tritanium", R3),		new String[]{"Tritanium"},	0xff, 0xff, 0xff,    0, "alloy",				  M, 5, WT, 0, 5f, 0f, 6f, 0f, S1, 25000, 1500, nt, 9900, 19876, 0, "Tn"),
		YELLORIUM	(10030, "Yellorium", 					new String[]{"Yellorium", "Yellorite"},	0xdc, 0xff, 0x00, 0, "metal",		 SY, 5, WT, 1, 5f, 3f, 6f, 0f, S1, 17500, 1500, nt, 1400, 4400, 0),
		
		//CUSTOM
		//RANDOM	(32751, "Random Material", 				new String[]{"Random"}, 	rnd(), rnd(), rnd(), rnd(), "", 		),
		CUSTOM1 	(32752,	"Custom 1",  					new String[]{"Custom1"}, 	0xff, 0xff, 0xff,    0, true, "",				 "", 0, "", 0, 0f, 0f, 0f, 0f, S1, 	   0,    0, nt,    0,    0, 0, "Tooltip of Custom Material 1"),
		CUSTOM2 	(32753,	"Custom 2",		  				new String[]{"Custom2"}, 	0xff, 0xff, 0xff,    0, true, "",				 "", 0, "", 0, 0f, 0f, 0f, 0f, S1, 	   0,    0, nt,    0,    0, 0, "Tooltip of Custom Material 2"),
		CUSTOM3 	(32754,	"Custom 3",  					new String[]{"Custom3"}, 	0xff, 0xff, 0xff,    0, true, "",				 "", 0, "", 0, 0f, 0f, 0f, 0f, S1, 	   0,    0, nt,    0,    0, 0, "Tooltip of Custom Material 3"),
		CUSTOM4 	(32755,	"Custom 4",  					new String[]{"Custom4"}, 	0xff, 0xff, 0xff,    0, true, "",				 "", 0, "", 0, 0f, 0f, 0f, 0f, S1, 	   0,    0, nt,    0,    0, 0, "Tooltip of Custom Material 4"),
		CUSTOM5 	(32756,	"Custom 5",  					new String[]{"Custom5"}, 	0xff, 0xff, 0xff,    0, true, "",				 "", 0, "", 0, 0f, 0f, 0f, 0f, S1, 	   0,    0, nt,    0,    0, 0, "Tooltip of Custom Material 5"),
		CUSTOM6 	(32757,	"Custom 6",		  				new String[]{"Custom6"}, 	0xff, 0xff, 0xff,    0, true, "",				 "", 0, "", 0, 0f, 0f, 0f, 0f, S1, 	   0,    0, nt,    0,    0, 0, "Tooltip of Custom Material 6"),
		CUSTOM7 	(32758,	"Custom 7",  					new String[]{"Custom7"}, 	0xff, 0xff, 0xff,    0, true, "",				 "", 0, "", 0, 0f, 0f, 0f, 0f, S1, 	   0,    0, nt,    0,    0, 0, "Tooltip of Custom Material 7"),
		CUSTOM8 	(32759,	"Custom 8",  					new String[]{"Custom8"}, 	0xff, 0xff, 0xff,    0, true, "",				 "", 0, "", 0, 0f, 0f, 0f, 0f, S1, 	   0,    0, nt,    0,    0, 0, "Tooltip of Custom Material 8"),
		CUSTOM9 	(32760,	"Custom 9",  					new String[]{"Custom9"}, 	0xff, 0xff, 0xff,    0, true, "",				 "", 0, "", 0, 0f, 0f, 0f, 0f, S1, 	   0,    0, nt,    0,    0, 0, "Tooltip of Custom Material 9"),
		CUSTOM10	(32761,	"Custom 10", 					new String[]{"Custom10"}, 	0xff, 0xff, 0xff,    0, true, "",				 "", 0, "", 0, 0f, 0f, 0f, 0f, S1, 	   0,    0, nt,    0,    0, 0, "Tooltip of Custom Material 10"),
		CUSTOM11	(32762,	"Custom 11", 					new String[]{"Custom11"}, 	0xff, 0xff, 0xff,    0, true, "",				 "", 0, "", 0, 0f, 0f, 0f, 0f, S1, 	   0,    0, nt,    0,    0, 0, "Tooltip of Custom Material 11"),
		CUSTOM12	(32763,	"Custom 12", 					new String[]{"Custom12"}, 	0xff, 0xff, 0xff,    0, true, "",				 "", 0, "", 0, 0f, 0f, 0f, 0f, S1, 	   0,    0, nt,    0,    0, 0, "Tooltip of Custom Material 12"),
		CUSTOM13	(32764,	"Custom 13", 					new String[]{"Custom13"}, 	0xff, 0xff, 0xff,    0, true, "",				 "", 0, "", 0, 0f, 0f, 0f, 0f, S1, 	   0,    0, nt,    0,    0, 0, "Tooltip of Custom Material 13"),
		CUSTOM14	(32765,	"Custom 14", 					new String[]{"Custom14"}, 	0xff, 0xff, 0xff,    0, true, "",				 "", 0, "", 0, 0f, 0f, 0f, 0f, S1, 	   0,    0, nt,    0,    0, 0, "Tooltip of Custom Material 14"),
		CUSTOM15	(32766,	"Custom 15", 					new String[]{"Custom15"}, 	0xff, 0xff, 0xff,    0, true, "",				 "", 0, "", 0, 0f, 0f, 0f, 0f, S1, 	   0,    0, nt,    0,    0, 0, "Tooltip of Custom Material 15"),
		CUSTOM16	(32767,	"Custom 16",	 				new String[]{"Custom16"}, 	0xff, 0xff, 0xff,    0, true, "",				 "", 0, "", 0, 0f, 0f, 0f, 0f, S1, 	   0,    0, nt,    0,    0, 0, "Tooltip of Custom Material 16")
		;
		
		private int meta; //meta-data of the item; block is meta % 16
		private JText name; //unlocalized name; block is "block" + floor(meta/16)
		private String[] oreDict; //oreDictionary name(s)
		private int red; //red part of color
		private int green; //green part of color
		private int blue; //blue part of color
		private int alpha; //alpha part of color
		private int harvestLevel; //0=gold/wood, 1=stone, 2=iron, 3=diamond 
		private float blockHardness; //higher = takes longer to mine
		private float oreHardness; //higher = takes longer to mine
		private int lightLevel; //0-15
		private float blockResistance; //blast resistance
		private float oreResistance; //blast resistance
		private JText[] tooltip; //chemical formula of the material or general information
		private String state; //solid, liquid, gaseous
		private String textureSet;
		private int blockTexture;
		private String fluidTexture;
		private String[] tags; //stuff like metal or no_plate
		private boolean disabled; //disables complete material
		private int density; //in kg/m�;g/l (1kg/m�=1000g/cm�), negative means lighter than air
		private int temperature; //in Kelvin
		private int meltingTemperature; //in Kelvin
		private int boilingTemperature; //in Kelvin
		@Nonnegative
		private int viscosity; //can not be negative, higher = fluid moves more slowly
		
		private Materials(int meta, JText name, String[] oreDict, int red, int green, int blue, int alpha, boolean disabled, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText[] tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = tooltip;
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String[] oreDict, int red, int green, int blue, int alpha, boolean disabled, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText[] tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = tooltip;
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String[] oreDict, int red, int green, int blue, int alpha, boolean disabled, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String[] oreDict, int red, int green, int blue, int alpha, boolean disabled, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String[] oreDict, int red, int green, int blue, int alpha, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText[] tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = tooltip;
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String[] oreDict, int red, int green, int blue, int alpha, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText[] tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = tooltip;
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String[] oreDict, int red, int green, int blue, int alpha, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String[] oreDict, int red, int green, int blue, int alpha, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String oreDict, int red, int green, int blue, int alpha, boolean disabled, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText[] tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = tooltip;
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String oreDict, int red, int green, int blue, int alpha, boolean disabled, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText[] tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = tooltip;
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String oreDict, int red, int green, int blue, int alpha, boolean disabled, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String oreDict, int red, int green, int blue, int alpha, boolean disabled, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String oreDict, int red, int green, int blue, int alpha, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText[] tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = tooltip;
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String oreDict, int red, int green, int blue, int alpha, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText[] tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = tooltip;
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String oreDict, int red, int green, int blue, int alpha, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String oreDict, int red, int green, int blue, int alpha, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String[] oreDict, int red, int green, int blue, int alpha, boolean disabled, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText[] tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = tooltip;
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String[] oreDict, int red, int green, int blue, int alpha, boolean disabled, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText[] tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = tooltip;
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String[] oreDict, int red, int green, int blue, int alpha, boolean disabled, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String[] oreDict, int red, int green, int blue, int alpha, boolean disabled, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String[] oreDict, int red, int green, int blue, int alpha, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText[] tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = tooltip;
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String[] oreDict, int red, int green, int blue, int alpha, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText[] tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = tooltip;
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String[] oreDict, int red, int green, int blue, int alpha, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String[] oreDict, int red, int green, int blue, int alpha, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String oreDict, int red, int green, int blue, int alpha, boolean disabled, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText[] tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = tooltip;
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String oreDict, int red, int green, int blue, int alpha, boolean disabled, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText[] tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = tooltip;
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String oreDict, int red, int green, int blue, int alpha, boolean disabled, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String oreDict, int red, int green, int blue, int alpha, boolean disabled, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String oreDict, int red, int green, int blue, int alpha, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText[] tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = tooltip;
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String oreDict, int red, int green, int blue, int alpha, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText[] tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = tooltip;
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String oreDict, int red, int green, int blue, int alpha, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String oreDict, int red, int green, int blue, int alpha, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String[] oreDict, int red, int green, int blue, int alpha, boolean disabled, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{tooltip};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String[] oreDict, int red, int green, int blue, int alpha, boolean disabled, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{tooltip};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String[] oreDict, int red, int green, int blue, int alpha, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{tooltip};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String[] oreDict, int red, int green, int blue, int alpha, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{tooltip};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String oreDict, int red, int green, int blue, int alpha, boolean disabled, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{tooltip};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String oreDict, int red, int green, int blue, int alpha, boolean disabled, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{tooltip};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String oreDict, int red, int green, int blue, int alpha, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{tooltip};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String oreDict, int red, int green, int blue, int alpha, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{tooltip};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String[] oreDict, int red, int green, int blue, int alpha, boolean disabled, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{tooltip};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String[] oreDict, int red, int green, int blue, int alpha, boolean disabled, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{tooltip};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String[] oreDict, int red, int green, int blue, int alpha, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{tooltip};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String[] oreDict, int red, int green, int blue, int alpha, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{tooltip};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String oreDict, int red, int green, int blue, int alpha, boolean disabled, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{tooltip};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String oreDict, int red, int green, int blue, int alpha, boolean disabled, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{tooltip};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String oreDict, int red, int green, int blue, int alpha, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{tooltip};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String oreDict, int red, int green, int blue, int alpha, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, JText tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{tooltip};
			this.viscosity = viscosity;
			
		}
		private Materials(int meta, JText name, String[] oreDict, int red, int green, int blue, int alpha, boolean disabled, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, String tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{new JText(tooltip)};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String[] oreDict, int red, int green, int blue, int alpha, boolean disabled, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, String tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{new JText(tooltip)};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String[] oreDict, int red, int green, int blue, int alpha, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, String tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{new JText(tooltip)};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String[] oreDict, int red, int green, int blue, int alpha, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, String tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{new JText(tooltip)};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String oreDict, int red, int green, int blue, int alpha, boolean disabled, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, String tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{new JText(tooltip)};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String oreDict, int red, int green, int blue, int alpha, boolean disabled, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, String tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{new JText(tooltip)};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String oreDict, int red, int green, int blue, int alpha, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, String tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{new JText(tooltip)};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String oreDict, int red, int green, int blue, int alpha, String[] tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, String tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = tags;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{new JText(tooltip)};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String[] oreDict, int red, int green, int blue, int alpha, boolean disabled, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, String tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{new JText(tooltip)};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String[] oreDict, int red, int green, int blue, int alpha, boolean disabled, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, String tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{new JText(tooltip)};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String[] oreDict, int red, int green, int blue, int alpha, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, String tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{new JText(tooltip)};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String[] oreDict, int red, int green, int blue, int alpha, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, String tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = oreDict;
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{new JText(tooltip)};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String oreDict, int red, int green, int blue, int alpha, boolean disabled, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, String tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{new JText(tooltip)};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String oreDict, int red, int green, int blue, int alpha, boolean disabled, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, String tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = disabled;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{new JText(tooltip)};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, JText name, String oreDict, int red, int green, int blue, int alpha, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, String tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = name;
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{new JText(tooltip)};
			this.viscosity = viscosity;
			
		}
		
		private Materials(int meta, String name, String oreDict, int red, int green, int blue, int alpha, String tags, String textureSet, int blockTexture, String fluidTexture, int harvestLevel, float blockHardness, float oreHardness, float blockResistance, float oreResistance, String state, int density, int viscosity, int temperature, int meltingTemperature, int boilingTemperature, int lightLevel, String tooltip) {
			
			this.alpha = alpha;
			this.blockHardness = blockHardness;
			this.blockResistance = blockResistance;
			this.blockTexture = blockTexture;
			this.blue = blue;
			this.boilingTemperature = boilingTemperature;
			this.density = density;
			this.disabled = false;
			this.fluidTexture = fluidTexture;
			this.green = green;
			this.harvestLevel = harvestLevel;
			this.lightLevel = lightLevel;
			this.meltingTemperature = meltingTemperature;
			this.meta = meta;
			this.name = new JText(name);
			this.oreDict = new String[]{oreDict};
			this.oreHardness = oreHardness;
			this.oreResistance = oreResistance;
			this.red = red;
			this.state = state;
			this.tags = new String[]{tags};;
			this.temperature = temperature;
			this.textureSet = textureSet;
			this.tooltip = new JText[]{new JText(tooltip)};
			this.viscosity = viscosity;
			
		}
		
		public int getAlpha() {
			return this.alpha;
		}
		
		public float getBlockHardness() {
			return this.blockHardness;
		}
		
		public float getBlockResistance() {
			return this.blockResistance;
		}
		
		public int getBlockTexture() {
			return this.blockTexture;
		}
		
		public int getBlue() {
			return this.blue;
		}
		
		public int getBolingTemperature() {
			return this.boilingTemperature;
		}
		
		public int getDensity() {
			return this.density;
		}
		
		public boolean isDisabled() {
			return this.disabled;
		}
		
		public String getFluidTexture() {
			return this.fluidTexture;
		}
		
		public int getGreen() {
			return this.green;
		}
		
		public int getHarvestLevel() {
			return this.harvestLevel;
		}
		
		public int getLightLevel() {
			return this.lightLevel;
		}
		
		public int getMeltigTemperature() {
			return this.meltingTemperature;
		}
		
		public int getMeta() {
			return meta;
		}
		
		public JText getName() {
			return this.name;
		}
		public String[] getOreDict() {
			return this.oreDict;
		}
		
		public float getOreHardness() {
			return this.oreHardness;
		}
		
		public float getOreResistance() {
			return this.oreResistance;
		}
		
		private int rnd() {
			return (int) (Math.random() * 255);
		}
		
		/*private String rItemSet() {
			return
		}*/
		
		public int getRed() {
			return this.red;
		}
		
		public String getState() {
			return this.state;
		}
		
		public String[] getTags() {
			return this.tags;
		}
		
		public int getTemperature() {
			return this.temperature;
		}
		
		public JText[] getTooltip() {
			return this.tooltip;
		}
		
		public String getTextureSet() {
			return this.textureSet;
		}
		
		public int getViscosity() {
			return this.viscosity;
		}
		
	}
	
	//standard values
	public static int nt = Reference.normalTemp;
	
	//colors
	public static final String TXT_AQUA = "aqua";
	public static final String TXT_BLACK = "black";
	public static final String TXT_BLUE = "blue";
	public static final String TXT_DARK_AQUA = "dark_aqua";
	public static final String TXT_DARK_BLUE = "dark_blue";
	public static final String TXT_DARK_GRAY = "dark_gray";
	public static final String TXT_DARK_GREEN = "dark_green";
	public static final String TXT_DARK_PURPLE = "dark_purple";
	public static final String TXT_DARK_RED = "dark_red";
	public static final String TXT_GOLD = "gold";
	public static final String TXT_GRAY = "gray";
	public static final String TXT_GREEN = "green";
	public static final String TXT_LIGHT_PURPLE = "light_purple";
	public static final String TXT_RED = "red";
	public static final String TXT_WHITE = "white";
	public static final String TXT_YELLOW = "yellow";
	
	//states
	public static final String S1 = "solid";
	public static final String S2 = "liquid";
	public static final String S3 = "gaseous";
	
	//rarities
	public static final String AN = "animated:";
	public static final String R1 = "common";
	public static final String R2 = "uncommon";
	public static final String R3 = "rare";
	public static final String R4 = "epic";
	
	//iconSets
	public static final String A  = "alchemical";
	public static final String D  = "diamond";
	public static final String DL = "dull";
	public static final String E  = "emerald";
	public static final String EN = "enriched";
	public static final String FY = "fiery";
	public static final String F  = "fine";
	public static final String FL = "flint";
	public static final String GH = "gem_horizontal";
	public static final String GV = "gem_vertical";
	public static final String G  = "glass";
	public static final String GS = "gas";
	public static final String L  = "lapis";
	public static final String LV = "lava";
	public static final String LF = "leaf";
	public static final String LG = "lignite";
	public static final String MF = "matter_fiery";
	public static final String MG = "magnetic";
	public static final String MT = "matter";
	public static final String M  = "metallic";
	public static final String N  = "netherstar";
	public static final String NC = "nuclear";
	public static final String NQ = "netherquartz";
	public static final String O  = "opal";
	public static final String P  = "paper";
	public static final String PL = "pulsating";
	public static final String PW = "powder";
	public static final String Q  = "quartz";
	public static final String R  = "rough";
	public static final String RB = "rainbow";
	public static final String RF = "refined";
	public static final String RP = "rainbow_pulsating";
	public static final String RY = "ruby";
	public static final String S  = "sand";
	public static final String SH = "shards";
	public static final String SY = "shiny";
	public static final String W  = "wood";
	public static final String WT = "water";
	public static final String XP = "xp";
}
