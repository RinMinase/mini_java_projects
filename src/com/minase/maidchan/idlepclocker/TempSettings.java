package com.minase.maidchan.idlepclocker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class TempSettings {
	
	public static String data;
	public static String Header = "IdlePCLocker";
	
	@SuppressWarnings("static-access")
	public TempSettings() {
		String data = "";
		
		try {
			FileReader fr = new FileReader("./Settings.ini");
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			
			while ((line = br.readLine()) != null) { data += line + "\r\n"; }
			br.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Unable to open file 'Settings.ini'");
			JOptionPane.showMessageDialog(null, "Creator: Rin Minase (Akasaka Ryuunosuke in the real world)\n\n" +
					"Hey! I could not locate the Settings file!\n" +
					"It should have been placed in the main folder! (duh!)\n\n" +
					"From : Maid-chan", "メイド�?�ゃん (Maid-chan)", 1, new ImageIcon("./img/idlepclocker/tray-128x83.png"));
			System.exit(0);
		}catch(IOException ex){
			System.out.println("Error reading file 'Settings.ini'");
			JOptionPane.showMessageDialog(null, "Creator: Rin Minase (Akasaka Ryuunosuke in the real world)\n\n" +
					"Sorry. It seems that there is an error reading the settings file\n" +
					"Umm. Please contact my owner Rin-sama to fix the bug.\n\n" +
					"From : Maid-chan", "メイド�?�ゃん (Maid-chan)", 1, new ImageIcon("./img/idlepclocker/tray-128x83.png"));
			System.exit(0);
		}
		
		this.data = data;
	}
	
	public static void main(String[] args) {
		System.out.println(Header);
		String x = data.split(Header + "]\r\n")[1];
		System.out.println(x);
		
		String[] y = x.split("[");
//		System.out.println(y);
		
		for (String z : y) {
			System.out.println(z);
		}
	}
	
	
}
