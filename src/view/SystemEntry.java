package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dao.*;
import fileOpreation.BorrowBookFormOp;
import fileOpreation.UserFormOp;
import model.Balance;
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
import java.util.List;
import javax.swing.JLabel;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 * 系统进入类
 * 系统开始的唯一接口
 * 通过此类进入系统
 * 程序开始执行后此页面出现
 * 同时开始模拟时间的变化
 * 点击进入系统时停止计时，可以开始文件的操作
 * 
 * 执行文件copy操作（备份文件操作）
 * @author 宽伟
 */

public class SystemEntry {

	public static JFrame frame;
	private JButton in ;
	private static JLabel time;

	public static Date date;//系统时间
	public static int days=0;

	//为更新余额信息
	//统计借书表中的所有用户系统开始时因借书产生的费用，再统计结束运行时的费用
	//两者相减得到此次产生的费用，相减加到用户的余额信息上
	public static List<Balance> balanceList1;
	public static List<Balance> balanceList2;

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
	}

	//其它类调用 
	public static void getInstance(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		BorrowBookFormOp borrowBookFormOp = BorrowBookFormOp.getInstance();
		balanceList1 = borrowBookFormOp.getBalanceList();
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					// 日期增加一天
					date = getPreDoneScore(date);
					days++;
					time.setText(sdf.format(date));
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
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
		//获取时间
		date = new Date(System.currentTimeMillis());
		initialize();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		timeAdvance();//模拟时间推进
		action();
	}

	//退出系统或者进入系统时更新各项数据，借书表，用户余额
	private void updateDate(){
		BorrowBookFormOp borrowBookFormOp = BorrowBookFormOp.getInstance();
		borrowBookFormOp.dateGrowth(days);//更新借书表中的数据
		balanceList2 = borrowBookFormOp.getBalanceList();
		UserFormOp userFormOp = UserFormOp.getInstance();
		userFormOp.updateBalance(balanceList1,balanceList2);
		days=0;
	}
	
	/**
	 * 模拟时间推进，
	 * 更改借书文件（借书时间等？）
	 * 等。。。
	 */
	public void timeAdvance(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		BorrowBookFormOp borrowBookFormOp = BorrowBookFormOp.getInstance();
		balanceList1 = borrowBookFormOp.getBalanceList();
		thread= new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					//日期增加一天
					days++;
					date = getPreDoneScore(date);
					time.setText(sdf.format(date));
					// TODO 更改借书表数据
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
		//进入系统
		in.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				SignInView signInView =  SignInView.getInstance();
				signInView.getFrame().setVisible(true);
				frame.setVisible(false);
				thread.stop();
				// TODO 将信息重新写入文件,更新借书表的信息，和用户的余额信息
				updateDate();
			}
		});
		
		//退出系统
		cancel.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO 将信息重新写入文件
				frame.dispose();
				thread.stop();
				updateDate();
			}
		});
	}

	private void initialize() {
		frame = new JFrame();
		InitWindow.init(frame);
		
		// 设置panel作为容器,控件加入其中
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 800);
		panel.setLayout(null);
		panel.setOpaque(false);// 设置为透明，可以看到图片

		frame.getContentPane().add(panel);
		
		in = new JButton("\u8FDB\u5165\u7CFB\u7EDF");
		in.setBackground(new Color(0, 139, 139));
		in.setFont(new Font("宋体", Font.PLAIN, 40));
		in.setBounds(514, 365, 213, 88);
		panel.add(in);
		
		JLabel label = new JLabel("\u5F53\u524D\u65F6\u95F4");
		label.setFont(new Font("华文楷体", Font.PLAIN, 35));
		label.setBounds(353, 193, 157, 59);
		panel.add(label);
		
	    time = new JLabel("New label");
		time.setFont(new Font("宋体", Font.PLAIN, 30));
		time.setBounds(626, 193, 253, 59);
		panel.add(time);
		
		cancel = new JButton("\u9000\u51FA");
		cancel.setFont(new Font("宋体", Font.PLAIN, 25));
		cancel.setBackground(new Color(0, 139, 139));
		cancel.setBounds(1012, 29, 113, 47);
		panel.add(cancel);
	}
	
	// Date是java.sql.Date类型
	@SuppressWarnings("static-access")
	protected static Date getPreDoneScore(Date holdDate) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(holdDate);
		calendar.add(calendar.DATE, -7);
		// calendar的time转成java.util.Date格式日期
		java.util.Date utilDate = (java.util.Date) calendar.getTime();
		calendar.add(calendar.DATE, 8);
		utilDate = (java.util.Date) calendar.getTime();
		// java.util.Date日期转换成转成java.sql.Date格式
		Date newDate = new Date(utilDate.getTime());
		return newDate;
	}
}
