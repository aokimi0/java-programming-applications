package Painter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * 界面左侧的形状选择条，继承自JToolBar，可以拖出来
 * @author flower
 *
 */
public class ShapeBar extends JToolBar{
	/**
	 * 圆形按钮，默认为null，第一个调用getCircleButton函数时进行构造
	 */
	private JButton circleButton;
	private JButton ovalButton;
	private JButton rectButton;
	private JButton lineButton;
	private JButton stringButton;
	/**
	 * 获取圆形按钮的函数，本函数使用了“懒加载”。
	 * 当第一次调用本函数circleButton必然为null，就创造该按钮对象，并对该按钮附加监听器，
	 * 监听器的唯一动作就是向控制器汇报点击按钮的类型
	 * 其它按钮采用类似的方法
	 * @return
	 */
	public JButton getCircleButton() {
		if(circleButton==null){
			circleButton = new JButton("yuan");
			circleButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Vars.paintControllor.setCurrentShape(PaintModel.CIRCLE);
				}
			});
		}
		return circleButton;
	}
	public JButton getOvalButton() {
		if(ovalButton==null){
			ovalButton = new JButton("oval");
			ovalButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Vars.paintControllor.setCurrentShape(PaintModel.OVAL);
				}
			});
		}
		return ovalButton;
	}
	public JButton getRectButton() {
		if(rectButton==null){
			rectButton = new JButton("rect");
			rectButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Vars.paintControllor.setCurrentShape(PaintModel.RECT);
				}
			});
		}
		return rectButton;
	}
	public JButton getLineButton() {
		if (lineButton == null) {
			lineButton = new JButton("line");
			lineButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Vars.paintControllor.setCurrentShape(PaintModel.LINE);
				}
			});
		}

		return lineButton;
	}
	public JButton getStringButton() {
		if (stringButton == null) {
			stringButton = new JButton("string");
			stringButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Vars.paintControllor.setCurrentShape(PaintModel.STRING);
				}
			});
		}

		return stringButton;
	}
	/**
	 * 构造函数，设置本工具条的摆放方式为纵向
	 * 依次添加各按钮
	 */
	public ShapeBar() {
		setOrientation(JToolBar.VERTICAL);
		add(getCircleButton());
		add(getOvalButton());
		add(getRectButton());
		add(getLineButton());
		add(getStringButton());
	}
}
