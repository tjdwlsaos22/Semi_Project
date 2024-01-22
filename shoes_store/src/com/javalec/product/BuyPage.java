package com.javalec.product;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.javalec.cart.Cart;
import com.javalec.util.ShareVar;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

public class BuyPage extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lblImage;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	private JTextField tfName;
	private JLabel lblNewLabel_1_1_1;
	private JTextField tfPrice;
	private JLabel lblNewLabel_1_1_1_1;
	private JLabel lblNewLabel_1_1_1_1_1;
	private JButton btnCartPage;
	private JButton btnSearchPage;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_1_1_1_1_2;
	private JTextField tfCount;
	private JTextField tfBrand;
	private JComboBox<Object> cbColor;
	private JComboBox<Object> cbSize;
	private Container container ;;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyPage dialog = new BuyPage();
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
	public BuyPage() {
		getContentPane().setEnabled(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				
			}
		});
		setTitle("구매 페이지");
		setBounds(100, 100, 973, 521);
		getContentPane().setLayout(null);
		getContentPane().add(getLblImage());
		getContentPane().add(getLblNewLabel_1());
		getContentPane().add(getLblNewLabel_1_1());
		getContentPane().add(getTfName());
		getContentPane().add(getLblNewLabel_1_1_1());
		getContentPane().add(getTfPrice());
		getContentPane().add(getLblNewLabel_1_1_1_1());
		getContentPane().add(getLblNewLabel_1_1_1_1_1());
		getContentPane().add(getBtnCartPage());
		getContentPane().add(getBtnSearchPage());
		getContentPane().add(getLblNewLabel_2());
		getContentPane().add(getLblNewLabel_1_1_1_1_2());
		getContentPane().add(getTfCount());
//		getContentPane().add(getCbSIze());
		getContentPane().add(getTfBrand());
//		getContentPane().add(getCbColor_01());
//		getContentPane().add(getCbColor());

	}
	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("이미지");
			lblImage.setBackground(new Color(255, 128, 64));
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			lblImage.setBounds(27, 194, 535, 259);
		}
		return lblImage;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("브랜드");
			lblNewLabel_1.setBounds(602, 85, 61, 21);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("상품명");
			lblNewLabel_1_1.setBounds(602, 131, 61, 21);
		}
		return lblNewLabel_1_1;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setEditable(false);
			tfName.setColumns(10);
			tfName.setBounds(659, 131, 248, 21);
		}
		return tfName;
	}
	private JLabel getLblNewLabel_1_1_1() {
		if (lblNewLabel_1_1_1 == null) {
			lblNewLabel_1_1_1 = new JLabel("가격");
			lblNewLabel_1_1_1.setBounds(602, 181, 61, 21);
		}
		return lblNewLabel_1_1_1;
	}
	private JTextField getTfPrice() {
		if (tfPrice == null) {
			tfPrice = new JTextField();
			tfPrice.setEditable(false);
			tfPrice.setColumns(10);
			tfPrice.setBounds(659, 181, 120, 21);
		}
		return tfPrice;
	}
	private JLabel getLblNewLabel_1_1_1_1() {
		if (lblNewLabel_1_1_1_1 == null) {
			lblNewLabel_1_1_1_1 = new JLabel("사이즈");
			lblNewLabel_1_1_1_1.setBounds(602, 230, 61, 21);
		}
		return lblNewLabel_1_1_1_1;
	}
	private JLabel getLblNewLabel_1_1_1_1_1() {
		if (lblNewLabel_1_1_1_1_1 == null) {
			lblNewLabel_1_1_1_1_1 = new JLabel("수량");
			lblNewLabel_1_1_1_1_1.setBounds(602, 289, 44, 21);
		}
		return lblNewLabel_1_1_1_1_1;
	}
	private JButton getBtnCartPage() {
		if (btnCartPage == null) {
			btnCartPage = new JButton("장바구니 담기");
			btnCartPage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
//					창 종료
					dispose();
					
//					Cart Page 로 이동
					Cart cart = new Cart();
					cart.main(null);
					
				}
			});
			btnCartPage.setBounds(785, 352, 121, 52);
		}
		return btnCartPage;
	}
	private JButton getBtnSearchPage() {
		if (btnSearchPage == null) {
			btnSearchPage = new JButton("뒤로가기");
			btnSearchPage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
//					창 종료
					dispose();
					
//					Search Page 로 이동
					SearchPage searchPage = new SearchPage();
					searchPage.setVisible(true);
					
				}
			});
			btnSearchPage.setBounds(27, 24, 97, 42);
		}
		return btnSearchPage;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setIcon(new ImageIcon(BuyPage.class.getResource("/com/javalec/images/shoes_logo.png")));
			lblNewLabel_2.setBounds(350, 10, 160, 163);
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_1_1_1_1_2() {
		if (lblNewLabel_1_1_1_1_2 == null) {
			lblNewLabel_1_1_1_1_2 = new JLabel("색상");
			lblNewLabel_1_1_1_1_2.setBounds(768, 231, 44, 21);
		}
		return lblNewLabel_1_1_1_1_2;
	}
	
	private JTextField getTfBrand() {
		if (tfBrand == null) {
			tfBrand = new JTextField();
			tfBrand.setEditable(false);
			tfBrand.setBounds(659, 85, 247, 21);
			tfBrand.setColumns(10);
		}
		return tfBrand;
	}
	
	
	private JTextField getTfCount() {
		if (tfCount == null) {
			tfCount = new JTextField();
			tfCount.setColumns(10);
			tfCount.setBounds(659, 289, 91, 21);
		}
		return tfCount;
	}
	
	
