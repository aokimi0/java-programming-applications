package control;

import util.*;

public abstract class CFactoty extends Factory{
	public abstract Event produce();
	//public abstract Event produce(int a,int b);
}

class PQuit extends CFactoty{
	
	@Override
	public Event produce() {
		return new Quit();
	}
}

class PPutChess extends CFactoty{
	
	public Event produce(int r,int c) {
		return new PutChess(r,c);
	}

	@Override
	public Event produce() {
		// TODO Auto-generated method stub
		return null;
	}
}

class PWin extends CFactoty{
	
	@Override
	public Event produce() {
		return new WinCheck();
	}
}