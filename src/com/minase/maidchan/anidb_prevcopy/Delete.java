package com.minase.maidchan.anidb_prevcopy;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


public class Delete extends JFrame {

	private static final long serialVersionUID = 5977872553474249302L;
	
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
	
	private JButton //btnReset,
					//btnPreview,
					btnSubmit,
					btnSearch;
	
	private JSlider sldData;
	
	private DelActionListener action;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delete frame = new Delete();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Delete() {
		
		action = new DelActionListener(this);
		
		setTitle("Anime Database -- Delete");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 265);
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
		pnlEpisodeNumbers.add(txtEpisodes);
		txtEpisodes.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtOVAs = new JTextField();
		txtOVAs.setEditable(false);
		txtOVAs.setToolTipText("OVAs");
		txtOVAs.setHorizontalAlignment(SwingConstants.CENTER);
		pnlEpisodeNumbers.add(txtOVAs);
		
		txtSpecials = new JTextField();
		txtSpecials.setEditable(false);
		txtSpecials.setToolTipText("Specials");
		txtSpecials.setHorizontalAlignment(SwingConstants.CENTER);
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
		pnlLength.add(txtLengthHr);
		
		txtLengthMin = new JTextField();
		txtLengthMin.setEditable(false);
		txtLengthMin.setToolTipText("Length:Minutes");
		pnlLength.add(txtLengthMin);
		
		txtLengthSec = new JTextField();
		txtLengthSec.setEditable(false);
		txtLengthSec.setToolTipText("Length:Seconds");
		pnlLength.add(txtLengthSec);
		
//		btnReset = new JButton("Reset");
//		btnReset.setEnabled(false);
//		btnReset.setBounds(166, 165, 70, 20);
//		contentPane.add(btnReset);
//		
//		btnPreview = new JButton("Preview");
//		btnPreview.setEnabled(false);
//		btnPreview.setBounds(246, 165, 90, 20);
//		contentPane.add(btnPreview);
		
		btnSubmit = new JButton("Delete Data");
		btnSubmit.setEnabled(false);
		btnSubmit.setBounds(166, 165, 170, 20);
//		btnSubmit.setBounds(6, 196, 150, 25);
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
		sldData.setValue(1);
		sldData.setMaximum(2);
		sldData.setEnabled(false);
		sldData.setBounds(286, 38, 50, 23);
		contentPane.add(sldData);
		
