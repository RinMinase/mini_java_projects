package com.minase.maidchan.idlepclocker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Actions implements KeyListener, MouseListener, ActionListener {

	IdlePCLocker ipl;
	
	public Actions(IdlePCLocker ipl){
		this.ipl = ipl;
		ShowAboutDialog();
	}
	
	public void ShowAboutDialog() {
		JOptionPane.showMessageDialog(null, "Creator: Rin Minase (Akasaka Ryuunosuke in the real world)\n\n" +
			"Those who ask rude questions such as 'How old are you supposed to be?' will be firmly punished.\n" +
			"I am not 'supposed to be' anything! (duh!)\n\n" +
			"From : Maid-chan", "メイドちゃん (Maid-chan)", 0, new ImageIcon("./img/idlepclocker/tray-128x83.png"));
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		ipl.resetTime();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		ipl.resetTime();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		ipl.resetTime();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (ipl.DebuggingMode) System.out.println(e.getActionCommand());
		
		if (e.getActionCommand().equals("Exit")) {
			ipl.st.remove(ipl.ticon);
			ipl.dispose();
			System.exit(0);
		}else if (e.getActionCommand().equals("About")) {
			ShowAboutDialog();
		}else if (e.getActionCommand().equals("Disable")){
			ipl.timerState = false;
			ipl.mnuState.setLabel("Enable");
		}else if (e.getActionCommand().equals("Enable")){
			ipl.timerState = true;
			ipl.mnuState.setLabel("Disable");
		}
		
//		System.out.println(ipl.chkmenuEnable.getState());
//		if (ipl.chkmenuEnable.getState()) {
//			ipl.programEnabled = true;
//			ipl.setVisible(true);
//		}else {
//			ipl.programEnabled = false;
//			ipl.setVisible(false);
//		}
	}

}
