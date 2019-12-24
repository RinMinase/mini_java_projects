package com.minase.maidchan.anidb_prevcopy;

import java.awt.Color;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.PlainDocument;

import com.sun.awt.AWTUtilities;

public class MainGUI extends JFrame {
	
	/**
	 * DATA TYPES for SQL
	 * 
	 * ENUM('FHD 1080p', 'HD 720p', 'HQ 480p', 'LQ') Quality
	 * TEXT Title
	 * INT Episodes
	 * TINYINT OVAs //OR SMALLINT
	 * TINYINT Episodes // OR SMALLINT
	 * VARCHAR(9) FileSize
	 * ENUM('Finished', 'Not the First Season', 'Not Finished') SeasonStatus
	 * ENUM('Finished', 'Not Finished') TitleStatus
	 * DATE('mmm dd yyyy') DateFinished
	 * VARCHAR(11) ReleaseDate
	 * // ENUM('Winter', 'Spring', 'Summer', 'Fall') ReleaseSeason
	 * // DATE('yyyy') ReleaseYear
	 * TIME('hh mm ss') Length
	 * 
	 */

	private static final long serialVersionUID = 7190490125365529134L;
	private boolean tabbedDialog = true;
	private boolean adminMode = true;
	
	public boolean debuggingMode = false;
	public boolean showAbtDlg = false;
	
	/**
	 * PASSING TO DATABASE 
	 */
	private String Quality;
	private String AnimeTitle;
	private int Episodes;
	private int OVAs;
	private int Specials;
	private String FileSize;
	private String SeriesStatus;
	private String TitleStatus;
	private String DateFinished;
//	private DateFormat DateFinishedFormat = new SimpleDateFormat("MM dd, yyyy");
	private String ReleaseSeason;
	private String Length;
	
	public String[] paramsAvailable = {"-tabdlg",
									   "-btndlg",
									   "-debug",
									   "-abtdlg"};
	
	public int[] Year = {2011,
						 2012,
						 2013,
						 2014,
						 2015,
						 2016,
						 2017,
						 2018,
						 2019,
						 2020};
	
	public int[] ReleaseYear = {1998,
								1999,
								2000,
								2001,
								2002,
								2003,
								2004,
								2005,
								2006,
								2007,
								2008,
								2009,
								2010,
								2011,
								2012,
								2013,
								2014,
								2015,
								2016,
								2017,
								2018,
								2019,
								2020};


	public String[] SizeTypeItems = {"GB",
									 "MB"};
	
	public String[] TitleStatusItems = {"Finished",
								   		"Not Finished"};
	
	public String[] QualityItems = {"FHD 1080p",
							   		"HD 720p",
							   		"HQ 480p",
							   		"LQ"};

	public String[] ReleaseSeasonItems = {"Winter",
									   	  "Spring",
									   	  "Summer",
									   	  "Fall"};
	
	public String[] SeriesStatusItems = {"Finished", 
										 "Not the first season", 
										 "Not Finished"};
	
	public String[] Month = {"Jan",	
						  	 "Feb", 
						  	 "Mar", 
						  	 "Apr", 
						  	 "May", 
						  	 "Jun",
						  	 "Jul", 
						  	 "Aug", 
						  	 "Sep", 
						  	 "Oct", 
						  	 "Nov", 
							 "Dec"};

	
	public String[] DefaultValues = {"HD 720p",
									 "Null",
									 "12",
									 "0",
									 "0",
									 "0.00",
									 "GB",
									 "Finished",
									 "Finished",
									 "Jan",
									 "01",
									 "2014",
									 "Winter",
									 "2014",
									 "00",
									 "00",
									 "00"};
	
	public String[] DataSent;
	public String[] FinalizedDataSent;
	
	//JComponents
	JLabel bgBubble, bgMaid;
	JTabbedPane tabPane;
	JPanel pnlNewContent,
		   pnlAll,
		   pnlFHD, 
		   pnlHD, 
		   pnlHQ, 
		   pnlLQ;
	
	JButton btnNewContent,
			btnAll,
			btnFHD,
			btnHD,
			btnHQ,
			btnLQ;
//			btnChangeViewType;
	
	//Components on Panel pnlNewContent
	JComboBox<String> p1Quality, 
					  p1SizeSelection, 
					  p1SeriesStatus, 
					  p1TitleStatus, 
					  p1DateMonth, 
					  p1DateDay, 
					  p1DateYear, 
					  p1ReleaseSeason, 
					  p1ReleaseYear;
	
	JTextField p1Title, 
			   p1Episodes, 
			   p1OVAs, 
			   p1Specials, 
			   p1Size,
			   p1LengthHour,
			   p1LengthMinute,
			   p1LengthSecond;
	
	JLabel p1lblQuality,
		   p1lblTitle,
		   p1lblEpisodes,
		   p1lblOVAs,
		   p1lblSpecials,
		   p1lblSize,
		   p1lblSeriesStatus,
		   p1lblTitleStatus,
		   p1lblDate,
		   p1lblRelease,
		   p1lblLengthGeneral,
		   p1lblLengthHour,
		   p1lblLengthMinute,
		   p1lblLengthSecond,
		   p1lblPreview;
	
