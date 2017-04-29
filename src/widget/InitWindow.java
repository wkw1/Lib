package widget;


import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * 
 * 初始化窗口函数
 * 提出公共代码
 * @author 宽伟
 *
 */

public class InitWindow {
	
	public static void init(JFrame frame){
		
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("图书馆管理系统");

		// 加载图片
		ImageIcon icon = new ImageIcon("images/11.jpg");
		//将图片放入label中
		JLabel label = new JLabel(icon);
		// 设置label的大小
		label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		// 获取窗口的第二层，将label放入
		frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		// 获取frame的顶层容器,并设置为透明
		JPanel j = (JPanel) frame.getContentPane();
		j.setOpaque(false);

		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

	}

}
