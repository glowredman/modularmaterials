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
    
    MODEL_ORE(
            "{",
            "  \"parent\": \"block/block\",",
            "  \"textures\": {",
            "    \"0\": \"%2$s\",",
            "    \"1\": \"" + MM_Reference.MODID + ":blocks/%1$s/ore\",",
            "    \"particle\": \"%2$s\"",
            "  },",
            "  \"elements\": [",
            "    {",
            "      \"from\": [0, 0, 0],",
            "      \"to\": [16, 16, 16],",
            "      \"faces\": {",
            "        \"north\": {\"texture\": \"#0\", \"tintindex\": -1, \"cullface\": \"north\"},",
            "        \"east\": {\"texture\": \"#0\", \"tintindex\": -1, \"cullface\": \"east\"},",
            "        \"south\": {\"texture\": \"#0\", \"tintindex\": -1, \"cullface\": \"south\"},",
            "        \"west\": {\"texture\": \"#0\", \"tintindex\": -1, \"cullface\": \"west\"},",
            "        \"up\": {\"texture\": \"#0\", \"tintindex\": -1, \"cullface\": \"up\"},",
            "        \"down\": {\"texture\": \"#0\", \"tintindex\": -1, \"cullface\": \"down\"}",
            "      }",
            "    },",
            "    {",
            "      \"from\": [0, 0, 0],",
            "      \"to\": [16, 16, 16],",
            "      \"faces\": {",
            "        \"north\": {\"texture\": \"#1\", \"tintindex\": 1, \"cullface\": \"north\"},",
            "        \"east\": {\"texture\": \"#1\", \"tintindex\": 1, \"cullface\": \"east\"},",
            "        \"south\": {\"texture\": \"#1\", \"tintindex\": 1, \"cullface\": \"south\"},",
            "        \"west\": {\"texture\": \"#1\", \"tintindex\": 1, \"cullface\": \"west\"},",
            "        \"up\": {\"texture\": \"#1\", \"tintindex\": 1, \"cullface\": \"up\"},",
            "        \"down\": {\"texture\": \"#1\", \"tintindex\": 1, \"cullface\": \"down\"}",
            "      }",
            "    }",
            "  ]",
            "}"),
    
    MODEL_BUCKET(
    		"{",
    		"  \"parent\": \"forge:item/bucket\",",
    		"  \"loader\": \"forge:bucket\",",
    		"  \"fluid\": \"%1$s\",",
    		"  \"flipGas\": true",
    		"}"),
    
    MODEL_BUCKET_DRIP(
    		"{",
    		"  \"parent\": \"forge:item/bucket_drip\",",
    		"  \"loader\": \"forge:bucket\",",
    		"  \"fluid\": \"%1$s\",",
    		"  \"flipGas\": true",
    		"}"),
    
    BLOCKSTATE_BLOCK(
            "{",
            "  \"variants\": {",
            "    \"\": {",
            "      \"model\": \"" + MM_Reference.MODID + ":block/%1$s/%2$s\"",
            "    }",
            "  }",
            "}"),
    
    BLOCKSTATE_ORE(
            "{",
            "  \"variants\": {",
            "    \"\": {",
            "      \"model\": \"" + MM_Reference.MODID + ":block/%1$s/ore/%2$s\"",
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
     * <p>MODEL_ITEM, MODEL_BLOCK, BLOCKSTATE_BLOCK: arg0 = texture, arg1 = type</p>
     * <P>MODEL_ORE: arg0 = texture, arg1 = baseTexture</p>
     * <p>MODEL_BUCKET, MODEL_BUCKET_DRIP: arg0 = fluid</P>
     * <p>BLOCKSTATE_ORE: arg0 = texture, arg1 = variant</p>
     * <p>LOOTTABLE_BLOCKS: arg0 = dropped item</p>
     * @param args see above
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
