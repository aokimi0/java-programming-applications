package gobang_ljx;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JOptionPane;


public class Controller {
	private static Controller instance=new Controller();
	private Controller(){}
	public static Controller getinstance(){
		return instance;
	}
	static boolean flag = true;
	private int localColor=Model.BLACK;
	private int otherColor=Model.WHITE;
	private boolean netMode=true;
	private boolean allowPutChess=true;
	
	public boolean getnetMode(){
		return netMode;
	}
	
	public int getotherColor(){
		return otherColor;
	}
	
	public void setlocalColor (int localColor){
		this.localColor=localColor;
	}
	
	public void setotherColor(int otherColor){
		this.otherColor=otherColor;
	}
	
	public void setNetMode(boolean netMode) {
		this.netMode = netMode;
	}
	
	public void setAllowPutChess(boolean allowPutChess) {
		this.allowPutChess = allowPutChess;
	}
	
	public void localPutChess(int row, int col) {
		if(!netMode){
			localModeputChess(row, col);
		}else{
			netModeputChess(row,col);
		}
	}

	private void localModeputChess(int row, int col) {
		boolean success=Model.getinstance().putChess(row, col, localColor);
		if(success){
			localColor=-localColor;
			ChessPanel.getinstance().repaint();
			int winner=Model.getinstance().whoWin();
			if(winner==Model.BLACK){
				JOptionPane.showMessageDialog(null, "黑棋赢");
			}
		}
	}
	
	private void netModeputChess(int row, int col) {
		if(!allowPutChess)
			return;
		boolean success=Model.getinstance().putChess(row, col, localColor);
		if(success){
			ChessPanel.getinstance().repaint();
			NetHelper.getinstance().sendChess(row, col);
			allowPutChess=false;
			int winner=Model.getinstance().whoWin();
			if(winner==Model.BLACK){
				JOptionPane.showMessageDialog(null, "黑棋赢");
			}
		}
		
	}
	
	public void netOtherPutChess(int row,int col){
		boolean success=Model.getinstance().putChess(row, col, otherColor);
		if(success){
			ChessPanel.getinstance().repaint();
			
			allowPutChess=true;
			
			int winner=Model.getinstance().whoWin();
			if(winner==Model.BLACK){
				JOptionPane.showMessageDialog(null, "黑棋赢");
			}
		}
	} 
	
	public void localRemoveChess(int row,int col){
		if(!netMode){
			localModeRemoveChess(row, col);
		}else{
			netModeRemoveChess(row,col);
		}
	}
	private void netModeRemoveChess(int row, int col) {
		if(allowPutChess)
			return;
		boolean success=Model.getinstance().removeChess(row, col);
		if(success){
			allowPutChess=true;
			ChessPanel.getinstance().repaint();
			//net send row,col
			NetHelper.getinstance().sendremoveChess(row, col);
		}
		
	}
	private void localModeRemoveChess(int row, int col) {
		boolean success=Model.getinstance().removeChess(row, col);
		if(success){
			ChessPanel.getinstance().repaint();
		}
		
	}
	public void netOtherRemoveChess(int row, int col) {
		boolean success=Model.getinstance().removeChess(row, col);
		if(success){
			ChessPanel.getinstance().repaint();
		}
	}
	public void setChat(String line) {
		TALKING.getinstance().addChat(line);
	}
	public void sendpeace() {
		String [] options = {"同意","不同意"};
		int pick=JOptionPane.showOptionDialog(null,"对方请求和棋","注意",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
		if(pick==0){
			NetHelper.getinstance().okpeace();
			Model.getinstance().againChess();
			ChessPanel.getinstance().repaint();
		}
		
	}
	static void playMusic(String musicName) {// 背景音乐播放
		 
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(musicName));
			AudioFormat aif = ais.getFormat();
			final SourceDataLine sdl;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
			sdl = (SourceDataLine) AudioSystem.getLine(info);
			sdl.open(aif);
			sdl.start();
			FloatControl fc = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);
			// value可以用来设置音量，从0-2.0
			double value = 2;
			float dB = (float) (Math.log(value == 0.0 ? 0.0001 : value) / Math.log(10.0) * 20.0);
			fc.setValue(dB);
			int nByte = 0;
			int writeByte = 0;
			final int SIZE = 1024 * 64;
			byte[] buffer = new byte[SIZE];
			while (nByte != -1) {// 判断 播放/暂停 状态
				
				if(flag) {
					
					nByte = ais.read(buffer, 0, SIZE);
					
					sdl.write(buffer, 0, nByte);
					
				}else {
					
					nByte = ais.read(buffer, 0, 0);
					
				}
				
			}
			sdl.stop();
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
