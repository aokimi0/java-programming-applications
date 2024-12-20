package CDproject;

public class DiskBook {
	Disk[] data=new Disk[1000];
	public int num=0;
	public void add(Disk d){
		if(find(d.getId())!=null){
			int num=d.getNum()+data[d.getId()].getNum();
			data[d.getId()].setNum(num);
		}
		else{
			data[d.getId()]=new Disk(d.getId(),d.getName(),d.getPrice(),d.getNum());
		}
	}
	public Disk find(int id){
		if(data[id]!=null)
			return data[id];
		return null;
	}
	public boolean remove(Disk d){
		if(d.getNum()<=data[d.getId()].getNum()){
			int num=data[d.getId()].getNum()-d.getNum();
			data[d.getId()].setNum(num);
			return true;
		}
		else{
			data[d.getId()].setNum(0);
			return false;
		}
	}
	public void list(){
		for(int i=0;i<1000;i++){
			if(find(i)!=null){
				System.out.println(data[i].toString());
			}
		}
	}
}
