package model;

import java.sql.Date;

/**
 * ͼ��ģ����
 * ���ͼ�����������
 * @author ��ΰ
 *
 */

public class BookModel {
	private String ISBN;//��ı��
	private String name;//����
	private String introduction;//���
	private String author;//����
	private String press;//������
	private int TN;//ͼ��������
	private int RN;//ͼ��ʣ������
	private int powerNeed;//��������Ȩ��,Ҳ��ʾ�û����� 1��ʾѧ����2��ʾ��ʦ
	private Date storageTime;//ͼ�����ʱ��
	private String bookType;
	
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public int getTN() {
		return TN;
	}
	public void setTN(int tN) {
		TN = tN;
	}
	public int getRN() {
		return RN;
	}
	public void setRN(int rN) {
		RN = rN;
	}
	public int getPowerNeed() {
		return powerNeed;
	}
	public void setPowerNeed(int powerNeed) {
		this.powerNeed = powerNeed;
	}
	public Date getStorageTime() {
		return storageTime;
	}
	public void setStorageTime(Date storageTime) {
		this.storageTime = storageTime;
	}
	@Override
	public String toString() {
		return "BookModel [ISBN=" + ISBN + ", name=" + name + ", introduction=" + introduction + ", author=" + author
				+ ", press=" + press + ", TN=" + TN + ", RN=" + RN + ", powerNeed=" + powerNeed + ", storageTime="
				+ storageTime + ", bookType=" + bookType + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ISBN == null) ? 0 : ISBN.hashCode());
		result = prime * result + RN;
		result = prime * result + TN;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((bookType == null) ? 0 : bookType.hashCode());
		result = prime * result + ((introduction == null) ? 0 : introduction.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + powerNeed;
		result = prime * result + ((press == null) ? 0 : press.hashCode());
		result = prime * result + ((storageTime == null) ? 0 : storageTime.hashCode());
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
		BookModel other = (BookModel) obj;
		if (ISBN == null) {
			if (other.ISBN != null)
				return false;
		} else if (!ISBN.equals(other.ISBN))
			return false;
		if (RN != other.RN)
			return false;
		if (TN != other.TN)
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookType == null) {
			if (other.bookType != null)
				return false;
		} else if (!bookType.equals(other.bookType))
			return false;
		if (introduction == null) {
			if (other.introduction != null)
				return false;
		} else if (!introduction.equals(other.introduction))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (powerNeed != other.powerNeed)
			return false;
		if (press == null) {
			if (other.press != null)
				return false;
		} else if (!press.equals(other.press))
			return false;
		if (storageTime == null) {
			if (other.storageTime != null)
				return false;
		} else if (!storageTime.equals(other.storageTime))
			return false;
		return true;
	}
	
	


}
