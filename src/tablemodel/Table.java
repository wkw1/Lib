package tablemodel;

import java.util.List;
import javax.swing.table.DefaultTableModel;
@SuppressWarnings("serial")

/**
 * �������ģ��
 * @author ��ΰ
 *
 * @param <T>ʵ�ֱ������
 * @param <E>����������ݵ���
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