//	private JComboBox getCbSIze() {
//		if (cbSIze == null) {		
//			cbSIze = new JComboBox();
//			cbSIze.setBounds(659, 229, 91, 23);
//		}
//		return cbSIze;
//	}

	
//	private JComboBox getCbColor() {
//		if (cbColor == null) {
//			cbColor = new JComboBox();
//			cbColor.setBounds(810, 230, 96, 23);
//		}
//		return cbColor;
//	}
	

//	--- Function ---
	
//	 SearchPage 정보를 받아서 출력
	public void selectByinfo(List<String> list) {	
		
		tfBrand.setText(list.get(0));
		tfName.setText(list.get(1));
		tfPrice.setText(list.get(2));
		
//		 Image 처리 : filename 이 틀려야 보여주기가 가능
		String fileImage = Integer.toString(ShareVar.filename);
		if(!fileImage.equals("0")) {
			lblImage.setText("");
			lblImage.setIcon(new ImageIcon(fileImage));
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		}
		
		cbColorAction(list);
		if(cbColor != null) {
			cbColor.setVisible(true);			
		}
		
		cbSizeAction(list);
		if(cbSize != null) {
			cbSize.setVisible(true);			
		}
		
	}
	
//	private void cbSizeAction() {
//		
//	}
	
	private void cbColorAction(List<String> list) {
		
		String cbBrand =  list.get(0);
		String cbName =  list.get(1);
		
		
		ProductDAO dao = new ProductDAO();
		ArrayList<ProductDTO> dtoList = dao.comboColorAction(cbBrand, cbName);
		
//		System.out.println("dddd : "+dtoList.size());
		
		int listCount = dtoList.size();
		
		String[] qTxt = new String[listCount];
		for(int i = 0; i<listCount; i++) {
			
			qTxt[i] = dtoList.get(i).getColor();
//			System.out.println(qTxt[i]);	
		}
		
		container = this.getContentPane();
		container.setLayout(null);
		cbColor = null;
		if(cbColor ==null) {
			cbColor = new JComboBox<Object>(qTxt);
			cbColor.setBounds(816, 229, 75, 23);
		}
//		System.out.println("cbColor : " + cbColor.toString());
		container.add(cbColor);
		
	}
	
	private void cbSizeAction(List<String> list) {
		
		String cbBrand =  list.get(0);
		String cbName =  list.get(1);
		
		
		ProductDAO dao = new ProductDAO();
		ArrayList<ProductDTO> dtoList = dao.comboSizeAction(cbBrand, cbName);
		
//		System.out.println("dddd : "+dtoList.size());
		
		int listCount = dtoList.size();
		
		Integer[] qTxt = new Integer[listCount];
		for(int i = 0; i<listCount; i++) {
			
			qTxt[i] = dtoList.get(i).getSize();
//			System.out.println(qTxt[i]);	
		}
		
		container = this.getContentPane();
		container.setLayout(null);
		cbSize = null;
		if(cbSize ==null) {
			cbSize = new JComboBox<Object>(qTxt);
			cbSize.setBounds(659, 230, 106, 23);
		}
//		System.out.println("cbColor : " + cbColor.toString());
		container.add(cbSize);
		
	}
}
