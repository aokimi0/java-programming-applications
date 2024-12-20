package CDproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boss {
	private static UserBook userbook=new UserBook();
	private static DiskBook diskbook=new DiskBook();
	BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args)throws Exception{
		new Boss().begin();
	}
	public void begin()throws Exception{
		while(true){
			printMainMenu();
			String line=in.readLine();
			int choice =Integer.parseInt(line);
			switch(choice){
			case 1:
				userManage();
				break;
			case 2:
				DiskManage();
				break;
			case 3:
				takedisk();
				break;
			case 4:
				borrowdisk();
				break;
			case 5:
				returnDisk();
				break;
			case 6:
				return;
			}
		}
	}
	private void borrowdisk() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("请输入你要借阅的CD的编号：");
		String line=in.readLine();
		int id=Integer.parseInt(line);
		if(diskbook.find(id)!=null){
			System.out.println("查询成功！该CD名为"+diskbook.data[id].getName()+",请输入您的会员编号：");
			String line1=in.readLine();
			int name_id=Integer.parseInt(line1);
			System.out.println("请输入您要借阅的数目：");
			String line2=in.readLine();
			int num=Integer.parseInt(line2);
			Disk borrow_disk=new Disk(id,diskbook.data[id].getName(),diskbook.data[id].getPrice(),num);
			if(diskbook.data[id].getNum()>=num){//
				diskbook.remove(borrow_disk);
				userbook.data[name_id].tools.add(borrow_disk);
				System.out.println("恭喜你借阅成功！");
				return;
			}
			else {
				System.out.println("抱歉目前库存中只有"+diskbook.data[id].getNum()+"张CD，您是否还要进行借阅？");
				System.out.println("1:是的，将"+diskbook.data[id].getNum()+"张CD全借走");
				System.out.println("2:不，不进行借阅");
				String line3=in.readLine();
				int choice=Integer.parseInt(line3);
				switch(choice){
				case 1:
					diskbook.remove(borrow_disk);
					userbook.data[name_id].tools.add(borrow_disk);
					System.out.println("恭喜你借阅成功！");
					break;
				case 2:
					return;
				}
			}
		}
		else {
			System.out.println("该CD库存为0，无法进行借阅操作");
			return;
		}
	}
	private void returnDisk() throws IOException {
		System.out.println("请输入你要归还的CD的编号：");
		String line=in.readLine();
		int id=Integer.parseInt(line);
		System.out.println("请输入你要归还的CD的数量：");
		String line1=in.readLine();
		int num=Integer.parseInt(line1);
		Disk return_disk=new Disk(id,diskbook.data[id].getName(),diskbook.data[id].getPrice(),num);
		diskbook.add(return_disk);
		System.out.println("请输入您的会员编号：");
		String line2=in.readLine();
		int userid=Integer.parseInt(line2);
		userbook.data[userid].tools.remove(return_disk);
		System.out.println("恭喜你归还成功！");
		return;
	}
	private void takedisk() throws IOException {
		System.out.println("请输入你要购买的CD的编号：");
		String line=in.readLine();
		int id=Integer.parseInt(line);
		if(diskbook.find(id)!=null){
			System.out.println("查询成功！该CD名为"+diskbook.data[id].getName()+",请输入您的会员编号：");
			String line1=in.readLine();
			int name_id=Integer.parseInt(line1);
			System.out.println("请输入您要购买的数目：");
			String line2=in.readLine();
			int num=Integer.parseInt(line2);
			Disk borrow_disk=new Disk(id,diskbook.data[id].getName(),diskbook.data[id].getPrice(),num);
			if(diskbook.data[id].getNum()>=num){//
				if(userbook.data[name_id].getMoney()>=num*diskbook.data[id].getPrice()){
					diskbook.remove(borrow_disk);
					userbook.data[name_id].setMoney(userbook.data[name_id].getMoney()-num*diskbook.data[id].getPrice());
					System.out.println("恭喜你购买成功！");
					return;
				}
				else {
					printAddMoney(name_id);
					takedisk();
					return;
				}
			}
			else {
				System.out.println("抱歉目前库存中只有"+diskbook.data[id].getNum()+"张CD，您是否还要进行购买？");
				System.out.println("1:是的，将"+diskbook.data[id].getNum()+"张CD全买走");
				System.out.println("2:不，不进行购买");
				String line3=in.readLine();
				int choice=Integer.parseInt(line3);
				switch(choice){
				case 1:
					diskbook.remove(borrow_disk);
					System.out.println("恭喜你购买成功！");
					break;
				case 2:
					return;
				}
			}
		}
		else {
			System.out.println("该CD库存为0，无法进行购买操作");
			return;
		}
			
	}
	private void printAddMoney(int name_id) throws IOException {
		System.out.println("抱歉 您余额不足！");
		System.out.println("是否进行充值？");
		System.out.println("1:充值");
		System.out.println("2：不充值");
		String line=in.readLine();
		int choice=Integer.parseInt(line);
		switch(choice){
		case 1:
			AddMoney(name_id);
			break;
		case 2:
			return;
			
		}
	}
	private void AddMoney(int name_id) throws IOException {
		System.out.println("请输入您要充值的钱：");
		String line=in.readLine();
		int money_add=Integer.parseInt(line);
		userbook.data[name_id].setMoney(money_add+userbook.data[name_id].getMoney());
		System.out.println("恭喜你充值成功！");
		return;
	}
	private void DiskManage() throws IOException {
		while(true){
			printDiskMenu();
			String line=in.readLine();
			int choice =Integer.parseInt(line);
			switch(choice){
			case 1:
				DiskAdd();
				break;
			case 2:
				DiskFind();
				break;
			case 3:
				ListDisk();
				break;
			case 4:
				return;
			}
		}
	}
	private void ListDisk() {
		diskbook.list();
		return;
	}
	private void DiskFind() throws IOException {
		System.out.println("请输入需要查找的CD的id：");
		String line=in.readLine();
		int id=Integer.parseInt(line);
		if(diskbook.find(id)!=null)
			System.out.println(diskbook.data[id].toString());
		else
			System.out.println("对不起库存中没有该CD的相关消息");
		return;
	}
	private void DiskAdd() throws IOException {
		System.out.println("请输入光盘id号：");
		String line=in.readLine();
		int id=Integer.parseInt(line);
		System.out.println("请输入光盘名称：");
		String name =in.readLine();
		System.out.println("请输入光盘单价：");
		String line1=in.readLine();
		int price=Integer.parseInt(line1);
		System.out.println("请输入光盘数量：");
		String line2=in.readLine();
		int num=Integer.parseInt(line2);
		Disk newdisk=new Disk(id,name,price,num);
		diskbook.add(newdisk);
		diskbook.num++;
		System.out.println("恭喜你！光盘信息添加成功！");
	}
	private void printDiskMenu() {
		System.out.println("1:disk add");
		System.out.println("2:disk find");
		System.out.println("3:list disk");
		System.out.println("4:return main menu");
		System.out.println("please choose:");
	}
	private void userManage() throws IOException {
		while(true){
			printUserMenu();
			String line=in.readLine();
			int choice =Integer.parseInt(line);
			switch(choice){
			case 1:
				userAdd();
				break;
			case 2:
				UserRemove();
				break;
			case 3:
				UserFind();
				break;
			case 4:
				ListUser();
				break;
			case 5:
				return;
			}
		}
	}
	private void UserRemove() throws IOException {
		System.out.println("请输入需要退会的会员id：");
		String line=in.readLine();
		int name_id=Integer.parseInt(line);
		if(userbook.find(name_id)!=null){
			System.out.println("请注意！退会后会员账中的钱概不退换！是否继续？");
			System.out.println("1:继续");
			System.out.println("2:停止");
			String line1=in.readLine();
			int choice=Integer.parseInt(line1);
			if(choice==1){
				Users remove_user=new Users(name_id,userbook.data[name_id].getName(),userbook.data[name_id].getMoney());
				userbook.remove(remove_user);
				System.out.println("恭喜你退会成功！祝您生活愉快！");
			}
			return;
		}
		else{
			System.out.println("您还不是本店会员哦，请检查输入的会员号是否正确！");
			return;
		}
	}
	private void ListUser() {
		userbook.list();
		return;
	}
	private void UserFind() throws IOException {
		System.out.println("请输入需要查找的用户的id：");
		String line=in.readLine();
		int id=Integer.parseInt(line);
		if(userbook.find(id)!=null)
			System.out.println(userbook.data[id].toString());
		else
			System.out.println("对不起库存中没有该用户的相关消息");
		return;
	}
	private void userAdd() throws IOException {
		System.out.println("请输入用户id：");
		String line=in.readLine();
		int id=Integer.parseInt(line);
		System.out.println("请输入用户名字：");
		String name =in.readLine();
		System.out.println("请输入用户充入的钱：");
		String line1=in.readLine();
		int money=Integer.parseInt(line1);
		if(userbook.data[id]!=null){
			System.out.println("恭喜你充钱成功！");
		}
		else{
			System.out.println("恭喜你！用户添加成功！");
		}
		Users user=new Users(id,name,money);
		userbook.add(user);
		userbook.num++;
	}
	private void printUserMenu() {
		System.out.println("1:user add");
		System.out.println("2:user remove");
		System.out.println("3:user find");
		System.out.println("4:list user");
		System.out.println("5:return main menu");
		System.out.println("please choose:");
	}
	private void printMainMenu() {
		System.out.println("1:进入会员管理");
		System.out.println("2:进入光盘管理");
		System.out.println("3:买光盘");
		System.out.println("4:借光盘");
		System.out.println("5:还光盘");
		System.out.println("6:退出系统");
		System.out.println("请选择：");
		
	}
}
