package com.javalec.purchaseShin;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.adminOrder.AdminOrderDao;
import com.javalec.adminOrder.AdminOrderDto;
import com.javalec.base.Main;
import com.javalec.customer.CustomerMain;
import com.javalec.customer.Mypage;
import com.javalec.productShin.SearchPage;
import com.javalec.productShin.ProductDAO;
import com.javalec.productShin.ProductDTO;
import com.javalec.util.ShareVar;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class OrderPage extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton btnHome;
	private JLabel lblImage;
	private JButton btnLogout;
	private JScrollPane scrollPane;
	private JTable innerTable;

//	outerTable 생성
	private final DefaultTableModel outerTable = new DefaultTableModel();
	private JButton btnUserInfo;
	private JButton btnCancel;
	private JComboBox cbSelect;
	private JTextField tfSelect;
	private JButton btnSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderPage dialog = new OrderPage();
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
	public OrderPage() {
		getContentPane().setBackground(new Color(29, 84, 141));
		setBackground(new Color(64, 62, 255));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				tableInit();
				searchAction();
			}
		});
		setTitle("구매내역");
		setBounds(100, 100, 770, 519);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnHome());
		getContentPane().add(getLblImage());
		getContentPane().add(getBtnLogout());
		getContentPane().add(getScrollPane());
		getContentPane().add(getBtnUserInfo());
		getContentPane().add(getBtnCancel());
		getContentPane().add(getCbSelect());
		getContentPane().add(getTfSelect());
		getContentPane().add(getBtnSearch());

	}

	private JButton getBtnHome() {
		if (btnHome == null) {
			btnHome = new JButton("");
			
			ImageIcon icon = new ImageIcon(OrderPage.class.getResource("/com/javalec/images/homeIcon.png"));
			Image changeToImg = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeToImg);
			btnHome.setIcon(changeIcon);
			btnHome.setHorizontalAlignment(SwingConstants.CENTER);
			
			btnHome.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnHomeClick();
				}
			});
			btnHome.setBounds(10, 10, 40, 40);
		}
		return btnHome;
	}

	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			
			ImageIcon icon = new ImageIcon(OrderPage.class.getResource("/com/javalec/images/logo.png"));
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

			ImageIcon icon = new ImageIcon(OrderPage.class.getResource("/com/javalec/images/logougIcon.png"));
			Image changeToImg = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeToImg);
			btnLogout.setIcon(changeIcon);
			btnLogout.setHorizontalAlignment(SwingConstants.CENTER);
			
			btnLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnLogoutClicked();
				}
			});
			btnLogout.setBounds(710, 10, 40, 40);
		}
		return btnLogout;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(28, 220, 700, 200);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}

	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable();
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerTable.setModel(outerTable);
		}
		return innerTable;
	}

//---	Function  ---

