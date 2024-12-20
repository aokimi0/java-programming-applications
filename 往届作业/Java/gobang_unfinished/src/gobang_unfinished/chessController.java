package gobang_unfinished;

import javax.swing.JOptionPane;

public class chessController {
	private static chessController instance;
	public static chessController getinstance(){
		if(instance==null)
			instance=new chessController();
		return instance;
	}
	private int localColor;
	private int otherColor=chessModel.WHITE;
	private boolean netMode=false;
	private boolean allowPutChess=true;
	
	public void setNetMode(boolean netMode) {
		this.netMode = netMode;
	}
	public boolean getnetMode() {
		return netMode;

}
	public void setAllowPutChess(boolean allowPutChess) {
		this.allowPutChess = allowPutChess;
	}
	
	public void setlocalColor(int color){
		localColor=color;
	}
	
	public void localPutChess(int row, int col) {
		if(!netMode){
			localModeputChess(row, col);
		}else{
			netModeputChess(row,col);
		}
	}
	public void netOtherPutChess(int row,int col){
		boolean success=chessModel.getinstance().putChess(row, col, otherColor);
		if(success){
			chessModel.getinstance().setstepsnum(chessModel.steps);
			checkerboard.getinstance().repaint();
			
			allowPutChess=true;
			int winner=chessModel.getinstance().whoWin();
			if(winner==chessModel.BLACK){
				JOptionPane.showMessageDialog(null, "ºÚÆåÓ®");
			}
		}
	} 
	private void netModeputChess(int row, int col) {
		if(!allowPutChess)
			return;
		boolean success=chessModel.getinstance().putChess(row, col, localColor);
		if(success){
			chessModel.getinstance().setstepsnum(chessModel.steps);
			checkerboard.getinstance().repaint();
			//net send row,col
			NetHelper.getinstance().sendChess(row, col);
			
			allowPutChess=false;
			int winner=chessModel.getinstance().whoWin();
			if(winner==chessModel.BLACK){
				JOptionPane.showMessageDialog(null, "ºÚÆåÓ®");
			}
		}
		
	}

	private void localModeputChess(int row, int col) {
		boolean success=chessModel.getinstance().putChess(row, col, localColor);
		if(success){
			localColor=-localColor;
			chessModel.getinstance().setstepsnum(chessModel.steps);
			checkerboard.getinstance().repaint();
			int winner=chessModel.getinstance().whoWin();
			if(winner==chessModel.BLACK){
				JOptionPane.showMessageDialog(null, "ºÚÆåÓ®");
			}
		}
	}

	public void connect(String ip) {
		localColor=chessModel.WHITE;
		otherColor=chessModel.BLACK;
		allowPutChess=false;
		NetHelper.getinstance().connect(ip);
		
	}
}
