package TCPwork;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Scrollable;

public class DownPart {
	private static DownPart instance;
	public static DownPart getinstance(){
		if(instance==null)
			instance=new DownPart();
		return instance;
	}
	
	JPanel panel=new JPanel();
	JPanel panelup=new JPanel();
	JPanel paneldown=new JPanel();
	JTextArea content=new JTextArea();
	JButton giveto =new JButton("发送至");
	String []choice={
			"Client1","Client2","Client3"
			};
	JComboBox<String> choose=new JComboBox<String>();
	JScrollPane scrollpane=new JScrollPane(content);
	
	public JPanel getpanelup(){
		for(int i=0;i<3;i++){
			choose.addItem(choice[i]);
		}
		
		giveto.setFont(new Font("黑体",Font.BOLD,25));
		choose.setFont(new Font("黑体",Font.BOLD,25));
		
		giveto.setPreferredSize(new Dimension(150,55));
		choose.setPreferredSize(new Dimension(150,55));
		
		giveto.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER,50,50));
		panel.add(giveto);
		panel.add(choose);
		
		return panelup;
	}
	
	public JPanel getpaneldown(){
		content.setFont(new Font("黑体",Font.BOLD,25));
		scrollpane.setPreferredSize(new Dimension(850,350));
		
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		paneldown.setBorder(BorderFactory.createTitledBorder("日志"));
		paneldown.add(scrollpane);
		
		return paneldown;
	}
	
	public JPanel getpanel(){
		panel.setPreferredSize(new Dimension(500,700));
		panel.setLayout(new GridLayout(2,1));
		panel.add(getpanelup());
		panel.add(getpaneldown());
		
		return panel;
	}
}
