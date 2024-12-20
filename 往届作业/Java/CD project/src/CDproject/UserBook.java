package CDproject;

public class UserBook {
	Users[] data=new Users[1000];
	public int num=0;
	public void add(Users d){
		if(find(d.getId())==null){
			data[d.getId()]=new Users(d.getId(),d.getName(),d.getMoney());
		}
		else {
			int money=d.getMoney()+data[d.getId()].getMoney();
			data[d.getId()].setMoney(money);
		}
	}
	public Users find(int id){
		if(data[id]!=null)
			return data[id];
		return null;
	}
	public boolean remove(Users d){
		if(data[d.getId()]!=null){
			data[d.getId()]=null;
			return true;
		}
		return false;
	}
	
	public void list(){
		for(int i=0;i<1000;i++){
			if(find(i)!=null){
				System.out.println(data[i].toString());
			}
		}
	}
}
