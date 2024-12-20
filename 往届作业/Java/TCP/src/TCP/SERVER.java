package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SERVER {

	public static void main(String[] args) throws Exception {
		ServerSocket ss=new ServerSocket(8090);
		System.out.println("¿ªÆô¼àÌý");
		Socket s=ss.accept();
		System.out.println("¼àÌýµ½client");
		final BufferedReader in =new BufferedReader(new InputStreamReader(s.getInputStream()));
		PrintWriter out=new PrintWriter(s.getOutputStream(),true);
		new Thread(){
			public void run() {
				String line;
				try {
					while((line=in.readLine())!=null){
						System.out.println(line);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		}.start();
		
		String line;
		BufferedReader keyin=new BufferedReader(new InputStreamReader(System.in));
		while((line=keyin.readLine())!=null){
			out.println(line);
		}
	}

}
