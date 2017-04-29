package model;

import java.sql.Date;

/**
 * �û�ģ����
 * ����û�����������
 * 
 * @author ��ΰ
 */

public class UserModel {
	private String name;//����
	private String ID;//ѧ��
	private String school;//ѧԺ
	private int power;//����Ȩ�� �û����� 1����ѧ����2������ʦ
	private int ANBooks;//�����������
	private int BNBooks;//�Ѿ���������
	private float balance;//�����Ϣ
	private Date joinDate;//ע��ʱ�� ע��ʱ��Ϊ0000-00-00��ʾδע��
	private String password;//���� δע������Ϊ��
	
	//�û�����ģʽ,��¼��ֻ��һ���û�
	public static UserModel userModel;
	public UserModel(){

	}

	//�����ڵ�¼ʱ����һ���û�
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
