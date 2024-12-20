package gobang_unfinished;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class illustrate {
	private static illustrate instance;
	public static illustrate getinstance(){
		if(instance==null)
			instance=new illustrate();
		return instance;
	}
	JFrame jframe =new JFrame();
	private JTextArea textarea=new JTextArea();
	
	public JTextArea gettextarea(){
		textarea.setEditable(false);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setText("该游戏是由wjwjwjwjwjwjwj开发的小游戏，就这么玩着吧");
		textarea.setFont(new Font("黑体",Font.BOLD,50));
		return textarea;
	}
	
	public void getframe(){
		jframe.setBounds(1000, 200,750 , 900);
		jframe.add(gettextarea());
		jframe.setVisible(true);
	}
}
