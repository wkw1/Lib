package widget;

import model.BookModel;

public class ISBNCreate {
	
	public static String CreISBN(BookModel B){
		String r="";

		switch(B.getBookType()){
			case "互联网":
				r=r+"00";
				break;
			case "历史":
				r=r+"01";
				break;
			case "心理":
				r=r+"02";
				break;
			case "小说":
				r=r+"03";
				break;
			case "经管":
				r=r+"04";
				break;
			case "励志":
				r=r+"05";
				break;
			case "传记":
				r=r+"06";
			case "社科":
				r=r+"07";
				break;
			case "科普":
				r=r+"08";
				break;
			case "理财":
				r=r+"09";
			case "通信":
				r=r+"10";
				break;
			case "生活":
				r=r+"11";
				break;
			case "情感":
				r=r+"12";
				break;
			case "计算机":
				r=r+"13";
				break;
		}
		long prime=11;
		long hashval=1;
		hashval=prime*hashval+((B.getAuthor()==null) ? 0 : B.getAuthor().hashCode());
		hashval=prime*hashval+((B.getIntroduction()==null) ? 0 : B.getIntroduction().hashCode());
		hashval=prime*hashval+((B.getName()==null) ? 0 : B.getName().hashCode());
		hashval=prime*hashval+((B.getPress()==null) ? 0 : B.getPress().hashCode());

		hashval=Math.abs(hashval%2147483645);//随便找了一个8位素数

		r=r+String.format("%010d", hashval);
		return r;
	}
}
