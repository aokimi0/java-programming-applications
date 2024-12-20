package Painter;
/**
 * 定义必须要使用的对象，便于在别的地方很方便的使用对象
 * @author flower
 *
 */
public class Vars {
	/**
	 * 用于绘图的面板对象
	 */
	public static final PaintPanel paintPanel = new PaintPanel();
	/**
	 * 工具面板，用于选择颜色，大小等
	 */
	public static final ToolPanel toolPanel = new ToolPanel();
	/**
	 * 形状条，放置选择形状按钮
	 */
	public static final ShapeBar shapeBar = new ShapeBar();
	/**
	 * 本程序的控制器对象
	 */
	public static final PaintControllor paintControllor = new PaintControllor();
	/**
	 * 本程序的模型对象
	 */
	public static final PaintModel paintModel = new PaintModel();
}
