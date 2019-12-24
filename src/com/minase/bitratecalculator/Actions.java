package com.minase.bitratecalculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Actions implements ActionListener, KeyListener, ItemListener {

	UI u;
	public String Source = "size";
	
	//TEMPLATE
	
//	if(Source.equals("size")){
//		
//	}else if(Source.equals("hr")){
//		
//	}else if(Source.equals("min")){
//		
//	}else if(Source.equals("sec")){
//		
//	}else if(Source.equals("bitrate")){
//		
//	}
	
	public Actions(UI u) {
		this.u = u;
		ShowAboutDialog();
	}
	
	public void ShowAboutDialog() {
		JOptionPane.showMessageDialog(null, "Creator: Rin Minase (Akasaka Ryuunosuke in the real world)\n\n" +
			"Those who ask rude questions such as 'How old are you supposed to be?' will be firmly punished.\n" +
			"I am not 'supposed to be' anything! (duh!)\n\n" +
			"From : Maid-chan", "メイドちゃん (Maid-chan)", 0, new ImageIcon("./img/bitratecalculator/tray-128x83.png"));
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER || 
				arg0.isShiftDown() || arg0.isAltDown() || 
				arg0.isControlDown() || arg0.isActionKey() ||
				arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE ||
				arg0.getKeyCode() == KeyEvent.VK_TAB ||
				arg0.getKeyCode() == KeyEvent.VK_CAPS_LOCK ||
				arg0.getKeyCode() == KeyEvent.VK_WINDOWS ||
				arg0.getKeyCode() == KeyEvent.VK_UP ||
				arg0.getKeyCode() == KeyEvent.VK_DOWN ||
				arg0.getKeyCode() == KeyEvent.VK_LEFT ||
				arg0.getKeyCode() == KeyEvent.VK_RIGHT ||
				arg0.getKeyCode() == KeyEvent.VK_DELETE ||
				arg0.getKeyCode() == KeyEvent.VK_ESCAPE ||
				arg0.getKeyCode() == KeyEvent.VK_PRINTSCREEN ||
				arg0.getKeyCode() == KeyEvent.VK_PAUSE ||
				arg0.getKeyCode() == KeyEvent.VK_INSERT ||
				arg0.getKeyCode() == KeyEvent.VK_NUM_LOCK ||
				arg0.getKeyCode() == KeyEvent.VK_SCROLL_LOCK ||
				arg0.getKeyCode() == KeyEvent.VK_HOME ||
				arg0.getKeyCode() == KeyEvent.VK_END){
		}else{
			try {
				
				if (arg0.getKeyChar() != '.') Integer.parseInt("" + arg0.getKeyChar());
				
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Hey! What are you doing!\n\n" +
					"You are supposed to input numbers here!\n" +
					"Can't your dissapointment of a brain understand this?\n\nFrom : Maid-chan", 
					"メイドちゃん (Maid-chan)", 0, new ImageIcon("./img/bitratecalculator/tray-128x83.png"));
				
				try {
					if(Source.equals("size")){
						u.size.setText(u.size.getText().substring(0, u.size.getText().length()-1));
					}else if(Source.equals("hr")){
						u.hr.setText(u.hr.getText().substring(0, u.hr.getText().length()-1));
					}else if(Source.equals("min")){
						u.min.setText(u.min.getText().substring(0, u.min.getText().length()-1));
					}else if(Source.equals("sec")){
						u.sec.setText(u.sec.getText().substring(0, u.sec.getText().length()-1));
					}else if(Source.equals("bitrate")){
						u.bitrate.setText(u.bitrate.getText().substring(0, u.bitrate.getText().length()-1));
					}
				}catch (StringIndexOutOfBoundsException e1) { }
			}
		
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		DecimalFormat df = new DecimalFormat("###.#");
		double totalbr = 0d, totalsize = 0d, totaltime = 0d;
				
		if(Source.equals("size")){
			
			if (u.min.getText().equals("")) {
				totaltime = 24d*60d;
				u.min.setText("24");				
			}else{
				totaltime = Double.parseDouble(u.min.getText()) * 60;
			}
			
			try { totalsize = Double.parseDouble(u.size.getText()); } catch (NumberFormatException e) { }
			
			if (u.bitratebox.getSelectedItem().equals("kbps")){
				totalbr = ((8385*totalsize)/totaltime);
			}else{
				totalbr = (((8385*totalsize)/totaltime)/1024);
			}
			u.bitrate.setText(""+ df.format(totalbr));
		}else if(Source.equals("hr")){
			
			totalsize = Double.parseDouble(u.size.getText());

			try { totaltime = Double.parseDouble(u.hr.getText()) * 3600; } catch (NumberFormatException e) { }
			
			if (u.min.getText().equals("")) {
				u.min.setText("0");				
			}else{
				totaltime += Double.parseDouble(u.min.getText()) * 60;
			}
			
			if (u.sec.getText().equals("")) {
				u.sec.setText("0");				
			}else{
				totaltime += Double.parseDouble(u.sec.getText());
			}
			
			if (u.bitratebox.getSelectedItem().equals("kbps")){
				totalbr = ((8385*totalsize)/totaltime);
			}else{
				totalbr = (((8385*totalsize)/totaltime)/1024);
			}
			u.bitrate.setText(""+ df.format(totalbr));
			
		}else if(Source.equals("min")){
			totalsize = Double.parseDouble(u.size.getText());

			try { totaltime = Double.parseDouble(u.min.getText()) * 60; } catch (NumberFormatException e) { }
			
			if (u.hr.getText().equals("")) {
				u.hr.setText("0");				
			}else{
				totaltime += Double.parseDouble(u.hr.getText()) * 3600;
			}
			
			if (u.sec.getText().equals("")) {
				u.sec.setText("0");				
			}else{
				totaltime += Double.parseDouble(u.sec.getText());
			}
			
			if (u.bitratebox.getSelectedItem().equals("kbps")){
				totalbr = ((8385*totalsize)/totaltime);
			}else{
				totalbr = (((8385*totalsize)/totaltime)/1024);
			}
			u.bitrate.setText(""+ df.format(totalbr));
			
		}else if(Source.equals("sec")){
			
			totalsize = Double.parseDouble(u.size.getText());

			try { totaltime = Double.parseDouble(u.sec.getText()); } catch (NumberFormatException e) { }
			
			if (u.hr.getText().equals("")) {
				u.hr.setText("0");				
			}else{
				totaltime += Double.parseDouble(u.hr.getText()) * 3600;
			}
			
			if (u.min.getText().equals("")) {
				u.min.setText("0");				
			}else{
				totaltime += Double.parseDouble(u.min.getText()) * 60;
			}
			
			if (u.bitratebox.getSelectedItem().equals("kbps")){
				totalbr = ((8385*totalsize)/totaltime);
			}else{
				totalbr = (((8385*totalsize)/totaltime)/1024);
			}
			u.bitrate.setText(""+ df.format(totalbr));
			
		}else if(Source.equals("bitrate")){
			
			if (u.min.getText().equals("")) {
				totaltime = 24d*60d;
				u.min.setText("24");				
			}else{
				totaltime = Double.parseDouble(u.min.getText()) * 60;
			}
			
			try { totalbr = Double.parseDouble(u.bitrate.getText()); } catch (NumberFormatException e) { }
			
			if (u.sizebox.getSelectedItem().equals("MB")){
				totalsize = ((totalbr*totaltime)/8385);	
			}else if (u.sizebox.getSelectedItem().equals("GB")){
				totalsize = (((totalbr*totaltime)/8385)/1024);
			}else if (u.sizebox.getSelectedItem().equals("KB")){
				totalsize = (((totalbr*totaltime)/8385)*1024);
			}else if (u.sizebox.getSelectedItem().equals("Bytes")){
				totalsize = ((((totalbr*totaltime)/8385)*1024)*1024);
			}
			u.size.setText(""+ df.format(totalsize));
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) { }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getActionCommand().equals("Exit")){
			System.exit(0);
		}else if (arg0.getActionCommand().equals("About")) {
			ShowAboutDialog();
		}

	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		
