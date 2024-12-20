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
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Server {
	private static Server instance;
	public static Server getinstance(){
		if(instance==null)
			instance=new Server();
		return instance;
	}
	
	static DownPart dd=DownPart.getinstance();
	static UpPart uu=UpPart.getinstance();
	
	static JPanel panelserver=new JPanel();
	static JFrame frameserver=new JFrame("SERVER");
	static PrintWriter out;
	static Socket s;
	static ServerSocket ss;
	
	static int count=0;
	
	static Socket ipport[]=new Socket[10];
	
	public static void main(String[] args) throws Exception{

		panelserver.setLayout(new GridLayout(2,1));
		panelserver.add(UpPart.getinstance().getpanel());
		panelserver.add(DownPart.getinstance().getpanel());
		panelserver.setPreferredSize(new Dimension(900,1200));
		
		frameserver.getContentPane().setLayout(new BorderLayout());
		frameserver.getContentPane().add(panelserver,BorderLayout.CENTER);
		
		frameserver.setBounds(180, 150, 1000, 1300);
		frameserver.setVisible(true);
		frameserver.setDefaultCloseOperation(frameserver.EXIT_ON_CLOSE);
		
		ss = new ServerSocket(Integer.parseInt(uu.servernum.getText()));
		dd.content.setText("开始监听，端口为："+uu.servernum.getText());
		
		dd.giveto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name=(String) dd.choose.getSelectedItem();
				if(name=="Client1"){
					String message=uu.content.getText();
					PrintWriter out = null;
					try {
						out = new PrintWriter(ipport[1].getOutputStream(),true);
						BufferedReader keyin=new BufferedReader(new InputStreamReader(new ByteArrayInputStream(message.getBytes())));
						String line;
						try {
							while((line=keyin.readLine())!=null){
								out.println(line);
								dd.content.setText(dd.content.getText()+'\n'+"已向客户端1发送消息："+message);
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else if(name=="Client2"){
					String message=uu.content.getText();
					PrintWriter out = null;
					try {
						out = new PrintWriter(ipport[2].getOutputStream(),true);
						BufferedReader keyin=new BufferedReader(new InputStreamReader(new ByteArrayInputStream(message.getBytes())));
						String line;
						try {
							while((line=keyin.readLine())!=null){
								out.println(line);
								dd.content.setText(dd.content.getText()+'\n'+"已向客户端2发送消息："+message);
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				else if(name=="Client3"){
					String message=uu.content.getText();
					PrintWriter out = null;
					try {
						out = new PrintWriter(ipport[3].getOutputStream(),true);
						BufferedReader keyin=new BufferedReader(new InputStreamReader(new ByteArrayInputStream(message.getBytes())));
						String line;
						try {
							while((line=keyin.readLine())!=null){
								out.println(line);
								dd.content.setText(dd.content.getText()+'\n'+"已向客户端3发送消息："+message);
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
		
		new Thread(){
			public void run(){
				try {
					while(true){
						dd.content.setText(dd.content.getText()+"\n"+"等待客户端连接。。。");
						s=ss.accept();
						count++;
						ipport[count]=s;
						dd.content.setText(dd.content.getText()+"\n"+"接受客户端Client"+count+"的请求");
						final BufferedReader in =new BufferedReader(new InputStreamReader(ipport[count].getInputStream()));
						new Thread(){
							int ok=count;
							public void run() {
								String line;
								try {
									while((line=in.readLine())!=null){
										dd.content.setText(dd.content.getText()+'\n'+"来自客户端Client"+ok+"的信息："+line);
									}
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							};
						}.start();
						
					}
				} catch (NumberFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			};
		
		}.start();
		

	}
}
