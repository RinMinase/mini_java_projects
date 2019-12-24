package com.minase.bitratecalculator;

//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JWindow;

public class temp extends JWindow {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4055468893993908272L;

	public static void main(String[] args) {
		temp t = new temp();

		JTextField jt = new JTextField();
		jt.setBounds(5, 5, 200, 25);
		
		JLabel jl = new JLabel();
		jl.setLayout(null);
		jl.add(jt);
		
//		t.setLayout(null);
//		t.add(jt);
		
		t.add(jl);
		t.setSize(200, 200);
//		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t.setVisible(true);
		
	}
}
