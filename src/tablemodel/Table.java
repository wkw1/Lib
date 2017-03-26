package tablemodel;

import java.util.List;

import javax.swing.table.DefaultTableModel;
@SuppressWarnings("serial")
public abstract class Table<T,E> extends DefaultTableModel{
	public Table(Object[][] objects, Object[] sObjects) {
		// TODO Auto-generated constructor stub
		super(objects,sObjects);
	}
	public Table(){
		
	}

	public abstract T get(List<E> lists);
	
	public abstract boolean nextPage();
	public abstract boolean formerPage();
	
}
