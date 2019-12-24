package com.minase.bitratecalculator;

import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.sun.awt.AWTUtilities;

public class UI extends JFrame {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 3024301179299971409L;

	public boolean DebuggingMode = false;
	
	//ScreenDimensions Capturers & Containers
	int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	int taskbarSize = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration()).bottom;
		
	//JComponents
	JLabel balloon, maidchan;
	JTextField size, hr, min, sec, bitrate;
	JComboBox<String> sizebox, bitratebox;
	JButton exit, about;
	JLabel[] labels;
	
	//ActionListener, KeyListener
	Actions action;
	
	public void init() {
		action = new Actions(this);

		setUndecorated(true);
		AWTUtilities.setWindowOpaque(this, false);
		setSize(470, 390);
		setResizable(false);
		setIconImage(new ImageIcon("./img/bitratecalculator/icon.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation((screenWidth-getWidth())/2, (screenHeight-taskbarSize-getHeight())/2);
		setTitle("Bitrate Calculator");
		setLayout(null);

		balloon = new JLabel(new ImageIcon("./img/bitratecalculator/balloon.png"));
		balloon.setBounds(10, 10, 350, 322);
				
		maidchan = new JLabel(new ImageIcon("./img/bitratecalculator/maid-chan.png"));
		maidchan.setBounds(getWidth()-198-5, getHeight()-taskbarSize-290, 198, 300);
		
		labels = new JLabel[6];
		
		for (int i = 0; i < labels.length; i++) {
			String temp = "";
			if (i == 0) temp = "Bitrate Calculator";
			if (i == 1) temp = "File Size :";
			if (i == 2) temp = "Hrs :";
			if (i == 3) temp = "Mins :";
			if (i == 4) temp = "Sec :";
			if (i == 5) temp = "Bitrate :";
			
			labels[i] = new JLabel(temp);
		}

		labels[0].setBounds(90, 20, 150, 30);
		labels[0].setFont(labels[0].getFont().deriveFont(17f));
		
		labels[1].setBounds(30, 70, 150, 20);
		labels[2].setBounds(30, 120, 50, 20);
		labels[3].setBounds(100, 120, 50, 20);
		labels[4].setBounds(170, 120, 50, 20);
		labels[5].setBounds(30, 170, 50, 20);
		
		size = new JTextField();
		size.setBounds(30, 90, 150, 25);
		
		sizebox = new JComboBox<>();
		sizebox.setBounds(185, 90, 80, 25);
		sizebox.addItem("GB");
		sizebox.addItem("MB");
		sizebox.addItem("KB");
		sizebox.addItem("Bytes");
		sizebox.setSelectedIndex(1);
		
		hr = new JTextField();
		hr.setBounds(30, 140, 50, 25);
		
		min = new JTextField();
		min.setBounds(100, 140, 50, 25);
		
		sec = new JTextField();
		sec.setBounds(170, 140, 50, 25);
		
		bitrate = new JTextField();
		bitrate.setBounds(30, 190, 150, 25);
		
		bitratebox = new JComboBox<>();
		bitratebox.setBounds(185, 190, 80, 25);
		bitratebox.addItem("mbps");
		bitratebox.addItem("kbps");
		bitratebox.setSelectedIndex(1);
		
		about = new JButton("About");
		about.setBounds(30, 240, 115, 30);
		
		exit = new JButton("Exit");
		exit.setBounds(155, 240, 115, 30);
		
		size.addKeyListener(action);
		hr.addKeyListener(action);
		min.addKeyListener(action);
		sec.addKeyListener(action);
		bitrate.addKeyListener(action);
		
		addingFocusListener();
		
		sizebox.addItemListener(action);
		bitratebox.addItemListener(action);
		sizebox.addActionListener(action);
		bitratebox.addActionListener(action);
		sizebox.setEnabled(false);
		bitratebox.setEnabled(false);
		
		about.addActionListener(action);
		exit.addActionListener(action);
		
		for (int i = 0; i < labels.length; i++) balloon.add(labels[i]);
		balloon.add(size);
		balloon.add(sizebox);
		balloon.add(hr);
		balloon.add(min);
		balloon.add(sec);
		balloon.add(bitrate);
		balloon.add(bitratebox);
		balloon.add(exit);
		balloon.add(about);
		
		add(maidchan);
		add(balloon);
		setVisible(true);
	}
	
	public void addingFocusListener() {
		size.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) { }
			
			@Override
			public void focusGained(FocusEvent e) { action.Source = "size"; }
		});

		hr.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) { }
			
			@Override
			public void focusGained(FocusEvent e) { action.Source = "hr"; }
		});

		min.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) { }
			
			@Override
			public void focusGained(FocusEvent e) { action.Source = "min"; }
		});

		sec.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) { }
			
			@Override
			public void focusGained(FocusEvent e) { action.Source = "sec"; }
		});

		bitrate.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) { }
			
			@Override
			public void focusGained(FocusEvent e) { action.Source = "bitrate"; }
		});
	}
	
	public static void main(String[] args) {
		UI u = new UI();
		u.init();
	}
	
}
