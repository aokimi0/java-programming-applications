package gobang_unfinished;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class chessView {
	private static chessView instance;
	public static chessView getinstance(){
		if(instance==null)
			instance=new chessView();
		return instance;
	}
	
	JFrame jframe=new JFrame();
	JLabel jlabel=new JLabel();
	JLabel steps=new JLabel("当前已走步数：",SwingConstants.LEFT);
	JLabel stepsnum=new JLabel();
	JLabel time=new JLabel("当前总共用时：",SwingConstants.LEFT);
	JLabel timepass=new JLabel();
	JPanel jpstep=new JPanel();
	JPanel jptime=new JPanel();
	
	JLabel stepsapace1=new JLabel();
	JLabel stepsapace2=new JLabel();
	JLabel stepsapace3=new JLabel();
	JLabel stepsapace4=new JLabel();
	JLabel stepsapace5=new JLabel();
	JLabel stepsapace6=new JLabel();
	
	public void getview(){
		jframe.setBounds(500, 350, 1500, 1000);
		jframe.getContentPane().setLayout(new BorderLayout());
		if(chessController.getinstance().getnetMode()==true)
			jframe.getContentPane().add(NetPanel.getinstance(),BorderLayout.NORTH);
		jlabel.setPreferredSize(new Dimension(500,1000));
		jlabel.setLayout(new GridLayout(12,3));
	
		steps.setFont(new Font("黑体",Font.BOLD,35));

		stepsnum.setPreferredSize(new Dimension(50,100));
		stepsnum.setOpaque(false);
		stepsnum.setFont(new Font("黑体",Font.BOLD,35));

		time.setFont(new Font("黑体",Font.BOLD,35));
		
		timepass.setPreferredSize(new Dimension(50,100));
		timepass.setOpaque(false);
		timepass.setFont(new Font("黑体",Font.BOLD,35));
		
		jpstep.add(steps);
		jpstep.add(stepsnum);
		jptime.add(time);
		jptime.add(timepass);
		jpstep.setBorder(BorderFactory.createLineBorder(Color.RED));
		jptime.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		jlabel.add(stepsapace1);
		jlabel.add(stepsapace2);
		jlabel.add(stepsapace3);
		jlabel.add(stepsapace4);
		jlabel.add(stepsapace5);

		jlabel.add(jpstep);
		
		jlabel.add(stepsapace6);
		jlabel.add(jptime);
		 
		jframe.getContentPane().add(jlabel,BorderLayout.WEST);
		jframe.getContentPane().add(checkerboard.getinstance(), BorderLayout.CENTER);
		jframe.getContentPane().add(South.getinstance().getpanel(),BorderLayout.SOUTH);
		
		jframe.setVisible(true);
		
		jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
	}
	
	public void getframe(int width){
		
	}
}
