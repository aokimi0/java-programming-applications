package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws Exception {
		Socket s=new Socket("192.168.0.103",8090);
		System.out.println("hello server");
		BufferedReader keyin=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out=new PrintWriter(s.getOutputStream(),true);
		final BufferedReader in =new BufferedReader(new InputStreamReader(s.getInputStream()));
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
		while((line=keyin.readLine())!=null){
			out.println(line);
		}
		s.close();
	}

}