//		String temptemp = arg0.getItem().tos;
//		String temp = temptemp.split("xxx")[1];
//		System.out.println("XXX" + temp);
		
//		if (arg0.getItem().toString().equals("GB\r\nMB")) u.size.setText("" + (Double.parseDouble(u.size.getText())*1024));
//		if (arg0.getItem().toString().equals("GB\r\nKB")) u.size.setText("" + ((Double.parseDouble(u.size.getText())*1024)*1024));
//		if (arg0.getItem().toString().equals("GB\r\nBytes")) u.size.setText("" + (((Double.parseDouble(u.size.getText())*1024)*1024)*1024));
//		
		
//		if (arg0.getItem().equals("GB")){
//			
//			
//		}else if (arg0.getItem().equals("MB")){
//			
//			if (arg0.getItem().equals("GB")) u.size.setText("" + (Double.parseDouble(u.size.getText())/1024));
//			if (arg0.getItem().equals("KB")) u.size.setText("" + (Double.parseDouble(u.size.getText())*1024));
//			if (arg0.getItem().equals("Bytes")) u.size.setText("" + ((Double.parseDouble(u.size.getText())*1024)*1024));
//			
//		}else if (arg0.getItem().equals("KB")){
//			
//			if (arg0.getItem().equals("GB")) u.size.setText("" + ((Double.parseDouble(u.size.getText())/1024)/1024));
//			if (arg0.getItem().equals("MB")) u.size.setText("" + (Double.parseDouble(u.size.getText())/1024));
//			if (arg0.getItem().equals("Bytes")) u.size.setText("" + (Double.parseDouble(u.size.getText())*1024));
//			
//		}else if (arg0.getItem().equals("Bytes")){
//
//			if (arg0.getItem().equals("GB")) u.size.setText("" + (((Double.parseDouble(u.size.getText())/1024)/1024)/1024));
//			if (arg0.getItem().equals("MB")) u.size.setText("" + ((Double.parseDouble(u.size.getText())/1024)/1024));
//			if (arg0.getItem().equals("KB")) u.size.setText("" + (Double.parseDouble(u.size.getText())/1024));
			
//		}else if (arg0.getItem().equals("mbps")){
//			
//		}else if (arg0.getItem().equals("kbps")){
//			
//		}
		
	}

}
