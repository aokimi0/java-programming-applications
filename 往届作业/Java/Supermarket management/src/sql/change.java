package sql;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class change implements ActionListener{
	
	private int choice=1;
	boolean time=false;
	private String[] content=new String[8];
	public change(){};
	public change(int choice1){
		choice=choice1;
	}
	public void setcontent(String[] temp){
		content=temp.clone();
	}
	private String getcontent(int i){
		return content[i];
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		time=!time;
		employee.getinstance().setContent(this);
		switch(choice){
		case 1:
			add_e();
			break;
		case 2:
			del_e();
			break;
		case 3:
			upd_e();
			break;
		case 4:
			fin_e();
			default:
		}

		
	}
	private void fin_e() {
		if(!time){
			employee.getinstance().reshow();
			employee.getinstance().setfindtext("查询员工信息");
		}
		else{
			Helper.getinstance().fin_e(content);
			employee.getinstance().setfindtext("返回");
			employee.getinstance().reshow_select();
		}
	}
	private void upd_e() {
		Helper.getinstance().upd_e(content);
		employee.getinstance().reshow();
		
	}
	private void del_e() {
		Helper.getinstance().del_e(content);
		employee.getinstance().reshow();
	}
	private void add_e() {
		Helper.getinstance().add_e(content);
		employee.getinstance().reshow();
	}

}
