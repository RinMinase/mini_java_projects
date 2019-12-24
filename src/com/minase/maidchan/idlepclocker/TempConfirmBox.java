package com.minase.maidchan.idlepclocker;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class TempConfirmBox {

	public static void main(String[] args) {

		int x = JOptionPane.showConfirmDialog(null, "Rin-sama, I have frozen your PC\nUnlock?", 
				"PC Frozen", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
				new ImageIcon("./img/idlepclocker/tray-128x83.png"));
		
		System.out.println(x);
		
	}
	
}
