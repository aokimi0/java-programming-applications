package outfiles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Files {
	static int cataloguenumber=0;
	static int filenumber=0;
	static int filesize=0;
	public static void main(String args[]) throws IOException{
		File filetext=new File("output.txt");
		if(filetext.exists())
			filetext.delete();
		filetext.createNewFile();
		FileWriter fw=new FileWriter("output.txt",true);//继承输出流outputstreamwriter，按字符向流中写入数据
		PrintWriter pw=new PrintWriter(fw);//创建一个文件并向文件中写入数据，可以理解为文件输出
		File file=new File("D:\\users\\wujing\\documents\\WeChat Files\\wxid_zl6a0l9q48ag22\\FileStorage\\General");
		printAllFile(file);
		pw.println("文件夹数为："+cataloguenumber);
		pw.flush();//清空缓冲区，不加他就没有办法显示
		pw.println("文件数为："+filenumber);
		pw.flush();
		pw.println("文件大小为："+filesize+"个字节");
		pw.flush();
	}

	private static void printAllFile(File dir) throws IOException {
		
		File filetext=new File("output.txt");
		if(filetext.exists())
			filetext.delete();//如果文件存在，要执行清空操作，这里运用删除重建的操作
		filetext.createNewFile();//重建
		FileWriter fw=new FileWriter("output.txt",true);
		PrintWriter pw=new PrintWriter(fw);
		
		pw.println(dir);
		pw.flush();
		File[] files =dir.listFiles();
		if(files!=null){

			for(File file:files){
				if(file.isDirectory()){
					cataloguenumber++;
					printAllFile(file);
				}else {
					filesize+=file.length();
					filenumber++;
					pw.println(file);
					pw.flush();
				}
			}
		}
	}
}
