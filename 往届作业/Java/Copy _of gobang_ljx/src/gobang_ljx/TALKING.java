package gobang_ljx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TALKING extends JPanel{
	private JPanel panel1=new JPanel();
	private JPanel panel2=new JPanel();
	private JPanel panel3=new JPanel();
	private JPanel panel4=new JPanel();
	private JTextArea chat=new JTextArea("欢迎您加入游戏！"+"\n"+"祝您玩的愉快>_<");
	private JTextArea message=new JTextArea();
	private JLabel tip=new JLabel("  请输入要发送的信息：");
	private JButton send=new JButton("发送");
	private JScrollPane chatting=new JScrollPane(chat);
	
	private TALKING(){
		setLayout(new BorderLayout());
		
		chat.setLineWrap(true);
		message.setLineWrap(true);
		chatting.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		chatting.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		chat.setFont(new Font("黑体",Font.BOLD,30));
		message.setFont(new Font("黑体",Font.BOLD,30));
		tip.setFont(new Font("黑体",Font.BOLD,30));
		send.setFont(new Font("黑体",Font.BOLD,30));

		chat.setPreferredSize(new Dimension(600,1600));
		message.setPreferredSize(new Dimension(480,70));
		tip.setPreferredSize(new Dimension(550,70));
		send.setPreferredSize(new Dimension(100,100));
		panel4.setPreferredSize(new Dimension(200,70));

		send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String text=message.getText();
				chat.setText(chat.getText()+"\n"+"自己："+"\n"+text);
				message.setText(null);
				NetHelper.getinstance().sendChat(text);

			}
		});
		
		panel1.setLayout(new BorderLayout());
		panel2.setLayout(new FlowLayout());
		panel2.add(message);
		panel2.add(send);
		panel1.add(tip,BorderLayout.NORTH);
		panel1.add(panel2,BorderLayout.CENTER);
		panel1.add(panel4,BorderLayout.SOUTH);
		panel3.add(chatting);

		panel3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		add(chatting,BorderLayout.CENTER);
		add(panel1,BorderLayout.SOUTH);
		
	};
	private static TALKING instance=new TALKING ();
	public static TALKING getinstance(){
		return instance;
	}
	
	public void addChat(String message){
		chat.setText(chat.getText()+"\n"+"对方："+"\n"+message);
	}

}
