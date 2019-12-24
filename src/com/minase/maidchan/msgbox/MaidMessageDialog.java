package com.minase.maidchan.msgbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MaidMessageDialog extends JDialog {

	private static final long serialVersionUID = -8237320322211012456L;
	
	private JLabel lblMaidImage, lblMessage;
	private JPanel contentPanel;
	public JButton btnOk;
	
	public MaidMessageDialog(String message) {
		
		setTitle("メイドちゃん (Maid-chan)");
//		setTitle("\u30E1\u30A4\u30C9\u3061\u3083\u3093 (Maid-chan)");
		setBounds(100, 100, 450, 240);
		setIconImage(new ImageIcon(getClass().getResource("img/tray-h.png")).getImage());
		
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		add(contentPanel);
		
		lblMaidImage = new JLabel(new ImageIcon(getClass().getResource("img/tray-128x83.png")));
		lblMaidImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaidImage.setBounds(10, 11, 83, 128);
		contentPanel.add(lblMaidImage);
		
		lblMessage = new JLabel(message);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(103, 11, 321, 147);
		contentPanel.add(lblMessage);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(335, 169, 89, 23);
		contentPanel.add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();				
			}
		});
	}
	
	public static void main(String[] args) {
		try {
			MaidMessageDialog dialog = new MaidMessageDialog("Message Dialog");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
