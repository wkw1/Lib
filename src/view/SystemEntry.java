package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dao.*;
import fileOpreation.BorrowBookFormOp;
import model.BBHModel;
import widget.InitWindow;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.Color;
import javax.swing.JLabel;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 * ϵͳ������
 * ϵͳ��ʼ��Ψһ�ӿ�
 * ͨ���������ϵͳ
 * ����ʼִ�к��ҳ�����
 * ͬʱ��ʼģ��ʱ��ı仯
 * �������ϵͳʱֹͣ��ʱ�����Կ�ʼ�ļ��Ĳ���
 * 
 * ִ���ļ�copy�����������ļ�������
 * @author ��ΰ
 */

public class SystemEntry {

	public static JFrame frame;
	private JButton in ;
	private static JLabel time;

	public static Date date;//ϵͳʱ��
	public static int days=0;

	//Ϊ���������Ϣ
	//ͳ�ƽ�����е������û�ϵͳ��ʼʱ���������ķ��ã���ͳ�ƽ�������ʱ�ķ���
	//��������õ��˴β����ķ��ã�����ӵ��û��������Ϣ��
	//public static List<Balance> balanceList1;
	//public static List<Balance> balanceList2;

	private static Thread thread;
	private JButton cancel;
	
	public static void main(String[] args) throws IOException, ParseException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					SystemEntry window = new SystemEntry();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		readFile();
	}

	private static void readFile() throws IOException, ParseException {
		BookDao bookDao = BookDao.getInstance();
		bookDao.readBookForm();
		UserDao userDao = UserDao.getInstance();
		userDao.readUserForm();
		BorrowBookDao borrowBookDao = BorrowBookDao.getInstance();
		borrowBookDao.readBookForm();
		OrderBookDao orderBookDao = OrderBookDao.getInstance();
		orderBookDao.readBookForm();
		InfoDao infoDao = InfoDao.getInstance();
		infoDao.readInfoForm();
		SearchKeyDao searchKeyDao = SearchKeyDao.getInstance();
		searchKeyDao.readUserForm();
	}

	//��������� 
	public static void getInstance(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//BorrowBookFormOp borrowBookFormOp = BorrowBookFormOp.getInstance();
		//balanceList1 = borrowBookFormOp.getBalanceList();
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					// ��������һ��
					date = getPreDoneScore(date);
					days++;
					time.setText(sdf.format(date));
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		thread.start();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	/**
	 * Create the application.
	 */
	public SystemEntry() {
		//��ȡʱ��
		SystemInfoDao.readUserForm();
		date =SystemInfoDao.SysTemDate;
		initialize();
		LogDao.addLogSystem("ϵͳ����");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		timeAdvance();//ģ��ʱ���ƽ�
		action();
	}

	//�˳�ϵͳ���߽���ϵͳʱ���¸������ݣ�������û����
	private void updateDate(){
		BorrowBookFormOp borrowBookFormOp = BorrowBookFormOp.getInstance();
		borrowBookFormOp.dateGrowth(days);//���½�����е�����
		LogDao.addLogSystem("�˳�ϵͳ");
		//balanceList2 = borrowBookFormOp.getBalanceList();
		//UserFormOp userFormOp = UserFormOp.getInstance();
		//userFormOp.updateBalance(balanceList1,balanceList2);
		days=0;
	}
	
	/**
	 * ģ��ʱ���ƽ���
	 * ���Ľ����ļ�������ʱ��ȣ���
	 * �ȡ�����
	 */
	public void timeAdvance(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//BorrowBookFormOp borrowBookFormOp = BorrowBookFormOp.getInstance();
		//balanceList1 = borrowBookFormOp.getBalanceList();
		thread= new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					//��������һ��
					days++;
					date = getPreDoneScore(date);
					time.setText(sdf.format(date));
					// TODO ���Ľ��������
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}
	
	private void action(){
		//����ϵͳ
		in.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				SignInView signInView =  SignInView.getInstance();
				signInView.getFrame().setVisible(true);
				LogDao.addLogSystem("������������ʱֹͣ");
				frame.setVisible(false);
				thread.stop();
				updateDate();
			}
		});
		
		//�˳�ϵͳ
		cancel.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				thread.stop();
				updateDate();
				try {
					restoreData();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	//������д���ļ�
	public void restoreData() throws IOException {
		// TODO ����Ϣ����д���ļ�
		//ͼ���
		BookDao bookDao = BookDao.getInstance();//ͼ�������
		if(bookDao.iSModify)
			bookDao.writeFile();//��д�����б�
		else if(bookDao.iSAdd)
			bookDao.addBooks();
		//��Ϣ��
		InfoDao info = InfoDao.getInstance();//��Ϣ����
		if(info.iSModify)
			info.writeFile();
		else if(info.iSAdd)
			info.addInfos();
		//�û���
		UserDao userDao = UserDao.getInstance();
		if(userDao.iSModify)
			userDao.writeFile();//��д�����б�
		else if(userDao.iSAdd)
			userDao.addUsers();
		//������ʷ��
		BBHDao bbhDao = BBHDao.getInstance();
		if(bbhDao.iSAdd)
			bbhDao.addRecords();
		//�����
		BorrowBookDao borrowBookDao = BorrowBookDao.getInstance();
		if(borrowBookDao.iSModify)
			borrowBookDao.writeFile();
		else if(borrowBookDao.iSAdd)
			borrowBookDao.addBBooks();
		//ԤԼ��
		OrderBookDao orderBookDao = OrderBookDao.getInstance();
		if(orderBookDao.iSModify)
			orderBookDao.writeFile();
		else if(orderBookDao.iSAdd)
			orderBookDao.addOBooks();
		//������
		SearchKeyDao searchKeyDao = SearchKeyDao.getInstance();
		if(searchKeyDao.iSModify)
			searchKeyDao.writeFile();
		else if(searchKeyDao.iSAdd)
			searchKeyDao.addKeywords();
	}

	private void initialize() {
		frame = new JFrame();
		InitWindow.init(frame);
		
		// ����panel��Ϊ����,�ؼ���������
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 800);
		panel.setLayout(null);
		panel.setOpaque(false);// ����Ϊ͸�������Կ���ͼƬ

		frame.getContentPane().add(panel);
		
		in = new JButton("\u8FDB\u5165\u7CFB\u7EDF");
		in.setBackground(new Color(0, 139, 139));
		in.setFont(new Font("���Ŀ���", Font.PLAIN, 40));
		in.setBounds(295, 398, 213, 88);
		panel.add(in);
		
		JLabel label = new JLabel("\u5F53\u524D\u65F6\u95F4");
		label.setFont(new Font("���Ŀ���", Font.PLAIN, 35));
		label.setBounds(353, 193, 157, 59);
		panel.add(label);
		
	    time = new JLabel("New label");
		time.setFont(new Font("����", Font.PLAIN, 30));
		time.setBounds(626, 193, 253, 59);
		panel.add(time);
		
		cancel = new JButton("\u9000\u51FA");
		cancel.setFont(new Font("���Ŀ���", Font.PLAIN, 40));
		cancel.setBackground(new Color(0, 139, 139));
		cancel.setBounds(717, 402, 213, 80);
		panel.add(cancel);
	}
	
	// Date��java.sql.Date����
	@SuppressWarnings("static-access")
	protected static Date getPreDoneScore(Date holdDate) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(holdDate);
		calendar.add(calendar.DATE, -7);
		// calendar��timeת��java.util.Date��ʽ����
		java.util.Date utilDate = (java.util.Date) calendar.getTime();
		calendar.add(calendar.DATE, 8);
		utilDate = (java.util.Date) calendar.getTime();
		// java.util.Date����ת����ת��java.sql.Date��ʽ
		Date newDate = new Date(utilDate.getTime());
		return newDate;
	}
}
