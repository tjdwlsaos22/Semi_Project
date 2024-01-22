package com.javalec.receipt;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.product.SearchPage;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReceiptPage extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JTable innerTable;
	private final DefaultTableModel outerTable = new DefaultTableModel();
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReceiptPage dialog = new ReceiptPage();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReceiptPage() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				tableInit();
				searchAction();
				
			}
		});
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getScrollPane());
		contentPanel.add(getBtnNewButton());
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 31, 438, 235);
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
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("홈으로");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					SearchPage searchPage = new SearchPage();
					searchPage.setVisible(true);
				}
			});
			btnNewButton.setBounds(6, 0, 117, 29);
		}
		return btnNewButton;
	}
	
	//기능담당
	
			//테이블 초기화
			private void tableInit() {
				outerTable.addColumn("브랜드");
				outerTable.addColumn("상품명");
				outerTable.addColumn("가격");
				outerTable.addColumn("사이즈");
				outerTable.addColumn("수량");
				outerTable.addColumn("색상");
				outerTable.setColumnCount(6);
			
//			Table Column 크기 정하기
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
			width = 100;
			col.setPreferredWidth(width);
			
			colNo = 3;
			col = innerTable.getColumnModel().getColumn(colNo);
			width = 100;
			col.setPreferredWidth(width);
			
			colNo = 4;
			col = innerTable.getColumnModel().getColumn(colNo);
			width = 100;
			col.setPreferredWidth(width);
			
			colNo = 5;
			col = innerTable.getColumnModel().getColumn(colNo);
			width = 100;
			col.setPreferredWidth(width);
			
			innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF);
			
//			Table 내용 지우기
			int i = outerTable.getRowCount();
			for(int j = 0; j<i; j++) {
				outerTable.removeRow(0);
			}
		    }
			
//		    검색
			private void searchAction() {
				ReceiptDao dao = new ReceiptDao();
				ArrayList<ReceiptDto> dtoList = dao.selectList();
				
				int listCount = dtoList.size();
				
				for(int i = 0; i < listCount; i++) {
					String[] qTxt = {dtoList.get(i).getbrand(),
									dtoList.get(i).getName(),
									Integer.toString(dtoList.get(i).getPrice()),
							        Integer.toString(dtoList.get(i).getSize()),
			         	            Integer.toString(dtoList.get(i).getQuantity()),
									dtoList.get(i).getColor()};
					outerTable.addRow(qTxt);
				}
			}
			

}
