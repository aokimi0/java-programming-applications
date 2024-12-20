package painter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicArrowButton;


public class North {
	private North(){}
	private static North instance;
	public static North getinstance(){
		if(instance==null){
			instance=new North();
		}
		return instance;
	}
	private JPanel leftUpPanel=null;
	private JPanel leftDownPanel=null;
	private JPanel rightUpPanel=null;
	private JPanel rightDownPanel=null;
	private JPanel basicArrowPanel=null;
	private JPanel panel=null;
	
	private JComboBox<Color> backgroundColorComBox=null;//background的颜色选择框
	private JComboBox<Color> shapeColorComBox=null;//shapecolor的颜色选择框
	
	private Color[] backgroundColors={Color.WHITE,Color.YELLOW,Color.RED};
	private Color[] ShapeColors={Color.BLACK,Color.YELLOW,Color.RED};
	
	private JCheckBox fillRegionCheckBox; //是否填充（勾选按钮）
	
	public JCheckBox getFillRegionCheckBox() {
		return fillRegionCheckBox;
	}
	private BasicArrowButton downBasicArrowButton;//向下选择
	private BasicArrowButton upBasicArrowButton;//向上选择
	private JLabel lbbg;
	private JLabel lbsc;
	private JLabel lbtext;
	private JLabel lbsize;
	private static JTextField numberfield;
	private static JTextField textfield;
	
	paintpanel pp=paintpanel.getinstance();
	
	private Component getBackgroundColorComboBox() {
		if(backgroundColorComBox==null){
			backgroundColorComBox=new JComboBox<Color>(backgroundColors);
			backgroundColorComBox.addItemListener(new ItemListener(){
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange()==ItemEvent.SELECTED){//查看是否为新选中的选项触发的
						Draw.getinstance().setBackgroundColor((Color)backgroundColorComBox.getSelectedItem());
					}
				}
			});
		}
		return backgroundColorComBox;
	}
	public JPanel getLeftDownPanel(){
		if(leftDownPanel==null){
			leftDownPanel=new JPanel();
			leftDownPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
			lbbg=new JLabel("BackGround");
			lbbg.setFont(new Font("黑体",Font.BOLD,20));
			leftDownPanel.add(lbbg);
			leftDownPanel.add(getBackgroundColorComboBox());
		}
		return leftDownPanel;
	}
	
	private Component getShapeColorComboBox() {
		if(shapeColorComBox==null){
			shapeColorComBox=new JComboBox<Color>(ShapeColors);
			shapeColorComBox.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange()==ItemEvent.SELECTED){
						Draw.getinstance().setShapeColor((Color)shapeColorComBox.getSelectedItem());
					}
					
				}
			});
		}
		return shapeColorComBox;
	}
	public JPanel getLeftUpPanel(){
		if(leftUpPanel==null){
			leftUpPanel=new JPanel();
			leftUpPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			lbsc=new JLabel("Shape Color");
			lbsc.setFont(new Font("黑体",Font.BOLD,20));
			leftUpPanel.add(lbsc);
			
			leftUpPanel.add(getShapeColorComboBox());
		}
		return leftUpPanel;
	}
	
	
	
	public static String gettext(){
		return textfield.getText();
	}
	public static String getsize1(){
		return numberfield.getText();
	}
	public JPanel getRightUpPanel(){
		if(rightUpPanel==null){
			rightUpPanel=new JPanel();
			rightUpPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			
			lbtext=new JLabel("Text");
			lbtext.setFont(new Font("黑体",Font.BOLD,20));
			rightUpPanel.add(lbtext);
			
			textfield=new JTextField();
			textfield.setFont(new Font("黑体",Font.BOLD,20));
			textfield.setPreferredSize(new Dimension(300,35));
			rightUpPanel.add(textfield);
		}
		return rightUpPanel;
	}
	
	
	public JLabel getsize(){
		lbsize=new JLabel("Size");
		lbsize.setFont(new Font("黑体",Font.BOLD,20));
		return lbsize;
	}
	public JTextField getnumberfield(){
		numberfield =new JTextField();
		numberfield.setPreferredSize(new Dimension(100,30));
		numberfield.setFont(new Font("黑体",Font.BOLD,20));
		return numberfield;
	}
	public JPanel getRightDownPanel(){
		if(rightDownPanel==null){
			rightDownPanel=new JPanel();
			rightDownPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			rightDownPanel.add(getsize());
			rightDownPanel.add(getnumberfield());
			
			upBasicArrowButton=new BasicArrowButton(BasicArrowButton.NORTH);
			downBasicArrowButton=new BasicArrowButton(BasicArrowButton.SOUTH);
			upBasicArrowButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					int sizetext=Integer.parseInt(getsize1())+1;
					numberfield.setText(String.valueOf(sizetext));
					Draw.list.get(Draw.list.size()-1).sizetext=Integer.parseInt(getsize1());
					pp.repaint();
				}
				
			});
			downBasicArrowButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					int sizetext=Integer.parseInt(getsize1())-1;
					numberfield.setText(String.valueOf(sizetext));
					Draw.list.get(Draw.list.size()-1).sizetext=Integer.parseInt(getsize1());
					pp.repaint();
				}
				
			});
			basicArrowPanel=new JPanel();
			basicArrowPanel.setLayout(new GridLayout(2,1));
			basicArrowPanel.add(upBasicArrowButton);
			basicArrowPanel.add(downBasicArrowButton);
			rightDownPanel.add(basicArrowPanel);
			
			fillRegionCheckBox=new JCheckBox();
			fillRegionCheckBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent e) {
					Draw.getinstance().setFillRegion(fillRegionCheckBox.isSelected());
				}
			});
			rightDownPanel.add(fillRegionCheckBox);
			JLabel lb=new JLabel("Fill the region");
			lb.setFont(new Font("黑体",Font.BOLD,20));
			rightDownPanel.add(lb);
		}
		return rightDownPanel;
	}
	
	
	public JPanel getpanel(){
		if(panel==null){
			panel=new JPanel();
			panel.setLayout(new GridLayout(2,2,10,10));
			panel.add(getLeftUpPanel());
			panel.add(getRightUpPanel());
			panel.add(getLeftDownPanel());
			panel.add(getRightDownPanel());
			panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			panel.setBorder(BorderFactory.createLoweredBevelBorder());
		}
		return panel;
	}
}
