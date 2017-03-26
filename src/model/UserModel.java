package model;

import java.sql.Date;

/**
 * 用户模型类
 * 存放用户的所有属性
 * 
 * @author 宽伟
 *
 */

public class UserModel {
	private String name;//姓名
	private String ID;//学号
	private String school;//学院
	private int power;//借书权限 用户种类 1代表学生，2代表老师
	private int ANBooks;//允许借书数量
	private int BNBooks;//已经借书数量
	private float balance;//余额信息
	private Date joinDate;//注册时间
	
	//用户单例模式
	private static volatile UserModel instance;
	
	//必须在登录时创建一个用户
		public UserModel() {
			super();
		}
	
	//必须在登录时创建一个用户
	public UserModel(String ID) {
		// TODO Auto-generated constructor stub
		if(instance ==null)
			instance =  new UserModel();
		instance.ID =ID;
	}
	
	
	public static UserModel getIstance(){
		if(instance==null)
			System.out.println("未登录！！！");
		return instance;
	}
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getANBooks() {
		return ANBooks;
	}
	public void setANBooks(int aNBooks) {
		ANBooks = aNBooks;
	}
	public int getBNBooks() {
		return BNBooks;
	}
	public void setBNBooks(int bNBooks) {
		BNBooks = bNBooks;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
}
