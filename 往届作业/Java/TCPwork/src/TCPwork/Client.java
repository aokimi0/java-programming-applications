package TCPwork;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Client {
	private static Client instance;
	public static Client getinstance(){
		if(instance==null)
			instance=new Client();
		return instance;
	}
	
	static JFrame frameclient=new JFrame("CLIENT");
	static JPanel panelclient=new JPanel();
	static PrintWriter out;
	static Socket s;
	static BufferedReader in;
	
	static DownPart_client dc=DownPart_client.getinstance();
	static UpPart_client uc=UpPart_client.getinstance();
	
	public static void main(String[] args) throws Exception{

		panelclient.setLayout(new GridLayout(2,1));
		panelclient.add(UpPart_client.getinstance().getpanel());
		panelclient.add(DownPart_client.getinstance().getpanel());
		panelclient.setPreferredSize(new Dimension(900,1200));
		
		frameclient.getContentPane().setLayout(new BorderLayout());
		frameclient.getContentPane().add(panelclient,BorderLayout.CENTER);
		
		frameclient.setBounds(1380, 150, 1000, 1300);
		frameclient.setVisible(true);
		frameclient.setDefaultCloseOperation(frameclient.EXIT_ON_CLOSE);
		
		String server=uc.servernum.getText();
		String port=uc.portcontent.getText();
		int portnum=Integer.parseInt(port);
		s=new Socket(server, portnum);
		dc.content.setText(dc.content.getText()+'\n'+"连接服务器"+server+":"+port+"成功！");
		dc.giveto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0){
				
				String content=uc.content.getText();
				
				try {
					out=new PrintWriter(s.getOutputStream(),true);
					BufferedReader keyin=new BufferedReader(new InputStreamReader(new ByteArrayInputStream(content.getBytes())));
					String line;
					while((line=keyin.readLine())!=null){
						out.println(line);
						dc.content.setText(dc.content.getText()+'\n'+"已向服务器端发送消息："+content);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					in =new BufferedReader(new InputStreamReader(s.getInputStream()));
					
					new Thread(){
						public void run(){
							String str;
							try {
								while((str=in.readLine())!=null){
									dc.content.setText(dc.content.getText()+'\n'+"来自服务器发送的信息："+str);
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}.start();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
			}
		});

	}
}
