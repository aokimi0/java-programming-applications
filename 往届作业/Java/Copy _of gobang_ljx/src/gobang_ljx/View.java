package gobang_ljx;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class View extends JFrame{
	private JButton removechess=new JButton("悔棋");
	private JButton peacechess=new JButton("和棋");
	private JPanel panel=new JPanel();
	private JPanel panel1=new JPanel();
	private JPanel panel2=new JPanel();
	private JPanel panel3=new JPanel();
	private JTextArea label=new JTextArea("       请选择你想听的音乐："+"\n\n"+"       (请注意只能单选！)"+"\n");
	private ButtonGroup bg=new ButtonGroup();
	private JRadioButton br=new JRadioButton("Rise");
	private JRadioButton d=new JRadioButton("Drive");
	private JRadioButton go=new JRadioButton("How Far I'll Go");
	private JRadioButton stop=new JRadioButton("stop");
	
	private View(){
		removechess.setFont(new Font("黑体",Font.BOLD,30));
		peacechess.setFont(new Font("黑体",Font.BOLD,30));
		br.setFont(new Font("黑体",Font.BOLD,30));
		d.setFont(new Font("黑体",Font.BOLD,30));
		stop.setFont(new Font("黑体",Font.BOLD,30));
		go.setFont(new Font("黑体",Font.BOLD,30));
		label.setFont(new Font("黑体",Font.BOLD,30));
		bg.add(br);
		bg.add(d);
		bg.add(stop);
		bg.add(go);
		
		label.setEditable(false);
		label.setOpaque(false);
		removechess.setPreferredSize(new Dimension(200,60));
		peacechess.setPreferredSize(new Dimension(200,60));
		label.setPreferredSize(new Dimension(535,150));
		panel3.setPreferredSize(new Dimension(535,120));
		
		removechess.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String []array=Model.chess.get(Model.chess.size()-1).split(",");
				Controller.getinstance().localRemoveChess(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
			}
		});
		
		peacechess.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				NetHelper.getinstance().sendpeace();
			}
		});
		
		ActionListener a=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final String str=((JRadioButton)e.getSource()).getText();
				new Thread(){
					public void run(){
							if(str=="Rise"){
								Controller.flag=true;
								Controller.playMusic("Biscuits - Rise.wav");
							}
							else if(str=="Drive"){
								Controller.flag=true;
								Controller.playMusic("Drive.wav");
							}else if(str=="How Far I'll Go"){
								Controller.flag=true;
								Controller.playMusic("How Far I'll Go.wav");
							}
							else if(str=="stop"){
								Controller.flag=false;
							}	
					}
				}.start();
				
			}
		};
		
		d.addActionListener(a);
		br.addActionListener(a);
		stop.addActionListener(a);
		go.addActionListener(a);
		
		panel2.setLayout(new FlowLayout());
		panel2.add(panel3);
		panel2.add(label);
		panel2.add(br);
		panel2.add(d);
		panel2.add(go);
		panel2.add(stop);
		panel2.setPreferredSize(new Dimension(500,200));
		
		panel.setLayout(new FlowLayout());
		panel.add(removechess);
		panel.add(peacechess);
		panel.setPreferredSize(new Dimension(500,200));
		
		panel1.setLayout(new BorderLayout());
		panel1.add(ChessPanel.getinstance(),BorderLayout.CENTER);
		panel1.add(panel,BorderLayout.SOUTH);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel1,BorderLayout.CENTER);
		if(Controller.getinstance().getnetMode())
			getContentPane().add(TALKING.getinstance(),BorderLayout.EAST);
		getContentPane().add(panel2,BorderLayout.WEST);
		if(!Controller.getinstance().getnetMode())
			setSize(1700,1200);
		else if(Controller.getinstance().getnetMode())
			setSize(2100, 1200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private static View instance=new View();
	public static View getinstance(){
		return instance;
	}
	
	
}
