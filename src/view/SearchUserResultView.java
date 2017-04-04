package view;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import action.AdAction;
import model.UserModel;
import tablemodel.SUserTableModel;
import widget.InitWindow;
import widget.MyButton;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
/**
 * �����õ��û������ͼ
 * @author ��ΰ
 *
 */
public class SearchUserResultView {

	private JFrame frame;
	private List<UserModel> lists=null;
	private SUserTableModel myTableModel;
	
	private JTable table;
	private int selectedRowIndex=-1;//�û����ѡ�е���
	
	private JButton formerPage;
	private JButton nextPage;
	private JButton sendMessage;
	private JButton delTheUser;
	private JButton cancel;
	
	private AdAction adAction;
	
	public static SearchUserResultView searchUserResultView;
	public static SearchUserResultView getInstance(String keyWord,String searchType){
		if(searchUserResultView==null)
			searchUserResultView = new SearchUserResultView(keyWord, searchType);
		return searchUserResultView;
	}
	
	
	public SearchUserResultView(String keyWord,String searchType) {
		adAction =  new AdAction();
		initialize();
		initData(keyWord,searchType);
		action();
	}
	
	//��ʼ������
	private void initData(String keyWord,String searchType){
		lists = new ArrayList<>();
		System.out.println("�ؼ��֣�"+keyWord+"�������ͣ�"+searchType);
		lists = adAction.searchUser(keyWord, searchType);
		myTableModel =  new SUserTableModel(table,lists);
		myTableModel.initData();
	}
	
	private void action(){
		
		formerPage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myTableModel.formerPage();
				selectedRowIndex =-1;
			}
		});
		
		nextPage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    myTableModel.nextPage();
			    selectedRowIndex=-1;
			}
		});
		
		//ɾ���û�
		delTheUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedRowIndex==-1)
					JOptionPane.showConfirmDialog(null, "��ѡ���û�����", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
				else{
					//ɾ�����û�
					int i = JOptionPane.showConfirmDialog(null, "�Ƿ�ɾ���û�����", " ��ʾ��Ϣ!",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(i==0){//ɾ��
						String ID = (String)table.getValueAt(selectedRowIndex, 0);
						 if(adAction.delUser(ID)){
							 myTableModel.updateDelData(selectedRowIndex);
							 JOptionPane.showConfirmDialog(null, "ɾ���ɹ�����", "��ʾ��Ϣ", 
									 JOptionPane.PLAIN_MESSAGE);
							 selectedRowIndex=-1;
						 }
					}
				}
			}
		});
		
		//���û�������Ϣ
		sendMessage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedRowIndex==-1)
					JOptionPane.showConfirmDialog(null, "��ѡ���û�����", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
				else{
					String str= JOptionPane.showInputDialog(null, "������Ϣ","��Ϣ��", 
							JOptionPane.PLAIN_MESSAGE);
					String ID = (String)table.getValueAt(selectedRowIndex, 0);
					System.out.println(str);
					if(!str.equals(null)){
						adAction.sendMessage(str, ID);
						JOptionPane.showConfirmDialog(null, "���ͳɹ���", "��ʾ��Ϣ", JOptionPane.PLAIN_MESSAGE);
					}
					 
				}
			}
		});
		
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				searchUserResultView = null;
			}
		});
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
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
	}
	
	private void initialize() {
		frame = new JFrame();
		BorderLayout borderLayout = (BorderLayout) frame.getContentPane().getLayout();
		borderLayout.setHgap(46);
		InitWindow.init(frame);
		// ����panel��Ϊ����,�ؼ���������
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 800);
		panel.setLayout(null);
		panel.setOpaque(false);// ����Ϊ͸�������Կ���ͼƬ
		frame.getContentPane().add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(92, 61, 900, 600);
		panel.add(scrollPane);
		
		table = new JTable();
		//�����಻�������ݴ�С�ı�
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    table.setRowHeight(70);
		scrollPane.setViewportView(table);
		
		formerPage = new JButton("\u4E0A\u4E00\u9875");
		formerPage.setBackground(new Color(0, 128, 128));
		formerPage.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
		formerPage.setBounds(327, 682, 113, 45);
		panel.add(formerPage);
		
		nextPage = new JButton("\u4E0B\u4E00\u9875");
		nextPage.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
		nextPage.setBackground(new Color(0, 128, 128));
		nextPage.setBounds(621, 682, 113, 45);
		panel.add(nextPage);
		
		sendMessage = new JButton("\u53D1\u9001\u6D88\u606F");
		sendMessage.setBackground(new Color(102, 205, 170));
		sendMessage.setBounds(1042, 223, 113, 45);
		panel.add(sendMessage);
		
		delTheUser = new JButton("\u5220\u9664\u6B64\u7528\u6237");
		delTheUser.setBackground(new Color(240, 128, 128));
		delTheUser.setBounds(1042, 336, 113, 45);
		panel.add(delTheUser);
		
		MyButton myButton = new MyButton("haha");
		myButton.setBackground(new Color(240, 128, 128));
		myButton.setBounds(1042, 600, 113, 45);
		panel.add(myButton);
		
		cancel = new JButton("\u9000\u51FA");
		cancel.setBackground(Color.RED);
		cancel.setBounds(1093, 0, 89, 27);
		panel.add(cancel);
	}

	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
