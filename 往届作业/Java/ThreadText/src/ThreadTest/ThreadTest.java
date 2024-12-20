package ThreadTest;

public class ThreadTest {
	
	public static void main(String args[]) throws InterruptedException{
		System.out.print("Thread-0:3 ");
		Thread0 t1=new Thread0(1,1000);
		t1.start();
		t1.join();
		
		System.out.print("Thread-1:1003 ");
		Thread0 t2=new Thread0(1001,2000);
		t2.start();
		t2.join();
		
		System.out.print("Thread-2:2003 ");
		Thread0 t3=new Thread0(2001,3000);
		t3.start();
	}
}