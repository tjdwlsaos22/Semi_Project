package com.javalec.cart;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.product.ProductDAO;
import com.javalec.product.ProductDTO;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.WindowEvent;

public class Cart extends JDialog {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JTable innertable;
	
//	outerTable 생성
	private final DefaultTableModel outerTable = new DefaultTableModel();
	
	
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

	/**
	 * Create the dialog.
	 */
	public Cart() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				tableInit();
			}
		});
		
		setTitle("장바구니");
		setBounds(100, 100, 730, 519);
		getContentPane().setLayout(null);
		getContentPane().add(getScrollPane());
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getTextField());
		getContentPane().add(getBtnNewButton());
		getContentPane().add(getBtnNewButton_1());

	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(36, 27, 617, 238);
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
			lblNewLabel = new JLabel("결제금액");
			lblNewLabel.setBounds(36, 360, 50, 15);
		}
		return lblNewLabel;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(111, 357, 96, 21);
			textField.setColumns(10);
		}
		return textField;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("주문하기");
			btnNewButton.setBounds(477, 423, 91, 23);
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("삭제하기");
			btnNewButton_1.setBounds(591, 423, 91, 23);
		}
		return btnNewButton_1;
	}
	
	
//---	Function  ---
	
//	Table 초기화 하기
	private void tableInit() {
		outerTable.addColumn("NO");
		outerTable.addColumn("이름");
		outerTable.addColumn("브랜드");
		outerTable.addColumn("사이즈");
		outerTable.addColumn("가격");
		outerTable.addColumn("수량");
		outerTable.setColumnCount(6);
		
//		Table Column 크기 정하기
		int colNo = 0;
		TableColumn col = innertable.getColumnModel().getColumn(colNo);
		int width = 50;
		col.setPreferredWidth(width);
	
		colNo = 1;
		col = innertable.getColumnModel().getColumn(colNo);
		width = 200;
		col.setPreferredWidth(width);
		
		colNo = 2;
		col = innertable.getColumnModel().getColumn(colNo);
		width = 200;
		col.setPreferredWidth(width);
		
		colNo = 4;
		col = innertable.getColumnModel().getColumn(colNo);
		width = 200;
		col.setPreferredWidth(width);
		
		colNo = 5;
		col = innertable.getColumnModel().getColumn(colNo);
		width = 200;
		col.setPreferredWidth(width);
		
		colNo = 6;
		col = innertable.getColumnModel().getColumn(colNo);
		width = 200;
		col.setPreferredWidth(width);
		
		innertable.setAutoResizeMode(innertable.AUTO_RESIZE_OFF);
		
		// Table 내용 지우기
				int i = outerTable.getRowCount();
				for(int j = 0; j<i; j++) {
					outerTable.removeRow(0);
				}
	}

	}
	
	
	

