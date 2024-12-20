package Painter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;
/**
 * 绘制面板，接收鼠标输入并汇报给控制器对象PaintControllor，并根据模型对象PaintModel中的形状情况绘制界面
 * @author flower
 *
 */
public class PaintPanel extends JPanel {
	/**
	 * 鼠标按下和抬起的两个点
	 */
	private Point p1, p2;
	/**
	 * 构造函数，负责给当前面板添加鼠标监听器
	 */
	public PaintPanel() {
		addMouseListener(new MouseAdapter() {
			/**
			 * 鼠标按下的时候，向控制器汇报鼠标按下，控制器应该将该点保存到p1
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				p1 = e.getPoint();
				Vars.paintControllor.reportMousePointPressed(p1);
			}
			/**
			 * 鼠标抬起的时候，向控制器汇报鼠标按下和抬起两个点，控制器应该将两个点分别保存到p1和p2
			 */
			@Override
			public void mouseReleased(MouseEvent e) {
				p2 = e.getPoint();
				repaint();
				Vars.paintControllor.reportMousePoint(p1, p2);
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			/**
			 * 鼠标拖动的时候，向控制器汇报当前点，控制器应该将该点保存为p2，与此同时，刷新界面，强制当前正在绘制的形状刷新
			 */
			@Override
			public void mouseDragged(MouseEvent e) {
				p2 = e.getPoint();
				Vars.paintControllor.reportMousePointRelease(p2);
				repaint();
			}
		});
	}

	/**
	 * 覆盖父类的方法，显示应该绘制的所有形状
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//设置当前面板不透明
		setOpaque(true);
		//根据控制器的背景色数值，修改本面板的背景色
		setBackground(Vars.paintControllor.getBackgroundColor());
		//从模型中获得要绘制的形状的数量
		int size = Vars.paintModel.getShapeSize();
		//依次从模型中获得曾经被保存的形状，并绘制它们
		for (int i = 0; i < size; i++) {
			Shape shape = Vars.paintModel.getShape(i);
			drawShape(shape, g);
		}
		//绘制当前正在绘制的形状
		drawCurrentShape(g);
	}
	/**
	 * 绘制当前正在绘制的形状
	 * @param g
	 */
	private void drawCurrentShape(Graphics g) {
		//从控制器对象中获得当前正在绘制的形状的所有相关数据
		int currentShape = Vars.paintControllor.getCurrentShape();
		Point point1 = Vars.paintControllor.getPoint1();
		Point point2 = Vars.paintControllor.getPoint2();
		int fontSize = Vars.paintControllor.getFontSize();
		String text = Vars.paintControllor.getText();
		Color shapeColor = Vars.paintControllor.getShapeColor();
		boolean fillRegion = Vars.paintControllor.isFillRegion();
		//定义一个形状类型的引用，先不赋值，根据情况赋值
		Shape shape;
		if(currentShape==PaintModel.STRING){//根据从控制器得到的形状类型消息，如果是文字类型
			//构造一个文字类型的Shape对象，需要“类型，文字，颜色，大小，位置”数据
			shape = new Shape(PaintModel.STRING,text,shapeColor,fontSize,point2);
		}else{
			//如果不是文字，就是普通形状，需要“类型，颜色，是否填充，起点，终点”数据
			shape = new Shape(currentShape,shapeColor,fillRegion,point1,point2);
		}
		//将刚刚构造出的对象进行描绘
		drawShape(shape,g);
	}
	/**
	 * 描绘一个形状，根据不同的形状类型调用不同的函数进行描绘
	 * @param shape
	 * @param g
	 */
	private void drawShape(Shape shape, Graphics g) {
		switch (shape.getShapeType()) {
		case PaintModel.CIRCLE:
			drawCircle(shape, g);
			break;
		case PaintModel.LINE:
			drawLine(shape, g);
			break;
		case PaintModel.OVAL:
			drawOval(shape, g);
			break;
		case PaintModel.RECT:
			drawRect(shape, g);
			break;
		case PaintModel.STRING:
			drawString(shape, g);
			break;
		}
	}
	/**
	 * 描绘字符串，从shape对象中取出文字，大小。
	 * 首先设置g的颜色
	 * 取出g的当前字体，使用font.deriveFont(float)方法，修改当前字体对象的大小，并得到一个新的字体对象
	 * 将新的字体对象设置给g对象
	 * 使用g对象绘制文字，
	 * @param shape
	 * @param g
	 */
	private void drawString(Shape shape, Graphics g) {
		g.setColor(shape.getShapeColor());
		Font font = g.getFont();
		font = font.deriveFont((float)shape.getFontSize());
		g.setFont(font);
		g.drawString(shape.getText(), (int) shape.getPoint1().getX(),
				(int) shape.getPoint1().getY());
	}
	/**
	 * 绘制矩形，首先根据形状对象中的颜色，设置g的颜色
	 * 如果是填充，就调用fillRect，否则就调用drawRect
	 * 注意左上角点的坐标是两个鼠标点的x，y较小值，宽高是x，y差值的绝对值
	 * @param shape
	 * @param g
	 */
	private void drawRect(Shape shape, Graphics g) {
		g.setColor(shape.getShapeColor());
		int x1 = (int) shape.getPoint1().getX();
		int y1 = (int) shape.getPoint1().getY();
		int x2 = (int) shape.getPoint2().getX();
		int y2 = (int) shape.getPoint2().getY();
		if (shape.isFillRegion()) {
			g.fillRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
					Math.abs(y1 - y2));
		}else{
			g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
					Math.abs(y1 - y2));
		}
	}
	/**
	 * 绘制椭圆，首先根据形状对象中的颜色，设置g的颜色
	 * 如果是填充，就调用fillRect，否则就调用drawRect
	 * 注意左上角点的坐标是两个鼠标点的x，y较小值，宽高是x，y差值的绝对值
	 * @param shape
	 * @param g
	 */
	private void drawOval(Shape shape, Graphics g) {
		g.setColor(shape.getShapeColor());
		int x1 = (int) shape.getPoint1().getX();
		int y1 = (int) shape.getPoint1().getY();
		int x2 = (int) shape.getPoint2().getX();
		int y2 = (int) shape.getPoint2().getY();
		if (shape.isFillRegion()) {
			g.fillOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
					Math.abs(y1 - y2));
		}else{
			g.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
					Math.abs(y1 - y2));
		}
	}
	/**
	 * 绘制线，首先根据形状对象中的颜色，设置g的颜色
	 * @param shape
	 * @param g
	 */
	private void drawLine(Shape shape, Graphics g) {
		g.setColor(shape.getShapeColor());
		int x1 = (int) shape.getPoint1().getX();
		int y1 = (int) shape.getPoint1().getY();
		int x2 = (int) shape.getPoint2().getX();
		int y2 = (int) shape.getPoint2().getY();
		g.drawLine(x1, y1, x2, y2);
		
	}
	/**
	 * 绘制圆，首先根据形状对象中的颜色，设置g的颜色
	 * 如果是填充，就调用fillRect，否则就调用drawRect
	 * 注意左上角点的坐标是两个鼠标点的x，y较小值，宽高是一样的，取x1-x2与y1-y2绝对值中的较小值
	 * @param shape
	 * @param g
	 */
	private void drawCircle(Shape shape, Graphics g) {
		g.setColor(shape.getShapeColor());
		int x1 = (int) shape.getPoint1().getX();
		int y1 = (int) shape.getPoint1().getY();
		int x2 = (int) shape.getPoint2().getX();
		int y2 = (int) shape.getPoint2().getY();
		int w1 = Math.abs(x1-x2);
		int w2 = Math.abs(y1-y2);
		int w = Math.min(w1, w2);
		if (shape.isFillRegion()) {
			g.fillOval(Math.min(x1, x2), Math.min(y1, y2), w,w);
		}else{
			g.drawOval(Math.min(x1, x2), Math.min(y1, y2), w,w);
		}
	}

}
