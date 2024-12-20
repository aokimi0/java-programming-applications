package painter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class West {
	private JPanel panel=null;
	public JPanel getpanel(){
		if(panel==null){
			panel=new JPanel();
			//panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			panel.setLayout(new GridLayout(7,1));
			getButton();
			panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
			panel.setPreferredSize(new Dimension(70,150));
		}
		return panel;
	}
	
	String []index={"圆形","椭圆","矩形","直线","文字"};
	public void getButton(){
		for(int i=0;i<5;i++){
			JButton button=new JButton(index[i]);
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String command=e.getActionCommand();
					paintpanel.getinstance().setcurrentShape(command);
					//响应成功
				}
			});
			button.setBorder(BorderFactory.createEtchedBorder());
			panel.add(button);
		}
	}
}
