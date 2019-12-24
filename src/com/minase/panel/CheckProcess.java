package com.minase.panel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CheckProcess {
	
	public boolean checkProcessIfRunning(String processName) {
		boolean isRunning = true;
		
		try {
			Process proc = Runtime.getRuntime().exec("wmic.exe");
			BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			OutputStreamWriter oStream = new OutputStreamWriter(proc.getOutputStream());
			
			oStream.write("process where name='"+ processName +"'");
			oStream.flush();
			oStream.close();
			
			int ctr = 0;
			String line;
			while ((line = input.readLine()) != null) {
				if (ctr == 3) {
					if (line.isEmpty()) { isRunning = false; }
					break;
				}
				ctr++;
			}
			input.close();
		} catch (IOException e) { e.printStackTrace(); }
		
		return isRunning;
	}
	
	/**
	 * SAMPLE CODE
	 * 
	 * CheckProcess cp = new CheckProcess();
	 * System.out.println(cp.checkProcessIfRunning("eclipse.exe"));
	 * 
	 */
	
}
