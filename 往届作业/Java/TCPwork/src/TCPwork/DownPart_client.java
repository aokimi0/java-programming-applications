package TCPwork;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DownPart_client {
	private static DownPart_client instance;
	public static DownPart_client getinstance(){
		if(instance==null)
			instance=new DownPart_client();
		return instance;
	}
	
	JPanel panel=new JPanel();
	JPanel panelup=new JPanel();
	JPanel paneldown=new JPanel();
	JButton giveto =new JButton("发送");
	JTextArea content=new JTextArea();
	JScrollPane scrollpane=new JScrollPane(content);
	
	public JPanel getpanelup(){
		
		giveto.setFont(new Font("黑体",Font.BOLD,25));
		giveto.setPreferredSize(new Dimension(150,55));
		giveto.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER,50,50));
		panel.add(giveto);
		
		return panelup;
	}
	
	public JPanel getpaneldown(){
		content.setFont(new Font("黑体",Font.BOLD,25));
		scrollpane.setPreferredSize(new Dimension(850,350));

		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
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
