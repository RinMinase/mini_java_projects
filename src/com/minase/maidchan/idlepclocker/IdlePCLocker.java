package com.minase.maidchan.idlepclocker;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.MouseInfo;
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

public class IdlePCLocker extends JWindow {

	private static final long serialVersionUID = -7265207927434238640L;
		
	public boolean DebuggingMode = false;
	public boolean programEnabled = true;	//beta stage
	public boolean changeToMinutes = false;
	public boolean timerState = true;	//true = running ; false = stopped/paused
//	public boolean lockingPC = false;
	
	public long time = 0;
	public long timeInHundreds;
	public long timeToLockPC = 7;
//	public long countdownTimer = 5;
	
	public int popupStartsAt = 0;
	public int mouseX, mouseY, newMouseX = 0, newMouseY = 0;
	
	//ActionListener
	Actions action;
	
	//JComponents
	JLabel background, jl, sec;
	
	//ScreenDimensions Capturers & Containers
	int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	int taskbarSize = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration()).bottom;
	
	//PopupMenu Components
	MenuItem mnuAbout, mnuExit, mnuState;
	final PopupMenu popup;

	//Tray Components
	SystemTray st;
	Image imgTray;
	final TrayIcon ticon;
	
	//Other Components
	DecimalFormat decformat = new DecimalFormat("###.00");
	
	public IdlePCLocker(){
		action = new Actions(this);
		
		Settings setng = new Settings("IdlePCLocker");
		this.DebuggingMode = setng.getDebuggingMode();
		this.timeToLockPC = setng.getTimeToLockPC();
		this.popupStartsAt = setng.getPopupStartAt();
		
		AWTUtilities.setWindowOpaque(this, false);		
		setSize(430, 322);
		setAlwaysOnTop(true);
		setLocation(screenWidth-getWidth(), screenHeight-getHeight()-taskbarSize);
		
		jl = new JLabel("<html><center><br>Rin-sama you<br>have been idle for" +
				"<br>&nbsp&nbsp&nbsp&nbsp&nbsp seconds.</center><br><br><p align=" + "right" + 
				">Maid-chan</p></html>");
		jl.setBounds(60, 40, 200, 240);
		jl.setFont(jl.getFont().deriveFont(18f));
		
		sec = new JLabel("0");
		sec.setHorizontalAlignment(JLabel.RIGHT);
		sec.setBounds(60, 141, 50, 40);
		sec.setFont(sec.getFont().deriveFont(18f));
		
		background = new JLabel(new ImageIcon("./img/idlepclocker/bg.png"));
		add(background);
		
		addKeyListener(action);
		addMouseListener(action);
		background.add(jl);
		background.add(sec);
		
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
		
		st = SystemTray.getSystemTray();
		imgTray = Toolkit.getDefaultToolkit().getImage("./img/idlepclocker/tray-h.png");
		ticon = new TrayIcon(imgTray, "Maid-chan PC Idle", popup);
		ticon.setImageAutoSize(true);
		
		try { st.add(ticon); } catch (AWTException e) { e.printStackTrace(); }
		
	}
	
	public static void main(String[] args) {
	
		final IdlePCLocker t = new IdlePCLocker();
		
		@SuppressWarnings("unused")
		Process pr;
			
		while(true) {
			
			while (t.time <= t.timeToLockPC) {
				
				if (t.mnuState.getLabel().equals("Disable") || t.timerState) {
					
					t.mouseX = t.newMouseX;
					t.mouseY = t.newMouseY;
					
					t.newMouseX = MouseInfo.getPointerInfo().getLocation().x;
					t.newMouseY = MouseInfo.getPointerInfo().getLocation().y;
					
					
					if (t.newMouseX == t.mouseX && t.newMouseY == t.mouseY) {
						t.timeInHundreds++;
						if (t.timeInHundreds % 100 == 0) {
		
							if (t.DebuggingMode){
								System.out.println("(" + MouseInfo.getPointerInfo().getLocation().x +
										", " + MouseInfo.getPointerInfo().getLocation().y + ")");
								System.out.printf(String.format("%d seconds have passed.", t.time));
								System.out.println();
							}
							
							t.time++;
							
//							if (t.lockingPC) t.countdownTimer--;

							if (t.time >= t.popupStartsAt) {
								if (!t.isVisible()) t.setVisible(true);
									
								if(t.time >= 60){
									double timeInMin = (int)t.time / 60.00d;
									if (t.jl.getText().equals("<html><center><br>Rin-sama you<br>have been idle for" +
											"<br>&nbsp&nbsp&nbsp&nbsp&nbsp seconds.</center><br><br><p align=" + "right" + 
											">Maid-chan</p></html>")){
										t.jl.setText("<html><center><br>Rin-sama you<br>have been idle for" +
												"<br>&nbsp&nbsp&nbsp&nbsp&nbsp minutes.</center><br><br><p align=" + "right" + 
												">Maid-chan</p></html>");
									}
									if (t.DebuggingMode) System.out.println(t.time);
									t.sec.setText("" + t.decformat.format(timeInMin));
								}else{
									t.sec.setText("" + t.time);	
								}				
							}else{
								t.setVisible(false);
							}
							
//							if (t.time == t.timeToLockPC){
//								t.lockingPC = true;
//							}
//							
//							if (t.lockingPC){
//								t.jl.setLocation(40, 40);
//								t.jl.setText("<html><center><br>Rin-sama, I will<br>now be locking your PC in" +
//								"<br>&nbsp&nbsp&nbsp&nbsp&nbsp seconds.</center><br><br><p align=" + "right" + 
//								">Maid-chan</p></html>");
//							}
//							
						}
						
					}else{
						
						if (t.jl.getText().equals("<html><center><br>Rin-sama you<br>have been idle for" +
								"<br>&nbsp&nbsp&nbsp&nbsp&nbsp minutes.</center><br><br><p align=" + "right" + 
								">Maid-chan</p></html>")){
							t.jl.setText("<html><center><br>Rin-sama you<br>have been idle for" +
									"<br>&nbsp&nbsp&nbsp&nbsp&nbsp seconds.</center><br><br><p align=" + "right" + 
									">Maid-chan</p></html>");
						}
						t.sec.setText("" + t.time);
						t.time = 0;
					}
					try { Thread.sleep(10); } catch (Exception e) { }
					
				}else{
					t.setVisible(false);
				}
			
			}
			
			if (t.DebuggingMode) {
			
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
	
//	public boolean getTimerState() {
//		return timerState;
//	}
//	
//	public void setTimerState(boolean state) {
//		this.timerState = state;
//	}
	
	public void resetTime(){
		this.sec.setText("0");
		this.time = 0;
	}
}
