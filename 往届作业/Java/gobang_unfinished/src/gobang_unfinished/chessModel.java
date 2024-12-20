package gobang_unfinished;

public class chessModel {
	private static chessModel instance;
	public static chessModel getinstance(){
		if(instance==null)
			instance=new chessModel();
		return instance;
	}
	
	public static int WIDTH=25;
	public static final int BLACK=1;
	public static final int SPACE=0;
	public static final int WHITE=-1;
	
	public static int steps=0;
	
	private static int[][] data=new int[WIDTH][WIDTH];

	private int lastRow,lastCol;//可以往list里面存
	
	public boolean putChess(int row,int col,int color){
		if(row>=0&&row<=WIDTH&&col>=0&&col<=WIDTH&&data[row][col]==SPACE){
			data[row][col]=color;
			lastRow=row;
			lastCol=col;
			if(color==chessModel.BLACK&&choosepart.getinstance().boxblack.isSelected()||color==chessModel.WHITE&&choosepart.getinstance().boxwhite.isSelected())
				steps++;
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
	
	public void removedata(){
		for(int row=0;row<WIDTH;row++){
			for(int col=0;col<WIDTH;col++){
				data[row][col]=SPACE;
			}
		}
		steps=0;
		setstepsnum(0);
	}
	
	public void setstepsnum(int i) {
		String num=String.valueOf(steps);
		chessView.getinstance().stepsnum.setText(num);
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
