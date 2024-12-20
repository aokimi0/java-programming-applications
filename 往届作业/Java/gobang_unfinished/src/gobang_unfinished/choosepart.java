package gobang_unfinished;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class choosepart {
	private static choosepart instance;
	public static choosepart getinstance(){
		if(instance==null)
			instance=new choosepart();
		return instance;
	}
	
	JFrame jframe=new JFrame();
	JLabel jlabelcolor=new JLabel("请选择你方棋子的颜色：",SwingConstants.LEFT);
	JLabel jlabelnum=new JLabel("请选择行列数：",SwingConstants.LEFT);
	JLabel jlabelattention=new JLabel("行列数最好选择8~25中间噢 >-<",SwingConstants.CENTER);
	ButtonGroup g=new ButtonGroup();
	JRadioButton boxblack=new JRadioButton("BLACK",false);
	JRadioButton boxwhite=new JRadioButton("WHITE",false);
	JTextField labelnum=new JTextField();
	JButton buttonback=new JButton("返回");
	JButton buttoncontinue=new JButton("继续");
	JPanel color=new JPanel();
	JPanel num=new JPanel();
	JPanel jpanel=new JPanel();
	JPanel jp=new JPanel();
	
	JPanel panel1=new JPanel();
	JPanel panel2=new JPanel();
	JPanel panel3=new JPanel();
	JPanel panel4=new JPanel();
	
	public int getlabelnum(){
		String num=labelnum.getText();
		int x=Integer.parseInt(num);
		return x;
	}
	
	public void setWIDTH(){
		chessModel.WIDTH=getlabelnum();
	}
	
	public void removeframe(){
		g.clearSelection();
		labelnum.setText(null);
	}
	
	public JFrame getframe(){
		jlabelcolor.setFont(new Font("黑体",Font.BOLD,40));
		jlabelnum.setFont(new Font("黑体",Font.BOLD,40));
		jlabelattention.setFont(new Font("黑体",Font.BOLD,25));
		labelnum.setFont(new Font("黑体",Font.BOLD,20));
		buttonback.setFont(new Font("黑体",Font.BOLD,20));
		buttoncontinue.setFont(new Font("黑体",Font.BOLD,20));
		
		jlabelcolor.setPreferredSize(new Dimension(500,35));
		jlabelnum.setPreferredSize(new Dimension(300,35));
		jlabelattention.setPreferredSize(new Dimension(200,35));
		labelnum.setPreferredSize(new Dimension(120,50));
		buttonback.setPreferredSize(new Dimension(120,50));
		buttoncontinue.setPreferredSize(new Dimension(120,50));
		
		buttonback.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				start.frame.setVisible(true);
				getframe().dispose();
				
			}
		});
		buttoncontinue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(labelnum.getText().length()==0){
					JOptionPane.showMessageDialog(null, "请输入你所需要的行列数！");
				}
				else if(getlabelnum()<8){
					JOptionPane.showMessageDialog(null, "输入的行列数太少咯~");
				}
				else if(getlabelnum()>25){
					JOptionPane.showMessageDialog(null, "输入的行列数太多咯~");
				}
				else if(g.isSelected(null)){
					JOptionPane.showMessageDialog(null, "请选择一个棋子颜色");
				}
				else{
					setWIDTH();
					chessModel.getinstance().removedata();
					chessView.getinstance().getview();
					jframe.setVisible(false);
				}

			}
		});
		
		ActionListener a=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String str=((JRadioButton)e.getSource()).getText();
				if(str=="BLACK")
					chessController.getinstance().setlocalColor(chessModel.BLACK);
				else if(str=="WHITE")
					chessController.getinstance().setlocalColor(chessModel.WHITE);
				
			}
		};
		boxblack.addActionListener(a);
		
		boxwhite.addActionListener(a);
		
		color.add(jlabelcolor);
		g.add(boxblack);
		g.add(boxwhite);
		color.add(boxblack);
		color.add(boxwhite);
		
		num.add(jlabelnum);
		num.add(labelnum);
		labelnum.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		
		jpanel.setLayout(new GridLayout(8,1));
		
		jpanel.add(panel1);
		jpanel.add(panel2);
		jpanel.add(num);
		
		jpanel.add(panel3);
		jpanel.add(jlabelattention);

		jpanel.add(panel4);
		jpanel.add(color);
		jpanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		jp.setLayout(new FlowLayout(FlowLayout.CENTER,50,25));
		jp.add(buttonback);
		jp.add(buttoncontinue);
		jp.setPreferredSize(new Dimension(50,100));
		
		jframe.getContentPane().setLayout(new BorderLayout());
		jframe.add(jpanel,BorderLayout.CENTER);
		jframe.add(jp,BorderLayout.SOUTH);
		
		jframe.setBounds(500, 500, 800, 550);;
		jframe.setVisible(true);
		
		return jframe;
	}
}
