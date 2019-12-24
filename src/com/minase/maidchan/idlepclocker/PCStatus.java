package com.minase.maidchan.idlepclocker;

public class PCStatus {
	
	public static final long MIN_IDLE_MINUTES = 2;
	
	public static enum IdleStatus {
		UNKNOWN, ONLINE, IDLE;
		
		public static IdleStatus getIdleStatus(long idleTime){

//			if (idleTime < MIN_IDLE_MINUTES) { return ONLINE; } else { return IDLE; }
			return (idleTime > MIN_IDLE_MINUTES * 60 * 1000) ? ONLINE : IDLE;
		}
	}
}
