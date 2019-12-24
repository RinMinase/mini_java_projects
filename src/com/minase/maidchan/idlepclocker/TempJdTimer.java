package com.minase.maidchan.idlepclocker;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

public class TempJdTimer extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3446748242952594234L;
	
	private JPanel basePane;
	private JLabel timeLabel;
	private long time;
	
	public TempJdTimer(){
		time = 0;
	}
	
	public void displayGUI() {
		setTitle("Timer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		basePane = new JPanel();
		basePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		basePane.setLayout(new BorderLayout(0, 0));
		setContentPane(basePane);
		
		timeLabel = new JLabel();
		basePane.add(timeLabel, BorderLayout.CENTER);
		
		pack();
		setSize(200, 150);
		setVisible(true);
	}
	

	public static void updateComponentFromAnotherThread(final JLabel toUpdate, final String newValue) {
		SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
			protected Void doInBackground() throws Exception {
				toUpdate.setText(newValue);
				toUpdate.setBounds(toUpdate.getBounds().x,
								   toUpdate.getBounds().y,
								   toUpdate.getPreferredSize().width,
								   toUpdate.getPreferredSize().height);
				return null;
			}
		};
		worker.run();
	}
	
	public static void main(String[] args) {
		final TempJdTimer t = new TempJdTimer();
		
		Thread gui = new Thread(new Runnable() {
			public void run() {
				t.displayGUI();
			}
		});
		
		Thread time = new Thread(new Runnable() {
			public void run() {
				while(true) {
					updateComponentFromAnotherThread(t.timeLabel, String.format("%d seconds have passed.", t.time));
					t.time++;
					try { Thread.sleep(1000); } catch (Exception e) {}
				}
			}
		});
		
		gui.start();
		time.start();
	}
	
}