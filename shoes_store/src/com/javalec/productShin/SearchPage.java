package com.javalec.productShin;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.adminOrder.AdminOrderDao;
import com.javalec.adminOrder.AdminOrderDto;
import com.javalec.base.Main;
import com.javalec.cartShin.Cart;
import com.javalec.customer.CustomerMain;
import com.javalec.customer.Mypage;
import com.javalec.purchaseShin.OrderPage;
import com.javalec.util.ShareVar;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class SearchPage extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lblImage;
	private JButton btnLogout;
	private JButton btnUserInfo;
	private JTextField tfSelection;
	private JButton btnQuery;
	private JButton btnNewButton_3;
	private JButton btnNewButton_3_1;
	
//	outerTable 생성
	private final DefaultTableModel outerTable = new DefaultTableModel();
	private JButton btnCart;
	private JButton btnPurchase;
	private JScrollPane scrollPane;
	private JTable innerTable;
	private JComboBox cbSelect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchPage dialog = new SearchPage();
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
	public SearchPage() {
		getContentPane().setBackground(new Color(29, 84, 141));
		setBackground(new Color(64, 62, 255));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				tableInit();
				searchAction();
			}
		});
		setTitle("메인화면");
		setBounds(100, 100, 770, 519);
		getContentPane().setLayout(null);
		getContentPane().add(getLblImage());
		getContentPane().add(getBtnLogout());
		getContentPane().add(getBtnUserInfo());
		getContentPane().add(getTfSelection());
		getContentPane().add(getBtnQuery());
		getContentPane().add(getBtnNewButton_3());
		getContentPane().add(getBtnNewButton_3_1());
		getContentPane().add(getBtnCart());
		getContentPane().add(getBtnPurchase());
		getContentPane().add(getScrollPane_1());
		getContentPane().add(getCbSelect());

	}
	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			
			ImageIcon icon = new ImageIcon(SearchPage.class.getResource("/com/javalec/images/logo.png"));
			Image changeToImg = icon.getImage().getScaledInstance(360, 380, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeToImg);
			lblImage.setIcon(changeIcon);
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			lblImage.setBounds(200, 10, 360, 150);
			
		}
		return lblImage;
	}
	private JButton getBtnLogout() {
		if (btnLogout == null) {
			btnLogout = new JButton("");
			
			ImageIcon icon = new ImageIcon(SearchPage.class.getResource("/com/javalec/images/logougIcon.png"));
			Image changeToImg = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeToImg);
			btnLogout.setIcon(changeIcon);
			btnLogout.setHorizontalAlignment(SwingConstants.CENTER);
			
			btnLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clickedLogoutBtn();
				}
			});
			btnLogout.setBounds(720, 10, 40, 40);
			
		}
		return btnLogout;
	}
	private JButton getBtnUserInfo() {
		if (btnUserInfo == null) {
			btnUserInfo = new JButton("");
			btnUserInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					goToMyPage();
				}
			});
			
			ImageIcon icon = new ImageIcon(SearchPage.class.getResource("/com/javalec/images/profileIcon.png"));
			Image changeToImg = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeToImg);
			btnUserInfo.setIcon(changeIcon);
			btnUserInfo.setHorizontalAlignment(SwingConstants.CENTER);
			
			btnUserInfo.setBounds(670, 10, 40, 40);
		}
		return btnUserInfo;
	}
	private JTextField getTfSelection() {
		if (tfSelection == null) {
			tfSelection = new JTextField();
			tfSelection.setBounds(132, 157, 200, 30);
			tfSelection.setColumns(10);
		}
		return tfSelection;
	}
	private JButton getBtnQuery() {
		if (btnQuery == null) {
			btnQuery = new JButton("");
			btnQuery.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tableInit();
					searchCondition();
				}
			});
			btnQuery.setBounds(338, 157, 30, 30);
			
			ImageIcon icon = new ImageIcon(OrderPage.class.getResource("/com/javalec/images/searchIcon.png"));
			Image changeToImg = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeToImg);
			btnQuery.setIcon(changeIcon);
			btnQuery.setHorizontalAlignment(SwingConstants.CENTER);
			
			
			
		}
		return btnQuery;
	}
	private JButton getBtnNewButton_3() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton("장바구니 현황");
			btnNewButton_3.setBounds(327, 775, 142, 49);
		}
		return btnNewButton_3;
	}
	private JButton getBtnNewButton_3_1() {
		if (btnNewButton_3_1 == null) {
			btnNewButton_3_1 = new JButton("주문 내역");
			btnNewButton_3_1.setBounds(484, 775, 142, 49);
		}
		return btnNewButton_3_1;
	}
	
	private JButton getBtnCart() {
		if (btnCart == null) {
			btnCart = new JButton("");
			
			ImageIcon icon = new ImageIcon(SearchPage.class.getResource("/com/javalec/images/cartIcon.png"));
			Image changeToImg = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeToImg);
			btnCart.setIcon(changeIcon);
			btnCart.setHorizontalAlignment(SwingConstants.CENTER);
			
			btnCart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnCartClicked();
				}
			});
			btnCart.setBounds(570, 10, 40, 40);
			
		}
		return btnCart;
	}
	
	private JButton getBtnPurchase() {
		if (btnPurchase == null) {
			btnPurchase = new JButton("");
			
			ImageIcon icon = new ImageIcon(SearchPage.class.getResource("/com/javalec/images/orderIcon.png"));
			Image changeToImg = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeToImg);
			btnPurchase.setIcon(changeIcon);
			btnPurchase.setHorizontalAlignment(SwingConstants.CENTER);
			
			btnPurchase.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					goToOrderPage(); 
				}
			});
			btnPurchase.setBounds(620, 10, 40, 40);
			
		}
		return btnPurchase;
	}
	
	private JScrollPane getScrollPane_1() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(33, 200, 700, 250);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}
	
	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable();
			innerTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tableClick();
				}
			});
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerTable.setModel(outerTable);
		}
		
		return innerTable;
	}
	
	private JComboBox getCbSelect() {
		if (cbSelect == null) {
			cbSelect = new JComboBox();
			cbSelect.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					cbSelectInfo();
				}
			});
			cbSelect.setModel(new DefaultComboBoxModel(new String[] {"브랜드", "제품명"}));
			cbSelect.setBounds(32, 157, 95, 30);
		}
		return cbSelect;
	}
	
