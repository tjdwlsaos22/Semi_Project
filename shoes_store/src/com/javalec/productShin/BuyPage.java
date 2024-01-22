package com.javalec.productShin;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.javalec.cartShin.Cart;
import com.javalec.purchaseShin.OrderPage;
import com.javalec.sale.SaleDao;
import com.javalec.sale.SaleDto;
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
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BuyPage extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lblImage;
	private JLabel lblNewLabel_1;
	private JTextField tfBrand;
	private JLabel lblNewLabel_1_1;
	private JTextField tfName;
	private JLabel lblNewLabel_1_1_1;
	private JTextField tfPrice;
	private JLabel lblNewLabel_1_1_1_1;
	private JLabel lblNewLabel_1_1_1_1_1;
	private JButton btnNewButton;
	private JButton btnBack;
	private JLabel lblNewLabel_1_1_1_1_2;
	private JTextField tfCount;
	private JComboBox cbSize;
	private JComboBox cbColor;
	private Container container;

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
		getContentPane().setBackground(new Color(29, 84, 141));
		setBackground(new Color(64, 62, 255));
		setTitle("구매 페이지");
		setBounds(100, 100, 770, 519);
		getContentPane().setLayout(null);
		getContentPane().add(getLblImage());
		getContentPane().add(getLblNewLabel_1());
		getContentPane().add(getTfBrand());
		getContentPane().add(getLblNewLabel_1_1());
		getContentPane().add(getTfName());
		getContentPane().add(getLblNewLabel_1_1_1());
		getContentPane().add(getTfPrice());
		getContentPane().add(getLblNewLabel_1_1_1_1());
		getContentPane().add(getLblNewLabel_1_1_1_1_1());
		getContentPane().add(getBtnNewButton());
		getContentPane().add(getBtnBack());
		getContentPane().add(getLblNewLabel_1_1_1_1_2());
		getContentPane().add(getTfCount());

	}
	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("이미지");
			lblImage.setBackground(new Color(255, 128, 64));
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			lblImage.setBounds(42, 80, 400, 340);
		}
		return lblImage;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("브랜드");
			lblNewLabel_1.setBounds(460, 85, 61, 21);
		}
		return lblNewLabel_1;
	}
	private JTextField getTfBrand() {
		if (tfBrand == null) {
			tfBrand = new JTextField();
			tfBrand.setEnabled(false);
			tfBrand.setBounds(510, 85, 220, 21);
			tfBrand.setColumns(10);
		}
		return tfBrand;
	}
	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("상품명");
			lblNewLabel_1_1.setBounds(460, 130, 61, 21);
		}
		return lblNewLabel_1_1;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setEnabled(false);
			tfName.setColumns(10);
			tfName.setBounds(510, 130, 220, 21);
		}
		return tfName;
	}
	private JLabel getLblNewLabel_1_1_1() {
		if (lblNewLabel_1_1_1 == null) {
			lblNewLabel_1_1_1 = new JLabel("가격");
			lblNewLabel_1_1_1.setBounds(460, 181, 61, 21);
		}
		return lblNewLabel_1_1_1;
	}
	private JTextField getTfPrice() {
		if (tfPrice == null) {
			tfPrice = new JTextField();
			tfPrice.setEnabled(false);
			tfPrice.setColumns(10);
			tfPrice.setBounds(510, 181, 91, 21);
		}
		return tfPrice;
	}
	private JLabel getLblNewLabel_1_1_1_1() {
		if (lblNewLabel_1_1_1_1 == null) {
			lblNewLabel_1_1_1_1 = new JLabel("사이즈");
			lblNewLabel_1_1_1_1.setBounds(460, 230, 61, 21);
		}
		return lblNewLabel_1_1_1_1;
	}
	private JLabel getLblNewLabel_1_1_1_1_1() {
		if (lblNewLabel_1_1_1_1_1 == null) {
			lblNewLabel_1_1_1_1_1 = new JLabel("수량");
			lblNewLabel_1_1_1_1_1.setBounds(460, 330, 44, 21);
		}
		return lblNewLabel_1_1_1_1_1;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("장바구니 담기");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnCartClicked();
				}
			});
			btnNewButton.setBounds(640, 400, 95, 23);
		}
		return btnNewButton;
	}
	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("");
			
			ImageIcon icon = new ImageIcon(BuyPage.class.getResource("/com/javalec/images/backIcon.png"));
			Image changeToImg = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeToImg);
			btnBack.setIcon(changeIcon);
			btnBack.setHorizontalAlignment(SwingConstants.CENTER);
			
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clickedBackIcon();
				}
			});
			btnBack.setBounds(10, 10, 40, 40);
		}
		return btnBack;
	}
	private JLabel getLblNewLabel_1_1_1_1_2() {
		if (lblNewLabel_1_1_1_1_2 == null) {
			lblNewLabel_1_1_1_1_2 = new JLabel("색상");
			lblNewLabel_1_1_1_1_2.setBounds(460, 280, 61, 21);
		}
		return lblNewLabel_1_1_1_1_2;
	}
	private JTextField getTfCount() {
		if (tfCount == null) {
			tfCount = new JTextField();
			tfCount.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if((e.getKeyChar() >= '0' && e.getKeyChar() <= '9' ) || e.getKeyCode() == KeyEvent.VK_CAPS_LOCK ||
						e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					} else {
						JFrame jframe = new JFrame();
						jframe.setAlwaysOnTop(true);
						JOptionPane.showMessageDialog(jframe, "숫자만 입력하세요.");
					}
					
				}
			});
			
			tfCount.setHorizontalAlignment(SwingConstants.TRAILING);
			tfCount.setColumns(10);
			tfCount.setBounds(510, 330, 91, 21);
		}
		return tfCount;
	}
	
	//---------------- function -------------------------
	
	//SearchPage에서 브랜드 상품명 가격을 가지고 창이 열릴 때 정보 뿌려주는 메소드 
	public void selectByinfo(List<String> list) {
		
		tfBrand.setText(list.get(0));
		tfName.setText(list.get(1));
		tfPrice.setText(list.get(2));
		
		String filePath = Integer.toString(ShareVar.filename);
		if(!filePath.equals("0")) {
			
			lblImage.setText("");
			ImageIcon icon = new ImageIcon(filePath);
			Image changeToImg = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeToImg);
			lblImage.setIcon(changeIcon);
			
			
			
			lblImage.setIcon(new ImageIcon(filePath));
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		}
		
		getBrandSize(list.get(0), list.get(1));
		getBrandColor(list.get(0), list.get(1));
		
	}
	
	//브랜드명, 제품명을 가지고 그 해당 제품의 사이즈 가져오기
	public void getBrandSize(String brand, String name) {

		ProductDAO dao = new ProductDAO();
		ArrayList <ProductDTO> dtoList = dao.getBrandSize(brand, name);
		
		String[] qTxt = new String[dtoList.size()];
		for(int i = 0; i < dtoList.size(); i++) {
			qTxt[i] = Integer.toString(dtoList.get(i).getSize());
		}
		
		//cbSize comboBox 설정 
		container = this.getContentPane();
		container.setLayout(null);
		cbSize = null;
		if(cbSize == null) {
			cbSize = new JComboBox(qTxt);
			cbSize.setBounds(510, 228, 100, 27);
		}
		
		container.add(cbSize);
	}
	
	//브랜드와 제품의 색상 가져오기
	public void getBrandColor(String brand, String name) {

		ProductDAO dao = new ProductDAO(); 
		ArrayList <ProductDTO> dtoList = dao.getBrandColor(brand, name);
		
		String[] qTxt = new String[dtoList.size()];
		for(int i = 0; i < dtoList.size(); i++) {
			qTxt[i] = dtoList.get(i).getColor();
		}
		
		//cbColor comboBox 설정 
		container = this.getContentPane();
		container.setLayout(null);
		cbColor = null;
		if(cbColor == null) {
			cbColor = new JComboBox(qTxt);
			cbColor.setBounds(510, 278, 100, 27);
		}
		
		container.add(cbColor);
	}
	
	//뒤로가기 버튼 클릭 메소드 
	private void clickedBackIcon() {
		this.dispose();
		SearchPage page = new SearchPage();
		page.setVisible(true);
	}
	
	//장바구니 버튼 클릭했을 떼 
	private void btnCartClicked() {
		JFrame jframe = new JFrame();
		jframe.setAlwaysOnTop(true);
		ProductDAO dao = new ProductDAO();
		if(checkField() == true) {
			String tmBrand = tfBrand.getText();
			String tmName = tfName.getText();
			int tmSize = Integer.parseInt((String)cbSize.getSelectedItem());
			String tmColor = (String)cbColor.getSelectedItem();
			int tmCartCount = Integer.parseInt(tfCount.getText());
			
			
//			getAllInfo();
//			this.dispose();
//			Cart cart = new Cart();
//			cart.setVisible(true);
			int currentStock = dao.selectCurrentStock(tmBrand, tmName, tmSize, tmColor, tmCartCount);
			if(currentStock >= tmCartCount) {
			
				//먼저 수량만큼 제품 브랜드 사이즈 컬러 orderProd 마이너스 insert 처리, ]
				if(dao.insertOrderProdWhenClickedBtn(tmBrand, tmName, tmSize, tmColor, tmCartCount) == true) {
					System.out.println("success into BuyPage[insertOrderProdWhenClickedBtn]");
					int insertedOseq = dao.oseqByInsertedOrderProd(tmBrand, tmName, tmSize, tmColor, tmCartCount);	//insert한 orderProd의 oseq를 가져
				
					//insert된 oseq를 가지고 produc에 제품 tmBrand 
					if(dao.insertProductQy(insertedOseq, tmBrand, tmName, tmSize) == true) {
						getAllInfo();
						this.dispose();
						Cart cart = new Cart();
						cart.setVisible(true);
						System.out.println("이츠 성공!!");
						System.out.println(tmBrand + "/" + tmName + "/" + tmSize + "/" + tmColor +"/"+tmCartCount+"추가");
					} else {
						System.out.println("오우 노우 제발 ..");
					}
				}
			} else if(currentStock == 0) {
				JOptionPane.showMessageDialog(jframe, "현재 재고는 "+currentStock+"개 입니다. 관리자에게 문의바랍니다.");
				tfCount.setText("");
				tfCount.requestFocus();
			} else {
				JOptionPane.showMessageDialog(jframe, "현재 재고는 "+currentStock+"개 입니다. 수량을 다시 입력하시거나 관리자에게 문의바랍니다.");
				tfCount.setText("");
				tfCount.requestFocus();
			}
		
		} 
	}
	
	//field 체크 메소드 
	private boolean checkField() {
		JFrame jframe = new JFrame();
		jframe.setAlwaysOnTop(true);
		
		final String REX = "^[0-9]*$";
		
		if(tfCount.getText().length() != 0) {
			if(Pattern.matches(REX, tfCount.getText())) {
				return true;
			}
			JOptionPane.showMessageDialog(jframe, "숫자만 입력하세요.");
			tfCount.setText("");
			tfCount.requestFocus();
		}
		JOptionPane.showMessageDialog(jframe , "수량을 입력해주세요!");
		tfCount.requestFocus();
		return false;
	}
	
	
	
	//buypage에서 입력한 수량과 그 제품의 seq를 조회
	public List<String> getAllInfo() {
		String brand = tfBrand.getText();
		String name = tfName.getText();
		int price = Integer.parseInt(tfPrice.getText());
		int size = Integer.parseInt(cbSize.getSelectedItem().toString());
		int cnt = Integer.parseInt(tfCount.getText());
		String color = cbColor.getSelectedItem().toString();

		ProductDAO dao = new ProductDAO(brand, name, price, size, cnt, color);
		
		int cartBtnClickedBySeq = dao.getAllInfo();
		
		//list에 product의 pseq와 입력한 수량을 넣어줌 
		List<String> list = new ArrayList<>();
		list.add(0, Integer.toString(cartBtnClickedBySeq));
		list.add(1, Integer.toString(cnt));
		
		
		System.out.println("BuyPage[dto.getSeqno] : "+ cartBtnClickedBySeq);
		
		//cart객체 insertActionByCartBtnClicked메소드가 list를 가지고 실행 
		Cart cart = new Cart();
		cart.insertActionByCartBtnClicked(list);
		
		return list;
	}
		
	
	//getAllInfo()실행 전에 product에 orderProd insert 처리
	
	
//	
//	if(	dao.insertToOrderProduct(brand, name, cnt, size, color)) {
//		
//		//insert된 orderProd의 oseq 가져오기
//		System.out.println("OrderPage[inseredOseq] start");
//		int inseredOseq =dao.getInsertedOseq(brand, name, cnt, size, color);
//		System.out.println("OrderPage[inseredOseq] inseredOseq : "+inseredOseq);
//		System.out.println("OrderPage[inseredOseq] end");
//		
//		//위의 oseq를 가지고 product에 insert 
//		if(dao.insertProductQuery(inseredOseq,brand, name, size)) {
//			System.out.println("성공성공");
//			System.out.println(brand + "/" + name + "/" + size + "/" + color +"/"+cnt+"추가");
//		}
//	} else {
//		System.out.println("실패....");
//	}

}
