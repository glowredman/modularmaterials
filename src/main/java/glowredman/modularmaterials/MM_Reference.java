package glowredman.modularmaterials;

import java.util.Map;

import glowredman.modularmaterials.data.JSONHandler;
import glowredman.modularmaterials.data.object.MM_Material;
import glowredman.modularmaterials.data.object.MM_Type;

public class MM_Reference {
	
	//Mod
	public static final String MODID = "modularmaterials";
	
	//Formatting
	public static String triggerShiftIsPressedFormatting = "\u00a7p";
	public static String triggerShiftIsNotPressedFormatting = "\u00a7P";
	public static String triggerCtrlIsPressedFormatting = "\u00a7q";
	public static String triggerCtrlIsNotPressedFormatting = "\u00a7Q";
	public static String triggerAltIsPressedFormatting = "\u00a7t";
	public static String triggerAltIsNotPressedFormatting = "\u00a7T";
	public static String triggerAnimatedFormattingChar = "\u00a7s";
	public static boolean enableFormattingDebugger = false;
	
	
	public static final Map<String, MM_Type> TYPES = JSONHandler.getTypes();
	public static final Map<String, MM_Material> MATERIALS = JSONHandler.getMaterials();

}
