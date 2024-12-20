package painter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class South {
	private South(){}
	private static South instance;
	public static South getinstance(){
		if(instance==null){
			instance=new South();
		}
		return instance;
	}
	JLabel x;
	JLabel y;
	JLabel x1;
	JLabel x2;
	JLabel y1;
	JLabel y2;
	JPanel panel=null;
	JTextField x_0;
	JTextField y_0;
	JTextField x_1;
	JTextField x_2;
	JTextField y_1;
	JTextField y_2;
	public JPanel getpanel(){
		if(panel==null){
			x=new JLabel("x=");
			x.setFont(new Font("黑体",Font.BOLD,20));
			
			y=new JLabel("y=");
			y.setFont(new Font("黑体",Font.BOLD,20));
			
			x1=new JLabel("Released   x1=");
			x1.setFont(new Font("黑体",Font.BOLD,20));
			
			x2=new JLabel("x2=");
			x2.setFont(new Font("黑体",Font.BOLD,20));
			
			y1=new JLabel("y1=");
			y1.setFont(new Font("黑体",Font.BOLD,20));
			
			y2=new JLabel("y2=");
			y2.setFont(new Font("黑体",Font.BOLD,20));
			
			x_0=new JTextField();
			x_0.setOpaque(false);
			x_0.setPreferredSize(new Dimension(50,20));
			
			y_0=new JTextField();
			y_0.setOpaque(false);
			y_0.setPreferredSize(new Dimension(50,20));
			
			x_1=new JTextField();
			x_1.setOpaque(false);
			x_1.setPreferredSize(new Dimension(50,20));
			
			y_1=new JTextField();
			y_1.setOpaque(false);
			y_1.setPreferredSize(new Dimension(50,20));
			
			x_2=new JTextField();
			x_2.setOpaque(false);
			x_2.setPreferredSize(new Dimension(50,20));
			
			y_2=new JTextField();
			y_2.setOpaque(false);
			y_2.setPreferredSize(new Dimension(50,20));
			
			panel=new JPanel();
			panel.setLayout(new FlowLayout());
			panel.add(x1);
			panel.add(x_1);
			panel.add(y1);
			panel.add(y_1);
			panel.add(x2);
			panel.add(x_2);
			panel.add(y2);
			panel.add(y_2);
			panel.add(x);
			panel.add(x_0);
			panel.add(y);
			panel.add(y_0);
			
			panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			panel.setBorder(BorderFactory.createLoweredBevelBorder());
			
		}
		return panel;
	}
}
