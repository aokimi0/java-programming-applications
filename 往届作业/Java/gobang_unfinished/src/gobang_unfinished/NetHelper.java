package gobang_unfinished;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

public class NetHelper {
	private static NetHelper instance;
	private NetHelper(){};
	public static NetHelper getinstance(){
		if(instance==null)
			instance=new NetHelper();
		return instance;
	}
	
	public static final int port=8090;
	private Socket s;
	private BufferedReader in;
	private PrintWriter out;
	
	public void beginListen(){
		new Thread(){
			public void run(){
				try {
					ServerSocket ss=new ServerSocket(port);
					s=ss.accept();
					//controller connect success
					in=new BufferedReader(new InputStreamReader(s.getInputStream()));
					out=new PrintWriter(s.getOutputStream(),true);
					startReadLine();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		
	}
	protected void startReadLine() {
		new Thread(){
			public void run(){
				while(true){
					try {
						String line;
						line = in.readLine();
						if(line.startsWith("PutChess")){
							parseChess(line);
						}else if(line.startsWith("Chat")){
							parseChat(line);
						}else if(line.startsWith("WIDTH")){
							parsewidth(line);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}//时刻准备对方发的信息
			}
		}.start();
	}
	protected void parsewidth(String line) {
		line=line.substring(6);
		JFrame frame=new JFrame();
		frame.getContentPane().add(checkerboard.getinstance());
		frame.setVisible(true);
	}
	protected void parseChat(String line) {
		// TODO Auto-generated method stub
		
	}
	
	protected void parseChess(String line) {
		line=line.substring(9);
		String [] array=line.split(",");
		int row=Integer.parseInt(array[0]);
		int col=Integer.parseInt(array[1]);
		chessController.getinstance().netOtherPutChess(row, col);
	}
	
	public void sendChess(final int row,final int col){
		new Thread(){
			public void run(){
				out.println("PutChess"+row+","+col);
			}
		}.start();
	}
	
	public void connect(String ip) {
		try {
			int port=Integer.parseInt(choose_ipport.getinstance().area2.getText());
			s=new Socket(ip,port);
			//controller.connect.success
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			out=new PrintWriter(s.getOutputStream(),true);
			startReadLine();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendWIDTH(int width){
		out.println("WIDTH"+width);
	}
}
