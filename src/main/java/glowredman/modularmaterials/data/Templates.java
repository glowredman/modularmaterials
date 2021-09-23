package glowredman.modularmaterials.data;

import glowredman.modularmaterials.MM_Reference;

public enum Templates {
    MODEL_ITEM(
            "{",
            "  \"parent\": \"item/generated\",",
            "  \"textures\": {",
            "    \"layer0\": \"" + MM_Reference.MODID + ":items/%1$s/%2$s\",",
            "    \"layer1\": \"" + MM_Reference.MODID + ":items/%1$s/%2$s_overlay\"", "  }",
            "}"),
    
    MODEL_BLOCK(
            "{",
            "  \"parent\": \"block/block\",",
            "  \"textures\": {",
            "    \"0\": \"" + MM_Reference.MODID + ":blocks/%1$s/%2$s\",",
            "    \"1\": \"" + MM_Reference.MODID + ":blocks/%1$s/%2$s_overlay\",",
            "    \"particle\": \"" + MM_Reference.MODID + ":blocks/%1$s/%2$s\"",
            "  },",
            "  \"elements\": [",
            "    {",
            "      \"from\": [0, 0, 0],",
            "      \"to\": [16, 16, 16],",
            "      \"faces\": {",
            "        \"north\": {\"texture\": \"#0\", \"tintindex\": 0, \"cullface\": \"north\"},",
            "        \"east\": {\"texture\": \"#0\", \"tintindex\": 0, \"cullface\": \"east\"},",
            "        \"south\": {\"texture\": \"#0\", \"tintindex\": 0, \"cullface\": \"south\"},",
            "        \"west\": {\"texture\": \"#0\", \"tintindex\": 0, \"cullface\": \"west\"},",
            "        \"up\": {\"texture\": \"#0\", \"tintindex\": 0, \"cullface\": \"up\"},",
            "        \"down\": {\"texture\": \"#0\", \"tintindex\": 0, \"cullface\": \"down\"}",
            "      }",
            "    },",
            "    {",
            "      \"from\": [0, 0, 0],",
            "      \"to\": [16, 16, 16],",
            "      \"faces\": {",
            "        \"north\": {\"texture\": \"#1\", \"tintindex\": -1, \"cullface\": \"north\"},",
            "        \"east\": {\"texture\": \"#1\", \"tintindex\": -1, \"cullface\": \"east\"},",
            "        \"south\": {\"texture\": \"#1\", \"tintindex\": -1, \"cullface\": \"south\"},",
            "        \"west\": {\"texture\": \"#1\", \"tintindex\": -1, \"cullface\": \"west\"},",
            "        \"up\": {\"texture\": \"#1\", \"tintindex\": -1, \"cullface\": \"up\"},",
            "        \"down\": {\"texture\": \"#1\", \"tintindex\": -1, \"cullface\": \"down\"}",
            "      }",
            "    }",
            "  ]",
            "}"),
    
    BLOCKSTATE_BLOCK(
            "{",
            "  \"variants\": {",
            "    \"\": {",
            "      \"model\": \"" + MM_Reference.MODID + ":block/%1$s/%2$s\"",
            "    }",
            "  }",
            "}"),
    
    LOOTTABLE_BLOCKS(
    		"{",
    		"  \"type\": \"minecraft:block\",",
    		"  \"pools\": [",
    		"    {",
    		"      \"rolls\": 1.0,",
    		"      \"bonus_rolls\": 0.0,",
    		"      \"entries\": [",
    		"        {",
    		"          \"type\": \"minecraft:item\",",
    		"          \"name\": \"%1$s\"",
    		"        }",
    		"      ],",
    		"      \"conditions\": [",
    		"        {",
    		"          \"condition\": \"minecraft:survives_explosion\"",
    		"        }",
    		"      ]",
    		"    }",
    		"  ]",
    		"}")
    ;

    private String[] template;

    private Templates(String... template) {
        this.template = template;
    }
    
    /**
     * <p>Models/Blockstates: %1$s = texture, %2$s = type, %3$s = oreBase/oreVariant</p>
     * <p>Loot Tables: %1$s = dropped item</p>
     */
    public String format(Object... args) {
        return String.format(this.toString(), args);
    }

    @Override
    public String toString() {
        String out = template[0];
        for (int i = 1; i < template.length; i++) {
            out += '\n' + template[i];
        }
        return out;
    }

}
