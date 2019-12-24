package com.minase.sao;

import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.sun.awt.AWTUtilities;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = 7306159536321783138L;
	
	private JPanel contentPane,
				   pnlIcons;
	
	JLabel[] lblIcons = new JLabel[3];
		   
	JLabel lblUser,
		   lblSettings,
		   lblRun;
	
	Actions action;
	
	ImageIcon[] imgDefault = new ImageIcon[3];
	ImageIcon[] imgHighlight = new ImageIcon[imgDefault.length];
//	ImageIcon[] imgUnhighlight = new ImageIcon[imgDefault.length];
		
	PopupMenu popup;
	MenuItem mnuExit;

	public GUI() {
		
		action = new Actions(this);

		setUndecorated(true);
		AWTUtilities.setWindowOpaque(this, false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(90, 260);
		setLocation(1366-getWidth(), (768-getHeight())/2);
		setAlwaysOnTop(true);
		
		try { imgDefault[0] = new ImageIcon(getClass().getResource("img/user.png")); } catch(NullPointerException e) { imgDefault[0] = new ImageIcon("./img/sao/user.png"); }
		try { imgDefault[1] = new ImageIcon(getClass().getResource("img/run.png")); } catch(NullPointerException e) { imgDefault[1] = new ImageIcon("./img/sao/run.png"); }
		try { imgDefault[2] = new ImageIcon(getClass().getResource("img/settings.png")); } catch(NullPointerException e) { imgDefault[2] = new ImageIcon("./img/sao/settings.png"); }
		
		try { imgHighlight[0] = new ImageIcon(getClass().getResource("img/user_h.png")); } catch(NullPointerException e) { imgHighlight[0] = new ImageIcon("./img/sao/user_h.png"); }
		try { imgHighlight[1] = new ImageIcon(getClass().getResource("img/run_h.png")); } catch(NullPointerException e) { imgHighlight[1] = new ImageIcon("./img/sao/run_h.png"); }
		try { imgHighlight[2] = new ImageIcon(getClass().getResource("img/settings_h.png")); } catch(NullPointerException e) { imgHighlight[2] = new ImageIcon("./img/sao/settings_h.png"); }
		
//		try { imgUnhighlight[0] = new ImageIcon(getClass().getResource("img/user_uh.png")); } catch(NullPointerException e) { imgUnhighlight[0] = new ImageIcon("./img/sao/user_uh.png"); }
//		try { imgUnhighlight[1] = new ImageIcon(getClass().getResource("img/run_uh.png")); } catch(NullPointerException e) { imgUnhighlight[1] = new ImageIcon("./img/sao/run_uh.png"); }
//		try { imgUnhighlight[2] = new ImageIcon(getClass().getResource("img/settings_uh.png")); } catch(NullPointerException e) { imgUnhighlight[2] = new ImageIcon("./img/sao/settings_uh.png"); }
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setOpaque(false);
		setContentPane(contentPane);
		
		pnlIcons = new JPanel();
		pnlIcons.setBounds(10, 10, 70, getHeight()-20);
		pnlIcons.setLayout(new GridLayout(3, 1, 0, 15));
		pnlIcons.setOpaque(false);
		contentPane.add(pnlIcons);
		
		for (int i = 0; i < lblIcons.length; i++) {
			lblIcons[i] = new JLabel(imgDefault[i]);
			lblIcons[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblIcons[i].addMouseListener(action);
			pnlIcons.add(lblIcons[i]);
		}
		
		/*lblUser = new JLabel(new ImageIcon(getClass().getResource("img/user.png")));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		pnlIcons.add(lblUser);
		
		lblSettings = new JLabel(new ImageIcon(getClass().getResource("img/settings.png")));
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		pnlIcons.add(lblSettings);
		
		lblRun = new JLabel(new ImageIcon(getClass().getResource("img/run.png")));
		lblRun.setHorizontalAlignment(SwingConstants.CENTER);
		pnlIcons.add(lblRun);

		pnlIcons.addMouseMotionListener(action);
		lblUser.addMouseMotionListener(action);
		lblSettings.addMouseMotionListener(action);
		lblRun.addMouseMotionListener(action);*/
		
	}
		
	@SuppressWarnings("unused")
	private void showRClickMenu() {
		popup = new PopupMenu();
		mnuExit = new MenuItem("Exit");
		
		popup.add(mnuExit);
		mnuExit.addActionListener(action);
	}
	
	private class Actions 
		implements
			ActionListener,
			FocusListener,
			KeyListener,
			MouseListener,
			MouseMotionListener {
		
		GUI gui;
		
		public Actions(GUI gui) {
			this.gui = gui;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			/*if (arg0.getSource().equals(gui.mnuExit)) {
				System.exit(0);
			}*/
		}

		@Override
		public void focusGained(FocusEvent arg0) { }

		@Override
		public void focusLost(FocusEvent arg0) { }

		@Override
		public void keyPressed(KeyEvent arg0) { }

		@Override
		public void keyReleased(KeyEvent arg0) { }

		@Override
		public void keyTyped(KeyEvent arg0) { }

		@Override
		public void mouseClicked(MouseEvent arg0) { }

		@Override
		public void mouseEntered(MouseEvent arg0) { }

		@Override
		public void mouseExited(MouseEvent arg0) { }

		@Override
		public void mousePressed(MouseEvent arg0) { 
			/*if (arg0.getSource().equals(gui.lblIcons[0])) {
				lblIcons[0].setIcon(imgHighlight[0]);
			} else if (arg0.getSource().equals(gui.lblIcons[1])) {
				
			} else if (arg0.getSource().equals(gui.lblIcons[2])) {
				try {
					lblIcons[0].setIcon(imgDefault[2]);
					for (int i = 1; i < lblIcons.length; i++) {
						lblIcons[i].setIcon(imgDefault[i-1]);
					}
					Thread.sleep(500);
					try {
						lblIcons[0].setIcon(imgHighlight[2]);
						for (int i = 1; i < lblIcons.length; i++) {
							lblIcons[i].setIcon(imgDefault[i-1]);
						}
						Thread.sleep(500);
					} catch (InterruptedException e) { }
				} catch (InterruptedException e) { }
				
			}*/
			
			if (arg0.getSource().equals(gui.lblIcons[0])) {
				lblIcons[0].setIcon(imgHighlight[0]);
				lblIcons[1].setIcon(imgDefault[1]);
				lblIcons[2].setIcon(imgDefault[2]);
			} else if (arg0.getSource().equals(gui.lblIcons[1])) {
				lblIcons[1].setIcon(imgHighlight[1]);
				lblIcons[0].setIcon(imgDefault[0]);
				lblIcons[2].setIcon(imgDefault[2]);
			} else if (arg0.getSource().equals(gui.lblIcons[2])) {
				lblIcons[2].setIcon(imgHighlight[2]);
				lblIcons[0].setIcon(imgDefault[0]);
				lblIcons[1].setIcon(imgDefault[1]);							
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			/*if (arg0.getButton() == 3) {
				if (arg0.getSource().equals(gui.lblIcons[0]) || 
						arg0.getSource().equals(gui.lblIcons[1]) || 
						arg0.getSource().equals(gui.lblIcons[2])) {
					
				}
			}*/			
		}

		@Override
		public void mouseDragged(MouseEvent arg0) { }

		@Override
		public void mouseMoved(MouseEvent arg0) {
//			System.out.println(arg0.getX());
			/*System.out.println(arg0.getY());
			if (arg0.getX() == 1 ||
					arg0.getY() == 1 ||
					arg0.getY() == 69) {
				
				
				lblUser.setIcon(new ImageIcon(getClass().getResource("img/user.png")));
				lblSettings.setIcon(new ImageIcon(getClass().getResource("img/settings.png")));
				lblRun.setIcon(new ImageIcon(getClass().getResource("img/run.png")));				
				
			} else {
				if (arg0.getSource().equals(gui.lblUser)) {
					lblUser.setIcon(new ImageIcon(getClass().getResource("img/user2.png")));
					lblSettings.setIcon(new ImageIcon(getClass().getResource("img/settings.png")));
					lblRun.setIcon(new ImageIcon(getClass().getResource("img/run.png")));
				} else if (arg0.getSource().equals(gui.lblSettings)) {
					lblUser.setIcon(new ImageIcon(getClass().getResource("img/user.png")));
					lblSettings.setIcon(new ImageIcon(getClass().getResource("img/settings2.png")));
					lblRun.setIcon(new ImageIcon(getClass().getResource("img/run.png")));
				} else if (arg0.getSource().equals(gui.lblRun)) {
					lblUser.setIcon(new ImageIcon(getClass().getResource("img/user.png")));
					lblSettings.setIcon(new ImageIcon(getClass().getResource("img/settings.png")));
					lblRun.setIcon(new ImageIcon(getClass().getResource("img/run2.png")));
				} else {
					lblUser.setIcon(new ImageIcon(getClass().getResource("img/user.png")));
					lblSettings.setIcon(new ImageIcon(getClass().getResource("img/settings.png")));
					lblRun.setIcon(new ImageIcon(getClass().getResource("img/run.png")));
				}
			}*/
		}
	}
}
