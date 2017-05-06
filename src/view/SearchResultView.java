package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import widget.InitWindow;
import widget.LimitNumberLenght;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import action.AdAction;
import action.UserAction;
import model.BookModel;
import tablemodel.SBookTableModel;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
/**
 * ���ѽ��ҳ��
 * ��������Ա���û������ѽ��
 * ���ݴ����ֵ�Ĳ�ͬ�жϵ�������Ϳؼ�����
 * @author ��ΰ
 *
 */
public class SearchResultView{

	private JFrame frame;
	private JTable table=null;
	private List<BookModel> lists=null;//��������б����������������
	private SBookTableModel myTableModel =null;
	private UserAction userAction;
	private AdAction adAction;
	private int who=0;//who��ʾ�û����ǹ���ԱΪ1��ʾ�û���Ϊ2��ʾ����Ա
	
	private JButton formerPage;
	private JButton nextPage;
	private JButton borrow;
	private JButton order;
	private JButton Gopage;//ǰ�������ҳ��
	
	private int selectedRowIndex=-1;//�û����ѡ�е���
    
    private String keyWord;
    private String searchType;
    private JButton back;
    private JLabel pages;
    //��ʾ��ǰҳ��������ҳ��
    private int pageNow=1;
    private int pageAll=1;
    private JTextField pageGo;//����ҳ����
    private int pageInput;
	/**
	 * @param keyWord
	 * @param searchType
	 * @param who ��ʾ�û����ǹ���ԱΪ1��ʾ�û���Ϊ2��ʾ����Ա
	 */
	public SearchResultView(String keyWord,String searchType,int who){
		this.keyWord=keyWord;
		this.searchType=searchType;
		this.who=who;
		initialize();
		if(who==1){
			userAction = UserAction.getInstance();
		}
		else{
			adAction=AdAction.getInstance();
			//�����û���ͬ����ҳ�水ť����
			borrow.setText("ɾ��");
			order.setText("�޸�");
		}
		frame.setVisible(true);
		getData();
		action();
	}
	//�����û���ͬ����ҳ�水ť����
	public static SearchResultView searchResultView=null;
	private JButton detail;

	// ����ģʽ���õ�Ŀǰ�Ĵ�ҳ��
	public static SearchResultView getInstance(String keyWord,String searchType,int who) {
		if (searchResultView == null) {
			searchResultView = new SearchResultView(keyWord, searchType, who);
		}
		return searchResultView;
	}
	
	//����Ա�޸���Ϣ�����ͼ���ͼ���
	public void updateData(BookModel bm){
		myTableModel.setUpdateData(bm);
	}
	
	//���ҵ�����
	public void getData(){
		lists = new ArrayList<BookModel>();
		System.out.println("�ؼ��֣�"+keyWord+"�������ͣ�"+searchType);
		if(who==1){
			lists = userAction.searchBook(keyWord, searchType);
		}
		else{
			lists = adAction.searchBook(keyWord, searchType);
		}
		//δ����������ʱ��lists�����ʾ��Ϣ
		if(lists==null){
			BookModel info = new BookModel();
			info.setISBN("δ�ҵ�ͼ�飡");
			lists = new ArrayList<>();
			lists.add(info);
		}
			
		myTableModel =  new SBookTableModel(table, frame, lists);
		myTableModel.initData();
		
		pageAll = (myTableModel.getRowListAll()-1)/8+1;
		pages.setText("��"+ pageNow +"ҳ ��"+  pageAll +"ҳ");
	}
	
	//���ֵ������
	public void action() {
		
		//ȥ��ָ��ҳ
		Gopage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//���ַ���ת��Ϊ����
				if(pageGo.getText().equals(""))
				{
					JOptionPane.showConfirmDialog(null, "��������ʵ�ҳ������",
							"��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
				}
				else{
					pageInput = Integer.parseInt(pageGo.getText());
					if (pageInput <= pageAll)
						myTableModel.goPage(pageInput);
					else
						JOptionPane.showConfirmDialog(null, "��������ʵ�ҳ������", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
				}
				
			}
		});
		
