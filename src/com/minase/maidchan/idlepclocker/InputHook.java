package com.minase.maidchan.idlepclocker;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;

public class InputHook {
	
	public interface Kernel32 extends StdCallLibrary {
		Kernel32 INSTANCE = (Kernel32)Native.loadLibrary("kernel32", Kernel32.class);
		
		public int GetTickCount();
	}
	
	public interface User32 extends StdCallLibrary {
		User32 INSTANCE = (User32)Native.loadLibrary("user32", User32.class);
		
		
		public static class LASTINPUTINFO extends Structure {
			public int cbSize = 8;
			public int dwTime;
			
			protected List<String> getFieldOrder() { return Arrays.asList("cbSize", "dwTime"); }
		}
		
		public boolean GetLastInputInfo(LASTINPUTINFO plii);
	
	}
	
	public static long getIdleTime() {
		User32.LASTINPUTINFO lastInput = new User32.LASTINPUTINFO();
		User32.INSTANCE.GetLastInputInfo(lastInput);
		
		return Kernel32.INSTANCE.GetTickCount() - lastInput.dwTime;
	}
	
}
