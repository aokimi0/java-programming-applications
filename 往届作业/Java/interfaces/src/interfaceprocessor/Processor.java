package interfaceprocessor;

public interface Processor {
	String name();
	Object process(Object input);
}
class Stringedchange implements Processor{//继承了Processor后Stringedchange为Processor的子类，可以向上转型为Processor类型对象
	public String name(){//java类的继承关系中，只能扩大一个函数的作用域范围，不能缩小，故此处必须写成public
		return "Stringed change:";
	}
	public Object process(Object input){//该process只需要一个参数：String s
		return Stringed.process((String)input);//Stringed的process是static，可以直接写成Stringed.process（）
	}
}