//---	Function  ---
	
	
//	Table 초기화 하기
	private void tableInit() {
		outerTable.addColumn("브랜드");
		outerTable.addColumn("상품명");
		outerTable.addColumn("가격");
		outerTable.setColumnCount(3);
		
		
//	Table Column 크기 정하기
		int colNo = 0;
		TableColumn col = innerTable.getColumnModel().getColumn(colNo);
		int width = 100;
		col.setPreferredWidth(width);
	
		colNo = 1;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 100;
		col.setPreferredWidth(width);
		
		colNo = 2;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 200;
		col.setPreferredWidth(width);
		
		//innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF);
		
		// Table 내용 지우기
		int i = outerTable.getRowCount();
		for(int j = 0; j<i; j++) {
			outerTable.removeRow(0);
		}
	}
	
	//	검색	
	private void searchAction() {
		ProductDAO dao = new ProductDAO();
		ArrayList<ProductDTO> dtoList = dao.selecList();
		
		int listCount = dtoList.size();
		
		for(int i = 0; i < listCount; i++) {
			DecimalFormat decFormat = new DecimalFormat("###,###");
			int tmp3 = dtoList.get(i).getPrice();
			String tmPrice = decFormat.format(tmp3);
			
			String[] qTxt = {
									 dtoList.get(i).getBrand(),
									 dtoList.get(i).getName(),
									 tmPrice};
			outerTable.addRow(qTxt);
		}
	}

//	Table 에서 Row 를 click 했을 경우 (브랜드명, 제품명, 가격, 제품사진
	
	private  List<String> tableClick() {
		int i = innerTable.getSelectedRow();
		
		System.out.println("dtdt : tableclick2 I : "+i );
		List<String> array = new ArrayList<>();
		
		if(i >= 0) {
			String tkBrand = (String)innerTable.getValueAt(i, 0);
			String tkName = (String)innerTable.getValueAt(i, 1);
			String tmp = (String)innerTable.getValueAt(i, 2);
			String tmPrice = tmp.replaceAll(",", "");
			
			
			int tkPrice = Integer.parseInt(tmPrice);
			
			
			ProductDAO dao = new ProductDAO(tkBrand, tkName, tkPrice);
			ProductDTO dto = dao.tableClick();
			
			array.add(dto.getBrand());
			array.add(dto.getName());
			array.add(Integer.toString(dto.getPrice()));
			
			dispose();
			
			BuyPage buyPage = new BuyPage();
			buyPage.setVisible(true);
			buyPage.selectByinfo(array);
		} 
		
		return array;
		
	}
	
	private void clickedLogoutBtn() {
		dispose();
		CustomerMain main = new CustomerMain();
		main.main(null);
	}
	
	
	private void btnCartClicked() {
		this.dispose();
		Cart cart = new Cart();
		cart.setVisible(true);
	}

	private void goToOrderPage() {
		this.dispose();
		OrderPage orderPage = new OrderPage();
		orderPage.setVisible(true);
	}
	
	//콤보박스 item check
	private int cbSelectInfo() {
		int i = cbSelect.getSelectedIndex();
		return i;
	}
	
	private void searchCondition() {
		ProductDAO dao = null;
		String str = tfSelection.getText();
		switch(cbSelectInfo()) {
		case 0:
			dao = new ProductDAO();
			ArrayList<ProductDTO> dtoList  = dao.searchConditionToBrand(str);

			for(int i = 0; i < dtoList.size(); i++) {
				DecimalFormat decFormat = new DecimalFormat("###,###");
				int tmp3 = dtoList.get(i).getPrice();
				String tmPrice = decFormat.format(tmp3);
				
				String[] qTxt = {
										 dtoList.get(i).getBrand(),
										 dtoList.get(i).getName(),
										 tmPrice};
				tableInit();
				outerTable.addRow(qTxt);
			}
			break;
			
		case 1:
			dao = new ProductDAO();
			ArrayList<ProductDTO> list  = dao.searchConditionToName(str);

			for(int i = 0; i < list.size(); i++) {
				DecimalFormat decFormat = new DecimalFormat("###,###");
				int tmp3 = list.get(i).getPrice();
				String tmPrice = decFormat.format(tmp3);
				String[] qTxt = {list.get(i).getBrand(),list.get(i).getName(), tmPrice};
				
				outerTable.addRow(qTxt);
			}
			break;
			
		}
	}
	
	private void goToMyPage() {
		System.out.println("searchPage: "+ShareVar.userid);
		this.dispose();
		Mypage page = new Mypage();
		page.setVisible(true);
	}
	
	
}
