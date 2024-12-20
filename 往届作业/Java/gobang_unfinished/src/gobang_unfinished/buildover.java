package gobang_unfinished;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class buildover {
	private static buildover instance;
	public static buildover getinstance(){
		if(instance==null)
			instance=new buildover();
		return instance;
	}
	JFrame jframe =new JFrame();
	private JTextArea textarea=new JTextArea();
	private JButton goon=new JButton("继续");
	private JPanel panel1=new JPanel();
	private JPanel panel2=new JPanel();
	
	public JPanel gettextarea(){
		textarea.setEditable(false);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setText("恭喜你已成功建立服务器端！"+'\n'+"您的IP地址为"+"\n"+"您的端口号为8090！");
		textarea.setOpaque(false);
		textarea.setFont(new Font("黑体",Font.BOLD,40));
		
		goon.setFont(new Font("黑体",Font.BOLD,40));
		goon.setPreferredSize(new Dimension(250,100));
		
		panel1.setLayout(new GridLayout(2,1));
		panel1.add(textarea);

		panel2.add(goon);
		panel1.add(panel2);
		
		goon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				buildover.getinstance().jframe.dispose();
				choosepart.getinstance().getframe().setVisible(true);
				
			}
		});
		
		return panel1;
	}
	
	public void getframe(){
		jframe.setBounds(1000, 200,750 , 900);
		jframe.getContentPane().add(gettextarea());
		jframe.setBackground(Color.WHITE);

		
		jframe.setVisible(true);
	}
}
