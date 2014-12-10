package ANNdroid.src.objects;

import ANNdroid.src.ANNdroid;
import ANNdroid.src.SDataSL;
import ANNdroid.src.objects.Categories;

import java.util.*;

public class Student extends User{

	private int gamesPlayed;
	private int gamesWon;
	private int gamesLost;
	private int gamesDraw;

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

		Map<Categories,Object> map = new HashMap<Categories,Object>();

		gamesPlayed = 0;
		gamesWon = 0;
		gamesLost = 0;
		gamesDraw = 0;

		nickname = "";
		month = "";
		day = "";
		age = -1;
		gender = "";
		region = "";
		chemistry = -1;
		physics = -1;
		biology = -1;

		map.put(Categories.GAMES_PLAYED,gamesPlayed);
		map.put(Categories.GAMES_WON,gamesWon);
		map.put(Categories.GAMES_LOST,gamesLost);
		map.put(Categories.GAMES_DRAW,gamesDraw);
		map.put(Categories.MONTH, month);
		map.put(Categories.DAY, day);
		map.put(Categories.AGE, age);
		map.put(Categories.GENDER, gender);
		map.put(Categories.REGION, region);
		map.put(Categories.CHEMISTRY, chemistry);
		map.put(Categories.PHYSICS, physics);
		map.put(Categories.BIOLOGY, biology);

		ANNdroid.simulator.studentData.put(this, map);
		SDataSL.save(ANNdroid.simulator.studentData);

	}

	public void setGamesPlayed(int num){
		this.gamesPlayed = num;
		ANNdroid.simulator.studentData.update(this,Categories.GAMES_PLAYED,num);
		SDataSL.save(ANNdroid.simulator.studentData);
	}

	public void setGamesWon(int num){
		this.gamesWon = num;
		ANNdroid.simulator.studentData.update(this,Categories.GAMES_WON,num);
		SDataSL.save(ANNdroid.simulator.studentData);

	}

	public void setGamesLost(int num){
		this.gamesLost = num;
		ANNdroid.simulator.studentData.update(this,Categories.GAMES_LOST,num);
		SDataSL.save(ANNdroid.simulator.studentData);

	}

	public void gamesDraw(int num){
		this.gamesDraw = num;
		ANNdroid.simulator.studentData.update(this,Categories.GAMES_DRAW,num);
		SDataSL.save(ANNdroid.simulator.studentData);

	}

	public void setNickname(String nickname){
		this.nickname = nickname;
	}

	public void setMonth(String month){
		this.month = month;
		ANNdroid.simulator.studentData.update(this,Categories.MONTH,month);
		SDataSL.save(ANNdroid.simulator.studentData);

	}

	public void setDay(String day){
		this.day = day;	
		ANNdroid.simulator.studentData.update(this,Categories.DAY,day);
		SDataSL.save(ANNdroid.simulator.studentData);

	}

	public void setAge(int age){
		this.age = age;
		ANNdroid.simulator.studentData.update(this,Categories.AGE,age);
		SDataSL.save(ANNdroid.simulator.studentData);

	}

	public void setGender(String gender){
		this.gender = gender;
		ANNdroid.simulator.studentData.update(this,Categories.GENDER,gender);
		SDataSL.save(ANNdroid.simulator.studentData);

	}

	public void setRegion(String region){
		this.region = region;
		ANNdroid.simulator.studentData.update(this,Categories.REGION,region);
		SDataSL.save(ANNdroid.simulator.studentData);

	}

	public void setChemistry(int chemistry){
		this.chemistry = chemistry;
		ANNdroid.simulator.studentData.update(this,Categories.CHEMISTRY,chemistry);
		SDataSL.save(ANNdroid.simulator.studentData);


	}

	public void setPhysics(int physics){
		this.physics = physics;
		ANNdroid.simulator.studentData.update(this,Categories.PHYSICS,physics);
		SDataSL.save(ANNdroid.simulator.studentData);

	}

	public void setBiology(int biology){
		this.biology = biology;
		ANNdroid.simulator.studentData.update(this,Categories.BIOLOGY,biology);
		SDataSL.save(ANNdroid.simulator.studentData);

	}

	public int getGamesPlayed(){
		return this.gamesPlayed;
	}

	public int getGamesWon(){
		return this.gamesWon;
	}

	public int getGamesLost(){
		return this.gamesLost;
	}

	public int getGamesDraw(){
		return this.gamesDraw;
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
		return this.month + " " + this.day;
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

	public boolean isProfileComplete(){
		return (!this.nickname.equals("") && !this.month.equals("") && !this.day.equals("") && !this.gender.equals("")
		&& !this.region.equals("") && this.age != -1 && this.chemistry != -1 && this.physics != -1 && this.biology != -1);
	}




}