//	Table 초기화 하기
	private void tableInit() {
		outerTable.addColumn("NO");
		outerTable.addColumn("브랜드");
		outerTable.addColumn("상품명");
		outerTable.addColumn("수량");
		outerTable.addColumn("사이즈");
		outerTable.addColumn("색상");
		outerTable.addColumn("주문금액");
		outerTable.addColumn("구매일자");
		outerTable.setColumnCount(8);

		// Table Column 크기 정하기
		
		int colNo = 0;
		TableColumn col = innerTable.getColumnModel().getColumn(colNo);
		int width = 40;
		col.setPreferredWidth(width);
		
		colNo = 1;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 40;
		col.setPreferredWidth(width);

		colNo = 2;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 100;
		col.setPreferredWidth(width);

		colNo = 3;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 30;
		col.setPreferredWidth(width);

		colNo = 4;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 30;
		col.setPreferredWidth(width);

		colNo = 5;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 100;
		col.setPreferredWidth(width);
		
		colNo = 6;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 100;
		col.setPreferredWidth(width);

		colNo = 7;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 80;
		col.setPreferredWidth(width);

		int i = outerTable.getRowCount();
		for (int j = 0; j < i; j++) {
			outerTable.removeRow(0);
		}
	}

	private JButton getBtnUserInfo() {
		if (btnUserInfo == null) {
			btnUserInfo = new JButton("");
			btnUserInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					goToMyPage();
				}
			});
			ImageIcon icon = new ImageIcon(OrderPage.class.getResource("/com/javalec/images/profileIcon.png"));
			Image changeToImg = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeToImg);
			btnUserInfo.setIcon(changeIcon);
			btnUserInfo.setHorizontalAlignment(SwingConstants.CENTER);
			btnHome.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnHomeClick();
				}
			});
			btnUserInfo.setBounds(660, 10, 40, 40);
		}
		return btnUserInfo;
	}

	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("주문취소");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnCancel();
				}
			});
			btnCancel.setBounds(635, 440, 95, 23);
		}
		return btnCancel;
	}

	private JComboBox getCbSelect() {
		if (cbSelect == null) {
			cbSelect = new JComboBox();
			cbSelect.setModel(new DefaultComboBoxModel(new String[] { "제품명", "구매일자", "브랜드" }));
			cbSelect.setBounds(32, 181, 95, 30);
		}
		return cbSelect;
	}

	private JTextField getTfSelect() {
		if (tfSelect == null) {
			tfSelect = new JTextField();
			tfSelect.setBounds(132, 180, 200, 30);
			tfSelect.setColumns(10);
		}
		return tfSelect;
	}

	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("");
			
			ImageIcon icon = new ImageIcon(OrderPage.class.getResource("/com/javalec/images/searchIcon.png"));
			Image changeToImg = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeToImg);
			btnSearch.setIcon(changeIcon);
			btnSearch.setHorizontalAlignment(SwingConstants.CENTER);
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					searchBtnClicked();
				}
			});
			btnSearch.setBounds(338, 180, 30, 30);
		}
		return btnSearch;
	}

	// ------------- function ------------------

	// 홈 버튼 클릭했을 때
	private void btnHomeClick() {
		this.dispose();
		SearchPage page = new SearchPage();
		page.setVisible(true);
	}

	// 로그아웃
	private void btnLogoutClicked() {
		this.dispose();
		CustomerMain main = new CustomerMain();
		main.main(null);
		
		ShareVar.userid = "";
		ShareVar.password = "";
		ShareVar.phone = "";
		ShareVar.name = "";
	}

	// 검색 버튼 클릭 시, 콤보박스 인덱스
	private void searchBtnClicked() {
		int num = cbSelect.getSelectedIndex();
		switch (num) {
		case 0:
			tableInit();
			selectProductName();
			break;
		case 1:
			tableInit();
			selectPdate();
			break;
		case 2:
			tableInit();
			selectBrand();
			break;
		default:
			break;
		}
	}

	// 창 활성화 시 조회되도록
	private void searchAction() {
		OrderDao dao = new OrderDao();
		ArrayList<OrderDto> dtoList = dao.searchAction();

		for (int i = 0; i < dtoList.size(); i++) {
			// select prodnum, pfile, oname, pcnt, (pcnt*oprice), pdate

			String tmProdNum = Integer.toString(dtoList.get(i).getProdnum());
			String tmPcnt = Integer.toString(dtoList.get(i).getPcnt());
			String tmSize = Integer.toString(dtoList.get(i).getPsize());
			DecimalFormat decFormat = new DecimalFormat("###,###");
			int tmp3 = dtoList.get(i).getOprice();
			String tmPrice = decFormat.format(tmp3);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			String date = dtoList.get(i).getPdate();
			Date strToDate = null;
			try {
				strToDate = dateFormat.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String[] qTxt = {tmProdNum, dtoList.get(i).getObrand(), dtoList.get(i).getOname(), tmPcnt, tmSize,
					dtoList.get(i).getOcolor(),  tmPrice, dateFormat.format(strToDate) };
			outerTable.addRow(qTxt);
		}

	}

	private void selectProductName() {
		String tkSelect = tfSelect.getText();

		OrderDao dao = new OrderDao();
		ArrayList<OrderDto> dtoList = dao.selectProductName(tkSelect);

		for (int i = 0; i < dtoList.size(); i++) {

			String tmProdNum = Integer.toString(dtoList.get(i).getProdnum());
			String tmPcnt = Integer.toString(dtoList.get(i).getPcnt());
			String tmSize = Integer.toString(dtoList.get(i).getPsize());
			DecimalFormat decFormat = new DecimalFormat("###,###");
			int tmp3 = dtoList.get(i).getOprice();
			String tmPrice = decFormat.format(tmp3);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			String date = dtoList.get(i).getPdate();
			Date strToDate = null;
			try {
				strToDate = dateFormat.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String[] qTxt = {tmProdNum, dtoList.get(i).getObrand(), dtoList.get(i).getOname(), tmPcnt, tmSize,
					dtoList.get(i).getOcolor(),  tmPrice, dateFormat.format(strToDate) };
			outerTable.addRow(qTxt);
		}

	}

	private void selectPdate() {
		String tkSelect = tfSelect.getText();

		OrderDao dao = new OrderDao();
		ArrayList<OrderDto> dtoList = dao.selectPdate(tkSelect);

		for (int i = 0; i < dtoList.size(); i++) {

			String tmProdNum = Integer.toString(dtoList.get(i).getProdnum());
			String tmPcnt = Integer.toString(dtoList.get(i).getPcnt());
			String tmSize = Integer.toString(dtoList.get(i).getPsize());
			DecimalFormat decFormat = new DecimalFormat("###,###");
			int tmp3 = dtoList.get(i).getOprice();
			String tmPrice = decFormat.format(tmp3);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			String date = dtoList.get(i).getPdate();
			Date strToDate = null;
			try {
				strToDate = dateFormat.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String[] qTxt = {tmProdNum, dtoList.get(i).getObrand(), dtoList.get(i).getOname(), tmPcnt, tmSize,
					dtoList.get(i).getOcolor(),  tmPrice, dateFormat.format(strToDate) };
			outerTable.addRow(qTxt);
		}
	}

	private void selectBrand() {
		String tkSelect = tfSelect.getText();

		OrderDao dao = new OrderDao();
		ArrayList<OrderDto> dtoList = dao.selectBrand(tkSelect);

		for (int i = 0; i < dtoList.size(); i++) {

			String tmProdNum = Integer.toString(dtoList.get(i).getProdnum());
			String tmPcnt = Integer.toString(dtoList.get(i).getPcnt());
			String tmSize = Integer.toString(dtoList.get(i).getPsize());
			DecimalFormat decFormat = new DecimalFormat("###,###");
			int tmp3 = dtoList.get(i).getOprice();
			String tmPrice = decFormat.format(tmp3);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			String date = dtoList.get(i).getPdate();
			Date strToDate = null;
			try {
				strToDate = dateFormat.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String[] qTxt = {tmProdNum, dtoList.get(i).getObrand(), dtoList.get(i).getOname(), tmPcnt, tmSize,
					dtoList.get(i).getOcolor(),  tmPrice, dateFormat.format(strToDate) };
			outerTable.addRow(qTxt);
		}
	}

	// 구매취소 버튼 눌렀을 시, orderProd에 브랜드와 제품명이 같은 경우의 수량에 ++해서 insert 해줘야함
	private void btnCancel() {
		JFrame jframe = new JFrame();
		jframe.setAlwaysOnTop(true);
		
		//선택한 열의 데이터 변수에 담기
		int i = innerTable.getSelectedRow();				  //열 선택 index 
		
		int purNum = Integer.parseInt((String)innerTable.getValueAt(i, 0));	//해당열의 prodnum 
		String brand = (String)innerTable.getValueAt(i, 1);
		String name = (String)innerTable.getValueAt(i, 2);
		int cnt = Integer.parseInt((String)innerTable.getValueAt(i, 3));
		int size = Integer.parseInt((String)innerTable.getValueAt(i, 4));
		String color = (String)innerTable.getValueAt(i, 5);
		String purDate = (String)innerTable.getValueAt(i, 7); //해당열의 date
		
		//오늘날짜 가져오기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String nowDate = sdf.format(now);
		
		// row 선택 체크
		if (i >= 0) {
			String tkProdNum = (String) innerTable.getValueAt(i, 0);
			
			//구매날짜가 오늘과 같을 때만 주문취소 가능;
			if(purDate.equals(nowDate)) {
				OrderDao dao = new OrderDao(purNum);
				if(dao.deleteAction() == true) {
					JOptionPane.showMessageDialog(jframe, "주문이 취소되었습니다.");
				
					// orderProd에 insert하기, insert된 orderProd의 oseq를 product에 insert 처리
					if(	dao.insertToOrderProduct(brand, name, cnt, size, color)) {
						
						//insert된 orderProd의 oseq 가져오기
						System.out.println("OrderPage[inseredOseq] start");
						int inseredOseq =dao.getInsertedOseq(brand, name, cnt, size, color);
						System.out.println("OrderPage[inseredOseq] inseredOseq : "+inseredOseq);
						System.out.println("OrderPage[inseredOseq] end");
						
						//위의 oseq를 가지고 product에 insert 
						if(dao.insertProductQuery(inseredOseq,brand, name, size)) {
							System.out.println("성공성공");
							System.out.println(brand + "/" + name + "/" + size + "/" + color +"/"+cnt+"추가");
						}
					} else {
						System.out.println("실패....");
					}
					
				}
			} else {
				JOptionPane.showMessageDialog(jframe, "오늘 날짜의 주문된 상품만 취소 가능합니다.", "경고", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(jframe, "주문취소할 상품을 선택하세요.");
		}
	}
	
	private void goToMyPage() {
		this.dispose();
		Mypage page = new Mypage();
		page.setVisible(true);
	}
}
