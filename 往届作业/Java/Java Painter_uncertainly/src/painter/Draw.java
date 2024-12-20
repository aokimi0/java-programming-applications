package painter;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class Draw extends MouseAdapter{
	private static Draw instance;
	public static Draw getinstance(){
		if(instance==null){
			instance=new Draw();
		}
		return instance;
	}
	
	private int sizetext=20;
	private static Color BackgroundColor=Color.BLUE;//无构造函数必加static（？）
	private static Color ShapeColor=Color.BLACK;
	private static boolean fillregion;
	private String currentShape;
	private static int startx=0,starty=0,endx=0,endy=0,x=0,y=0;
	paintpanel pp=paintpanel.getinstance();
	
	public static ArrayList<Shape> list=new ArrayList<Shape>();
	
	public void setBackgroundColor(Color selectedItem) {
		BackgroundColor=selectedItem;
		//响应成功
	}
	public Color getBackgrounColor(){
		return BackgroundColor;
	}
	public void setShapeColor(Color selectedItem) {
		ShapeColor=selectedItem;
	}
	public Color getShapeColor(){
		return ShapeColor;
	}
	public void setFillRegion(boolean selected) {
		fillregion=selected;
		//响应成功
	}
	
	public int getstartx() {
		return startx;
	}
	public int getstarty() {
		return starty;
	}

	
	//以下均响应成功
	public void mousePressed(MouseEvent e){
		currentShape=paintpanel.currentShape;
		startx=e.getX();
		starty=e.getY();
		paintpanel.startx=e.getX();
		paintpanel.starty=e.getY();
		
		String xx=String.valueOf(startx);
		String yy=String.valueOf(starty);
		
		South.getinstance().x_1.setText(xx);
		South.getinstance().y_1.setText(yy);
	}
	public void mouseReleased(MouseEvent e){
		endx=e.getX();
		endy=e.getY();
		String xx=String.valueOf(endx);
		String yy=String.valueOf(endy);
		
		South.getinstance().x_2.setText(xx);
		South.getinstance().y_2.setText(yy);
		
		if(currentShape.equals("圆形")){
			Shape s=new Shape(startx,starty,endx,endy,fillregion,BackgroundColor,ShapeColor,"圆形");
			list.add(s);
			pp.repaint();
		}
		else if(currentShape.equals("椭圆")){
			Shape s=new Shape(startx,starty,endx,endy,fillregion,BackgroundColor,ShapeColor,"椭圆");
			list.add(s);
			pp.repaint();
		}
		else if(currentShape.equals("矩形")){
			Shape s=new Shape(startx,starty,endx,endy,fillregion,BackgroundColor,ShapeColor,"矩形");
			list.add(s);
			pp.repaint();
		}
		else if(currentShape.equals("直线")){
			Shape s=new Shape(startx,starty,endx,endy,fillregion,BackgroundColor,ShapeColor,"直线");
			list.add(s);
			pp.repaint();
		}
	}
	public void mouseClicked(MouseEvent e){
		endx=e.getX();
		endy=e.getY();
		if(currentShape.equals("文字")){
			String text=North.gettext();
			//可以运行
			setsizetext();
			Shape s=new Shape(text,endx,endy,ShapeColor,sizetext,"文字");
			list.add(s);
			pp.repaint();
		}
	}
	
	public void mouseDragged(MouseEvent e){

		paintpanel.getinstance().x=e.getX();
		paintpanel.getinstance().y=e.getY();
		paintpanel.getinstance().fillregion=fillregion;
		paintpanel.Shape=currentShape;
		paintpanel.getinstance().ShapeColor=ShapeColor;
		paintpanel.getinstance().backgroundColor=BackgroundColor;

		x=e.getX();
		y=e.getY();
		
		String xx=String.valueOf(x);
		String yy=String.valueOf(y);
		
		South.getinstance().x_0.setText(xx);
		South.getinstance().y_0.setText(yy);
		
		pp.repaint();
		
	}
	
	public void mouseMoved(MouseEvent e){
		x=e.getX();
		y=e.getY();
		
		String xx=String.valueOf(x);
		String yy=String.valueOf(y);
		
		South.getinstance().x_0.setText(xx);
		South.getinstance().y_0.setText(yy);
	}
	
	public void setsizetext() {
		String sizetext_str=North.getsize1();
		if(!sizetext_str.equals(""))
			sizetext=Integer.parseInt(sizetext_str);
	}
	
	
}
