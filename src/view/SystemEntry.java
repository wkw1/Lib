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
 * 系统进入类
 * 系统开始的唯一接口
 * 通过此类进入系统
 * 程序开始执行后此页面出现
 * 同时开始模拟时间的变化
 * 点击进入系统时停止计时，可以开始文件的操作
 * 
 * 执行文件copy操作（备份文件操作）
 * @author 宽伟
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
		timeAdvance();//模拟时间推进
		action();
	}
	/**
	 * 模拟时间推进，
	 * 更改借书文件（借书时间等？）
	 * 等。。。
	 */
	public void timeAdvance(){
		// TODO 模拟时间推进
	}
	
	private void action(){
		//进入系统
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
	}
}
