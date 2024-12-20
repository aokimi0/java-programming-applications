package interfaceprocessor;

public class Apply {
	public static void process(Processor p,Object s){//两个参数一个是Processor对象，一个是所有类型对象，此题要求是String类型对象
		System.out.println("Using Processor"+p.name());
		System.out.println(p.process(s));
	}
}
