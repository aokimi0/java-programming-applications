package TCPwork;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpPart_client {
	private static UpPart_client instance;
	public static UpPart_client getinstance(){
		if(instance==null)
			instance=new UpPart_client();
		return instance;
	}
	
	JPanel panel=new JPanel();
	JPanel panelup=new JPanel();
	JPanel paneldown=new JPanel();
	JLabel text=new JLabel("请输入服务器地址：",JLabel.CENTER);
	JTextField servernum=new JTextField("10.136.120.191");
	JTextField portcontent=new JTextField("8090");
	JLabel port=new JLabel("端口：");
	JTextField content=new JTextField();
	
	
	public JPanel getpanelup(){
		text.setFont(new Font("黑体",Font.BOLD,25));
		servernum.setFont(new Font("黑体",Font.BOLD,25));
		port.setFont(new Font("黑体",Font.BOLD,25));
		portcontent.setFont(new Font("黑体",Font.BOLD,25));
		content.setFont(new Font("黑体",Font.BOLD,25));
		
		text.setPreferredSize(new Dimension(250,100));
		servernum.setPreferredSize(new Dimension(250,50));
		port.setPreferredSize(new Dimension(80,50));
		portcontent.setPreferredSize(new Dimension(120,50));
		
		portcontent.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER,50,50));
		panel.add(text);
		panel.add(servernum);
		panel.add(port);
		panel.add(portcontent);
		
		return panelup;
	}
	
	public JPanel getpaneldown(){
		content.setPreferredSize(new Dimension(850,350));
		paneldown.setBorder(BorderFactory.createTitledBorder("需要发送的信息"));
		paneldown.add(content);
		
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
