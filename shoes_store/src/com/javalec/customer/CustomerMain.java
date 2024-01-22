package com.javalec.customer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;

import com.javalec.util.ShareVar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CustomerMain {

	private JFrame frame;
	private JLabel lblNewLabel;
	private JLabel lblPw;
	private JTextField tfID;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JPasswordField pfPW;
	private JLabel lblimage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerMain window = new CustomerMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setBounds(100, 100, 617, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(getLblNewLabel());
		frame.getContentPane().add(getLblPw());
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getLblNewLabel());
		frame.getContentPane().add(getLblPw());
		frame.getContentPane().add(getTfID());
		frame.getContentPane().add(getBtnNewButton());
		frame.getContentPane().add(getBtnNewButton_1());
		frame.getContentPane().add(getPfPW());
		frame.getContentPane().add(getLblimage());
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("ID");
			lblNewLabel.setFont(new Font("Gulim", Font.PLAIN, 20));
			lblNewLabel.setBounds(96, 235, 61, 22);
		}
		return lblNewLabel;
	}
	private JLabel getLblPw() {
		if (lblPw == null) {
			lblPw = new JLabel("PW");
			lblPw.setFont(new Font("Gulim", Font.PLAIN, 20));
			lblPw.setBounds(96, 288, 61, 22);
		}
		return lblPw;
	}
	private JTextField getTfID() {
		if (tfID == null) {
			tfID = new JTextField();
			tfID.setBounds(187, 235, 174, 22);
			tfID.setColumns(10);
		}
		return tfID;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("로그인");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login();
				}
			});
			btnNewButton.setBounds(407, 235, 91, 23);
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("회원가입");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					userRegistration();
				}
			});
			btnNewButton_1.setBounds(407, 268, 91, 42);
		}
		return btnNewButton_1;
	}
	private JPasswordField getPfPW() {
		if (pfPW == null) {
			pfPW = new JPasswordField();
			pfPW.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						login();
					}
				}
			});
			pfPW.setBounds(187, 288, 174, 22);
		}
		return pfPW;
	}
	
	private void login() {
		String id = tfID.getText();
		String pass = new String(pfPW.getPassword());
		
		if(tfID.getText().trim().length()==0) {
			JOptionPane.showMessageDialog(null, "아이디 입력필수");
			tfID.requestFocus();
			return;
		}else if(pass.trim().length()==0) {
			JOptionPane.showMessageDialog(null, "패스워드 입력 필수");
			pfPW.requestFocus();
			
		}else {
			confirm();
		}
		
	}
	private void confirm() {
		String inputID = tfID.getText();
		char[] pw = pfPW.getPassword();
		String inputPw = new String(pw);
		
		LocalDate now = LocalDate.now();
		
		LocalDate SeoulNow = LocalDate.now(ZoneId.of("Asia/Seoul"));
	
		
		String login="";
		Dao dao = new Dao();
		dao.confirm(inputID, inputPw);
		ArrayList<Dto> dtoList1 = dao.confirm(inputID, inputPw);
		dtoList1.size();
		
		
	if(inputID.equals("admin")) {
		if(dtoList1.size()>0) {
			if(inputID.equals(dtoList1.get(0).getUserid()) && inputPw.equals(dtoList1.get(0).getUserpw())) {
				JOptionPane.showMessageDialog(null, SeoulNow+" 관리자 로그인 성공" );
				ShareVar.userid=dtoList1.get(0).getUserid();
				ShareVar.password=dtoList1.get(0).getUserpw();
				ShareVar.name = dtoList1.get(0).getUsername();
				ShareVar.phone=dtoList1.get(0).getUserphone();
				String filepath = dtoList1.get(0).getFilepath();
				clearcolumn();
				//Lobby();
			} else {
				JOptionPane.showMessageDialog(null, "로그인 실패");	
				clearcolumn();}
		}else {
			JOptionPane.showMessageDialog(null, "로그인 실패");
			clearcolumn();}
	}else {if(dtoList1.size()>0) {
		if(inputID.equals(dtoList1.get(0).getUserid()) && inputPw.equals(dtoList1.get(0).getUserpw())) {
			JOptionPane.showMessageDialog(null, SeoulNow+" 로그인 성공" );
			ShareVar.userid=dtoList1.get(0).getUserid();
			System.out.println(dtoList1.get(0).userid);
			ShareVar.password=dtoList1.get(0).getUserpw();
			ShareVar.name = dtoList1.get(0).getUsername();
			ShareVar.phone=dtoList1.get(0).getUserphone();
			String filepath = dtoList1.get(0).getFilepath();
			System.out.println(filepath);
			clearcolumn();
			Lobby();
		} else {
			JOptionPane.showMessageDialog(null, "로그인 실패");	}
	}else {
		JOptionPane.showMessageDialog(null, "로그인 실패");}
			
	}
	}
	private void Lobby() {
		Lobby user = new Lobby();
		user.setVisible(true);
		CustomerMain main = new CustomerMain();
		
		
		
		
}
	private void clearcolumn() {

		tfID.setText("");
		pfPW.setText("");
	}
	
	private void userRegistration() {
		UserRegistration user = new UserRegistration();
		user.setVisible(true);
		
		
	}
	private JLabel getLblimage() {
		if (lblimage == null) {
			lblimage = new JLabel(""); 
			lblimage.setIcon(new ImageIcon(CustomerMain.class.getResource("/com/javalec/images/신발가게.jpg")));
			lblimage.setBounds(0, -20,618 ,410 );
		}
		return lblimage;
	}
}
	