package com.clgdante.d3paraxp.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import com.clgdante.d3paraxp.database.DataBase;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1726456445275036844L;

	private DataBase db;

	public JTextField txt_lvl1;
	public JTextField txt_lvl2;
	public JTextField txt_lvlresult;
	public JTextField txt_exp1;
	public JTextField txt_exp2;
	public JTextField txt_expresult;

	public MainFrame(DataBase db) {
		this.db = db;
		initComponents();
		pack();
	}

	private void initComponents() {
		JPanel basic = new JPanel();
		basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
		add(basic);

		JPanel pnl_top = new JPanel();

		GridLayout gridLayout = new GridLayout();
		gridLayout.setHgap(20);
		gridLayout.setVgap(10);
		gridLayout.setRows(3);
		gridLayout.setColumns(4);
		pnl_top.setLayout(gridLayout);

		pnl_top.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

		JLabel lbl_lvlmerger = new JLabel("Level Merger");
		pnl_top.add(lbl_lvlmerger);

		JLabel lbl_lvl1 = new JLabel("Enter first level:");
		pnl_top.add(lbl_lvl1);

		JLabel lbl_lvl2 = new JLabel("Enter second level:");
		pnl_top.add(lbl_lvl2);

		JLabel lbl_result = new JLabel("Level after merging:");
		pnl_top.add(lbl_result);

		JLabel lbl_lvl = new JLabel("Level:");
		pnl_top.add(lbl_lvl);

		txt_lvl1 = new JTextField(4);
		PlainDocument doc1 = (PlainDocument) txt_lvl1.getDocument();
		doc1.setDocumentFilter(new DocFilter());
		pnl_top.add(txt_lvl1);

		txt_lvl2 = new JTextField(4);
		PlainDocument doc2 = (PlainDocument) txt_lvl2.getDocument();
		doc2.setDocumentFilter(new DocFilter());
		pnl_top.add(txt_lvl2);

		txt_lvlresult = new JTextField();
		txt_lvlresult.setEditable(false);
		pnl_top.add(txt_lvlresult);

		JLabel lbl_exp = new JLabel("Total Exp:");
		pnl_top.add(lbl_exp);

		txt_exp1 = new JTextField("");
		txt_exp1.setEditable(false);
		pnl_top.add(txt_exp1);

		txt_exp2 = new JTextField("");
		txt_exp2.setEditable(false);
		pnl_top.add(txt_exp2);

		txt_expresult = new JTextField();
		txt_expresult.setEditable(false);
		pnl_top.add(txt_expresult);

		basic.add(pnl_top);

		JPanel pnl_btn = new JPanel();
		pnl_btn.setMaximumSize(new Dimension(2000, 10));
		pnl_btn.setLayout(new BorderLayout());
		pnl_btn.setBorder(new EmptyBorder(0, 0, 10, 40));

		JButton btn_submit = new JButton("Submit");
		btn_submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DecimalFormat formatter = new DecimalFormat("#,###");

				int pLvlOne = Integer.parseInt(txt_lvl1.getText());
				int pLvlTwo = Integer.parseInt(txt_lvl2.getText());

				long totalExpOne = db.lookupTotalExp(pLvlOne);
				String str_totalExpOne = formatter.format(totalExpOne);
				long totalExpTwo = db.lookupTotalExp(pLvlTwo);
				String str_totalExpTwo = formatter.format(totalExpTwo);

				txt_exp1.setText(str_totalExpOne);
				txt_exp2.setText(str_totalExpTwo);

				long totalExpSum = totalExpOne + totalExpTwo;
				String str_totalExpSum = formatter.format(totalExpSum);
				txt_expresult.setText(str_totalExpSum);

				int pLvlSum = db.lookupPLvl(totalExpSum);
				String str_pLvlSum = Integer.toString(pLvlSum);
				txt_lvlresult.setText(str_pLvlSum);

			}
		});
		pnl_btn.add(btn_submit, BorderLayout.EAST);

		basic.add(pnl_btn);

	}
}
