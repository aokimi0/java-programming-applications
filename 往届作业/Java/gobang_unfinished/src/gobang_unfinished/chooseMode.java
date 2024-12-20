package gobang_unfinished;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class chooseMode extends JFrame{
	private JLabel tell=new JLabel("请选择你需要的模式：");
	ButtonGroup b=new ButtonGroup();
	private JRadioButton server=new JRadioButton("建立一个棋局");
	private JRadioButton client=new JRadioButton("参加别人的棋局");
	private JButton sure=new JButton("确定");
	private JPanel center=new JPanel();
	private JPanel up=new JPanel();
	private JPanel down=new JPanel();

	private chooseMode(){
		getContentPane().setLayout(new BorderLayout());
		setBounds(500, 500, 800, 550);
		
		b.add(server);
		b.add(client);
		
		server.setPreferredSize(new Dimension(250,100));
		client.setPreferredSize(new Dimension(250,100));
		tell.setPreferredSize(new Dimension(350,130));
		sure.setPreferredSize(new Dimension(150,80));
		
		server.setFont(new Font("黑体",Font.BOLD,30));
		client.setFont(new Font("黑体",Font.BOLD,30));
		tell.setFont(new Font("黑体",Font.BOLD,30));
		sure.setFont(new Font("黑体",Font.BOLD,30));
		
		up.add(tell);
		center.add(server);
		center.add(client);
		down.add(sure);
		
		center.setPreferredSize(new Dimension(500,100));
		
		getContentPane().add(up,BorderLayout.NORTH);
		getContentPane().add(center,BorderLayout.CENTER);
		getContentPane().add(down,BorderLayout.SOUTH);
		
		sure.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(server.isSelected()){
					buildover.getinstance().getframe();
					getinstance().dispose();
				}else if(client.isSelected()){
					choose_ipport.getinstance().setVisible(true);
					getinstance().dispose();
				}
				
			}
			
		});
		
	};
	private static chooseMode instance;
	public static chooseMode getinstance(){
		if(instance==null)
			instance=new chooseMode();
		return instance;
	}
}
