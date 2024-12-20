package gobang_ljx;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class start_central {
	private static start_central instance;
	public static start_central getinstance(){
		if(instance==null)
			instance=new start_central();
		return instance;
	}
	
	JButton b1=new JButton();
	JButton b2=new JButton();
	JButton b3=new JButton();
	JLabel jl=new JLabel("五子棋",SwingConstants.CENTER);
	private int pick;
	
	public int getpick(){
		return pick;
	}
	
	public JPanel setPanel(){
		JPanel jf=new JPanel();
		JPanel jp=new JPanel();
		jf.setLayout(new FlowLayout(FlowLayout.CENTER,50,50));
		
		b1.setText("双人对战");
		b1.setFont(new Font("黑体",Font.BOLD,20));
		b1.setPreferredSize(new Dimension(200,100));
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Controller.getinstance().setNetMode(false);
				
				choose_ColorWidth();
				start.frame.dispose();
			}
		});
		
		b2.setPreferredSize(new Dimension(200,100));
		b2.setText("联网挑战");
		b2.setFont(new Font("黑体",Font.BOLD,20));
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Controller.getinstance().setNetMode(true);
				String [] options = {"创建一局游戏","加入别人房间"};
				int pick=JOptionPane.showOptionDialog(null,"请选择你的选项：","提示",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
				if(pick==0){
					choose_ColorWidth();
					NetHelper.getinstance().beginListen();
				}else if(pick==1){
					String ip=JOptionPane.showInputDialog(null,"请输入对方IP地址：（不输入则默认为本地地址）",null,JOptionPane.QUESTION_MESSAGE);
					if(ip==null){
						ip="localhost";
					}
					NetHelper.getinstance().connect(ip);
					View.getinstance().setVisible(true);
				}
				start.frame.dispose();
			}
		});
		
		b3.setPreferredSize(new Dimension(200,100));
		b3.setText("游戏说明");
		b3.setFont(new Font("黑体",Font.BOLD,20));
		b3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				illustrate.getinstance().setVisible(true);
				start.frame.dispose();
				
			}
		});
		
		jl.setPreferredSize(new Dimension (1100,350));
		jl.setFont(new Font("黑体",Font.BOLD|Font.ITALIC|Font.PLAIN,150));
		
		jf.add(jl);
		jf.add(b1);
		jf.add(b2);
		jf.add(b3);
		
		return jf;
	}

	private void choose_ColorWidth() {
		String width=JOptionPane.showInputDialog(null,"请输入你想选择的五子棋的行列数",null,JOptionPane.QUESTION_MESSAGE);
		if(width==null)
			return;
		Model.getinstance().setWIDTH(Integer.parseInt(width));
		if(Integer.parseInt(width)<9||Integer.parseInt(width)>25)
			JOptionPane.showMessageDialog(null, "输入的行列数需在9~25中间","提示",JOptionPane.WARNING_MESSAGE);
		else{	
			String [] options = {"黑棋","白棋"};
			int pick=JOptionPane.showOptionDialog(null,"请选择先手棋子：","提示",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
			if(pick==0){
				Controller.getinstance().setlocalColor(Model.BLACK);
				Controller.getinstance().setotherColor(Model.WHITE);
			}else if(pick==1){
				Controller.getinstance().setlocalColor(Model.WHITE);
				Controller.getinstance().setotherColor(Model.BLACK);
			}
			View.getinstance().setVisible(true);
		}
	}
}
