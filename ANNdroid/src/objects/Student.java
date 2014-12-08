package ANNdroid.src.objects;

public class Student extends User{

	private String nickname;
	private String month;
	private String day;
	private int age;
	private String gender;
	private String region;
	private int chemistry;
	private int physics;
	private int biology;

	public Student(String username, String password, String fname, String lname){
		super(username, password, fname, lname);
		nickname = "";
		month = "";
		day = "";
		age = -1;
		gender = "";
		region = "";
		chemistry = -1;
		physics = -1;
		biology = -1;
	}

	public void setNickname(String nickname){
		this.nickname = nickname;
	}

	public void setMonth(String month){
		this.month = month;
	}

	public void setDay(String day){
		this.day = day;
	}

	public void setAge(int age){
		this.age = age;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public void setRegion(String region){
		this.region = region;
	}

	public void setChemistry(int chemistry){
		this.chemistry = chemistry;
	}

	public void setPhysics(int physics){
		this.physics = physics;
	}

	public void setBiology(int biology){
		this.biology = biology;
	}

	public String getNickname(){
		return this.nickname;
	}

	public String getMonth(){
		return this.month;
	}

	public String getDay(){
		return this.day;
	}

	public String getBirthday(){
		return this.month + this.day;
	}

	public int getAge(){
		return this.age;
	}

	public String getGender(){
		return this.gender;
	}

	public String getRegion(){
		return this.region;
	}

	public int getChemistry(){
		return this.chemistry;
	}

	public int getPhysics(){
		return this.physics;
	}

	public int getBiology(){
		return this.biology;
	}




}