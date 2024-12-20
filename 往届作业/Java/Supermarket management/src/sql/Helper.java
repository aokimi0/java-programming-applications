package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Helper {
	Connection conn;
	Statement stmt;
	String[] text_e={"empname","empid","sex","id","age","depname","phone","catname","salary","depid"};
	String[] text_c={"productname", "proid", "vendornum", "vendorname", "inventory","outprice","inprice"};
	
	private static Helper instance=new Helper();
	public static Helper getinstance(){
		return instance;
	}

	public Helper(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3316"
					+ "/supermarketmanagement?"
					+ "useUnicode=true&characterEncoding=UTF-8", "root", "");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void settext(Object[][] rowData,int choice,String aim){
		switch(choice){
			case 1:
			try {
				gettext_emp(rowData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			case 2:
			try {
				gettext_com(rowData);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			case 3:
			try {
				gettext_emp_sel(rowData,aim);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
				default:
		}
		
	}

	private void gettext_emp_sel(Object[][] rowData,String aim) throws Exception{
		Statement stmt=conn.createStatement();
		ResultSet rst;
		ResultSet rst1;
		rst=stmt.executeQuery("select* from employee natural join working natural join category natural join department");
		int j=0;
		while(rst.next()){
			for(int i=0;i<10;i++){
				rowData[j][i]="";
			}
			j++;
		}
		rst=stmt.executeQuery("select* from employee natural join working natural join category natural join department where empid='"+aim+"\'");
		while(rst.next())
			for(int i=0;i<10;i++)
				rowData[0][i]=rst.getString(text_e[i]);
	}

	private void gettext_com(Object[][] rowData) throws Exception{
		Statement stmt=conn.createStatement();
		ResultSet rst=stmt.executeQuery("select* from product");
		int j=0;
		while(rst.next()){
			for(int i=0;i<7;i++){
				rowData[j][i]=rst.getString(text_c[i]);
			}
			j++;
		}
		
	}

	private void gettext_emp(Object[][] rowData) throws Exception{
		Statement stmt=conn.createStatement();
		ResultSet rst;
		rst=stmt.executeQuery("select* from employee natural join working natural join category natural join department");
		int j=0;
		while(rst.next()){
			for(int i=0;i<10;i++){
				rowData[j][i]="";
			}
			j++;
		}
		for(int i=0;i<10;i++){
			rowData[j][i]="";
		}
		rst=stmt.executeQuery("select* from employee natural join working natural join category natural join department");
		j=0;
		while(rst.next()){
			for(int i=0;i<10;i++){
				rowData[j][i]=rst.getString(text_e[i]);
			}
			j++;
		}
	}

	public void add_e(String[] content) {
		if(content[3].equals("")){
			JOptionPane.showMessageDialog(null, 
					"身份证号不能为空！","格式错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(content[0].equals("")){
			JOptionPane.showMessageDialog(null, 
					"员工姓名不能为空！","格式错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(content[1].equals("")){
			JOptionPane.showMessageDialog(null,
					"员工编号不能为空！","格式错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(!single_num(content)){
			JOptionPane.showMessageDialog(null, 
					"员工编号已存在！","格式错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(!single_id(content)){
			JOptionPane.showMessageDialog(null, 
					"员工身份证号已存在！","格式错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		String str="INSERT INTO employee VALUES(";
		for(int i=0;i<7;i++){
			if(i!=0)
				str+=",";
			if(!content[i].equals("")){
				if(i!=1&&i!=4)
					str+="\'";
				str=str+content[i];
				if(i!=1&&i!=4)
					str+="\'";
			}
			else{
				if(i!=5)
					str=str+"NULL";
				else
					str+="'后勤'";
			}
				
		}
		str+=")";
		String str1="INSERT INTO working VALUES(";
		if(!content[7].equals(""))
			str1=str1+content[1]+','+'\''+content[7]+'\''+')';
		else
			str1=str1+content[1]+",'职员')";
		try {
			String[] content1=content;
			//content1[7]="职员";
			if(exists_cat(content1)){
				stmt = conn.createStatement();
				stmt.executeUpdate(str);
				//stmt.executeUpdate(str1);
			}
			else
				JOptionPane.showMessageDialog(null, 
						"该部门无这个职位！","内容错误",JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean single_id(String[] content) {
		String str = "SELECT * from employee";
		ResultSet rst;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(str);
			while(rst.next()){
				if(content[3].equals(rst.getString("id"))){
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	private boolean single_num(String[] content) {
		String str = "SELECT * from employee";
		ResultSet rst;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(str);
			while(rst.next()){
				if(content[1].equals(rst.getString("empid"))){
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public void del_e(String[] content) {
		if(!exists(content))
			return;
		String str="DELETE FROM employee WHERE empid="+content[1];
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void fin_e(String[] content) {
		if(!exists(content))
			return;
		String str = "select* from employee WHERE id="+content[3];
		ResultSet rst1;
		try {
			stmt = conn.createStatement();
			rst1 = stmt.executeQuery(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean exists(String[] content) {
		if(content[3].equals("")){
			JOptionPane.showMessageDialog(null, "请检查你的输入！身份证号不能为空！","格式错误",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		String str = "SELECT * from employee";
		ResultSet rst;
		boolean tag=false;//是否存在这个人
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(str);
			while(rst.next()){
				if(content[3].equals(rst.getString("id"))){
					tag=true;//存在这个人并且之后检查各项指标是否符合要求
					for(int i=0;i<7;i++){
						if(content[i].equals(""))
							continue;
						if(!content[i].equals(rst.getString(text_e[i]))){
							JOptionPane.showMessageDialog(null, "不存在这个人！请检查你的输入！","格式错误",JOptionPane.ERROR_MESSAGE);
							return false;//不符合要求
						}
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!tag){//不存在这个人
			JOptionPane.showMessageDialog(null, "不存在这个人！请检查你的输入！","格式错误",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	private boolean exists_cat(String[] content) {
		Statement stmt;
		ResultSet rst;
		String str="";
		boolean tag=false;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery("select* from employee");
			while(rst.next()){
				if(rst.getString(text_e[1]).equals(content[1])){
					str=rst.getString(text_e[5]);//找到部门名称
					break;
				}
			}
			if(!content[5].equals(""))
				str=content[5];//如果部门没有变动的时候，则用原部门，但若部门有变动，则用新部门来判断
			rst = stmt.executeQuery("select* from category");
			while(rst.next()){
				if(rst.getString(text_e[5]).equals(str)&&rst.getString(text_e[7]).equals(content[7])){
					tag=true;
					break;
				}
					
			}
			if(content[7].equals(""))
				tag=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(str+' '+content[7]+" "+tag);
		return tag;
	}
	
	public void upd_e(String[] content) {
		if(!exists(content)){
			JOptionPane.showMessageDialog(null, "不存在这个人！请检查你的输入！",
					"内容错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		Statement stmt;
		ResultSet rst;

		boolean tag=false;
		try {
			String str="";
			String str1="";
			String str2="";
			stmt = conn.createStatement();
			rst = stmt.executeQuery("select* from employee natural "
					+ "join working natural join category natural join department");
			while(rst.next()){
				if(!rst.getString(text_e[1]).equals(content[1]))
					continue;
				for(int i=0;i<7;i++){
					if(content[i].equals("")||i==1||i==3)
						continue;
					if(!tag)
						str="UPDATE employee SET ";
					if(tag)
						str+=",";
					str+=text_e[i]+"=";
					if(i!=4)
						str+='\'';
					str+=content[i];
					if(i!=4)
						str+='\'';
					tag=true;
				}
				break;
			}
			stmt = conn.createStatement();
			boolean tag_c=true;

			if(!content[7].equals("")){
				tag_c=exists_cat(content);
				if(!tag_c){

					str2+="insert into category(depname,catname) values('"+content[5]+"','"+content[7]+"')";
					//JOptionPane.showMessageDialog(null, "该部门不存在这个职位！",
							//"内容错误",JOptionPane.ERROR_MESSAGE);
					System.out.println(str2);
					stmt.executeUpdate(str2);
				}
				str1="UPDATE working SET catname='"+content[7]
						+"' WHERE empid="+content[1];
				stmt.executeUpdate(str1);
			}
			if(tag&&tag_c){
				str+=" WHERE empid="+content[1];
				stmt.executeUpdate(str);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	
}