	JButton p1btnPreviewData,
			p1btnSubmitData,
			p1btnResetFields;
	
	JTextArea p1Preview;
	
	JScrollPane p1PreviewScroll;

	//Components on pnlAll
	JPanel p2HeaderPanel;
	
	JButton p2Update,
			p2Edit,
			p2Delete;
	
	JTextArea p2TextArea;
	
	JScrollPane p2TextAreaScroll;
	
	//Components on pnlFHD
	JPanel p3HeaderPanel;
	
	JButton p3Update,
			p3Edit,
			p3Delete;
	
	JTextArea p3TextArea;
	
	JScrollPane p3TextAreaScroll;
	
	JTable p3DBTable;
	
	//Components on pnlHD
	JPanel p4HeaderPanel;
	
	JButton p4Update,
			p4Edit,
			p4Delete;
	
	JTextArea p4TextArea;
	
	JScrollPane p4TextAreaScroll;
	
	//Components on pnlHQ
	JPanel p5HeaderPanel;
	
	JButton p5Update,
			p5Edit,
			p5Delete;
	
	JTextArea p5TextArea;
	
	JScrollPane p5TextAreaScroll;
		
	//Other Classes Used
	Date date = new Date();
	DateFormat dateFormat;
	MinaseDocFilter docFilter = new MinaseDocFilter();
	DBUtil dbu;
	
	String todaysMonth, todaysDay, todaysYear;
	
	Actions action;
	
	public static void main(String[] args) {
		MainGUI gui = new MainGUI();
		gui.initialize();
		
//		ShowStats ss = new ShowStats();
//		
//		QuickAdd qa = new QuickAdd();
		
	}
	
	public void initialize() {

		if (adminMode) {
			String paramsAllowed = "";
			
			for (int i = 0; i < paramsAvailable.length; i++) paramsAllowed += ((i + 1) % 3 == 0) ? paramsAvailable[i] + "\n"  : paramsAvailable[i] + "     ";
			paramsAllowed = paramsAllowed.trim();

			String[] initialParams;
			String tempParams = JOptionPane.showInputDialog(null, "Parameters Avalable (separate by spaces) :\n" + paramsAllowed, "Maid-chan - Anime Database", 1);
			if (debuggingMode) System.out.println(tempParams);
			
			if (tempParams != null) {
				initialParams = tempParams.split(" ");
				checkParams(initialParams);				
			}	
		}
		
		action = new Actions(this);
		
		setLayout(null);
		setSize(700, 650);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Maid-chan - Anime Database");
		
		AWTUtilities.setWindowOpaque(this, false);
		
		this.DefaultValues[9] = getCurrentMonth();
		this.DefaultValues[10] = getCurrentDay();
		this.DefaultValues[11] = getCurrentYear();
		this.DefaultValues[12] = getCurrentSeason();
		this.DefaultValues[13] = getCurrentYear();
		
		//bgBubble = new JLabel(new ImageIcon("./img/anidb/bubble.png"));		
		bgBubble = new JLabel(new ImageIcon(getClass().getResource("img/bubble.png")));
		bgBubble.setSize(680, 626);
		add(bgBubble);
		
		setPanelNewContent();
		setPanelAll();
		setPanelFHD();
		setPanelHD();
		setPanelHQ();
		setPanelLQ();
		
//		btnChangeViewType = new JButton("Change View Type");
//		btnChangeViewType.setBounds(240, 567 , 200, 30);
//		
//		btnChangeViewType.addActionListener(action);
//		
//		bgBubble.add(btnChangeViewType);
		setViewType();		
		
		setVisible(true);
	}
	
	private String getCurrentMonth() {
		dateFormat = new SimpleDateFormat("MMM");
		return dateFormat.format(date);
	}
	
