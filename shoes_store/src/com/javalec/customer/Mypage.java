package com.javalec.customer;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

import com.javalec.util.ShareVar;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Font;
import java.awt.Image;
import java.awt.JobAttributes;
import java.awt.Window;

import javax.swing.JPasswordField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Mypage extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lblNewLabel;
	private JLabel lblPw;
	private JLabel lblPw_1;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_1_1_1;
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfPhone;
	private JPasswordField pfPw;
	private JPasswordField pfPw2;
	private JButton btnCheckpw;
	private JButton btnEdit;
	private JButton btnFile;
	private JTextField tfFilepath;
	private JLabel lblImage;
	
	private int checkdialog=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mypage dialog = new Mypage();
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
	public Mypage() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				if(checkdialog==0) {
					action1();
					
				}
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
			
			}
		});
		setBounds(100, 100, 617, 443);
		getContentPane().setLayout(null);
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getLblPw());
		getContentPane().add(getLblPw_1());
		getContentPane().add(getLblNewLabel_1_1());
		getContentPane().add(getLblNewLabel_1_1_1());
		getContentPane().add(getTfId());
		getContentPane().add(getTfName());
		getContentPane().add(getTfPhone());
		getContentPane().add(getPfPw());
		getContentPane().add(getPfPw2());
		getContentPane().add(getBtnCheckpw());
		getContentPane().add(getBtnEdit());
		getContentPane().add(getBtnFile());
		getContentPane().add(getTfFilepath());
		getContentPane().add(getLblImage());

	}
	
	private void action1() {
		Dao dao = new Dao();
		Dto dto =dao.Action2();
		
		tfId.setText(dto.getUserid());
		tfName.setText(dto.getUsername());
		tfPhone.setText(dto.getUserphone());
		pfPw.setText(dto.getUserpw());
		char[] pw = pfPw2.getPassword();
		String pass = new String(pw);
		
		String filepath1 = dto.getFilepath();
		//System.out.println(filepath1);
		tfFilepath.setText(filepath1);
	
		ImageIcon icon =  new ImageIcon(filepath1);
		Image i = icon.getImage();
		Image s = i.getScaledInstance(235, 139, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icons =  new ImageIcon(s);
		lblImage.setIcon(icons);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblImage);
		setVisible(true);
		
		

		
		
		/*FileDialog fdopen = new FileDialog(this,"파일경로",FileDialog.LOAD);
		fdopen.setVisible(true);
		
		String path = fdopen.getDirectory();
		String name= fdopen.getFile();
		
		if(path!=null) {
			tfFilepath.setText(path+name);
		}*/
	
	
		
		
	
		
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("ID:");
			lblNewLabel.setFont(new Font("Gulim", Font.PLAIN, 16));
			lblNewLabel.setBounds(39, 62, 57, 25);
		}
		return lblNewLabel;
	}
	private JLabel getLblPw() {
		if (lblPw == null) {
			lblPw = new JLabel("PW:");
			lblPw.setFont(new Font("Gulim", Font.PLAIN, 16));
			lblPw.setBounds(39, 109, 57, 25);
		}
		return lblPw;
	}
	private JLabel getLblPw_1() {
		if (lblPw_1 == null) {
			lblPw_1 = new JLabel("PW 확인:");
			lblPw_1.setFont(new Font("Gulim", Font.PLAIN, 16));
			lblPw_1.setBounds(39, 161, 75, 25);
		}
		return lblPw_1;
	}
	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("이름:");
			lblNewLabel_1_1.setFont(new Font("Gulim", Font.PLAIN, 16));
			lblNewLabel_1_1.setBounds(39, 208, 57, 25);
		}
		return lblNewLabel_1_1;
	}
	private JLabel getLblNewLabel_1_1_1() {
		if (lblNewLabel_1_1_1 == null) {
			lblNewLabel_1_1_1 = new JLabel("전화번호:");
			lblNewLabel_1_1_1.setFont(new Font("Gulim", Font.PLAIN, 16));
			lblNewLabel_1_1_1.setBounds(39, 264, 75, 25);
		}
		return lblNewLabel_1_1_1;
	}
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setEditable(false);
			tfId.setBounds(166, 63, 131, 25);
			tfId.setColumns(10);
		}
		return tfId;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setEditable(false);
			tfName.setColumns(10);
			tfName.setBounds(166, 208, 106, 25);
		}
		return tfName;
	}
	private JTextField getTfPhone() {
		if (tfPhone == null) {
			tfPhone = new JTextField();
			tfPhone.setEditable(false);
			tfPhone.setColumns(10);
			tfPhone.setBounds(166, 264, 180, 25);
		}
		return tfPhone;
	}
	private JPasswordField getPfPw() {
		if (pfPw == null) {
			pfPw = new JPasswordField();
			pfPw.setBounds(166, 109, 152, 25);
		}
		return pfPw;
	}
	private JPasswordField getPfPw2() {
		if (pfPw2 == null) {
			pfPw2 = new JPasswordField();
			pfPw2.setBounds(166, 161, 152, 25);
		}
		return pfPw2;
	}
	private JButton getBtnCheckpw() {
		if (btnCheckpw == null) {
			btnCheckpw = new JButton("비밀번호 확인");
			btnCheckpw.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pwcheck();
				}
			});
			btnCheckpw.setBounds(339, 111, 106, 25);
		}
		return btnCheckpw;
	}
	private JButton getBtnEdit() {
		if (btnEdit == null) {
			btnEdit = new JButton("수정 완료");
			btnEdit.setEnabled(false);
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					update();
					
				}
			});
			btnEdit.setBounds(227, 357, 91, 25);
		}
		return btnEdit;
	}
	private JButton getBtnFile() {
		if (btnFile == null) {
			btnFile = new JButton("파일경로");
			btnFile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Filepath();
				}
				}
			);
			btnFile.setBounds(433, 339, 91, 25);
		}
		return btnFile;}
	
	private JTextField getTfFilepath() {
		if (tfFilepath == null) {
			tfFilepath = new JTextField();
			tfFilepath.setEditable(false);
			tfFilepath.setBounds(358, 374, 235, 21);
			tfFilepath.setColumns(10);
		}
		return tfFilepath;
	}
	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			lblImage.setBounds(358, 167, 235, 139);
		}
		return lblImage;
	}
	private void check1() {
		String id = tfId.getText();
		char[] pw = pfPw.getPassword();
		String pass = new String(pw);
		char[] pw1 = pfPw2.getPassword();
		String pass2 = new String(pw1);
		String name = tfName.getText();
		String phone =tfPhone.getText();
		String login ="";
		
		
		if(tfId.getText().length()==0) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
		}
		else {
			check3();
		}}
	
	private void check3() {
		String id = tfId.getText();
		char[] pw = pfPw.getPassword();
		String pass = new String(pw);
		char[] pw1 = pfPw2.getPassword();
		String pass2 = new String(pw1);
		String name = tfName.getText();
		String phone =tfPhone.getText();
		String login ="";
		int i=0;
		
		Dao dao = new Dao(id, pass);
		boolean result = dao.check();
		
		if(result==false) {
			JOptionPane.showMessageDialog(null, "이전 아이디이거나 중복입니다");
		}else {
			JOptionPane.showMessageDialog(null, "사용가능합니다");
			tfId.setEditable(false);
			pfPw.setEditable(true);
			pfPw2.setEditable(true);
			btnEdit.setEnabled(true);
			
		}	
		}
	private void pwcheck() {
		char[] pw = pfPw.getPassword();
		String pass = new String(pw);
		char[] pw1 = pfPw2.getPassword();
		String pass2 = new String(pw1);
		int i=1;
		final String SAMEPT="(\\w)\\1\\1";
		Pattern ThreeChar=null;
		ThreeChar = Pattern.compile("(\\p{Alnum})\\1{2,}");
		Matcher matcher1;
		matcher1 = ThreeChar.matcher(pass);
		
		if(pass.length()<4||pass2.length()>8) {
			JOptionPane.showMessageDialog(null, "비밀번호를 4자리 이상 8자리 미만으로 입력하세요");
			checkdialog=1;
		}
		else if(matcher1.find()) {
			JOptionPane.showMessageDialog(null, "동일한 문자나 숫자를 3개이상 사용하실 수 없습니다.");
			checkdialog=1;
		}
		else if(pass.equals(pass2)) {
			JOptionPane.showMessageDialog(null, "비밀번호가 일치합니다");
			checkdialog=1;
			tfName.setEditable(true);
			tfPhone.setEditable(true);
			pfPw.setEditable(false);
			pfPw2.setEditable(false);
			btnEdit.setEnabled(true);
			
		}else {
			JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다");
			checkdialog=1;
		}
	}
	
	
	private void update() {
		String id =tfId.getText();
		char[] pass =pfPw.getPassword();
		String pw=new String(pass);
		char[] pass1=pfPw2.getPassword();
		String pw1 =  new String(pass1);
		String name= tfName.getText();
		String phone = tfPhone.getText();
		String file1 = tfFilepath.getText();
		
		
		if(id.length()!=0 &&pw.length()!=0&&pw1.length()!=0 &&name.length()!=0&& phone.length()!=0&&file1.length()!=0) {
			update2();
		
		}
		else{
			JOptionPane.showMessageDialog(null, "잘못된 부분이 있습니다.");}}

	
	private void update2() {
		String id =tfId.getText();
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
		boolean result = dao.updateAction();
		if(result==true) {
			JOptionPane.showMessageDialog(null, tfName.getText()+"님의 수정이 완료되었습니다.");
			dispose();
			
		}else {
			JOptionPane.showMessageDialog(null, "잘못된 부분이 없는지 확인하세요");
		}}

		/*private void Filepath() {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG","PNG","BMP", "jpg","png","bmp");
			chooser.setFileFilter(filter);*/
			
			//int result = chooser.showOpenDialog(null);
			//if(result ==JFileChooser.APPROVE_OPTION) {
				/*String filepath = file
				return;
			}
			String filepath = chooser.getSelectedFile().getPath();// 파일경로 가져오기
			tfFilepath.setText(filepath);
			lblImage.setIcon(new ImageIcon(filepath));
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			
			File file = new File(filepath);
			file.delete();
		
		}*/
				
				/*int result = chooser.showOpenDialog(null);
				if(result ==JFileChooser.APPROVE_OPTION) {
					String filepath = chooser.getSelectedFile().getAbsolutePath();
					System.out.println("selected file"+filepath);
					tfFilepath.setText(filepath);
				
				lblImage.setIcon(new ImageIcon(filepath));
				lblImage.setHorizontalAlignment(SwingConstants.CENTER);
				
				btnEdit.setEnabled(true);}
				
			}*/
			
			private void Filepath() {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG","PNG","BMP", "jpg","png","bmp");
				chooser.setFileFilter(filter);
				
				int ret = chooser.showOpenDialog(null);
				if(ret!=JFileChooser.APPROVE_OPTION&&tfFilepath.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.");
					checkdialog=1;
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
				checkdialog=1;
				}
	
	private void change1() {
		
		tfId.setEditable(false);
		btnCheckpw.setEnabled(true);
		pfPw.setEditable(true);
		pfPw2.setEditable(true);
	}}
		