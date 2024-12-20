package painter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class View {
	paintpanel pp=paintpanel.getinstance();
	private static View instance;
	public static View getinstance(){
		if(instance==null){
			instance=new View();
		}
		return instance;
	}
	public static void main(String args[]){
		getinstance().init();
	}
	private void init() {

		JMenuBar menubar=new JMenuBar();
		JMenu menuFile=new JMenu("File");
		JMenu menuEdit=new JMenu("Edit");
		JMenu menuHelp=new JMenu("Help");
		JMenuItem menuitemstart=new JMenuItem("start");
		JMenuItem menuitemback=new JMenuItem("Back");
		JMenuItem menuitemempty=new JMenuItem("Empty");
		
		
		
		menubar.setPreferredSize(new Dimension(100,30));
		menuFile.setFont(new Font("黑体",Font.BOLD,15));
		menuEdit.setFont(new Font("黑体",Font.BOLD,15));
		menuHelp.setFont(new Font("黑体",Font.BOLD,15));
		menuitemstart.setFont(new Font("黑体",Font.BOLD,15));
		menuitemback.setFont(new Font("黑体",Font.BOLD,15));
		menuitemempty.setFont(new Font("黑体",Font.BOLD,15));
		menubar.add(menuFile);
		menubar.add(menuEdit);
		menubar.add(menuHelp);
		menuFile.add(menuitemstart);
		menuEdit.add(menuitemback);
		menuEdit.add(menuitemempty);
		menuitemback.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Draw.list.remove(Draw.list.size()-1);
				pp.repaint();
				
			}
		});
		menuitemempty.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int length=Draw.list.size();
				for(int i=0;i<length;i++){
					Draw.list.remove(Draw.list.size()-1);
				}
				pp.repaint();
			}
		});
		JFrame frame=new JFrame("Java Painter");
		frame.setJMenuBar(menubar);
		frame.getContentPane().setLayout(new BorderLayout());
		
		North north=North.getinstance();
		JPanel panelnorth=north.getpanel();
		frame.add(panelnorth,BorderLayout.NORTH);
		
		West west=new West();
		JPanel panelwest=west.getpanel();
		frame.add(panelwest,BorderLayout.WEST);
		
		Draw d1=new Draw();
		paintpanel sp=paintpanel.getinstance();
		sp.setBackground(Color.WHITE);
		sp.addMouseListener(d1);
		sp.addMouseMotionListener(d1);
		sp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		frame.add(sp,BorderLayout.CENTER);
		
		South south=South.getinstance();
		JPanel panelsouth=south.getpanel();
		frame.add(panelsouth,BorderLayout.SOUTH);
		
		frame.setVisible(true);
		frame.setSize(800, 700);

	}

}
