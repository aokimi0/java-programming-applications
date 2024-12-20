package CDproject;

public class Users {
	private int id;
	private String name;
	private int money;
	public DiskBook tools=new DiskBook();
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + money;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((tools == null) ? 0 : tools.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (id != other.id)
			return false;
		if (money != other.money)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tools == null) {
			if (other.tools != null)
				return false;
		} else if (!tools.equals(other.tools))
			return false;
		return true;
	}
	public Users(int id, String name, int money) {
		super();
		this.id = id;
		this.name = name;
		this.money = money;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public DiskBook getTools() {
		return tools;
	}
	public void setTools(DiskBook tools) {
		this.tools = tools;
	}
	@Override
	public String toString() {
		for(int i=0;i<1000;i++){
			if(tools.data[i]!=null){
				tools.list();
				return "Users [id=" + id + ", name=" + name + ", money=" + money +  ", tools" + "ÈçÉÏ" + "]";
			}
		}

		return "Users [id=" + id + ", name=" + name + ", money=" + money +  "]";
	}
	
}
