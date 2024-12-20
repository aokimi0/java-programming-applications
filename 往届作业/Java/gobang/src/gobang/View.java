package gobang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class View {
	private static View instance;
	public static View getinstance(){
		if(instance==null){
			instance=new View();
		}
		return instance;
	}
	public void repaint(){
		Model m=Model.getinstance();
		for(int row=0;row<Model.WIDTH;row++){
			for(int col=0;col<Model.WIDTH;col++){
				int color=m.getchess(row,col);
				if(color==Model.SPACE){
					System.out.print(" ©ï ");
				}else if(color==Model.BLACK){
					System.out.print("*");
				}else if(color==Model.WHITE){
					System.out.print("O");
				}
			}
			System.out.println();
		}
	}
	public static void input(){
		System.out.println("please input");
		BufferedReader in =new BufferedReader(new InputStreamReader(System.in));
		try {
			String line=in.readLine();
			int row=Integer.parseInt(line);
			line=in.readLine();
			int col = Integer.parseInt(line);
			Control.getIntance().report(row,col);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
