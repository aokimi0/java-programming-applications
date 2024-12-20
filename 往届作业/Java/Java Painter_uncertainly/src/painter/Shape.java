package painter;

import java.awt.Color;

public class Shape {
	public boolean fillregion=false;
	public int startx,starty,endx,endy;
	public String Shape="т╡пн";
	public static String currentShape;
	public String text;
	public Color backgroundcolor;
	public Color ShapeColor;
	public int sizetext;
	
	public Shape(){};
	public Shape(int startx,int starty,int endx,int endy, boolean fillregion,Color backgroundcolor,Color ShapeColor,String currentShape){
		this.endx=endx;
		this.endy=endy;
		this.fillregion=fillregion;
		this.startx=startx;
		this.starty=starty;
		this.Shape=currentShape;
		this.backgroundcolor=backgroundcolor;
		this.ShapeColor=ShapeColor;
	}
	public Shape(String text,int endx,int endy,Color ShapeColor,int sizetext,String currentShape){
		this.endx=endx;
		this.endy=endy;
		this.text=text;
		this.Shape=currentShape;
		this.ShapeColor=ShapeColor;
		this.sizetext=sizetext;
	}
	
	
}
