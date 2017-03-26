package model;

import java.sql.Date;

/**
 * �û�ģ����
 * ����û�����������
 * 
 * @author ��ΰ
 *
 */

public class UserModel {
	private String name;//����
	private String ID;//ѧ��
	private String school;//ѧԺ
	private int power;//����Ȩ�� �û����� 1����ѧ����2������ʦ
	private int ANBooks;//�����������
	private int BNBooks;//�Ѿ���������
	private float balance;//�����Ϣ
	private Date joinDate;//ע��ʱ��
	
	//�û�����ģʽ
	private static volatile UserModel instance;
	
	//�����ڵ�¼ʱ����һ���û�
		public UserModel() {
			super();
		}
	
	//�����ڵ�¼ʱ����һ���û�
	public UserModel(String ID) {
		// TODO Auto-generated constructor stub
		if(instance ==null)
			instance =  new UserModel();
		instance.ID =ID;
	}
	
	
	public static UserModel getIstance(){
		if(instance==null)
			System.out.println("δ��¼������");
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
