package com.minase.maidchan.anidb_prevcopy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TabbedDBAdder {
	
	Statement stm;
	Connection con;

	public TabbedDBAdder() {
		
		String values[][] = SplitText(ReadFile("Rin Minase's Anime Database.txt"));
		connectToDatabase();
		for (int i = 0; i < values.length; i++) {
			
//			System.out.println("insert into animedatabase values('" + 
//					values[i][0] + "', '" + 
//					values[i][1] + "', " + 
//					Integer.parseInt(values[i][2]) + ", " + 
//					Integer.parseInt(values[i][3]) + ", " + 
//					Integer.parseInt(values[i][4]) + ", '" + 
//					values[i][5] + "', '" + 
//					values[i][6] + "', '" +  
//					values[i][7] + "', '" + 
//					values[i][8] + "', '" + 
//					values[i][9] + "', '" + 
//					values[i][10] + "')");
			
			insert("insert into animedatabase values('" + 
					values[i][0] + "', '" + 
					values[i][1] + "', " + 
					Integer.parseInt(values[i][2]) + ", " + 
					Integer.parseInt(values[i][3]) + ", " + 
					Integer.parseInt(values[i][4]) + ", '" + 
					values[i][5] + "', '" + 
					values[i][6] + "', '" +  
					values[i][7] + "', '" + 
					values[i][8] + "', '" + 
					values[i][9] + "', '" + 
					values[i][10] + "')");
		}
		
//		System.out.println(values[77][1]);
//		
//		ResultSet rs = retrieve("select * from animedatabase");
//		int ctr = 0;
//		try {
//			while (rs.next()) {
//				System.out.println(ctr);
//				System.out.println(rs.getString("TitleStatus"));
//				ctr++;
//			}
//		} catch (SQLException e) {System.err.println(e.getMessage());}
	}
	
	private String ReadFile(String Location) {
		String FinalText = "";
		try {
			FileReader fr = new FileReader(Location);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while((line = br.readLine()) != null) { FinalText += line + "\r\n"; }
			br.close();
		} catch (FileNotFoundException ex) { System.err.println("Unable to open file '" + Location + "'"); System.exit(0);
		} catch (IOException ex) { System.err.println("Error reading file '" + Location + "'"); System.exit(0); }
		
		return FinalText;
	}
	
	private String[][] SplitText(String textToSplit) {
		String[][] finalValue = null;
		
		String[] splitByTitles = (textToSplit.replaceAll("\'", "\'\'")).split("\r\n");
		String[][] splitByValues = new String[splitByTitles.length][13];
		
		for (int i = 0; i < splitByTitles.length; i++) { splitByValues[i] = splitByTitles[i].split("\t"); }
		
		for (int i = 0; i < splitByValues.length; i++) {
			
//			for (int j = 0; j < splitByValues[i][2].length(); j++) {
//				if (splitByValues[i][2].charAt(i) == '\'') {
//					splitByValues[i][2]
//				}
//			}
			
//			splitByValues[i]
			
			if (splitByValues[i][2].isEmpty()) { splitByValues[i][2] = "0"; }
			if (splitByValues[i][3].isEmpty()) { splitByValues[i][3] = "0"; }
			if (splitByValues[i][4].isEmpty()) { splitByValues[i][4] = "0"; }
			if (splitByValues[i][5].isEmpty()) { splitByValues[i][5] = "0.00 GB"; }
			if (splitByValues[i][10].isEmpty()) { splitByValues[i][11] = "00"; }
			if (splitByValues[i][11].isEmpty()) { splitByValues[i][12] = "00"; }
			if (splitByValues[i][12].isEmpty()) { splitByValues[i][13] = "00"; }
		}
		
		finalValue = new String[splitByValues.length][11];
		for (int i = 0; i < finalValue.length; i++) {
			for (int j = 0; j < finalValue[i].length; j++) {
				if (j < 10) { finalValue[i][j] = splitByValues[i][j]; } 
				else if (j == 10) { finalValue[i][10] = splitByValues[i][10] + ":" + splitByValues[i][11] + ":" + splitByValues[i][12]; }
			}
		}
		
//		for (int i = 0; i < finalValue.length; i++) { for (int j = 0; j < finalValue[i].length; j++) { System.out.println(finalValue[i][j]); } }
				
		return finalValue;
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
	
	@SuppressWarnings("unused")
	private ResultSet retrieve(String command) {
		try {
			ResultSet rs = stm.executeQuery(command);
			return rs;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		TabbedDBAdder tabAdd = new TabbedDBAdder();
	}
}
