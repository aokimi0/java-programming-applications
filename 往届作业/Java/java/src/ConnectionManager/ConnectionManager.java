package ConnectionManager;

public class ConnectionManager {
	static int count=0;
	static int num=5;
	ConnectionManager(int i){num=i;};
	static Connection[] connection=new Connection[num];
	static void getConnection(){
		for(int i=0;i<num;i++){
			Connection x=Connection.makeup();//makeup()为静态函数可以直接应用Connection调用
			connection[i]=x;
			count++;
		}
	}
	static Connection DeleteConnection(){
		if(count==0){
			System.out.println("no more connection to delete");
			return null;
		}
		else {
			System.out.println("少了一个connection");
			count--;
			return connection[count];
		}
	}
	public static void main(String[] args){
		ConnectionManager cm=new ConnectionManager(3);
		cm.getConnection();
		System.out.println("还剩下"+cm.count+"个connection");
		cm.DeleteConnection();
		System.out.println("还剩下"+cm.count+"个connection");
		cm.DeleteConnection();
		System.out.println("还剩下"+cm.count+"个connection");
		cm.DeleteConnection();
		System.out.println("还剩下"+cm.count+"个connection");
		cm.DeleteConnection();
		
	}
}
class Connection{
	private Connection(){
		System.out.println("多了一个connection");
	};
	public static Connection makeup(){//必须为静态static
		return new Connection();
	}
		
}