package com.minase.maidchan.anidb_prevcopy;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.PlainDocument;


public class Edit extends JFrame {

	private static final long serialVersionUID = -7149513586983235459L;

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
					   txtLengthSec,
					   txtSearch;
	
	public JComboBox<String> cbQuality, 
							 cbFileType, 
							 cbSeriesStatus, 
							 cbTitleStatus, 
							 cbMonth, 
							 cbDay, 
							 cbYear, 
							 cbReleaseSeason, 
							 cbReleaseYear,
							 cbSearch;
	
	private JButton btnReset,
					btnPreview,
					btnSubmit,
					btnSearch;
	
	private JSlider sldData;
	
	EditActionListener action;
	MinaseDocFilter docFilter = new MinaseDocFilter();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Edit frame = new Edit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Edit() {
		
		action = new EditActionListener(this);
		
		setTitle("Anime Database -- Edit");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 257);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbQuality = new JComboBox<String>(new String[] {"", "FHD 1080p", "HD 720p", "HQ 480p", "LQ"});
		cbQuality.setEnabled(false);
		cbQuality.setBounds(6, 41, 90, 20);
		contentPane.add(cbQuality);
		
		txtTitle = new JTextField();
		txtTitle.setEditable(false);
		txtTitle.setBounds(106, 41, 170, 20);
		contentPane.add(txtTitle);
		
		txtFileSize = new JTextField();
		txtFileSize.setEditable(false);
		txtFileSize.setToolTipText("File Size");
		txtFileSize.setHorizontalAlignment(SwingConstants.CENTER);
		txtFileSize.setBounds(186, 72, 80, 20);
		contentPane.add(txtFileSize);

		cbFileType = new JComboBox<String>(new String[] {"", "GB", "MB"});
		cbFileType.setEnabled(false);
		cbFileType.setBounds(276, 72, 60, 20);
		contentPane.add(cbFileType);
		
		pnlEpisodeNumbers = new JPanel();
		pnlEpisodeNumbers.setBounds(6, 72, 170, 20);
		pnlEpisodeNumbers.setLayout(new GridLayout(1, 0, 10, 0));
		contentPane.add(pnlEpisodeNumbers);
		
		txtEpisodes = new JTextField();
		txtEpisodes.setEditable(false);
		txtEpisodes.setToolTipText("Episodes");
		txtEpisodes.setHorizontalAlignment(SwingConstants.CENTER);
		((PlainDocument) txtEpisodes.getDocument()).setDocumentFilter(docFilter);
		pnlEpisodeNumbers.add(txtEpisodes);
		
		txtOVAs = new JTextField();
		txtOVAs.setEditable(false);
		txtOVAs.setToolTipText("OVAs");
		txtOVAs.setHorizontalAlignment(SwingConstants.CENTER);
		((PlainDocument) txtOVAs.getDocument()).setDocumentFilter(docFilter);
		pnlEpisodeNumbers.add(txtOVAs);
		
		txtSpecials = new JTextField();
		txtSpecials.setEditable(false);
		txtSpecials.setToolTipText("Specials");
		txtSpecials.setHorizontalAlignment(SwingConstants.CENTER);
		((PlainDocument) txtSpecials.getDocument()).setDocumentFilter(docFilter);
		pnlEpisodeNumbers.add(txtSpecials);
		
		pnlStatus = new JPanel();
		pnlStatus.setBounds(6, 103, 333, 20);
		pnlStatus.setLayout(new GridLayout(1, 0, 10, 0));
		contentPane.add(pnlStatus);
		
		cbSeriesStatus = new JComboBox<String>(new String[] {"", "Finished", "Not the first season", "Not Finished"});
		cbSeriesStatus.setEnabled(false);
		cbSeriesStatus.setToolTipText("Series Status");
		pnlStatus.add(cbSeriesStatus);
		
		cbTitleStatus = new JComboBox<String>(new String[] {"", "Finished", "Not Finished"});
		cbTitleStatus.setEnabled(false);
		cbTitleStatus.setToolTipText("Title Status");
		pnlStatus.add(cbTitleStatus);
		
		cbMonth = new JComboBox<String>(new String[] {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});
		cbMonth.setEnabled(false);
		cbMonth.setToolTipText("Month Finished");
		cbMonth.setBounds(6, 134, 50, 20);
		contentPane.add(cbMonth);
		
