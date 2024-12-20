package Review;
class A{
	A(){
		System.out.println("A.set");
	}
	public void abs(){
		System.out.println("4");
	}
}
class B{
	B(){
		System.out.println("B.set");
	}
}
class C extends A{
	B b=new B();
	@Override
	public void abs(){
		System.out.println("5");
	}
}
public class Review {
	public static void main(String[] args){
		C c=new C();
		c.abs();
	}
}
