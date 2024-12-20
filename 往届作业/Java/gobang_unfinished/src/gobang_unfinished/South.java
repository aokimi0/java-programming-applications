package gobang_unfinished;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class South {
	private static South instance;
	public static South getinstance(){
		if(instance==null)
			instance=new South();
		return instance;
	}
	
	JPanel jpanel=new JPanel();
	JButton buttonlast=new JButton("撤销");
	JButton buttonagain=new JButton("重玩");
	JButton buttonback=new JButton("返回上一界面");
	
	public JPanel getpanel(){
		jpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
		jpanel.setPreferredSize(new Dimension(50,100));
		
		buttonlast.setPreferredSize(new Dimension(250,60));
		buttonagain.setPreferredSize(new Dimension(250,60));
		buttonback.setPreferredSize(new Dimension(250,60));
		
		buttonlast.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		buttonagain.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chessModel.getinstance().removedata();
				checkerboard.getinstance().repaint();
				chessController.getinstance().setlocalColor(choosepart.getinstance().boxblack.isSelected()?chessModel.BLACK:chessModel.WHITE);
			}
		});
		
		buttonback.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				choosepart.getinstance().removeframe();
				choosepart.getinstance().jframe.setVisible(true);
				chessView.getinstance().jframe.setVisible(false);
			}
		});
		
		jpanel.add(buttonlast);
		jpanel.add(buttonagain);
		jpanel.add(buttonback);
		
		return jpanel;
	}
}
