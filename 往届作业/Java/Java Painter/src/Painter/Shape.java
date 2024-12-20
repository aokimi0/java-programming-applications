package Painter;

import java.awt.Color;
import java.awt.Point;
/**
 * 形状对象，保存一个形状所需要的所有数据
 * @author flower
 *
 */
public class Shape {
	/**
	 * 形状类型，其值域是PaintModel中的常量
	 */
	private int shapeType ;
	/**
	 * 形状的颜色
	 */
	private Color shapeColor;
	/**
	 * 如果该形状不是普通的矩形、圆等，是文字，则text变量保存文字内容
	 */
	private String text;
	/**
	 * 鼠标按下时的点
	 */
	private Point point1;
	/**
	 * 鼠标抬起时的点
	 */
	private Point point2;
	/**
	 * 形状是否填充
	 */
	private boolean fillRegion;
	/**
	 * 文字字体的大小
	 */
	private int fontSize = 10;
	/**
	 * 普通形状的构造函数
	 * @param shapeType 形状类型
	 * @param shapeColor 形状的颜色
	 * @param fillRegion 是否填充
	 * @param point1 鼠标按下时的点
	 * @param point2 鼠标抬起或者拖动时的点
	 */
	public Shape(int shapeType,Color shapeColor,boolean fillRegion, Point point1, Point point2) {
		super();
		this.shapeType = shapeType;
		this.shapeColor = shapeColor;
		this.fillRegion = fillRegion;
		this.point1 = point1;
		this.point2 = point2;
	}
	/**
	 * 文字类型的形状构造函数
	 * @param shapeType 形状类型，必须是PaintModel中的String类型
	 * @param text 要显示的文字
	 * @param shapeColor 文字的颜色
	 * @param fontSize 文字字体的大小
	 * @param point1 要显示文字的位置
	 */
	public Shape(int shapeType, String text,Color shapeColor,int fontSize, Point point1) {
		super();
		this.shapeType = shapeType;
		this.shapeColor = shapeColor;
		this.fontSize = fontSize;
		this.text = text;
		this.point1 = point1;
	}

	public int getShapeType() {
		return shapeType;
	}
	public Point getPoint1() {
		return point1;
	}
	public Point getPoint2() {
		return point2;
	}
	public String getText() {
		return text;
	}
	public int getFontSize() {
		return fontSize;
	}
	public Color getShapeColor() {
		return shapeColor;
	}
	public boolean isFillRegion() {
		return fillRegion;
	}
}
