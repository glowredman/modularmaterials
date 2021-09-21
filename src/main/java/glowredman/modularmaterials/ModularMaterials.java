package glowredman.modularmaterials;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import glowredman.modularmaterials.client.ClientHandler;
import net.minecraftforge.fml.common.Mod;

@Mod(MM_Reference.MODID)
public class ModularMaterials {
	
	private static final Logger LOGGER = LogManager.getLogger();
	private static final String LOG_PREFIX = "[" + MM_Reference.MODID + "] ";
	
	public ModularMaterials() {
		});
	}

	public static void trace(String s) {
		LOGGER.trace(LOG_PREFIX + s);
	}

	public static void debug(String s) {
		LOGGER.debug(LOG_PREFIX + s);
	}
	
	public static void info(String s) {
		LOGGER.info(LOG_PREFIX + s);
	}
	
	public static void warn(String s) {
		LOGGER.warn(LOG_PREFIX + s);
	}
	
	public static void error(String s) {
		LOGGER.error(LOG_PREFIX + s);
	}
	
	public static void fatal(String s) {
		LOGGER.fatal(LOG_PREFIX + s);
	}
}