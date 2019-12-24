package com.minase.maidchan.aboutbox;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AboutMaidchan extends JFrame implements ActionListener {

	private static final long serialVersionUID = 6382621366320763890L;

	//ScreenDimensions Capturers & Containers
	int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	int taskbarSize = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration()).bottom;
	
	JLabel maidicon, message;
//	JButton close, changelog;
	
	public void initialize() {
		setSize(674, 170);
		setLocation((screenWidth-getWidth())/2, (screenHeight-getHeight()-taskbarSize)/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("メイドちゃん (Maid-chan)");
		setResizable(false);
		setIconImage(new ImageIcon("./img/idlepclocker/tray-h.png").getImage());
		setLayout(null);
		
		maidicon = new JLabel(new ImageIcon("./img/idlepclocker/tray-128x83.png"));
		maidicon.setBounds(5, 5, maidicon.getIcon().getIconWidth(), maidicon.getIcon().getIconHeight());

		// CSS -> style="postion:absolute; right:20px; top:0px;"
				
		message = new JLabel("<html>Creator: Rin Minase (Akasaka Ryuunosuke in the real world)<br><br>" +
				"Those who ask rude questions such as 'How old are you supposed to be?' will be firmly punished.<br>" +
				"I am not 'supposed to be' anything! (duh!)<br><br>" +
				"From : Maid-chan</html>");
		message.setBounds((5+maidicon.getWidth()+15), 5, getWidth()-maidicon.getWidth()-10, getHeight()-50);
		
		add(maidicon);
		add(message);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		AboutMaidchan aui = new AboutMaidchan();
		aui.initialize();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
