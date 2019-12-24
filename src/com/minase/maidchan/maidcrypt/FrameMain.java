package com.minase.maidchan.maidcrypt;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.sun.awt.AWTUtilities;

@SuppressWarnings("serial")
public class FrameMain extends JFrame {

	JLabel balloon, maidchan, incPass;
	JPasswordField passField;
	
	Actions action;
	
	//CONSTANTS
	int taskbarSize = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration()).bottom;
	
	public FrameMain() {
		action = new Actions(this);
		
		setUndecorated(true);
		AWTUtilities.setWindowOpaque(this, false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(470, 390);
		setLayout(null);
		setTitle("MaidCrypt by Rin Minase");
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(getClass().getResource("img/icon.png")).getImage());
		
		balloon = new JLabel(new ImageIcon(getClass().getResource("img/balloon.png")));
		balloon.setBounds(10, 10, 350, 322);
				
		maidchan = new JLabel(new ImageIcon(getClass().getResource("img/maid-chan.png")));
		maidchan.setBounds(getWidth()-198-5, getHeight()-taskbarSize-290, 198, 300);
				
		JLabel msgTitle = new JLabel("MaidCrypt v1.0");
		msgTitle.setBounds(0, 30, 340, 45);
		msgTitle.setFont(msgTitle.getFont().deriveFont(24f));
		msgTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel msgTop = new JLabel("Enter password:");
		msgTop.setBounds(10, 90, 260, 40);
		msgTop.setFont(msgTop.getFont().deriveFont(21f));
		msgTop.setHorizontalAlignment(SwingConstants.CENTER);
		
		passField = new JPasswordField();
		passField.setBounds(40, 130, 200, 40);
		passField.setFont(passField.getFont().deriveFont(21f));
		passField.setHorizontalAlignment(SwingConstants.CENTER);
//		passField.setOpaque(false);
		
		incPass = new JLabel("Incorrect password");
		incPass.setBounds(40, 170, 200, 20);
		incPass.setForeground(Color.RED);
		incPass.setHorizontalAlignment(SwingConstants.CENTER);
		incPass.setVisible(false);
		
		JLabel copyright = new JLabel("Made by: Rin Minase");
		copyright.setBounds(40, 210, 150, 20);
		copyright.setFont(copyright.getFont().deriveFont(10f));
		
		JLabel copyright2 = new JLabel("Minase Conglomerate");
		copyright2.setBounds(40, 225, 150, 20);
		copyright2.setFont(copyright2.getFont().deriveFont(10f));
				
		balloon.add(msgTitle);
		balloon.add(msgTop);
		balloon.add(passField);
		balloon.add(copyright);
		balloon.add(copyright2);
		balloon.add(incPass);
		
		add(maidchan);
		add(balloon);
		
		passField.addKeyListener(action);
	}
	
	public static void main(String[] args) {
		FrameMain frame = new FrameMain();
		frame.setVisible(true);
	}

	private class Actions implements KeyListener {
		
		FrameMain frame;
		MaidCrypter mc = new MaidCrypter();
		
		@SuppressWarnings("unused")
		public void ShowAboutDialog() {
			JOptionPane.showMessageDialog(null, "Creator: Rin Minase\n\n" +
				"Those who ask rude questions such as 'How old are you supposed to be?' will be firmly punished.\n" +
				"I am not 'supposed to be' anything! (duh!)\n\n" +
				"From : Maid-chan", "メイドちゃん (Maid-chan)", 0, new ImageIcon(getClass().getResource("img/tray-128x83.png")));
		}
		
		public Actions(FrameMain frame) {
			this.frame = frame;
//			ShowAboutDialog();
		}
		
		@Override
		public void keyPressed(KeyEvent arg0) { }

		@Override
		public void keyReleased(KeyEvent arg0) {
			if (arg0.getSource().equals(frame.passField)) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					String pass = "";
					for (char c : frame.passField.getPassword()) { pass+=c; }
//					System.out.println(pass);
					
					// ASCII VALS
					
					if(pass.equals("\u0072\u0075\u006B\u0069\u0061\u0063\u0068\u0061\u006E\u0073\u0068\u0061\u006E\u0061\u0063\u0068\u0061\u006E")){
//						System.out.println("pass");
						frame.incPass.setVisible(false);
						pass = "";
						frame.passField.setText("");
						mc.mount();						
					} else {
						frame.incPass.setVisible(true);
						
						Timer timer = new Timer(1500, new ActionListener() { public void actionPerformed(ActionEvent arg0) { frame.incPass.setVisible(false); } });
						timer.start();
					}
					pass = "";
					frame.passField.setText("");
				}
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) { }
		
	}
}
