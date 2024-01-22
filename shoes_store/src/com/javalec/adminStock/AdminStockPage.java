package com.javalec.adminStock;

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

import com.javalec.base.Main;
import com.javalec.customer.CustomerMain;
import com.javalec.productShin.ProductDAO;
import com.javalec.productShin.ProductDTO;
import com.javalec.sale.SalePage;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminStockPage extends JDialog {

	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JMenu mnBack;
	private JLabel lblNewLabel;
	private JButton btnLogOut;
	private JRadioButton rbSelect;
	private JComboBox cbSelect;
	private JTextField tfSelect;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable innerTable;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField tfName;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	private JTextField tfSize;
	private JLabel lblNewLabel_1_2;
	private JTextField tfCnt;
	private JLabel lblNewLabel_1_2_1;
	private JTextField tfColor;
	private JLabel lblNewLabel_1_2_1_1;
	private JTextField tfPrice;
	private JLabel lblNewLabel_1_2_1_1_1;
	private JLabel lblImage;
	private JLabel lblNewLabel_1_2_1_1_2;
	private JTextField tfFilePath;
	private JButton btnPutImage;

	private DefaultTableModel outerTable = new DefaultTableModel();
	private JButton btnSave;
	private JLabel lblNewLabel_1_2_1_1_3;
	private JLabel lblNewLabel_1_3;
	private JComboBox comboBox;
	private JTextField tfBrand;
	private JTextField tfSeq;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminStockPage dialog = new AdminStockPage();
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
	public AdminStockPage() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				tableInit();
				clearColumn();
				searchAction();
				screenShow();
			}
		});
		setTitle("재고현황");
		setBounds(100, 100, 448, 560);
		
		setJMenuBar(getMenuBar());
		getContentPane().setLayout(null);
		getContentPane().add(getRbSelect());
		getContentPane().add(getCbSelect());
		getContentPane().add(getTfSelect());
		getContentPane().add(getBtnSearch());
		getContentPane().add(getScrollPane());
		getContentPane().add(getTfName());
		getContentPane().add(getLblNewLabel_1());
		getContentPane().add(getLblNewLabel_1_1());
		getContentPane().add(getTfSize());
		getContentPane().add(getLblNewLabel_1_2());
		getContentPane().add(getTfCnt());
		getContentPane().add(getLblNewLabel_1_2_1());
		getContentPane().add(getTfColor());
		getContentPane().add(getLblNewLabel_1_2_1_1());
		getContentPane().add(getTfPrice());
		getContentPane().add(getBtnSave());
		getContentPane().add(getLblNewLabel_1_2_1_1_3());
		getContentPane().add(getLblNewLabel_1_3());
		getContentPane().add(getTfBrand());
		getContentPane().add(getTfSeq());

	}

	private JMenuBar getMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnBack());
			menuBar.add(getLblNewLabel());
			menuBar.add(getBtnLogOut());
		}
		return menuBar;
	}

	private JMenu getMnBack() {
		if (mnBack == null) {
			mnBack = new JMenu("");
			mnBack.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					goToBackMenu();
				}
			});

			ImageIcon icon = new ImageIcon(AdminStockPage.class.getResource("/com/javalec/images/backIcon.png"));
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

	private JRadioButton getRbSelect() {
		if (rbSelect == null) {
			rbSelect = new JRadioButton("검색");
			rbSelect.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					screenShow();
				}
			});
			rbSelect.setSelected(true);
			buttonGroup.add(rbSelect);
			rbSelect.setBounds(18, 16, 84, 23);
		}
		return rbSelect;
	}

	private JComboBox getCbSelect() {
		if (cbSelect == null) {
			cbSelect = new JComboBox();
			cbSelect.setModel(new DefaultComboBoxModel(new String[] {"브랜드", "제품명", "색상"}));
			cbSelect.setBounds(18, 51, 90, 23);
		}
		return cbSelect;
	}

	private JTextField getTfSelect() {
		if (tfSelect == null) {
			tfSelect = new JTextField();
			tfSelect.setBounds(111, 48, 194, 26);
			tfSelect.setColumns(10);
		}
		return tfSelect;
	}

	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("검색하기");
			btnSearch.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tableInit();
					searchBtnClicked();
					tfSelect.setText("");
				}
			});
			btnSearch.setBounds(303, 48, 117, 29);
		}
		return btnSearch;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(18, 86, 399, 104);
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

	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setBounds(147, 248, 250, 26);
			tfName.setColumns(10);
		}
		return tfName;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("제품명 : ");
			lblNewLabel_1.setBounds(33, 248, 61, 16);
		}
		return lblNewLabel_1;
	}

	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("사이즈 : ");
			lblNewLabel_1_1.setBounds(33, 285, 61, 16);
		}
		return lblNewLabel_1_1;
	}

	private JTextField getTfSize() {
		if (tfSize == null) {
			tfSize = new JTextField();
			tfSize.setHorizontalAlignment(SwingConstants.TRAILING);
			tfSize.setColumns(10);
			tfSize.setBounds(147, 280, 80, 26);
		}
		return tfSize;
	}

	private JLabel getLblNewLabel_1_2() {
		if (lblNewLabel_1_2 == null) {
			lblNewLabel_1_2 = new JLabel("수량 : ");
			lblNewLabel_1_2.setBounds(33, 335, 61, 16);
		}
		return lblNewLabel_1_2;
	}

	private JTextField getTfCnt() {
		if (tfCnt == null) {
			tfCnt = new JTextField();
			tfCnt.setHorizontalAlignment(SwingConstants.TRAILING);
			tfCnt.setColumns(10);
			tfCnt.setBounds(147, 328, 80, 26);
		}
		return tfCnt;
	}

	private JLabel getLblNewLabel_1_2_1() {
		if (lblNewLabel_1_2_1 == null) {
			lblNewLabel_1_2_1 = new JLabel("컬러 : ");
			lblNewLabel_1_2_1.setBounds(33, 381, 61, 16);
		}
		return lblNewLabel_1_2_1;
	}

	private JTextField getTfColor() {
		if (tfColor == null) {
			tfColor = new JTextField();
			tfColor.setHorizontalAlignment(SwingConstants.CENTER);
			tfColor.setColumns(10);
			tfColor.setBounds(147, 376, 80, 26);
		}
		return tfColor;
	}

	private JLabel getLblNewLabel_1_2_1_1() {
		if (lblNewLabel_1_2_1_1 == null) {
			lblNewLabel_1_2_1_1 = new JLabel("금액 : ");
			lblNewLabel_1_2_1_1.setBounds(33, 423, 61, 16);
		}
		return lblNewLabel_1_2_1_1;
	}

	private JTextField getTfPrice() {
		if (tfPrice == null) {
			tfPrice = new JTextField();
			tfPrice.setHorizontalAlignment(SwingConstants.TRAILING);
			tfPrice.setColumns(10);
			tfPrice.setBounds(147, 418, 80, 26);
		}
		return tfPrice;
	}

	private JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("저장하기");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPart();
				}
			});
			btnSave.setBounds(314, 418, 117, 29);
		}
		return btnSave;
	}
	
	private JLabel getLblNewLabel_1_2_1_1_3() {
		if (lblNewLabel_1_2_1_1_3 == null) {
			lblNewLabel_1_2_1_1_3 = new JLabel();
			lblNewLabel_1_2_1_1_3.setBounds(1, 382, 61, 16);
		}
		return lblNewLabel_1_2_1_1_3;
	}
	
	private JLabel getLblNewLabel_1_3() {
		if (lblNewLabel_1_3 == null) {
			lblNewLabel_1_3 = new JLabel("브랜드 : ");
			lblNewLabel_1_3.setBounds(33, 216, 61, 16);
		}
		return lblNewLabel_1_3;
	}
	
	private JTextField getTfSeq() {
		if (tfSeq == null) {
			tfSeq = new JTextField();
			tfSeq.setVisible(false);
			tfSeq.setHorizontalAlignment(SwingConstants.TRAILING);
			tfSeq.setColumns(10);
			tfSeq.setBounds(254, 214, 80, 26);
		}
		return tfSeq;
	}
	
	private JTextField getTfBrand() {
		if (tfBrand == null) {
			tfBrand = new JTextField();
			tfBrand.setHorizontalAlignment(SwingConstants.TRAILING);
			tfBrand.setColumns(10);
			tfBrand.setBounds(147, 214, 80, 26);
		}
		return tfBrand;
	}
	
	// ---------------function ------------------------

	//로그아웃 메소드
	private void logout() {
		CustomerMain main = new CustomerMain();
		main.main(null);
		this.dispose();
	}
	
	//뒤로가기 메소드 
	private void goToBackMenu() {
		this.dispose();
		SalePage page = new SalePage();
		page.setVisible(true);
	}
	
	// 테이블 초기화 메소드
	private void tableInit() {
		outerTable.addColumn("seq");
		outerTable.addColumn("브랜드명");
		outerTable.addColumn("제품명");
		outerTable.addColumn("가격");
		outerTable.addColumn("수량");
		outerTable.addColumn("사이즈");
		outerTable.addColumn("색상");
		outerTable.setColumnCount(7);

		TableColumn col = innerTable.getColumnModel().getColumn(0);
		col.setPreferredWidth(50);
		
		col = innerTable.getColumnModel().getColumn(1);
		col.setPreferredWidth(100);

		col = innerTable.getColumnModel().getColumn(2);
		col.setPreferredWidth(150);

		col = innerTable.getColumnModel().getColumn(3);
		col.setPreferredWidth(150);
		
		col = innerTable.getColumnModel().getColumn(4);
		col.setPreferredWidth(80);

		int i = outerTable.getRowCount();
		for (int j = 0; j < i; j++) {
			outerTable.removeRow(0);
		}
		
	}

	private void clearColumn() {
		tfBrand.setText("");
		tfName.setText("");
		tfSize.setText("");
		tfCnt.setText("");
		tfColor.setText("");
		tfPrice.setText("");
		//lblImage.setIcon(new ImageIcon());
	}

	private void screenShow() {
		// 검색 라디오버튼 클릭
		if (rbSelect.isSelected() == true) {
			tfBrand.setEditable(false);
			tfName.setEditable(false);
			tfSize.setEditable(false);
			tfCnt.setEditable(false);
			tfColor.setEditable(false);
			tfPrice.setEditable(false);
			btnSave.setVisible(false);
		}
	}
	
	private void actionPart() {
		//검색
		if(rbSelect.isSelected() == true) {
			screenShow();
		}
	}

	//field 값 체크 메소드 
	private int fieldCheck() {
		int i = 0;
		if(tfName.getText().trim().length() == 0) {
			i++;
			tfName.requestFocus();
		}
		
		if(tfSize.getText().trim().length() == 0) {
			i++;
			tfSize.requestFocus();
		}
		
		if(tfCnt.getText().trim().length() == 0) {
			i++;
			tfCnt.requestFocus();
		}
		
		if(tfColor.getText().trim().length() == 0) {
			i++;
			tfColor.requestFocus();
		}
		
		if(tfPrice.getText().trim().length() == 0) {
			i++;
			tfPrice.requestFocus();
		}
		return i;
	}
	
	//이미지 넣기 버튼 클릭 시 
	private void clickedBtnInputImage() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "png", "jpeg","bmp");
		chooser.setFileFilter(filter);
		
		int ret = chooser.showOpenDialog(null);
		if(ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하세요.");
			return;
		} 
		
		String filePath = chooser.getSelectedFile().getPath();
		System.out.println("filePath : "+filePath);
		
		tfFilePath.setText(filePath);

		ImageIcon icon = new ImageIcon(filePath);
		Image changeToImg = icon.getImage().getScaledInstance(170, 100, Image.SCALE_SMOOTH);
		
		lblImage.setIcon(new ImageIcon(changeToImg));
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		
		
	}
	
	
	//************************** query ******************************

	// 창 활성화 시 조회되도록
	private void searchAction() {
		AdminStockDao dao = new AdminStockDao();
		ArrayList<AdminStockDto> dtoList = dao.searchAction();

		int listCnt = dtoList.size();
		// select oseq, obrand, oname, oprice, ocnt, osize, ocolor from orderProd ";
		for (int i = 0; i < listCnt; i++) {
			String tmSeq = Integer.toString(dtoList.get(i).getOseq());
			String tmCnt = Integer.toString(dtoList.get(i).getOcnt());
			String tmSize = Integer.toString(dtoList.get(i).getOsize());
			//가격 포맷 ###,### 설정
			DecimalFormat decFormat = new DecimalFormat("###,###");
			int tmp3 = dtoList.get(i).getOprice();
			String tmPrice = decFormat.format(tmp3);

			String[] qTxt = {tmSeq, dtoList.get(i).getObrand(), dtoList.get(i).getOname(), tmPrice,
							tmCnt, tmSize, dtoList.get(i).getOcolor()};
			outerTable.addRow(qTxt);
		}
	}
	
	//테이블 안 데이터 클릭했을 때
	private void cellClicked() {
		int i = innerTable.getSelectedRow();
		String seq = (String) innerTable.getValueAt(i, 0);
		int sequence = Integer.parseInt(seq);
		
		AdminStockDao dao = new AdminStockDao(sequence);
		AdminStockDto dto = dao.cellClicked();
		tfSeq.setText(Integer.toString(dto.getOseq()));
		tfBrand.setText(dto.getObrand());
		tfName.setText(dto.getOname());
		tfSize.setText(Integer.toString(dto.getOsize()));
		tfCnt.setText(Integer.toString(dto.getOcnt()));
		tfColor.setText(dto.getOcolor());
		tfPrice.setText(Integer.toString(dto.getOprice()));
	}
	

