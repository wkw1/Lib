package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import widget.InitWindow;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.Color;
import javax.swing.JLabel;
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
 *
 */

public class SystemEntry {

	public static JFrame frame;
	private JButton in ;
	private static JLabel time;

	public static Date date;//ϵͳʱ��
	
	private static Thread thread;
	private JButton cancel;
	
	public static void main(String[] args) {
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
	}
	
	//��������� 
	public static void getInstance(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		thread = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					// ��������һ��
					date = getPreDoneScore(date);
					//System.out.println(date);
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
	}

	/**
	 * Create the application.
	 */
	public SystemEntry() {
		//��ȡʱ��
		date = new Date(System.currentTimeMillis());
		initialize();
		frame.setVisible(true);
		timeAdvance();//ģ��ʱ���ƽ�
		action();
	}
	
	/**
	 * ģ��ʱ���ƽ���
	 * ���Ľ����ļ�������ʱ��ȣ���
	 * �ȡ�����
	 */
	public void timeAdvance(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		thread= new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					//��������һ��
					date = getPreDoneScore(date);
					//System.out.println(date);
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
	}
	
	private void action(){
		//����ϵͳ
		in.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				SignInView signInView =  SignInView.getInstance();
				signInView.getFrame().setVisible(true);
				frame.setVisible(false);
				thread.stop();
			}
		});
		
		//�˳�ϵͳ
		cancel.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.dispose();
				thread.stop();
			}
		});
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
		in.setFont(new Font("����", Font.PLAIN, 40));
		in.setBounds(514, 365, 213, 88);
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
		cancel.setFont(new Font("����", Font.PLAIN, 25));
		cancel.setBackground(new Color(0, 139, 139));
		cancel.setBounds(1012, 29, 113, 47);
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
