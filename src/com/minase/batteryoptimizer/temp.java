package com.minase.batteryoptimizer;

//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;


public class temp {
	public static void main(String[] args) {
//		JFrame jf = new JFrame();
//
//		jf.setSize(350, 500);
//		jf.setLocation((1366-jf.getWidth())/2, (768-jf.getHeight())/2);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jf.setResizable(false);
//		
//		
//		
//		jf.setVisible(true);
		
		try {
			@SuppressWarnings("unused")
			Process pr = new ProcessBuilder("taskkill.exe", "/f", "/IM", "notepad.exe").start();
			System.out.println("deleted");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		String FinalText = "";
//		
//		try {
//			FileReader fr = new FileReader("./img/idlepclocker/x.txt");
//			BufferedReader br = new BufferedReader(fr);
//			String line = null;
//			
//			while((line = br.readLine()) != null) {
//				FinalText += line + "\r\n";
//			}
//			
//			br.close();
//			
//		}catch(FileNotFoundException ex) {
//			System.out.println("Unable to open file '" + "./img/idlepclocker/x.txt" + "'");
//			JOptionPane.showMessageDialog(null, "Unable to open file '" + "./img/idlepclocker/x.txt" + "'", "FileNotFoundException", 1);
//			System.exit(0);
//		}catch(IOException ex){
//			System.out.println("Error reading file '" + "./img/idlepclocker/x.txt" + "'");
//			JOptionPane.showMessageDialog(null, "Error reading file '" + "./img/idlepclocker/x.txt" + "'", "IOException", 1);
//			System.exit(0);
//		}
//		
//		System.out.println(FinalText);
//		temp t = new temp();
//		Process p;
//		
//			p = new Process() {
//				
//				@Override
//				public int waitFor() throws InterruptedException {
//					// TODO Auto-generated method stub
//					return 0;
//				}
//				
//				@Override
//				public OutputStream getOutputStream() {
//					// TODO Auto-generated method stub
//					return null;
//				}
//				
//				@Override
//				public InputStream getInputStream() {
//					// TODO Auto-generated method stub
//					return null;
//				}
//				
//				@Override
//				public InputStream getErrorStream() {
//					// TODO Auto-generated method stub
//					return null;
//				}
//				
//				@Override
//				public int exitValue() {
//					// TODO Auto-generated method stub
//					return 0;
//				}
//				
//				@Override
//				public void destroy() {
//					// TODO Auto-generated method stub
//					
//				}
//			};
//			System.out.println("" + t.isRunning(p));
//		
//		
//	}
//	
//	 boolean isRunning(Process process) {
//	     try {
//	         process.exitValue();
//	         return false;
//	     } catch (Exception e) {
//	         return true;
//	     }
	 }
}
