package widget;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
/**
 * 限制输入的控件
 * 限制文本输入框只能输入数字
 * 
 * @author 宽伟
 *
 */

public class LimitNumberLenght extends PlainDocument {

	private static final long serialVersionUID = 1L;
	
	private int limit;
	/**
	 * @param limit 限制输入数字的长度
	 */
	public LimitNumberLenght(int limit){
		super();
		this.limit = limit;
	}
	
	public void insertString(int offset,String string ,AttributeSet attr) throws BadLocationException{
		if(string==null)
			return;
		if((getLength()+string.length())<=limit){
			char[] chars = string.toCharArray();
			int length=0;
			for(int i=0;i<chars.length;i++){
				if(chars[i]>'0'&&chars[i]<'9')
					chars[length++] = chars[i];
				}
			
			 super.insertString(offset, new String(chars,0,length), attr);
		}
	}
}
