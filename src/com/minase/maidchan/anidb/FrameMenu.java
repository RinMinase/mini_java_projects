package com.minase.maidchan.anidb;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class FrameMenu extends JFrame {

	private JPanel contentPane;
	
	JButton btnAdd,
			btnView,
			btnEdit,
			btnDelete;
	
	Font font = new Font("Century Gothic", Font.PLAIN, 14);
	
	Actions action;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameMenu frame = new FrameMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public FrameMenu() {
		
		action = new Actions();
		
		setTitle("Rin's Anime Database");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 340, 220);
		setLocationRelativeTo(null);

		try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch(Exception e) { System.err.println(e.getMessage()); }
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAdd = new JButton("Add new entry");
		btnAdd.setBounds(10, 11, 300, 30);
		btnAdd.setFont(font);
//		btnAdd.setFont(new Font("Century Gothic", 0, 14));
		contentPane.add(btnAdd);
		
		btnView = new JButton("View database");
		btnView.setBounds(10, 52, 300, 30);
		btnView.setFont(btnAdd.getFont().deriveFont(14f));
		contentPane.add(btnView);
		
		btnEdit = new JButton("Edit an entry");
		btnEdit.setBounds(10, 93, 300, 30);
		btnEdit.setFont(btnAdd.getFont().deriveFont(14f));
		contentPane.add(btnEdit);
		
		btnDelete = new JButton("Delete an entry");
		btnDelete.setBounds(10, 134, 300, 30);
		btnDelete.setFont(btnAdd.getFont().deriveFont(14f));
		contentPane.add(btnDelete);

		btnAdd.addActionListener(action);
		btnView.addActionListener(action);
		btnEdit.addActionListener(action);
		btnDelete.addActionListener(action);
	}
	
	private class Actions implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnAdd)) {
				
			} else if (e.getSource().equals(btnView)) {
				
			} else if (e.getSource().equals(btnEdit)) {
				
			} else if (e.getSource().equals(btnDelete)) {
				
			}
		}
	}
}
