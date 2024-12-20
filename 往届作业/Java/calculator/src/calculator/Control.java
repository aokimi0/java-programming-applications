package calculator;
import calculator.*;
public class Control {
	
	private static Control instance;
	public static Control getIntance(){
		if(instance==null){
			instance=new Control();
		}
		return instance;
	}
	public void pressBtn(String command) {
		if(command.equals("+")||command.equals("-")||command.equals("*")||command.equals("��")||command.equals("%")){
			Model.getinstance().setOperator(command);
			View.getinstance().showOperator(command);
			Model.getinstance().zero=false;
		}else if(command.equals("=")){
			double num_1=Double.parseDouble(Model.getinstance().num1.toString());
			double num_2=Double.parseDouble(Model.getinstance().num2.toString());
			switch(Model.getinstance().opt){
			case "+":
				View.getinstance().showNumber(Model.getinstance().add(num_1,num_2));
				break;
			case "-":
				View.getinstance().showNumber(Model.getinstance().sub(num_1,num_2));
				break;
			case"*":
				View.getinstance().showNumber(Model.getinstance().plus(num_1,num_2));
				break;
			case "��":
				View.getinstance().showNumber(Model.getinstance().div(num_1,num_2));
				break;	
			case"%":
				View.getinstance().showNumber(Model.getinstance().mold(num_1,num_2));
				break;	
			}
			Model.getinstance().remake();
			
		}else if(command.equals("+/-")){
			Model.getinstance().change();
			View.getinstance().showNumber(Model.getinstance().getResult());
		}else if(command.equals("CE")){
			Model.getinstance().CE();
			View.getinstance().showNumber(Model.getinstance().getResult());
		}else if(command.equals("C")){
			Model.getinstance().C();
			View.getinstance().showNumber(Model.getinstance().getResult());
		}else if(command.equals(".")){
			Model.getinstance().dot();
			View.getinstance().showNumber(Model.getinstance().getResult());
		}else if(command.equals("��")){
			Model.getinstance().cha();
			View.getinstance().showNumber(Model.getinstance().getResult());
		}else if(command.equals("1/x")){
			double num_1=Double.parseDouble(Model.getinstance().num1.toString());
			if(Model.getinstance().opt!=null){
				double num_2=Double.parseDouble(Model.getinstance().num2.toString());
				Model.getinstance().one(num_1,num_2);
			}
			else
				Model.getinstance().one(num_1,0);
			View.getinstance().showNumber(Model.getinstance().getResult());
		}else if(command.equals("x2")){
			double num_1=Double.parseDouble(Model.getinstance().num1.toString());
			if(Model.getinstance().opt!=null){
				double num_2=Double.parseDouble(Model.getinstance().num2.toString());
				Model.getinstance().twi(num_1,num_2);
			}
			else
				Model.getinstance().twi(num_1,0);
			View.getinstance().showNumber(Model.getinstance().getResult());
		}else if(command.equals("x1/2")){
			double num_1=Double.parseDouble(Model.getinstance().num1.toString());
			if(Model.getinstance().opt!=null){
				double num_2=Double.parseDouble(Model.getinstance().num2.toString());
				Model.getinstance().half(num_1,num_2);
			}
			else
				Model.getinstance().half(num_1,0);
			View.getinstance().showNumber(Model.getinstance().getResult());
		}
		else {
			if(Model.getinstance().zero){
				Model.getinstance().num1=new StringBuilder();
				Model.getinstance().zero=false;
			}
			Model.getinstance().addNumber(command);
			View.getinstance().showNumber(Model.getinstance().getResult());
		}
	}
	
}
