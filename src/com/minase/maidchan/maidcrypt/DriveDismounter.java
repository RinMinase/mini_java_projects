package com.minase.maidchan.maidcrypt;

import java.io.IOException;

public class DriveDismounter {
	
	private String driveLetter = "";

	public DriveDismounter(String driveLetter) {
		this.driveLetter = driveLetter;
		Dismount();
	}
	
	public DriveDismounter(char driveLetter) {
		this.driveLetter = "" + driveLetter;
		Dismount();
	}
	
	private int Dismount() {
		try {
			new ProcessBuilder("truecrypt", "/d" + driveLetter, "/q", "/s").start();
			return 0;
		} catch (IOException e) { System.err.println(e.getMessage()); return 1; }
	}
	
	public static void main(String[] args) {
		new DriveDismounter("o");
	}
}
