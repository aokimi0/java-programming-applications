package ConnectionManager;

public class Ways {
	public void way1(){//需要能调用way2，不能是static
		System.out.println("way1.constructor");
		way2();
	}
	public void way2(){//需要被重写，不能加static
		System.out.println("Ways->way2.constructor");
	}
	public static void main(String[] args){
		Waytowhere ww=new Waytowhere();
		ww.way2();
	}
}
class Waytowhere extends Ways{
	@Override
	public void way2(){
		System.out.println("Waytowhere->way2.constructor");
	}
}
