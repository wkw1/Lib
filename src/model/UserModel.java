package model;

import java.sql.Date;

/**
 * 用户模型类
 * 存放用户的所有属性
 * 
 * @author 宽伟
 */

public class UserModel {
	private String name;//姓名
	private String ID;//学号
	private String school;//学院
	private int power;//借书权限 用户种类 1代表学生，2代表老师
	private int ANBooks;//允许借书数量
	private int BNBooks;//已经借书数量
	private float balance;//余额信息
	private Date joinDate;//注册时间 注册时间为0000-00-00表示未注册
	private String password;//密码 未注册密码为空
	
	//用户单例模式,登录后只有一个用户
	public static UserModel userModel;
	public UserModel(){

	}

	//必须在登录时创建一个用户
	public UserModel(UserModel user) {
		userModel.setANBooks(user.getANBooks());
		userModel.setBalance(user.getBalance());
		userModel.setBNBooks(user.getBNBooks());
		userModel.setID(user.getID());
		userModel.setJoinDate(user.getJoinDate());
		userModel.setName(user.getName());
		userModel.setPassword(user.getPassword());
		userModel.setPower(user.getPower());
		userModel.setSchool(user.getSchool());

	}

	public static UserModel getInstance(UserModel user){
		if(userModel==null){
			userModel= new UserModel(user);
		}
		return  userModel;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserModel{" +
				"name='" + name + '\'' +
				", ID='" + ID + '\'' +
				", school='" + school + '\'' +
				", power=" + power +
				", ANBooks=" + ANBooks +
				", BNBooks=" + BNBooks +
				", balance=" + balance +
				", joinDate=" + joinDate +
				", password='" + password + '\'' +
				'}';
	}
}
