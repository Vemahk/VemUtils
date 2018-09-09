package me.vem.utils;

public class Utilities {

	public static void sleep(int hz) { sleep(hz, 0); }
	
	public static void sleep(int hz, long dt) {
		long nanodelay = 1000000000 / hz - dt;
		if(nanodelay < 0) return;
		
		try {
			Thread.sleep(nanodelay / 1000000, (int)(nanodelay % 1000000));
		} catch (InterruptedException e) {}
	}
	
}
