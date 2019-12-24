package com.minase.maidchan.run;

import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sun.awt.AWTUtilities;

class ShowAnimatedGif extends JFrame {
	
	private static final long serialVersionUID = 7357152610242714279L;

   
    
	public ShowAnimatedGif() {
				
		setLayout(null);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Eva!");
		setIconImage(new ImageIcon("./img/run/eva.png").getImage());
        
        JLabel label = new JLabel(new ImageIcon("./img/run/eva-100ms.gif"));
//      label.setOpaque(true);
//		label.setBackground(new Color(255, 255, 255, 0));
		label.setBounds(0, 0, 128, 128);
        
        AWTUtilities.setWindowOpaque(this, false);
        
        setAlwaysOnTop(true);
        add(label);
//        pack();
        setBounds(200, 200, 128, 128);
        setLocationRelativeTo(null);
        setVisible(true);
	}

    @SuppressWarnings("unused")
	public static void main(String[] args) throws MalformedURLException  {
//    	URL url = new URL("http://www.gifmaker.me/files/download/home/20131213/22/9TWuZKDab0Al21koJXv4np/output_dAuvm6.gif");
//        Icon icon = new ImageIcon(url);
//        JLabel label = new JLabel(icon);
        
    	ShowAnimatedGif ui = new ShowAnimatedGif();
        
    }
}