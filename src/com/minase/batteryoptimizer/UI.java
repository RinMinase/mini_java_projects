package com.minase.batteryoptimizer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class UI extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8487400596384313866L;
	
	//COMPONENTS
	JTabbedPane jtp;
	JPanel jp;
	JCheckBox[] jcApp, jcSvc, jcAdv;
	JButton jbApp, jbAppAdv, jbSvc, jbAdv, optimize, unoptimize;
	FinalBtnAction action;
	
	//MENU BAR AND ITEMS
	static JMenuBar jm;
	static JMenu mHelp;
	static JMenuItem miAbout;

	public static void addMenus(){
		
		//menu items
		mHelp = new JMenu("Help");
		
		//help submenu items
		miAbout = new JMenuItem("About");
		mHelp.add(miAbout);

		//adds to menu bar
		jm = new JMenuBar();
		jm.add(mHelp);
				
	}

	public void initialize(){
		
		action = new FinalBtnAction(this);
		
		setSize(350, 500);
		setLocation((1366-getWidth())/2, (768-getHeight())/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Battery Optimizer by Rin Minase");
		
//		UI.addMenus();
//		setJMenuBar(jm);
		
		jp = new JPanel();
		jp.setLayout(null);
		
		jtp = new JTabbedPane();
		jtp.setBounds(5, 5, this.getWidth()-16, 413);
		
		JPanel[] tabPanel = new JPanel[4];
		tabPanel[0] = new JPanel();
		tabPanel[1] = new JPanel();
		tabPanel[2] = new JPanel();
		tabPanel[3] = new JPanel();
		for (int i = 0; i < tabPanel.length; i++) tabPanel[i].setLayout(null);
		
		/**
		 * 	APPLICATIONS TAB
		 */
		
		JPanel[] appSubPanel = new JPanel[2];
		appSubPanel[0] = new JPanel();
		appSubPanel[0].setLayout(null);
		appSubPanel[0].setBounds(7, 5, 315, 200);
		appSubPanel[0].setBorder(BorderFactory.createTitledBorder("Basic Applications"));

		appSubPanel[1] = new JPanel();
		appSubPanel[1].setLayout(null);
		appSubPanel[1].setBounds(7, 208, 315, 155);
		appSubPanel[1].setBorder(BorderFactory.createTitledBorder("Advanced Applications"));
		
		jcApp = new JCheckBox[5];		
		jcApp[0] = new JCheckBox("RocketDock");
		jcApp[1] = new JCheckBox("Rainmeter");
		jcApp[2] = new JCheckBox("Launchy");
		jcApp[3] = new JCheckBox("Rainmeter Skin - Now Playing");
		jcApp[4] = new JCheckBox("Rainmeter Skin - Roromiya Karuta");
		

		jbApp = new JButton("Check All");
		jbApp.setBounds(10, 25, 150, 20);
		jbApp.setFocusable(false);
		jbApp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (arg0.getActionCommand().equals("Check All")) {
					for (int i = 0; i < 3; i++) jcApp[i].setSelected(true);
					jbApp.setText("Uncheck All");
				}else{
					for (int i = 0; i < 3; i++) jcApp[i].setSelected(false);
					jbApp.setText("Check All");
				}
						
			}
		});

		jbAppAdv = new JButton("Check All");
		jbAppAdv.setBounds(10, 25, 150, 20);
		jbAppAdv.setFocusable(false);
		jbAppAdv.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (arg0.getActionCommand().equals("Check All")) {
					for (int i = 3; i < jcSvc.length; i++) jcApp[i].setSelected(true);
					jbAppAdv.setText("Uncheck All");
				}else{
					for (int i = 3; i < jcSvc.length; i++) jcApp[i].setSelected(false);
					jbAppAdv.setText("Check All");
				}
						
			}
		});
		
		for (int i = 0; i < 3; i++) {
			jcApp[i].setBounds(10, 50+(30*i), 300, 25);
			appSubPanel[0].add(jcApp[i]);
		}
		
		appSubPanel[0].add(jbApp);
		
		for (int i = 3; i < jcApp.length; i++) {
			jcApp[i].setBounds(10, 50+(30*(i-3)), 300, 25);
			appSubPanel[1].add(jcApp[i]);
		}
		
		appSubPanel[1].add(jbAppAdv);
		
		tabPanel[0].add(appSubPanel[0]);
		tabPanel[0].add(appSubPanel[1]);
		
		/**
		 * 	SERVICES TAB
		 */
				
		jcSvc = new JCheckBox[5];
		jcSvc[0] = new JCheckBox("Application Experience");
		jcSvc[1] = new JCheckBox("Connectify");
		jcSvc[2] = new JCheckBox("PDAgent");
		jcSvc[3] = new JCheckBox("PDEngine");
		jcSvc[4] = new JCheckBox("Print Spooler");
		
		jbSvc = new JButton("Check All");
		jbSvc.setBounds(10, 10, 150, 25);
		jbSvc.setFocusable(false);
		jbSvc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (arg0.getActionCommand().equals("Check All")) {
					for (int i = 0; i < jcSvc.length; i++) jcSvc[i].setSelected(true);
					jbSvc.setText("Uncheck All");
				}else{
					for (int i = 0; i < jcSvc.length; i++) jcSvc[i].setSelected(false);
					jbSvc.setText("Check All");
				}
						
			}
		});
		
		for (int i = 0; i < jcSvc.length; i++) {
			jcSvc[i].setBounds(10, 45+(30*i), 300, 25);
			tabPanel[1].add(jcSvc[i]);
		}	
		
		tabPanel[1].add(jbSvc);
		

		/**
		 * 	ADVANCED TAB
		 */	
				
		jcAdv = new JCheckBox[3];
		jcAdv[0] = new JCheckBox("Disable WIFI");
		jcAdv[1] = new JCheckBox("Disable Aero");
		jcAdv[2] = new JCheckBox("Disable Transparency");
		
		jbAdv = new JButton("Check All");
		jbAdv.setBounds(10, 10, 150, 25);
		jbAdv.setFocusable(false);
		jbAdv.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getActionCommand().equals("Check All")) {
					for (int i = 0; i < jcAdv.length; i++) jcAdv[i].setSelected(true);
					jbAdv.setText("Uncheck All");
				}else{
					for (int i = 0; i < jcAdv.length; i++) jcAdv[i].setSelected(false);
					jbAdv.setText("Check All");
				}			
			}
		});
		
		for (int i = 0; i < jcAdv.length; i++) {
			jcAdv[i].setEnabled(false);
			jcAdv[i].setBounds(10, 45+(30*i), 300, 25);
			tabPanel[2].add(jcAdv[i]);
		}
		
		tabPanel[2].add(jbAdv);

		/**
		 * 	ABOUT TAB
		 */	

		JButton jlIcon = new JButton();
		JLabel jlTitle = new JLabel("Battery Optimizer");
		JLabel jlSubTitle = new JLabel("<html>Made by: Rin Minase<br>Copyright © 2013 Minase Conglomerate</html>");
		
		jlIcon.setBounds(10, 10, 48, 48);
		jlTitle.setBounds(70, 10, 270, 50);
		jlTitle.setFont(jlTitle.getFont().deriveFont(20f));
		jlSubTitle.setBounds(10, 90, 300, 50);
		
		
		tabPanel[3].add(jlIcon);
		tabPanel[3].add(jlTitle);
		tabPanel[3].add(jlSubTitle);

		/**
		 * 	OUTER PANE
		 */
		
		jtp.addTab("Applications", tabPanel[0]);
		jtp.addTab("Services", tabPanel[1]);
		jtp.addTab("Advanced", tabPanel[2]);
		jtp.addTab("About", tabPanel[3]);
		
		optimize = new JButton("Optimize");		
		unoptimize = new JButton("Unoptimize");

		optimize.setBounds((getWidth()-280)/3, 430, 140, 30);
		unoptimize.setBounds(optimize.getX()+((getWidth()-280)/3)+135, 430, 140, 30);

		optimize.addActionListener(action);
		
		jp.add(optimize);
		jp.add(unoptimize);
		jp.add(jtp);
		
		getContentPane().add(jp);
		setVisible(true);
		
	}
	

	public static void main(String[] args) {
		UI u = new UI();
		u.initialize();
	}
	
	
}
