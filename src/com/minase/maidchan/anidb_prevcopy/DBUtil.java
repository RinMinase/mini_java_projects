package com.minase.maidchan.anidb_prevcopy;

import java.sql.*;

public class DBUtil {

	private final String DBDriver = "com.mysql.jdbc.Driver";
	private final String DBUrl = "jdbc:mysql://127.0.0.1/";
	private final String DBName = "anidb";
	private final String DBUser = "root";
	private final String DBPass = "root";
	@SuppressWarnings("unused")
	private final String TableStatement = "create table AnimeDatabase (Quality enum('FHD 1080p', 'HD 720p', 'HQ 480p', 'LQ'), " +
										  "AnimeTitle text, " +
										  "Episodes int, " +
										  "OVAs tinyint, " +
										  "Specials tinyint, " +
										  "FileSize varchar(9), " +
										  "SeasonStatus enum('Finished', 'Not the first season', 'Not Finished'), " +
										  "TitleStatus enum('Finished', 'Not Finished'), " +
										  "DateFinished varchar(12), " +
										  "ReleaseSeason varchar(11), " +
										  "Length varchar(8))";
	
	private Connection con;
	private Statement stm;

	public DBUtil() {
		try { Class.forName(DBDriver); } catch (ClassNotFoundException ex) { System.err.println(ex.getMessage()); }
		
		try {
			con = DriverManager.getConnection(DBUrl + DBName, DBUser, DBPass);
			stm = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException ex) { System.err.println(ex.getMessage()); }
	}
	
	public ResultSet retrieve(String command) {
		try {
			ResultSet rs = stm.executeQuery(command);
			return rs;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return null;
		}
	}

	public int insert(String commandStr) { 
		try {
			return stm.executeUpdate(commandStr);
		} catch(SQLException ex) {
			System.err.println(ex.getMessage());
			return 0;
		}
	}
	
	public void disconnect() {
		try {
			stm.close();
			con.close();
		} catch( SQLException ex) { System.err.println(ex.getMessage()); }
	}
}
