package com.javalec.customer;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JDialog;

import com.javalec.util.ShareVar;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Lobby extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton btnSetting;
	private JLabel lblName;
	private JButton btnNewButton;
	private JLabel lblImage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lobby dialog = new Lobby();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Lobby() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				checkstatus();
			}
		});
		setBounds(100, 100, 617, 443);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnSetting());
		getContentPane().add(getLblName());
		getContentPane().add(getBtnNewButton());
		getContentPane().add(getLblImage());

	}
	private JButton getBtnSetting() {
		if (btnSetting == null) {
			btnSetting = new JButton("");
			btnSetting.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//checkpage();
					Mypage();
				}
			});
			btnSetting.setIcon(new ImageIcon("C:\\Users\\qazxd\\Downloads\\cogwheel_114848 (1).png"));
			btnSetting.setBounds(544, 0, 59, 48);
		}
		return btnSetting;
	}
	
	private void checkstatus() {
		Dao dao = new Dao();
		Dto dto =dao.Action2();
		lblName.setText(dto.getUsername()+"님 환영합니다.");
		String filepath1 = dto.getFilepath();
		ImageIcon icon =  new ImageIcon(filepath1);
		Image i = icon.getImage();
		Image s = i.getScaledInstance(59, 48, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icons =  new ImageIcon(s);
		lblImage.setIcon(icons);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblImage);
		setVisible(true);
	
		
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("");
			lblName.setBounds(392, 0, 153, 48);
		}
		return lblName;
	}
	
	//private void checkpage() {
	//	customerCheck user = new customerCheck();
	//	user.setVisible(true);
	
	
		
	//}
	
	private void Mypage() {
		Mypage user = new Mypage();
		user.setVisible(true);
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("로그아웃");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					logout();
				}
			});
			btnNewButton.setBounds(512, 49, 91, 23);
		}
		return btnNewButton;
	}
	
	private void logout() {
		int result=JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?","Confirm",JOptionPane.YES_NO_OPTION);
		if(result==JOptionPane.CLOSED_OPTION) {
			JOptionPane.showMessageDialog(null, "잘못된 입력입니다.");
		}else if(result==JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "로그아웃이 완료되었습니다.");
			dispose();
		}else {
			JOptionPane.showMessageDialog(null, "로그인 상태입니다.");
		}
		
	}
	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			lblImage.setBounds(0, 0, 59, 48);
		}
		return lblImage;
	}
}
