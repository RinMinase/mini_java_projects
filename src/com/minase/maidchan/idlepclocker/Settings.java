package com.minase.maidchan.idlepclocker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Settings {
	private boolean DebuggingMode = false;
	private long timeToLockPC = 0;
	private int popupStartsAt = 0;
	private String Header = "";
	
	public boolean getDebuggingMode(){
		return DebuggingMode;
	}
	
	public long getTimeToLockPC() {
		return timeToLockPC;
	}
	
	public int getPopupStartAt() {
		return popupStartsAt;
	}
	
	private void setSettings(boolean Debug, long timeToLock, int popupStart){
		this.DebuggingMode = Debug;
		this.timeToLockPC = timeToLock;
		this.popupStartsAt = popupStart;
	}
	
	public Settings(String Header) {
		// Getting Settings
		this.Header = Header;
		
		String SettingsArray[] = SplitSettingData(RemoveHeader(ReadSettingsFile()));
		
		boolean Debug = Boolean.parseBoolean(SettingsArray[0]);
		long timeToLock = Long.parseLong(SettingsArray[1]);
		int popupStart = Integer.parseInt(SettingsArray[2]);
		
		setSettings(Debug, timeToLock, popupStart);
	}
	
	private String ReadSettingsFile(){
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
		
		return data;	
	}
	
//	private String newRemovedHeader(String data){
//		System.out.println(this.Header);
//		String x = data.split(this.Header + "]\r\n")[1];
//		System.out.println(x);
//		
//		String[] y = x.split("[");
////		System.out.println(y);
//		
//		for (String z : y) {
//			System.out.println(z);
//		}
//		
////		System.out.println(data.split(this.Header + "]\r\n")[1].split(";\r\n[")[0]);
//		
//		return data.split(this.Header + "]\r\n")[1].split(";\r\n[")[0];
//		
//	}
	
	private String RemoveHeader(String data){
		return data.split(this.Header + "]\r\n")[1];
	}
	
	private String[] SplitSettingData(String data){
		data = data.replaceAll("\r\n", "");
		String Temp[] = data.split(";");
		
		for (int i = 0; i < Temp.length; i++) { Temp[i] = Temp[i].split("=")[1]; }
		
		return Temp;
	}
	
}
