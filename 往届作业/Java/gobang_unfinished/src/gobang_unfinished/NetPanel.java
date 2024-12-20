package gobang_unfinished;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NetPanel extends JPanel{
	private NetPanel(){
		setLayout(new FlowLayout());
		add(listenBtn);
		add(ipTF);
		add(connectBtn);
		ipTF.setText("localhost");
		listenBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				NetHelper.getinstance().beginListen();
				listenBtn.setEnabled(false);
				
			}
		});
		connectBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String ip=ipTF.getText();
				NetHelper.getinstance().connect(ip);
				
			}
		});
	};
	private static NetPanel instance=new NetPanel();
	public static NetPanel getinstance() {
		if(instance==null)
			instance=new NetPanel();
		return instance;

	}
	private JButton listenBtn=new JButton("开始监听");
	private JButton connectBtn=new JButton("连接服务器");
	private JTextField ipTF=new JTextField(20);
	
}