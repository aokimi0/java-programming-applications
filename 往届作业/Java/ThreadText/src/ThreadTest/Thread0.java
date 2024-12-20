package ThreadTest;

public class Thread0 extends Thread{
	public int start;
	public int end;
	Thread0(int start,int end){
		this.start=start;
		this.end=end;
	}
	@Override
	public void run(){
		for(int i=start;i<end;i++){
			if(issushu(i))
				System.out.print(i+" ");
		}
		System.out.println();
	}
	private boolean issushu(int i) {
		if(i==1)
			return false;
		if(i==2)
			return true;
		for(int j=2;j<i;j++){
			if(i%j==0)
				return false;
		}
		return true;
	}
}
