package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import widget.InitWindow;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
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

	private JFrame frame;
	private JButton in ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SystemEntry window = new SystemEntry();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SystemEntry() {
		initialize();
		timeAdvance();//ģ��ʱ���ƽ�
		action();
	}
	/**
	 * ģ��ʱ���ƽ���
	 * ���Ľ����ļ�������ʱ��ȣ���
	 * �ȡ�����
	 */
	public void timeAdvance(){
		// TODO ģ��ʱ���ƽ�
	}
	
	private void action(){
		//����ϵͳ
		in.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SignInView signInView =  SignInView.getInstance();
				signInView.getFrame().setVisible(true);
				
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
		
		in = new JButton("\u8FDB\u5165\u7CFB\u7EDF");
		in.setBackground(new Color(0, 139, 139));
		in.setFont(new Font("����", Font.PLAIN, 40));
		in.setBounds(514, 365, 213, 88);
		panel.add(in);
	}
}
