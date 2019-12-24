package com.minase.maidchan.dock;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class UI extends JFrame{

	private static final long serialVersionUID = 7004553814389515874L;

	Actions actions;
	
	//ScreenDimensions Capturers & Containers
		int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int taskbarSize = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration()).bottom;
		
	public UI() {
		// TODO Auto-generated constructor stub
	}
	
	public void init(){
		actions = new Actions(this);
		
		setUndecorated(true);
		setSize(150, 500);
		//screenWidth-getWidth()
		setLocation(0, (screenHeight-getHeight()-taskbarSize)/2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		UI u = new UI();
		u.init();
	}
	
}