//	//입력 메소드 
//	private void insertAction() {
//		
//		String brand = tfBrand.getText().trim();
//		String name = tfName.getText().trim();
//		int size = Integer.parseInt(tfSize.getText().trim());
//		int cnt = Integer.parseInt(tfCnt.getText().trim());
//		String color = tfColor.getText().trim();
//		int price = Integer.parseInt(tfPrice.getText().trim());
//		
//		
//		AdminStockDao dao = new AdminStockDao(brand, name, price, cnt, size, color);
//		if(dao.insertAction() == true) {
//			JOptionPane.showMessageDialog(null, "["+tfBrand+"] "+tfName.getText()+" 제품" + tfCnt.getText() +"개 등록되었습니다.");
//		} else {
//			JOptionPane.showMessageDialog(null, "입력 중 문제 발생했습니다.");
//		}
//	}
//	
//	
//	//수정 메소드
//	private void updateAction() {
//		int seqno = Integer.parseInt(tfSeq.getText());
//		
//		String brand = tfBrand.getText().trim();
//		String name = tfName.getText().trim();
//		int size = Integer.parseInt(tfSize.getText().trim());
//		int cnt = Integer.parseInt(tfCnt.getText().trim());
//		String color = tfColor.getText().trim();
//		int price = Integer.parseInt(tfPrice.getText().trim());
//
//		AdminStockDao dao = new AdminStockDao(seqno, brand, name, price, size, cnt, color);
//		if(dao.updateAction() == true) {
//			JOptionPane.showMessageDialog(null, "["+tfBrand+"] "+tfName.getText()+" 제품" + tfCnt.getText() +"개로 수정되었습니다.");
//		} else {
//			JOptionPane.showMessageDialog(null, "입력 중 문제 발생했습니다.");
//		}
//	}
//
//	//삭제 메소드
//	private void deleteAction() {
//		int seqno = Integer.parseInt(tfSeq.getText());
//		AdminStockDao dao = new AdminStockDao(seqno);
//		if(dao.deleteAction() == true) {
//			JOptionPane.showMessageDialog(null, "해당 제품이 삭제되었습니다.");
//		} else {
//			JOptionPane.showMessageDialog(null, "입력 중 문제 발생했습니다.");
//		}
//	}
	
	//검색
	private void searchBtnClicked() {
		AdminStockDao dao = null;
		String inputStr = tfSelect.getText();
		ArrayList<AdminStockDto> dtoList = new ArrayList<>();
		int index = cbSelect.getSelectedIndex();
		
		switch(index) {
		case 0:
			dao = new AdminStockDao();
			dtoList = dao.searchConditionToBrand(inputStr);

			for (int i = 0; i < dtoList.size(); i++) {
				String tmSeq = Integer.toString(dtoList.get(i).getOseq());
				String tmCnt = Integer.toString(dtoList.get(i).getOcnt());
				String tmSize = Integer.toString(dtoList.get(i).getOsize());
				//가격 포맷 ###,### 설정
				DecimalFormat decFormat = new DecimalFormat("###,###");
				int tmp3 = dtoList.get(i).getOprice();
				String tmPrice = decFormat.format(tmp3);

				String[] qTxt = {tmSeq, dtoList.get(i).getObrand(), dtoList.get(i).getOname(), tmPrice,
								tmCnt, tmSize, dtoList.get(i).getOcolor()};
				outerTable.addRow(qTxt);
			}
			break;
		case 1:
			dao = new AdminStockDao();
			dtoList = dao.searchConditionToName(inputStr);

			for (int i = 0; i < dtoList.size(); i++) {
				String tmSeq = Integer.toString(dtoList.get(i).getOseq());
				String tmCnt = Integer.toString(dtoList.get(i).getOcnt());
				String tmSize = Integer.toString(dtoList.get(i).getOsize());
				//가격 포맷 ###,### 설정
				DecimalFormat decFormat = new DecimalFormat("###,###");
				int tmp3 = dtoList.get(i).getOprice();
				String tmPrice = decFormat.format(tmp3);

				String[] qTxt = {tmSeq, dtoList.get(i).getObrand(), dtoList.get(i).getOname(), tmPrice,
								tmCnt, tmSize, dtoList.get(i).getOcolor()};
				outerTable.addRow(qTxt);
			}
			break;
		case 2:
			dao = new AdminStockDao();
			dtoList = dao.searchConditionToColor(inputStr);

			for (int i = 0; i < dtoList.size(); i++) {
				String tmSeq = Integer.toString(dtoList.get(i).getOseq());
				String tmCnt = Integer.toString(dtoList.get(i).getOcnt());
				String tmSize = Integer.toString(dtoList.get(i).getOsize());
				//가격 포맷 ###,### 설정
				DecimalFormat decFormat = new DecimalFormat("###,###");
				int tmp3 = dtoList.get(i).getOprice();
				String tmPrice = decFormat.format(tmp3);

				String[] qTxt = {tmSeq, dtoList.get(i).getObrand(), dtoList.get(i).getOname(), tmPrice,
								tmCnt, tmSize, dtoList.get(i).getOcolor()};
				outerTable.addRow(qTxt);
			}
			break;
		}
	}

}