		/**
		 * ADDING LISTENERS
		 */
//		btnReset.addActionListener(action);
		btnSubmit.addActionListener(action);
		btnSearch.addActionListener(action);
		sldData.addChangeListener(action);
	}
	
	private class DelActionListener implements ActionListener, ChangeListener {

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
		
		Delete d;
		
		Connection con;
		Statement stm;
		
		public DelActionListener(Delete d) {
			this.d = d;
		}
		
		@SuppressWarnings("resource")
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource().equals(d.btnSearch)) {
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
							
							System.out.println("select * from animedatabase where Quality='" + cbSearch.getSelectedItem().toString() + "' and AnimeTitle='" + txtSearch.getText() + "'");

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
					
				} else if (cbSearch.getSelectedItem().equals("") || cbSearch.getSelectedItem().equals(null)) {
					try {
						ResultSet rs = retrieve("select * from animedatabase where AnimeTitle='" + txtSearch.getText() + "'");
						
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
					
					rsNew = retrieve("select * from animedatabase where AnimeTitle='" + txtSearch.getText() + "'");
				}		

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
						
						sldData.setEnabled(true);
						sldData.setMaximum(counter-1);
						sldData.setValue(0);
						btnSubmit.setEnabled(true);
					}
				} catch (ArrayIndexOutOfBoundsException AIndexOB) { }
				
			} else if (arg0.getSource().equals(d.btnSubmit)) {
				
				if (JOptionPane.showConfirmDialog(null, "\nHey! I will be deleting the Anime '" + d.txtTitle.getText() + "' from the database." +
						"\nAre you sure of this?\nYou won't be able to undo your action once I have already deleted it.\n\nFrom: Maid-chan\n\n", 
						"メイド�?�ゃん (Maid-chan)", JOptionPane.YES_NO_OPTION, 0, 
						new ImageIcon(getClass().getResource("img/tray-128x83.png"))) == 0) {
					
					insert("delete from animedatabase where Quality='" +
							d.cbQuality.getSelectedItem().toString() + "' and AnimeTitle='" +
							d.txtTitle.getText() + "' and Episodes=" +
							Integer.parseInt(d.txtEpisodes.getText()) + " and OVAs=" +
							Integer.parseInt(d.txtOVAs.getText()) + " and Specials=" +
							Integer.parseInt(d.txtSpecials.getText()) + " and FileSize='" +
							d.txtFileSize.getText() + " " + d.cbFileType.getSelectedItem().toString() + "' and SeasonStatus='" +
							d.cbSeriesStatus.getSelectedItem().toString() + "' and TitleStatus='" +
							d.cbTitleStatus.getSelectedItem().toString() + "' and DateFinished='" +
							d.cbMonth.getSelectedItem().toString() + " " + d.cbDay.getSelectedItem().toString() + ", " + d.cbYear.getSelectedItem().toString() + "' and ReleaseSeason='" +
							d.cbReleaseSeason.getSelectedItem().toString() + " " + d.cbReleaseYear.getSelectedItem().toString() + "' and Length='" +
							d.txtLengthHr.getText() + ":" + d.txtLengthMin.getText() + ":" + d.txtLengthSec.getText() + "'");
					
					JOptionPane.showMessageDialog(null, "\nThe entry that you selected has been deleted\nAnd will never be recovered again!\n\nFrom: Maid-chan", 
							"メイド�?�ゃん (Maid-chan)", 0, new ImageIcon(getClass().getResource("img/tray-128x83.png")));
					
					d.cbQuality.setSelectedItem("");
					d.txtTitle.setText("");
					d.txtEpisodes.setText("");
					d.txtOVAs.setText("");
					d.txtSpecials.setText("");
					d.txtFileSize.setText("");
					d.cbFileType.setSelectedItem("");
					d.cbSeriesStatus.setSelectedItem("");
					d.cbTitleStatus.setSelectedItem("");
					d.cbMonth.setSelectedItem("");
					d.cbDay.setSelectedItem("");
					d.cbYear.setSelectedItem("");
					d.cbReleaseSeason.setSelectedItem("");
					d.cbReleaseYear.setSelectedItem("");
					d.txtLengthHr.setText("");
					d.txtLengthMin.setText("");
					d.txtLengthSec.setText("");
					
					d.btnSubmit.setEnabled(false);
				}
			}
		}

		@Override
		public void stateChanged(ChangeEvent arg0) {
			if (arg0.getSource().equals(d.sldData)) {
				d.cbQuality.setSelectedItem(dataQuality[d.sldData.getValue()]);
				d.txtTitle.setText(dataTitle[d.sldData.getValue()]);
				d.txtEpisodes.setText(dataEpisodes[d.sldData.getValue()]);
				d.txtOVAs.setText(dataOVAs[d.sldData.getValue()]);
				d.txtSpecials.setText(dataSpecials[d.sldData.getValue()]);
				d.txtFileSize.setText(dataFileSize[d.sldData.getValue()]);
				d.cbFileType.setSelectedItem(dataFileType[d.sldData.getValue()]);
				d.cbSeriesStatus.setSelectedItem(dataSeriesStatus[d.sldData.getValue()]);
				d.cbTitleStatus.setSelectedItem(dataTitleStatus[d.sldData.getValue()]);
				d.cbMonth.setSelectedItem(dataMonth[d.sldData.getValue()]);
				d.cbDay.setSelectedItem(dataDay[d.sldData.getValue()]);
				d.cbYear.setSelectedItem(dataYear[d.sldData.getValue()]);
				d.cbReleaseSeason.setSelectedItem(dataRelSeason[d.sldData.getValue()]);
				d.cbReleaseYear.setSelectedItem(dataRelYear[d.sldData.getValue()]);
				d.txtLengthHr.setText(dataLengthHr[d.sldData.getValue()]);
				d.txtLengthMin.setText(dataLengthMin[d.sldData.getValue()]);
				d.txtLengthSec.setText(dataLengthSec[d.sldData.getValue()]);
			}
			
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