		//�����һҳ
		formerPage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(myTableModel.formerPage()){
					selectedRowIndex=-1;//ʹѡ�е���������
					pageNow--;
					pages.setText("��"+ pageNow +"ҳ ��"+  pageAll +"ҳ");
				}
				else
					JOptionPane.showConfirmDialog(null, "�Ѿ��ǵ�һҳ�ˣ�",
							"��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		// �����һҳ��ˢ�±��
		nextPage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(myTableModel.nextPage()){
					selectedRowIndex=-1;//ʹѡ�е���������
					pageNow++;
					pages.setText("��"+ pageNow +"ҳ ��"+  pageAll +"ҳ");
				}
				else
					JOptionPane.showConfirmDialog(null, "�Ѿ������һҳ�ˣ�",
							"��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
				    
			}
		});

		// �������/ɾ��
		borrow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//�û� ����
				if(who==1){
					if (selectedRowIndex == -1 || table.getValueAt(selectedRowIndex, 6) == null) {
						System.out.println("��ѡ��Ҫ���ĵ�ͼ��");
						JOptionPane.showConfirmDialog(null, "��ѡ��Ҫ���ĵ�ͼ��", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
					} else {
						if(userAction.user.getBalance()<=-20)
							JOptionPane.showConfirmDialog(null, "Ƿ�ѹ��ࡣ����ϵ����Ա��ֵ",
									"��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
						else if((int)table.getValueAt(selectedRowIndex, 5)==0){
							JOptionPane.showConfirmDialog(null, "�����޿�档��",
									"��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
						}
						// �������,�жϽ���Ȩ��
						else{
							String ISBN = (String) table.getValueAt(selectedRowIndex, 0);
							int power = (int)table.getValueAt(selectedRowIndex,6);
							if(power<=userAction.user.getPower())
								if (userAction.borrowBook(ISBN)) {
									myTableModel.updateData(selectedRowIndex);//ˢ�±��
									JOptionPane.showConfirmDialog(null, "����ɹ�",
											"��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
								} else
									JOptionPane.showConfirmDialog(null, "����ʧ�ܣ�����",
											"��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
							else{
								JOptionPane.showConfirmDialog(null, "Ȩ�޲������ܽ��Ĵ���",
										"��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
							}
						}

					}
				}
				else{//����Աɾ��
					if (selectedRowIndex == -1 || table.getValueAt(selectedRowIndex, 6) == null) {
						JOptionPane.showConfirmDialog(null, "��ѡ��Ҫɾ����ͼ��", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
					} else {
						String ISBN = (String) table.getValueAt(selectedRowIndex, 0);
						if (adAction.delBook(ISBN)) {
							// TODO ɾ���ɹ�
							myTableModel.updateDelData(selectedRowIndex);
							//��ѡ����
							selectedRowIndex=-1;
							//���¼��㵱ǰҳ��
							pageAll = myTableModel.getRowListAll()/8+1;
							pages.setText("��"+ pageNow +"ҳ ��"+  pageAll +"ҳ");
							JOptionPane.showConfirmDialog(null, "ɾ���ɹ�", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
						} else {
							JOptionPane.showConfirmDialog(null, "ɾ��ʧ�ܣ�����", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
						}
					}
				}
				
			}
		});

		// ���ԤԼ/����
		order.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (who == 1) {//�û�ԤԼ
					if (selectedRowIndex == -1 || table.getValueAt(selectedRowIndex, 6) == null) {
						System.out.println("��ѡ��ҪԤԼ��ͼ��");
						JOptionPane.showConfirmDialog(null, "��ѡ��ҪԤԼ��ͼ��", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
					} else {
						int RN = (int) table.getValueAt(selectedRowIndex, 5);
						int power = (int)table.getValueAt(selectedRowIndex,6);
						if(power<=userAction.user.getPower())
							if (RN > 0) {
								System.out.println("�����ֱ�ӽ��ģ�����ԤԼ");
								JOptionPane.showConfirmDialog(null, "�����ֱ�ӽ��ģ�����ԤԼ",
										"��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
							} else {
								// ԤԼ����
								String ISBN = (String) table.getValueAt(selectedRowIndex, 0);
								if (userAction.orderBook(ISBN)) {
									JOptionPane.showConfirmDialog(null, "ԤԼ�ɹ�",
											"��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
								} else {
									JOptionPane.showConfirmDialog(null, "ԤԼʧ�ܣ�����",
											"��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
								}
							}
						else
							JOptionPane.showConfirmDialog(null, "Ȩ�޲�������ԤԼ������",
									"��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);

					}
				}
				else{//����Ա����
					if (selectedRowIndex == -1 || table.getValueAt(selectedRowIndex, 6) == null) {
						JOptionPane.showConfirmDialog(null, "��ѡ��Ҫ���ĵ�ͼ��",
								"��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
					} else {
						InputBookView inputBookView = new InputBookView(myTableModel.getChoose(selectedRowIndex), 2);
						inputBookView.getFrame().setVisible(true);
					}
				}
			}
		});
		
		detail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedRowIndex == -1 || table.getValueAt(selectedRowIndex, 6) == null) {
					JOptionPane.showConfirmDialog(null, "��ѡ��Ҫ�鿴��ͼ��", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
				}
				else{
					BookDetailView bookDetailView = new BookDetailView(myTableModel.getChoose(selectedRowIndex));
					bookDetailView.getFrame().setVisible(true);
				}
			}
		});

		// �������¼�
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// ���û�ѡȡ�ĵ������ݲ���
				int selectRows = table.getSelectedRows().length;// ȡ���û���ѡ�е�����
				if (selectRows == 1) {
					selectedRowIndex = table.getSelectedRow();// ȡ���û���ѡ����
					// System.out.print(list.get(selectedRowIndex).get("i"));
					System.out.print(selectedRowIndex);

					String string = (String) table.getValueAt(selectedRowIndex, 1);

					System.out.print(string);
				}
			}
		});
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				searchResultView =null;
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		InitWindow.init(frame);
		// ����panel��Ϊ����,�ؼ���������
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 800);
		panel.setLayout(null);
		panel.setOpaque(false);// ����Ϊ͸�������Կ���ͼƬ
		
		
		frame.getContentPane().add(panel);
		
		JLabel label = new JLabel("\u641C\u7D22\u7ED3\u679C");
		label.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		label.setBounds(471, 13, 113, 31);
		panel.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 57, 1000, 600);
		panel.add(scrollPane);
		
		table = new JTable();
		//�����಻�������ݴ�С�ı�
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    table.setRowHeight(70);
		scrollPane.setViewportView(table);
		
		
	    nextPage = new JButton("��һҳ");
	    nextPage.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
	    nextPage.setBackground(new Color(102, 204, 204));
		nextPage.setBounds(503, 676, 113, 44);
		panel.add(nextPage);
		
	    formerPage = new JButton("��һҳ");
	    formerPage.setBackground(new Color(102, 204, 204));
	    formerPage.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
		formerPage.setBounds(280, 676, 113, 44);
		panel.add(formerPage);
		
		borrow = new JButton("����");
		borrow.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
		borrow.setBackground(new Color(153, 204, 153));
		borrow.setBounds(1038, 284, 130, 44);
		panel.add(borrow);
		
		order = new JButton("ԤԼ");
		order.setBackground(new Color(153, 102, 255));
		order.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
		order.setBounds(1038, 398, 130, 44);
		panel.add(order);
		
		back = new JButton("�˳�");
		back.setFont(new Font("���Ŀ���", Font.PLAIN, 15));
		back.setBounds(1079, 17, 89, 27);
		panel.add(back);
		
	    pages = new JLabel("�� i ҳ �� n ҳ");
		pages.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		pages.setBounds(897, 683, 119, 35);
		panel.add(pages);
		
		JLabel label_1 = new JLabel("��          ҳ");
		label_1.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		label_1.setBounds(661, 685, 102, 31);
		panel.add(label_1);
		
		pageGo = new JTextField();
		pageGo.setBounds(687, 687, 38, 31);
		panel.add(pageGo);
		pageGo.setColumns(10);
		pageGo.setDocument(new LimitNumberLenght(3));
		
		Gopage = new JButton("ǰ��");
		Gopage.setBackground(new Color(220, 20, 60));
		Gopage.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		Gopage.setBounds(776, 689, 77, 31);
		panel.add(Gopage);
		
		detail = new JButton("\u8BE6\u60C5");
		detail.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
		detail.setBackground(new Color(0, 128, 128));
		detail.setBounds(1038, 584, 130, 35);
		panel.add(detail);
		
	}	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
