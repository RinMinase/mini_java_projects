package com.minase.maidchan.dock;

import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

//import com.minase.maidchan.aboutbox.*;

public class Actions implements ActionListener, DragSourceListener, MouseListener {

	UI u;
//	AboutUI au = new AboutUI();
	
	public void ShowAboutDialog() {
		JOptionPane.showMessageDialog(null, "Creator: Rin Minase (Akasaka Ryuunosuke in the real world)\n\n" +
			"Those who ask rude questions such as 'How old are you supposed to be?' will be firmly punished.\n" +
			"I am not 'supposed to be' anything! (duh!)\n\n" +
			"From : Maid-chan", "メイド�?�ゃん (Maid-chan)", 0, new ImageIcon("./img/idlepclocker/tray-128x83.png"));
	}
	
	public Actions(UI u) {
		this.u = u;
		ShowAboutDialog();
//		au.setVisible(true);
	}
	
	@Override
	public void dragDropEnd(DragSourceDropEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragEnter(DragSourceDragEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragExit(DragSourceEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragOver(DragSourceDragEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dropActionChanged(DragSourceDragEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
