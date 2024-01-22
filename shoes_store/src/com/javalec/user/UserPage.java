package com.javalec.user;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;

public class UserPage extends JDialog {

	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JMenu mnBack;
	private JMenu mnForward;
	private JLabel lblLoginName;
	private JButton btnNewButton;
	private JMenu mnProducts;
	private JMenu mnMyPage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserPage dialog = new UserPage();
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
	public UserPage() {
		setTitle("제품현황");
		setBounds(100, 100, 450, 500);
		setJMenuBar(getMenuBar());
		getContentPane().setLayout(null);

	}
	

	private JMenuBar getMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.setBackground(new Color(0, 0, 0));
			menuBar.add(getMnBack());
			menuBar.add(getMnForward());
			menuBar.add(getMnProducts());
			menuBar.add(getMnMyPage());
			menuBar.add(getBtnNewButton());
		}
		return menuBar;
	}
	private JMenu getMnBack() {
		if (mnBack == null) {
			mnBack = new JMenu("");
			
			ImageIcon icon = new ImageIcon(UserPage.class.getResource("/com/javalec/images/backIcon.png"));
			Image changeToImg = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeToImg);
			mnBack.setIcon(changeIcon);
		}
		return mnBack;
	}
	private JMenu getMnForward() {
		if (mnForward == null) {
			mnForward = new JMenu("");
			ImageIcon icon = new ImageIcon(UserPage.class.getResource("/com/javalec/images/forwardIcon.png"));
			Image changeToImg = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeToImg);
			mnForward.setIcon(changeIcon);
		}
		return mnForward;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("로그아웃");
		}
		return btnNewButton;
	}
	
	private JMenu getMnProducts() {
		if (mnProducts == null) {
			mnProducts = new JMenu("제품현황");
		}
		return mnProducts;
	}
	
	private JMenu getMnMyPage() {
		if (mnMyPage == null) {
			mnMyPage = new JMenu("마이페이지");
		}
		return mnMyPage;
	}
	
}
