package Painter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicArrowButton;
/**
 * 工具面板，此面板要放置的组件较多，相对比较复杂，但都是面板与面板的嵌套
 * @author flower
 *
 */
public class ToolPanel extends JPanel {
	/**
	 * 整体上分为四个面板，左上，左下，右上，右下
	 */
	private JPanel leftUpPanel = null;
	private JPanel leftDownPanel = null;
	private JPanel rightUpPanel = null;
	private JPanel rightDownPanel = null;

	/**
	 * 背景色组合框和形状颜色组合框
	 */
	private JComboBox<Color> backgroundColorComboBox = null;
	private JComboBox<Color> shapeColorComboBox = null;
	
	/**
	 * 为背景色组合框和形状颜色组合框准备的颜色对象数组
	 */
	private Color[] backgroundColors = { Color.white,Color.yellow, Color.red };
	private Color[] shapeColors = { Color.black,Color.yellow, Color.red };
	
	/**
	 * 是否填充checkbox
	 */
	private JCheckBox fillRegionCheckbox;
	/**
	 * 文字大小面板，里面放置"size"标签、数字面板
	 */
	private JPanel sizePanel;
	/**
	 * 数字面板，里面放置文字大小文本域和加减按钮
	 */
	private JPanel numberPanel;
	/**
	 * 用于输入文字的文本域
	 */
	private JTextField stringTextField;
	/**
	 * 用于显示文字大小的文本域
	 */
	private JTextField fontSizeTextField;
	/**
	 * 放置调整文字大小的加减按钮的面板
	 */
	private JPanel basicArrowPanel;
	/**
	 * 调整文字大小的按钮
	 */
	private BasicArrowButton downBasicArrowButton;
	private BasicArrowButton upBasicArrowButton;

	/**
	 * 获取左下面板，该函数实现了“懒加载”。
	 * 第一次调用本函数时，leftDownPanel肯定为null，进行构造。
	 * 以后调用的时候，直接返回对象。
	 * @return
	 */
	public JPanel getLeftDownPanel() {
		if (leftDownPanel == null) {
			leftDownPanel = new JPanel();
			leftDownPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			leftDownPanel.add(new JLabel("Background"));
			leftDownPanel.add(getBackgroundColorComboBox());
		}

		return leftDownPanel;
	}

	private JComboBox<Color> getBackgroundColorComboBox() {
		if (backgroundColorComboBox == null) {
			backgroundColorComboBox = new JComboBox<Color>(backgroundColors);
			backgroundColorComboBox.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					//只要组合框中选项被选择了，马上向控制器汇报被修改的颜色
					if (e.getStateChange()==ItemEvent.SELECTED) {
						Vars.paintControllor.setBackgroundColor((Color) backgroundColorComboBox.getSelectedItem());
					}
				}
			});
		}

		return backgroundColorComboBox;
	}

	public JPanel getLeftUpPanel() {
		if (leftUpPanel == null) {
			leftUpPanel = new JPanel();
			leftUpPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			leftUpPanel.add(new JLabel("Shape Color"));
			leftUpPanel.add(getShapeColorComboBox());
		}
		return leftUpPanel;
	}

	public JComboBox<Color> getShapeColorComboBox() {
		if (shapeColorComboBox == null) {
			shapeColorComboBox = new JComboBox<Color>(shapeColors);
			shapeColorComboBox.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					//只要组合框中选项被选择了，马上向控制器汇报被修改的颜色
					if (e.getStateChange() == ItemEvent.SELECTED) {
						Vars.paintControllor.setShapeColor((Color) shapeColorComboBox.getSelectedItem());
					}
				}
			});
		}
		return shapeColorComboBox;
	}

	public JPanel getRightDownPanel() {
		if (rightDownPanel == null) {
			rightDownPanel = new JPanel(new GridLayout(1, 2));
			rightDownPanel.add(getSizePanel());
			rightDownPanel.add(getFillRegionCheckbox());
		}
		return rightDownPanel;
	}

	private JCheckBox getFillRegionCheckbox() {
		if (fillRegionCheckbox == null) {
			fillRegionCheckbox = new JCheckBox("Fill the region");
			fillRegionCheckbox.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					//只要是否填充状态改变了，马上向控制器汇报当前状态
					Vars.paintControllor.setFillRegion(fillRegionCheckbox
							.isSelected());
				}
			});
		}

		return fillRegionCheckbox;
	}

	private JPanel getSizePanel() {
		if (sizePanel == null) {
			sizePanel = new JPanel(new GridLayout(1, 2));
			sizePanel.add(new JLabel("Size"));
			sizePanel.add(getNumberPanel());
		}

		return sizePanel;
	}

	private JPanel getNumberPanel() {
		if (numberPanel == null) {
			numberPanel = new JPanel(new BorderLayout());

			numberPanel.add(getNumberTextField(), BorderLayout.CENTER);
			numberPanel.add(getBasicArrowPanel(), BorderLayout.EAST);
		}

		return numberPanel;
	}

	private JPanel getBasicArrowPanel() {
		if (basicArrowPanel == null) {
			basicArrowPanel = new JPanel(new GridLayout(2, 1));
			basicArrowPanel.add(getUpBasicArrowButton());
			basicArrowPanel.add(getDownBasicArrowButton());
		}

		return basicArrowPanel;
	}

	private BasicArrowButton getDownBasicArrowButton() {
		if (downBasicArrowButton == null) {
			downBasicArrowButton = new BasicArrowButton(BasicArrowButton.SOUTH);
			downBasicArrowButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					//当前监听器监听的是下箭头，所以向控制器汇报字体大小修改值为-1
					Vars.paintControllor.addFontSize(-1);
				}
			});

		}

		return downBasicArrowButton;
	}

	private BasicArrowButton getUpBasicArrowButton() {
		if (upBasicArrowButton == null) {
			upBasicArrowButton = new BasicArrowButton(BasicArrowButton.NORTH);
			upBasicArrowButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					//当前监听器监听的是上箭头，所以向控制器汇报字体大小修改值为1
					Vars.paintControllor.addFontSize(1);
				}
			});

		}

		return upBasicArrowButton;
	}

	private JTextField getNumberTextField() {
		if (fontSizeTextField == null) {
			fontSizeTextField = new JTextField("10");

		}

		return fontSizeTextField;
	}

	public JPanel getRightUpPanel() {
		if (rightUpPanel == null) {
			rightUpPanel = new JPanel(new BorderLayout());
			rightUpPanel.add(new JLabel("Text"), BorderLayout.WEST);
			rightUpPanel.add(getStringTextField());
		}
		return rightUpPanel;
	}

	private JTextField getStringTextField() {
		if (stringTextField == null) {
			stringTextField = new JTextField("hello");
			stringTextField.addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent e) {
					// 文字域只要有按键按下，马上向控制器汇报当前的文字内容
					Vars.paintControllor.setText(stringTextField.getText()
							+ e.getKeyChar());
				}
			});
		}

		return stringTextField;
	}

	/**
	 * 构造函数，设置当前面板为2x2的布局，并分别添加四个面板
	 */
	public ToolPanel() {
		setLayout(new GridLayout(2, 2));
		add(getLeftUpPanel());
		add(getRightUpPanel());
		add(getLeftDownPanel());
		add(getRightDownPanel());
	}
	/**
	 * 在字体大小文本域显示当前字体大小。
	 * 本函数被控制器调用，只要控制器被通知字体大小被修改，就调用本函数
	 */
	public void refreshFontSize() {
		fontSizeTextField.setText(Vars.paintControllor.getFontSize() + "");
	}

}
