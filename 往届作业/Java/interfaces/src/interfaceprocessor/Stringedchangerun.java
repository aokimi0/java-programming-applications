package interfaceprocessor;

public class Stringedchangerun {
	public static void main(String[] args){
		String s=new String(args[0]);
		Stringedchange sc=new Stringedchange();
		Apply.process(sc, s);//sc.process(s)
	}
}