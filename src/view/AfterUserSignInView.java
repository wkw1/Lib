package view;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;

import model.UserModel;
import widget.InitWindow;
import javax.swing.JComboBox;
import java.awt.Font;
/**
 * �û���¼��ҳ��
 * @author ��ΰ
 *
 */

public class AfterUserSignInView {

	private JFrame frame;
	private JTextField keyWord;
	private JLabel welcome;
	private JComboBox<String> searchType;
	private JButton search;
	private JButton myBorrow;
	private JButton myorder ;
	private JButton signOut;
	private JButton myBorrowHistory;
	private JButton infoMore ;
	
	private JTextArea myInfo ;
	private JTextArea searchRanking;
	private JTextArea info;

	/**
	 * Create the application.
	 */
	public AfterUserSignInView() {
		initialize();
		getData();
		action();
	}
	
	private void getData(){
		//�ҵ���������Ϣ����
		myInfo.setText("ID:"+UserModel.getIstance().getID()+"\n");
		//�����������ϸ���
		searchRanking.setText("\n  ����� \n  Java\n  C++ \n  "
				+ "Python\n  ��������� \n  ���ݿ�\n  ����ϵͳ");
		//��Ϣ֪ͨ
		info.setText("������Ϣ������");
		
	}

	private void action(){
		
		welcome.setText("��ӭ:  "+UserModel.getIstance().getID()+" !");
		/**
		 * ���������ť����ת���������ҳ��
		 * ��ִ�в���
		 * 1 ��ȡ��ѯ������  searchType
		 * 2 ��ȡ��ѯ�ؼ��� keyWord
		 * 3 �ж��Ƿ�������Ϣ
		 */
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//��������ҳ�� ���ݹؼ��ֺ���������,����ģʽ
				SearchResultView window = SearchResultView.getInstance
						( keyWord.getText().replaceAll("\\s", ""),
								(String) searchType.getSelectedItem(),1);
				window.getFrame().setVisible(true);
			}
		});
		
		/**
		 * ����ҵĽ��İ�ť
		 * ��ѯ�����õ��ҵĽ������
		 * ��ת����һ��ҳ��
		 * 
		 */
		myBorrow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MyBorrowView myBorrowView = MyBorrowView.getInstance();
				myBorrowView.getFrame().setVisible(true);
			}
		});
		//����ҵ�ԤԼ
		myorder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MyOrderView myOrderView = MyOrderView.getInstance();
				myOrderView.getFrame().setVisible(true);
			}
		});
		
		myBorrowHistory.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MyBBHView myBBHView = new MyBBHView();
				myBBHView.getFrame().setVisible(true);
				
			}
		});
		
		//�˳���¼
		signOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.dispose();
				SignInView signInView = new SignInView();
				signInView.getFrame().setVisible(true);
			}
		});
		
		//������Ϣ
		infoMore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showConfirmDialog(null, 
						"û\nr\nr\ne\ne\n\n\nrer", "����", JOptionPane.PLAIN_MESSAGE);
			}
		});
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		InitWindow.init(frame);//��ʼ������
		
		//����panel��Ϊ����,�ؼ���������
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 800);
		panel.setLayout(null);
		panel.setOpaque(false);//����Ϊ͸�������Կ���ͼƬ
		frame.getContentPane().add(panel);
		
		
		myBorrow = new JButton("\u6211\u7684\u501F\u9605");
		myBorrow.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
		myBorrow.setBounds(0, 46, 203, 57);
		panel.add(myBorrow);
		
		myorder = new JButton(" \u6211\u7684\u9884\u7EA6");
		myorder.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
		myorder.setForeground(new Color(0, 0, 0));
		myorder.setBackground(new Color(204, 153, 0));
		myorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		myorder.setBounds(0, 122, 203, 57);
		panel.add(myorder);
		
		myBorrowHistory = new JButton("\u501F\u4E66\u5386\u53F2");
		myBorrowHistory.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
		myBorrowHistory.setBounds(0, 192, 203, 52);
		panel.add(myBorrowHistory);
		
		myInfo = new JTextArea();
		myInfo.setEditable(false);
		myInfo.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		myInfo.setBackground(new Color(60, 179, 113));
		myInfo.setText("\u6211\u7684\u8D44\u6599");
		myInfo.setBounds(0, 257, 203, 498);
		panel.add(myInfo);
		
		
		keyWord = new JTextField();
		keyWord.setFont(new Font("��������", Font.PLAIN, 20));
		keyWord.setBounds(393, 102, 166, 44);
		keyWord.setColumns(10);
		panel.add(keyWord);
		
		JLabel label1 = new JLabel("\u641C\u7D22\u7C7B\u578B");
		label1.setFont(new Font("���Ŀ���", Font.PLAIN, 18));
		label1.setBounds(284, 55, 104, 34);
		panel.add(label1);
		
		JLabel label2 = new JLabel("\u5173\u952E\u5B57");
		label2.setFont(new Font("���Ŀ���", Font.PLAIN, 18));
		label2.setBounds(284, 102, 104, 44);
		panel.add(label2);
		
		searchRanking= new JTextArea();
		searchRanking.setFont(new Font("���Ŀ���", Font.PLAIN, 25));
		searchRanking.setToolTipText("");
		searchRanking.setEditable(false);
		searchRanking.setBackground(new Color(188, 143, 143));
		searchRanking.setText("\u641C\u7D22\u6392\u540D");
		searchRanking.setBounds(284, 257, 377, 498);
		panel.add(searchRanking);
		
		info = new JTextArea();
		info.setFont(new Font("�����п�", Font.PLAIN, 25));
		info.setEditable(false);
		info.setBackground(new Color(153, 153, 255));
		info.setBounds(795, 70, 385, 260);
		panel.add(info);
		
		JTextArea systemInfo = new JTextArea();
		systemInfo.setFont(new Font("������κ", Font.PLAIN, 25));
		systemInfo.setEditable(false);
		systemInfo.setBackground(new Color(173, 216, 230));
		systemInfo.setText("\u7CFB\u7EDF\u4ECB\u7ECD");
		systemInfo.setBounds(795, 376, 385, 379);
		panel.add(systemInfo);
		
		signOut = new JButton("\u9000\u51FA\u767B\u5F55");
		signOut.setFont(new Font("���Ŀ���", Font.PLAIN, 15));
		signOut.setBounds(1041, 30, 125, 27);
		panel.add(signOut);
		
	    search = new JButton("\u641C\u7D22");
	    search.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		search.setBounds(384, 173, 125, 34);
		panel.add(search);
		
		JLabel label_2 = new JLabel("\u6211\u7684\u56FE\u4E66\u9986");
		label_2.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		label_2.setBounds(0, 0, 125, 33);
		panel.add(label_2);
		
		welcome = new JLabel("\u6B22\u8FCE");
		welcome.setFont(new Font("���Ŀ���", Font.PLAIN, 20));
		welcome.setBounds(1041, 7, 125, 18);
		panel.add(welcome);
		
	    searchType = new JComboBox<String>();
		searchType.setBounds(393, 55, 166, 34);
		panel.add(searchType);
		searchType.addItem("ISBN");
		searchType.addItem("����");
		searchType.addItem("������");
		searchType.addItem("����");
		
		JLabel label = new JLabel("\u6D88\u606F\u901A\u77E5");
		label.setFont(new Font("���Ŀ���", Font.PLAIN, 15));
		label.setBounds(939, 49, 72, 18);
		panel.add(label);
		
		infoMore = new JButton("\u66F4\u591A");
		infoMore.setFont(new Font("���Ŀ���", Font.PLAIN, 15));
		infoMore.setBackground(new Color(70, 130, 180));
		infoMore.setBounds(1096, 336, 84, 27);
		panel.add(infoMore);
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
