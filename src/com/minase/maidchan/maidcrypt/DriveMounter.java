package com.minase.maidchan.maidcrypt;

import java.io.IOException;

public class DriveMounter {

	private String Location = "";
	private String driveLetter = "";
	private String Password = "";
	private static String offset = "\u0072\u0075\u006B\u0069\u0061\u0063\u0068\u0061\u006E\u0073\u0068\u0061\u006E\u0061\u0063\u0068\u0061\u006E";
	
	public DriveMounter(String Location, char driveLetter, String Password) {
		this.Location = Location;
		this.driveLetter = "" + driveLetter;
		this.Password = Password;
		
		MountDisk();
	}
	
	public DriveMounter(String Location, String driveLetter, String Password) {
		this.Location = Location;
		this.driveLetter = driveLetter;
		this.Password = Password;
		
		MountDisk();
	}
	
	private int MountDisk() {
		try {
			new ProcessBuilder("truecrypt", "/v", "\"" + Location + "\"", "/l" + driveLetter, "/e", "/p", "\""+ Password +"\"", "/a", "/q", "/s").start();
			return 0;
		} catch (IOException e) { System.err.println(e.getMessage()); return 1; }
	}
	
	public static void main(String[] args) {
		
		new DriveMounter("D:\\Video Downloads\\_SAVED FILES\\Ero\\8923021", "o", offset);
	}

}
