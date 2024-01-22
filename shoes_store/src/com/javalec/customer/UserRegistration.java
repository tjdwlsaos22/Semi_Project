package com.javalec.customer;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.JobAttributes;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class UserRegistration extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton btnRegi;
	private JLabel lblNewLabel;
	private JLabel lblPw;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_1_1_1;
	private JLabel lblNewLabel_1_1_1_1;
	private JTextField tfID;
	private JTextField tfName;
	private JTextField tfPhone;
	private JPasswordField pfPw;
	private JPasswordField pfPw2;
	private JButton btnconfirm;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnfilePath;
	private JTextField tfFilepath;
	private JLabel lblImage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegistration dialog = new UserRegistration();
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
	public UserRegistration() {
		setBounds(100, 100, 714, 461);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnRegi());
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getLblPw());
		getContentPane().add(getLblNewLabel_1_1());
		getContentPane().add(getLblNewLabel_1_1_1());
		getContentPane().add(getLblNewLabel_1_1_1_1());
		getContentPane().add(getTfID());
		getContentPane().add(getTfName());
		getContentPane().add(getTfPhone());
		getContentPane().add(getPfPw());
		getContentPane().add(getPfPw2());
		getContentPane().add(getBtnconfirm());
		getContentPane().add(getBtnConfirm());
		getContentPane().add(getBtnCancel());
		getContentPane().add(getBtnfilePath());
		getContentPane().add(getTfFilepath());
		getContentPane().add(getLblImage());

	}

	private JButton getBtnRegi() {
		if (btnRegi == null) {
			btnRegi = new JButton("회원가입 완료");
			btnRegi.setEnabled(false);
			btnRegi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					okaction();
				}
			});
			btnRegi.setBounds(242, 350, 108, 34);
		}
		return btnRegi;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("ID");
			lblNewLabel.setFont(new Font("Gulim", Font.PLAIN, 18));
			lblNewLabel.setBounds(109, 81, 61, 24);
		}
		return lblNewLabel;
	}
	private JLabel getLblPw() {
		if (lblPw == null) {
			lblPw = new JLabel("PW");
			lblPw.setFont(new Font("Gulim", Font.PLAIN, 18));
			lblPw.setBounds(109, 124, 61, 24);
		}
		return lblPw;
	}
	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("PW확인");
			lblNewLabel_1_1.setFont(new Font("Gulim", Font.PLAIN, 18));
			lblNewLabel_1_1.setBounds(109, 171, 81, 24);
		}
		return lblNewLabel_1_1;
	}
	private JLabel getLblNewLabel_1_1_1() {
		if (lblNewLabel_1_1_1 == null) {
			lblNewLabel_1_1_1 = new JLabel("이름");
			lblNewLabel_1_1_1.setFont(new Font("Gulim", Font.PLAIN, 18));
			lblNewLabel_1_1_1.setBounds(109, 226, 61, 24);
		}
		return lblNewLabel_1_1_1;
	}
	private JLabel getLblNewLabel_1_1_1_1() {
		if (lblNewLabel_1_1_1_1 == null) {
			lblNewLabel_1_1_1_1 = new JLabel("전화번호");
			lblNewLabel_1_1_1_1.setFont(new Font("Gulim", Font.PLAIN, 18));
			lblNewLabel_1_1_1_1.setBounds(109, 284, 81, 24);
		}
		return lblNewLabel_1_1_1_1;
	}
	private JTextField getTfID() {
		if (tfID == null) {
			tfID = new JTextField();
			tfID.setBounds(208, 81, 108, 24);
			tfID.setColumns(10);
		}
		return tfID;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setEditable(false);
			tfName.setColumns(10);
			tfName.setBounds(208, 226, 160, 24);
		}
		return tfName;
	}
	private JTextField getTfPhone() {
		if (tfPhone == null) {
			tfPhone = new JTextField();
			tfPhone.setEditable(false);
			tfPhone.setColumns(10);
			tfPhone.setBounds(208, 284, 225, 24);
		}
		return tfPhone;
	}
	private JPasswordField getPfPw() {
		if (pfPw == null) {
			pfPw = new JPasswordField();
			pfPw.setEditable(false);
			pfPw.setBounds(208, 124, 160, 24);
		}
		return pfPw;
	}
	private JPasswordField getPfPw2() {
		if (pfPw2 == null) {
			pfPw2 = new JPasswordField();
			pfPw2.setEditable(false);
			pfPw2.setBounds(208, 171, 160, 24);
		}
		return pfPw2;
	}
	private JButton getBtnconfirm() {
		if (btnconfirm == null) {
			btnconfirm = new JButton("중복체크");
			btnconfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					check();
				}
			});
			btnconfirm.setBounds(368, 81, 91, 24);
		}
		return btnconfirm;
	}
	private JButton getBtnConfirm() {
		if (btnConfirm == null) {
			btnConfirm = new JButton("비밀번호 체크");
			btnConfirm.setEnabled(false);
			btnConfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pwcheck();
				}
			});
			btnConfirm.setBounds(380, 148, 123, 24);
		}
		return btnConfirm;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("취소");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cancel();
				}
			});
			btnCancel.setBounds(609, 391, 91, 23);
		}
		return btnCancel;
	}
	private JButton getBtnfilePath() {
		if (btnfilePath == null) {
			btnfilePath = new JButton("파일경로");
			btnfilePath.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filepath();
				}
				
			});
			btnfilePath.setBounds(542, 326, 91, 23);
		}
		return btnfilePath;
	}
	private JTextField getTfFilepath() {
		if (tfFilepath == null) {
			tfFilepath = new JTextField();
			tfFilepath.setEditable(false);
			tfFilepath.setBounds(465, 357, 235, 21);
			tfFilepath.setColumns(10);
		}
		return tfFilepath;
	}
	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			lblImage.setBounds(465, 177, 235, 139);
		}
		return lblImage;
	}
	private void check() {
		String id = tfID.getText();
		char[] pw = pfPw.getPassword();
		String pass = new String(pw);
		char[] pw1 = pfPw2.getPassword();
		String pass2 = new String(pw1);
		String name = tfName.getText();
		String phone =tfPhone.getText();
		String login ="";
		Dao dao = new Dao(id, pass);
		boolean result = dao.check();
		if(id.contains("admin")) {
			JOptionPane.showMessageDialog(null, "사용할 수 없는 아이디입니다");
		}else {
		if(result==false) {
			JOptionPane.showMessageDialog(null, "중복입니다");
		}else {
			JOptionPane.showMessageDialog(null, "사용가능합니다");
			tfID.setEditable(false);
			pfPw.setEditable(true);
			pfPw2.setEditable(true);
			btnConfirm.setEnabled(true);
		}
		}}	
		
	private void pwcheck() {
		char[] pw = pfPw.getPassword();
		String pass = new String(pw);
		char[] pw1 = pfPw2.getPassword();
		String pass2 = new String(pw1);
		final String SAMEPT="(\\w)\\1\\1";
		Pattern ThreeChar=null;
		ThreeChar = Pattern.compile("(\\p{Alnum})\\1{2,}");
		//Matcher matcher;
		Matcher matcher1;
		//matcher = Pattern.compile(SAMEPT).matcher(pass);
		matcher1 = ThreeChar.matcher(pass);
		//String listchar ="abc|bcd|cde|def|efg|fgh|ghi|hij|ijk|jkl|klm|lmn|mno|nop|opq|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz|012|123|234|345|456|567|678|789|890";
		//String[] arrlistchar = listchar.split("\\|");
		//for(int i=0;i<arrlistchar.length;i++) {
		//	if(pass.toLowerCase().matches(".*"+arrlistchar[i]+".*")) {
		//		JOptionPane.showMessageDialog(null, "연속된 3자리 숫자나 문자 사용불가");
		//	}
		//}
		
		
		if(pass.length()<4||pass2.length()>8) {
			JOptionPane.showMessageDialog(null, "비밀번호를 4자리 이상 8자리 미만으로 입력하세요");
		}
		else if(matcher1.find()) {
			JOptionPane.showMessageDialog(null, "동일한 문자나 숫자를 3개이상 사용하실 수 없습니다.");
		}
		//else if(matcher.find()) {
		//	JOptionPane.showMessageDialog(null, "동일한 문자를 3개이상 연속으로 사용할 수 없습니다.");
		//}
		else if(pass.equals(pass2)) {
			JOptionPane.showMessageDialog(null, "비밀번호가 일치합니다");
			tfName.setEditable(true);
			tfPhone.setEditable(true);
			btnRegi.setEnabled(true);
			pfPw.setEditable(false);
			pfPw2.setEditable(false);
			
		}else {
			JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
		}
	}
	private void okaction() {
		String id =tfID.getText();
		char[] pass =pfPw.getPassword();
		String pw=new String(pass);
		char[] pass1=pfPw2.getPassword();
		String pw1 =  new String(pass1);
		String name= tfName.getText();
		String phone = tfPhone.getText();
		String file1 = tfFilepath.getText();
		
		
		
		
		
		if(name.length()!=0 && phone.length()!=0&&file1.length()!=0) {
			okaction1();
		
		}
		else{
			JOptionPane.showMessageDialog(null, "잘못된 부분이 있습니다.");}
		
	}
	
	private void okaction1() {
		String id =tfID.getText();
		char[] pass =pfPw.getPassword();
		String pw=new String(pass);
		char[] pass1=pfPw2.getPassword();
		String pw1 =  new String(pass1);
		String name= tfName.getText();
		String phone = tfPhone.getText();
		String file1 = tfFilepath.getText();
		
		FileInputStream input = null;
		File file = new File(tfFilepath.getText());
		try {
			input= new FileInputStream(file);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Dao dao = new Dao(id,pw,name,phone,input,file1);
		boolean result = dao.insertAction();
		
		if(result==true) {
			JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
			dispose();
		}else {
			JOptionPane.showMessageDialog(null, "잘못된 부분이 있습니다");
		}
		
		
	}
		
		private void filepath() {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG","PNG","BMP", "jpg","png","bmp");
			chooser.setFileFilter(filter);
			
			int ret = chooser.showOpenDialog(null);
			if(ret!=JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.");
				return;
			}
			String FILEPATH = chooser.getSelectedFile().getPath();
			tfFilepath.setText(FILEPATH);
			ImageIcon icon =  new ImageIcon(FILEPATH);
			Image i = icon.getImage();
			Image s = i.getScaledInstance(235, 139, java.awt.Image.SCALE_SMOOTH);
			ImageIcon icons =  new ImageIcon(s);
			lblImage.setIcon(icons);
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(lblImage);
			setVisible(true);

			
		
		}
		private void cancel() {
			dispose();
		}
	}

