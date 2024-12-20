package gobang_unfinished;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class checkerboard extends JPanel{
	private int gap=50;
	private int unit=10;
	private int sx=10;
	private int sy=10;
	
	private static checkerboard instance;
	private checkerboard(){
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int width=getWidth();
				int height=getHeight();
				int min=width<height?width:height;
				unit=(min-2*gap)/(chessModel.WIDTH-1);
				sx=(width-(chessModel.WIDTH-1)*unit)/2;
				sy=(height-(chessModel.WIDTH-1)*unit)/2;
				repaint();
			}
		});;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int col=(e.getX()-sx)/unit;
				int row=(e.getY()-sy)/unit;
				if((e.getX()-sx)%unit>unit/2){
					col++;
				}
				if((e.getY()-sy)%unit>unit/2){
					row++;
				}
				chessController.getinstance().localPutChess(row,col);

			}
		});
	};
	public static checkerboard getinstance(){
		if(instance==null)
			instance=new checkerboard();
		return instance;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawPanel(g);
		drawChess(g);
	}
	
	private void drawChess(Graphics g) {
		chessModel m=chessModel.getinstance();
		for(int row=0;row<chessModel.WIDTH;row++){
			for(int col=0;col<chessModel.WIDTH;col++){
				int color=m.getchess(row, col);
				if(color==chessModel.BLACK){
					g.setColor(Color.BLACK);
					g.fillOval(sx+col*unit-unit/2, sy+row*unit-unit/2, unit, unit);
				}else if(color==chessModel.WHITE){
					g.setColor(Color.WHITE);
					g.fillOval(sx+col*unit-unit/2, sy+row*unit-unit/2, unit, unit);
				}
			}
		}
	}
	
	private void drawPanel(Graphics g) {
		g.setColor(Color.BLACK);
		for(int i=0;i<chessModel.WIDTH;i++){
			g.drawLine(sx, sy+i*unit, sx+unit*(chessModel.WIDTH-1), sy+i*unit);
			g.drawLine(sx+i*unit,sy,sx+i*unit,sy+unit*(chessModel.WIDTH-1));
		}
	}
}
