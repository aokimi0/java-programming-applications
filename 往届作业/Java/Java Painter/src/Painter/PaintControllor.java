package Painter;

import java.awt.Color;
import java.awt.Point;

/**
 * 控制器，接收界面所有操作的通知，并记录下来
 * 特殊时刻，接收到背景颜色修改时，通知界面刷新。
 * @author flower
 *
 */
public class PaintControllor {
	/**
	 * 当前正在描绘的形状，由PaintModel中的常量赋值
	 */
	private int currentShape;
	/**
	 * 形状的颜色，根据界面选择赋值,默认是黑色
	 */
	private Color shapeColor = Color.black;
	/**
	 * 背景色，根据界面选择情况赋值，默认是白色
	 */
	private Color backgroundColor = Color.white;
	/**
	 * 要显示的文字，根据界面输入的情况赋值，默认是hello
	 */
	private String text = "hello";
	/**
	 * 字体大小，根据界面输入情况赋值，默认是10
	 */
	private int fontSize = 10;
	/**
	 * 形状是否填充，根据界面输入情况赋值，默认是“不填充”
	 */
	private boolean fillRegion = false;
	/**
	 * 鼠标按下的时的坐标
	 */
	private Point point1;
	/**
	 * 鼠标抬起或者拖动时的坐标
	 */
	private Point point2;

	public void setCurrentShape(int shape) {
		this.currentShape = shape;
	}

	public int getFontSize() {
		return fontSize;
	}
	/**
	 * 点击界面字体大小旁边的箭头时调用本函数，参数可以是正整数，也可以是负整数
	 * @param step
	 */
	public void addFontSize(int step) {
		this.fontSize += step;
		Vars.toolPanel.refreshFontSize();
	}

	public void setFillRegion(boolean selected) {
		fillRegion = selected;
	}

	public void setText(String text) {
		this.text = text;
	}
	/**
	 * 接收界面背景色改变的操作，一旦接收到，记录下来，并且通知面板刷新
	 * @param color
	 */
	public void setBackgroundColor(Color color) {
		this.backgroundColor = color;
		Vars.paintPanel.repaint();
	}

	public void setShapeColor(Color color) {
		this.shapeColor = color;
	}
	/**
	 * 接收鼠标抬起或者拖动时的通知，一旦接收到通知，就根据当前形状，通知模型对象PaintModel记录当前形状
	 * @param p1
	 * @param p2
	 */
	public void reportMousePoint(Point p1, Point p2) {
		this.point1 = p1;
		this.point2 = p2;
		if (currentShape != PaintModel.STRING) {
			Vars.paintModel.addShape(currentShape,shapeColor,fillRegion, p1, p2);
		}else{
			Vars.paintModel.addString(text,shapeColor,fontSize,p2);
		}
	}
	public int getCurrentShape() {
		return currentShape;
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public Color getShapeColor() {
		return shapeColor;
	}
	public String getText() {
		return text;
	}
	public Point getPoint2() {
		return point2;
	}
	public boolean isFillRegion() {
		return fillRegion;
	}

	public Point getPoint1() {
		return point1;
	}

	public void reportMousePointPressed(Point p1) {
		this.point1 = p1;
	}

	public void reportMousePointRelease(Point p2) {
		this.point2 = p2;
	}
}

