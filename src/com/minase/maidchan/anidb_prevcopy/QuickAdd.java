package com.minase.maidchan.anidb_prevcopy;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;


public class QuickAdd extends JFrame {

	private static final long serialVersionUID = 5977872553474249302L;
	private boolean debuggingMode = false;
	
	private JLabel lblCopyright;
	
	private JPanel contentPane, 
				   pnlEpisodeNumbers,
				   pnlStatus, 
				   pnlLength;
	
	public JTextField txtTitle, 
					   txtEpisodes, 
					   txtOVAs, 
					   txtSpecials, 
					   txtFileSize, 
					   txtLengthHr, 
					   txtLengthMin, 
					   txtLengthSec;
	
	public JComboBox<String> cbQuality, 
							 cbFileType, 
							 cbSeriesStatus, 
							 cbTitleStatus, 
							 cbMonth, 
							 cbDay, 
							 cbYear, 
							 cbReleaseSeason, 
							 cbReleaseYear;
	
	private JButton btnReset,
					btnPreview,
					btnSubmit;
	
	Date date = new Date();
	DateFormat dateFormat;
	MinaseDocFilter docFilter = new MinaseDocFilter();

	QAddActionListener action;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuickAdd frame = new QuickAdd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public QuickAdd() {
		
		action = new QAddActionListener(this);
		
		setTitle("Anime Database -- Quick Add");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 230);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbQuality = new JComboBox<String>(new String[] {"FHD 1080p", "HD 720p", "HQ 480p", "LQ"});
		cbQuality.setBounds(10, 11, 90, 20);
		cbQuality.setSelectedIndex(1);
		contentPane.add(cbQuality);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(110, 11, 230, 20);
		contentPane.add(txtTitle);
		
		txtFileSize = new JTextField();
		txtFileSize.setHorizontalAlignment(SwingConstants.CENTER);
		txtFileSize.setBounds(190, 42, 80, 20);
		contentPane.add(txtFileSize);
		
		cbFileType = new JComboBox<String>(new String[] {"GB", "MB"});
		cbFileType.setBounds(280, 42, 60, 20);
		contentPane.add(cbFileType);
		
		pnlEpisodeNumbers = new JPanel();
		pnlEpisodeNumbers.setBounds(10, 42, 170, 20);
		pnlEpisodeNumbers.setLayout(new GridLayout(1, 0, 10, 0));
		contentPane.add(pnlEpisodeNumbers);
		
		txtEpisodes = new JTextField();
		txtEpisodes.setToolTipText("Episodes");
		txtEpisodes.setHorizontalAlignment(SwingConstants.CENTER);
		((PlainDocument) txtEpisodes.getDocument()).setDocumentFilter(docFilter);
		pnlEpisodeNumbers.add(txtEpisodes);
		
		txtOVAs = new JTextField();
		txtOVAs.setToolTipText("OVAs");
		txtOVAs.setHorizontalAlignment(SwingConstants.CENTER);
		((PlainDocument) txtOVAs.getDocument()).setDocumentFilter(docFilter);
		pnlEpisodeNumbers.add(txtOVAs);
		
		txtSpecials = new JTextField();
		txtSpecials.setToolTipText("Specials");
		txtSpecials.setHorizontalAlignment(SwingConstants.CENTER);
		((PlainDocument) txtSpecials.getDocument()).setDocumentFilter(docFilter);
		pnlEpisodeNumbers.add(txtSpecials);
		
		pnlStatus = new JPanel();
		pnlStatus.setBounds(10, 73, 333, 20);
		pnlStatus.setLayout(new GridLayout(1, 0, 10, 0));
		contentPane.add(pnlStatus);
		
		cbSeriesStatus = new JComboBox<String>(new String[] {"Finished", "Not the first season", "Not Finished"});
		cbSeriesStatus.setToolTipText("Series Status");
		pnlStatus.add(cbSeriesStatus);
		