		cbDay = new JComboBox<String>(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
		cbDay.setEnabled(false);
		cbMonth.setToolTipText("Day Finished");
		cbDay.setBounds(66, 134, 50, 20);
		contentPane.add(cbDay);
		
		cbYear = new JComboBox<String>(new String[] {"", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"});
		cbYear.setEnabled(false);
		cbMonth.setToolTipText("Year Finished");
		cbYear.setBounds(126, 134, 60, 20);
		contentPane.add(cbYear);
		
		cbReleaseSeason = new JComboBox<String>(new String[] {"", "Winter", "Spring", "Summer", "Fall"});
		cbReleaseSeason.setEnabled(false);
		cbMonth.setToolTipText("Season Released");
		cbReleaseSeason.setBounds(196, 134, 70, 20);
		contentPane.add(cbReleaseSeason);
		
		cbReleaseYear = new JComboBox<String>(new String[] {"", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"});
		cbReleaseYear.setEnabled(false);
		cbMonth.setToolTipText("Year Released");
		cbReleaseYear.setBounds(276, 134, 60, 20);
		contentPane.add(cbReleaseYear);
		
		pnlLength = new JPanel();
		pnlLength.setBounds(6, 165, 150, 20);
		pnlLength.setLayout(new GridLayout(0, 3, 10, 0));
		contentPane.add(pnlLength);
		
		txtLengthHr = new JTextField();
		txtLengthHr.setEditable(false);
		txtLengthHr.setToolTipText("Length:Hours");
		txtLengthHr.setHorizontalAlignment(SwingConstants.CENTER);
		((PlainDocument) txtLengthHr.getDocument()).setDocumentFilter(docFilter);
		pnlLength.add(txtLengthHr);
		
		txtLengthMin = new JTextField();
		txtLengthMin.setEditable(false);
		txtLengthMin.setToolTipText("Length:Minutes");
		txtLengthMin.setHorizontalAlignment(SwingConstants.CENTER);
		((PlainDocument) txtLengthMin.getDocument()).setDocumentFilter(docFilter);
		pnlLength.add(txtLengthMin);
		
		txtLengthSec = new JTextField();
		txtLengthSec.setEditable(false);
		txtLengthSec.setToolTipText("Length:Seconds");
		txtLengthSec.setHorizontalAlignment(SwingConstants.CENTER);
		((PlainDocument) txtLengthSec.getDocument()).setDocumentFilter(docFilter);
		pnlLength.add(txtLengthSec);
		
		btnReset = new JButton("Reset");
		btnReset.setEnabled(false);
		btnReset.setBounds(166, 165, 70, 20);
		contentPane.add(btnReset);
		
		btnPreview = new JButton("Preview");
		btnPreview.setEnabled(false);
		btnPreview.setBounds(246, 165, 90, 20);
		contentPane.add(btnPreview);
		
		btnSubmit = new JButton("Edit Data");
		btnSubmit.setEnabled(false);
		btnSubmit.setBounds(6, 196, 150, 25);
		contentPane.add(btnSubmit);
		
		lblCopyright = new JLabel("\u00A9 2014 Minase Conglomerate");
		lblCopyright.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCopyright.setBounds(166, 198, 170, 20);
		contentPane.add(lblCopyright);
		
		cbSearch = new JComboBox<String>(new String[] {"", "FHD 1080p", "HD 720p", "HQ 480p", "LQ"});
		cbSearch.setSelectedIndex(0);
		cbSearch.setBounds(10, 11, 90, 20);
		contentPane.add(cbSearch);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(110, 11, 140, 20);
		contentPane.add(txtSearch);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(260, 10, 80, 20);
		contentPane.add(btnSearch);
		
		sldData = new JSlider();
		sldData.setValue(0);
		sldData.setMaximum(0);
		sldData.setEnabled(false);
		sldData.setBounds(286, 38, 50, 23);
		contentPane.add(sldData);
	
		/**
		 * ADDING LISTENERS
		 */
		cbQuality.addItemListener(action);
		txtTitle.addKeyListener(action);
		txtEpisodes.addKeyListener(action);
		txtOVAs.addKeyListener(action);
		txtSpecials.addKeyListener(action);
		txtFileSize.addKeyListener(action);
		cbFileType.addItemListener(action);
		cbSeriesStatus.addItemListener(action);
		cbTitleStatus.addItemListener(action);
		cbMonth.addItemListener(action);
		cbDay.addItemListener(action);
		cbYear.addItemListener(action);
		cbReleaseSeason.addItemListener(action);
		cbReleaseYear.addItemListener(action);
		txtLengthHr.addKeyListener(action);
		txtLengthMin.addKeyListener(action);
		txtLengthSec.addKeyListener(action);

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
		btnSubmit.addActionListener(action);
		txtSearch.addKeyListener(action);
		btnSearch.addActionListener(action);
		sldData.addChangeListener(action);
	}
	
	private class EditActionListener implements ActionListener, ChangeListener, FocusListener, ItemListener, KeyListener {

		private String[] dataQuality = new String[0], 
						 dataTitle = new String[0], 
						 dataEpisodes = new String[0],
						 dataOVAs = new String[0],
						 dataSpecials = new String[0],
						 dataFileSize = new String[0],
						 dataFileType = new String[0],
						 dataSeriesStatus = new String[0],
						 dataTitleStatus = new String[0],
						 dataMonth = new String[0],
						 dataDay = new String[0],
						 dataYear = new String[0],
						 dataRelSeason = new String[0],
						 dataRelYear = new String[0],
						 dataLengthHr = new String[0],
						 dataLengthMin = new String[0],
						 dataLengthSec = new String[0];
		
//		private int sliderValue = 0;
		
		Edit e;
		
		Connection con;
		Statement stm;
		
		public EditActionListener(Edit e) {
			this.e = e;
		}
		
		@SuppressWarnings("resource")
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource().equals(e.btnSearch)) {
				connectToDatabase();
				int counter = 0;

				ResultSet rsNew = null;
				if (arg0.getActionCommand().equals("comboBoxChanged")) {
					try {
						if (arg0.getSource().equals(e.cbMonth)) {
							addDay(e.cbDay, e.cbMonth.getSelectedItem().toString(), Integer.parseInt(e.cbYear.getSelectedItem().toString()));
						} else if (arg0.getSource().equals(e.cbYear)) {
							addDay(e.cbDay, e.cbMonth.getSelectedItem().toString(), Integer.parseInt(e.cbYear.getSelectedItem().toString()));
						}
					} catch (Exception e) { }
				} else if (cbSearch.getSelectedIndex() == 0 && txtSearch.getText().equals("")) {
					try {
						ResultSet rs = retrieve("select * from animedatabase");

						while (rs.next()) { counter++; }
						
						dataQuality = new String[counter];
						dataTitle = new String[counter];
						dataEpisodes = new String[counter];
						dataOVAs = new String[counter];
						dataSpecials = new String[counter];
						dataFileSize = new String[counter];
						dataFileType = new String[counter];
						dataSeriesStatus = new String[counter];
						dataTitleStatus = new String[counter];
						dataMonth = new String[counter];
						dataDay = new String[counter];
						dataYear = new String[counter];
						dataRelSeason = new String[counter];
						dataRelYear = new String[counter];
						dataLengthHr = new String[counter];
						dataLengthMin = new String[counter];
						dataLengthSec = new String[counter];
					} catch (SQLException e) { System.err.println(e.getMessage());}
					
					rsNew = retrieve("select * from animedatabase");
					
				} else if (cbSearch.getSelectedIndex() == 1 || cbSearch.getSelectedIndex() == 2 || cbSearch.getSelectedIndex() == 3 || cbSearch.getSelectedIndex() == 4) {
					if (txtSearch.getText().equals("") || txtSearch.getText().equals(null)) {
						try {
							ResultSet rs = retrieve("select * from animedatabase where Quality='" + cbSearch.getSelectedItem().toString() + "'");

							while (rs.next()) { counter++; }
							
							dataQuality = new String[counter];
							dataTitle = new String[counter];
							dataEpisodes = new String[counter];
							dataOVAs = new String[counter];
							dataSpecials = new String[counter];
							dataFileSize = new String[counter];
							dataFileType = new String[counter];
							dataSeriesStatus = new String[counter];
							dataTitleStatus = new String[counter];
							dataMonth = new String[counter];
							dataDay = new String[counter];
							dataYear = new String[counter];
							dataRelSeason = new String[counter];
							dataRelYear = new String[counter];
							dataLengthHr = new String[counter];
							dataLengthMin = new String[counter];
							dataLengthSec = new String[counter];
						} catch (SQLException e) { System.err.println(e.getMessage()); }
						
						rsNew = retrieve("select * from animedatabase where Quality='" + cbSearch.getSelectedItem().toString() + "'");
					} else {
						try {
							ResultSet rs = retrieve("select * from animedatabase where Quality='" + cbSearch.getSelectedItem().toString() + "' and AnimeTitle='" + txtSearch.getText() + "'");
							
							while (rs.next()) { counter++; }
							
							dataQuality = new String[counter];
							dataTitle = new String[counter];
							dataEpisodes = new String[counter];
							dataOVAs = new String[counter];
							dataSpecials = new String[counter];
							dataFileSize = new String[counter];
							dataFileType = new String[counter];
							dataSeriesStatus = new String[counter];
							dataTitleStatus = new String[counter];
							dataMonth = new String[counter];
							dataDay = new String[counter];
							dataYear = new String[counter];
							dataRelSeason = new String[counter];
							dataRelYear = new String[counter];
							dataLengthHr = new String[counter];
							dataLengthMin = new String[counter];
							dataLengthSec = new String[counter];
						} catch (SQLException e) {
							System.err.println(e.getMessage());
						}
						
						rsNew = retrieve("select * from animedatabase where Quality='" + cbSearch.getSelectedItem().toString() + "' and AnimeTitle='" + txtSearch.getText() + "'");
					}
					
				} else if (e.cbSearch.getSelectedItem().equals("") || e.cbSearch.getSelectedItem().equals(null)) {
					if (!e.txtSearch.getText().equals("") || !e.txtSearch.getText().equals(null)) {
						try {
							ResultSet rs = retrieve("select * from animedatabase where AnimeTitle='" + e.txtSearch.getText() + "'");
							
							while (rs.next()) { counter++; }
							
							dataQuality = new String[counter];
							dataTitle = new String[counter];
							dataEpisodes = new String[counter];
							dataOVAs = new String[counter];
							dataSpecials = new String[counter];
							dataFileSize = new String[counter];
							dataFileType = new String[counter];
							dataSeriesStatus = new String[counter];
							dataTitleStatus = new String[counter];
							dataMonth = new String[counter];
							dataDay = new String[counter];
							dataYear = new String[counter];
							dataRelSeason = new String[counter];
							dataRelYear = new String[counter];
							dataLengthHr = new String[counter];
							dataLengthMin = new String[counter];
							dataLengthSec = new String[counter];
						} catch (SQLException e) { System.err.println(e.getMessage()); }	
						rsNew = retrieve("select * from animedatabase where AnimeTitle='" + txtSearch.getText() + "'");		
					}
				}
				
				if (counter != 0) {
					try {
						int tmpCtr = 0;
						while (rsNew.next()) {
							try { dataQuality[tmpCtr] = rsNew.getString("Quality"); } catch (StringIndexOutOfBoundsException StrOB) { dataQuality[tmpCtr] = ""; }
							try { dataTitle[tmpCtr] = rsNew.getString("AnimeTitle"); } catch (StringIndexOutOfBoundsException StrOB) { dataTitle[tmpCtr] = ""; }
							try { dataEpisodes[tmpCtr] = "" + rsNew.getInt("Episodes"); } catch (StringIndexOutOfBoundsException StrOB) { dataEpisodes[tmpCtr] = ""; }
							try { dataOVAs[tmpCtr] = "" + rsNew.getInt("OVAs"); } catch (StringIndexOutOfBoundsException StrOB) { dataOVAs[tmpCtr] = ""; }
							try { dataSpecials[tmpCtr] = "" + rsNew.getInt("Specials"); } catch (StringIndexOutOfBoundsException StrOB) { dataSpecials[tmpCtr] = ""; }
							
							try {
								String tempFileSize = rsNew.getString("FileSize");
								dataFileSize[tmpCtr] = tempFileSize.substring(0, tempFileSize.indexOf(" "));
								dataFileType[tmpCtr] = tempFileSize.substring((tempFileSize.indexOf(" ")+1), tempFileSize.length());
							} catch (StringIndexOutOfBoundsException StrOB) { 
								dataFileSize[tmpCtr] = "";
								dataFileType[tmpCtr] = "";
							}
							
							try { dataSeriesStatus[tmpCtr] = rsNew.getString("SeasonStatus"); } catch (StringIndexOutOfBoundsException StrOB) { dataSeriesStatus[tmpCtr] = ""; }
							try { dataTitleStatus[tmpCtr] = rsNew.getString("TitleStatus"); } catch (StringIndexOutOfBoundsException StrOB) { dataTitleStatus[tmpCtr] = ""; }
							try { dataMonth[tmpCtr] = rsNew.getString("DateFinished").split(" ")[0]; } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException StrOB) { dataMonth[tmpCtr] = ""; }
							try {dataDay[tmpCtr] = (rsNew.getString("DateFinished").split(" ")[1]).substring(0, 2);} catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException StrOB) { dataDay[tmpCtr] = ""; }
							try {dataYear[tmpCtr] = rsNew.getString("DateFinished").split(" ")[2];} catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException StrOB) { dataYear[tmpCtr] = ""; }
							try { dataRelSeason[tmpCtr] = rsNew.getString("ReleaseSeason").split(" ")[0]; } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException StrOB) { dataRelSeason[tmpCtr] = ""; }
							try { dataRelYear[tmpCtr] = rsNew.getString("ReleaseSeason").split(" ")[1];} catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException StrOB) { dataRelYear[tmpCtr] = ""; }
							try { dataLengthHr[tmpCtr] = rsNew.getString("Length").split(":")[0];} catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException StrOB) { dataLengthHr[tmpCtr] = ""; }
							try {dataLengthMin[tmpCtr] = rsNew.getString("Length").split(":")[1];} catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException StrOB) { dataLengthMin[tmpCtr] = ""; }
							try { dataLengthSec[tmpCtr] = rsNew.getString("Length").split(":")[2];} catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException StrOB) { dataLengthSec[tmpCtr] = ""; }
							tmpCtr++;
						}
						
					} catch (SQLException e) { System.err.println(e.getMessage()); }
					
					try {
						if (!dataQuality[0].equals(null) || !dataQuality[0].equals("")
								|| !dataTitle[0].equals(null) || !dataTitle[0].equals("")
								|| !dataEpisodes[0].equals(null) || !dataEpisodes[0].equals("")
								|| !dataOVAs[0].equals(null) || !dataOVAs[0].equals("")
								|| !dataSpecials[0].equals(null) || !dataSpecials[0].equals("")
								|| !dataFileSize[0].equals(null) || !dataFileSize[0].equals("")
								|| !dataFileType[0].equals(null) || !dataFileType[0].equals("")
								|| !dataSeriesStatus[0].equals(null) || !dataSeriesStatus[0].equals("")
								|| !dataTitleStatus[0].equals(null) || !dataTitleStatus[0].equals("")
								|| !dataMonth[0].equals(null) || !dataMonth[0].equals("")
								|| !dataDay[0].equals(null) || !dataDay[0].equals("")
								|| !dataYear[0].equals(null) || !dataYear[0].equals("")
								|| !dataRelSeason[0].equals(null) || !dataRelSeason[0].equals("")
								|| !dataRelYear[0].equals(null) || !dataRelYear[0].equals("")
								|| !dataLengthHr[0].equals(null) || !dataLengthHr[0].equals("")
								|| !dataLengthMin[0].equals(null) || !dataLengthMin[0].equals("")
								|| !dataLengthSec[0].equals(null) || !dataLengthSec[0].equals("")) {
							
							e.sldData.setEnabled(true);
							e.sldData.setMaximum(counter-1);
							e.sldData.setValue(0);
							
							e.cbQuality.setSelectedItem(dataQuality[e.sldData.getValue()]);
							e.txtTitle.setText(dataTitle[e.sldData.getValue()]);
							e.txtEpisodes.setText(dataEpisodes[e.sldData.getValue()]);
							e.txtOVAs.setText(dataOVAs[e.sldData.getValue()]);
							e.txtSpecials.setText(dataSpecials[e.sldData.getValue()]);
							e.txtFileSize.setText(dataFileSize[e.sldData.getValue()]);
							e.cbFileType.setSelectedItem(dataFileType[e.sldData.getValue()]);
							e.cbSeriesStatus.setSelectedItem(dataSeriesStatus[e.sldData.getValue()]);
							e.cbTitleStatus.setSelectedItem(dataTitleStatus[e.sldData.getValue()]);
							e.cbMonth.setSelectedItem(dataMonth[e.sldData.getValue()]);
							e.cbDay.setSelectedItem(dataDay[e.sldData.getValue()]);
							e.cbYear.setSelectedItem(dataYear[e.sldData.getValue()]);
							e.cbReleaseSeason.setSelectedItem(dataRelSeason[e.sldData.getValue()]);
							e.cbReleaseYear.setSelectedItem(dataRelYear[e.sldData.getValue()]);
							e.txtLengthHr.setText(dataLengthHr[e.sldData.getValue()]);
							e.txtLengthMin.setText(dataLengthMin[e.sldData.getValue()]);
							e.txtLengthSec.setText(dataLengthSec[e.sldData.getValue()]);
							
							e.cbQuality.setEnabled(true);
							e.txtTitle.setEditable(true);
							e.txtEpisodes.setEditable(true);
							e.txtOVAs.setEditable(true);
							e.txtSpecials.setEditable(true);
							e.txtFileSize.setEditable(true);
							e.cbFileType.setEnabled(true);
							e.cbSeriesStatus.setEnabled(true);
							e.cbTitleStatus.setEnabled(true);
							e.cbMonth.setEnabled(true);
							e.cbDay.setEnabled(true);
							e.cbYear.setEnabled(true);
							e.cbReleaseSeason.setEnabled(true);
							e.cbReleaseYear.setEnabled(true);
							e.txtLengthHr.setEditable(true);
							e.txtLengthMin.setEditable(true);
							e.txtLengthSec.setEditable(true);
							
							e.btnReset.setEnabled(true);
							e.btnPreview.setEnabled(true);
							e.btnSubmit.setEnabled(true);
						}
					} catch (ArrayIndexOutOfBoundsException AIndexOB) { }
				} else {
					
					e.cbQuality.setSelectedItem("");
					e.txtTitle.setText("");
					e.txtEpisodes.setText("");
					e.txtOVAs.setText("");
					e.txtSpecials.setText("");
					e.txtFileSize.setText("");
					e.cbFileType.setSelectedItem("");
					e.cbSeriesStatus.setSelectedItem("");
					e.cbTitleStatus.setSelectedItem("");
					e.cbMonth.setSelectedItem("");
					e.cbDay.setSelectedItem("");
					e.cbYear.setSelectedItem("");
					e.cbReleaseSeason.setSelectedItem("");
					e.cbReleaseYear.setSelectedItem("");
					e.txtLengthHr.setText("");
					e.txtLengthMin.setText("");
					e.txtLengthSec.setText("");
					
					e.cbQuality.setEnabled(false);
					e.txtTitle.setEditable(false);
					e.txtEpisodes.setEditable(false);
					e.txtOVAs.setEditable(false);
					e.txtSpecials.setEditable(false);
					e.txtFileSize.setEditable(false);
					e.cbFileType.setEnabled(false);
					e.cbSeriesStatus.setEnabled(false);
					e.cbTitleStatus.setEnabled(false);
					e.cbMonth.setEnabled(false);
					e.cbDay.setEnabled(false);
					e.cbYear.setEnabled(false);
					e.cbReleaseSeason.setEnabled(false);
					e.cbReleaseYear.setEnabled(false);
					e.txtLengthHr.setEditable(false);
					e.txtLengthMin.setEditable(false);
					e.txtLengthSec.setEditable(false);
					
					e.btnReset.setEnabled(false);
					e.btnPreview.setEnabled(false);
					e.btnSubmit.setEnabled(false);
				}
			}else if (arg0.getSource().equals(e.btnSubmit)) {
				
				if (JOptionPane.showConfirmDialog(null, "\nHey! I will be editing the Anime '" + e.txtTitle.getText() + "'." +
						"\nAre you sure of this?\nYou won't be able to undo your action once I have already edited it.\n\nFrom: Maid-chan\n\n", 
						"メイド�?�ゃん (Maid-chan)", JOptionPane.YES_NO_OPTION, 0, 
						new ImageIcon(getClass().getResource("img/tray-128x83.png"))) == 0) {
					
					insert("update animedatabase set " +
							"Quality='" + e.cbQuality.getSelectedItem().toString() + "', " +
							"AnimeTitle='" + e.txtTitle.getText() + "', " +
							"Episodes=" + Integer.parseInt(e.txtEpisodes.getText()) + ", " +
							"OVAs=" + Integer.parseInt(e.txtOVAs.getText()) + ", " +
							"Specials=" + Integer.parseInt(e.txtSpecials.getText()) + ", " +
							"Filesize='" + e.txtFileSize.getText() + " " + e.cbFileType.getSelectedItem().toString() + "', " +
							"SeasonStatus='" + e.cbSeriesStatus.getSelectedItem().toString() + "', " +
							"TitleStatus='" + e.cbTitleStatus.getSelectedItem().toString() + "', " +
							"DateFinished='" + e.cbMonth.getSelectedItem().toString() + " " + e.cbDay.getSelectedItem().toString() + ", " + e.cbYear.getSelectedItem().toString() + "', " +
							"ReleaseSeason='" + e.cbReleaseSeason.getSelectedItem().toString() + " " + e.cbReleaseYear.getSelectedItem().toString() + "', " +
							"Length='" + e.txtLengthHr.getText() + ":" + e.txtLengthMin.getText() + ":" + e.txtLengthSec.getText() + "' where Quality='" +
							dataQuality[e.sldData.getValue()] + "' and AnimeTitle='" +
							dataTitle[e.sldData.getValue()] + "' and Episodes=" +
							Integer.parseInt(dataEpisodes[e.sldData.getValue()]) + " and OVAs=" +
							Integer.parseInt(dataOVAs[e.sldData.getValue()]) + " and Specials=" +
							Integer.parseInt(dataSpecials[e.sldData.getValue()]) + " and FileSize='" +
							dataFileSize[e.sldData.getValue()] + " " + dataFileType[e.sldData.getValue()] + "' and SeasonStatus='" +
							dataSeriesStatus[e.sldData.getValue()]+ "' and TitleStatus='" +
							dataTitleStatus[e.sldData.getValue()] + "' and DateFinished='" +
							dataMonth[e.sldData.getValue()] + " " + dataDay[e.sldData.getValue()] + ", " + dataYear[e.sldData.getValue()] + "' and ReleaseSeason='" +
							dataRelSeason[e.sldData.getValue()] + " " + dataRelYear[e.sldData.getValue()] + "' and Length='" +
							dataLengthHr[e.sldData.getValue()] + ":" + dataLengthMin[e.sldData.getValue()] + ":" + dataLengthSec[e.sldData.getValue()] + "'");
	
					
//					insert("update animedatabase set Quality='" + 
//							e.cbQuality.getSelectedItem().toString() + "' where Quality='" +
//							dataQuality[e.sldData.getValue()] + "' and AnimeTitle='" +
//							dataTitle[e.sldData.getValue()] + "' and Episodes=" +
//							Integer.parseInt(dataEpisodes[e.sldData.getValue()]) + " and OVAs=" +
//							Integer.parseInt(dataOVAs[e.sldData.getValue()]) + " and Specials=" +
//							Integer.parseInt(dataSpecials[e.sldData.getValue()]) + " and FileSize='" +
//							dataFileSize[e.sldData.getValue()] + " " + dataFileType[e.sldData.getValue()] + "' and SeasonStatus='" +
//							dataSeriesStatus[e.sldData.getValue()]+ "' and TitleStatus='" +
//							dataTitleStatus[e.sldData.getValue()] + "' and DateFinished='" +
//							dataMonth[e.sldData.getValue()] + " " + dataDay[e.sldData.getValue()] + ", " + dataYear[e.sldData.getValue()] + "' and ReleaseSeason='" +
//							dataRelSeason[e.sldData.getValue()] + " " + dataRelYear[e.sldData.getValue()] + "' and Length='" +
//							dataLengthHr[e.sldData.getValue()] + ":" + dataLengthMin[e.sldData.getValue()] + ":" + dataLengthSec[e.sldData.getValue()] + "'");
//	
//					insert("update animedatabase set AnimeTitle='" + 
//							e.txtTitle.getText() + "' where Quality='" +
//							dataQuality[e.sldData.getValue()] + "' and AnimeTitle='" +
//							dataTitle[e.sldData.getValue()] + "' and Episodes=" +
//							Integer.parseInt(dataEpisodes[e.sldData.getValue()]) + " and OVAs=" +
//							Integer.parseInt(dataOVAs[e.sldData.getValue()]) + " and Specials=" +
//							Integer.parseInt(dataSpecials[e.sldData.getValue()]) + " and FileSize='" +
//							dataFileSize[e.sldData.getValue()] + " " + dataFileType[e.sldData.getValue()] + "' and SeasonStatus='" +
//							dataSeriesStatus[e.sldData.getValue()]+ "' and TitleStatus='" +
//							dataTitleStatus[e.sldData.getValue()] + "' and DateFinished='" +
//							dataMonth[e.sldData.getValue()] + " " + dataDay[e.sldData.getValue()] + ", " + dataYear[e.sldData.getValue()] + "' and ReleaseSeason='" +
//							dataRelSeason[e.sldData.getValue()] + " " + dataRelYear[e.sldData.getValue()] + "' and Length='" +
//							dataLengthHr[e.sldData.getValue()] + ":" + dataLengthMin[e.sldData.getValue()] + ":" + dataLengthSec[e.sldData.getValue()] + "'");
//	
//					insert("update animedatabase set Episodes=" + 
//							e.txtEpisodes.getText() + " where Quality='" +
//							dataQuality[e.sldData.getValue()] + "' and AnimeTitle='" +
//							dataTitle[e.sldData.getValue()] + "' and Episodes=" +
//							Integer.parseInt(dataEpisodes[e.sldData.getValue()]) + " and OVAs=" +
//							Integer.parseInt(dataOVAs[e.sldData.getValue()]) + " and Specials=" +
//							Integer.parseInt(dataSpecials[e.sldData.getValue()]) + " and FileSize='" +
//							dataFileSize[e.sldData.getValue()] + " " + dataFileType[e.sldData.getValue()] + "' and SeasonStatus='" +
//							dataSeriesStatus[e.sldData.getValue()]+ "' and TitleStatus='" +
//							dataTitleStatus[e.sldData.getValue()] + "' and DateFinished='" +
//							dataMonth[e.sldData.getValue()] + " " + dataDay[e.sldData.getValue()] + ", " + dataYear[e.sldData.getValue()] + "' and ReleaseSeason='" +
//							dataRelSeason[e.sldData.getValue()] + " " + dataRelYear[e.sldData.getValue()] + "' and Length='" +
//							dataLengthHr[e.sldData.getValue()] + ":" + dataLengthMin[e.sldData.getValue()] + ":" + dataLengthSec[e.sldData.getValue()] + "'");
//	
//					insert("update animedatabase set OVAs=" + 
//							e.txtOVAs.getText() + " where Quality='" +
//							dataQuality[e.sldData.getValue()] + "' and AnimeTitle='" +
//							dataTitle[e.sldData.getValue()] + "' and Episodes=" +
//							Integer.parseInt(dataEpisodes[e.sldData.getValue()]) + " and OVAs=" +
//							Integer.parseInt(dataOVAs[e.sldData.getValue()]) + " and Specials=" +
//							Integer.parseInt(dataSpecials[e.sldData.getValue()]) + " and FileSize='" +
//							dataFileSize[e.sldData.getValue()] + " " + dataFileType[e.sldData.getValue()] + "' and SeasonStatus='" +
//							dataSeriesStatus[e.sldData.getValue()]+ "' and TitleStatus='" +
//							dataTitleStatus[e.sldData.getValue()] + "' and DateFinished='" +
//							dataMonth[e.sldData.getValue()] + " " + dataDay[e.sldData.getValue()] + ", " + dataYear[e.sldData.getValue()] + "' and ReleaseSeason='" +
//							dataRelSeason[e.sldData.getValue()] + " " + dataRelYear[e.sldData.getValue()] + "' and Length='" +
//							dataLengthHr[e.sldData.getValue()] + ":" + dataLengthMin[e.sldData.getValue()] + ":" + dataLengthSec[e.sldData.getValue()] + "'");
//	
//					insert("update animedatabase set Specials=" + 
//							e.txtSpecials.getText() + " where Quality='" +
//							dataQuality[e.sldData.getValue()] + "' and AnimeTitle='" +
//							dataTitle[e.sldData.getValue()] + "' and Episodes=" +
//							Integer.parseInt(dataEpisodes[e.sldData.getValue()]) + " and OVAs=" +
//							Integer.parseInt(dataOVAs[e.sldData.getValue()]) + " and Specials=" +
//							Integer.parseInt(dataSpecials[e.sldData.getValue()]) + " and FileSize='" +
//							dataFileSize[e.sldData.getValue()] + " " + dataFileType[e.sldData.getValue()] + "' and SeasonStatus='" +
//							dataSeriesStatus[e.sldData.getValue()]+ "' and TitleStatus='" +
//							dataTitleStatus[e.sldData.getValue()] + "' and DateFinished='" +
//							dataMonth[e.sldData.getValue()] + " " + dataDay[e.sldData.getValue()] + ", " + dataYear[e.sldData.getValue()] + "' and ReleaseSeason='" +
//							dataRelSeason[e.sldData.getValue()] + " " + dataRelYear[e.sldData.getValue()] + "' and Length='" +
//							dataLengthHr[e.sldData.getValue()] + ":" + dataLengthMin[e.sldData.getValue()] + ":" + dataLengthSec[e.sldData.getValue()] + "'");
//	
//					insert("update animedatabase set FileSize='" + 
//							e.txtFileSize.getText() + " " + e.cbFileType.getSelectedItem().toString() + "' where Quality='" +
//							dataQuality[e.sldData.getValue()] + "' and AnimeTitle='" +
//							dataTitle[e.sldData.getValue()] + "' and Episodes=" +
//							Integer.parseInt(dataEpisodes[e.sldData.getValue()]) + " and OVAs=" +
//							Integer.parseInt(dataOVAs[e.sldData.getValue()]) + " and Specials=" +
//							Integer.parseInt(dataSpecials[e.sldData.getValue()]) + " and FileSize='" +
//							dataFileSize[e.sldData.getValue()] + " " + dataFileType[e.sldData.getValue()] + "' and SeasonStatus='" +
//							dataSeriesStatus[e.sldData.getValue()]+ "' and TitleStatus='" +
//							dataTitleStatus[e.sldData.getValue()] + "' and DateFinished='" +
//							dataMonth[e.sldData.getValue()] + " " + dataDay[e.sldData.getValue()] + ", " + dataYear[e.sldData.getValue()] + "' and ReleaseSeason='" +
//							dataRelSeason[e.sldData.getValue()] + " " + dataRelYear[e.sldData.getValue()] + "' and Length='" +
//							dataLengthHr[e.sldData.getValue()] + ":" + dataLengthMin[e.sldData.getValue()] + ":" + dataLengthSec[e.sldData.getValue()] + "'");
//	
//					insert("update animedatabase set SeasonStatus='" + 
//							e.cbSeriesStatus.getSelectedItem().toString() + "' where Quality='" +
//							dataQuality[e.sldData.getValue()] + "' and AnimeTitle='" +
//							dataTitle[e.sldData.getValue()] + "' and Episodes=" +
//							Integer.parseInt(dataEpisodes[e.sldData.getValue()]) + " and OVAs=" +
//							Integer.parseInt(dataOVAs[e.sldData.getValue()]) + " and Specials=" +
//							Integer.parseInt(dataSpecials[e.sldData.getValue()]) + " and FileSize='" +
//							dataFileSize[e.sldData.getValue()] + " " + dataFileType[e.sldData.getValue()] + "' and SeasonStatus='" +
//							dataSeriesStatus[e.sldData.getValue()]+ "' and TitleStatus='" +
//							dataTitleStatus[e.sldData.getValue()] + "' and DateFinished='" +
//							dataMonth[e.sldData.getValue()] + " " + dataDay[e.sldData.getValue()] + ", " + dataYear[e.sldData.getValue()] + "' and ReleaseSeason='" +
//							dataRelSeason[e.sldData.getValue()] + " " + dataRelYear[e.sldData.getValue()] + "' and Length='" +
//							dataLengthHr[e.sldData.getValue()] + ":" + dataLengthMin[e.sldData.getValue()] + ":" + dataLengthSec[e.sldData.getValue()] + "'");
//					
//					insert("update animedatabase set TitleStatus='" + 
//							e.cbTitleStatus.getSelectedItem().toString() + "' where Quality='" +
//							dataQuality[e.sldData.getValue()] + "' and AnimeTitle='" +
//							dataTitle[e.sldData.getValue()] + "' and Episodes=" +
//							Integer.parseInt(dataEpisodes[e.sldData.getValue()]) + " and OVAs=" +
//							Integer.parseInt(dataOVAs[e.sldData.getValue()]) + " and Specials=" +
//							Integer.parseInt(dataSpecials[e.sldData.getValue()]) + " and FileSize='" +
//							dataFileSize[e.sldData.getValue()] + " " + dataFileType[e.sldData.getValue()] + "' and SeasonStatus='" +
//							dataSeriesStatus[e.sldData.getValue()]+ "' and TitleStatus='" +
//							dataTitleStatus[e.sldData.getValue()] + "' and DateFinished='" +
//							dataMonth[e.sldData.getValue()] + " " + dataDay[e.sldData.getValue()] + ", " + dataYear[e.sldData.getValue()] + "' and ReleaseSeason='" +
//							dataRelSeason[e.sldData.getValue()] + " " + dataRelYear[e.sldData.getValue()] + "' and Length='" +
//							dataLengthHr[e.sldData.getValue()] + ":" + dataLengthMin[e.sldData.getValue()] + ":" + dataLengthSec[e.sldData.getValue()] + "'");
//	
//					insert("update animedatabase set DateFinished='" + 
//							e.cbMonth.getSelectedItem().toString() + " " + e.cbDay.getSelectedItem().toString() + ", " + e.cbYear.getSelectedItem().toString() + "' where Quality='" +
//							dataQuality[e.sldData.getValue()] + "' and AnimeTitle='" +
//							dataTitle[e.sldData.getValue()] + "' and Episodes=" +
//							Integer.parseInt(dataEpisodes[e.sldData.getValue()]) + " and OVAs=" +
//							Integer.parseInt(dataOVAs[e.sldData.getValue()]) + " and Specials=" +
//							Integer.parseInt(dataSpecials[e.sldData.getValue()]) + " and FileSize='" +
//							dataFileSize[e.sldData.getValue()] + " " + dataFileType[e.sldData.getValue()] + "' and SeasonStatus='" +
//							dataSeriesStatus[e.sldData.getValue()]+ "' and TitleStatus='" +
//							dataTitleStatus[e.sldData.getValue()] + "' and DateFinished='" +
//							dataMonth[e.sldData.getValue()] + " " + dataDay[e.sldData.getValue()] + ", " + dataYear[e.sldData.getValue()] + "' and ReleaseSeason='" +
//							dataRelSeason[e.sldData.getValue()] + " " + dataRelYear[e.sldData.getValue()] + "' and Length='" +
//							dataLengthHr[e.sldData.getValue()] + ":" + dataLengthMin[e.sldData.getValue()] + ":" + dataLengthSec[e.sldData.getValue()] + "'");
//	
//					insert("update animedatabase set ReleaseSeason='" + 
//							e.cbReleaseSeason.getSelectedItem().toString() + " " + e.cbReleaseYear.getSelectedItem().toString() + "' where Quality='" +
//							dataQuality[e.sldData.getValue()] + "' and AnimeTitle='" +
//							dataTitle[e.sldData.getValue()] + "' and Episodes=" +
//							Integer.parseInt(dataEpisodes[e.sldData.getValue()]) + " and OVAs=" +
//							Integer.parseInt(dataOVAs[e.sldData.getValue()]) + " and Specials=" +
//							Integer.parseInt(dataSpecials[e.sldData.getValue()]) + " and FileSize='" +
//							dataFileSize[e.sldData.getValue()] + " " + dataFileType[e.sldData.getValue()] + "' and SeasonStatus='" +
//							dataSeriesStatus[e.sldData.getValue()]+ "' and TitleStatus='" +
//							dataTitleStatus[e.sldData.getValue()] + "' and DateFinished='" +
//							dataMonth[e.sldData.getValue()] + " " + dataDay[e.sldData.getValue()] + ", " + dataYear[e.sldData.getValue()] + "' and ReleaseSeason='" +
//							dataRelSeason[e.sldData.getValue()] + " " + dataRelYear[e.sldData.getValue()] + "' and Length='" +
//							dataLengthHr[e.sldData.getValue()] + ":" + dataLengthMin[e.sldData.getValue()] + ":" + dataLengthSec[e.sldData.getValue()] + "'");
//	
//					insert("update animedatabase set Length='" + 
//							e.txtLengthHr.getText() + ":" + e.txtLengthMin.getText() + ":" + e.txtLengthSec.getText() + "' where Quality='" +
//							dataQuality[e.sldData.getValue()] + "' and AnimeTitle='" +
//							dataTitle[e.sldData.getValue()] + "' and Episodes=" +
//							Integer.parseInt(dataEpisodes[e.sldData.getValue()]) + " and OVAs=" +
//							Integer.parseInt(dataOVAs[e.sldData.getValue()]) + " and Specials=" +
//							Integer.parseInt(dataSpecials[e.sldData.getValue()]) + " and FileSize='" +
//							dataFileSize[e.sldData.getValue()] + " " + dataFileType[e.sldData.getValue()] + "' and SeasonStatus='" +
//							dataSeriesStatus[e.sldData.getValue()]+ "' and TitleStatus='" +
//							dataTitleStatus[e.sldData.getValue()] + "' and DateFinished='" +
//							dataMonth[e.sldData.getValue()] + " " + dataDay[e.sldData.getValue()] + ", " + dataYear[e.sldData.getValue()] + "' and ReleaseSeason='" +
//							dataRelSeason[e.sldData.getValue()] + " " + dataRelYear[e.sldData.getValue()] + "' and Length='" +
//							dataLengthHr[e.sldData.getValue()] + ":" + dataLengthMin[e.sldData.getValue()] + ":" + dataLengthSec[e.sldData.getValue()] + "'");
	
					JOptionPane.showMessageDialog(null, "\nThe entry that you selected has been updated\n\nFrom: Maid-chan", 
							"メイド�?�ゃん (Maid-chan)", 0, new ImageIcon(getClass().getResource("img/tray-128x83.png")));
					
					e.cbQuality.setSelectedItem("");
					e.txtTitle.setText("");
					e.txtEpisodes.setText("");
					e.txtOVAs.setText("");
					e.txtSpecials.setText("");
					e.txtFileSize.setText("");
					e.cbFileType.setSelectedItem("");
					e.cbSeriesStatus.setSelectedItem("");
					e.cbTitleStatus.setSelectedItem("");
					e.cbMonth.setSelectedItem("");
					e.cbDay.setSelectedItem("");
					e.cbYear.setSelectedItem("");
					e.cbReleaseSeason.setSelectedItem("");
					e.cbReleaseYear.setSelectedItem("");
					e.txtLengthHr.setText("");
					e.txtLengthMin.setText("");
					e.txtLengthSec.setText("");
					
					e.cbQuality.setEnabled(false);
					e.txtTitle.setEditable(false);
					e.txtEpisodes.setEditable(false);
					e.txtOVAs.setEditable(false);
					e.txtSpecials.setEditable(false);
					e.txtFileSize.setEditable(false);
					e.cbFileType.setEnabled(false);
					e.cbSeriesStatus.setEnabled(false);
					e.cbTitleStatus.setEnabled(false);
					e.cbMonth.setEnabled(false);
					e.cbDay.setEnabled(false);
					e.cbYear.setEnabled(false);
					e.cbReleaseSeason.setEnabled(false);
					e.cbReleaseYear.setEnabled(false);
					e.txtLengthHr.setEditable(false);
					e.txtLengthMin.setEditable(false);
					e.txtLengthSec.setEditable(false);
					
					e.btnReset.setEnabled(false);
					e.btnPreview.setEnabled(false);
					e.btnSubmit.setEnabled(false);
				}
			} else if (arg0.getSource().equals(e.btnReset)) {
				if (JOptionPane.showConfirmDialog(null, "\nHey!\nI will be resetting the fields to their default values." +
						"\nAre you sure of this?\n\nFrom: Maid-chan\n\n", 
						"メイド�?�ゃん (Maid-chan)", JOptionPane.OK_CANCEL_OPTION, 0, 
						new ImageIcon(getClass().getResource("img/tray-128x83.png"))) == 0) {
				
					e.cbQuality.setSelectedItem(dataQuality[e.sldData.getValue()]);
					e.txtTitle.setText(dataTitle[e.sldData.getValue()]);
					e.txtEpisodes.setText(dataEpisodes[e.sldData.getValue()]);
					e.txtOVAs.setText(dataOVAs[e.sldData.getValue()]);
					e.txtSpecials.setText(dataSpecials[e.sldData.getValue()]);
					e.txtFileSize.setText(dataFileSize[e.sldData.getValue()]);
					e.cbFileType.setSelectedItem(dataFileType[e.sldData.getValue()]);
					e.cbSeriesStatus.setSelectedItem(dataSeriesStatus[e.sldData.getValue()]);
					e.cbTitleStatus.setSelectedItem(dataTitleStatus[e.sldData.getValue()]);
					e.cbMonth.setSelectedItem(dataMonth[e.sldData.getValue()]);
					e.cbDay.setSelectedItem(dataDay[e.sldData.getValue()]);
					e.cbYear.setSelectedItem(dataYear[e.sldData.getValue()]);
					e.cbReleaseSeason.setSelectedItem(dataRelSeason[e.sldData.getValue()]);
					e.cbReleaseYear.setSelectedItem(dataRelYear[e.sldData.getValue()]);
					e.txtLengthHr.setText(dataLengthHr[e.sldData.getValue()]);
					e.txtLengthMin.setText(dataLengthMin[e.sldData.getValue()]);
					e.txtLengthSec.setText(dataLengthSec[e.sldData.getValue()]);
					
				}
			} else if (arg0.getSource().equals(e.btnPreview)) {
				
			}
		}

		@Override
		public void stateChanged(ChangeEvent arg0) {
			if (arg0.getSource().equals(e.sldData)) {

//				System.out.println(e.sldData.getMaximum());
				
				e.cbQuality.setSelectedItem(dataQuality[e.sldData.getValue()]);
				e.txtTitle.setText(dataTitle[e.sldData.getValue()]);
				e.txtEpisodes.setText(dataEpisodes[e.sldData.getValue()]);
				e.txtOVAs.setText(dataOVAs[e.sldData.getValue()]);
				e.txtSpecials.setText(dataSpecials[e.sldData.getValue()]);
				e.txtFileSize.setText(dataFileSize[e.sldData.getValue()]);
				e.cbFileType.setSelectedItem(dataFileType[e.sldData.getValue()]);
				e.cbSeriesStatus.setSelectedItem(dataSeriesStatus[e.sldData.getValue()]);
				e.cbTitleStatus.setSelectedItem(dataTitleStatus[e.sldData.getValue()]);
				e.cbMonth.setSelectedItem(dataMonth[e.sldData.getValue()]);
				e.cbDay.setSelectedItem(dataDay[e.sldData.getValue()]);
				e.cbYear.setSelectedItem(dataYear[e.sldData.getValue()]);
				e.cbReleaseSeason.setSelectedItem(dataRelSeason[e.sldData.getValue()]);
				e.cbReleaseYear.setSelectedItem(dataRelYear[e.sldData.getValue()]);
				e.txtLengthHr.setText(dataLengthHr[e.sldData.getValue()]);
				e.txtLengthMin.setText(dataLengthMin[e.sldData.getValue()]);
				e.txtLengthSec.setText(dataLengthSec[e.sldData.getValue()]);
				
//				if (e.cbQuality.getSelectedItem().equals("") && 
//						e.txtTitle.getText().equals("") &&
//						e.txtEpisodes.getText().equals("") &&
//						e.txtOVAs.getText().equals("") &&
//						e.txtSpecials.getText().equals("") &&
//						e.txtFileSize.getText().equals("") &&
//						e.cbFileType.getSelectedItem().equals("") &&
//						e.cbSeriesStatus.getSelectedItem().equals("") &&
//						e.cbTitleStatus.getSelectedItem().equals("") &&
//						e.cbMonth.getSelectedItem().equals("") &&
//						e.cbDay.getSelectedItem().equals("") &&
//						e.cbYear.getSelectedItem().equals("") &&
//						e.cbReleaseSeason.getSelectedItem().equals("") &&
//						e.cbReleaseYear.getSelectedItem().equals("") &&
//						e.txtLengthHr.getText().equals("") &&
//						e.txtLengthMin.getText().equals("") &&
//						e.txtLengthSec.getText().equals("")) {
//					
//					System.out.println(1);
//					
//					if ((e.cbQuality.getSelectedItem().toString()).equals(dataQuality[sliderValue]) &&
//							e.txtTitle.getText().equals(dataTitle[sliderValue]) &&
//							e.txtEpisodes.getText().equals(dataEpisodes[sliderValue]) &&
//							e.txtOVAs.getText().equals(dataOVAs[sliderValue]) &&
//							e.txtSpecials.getText().equals(dataSpecials[sliderValue]) &&
//							e.txtFileSize.getText().equals(dataFileSize[sliderValue]) &&
//							e.cbFileType.getSelectedItem().toString().equals(dataFileType[sliderValue]) &&
//							e.cbSeriesStatus.getSelectedItem().toString().equals(dataSeriesStatus[sliderValue]) &&
//							e.cbTitleStatus.getSelectedItem().toString().equals(dataTitleStatus[sliderValue]) &&
//							e.cbMonth.getSelectedItem().toString().equals(dataMonth[sliderValue]) &&
//							e.cbDay.getSelectedItem().toString().equals(dataDay[sliderValue]) &&
//							e.cbYear.getSelectedItem().toString().equals(dataYear[sliderValue]) &&
//							e.cbReleaseSeason.getSelectedItem().toString().equals(dataRelSeason[sliderValue]) &&
//							e.cbReleaseYear.getSelectedItem().toString().equals(dataRelYear[sliderValue]) &&
//							e.txtLengthHr.getText().equals(dataLengthHr[sliderValue]) &&
//							e.txtLengthMin.getText().equals(dataLengthMin[sliderValue]) &&
//							e.txtLengthSec.getText().equals(dataLengthSec[sliderValue])) {
//						
//
//						e.cbQuality.setSelectedItem(dataQuality[e.sldData.getValue()]);
//						e.txtTitle.setText(dataTitle[e.sldData.getValue()]);
//						e.txtEpisodes.setText(dataEpisodes[e.sldData.getValue()]);
//						e.txtOVAs.setText(dataOVAs[e.sldData.getValue()]);
//						e.txtSpecials.setText(dataSpecials[e.sldData.getValue()]);
//						e.txtFileSize.setText(dataFileSize[e.sldData.getValue()]);
//						e.cbFileType.setSelectedItem(dataFileType[e.sldData.getValue()]);
//						e.cbSeriesStatus.setSelectedItem(dataSeriesStatus[e.sldData.getValue()]);
//						e.cbTitleStatus.setSelectedItem(dataTitleStatus[e.sldData.getValue()]);
//						e.cbMonth.setSelectedItem(dataMonth[e.sldData.getValue()]);
//						e.cbDay.setSelectedItem(dataDay[e.sldData.getValue()]);
//						e.cbYear.setSelectedItem(dataYear[e.sldData.getValue()]);
//						e.cbReleaseSeason.setSelectedItem(dataRelSeason[e.sldData.getValue()]);
//						e.cbReleaseYear.setSelectedItem(dataRelYear[e.sldData.getValue()]);
//						e.txtLengthHr.setText(dataLengthHr[e.sldData.getValue()]);
//						e.txtLengthMin.setText(dataLengthMin[e.sldData.getValue()]);
//						e.txtLengthSec.setText(dataLengthSec[e.sldData.getValue()]);
//						
//					} else {
//						
//						if (JOptionPane.showConfirmDialog(null, "\nHey! Based on your actions I will now be moving to the next values.\nAny unsaved changes will not be saved." +
//								"\nAre you sure of this?\n\nFrom: Maid-chan\n\n", 
//								"メイド�?�ゃん (Maid-chan)", JOptionPane.OK_CANCEL_OPTION, 0, 
//								new ImageIcon(getClass().getResource("img/tray-128x83.png"))) == 0) {
//						
//
//							e.cbQuality.setSelectedItem(dataQuality[e.sldData.getValue()]);
//							e.txtTitle.setText(dataTitle[e.sldData.getValue()]);
//							e.txtEpisodes.setText(dataEpisodes[e.sldData.getValue()]);
//							e.txtOVAs.setText(dataOVAs[e.sldData.getValue()]);
//							e.txtSpecials.setText(dataSpecials[e.sldData.getValue()]);
//							e.txtFileSize.setText(dataFileSize[e.sldData.getValue()]);
//							e.cbFileType.setSelectedItem(dataFileType[e.sldData.getValue()]);
//							e.cbSeriesStatus.setSelectedItem(dataSeriesStatus[e.sldData.getValue()]);
//							e.cbTitleStatus.setSelectedItem(dataTitleStatus[e.sldData.getValue()]);
//							e.cbMonth.setSelectedItem(dataMonth[e.sldData.getValue()]);
//							e.cbDay.setSelectedItem(dataDay[e.sldData.getValue()]);
//							e.cbYear.setSelectedItem(dataYear[e.sldData.getValue()]);
//							e.cbReleaseSeason.setSelectedItem(dataRelSeason[e.sldData.getValue()]);
//							e.cbReleaseYear.setSelectedItem(dataRelYear[e.sldData.getValue()]);
//							e.txtLengthHr.setText(dataLengthHr[e.sldData.getValue()]);
//							e.txtLengthMin.setText(dataLengthMin[e.sldData.getValue()]);
//							e.txtLengthSec.setText(dataLengthSec[e.sldData.getValue()]);
//							
//						} else {
//							e.sldData.setValue(sliderValue);
//						}
//					}	
//				}
			}
		}

		@Override
		public void keyPressed(KeyEvent arg0) { }

		@SuppressWarnings("resource")
		@Override
		public void keyReleased(KeyEvent arg0) {
//			sliderValue = e.sldData.getValue();
			
			if (arg0.getSource().equals(e.txtTitle)) {
				e.txtTitle.setToolTipText(e.txtTitle.getText());
			} else if (arg0.getSource().equals(e.txtSearch)) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					connectToDatabase();
					int counter = 0;

					ResultSet rsNew = null;
					
					if (cbSearch.getSelectedIndex() == 0 && txtSearch.getText().equals("")) {
						try {
							ResultSet rs = retrieve("select * from animedatabase");

							while (rs.next()) { counter++; }
							
							dataQuality = new String[counter];
							dataTitle = new String[counter];
							dataEpisodes = new String[counter];
							dataOVAs = new String[counter];
							dataSpecials = new String[counter];
							dataFileSize = new String[counter];
							dataFileType = new String[counter];
							dataSeriesStatus = new String[counter];
							dataTitleStatus = new String[counter];
							dataMonth = new String[counter];
							dataDay = new String[counter];
							dataYear = new String[counter];
							dataRelSeason = new String[counter];
							dataRelYear = new String[counter];
							dataLengthHr = new String[counter];
							dataLengthMin = new String[counter];
							dataLengthSec = new String[counter];
						} catch (SQLException e) { System.err.println(e.getMessage());}
						
						rsNew = retrieve("select * from animedatabase");
						
					} else if (cbSearch.getSelectedIndex() == 1 || cbSearch.getSelectedIndex() == 2 || cbSearch.getSelectedIndex() == 3 || cbSearch.getSelectedIndex() == 4) {
						if (txtSearch.getText().equals("") || txtSearch.getText().equals(null)) {
							try {
								ResultSet rs = retrieve("select * from animedatabase where Quality='" + cbSearch.getSelectedItem().toString() + "'");

								while (rs.next()) { counter++; }
								
								dataQuality = new String[counter];
								dataTitle = new String[counter];
								dataEpisodes = new String[counter];
								dataOVAs = new String[counter];
								dataSpecials = new String[counter];
								dataFileSize = new String[counter];
								dataFileType = new String[counter];
								dataSeriesStatus = new String[counter];
								dataTitleStatus = new String[counter];
								dataMonth = new String[counter];
								dataDay = new String[counter];
								dataYear = new String[counter];
								dataRelSeason = new String[counter];
								dataRelYear = new String[counter];
								dataLengthHr = new String[counter];
								dataLengthMin = new String[counter];
								dataLengthSec = new String[counter];
							} catch (SQLException e) { System.err.println(e.getMessage()); }
							
							rsNew = retrieve("select * from animedatabase where Quality='" + cbSearch.getSelectedItem().toString() + "'");
						} else {
							try {
								ResultSet rs = retrieve("select * from animedatabase where Quality='" + cbSearch.getSelectedItem().toString() + "' and AnimeTitle='" + txtSearch.getText() + "'");
								
								while (rs.next()) { counter++; }
								
								dataQuality = new String[counter];
								dataTitle = new String[counter];
								dataEpisodes = new String[counter];
								dataOVAs = new String[counter];
								dataSpecials = new String[counter];
								dataFileSize = new String[counter];
								dataFileType = new String[counter];
								dataSeriesStatus = new String[counter];
								dataTitleStatus = new String[counter];
								dataMonth = new String[counter];
								dataDay = new String[counter];
								dataYear = new String[counter];
								dataRelSeason = new String[counter];
								dataRelYear = new String[counter];
								dataLengthHr = new String[counter];
								dataLengthMin = new String[counter];
								dataLengthSec = new String[counter];
							} catch (SQLException e) {
								System.err.println(e.getMessage());
							}
							
							rsNew = retrieve("select * from animedatabase where Quality='" + cbSearch.getSelectedItem().toString() + "' and AnimeTitle='" + txtSearch.getText() + "'");
						}
						
					} else if (e.cbSearch.getSelectedItem().equals("") || e.cbSearch.getSelectedItem().equals(null)) {
						if (!e.txtSearch.getText().equals("") || !e.txtSearch.getText().equals(null)) {
							try {
								ResultSet rs = retrieve("select * from animedatabase where AnimeTitle='" + e.txtSearch.getText() + "'");
								
								while (rs.next()) { counter++; }
								
								dataQuality = new String[counter];
								dataTitle = new String[counter];
								dataEpisodes = new String[counter];
								dataOVAs = new String[counter];
								dataSpecials = new String[counter];
								dataFileSize = new String[counter];
								dataFileType = new String[counter];
								dataSeriesStatus = new String[counter];
								dataTitleStatus = new String[counter];
								dataMonth = new String[counter];
								dataDay = new String[counter];
								dataYear = new String[counter];
								dataRelSeason = new String[counter];
								dataRelYear = new String[counter];
								dataLengthHr = new String[counter];
								dataLengthMin = new String[counter];
								dataLengthSec = new String[counter];
							} catch (SQLException e) { System.err.println(e.getMessage()); }	
							rsNew = retrieve("select * from animedatabase where AnimeTitle='" + txtSearch.getText() + "'");		
						}
					}
					
					if (counter != 0) {
						try {
							int tmpCtr = 0;
							while (rsNew.next()) {
								try { dataQuality[tmpCtr] = rsNew.getString("Quality"); } catch (StringIndexOutOfBoundsException StrOB) { dataQuality[tmpCtr] = ""; }
								try { dataTitle[tmpCtr] = rsNew.getString("AnimeTitle"); } catch (StringIndexOutOfBoundsException StrOB) { dataTitle[tmpCtr] = ""; }
								try { dataEpisodes[tmpCtr] = "" + rsNew.getInt("Episodes"); } catch (StringIndexOutOfBoundsException StrOB) { dataEpisodes[tmpCtr] = ""; }
								try { dataOVAs[tmpCtr] = "" + rsNew.getInt("OVAs"); } catch (StringIndexOutOfBoundsException StrOB) { dataOVAs[tmpCtr] = ""; }
								try { dataSpecials[tmpCtr] = "" + rsNew.getInt("Specials"); } catch (StringIndexOutOfBoundsException StrOB) { dataSpecials[tmpCtr] = ""; }
								
								try {
									String tempFileSize = rsNew.getString("FileSize");
									dataFileSize[tmpCtr] = tempFileSize.substring(0, tempFileSize.indexOf(" "));
									dataFileType[tmpCtr] = tempFileSize.substring((tempFileSize.indexOf(" ")+1), tempFileSize.length());
								} catch (StringIndexOutOfBoundsException StrOB) { 
									dataFileSize[tmpCtr] = "";
									dataFileType[tmpCtr] = "";
								}
								
								try { dataSeriesStatus[tmpCtr] = rsNew.getString("SeasonStatus"); } catch (StringIndexOutOfBoundsException StrOB) { dataSeriesStatus[tmpCtr] = ""; }
								try { dataTitleStatus[tmpCtr] = rsNew.getString("TitleStatus"); } catch (StringIndexOutOfBoundsException StrOB) { dataTitleStatus[tmpCtr] = ""; }
								try { dataMonth[tmpCtr] = rsNew.getString("DateFinished").split(" ")[0]; } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException StrOB) { dataMonth[tmpCtr] = ""; }
								try {dataDay[tmpCtr] = (rsNew.getString("DateFinished").split(" ")[1]).substring(0, 2);} catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException StrOB) { dataDay[tmpCtr] = ""; }
								try {dataYear[tmpCtr] = rsNew.getString("DateFinished").split(" ")[2];} catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException StrOB) { dataYear[tmpCtr] = ""; }
								try { dataRelSeason[tmpCtr] = rsNew.getString("ReleaseSeason").split(" ")[0]; } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException StrOB) { dataRelSeason[tmpCtr] = ""; }
								try { dataRelYear[tmpCtr] = rsNew.getString("ReleaseSeason").split(" ")[1];} catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException StrOB) { dataRelYear[tmpCtr] = ""; }
								try { dataLengthHr[tmpCtr] = rsNew.getString("Length").split(":")[0];} catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException StrOB) { dataLengthHr[tmpCtr] = ""; }
								try {dataLengthMin[tmpCtr] = rsNew.getString("Length").split(":")[1];} catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException StrOB) { dataLengthMin[tmpCtr] = ""; }
								try { dataLengthSec[tmpCtr] = rsNew.getString("Length").split(":")[2];} catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException StrOB) { dataLengthSec[tmpCtr] = ""; }
								tmpCtr++;
							}
							
						} catch (SQLException e) { System.err.println(e.getMessage()); }
						
						try {
							if (!dataQuality[0].equals(null) || !dataQuality[0].equals("")
									|| !dataTitle[0].equals(null) || !dataTitle[0].equals("")
									|| !dataEpisodes[0].equals(null) || !dataEpisodes[0].equals("")
									|| !dataOVAs[0].equals(null) || !dataOVAs[0].equals("")
									|| !dataSpecials[0].equals(null) || !dataSpecials[0].equals("")
									|| !dataFileSize[0].equals(null) || !dataFileSize[0].equals("")
									|| !dataFileType[0].equals(null) || !dataFileType[0].equals("")
									|| !dataSeriesStatus[0].equals(null) || !dataSeriesStatus[0].equals("")
									|| !dataTitleStatus[0].equals(null) || !dataTitleStatus[0].equals("")
									|| !dataMonth[0].equals(null) || !dataMonth[0].equals("")
									|| !dataDay[0].equals(null) || !dataDay[0].equals("")
									|| !dataYear[0].equals(null) || !dataYear[0].equals("")
									|| !dataRelSeason[0].equals(null) || !dataRelSeason[0].equals("")
									|| !dataRelYear[0].equals(null) || !dataRelYear[0].equals("")
									|| !dataLengthHr[0].equals(null) || !dataLengthHr[0].equals("")
									|| !dataLengthMin[0].equals(null) || !dataLengthMin[0].equals("")
									|| !dataLengthSec[0].equals(null) || !dataLengthSec[0].equals("")) {
								
								e.sldData.setEnabled(true);
								e.sldData.setMaximum(counter-1);
								e.sldData.setValue(0);
								
								e.cbQuality.setSelectedItem(dataQuality[e.sldData.getValue()]);
								e.txtTitle.setText(dataTitle[e.sldData.getValue()]);
								e.txtEpisodes.setText(dataEpisodes[e.sldData.getValue()]);
								e.txtOVAs.setText(dataOVAs[e.sldData.getValue()]);
								e.txtSpecials.setText(dataSpecials[e.sldData.getValue()]);
								e.txtFileSize.setText(dataFileSize[e.sldData.getValue()]);
								e.cbFileType.setSelectedItem(dataFileType[e.sldData.getValue()]);
								e.cbSeriesStatus.setSelectedItem(dataSeriesStatus[e.sldData.getValue()]);
								e.cbTitleStatus.setSelectedItem(dataTitleStatus[e.sldData.getValue()]);
								e.cbMonth.setSelectedItem(dataMonth[e.sldData.getValue()]);
								e.cbDay.setSelectedItem(dataDay[e.sldData.getValue()]);
								e.cbYear.setSelectedItem(dataYear[e.sldData.getValue()]);
								e.cbReleaseSeason.setSelectedItem(dataRelSeason[e.sldData.getValue()]);
								e.cbReleaseYear.setSelectedItem(dataRelYear[e.sldData.getValue()]);
								e.txtLengthHr.setText(dataLengthHr[e.sldData.getValue()]);
								e.txtLengthMin.setText(dataLengthMin[e.sldData.getValue()]);
								e.txtLengthSec.setText(dataLengthSec[e.sldData.getValue()]);
								
								e.cbQuality.setEnabled(true);
								e.txtTitle.setEditable(true);
								e.txtEpisodes.setEditable(true);
								e.txtOVAs.setEditable(true);
								e.txtSpecials.setEditable(true);
								e.txtFileSize.setEditable(true);
								e.cbFileType.setEnabled(true);
								e.cbSeriesStatus.setEnabled(true);
								e.cbTitleStatus.setEnabled(true);
								e.cbMonth.setEnabled(true);
								e.cbDay.setEnabled(true);
								e.cbYear.setEnabled(true);
								e.cbReleaseSeason.setEnabled(true);
								e.cbReleaseYear.setEnabled(true);
								e.txtLengthHr.setEditable(true);
								e.txtLengthMin.setEditable(true);
								e.txtLengthSec.setEditable(true);
								
								e.btnReset.setEnabled(true);
								e.btnPreview.setEnabled(true);
								e.btnSubmit.setEnabled(true);
							}
						} catch (ArrayIndexOutOfBoundsException AIndexOB) { }
					} else {
						
						e.cbQuality.setSelectedItem("");
						e.txtTitle.setText("");
						e.txtEpisodes.setText("");
						e.txtOVAs.setText("");
						e.txtSpecials.setText("");
						e.txtFileSize.setText("");
						e.cbFileType.setSelectedItem("");
						e.cbSeriesStatus.setSelectedItem("");
						e.cbTitleStatus.setSelectedItem("");
						e.cbMonth.setSelectedItem("");
						e.cbDay.setSelectedItem("");
						e.cbYear.setSelectedItem("");
						e.cbReleaseSeason.setSelectedItem("");
						e.cbReleaseYear.setSelectedItem("");
						e.txtLengthHr.setText("");
						e.txtLengthMin.setText("");
						e.txtLengthSec.setText("");
						
						e.cbQuality.setEnabled(false);
						e.txtTitle.setEditable(false);
						e.txtEpisodes.setEditable(false);
						e.txtOVAs.setEditable(false);
						e.txtSpecials.setEditable(false);
						e.txtFileSize.setEditable(false);
						e.cbFileType.setEnabled(false);
						e.cbSeriesStatus.setEnabled(false);
						e.cbTitleStatus.setEnabled(false);
						e.cbMonth.setEnabled(false);
						e.cbDay.setEnabled(false);
						e.cbYear.setEnabled(false);
						e.cbReleaseSeason.setEnabled(false);
						e.cbReleaseYear.setEnabled(false);
						e.txtLengthHr.setEditable(false);
						e.txtLengthMin.setEditable(false);
						e.txtLengthSec.setEditable(false);
						
						e.btnReset.setEnabled(false);
						e.btnPreview.setEnabled(false);
						e.btnSubmit.setEnabled(false);
					}
				}
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) { }

		@Override
		public void itemStateChanged(ItemEvent arg0) {
//			sliderValue = e.sldData.getValue();
		}
		
		@Override
		public void focusGained(FocusEvent arg0) { }

		@Override
		public void focusLost(FocusEvent arg0) {
			
			if (arg0.getSource().equals(e.txtTitle)) {
				String unmodifiedTitle = e.txtTitle.getText();
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
				e.txtTitle.setText(modifiedTitle);
				e.txtTitle.setToolTipText(e.txtTitle.getText());
				
			} else if (arg0.getSource().equals(e.txtFileSize)) {
				e.txtFileSize.setText(e.txtFileSize.getText().replaceAll("[^0-9.]", ""));
			} else if (arg0.getSource().equals(e.txtLengthHr)) {
				try {
					if (Integer.parseInt(e.txtLengthHr.getText()) > 10) {
						e.txtLengthHr.setText("0" + e.txtLengthHr.getText());
					}
				} catch (NumberFormatException e) { }
			} else if (arg0.getSource().equals(e.txtLengthMin)) {
				
				try {
					if (Integer.parseInt(e.txtLengthMin.getText()) > 59) {
						try { 
							e.txtLengthHr.setText(((Integer.parseInt(e.txtLengthHr.getText())) + (Integer.parseInt(e.txtLengthMin.getText()) / 60)) + "");
						} catch (NumberFormatException ex) { 
							e.txtLengthHr.setText((Integer.parseInt(e.txtLengthMin.getText()) / 60) + ""); 
						}
						e.txtLengthMin.setText((Integer.parseInt(e.txtLengthMin.getText()) % 60) + "");
					}
					
					if (Integer.parseInt(e.txtLengthMin.getText()) > 10) {
						e.txtLengthMin.setText("0" + e.txtLengthMin.getText());
					}
				} catch(NumberFormatException ex) { }
				
			} else if (arg0.getSource().equals(e.txtLengthSec)) {
				
				try {
					if (Integer.parseInt(e.txtLengthSec.getText()) > 59) {
						try { 
							e.txtLengthMin.setText(((Integer.parseInt(e.txtLengthMin.getText())) + (Integer.parseInt(e.txtLengthSec.getText()) / 60)) + "");
						} catch (NumberFormatException ex) { 
							e.txtLengthMin.setText((Integer.parseInt(e.txtLengthSec.getText()) / 60) + ""); 
						}
						e.txtLengthSec.setText((Integer.parseInt(e.txtLengthSec.getText()) % 60) + "");
					}
					
					if (Integer.parseInt(e.txtLengthSec.getText()) > 10) {
						e.txtLengthSec.setText("0" + e.txtLengthSec.getText());
					}
				} catch(NumberFormatException ex) { }
			}
		}
		
		/*private void updateItems() {
			// TODO
			String[] updDataVals = {"Quality", 
									"AnimeTitle", 
									"Episodes", 
									"OVAs", 
									"Specials", 
									"FileSize", 
									"SeasonStatus", 
									"TitleStatus", 
									"DateFinished", 
									"ReleaseSeason", 
									"Length"};
			
			String[] updDataComp = {e.cbQuality.getSelectedItem().toString(),
									e.txtEpisodes.getText(),
									e.txtEpisodes.getText(),
									e.txtOVAs.getText(),
									e.txtSpecials.getText(),
									e.txtFileSize.getText() + " " + e.cbFileType.getSelectedItem().toString(),
									e.cbSeriesStatus.getSelectedItem().toString(),
									e.cbTitleStatus.getSelectedItem().toString(),
									e.cbMonth.getSelectedItem().toString() + " " + e.cbDay.getSelectedItem().toString() + ", " + e.cbYear.getSelectedItem().toString(),
									e.cbReleaseSeason.getSelectedItem().toString() + " " + e.cbReleaseYear.getSelectedItem().toString(),
									e.txtLengthHr.getText() + ":" + e.txtLengthMin.getText() + ":" + e.txtLengthSec.getText()};
			
			for (int i = 0; i < updDataVals.length; i++) {
				insert("update animedatabase set " + updDataVals[i] + "='" + 
						updDataVals[i] + "' where Quality='" +
						dataQuality[e.sldData.getValue()] + "' and AnimeTitle='" +
						dataTitle[e.sldData.getValue()] + "' and Episodes=" +
						Integer.parseInt(dataEpisodes[e.sldData.getValue()]) + " and OVAs=" +
						Integer.parseInt(dataOVAs[e.sldData.getValue()]) + " and Specials=" +
						Integer.parseInt(dataSpecials[e.sldData.getValue()]) + " and FileSize='" +
						dataFileSize[e.sldData.getValue()] + " " + dataFileType[e.sldData.getValue()] + "' and SeasonStatus='" +
						dataSeriesStatus[e.sldData.getValue()]+ "' and TitleStatus='" +
						dataTitleStatus[e.sldData.getValue()] + "' and DateFinished='" +
						dataMonth[e.sldData.getValue()] + " " + dataDay[e.sldData.getValue()] + ", " + dataYear[e.sldData.getValue()] + "' and ReleaseSeason='" +
						dataRelSeason[e.sldData.getValue()] + " " + dataRelYear[e.sldData.getValue()] + "' and Length='" +
						dataLengthHr[e.sldData.getValue()] + ":" + dataLengthMin[e.sldData.getValue()] + ":" + dataLengthSec[e.sldData.getValue()] + "'");
			}	
		}*/
		
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
		
		private void connectToDatabase() {
			
			String DBDriver = "com.mysql.jdbc.Driver";
			String DBUrl = "jdbc:mysql://127.0.0.1/";
			String DBName = "anidb";
			String DBUser = "root";
			String DBPass = "root";
			
			try { Class.forName(DBDriver); } catch (ClassNotFoundException ex) { System.err.println(ex.getMessage()); }
			
			try {
				con = DriverManager.getConnection(DBUrl + DBName, DBUser, DBPass);
				stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			} catch (SQLException ex) { System.err.println(ex.getMessage()); }
		}
		
		private int insert(String command) {
			try {
				return stm.executeUpdate(command);
			} catch(SQLException ex) {
				System.err.println(ex.getMessage());
				return 0;
			}
		}
		
		private ResultSet retrieve(String command) {
			try {
				ResultSet rs = stm.executeQuery(command);
				return rs;
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
				return null;
			}
		}
	}
}
