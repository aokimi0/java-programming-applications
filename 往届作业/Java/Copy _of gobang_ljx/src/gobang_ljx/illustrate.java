package gobang_ljx;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class illustrate extends JFrame{
	ImageIcon illustrate=new ImageIcon("illustrate.jpg");
	JLabel label=new JLabel(illustrate);

	private illustrate(){
		setBounds(200, 200, illustrate.getIconWidth()+40, illustrate.getIconHeight()+70);
		getContentPane().add(label);
	}
	private static illustrate instance=new illustrate();
	public static illustrate getinstance(){
		return instance;
	}
	
}