	private String getCurrentDay() {
		dateFormat = new SimpleDateFormat("d");
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
	
	public void addPreloadDay(JComboBox<String> comboBoxDay, String Month, int Year) {

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
	
	private void setPanelNewContent() {
		
		pnlNewContent = new JPanel();
		pnlNewContent.setLayout(null);
		pnlNewContent.setOpaque(false);
		
		p1lblQuality = new JLabel("Anime Quality :");
		p1lblQuality.setBounds(10, 10, 100, 25);
		
		p1lblTitle = new JLabel("Anime Title :");
		p1lblTitle.setBounds(10, 40, 100, 25);
		
		p1lblEpisodes = new JLabel("Episodes :");
		p1lblEpisodes.setBounds(10, 70, 100, 25);
		
		p1lblOVAs = new JLabel("OVAs :");
		p1lblOVAs.setBounds(10, 100, 100, 25);
		
		p1lblSpecials = new JLabel("Specials :");
		p1lblSpecials.setBounds(10, 130, 100, 25);
		
		p1lblSize = new JLabel("File Size :");
		p1lblSize.setBounds(10, 160, 100, 25);
		
		p1lblSeriesStatus = new JLabel("Series Status :");
		p1lblSeriesStatus.setBounds(10, 190, 100, 25);
		
		p1lblTitleStatus = new JLabel("Title Status :");
		p1lblTitleStatus.setBounds(10, 220, 100, 25);
		
		p1lblDate = new JLabel("Date Finished :");
		p1lblDate.setBounds(10, 250, 100, 25);
		
		p1lblRelease = new JLabel("Season Released :");
		p1lblRelease.setBounds(10, 280, 110, 25);
		
		p1lblLengthGeneral = new JLabel("Anime Length :");
		p1lblLengthGeneral.setBounds(10, 310, 100, 25);
		
		p1lblLengthHour = new JLabel("Hours :");
		p1lblLengthHour.setBounds(10, 340, 60, 25);
		
		p1lblLengthMinute = new JLabel("Minutes :");
		p1lblLengthMinute.setBounds(120, 340, 60, 25);
		
		p1lblLengthSecond = new JLabel("Seconds :");
		p1lblLengthSecond.setBounds(230, 340, 60, 25);

		p1lblPreview = new JLabel("Preview");
		p1lblPreview.setBounds(350, 10, 150, 20);
		
		p1Quality = new JComboBox<String>();
		p1Quality.setBounds(130, 10, 100, 25);
		for (String item : QualityItems) p1Quality.addItem(item);
		p1Quality.setSelectedIndex(1);
		
		p1Title = new JTextField();
		p1Title.setBounds(130, 40, 200, 25);
		p1Title.setToolTipText(p1Title.getText());
		
		p1Episodes = new JTextField();
		p1Episodes.setBounds(130, 70, 50, 25);
		p1Episodes.setHorizontalAlignment(JTextField.CENTER);
		((PlainDocument) p1Episodes.getDocument()).setDocumentFilter(docFilter);
		
		p1OVAs = new JTextField();
		p1OVAs.setBounds(130, 100, 50, 25);
		p1OVAs.setHorizontalAlignment(JTextField.CENTER);
		((PlainDocument) p1OVAs.getDocument()).setDocumentFilter(docFilter);
		
		p1Specials = new JTextField();
		p1Specials.setBounds(130, 130, 50, 25);
		p1Specials.setHorizontalAlignment(JTextField.CENTER);
		((PlainDocument) p1Specials.getDocument()).setDocumentFilter(docFilter);
		
		p1Size = new JTextField();
		p1Size.setBounds(130, 160, 85, 25);
		p1Size.setHorizontalAlignment(JTextField.CENTER);
		
		p1SizeSelection = new JComboBox<String>();
		p1SizeSelection.setBounds(220, 160, 70, 25);
		for (String item : SizeTypeItems) p1SizeSelection.addItem(item);
		p1SizeSelection.setSelectedIndex(0);
		
		p1SeriesStatus = new JComboBox<String>();
		p1SeriesStatus.setBounds(130, 190, 200, 25);
		for (String item : SeriesStatusItems) p1SeriesStatus.addItem(item);
		p1SeriesStatus.setSelectedIndex(0);
		
		p1TitleStatus = new JComboBox<String>();
		p1TitleStatus.setBounds(130, 220, 200, 25);
		p1TitleStatus.addItem("Finished");
		p1TitleStatus.addItem("Not Finished");
		p1TitleStatus.setSelectedIndex(0);
		
		p1DateMonth = new JComboBox<String>();
		p1DateMonth.setBounds(130, 250, 55, 25);
		for (String item : Month) p1DateMonth.addItem(item);
		p1DateMonth.setSelectedItem(getCurrentMonth());
		
		p1DateDay = new JComboBox<String>();
		p1DateDay.setBounds(190, 250, 55, 25);
		addPreloadDay(p1DateDay, p1DateMonth.getSelectedItem().toString(), Integer.parseInt(getCurrentYear()));
		p1DateDay.setSelectedItem(getCurrentDay());
		
		p1DateYear = new JComboBox<String>();
		p1DateYear.setBounds(250, 250, 80, 25);
		for (int item : Year) p1DateYear.addItem("" + item);
		p1DateYear.setSelectedItem(getCurrentYear());
		
		p1ReleaseSeason = new JComboBox<String>();
		p1ReleaseSeason.setBounds(130, 280, 115, 25);
		for (String item : ReleaseSeasonItems) p1ReleaseSeason.addItem(item);
		p1ReleaseSeason.setSelectedItem(getCurrentSeason());
		
		p1ReleaseYear = new JComboBox<String>();
		p1ReleaseYear.setBounds(250, 280, 80, 25);
		for (int item : ReleaseYear) p1ReleaseYear.addItem("" + item);
		p1ReleaseYear.setSelectedItem(getCurrentYear());

		p1LengthHour = new JTextField();
		p1LengthHour.setBounds(70, 340, 40, 25);
		p1LengthHour.setHorizontalAlignment(JTextField.CENTER);
		((PlainDocument) p1LengthHour.getDocument()).setDocumentFilter(docFilter);

		p1LengthMinute = new JTextField();
		p1LengthMinute.setBounds(180, 340, 40, 25);
		p1LengthMinute.setHorizontalAlignment(JTextField.CENTER);
		((PlainDocument) p1LengthMinute.getDocument()).setDocumentFilter(docFilter);

		p1LengthSecond = new JTextField();
		p1LengthSecond.setBounds(290, 340, 40, 25);
		p1LengthSecond.setHorizontalAlignment(JTextField.CENTER);
		((PlainDocument) p1LengthSecond.getDocument()).setDocumentFilter(docFilter);
		
		p1Preview = new JTextArea();
		p1Preview.setBounds(350, 30, 230, 430);
		p1Preview.setEditable(false);
		
		p1PreviewScroll = new JScrollPane(p1Preview);
		p1PreviewScroll.setBounds(350, 30, 230, 430);
		p1PreviewScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		p1PreviewScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		p1btnResetFields = new JButton("Reset Fields");
		p1btnResetFields.setBounds(20, 390, 150, 30);
		
		p1btnPreviewData = new JButton("Preview Data");
		p1btnPreviewData.setBounds(180, 390, 150, 30);
		
		p1btnSubmitData = new JButton("Submit Data");
		p1btnSubmitData.setBounds(100, 430, 150, 30);
		
		/**
		 * ACTION LISTENERS, 
		 * KEYLISTENERS, 
		 * FOCUSLISTENERS, 
		 * ITEMLISTENERS, 
		 * MOUSELISTENERS
		 */

		p1Title.addKeyListener(action);
		p1Title.addFocusListener(action);
		
		p1Size.addFocusListener(action);
		
		p1DateMonth.addActionListener(action);
		p1DateMonth.addItemListener(action);
		
		p1DateYear.addActionListener(action);
		p1DateYear.addItemListener(action);
		
		p1LengthMinute.addFocusListener(action);
		p1LengthSecond.addFocusListener(action);

		p1btnResetFields.addActionListener(action);
		p1btnPreviewData.addActionListener(action);
		p1btnSubmitData.addActionListener(action);
		
		/**
		 * ADDING CONTENT TO PANE
		 */
		
		pnlNewContent.add(p1lblQuality);
		pnlNewContent.add(p1Quality);
		
		pnlNewContent.add(p1lblTitle);
		pnlNewContent.add(p1Title);
		
		pnlNewContent.add(p1lblEpisodes);
		pnlNewContent.add(p1Episodes);
		
		pnlNewContent.add(p1lblOVAs);
		pnlNewContent.add(p1OVAs);
		
		pnlNewContent.add(p1lblSpecials);
		pnlNewContent.add(p1Specials);
		
		pnlNewContent.add(p1lblSize);
		pnlNewContent.add(p1Size);
		pnlNewContent.add(p1SizeSelection);
		
		pnlNewContent.add(p1lblSeriesStatus);
		pnlNewContent.add(p1SeriesStatus);
		
		pnlNewContent.add(p1lblTitleStatus);
		pnlNewContent.add(p1TitleStatus);
		
		pnlNewContent.add(p1lblDate);
		pnlNewContent.add(p1DateMonth);
		pnlNewContent.add(p1DateDay);
		pnlNewContent.add(p1DateYear);
		
		pnlNewContent.add(p1lblRelease);
		pnlNewContent.add(p1ReleaseSeason);
		pnlNewContent.add(p1ReleaseYear);
		
		pnlNewContent.add(p1lblLengthGeneral);
		
		pnlNewContent.add(p1lblLengthHour);
		pnlNewContent.add(p1LengthHour);
		
		pnlNewContent.add(p1lblLengthMinute);
		pnlNewContent.add(p1LengthMinute);
		
		pnlNewContent.add(p1lblLengthSecond);
		pnlNewContent.add(p1LengthSecond);
		
		pnlNewContent.add(p1btnResetFields);
		pnlNewContent.add(p1btnPreviewData);
		pnlNewContent.add(p1btnSubmitData);
		
//		pnlNewContent.add(p1lblPreview);
		pnlNewContent.add(p1PreviewScroll);
		
	}
	
	private void setPanelAll() {
		
		pnlAll = new JPanel();
		pnlAll.setLayout(null);
		pnlAll.setOpaque(false);
		
		p2HeaderPanel = new JPanel();
		p2HeaderPanel.setLayout(new GridLayout(1, 3, 20, 0));
		p2HeaderPanel.setBounds(10, 10, 575, 30);
		p2HeaderPanel.setOpaque(false);
		
		p2Update = new JButton("Update");
		p2Edit = new JButton("Edit");
		p2Delete = new JButton("Delete");

		p2HeaderPanel.add(p2Update);
		p2HeaderPanel.add(p2Edit);
		p2HeaderPanel.add(p2Delete);
		
		p2TextArea = new JTextArea();
		p2TextArea.setBounds(10, 45, 575, 425);
		p2TextArea.setEditable(false);
		p2TextArea.setText("Quality\t" +
						   "Anime Title\t\t\t" +
						   "Episodes\t" +
						   "OVAs\t" +
						   "Specials\t" +
						   "File Size\t" +
						   "Season Status\t\t" +
						   "Title Status\t\t" +
						   "Date Finished\t\t" +
						   "Release Season\t" +
						   "Length\t\n");
		
		p2TextAreaScroll = new JScrollPane(p2TextArea);
		p2TextAreaScroll.setBounds(10, 45, 575, 425);
		p2TextAreaScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		p2TextAreaScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		

		dbu = new DBUtil();
		
		String xQual, xTitle, xSize, xSeriesS, xTitleS, xDate, xRelSeason, xLength;
		int xEps, xOVA, xSpc;
		
		try {
			ResultSet rs = dbu.retrieve("select * from AnimeDatabase");
			while(rs.next()) {
				xQual = rs.getString("Quality");
				xTitle = rs.getString("AnimeTitle");
				xEps = rs.getInt("Episodes");
				xOVA = rs.getInt("OVAs");
				xSpc = rs.getInt("Specials");
				xSize = rs.getString("FileSize");
				xSeriesS = rs.getString("SeasonStatus");
				xTitleS = rs.getString("TitleStatus");
				xDate = rs.getString("DateFinished");
				xRelSeason = rs.getString("ReleaseSeason");
				xLength = rs.getString("Length");
				
				String temp = xQual + "\t" +
							  xTitle + "\t\t" +
							  xEps + "\t" +
							  xOVA + "\t" +
							  xSpc + "\t" +
							  xSize + "\t" +
							  xSeriesS + "\t\t" +
							  xTitleS + "\t\t" +
							  xDate + "\t\t" +
							  xRelSeason + "\t\t" +
							  xLength + "\t";

				p2TextArea.setText(p2TextArea.getText() + "\n" + temp);
			}
		} catch (SQLException e) { e.getMessage(); }
		
		pnlAll.add(p2HeaderPanel);
		pnlAll.add(p2TextAreaScroll);
		
	}

	private void setPanelFHD() {
		
		pnlFHD = new JPanel();
		pnlFHD.setLayout(null);
		pnlFHD.setOpaque(false);
		
		p3HeaderPanel = new JPanel();
		p3HeaderPanel.setLayout(new GridLayout(1, 3, 20, 0));
		p3HeaderPanel.setBounds(10, 10, 575, 30);
		p3HeaderPanel.setOpaque(false);
		
		p3Update = new JButton("Update");
		p3Edit = new JButton("Edit");
		p3Delete = new JButton("Delete");

		p3HeaderPanel.add(p3Update);
		p3HeaderPanel.add(p3Edit);
		p3HeaderPanel.add(p3Delete);
		
//		p3DBTable = new JTable();
//		p3DBTable.setBounds(10, 45, 575, 425);
//		p3DBTable.addColumn(new TableColumn());
		
		p3TextArea = new JTextArea();
		p3TextArea.setBounds(10, 45, 575, 425);
		p3TextArea.setEditable(false);
		p3TextArea.setText("Quality\t" +
						   "Anime Title\t\t\t" +
						   "Episodes\t" +
						   "OVAs\t" +
						   "Specials\t" +
						   "File Size\t" +
						   "Season Status\t\t" +
						   "Title Status\t\t" +
						   "Date Finished\t\t" +
						   "Release Season\t" +
						   "Length\t\n");
		
		p3TextAreaScroll = new JScrollPane(p3TextArea);
		p3TextAreaScroll.setBounds(10, 45, 575, 425);
		p3TextAreaScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		p3TextAreaScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		

		dbu = new DBUtil();
		
		String xQual, xTitle, xSize, xSeriesS, xTitleS, xDate, xRelSeason, xLength;
		int xEps, xOVA, xSpc;
		
		try {
			ResultSet rs = dbu.retrieve("select * from AnimeDatabase where Quality='FHD 1080p'");
			while(rs.next()) {
				xQual = rs.getString("Quality");
				xTitle = rs.getString("AnimeTitle");
				xEps = rs.getInt("Episodes");
				xOVA = rs.getInt("OVAs");
				xSpc = rs.getInt("Specials");
				xSize = rs.getString("FileSize");
				xSeriesS = rs.getString("SeasonStatus");
				xTitleS = rs.getString("TitleStatus");
				xDate = rs.getString("DateFinished");
				xRelSeason = rs.getString("ReleaseSeason");
				xLength = rs.getString("Length");
				
				String temp = xQual + "\t" +
							  xTitle + "\t\t" +
							  xEps + "\t" +
							  xOVA + "\t" +
							  xSpc + "\t" +
							  xSize + "\t" +
							  xSeriesS + "\t\t" +
							  xTitleS + "\t\t" +
							  xDate + "\t\t" +
							  xRelSeason + "\t\t" +
							  xLength + "\t";

				p3TextArea.setText(p3TextArea.getText() + "\n" + temp);
			}
		} catch (SQLException e) { e.getMessage(); }
		
		pnlFHD.add(p3HeaderPanel);
//		pnlFHD.add(p3DBTable);
		pnlFHD.add(p3TextAreaScroll);
	}

	private void setPanelHD() {
		pnlHD = new JPanel();
		pnlHD.setLayout(null);
		pnlHD.setOpaque(false);
		
		p4HeaderPanel = new JPanel();
		p4HeaderPanel.setLayout(new GridLayout(1, 3, 20, 0));
		p4HeaderPanel.setBounds(10, 10, 575, 30);
		p4HeaderPanel.setOpaque(false);
		
		p4Update = new JButton("Update");
		p4Edit = new JButton("Edit");
		p4Delete = new JButton("Delete");

		p4HeaderPanel.add(p4Update);
		p4HeaderPanel.add(p4Edit);
		p4HeaderPanel.add(p4Delete);
		
		p4TextArea = new JTextArea();
		p4TextArea.setBounds(10, 45, 575, 425);
		p4TextArea.setEditable(false);
		p4TextArea.setText("Quality\t" +
						   "Anime Title\t\t\t" +
						   "Episodes\t" +
						   "OVAs\t" +
						   "Specials\t" +
						   "File Size\t" +
						   "Season Status\t\t" +
						   "Title Status\t\t" +
						   "Date Finished\t\t" +
						   "Release Season\t" +
						   "Length\t\n");
		
		p4TextAreaScroll = new JScrollPane(p4TextArea);
		p4TextAreaScroll.setBounds(10, 45, 575, 425);
		p4TextAreaScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		p4TextAreaScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		

		dbu = new DBUtil();
		
		String xQual, xTitle, xSize, xSeriesS, xTitleS, xDate, xRelSeason, xLength;
		int xEps, xOVA, xSpc;
		
		try {
			ResultSet rs = dbu.retrieve("select * from AnimeDatabase where Quality='HD 720p'");
			while(rs.next()) {
				xQual = rs.getString("Quality");
				xTitle = rs.getString("AnimeTitle");
				xEps = rs.getInt("Episodes");
				xOVA = rs.getInt("OVAs");
				xSpc = rs.getInt("Specials");
				xSize = rs.getString("FileSize");
				xSeriesS = rs.getString("SeasonStatus");
				xTitleS = rs.getString("TitleStatus");
				xDate = rs.getString("DateFinished");
				xRelSeason = rs.getString("ReleaseSeason");
				xLength = rs.getString("Length");
				
				String temp = xQual + "\t" +
							  xTitle + "\t\t" +
							  xEps + "\t" +
							  xOVA + "\t" +
							  xSpc + "\t" +
							  xSize + "\t" +
							  xSeriesS + "\t\t" +
							  xTitleS + "\t\t" +
							  xDate + "\t\t" +
							  xRelSeason + "\t\t" +
							  xLength + "\t";

				p4TextArea.setText(p4TextArea.getText() + "\n" + temp);
			}
		} catch (SQLException e) { e.getMessage(); }
		
		pnlHD.add(p4HeaderPanel);
//		pnlHD.add(p4DBTable);
		pnlHD.add(p4TextAreaScroll);
	}

	private void setPanelHQ() {
		pnlHQ = new JPanel();
		pnlHQ.setLayout(null);
		pnlHQ.setOpaque(false);
		
		p5HeaderPanel = new JPanel();
		p5HeaderPanel.setLayout(new GridLayout(1, 3, 20, 0));
		p5HeaderPanel.setBounds(10, 10, 575, 30);
		p5HeaderPanel.setOpaque(false);
		
		p5Update = new JButton("Update");
		p5Edit = new JButton("Edit");
		p5Delete = new JButton("Delete");

		p5HeaderPanel.add(p5Update);
		p5HeaderPanel.add(p5Edit);
		p5HeaderPanel.add(p5Delete);
		
		p5TextArea = new JTextArea();
		p5TextArea.setBounds(10, 45, 575, 425);
		p5TextArea.setEditable(false);
		p5TextArea.setText("Quality\t" +
						   "Anime Title\t\t\t" +
						   "Episodes\t" +
						   "OVAs\t" +
						   "Specials\t" +
						   "File Size\t" +
						   "Season Status\t\t" +
						   "Title Status\t\t" +
						   "Date Finished\t\t" +
						   "Release Season\t" +
						   "Length\t\n");
		
		p5TextAreaScroll = new JScrollPane(p5TextArea);
		p5TextAreaScroll.setBounds(10, 45, 575, 425);
		p5TextAreaScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		p5TextAreaScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		

		dbu = new DBUtil();
		
		String xQual, xTitle, xSize, xSeriesS, xTitleS, xDate, xRelSeason, xLength;
		int xEps, xOVA, xSpc;
		
		try {
			ResultSet rs = dbu.retrieve("select * from AnimeDatabase where Quality='HQ 480p'");
			while(rs.next()) {
				xQual = rs.getString("Quality");
				xTitle = rs.getString("AnimeTitle");
				xEps = rs.getInt("Episodes");
				xOVA = rs.getInt("OVAs");
				xSpc = rs.getInt("Specials");
				xSize = rs.getString("FileSize");
				xSeriesS = rs.getString("SeasonStatus");
				xTitleS = rs.getString("TitleStatus");
				xDate = rs.getString("DateFinished");
				xRelSeason = rs.getString("ReleaseSeason");
				xLength = rs.getString("Length");
				
				String temp = xQual + "\t" +
							  xTitle + "\t\t" +
							  xEps + "\t" +
							  xOVA + "\t" +
							  xSpc + "\t" +
							  xSize + "\t" +
							  xSeriesS + "\t\t" +
							  xTitleS + "\t\t" +
							  xDate + "\t\t" +
							  xRelSeason + "\t\t" +
							  xLength + "\t";

				p5TextArea.setText(p5TextArea.getText() + "\n" + temp);
			}
		} catch (SQLException e) { e.getMessage(); }
		
		pnlHQ.add(p5HeaderPanel);
//		pnlHQ.add(p5DBTable);
		pnlHQ.add(p5TextAreaScroll);
	}

	private void setPanelLQ() {
		// TODO
	}
	
	public void setDataSent(String quality, 
							String title, 
							String episodes, 
							String ovas, 
							String specials,
							String fileSize,
							String sizeType,
							String seriesStatus,
							String titleStatus,
							String month,
							String day,
							String year,
							String relSeason,
							String relYear,
							String lengthHr,
							String lengthMin,
							String lengthSec) {
		
		this.DataSent = new String[17];

		this.DataSent[0] = quality;
		this.DataSent[1] = title;
		this.DataSent[2] = episodes;
		this.DataSent[3] = ovas;
		this.DataSent[4] = specials;
		this.DataSent[5] = fileSize;
		this.DataSent[6] = sizeType;
		this.DataSent[7] = seriesStatus;
		this.DataSent[8] = titleStatus;
		this.DataSent[9] = month;
		this.DataSent[10] = day;
		this.DataSent[11] = year;
		this.DataSent[12] = relSeason;
		this.DataSent[13] = relYear;
		this.DataSent[14] = lengthHr;
		this.DataSent[15] = lengthMin;
		this.DataSent[16] = lengthSec;
	}
	
	public void setFinalizedDataSent() {
		FinalizedDataSent = new String[11];
		this.FinalizedDataSent[0] = this.DataSent[0]; //QUALITY
		this.FinalizedDataSent[1] = this.DataSent[1]; //TITLE
		this.FinalizedDataSent[2] = this.DataSent[2]; //EPS
		this.FinalizedDataSent[3] = this.DataSent[3]; //OVA
		this.FinalizedDataSent[4] = this.DataSent[4]; //SPECIALS
		this.FinalizedDataSent[5] = this.DataSent[5] + " " + this.DataSent[6]; //FILESIZE
		this.FinalizedDataSent[6] = this.DataSent[7]; //SERIES STATUS
		this.FinalizedDataSent[7] = this.DataSent[8]; //TITLE STATUS
		this.FinalizedDataSent[8] = this.DataSent[9] + " " + this.DataSent[10] + ", " + this.DataSent[11]; //DATE FINISHED
		this.FinalizedDataSent[9] = this.DataSent[12] + " " + this.DataSent[13]; // SEASON RELEASED
		this.FinalizedDataSent[10] = this.DataSent[14] + ":" + this.DataSent[15] + ":" + this.DataSent[16];

	}
	
	private void setViewType() {
		if (tabbedDialog) {
			
			tabPane = new JTabbedPane();
			tabPane.setBounds(40, 50, 600, 506);
			tabPane.addTab("New Content", pnlNewContent);
			tabPane.addTab("All Anime", pnlAll);
			tabPane.addTab("FHD 1080p", pnlFHD);
			tabPane.addTab("HD 720p", pnlHD);
			tabPane.addTab("HQ 480p", pnlHQ);
			tabPane.addTab("LQ", null);
			tabPane.setFocusable(false);
	
			tabPane.setBackgroundAt(0, new Color(253, 255, 224));
			tabPane.setBackgroundAt(1, new Color(153, 255, 102));
			tabPane.setBackgroundAt(2, new Color(255, 192, 0));
			tabPane.setBackgroundAt(3, new Color(102, 153, 255));
			tabPane.setBackgroundAt(4, new Color(255, 255, 255));
	
			bgBubble.add(tabPane);
			
		} else {

			btnNewContent = new JButton("New Content");
			btnNewContent.setBounds(55, 55, 120, 20);
			btnNewContent.setBackground(new Color(185, 211, 238));
			btnNewContent.setBorderPainted(false);
			btnNewContent.setFocusable(false);
			
			JPanel buttonsPanel = new JPanel();
			buttonsPanel.setLayout(new GridLayout(1, 5, 5, 0));
			buttonsPanel.setBounds(180, 55, 445, 20);
			buttonsPanel.setOpaque(false);
			
			btnAll = new JButton("All");
			btnAll.setBackground(new Color(185, 211, 238));
			btnAll.setBorderPainted(false);
			btnAll.setFocusable(false);
			

			btnFHD = new JButton("FHD");
			btnFHD.setBounds(210, 55, 100, 20);
			btnFHD.setBackground(new Color(153, 255, 102));
			btnFHD.setBorderPainted(false);
			btnFHD.setFocusable(false);

			btnHD = new JButton("HD");
			btnHD.setBounds(315, 55, 100, 20);
			btnHD.setBackground(new Color(255, 192, 0));
			btnHD.setBorderPainted(false);
			btnHD.setFocusable(false);

			btnHQ = new JButton("HQ");
			btnHQ.setBounds(420, 55, 100, 20);
			btnHQ.setBackground(new Color(102, 153, 255));
			btnHQ.setBorderPainted(false);
			btnHQ.setFocusable(false);

			btnLQ = new JButton("LQ");
//			btnLQ.setBounds(525, 55, 100, 20);
			btnLQ.setBackground(new Color(255, 255, 255));
			btnLQ.setBorderPainted(false);
			btnLQ.setFocusable(false);

			pnlNewContent.setBounds(40, 80, 600, 486);
			pnlFHD.setBounds(40, 80, 600, 486);
			pnlHD.setBounds(40, 80, 600, 486);
			pnlHQ.setBounds(40, 80, 600, 486);
//			pnlLQ.setBounds(40, 80, 600, 486);

			btnNewContent.addActionListener(action);
			btnAll.addActionListener(action);
			btnFHD.addActionListener(action);
			btnHD.addActionListener(action);
			btnHQ.addActionListener(action);
			btnLQ.addActionListener(action);

			buttonsPanel.add(btnAll);
			buttonsPanel.add(btnFHD);
			buttonsPanel.add(btnHD);
			buttonsPanel.add(btnHQ);
			buttonsPanel.add(btnLQ);
			
			bgBubble.add(btnNewContent);
			bgBubble.add(buttonsPanel);
//			bgBubble.add(btnFHD);
//			bgBubble.add(btnHD);
//			bgBubble.add(btnHQ);
//			bgBubble.add(btnLQ);
			bgBubble.add(pnlNewContent);
		}
	}
	
	private void checkParams(String[] params) {		
		for (String s : params) {
			if (s.equals("-tabdlg")) {
				this.tabbedDialog = true;
			} else if (s.equals("-btndlg")) {
				this.tabbedDialog = false;
			} else if (s.equals("-debug")) {
				this.debuggingMode = true;
			} else if (s.equals("-abtdlg")) {
				this.showAbtDlg = (this.showAbtDlg) ? false : true;
			}
		}
	}
	
	public void resetFields() {
		p1Quality.setSelectedIndex(1);
		p1Title.setText("");
		p1Episodes.setText("");
		p1OVAs.setText("");
		p1Specials.setText("");
		p1Size.setText("");
		p1SizeSelection.setSelectedIndex(0);
		p1SeriesStatus.setSelectedIndex(0);
		p1TitleStatus.setSelectedIndex(0);
		p1DateMonth.setSelectedItem(getCurrentMonth());
		p1DateDay.setSelectedItem(getCurrentDay());
		p1DateYear.setSelectedItem(getCurrentYear());
		p1ReleaseSeason.setSelectedItem(getCurrentSeason());
		p1ReleaseYear.setSelectedItem(getCurrentYear());
		p1LengthHour.setText("");
		p1LengthMinute.setText("");
		p1LengthSecond.setText("");
		p1Preview.setText("");
	}
	
	
	/**
	 * GETTERS AND SETTERS
	 */

	public String getQuality() {
		return this.Quality;
	}

	public void setQuality(String quality) {
		this.Quality = quality;
	}
	
	public String getAnimeTitle() {
		return this.AnimeTitle;
	}
	
	public void setAnimeTitle(String title) {
		this.AnimeTitle = title;
	}

	public int getEpisodes() {
		return this.Episodes;
	}

	public void setEpisodes(int episodes) {
		this.Episodes = episodes;
	}
	
	public void setEpisodes(String episodes) {
		this.Episodes = Integer.parseInt(episodes);
	}
	
	public int getOVAs() {
		return this.OVAs;
	}

	public void setOVAs(int OVAs) {
		this.OVAs = OVAs;
	}

	public void setOVAs(String OVAs) {
		this.OVAs = Integer.parseInt(OVAs);
	}

	public int getSpecials() {
		return this.Specials;
	}

	public void setSpecials(int specials) {
		this.Specials = specials;
	}

	public void setSpecials(String specials) {
		this.Specials = Integer.parseInt(specials);
	}
	
	public String getFileSize() {
		return this.FileSize;
	}

	public void setFileSize(float fileSize, String fileType) {
		this.FileSize = fileSize + " " + fileType;
	}

	public void setFileSize(String fileSize, String fileType) {
		this.FileSize = fileSize + " " + fileType;
	}
	
	public String getSeriesStatus() {
		return this.SeriesStatus;
	}

	public void setSeriesStatus(String seriesStatus) {
		this.SeriesStatus = seriesStatus;
	}

	public String getTitleStatus() {
		return this.TitleStatus;
	}

	public void setTitleStatus(String titleStatus) {
		this.TitleStatus = titleStatus;
	}

	public String getDateFinished() {		
		return this.DateFinished;
	}

	public void setDateFinished(int month, int day, int year) {
		this.DateFinished = month +  " " + day + ", " + year;
	}

	public void setDateFinished(String month, String day, String year) {
		this.DateFinished = month +  " " + day + ", " + year;
	}

	public String getReleaseSeason() {
		return this.ReleaseSeason;
	}

	public void setReleaseSeason(String releaseSeason, String releaseYear) {
		this.ReleaseSeason = releaseSeason + " " + releaseYear;
	}

	public String getLength() {
		return this.Length;
	}

	public void setLength(int hour, int minute, int second) {
		this.Length = hour + ":" + minute + ":" + second;
	}	

	public void setLength(String hour, String minute, String second) {
		this.Length = hour + ":" + minute + ":" + second;
	}
}
