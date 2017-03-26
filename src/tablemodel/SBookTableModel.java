package tablemodel;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import model.BookModel;
/**
 * 
 * �����ģ�� ����ͼ���
 * �����б�����
 * @author ��ΰ
 *
 */

public class SBookTableModel extends Table<SBookTableModel, BookModel> {

	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private JFrame frame;
	private List<BookModel> list=null;
	
	private int tablewight =140;

	private int lastLine = 0;// ��ǰ������һ��
	private int firstLine = 0;// ��ǰ��ĵ�һ��
	private int rowListAll = 0;// list�ܳ���
	
	private int chooseLine;
	
	private SBookTableModel myTableModel;
	
	public SBookTableModel(){
		
	}
	
	public SBookTableModel(JTable table,JFrame frame,List<BookModel> list){
		this.table= table;
		this.frame = frame;
		this.list=list;
	}

	public SBookTableModel(Object[][] objects,Object[] sObjects) {
		super(objects,sObjects);
		this.fireTableDataChanged();
		
	}
	public void initData(){
		rowListAll = list.size();
		 //���һ����
		lastLine+=8;
		myTableModel = get(list);
		// �������������
		table.setModel(myTableModel);
		for(int i=0 ;i<7;i++){
			table.getColumnModel().getColumn(i).setPreferredWidth(tablewight);
			table.getColumnModel().getColumn(i).setMaxWidth(200);
		}
	}	
	
	//����������������
	public boolean updateData(int index){
		list.get(index+firstLine).setRN(list.get(index+firstLine).getRN()-1);
		updateTable();
		return true;
	}
	//ɾ��ͼ���Ĳ���
	public boolean updateDelData(int index){
		if(lastLine-firstLine!=8||lastLine==rowListAll){
			lastLine--;
		}
		list.remove(index+firstLine);
		rowListAll--;
		updateTable();
		return true;
	}
	//����Ա�޸�ͼ����Ϣ����±������
	public void setUpdateData(BookModel bm){
		list.get(chooseLine).setIntroduction(bm.getIntroduction());
		list.get(chooseLine).setAuthor(bm.getAuthor());
		list.get(chooseLine).setName(bm.getName());
		list.get(chooseLine).setBookType(bm.getBookType());
		//����������֮��Ҫ�����ɽ�����
		list.get(chooseLine).setRN(bm.getTN()-list.get(chooseLine).getTN()+list.get(chooseLine).getRN());
		
		//...
		updateTable();
	}
	
	
	//�õ�ѡ��������
	public BookModel getChoose(int row){
		chooseLine = row+firstLine;
		return list.get(chooseLine);
	}
	
	@Override
	public SBookTableModel get(List<BookModel> lists) {
		// �������ö�ά�����ʾ
		Object[][] mObjects = new Object[8][8];
		BookModel sbm = new BookModel();
		if (lists == null) {
			System.out.println("����Ľ����Ϊ��");
			return null;
		}

		for (int i = 0; i < lists.size() && i < 8; i++) {
			sbm = lists.get(i);
			mObjects[i][7] = i;// ���
			mObjects[i][0] = sbm.getISBN();
			mObjects[i][1] = sbm.getName();
			mObjects[i][2] = sbm.getIntroduction();
			mObjects[i][3] = sbm.getPress();
			mObjects[i][4] = sbm.getAuthor();
			mObjects[i][5] = sbm.getStorageTime();
			mObjects[i][6] = sbm.getRN();

		}
		// ��ͷ����
		String[] strings = { "ISBN", "����", "���", "������", "����", "���ʱ��", "�ɽ�����" };

		SBookTableModel model = new SBookTableModel(mObjects, strings);

		return model;
	}

	@Override
	public boolean nextPage() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		System.out.println("�����һҳSearchView");
		if(rowListAll-lastLine>0){
			if(rowListAll-lastLine>=7){
				firstLine=lastLine;
				lastLine+=8;
			}
			else{
				firstLine = lastLine;
				lastLine =rowListAll;
			}
			updateTable();
			return true;
		}
		else{
			System.out.println("�Ѿ������һҳ��");
			return false;
		}
	}

	@Override
	public boolean formerPage() {
		// TODO Auto-generated method stub
		System.out.println("�����һҳSearchView");
		if (firstLine>0) {
			if(firstLine>=8){
				lastLine=firstLine;
				firstLine-=8;
			}
			else{
				lastLine=firstLine;
				firstLine=0;
			}
			updateTable();
			return true;
				
		} else {
			System.out.println("�Ѿ��ǵ�һҳ");
			return false;
		}
	}
	//ǰ��ָ��ҳ
	public boolean goPage(int pages){
		firstLine = (pages-1)*8;
		lastLine=firstLine+8;
		if(lastLine>rowListAll)
			lastLine=rowListAll;
		updateTable();
		return true;
	}
	
	// �������������޸ı������
	private void updateTable() {
		myTableModel = get(list.subList(firstLine, lastLine));
		table.setModel(myTableModel);
		for (int i = 0; i < 7; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(tablewight);
			table.getColumnModel().getColumn(i).setMaxWidth(200);
		}
		table.updateUI();
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}
	public int getRowListAll() {
		return rowListAll;
	}
	public void setRowListAll(int rowListAll) {
		this.rowListAll = rowListAll;
	}
}