		cbTitleStatus = new JComboBox<String>(new String[] {"Finished", "Not Finished"});
		cbTitleStatus.setToolTipText("Title Status");
		pnlStatus.add(cbTitleStatus);
		
		cbMonth = new JComboBox<String>(new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});
		cbMonth.setBounds(10, 104, 50, 20);
		cbMonth.setSelectedItem(getCurrentMonth());
		contentPane.add(cbMonth);
		
		cbDay = new JComboBox<String>();
		cbDay.setBounds(70, 104, 50, 20);
		addPreloadDay(cbDay, getCurrentMonth(), Integer.parseInt(getCurrentYear()));
		cbDay.setSelectedItem(getCurrentDay());
		contentPane.add(cbDay);
		
		cbYear = new JComboBox<String>(new String[] {"2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"});
		cbYear.setBounds(130, 104, 60, 20);
		cbYear.setSelectedItem(getCurrentYear());
		contentPane.add(cbYear);
		
		cbReleaseSeason = new JComboBox<String>(new String[] {"Winter", "Spring", "Summer", "Fall"});
		cbReleaseSeason.setBounds(200, 104, 70, 20);
		cbReleaseSeason.setSelectedItem(getCurrentSeason());
		contentPane.add(cbReleaseSeason);
		
		cbReleaseYear = new JComboBox<String>(new String[] {"1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"});
		cbReleaseYear.setBounds(280, 104, 60, 20);
		cbReleaseYear.setSelectedItem(getCurrentYear());
		contentPane.add(cbReleaseYear);
		
		pnlLength = new JPanel();
		pnlLength.setBounds(10, 135, 150, 20);
		pnlLength.setLayout(new GridLayout(0, 3, 10, 0));
		contentPane.add(pnlLength);
		
		txtLengthHr = new JTextField();
		txtLengthHr.setToolTipText("Length: Hours");
		txtLengthHr.setHorizontalAlignment(SwingConstants.CENTER);
		((PlainDocument) txtLengthHr.getDocument()).setDocumentFilter(docFilter);
		pnlLength.add(txtLengthHr);
		
		txtLengthMin = new JTextField();
		txtLengthMin.setToolTipText("Length: Minutes");
		txtLengthMin.setHorizontalAlignment(SwingConstants.CENTER);
		((PlainDocument) txtLengthMin.getDocument()).setDocumentFilter(docFilter);
		pnlLength.add(txtLengthMin);
		
		txtLengthSec = new JTextField();
		txtLengthSec.setToolTipText("Length: Seconds");
		txtLengthSec.setHorizontalAlignment(SwingConstants.CENTER);
		((PlainDocument) txtLengthSec.getDocument()).setDocumentFilter(docFilter);
		pnlLength.add(txtLengthSec);
		
		btnReset = new JButton("Reset");
		btnReset.setBounds(170, 135, 70, 20);
		contentPane.add(btnReset);
		
		btnPreview = new JButton("Preview");
		btnPreview.setBounds(250, 135, 90, 20);
		contentPane.add(btnPreview);
		
		btnSubmit = new JButton("Submit Data");
		btnSubmit.setBounds(10, 166, 150, 25);
		contentPane.add(btnSubmit);
		
		lblCopyright = new JLabel("\u00A9 2014 Minase Conglomerate");
		lblCopyright.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCopyright.setBounds(169, 171, 170, 20);
		contentPane.add(lblCopyright);
		
		
		/**
		 * ADDING LISTENERS
		 */
		txtTitle.addKeyListener(action);
		txtTitle.addFocusListener(action);
		txtFileSize.addFocusListener(action);
		cbMonth.addActionListener(action);
		cbYear.addActionListener(action);
		txtLengthHr.addFocusListener(action);
		txtLengthMin.addFocusListener(action);
		txtLengthSec.addFocusListener(action);
		btnReset.addActionListener(action);
		btnPreview.addActionListener(action);
		
	}
	
	private String getCurrentMonth() {
		dateFormat = new SimpleDateFormat("MMM");
		return dateFormat.format(date);
	}
	
	private String getCurrentDay() {
		dateFormat = new SimpleDateFormat("dd");
		return dateFormat.format(date);		
	}
	
	private String getCurrentYear() {
		dateFormat = new SimpleDateFormat("yyyy");
		return dateFormat.format(date);		
	}
	
	private String getCurrentSeason() {
		dateFormat = new SimpleDateFormat("MM");
		int MonthOfToday = Integer.parseInt(dateFormat.format(date));
		
		return (MonthOfToday <= 3) ? "Winter" : (MonthOfToday <= 6) ? "Spring" : (MonthOfToday <= 9) ? "Summer" : "Fall";	
	}
	
	private void addPreloadDay(JComboBox<String> comboBoxDay, String Month, int Year) {

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
				} else {
					for (int i = 1; i <= 9; i++) comboBoxDay.addItem("0" + i);
					for (int i = 10; i <= 28; i++) comboBoxDay.addItem("" + i);					
				}
				
				break;
				
			default :

				for (int i = 1; i <= 9; i++) comboBoxDay.addItem("0" + i);
				for (int i = 10; i <= 30; i++) comboBoxDay.addItem("" + i);
				break;
				
		}
	}
	
	private void resetFields() {
		cbQuality.setSelectedIndex(1);
		txtTitle.setText("");
		txtEpisodes.setText("");
		txtOVAs.setText("");
		txtSpecials.setText("");
		txtFileSize.setText("");
		cbFileType.setSelectedIndex(0);
		cbSeriesStatus.setSelectedIndex(0);
		cbTitleStatus.setSelectedIndex(0);
		cbMonth.setSelectedItem(getCurrentMonth());
		cbYear.setSelectedItem(getCurrentYear());
		cbDay.setSelectedItem(getCurrentDay());
		cbReleaseSeason.setSelectedItem(getCurrentSeason());
		cbReleaseYear.setSelectedItem(getCurrentYear());
		txtLengthHr.setText("");
		txtLengthMin.setText("");
		txtLengthSec.setText("");
	}
	
	@SuppressWarnings("unused")
	private void previewData() {
		if (txtTitle.getText().equals("")) txtTitle.setText("Null");
		if (txtEpisodes.getText().equals("")) txtEpisodes.setText("0");
		if (txtOVAs.getText().equals("")) txtOVAs.setText("0");
		if (txtSpecials.getText().equals("")) txtSpecials.setText("0");
		if (txtFileSize.getText().equals("")) txtFileSize.setText("00.00");
		if (txtLengthHr.getText().equals("")) txtLengthHr.setText("00");
		if (txtLengthMin.getText().equals("")) txtLengthMin.setText("00");
		if (txtLengthSec.getText().equals("")) txtLengthSec.setText("00");
	}
	
	private class QAddActionListener implements ActionListener, FocusListener, KeyListener {
		
		QuickAdd qa;

		@Override
		public void keyPressed(KeyEvent arg0) { }

		@Override
		public void keyReleased(KeyEvent arg0) {
			if (arg0.getSource().equals(qa.txtTitle)) {
				qa.txtTitle.setToolTipText(qa.txtTitle.getText());
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) { }

		@Override
		public void focusGained(FocusEvent arg0) { }

		@Override
		public void focusLost(FocusEvent arg0) {
			
			if (arg0.getSource().equals(qa.txtTitle)) {
				String unmodifiedTitle = qa.txtTitle.getText();
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
				qa.txtTitle.setText(modifiedTitle);
				qa.txtTitle.setToolTipText(qa.txtTitle.getText());
			} else if (arg0.getSource().equals(qa.txtFileSize)) {
				qa.txtFileSize.setText(qa.txtFileSize.getText().replaceAll("[^0-9.]", ""));
			} else if (arg0.getSource().equals(qa.txtLengthHr)) {
				try {
					if (Integer.parseInt(qa.txtLengthHr.getText()) > 10) {
						qa.txtLengthHr.setText("0" + qa.txtLengthHr.getText());
					}
				} catch (NumberFormatException e) { }
			} else if (arg0.getSource().equals(qa.txtLengthMin)) {
				
				try {
					if (Integer.parseInt(qa.txtLengthMin.getText()) > 59) {
						try { 
							qa.txtLengthHr.setText(((Integer.parseInt(qa.txtLengthHr.getText())) + (Integer.parseInt(qa.txtLengthMin.getText()) / 60)) + "");
						} catch (NumberFormatException e) { 
							qa.txtLengthHr.setText((Integer.parseInt(qa.txtLengthMin.getText()) / 60) + ""); 
						}
						qa.txtLengthMin.setText((Integer.parseInt(qa.txtLengthMin.getText()) % 60) + "");
					}
					
					if (Integer.parseInt(qa.txtLengthMin.getText()) > 10) {
						qa.txtLengthMin.setText("0" + qa.txtLengthMin.getText());
					}
				} catch(NumberFormatException ex) { }
				
			} else if (arg0.getSource().equals(qa.txtLengthSec)) {
				
				try {
					if (Integer.parseInt(qa.txtLengthSec.getText()) > 59) {
						try { 
							qa.txtLengthMin.setText(((Integer.parseInt(qa.txtLengthMin.getText())) + (Integer.parseInt(qa.txtLengthSec.getText()) / 60)) + "");
						} catch (NumberFormatException e) { 
							qa.txtLengthMin.setText((Integer.parseInt(qa.txtLengthSec.getText()) / 60) + ""); 
						}
						qa.txtLengthSec.setText((Integer.parseInt(qa.txtLengthSec.getText()) % 60) + "");
					}
					
					if (Integer.parseInt(qa.txtLengthSec.getText()) > 10) {
						qa.txtLengthSec.setText("0" + qa.txtLengthSec.getText());
					}
				} catch(NumberFormatException ex) { }
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (qa.debuggingMode) System.out.println(arg0.getActionCommand() + "\n" + arg0.getSource());
			if (arg0.getActionCommand().equals("comboBoxChanged")) {
				try {
					if (arg0.getSource().equals(qa.cbMonth)) {
						addDay(qa.cbDay, qa.cbMonth.getSelectedItem().toString(), Integer.parseInt(qa.cbYear.getSelectedItem().toString()));
					} else if (arg0.getSource().equals(qa.cbYear)) {
						addDay(qa.cbDay, qa.cbMonth.getSelectedItem().toString(), Integer.parseInt(qa.cbYear.getSelectedItem().toString()));
					}
				} catch (Exception e) { }
			} else if (arg0.getSource().equals(qa.btnReset)) {
				if (JOptionPane.showConfirmDialog(null, "\nHey!\nI will be resetting the fields to their default values." +
						"\nAre you sure of this?\n\nFrom: Maid-chan\n\n", 
						"メイド�?�ゃん (Maid-chan)", JOptionPane.OK_CANCEL_OPTION, 0, 
						new ImageIcon(getClass().getResource("img/tray-128x83.png"))) == 0) {

					qa.resetFields();					
				}
				
			} else if (arg0.getSource().equals(qa.btnPreview)) {
				//TODO PREVIEW
//				JOptionPane.showMessageDialog(null, arg1, arg2, arg3, arg4)
			}
			
		}
		
		public QAddActionListener(QuickAdd qa) {
			this.qa = qa;
		}
		
		public void addDay(JComboBox<String> comboBoxDay, String Month, int Year) {

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
		
		@SuppressWarnings("unused")
		private void limitTextLength(JTextField textfield) {
			// TODO limitTextLength
		}
	}
}
