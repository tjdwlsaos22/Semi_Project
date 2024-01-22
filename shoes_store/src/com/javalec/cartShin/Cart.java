package com.javalec.cartShin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.adminOrder.AdminOrderDao;
import com.javalec.product.BuyPage;
import com.javalec.product.ProductDAO;
import com.javalec.product.ProductDTO;
import com.javalec.productShin.SearchPage;
import com.javalec.purchaseShin.OrderPage;
import com.javalec.util.ShareVar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.SwingConstants;

public class Cart extends JDialog {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JTextField tfTotalCnt;
	private JButton btnPurchase;
	private JButton btnNewButton_1;
	private JTable innertable;
	private final DefaultTableModel outerTable = new DefaultTableModel();
	private JButton btnProduct;
	private JLabel lblNewLabel_1;
	private JTextField tfTotalMoney;
	private JButton btnLogout;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cart dialog = new Cart();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Cart() {
		getContentPane().setBackground(new Color(29, 84, 141));
		setBackground(new Color(64, 62, 255));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				tableInit();
				searchAction();
				showTotalInfo();
			}
		});

		setTitle("장바구니");
		setBounds(100, 100, 770, 519);
		getContentPane().setLayout(null);
		getContentPane().add(getScrollPane());
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getTfTotalCnt());
		getContentPane().add(getBtnPurchase());
		getContentPane().add(getBtnNewButton_1());
		getContentPane().add(getBtnProduct());
		getContentPane().add(getLblNewLabel_1());
		getContentPane().add(getTfTotalMoney());

	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(36, 60, 700, 250);
			scrollPane.setViewportView(getInnertable());
		}
		return scrollPane;
	}

	private JTable getInnertable() {
		if (innertable == null) {
			innertable = new JTable();
			innertable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tableClick();
				}
			});
			innertable.setFillsViewportHeight(true);
			innertable.setBorder(new LineBorder(new Color(0, 0, 0)));
			innertable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innertable.setModel(outerTable);
		}
		return innertable;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("총 구매 수량 : ");
			lblNewLabel.setBounds(320, 360, 80, 15);
		}
		return lblNewLabel;
	}

	private JTextField getTfTotalCnt() {
		if (tfTotalCnt == null) {
			tfTotalCnt = new JTextField();
			tfTotalCnt.setEditable(false);
			tfTotalCnt.setHorizontalAlignment(SwingConstants.TRAILING);
			tfTotalCnt.setBounds(400, 357, 110, 21);
			tfTotalCnt.setColumns(10);
		}
		return tfTotalCnt;
	}

	private JButton getBtnPurchase() {
		if (btnPurchase == null) {
			btnPurchase = new JButton("구매하기");
			btnPurchase.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buyBtnClicked(searchAction());
				}
			});
			btnPurchase.setBounds(540, 423, 91, 23);
		}
		return btnPurchase;
	}

	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("삭제하기");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteBtnClick();
				}
			});
			btnNewButton_1.setBounds(640, 423, 91, 23);
		}
		return btnNewButton_1;
	}

	private JButton getBtnProduct() {
		if (btnProduct == null) {
			btnProduct = new JButton("");
			ImageIcon icon = new ImageIcon(OrderPage.class.getResource("/com/javalec/images/homeIcon.png"));
			Image changeToImg = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeToImg);
			btnProduct.setIcon(changeIcon);
			btnProduct.setHorizontalAlignment(SwingConstants.CENTER);
			
			btnProduct.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					goToProductPage();
				}
			});
			btnProduct.setBounds(10, 10, 40, 40);
		}
		return btnProduct;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("총 구매 금액 :");
			lblNewLabel_1.setBounds(540, 360, 80, 15);
		}
		return lblNewLabel_1;
	}

	private JTextField getTfTotalMoney() {
		if (tfTotalMoney == null) {
			tfTotalMoney = new JTextField();
			tfTotalMoney.setEditable(false);
			tfTotalMoney.setHorizontalAlignment(SwingConstants.TRAILING);
			tfTotalMoney.setColumns(10);
			tfTotalMoney.setBounds(620, 357, 110, 21);
		}
		return tfTotalMoney;
	}

