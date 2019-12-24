package com.minase.maidchan.idlepclocker;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JWindow;

import com.sun.awt.AWTUtilities;

public class IdlePCLockerJNAImplemented extends JWindow {

	private static final long serialVersionUID = -3353397902941851884L;
	
	public boolean debuggingMode = true;
	public boolean changeToMins = false;
	public boolean timerState = true; //true = running ; false = stopped/paused
	
	public long time = 0;
	public long timeToLockPC = 7;
	
	public int popupStartsAt = 0;
	
	//ActionListener, MouseListener & Other Listeners
	ActionsJNAImplemented action;
	
	//JComponents
	JLabel background, msgBody, msgTimer;
	
	//ScreenDimensions Capturers & Containers
	int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	int taskbarSize = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration()).bottom;
		
	//PopupMenu and Tray Components
	MenuItem mnuAbout, mnuState, mnuExit;
	final PopupMenu popup;
	SystemTray sysTray;
	Image imgTray;
	final TrayIcon trayIcon;
	
	//Other Components
	DecimalFormat decimalFormat = new DecimalFormat("###.00");
	
	public IdlePCLockerJNAImplemented() {
		action = new ActionsJNAImplemented(this);
		
		AWTUtilities.setWindowOpaque(this, false);
		setSize(430, 322);
		setAlwaysOnTop(true);
		setLocation(screenWidth-getWidth(), screenHeight-getHeight()-taskbarSize);
		
		msgBody = new JLabel("<html><center><br>Rin-sama you<br>have been idle for" +
				"<br>&nbsp&nbsp&nbsp&nbsp&nbsp seconds.</center><br><br><p align=" + "right" + 
				">Maid-chan</p></html>");
		msgBody.setBounds(60, 40, 200, 240);
		msgBody.setFont(msgBody.getFont().deriveFont(18f));
		
		msgTimer = new JLabel("0");
		msgTimer.setHorizontalAlignment(JLabel.RIGHT);
		msgTimer.setBounds(60, 141, 50, 40);
		msgTimer.setFont(msgTimer.getFont().deriveFont(18f));
		
		background = new JLabel(new ImageIcon("./img/idlepclocker/bg.png"));
		add(background);
		
		addKeyListener(action);
		addMouseListener(action);
		background.add(msgBody);
		background.add(msgTimer);
		
		//Menu Initialization
		popup = new PopupMenu();
		mnuAbout = new MenuItem("About");
		mnuState = new MenuItem("Disable");
		mnuExit = new MenuItem("Exit");
		
		popup.add(mnuAbout);
		popup.add(mnuState);
		popup.addSeparator();
		popup.add(mnuExit);
		
		mnuAbout.addActionListener(action);
		mnuState.addActionListener(action);
		mnuExit.addActionListener(action);
		
		sysTray = SystemTray.getSystemTray();
		imgTray = Toolkit.getDefaultToolkit().getImage("./img/idlepclocker/tray-h.png");
		trayIcon = new TrayIcon(imgTray, "Maid-chan PC Idle", popup);
		trayIcon.setImageAutoSize(true);
		
		try { sysTray.add(trayIcon); } catch (AWTException e) { e.printStackTrace(); }
	}
	
	public static void main(String[] args) {
		
		final IdlePCLockerJNAImplemented t = new IdlePCLockerJNAImplemented();
		
		@SuppressWarnings("unused")
		Process pr;
		
		while(true){
			
			while (t.time <= t.timeToLockPC) {
				
				if (t.mnuState.getLabel().equals("Disable") || t.timerState) {
					t.time = InputHook.getIdleTime() / 1000;
					
					if (t.time >= t.popupStartsAt) {
						if (!t.isVisible()) t.setVisible(true);
							
						if(t.time >= 60){
							double timeInMin = (int)t.time / 60.00d;
							
							if (t.msgBody.getText().equals("<html><center><br>Rin-sama you<br>have been idle for" +
									"<br>&nbsp&nbsp&nbsp&nbsp&nbsp seconds.</center><br><br><p align=" + "right" + 
									">Maid-chan</p></html>")){
								t.msgBody.setText("<html><center><br>Rin-sama you<br>have been idle for" +
										"<br>&nbsp&nbsp&nbsp&nbsp&nbsp minutes.</center><br><br><p align=" + "right" + 
										">Maid-chan</p></html>");
							}
							
							if (t.debuggingMode) System.out.println(t.time);
							t.msgTimer.setText("" + t.decimalFormat.format(timeInMin));
						}else{
							
							if (t.msgBody.getText().equals("<html><center><br>Rin-sama you<br>have been idle for" +
									"<br>&nbsp&nbsp&nbsp&nbsp&nbsp minutes.</center><br><br><p align=" + "right" + 
									">Maid-chan</p></html>")){
								t.msgBody.setText("<html><center><br>Rin-sama you<br>have been idle for" +
										"<br>&nbsp&nbsp&nbsp&nbsp&nbsp seconds.</center><br><br><p align=" + "right" + 
										">Maid-chan</p></html>");
							}
							
							t.msgTimer.setText("" + t.time);	
						}
						try { Thread.sleep(1000); } catch (Exception e) { }
					}
				}else{
					t.setVisible(false);
				}
			}
			
			if (t.debuggingMode) {
				
				try { 
					System.out.println("Logged Out"); 
				} catch (Exception e) {
					// NO CATCHES
				} finally { 
					int choice = JOptionPane.showConfirmDialog(null, "Rin-sama, I have frozen your PC\nUnlock?", 
							"PC Frozen", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
							new ImageIcon("./img/idlepclocker/tray-128x83.png"));
					
					if (choice == 0) { 
						t.timerState = true;
						t.mnuState.setLabel("Disable");
					} else { 
						t.timerState = false;
						t.mnuState.setLabel("Enable");
					}
					
					try { Thread.sleep(300); } catch (Exception e2) { }
					t.resetTime(); 
				}
			
			} else {
			
				try {
					pr = new ProcessBuilder("rundll32.exe", "user32.dll,", "LockWorkStation").start();
				} catch (IOException e) {
					e.printStackTrace(); 
				} finally { 
					int choice = JOptionPane.showConfirmDialog(null, "Rin-sama, I have frozen your PC\nUnlock?", 
							"PC Frozen", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
							new ImageIcon("./img/idlepclocker/tray-128x83.png"));
					
					if (choice == 0) { 
						t.timerState = true;
						t.mnuState.setLabel("Disable");
					} else { 
						t.timerState = false;
						t.mnuState.setLabel("Enable");
					}
					
					try { Thread.sleep(300); } catch (Exception e2) { }
					t.resetTime(); 
				}	
			}
			
		}
		
	}

	public void resetTime(){
		this.msgBody.setText("0");
		this.time = 0;
	}
}
