package glowredman.modularmaterials.data.object.sub;

import static glowredman.modularmaterials.util.XSTR.XSTR_INSTANCE;

import java.util.ArrayList;
import java.util.List;

import glowredman.modularmaterials.client.FormattingHandler;

public class BlockProperties {
	
	public float enchantPowerBonus = 0.0f;
	public int fireSpreadSpeed = 0;
	public int flammability = 0;
	public float friction = 0.6f;
	public float hardness = 5.0f;
	public float jumpFactor = 1.0f;
	public int lightLevel = 0;
	public String mapColor = "AUTO";
	public String material = "METAL";
	public boolean requiresToolForDrops = true;
	public float resistance = 6.0f;
	public String sound = "METAL";
	public float speedFactor = 1.0f;
	public boolean sticky = false;
	public List<String> typeEnabledTags = new ArrayList<>();
	public List<String> tags = new ArrayList<>();
	
	@Override
	public String toString() {
		return String.format("{enchantPowerBonus: %f, fireSpreadSpeed: %d, flammability: %d, friction: %f, hardness: %f, jumpFactor: %f, lightLevel: %d, mapColor: %s, material: %s, resistance: %f, sound: %s, speedFactor: %f, sticky: %b, typeEnabledTags: %s, tags: %s}",
				enchantPowerBonus, fireSpreadSpeed, flammability, friction, hardness, jumpFactor, lightLevel, mapColor, material, resistance, sound, speedFactor, sticky, FormattingHandler.listToString(typeEnabledTags), FormattingHandler.listToString(tags));
	}
		
	public static BlockProperties random() {
		BlockProperties b = new BlockProperties();
		b.enchantPowerBonus = XSTR_INSTANCE.nextFloat() * 8.0f;
		b.fireSpreadSpeed = XSTR_INSTANCE.nextInt(301);
		b.flammability = XSTR_INSTANCE.nextInt(301);
		b.friction = XSTR_INSTANCE.nextFloat();
		b.hardness = XSTR_INSTANCE.nextFloat() * 10.0f;
		b.jumpFactor = XSTR_INSTANCE.nextFloat() * 3.0f;
		b.lightLevel = XSTR_INSTANCE.nextInt(16);
		b.mapColor = "AUTO";
		b.material = "METAL";
		b.resistance = XSTR_INSTANCE.nextFloat() * 10.0f;
		b.sound = "METAL";
		b.speedFactor = XSTR_INSTANCE.nextFloat() * 3.0f;
		b.sticky = XSTR_INSTANCE.nextBoolean();
		return b;
	}

}