//---	Function  ---

//	Table 초기화 하기
	private void tableInit() {
		outerTable.addColumn("NO");
		outerTable.addColumn("브랜드명");
		outerTable.addColumn("제품명");
		outerTable.addColumn("색상");
		outerTable.addColumn("사이즈");
		outerTable.addColumn("가격");
		outerTable.addColumn("수량");
		outerTable.setColumnCount(7);

//		Table Column 크기 정하기
		int colNo = 0;
		TableColumn col = innertable.getColumnModel().getColumn(colNo);
		int width = 50;
		col.setPreferredWidth(width);

		colNo = 1;
		col = innertable.getColumnModel().getColumn(colNo);
		width = 120;
		col.setPreferredWidth(width);

		colNo = 2;
		col = innertable.getColumnModel().getColumn(colNo);
		width = 180;
		col.setPreferredWidth(width);

		colNo = 4;
		col = innertable.getColumnModel().getColumn(colNo);
		width = 100;
		col.setPreferredWidth(width);

		colNo = 5;
		col = innertable.getColumnModel().getColumn(colNo);
		width = 100;
		col.setPreferredWidth(width);
		
		colNo = 6;
		col = innertable.getColumnModel().getColumn(colNo);
		width = 80;
		col.setPreferredWidth(width);
		

		innertable.setAutoResizeMode(innertable.AUTO_RESIZE_OFF);

		// Table 내용 지우기
		int i = outerTable.getRowCount();
		for (int j = 0; j < i; j++) {
			outerTable.removeRow(0);
		}
	}

	// 제품현황가기 버튼
	private void goToProductPage() {
		SearchPage page = new SearchPage();
		this.dispose();
		page.setVisible(true);
	}

	// BuyPage에서 메소드 호출되야하므로 public
	// BuyPage에서 장바구니 버튼 눌렀을 때, cart테이블에 정보 insert 처리
	public void insertActionByCartBtnClicked(List<String> list) {

		int seq = Integer.parseInt(list.get(0));
		int cartCount = Integer.parseInt(list.get(1));
		System.out.println("Cart[insertActionByCartBtnClicked] seq : "+seq);
		System.out.println("Cart[insertActionByCartBtnClicked] cartCount : "+cartCount);
		CartDao dao = new CartDao(seq, cartCount);
		dao.insertActionByCartBtnClicked();
	}

	// 테이블에 조회되도록
	public ArrayList<CartDto> searchAction() {
		CartDao dao = new CartDao();

		// cartnum, obrand, oname, osize, oprice, cnt를 가져
		ArrayList<CartDto> dtoList = dao.selecList();
		for (int i = 0; i < dtoList.size(); i++) {
			String tmCartNum = Integer.toString(dtoList.get(i).getCartNum());
			String tmSize = Integer.toString(dtoList.get(i).getSize());
			
			DecimalFormat decFormat = new DecimalFormat("###,###");
			int tmp3 = dtoList.get(i).getPrice();
			String tmPrice = decFormat.format(tmp3);
			String tmCartCount = Integer.toString(dtoList.get(i).getCartCount());
			String[] qTxt = { tmCartNum, dtoList.get(i).getBrand(), dtoList.get(i).getName(), dtoList.get(i).getColor() , tmSize, tmPrice,
					tmCartCount };
			outerTable.addRow(qTxt);
		}
//		updateorderProdInfo(dtoList);
		return dtoList;
	}
	
	// 테이블 클릭 했을 때
	private int tableClick() {
		int i = innertable.getSelectedRow();

		return i;
	}
	
	// 장바구니 삭제 
	private void deleteBtnClick() {
		JFrame jframe = new JFrame();
		jframe.setAlwaysOnTop(true);

		int rowNum = this.tableClick();
		if (rowNum >= 0) {

			String tkCartNum = (String) innertable.getValueAt(rowNum, 0);
			int wkCartNum = Integer.parseInt(tkCartNum);

			CartDao dao = new CartDao();
			dao.deleteAction(wkCartNum);
			JOptionPane.showMessageDialog(jframe, "삭제되었습니다.");

		} else {
			JOptionPane.showMessageDialog(jframe, "삭제할 상품을 선택해 주세요.");
		}
	}
	
	//총 구매수량, 총 구매 금액 조회하기 
	private void showTotalInfo() {
		
		CartDao dao = new CartDao();
		CartDto dto = dao.showTotalInfo();
		
		DecimalFormat decFormat = new DecimalFormat("###,###");
		int tmp3 = dto.getTotalMoney();
		String tmPrice = decFormat.format(tmp3);
		
		tfTotalCnt.setText(Integer.toString(dto.getTotalCnt()));
		tfTotalMoney.setText(tmPrice);
		
	}
	
	// 구매하기 버튼 클릭했을 때, (구매 테이블에 insert, 장바구니 테이블 delete 처리)
	private void buyBtnClicked(ArrayList<CartDto> list) {
		int num = 1;
		
		JFrame jframe = new JFrame();
		jframe.setAlwaysOnTop(true);
		CartDao dao = new CartDao();
		if(JOptionPane.showConfirmDialog(jframe, "위 상품들을 구매하시겠습니까?","알림",JOptionPane.YES_NO_OPTION) == 0) {

			//구매테이블에 정보 insert 하기
			
			if(dao.insertCartInfo() == true) {
				deleteCartInfo();
				//updateorderProdInfo(list);
				System.out.println("insertCartInfo() : CART 정보가 Purchase 테이블에 들어갔습니다.");
				JOptionPane.showMessageDialog(jframe, "구매완료되었습니다.");
				OrderPage orderPage = new OrderPage();
				orderPage.setVisible(true);
				this.dispose();
				
			} else {
				System.out.println("실패 ");
				JOptionPane.showMessageDialog(jframe,"실행 도중 문제가 발생했습니다.");
			}
		}
		
	}
	

	//구매하기 버튼 클릭 시, orderProd에 수량 update
