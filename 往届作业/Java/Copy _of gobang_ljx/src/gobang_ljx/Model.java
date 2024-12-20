package gobang_ljx;

import java.util.LinkedList;


public class Model {
	private static Model instance=new Model();
	public static Model getinstance(){
		return instance;
	}
	private Model(){}
	
	public static final int WHITE=1;
	public static final int BLACK=-1;
	public static final int SPACE=0;
	
	public static int WIDTH=25;

	private static int [][]data=new int [WIDTH][WIDTH];
	
	public static LinkedList<String> chess=new LinkedList<String>();
	
	public void setWIDTH(int width){
		WIDTH=width;
	}
	
	public void againChess(){
		for(int i=0;i<WIDTH;i++){
			for(int j=0;j<WIDTH;j++){
				data[i][j]=SPACE;
			}
		}
		chess.clear();
	}
	
	public boolean removeChess(int row,int col){
		if(chess.size()>=1){
			String []array1=chess.get(chess.size()-1).split(",");
			data[Integer.parseInt(array1[0])][Integer.parseInt(array1[1])]=SPACE;
			chess.removeLast();
			return true;
		}
			
		return false;
	}
	
	public boolean putChess(int row,int col,int color){
		if(row>=0&&row<=WIDTH&&col>=0&&col<=WIDTH&&data[row][col]==SPACE){
			data[row][col]=color;
			chess.add(String.valueOf(row)+","+String.valueOf(col));
			return true;
		}
		return false;
	}

	public int getchess (int row,int col){
		if(row>=0&&row<=WIDTH&&col>=0&&col<=WIDTH){
			return data[row][col];
		}
		return SPACE;
	}
	
	public int whoWin(){
		int blackconnect=0,whiteconnect=0;
		for(int col=0;col<WIDTH-4;col++){
			for(int row=0;row<WIDTH-4;row++){
				if(data[row][col]==BLACK){
					blackconnect++;
					if(blackconnect==5)
						return BLACK;
				}else 
					blackconnect=0;
				
				if(data[row][col]==WHITE){
					whiteconnect++;
					if(whiteconnect==5)
						return WHITE;
				}else 
					whiteconnect=0;
			}
				blackconnect=0;
				whiteconnect=0;
		}
		for(int row=0;row<WIDTH-4;row++){
			for(int col=0;col<WIDTH-4;col++){
				if(data[row][col]==BLACK){
					blackconnect++;
					if(blackconnect==5)
						return BLACK;
				}else 
					blackconnect=0;
				
				if(data[row][col]==WHITE){
					whiteconnect++;
					if(whiteconnect==5)
						return WHITE;
				}else 
					whiteconnect=0;
			}
				blackconnect=0;
				whiteconnect=0;
		}
		for(int row=0;row<WIDTH-4;row++){
			for(int col=0;col<WIDTH-4;col++){
				if(data[row][col]==BLACK){
					blackconnect++;
					int move=1;
					for(;move<5;move++){
						if(data[row+move][col+move]==BLACK){
							blackconnect++;
							if(blackconnect==5)
								return BLACK;
						}else {
							blackconnect=0;
							break;
						}
					}
				}
				
				if(data[row][col]==WHITE){
					whiteconnect++;
					int move=1;
					for(;move<5;move++){
						if(data[row+move][col+move]==WHITE){
							whiteconnect++;
							if(whiteconnect==5)
								return WHITE;
						}else {
							whiteconnect=0;
							break;
						}
					}
				}
				
			}
		}
		
		for(int row=0;row<WIDTH-4;row++){
			for(int col=4;col<WIDTH;col++){
				if(data[row][col]==BLACK){
					blackconnect++;
					int move=1;
					for(;move<5;move++){
						if(data[row+move][col-move]==BLACK){
							blackconnect++;
							if(blackconnect==5)
								return BLACK;
						}else {
							blackconnect=0;
							break;
						}
					}
				}
				
				if(data[row][col]==WHITE){
					whiteconnect++;
					int move=1;
					for(;move<5;move++){
						if(data[row+move][col-move]==WHITE){
							whiteconnect++;
							if(whiteconnect==5)
								return WHITE;
						}else {
							whiteconnect=0;
							break;
						}
					}
				}
				
			}
		}
		return SPACE;
	}

	
}
