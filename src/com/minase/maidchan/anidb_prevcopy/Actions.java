package com.minase.maidchan.anidb_prevcopy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Actions implements ActionListener, KeyListener, MouseListener, ItemListener, FocusListener {

	MainGUI gui;
	String itemSelected, sourceLocation;
	Object sourceObject;
	
	public Actions(MainGUI gui) {
		this.gui = gui;
		if (gui.showAbtDlg) ShowAboutDialog();
	}
	
	public void ShowAboutDialog() {
		JOptionPane.showMessageDialog(null, "Creator: Rin Minase (Akasaka Ryuunosuke in the real world)\n\n" +
			"Those who ask rude questions such as 'How old are you supposed to be?' will be firmly punished.\n" +
			"I am not 'supposed to be' anything! (duh!)\n\n" +
			"From : Maid-chan", "メイドーちゃん (Maid-chan)", 0, new ImageIcon(getClass().getResource("img/tray-128x83.png"))); 
		//new ImageIcon("./img/anidb/tray-128x83.png")
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) { }

	@Override
	public void mouseEntered(MouseEvent arg0) { }

	@Override
	public void mouseExited(MouseEvent arg0) { }

	@Override
	public void mousePressed(MouseEvent arg0) { }

	@Override
	public void mouseReleased(MouseEvent arg0) { }

	@Override
	public void keyPressed(KeyEvent arg0) { }

	@Override
	public void keyReleased(KeyEvent arg0) { 
		if (arg0.getSource().equals(gui.p1Title)) {
			gui.p1Title.setToolTipText(gui.p1Title.getText());
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) { }

	@Override
	public void focusGained(FocusEvent arg0) { }

	@Override
	public void focusLost(FocusEvent arg0) {
//		this.sourceLocation = arg0.getComponent().toString();
//		this.sourceLocation = this.sourceLocation.substring(this.sourceLocation.indexOf('[')+2, this.sourceLocation.length()-1);
//		this.sourceLocation = this.sourceLocation.split(",", 3)[0] + "," + this.sourceLocation.split(",", 3)[1];
//		
//		if(gui.debuggingMode) System.out.println(sourceLocation);
//		
//		if (this.sourceLocation.equals(gui.p1Title.getX() + "," + gui.p1Title.getY())) {
//			this.sourceObject = gui.p1Title;
//		} else if (this.sourceLocation.equals(gui.p1Size.getX() + "," + gui.p1Size.getY())) {
//			this.sourceObject = gui.p1Size;
//		} else if (this.sourceLocation.equals(gui.p1LengthMinute.getX() + "," + gui.p1LengthMinute.getY())) {
//			this.sourceObject = gui.p1LengthMinute;
//		} else if (this.sourceLocation.equals(gui.p1LengthSecond.getX() + "," + gui.p1LengthSecond.getY())) {
//			this.sourceObject = gui.p1LengthSecond;
//		}
//		
//		if (this.sourceObject.equals(gui.p1Title)) {
//			String unmodifiedTitle = gui.p1Title.getText();
//			String[] unmodifiedTitleWords = unmodifiedTitle.split(" ");
//			String modifiedTitle = "";
//			
//			for (int i = 0; i < unmodifiedTitleWords.length; i++) {
//				if (unmodifiedTitleWords[i].length() > 2) {
//					unmodifiedTitleWords[i] = ("" + unmodifiedTitleWords[i].charAt(0)).toUpperCase() 
//							+ unmodifiedTitleWords[i].substring(1, unmodifiedTitleWords[i].length());
//				}
//			}
//			
//			for (int i = 0; i < unmodifiedTitleWords.length; i++) modifiedTitle += unmodifiedTitleWords[i] + " ";
//			modifiedTitle = modifiedTitle.trim();
//			gui.p1Title.setText(modifiedTitle);
//			gui.p1Title.setToolTipText(gui.p1Title.getText());
//		} else if (this.sourceObject.equals(gui.p1Size)) {
//			gui.p1Size.setText(gui.p1Size.getText().replaceAll("[^0-9.]", ""));
//		} else if (this.sourceObject.equals(gui.p1LengthMinute)) {
//			
//			if (Integer.parseInt(gui.p1LengthMinute.getText()) > 59) {
//				try { 
//					gui.p1LengthHour.setText(((Integer.parseInt(gui.p1LengthHour.getText())) + (Integer.parseInt(gui.p1LengthMinute.getText()) / 60)) + "");
//				} catch (NumberFormatException e) { 
//					gui.p1LengthHour.setText((Integer.parseInt(gui.p1LengthMinute.getText()) / 60) + ""); 
//				}
//				gui.p1LengthMinute.setText((Integer.parseInt(gui.p1LengthMinute.getText()) % 60) + "");
//			}
//		} else if (this.sourceObject.equals(gui.p1LengthSecond)) {
//			
//			if (Integer.parseInt(gui.p1LengthSecond.getText()) > 59) {
//				try { 
//					gui.p1LengthMinute.setText(((Integer.parseInt(gui.p1LengthMinute.getText())) + (Integer.parseInt(gui.p1LengthSecond.getText()) / 60)) + "");
//				} catch (NumberFormatException e) { 
//					gui.p1LengthMinute.setText((Integer.parseInt(gui.p1LengthSecond.getText()) / 60) + ""); 
//				}
//				gui.p1LengthSecond.setText((Integer.parseInt(gui.p1LengthSecond.getText()) % 60) + "");
//			}
//		}
		
		if (arg0.getSource().equals(gui.p1Title)) {
			String unmodifiedTitle = gui.p1Title.getText();
			String[] unmodifiedTitleWords = unmodifiedTitle.split(" ");
			String modifiedTitle = "";
			
			for (int i = 0; i < unmodifiedTitleWords.length; i++) {
				if (unmodifiedTitleWords[i].length() > 2) {
					unmodifiedTitleWords[i] = ("" + unmodifiedTitleWords[i].charAt(0)).toUpperCase() 
							+ unmodifiedTitleWords[i].substring(1, unmodifiedTitleWords[i].length());
				}
			}
			
			for (int i = 0; i < unmodifiedTitleWords.length; i++) modifiedTitle += unmodifiedTitleWords[i] + " ";
			modifiedTitle = modifiedTitle.trim();
			gui.p1Title.setText(modifiedTitle);
			gui.p1Title.setToolTipText(gui.p1Title.getText());
		} else if (arg0.getSource().equals(gui.p1Size)) {
			gui.p1Size.setText(gui.p1Size.getText().replaceAll("[^0-9.]", ""));
		} else if (arg0.getSource().equals(gui.p1LengthMinute)) {
			try {
				if (Integer.parseInt(gui.p1LengthMinute.getText()) > 59) {
					try { 
						gui.p1LengthHour.setText(((Integer.parseInt(gui.p1LengthHour.getText())) + (Integer.parseInt(gui.p1LengthMinute.getText()) / 60)) + "");
					} catch (NumberFormatException e) { 
						gui.p1LengthHour.setText((Integer.parseInt(gui.p1LengthMinute.getText()) / 60) + ""); 
					}
					gui.p1LengthMinute.setText((Integer.parseInt(gui.p1LengthMinute.getText()) % 60) + "");
				}
			} catch(NumberFormatException ex) { }
		} else if (arg0.getSource().equals(gui.p1LengthSecond)) {
			try {
				if (Integer.parseInt(gui.p1LengthSecond.getText()) > 59) {
					try { 
						gui.p1LengthMinute.setText(((Integer.parseInt(gui.p1LengthMinute.getText())) + (Integer.parseInt(gui.p1LengthSecond.getText()) / 60)) + "");
					} catch (NumberFormatException e) { 
						gui.p1LengthMinute.setText((Integer.parseInt(gui.p1LengthSecond.getText()) / 60) + ""); 
					}
					gui.p1LengthSecond.setText((Integer.parseInt(gui.p1LengthSecond.getText()) % 60) + "");
				}
			} catch(NumberFormatException ex) { }
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		this.itemSelected = e.getItem().toString();
		this.sourceLocation = e.getSource().toString();
		this.sourceLocation = this.sourceLocation.substring(this.sourceLocation.indexOf('[')+2, this.sourceLocation.length()-1);
		this.sourceLocation = this.sourceLocation.split(",", 3)[0] + "," + this.sourceLocation.split(",", 3)[1];
		
		if(gui.debuggingMode) System.out.println(sourceLocation);
		
		if (this.sourceLocation.equals(gui.p1Quality.getX() + "," + gui.p1Quality.getY())) {
			this.sourceObject = gui.p1Quality;
		} else if (this.sourceLocation.equals(gui.p1SizeSelection.getX() + "," + gui.p1SizeSelection.getY())){
			this.sourceObject = gui.p1SizeSelection;
		} else if (this.sourceLocation.equals(gui.p1SeriesStatus.getX() + "," + gui.p1SeriesStatus.getY())){
			this.sourceObject = gui.p1SeriesStatus;
		} else if (this.sourceLocation.equals(gui.p1TitleStatus.getX() + "," + gui.p1TitleStatus.getY())){
			this.sourceObject = gui.p1TitleStatus;
		} else if (this.sourceLocation.equals(gui.p1DateMonth.getX() + "," + gui.p1DateMonth.getY())){
			this.sourceObject = gui.p1DateMonth;
		} else if (this.sourceLocation.equals(gui.p1DateDay.getX() + "," + gui.p1DateDay.getY())){
			this.sourceObject = gui.p1DateDay;
		} else if (this.sourceLocation.equals(gui.p1DateYear.getX() + "," + gui.p1DateYear.getY())){
			this.sourceObject = gui.p1DateYear;
		} else if (this.sourceLocation.equals(gui.p1ReleaseSeason.getX() + "," + gui.p1ReleaseSeason.getY())){
			this.sourceObject = gui.p1ReleaseSeason;
		} else if (this.sourceLocation.equals(gui.p1ReleaseYear.getX() + "," + gui.p1ReleaseYear.getY())){
			this.sourceObject = gui.p1ReleaseYear;
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

//		if (arg0.getActionCommand().equals("comboBoxChanged")) {
//			try {
//				if (this.sourceObject.equals(gui.p1DateMonth)) {
//					addDay(gui.p1DateDay, this.itemSelected.toString(), Integer.parseInt(gui.p1DateYear.getSelectedItem().toString()));
//				} else if (this.sourceObject.equals(gui.p1DateYear)) {
//					addDay(gui.p1DateDay, gui.p1DateMonth.getSelectedItem().toString(), Integer.parseInt(this.itemSelected));
//				}
//			} catch (Exception e) { }
//			
//		} else if (arg0.getActionCommand().equals("Preview Data")) {
//			previewData();
//		} else if (arg0.getActionCommand().equals("Reset Fields")) {
//			gui.resetFields();
//		} else if (arg0.getActionCommand().equals("Submit Data")) {
//			previewData();
//			
//			gui.setQuality(gui.DataSent[0]);
//			gui.setAnimeTitle(gui.DataSent[1]);
//			gui.setEpisodes(gui.DataSent[2]);
//			gui.setOVAs(gui.DataSent[3]);
//			gui.setSpecials(gui.DataSent[4]);
//			gui.setFileSize(gui.DataSent[5], gui.DataSent[6]);
//			gui.setSeriesStatus(gui.DataSent[7]);
//			gui.setTitleStatus(gui.DataSent[8]);
//			gui.setDateFinished(gui.DataSent[9], gui.DataSent[10], gui.DataSent[11]);
//			gui.setReleaseSeason(gui.DataSent[12], gui.DataSent[13]);
//			gui.setLength(gui.DataSent[14], gui.DataSent[15], gui.DataSent[16]);
//			
//			String toBeSentData = "insert into AnimeDatabase values('" +
//								  gui.getQuality() + "', '" + 
//								  gui.getAnimeTitle() + "', " + 
//								  gui.getEpisodes() + ", " + 
//								  gui.getOVAs() + ", " + 
//								  gui.getSpecials() + ", '" + 
//								  gui.getFileSize() + "', '" + 
//								  gui.getSeriesStatus() + "', '" + 
//								  gui.getTitleStatus() + "', '" + 
//								  gui.getDateFinished() + "', '" + 
//								  gui.getReleaseSeason() + "', '" + 
//								  gui.getLength() + "')";
//			
//			if (gui.debuggingMode) System.out.println(toBeSentData);
//			
//	//		JOptionPane.showMessageDialog(null, toBeSentData);
//			
//			int confirmAns = JOptionPane.showConfirmDialog(null, "Rin-sama,\n" +
//								"this would be the data that will be sent to your Anime Database.\n" +
//								"Would this be the correct values for it, please recheck?\n\n" + toBeSentData + "\n\n" +
//								"From : Maid-chan", "メイド�?�ゃん (Maid-chan)", JOptionPane.YES_NO_OPTION, 0, 
//								new ImageIcon(getClass().getResource("img/tray-128x83.png")));
//			
//			System.out.println(confirmAns);
//			
//			if (confirmAns == 0) { // YES
//				
//			} else { // NO
//				
//			}
//			
//	//		JOptionPane.showMessageDialog(null, "Rin-sama,\n" +
//	//				"this would be the data that will be sent to your Anime Database.\n" +
//	//				"Would this be the correct values for it, please recheck?\n\n" + toBeSentData + "\n\n" +
//	//				"From : Maid-chan", "メイド�?�ゃん (Maid-chan)", 0, new ImageIcon(getClass().getResource("img/tray-128x83.png"))); 
//		}
		
		if (arg0.getActionCommand().equals("comboBoxChanged")) {
			try {
				if (this.sourceObject.equals(gui.p1DateMonth)) {
					addDay(gui.p1DateDay, this.itemSelected.toString(), Integer.parseInt(gui.p1DateYear.getSelectedItem().toString()));
				} else if (this.sourceObject.equals(gui.p1DateYear)) {
					addDay(gui.p1DateDay, gui.p1DateMonth.getSelectedItem().toString(), Integer.parseInt(this.itemSelected));
				}
			} catch (Exception e) { }
			
		} else if (arg0.getSource().equals(gui.p1btnPreviewData)) {
			previewData();
		} else if (arg0.getSource().equals(gui.p1btnResetFields)) {
			gui.resetFields();
		} else if (arg0.getSource().equals(gui.p1btnSubmitData)) {
			previewData();
			
			gui.setQuality(gui.DataSent[0]);
			gui.setAnimeTitle(gui.DataSent[1]);
			gui.setEpisodes(gui.DataSent[2]);
			gui.setOVAs(gui.DataSent[3]);
			gui.setSpecials(gui.DataSent[4]);
			gui.setFileSize(gui.DataSent[5], gui.DataSent[6]);
			gui.setSeriesStatus(gui.DataSent[7]);
			gui.setTitleStatus(gui.DataSent[8]);
			gui.setDateFinished(gui.DataSent[9], gui.DataSent[10], gui.DataSent[11]);
			gui.setReleaseSeason(gui.DataSent[12], gui.DataSent[13]);
			gui.setLength(gui.DataSent[14], gui.DataSent[15], gui.DataSent[16]);
			
			String toBeSentData = "insert into AnimeDatabase values('" +
								  gui.getQuality() + "', '" + 
								  gui.getAnimeTitle() + "', " + 
								  gui.getEpisodes() + ", " + 
								  gui.getOVAs() + ", " + 
								  gui.getSpecials() + ", '" + 
								  gui.getFileSize() + "', '" + 
								  gui.getSeriesStatus() + "', '" + 
								  gui.getTitleStatus() + "', '" + 
								  gui.getDateFinished() + "', '" + 
								  gui.getReleaseSeason() + "', '" + 
								  gui.getLength() + "')";
			
			if (gui.debuggingMode) System.out.println(toBeSentData);
			
//			JOptionPane.showMessageDialog(null, toBeSentData);
			
			int confirmAns = JOptionPane.showConfirmDialog(null, "Rin-sama,\n" +
								"this would be the data that will be sent to your Anime Database.\n" +
								"Would this be the correct values for it, please recheck?\n\n" + toBeSentData + "\n\n" +
								"From : Maid-chan", "メイド�?�ゃん (Maid-chan)", JOptionPane.YES_NO_OPTION, 0, 
								new ImageIcon(getClass().getResource("img/tray-128x83.png")));
			
			System.out.println(confirmAns);
			
			if (confirmAns == 0) { // YES
				
			} else { // NO
				
			}
			
//			JOptionPane.showMessageDialog(null, "Rin-sama,\n" +
//					"this would be the data that will be sent to your Anime Database.\n" +
//					"Would this be the correct values for it, please recheck?\n\n" + toBeSentData + "\n\n" +
//					"From : Maid-chan", "メイド�?�ゃん (Maid-chan)", 0, new ImageIcon(getClass().getResource("img/tray-128x83.png"))); 
		}
		
	}
	
	private void previewData() {
		gui.p1Preview.setText("");
		if (gui.p1Title.getText().equals("")) gui.p1Title.setText(gui.DefaultValues[1]);
		if (gui.p1Episodes.getText().equals("")) gui.p1Episodes.setText(gui.DefaultValues[2]);
		if (gui.p1OVAs.getText().equals("")) gui.p1OVAs.setText(gui.DefaultValues[3]);
		if (gui.p1Specials.getText().equals("")) gui.p1Specials.setText(gui.DefaultValues[4]);
		if (gui.p1Size.getText().equals("")) gui.p1Size.setText(gui.DefaultValues[5]);
		if (gui.p1LengthHour.getText().equals("")) gui.p1LengthHour.setText(gui.DefaultValues[14]);
		if (gui.p1LengthMinute.getText().equals("")) gui.p1LengthMinute.setText(gui.DefaultValues[15]);
		if (gui.p1LengthSecond.getText().equals("")) gui.p1LengthSecond.setText(gui.DefaultValues[16]);
		
		gui.setDataSent(gui.p1Quality.getSelectedItem().toString(),
						gui.p1Title.getText(), 
						gui.p1Episodes.getText(), 
						gui.p1OVAs.getText(), 
						gui.p1Specials.getText(), 
						gui.p1Size.getText(), 
						gui.p1SizeSelection.getSelectedItem().toString(), 
						gui.p1SeriesStatus.getSelectedItem().toString(), 
						gui.p1TitleStatus.getSelectedItem().toString(), 
						gui.p1DateMonth.getSelectedItem().toString(), 
						gui.p1DateDay.getSelectedItem().toString(), 
						gui.p1DateYear.getSelectedItem().toString(), 
						gui.p1ReleaseSeason.getSelectedItem().toString(), 
						gui.p1ReleaseYear.getSelectedItem().toString(), 
						gui.p1LengthHour.getText(), 
						gui.p1LengthMinute.getText(), 
						gui.p1LengthSecond.getText());
		
		gui.setFinalizedDataSent();
		for (String s : gui.FinalizedDataSent) gui.p1Preview.setText(gui.p1Preview.getText() + s + "\n");
		gui.p1Preview.setText((gui.p1Preview.getText()).trim());
	}
	
	private void addDay(JComboBox<String> comboBoxDay, String Month, int Year) {

		int selectedDay = Integer.parseInt(comboBoxDay.getSelectedItem().toString());
		comboBoxDay.removeAllItems();
		
		switch (Month) {
			case "Jan":
			case "Mar":
			case "May":
			case "Jul":
			case "Aug":
			case "Oct":
			case "Dec":

				for (int i = 1; i <= 9; i++) comboBoxDay.addItem("0" + i);
				for (int i = 10; i <= 31 ; i++) comboBoxDay.addItem("" + i);
				break;

			case "Feb":
				if(Year % 4 == 0) {
					for (int i = 1; i <= 9; i++) comboBoxDay.addItem("0" + i);
					for (int i = 10; i <= 29; i++) comboBoxDay.addItem("" + i);	
					if (selectedDay > 29) selectedDay = 29;				
				} else {
					for (int i = 1; i <= 9; i++) comboBoxDay.addItem("0" + i);
					for (int i = 10; i <= 28; i++) comboBoxDay.addItem("" + i);
					if (selectedDay > 28) selectedDay = 28;
				}
				
				break;
				
			default :
				for (int i = 1; i <= 9; i++) comboBoxDay.addItem("0" + i);
				for (int i = 10; i <= 30; i++) comboBoxDay.addItem("" + i);
				if (selectedDay > 30) selectedDay = 30;
				break;
		}
		
		comboBoxDay.setSelectedItem("" + selectedDay);
	}

}
