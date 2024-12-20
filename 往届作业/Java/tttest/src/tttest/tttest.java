package tttest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class tttest {

	public static void main(String[] args) throws IOException {
		URL web=new URL("http://www.baidu.com");
		BufferedReader in=new BufferedReader(new InputStreamReader(web.openStream()));
		String inputline;
		while((inputline=in.readLine())!=null)
			System.out.println(inputline);
		in.close();
	}
}
