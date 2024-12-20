package calculator;
import calculator.*;
public class Model {
	boolean change1 = true; 
	boolean change2 = true; 
	boolean done1=false;
	boolean done2=false;
	boolean zero=true;
	private static Model instance;
	public static Model getinstance(){
		if(instance==null){
			instance=new Model();
		}
	return instance;
	}
	StringBuilder num1=new StringBuilder();
	StringBuilder num2=new StringBuilder();
	String opt=null;
	public void addNumber(String num){
		if(opt==null){
			num1.append(num);//append:��ĩβ����һ��Ԫ��
		}else{
			num2.append(num);
		}
	}
	public void setOperator(String opt){
		this.opt=opt;
	}
	public String getResult(){
		if(opt==null){
			return num1.toString();
		}else {
			return num2.toString();
		}
	}
	public String add(double num_1, double num_2) {
		double result=num_1+num_2;
		String str=String.valueOf(result);
		num1=new StringBuilder();
		num1.append(str);
		return str;
	}
	public String sub(double num_1, double num_2) {
		double result=num_1-num_2;
		String str=String.valueOf(result);
		num1=new StringBuilder();
		num1.append(str);
		return str;
	}
	public String plus(double num_1, double num_2) {
		double result=num_1*num_2;
		String str=String.valueOf(result);
		num1=new StringBuilder();
		num1.append(str);
		return str;
	}
	public String div(double num_1, double num_2) {
		double result=num_1/num_2;
		String str=String.valueOf(result);
		num1=new StringBuilder();
		num1.append(str);
		return str;
	}
	public void C() {
		num1.replace(0, num1.length(), "");
		num2.replace(0, num2.length(), "");
		opt=null;
		View.getinstance().showNumber(getResult());
		
	}
	public void remake() {
		zero=true;
		opt=null;
		num2=new StringBuilder();
		done1=false;
		done2=false;
		change1=true;
		change2=true;
	}
	public void change() {
		if(opt==null&&change1==true){
			if(done1)
				num1.replace(0, 1, "-");
			else {
				num1.insert(0, "-");
				done1=true;
			}
			change1 = false; 
		}else if(opt==null&&change1==false){
			num1.replace(0,1, "+");
			change1=true;
		}else if(opt!=null&&change2==true){
			if(done2)
				num2.replace(0, 1, "-");
			else{
				num2.insert(0, "-");
				done2=true;
			}
			change2 = false; 
		}else{
			num2.replace(0,1, "+");
			change2=true;
		}
	}
	public void CE() {
		if(opt==null){
			num1.replace(0, num1.length(), "");
		}else
			num2.replace(0, num2.length(), "");	
	}
	public void dot() {
		if(opt==null){
			num1.append(".");
		}else
			num2.append(".");
	}
	public void cha() {
		if(opt==null){
			num1.deleteCharAt(num1.length()-1);
		}else
			num2.deleteCharAt(num2.length()-1);
		
	}
	public String mold(double num_1, double num_2) {
		double result=num_1%num_2;
		String str=String.valueOf(result);
		num1=new StringBuilder();
		num1.append(str);
		return str;
	}
	public void one(double num_1, double num_2) {
		if(opt==null){
			double result=1/num_1;
			String str=String.valueOf(result);
			num1.replace(0, num1.length(), str);
		}else{
			double result=1/num_2;
			String str=String.valueOf(result);
			num2.replace(0, num2.length(), str);
		}
		
	}
	public void twi(double num_1, double num_2) {
		if(opt==null){
			double result=num_1*num_1;
			String str=String.valueOf(result);
			num1.replace(0, num1.length(), str);
		}else{
			double result=num_2*num_2;
			String str=String.valueOf(result);
			num2.replace(0, num2.length(), str);
		}
		
	}
	public void half(double num_1, double num_2) {
		if(opt==null){
			double result=Math.sqrt(num_1);
			String str=String.valueOf(result);
			num1.replace(0, num1.length(), str);
		}else{
			double result=Math.sqrt(num_2);
			String str=String.valueOf(result);
			num2.replace(0, num2.length(), str);
		}
		
	}
}
