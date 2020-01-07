package glowredman.modularmaterials.util;

public class MiscUtils {
	
	public static String timer(long startTime) {
		double elapsedTime = System.nanoTime() - startTime;
		int ms = (int) Math.floor(elapsedTime / 1000000);
		int �s = (int) Math.floor((elapsedTime - ms * 1000000) / 1000);
		int ns = (int) (elapsedTime - ms * 1000000 - �s * 1000);
		return ms + "ms " + �s + "�s " + ns + "ns";
	}

}
