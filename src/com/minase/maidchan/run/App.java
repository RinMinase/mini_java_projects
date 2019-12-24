package com.minase.maidchan.run;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import com.sun.awt.AWTUtilities;

public class App extends JFrame {
	
	private static final long serialVersionUID = 8245256649529473532L;
	
	private int x = 0, y = 0;
	public int width = 0, height = 0, initX = 0, initY = 0;
	
	public ImageIcon img = new ImageIcon("./img/run/eva-100ms.gif");

	//ScreenDimensions Capturers & Containers
	int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	int taskbarSize = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration()).bottom;
	
	//JComponents
	JLabel gif = new JLabel();	
	
	public App(int x, int y, int width, int height) {
		init(x, y, width, height);
	}
	
	public void init(int x, int y, int width, int height) {
		this.x = this.initX = x - img.getIconWidth();
		this.y = this.initY = y;
		this.width = width;
		this.height = height;
		
		setLayout(null);
		setUndecorated(true);
		setTitle("Eva!");
		setBounds(initX + img.getIconWidth(), initY, width, height);
		setIconImage(new ImageIcon("./img/run/eva.png").getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setExtendedState(JFrame.MAXIMIZED_BOTH | getExtendedState()); //Full screen/maximize window
		
//		gif.setOpaque(true);
//		gif.setBackground(new Color(255, 255, 255, 0));
		gif.setIcon(img);
		gif.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		//pic.setLocation(800-fecture.getIconWidth(), 450-fecture.getIconHeight());
		
		AWTUtilities.setWindowOpaque(this, false);
		
		
		
		add(gif);
	}
	
//	public void initialize() {
//		AWTUtilities.setWindowOpaque(this, false);
//		
//		setUndecorated(true);
//		setSize(screenWidth, screenHeight);
//		setLocation(0, 0);
//		setTitle("Eva!");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setResizable(false);
//		setIconImage(new ImageIcon("./img/run/eva.png").getImage());
//		setLayout(null);
//		
//		gif = new JLabel(img);
//		gif.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
//		add(gif);		
//	}
	
	public synchronized void display(){
		setVisible(true);		
	}
	
	public synchronized void imgMove() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					repaint();
					gif.setIcon(img);
					gif.setBounds(x, y, img.getIconWidth(), img.getIconHeight());
					
					if(x < screenWidth) {
						x += 15;
					} else {
						x = 0;
						if(y < screenHeight - 2*img.getIconHeight()) {
							y += img.getIconHeight();
						} else {
							y = 0;
						}
					}
					
					try{ Thread.sleep(80); } catch(InterruptedException e) { }
				}
			}
		});
		t.start();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				App app = new App(0-100, 13, 1366+128, 768);
//				app.initialize();
				app.display();
				app.imgMove();
			}
		});
		
	}
	
}
