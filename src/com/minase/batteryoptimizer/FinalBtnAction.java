package com.minase.batteryoptimizer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FinalBtnAction implements ActionListener {

	UI u;
	String TasksKilled = "Tasks Killed:\n";
	
	public FinalBtnAction(UI u){
		this.u = u;
	}
	
	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Optimize")){
			if (u.jcApp[0].isSelected()) {
				try {
					Process pr = new ProcessBuilder("taskkill.exe", "/f", "/IM", "notepad.exe").start();
					TasksKilled += "Rocketdock\n";
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

}
