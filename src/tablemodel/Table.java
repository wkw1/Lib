package tablemodel;

import java.util.List;
import javax.swing.table.DefaultTableModel;
@SuppressWarnings("serial")

/**
 * 抽象类表模型
 * @author 宽伟
 *
 * @param <T>实现表的类名
 * @param <E>表中填充数据的类
 */
public abstract class Table<T,E> extends DefaultTableModel{
	public Table(Object[][] objects, Object[] sObjects) {
		super(objects,sObjects);
	}
	public Table(){
		
	}

	public abstract T get(List<E> lists);
	
	public abstract boolean nextPage();
	public abstract boolean formerPage();

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
}
