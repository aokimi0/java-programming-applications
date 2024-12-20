package sql;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class commodity extends JFrame{
	JPanel panel1=new JPanel(new BorderLayout());
	JPanel panel2=new JPanel();
	JPanel panel3=new JPanel();
	JPanel panel4=new JPanel();
	JPanel panel5=new JPanel();
	JPanel panel6=new JPanel();
	JPanel panel7=new JPanel();
	JLabel label1=new JLabel("商品信息如下：");
	
	JButton[] buttonall=new JButton[3];
	JLabel[] labelall=new JLabel[7];
	JTextField[] tfall=new JTextField[7];
	
	JTable ta=new JTable();
	
	String[] columnNames = {"商品名称：", "商品编号：", "供应商编号：", "供应商名称：", "商品库存：","售卖单价：","进货单价："};
	Object[][] rowData = new Object[50][7];
	
	public commodity()throws Exception{
		setBounds(150, 150, 1000, 1200);
		setLayout(new BorderLayout());
		init();

	}

	private void init() throws Exception{
		this.setLayout(new BorderLayout());
		panel7.setLayout(new GridLayout(1,2));
		panel7.add(getleftpanel());
		this.getContentPane().add(panel7,BorderLayout.CENTER);
		this.getContentPane().add(new JPanel(),BorderLayout.EAST);
		this.getContentPane().add(new JPanel(),BorderLayout.NORTH);
		this.getContentPane().add(new JPanel(),BorderLayout.SOUTH);
		this.getContentPane().add(new JPanel(),BorderLayout.WEST);
	}

	private void getTableText(Object[][] rowData) throws Exception{
		Helper.getinstance().settext(rowData,2,"");
	}
	private JPanel gettablepanel() throws Exception{
		getTableText(rowData);
		ta=new JTable(rowData,columnNames);
		ta.setFont(new Font("黑体", Font.BOLD, 15));
		ta.setIntercellSpacing(new Dimension(0,1));
		ta.getColumnModel().getColumn(0).setPreferredWidth(70);
		ta.setRowHeight(30);
		ta.setEnabled(false);
		
		JScrollPane sp = new JScrollPane();
		sp.setViewportView(ta);
		sp.setOpaque(false);
		sp.getViewport().setOpaque(false);
		sp.setPreferredSize(new Dimension(800,700));
		
		panel1.add(ta.getTableHeader(),BorderLayout.NORTH);
		panel1.add(sp,BorderLayout.CENTER);
		return panel1;
	}
	private JPanel getleftpanel()throws Exception{
		panel2.setLayout(new BorderLayout());
		panel2.add(label1,BorderLayout.NORTH);
		panel2.add(gettablepanel(),BorderLayout.CENTER);
		label1.setPreferredSize(new Dimension(100,100));
		label1.setFont(new Font("黑体", Font.BOLD, 27));
		
		return panel2;
	}
}
