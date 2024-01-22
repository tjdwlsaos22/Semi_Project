package com.javalec.sale;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView.TableRow;

import com.javalec.adminOrder.AdminOrderDao;
import com.javalec.adminOrder.AdminOrderDto;
import com.javalec.adminOrder.AdminOrderPage;
import com.javalec.adminStock.AdminStockPage;
import com.javalec.base.Main;
import com.javalec.customer.CustomerMain;
import com.javalec.util.ShareVar;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class SalePage extends JDialog {

	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JMenu mnBack;
	private JLabel lblNewLabel;
	private JButton btnLogOut;
	private JTextField tfSelectDate;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable innerTable;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private DefaultTableModel outerTable = new DefaultTableModel();
	private JLabel lblNewLabel_1_2_1_1_3;
	private JComboBox comboBox;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_1_2_1_1_2_1;
	private JTextField tfDate;
	private JLabel lblNewLabel_1_2_1_1_2_1_1;
	private JTextField tfTotalAmount;
	private JLabel lblNewLabel_1_2_1_1_2_1_1_1;
	private JComboBox cbBrand;
	private JLabel lblNewLabel_1_2_1_1_2_1_1_2;
	private JTextField tfBrandForAmount;
	private JLabel lblNewLabel_1_2_1_1_2_1_1_2_1;
	private JTextField tfPurchaseCnt;
	private JLabel lblNewLabel_1_2_1_1_2_1_1_3;
	private JTextField tfTotalCnt;
	private JButton btnProdRegister;
	private JButton btnStock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalePage dialog = new SalePage();
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
	public SalePage() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				tableInit();
				clearColumn();
				searchAction();
				selectCbValue();
			}
		});
		setTitle("매출현황");
		setBounds(100, 100, 448, 560);
		setJMenuBar(getMenuBar());
		getContentPane().setLayout(null);
		getContentPane().add(getTfSelectDate());
		getContentPane().add(getBtnSearch());
		getContentPane().add(getScrollPane());
		getContentPane().add(getLblNewLabel_1_2_1_1_3());
		getContentPane().add(getLblNewLabel_2());
		getContentPane().add(getLblNewLabel_1_2_1_1_2_1());
		getContentPane().add(getTfDate());
		getContentPane().add(getLblNewLabel_1_2_1_1_2_1_1());
		getContentPane().add(getTfTotalAmount());
		getContentPane().add(getLblNewLabel_1_2_1_1_2_1_1_1());
		getContentPane().add(getCbBrand());
		getContentPane().add(getLblNewLabel_1_2_1_1_2_1_1_2());
		getContentPane().add(getTfBrandForAmount());
		getContentPane().add(getLblNewLabel_1_2_1_1_2_1_1_2_1());
		getContentPane().add(getTfPurchaseCnt());
		getContentPane().add(getLblNewLabel_1_2_1_1_2_1_1_3());
		getContentPane().add(getTfTotalCnt());

	}

	private JMenuBar getMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnBack());
			menuBar.add(getLblNewLabel());
			menuBar.add(getBtnLogOut());
			menuBar.add(getBtnProdRegister());
			menuBar.add(getBtnStock());
		}
		return menuBar;
	}

	private JMenu getMnBack() {
		if (mnBack == null) {
			mnBack = new JMenu("");
			mnBack.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					logout();
				}
			});

			ImageIcon icon = new ImageIcon(SalePage.class.getResource("/com/javalec/images/backIcon.png"));
			Image changeToImg = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeToImg);
			mnBack.setIcon(changeIcon);
		}
		return mnBack;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("관리자님 환영합니다!");
			lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		}
		return lblNewLabel;
	}

	private JButton getBtnLogOut() {
		if (btnLogOut == null) {
			btnLogOut = new JButton("로그아웃");
			btnLogOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					logout();
				}
			});
		}
		return btnLogOut;
	}

	private JTextField getTfSelectDate() {
		if (tfSelectDate == null) {
			tfSelectDate = new JTextField();
			tfSelectDate.setBounds(90, 25, 194, 26);
			tfSelectDate.setColumns(10);
		}
		return tfSelectDate;
	}

	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("검색하기");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tableInit();
					clearColumn();
					btnSearchClicked();
				}
			});
			btnSearch.setBounds(303, 25, 117, 29);
		}
		return btnSearch;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(18, 60, 399, 104);
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
					cellClicked();
				}
			});
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerTable.setModel(outerTable);
		}
		return innerTable;
	}
	
	private JLabel getLblNewLabel_1_2_1_1_3() {
		if (lblNewLabel_1_2_1_1_3 == null) {
			lblNewLabel_1_2_1_1_3 = new JLabel();
			lblNewLabel_1_2_1_1_3.setBounds(1, 382, 61, 16);
		}
		return lblNewLabel_1_2_1_1_3;
	}
	
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("날짜입력 : ");
			lblNewLabel_2.setBounds(28, 30, 61, 16);
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_1_2_1_1_2_1() {
		if (lblNewLabel_1_2_1_1_2_1 == null) {
			lblNewLabel_1_2_1_1_2_1 = new JLabel("날짜 : ");
			lblNewLabel_1_2_1_1_2_1.setBounds(33, 180, 61, 16);
		}
		return lblNewLabel_1_2_1_1_2_1;
	}
	private JTextField getTfDate() {
		if (tfDate == null) {
			tfDate = new JTextField();
			tfDate.setEditable(false);
			tfDate.setColumns(10);
			tfDate.setBounds(110, 175, 100, 26);
		}
		return tfDate;
	}
	private JLabel getLblNewLabel_1_2_1_1_2_1_1() {
		if (lblNewLabel_1_2_1_1_2_1_1 == null) {
			lblNewLabel_1_2_1_1_2_1_1 = new JLabel("총 매출액 : ");
			lblNewLabel_1_2_1_1_2_1_1.setBounds(33, 220, 61, 16);
		}
		return lblNewLabel_1_2_1_1_2_1_1;
	}
	private JTextField getTfTotalAmount() {
		if (tfTotalAmount == null) {
			tfTotalAmount = new JTextField();
			tfTotalAmount.setEditable(false);
			tfTotalAmount.setHorizontalAlignment(SwingConstants.TRAILING);
			tfTotalAmount.setColumns(10);
			tfTotalAmount.setBounds(110, 215, 100, 26);
		}
		return tfTotalAmount;
	}
	private JLabel getLblNewLabel_1_2_1_1_2_1_1_1() {
		if (lblNewLabel_1_2_1_1_2_1_1_1 == null) {
			lblNewLabel_1_2_1_1_2_1_1_1 = new JLabel("[브랜드 별 일 매출액]");
			lblNewLabel_1_2_1_1_2_1_1_1.setBounds(33, 340, 200, 16);
		}
		return lblNewLabel_1_2_1_1_2_1_1_1;
	}
	private JComboBox getCbBrand() {
		if (cbBrand == null) {
			//콤보박스 설정
			String[] selections = this.selectCbValue();
			cbBrand = new JComboBox(selections);
			cbBrand.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					selectSaleByBrand();
				}
			});
			
			cbBrand.setSelectedIndex(0);
			cbBrand.setBounds(37, 380, 120, 27);
		}
		return cbBrand;
	}
	
	private JLabel getLblNewLabel_1_2_1_1_2_1_1_2() {
		if (lblNewLabel_1_2_1_1_2_1_1_2 == null) {
			lblNewLabel_1_2_1_1_2_1_1_2 = new JLabel("브랜드 별 매출액 : ");
			lblNewLabel_1_2_1_1_2_1_1_2.setBounds(49, 430, 100, 16);
		}
		return lblNewLabel_1_2_1_1_2_1_1_2;
	}
	private JTextField getTfBrandForAmount() {
		if (tfBrandForAmount == null) {
			tfBrandForAmount = new JTextField();
			tfBrandForAmount.setEditable(false);
			tfBrandForAmount.setHorizontalAlignment(SwingConstants.TRAILING);
			tfBrandForAmount.setColumns(10);
			tfBrandForAmount.setBounds(155, 425, 180, 26);
		}
		return tfBrandForAmount;
	}
	
	private JLabel getLblNewLabel_1_2_1_1_2_1_1_2_1() {
		if (lblNewLabel_1_2_1_1_2_1_1_2_1 == null) {
			lblNewLabel_1_2_1_1_2_1_1_2_1 = new JLabel("브랜드 별 판매수량 : ");
			lblNewLabel_1_2_1_1_2_1_1_2_1.setBounds(49, 460, 120, 16);
		}
		return lblNewLabel_1_2_1_1_2_1_1_2_1;
	}
	private JTextField getTfPurchaseCnt() {
		if (tfPurchaseCnt == null) {
			tfPurchaseCnt = new JTextField();
			tfPurchaseCnt.setEditable(false);
			tfPurchaseCnt.setHorizontalAlignment(SwingConstants.TRAILING);
			tfPurchaseCnt.setColumns(10);
			tfPurchaseCnt.setBounds(155, 455, 180, 26);
		}
		return tfPurchaseCnt;
	}
	
	private JLabel getLblNewLabel_1_2_1_1_2_1_1_3() {
		if (lblNewLabel_1_2_1_1_2_1_1_3 == null) {
			lblNewLabel_1_2_1_1_2_1_1_3 = new JLabel("총 판매수량 : ");
			lblNewLabel_1_2_1_1_2_1_1_3.setBounds(33, 260, 80, 16);
		}
		return lblNewLabel_1_2_1_1_2_1_1_3;
	}
	
	private JTextField getTfTotalCnt() {
		if (tfTotalCnt == null) {
			tfTotalCnt = new JTextField();
			tfTotalCnt.setEditable(false);
			tfTotalCnt.setHorizontalAlignment(SwingConstants.TRAILING);
			tfTotalCnt.setColumns(10);
			tfTotalCnt.setBounds(110, 255, 100, 26);
		}
		return tfTotalCnt;
	}
	
	private JButton getBtnProdRegister() {
		if (btnProdRegister == null) {
			btnProdRegister = new JButton("제품등록");
			btnProdRegister.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					goToAdminOrderPage();
				}
			});
		}
		return btnProdRegister;
	}
	private JButton getBtnStock() {
		if (btnStock == null) {
			btnStock = new JButton("재고현황");
			btnStock.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					goToAdminStockPage();
				}
			});
		}
		return btnStock;
	}
	
	// ---------------function ------------------------

	//로그아웃 메소드
	private void logout() {
		CustomerMain main = new CustomerMain();
		main.main(null);
		this.dispose();
	}
	
	
	// 테이블 초기화 메소드
	private void tableInit() {
		outerTable.addColumn("날짜");
		outerTable.addColumn("총매출액");
		outerTable.addColumn("총판매수량");
		outerTable.setColumnCount(3);

		TableColumn col = innerTable.getColumnModel().getColumn(0);
		col.setPreferredWidth(170);
		
		col = innerTable.getColumnModel().getColumn(1);
		col.setPreferredWidth(150);

		col = innerTable.getColumnModel().getColumn(2);
		col.setPreferredWidth(80);

		int i = outerTable.getRowCount();
		for (int j = 0; j < i; j++) {
			outerTable.removeRow(0);
		}
	}

	//컬럼 초기화 메소드 
	private void clearColumn() {
		tfDate.setText("");
		tfTotalAmount.setText("");
		tfTotalCnt.setText("");
		tfBrandForAmount.setText("");
		tfPurchaseCnt.setText("");
	}
	
	
	//창 활성화되었을 때, innerTable 조회 메소드 
	private void searchAction() {
		SaleDao dao = new SaleDao();
		ArrayList <SaleDto> dtoList = dao.searchAction();
		
		for(int i = 0; i < dtoList.size(); i++) {
			//String tmNum = Integer.toString(dtoList.get(i).getProdnum());
			String tmTCnt = Integer.toString(dtoList.get(i).getTotalCnt());
			
			//가격 포맷 ###,### 설정
			DecimalFormat decFormat = new DecimalFormat("###,###");
			int tmp3 = dtoList.get(i).getTotalSales();
			String tmTSale = decFormat.format(tmp3);
			
			String date = dtoList.get(i).getPdate();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date strToDate = null;
			try {
				strToDate = dateFormat.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String[] qTxt = {dateFormat.format(strToDate),  tmTSale, tmTCnt};
			outerTable.addRow(qTxt);
		}
	}
	
	//innerTable의 cell 클릭했을 때, 아래 field에 값 불러오는 메소드 
	private void cellClicked() {
		
		int i = innerTable.getSelectedRow();
		String date = (String) innerTable.getValueAt(i, 0);
		
		SaleDao dao = new SaleDao(date);
		SaleDto dto = dao.cellClicked();
		
		//날짜 형식 변
		String getDate = dto.getPdate();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date strToDate = null;
		try {
			strToDate = dateFormat.parse(getDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		DecimalFormat decFormat = new DecimalFormat("###,###");
		int tmp3 = dto.getTotalSales();
		String tmTSale = decFormat.format(tmp3);
		tfBrandForAmount.setText(tmTSale);
		
		
		tfTotalAmount.setText(tmTSale);
		tfDate.setText(dateFormat.format(strToDate));
		tfTotalCnt.setText(Integer.toString(dto.getTotalCnt()));
		
		selectSaleByBrand();
	}
	
	
	//checkBox에 넣을 value 가져오기
	private String[] selectCbValue() {
		SaleDao dao = new SaleDao();
		ArrayList <SaleDto> dtoList = dao.selectCbValue();
		String[] qTxt = new String[dtoList.size()];
		for(int i = 0; i < dtoList.size(); i++) {
			qTxt[i] = dtoList.get(i).getBrand();
		}
		return qTxt;
	}
	
	
	//브랜드 별 매출액과 판매수량 조회하기 
	private void selectSaleByBrand() {
		tfBrandForAmount.setText("0");
		tfPurchaseCnt.setText("0");
		int j = innerTable.getSelectedRow();
		String date = (String) innerTable.getValueAt(j, 0);
		String brand = (String) cbBrand.getSelectedItem();
		SaleDao dao = new SaleDao(date, brand);
		
		ArrayList <SaleDto> dtoList = dao.selectSaleByBrand();
		String[] qTxt = new String[dtoList.size()];
		
		for(int i = 0; i < dtoList.size(); i++) {
			qTxt[i] = dtoList.get(i).getBrand();
			
			//가격 포맷 ###,### 설정
			DecimalFormat decFormat = new DecimalFormat("###,###");
			int tmp3 =dtoList.get(i).getTotalSales();
			String tmTSale = decFormat.format(tmp3);
			tfBrandForAmount.setText(tmTSale);
			tfPurchaseCnt.setText(Integer.toString(dtoList.get(i).getTotalCnt()));
		}
	}

	//검색하기 버튼 클릭 했을 때 
	private void btnSearchClicked() {
		String date = tfSelectDate.getText().trim();
		SaleDao dao = new SaleDao();
		ArrayList <SaleDto> dtoList = dao.btnSearchClicked(date);
		
		for(int i = 0; i < dtoList.size(); i++) {
			String tmCnt = Integer.toString(dtoList.get(i).getTotalCnt());
			
			//가격 포맷 ###,### 설정
			DecimalFormat decFormat = new DecimalFormat("###,###");
			int tmp3 =dtoList.get(i).getTotalSales();
			String tmTSale = decFormat.format(tmp3);
			
			String[] qTxt = {dtoList.get(i).getPdate(), tmTSale, tmCnt};
			outerTable.addRow(qTxt);
		}
	}
	
	private void goToAdminOrderPage() {
		this.dispose();
		AdminOrderPage adminOrderPage = new AdminOrderPage();
		adminOrderPage.setVisible(true);
		
	}
	
	private void goToAdminStockPage() {
		this.dispose();
		AdminStockPage adminStockPage = new AdminStockPage();
		adminStockPage.setVisible(true);
	}
}
