package gobang;

public class Model {
	public static final int WIDTH=19;
	public static final int BLACK=1;
	public static final int SPACE=0;
	public static final int WHITE=-1;
	
	private static int data[][]=new int[WIDTH][WIDTH];
	private int lastRow,lastCol;
	
	private static Model instance;
	public static Model getinstance(){
		if(instance==null){
			instance=new Model();
		}
		return instance;
	}
	public boolean putChess(int row,int col,int color){
		if(row>=0&&row<WIDTH&&col>=0&&col<WIDTH&&data[row][col]==SPACE){
			data[row][col]=color;
			lastRow=row;
			lastCol=col;
			
			return true;
		}
		return false;
	}
	public int getchess(int row, int col) {
		return data[row][col];
	}
	public int whoWin(){
		int blackconnect=0,whiteconnect=0;
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
		
		for(int row=4;row<WIDTH;row++){
			for(int col=4;col<WIDTH;col++){
				if(data[row][col]==BLACK){
					int move=1;
					for(;move<5;move++){
						if(data[row-move][col+move]==BLACK){
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
					int move=1;
					for(;move<5;move++){
						if(data[row-move][col+move]==WHITE){
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
