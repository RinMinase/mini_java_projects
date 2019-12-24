package com.minase.maidchan.maidcrypt;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.sun.awt.AWTUtilities;

public class FrameDnD extends JFrame {

	private static final long serialVersionUID = -6454699649727981115L;
	
	JLabel balloon, maidchan;
	
	Actions action;
	
	//CONSTANTS
	int taskbarSize = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration()).bottom;
		
	public FrameDnD() {
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
				
		JLabel msgTitle = new JLabel("MaidCrypt v1.1");
		msgTitle.setBounds(0, 30, 340, 45);
		msgTitle.setFont(msgTitle.getFont().deriveFont(24f));
		msgTitle.setHorizontalAlignment(SwingConstants.CENTER);
		
		balloon.add(msgTitle);
		
		add(maidchan);
		add(balloon);

//		balloon.addMouseMotionListener(action);
//		balloon.addMouseListener(action);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new FrameDnD();
	}
	
	private class Actions implements
		ActionListener,
		MouseMotionListener,
		MouseListener {
		
		@SuppressWarnings("unused")
		FrameDnD dnd;
		
		public Actions(FrameDnD dnd) {
			this.dnd = dnd;
		}

		@Override
		public void mouseClicked(MouseEvent arg0) { }

		@Override
		public void mouseEntered(MouseEvent arg0) {
			System.err.println(1);
		}

		@Override
		public void mouseExited(MouseEvent arg0) { }

		@Override
		public void mousePressed(MouseEvent arg0) { }

		@Override
		public void mouseReleased(MouseEvent arg0) { }

		@Override
		public void mouseDragged(MouseEvent arg0) { }

		@Override
		public void mouseMoved(MouseEvent arg0) {
//			System.out.println(arg0.getX() + ", " + arg0.getY());
//			System.err.println(arg0.getXOnScreen() + ", " + arg0.getYOnScreen());
		}

		@Override
		public void actionPerformed(ActionEvent arg0) { }
	}	
}
