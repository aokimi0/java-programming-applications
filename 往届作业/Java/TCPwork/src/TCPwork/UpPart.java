package TCPwork;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpPart {
	private static UpPart instance;
	public static UpPart getinstance(){
		if(instance==null)
			instance=new UpPart();
		return instance;
	}
	
	JPanel panel=new JPanel();
	JPanel panelup=new JPanel();
	JPanel paneldown=new JPanel();
	JLabel text=new JLabel("请输入服务器端口号：",JLabel.CENTER);
	JTextField servernum=new JTextField("8090");
	JButton stop =new JButton("STOP");
	JTextField content=new JTextField();
	
	ServerSocket ss;
	Socket s;
	
	public JPanel getpanelup(){
		text.setFont(new Font("黑体",Font.BOLD,25));
		servernum.setFont(new Font("黑体",Font.BOLD,25));
		stop.setFont(new Font("黑体",Font.BOLD,25));
		content.setFont(new Font("黑体",Font.BOLD,25));
		
		text.setPreferredSize(new Dimension(300,100));
		servernum.setPreferredSize(new Dimension(120,50));
		stop.setPreferredSize(new Dimension(80,50));
		
		stop.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER,50,50));
		panel.add(text);
		panel.add(servernum);
		panel.add(stop);
		
		return panelup;
	}
	
	public JPanel getpaneldown(){
		content.setPreferredSize(new Dimension(850,350));
		paneldown.setBorder(BorderFactory.createTitledBorder("需要发送的内容"));
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
