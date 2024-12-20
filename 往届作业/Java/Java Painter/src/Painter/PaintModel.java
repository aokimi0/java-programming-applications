package Painter;

import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;
/**
 * 模型对象，定义形状常量，保存形状数据，返回形状数据
 * @author flower
 *
 */
public class PaintModel {
	/**定义圆*/
	public static final int CIRCLE = 1;
	/**定义椭圆*/
	public static final int OVAL = 2;
	/**定义矩形*/
	public static final int RECT = 3;
	/**定义线*/
	public static final int LINE = 4;
	/**定义文字*/
	public static final int STRING = 5;
	/**
	 * 保存所有形状数据的一个对象，是LinkedList类型，只保存Shape类型的对象
	 */
	private LinkedList<Shape> data = new LinkedList<Shape>();
	/**
	 * 添加形状，不包含文字类型的对象，将传来的参数构造一个Shape对象，并添加到data对象中
	 * @param currentShape 当前形状
	 * @param shapeColor 形状的颜色
	 * @param fillRegion 是否填充
	 * @param p1 鼠标按下的点
	 * @param p2 鼠标抬起的点
	 */
	public void addShape(int currentShape, Color shapeColor,boolean fillRegion, Point p1, Point p2) {
		data.add(new Shape(currentShape, shapeColor,fillRegion,p1, p2));
	}
	/**
	 * 添加文字类型的形状对象，将传来的参数构造一个Shape对象，并添加到data对象中
	 * @param text 要显示的文字
	 * @param shapeColor 文字的颜色
	 * @param fontSize 字体的大小
	 * @param point 鼠标按下的点
	 */
	public void addString(String text, Color shapeColor,int fontSize,Point point) {
		data.add(new Shape(STRING,text,shapeColor,fontSize,point));
	}
	/**
	 * 获得一共有多少个形状
	 * @return 已经被保存形状的个数
	 */
	public int getShapeSize(){
		return data.size();
	}
	/**
	 * 根据下标获取相对应的形状对象
	 * @param index
	 * @return
	 */
	public Shape getShape(int index){
		return data.get(index);
	}
}
