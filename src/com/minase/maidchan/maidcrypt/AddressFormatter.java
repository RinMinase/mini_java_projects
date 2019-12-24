package com.minase.maidchan.maidcrypt;

import java.io.File;

public class AddressFormatter {
	
	/**
	 * ~~STEPS~~
	 * 
	 * -CHECK LOCATION & FILE IF VALID
	 * -IF VALID
	 * 		-STORE LOCATION INSIDE A VARIABLE FOR UNCRYPTING PURPOSES
	 * 		-CUT FROM LAST "/" **FILE.TC** WILL BE LEFT
	 * 		-PUT INSIDE A VARIABLE FOR DISPLAY PURPOSES
	 * 		-TRY TO UNCRYPT
	 * 			-ON ERROR: POPUP "INVALID FILE"
	 * 			-ON SUCCESS: MOUNT!
	 * -IF INVALID
	 * 		-DO NOT STORE LOCATION
	 * 		-POPUP "FILE LOCATION INVALID" OR "CANT FIND FILE"
	 * 
	 * NOTES:
	 * 	-DRAG AND DROP AS MUCH AS POSSIBLE
	 * 		-OTHERWISE HAVE A TEXTFIELD (VERY VERY OPTIONAL)
	 * 	-BUTTON FOR MOUNT AND UNMOUNT
	 * 	-TEXTBOX OR COMBOBOX FOR DRIVE LETTER TO MOUNT
	 * 	-PASSWORD (PASSFIELD)
	 */
	
	public String Location = "";
	public String FileName = "";

	public AddressFormatter(String Address) {
		File file = new File(Address);
//		System.out.println("Exists - " + file.exists() + "\nIs a file - " + !file.isDirectory());
		
		if(file.exists() && !file.isDirectory()) { 
			System.out.println("exists!"); 
			
			Location = Address;
			String tempAddress = Address;
			try {
				tempAddress = tempAddress.replace("\\", "/");
				tempAddress = tempAddress.split("/")[tempAddress.split("/").length-1];
//				System.out.println(tempAddress);
				FileName = tempAddress;
			} catch (Exception e) { System.err.println(e.getMessage()); }
						
		} else {
			System.err.println("does not exist!");
		}
	}
	
	/*public static void main(String[] args) {
		new AddressFormatter("E:/File");
	}*/
	
}
