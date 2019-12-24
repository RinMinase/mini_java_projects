package com.minase.maidchan.idlepclocker;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sun.awt.AWTUtilities;

public class TimerStateUI extends JFrame {

	private static final long serialVersionUID = -1476397468093938842L;
//	static IdlePCLocker ipc = new IdlePCLocker();
	
	JLabel bg, titlemsg;
	JButton enable, disable;
	
	public TimerStateUI() {
		
		AWTUtilities.setWindowOpaque(this, true);
		
		setUndecorated(true);
		setSize(430, 322);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		bg = new JLabel(new ImageIcon("./img/idlepclocker/bg.png"));
		bg.setBounds(0, 0, 430, 322);
		
		titlemsg = new JLabel("<html><center>Rin-sama,<br>I have frozen your<br>PC Locking program<br>to stop it from<br>draining your precious battery</center></html>");
		titlemsg.setBounds(15, 10, 410, 150);
		titlemsg.setFont(titlemsg.getFont().deriveFont(16f));
		
		bg.add(titlemsg);
		
		add(bg);
		setVisible(true);	
	}
	
//	public static void main(String[] args) {
//		TimerStateUI tsu = new TimerStateUI();
//	}
	
	

}
