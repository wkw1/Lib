package widget;

import model.BookModel;

public class ISBNCreate {
	
	public static String CreISBN(BookModel B){
		String r="";

		switch(B.getBookType()){
			case "������":
				r=r+"00";
				break;
			case "��ʷ":
				r=r+"01";
				break;
			case "����":
				r=r+"02";
				break;
			case "С˵":
				r=r+"03";
				break;
			case "����":
				r=r+"04";
				break;
			case "��־":
				r=r+"05";
				break;
			case "����":
				r=r+"06";
			case "���":
				r=r+"07";
				break;
			case "����":
				r=r+"08";
				break;
			case "���":
				r=r+"09";
			case "ͨ��":
				r=r+"10";
				break;
			case "����":
				r=r+"11";
				break;
			case "���":
				r=r+"12";
				break;
			case "�����":
				r=r+"13";
				break;
		}
		long prime=11;
		long hashval=1;
		hashval=prime*hashval+((B.getAuthor()==null) ? 0 : B.getAuthor().hashCode());
		hashval=prime*hashval+((B.getIntroduction()==null) ? 0 : B.getIntroduction().hashCode());
		hashval=prime*hashval+((B.getName()==null) ? 0 : B.getName().hashCode());
		hashval=prime*hashval+((B.getPress()==null) ? 0 : B.getPress().hashCode());

		hashval=Math.abs(hashval%2147483645);//�������һ��8λ����

		r=r+String.format("%010d", hashval);
		return r;
	}
}
