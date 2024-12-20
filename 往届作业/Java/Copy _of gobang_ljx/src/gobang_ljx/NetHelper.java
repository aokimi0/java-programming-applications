package gobang_ljx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


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
					sendWidth(Model.WIDTH);
					sendcolor(Controller.getinstance().getotherColor());
					startReadLine();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		
	}
	protected void sendcolor(int otherColor) {
		out.println("Color:"+String.valueOf(otherColor));
		
	}
	protected void startReadLine() {
		new Thread(){
			public void run(){
				while(true){
					try {
						String line;
						line = in.readLine();
						if(line.startsWith("PutChess:")){
							parseChess(line);
						}else if(line.startsWith("Chat:")){
							parseChat(line);
						}else if(line.startsWith("Width:")){
							parseWidth(line);
						}else if(line.startsWith("RemoveChess:")){
							parseRemove(line);
						}else if(line.startsWith("Peace:")){
							parsePeace();
						}else if(line.startsWith("okPeace:")){
							parseokPeace();
						}else if(line.startsWith("Color:")){
							parseColor(line);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}//时刻准备对方发的信息
			}
		}.start();
	}
	
	protected void parseokPeace() {
		Model.getinstance().againChess();
		ChessPanel.getinstance().repaint();
		
	}
	protected void parsePeace() {
		Controller.getinstance().sendpeace();
		
	}
	protected void parseRemove(String line) {
		line=line.substring(12);
		String[] array=line.split(",");
		int row=Integer.parseInt(array[0]);
		int col=Integer.parseInt(array[1]);
		Controller.getinstance().netOtherRemoveChess(row, col);
		
	}
	protected void parseWidth(String line) {
		line=line.substring(6);
		Model.getinstance().setWIDTH(Integer.parseInt(line));
		
	}
	protected void parseChat(String line) {
		line=line.substring(5);
		Controller.getinstance().setChat(line);
		
	}
	
	protected void parseChess(String line) {
		line=line.substring(9);
		String[] array=line.split(",");
		int row=Integer.parseInt(array[0]);
		int col=Integer.parseInt(array[1]);
		Controller.getinstance().netOtherPutChess(row, col);
	}
	
	public void sendChat(final String message){
		new Thread(){
			public void run(){
				out.println("Chat:"+message);
			}
		}.start();
	}
	
	public void sendChess(final int row,final int col){
		new Thread(){
			public void run(){
				out.println("PutChess:"+row+","+col);
			}
		}.start();
	}
	
	public void sendWidth(final int width){
		out.println("Width:"+String.valueOf(width));
	}
	public void connect(String ip) {
		try {
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
	public void sendremoveChess(final int row, final int col) {
		new Thread(){
			public void run(){
				out.println("RemoveChess:"+row+","+col);
			}
		}.start();
		
	}
	public void sendpeace() {
		new Thread(){
			public void run(){
				out.println("Peace:");
			}
		}.start();
		
	}
	public void okpeace() {
		new Thread(){
			public void run(){
				out.println("okPeace:");
			}
		}.start();
		
	}
	private void parseColor(String line) {
		String Color=line.substring(6);
		Controller.getinstance().setlocalColor(Integer.parseInt(Color));
		Controller.getinstance().setotherColor(-1*Integer.parseInt(Color));
	}
	

	
}
