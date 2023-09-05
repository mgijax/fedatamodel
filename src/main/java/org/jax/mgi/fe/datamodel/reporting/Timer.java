package org.jax.mgi.fe.datamodel.reporting;

/**
 * Timer
 * @author jsb
 * This helper class prints out string representations of time passed.
 */
public class Timer {
    private static long lastTime = System.currentTimeMillis();
    private static long firstTime = lastTime;
    
    public static long getElapsed () {
    	long now = System.currentTimeMillis();
    	long elapsed = now - lastTime;
    	lastTime = now;
    	return elapsed;
    }
    
    public static long write (String message) {
    	long elapsed = getElapsed();
    	System.out.println (elapsed + "ms : " + message);
    	return elapsed;
    }

    public static void writeTotal () {
    	long now = System.currentTimeMillis();
    	System.out.println ((now - firstTime) + "ms : total time");
    	return;
    }
}
