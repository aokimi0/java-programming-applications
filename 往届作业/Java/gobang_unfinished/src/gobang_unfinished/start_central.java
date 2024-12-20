package gobang_unfinished;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
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
				choosepart.getinstance().getframe();
				start.frame.dispose();
			}
		});
		
		b2.setPreferredSize(new Dimension(200,100));
		b2.setText("联网挑战");
		b2.setFont(new Font("黑体",Font.BOLD,20));
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				chooseMode.getinstance().setVisible(true);;
				start.frame.dispose();
				chessController.getinstance().setNetMode(true);
			}
		});
		
		b3.setPreferredSize(new Dimension(200,100));
		b3.setText("游戏说明");
		b3.setFont(new Font("黑体",Font.BOLD,20));
		b3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				illustrate.getinstance().getframe();
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
}
