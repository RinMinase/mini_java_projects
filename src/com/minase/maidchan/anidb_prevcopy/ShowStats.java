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
import java.text.DecimalFormat;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class ShowStats extends JFrame {

	private static final long serialVersionUID = 3085341227299775250L;
	
	private int ctrFHD = 0, 
				ctrHD = 0, 
				ctrHQ = 0, 
				ctrLQ = 0,
				ctrSeasons = 0,
				ctrTitles = 0,
				ctrDay = 0,
				ctrHr = 0,
				ctrMin = 0,
				ctrSec = 0;

	private double ctrSize = 0d;
	
	private JPanel contentPane,
				   pnlQuality,
				   pnlQualityVal;
	
	private JTextField txtFHD, 
					   txtHD, 
					   txtHQ, 
					   txtLQ, 
					   txtTitlesWatched,
					   txtSeasonsWatched,
//					   txtAnimeLifetime,
					   txtTotalSize;
	
	private JLabel lblTitle,
				   lblQualitiesCollected,
				   lblFHD,
				   lblHD,
				   lblHQ,
				   lblLQ,
				   lblTitlesWatched,
				   lblSeasonsWatched,
				   lblAnimeLifetimeHeader,
				   lblTotalSize,
				   lblAnimeLifetime;
	
	private JComboBox<String> cbTotalSize;
	
	Connection con;
	Statement stm;
	SSActionListener action;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowStats frame = new ShowStats();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ShowStats() {
		
		action = new SSActionListener(this);
		
		setResizable(false);
		setTitle("Rin Minase's Anime Stats");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 290, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("Rin's Stats"); // ã‚Šã‚“ ã?® Stats // ã‚Šã‚“ ã?¿ã?ªã?› ã?® // \u308A\u3093 \u306E Stats
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 264, 35);
		lblTitle.setFont(lblTitle.getFont().deriveFont(29f));
		contentPane.add(lblTitle);
		
		lblQualitiesCollected = new JLabel("Qualities Collected");
		lblQualitiesCollected.setBounds(10, 57, 120, 15);
		contentPane.add(lblQualitiesCollected);
		
		pnlQuality = new JPanel();
		pnlQuality.setBounds(30, 83, 65, 80);
		contentPane.add(pnlQuality);
		pnlQuality.setLayout(new GridLayout(4, 4, 0, 0));
		
		lblFHD = new JLabel("FHD 1080p");
		pnlQuality.add(lblFHD);
		
		lblHD = new JLabel("HD 720p");
		pnlQuality.add(lblHD);
		
		lblHQ = new JLabel("HQ 480p");
		pnlQuality.add(lblHQ);
		
		lblLQ = new JLabel("LQ");
		pnlQuality.add(lblLQ);
		
		pnlQualityVal = new JPanel();
		pnlQualityVal.setBounds(105, 83, 40, 80);
		contentPane.add(pnlQualityVal);
		pnlQualityVal.setLayout(new GridLayout(0, 1, 0, 0));
		
		txtFHD = new JTextField();
		txtFHD.setHorizontalAlignment(SwingConstants.CENTER);
		txtFHD.setEditable(false);
		pnlQualityVal.add(txtFHD);
		
		txtHD = new JTextField();
		txtHD.setHorizontalAlignment(SwingConstants.CENTER);
		txtHD.setEditable(false);
		pnlQualityVal.add(txtHD);
		
		txtHQ = new JTextField();
		txtHQ.setHorizontalAlignment(SwingConstants.CENTER);
		txtHQ.setEditable(false);
		pnlQualityVal.add(txtHQ);
		
		txtLQ = new JTextField();
		txtLQ.setHorizontalAlignment(SwingConstants.CENTER);
		txtLQ.setEditable(false);
		pnlQualityVal.add(txtLQ);
		
		lblTitlesWatched = new JLabel("Titles Watched");
		lblTitlesWatched.setBounds(155, 57, 85, 15);
		contentPane.add(lblTitlesWatched);
		
		txtTitlesWatched = new JTextField();
		txtTitlesWatched.setEditable(false);
		txtTitlesWatched.setBounds(155, 77, 119, 20);
		contentPane.add(txtTitlesWatched);
		
		lblSeasonsWatched = new JLabel("Seasons Watched");
		lblSeasonsWatched.setBounds(155, 108, 119, 15);
		contentPane.add(lblSeasonsWatched);
		
		txtSeasonsWatched = new JTextField();
		txtSeasonsWatched.setEditable(false);
		txtSeasonsWatched.setBounds(155, 127, 119, 20);
		contentPane.add(txtSeasonsWatched);
		
		lblTotalSize = new JLabel("Total Size");
		lblTotalSize.setBounds(10, 176, 65, 15);
		contentPane.add(lblTotalSize);
		
		txtTotalSize = new JTextField();
		txtTotalSize.setEditable(false);
		txtTotalSize.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotalSize.setBounds(115, 174, 99, 20);
		contentPane.add(txtTotalSize);
		
		cbTotalSize = new JComboBox<String>(new String[] {"GB", "TB"});
		cbTotalSize.setBounds(224, 173, 50, 20);
		contentPane.add(cbTotalSize);
		
		lblAnimeLifetimeHeader = new JLabel("Anime Lifetime");
		lblAnimeLifetimeHeader.setBounds(10, 207, 95, 15);
		contentPane.add(lblAnimeLifetimeHeader);
		
		lblAnimeLifetime = new JLabel();
		lblAnimeLifetime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAnimeLifetime.setBounds(10, 203, 270, 40);
		contentPane.add(lblAnimeLifetime);
		
		setupData();
		cbTotalSize.addActionListener(action);
	}
	
	private void setupData() {
		connectToDatabase();
		ResultSet rs = retrieve("select * from animedatabase");
		
		try {
			while (rs.next()) {
				if (rs.getString("TitleStatus").equals("Finished")) {
				
					ctrTitles++;
					
					if(rs.getString("Quality").equals("FHD 1080p")) { ctrFHD++; } 
					else if (rs.getString("Quality").equals("HD 720p")) { ctrHD++; } 
					else if (rs.getString("Quality").equals("HQ 480p")) { ctrHQ++; } 
					else if (rs.getString("Quality").equals("LQ")) { ctrLQ++; }
					
					if (rs.getString("SeasonStatus").equals("Finished")) {  ctrSeasons++; }
					
					try { ctrSize += Double.parseDouble((rs.getString("FileSize")).split(" ")[0]); } catch (NumberFormatException nfe) { }
					try { ctrHr += Integer.parseInt((rs.getString("Length")).split(":")[0]); } catch (NumberFormatException | ArrayIndexOutOfBoundsException nfe) { }
					try { ctrMin += Integer.parseInt((rs.getString("Length")).split(":")[1]); } catch (NumberFormatException | ArrayIndexOutOfBoundsException nfe) { }
					try { ctrSec += Integer.parseInt((rs.getString("Length")).split(":")[2]); } catch (NumberFormatException | ArrayIndexOutOfBoundsException nfe) { }
				}
			}
		} catch (SQLException e) { System.err.println(e.getMessage()); }

		ctrMin += ctrSec / 60;
		ctrSec = ctrSec % 60;
		
		ctrHr += ctrMin / 60;
		ctrMin = ctrMin % 60;
		
		ctrDay += ctrHr / 24;
		ctrHr = ctrHr % 24;

		txtFHD.setText("" + ctrFHD);
		txtHD.setText("" + ctrHD);
		txtHQ.setText("" + ctrHQ);
		txtLQ.setText("" + ctrLQ);
		txtTitlesWatched.setText(ctrTitles + " Titles");
		txtSeasonsWatched.setText(ctrSeasons + " Seasons");
		txtTotalSize.setText("" + ctrSize);
		
		lblAnimeLifetime.setText("<html><p align=\"right\">" +
				ctrDay + " days, " + 
				ctrHr + " hours,<br>" +
				ctrMin + " minutes and, " +  
				ctrSec + " seconds</p></html>");
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
	
	@SuppressWarnings("unused")
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
	
	private class SSActionListener implements ActionListener {

		ShowStats ss;
		DecimalFormat decFormat = new DecimalFormat("#0.00");
		
		public SSActionListener(ShowStats ss) {
			this.ss = ss;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getActionCommand().equals("comboBoxChanged")) {
				if (arg0.getSource().equals(ss.cbTotalSize)) {
					txtTotalSize.setText((ss.cbTotalSize.getSelectedItem().equals("GB")) ? decFormat.format(ctrSize) : decFormat.format(ctrSize / 1024d));
				}
			}
		}
	}
}
