package com.javalec.customer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.util.ShareVar;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class customerCheck extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btncheck;
	private JPasswordField pfPW;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			customerCheck dialog = new customerCheck();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public customerCheck() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getBtncheck());
		contentPanel.add(getPfPW());
		contentPanel.add(getLblNewLabel());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	private JButton getBtncheck() {
		if (btncheck == null) {
			btncheck = new JButton("확인");
			btncheck.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					check();
				}
			});
			btncheck.setBounds(160, 230, 91, 23);
		}
		return btncheck;
	}
	private JPasswordField getPfPW() {
		if (pfPW == null) {
			pfPW = new JPasswordField();
			pfPW.setBounds(83, 115, 236, 23);
		}
		return pfPW;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("비밀번호를 입력하세요");
			lblNewLabel.setBounds(83, 55, 236, 15);
		}
		return lblNewLabel;
	}
	private void check() {
		char[] pw = pfPW.getPassword();
		String pass = new String(pw);
		
		Dao dao = new Dao();
		Dto dto = dao.pwch();
		char[] pw1 = pfPW.getPassword();
		String pass1 = new String(pw1);
		
		if(pass.equals(pass1)) {
			JOptionPane.showMessageDialog(null, "확인되었습니다");
			Mypage();
			dispose();
		}else {
			JOptionPane.showMessageDialog(null, "비밀번호를 확인해 주십시오");
		}
	}
	private void Mypage() {
		Mypage user = new Mypage();
		user.setVisible(true);
	}
}
