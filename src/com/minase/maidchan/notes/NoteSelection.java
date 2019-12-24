package com.minase.maidchan.notes;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListDataListener;

import com.sun.awt.AWTUtilities;

public class NoteSelection extends JFrame {

	private static final long serialVersionUID = 9040096823705120804L;
	
	private JLabel balloon, maidchan;
	
	private JList<String> notelist;
	
	//CONSTANTS
	int taskbarSize = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration()).bottom;
	
	public NoteSelection() {
		
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
				
		JLabel msgTitle = new JLabel("MaidNotes v1.0");
		msgTitle.setBounds(0, 30, 340, 45);
		msgTitle.setFont(msgTitle.getFont().deriveFont(24f));
		msgTitle.setHorizontalAlignment(SwingConstants.CENTER);
		balloon.add(msgTitle);
		
		
		notelist = new JList<String>();
		notelist.setBounds(20, 40, 310, 260);
		balloon.add(notelist);
		
		add(maidchan);
		add(balloon);
	}
	
	public static void main(String[] args) {
		NoteSelection frame = new NoteSelection();
		frame.setVisible(true);
	}
	

}