//	private void updateorderProdInfo(ArrayList<CartDto> dtoList) {
//		
//		System.out.println("updateorderProdInfo called");
//		CartDao dao = null;
//		for(int i = 0; i < dtoList.size(); i++) {
//			//String brand, String name, int cnt, int size, String color
//			// tmCartNum, dtoList.get(i).getBrand(), dtoList.get(i).getName(), dtoList.get(i).getColor() , tmSize, tmPrice	tmCartCount };
//			
//			String tmBrand = dtoList.get(i).getBrand();
//			String tmName = dtoList.get(i).getName();
//			int tmCartCount = dtoList.get(i).getCartCount();
//			int tmSize = dtoList.get(i).getSize();
//			String tmColor = dtoList.get(i).getColor();
//			
//			
//			System.out.println("goto updateorderProdInfo ");
//			dao = new CartDao();
//			if(dao.updateorderProdInfo(tmBrand, tmName, tmCartCount, tmSize, tmColor) == true) {
//				System.out.println("성공 : Cart ");
//			} else {
//				System.out.println("실패 ");
//			}
//		}
//	}
	
	
	//장바구니 비우기
	private void deleteCartInfo() {
		CartDao dao = new CartDao();
		
		if(dao.deleteCartInfo() == true) {
			System.out.println(" deleteCartInfo(): CART 정보가 삭제됨 ");
		} else {
			System.out.println("실패 ");
			JOptionPane.showMessageDialog(null,"실행 도중 문제가 발생했습니다.");
		}
	}

}
