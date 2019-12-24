package com.minase.maidchan.maidcrypt;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.sun.awt.AWTUtilities;

@SuppressWarnings("serial")
public class FrameCrypter extends JFrame {
	
	JLabel balloon, maidchan;
	JButton btnMount, btnUnmount;
	
	Actions action;
	
	//CONSTANTS
	int taskbarSize = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration()).bottom;
	
	public FrameCrypter() {
		action = new Actions(this);
		
		setUndecorated(true);
		AWTUtilities.setWindowOpaque(this, false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(415, 130);
		setLocation(1366-getWidth(), 768-taskbarSize-getHeight());
		setTitle("MaidCrypt by Rin Minase");
		setIconImage(new ImageIcon(getClass().getResource("img/icon.png")).getImage());
		
		balloon = new JLabel(new ImageIcon(getClass().getResource("img/balloon-s.png")));
		balloon.setBounds(0, 0, 350, 128);
				
		maidchan = new JLabel(new ImageIcon(getClass().getResource("img/tray-128x83.png")));
		maidchan.setBounds(330, 0, 83, 128);
		
		JLabel msgTitle = new JLabel("MaidCrypt v1.0");
		msgTitle.setBounds(20, 15, 350, 45);
		msgTitle.setFont(msgTitle.getFont().deriveFont(24f));
		msgTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch(Exception e) { System.err.println(e.getMessage()); }
		
		btnMount = new JButton("Mount");
		btnMount.setBounds(70, 60, 120, 40);
		btnMount.setFont(btnMount.getFont().deriveFont(16f));
		
		btnUnmount = new JButton("Unmount");
		btnUnmount.setBounds(200, 60, 120, 40);
		btnUnmount.setFont(btnUnmount.getFont().deriveFont(16f));
		
		balloon.add(msgTitle);
		balloon.add(btnMount);
		balloon.add(btnUnmount);
		
		add(maidchan);
		add(balloon);

		btnMount.addActionListener(action);
		btnUnmount.addActionListener(action);
		
		setVisible(true);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		FrameCrypter frame = new FrameCrypter();
	}
	
	private class Actions implements ActionListener {

		FrameCrypter fc;
		MaidCrypter mc = new MaidCrypter();
		
		public Actions(FrameCrypter fc) {
			this.fc = fc;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource().equals(fc.btnMount)) {
				mc.mount();
				JOptionPane.showMessageDialog(null, "\nRin-sama,\ndrive has been unlocked.\n\nFrom : Maid-chan", "メイドちゃん (Maid-chan)", 
						0, new ImageIcon(getClass().getResource("img/tray-128x83.png")));
			} else if (arg0.getSource().equals(fc.btnUnmount)) {
				mc.unmount();
				JOptionPane.showMessageDialog(null, "\nRin-sama,\ndrive has been re-locked.\n\nFrom : Maid-chan", "メイドちゃん (Maid-chan)", 
						0, new ImageIcon(getClass().getResource("img/tray-128x83.png")));
			}
		}
		
	}
	
}
