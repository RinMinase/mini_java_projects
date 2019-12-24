package com.minase.maidchan.mypc.maidcrypt;

import java.io.IOException;

public class MaidCrypter {

	final String locale = "";
	final String offset = "\u0072\u0075\u006B\u0069\u0061\u0063\u0068\u0061\u006E\u0073\u0068\u0061\u006E\u0061\u0063\u0068\u0061\u006E";
	Process pr = null;
	
	public int mount() {
		try {
			pr = new ProcessBuilder("truecrypt", "/v", "\"D:\\Video Downloads\\_SAVED FILES\\Ero\\8923021\"", "/lo", "/e", "/p", "\""+ offset +"\"", "/a", "/q", "/s").start();
			return 0;
		} catch (IOException e) { System.err.println(e.getMessage()); return 1; }
	}

	public int unmount() {
		try {
			pr = new ProcessBuilder("truecrypt", "/do", "/q", "/s").start();
			return 0;
		} catch (IOException e) { System.err.println(e.getMessage()); return 1; }
	}

}
	
/**

try {
	@SuppressWarnings("unused")
	Process pr = new ProcessBuilder("taskkill.exe", "/f", "/IM", "notepad.exe").start();
	System.out.println("deleted");
} catch (IOException e) {
	e.printStackTrace();
}

*/

/**

MOUNT
truecrypt /v 8923021 /lo /e /p "rukiachanshanachan" /a /q /s
truecrypt /v "D:\Video Downloads\_SAVED FILES\Ero\8923021" /lo /e /p "rukiachanshanachan" /a /q /s

MOUNT - WITH BEEP
truecrypt /v 8923021 /lo /e /p "rukiachanshanachan" /a /b /q /s
truecrypt /v "D:\Video Downloads\_SAVED FILES\Ero\8923021" /lo /e /p "rukiachanshanachan" /a /b /q /s

UNMOUNT
truecrypt /do /q /s

 */