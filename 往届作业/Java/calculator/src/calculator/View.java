package calculator;
import calculator.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class View {
	private static View instance;
	public static View getinstance(){
		if(instance==null){
			instance=new View();
		}
		return instance;
	}
	
	JTextField textfield=new JTextField();

	public void showNumber(String num){
		textfield.setText(num);
	}
	public static void main(String args[]){
		getinstance().begin();
	}

	private void begin() {
		JFrame frame=new JFrame("");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(textfield,BorderLayout.NORTH);
		textfield.setPreferredSize(new Dimension(600,200));
		textfield.setFont(new Font("���Ĳ���",Font.BOLD, 70));
		textfield.setHorizontalAlignment(SwingConstants.RIGHT);
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(6,4));
		ActionListener actionlistener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String command=e.getActionCommand();
				Control.getIntance().pressBtn(command);
			}
		};
		JButton btn1=new JButton("1");
		btn1.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btn1.setActionCommand("1");
		btn1.addActionListener(actionlistener);
		
		JButton btnCE=new JButton("CE");
		btnCE.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btnCE.setActionCommand("CE");
		btnCE.addActionListener(actionlistener);
		
		JButton btnC=new JButton("C");
		btnC.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btnC.setActionCommand("C");
		btnC.addActionListener(actionlistener);
		
		JButton btndel=new JButton("��");
		btndel.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btndel.setActionCommand("��");
		btndel.addActionListener(actionlistener);
		
		JButton btndot=new JButton(".");
		btndot.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btndot.setActionCommand(".");
		btndot.addActionListener(actionlistener);
		
		JButton btnplus=new JButton("*");
		btnplus.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btnplus.setActionCommand("*");
		btnplus.addActionListener(actionlistener);
		
		JButton btn2=new JButton("2");
		btn2.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btn2.setActionCommand("2");
		btn2.addActionListener(actionlistener);
		
		JButton btn3=new JButton("3");
		btn3.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btn3.setActionCommand("3");
		btn3.addActionListener(actionlistener);
		
		JButton btn4=new JButton("4");
		btn4.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btn4.setActionCommand("4");
		btn4.addActionListener(actionlistener);
		
		JButton btn5=new JButton("5");
		btn5.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btn5.setActionCommand("5");
		btn5.addActionListener(actionlistener);
		
		JButton btn6=new JButton("6");
		btn6.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btn6.setActionCommand("6");
		btn6.addActionListener(actionlistener);
		
		JButton btn7=new JButton("7");
		btn7.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btn7.setActionCommand("7");
		btn7.addActionListener(actionlistener);
		
		JButton btn8=new JButton("8");
		btn8.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btn8.setActionCommand("8");
		btn8.addActionListener(actionlistener);
		
		JButton btn9=new JButton("9");
		btn9.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btn9.setActionCommand("9");
		btn9.addActionListener(actionlistener);
		
		JButton btndiv=new JButton("��");
		btndiv.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btndiv.setActionCommand("��");
		btndiv.addActionListener(actionlistener);
		
		JButton btnadd=new JButton("+");
		btnadd.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btnadd.setActionCommand("+");
		btnadd.addActionListener(actionlistener);
		
		JButton btnsub=new JButton("-");
		btnsub.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btnsub.setActionCommand("-");
		btnsub.addActionListener(actionlistener);
		
		JButton btnequ=new JButton("=");
		btnequ.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btnequ.setActionCommand("=");
		btnequ.addActionListener(actionlistener);
		
		JButton btn0=new JButton("0");
		btn0.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btn0.setActionCommand("0");
		btn0.addActionListener(actionlistener);
		
		JButton btnchange=new JButton("+/-");
		btnchange.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btnchange.setActionCommand("+/-");
		btnchange.addActionListener(actionlistener);
		
		JButton btnmold=new JButton("%");
		btnmold.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btnmold.setActionCommand("%");
		btnmold.addActionListener(actionlistener);
		
		JButton btntwi=new JButton("x2");
		btntwi.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btntwi.setActionCommand("x2");
		btntwi.addActionListener(actionlistener);
		
		JButton btnhalf=new JButton("x1/2");
		btnhalf.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btnhalf.setActionCommand("x1/2");
		btnhalf.addActionListener(actionlistener);
		
		JButton btnone=new JButton("1/x");
		btnone.setFont(new Font("���Ĳ���",Font.BOLD, 50));
		btnone.setActionCommand("1/x");
		btnone.addActionListener(actionlistener);
		
		panel.add(btnmold);
		panel.add(btnCE);
		panel.add(btnC);
		panel.add(btndel);
		panel.add(btnone);
		panel.add(btntwi);
		panel.add(btnhalf);
		panel.add(btndiv);
		panel.add(btn7);
		panel.add(btn8);
		panel.add(btn9);
		panel.add(btnplus);
		panel.add(btn4);
		panel.add(btn5);
		panel.add(btn6);
		panel.add(btnsub);
		panel.add(btn1);
		panel.add(btn2);
		panel.add(btn3);
		panel.add(btnadd);
		panel.add(btnchange);
		panel.add(btn0);
		panel.add(btndot);
		panel.add(btnequ);
		
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setSize(650, 800);
		frame.setVisible(true);
	}
	public void showOperator(String command) {
		textfield.setText(command);
		
	}
}
