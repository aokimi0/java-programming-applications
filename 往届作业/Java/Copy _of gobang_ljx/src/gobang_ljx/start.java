package gobang_ljx;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class start {
	private static start instance;
	public static start getinstance(){
		if(instance==null)
			instance=new start();
		return instance;
	}
	public static JFrame frame=new JFrame();
	
	public static void main(String[] args) {
		start.begin();
	}

	private static void begin() {
		frame.getContentPane().setLayout(new BorderLayout());
		start_central sc=start_central.getinstance();
		frame.add(sc.setPanel(),BorderLayout.CENTER);
		frame.pack();
		frame.setBounds(600, 300, 1100, 750);
		frame.setVisible(true);
	}

}
